package com.binance.connector.quarkus.spot.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseRequestDTO {
    // For request
    private String apiKey;
    private String timestamp;
    private String signature;

    public BaseRequestDTO() {}

    public String getSignature() {
        return signature;
    }
    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getApiKey() {
        return apiKey;
    }
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String toUrlQueryString() {
        return "apiKey=" + apiKey + "&timestamp=" + timestamp;
    }

    @Override
    public String toString() {
        return "apiKey=" + apiKey + "&timestamp=" + timestamp;
    }
}
