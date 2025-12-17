package com.binance.connector.quarkus.spot.model.events.userdata;

import com.binance.connector.quarkus.spot.model.events.AbstractEvent;
import com.binance.connector.quarkus.spot.model.BinanceUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OutboundAccountPosition extends AbstractEvent {
    /*
      "e": "outboundAccountPosition", //Event type
  "E": 1564034571105,             //Event Time
  "u": 1564034571073,             //Time of last account update
  "B": [                          //Balances Array
    {
      "a": "ETH",                 //Asset
      "f": "10000.000000",        //Free
      "l": "0.000000"             //Locked
    }
  ]
     */
    public Balance[] balances;
    public Long lastUpdateTime;
    public static OutboundAccountPosition fromMap(Map<String,?> m) {
        OutboundAccountPosition o = new OutboundAccountPosition();
        o.eventType = "outboundAccountPosition";
        o.eventTime = BinanceUtils.fromMapLong(m, "E");
        o.lastUpdateTime = BinanceUtils.fromMapLong(m, "u");
        o.balances = BinanceUtils.listToArray((List<Map<String,?>>) m.get("B"), Balance::fromEventMap, Balance[]::new, new Balance[0]);
        return o;
    }

    @Override
    public String toString() {
        return "OutboundAccountPosition{" +
                "balances=" + Arrays.toString(balances) +
                ", eventTime=" + new Date(eventTime) +
                '}';
    }
}
