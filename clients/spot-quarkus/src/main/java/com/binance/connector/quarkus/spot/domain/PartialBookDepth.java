package com.binance.connector.quarkus.spot.domain;


import com.binance.connector.quarkus.spot.model.BinanceUtils;
import com.binance.connector.quarkus.spot.model.events.DiffDepth;
import io.vertx.core.json.Json;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class PartialBookDepth {
    private static final Logger Log = Logger.getLogger(DiffDepth.class);
    public long lastUpdateId;
    public final String symbol;
    public ConcurrentSkipListMap<Double, Double> bids = new ConcurrentSkipListMap<>();; //key - price, value - quantity
    public ConcurrentSkipListMap<Double, Double> asks = new ConcurrentSkipListMap<>();;

    public PartialBookDepth(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "PartialBookDepth{symbol=" + symbol +
                ", lastUpdateId=" + lastUpdateId +
                ", bids=" + bids +
                ", asks=" + asks +
                '}';
    }

    public static PartialBookDepth fromString(String symbol, String event) {
        if (event != null) {
            try {
                Map map = Json.decodeValue(event, Map.class);
                return fromMap(symbol, map);
            } catch (Throwable t) {
                Log.error("Cannot parsing PartialBookDepth", t);
            }
        }
        return null;
    }
    public static PartialBookDepth fromMap(String symbol, Map<String,?> map) {
        PartialBookDepth depth = new PartialBookDepth(symbol);
        depth.lastUpdateId = BinanceUtils.fromMapLong(map, "lastUpdateId");
        Object o = map.get("bids");
        if (o instanceof List && ((List<?>) o).size() > 0) {
            for (Object a : (List<?>) o) {
                if (a instanceof List) {
                    depth.bids.put(Double.parseDouble((String) ((List<?>) a).get(0)), Double.parseDouble((String) ((List<?>) a).get(1)));
                }
            }
        }
        o = map.get("asks");
        if (o instanceof List && ((List<?>) o).size() > 0) {
            for (Object a : (List<?>) o) {
                if (a instanceof List) {
                    depth.asks.put(Double.parseDouble((String) ((List<?>) a).get(0)), Double.parseDouble((String) ((List<?>) a).get(1)));
                }
            }
        }
        return depth;
    }
}
