package com.binance.connector.quarkus.spot.domain.filters;


import com.binance.connector.quarkus.spot.domain.SymbolFilter;
import com.binance.connector.quarkus.spot.domain.enums.SymbolFilterType;

/**
 * The NOTIONAL filter defines the acceptable notional range allowed for an order on a symbol.
 * <br>
 * applyMinToMarket determines whether the minNotional will be applied to MARKET orders.
 * applyMaxToMarket determines whether the maxNotional will be applied to MARKET orders.
 * <br>
 * In order to pass this filter, the notional (price * quantity) has to pass the following conditions:
 * <br>
 *     price * quantity <= maxNotional
 *     price * quantity >= minNotional
 * <br>
 * For MARKET orders, the average price used over the last avgPriceMins minutes will be used for calculation.
 * If the avgPriceMins is 0, then the last price will be used.
 */
public class Notional extends AvgPriceMonitor implements SymbolFilter {
    public final SymbolFilterType filterType = SymbolFilterType.NOTIONAL;
    public final Double minNotional;
    public final Boolean applyMinToMarket;
    public final Double maxNotional;
    public final Boolean applyMaxToMarket;

    public Notional(double minNotional, boolean applyMinToMarket, double maxNotional, boolean applyMaxToMarket, Integer avgPriceMins) {
        super(avgPriceMins);
        this.minNotional = minNotional;
        this.applyMinToMarket = applyMinToMarket;
        this.maxNotional = maxNotional;
        this.applyMaxToMarket = applyMaxToMarket;
    }

    @Override
    public String toString() {
        return "\"Notional\":{" +
                "\"minNotional\"=" + minNotional +
                ", \"applyMinToMarket\"=" + applyMinToMarket +
                ", \"maxNotional\"=" + maxNotional +
                ", \"applyMaxToMarket\"=" + applyMaxToMarket +
                ", \"avgPriceMins\"=" + avgPriceMins +
                ", \"filterType\"=\"" + filterType +'"'+
                '}';
    }
}
