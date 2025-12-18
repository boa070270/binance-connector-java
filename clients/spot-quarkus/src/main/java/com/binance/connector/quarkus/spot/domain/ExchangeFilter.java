package com.binance.connector.quarkus.spot.domain;


import com.binance.connector.quarkus.spot.domain.enums.ExchangeFilterType;

import java.util.Map;

public class ExchangeFilter {
    public ExchangeFilterType filterType;
    public double maxPosition;
    public int minTrailingAboveDelta;
    public int maxTrailingAboveDelta;
    public int minTrailingBelowDelta;
    public int maxTrailingBelowDelta;
    public int maxNumOrders;

    public static ExchangeFilter fromMap(Map m) {
        ExchangeFilter f = new ExchangeFilter();
        f.filterType = ExchangeFilterType.valueOf((String) m.get("filterType"));
        f.maxPosition = Double.parseDouble((String) m.get("maxPosition"));
        if (m.get("minTrailingAboveDelta") != null) {
            f.minTrailingAboveDelta = (int) m.get("minTrailingAboveDelta");
        }
        if (m.get("maxTrailingAboveDelta") != null) {
            f.maxTrailingAboveDelta = (int) m.get("maxTrailingAboveDelta");
        }
        if (m.get("minTrailingBelowDelta") != null) {
            f.minTrailingBelowDelta = (int) m.get("minTrailingBelowDelta");
        }
        if (m.get("maxTrailingBelowDelta") != null) {
            f.maxTrailingBelowDelta = (int) m.get("maxTrailingBelowDelta");
        }
        if (m.get("maxNumOrders") != null) {
            f.maxNumOrders = (int) m.get("maxNumOrders");
        }
        return f;
    }

    @Override
    public String toString() {
        return "{\"ExchangeFilter\":{" +
                "\"filterType\":\"" + filterType +
                "\", \"maxPosition\":" + maxPosition +
                ", \"minTrailingAboveDelta\":" + minTrailingAboveDelta +
                ", \"maxTrailingAboveDelta\":" + maxTrailingAboveDelta +
                ", \"minTrailingBelowDelta\":" + minTrailingBelowDelta +
                ", \"maxTrailingBelowDelta\":" + maxTrailingBelowDelta +
                ", \"maxNumOrders\":" + maxNumOrders +
                "}}";
    }
}
