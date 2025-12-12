package com.binance.connector.quarkus.spot;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacSignatureGenerator implements SignatureGenerator {
    private static final String HMAC_SHA256 = "HmacSHA256";
    private final String apiSecret;

    public HmacSignatureGenerator(String apiSecret) {
        //        ParameterChecker.checkParameterType(apiSecret, String.class, "apiSecret");
        this.apiSecret = apiSecret;
    }

    @Override
    public byte[] sign(String input) {
        return sign(input.getBytes());
    }

    @Override
    public byte[] sign(byte[] input) {
        byte[] hmacSha256;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(apiSecret.getBytes(), HMAC_SHA256);
            Mac mac = Mac.getInstance(HMAC_SHA256);
            mac.init(secretKeySpec);
            hmacSha256 = mac.doFinal(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate hmac-sha256", e);
        }
        return hmacSha256;
    }

    @Override
    public String signAsString(String input) {
        return signAsString(input.getBytes());
    }

    @Override
    public String signAsString(byte[] input) {
        return Hex.encodeHexString(sign(input));
    }
}
