package com.binance.connector.quarkus.spot.domain;


import com.binance.connector.quarkus.spot.model.BinanceUtils;

public class CalcPrice {
    public PriceTime lastPrice;
    public double avgP;
    public double avgV;
    public double avgA;
    public double sigmaP;
    public double sigmaV;
    public double sigmaA;
    public double avgS3p;
    public double avgS3v;
    public double avgS3a;

    @Override
    public String toString() {
        return "CalcPrice{" +
                "lastPrice=" + lastPrice +
                ", avgP=" + BinanceUtils.pDouble(avgP) +
                ", avgV=" + BinanceUtils.pDouble(avgV) +
                ", avgA=" + BinanceUtils.pDouble(avgA) +
                ", sigmaP=" + BinanceUtils.pDouble(sigmaP) +
                ", sigmaV=" + BinanceUtils.pDouble(sigmaV) +
                ", sigmaA=" + BinanceUtils.pDouble(sigmaA) +
                ", avgS3p=" + BinanceUtils.pDouble(avgS3p) +
                ", avgS3v=" + BinanceUtils.pDouble(avgS3v) +
                ", avgS3a=" + BinanceUtils.pDouble(avgS3a) +
                '}';
    }
}
