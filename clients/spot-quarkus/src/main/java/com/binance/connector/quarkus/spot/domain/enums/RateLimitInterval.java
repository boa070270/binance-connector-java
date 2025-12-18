package com.binance.connector.quarkus.spot.domain.enums;

import java.util.concurrent.TimeUnit;

public enum RateLimitInterval {
    SECOND(TimeUnit.SECONDS), MINUTE(TimeUnit.MINUTES), HOUR(TimeUnit.HOURS), DAY(TimeUnit.DAYS);
    public final TimeUnit timeUnit;
    RateLimitInterval(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
}
