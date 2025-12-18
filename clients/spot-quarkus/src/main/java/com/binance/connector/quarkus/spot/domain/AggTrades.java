package com.binance.connector.quarkus.spot.domain;

import java.util.Map;

public class AggTrades {
    /**
     * Aggregate tradeId
     */
    public Long aggTradeId; //a
    public Double price; //p
    public Double quantity; //q
    public Long firstTradeId; //f
    public Long lastTradeId; //l
    public Long timestamp; //T
    /**
     * Was the buyer the maker?
     */
    public Boolean isBuyer; //m
    /**
     * Was the trade the best price match?
     */
    public Boolean isBestPrice; //M
    public static AggTrades fromMap(Map map) {
        AggTrades trades = new AggTrades();
        trades.aggTradeId = ((Number) map.get("a")).longValue();
        trades.price = Double.parseDouble((String) map.get("p"));
        trades.quantity = Double.parseDouble((String) map.get("q"));
        trades.firstTradeId = ((Number) map.get("f")).longValue();
        trades.lastTradeId = ((Number) map.get("l")).longValue();
        trades.timestamp = ((Number) map.get("T")).longValue();
        trades.isBuyer = (Boolean) map.get("m");
        trades.isBestPrice = (Boolean) map.get("M");
        return trades;
    }
}
