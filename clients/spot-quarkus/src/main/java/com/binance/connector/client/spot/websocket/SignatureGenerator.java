package com.binance.connector.client.spot.websocket;

public interface SignatureGenerator {
    byte[] sign(String input) throws CryptoException;

    byte[] sign(byte[] input) throws CryptoException;

    String signAsString(String input) throws CryptoException;

    String signAsString(byte[] input) throws CryptoException;
}
