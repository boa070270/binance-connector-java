package com.binance.connector.quarkus.spot.model;

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
