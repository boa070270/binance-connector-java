package com.binance.connector.quarkus.spot.model.events;

import com.binance.connector.quarkus.spot.model.BinanceUtils;
import org.jboss.logging.Logger;

import java.util.Map;

public class TradeEvent extends AbstractEvent implements Cloneable {
    private static final Logger Log = Logger.getLogger(TradeEvent.class);
    private static final String e = "{\"e\":\"trade\",";
    private static final int ei = e.length();
    private static final String E = "\"E\":";
    private static final int Ei = E.length();
    private static final String s = "\"s\":\"";
    private static final int si = s.length();
    private static final String t = "\"t\":";
    private static final int ti = t.length();
    private static final String p = "\"p\":\"";
    private static final int pi = p.length();
    private static final String q = "\"q\":\"";
    private static final int qi = q.length();
    private static final String b = "\"b\":";
    private static final int bi = b.length();
    private static final String a = "\"a\":";
    private static final int ai = a.length();
    private static final String T = "\"T\":";
    private static final int Ti = T.length();
    private static final String m = "\"m\":";
    private static final int mi = m.length();
    public final long time = System.nanoTime();
    public String symbol;
    public long tradeID;
    public Double price;
    public Double quantity;
    public long buyerOrderID;
    public long sellerOrderID;
    public long registerTime;
    public boolean isBuyerMarketMaker;
    /**
     "e": "trade",     // Event type
     "E": 123456789,   // Event time
     "s": "BNBBTC",    // Symbol
     "t": 12345,       // Trade ID
     "p": "0.001",     // Price
     "q": "100",       // Quantity
     "b": 88,          // Buyer order ID
     "a": 50,          // Seller order ID
     "T": 123456785,   // Trade time
     "m": true,        // Is the buyer the market maker?
     "M": true         // Ignore
     */
    public static TradeEvent fromMap(Map<String,Object> m) {
        TradeEvent t = new TradeEvent();
        try {
            t.eventType = "trade";
            t.eventTime = BinanceUtils.fromMapLong(m, "E");
            t.symbol = (String) m.get("s");
            t.tradeID = BinanceUtils.fromMapLong(m, "t");
            t.price = BinanceUtils.fromMapDouble(m, "p");
            t.quantity = BinanceUtils.fromMapDouble(m, "q");
            t.buyerOrderID = BinanceUtils.fromMapLong(m, "b");
            t.sellerOrderID = BinanceUtils.fromMapLong(m, "a");
            t.registerTime = BinanceUtils.fromMapLong(m, "T");
            t.isBuyerMarketMaker = BinanceUtils.fromMapBoolean(m, "m");
        }catch (Throwable e) {
            Log.error("TradeEvent::fromMap", e);
        }
        return t;
    }
    public static TradeEvent fromString(String event) {
        TradeEvent t = new TradeEvent();
        String s = event.substring(0, ei);
        if (!s.equals(e)) throw new RuntimeException("Invalid TradeEvent format");
        t.eventType = "trade";
        int i = ei + Ei - 1;
        int k = i+1;
        while (event.charAt(++i) != ',');
        t.eventTime = Long.parseLong(event.substring(k, i));
        i += si; k = i+1;
        while (event.charAt(++i) != '"');
        t.symbol = event.substring(k, i);
        i++; i += ti; k = i+1;
        while (event.charAt(++i) != ',');
        t.tradeID = Long.parseLong(event.substring(k, i));
        i += pi; k = i+1;
        while (event.charAt(++i) != '"');
        t.price = Double.parseDouble(event.substring(k, i));
        i++; i += qi; k = i+1;
        while (event.charAt(++i) != '"');
        t.quantity = Double.parseDouble(event.substring(k, i));
        i++; i += bi;k = i+1;
        while (event.charAt(++i) != ',');
        t.buyerOrderID = Long.parseLong(event.substring(k, i));
        i += ai;k = i+1;
        while (event.charAt(++i) != ',');
        t.sellerOrderID = Long.parseLong(event.substring(k, i));
        i += Ti; k = i+1;
        while (event.charAt(++i) != ',');
        t.registerTime = Long.parseLong(event.substring(k, i));
        i += mi; k = i +1;
        while (event.charAt(++i) != ',');
        t.isBuyerMarketMaker = Boolean.parseBoolean(event.substring(k, i));
        return t;
    }
    @Override
    public String toString() {
        return "Trade{" +
                "eventTime=" + eventTime +
                ", symbol='" + symbol + '\'' +
                ", tradeID=" + tradeID +
                ", price=" + price +
                ", quantity=" + quantity +
                ", buyerOrderID=" + buyerOrderID +
                ", sellerOrderID=" + sellerOrderID +
                ", tradeTime=" + registerTime +
                ", isBuyerMarketMaker=" + isBuyerMarketMaker +
                '}';
    }
    public String likeJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"eventTime\":").append(eventTime).append(',');
        sb.append("\"symbol\":\"").append(symbol).append("\",");
        sb.append("\"tradeID\":\"").append(tradeID).append("\",");
        sb.append("\"price\":\"").append(price).append("\",");
        sb.append("\"quantity\":\"").append(quantity).append("\",");
        sb.append("\"tradeTime\":").append(registerTime).append(",");
        sb.append("\"isBuyerMarketMaker\":\"").append(isBuyerMarketMaker).append("\"}");
        return sb.toString();
    }
    @Override
    public TradeEvent clone() {
        try {
            TradeEvent clone = (TradeEvent) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
