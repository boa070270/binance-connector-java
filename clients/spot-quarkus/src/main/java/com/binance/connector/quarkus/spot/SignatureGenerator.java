package com.binance.connector.quarkus.spot;

public interface SignatureGenerator {
    /**
     * RSA, Ed25519, or HMAC
     * @return
     */
    String getAlgorithm();
    byte[] sign(String input) throws CryptoException;

    byte[] sign(byte[] input) throws CryptoException;

    String signAsString(String input) throws CryptoException;

    String signAsString(byte[] input) throws CryptoException;
}
