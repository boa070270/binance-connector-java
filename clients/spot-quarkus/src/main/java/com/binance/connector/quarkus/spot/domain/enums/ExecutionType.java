package com.binance.connector.quarkus.spot.domain.enums;

public enum ExecutionType {
    /**
     * The order has been accepted into the engine.
     */
    NEW,
    /**
     * The order has been canceled by the user.
     */
    CANCELED,
    /**
     * (currently unused)
     */
    REPLACED,
    /**
     * The order has been rejected and was not processed. (This is never pushed into the User Data Stream)
     */
    REJECTED,
    /**
     *  Part of the order or all of the order's quantity has filled.
     */
    TRADE,
    /**
     *  The order was canceled according to the order type's rules
     *  (e.g. LIMIT FOK orders with no fill, LIMIT IOC or MARKET orders that partially fill)
     *  or by the exchange, (e.g. orders canceled during liquidation, orders canceled during maintenance)
     */
    EXPIRED
}
