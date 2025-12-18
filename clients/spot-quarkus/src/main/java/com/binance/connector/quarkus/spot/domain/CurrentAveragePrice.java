package com.binance.connector.quarkus.spot.domain;


import com.binance.connector.quarkus.spot.model.BinanceUtils;

import java.util.Map;

public class CurrentAveragePrice {
    public Long mins;
    public Double price;

    public static CurrentAveragePrice fromMap(Map<String,?> m) {
        CurrentAveragePrice averagePrice = new CurrentAveragePrice();
        averagePrice.mins = BinanceUtils.fromMapLong(m, "mins");
        averagePrice.price = BinanceUtils.fromMapDouble(m , "price");
        return averagePrice;
    }
}
