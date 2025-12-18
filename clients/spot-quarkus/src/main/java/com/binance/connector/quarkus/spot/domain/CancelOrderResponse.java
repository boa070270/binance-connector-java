package com.binance.connector.quarkus.spot.domain;

import com.binance.connector.quarkus.spot.domain.enums.OrderStatus;
import com.binance.connector.quarkus.spot.domain.enums.OrderType;
import com.binance.connector.quarkus.spot.domain.enums.SideEnum;
import com.binance.connector.quarkus.spot.domain.enums.TimeInForceEnum;
import com.binance.connector.quarkus.spot.model.BinanceUtils;

import java.util.Map;

public class CancelOrderResponse {
    public String symbol;
    public String origClientOrderId;
    public Long orderId;
    public Integer orderListId;
    public String clientOrderId;
    public Double price;
    public Double origQty;
    public Double executedQty;
    public Double cummulativeQuoteQty;
    public OrderStatus status;
    public TimeInForceEnum timeInForce;
    public OrderType type;
    public SideEnum side;

    public static CancelOrderResponse fromMap(Map m) {
        CancelOrderResponse o = new CancelOrderResponse();
        o.symbol = (String) m.get("symbol");
        o.origClientOrderId = (String) m.get("origClientOrderId");
        o.orderId = BinanceUtils.fromMapLong(m, "orderId");
        o.orderListId = BinanceUtils.fromMapInt(m, "orderListId");
        o.clientOrderId = (String) m.get("clientOrderId");
        o.price = BinanceUtils.fromMapDouble(m, "price");
        o.origQty = BinanceUtils.fromMapDouble(m, "origQty");
        o.executedQty = BinanceUtils.fromMapDouble(m, "executedQty");
        o.cummulativeQuoteQty = BinanceUtils.fromMapDouble(m, "cummulativeQuoteQty");
        o.status = OrderStatus.valueOf((String) m.get("status"));
        o.timeInForce = m.get("timeInForce") != null? TimeInForceEnum.valueOf((String) m.get("timeInForce")) : null;
        o.type = OrderType.valueOf((String) m.get("type"));
        o.side = SideEnum.valueOf((String) m.get("side"));
        return o;
    }

    @Override
    public String toString() {
        return "{\"CancelOrderResponse\":{" +
                "\"symbol\":\"" + symbol +
                "\", \"origClientOrderId\":\"" + origClientOrderId +
                "\", \"orderId\":\"" + orderId +
                "\", \"orderListId\":" + orderListId +
                "\", \"clientOrderId\":\"" + clientOrderId +
                "\", \"price\":\"" + price +
                "\", \"origQty\":\"" + origQty +
                "\", \"executedQty\":" + executedQty +
                "\", \"cummulativeQuoteQty\":\"" + cummulativeQuoteQty +
                "\", \"status\":\"" + status +
                "\", \"timeInForce\":\"" + timeInForce +
                "\", \"type\":\"" + type +
                "\", \"side\":\"" + side +
                "\"}}";
    }
}
