package com.binance.connector.quarkus.spot.domain.filters;

import com.binance.connector.quarkus.spot.domain.SymbolFilter;
import com.binance.connector.quarkus.spot.domain.enums.SymbolFilterType;

/**
 * The TRAILING_DELTA filter defines the minimum and maximum value for the parameter trailingDelta.
 * <br>
 * In order for a trailing stop order to pass this filter, the following must be true:
 * <br>
 * For STOP_LOSS BUY, STOP_LOSS_LIMIT_BUY,TAKE_PROFIT SELL and TAKE_PROFIT_LIMIT SELL orders:
 * <br>
 *     trailingDelta >= minTrailingAboveDelta
 *     trailingDelta <= maxTrailingAboveDelta
 * <br>
 * For STOP_LOSS SELL, STOP_LOSS_LIMIT SELL, TAKE_PROFIT BUY, and TAKE_PROFIT_LIMIT BUY orders:
 * <br>
 *     trailingDelta >= minTrailingBelowDelta
 *     trailingDelta <= maxTrailingBelowDelta
 */
public class TrailingDelta implements SymbolFilter {
    public final SymbolFilterType filterType = SymbolFilterType.TRAILING_DELTA;
    public final long minTrailingAboveDelta;
    public final long maxTrailingAboveDelta;
    public final long minTrailingBelowDelta;
    public final long maxTrailingBelowDelta;

    public TrailingDelta(long minTrailingAboveDelta, long maxTrailingAboveDelta, long minTrailingBelowDelta, long maxTrailingBelowDelta) {
        this.minTrailingAboveDelta = minTrailingAboveDelta;
        this.maxTrailingAboveDelta = maxTrailingAboveDelta;
        this.minTrailingBelowDelta = minTrailingBelowDelta;
        this.maxTrailingBelowDelta = maxTrailingBelowDelta;
    }

    @Override
    public String toString() {
        return "\"TrailingDelta\":{" +
                "minTrailingAboveDelta=" + minTrailingAboveDelta +
                ", maxTrailingAboveDelta=" + maxTrailingAboveDelta +
                ", minTrailingBelowDelta=" + minTrailingBelowDelta +
                ", maxTrailingBelowDelta=" + maxTrailingBelowDelta +
                ", filterType=" + filterType +
                '}';
    }
}
