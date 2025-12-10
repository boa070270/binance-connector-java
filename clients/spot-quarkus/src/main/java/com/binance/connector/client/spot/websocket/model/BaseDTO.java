package com.binance.connector.client.spot.websocket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseDTO extends BaseRequestDTO {

    private ErrorResponseDTO error;
    @JsonIgnore
    public ErrorResponseDTO getError() {
        return error;
    }
    public void setError(ErrorResponseDTO error) {
        this.error = error;
    }
}
