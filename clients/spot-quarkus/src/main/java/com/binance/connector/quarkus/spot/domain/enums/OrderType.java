package com.binance.connector.quarkus.spot.domain.enums;

/**
 * 2022-08-08
 *
 * SPOT API
 *
 *     Changes to POST /api/v3/order and POST /api/v3/order/cancelReplace
 *         New optional field strategyId is a parameter used to identify an order as part of a strategy.
 *         New optional field strategyType is a parameter used to identify what strategy was running. (E.g. If all the orders are part of spot grid strategy, it can be set to strategyType=1000000)
 *         Note: strategyType cannot be less than 1000000.
 *     Changes to POST /api/v3/order/oco
 *         New optional fields limitStrategyId, limitStrategyType. stopStrategyId, stopStrategyType
 *         These are the strategy metadata parameters for both legs of the OCO orders.
 *         limitStrategyType and stopStrategyType both cannot be less than 1000000.
 *     Changes to GET /api/v3/order, GET /api/v3/openOrders, and GET /api/v3/allOrders
 *         New fields strategyId and strategyType will appear in the response JSON for orders that had these fields populated upon order placement.
 *     Changes to DELETE /api/v3/order and DELETE /api/v3/openOrders
 *         New fields strategyId and strategyType will appear in the response JSON for cancelled orders that had these fields populated upon order placement.
 *
 */
public enum OrderType {
    LIMIT, MARKET,
    /**
     * New order type: OCO ("One Cancels the Other")
     *
     *     An OCO has 2 orders: (also known as legs in financial terms)
     *         STOP_LOSS or STOP_LOSS_LIMIT leg
     *         LIMIT_MAKER leg
     *     Price Restrictions:
     *         SELL Orders : Limit Price > Last Price > Stop Price
     *         BUY Orders : Limit Price < Last Price < Stop Price
     *         As stated, the prices must "straddle" the last traded price on the symbol. EX: If the last price is 10:
     *             A SELL OCO must have the limit price greater than 10, and the stop price less than 10.
     *             A BUY OCO must have a limit price less than 10, and the stop price greater than 10.
     *     Quantity Restrictions:
     *         Both legs must have the same quantity.
     *         ICEBERG quantities however, do not have to be the same.
     *     Execution Order:
     *         If the LIMIT_MAKER is touched, the limit maker leg will be executed first BEFORE canceling the Stop Loss Leg.
     *         if the Market Price moves such that the STOP_LOSS or STOP_LOSS_LIMIT will trigger, the Limit Maker leg will be cancelled BEFORE executing the STOP_LOSS Leg.
     *     Cancelling an OCO
     *         Cancelling either order leg will cancel the entire OCO.
     *         The entire OCO can be canceled via the orderListId or the listClientOrderId.
     *     New Enums for OCO:
     *         ListStatusType
     *             RESPONSE - used when ListStatus is responding to a failed action. (either order list placement or cancellation)
     *             EXEC_STARTED - used when an order list has been placed or there is an update to a list's status.
     *             ALL_DONE - used when an order list has finished executing and is no longer active.
     *         ListOrderStatus
     *             EXECUTING - used when an order list has been placed or there is an update to a list's status.
     *             ALL_DONE - used when an order list has finished executing and is no longer active.
     *             REJECT - used when ListStatus is responding to a failed action. (either order list placement or cancellation)
     *         ContingencyType
     *             OCO - specifies the type of order list.
     *     New Endpoints:
     *         POST api/v3/order/oco
     *         DELETE api/v3/orderList
     *         GET api/v3/orderList
     */
    STOP_LOSS, STOP_LOSS_LIMIT, LIMIT_MAKER,

    TAKE_PROFIT, TAKE_PROFIT_LIMIT
}
