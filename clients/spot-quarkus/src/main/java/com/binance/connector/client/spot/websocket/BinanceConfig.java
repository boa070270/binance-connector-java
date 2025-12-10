package com.binance.connector.client.spot.websocket;

import com.binance.connector.client.common.configuration.SignatureConfiguration;
import com.binance.connector.client.common.websocket.dtos.BaseRequestDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

public class BinanceConfig {
    public static final int DEFAULT_RECONNECT_INTERVAL_TIME = (23 * 60 * 60 * 1000);
    public static final int DEFAULT_POOL_SIZE = 10;
    public static final int DEFAULT_RECONNECT_BATCH_SIZE = 2;
    public static final int DEFAULT_RETRIES = 3;
    public static final int DEFAULT_BACKOFF = 200;
    public static final int DEFAULT_CONNECT_TIMEOUT = 1000;
    public static final int DEFAULT_READ_TIMEOUT = 5000;
    public static final long DEFAULT_MAX_MESSAGE_SIZE = 65536L;
    private Boolean autoLogon = false;
    private SignatureConfiguration signatureConfiguration;
    private BinanceEndpointEnum binanceEndpointEnum = null;
    BinanceQuarkusConfig config;
    public BinanceConfig(BinanceQuarkusConfig config) {
        this.config = config;
    }

    public BinanceQuarkusConfig.Ws ws() {
        return config.ws();
    }

    public BinanceQuarkusConfig.Api api() {
        return config.api();
    }

    public Integer getReconnectIntervalTime() {
        return DEFAULT_RECONNECT_INTERVAL_TIME;
    }

    public boolean getAutoLogon() {
        return autoLogon;
    }

    public SignatureConfiguration getSignatureConfiguration() {
        return signatureConfiguration;
    }

    public BinanceEndpointEnum getBinanceEndpoint() {
        return binanceEndpointEnum;
    }
}
