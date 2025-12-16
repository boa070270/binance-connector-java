package com.binance.connector.quarkus.spot.model.events;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntFunction;

public class BinanceUtils {
    public static String pDouble(double d) {
        return BigDecimal.valueOf(d).setScale(8, RoundingMode.HALF_DOWN).toPlainString();
    }
    public static double sigma(double[] source, double avg) {
        if (source.length > 1) {
            double s = 0;
            for (int i = 0; i < source.length; i++) {
                double k = source[i] - avg;
                s += k * k;
            }
            s = Math.sqrt(s / (source.length - (source.length <= 50 ? 1 : 0)));
            return s;
        }
        return source.length > 0 ? (source[0] - avg)*(source[0] - avg) / 2 : 0;
    }
    static Number asNumber(Object n, Function<String, Number> cnv) {
        if (n instanceof Number)
            return (Number) n;
        return cnv.apply((String) n);
    }
    public static Long fromMapLong(Map<String,?> m, String name) {
        if (m != null && m.get(name) != null) {
            return asNumber(m.get(name), Long::parseLong).longValue();
        }
        return null;
    }
    public static Integer fromMapInt(Map<String,?> m, String name) {
        if (m != null && m.get(name) != null) {
            return asNumber(m.get(name), Integer::parseInt).intValue();
        }
        return null;
    }
    public static Boolean fromMapBoolean(Map<String,?> m, String name) {
        if (m != null && m.get(name) != null) {
            return (boolean) m.get(name);
        }
        return null;
    }
    public static Double fromMapDouble(Map<String,?> m, String name) {
        if (m != null && m.get(name) != null) {
            return asNumber(m.get(name), Double::parseDouble).doubleValue();
        }
        return null;
    }
    public static <A> A[] listToArray(List<Map<String,?>> list, Function<Map<String,?>, A> mapper, IntFunction<A[]> generator, A[] def) {
        if (list != null) {
            return list.stream().map(mapper).toArray(generator);
        }
        return def;
    }
    static final double ZERO_THRESHOLD = Double.MIN_VALUE * 2;
    public static boolean isDoubleZero(Double d) {
        return d != null && Math.abs(d) < ZERO_THRESHOLD;
    }
    public static int compareDouble(double d1, double d2) {
        return compareWithRange(d1,d2,ZERO_THRESHOLD);
    }
    public static int compareWithRate(double d1, double d2, double rate) {
        return compareWithRange(d1, d2, d1 * rate);
    }

    public static int compareWithRange(double d1, double d2, double range) {
        if (Math.abs(d1 - d2) < range) return 0;
        else if (d1 < d2) return -1;
        return 1;
    }

    public static boolean inRange(double d1, double d2, double range) {
        return Math.abs(d1 - d2) < range;
    }
}

