package com.binance.connector.quarkus.spot.domain.filters;

import com.binance.connector.quarkus.spot.domain.SymbolFilter;
import com.binance.connector.quarkus.spot.domain.enums.SymbolFilterType;

/**
 * The MARKET_LOT_SIZE filter defines the quantity (aka "lots" in auction terms) rules for MARKET orders on a symbol. There are 3 parts:
 * <br>
 *     minQty defines the minimum quantity allowed.
 *     maxQty defines the maximum quantity allowed.
 *     stepSize defines the intervals that a quantity can be increased/decreased by.
 * <br>
 * In order to pass the market lot size, the following must be true for quantity:
 * <br>
 *     quantity >= minQty
 *     quantity <= maxQty
 *     (quantity-minQty) % stepSize == 0
 */
public class MarketLotSize implements SymbolFilter {
    public final SymbolFilterType filterType = SymbolFilterType.MARKET_LOT_SIZE;
    public final Double minQty;
    public final Double maxQty;
    public final Double stepSize;

    public MarketLotSize(double minQty, double maxQty, double stepSize) {
        this.minQty = minQty;
        this.maxQty = maxQty;
        this.stepSize = stepSize;
    }

    @Override
    public String toString() {
        return "\"MarketLotSize\":{" +
                "\"minQty\":" + minQty +
                ", \"maxQty\":" + maxQty +
                ", \"stepSize\":" + stepSize +
                ", \"filterType\":\"" + filterType + '"' +
                '}';
    }
}
