package com.binance.connector.quarkus.spot.domain;


import com.binance.connector.quarkus.spot.model.BinanceUtils;

import java.util.Map;

public class Trade {
    public String symbol;
    public Long id;
    public Long orderId;
    public Integer orderListId;
    public Double price;
    public Double qty;
    public Double quoteQty;
    public Double commission;
    public String commissionAsset;
    public Boolean isBestMatch;
    public Long time;
    public Boolean isBuyer;
    public Boolean isMaker;
    public Boolean isBuyerMaker;

    public static Trade fromMap(Map<String,?> map) {
        Trade trade = new Trade();
        trade.symbol = (String) map.get("symbol");
        trade.id = BinanceUtils.fromMapLong(map, "id");
        trade.orderId = BinanceUtils.fromMapLong(map, "orderId");
        trade.orderListId = BinanceUtils.fromMapInt(map, "orderListId");
        trade.price = BinanceUtils.fromMapDouble(map, "price");
        trade.qty = BinanceUtils.fromMapDouble(map, "qty");
        trade.quoteQty = BinanceUtils.fromMapDouble(map, "quoteQty");
        trade.commission = BinanceUtils.fromMapDouble(map, "commission");
        trade.commissionAsset = (String) map.get("commissionAsset");
        trade.time = BinanceUtils.fromMapLong(map, "time");
        trade.isBuyer = BinanceUtils.fromMapBoolean(map, "isBuyer");
        trade.isMaker = BinanceUtils.fromMapBoolean(map, "isMaker");
        trade.isBestMatch = BinanceUtils.fromMapBoolean(map, "isBestMatch");
        trade.isBuyerMaker = BinanceUtils.fromMapBoolean(map, "isBuyerMaker");
        return trade;
    }

    @Override
    public String toString() {
        return "Trade{" +
                (symbol != null ? "symbol='" + symbol + '\'' : "") +
                (id != null ?", id=" + id : "") +
                (orderId != null? ", orderId=" + orderId : "") +
                (orderListId != null ?", orderListId=" + orderListId : "") +
                (price != null? ", price=" + price : "") +
                (qty != null? ", qty=" + qty : "") +
                (quoteQty != null?", quoteQty=" + quoteQty:"") +
                (commission != null? ", commission=" + commission:"") +
                (commissionAsset != null? ", commissionAsset='" + commissionAsset + '\'': "") +
                (isBestMatch != null?", isBestMatch=" + isBestMatch:"") +
                (time != null? ", time=" + time:"") +
                (isBuyer !=null? ", isBuyer=" + isBuyer:"") +
                (isMaker != null?", isMaker=" + isMaker:"") +
                (isBuyerMaker != null?", isBuyerMaker=" + isBuyerMaker:"") +
                '}';
    }
}
