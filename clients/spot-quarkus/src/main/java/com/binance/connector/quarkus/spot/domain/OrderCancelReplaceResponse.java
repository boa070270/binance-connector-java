package com.binance.connector.quarkus.spot.domain;

import java.util.Map;

public class OrderCancelReplaceResponse {
    public String cancelResult;
    public String newOrderResult;
    public CancelOrderResponse cancelResponse;
    public OrderResponse newOrderResponse;
    public static OrderCancelReplaceResponse fromMap(Map m) {
        OrderCancelReplaceResponse o = new OrderCancelReplaceResponse();
        o.cancelResult = (String) m.get("cancelResult");
        o.newOrderResult = (String) m.get("newOrderResult");
        o.cancelResponse = CancelOrderResponse.fromMap((Map) m.get("cancelResponse"));
        o.newOrderResponse = OrderResponse.fromMap((Map) m.get("newOrderResponse"));
        return o;
    }
}
