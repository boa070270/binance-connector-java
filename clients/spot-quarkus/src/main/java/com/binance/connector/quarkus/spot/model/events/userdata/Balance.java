package com.binance.connector.quarkus.spot.model.events.userdata;

import com.binance.connector.quarkus.spot.model.BinanceUtils;

import java.util.Map;

public class Balance {
    public String asset;
    public Double free = 0d;
    public Double locked = 0d;
    public Balance(){}
    public Balance(String asset, Double free, Double locked){
        this.asset = asset;
        this.free = free;
        this.locked = locked;
    }
    public static Balance fromMap(Map m) {
        Balance b = new Balance();
        b.asset = (String) m.get("asset");
        b.free = BinanceUtils.fromMapDouble(m, "free");
        b.locked = BinanceUtils.fromMapDouble(m, "locked");
        return b;
    }
    public static Balance fromEventMap(Map m) {
        Balance b = new Balance();
        b.asset = (String) m.get("a");
        b.free = BinanceUtils.fromMapDouble(m, "f");
        b.locked = BinanceUtils.fromMapDouble(m, "l");
        return b;
    }

    @Override
    public String toString() {
        return "{\"Balance\":{" +
                "\"asset\":\"" + asset +
                "\", \"free\":\"" + free +
                "\", \"locked\":\"" + locked +
                "\"}}";
    }
}

