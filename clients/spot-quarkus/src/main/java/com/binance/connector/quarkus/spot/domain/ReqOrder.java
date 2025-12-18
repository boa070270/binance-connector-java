package com.binance.connector.quarkus.spot.domain;

import java.util.LinkedHashMap;

public class ReqOrder {
    public final String symbol;
    public Long orderId;
    public String origClientOrderId;
    /**
     * The value cannot be greater than 60000
     */
    public Long recvWindow;

    public ReqOrder(String symbol) {
        this.symbol = symbol;
    }

    public LinkedHashMap<String, Object> parameters() throws ValidateException {
        if (orderId == null && origClientOrderId == null) {
            throw new ValidateException("Invalid orderId");
        }
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("symbol", symbol);
        if (orderId != null) {
            map.put("orderId", orderId.toString());
        } else {
            map.put("origClientOrderId", origClientOrderId);
        }
        if (recvWindow != null)
            map.put("newClientOrderId", recvWindow.toString());
        return map;
    }
}
