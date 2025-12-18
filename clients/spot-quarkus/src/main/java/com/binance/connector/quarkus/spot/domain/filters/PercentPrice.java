package com.binance.connector.quarkus.spot.domain.filters;

import com.binance.connector.quarkus.spot.domain.SymbolFilter;
import com.binance.connector.quarkus.spot.domain.enums.SymbolFilterType;
import org.jboss.logging.Logger;

/**
 * The PERCENT_PRICE filter defines valid range for a price based on the average of the previous trades.
 * avgPriceMins is the number of minutes the average price is calculated over. 0 means the last price is used.
 * <br>
 * In order to pass the percent price, the following must be true for price:
 * <br>
 *     price <= weightedAveragePrice * multiplierUp
 *     price >= weightedAveragePrice * multiplierDown
 */
public class PercentPrice extends AvgPriceMonitor implements SymbolFilter {
    private static final Logger Log = Logger.getLogger(PercentPrice.class);

    public final SymbolFilterType filterType = SymbolFilterType.PERCENT_PRICE;
    public final Double multiplierUp;
    public final Double multiplierDown;
    public PercentPrice(double multiplierUp, double multiplierDown, double avgPriceMins) {
        super(avgPriceMins);
        this.multiplierUp = multiplierUp;
        this.multiplierDown = multiplierDown;
    }


    @Override
    public String toString() {
        return "\"PercentPrice\":{" +
                "\"multiplierUp\"=" + multiplierUp +
                ", \"multiplierDown\"=" + multiplierDown +
                ", \"avgPriceMins\"=" + avgPriceMins +
                ", \"filterType\"=\"" + filterType + '\"'+
                '}';
    }
}
