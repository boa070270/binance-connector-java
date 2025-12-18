package com.binance.connector.quarkus.spot.domain.filters;

import com.binance.connector.quarkus.spot.domain.SymbolFilter;
import com.binance.connector.quarkus.spot.domain.enums.OrderType;
import com.binance.connector.quarkus.spot.domain.enums.SymbolFilterType;
import org.jboss.logging.Logger;

import java.util.concurrent.atomic.AtomicLong;

/**
 * The MAX_NUM_ORDERS filter defines the maximum number of orders an account is allowed to have open on a symbol.
 * Note that both "algo" orders and normal orders are counted for this filter.
 */
public class MaxNumOrders implements SymbolFilter {
    private static final Logger Log = Logger.getLogger(MaxNumOrders.class);

    public final SymbolFilterType filterType = SymbolFilterType.MAX_NUM_ORDERS;
    protected final Integer maxNumOrders;
    private AtomicLong counter = new AtomicLong();
    private String symbol;
    public MaxNumOrders(int maxNumOrders) {
        this.maxNumOrders = maxNumOrders;
    }

    @Override
    public String toString() {
        return "\"MaxNumOrders\":{" +
                "\"maxNumOrders\"=" + maxNumOrders +
                ", \"filterType\"=\"" + filterType + '"'+
                '}';
    }

    protected boolean filterOrders(OrderType type, Double icebergQty) {
        return true;
    }

}
