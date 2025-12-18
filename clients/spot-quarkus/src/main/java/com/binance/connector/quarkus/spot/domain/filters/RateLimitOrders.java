package com.binance.connector.quarkus.spot.domain.filters;

import com.binance.connector.quarkus.spot.domain.enums.RateLimitInterval;
import com.binance.connector.quarkus.spot.domain.enums.RateLimitType;

public class RateLimitOrders extends AbstractRateLimit {
    public RateLimitOrders(RateLimitInterval interval, Integer intervalNum, Integer limit) {
        super(interval, intervalNum, limit);
        this.rateLimitType = RateLimitType.ORDERS;
    }

}
