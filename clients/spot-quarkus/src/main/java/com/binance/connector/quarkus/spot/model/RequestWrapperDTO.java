package com.binance.connector.quarkus.spot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestWrapperDTO<T extends BaseRequestDTO, U extends BaseDTO> {
    protected Long id;
    protected T params;
    protected String method;
    @JsonIgnore
    protected transient RequestResponseUnion.DecodeJson<U> responseType;
    @JsonIgnore
    protected transient CompletableFuture<U> responseCallback = new CompletableFuture<>();

    public RequestWrapperDTO() {}

    public RequestWrapperDTO(Long id, T params, String method) {
        this.id = id;
        this.params = params;
        this.method = method;
    }

    public Long getId() {
        return id;
    }

    public T getParams() {
        return params;
    }

    public String getMethod() {
        return method;
    }

    public RequestResponseUnion.DecodeJson<U> getResponseType() {
        return responseType;
    }

    public void setResponseType(RequestResponseUnion.DecodeJson<U> responseType) {
        this.responseType = responseType;
    }

    private RequestWrapperDTO(Builder<T, U> builder) {
        id = builder.id;
        params = builder.params;
        method = builder.method;
        responseType = builder.responseType;
    }

    public CompletableFuture<U> getResponseCallback() {
        return responseCallback;
    }
    public void decodeMsg(String msg) {
        if (responseType != null && responseCallback != null) {
            responseCallback.complete(responseType.decode(msg));
        }
    }
    public static final class Builder<T extends BaseRequestDTO, U extends BaseDTO> {
        private Long id;
        private T params;
        private String method;
        private RequestResponseUnion.DecodeJson<U> responseType;

        public Builder() {}

        public Builder<T, U> id(Long val) {
            id = val;
            return this;
        }

        public Builder<T, U> params(T val) {
            params = val;
            return this;
        }

        public Builder<T, U> method(String val) {
            method = val;
            return this;
        }

        public Builder<T, U> responseType(RequestResponseUnion.DecodeJson<U> val) {
            responseType = val;
            return this;
        }

        public RequestWrapperDTO<T, U> build() {
            return new RequestWrapperDTO<>(this);
        }
    }
}
