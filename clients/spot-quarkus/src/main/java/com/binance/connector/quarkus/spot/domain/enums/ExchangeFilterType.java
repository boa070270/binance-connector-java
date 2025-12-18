package com.binance.connector.quarkus.spot.domain.enums;

/**
 * From 2020-05-01 UTC 00:00, all symbols will have a limit of 200 open orders using the MAX_NUM_ORDERS filter.
 *
 *     No existing orders will be removed or canceled.
 *     Accounts that have 200 or more open orders on a symbol will not be able to place new orders on that symbol until the open order count is below 200.
 *     OCO orders count as 2 open orders before the LIMIT order is touched or the STOP_LOSS (or STOP_LOSS_LIMIT) order is triggered; once this happens the other order is canceled and will no longer count as an open order.
 */
public enum ExchangeFilterType {
    /**
     * The EXCHANGE_MAX_NUM_ORDERS filter defines the maximum number of orders an account is allowed to have open on the exchange.
     * Note that both "algo" orders and normal orders are counted for this filter.
     */
    EXCHANGE_MAX_NUM_ORDERS,
    /**
     * The EXCHANGE_MAX_NUM_ALGO_ORDERS filter defines the maximum number of "algo" orders an account is allowed to have open on the exchange.
     * "Algo" orders are STOP_LOSS, STOP_LOSS_LIMIT, TAKE_PROFIT, and TAKE_PROFIT_LIMIT orders.
     */
    EXCHANGE_MAX_NUM_ALGO_ORDERS,
    /**
     * The EXCHANGE_MAX_NUM_ICEBERG_ORDERS filter defines the maximum number of iceberg orders an account is allowed to have open on the exchange.
     */
    EXCHANGE_MAX_NUM_ICEBERG_ORDERS
}
