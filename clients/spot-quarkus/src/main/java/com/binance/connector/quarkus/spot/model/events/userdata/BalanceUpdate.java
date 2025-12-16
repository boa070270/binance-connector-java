package com.binance.connector.quarkus.spot.model.events.userdata;


import com.binance.connector.quarkus.spot.model.events.AbstractEvent;
import com.binance.connector.quarkus.spot.model.events.BinanceUtils;

import java.util.Map;

public class BalanceUpdate extends AbstractEvent {
    /*
  "e": "balanceUpdate",         //Event Type
  "E": 1573200697110,           //Event Time
  "a": "BTC",                   //Asset
  "d": "100.00000000",          //Balance Delta
  "T": 1573200697068            //Clear Time
     */
    public String asset;
    public Double balanceDelta;
    public Long clearTime;
    public static BalanceUpdate fromMap(Map m) {
        BalanceUpdate b = new BalanceUpdate();
        b.eventType = "balanceUpdate";
        b.eventTime = BinanceUtils.fromMapLong(m, "E");
        b.asset = (String) m.get("a");
        b.balanceDelta = BinanceUtils.fromMapDouble(m, "d");
        b.clearTime = BinanceUtils.fromMapLong(m, "T");
        return b;
    }

    @Override
    public String toString() {
        return "BalanceUpdate{" +
                "asset='" + asset + '\'' +
                ", balanceDelta=" + balanceDelta +
                ", clearTime=" + clearTime +
                ", eventTime=" + eventTime +
                '}';
    }
}
