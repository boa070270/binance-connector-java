package com.binance.connector.quarkus.spot;

import com.binance.connector.client.common.websocket.service.StreamBlockingQueue;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class StreamBlockingQueueWrapper<T> {
    private final StreamBlockingQueue<String> innerQueue;
    private final TypeToken<T> convertType;
    private final Gson gson;

    public StreamBlockingQueueWrapper(StreamBlockingQueue<String> innerQueue, TypeToken<T> type) {
        this(innerQueue, type, JSON.getGson());
    }

    public StreamBlockingQueueWrapper(
            StreamBlockingQueue<String> innerQueue, TypeToken<T> type, Gson gson) {
        this.innerQueue = innerQueue;
        this.convertType = type;
        this.gson = gson;
    }

    public StreamBlockingQueue<String> getInnerQueue() {
        return innerQueue;
    }

    public T take() throws InterruptedException {
        String take = innerQueue.take();
        return gson.fromJson(take, convertType);
    }
}
