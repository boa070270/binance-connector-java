package com.binance.connector.quarkus.spot;

public interface CycleBuffer<T> {
    /**
     * Return the older value that is pulled from buffer
     * @param value can be null!!!
     * @return
     */
    T
    add(T value);

    T get(int index);
    T last();

    int size();
    int capacity();

    boolean isFull();

    void clear();
}
