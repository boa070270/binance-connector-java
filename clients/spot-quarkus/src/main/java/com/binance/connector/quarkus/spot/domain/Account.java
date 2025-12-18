package com.binance.connector.quarkus.spot.domain;

import com.binance.connector.quarkus.spot.model.BinanceUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

public class Account {
    public Long makerCommission;
    public Long takerCommission;
    public Long buyerCommission;
    public Long sellerCommission;
    public Boolean canTrade;
    public Boolean canWithdraw;
    public Boolean canDeposit;
    public Long updateTime;
    public String accountType;
    public Balance[] balances;
    public static Account fromMap(Map m) {
        Account a = new Account();
        a.makerCommission = BinanceUtils.fromMapLong(m, "makerCommission");
        a.takerCommission = BinanceUtils.fromMapLong(m, "takerCommission");
        a.buyerCommission = BinanceUtils.fromMapLong(m, "buyerCommission");
        a.sellerCommission = BinanceUtils.fromMapLong(m, "sellerCommission");
        a.canTrade = BinanceUtils.fromMapBoolean(m, "canTrade");
        a.canWithdraw = BinanceUtils.fromMapBoolean(m, "canWithdraw");
        a.canDeposit = BinanceUtils.fromMapBoolean(m, "canDeposit");
        a.updateTime = BinanceUtils.fromMapLong(m, "updateTime");
        a.accountType = (String) m.get("accountType");
        a.balances = BinanceUtils.listToArray((List<Map<String,?>>) m.get("balances"), Balance::fromMap, Balance[]::new, new Balance[0]);
        return a;
    }

    @Override
    public String toString() {
        return "{Account{" +
                "\"makerCommission\":" + makerCommission +
                ", \"takerCommission\":" + takerCommission +
                ", \"buyerCommission\":" + buyerCommission +
                ", \"sellerCommission\":" + sellerCommission +
                ", \"canTrade\":" + canTrade +
                ", \"canWithdraw\":" + canWithdraw +
                ", \"canDeposit\":" + canDeposit +
                ", \"updateTime\":" + updateTime +
                ", \"accountType\":\"" + accountType +
                "\", \"balances\":" + Arrays.toString(balances) +
                "}}";
    }
}
