package com.binance.connector.client.spot.websocket.model;

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
}
