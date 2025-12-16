package com.binance.connector.quarkus.spot.model.events.userdata;


import com.binance.connector.quarkus.spot.model.OrderType;
import com.binance.connector.quarkus.spot.model.Side;
import com.binance.connector.quarkus.spot.model.TimeInForce;
import com.binance.connector.quarkus.spot.model.events.AbstractEvent;
import com.binance.connector.quarkus.spot.model.events.BinanceUtils;

import java.util.Map;

public class ExecutionReport extends AbstractEvent {
    public String symbol;                   // "s": "ETHBTC",                 // Symbol 3
    public String clientOrderId;            // "c": "mUvoqJxFIILMdfAW5iGSOW", // Client order ID 4
    public Side side;                   // "S": "BUY",                    // Side 5
    public OrderType type;                  // "o": "LIMIT",                  // Order type 6
    public TimeInForce timeInForceEnum; // "f": "GTC",                    // Time in force 7
    public Double quantity;                 // "q": "1.00000000",             // Order quantity 8
    public Double price;                    // "p": "0.10264410",             // Order price
    public Double stopPrice;                // "P": "0.00000000",             // Stop price
    public Long trailingDelta;              // "d": 4,                        // Trailing Delta; This is only visible if the order was a trailing stop order.
    public Double icebergQty;               // "F": "0.00000000",             // Iceberg quantity
    public Integer orderListId;             // "g": -1,                       // OrderListId
    public String originalClientId;         // "C": "",                       // Original client order ID; This is the ID of the order being canceled
    public OrderStatus status;              // "x": "NEW",                    // Current execution type
    public ExecutionType executionStatus;   // "X": "NEW",                    // Current order status
    public String rejectReason;             // "r": "NONE",                   // Order reject reason; will be an error code.
    public Long orderId;                    // "i": 4293153,                  // Order ID
    public Double lastExecutedQty;          // "l": "0.00000000",             // Last executed quantity
    public Double cumulativeQty;            // "z": "0.00000000",             // Cumulative filled quantity
    public Double lastExecutedPrice;        // "L": "0.00000000",             // Last executed price
    public Double fees;                     // "n": "0",                      // Commission amount
    public String feesAsset;                // "N": null,                     // Commission asset
    public Long transactionTime;            // "T": 1499405658657,            // Transaction time
    public Long tradeId;                    // "t": -1,                       // Trade ID
    public Long ignore;                     // "I": 8641984,                  // Ignore
    public Boolean isBookOrder;             // "w": true,                     // Is the order on the book?
    public Boolean isMaker;                 // "m": false,                    // Is this trade the maker side?
    public Boolean ignore2;                 // "M": false,                    // Ignore
    public Long creationTime;               // "O": 1499405658657,            // Order creation time
    public Double cumulativeQuote;          // "Z": "0.00000000",             // Cumulative quote asset transacted quantity
    public Double lastQuote;                // "Y": "0.00000000",             // Last quote asset transacted quantity (i.e. lastPrice * lastQty)
    public Double quoteQty;                 // "Q": "0.00000000"              // Quote Order Qty
    public boolean isCompleted() {
        return isCompleted(status);
    }
    public boolean isSuccessfullyCompleted() {
        return isSuccessfullyCompleted(status);
    }
    public static boolean isCompleted(OrderStatus status) {
        return status == OrderStatus.CANCELED || status == OrderStatus.FILLED || status == OrderStatus.EXPIRED || status == OrderStatus.REJECTED;
    }
    public static boolean isSuccessfullyCompleted(OrderStatus status) {
        return status == OrderStatus.FILLED;
    }
    public static ExecutionReport fromMap(Map map) {
        ExecutionReport r = new ExecutionReport();
        r.eventType = "executionReport";
        r.eventTime = BinanceUtils.fromMapLong(map, "E");
        r.symbol = (String) map.get("s");
        r.clientOrderId = (String) map.get("c");
        r.side = Side.valueOf((String) map.get("S"));
        r.type = OrderType.valueOf((String) map.get("o"));
        r.timeInForceEnum = TimeInForce.valueOf((String) map.get("f"));
        r.quantity = BinanceUtils.fromMapDouble(map, "q");
        r.price = BinanceUtils.fromMapDouble(map, "p");
        r.stopPrice = BinanceUtils.fromMapDouble(map, "P");
        r.trailingDelta = BinanceUtils.fromMapLong(map, "d");
        r.icebergQty = BinanceUtils.fromMapDouble(map, "F");
        r.orderListId = BinanceUtils.fromMapInt(map, "g");
        r.originalClientId = (String) map.get("C");
        r.status = OrderStatus.valueOf((String) map.get("X"));
        r.executionStatus = ExecutionType.valueOf((String) map.get("x"));
        r.rejectReason = (String) map.get("r");
        r.orderId = BinanceUtils.fromMapLong(map, "i");
        r.lastExecutedQty = BinanceUtils.fromMapDouble(map, "l");
        r.cumulativeQty = BinanceUtils.fromMapDouble(map, "z");
        r.lastExecutedPrice = BinanceUtils.fromMapDouble(map, "L");
        r.fees = BinanceUtils.fromMapDouble(map, "n");
        r.feesAsset = (String) map.get("N");
        r.transactionTime = BinanceUtils.fromMapLong(map, "T");
        r.tradeId = BinanceUtils.fromMapLong(map, "t");
        r.ignore = BinanceUtils.fromMapLong(map,"I");
        r.isBookOrder = BinanceUtils.fromMapBoolean(map, "w");
        r.isMaker = BinanceUtils.fromMapBoolean(map, "m");
        r.ignore2 = BinanceUtils.fromMapBoolean(map, "M");
        r.creationTime = BinanceUtils.fromMapLong(map, "O");
        r.cumulativeQuote = BinanceUtils.fromMapDouble(map, "Z");
        r.lastQuote = BinanceUtils.fromMapDouble(map, "Y");
        r.quoteQty = BinanceUtils.fromMapDouble(map, "Q");
        return r;
    }
    @Override
    public String toString() {
        return "ExecutionReport{" +
                (eventTime != null?", eventTime=" + eventTime : "") +
                (symbol!=null?", symbol='" + symbol + '\'':"") +
                (clientOrderId != null?", clientOrderId='" + clientOrderId + '\'':"") +
                (side!=null?", side=" + side:"") +
                (type!=null?", type=" + type:"") +
                (timeInForceEnum!= null?", timeInForceEnum=" + timeInForceEnum:"") +
                (quantity!=null?", quantity=" + quantity:"") +
                (price!=null?", price=" + price:"") +
                (stopPrice!=null?", stopPrice=" + stopPrice:"") +
                (trailingDelta!=null?", trailingDelta=" + trailingDelta:"") +
                (icebergQty!=null?", icebergQty=" + icebergQty:"") +
                (orderListId!=null?", orderListId=" + orderListId:"") +
                (originalClientId!=null?", originalClientId='" + originalClientId + '\'':"") +
                (status!=null?", status=" + status:"") +
                (executionStatus!=null?", executionStatus=" + executionStatus:"") +
                (rejectReason!=null?", rejectReason='" + rejectReason + '\'':"") +
                (orderId!=null?", orderId=" + orderId:"") +
                (lastExecutedQty !=null?", lastExecutedQty=" + lastExecutedQty :"") +
                (cumulativeQty!=null?", cumulativeQty=" + cumulativeQty:"") +
                (lastExecutedPrice!=null?", lastExecutedPrice=" + lastExecutedPrice:"") +
                (fees!=null?", fees=" + fees:"") +
                (feesAsset!=null?", feesAsset='" + feesAsset + '\'':"") +
                (transactionTime!=null?", transactionTime=" + transactionTime:"") +
                (tradeId!=null?", tradeId=" + tradeId:"") +
                (ignore!=null?", ignore=" + ignore:"") +
                (isBookOrder!=null?", isBookOrder=" + isBookOrder:"") +
                (isMaker!=null?", isMaker=" + isMaker:"") +
                (ignore2!=null?", ignore2=" + ignore2:"") +
                (creationTime!=null?", creationTime=" + creationTime:"") +
                (cumulativeQuote!=null?", cumulativeQuote=" + cumulativeQuote:"") +
                (lastQuote!=null?", lastQuote=" + lastQuote:"") +
                (quoteQty!=null?", quoteQty=" + quoteQty:"") +
                '}';
    }
}
