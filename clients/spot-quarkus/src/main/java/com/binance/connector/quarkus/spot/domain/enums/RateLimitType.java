package com.binance.connector.quarkus.spot.domain.enums;

public enum RateLimitType {
    /**
     * RAW_REQUESTS rate limit. Limits based on the number of requests over X minutes regardless of weight.
     */
    RAW_REQUESTS,
    /**
     * REQUESTS rate limit type changed to REQUEST_WEIGHT.
     * This limit was always logically request weight and the previous name for it caused confusion.
     */
    REQUEST_WEIGHT,
    /**
     * The order rate limit is counted against each account
     */
    ORDERS,
    CONNECTIONS
}
