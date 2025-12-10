package com.binance.connector.client.common.websocket.dtos;

import java.nio.charset.StandardCharsets;

public class BaseDTO extends BaseRequestDTO {
    // For response
    private ErrorResponseDTO error;

    public BaseDTO() {}

    public ErrorResponseDTO getError() {
        return error;
    }

    public void setError(ErrorResponseDTO error) {
        this.error = error;
    }
    public static String asciiEncode(String s) {
        return new String(s.getBytes(), StandardCharsets.US_ASCII);
    }
}
