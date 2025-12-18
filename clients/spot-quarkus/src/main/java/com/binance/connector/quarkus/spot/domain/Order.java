package com.binance.connector.quarkus.spot.domain;

import com.binance.connector.quarkus.spot.domain.enums.OrderType;
import com.binance.connector.quarkus.spot.domain.enums.SideEnum;
import com.binance.connector.quarkus.spot.domain.enums.TimeInForceEnum;

import java.util.LinkedHashMap;

public class Order {
    public String symbol;
    public SideEnum side;
    public OrderType type;
    /**
     * Order time in force
     *  At Binance, you can place GTC (Good-Till-Cancel), IOC (Immediate-Or-Cancel), or FOK (Fill-Or-Kill) orders:
     *
     *  GTC (Good-Till-Cancel): the order will last until it is completed or you cancel it.
     *  IOC (Immediate-Or-Cancel): the order will attempt to execute all or part of it immediately at the price and quantity available,
     *  then cancel any remaining, unfilled part of the order. If no quantity is available at the chosen price when you place the order,
     *  it will be canceled immediately. Please note that Iceberg orders are not supported.
     *  FOK (Fill-Or-Kill): the order is instructed to execute in full immediately (filled), otherwise it will be canceled (killed).
     *  Please note that Iceberg orders are not supported.
     */
    public TimeInForceEnum timeInForce;
    public Double quantity;
    public Double quoteOrderQty;
    public Double price;
    /**
     * A unique id among open orders. Automatically generated if not sent.
     */
    public String newClientOrderId;
    /**
     * Used with STOP_LOSS, STOP_LOSS_LIMIT, TAKE_PROFIT, and TAKE_PROFIT_LIMIT orders.
     */
    public Double stopPrice;
    /**
     * Used with STOP_LOSS, STOP_LOSS_LIMIT, TAKE_PROFIT, and TAKE_PROFIT_LIMIT orders.
     * For more details on SPOT implementation on trailing stops,
     * please refer to Trailing Stop FAQ <a href="https://github.com/binance/binance-spot-api-docs/blob/master/faqs/trailing-stop-faq.md">
     */
    public Long trailingDelta;
    /**
     * Used with LIMIT, STOP_LOSS_LIMIT, and TAKE_PROFIT_LIMIT to create an iceberg order.
     */
    public Double icebergQty;
    /**
     * Set the response JSON. ACK, RESULT, or FULL; MARKET and LIMIT order types default to FULL, all other orders default to ACK.
     */
    public Double newOrderRespType;
    /**
     * The value cannot be greater than 60000
     */
    public Long recvWindow;
    public Long timestamp;
    /**
     * Additional mandatory parameters based on type:
     * LIMIT 	timeInForce, quantity, price
     * MARKET 	quantity or quoteOrderQty
     * STOP_LOSS 	quantity, stopPrice or trailingDelta
     * STOP_LOSS_LIMIT 	timeInForce, quantity, price, stopPrice or trailingDelta
     * TAKE_PROFIT 	quantity, stopPrice or trailingDelta
     * TAKE_PROFIT_LIMIT 	timeInForce, quantity, price, stopPrice or trailingDelta
     * LIMIT_MAKER 	quantity, price
     */
    public static Order makeOrder(String symbol, SideEnum side, OrderType type, GenID.PrefixGenIdEnum prefix) throws ValidateException {
        if (symbol == null)
            throw new ValidateException("Invalid symbol");
        Order order = new Order();
        order.symbol = symbol;
        order.side = side;
        order.type = type;
        order.newClientOrderId = GenID.next(prefix);
        return order;
    }
    public static Order makeOrder(String symbol, SideEnum side, OrderType type) throws ValidateException {
        return makeOrder(symbol, side, type, GenID.PrefixGenIdEnum.DOWN);
    }
    public static Order makeOrderLimit(String symbol, SideEnum side, TimeInForceEnum timeInForce, double quantity, double price) throws ValidateException {
        Order order = makeOrder(symbol, side, OrderType.LIMIT);
        order.timeInForce = timeInForce;
        order.quantity = quantity;
        order.price = price;
        return order;
    }
    public static Order makeOrderMarket(String symbol, SideEnum side, double quantity, double quoteOrderQty) throws ValidateException {
        Order order = makeOrder(symbol, side, OrderType.MARKET);
        if (quantity <= 0 && quoteOrderQty <= 0) {
            throw new ValidateException("One parameter quantity or quoteOrderQty must be greater than 0");
        }
        if (quantity > 0)
            order.quantity = quantity;
        else
            order.quoteOrderQty = quoteOrderQty;
        return order;
    }
    public static Order makeOrderStopLoss(String symbol, SideEnum side, double quantity, double stopPrice, long trailingDelta) throws ValidateException {
        Order order = makeOrder(symbol, side, OrderType.STOP_LOSS);
        if (quantity <= 0) {
            throw new ValidateException("Invalid parameter quantity");
        }
        order.quantity = quantity;
        order.stopPrice = stopPrice;
        order.trailingDelta = trailingDelta;
        return order;
    }
    public static Order makeOrderStopLossLimit(String symbol, SideEnum side, String type, TimeInForceEnum timeInForce, double quantity, double price, double stopPrice, long trailingDelta) throws ValidateException {
        Order order = makeOrder(symbol, side, OrderType.STOP_LOSS_LIMIT);
        order.timeInForce = timeInForce;
        order.quantity = quantity;
        order.price = price;
        order.stopPrice = stopPrice;
        order.trailingDelta = trailingDelta;
        return order;
    }
    public static Order makeOrderTakeProfit(String symbol, SideEnum side, double quantity, double stopPrice, long trailingDelta) throws ValidateException {
        Order order = makeOrder(symbol, side, OrderType.TAKE_PROFIT);
        if (quantity <= 0) {
            throw new ValidateException("Invalid parameter quantity");
        }
        order.quantity = quantity;
        order.stopPrice = stopPrice;
        order.trailingDelta = trailingDelta;
        return order;
    }
    public static Order makeOrderTakeProfitLimit(String symbol, SideEnum side, String type, TimeInForceEnum timeInForce, double quantity, double price, double stopPrice, long trailingDelta) throws ValidateException {
        Order order = makeOrder(symbol, side, OrderType.TAKE_PROFIT_LIMIT);
        order.timeInForce = timeInForce;
        order.quantity = quantity;
        order.price = price;
        order.stopPrice = stopPrice;
        order.trailingDelta = trailingDelta;
        return order;
    }
    public static Order makeOrderLimitMaker(String symbol, SideEnum side, double quantity, double price) throws ValidateException {
        Order order = makeOrder(symbol, side, OrderType.LIMIT_MAKER);
        order.quantity = quantity;
        order.price = price;
        return order;
    }
    public LinkedHashMap<String, Object> parameters() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("symbol", this.symbol);
        map.put("side", this.side.name());
        map.put("type", this.type.name());
        if (this.timeInForce != null) map.put("timeInForce", this.timeInForce.name());
        if (this.quantity != null) map.put("quantity", this.quantity);
        if (this.quoteOrderQty != null) map.put("quoteOrderQty", this.quoteOrderQty);
        if (this.price != null) map.put("price", this.price);
        if (this.newClientOrderId != null) map.put("newClientOrderId", this.newClientOrderId);
        if (this.stopPrice != null) map.put("stopPrice", this.stopPrice);
        if (this.trailingDelta != null) map.put("trailingDelta", this.trailingDelta);
        if (this.icebergQty != null) map.put("icebergQty", this.icebergQty);
        if (this.newOrderRespType != null) map.put("newOrderRespType", this.newOrderRespType);
        if (this.recvWindow != null) map.put("recvWindow", this.recvWindow);
        if (this.timestamp != null) map.put("timestamp", this.timestamp);
        return map;
    }

    @Override
    public String toString() {
        return "Order{" +
                "symbol='" + symbol + '\'' +
                ", side=" + side +
                ", type=" + type +
                (timeInForce != null ? ", timeInForce=" + timeInForce : "") +
                (quantity != null?", quantity=" + quantity:"") +
                (quoteOrderQty != null?", quoteOrderQty=" + quoteOrderQty:"") +
                (price!=null?", price=" + price:"") +
                (newClientOrderId!=null?", newClientOrderId='" + newClientOrderId:"") + '\'' +
                (stopPrice!=null?", stopPrice=" + stopPrice:"") +
                (trailingDelta!=null?", trailingDelta=" + trailingDelta:"") +
                (icebergQty!=null?", icebergQty=" + icebergQty:"") +
                (newOrderRespType!=null?", newOrderRespType=" + newOrderRespType:"") +
                (recvWindow!=null?", recvWindow=" + recvWindow:"") +
                (timestamp!=null?", timestamp=" + timestamp:"") +
                '}';
    }
    public static AbstractRequest<Order> wrapOrder(Order order) {
        return AbstractRequest.wrap(order, 1, 1, true);
    }
}
