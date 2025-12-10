package com.binance.connector.client.common.websocket.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class StreamBlockingQueue<T> implements BlockingQueue<T> {
    private final BlockingQueue<T> innerQueue;
    private final String operationId;

    public StreamBlockingQueue(BlockingQueue<T> innerQueue) {
        this(innerQueue, "");
    }

    public StreamBlockingQueue(BlockingQueue<T> innerQueue, String operationId) {
        this.innerQueue = innerQueue;
        this.operationId = operationId;
    }

    public String getOperationId() {
        return operationId;
    }

    public BlockingQueue<T> getInnerQueue() {
        return innerQueue;
    }

    @Override
    public boolean offer(T t) {
        return innerQueue.offer(t);
    }

    @Override
    public boolean add(T t) {
        return innerQueue.add(t);
    }

    @Override
    public void put(T t) throws InterruptedException {
        innerQueue.put(t);
    }

    @Override
    public boolean offer(T t, long timeout, TimeUnit unit) throws InterruptedException {
        return innerQueue.offer(t, timeout, unit);
    }

    
    @Override
    public T take() throws InterruptedException {
        return innerQueue.take();
    }


    @Override
    public T poll(long timeout, TimeUnit unit) throws InterruptedException {
        return innerQueue.poll(timeout, unit);
    }

    @Override
    public int remainingCapacity() {
        return innerQueue.remainingCapacity();
    }

    @Override
    public boolean remove(Object o) {
        return innerQueue.remove(o);
    }

    @Override
    public boolean contains(Object o) {
        return innerQueue.contains(o);
    }

    @Override
    public int drainTo(Collection<? super T> c) {
        return innerQueue.drainTo(c);
    }

    @Override
    public int drainTo(Collection<? super T> c, int maxElements) {
        return innerQueue.drainTo(c, maxElements);
    }

    @Override
    public T remove() {
        return innerQueue.remove();
    }

    @Override
    public T poll() {
        return innerQueue.poll();
    }

    @Override
    public T element() {
        return innerQueue.element();
    }

    @Override
    public T peek() {
        return innerQueue.peek();
    }

    @Override
    public int size() {
        return innerQueue.size();
    }

    @Override
    public boolean isEmpty() {
        return innerQueue.isEmpty();
    }

    
    @Override
    public Iterator<T> iterator() {
        return innerQueue.iterator();
    }

    
    @Override
    public Object[] toArray() {
        return innerQueue.toArray();
    }

    
    @Override
    public <T1> T1[] toArray(T1[] a) {
        return innerQueue.toArray(a);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return innerQueue.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return innerQueue.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return innerQueue.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return innerQueue.retainAll(c);
    }

    @Override
    public void clear() {
        innerQueue.clear();
    }
}
