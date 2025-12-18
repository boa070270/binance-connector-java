package com.binance.connector.quarkus.spot.domain;



import com.binance.connector.quarkus.spot.domain.enums.*;
import com.binance.connector.quarkus.spot.model.BinanceUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderResponse {
    public String symbol;
    public String clientOrderId;
    public SideEnum side;
    public OrderType type;
    public TimeInForceEnum timeInForce;
    public Long orderId;
    public Long orderListId;
    public Long transactTime;
    public Double price;
    public Double origQty;
    public Double executedQty;
    public Double cummulativeQuoteQty;
    public OrderStatus status;
    public OrderFill[] fills;
    public boolean isTest;
    public boolean isCompleted() {
        return status == OrderStatus.CANCELED || status == OrderStatus.FILLED || status == OrderStatus.EXPIRED || status == OrderStatus.REJECTED;
    }
    public static OrderResponse fromMap(Map<String,?> map) {
        OrderResponse r = new OrderResponse();
        r.symbol = (String) map.get("symbol");
        r.orderId = BinanceUtils.fromMapLong(map, "orderId");
        r.orderListId = BinanceUtils.fromMapLong(map, "orderListId");
        r.clientOrderId = (String) map.get("clientOrderId");
        r.transactTime = BinanceUtils.fromMapLong(map, "transactTime");
        r.price = BinanceUtils.fromMapDouble(map, "price");
        r.origQty = BinanceUtils.fromMapDouble(map, "origQty");
        r.executedQty = BinanceUtils.fromMapDouble(map, "executedQty");
        r.cummulativeQuoteQty = BinanceUtils.fromMapDouble(map, "cummulativeQuoteQty");
        if (map.get("status") != null) r.status = OrderStatus.valueOf((String) map.get("status"));
        if (map.get("timeInForce") != null) r.timeInForce = TimeInForceEnum.valueOf((String) map.get("timeInForce"));
        if (map.get("type") != null) r.type = OrderType.valueOf((String) map.get("type"));
        if (map.get("side") != null) r.side = SideEnum.valueOf((String) map.get("side"));
        r.fills = BinanceUtils.listToArray((List<Map<String,?>>)map.get("fills"), OrderFill::fromMap, OrderFill[]::new, new OrderFill[0]);
        return r;
    }

    @Override
    public String toString() {
        return "{\"OrderResponse\":{" +
                (symbol != null?"\"symbol\":\"" + symbol + '"':"") +
                (orderId != null?", \"orderId\":" + orderId:"") +
                (orderListId != null?", \"orderListId\":" + orderListId:"") +
                (clientOrderId != null?", \"clientOrderId\":\"" + clientOrderId + '"':"") +
                (transactTime != null?", \"transactTime\":" + transactTime:"") +
                (price != null?", \"price\":\"" + price+'"':"") +
                (origQty != null?", \"origQty\":\"" + origQty+'"':"") +
                (executedQty != null?", \"executedQty\":\"" + executedQty+'"':"") +
                (cummulativeQuoteQty != null?", \"cummulativeQuoteQty\":\"" + cummulativeQuoteQty+'"':"") +
                (status != null?", \"status\":\"" + status+'"':"") +
                (timeInForce != null?", \"timeInForce\":\"" + timeInForce +'"':"") +
                (type != null?", \"type\":\"" + type +'"':"") +
                (side != null?", \"side\":\"" + side +'"':"") +
                (fills != null?", \"fills\":" + Arrays.toString(fills):"[]") +
                "}}";
    }
    public static OrderResponse testOrderResponse(Order order) {
        OrderResponse r = new OrderResponse();
        r.isTest = true;
        r.orderId = -1L;
        r.clientOrderId = UUID.randomUUID().toString();
        r.status = order.type == OrderType.MARKET? OrderStatus.FILLED : OrderStatus.NEW;
        r.side = order.side;
        r.type = order.type;
        r.timeInForce = order.timeInForce;
        r.price = order.price;
        r.origQty = order.quantity;
        return r;
    }
}
