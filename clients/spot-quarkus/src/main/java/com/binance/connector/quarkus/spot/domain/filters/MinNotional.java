package com.binance.connector.quarkus.spot.domain.filters;

import com.binance.connector.quarkus.spot.domain.SymbolFilter;
import com.binance.connector.quarkus.spot.domain.enums.SymbolFilterType;

/**
 * The MIN_NOTIONAL filter defines the minimum notional value allowed for an order on a symbol.
 * An order's notional value is the price * quantity.
 * If the order is an Algo order (e.g. STOP_LOSS_LIMIT), then the notional value of the stopPrice * quantity will also be evaluated.
 * If the order is an Iceberg Order, then the notional value of the price * icebergQty will also be evaluated.
 * applyToMarket determines whether or not the MIN_NOTIONAL filter will also be applied to MARKET orders.
 * Since MARKET orders have no price, the average price is used over the last avgPriceMins minutes.
 * avgPriceMins is the number of minutes the average price is calculated over. 0 means the last price is used.
 */
public class MinNotional extends AvgPriceMonitor implements SymbolFilter {
    public final SymbolFilterType filterType = SymbolFilterType.MIN_NOTIONAL;
    public final Double minNotional;
    public final Boolean applyToMarket;

    public MinNotional(double minNotional, boolean applyToMarket, Integer avgPriceMins) {
        super(avgPriceMins);
        this.minNotional = minNotional;
        this.applyToMarket = applyToMarket;
    }

    @Override
    public String toString() {
        return "\"MinNotional\":{" +
                "\"minNotional\"=" + minNotional +
                ", \"applyToMarket\"=" + applyToMarket +
                ", \"avgPriceMins\"=" + avgPriceMins +
                ", \"filterType\"=\"" + filterType +'"'+
                '}';
    }
}
