package com.binance.connector.quarkus.spot.domain.filters;

import com.binance.connector.quarkus.spot.domain.AbstractRequest;
import com.binance.connector.quarkus.spot.domain.SymbolFilter;
import com.binance.connector.quarkus.spot.domain.enums.SymbolFilterType;

/**
 * The MAX_POSITION filter defines the allowed maximum position an account can have on the base asset of a symbol.
 * An account's position defined as the sum of the account's:
 * <br>
 *     free balance of the base asset
 *     locked balance of the base asset
 *     sum of the qty of all open BUY orders
 * <br>
 * BUY orders will be rejected if the account's position is greater than the maximum position allowed.
 * <br>
 * If an order's quantity can cause the position to overflow, this will also fail the MAX_POSITION filter.
 */
public class MaxPosition implements SymbolFilter {
    public final SymbolFilterType filterType = SymbolFilterType.MAX_POSITION;
    public final Double maxPosition;
    public double free;
    public double locked;
    public double sumBuys;
    public String symbol;
    public String asset;

    public MaxPosition(double maxPosition) {
        this.maxPosition = maxPosition;
    }

    @Override
    public String toString() {
        return "\"MaxPosition\":{" +
                "\"maxPosition\"=" + maxPosition +
                ", \"filterType\"=\"" + filterType + '"'+
                '}';
    }

}
