package com.binance.connector.client.spot.websocket.model;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * BookTickers parser
 * {
 *   "u":400900217,     // order book updateId
 *   "s":"BNBUSDT",     // symbol
 *   "b":"25.35190000", // best bid price
 *   "B":"31.21000000", // best bid qty
 *   "a":"25.36520000", // best ask price
 *   "A":"40.66000000"  // best ask qty
 * }
 */
public class BookTicker {
    final long u;
    final String s;
    final double b;
    final double B;
    final double a;
    final double A;
    static final int BINARY_SIZE = /*u*/ 8 + /*symbol*/ 16 + /*b,B,a,A*/ 4*8;
    public BookTicker(long u, String s, double b, double B, double a, double A) {
        this.u = u;
        this.s = s;
        this.b = b;
        this.B = B;
        this.a = a;
        this.A = A;
    }
    public long getUpdateId() {
        return u;
    }
    public String getSymbol() {
        return s.toLowerCase();
    }
    public double getBidPrice() {
        return b;
    }
    public double getBidQty() {
        return B;
    }
    public double getAskPrice() {
        return a;
    }
    public double getAskQty() {
        return A;
    }
    public byte[] payload() {
        ByteBuffer bb = ByteBuffer.allocate(BINARY_SIZE).order(java.nio.ByteOrder.LITTLE_ENDIAN);
        bb.putLong(u);
        byte[] sym16 = new byte[16];
        byte[] sBytes = s.getBytes(StandardCharsets.US_ASCII);
        int copyLen = Math.min(sBytes.length, 16);
        System.arraycopy(sBytes, 0, sym16, 8, copyLen);
        bb.put(sym16);
        bb.putDouble(b);
        bb.putDouble(B);
        bb.putDouble(a);
        bb.putDouble(A);
        return bb.array();
    }
    public static BookTicker parse(byte[] payload) {
        ByteBuffer bb = ByteBuffer.wrap(payload).order(java.nio.ByteOrder.LITTLE_ENDIAN);
        long u = bb.getLong();
        byte[] sBytes = new byte[16];
        bb.get(sBytes);
        String s = new String(sBytes, StandardCharsets.US_ASCII);
        double b = bb.getDouble();
        double B = bb.getDouble();
        double a = bb.getDouble();
        double A = bb.getDouble();
        return new BookTicker(u, s, b, B, a, A);
    }
}
