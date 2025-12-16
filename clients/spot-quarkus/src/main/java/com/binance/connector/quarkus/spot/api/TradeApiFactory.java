package com.binance.connector.quarkus.spot.api;

import com.binance.connector.quarkus.spot.SecurityKeysLoader;
import io.vertx.core.Vertx;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TradeApiFactory {
    @Inject
    Vertx vertx;
    @Inject
    SecurityKeysLoader securityKeysLoader;
    @Inject
    BinanceSpotConfig spotConfig;

    public TradeApi createInstance() {
        return new TradeApi(spotConfig, vertx, securityKeysLoader);
    }

    public TradeApi createInstance(BinanceSpotConfig spotConfig, SecurityKeysLoader securityKeysLoader) {
        return new TradeApi(spotConfig, vertx, securityKeysLoader);
    }
}
