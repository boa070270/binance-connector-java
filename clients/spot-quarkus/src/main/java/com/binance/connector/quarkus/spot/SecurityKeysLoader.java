package com.binance.connector.quarkus.spot;

public interface SecurityKeysLoader {
    static class LoaderResult {
        public String apiKey;
        public SignatureGenerator signatureGenerator;
        public LoaderResult(String apiKey, SignatureGenerator signatureGenerator) {
            this.apiKey = apiKey;
            this.signatureGenerator = signatureGenerator;
        }
    }
    LoaderResult load(String storeKey);
}
