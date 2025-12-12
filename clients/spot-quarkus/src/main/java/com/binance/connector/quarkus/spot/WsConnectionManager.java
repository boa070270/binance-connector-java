package com.binance.connector.quarkus.spot;

import com.binance.connector.client.common.websocket.adapter.ConnectionInterface;
import com.binance.connector.client.common.websocket.dtos.ApiRequestWrapperDTO;
import com.binance.connector.client.common.websocket.dtos.RequestWrapperDTO;
import com.binance.connector.quarkus.spot.model.BookTicker;
import com.binance.connector.quarkus.spot.model.BookTickersParser;
import io.smallrye.mutiny.Uni;
import io.vertx.core.MultiMap;
import io.vertx.core.http.WebSocketClientOptions;
import io.vertx.core.http.WebSocketConnectOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.http.WebSocket;
import io.vertx.mutiny.core.http.WebSocketClient;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.net.URI;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class WsConnectionManager {
    static final Logger LOG = Logger.getLogger(WsConnectionManager.class);
    static final String STREAM_ENDPOINT = "wss://stream.binance.com:443";
    static final String API_ENDPOINT = "wss://ws-api.binance.com:443/ws-api/v3";
    static final String MARKET_ENDPOINT = "wss://data-stream.binance.vision";
    @Inject
    Vertx vertx;
    @Inject
    BinanceQuarkusConfig config;
    WebSocketClient client;
    String apiHost;
    int apiPort;
    String streamHost;
    int streamPort;
    WebSocketConnectOptions defaultOptionsApi;
    WebSocketConnectOptions defaultOptionsStreamRaw;
    WebSocketConnectOptions defaultOptionsStreamCombined;
    WebSocketConnectOptions defaultOptionsStreamMarked;
    boolean isShutdown = false;
    String agentName;
    private BookTickersParser bookTickersParser = new BookTickersParser();
    @PostConstruct
    void init() {
        WebSocketClientOptions opts = new WebSocketClientOptions()
                .setSsl(true)
                .setVerifyHost(true)
                .setTryUsePerFrameCompression(true)
                ;
        client = vertx.createWebSocketClient(opts);
        defaultOptionsApi = createOptions(API_ENDPOINT);
        defaultOptionsStreamRaw = createOptions(STREAM_ENDPOINT+"/ws/");
        defaultOptionsStreamCombined = createOptions(STREAM_ENDPOINT+"/stream");
        defaultOptionsStreamMarked = createOptions(MARKET_ENDPOINT);
    }
    private WebSocketConnectOptions createOptions(String url) {
        MultiMap headers = MultiMap.caseInsensitiveMultiMap();
        headers.add("User-Agent", (agentName != null ? agentName: "BinanceJavaClient/1.0"));
        URI uri = URI.create(url);
        return new WebSocketConnectOptions()
                .setSsl(true)
                .setHost(uri.getHost())
                .setPort(uri.getPort())
                .setURI(uri.getPath())
                .setHeaders(headers);
    }

    public Uni<ConnectionInterface> webSocketApi() {
        return client.connect(defaultOptionsApi).onItem().transform(ws -> new BaseApiConnection(defaultOptionsApi, ws));
    }

    public Uni<ConnectionInterface> webSocketStream(String path) {
        return client.connect(createOptions(STREAM_ENDPOINT+path)).onItem().transform(ws -> new BaseApiConnection(defaultOptionsApi, ws));
    }
    abstract class BaseConnection  {
        WebSocket ws;
        AtomicInteger nextId = new AtomicInteger();
        ConcurrentHashMap<Integer, CompletableFuture<JsonObject>> pending = new ConcurrentHashMap<>();
        boolean shutdown = false;
        WebSocketConnectOptions options;
        BaseConnection(WebSocketConnectOptions options, WebSocket ws) {
            this.options = options;
            this.ws = ws;
            this.ws.textMessageHandler(this::handleMessage);
            this.ws.exceptionHandler(err -> LOG.error("WS error", err));
            this.ws.closeHandler(this::connect);
        }
        private void handleMessage(String text) {
            KindOfResponseEnum kind = KindOfResponseEnum.chkKindOfResponse(text);
            switch (kind) {
                case ERROR -> handleErrorMsg(text);
                case PARTIAL_BOOK_DEPTH,STREAM_COMBINE_EVENT,STREAM_CMD,STREAM_RAW_EVENT -> handleStreamEvent(text);
                case USR_DATA_STREAM -> handleUsrDataStreamEvent(text);
                case WEB_SOCKET_API ->handleWebSockApiEvent(text);
                case UNKNOWN -> handleUnknownMsg(text);
                case BOOK_TICKER -> handleBookTicker(bookTickersParser.parse(text));
            }
        }
        protected void handleBookTicker(BookTicker msg) {
            LOG.debug("Got: " + msg);
        }
        protected void handleErrorMsg(String msg) {
            LOG.warn("Got: " + msg);
        }
        protected void handleStreamEvent(String msg) {
            LOG.debug("Got: " + msg);
        }
        protected void handleUsrDataStreamEvent(String msg) {
            LOG.debug("Got: " + msg);
        }
        protected void handleWebSockApiEvent(String msg) {
            LOG.debug("Got: " + msg);
        }
        protected void handleUnknownMsg(String msg) {
            LOG.warn("Got: " + msg);
        }
        void shutdown() {
            this.shutdown = true;
            if (this.ws != null && !this.ws.isClosed()) {
                this.ws.closeAndForget();
            }
        }
        public Uni<JsonObject> call(String method, JsonObject params) {
            Integer id = nextId.incrementAndGet();
            JsonObject request = new JsonObject()
                    .put("id", id)
                    .put("method", method)
                    .put("params", params);
            CompletableFuture<JsonObject> future = new CompletableFuture<>();
            pending.put(id, future);
            if (ws == null || ws.isClosed()) {
                throw new IllegalStateException("WS not connected");
            }
            ws.writeTextMessage(request.encode());
            return Uni.createFrom().completionStage(future);
        }
        public void connect() {
            LOG.warn("WS closed, reconnecting");
            if (isShutdown || this.shutdown) {
                client.connect(this.options).subscribe().with(
                        x -> {
                            this.ws = x;
                            LOG.info("Reconnected");
                        },
                        e -> LOG.error("Reconnect failed", e)
                );
            }
        }

    }
    class BaseApiConnection implements ConnectionInterface {
        WebSocket ws;
        AtomicInteger nextId = new AtomicInteger();
        ConcurrentHashMap<Integer, CompletableFuture<JsonObject>> pending = new ConcurrentHashMap<>();
        boolean shutdown = false;
        WebSocketConnectOptions options;
        BaseApiConnection(WebSocketConnectOptions options, WebSocket ws) {
            this.options = options;
            this.ws = ws;
            this.ws.textMessageHandler(this::handleMessage);
            this.ws.exceptionHandler(err -> LOG.error("WS error", err));
            this.ws.closeHandler(this::connect);
        }
        private void handleMessage(String text) {
            JsonObject msg = new JsonObject(text);
            Integer id = msg.getInteger("id");
            if (id != null) {
                CompletableFuture<JsonObject> fut = this.pending.remove(id);
                if (fut != null) {
                    fut.complete(msg);
                    return;
                }
            }
            // тут можна обробляти async events без id
            LOG.debugf("Unmatched WS message: %s", text);
        }

        void shutdown() {
            this.shutdown = true;
            if (this.ws != null && !this.ws.isClosed()) {
                this.ws.closeAndForget();
            }
        }
        public Uni<JsonObject> call(String method, JsonObject params) {
            Integer id = nextId.incrementAndGet();

            JsonObject request = new JsonObject()
                    .put("id", id)
                    .put("method", method)
                    .put("params", params);

            CompletableFuture<JsonObject> future = new CompletableFuture<>();
            pending.put(id, future);

            if (ws == null || ws.isClosed()) {
                throw new IllegalStateException("WS not connected");
            }

            ws.writeTextMessage(request.encode());

            return Uni.createFrom().completionStage(future);
        }
        @Override
        public void connect() {
            LOG.warn("WS closed, reconnecting");
            if (isShutdown || this.shutdown) {
                client.connect(this.options).subscribe().with(
                        x -> {
                            this.ws = x;
                            LOG.info("Reconnected");
                        },
                        e -> LOG.error("Reconnect failed", e)
                );
            }
        }

        @Override
        public void disconnect() {
            this.shutdown();
        }

        @Override
        public void send(ApiRequestWrapperDTO request) {
//            BaseRequestDTO baseRequest = request.getParams();
//            if (baseRequest == null) {
//                baseRequest = new BaseRequestDTO();
//            }
//
//            if (request.isApiKeyOnly() && baseRequest.getApiKey() == null) {
//                baseRequest.setApiKey(configuration.getSignatureConfiguration().getApiKey());
//            }
//
//            if (request.isSigned()) {
//                baseRequest.setTimestamp(getTimestamp().toString());
//
//                if (!isLoggedOn) {
//                    if (baseRequest.getApiKey() == null) {
//                        baseRequest.setApiKey(configuration.getSignatureConfiguration().getApiKey());
//                    }
//
//                    try {
//                        baseRequest.setSignature(
//                                signatureGenerator.signAsString(baseRequest.toUrlQueryString()));
//                    } catch (CryptoException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//
//            send(request);
        }

        @Override
        public BlockingQueue<String> sendForStream(ApiRequestWrapperDTO request) throws InterruptedException {
            return null;
        }

        @Override
        public void send(RequestWrapperDTO request) throws InterruptedException {
//            this.ws.sendString(
//                            gson.toJson(request),
//                            new WriteCallback() {
//                                @Override
//                                public void writeFailed(Throwable x) {
//                                    throw new ApiException(x);
//                                }
//
//                                @Override
//                                public void writeSuccess() {
//                                    ConnectionWrapper.this.pendingRequest.put(request.getId(), request);
//                                }
//                            });
        }
        @Override
        public void setUserAgent(String userAgent) {
            agentName = userAgent;
        }

        @Override
        public boolean isConnected() {
            return false;
        }

        @Override
        public void setLogonMethods(List<String> logonMethods) {

        }

        @Override
        public void setLogoutMethods(List<String> logoutMethods) {

        }

        @Override
        public void stop() throws Exception {

        }
    }
}

