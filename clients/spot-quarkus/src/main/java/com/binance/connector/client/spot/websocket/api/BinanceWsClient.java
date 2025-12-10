package com.binance.connector.client.spot.websocket.api;

import com.binance.connector.client.spot.websocket.BinanceQuarkusConfig;
import io.vertx.mutiny.core.Vertx;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.mutiny.core.http.HttpClient;
import io.vertx.mutiny.core.http.WebSocket;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;
import io.vertx.core.json.JsonObject;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

@ApplicationScoped
public class BinanceWsClient {

    private static final Logger LOG = Logger.getLogger(BinanceWsClient.class);

    @Inject Vertx vertx;
    @Inject
    BinanceQuarkusConfig cfg;
    @Inject BinanceWsSigner signer;

    private HttpClient client;
    private volatile WebSocket ws;

    private final Map<String, CompletableFuture<JsonObject>> pending = new ConcurrentHashMap<>();

    @PostConstruct
    void init() {
        URI uri = URI.create(cfg.api().endpoint());
        if (cfg.api().useTestNet())
            uri = URI.create(cfg.api().endpointTest());
        HttpClientOptions options = new HttpClientOptions()
                .setSsl("wss".equalsIgnoreCase(uri.getScheme()))
                .setVerifyHost(true)
                .setDefaultHost(uri.getHost())
                .setDefaultPort(uri.getPort() > 0 ? uri.getPort() : 443);

        client = vertx.createHttpClient(options);
        connect(uri).subscribe().with(
                v -> LOG.info("Binance WS connected"),
                e -> LOG.error("Binance WS initial connect failed", e)
        );
    }

    private Uni<Void> connect(URI uri) {
        String path = uri.getPath().isEmpty() ? "/" : uri.getPath();
        return client.webSocket(path)
                .onItem().invoke(socket -> {
                    this.ws = socket;
                    socket.textMessageHandler(this::handleMessage);
                    socket.exceptionHandler(err -> LOG.error("WS error", err));
                    socket.closeHandler(() -> {
                        LOG.warn("WS closed, reconnecting");
                        // простий reconnect; можна зробити backoff
                        connect(uri).subscribe().with(
                                x -> LOG.info("Reconnected"),
                                e -> LOG.error("Reconnect failed", e)
                        );
                    });
                })
                .replaceWithVoid();
    }

    public Uni<JsonObject> call(String method, JsonObject params) {
        String id = UUID.randomUUID().toString();

        JsonObject request = new JsonObject()
                .put("id", id)
                .put("method", method)
                .put("params", params);

        CompletableFuture<JsonObject> future = new CompletableFuture<>();
        pending.put(id, future);

        return Uni.createFrom().item(() -> {
                    if (ws == null || ws.isClosed()) {
                        throw new IllegalStateException("WS not connected");
                    }
                    ws.writeTextMessage(request.encode());
                    return future;
                })
                .onItem().transformToUni(f -> Uni.createFrom().completionStage(f));
    }

    private void handleMessage(String text) {
        JsonObject msg = new JsonObject(text);
        String id = msg.getString("id");
        if (id != null) {
            CompletableFuture<JsonObject> fut = pending.remove(id);
            if (fut != null) {
                fut.complete(msg);
                return;
            }
        }
        // тут можна обробляти async events без id
        LOG.debugf("Unmatched WS message: %s", text);
    }

    @PreDestroy
    void shutdown() {
        if (ws != null && !ws.isClosed()) {
            ws.closeAndForget();
        }
        if (client != null) {
            client.closeAndForget();
        }
    }
}
