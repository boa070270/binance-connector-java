package com.binance.connector.quarkus.spot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiRequestWrapperDTO<T extends BaseRequestDTO, U extends BaseDTO> extends RequestWrapperDTO<T, U> {
    @JsonIgnore
    private transient boolean signed = true;
    @JsonIgnore
    private transient boolean apiKeyOnly = false;
    @JsonIgnore
    public boolean isApiKeyOnly() {
        return apiKeyOnly;
    }
    @JsonIgnore
    public boolean isSigned() {
        return signed;
    }
    @JsonIgnore
    public void setSigned(boolean signed) {
        this.signed = signed;
    }

    public ApiRequestWrapperDTO(Long id, T params, String method, boolean signed) {
        super(id, params, method);
        this.signed = signed;
    }

    public T getParams() {
        return params;
    }

    private ApiRequestWrapperDTO(Builder<T, U> builder) {
        id = builder.id;
        params = builder.params;
        method = builder.method;
        responseType = builder.responseType;
        signed = builder.signed;
        apiKeyOnly = builder.apiKeyOnly;
    }

    public static final class Builder<T extends BaseRequestDTO, U extends BaseDTO> {
        private Long id;
        private T params;
        private String method;
        private RequestResponseUnion.DecodeJson<U> responseType;
        private boolean signed = true;
        private boolean apiKeyOnly = false;

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

        public Builder<T, U> signed(Boolean val) {
            signed = val;
            return this;
        }

        public Builder<T, U> apiKeyOnly(Boolean val) {
            apiKeyOnly = val;
            return this;
        }

        public ApiRequestWrapperDTO<T, U> build() {
            return new ApiRequestWrapperDTO<>(this);
        }
    }
}
