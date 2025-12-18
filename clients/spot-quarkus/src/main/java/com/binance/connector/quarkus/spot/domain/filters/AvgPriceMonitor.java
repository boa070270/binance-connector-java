package com.binance.connector.quarkus.spot.domain.filters;


public class AvgPriceMonitor {
    protected final double avgPriceMins;
    String symbol;
    AvgPriceMonitor(double avgPriceMins) {
        this.avgPriceMins = avgPriceMins;
    }
}
