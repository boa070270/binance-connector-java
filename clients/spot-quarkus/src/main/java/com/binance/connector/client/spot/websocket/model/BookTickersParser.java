package com.binance.connector.client.spot.websocket.model;

import jakarta.enterprise.context.Dependent;
import org.jboss.logging.Logger;

/**
 * BookTickers parser
 * Parse string {"stream":"ethusdt@bookTicker","data":{"u":63486082052,"s":"ETHUSDT","b":"3700.00000000","B":"33.99730000","a":"3700.01000000","A":"19.89620000"}}
 * returns BookTickers object
 */
@Dependent
public class BookTickersParser {
    private static final Logger LOG = Logger.getLogger(BookTickersParser.class);
    static final String updateIdMark = "\"u\":";
    static final String symbolMark = "\"s\":\"";
    static final String bidPrefixMark = "\"b\":\"";
    static final String bidQtyMark = "\"B\":\"";
    static final String askPrefixMark = "\"a\":\"";;
    static final String askQtyMark = "\"A\":\"";;
    public BookTicker parse(String msg) {
        if (msg == null) return null;
        int i = msg.indexOf(updateIdMark);
        if (i < 0) {
            LOG.warn("No 'updateId' found in message: " + msg);
            return null;
        }
        int from = i + updateIdMark.length();
        i = msg.indexOf(',', from);
        if (i < 0) {
            LOG.warn("Unterminated string: 'updateId' " + msg);
            return null;
        }
        long updateId = Long.parseLong(msg.substring(from, i));
        from = i + 1;
        i = msg.indexOf(symbolMark, from);
        if (i < 0) {
            LOG.warn("No 'symbol' found in message: " + msg);
            return null;
        }
        from = i + symbolMark.length();
        i = msg.indexOf("\",", from);
        String symbol = msg.substring(from, i);
        from = i + 2;
        i = msg.indexOf(bidPrefixMark, from);
        if (i < 0) {
            LOG.warn("No 'b' found in message: " + msg);
            return null;
        }
        from = i + bidPrefixMark.length();
        i = msg.indexOf("\",", from);
        double bidPrice = Double.parseDouble(msg.substring(from, i));
        from = i + 2;
        i = msg.indexOf(bidQtyMark, from);
        if (i < 0) {
            LOG.warn("No 'B' found in message: " + msg);
            return null;
        }
        from = i + bidQtyMark.length();
        i = msg.indexOf("\",", from);
        double bidQty = Double.parseDouble(msg.substring(from, i));
        from = i + 2;
        i = msg.indexOf(askPrefixMark, from);
        if (i < 0) {
            LOG.warn("No 'a' found in message: " + msg);
            return null;
        }
        from = i + askPrefixMark.length();
        i = msg.indexOf("\",", from);
        double askPrice = Double.parseDouble(msg.substring(from, i));
        from = i + 2;
        i = msg.indexOf(askQtyMark, from);
        if (i < 0) {
            LOG.warn("No 'A' found in message: " + msg);
            return null;
        }
        from = i + askPrefixMark.length();
        i = msg.indexOf("\"", from);
        double askQty = Double.parseDouble(msg.substring(from, i));
        return new BookTicker(updateId, symbol, bidPrice, bidQty, askPrice, askQty);
    }
}
