package com.binance.connector.quarkus.spot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSON {
    private static Gson gson;
    static {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.serializeNulls();
        builder.disableHtmlEscaping();
        gson = builder.create();
    }
    public static Gson getGson() {
        return gson;
    }
}
