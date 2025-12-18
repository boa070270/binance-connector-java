package com.binance.connector.quarkus.spot.domain;

import java.util.Map;

public class AvgPrice {
    public int mins;
    public double price;

    public static AvgPrice fromMap(Map map) {
        AvgPrice price = new AvgPrice();
        price.mins = (int) map.get("mins");
        price.price = Double.parseDouble((String) map.get("price"));
        return price;
    }
}
