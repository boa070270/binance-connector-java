package com.binance.connector.quarkus.spot.domain.filters;

import com.binance.connector.quarkus.spot.domain.enums.OrderType;
import com.binance.connector.quarkus.spot.domain.enums.SymbolFilterType;

/**
 * The MAX_NUM_ALGO_ORDERS filter defines the maximum number of "algo" orders an account is allowed to have open on a symbol.
 * "Algo" orders are STOP_LOSS, STOP_LOSS_LIMIT, TAKE_PROFIT, and TAKE_PROFIT_LIMIT orders.
 */
public class MaxNumAlgoOrders extends MaxNumOrders {
    public final SymbolFilterType filterType = SymbolFilterType.MAX_NUM_ALGO_ORDERS;

    public MaxNumAlgoOrders(int maxNumAlgoOrders) {
        super(maxNumAlgoOrders);
    }

    @Override
    protected boolean filterOrders(OrderType type, Double icebergQty) {
        return type == OrderType.STOP_LOSS || type == OrderType.STOP_LOSS_LIMIT || type == OrderType.TAKE_PROFIT || type == OrderType.TAKE_PROFIT_LIMIT;
    }

    @Override
    public String toString() {
        return "\"MaxNumAlgoOrders\":{" +
                "\"maxNumAlgoOrders\"=" + maxNumOrders +
                ", \"filterType\"=\"" + filterType + '"' +
                '}';
    }
}
