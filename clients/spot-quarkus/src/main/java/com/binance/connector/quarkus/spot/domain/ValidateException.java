package com.binance.connector.quarkus.spot.domain;

public class ValidateException extends Exception {
    public ValidateException(String invalid_order_type) {
    }
}
