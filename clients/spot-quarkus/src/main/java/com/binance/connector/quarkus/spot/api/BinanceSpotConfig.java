package com.binance.connector.quarkus.spot.api;

import io.smallrye.config.ConfigMapping;

import java.util.List;

@ConfigMapping(prefix = "binance.spot")
public interface BinanceSpotConfig {
    boolean useTestNet();

    String endpointApi();

    String endpointTest();
    List<String> symbols();

    String storeKey();
}
