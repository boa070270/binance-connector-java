package com.binance.connector.quarkus.spot.domain.enums;

public enum OrderStatus {
    /**
     * The order has been accepted by the engine.
     */
    NEW,
    /**
     * A part of the order has been filled.
     */
    PARTIALLY_FILLED,
    /**
     * The order has been completed.
     */
    FILLED,
    /**
     * The order has been canceled by the user.
     */
    CANCELED,
    /**
     * Currently unused
     */
    PENDING_CANCEL,
    /**
     * The order was not accepted by the engine and not processed.
     */
    REJECTED,
    /**
     * The order was canceled according to the order type's rules
     * (e.g. LIMIT FOK orders with no fill, LIMIT IOC or MARKET orders that partially fill) or by the exchange,
     * (e.g. orders canceled during liquidation, orders canceled during maintenance)
     */
    EXPIRED
}
