package com.binance.connector.client.spot.websocket.model;

import com.google.gson.Gson;

public class JSON {
    private static final Gson GSON = new Gson();
    public static Gson getGson() {
        return GSON;
    }
}
