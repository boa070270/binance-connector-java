package com.binance.connector.quarkus.spot.domain;


import com.binance.connector.quarkus.spot.domain.enums.SideEnum;

public class Deal {
//    String symbol;
    public SideEnum side;
    public double qty;
    public int count = 1;
    public double firstPrice;
    public double lastPrice;
    public double accumulatePrice;
    public String sellerOrderID;
    public String buyerOrderID;
    public int isBuyerMarketMakerTrue;
    public int isBuyerMarketMakerFalse;
    public Deal(String tradeEvent) {
//        int s = tradeEvent.indexOf("s\":\"")+4;
//        symbol = tradeEvent.substring(s, tradeEvent.indexOf("\"",s));
        int s = tradeEvent.indexOf("p\":\"")+4;
        accumulatePrice = Double.parseDouble(tradeEvent.substring(s, tradeEvent.indexOf("\"",s)));
        firstPrice = accumulatePrice;
        lastPrice = accumulatePrice;
        s = tradeEvent.indexOf("q\":\"", s)+4;
        qty = Double.parseDouble(tradeEvent.substring(s, tradeEvent.indexOf("\"",s)));
        s = tradeEvent.indexOf("b\":", s)+4;
        buyerOrderID = tradeEvent.substring(s, tradeEvent.indexOf(",",s));
        s = tradeEvent.indexOf("a\":", s)+4;
        sellerOrderID = tradeEvent.substring(s, tradeEvent.indexOf(",",s));
        s = tradeEvent.indexOf("m\":\"", s)+4;
        boolean isBuyerMarketMaker = Boolean.parseBoolean(tradeEvent.substring(s, tradeEvent.indexOf(",",s)));
        if (isBuyerMarketMaker) isBuyerMarketMakerTrue = 1;
        else isBuyerMarketMakerFalse = 1;
    }
    public String formatStr() {
        return String.format("side: %1$4s, amount:%2$ 14.6f, count:%3$ 4d, firstPrice:%4$14.6f, lastPrice:%5$14.6f, avgPrice:%6$14.6f, marketBuyerTrue:%7$ 3d, marketBuyerFalse:%8$ 3d",
                side, qty, count, firstPrice, lastPrice, accumulatePrice/count, isBuyerMarketMakerTrue, isBuyerMarketMakerFalse
                );
    }
    @Override
    public String toString() {
        return "Deal{" +
//                "symbol='" + symbol + '\'' +
                ", side=" + side +
                ", amount=" + qty +
                ", count=" + count +
                ", firstPrice=" + firstPrice +
                ", lastPrice=" + lastPrice +
                ", price=" + accumulatePrice/count +
//                ", buyerOrderID='" + buyerOrderID + '\'' +
//                ", sellerOrderID='" + sellerOrderID + '\'' +
                ", isBuyerMarketMakerTrue=" + isBuyerMarketMakerTrue +
                ", isBuyerMarketMakerFalse=" + isBuyerMarketMakerFalse +
                '}';
    }
}
