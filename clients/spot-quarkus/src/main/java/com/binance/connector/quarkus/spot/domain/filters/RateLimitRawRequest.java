package com.binance.connector.quarkus.spot.domain.filters;

import com.binance.connector.quarkus.spot.domain.enums.RateLimitInterval;
import com.binance.connector.quarkus.spot.domain.enums.RateLimitType;

public class RateLimitRawRequest extends AbstractRateLimit {
    public RateLimitRawRequest(RateLimitInterval interval, Integer intervalNum, Integer limit) {
        super(interval, intervalNum, limit);
        rateLimitType = RateLimitType.RAW_REQUESTS;
    }
}
