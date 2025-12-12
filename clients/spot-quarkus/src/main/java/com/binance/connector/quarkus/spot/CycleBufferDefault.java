package com.binance.connector.quarkus.spot;

import java.util.function.Function;
import java.util.function.Supplier;

public class CycleBufferDefault<T> implements CycleBuffer<T> {
    protected final short capacity;
    protected final T[] buffer;
    protected char writePosition;
    protected char readPosition;
    protected char filled;
    public CycleBufferDefault(short capacity, Function<Integer,  T[]> bufferSupplier, Supplier<T> fillBuffer) {
        buffer = bufferSupplier.apply((int) capacity);
        this.capacity = capacity;
        if (fillBuffer != null) {
            for (int i = 0; i < capacity; ++i) {
                buffer[i] = fillBuffer.get();
            }
        }
    }
    @Override
    public T add(T value) {
        T result = null;
        if (filled == capacity) {
            result = buffer[writePosition];
            if (readPosition == writePosition) {
                readPosition++;
                if (readPosition == capacity) readPosition = 0;
            }
        } else {
            filled++;
        }
        buffer[writePosition++] = value;
        if (writePosition == capacity) {
            writePosition = 0;
        }
        return result;
    }
    @Override
    public T get(int index) {
        if (index < 0) index = filled + index;
        if (index < 0 || index >= capacity) throw new IndexOutOfBoundsException("Index "+ index +", capacity "+ capacity);
        int i = (readPosition + index);
        if (i >= capacity) i -= capacity;
        return buffer[i];
    }

    @Override
    public T last() {
        short p = (short) (writePosition - 1);
        return buffer[p < 0? capacity -1: p];
    }

    @Override
    public int size() {
        return filled;
    }

    @Override
    public int capacity() {
        return capacity;
    }
    @Override
    public boolean isFull() {
        return filled == capacity;
    }

    @Override
    public void clear() {
        filled = 0;
        readPosition = 0;
        writePosition = 0;
    }
}
