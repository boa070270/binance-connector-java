package com.binance.connector.quarkus.spot.domain.enums;

public enum SymbolFilterType {
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
    PRICE_FILTER,
    /**
     * The PERCENT_PRICE_BY_SIDE filter defines the valid range for the price based on the lastPrice of the symbol.
     * There is a different range depending on whether the order is placed on the BUY side or the SELL side.
     *
     * Buy orders will succeed on this filter if:
     *
     *     Order price <= bidMultiplierUp * lastPrice
     *     Order price >= bidMultiplierDown * lastPrice
     *
     * Sell orders will succeed on this filter if:
     *
     *     Order Price <= askMultiplierUp * lastPrice
     *     Order Price >= askMultiplierDown * lastPrice
     */
    PERCENT_PRICE_BY_SIDE,
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
    LOT_SIZE,
    /**
     * The MIN_NOTIONAL filter defines the minimum notional value allowed for an order on a symbol.
     * An order's notional value is the price * quantity.
     * If the order is an Algo order (e.g. STOP_LOSS_LIMIT), then the notional value of the stopPrice * quantity will also be evaluated.
     * If the order is an Iceberg Order, then the notional value of the price * icebergQty will also be evaluated.
     * applyToMarket determines whether or not the MIN_NOTIONAL filter will also be applied to MARKET orders.
     * Since MARKET orders have no price, the average price is used over the last avgPriceMins minutes.
     * avgPriceMins is the number of minutes the average price is calculated over. 0 means the last price is used.
     */
    MIN_NOTIONAL,
    /**
     * The NOTIONAL filter defines the acceptable notional range allowed for an order on a symbol.
     *
     * applyMinToMarket determines whether the minNotional will be applied to MARKET orders.
     * applyMaxToMarket determines whether the maxNotional will be applied to MARKET orders.
     *
     * In order to pass this filter, the notional (price * quantity) has to pass the following conditions:
     *
     *     price * quantity <= maxNotional
     *     price * quantity >= minNotional
     *
     * For MARKET orders, the average price used over the last avgPriceMins minutes will be used for calculation.
     * If the avgPriceMins is 0, then the last price will be used.
     */
    NOTIONAL,
    /**
     * The ICEBERG_PARTS filter defines the maximum parts an iceberg order can have. The number of ICEBERG_PARTS is defined as CEIL(qty / icebergQty).
     */
    ICEBERG_PARTS,
    /**
     * The MARKET_LOT_SIZE filter defines the quantity (aka "lots" in auction terms) rules for MARKET orders on a symbol. There are 3 parts:
     *
     *     minQty defines the minimum quantity allowed.
     *     maxQty defines the maximum quantity allowed.
     *     stepSize defines the intervals that a quantity can be increased/decreased by.
     *
     * In order to pass the market lot size, the following must be true for quantity:
     *
     *     quantity >= minQty
     *     quantity <= maxQty
     *     (quantity-minQty) % stepSize == 0
     */
    MARKET_LOT_SIZE,
    /**
     * The MAX_NUM_ORDERS filter defines the maximum number of orders an account is allowed to have open on a symbol.
     * Note that both "algo" orders and normal orders are counted for this filter.
     */
    MAX_NUM_ORDERS,
    /**
     * The MAX_NUM_ALGO_ORDERS filter defines the maximum number of "algo" orders an account is allowed to have open on a symbol.
     * "Algo" orders are STOP_LOSS, STOP_LOSS_LIMIT, TAKE_PROFIT, and TAKE_PROFIT_LIMIT orders.
     */
    MAX_NUM_ALGO_ORDERS,
    /**
     * The MAX_POSITION filter defines the allowed maximum position an account can have on the base asset of a symbol.
     * An account's position defined as the sum of the account's:
     *
     *     free balance of the base asset
     *     locked balance of the base asset
     *     sum of the qty of all open BUY orders
     *
     * BUY orders will be rejected if the account's position is greater than the maximum position allowed.
     *
     * If an order's quantity can cause the position to overflow, this will also fail the MAX_POSITION filter.
     */
    MAX_POSITION,
    /**
     * The PERCENT_PRICE filter defines valid range for a price based on the average of the previous trades.
     * avgPriceMins is the number of minutes the average price is calculated over. 0 means the last price is used.
     *
     * In order to pass the percent price, the following must be true for price:
     *
     *     price <= weightedAveragePrice * multiplierUp
     *     price >= weightedAveragePrice * multiplierDown
     */
    PERCENT_PRICE,
    /**
     * The MAX_NUM_ICEBERG_ORDERS filter defines the maximum number of ICEBERG orders an account is allowed to have open on a symbol.
     * An ICEBERG order is any order where the icebergQty is > 0.
     */
    MAX_NUM_ICEBERG_ORDERS,
    /**
     * The TRAILING_DELTA filter defines the minimum and maximum value for the parameter trailingDelta.
     *
     * In order for a trailing stop order to pass this filter, the following must be true:
     *
     * For STOP_LOSS BUY, STOP_LOSS_LIMIT_BUY,TAKE_PROFIT SELL and TAKE_PROFIT_LIMIT SELL orders:
     *
     *     trailingDelta >= minTrailingAboveDelta
     *     trailingDelta <= maxTrailingAboveDelta
     *
     * For STOP_LOSS SELL, STOP_LOSS_LIMIT SELL, TAKE_PROFIT BUY, and TAKE_PROFIT_LIMIT BUY orders:
     *
     *     trailingDelta >= minTrailingBelowDelta
     *     trailingDelta <= maxTrailingBelowDelta
     */
    TRAILING_DELTA
}
