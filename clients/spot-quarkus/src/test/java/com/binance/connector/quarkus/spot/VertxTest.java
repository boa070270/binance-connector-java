package com.binance.connector.quarkus.spot;

import com.binance.connector.quarkus.spot.model.AccountStatusRequest;
import io.vertx.core.Vertx;
import io.vertx.core.http.WebSocket;
import io.vertx.core.http.WebSocketClient;
import io.vertx.core.http.WebSocketClientOptions;
import io.vertx.core.json.Json;

public class VertxTest {
    Vertx vertx = Vertx.vertx();
    boolean finish = false;
    boolean firstMsg = true;
    WebSocket ws;
//    JdkPrivateKey privateKey = new JdkPrivateKey("Ed25519", System.getenv("PRIVATE_KEY"));
    HmacSignatureGenerator privateKey = new HmacSignatureGenerator(System.getenv("BIN_SEC"));
    public VertxTest() {}
    void runTest() {
        WebSocketClientOptions options = new WebSocketClientOptions()
                .setSsl(true); // WSS на 443

        WebSocketClient client = vertx.createWebSocketClient(options);
        AccountStatusRequest request = new AccountStatusRequest();
//        request.setApiKey(System.getenv("API_KEY"));
        request.setApiKey(System.getenv("BIN_API"));
        request.setTimestamp(""+System.currentTimeMillis());
        request.setSignature(privateKey.signAsString(request.toUrlQueryString()));
        // приклад: один raw stream (наприклад, BTCUSDT aggTrade)
        client.connect(443, "ws-api.binance.com", "/ws-api/v3")
                .onSuccess(ws -> {
                    System.out.println("Connected!");
                    this.ws = ws;
                    ws.textMessageHandler(msg -> {
                        System.out.println("Received: " + msg);
                        if (finish) {
                            System.out.println("Time to close");
                            ws.close()
                                    .onComplete(x -> vertx.close());
                        }
                    });

                    ws.exceptionHandler(err -> {
                        System.err.println("WS error: " + err.getMessage());
                        vertx.close();
                    });

                    ws.closeHandler(v -> vertx.close());
                    ws.pongHandler(v -> System.err.println("Pong"));
                })
                .onFailure(err -> {
                    System.err.println("Connection failed: " + err.getMessage());
                    vertx.close(); // критично: інакше JVM буде "висіти"
                });
        try {
            while (ws == null) {
                Thread.sleep(100);
            }
            String msg = "{\"id\":0,\"method\":\"account.status\",\"params\":"+Json.encode(request)+"}";
            System.out.println("Sending: " + msg);
            ws.writeTextMessage(msg);
            Thread.sleep(5000);
            finish = true;
            ws.close();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }
    public static void main(String[] args) {
        VertxTest test = new VertxTest();
        test.runTest();
    }
}
