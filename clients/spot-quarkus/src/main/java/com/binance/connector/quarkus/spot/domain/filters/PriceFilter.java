package com.binance.connector.quarkus.spot.domain.filters;

import com.binance.connector.quarkus.spot.domain.SymbolFilter;
import com.binance.connector.quarkus.spot.domain.enums.SymbolFilterType;

/**
 * The PRICE_FILTER defines the price rules for a symbol. There are 3 parts:
 *
 *     minPrice defines the minimum price/stopPrice allowed; disabled on minPrice == 0.
 *     maxPrice defines the maximum price/stopPrice allowed; disabled on maxPrice == 0.
 *     tickSize defines the intervals that a price/stopPrice can be increased/decreased by; disabled on tickSize == 0.
 *
 * Any of the above variables can be set to 0, which disables that rule in the price filter.
 * In order to pass the price filter, the following must be true for price/stopPrice of the enabled rules:
 *
 *     price >= minPrice
 *     price <= maxPrice
 *     price % tickSize == 0
 */
public class PriceFilter implements SymbolFilter {
    public final SymbolFilterType filterType = SymbolFilterType.PRICE_FILTER;
    public final Double minPrice;
    public final Double maxPrice;
    public final Double tickSize;

    public PriceFilter(double minPrice, double maxPrice, double tickSize) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.tickSize = tickSize;
    }

    @Override
    public String toString() {
        return "\"PriceFilter\":{" +
                "\"minPrice\"=" + minPrice +
                ", \"maxPrice\"=" + maxPrice +
                ", \"tickSize\"=" + tickSize +
                ", \"filterType\"=\"" + filterType +'"'+
                '}';
    }
}
