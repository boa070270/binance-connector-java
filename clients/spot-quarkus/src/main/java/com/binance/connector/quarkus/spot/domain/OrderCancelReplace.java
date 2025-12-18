package com.binance.connector.quarkus.spot.domain;


import com.binance.connector.quarkus.spot.model.CancelReplaceMode;

import java.util.LinkedHashMap;

public class OrderCancelReplace {
    final Order order;
    public final CancelReplaceMode cancelReplaceMode;
    public String cancelNewClientOrderId;
    public String cancelOrigClientOrderId;
    public Long cancelOrderId;
    public String newClientOrderId;
    public OrderCancelReplace(CancelReplaceMode cancelReplaceMode, Order newOrder, OrderResponse oldOrder) {
        this.cancelReplaceMode = cancelReplaceMode;
        this.order = newOrder;
        this.cancelOrderId = oldOrder.orderId;
        this.cancelOrigClientOrderId = null;
    }

    public LinkedHashMap<String, Object> parameters() {
        LinkedHashMap<String, Object> papams = order.parameters();
        papams.put("cancelReplaceMode", cancelReplaceMode);
        if (cancelOrderId != null) {
            papams.put("cancelOrderId", cancelOrderId);
        } else {
            papams.put("cancelOrigClientOrderId", cancelOrigClientOrderId);
        }
        if (cancelNewClientOrderId != null) papams.put("cancelNewClientOrderId", cancelNewClientOrderId);
        if (newClientOrderId != null) papams.put("newClientOrderId", newClientOrderId);
        return papams;
    }
}
