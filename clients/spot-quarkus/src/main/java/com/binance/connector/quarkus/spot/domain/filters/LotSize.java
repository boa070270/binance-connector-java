package com.binance.connector.quarkus.spot.domain.filters;


import com.binance.connector.quarkus.spot.domain.AbstractRequest;
import com.binance.connector.quarkus.spot.domain.Order;
import com.binance.connector.quarkus.spot.domain.SymbolFilter;
import com.binance.connector.quarkus.spot.domain.enums.SymbolFilterType;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * The LOT_SIZE filter defines the quantity (aka "lots" in auction terms) rules for a symbol. There are 3 parts:
 *
 *     minQty defines the minimum quantity/icebergQty allowed.
 *     maxQty defines the maximum quantity/icebergQty allowed.
 *     stepSize defines the intervals that a quantity/icebergQty can be increased/decreased by.
 *
 * In order to pass the lot size, the following must be true for quantity/icebergQty:
 *
 *     quantity >= minQty
 *     quantity <= maxQty
 *     (quantity-minQty) % stepSize == 0
 */
public class LotSize implements SymbolFilter {
    public final SymbolFilterType filterType = SymbolFilterType.LOT_SIZE;
    public final Double minQty;
    public final Double maxQty;
    public final Double stepSize;

    public LotSize(double minQty, double maxQty, double stepSize) {
        this.minQty = minQty;
        this.maxQty = maxQty;
        this.stepSize = stepSize;
    }
    @Override
    public String toString() {
        return "\"LotSize\":{" +
                "\"minQty\"=" + minQty +
                ", \"maxQty\"=" + maxQty +
                ", \"stepSize\"=" + stepSize +
                ", \"filterType\"=\"" + filterType + '"' +
                '}';
    }
}
