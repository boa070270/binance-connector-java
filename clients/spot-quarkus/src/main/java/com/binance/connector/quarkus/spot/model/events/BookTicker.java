package com.binance.connector.quarkus.spot.model.events;

import com.binance.connector.quarkus.spot.model.BinanceUtils;
import org.jboss.logging.Logger;

import java.util.Map;

/**
 * {
 *   "u":400900217,     // order book updateId
 *   "s":"BNBUSDT",     // symbol
 *   "b":"25.35190000", // best bid price
 *   "B":"31.21000000", // best bid qty
 *   "a":"25.36520000", // best ask price
 *   "A":"40.66000000"  // best ask qty
 * }
 */
public class BookTicker {
    private static final Logger Log = Logger.getLogger(BookTicker.class);

    private static final String u = "{\"u\":";
    private static final int ui = u.length();
    private static final String s = "\"s\":\"";
    private static final int si = s.length();
    private static final String b = "\"b\":\"";
    private static final int bi = b.length();
    private static final String B = "\"B\":\"";
    private static final int Bi = B.length();
    private static final String a = "\"a\":\"";
    private static final int ai = a.length();
    private static final String A = "\"A\":\"";
    private static final int Ai = A.length();

    public static final BookTicker EMPTY = new BookTicker();
    public final long time = System.nanoTime();
    public Long orderBookUpdateId = 0L; //u
    public String symbol = ""; //s
    public Double bestBidPrice = 0D; //b
    public Double bestBidQty = 0D; //B
    public Double bestAskPrice = 0D; //a
    public Double bestAskQty = 0D; //A
    public final long localTime = System.currentTimeMillis();
    public static BookTicker fromMap(Map m) {
        BookTicker b = new BookTicker();
        try {
            b.orderBookUpdateId = BinanceUtils.fromMapLong(m, "u");
            b.symbol = (String) m.get("s");
            b.bestBidPrice = BinanceUtils.fromMapDouble(m, "b");
            b.bestBidQty = BinanceUtils.fromMapDouble(m, "B");
            b.bestAskPrice = BinanceUtils.fromMapDouble(m, "a");
            b.bestAskQty = BinanceUtils.fromMapDouble(m, "A");
        } catch (Throwable t) {
            Log.error("BookTicker::fromMap", t);
        }
        return b;
    }
    public static BookTicker fromString(String event) {
        BookTicker b = new BookTicker();
        String s = event.substring(0, ui);
        if (!s.equals(u)) throw new RuntimeException("Invalid BookTicket format");
        int i = ui;
        int k = i;
        while (event.charAt(++i) != ',');
        b.orderBookUpdateId = Long.parseLong(event.substring(k, i));
        i += si;
        k = i+1;
        while (event.charAt(++i) != '"');
        b.symbol = event.substring(k, i);
        i++; i += bi; k = i+1;
        while (event.charAt(++i) != '"');
        b.bestBidPrice = Double.parseDouble(event.substring(k, i));
        i++; i += Bi;k = i+1;
        while (event.charAt(++i) != '"');
        b.bestBidQty = Double.parseDouble(event.substring(k, i));
        i++; i += ai;k = i+1;
        while (event.charAt(++i) != '"');
        b.bestAskPrice = Double.parseDouble(event.substring(k, i));
        i++; i += Ai;k = i+1;
        while (event.charAt(++i) != '"');
        b.bestAskQty = Double.parseDouble(event.substring(k, i));
        return b;
    }
    @Override
    public String toString() {
        return "BookTicker{" +
                "orderBookUpdateId=" + orderBookUpdateId +
                ", symbol='" + symbol + '\'' +
                ", bestBidPrice=" + bestBidPrice +
                ", bestBidQty=" + bestBidQty +
                ", bestAskPrice=" + bestAskPrice +
                ", bestAskQty=" + bestAskQty +
                '}';
    }

    public String likeJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"orderBookUpdateId\":\"").append(orderBookUpdateId).append("\",");
        sb.append("\"symbol\":\"").append(symbol).append("\",");
        sb.append("\"bestBidPrice\":\"").append(bestBidPrice).append("\",");
        sb.append("\"bestBidQty\":\"").append(bestBidQty).append("\",");
        sb.append("\"bestAskPrice\":\"").append(bestAskPrice).append("\",");
        sb.append("\"bestAskQty\":\"").append(bestAskQty).append("\"}");
        return sb.toString();
    }
}
