package com.binance.connector.quarkus.spot;

public enum KindOfResponseEnum {
    ERROR, STREAM_CMD, STREAM_RAW_EVENT, STREAM_COMBINE_EVENT, USR_DATA_STREAM, WEB_SOCKET_API, BOOK_TICKER, PARTIAL_BOOK_DEPTH, UNKNOWN;
    public static KindOfResponseEnum chkKindOfResponse(String msg) {
        if (msg.startsWith("{\"method\":")) {
            return STREAM_CMD;
        } else if (msg.startsWith("{\"code\":")){
            return ERROR;
        } else if (msg.startsWith("{\"e\":")) {
            return STREAM_RAW_EVENT;
        } else if (msg.startsWith("{\"stream\":")) {
            return STREAM_RAW_EVENT;
        } else if (msg.startsWith("{\"subscriptionId\":")) {
            return USR_DATA_STREAM;
        } else if (msg.startsWith("{\"id\":")) {
            return WEB_SOCKET_API;
        } else if (msg.startsWith("{\"u\":")) {
            return BOOK_TICKER;
        } else if (msg.startsWith("{\"lastUpdateId\":")) {
            return PARTIAL_BOOK_DEPTH;
        } else {
            return UNKNOWN;
        }
    }
}
