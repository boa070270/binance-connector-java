package com.binance.connector.quarkus.spot.domain.filters;

import com.binance.connector.quarkus.spot.domain.enums.OrderType;
import com.binance.connector.quarkus.spot.domain.enums.SymbolFilterType;

/**
 * The MAX_NUM_ICEBERG_ORDERS filter defines the maximum number of ICEBERG orders an account is allowed to have open on a symbol.
 * An ICEBERG order is any order where the icebergQty is > 0.
 */
public class MaxNumIcebergOrders extends MaxNumOrders {
    public final SymbolFilterType filterType = SymbolFilterType.MAX_NUM_ICEBERG_ORDERS;
    public MaxNumIcebergOrders(int maxNumIcebergOrders) {
        super(maxNumIcebergOrders);
    }

    @Override
    protected boolean filterOrders(OrderType type, Double icebergQty) {
        return icebergQty != null && icebergQty > 0.000000001;
    }

    @Override
    public String toString() {
        return "\"MaxNumIcebergOrders\":{" +
                "\"maxNumIcebergOrders\"=" + maxNumOrders +
                ", \"filterType\"=\"" + filterType + '"' +
                '}';
    }
}
