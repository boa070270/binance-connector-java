package com.binance.connector.quarkus.spot.model.events.userdata;

import com.binance.connector.quarkus.spot.model.events.AbstractEvent;
import com.binance.connector.quarkus.spot.model.BinanceUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ListStatusReport extends AbstractEvent {
    public String symbol;                   // "s": "ETHBTC",                 // Symbol
    public Integer orderListId;             // "g": 2,                           //OrderListId
    public String contingencyType;          // "c": "OCO",                       //Contingency Type
    public String listStatusType;           // "l": "EXEC_STARTED",              //List Status Type
    public String listOrderStatus;          // "L": "EXECUTING",                 //List Order Status
    public String listRejectReason;         // "r": "NONE",                      //List Reject Reason
    public String listClientOrderID;        // "C": "F4QN4G8DlFATFlIUQ0cjdD",    //List Client Order ID
    public Long transactionTime;            // "T": 1499405658657,            // Transaction time
    public ListOCOOrder[] listOrders;          // "O":                             //An array of objects
                                            //  [{"s": "ETHBTC", /*Symbol*/ "i": 17, /* orderId */ "c": "AJYsMjErWJesZvqlJCTUgL" /*ClientOrderId*/},
                                            //   {"s": "ETHBTC", "i": 18, "c": "bfYPSQdLoqAJeNrOr9adzq"}]
    public static ListStatusReport fromMap(Map<String,?> map) {
        ListStatusReport r = new ListStatusReport();
        r.eventType = "listStatus";
        r.eventTime = BinanceUtils.fromMapLong(map, "E");
        r.symbol = (String) map.get("s");
        r.orderListId = BinanceUtils.fromMapInt(map, "g");
        r.contingencyType = (String) map.get("c");
        r.listStatusType = (String) map.get("l");
        r.listOrderStatus = (String) map.get("L");
        r.listRejectReason = (String) map.get("r");
        r.listClientOrderID = (String) map.get("C");
        r.transactionTime = BinanceUtils.fromMapLong(map, "T");
        r.listOrders = BinanceUtils.listToArray((List<Map<String,?>>) map.get("O"), ListOCOOrder::fromMap, ListOCOOrder[]::new, new ListOCOOrder[0]);
        return r;
    }

    @Override
    public String toString() {
        return "ListStatusReport{" +
                "eventType='" + eventType + '\'' +
                ", eventTime=" + eventTime +
                (symbol!=null?", symbol='" + symbol + '\'':"") +
                (orderListId!=null?", orderListId=" + orderListId:"") +
                (contingencyType!=null?", contingencyType='" + contingencyType + '\'':"") +
                (listStatusType!=null?", listStatusType='" + listStatusType + '\'':"") +
                (listOrderStatus!=null?", listOrderStatus='" + listOrderStatus + '\'':"") +
                (listRejectReason!=null?", listRejectReason='" + listRejectReason + '\'':"") +
                (listClientOrderID!=null?", listClientOrderID='" + listClientOrderID + '\'':"") +
                (transactionTime!=null?", transactionTime=" + transactionTime:"") +
                ", listOrders=" + Arrays.toString(listOrders) +
                '}';
    }

    public static class ListOCOOrder {
        public String symbol;
        public Long orderId;
        public String clientOrderId;
        public static ListOCOOrder fromMap(Map map) {
            ListOCOOrder r = new ListOCOOrder();
            r.symbol = (String) map.get("s");
            r.clientOrderId = (String) map.get("c");
            r.orderId = BinanceUtils.fromMapLong(map, "i");
            return r;
        }

        @Override
        public String toString() {
            return "ListOCOOrder{" +
                    "symbol='" + symbol + '\'' +
                    ", orderId=" + orderId +
                    ", clientOrderId='" + clientOrderId + '\'' +
                    '}';
        }
    }
}
