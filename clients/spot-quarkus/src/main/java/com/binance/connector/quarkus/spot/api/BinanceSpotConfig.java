package com.binance.connector.quarkus.spot.api;

import io.smallrye.config.ConfigMapping;

import java.util.List;

public interface BinanceSpotConfig {
    boolean useTestNet();

    String endpointApi();

    String endpointTest();

    String storeKey();
}
