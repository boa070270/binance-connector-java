package com.binance.connector.quarkus.spot.domain;

import com.binance.connector.quarkus.spot.domain.enums.*;
import com.binance.connector.quarkus.spot.model.BinanceUtils;

import java.util.Map;

public class OrderInfo {
    public String symbol;
    public String clientOrderId;
    public SideEnum side;
    public OrderType type;
    public TimeInForceEnum timeInForce;
    public Double price;
    public Double stopPrice;
    public Double icebergQty;
    public Long orderListId;
    public OrderStatus status;
    public Long orderId;
    public Double origQty;
    public Double executedQty;
    public Double cumulativeQuoteQty;
    public Long time;
    public Long updateTime;
    public Boolean isWorking;
    public Double origQuoteOrderQty;
    public static OrderInfo fromMap(Map map) {
        OrderInfo r = new OrderInfo();
        r.symbol = (String) map.get("symbol");
        r.orderId = BinanceUtils.fromMapLong(map, "orderId");
        r.orderListId = BinanceUtils.fromMapLong(map, "orderListId");
        r.clientOrderId = (String) map.get("clientOrderId");
        r.price = BinanceUtils.fromMapDouble(map, "price");
        r.origQty = BinanceUtils.fromMapDouble(map, "origQty");
        r.executedQty = BinanceUtils.fromMapDouble(map, "executedQty");
        r.cumulativeQuoteQty = BinanceUtils.fromMapDouble(map, "cumulativeQuoteQty");
        if (map.get("status") != null) r.status = OrderStatus.valueOf((String) map.get("status"));
        if (map.get("timeInForce") != null) r.timeInForce = TimeInForceEnum.valueOf((String) map.get("timeInForce"));
        if (map.get("type") != null) r.type = OrderType.valueOf((String) map.get("type"));
        if (map.get("side") != null) r.side = SideEnum.valueOf((String) map.get("side"));
        r.stopPrice = BinanceUtils.fromMapDouble(map, "stopPrice");
        r.icebergQty = BinanceUtils.fromMapDouble(map, "icebergQty");
        r.time = BinanceUtils.fromMapLong(map, "time");
        r.updateTime = BinanceUtils.fromMapLong(map, "updateTime");
        r.isWorking = BinanceUtils.fromMapBoolean(map, "isWorking");
        r.origQuoteOrderQty = BinanceUtils.fromMapDouble(map, "origQuoteOrderQty");
        return r;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "symbol='" + symbol + '\'' +
                ", orderId=" + orderId +
                ", status=" + status +
                ", type=" + type +
                ", side=" + side +
                ", price=" + price +
                ", origQty=" + origQty +
                ", executedQty=" + executedQty +
                ", origQuoteOrderQty=" + origQuoteOrderQty +
                ", cummulativeQuoteQty=" + cumulativeQuoteQty +
                ", timeInForce=" + timeInForce +
                ", orderListId=" + orderListId +
                ", clientOrderId='" + clientOrderId + '\'' +
                ", stopPrice=" + stopPrice +
                ", icebergQty=" + icebergQty +
                ", time=" + time +
                ", updateTime=" + updateTime +
                ", isWorking=" + isWorking +
                '}';
    }
}
