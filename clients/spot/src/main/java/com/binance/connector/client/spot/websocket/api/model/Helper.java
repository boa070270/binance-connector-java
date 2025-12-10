package com.binance.connector.client.spot.websocket.api.model;

import com.google.gson.JsonElement;

public class Helper {
    public static String getAsString(JsonElement jsonElement) {
        if (jsonElement == null || jsonElement.isJsonNull()) return null;
        return jsonElement.getAsString();
    }
}
