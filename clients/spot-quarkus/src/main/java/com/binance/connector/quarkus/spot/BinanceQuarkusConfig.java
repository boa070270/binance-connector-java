package com.binance.connector.quarkus.spot;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "binance")
public interface BinanceQuarkusConfig {

    Ws ws();
    Api api();

    interface Ws {
        String endpoint();
        String single();
        String multi();
    }

    interface Api {
        String endpoint();
        String endpointTest();
        String key();
        String secret();
        boolean useTestNet();
        double recvWindow();
    }
}

