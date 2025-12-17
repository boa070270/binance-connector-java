package com.binance.connector.quarkus.spot.model.events;

import com.binance.connector.quarkus.spot.model.BinanceUtils;
import io.vertx.core.json.Json;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class DiffDepth extends AbstractEvent {
    private static final Logger Log = Logger.getLogger(DiffDepth.class);
    public String symbol;
    public long firstUpdateId;
    public long finalUpdateId;
    public ConcurrentSkipListMap<Double, Double> bids = new ConcurrentSkipListMap<>(); //key - price, value - quantity
    public ConcurrentSkipListMap<Double, Double> asks = new ConcurrentSkipListMap<>(); //key - price, value - quantity

    @Override
    public String toString() {
        return "DiffDepth{" +
                "symbol='" + symbol + '\'' +
                ", firstUpdateId=" + firstUpdateId +
                ", finalUpdateId=" + finalUpdateId +
                ", bids=" + bids +
                ", asks=" + asks +
                ", eventType='" + eventType + '\'' +
                ", eventTime=" + eventTime +
                '}';
    }

    public static DiffDepth fromString(String event) {
        if (event != null) {
            try {
                Map map = Json.decodeValue(event, Map.class);
                if ("depthUpdate".equals(map.get("e"))) {
                    DiffDepth depth = new DiffDepth();
                    depth.eventType = "depthUpdate";
                    depth.eventTime = BinanceUtils.fromMapLong(map, "E");
                    depth.symbol = (String) map.get("s");
                    depth.firstUpdateId = BinanceUtils.fromMapLong(map, "U"); //TODO is the document lies
                    depth.finalUpdateId = BinanceUtils.fromMapLong(map, "u");
                    Object o = map.get("b");
                    if (o instanceof List && ((List<?>) o).size() > 0) {
                        for (Object a : (List<?>) o) {
                            if (a instanceof List) {
                                depth.bids.put(Double.parseDouble((String) ((List<?>) a).get(0)), Double.parseDouble((String) ((List<?>) a).get(1)));
                            }
                        }
                    }
                    o = map.get("a");
                    if (o instanceof List && ((List<?>) o).size() > 0) {
                        for (Object a : (List<?>) o) {
                            if (a instanceof List) {
                                depth.asks.put(Double.parseDouble((String) ((List<?>) a).get(0)), Double.parseDouble((String) ((List<?>) a).get(1)));
                            }
                        }
                    }
                    return depth;
                }
            } catch (Throwable t) {
                Log.error("Cannot parsing depthUpdate", t);
            }
        }
        return null;
    }
}
