package com.binance.connector.quarkus.spot.domain;

import java.util.concurrent.atomic.AtomicLong;

public final class GenID {
    public enum PrefixGenIdEnum {UP, DOWN};
    private static GenID INSTANCE; // TODO
    private String PREXIF_UP;
    public String prefixUp() { return PREXIF_UP; }
    private String PREXIF_DOWN;
    public String prefixDown() { return PREXIF_DOWN; }
    private GenID(long l){
        String prefix = Long.toString(l, 36);
        if (prefix.toUpperCase().equals(prefix.toLowerCase())) {
            PREXIF_UP = "BOA"+prefix;
            PREXIF_DOWN = "MEW"+prefix;
        } else {
            PREXIF_UP = prefix.toUpperCase();
            PREXIF_DOWN = prefix.toLowerCase();
        }
    };

    public static GenID getInstance(long l) {
        if (INSTANCE == null)
            INSTANCE = new GenID(l);
        return INSTANCE;
    }

    private final AtomicLong counter = new AtomicLong(10000000);
    private String nextID(PrefixGenIdEnum prefix) {
        String s = "0000000000"+Long.toString(counter.incrementAndGet(), 36);
        return (prefix == PrefixGenIdEnum.UP? PREXIF_UP : PREXIF_DOWN) + s.substring(s.length() - 10);
    }
    public static String next(PrefixGenIdEnum prefix) {
        return getInstance(System.currentTimeMillis()).nextID(prefix);
    }
    public static String next() {
        return getInstance(System.currentTimeMillis()).nextID(PrefixGenIdEnum.UP);
    }
    public static String getPrefix(PrefixGenIdEnum prefix) {
        return prefix == PrefixGenIdEnum.UP? getInstance(System.currentTimeMillis()).PREXIF_UP : getInstance(System.currentTimeMillis()).PREXIF_DOWN;
    }
}
