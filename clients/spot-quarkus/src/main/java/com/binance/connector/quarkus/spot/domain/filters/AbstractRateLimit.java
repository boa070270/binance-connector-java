package com.binance.connector.quarkus.spot.domain.filters;

import com.binance.connector.quarkus.spot.domain.enums.RateLimitInterval;
import com.binance.connector.quarkus.spot.domain.enums.RateLimitType;
import com.binance.connector.quarkus.spot.model.BinanceUtils;

import java.util.Map;

public abstract class AbstractRateLimit {
    public RateLimitType rateLimitType;//: "REQUEST_WEIGHT",
    public RateLimitInterval interval;//: "MINUTE",
    public Integer intervalNum;//": 1,
    public Integer limit;//": 1200
    protected AbstractRateLimit(RateLimitInterval interval, Integer intervalNum, Integer limit) {
        this.interval = interval;
        this.intervalNum = intervalNum;
        this.limit = limit;
    }
    public static AbstractRateLimit fromMap(Map<String, ?> map) {
        RateLimitType rateLimitType = RateLimitType.valueOf((String) map.get("rateLimitType"));
        RateLimitInterval interval = RateLimitInterval.valueOf((String) map.get("interval"));
        Integer intervalNum = BinanceUtils.fromMapInt(map,"intervalNum");
        Integer limit = BinanceUtils.fromMapInt(map,"limit");
        switch (rateLimitType) {
            case RAW_REQUESTS:
                return new RateLimitRawRequest(interval, intervalNum, limit);
            case REQUEST_WEIGHT:
                return new RateLimitRequestWeight(interval, intervalNum, limit);
            case ORDERS:
                return new RateLimitOrders(interval, intervalNum, limit);
            case CONNECTIONS:
                return new RateLimitConnections(interval, intervalNum, limit);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return '"'+this.getClass().getSimpleName() +"\":{" +
                "rateLimitType=\"" + rateLimitType + '"' +
                ", interval=\"" + interval + '"' +
                ", intervalNum=" + intervalNum +
                ", limit=" + limit +
                '}';
    }
}
