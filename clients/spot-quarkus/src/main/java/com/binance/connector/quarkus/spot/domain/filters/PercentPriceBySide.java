package com.binance.connector.quarkus.spot.domain.filters;

import com.binance.connector.quarkus.spot.domain.enums.SymbolFilterType;

/**
 The PERCENT_PRICE_BY_SIDE filter defines the valid range for the price based on the average of the previous trades.
 avgPriceMins is the number of minutes the average price is calculated over. 0 means the last price is used.
 There is a different range depending on whether the order is placed on the BUY side or the SELL side.
 Buy orders will succeed on this filter if:
    Order price <= weightedAveragePrice * bidMultiplierUp
    Order price >= weightedAveragePrice * bidMultiplierDown
 Sell orders will succeed on this filter if:
    Order Price <= weightedAveragePrice * askMultiplierUp
    Order Price >= weightedAveragePrice * askMultiplierDown
 */
public class PercentPriceBySide extends PercentPrice {
    public final SymbolFilterType filterType = SymbolFilterType.PERCENT_PRICE_BY_SIDE;
    //bidMultiplierUp = multiplierUp
    //bidMultiplierDown = multiplierDown;
    public final Double askMultiplierUp;
    public final Double askMultiplierDown;
    //avgPriceMins;

    public PercentPriceBySide(double bidMultiplierUp, double bidMultiplierDown, double askMultiplierUp, double askMultiplierDown, int avgPriceMins) {
        super(bidMultiplierUp, bidMultiplierDown, avgPriceMins);
        this.askMultiplierUp = askMultiplierUp;
        this.askMultiplierDown = askMultiplierDown;
    }

    @Override
    public String toString() {
        return "\"PercentPriceBySide\":{" +
                "\"bidMultiplierUp\"=" + multiplierUp +
                ", \"bidMultiplierDown\"=" + multiplierDown +
                ", \"askMultiplierUp\"=" + askMultiplierUp +
                ", \"askMultiplierDown\"=" + askMultiplierDown +
                ", \"avgPriceMins\"=" + avgPriceMins +
                ", \"filterType\"=\"" + filterType +'"'+
                '}';
    }
}
