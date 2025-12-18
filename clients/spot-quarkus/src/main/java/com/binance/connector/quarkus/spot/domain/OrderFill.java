package com.binance.connector.quarkus.spot.domain;


import com.binance.connector.quarkus.spot.model.BinanceUtils;

import java.util.Map;

public class OrderFill {
    public Double price;
    public Double qty;
    public Double commission;
    public String commissionAsset;
    public static OrderFill fromMap(Map map) {
        OrderFill o = new OrderFill();
        o.price = BinanceUtils.fromMapDouble(map, "price");
        o.qty = BinanceUtils.fromMapDouble(map, "qty");
        o.commission = BinanceUtils.fromMapDouble(map, "commission");
        o.commissionAsset = (String) map.get("commissionAsset");
        return o;
    }

    @Override
    public String toString() {
        return "OrderFill{" +
                "price=" + price +
                ", qty=" + qty +
                ", commission=" + commission +
                ", commissionAsset='" + commissionAsset + '\'' +
                '}';
    }
}
