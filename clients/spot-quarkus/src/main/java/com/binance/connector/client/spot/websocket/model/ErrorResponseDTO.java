package com.binance.connector.client.spot.websocket.model;

public class ErrorResponseDTO {
    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
