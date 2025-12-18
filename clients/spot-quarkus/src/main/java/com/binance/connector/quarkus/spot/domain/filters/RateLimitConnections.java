package com.binance.connector.quarkus.spot.domain.filters;

import com.binance.connector.quarkus.spot.domain.enums.RateLimitInterval;
import com.binance.connector.quarkus.spot.domain.enums.RateLimitType;

public class RateLimitConnections extends AbstractRateLimit {
    public RateLimitConnections(RateLimitInterval interval, Integer intervalNum, Integer limit) {
        super(interval, intervalNum, limit);
        this.rateLimitType = RateLimitType.CONNECTIONS;
    }

}
