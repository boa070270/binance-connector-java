package com.binance.connector.client.spot.websocket.stream;
import com.binance.connector.client.spot.websocket.BinanceQuarkusConfig;
import io.quarkus.runtime.Startup;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.Vertx;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.mutiny.core.http.HttpClient;
import io.vertx.mutiny.core.http.WebSocket;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.net.URI;

@ApplicationScoped
@Startup
public class BinancePublicWsClient {

    private static final Logger LOG = Logger.getLogger(BinancePublicWsClient.class);

    @Inject
    Vertx vertx;

    @Inject
    BinanceQuarkusConfig cfg;

    private HttpClient client;
    private WebSocket ws;

    @PostConstruct
    void init() {
        URI uri = URI.create(cfg.ws().endpoint());
        HttpClientOptions options = new HttpClientOptions()
                .setSsl("wss".equalsIgnoreCase(uri.getScheme()))
                .setVerifyHost(true)
                .setDefaultHost(uri.getHost())
                .setDefaultPort(uri.getPort() > 0 ? uri.getPort() : 443);

        this.client = vertx.createHttpClient(options);

        // Наприклад, aggTrade BTCUSDT + bookTicker BTCUSDT
        String streams = "btcusdt@aggTrade/btcusdt@bookTicker";
        connectMultiStream(streams)
                .subscribe().with(
                        success -> LOG.info("Binance public WS connected"),
                        failure -> LOG.error("Binance public WS connect failed", failure)
                );
    }

    private Uni<Void> connectMultiStream(String streams) {
        String host = "stream.binance.com";
        int port = 9443;
        String path = "/stream?streams=" + streams;

        return client.webSocket(port, host, path)
                .onItem().invoke(socket -> {
                    this.ws = socket;
                    LOG.infof("Connected to %s%s", host, path);

                    socket.textMessageHandler(this::handleMessage);
                    socket.exceptionHandler(err ->
                            LOG.error("WS error: " + err.getMessage(), err));
                    socket.closeHandler(new Runnable() {
                        @Override
                        public void run() {
                            LOG.warn("Binance WS closed, will reconnect");
                            reconnect(streams);
                        }
                    });
                })
                .replaceWithVoid();
    }

    private void reconnect(String streams) {
        // дуже проста стратегія; можна зробити розумний backoff
        connectMultiStream(streams)
                .subscribe().with(
                        v -> LOG.info("Reconnected Binance WS"),
                        e -> LOG.error("Reconnect failed", e)
                );
    }

    private void handleMessage(String text) {
        // Тут сирий JSON від Binance multi-stream
        // Формат: {"stream":"btcusdt@aggTrade","data":{...}}
        LOG.debugf("Binance WS msg: %s", text);

        // Далі – або Jackson/Jakarta JSON, або мапа у твій internal event і fire()
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
