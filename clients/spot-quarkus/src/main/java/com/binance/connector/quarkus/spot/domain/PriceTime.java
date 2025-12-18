package com.binance.connector.quarkus.spot.domain;

public class PriceTime {
    public final long time;
    public final double price;
    public double qty;
    public double v;
    public double a;
    public double v2;
    public double a2;
    public PriceTime(long time, double price, double qty) {
        this.time = time;
        this.price = price;
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "PriceTime{" +
                "time=" + time +
                ", price=" + price +
                ", qty=" + qty +
                ", v=" + v +
                ", a=" + a +
                ", v2=" + v2 +
                ", a2=" + a2 +
                '}';
    }
}
