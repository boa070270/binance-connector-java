package com.binance.connector.client.spot.websocket.stream;

import com.binance.connector.client.spot.websocket.BinanceQuarkusConfig;
import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.http.HttpClient;
import io.vertx.mutiny.core.http.WebSocket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class BinancePublicWsClientTest {

    private Vertx vertx;
    private BinanceQuarkusConfig config = new BinanceQuarkusConfig() {

        @Override
        public Ws ws() {
            return new Ws() {
                @Override
                public String endpoint() {
                    return "wss://stream.binance.com:9443";
                }

                @Override
                public String single() {
                    return "/ws/";
                }

                @Override
                public String multi() {
                    return "/stream";
                }
            };
        }

        @Override
        public Api api() {
            return new Api() {
                @Override
                public String endpoint() {
                    return "wss://ws-api.binance.com:443/ws-api/v3";
                }

                @Override
                public String endpointTest() {
                    return "wss://ws-api.testnet.binance.vision/ws-api/v3";
                }

                @Override
                public String key() {
                    return "";
                }

                @Override
                public String secret() {
                    return "";
                }

                @Override
                public boolean useTestNet() {
                    return true;
                }

                @Override
                public double recvWindow() {
                    return 500;
                }
            };
        }
    };

    @BeforeEach
    void setUp() {
        vertx = Vertx.vertx();   // правильна ініціалізація
    }

    @AfterEach
    void tearDown() {
        vertx.close();
    }

    @Test
    public void testInitRealConnection() {
        // Arrange
        BinancePublicWsClient client = new BinancePublicWsClient();
        client.vertx = vertx;
        client.cfg = config;
        // Act
        client.init();
        // Assert
        try {
            Thread.currentThread().sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void testInitSuccessfulConnection() {
        // Arrange
        Vertx mockVertx = mock(Vertx.class);
        HttpClient mockHttpClient = mock(HttpClient.class);
        WebSocket mockWebSocket = mock(WebSocket.class);

        when(mockVertx.createHttpClient(any(HttpClientOptions.class))).thenReturn(mockHttpClient);
        when(mockHttpClient.webSocket(
                eq(9443),
                eq("stream.binance.com"),
                eq("/stream?streams=btcusdt@aggTrade/btcusdt@bookTicker"))
        ).thenReturn(Uni.createFrom().item(mockWebSocket));

        BinancePublicWsClient client = new BinancePublicWsClient();
        client.vertx = mockVertx;
        client.cfg = config;
//        BinancePublicWsClient.LOG = mockLogger;

        // Act
        client.init();

        // Assert
        verify(mockHttpClient).webSocket(
                eq(9443),
                eq("stream.binance.com"),
                eq("/stream?streams=btcusdt@aggTrade/btcusdt@bookTicker")
        );
    }

    @Test
    public void testInitFailedConnection() {
        // Arrange
        Vertx mockVertx = mock(Vertx.class);
        HttpClient mockHttpClient = mock(HttpClient.class);

        when(mockVertx.createHttpClient(any(HttpClientOptions.class))).thenReturn(mockHttpClient);
        when(mockHttpClient.webSocket(
                eq(9443),
                eq("stream.binance.com"),
                eq("/stream?streams=btcusdt@aggTrade/btcusdt@bookTicker"))
        ).thenReturn(Uni.createFrom().failure(new RuntimeException("Connection failed")));

        BinancePublicWsClient client = new BinancePublicWsClient();
        client.vertx = mockVertx;
        client.cfg = config;
//        BinancePublicWsClient.LOG = mockLogger;

        // Act
        client.init();

        // Assert
        verify(mockHttpClient).webSocket(
                eq(9443),
                eq("stream.binance.com"),
                eq("/stream?streams=btcusdt@aggTrade/btcusdt@bookTicker")
        );
    }
}