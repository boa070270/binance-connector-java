package com.binance.connector.quarkus.spot.domain;

import java.util.Arrays;
import java.util.LinkedHashMap;

public interface AbstractRequest<T> {
    /**
     * the weight of request
     * @return
     */
    int weightUID();
    int weightIP();

    boolean isOrder();
    T data();
    static <T> AbstractRequest<T> wrap(T data, int weightUID, int weghtIP, boolean isOrder) {
        return new AbstractRequest<T>() {
            @Override
            public int weightUID() {
                return weightUID;
            }
            @Override
            public int weightIP() {
                return weghtIP;
            }
            @Override
            public boolean isOrder() {
                return isOrder;
            }

            @Override
            public T data() {
                return data;
            }
        };
    }
    static AbstractRequest<LinkedHashMap<String, Object>> exchangeInfoRequest(String ...symbol) {
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        if (symbol.length > 1) {
            data.put("symbols", symbol);
        } else if (symbol.length == 1) {
            data.put("symbol", symbol[0]);
        }
        return wrap(data, 0, 10, false);
    }
    static LinkedHashMap<String,Object> queryOrders(String symbol, Long orderId, Long startTime, Long endTime, Long fromId, Integer limit, Integer recvWindow) throws ValidateException {
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        if (symbol == null) throw new ValidateException("Field symbol is mandatory");
        data.put("symbol", symbol);
        if (orderId != null) {
            data.put("orderId", orderId);
        } else if (startTime != null || endTime != null) {
            data.put("startTime", startTime);
            data.put("endTime", endTime);
        } else if (fromId != null) {
            data.put("fromId",fromId);
        }
        if (limit != null) data.put("limit", limit);
        if (recvWindow != null) data.put("recvWindow", recvWindow);
        return data;
    }
    static AbstractRequest<LinkedHashMap<String, Object>> myTrades(String symbol, Long orderId, Long startTime, Long endTime, Long fromId, Integer limit, Integer recvWindow) throws ValidateException {
        return wrap(queryOrders(symbol, orderId, startTime, endTime, fromId, limit, recvWindow),
                0, 10, false);
    }
    static AbstractRequest<LinkedHashMap<String, Object>> getOrders(String symbol, Long orderId, Long startTime, Long endTime, Long fromId, Integer limit, Integer recvWindow) throws ValidateException {
        return wrap(queryOrders(symbol, orderId, startTime, endTime, fromId, limit, recvWindow),
                0, 10, false);
    }
    static AbstractRequest<LinkedHashMap<String, Object>> getOpenOrders(String symbol, Integer recvWindow) {
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        int weightIP = 40;
        if (symbol != null) {
            data.put("symbol", symbol);
            weightIP = 3;
        }
        if (recvWindow != null) data.put("recvWindow", recvWindow);
        return wrap(data, 0, weightIP, false);
    }
    static AbstractRequest<LinkedHashMap<String, Object>> account(Integer recvWindow) {
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        if (recvWindow != null) data.put("recvWindow", recvWindow);
        return wrap(data, 0, 10, false);
    }
    static String printRequests(AbstractRequest<?>[] requests) {
        return '[' + Arrays.stream(requests).map(AbstractRequest::data).collect(StringBuilder::new, (s,r) -> s.append(r).append(','), StringBuilder::append).toString() +']';
    }
}
