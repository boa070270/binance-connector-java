package com.binance.connector.quarkus.spot;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class JdkPrivateKey implements SignatureGenerator {

    private final Signature signature;

    public JdkPrivateKey(String algorithm, byte[] keyContent) {
        try {
            String pem = new String(keyContent);
            pem = removeHeaderFooter(pem);
            byte[] der = Base64.getDecoder().decode(pem);

            // algorithm: "Ed25519" або "RSA"
            KeyFactory kf = KeyFactory.getInstance(algorithm);
            PrivateKey privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(der));

            // algo for Signature: "Ed25519" або "SHA256withRSA"
            String sigAlgorithm = switch (algorithm) {
                case "Ed25519" -> "Ed25519";
                case "RSA" -> "SHA256withRSA";
                default -> throw new IllegalArgumentException("Unsupported algo: " + algorithm);
            };

            this.signature = Signature.getInstance(sigAlgorithm);
            this.signature.initSign(privateKey);
        } catch (Exception e) {
            throw new RuntimeException("Failed to init private key", e);
        }
    }

    @Override
    public synchronized byte[] sign(String input) {
        return sign(input.getBytes());
    }

    @Override
    public byte[] sign(byte[] input) {
        try {
            signature.update(input);
            byte[] raw = signature.sign();
            return Base64.getEncoder().encode(raw);
        } catch (Exception e) {
            throw new RuntimeException("Failed to sign", e);
        }
    }

    @Override
    public String signAsString(String input) {
        return new String(sign(input));
    }

    @Override
    public String signAsString(byte[] input) {
        return "";
    }

    private String removeHeaderFooter(String s) {
        return s.replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replace("\r", "")
                .replace("\n", "")
                .trim();
    }
}
