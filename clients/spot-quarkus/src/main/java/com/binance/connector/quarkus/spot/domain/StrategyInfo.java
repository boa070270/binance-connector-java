package com.binance.connector.quarkus.spot.domain;

import java.util.Map;

public class StrategyInfo {
    private final String id;
    String name;
    String symbol;
    long lastOperation;
    long countOperation;
    double accumulatedMargin;
    double snapshotPrice;
    Map<String, Object> anotherParameters;
    Map<String, String> options;
    public StrategyInfo(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public long getLastOperation() {
        return lastOperation;
    }

    public long getCountOperation() {
        return countOperation;
    }

    public double getAccumulatedMargin() {
        return accumulatedMargin;
    }

    public double getSnapshotPrice() {
        return snapshotPrice;
    }

    public Map<String, Object> getAnotherParameters() {
        return anotherParameters;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setLastOperation(long lastOperation) {
        this.lastOperation = lastOperation;
    }

    public void setCountOperation(long countOperation) {
        this.countOperation = countOperation;
    }

    public void setAccumulatedMargin(double accumulatedMargin) {
        this.accumulatedMargin = accumulatedMargin;
    }

    public void setSnapshotPrice(double snapshotPrice) {
        this.snapshotPrice = snapshotPrice;
    }

    public void setAnotherParameters(Map<String, Object> anotherParameters) {
        this.anotherParameters = anotherParameters;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "StrategyInfo{id=" + id +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", lastOperation=" + lastOperation +
                ", countOperation=" + countOperation +
                ", accumulatedMargin=" + accumulatedMargin +
                ", snapshotPrice=" + snapshotPrice +
                ", anotherParameters=" + anotherParameters +
                '}';
    }
}
