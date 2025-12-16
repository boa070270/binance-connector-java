package com.binance.connector.quarkus.spot.model.events;

import com.binance.connector.quarkus.spot.model.events.userdata.BalanceUpdate;
import com.binance.connector.quarkus.spot.model.events.userdata.ExecutionReport;
import com.binance.connector.quarkus.spot.model.events.userdata.ListStatusReport;
import com.binance.connector.quarkus.spot.model.events.userdata.OutboundAccountPosition;
import io.vertx.core.json.Json;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbstractEvent {
    public String eventType;
    public Long eventTime;

    public static AbstractEvent fromString(String s) {
        Pattern p = Pattern.compile("^\\{\"e\":\"(\\w*)\",.*");
        Matcher matcher = p.matcher(s);
        if (matcher.matches()) {
            String event = matcher.group(1);
            switch (event) {
                case "trade":
                    return TradeEvent.fromMap(Json.decodeValue(s, Map.class));
                case "executionReport":
                    return ExecutionReport.fromMap(Json.decodeValue(s, Map.class));
                case "listStatus":
                    return ListStatusReport.fromMap(Json.decodeValue(s, Map.class));
                case "outboundAccountPosition":
                    return OutboundAccountPosition.fromMap(Json.decodeValue(s, Map.class));
                case "balanceUpdate":
                    return BalanceUpdate.fromMap(Json.decodeValue(s, Map.class));
            }
        }
        return null;
    }
}
