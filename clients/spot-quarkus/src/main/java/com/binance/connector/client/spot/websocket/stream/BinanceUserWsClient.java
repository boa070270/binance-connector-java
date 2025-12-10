package com.binance.connector.client.spot.websocket.stream;

import io.quarkus.runtime.Startup;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.Vertx;
import io.vertx.core.http.WebsocketVersion;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.mutiny.core.http.HttpClient;
import io.vertx.mutiny.core.http.WebSocket;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

@ApplicationScoped
public class BinanceUserWsClient {

    private static final Logger LOG = Logger.getLogger(BinanceUserWsClient.class);

    @Inject
    Vertx vertx;

    private HttpClient client;
    private WebSocket ws;

    @PostConstruct
    void init() {
        HttpClientOptions options = new HttpClientOptions()
                .setSsl(true)
                .setVerifyHost(true);
        this.client = vertx.createHttpClient(options);
    }

    public void connect(String listenKey) {
        String host = "stream.binance.com";
        int port = 9443;
        String path = "/ws/" + listenKey;

        client.webSocket(port, host, path)
                .onItem().invoke(socket -> {
                    this.ws = socket;
                    LOG.infof("Connected to userData WS: %s", listenKey);

                    socket.textMessageHandler(this::handleUserDataMessage);
                    socket.closeHandler(new Runnable() {
                        @Override
                        public void run() {
                            LOG.warn("UserData WS closed, consider re-create listenKey");
                            // Можна тригернути повторний createListenKey
                        }
                    });
                    socket.exceptionHandler(err ->
                            LOG.error("UserData WS error", err));
                })
                .subscribe().with(
                        v -> {},
                        e -> LOG.error("Failed to connect userData WS", e)
                );
    }

    private void handleUserDataMessage(String msg) {
        // Тут повідомлення типів: executionReport, outboundAccountPosition, etc.
        LOG.debugf("UserData msg: %s", msg);

        // Розбір JSON і маршрутизація у твій event layer
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
