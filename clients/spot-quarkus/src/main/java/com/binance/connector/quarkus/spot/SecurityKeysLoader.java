package com.binance.connector.quarkus.spot;

public interface SecurityKeysLoader {
    static class LoaderResult {
        public String apiKey;
        public SignatureGenerator signatureGenerator;
    }
    LoaderResult load(String storeKey);
}
