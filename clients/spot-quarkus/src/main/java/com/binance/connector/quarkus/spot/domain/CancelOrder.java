package com.binance.connector.quarkus.spot.domain;

import java.util.LinkedHashMap;

public class CancelOrder extends ReqOrder {
    public String newClientOrderId;

    public CancelOrder(String symbol) {
        super(symbol);
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
        if (newClientOrderId != null) {
            map.put("newClientOrderId", newClientOrderId);
        }
        if (recvWindow != null)
            map.put("newClientOrderId", recvWindow.toString());
        return map;
    }

}
