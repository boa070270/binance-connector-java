package com.binance.connector.quarkus.spot.domain;


import com.binance.connector.quarkus.spot.domain.enums.OrderType;
import com.binance.connector.quarkus.spot.model.BinanceUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ExchangeSymbol {
    public String symbol; //"ETHBTC"
    public String status; //"TRADING"
    public String baseAsset; //"ETH"
    public int baseAssetPrecision; //8
    public String quoteAsset; //"BTC"
    public int quotePrecision;
    public int quoteAssetPrecision; //8
    public int baseCommissionPrecision;//8
    public int quoteCommissionPrecision; //8
    public OrderType[] orderTypes;//["LIMIT"]
    public boolean icebergAllowed;
    public boolean ocoAllowed;
    public boolean otoAllowed;
    public boolean opoAllowed;
    public boolean quoteOrderQtyMarketAllowed;
    public boolean allowTrailingStop;
    public boolean cancelReplaceAllowed;
    public boolean amendAllowed;
    public boolean pegInstructionsAllowed;
    public boolean isSpotTradingAllowed;
    public boolean isMarginTradingAllowed;
    public SymbolFilter[] filters;
    public String[] permissions;
    public String defaultSelfTradePreventionMode;
    public String[] allowedSelfTradePreventionModes;
    public static ExchangeSymbol fromMap(Map m) {
        ExchangeSymbol s = new ExchangeSymbol();
        s.symbol = m.get("symbol").toString().intern();
        s.status = (String) m.get("status");
        s.baseAsset = (String) m.get("baseAsset");
        s.baseAssetPrecision = BinanceUtils.fromMapInt(m,"baseAssetPrecision");
        s.quoteAsset = (String) m.get("quoteAsset");
        s.quotePrecision = BinanceUtils.fromMapInt(m,"quotePrecision");
        s.quoteAssetPrecision = BinanceUtils.fromMapInt(m,"quoteAssetPrecision");
        s.baseCommissionPrecision = BinanceUtils.fromMapInt(m,"baseCommissionPrecision");
        s.quoteCommissionPrecision = BinanceUtils.fromMapInt(m,"quoteCommissionPrecision");
        if (m.get("orderTypes") != null) s.orderTypes = (OrderType[]) ((List<String>) m.get("orderTypes")).stream().map(o -> OrderType.valueOf((String) o)).toArray(OrderType[]::new);
        s.icebergAllowed = BinanceUtils.fromMapBoolean(m,"icebergAllowed");
        s.ocoAllowed = BinanceUtils.fromMapBoolean(m,"ocoAllowed");
        s.quoteOrderQtyMarketAllowed = BinanceUtils.fromMapBoolean(m,"quoteOrderQtyMarketAllowed");
        s.allowTrailingStop = BinanceUtils.fromMapBoolean(m,"allowTrailingStop");
        s.isSpotTradingAllowed = BinanceUtils.fromMapBoolean(m,"isSpotTradingAllowed");
        s.isMarginTradingAllowed = BinanceUtils.fromMapBoolean(m,"isMarginTradingAllowed");
        if (m.get("filters") != null) s.filters = (SymbolFilter[]) ((List<Map>) m.get("filters")).stream().map(o -> SymbolFilterFactory.fromMap((Map) o)).toArray(SymbolFilter[]::new);
        if (m.get("permissions") != null) s.permissions = (String[]) ((List<String>) m.get("permissions")).stream().toArray(String[]::new);
        s.cancelReplaceAllowed = BinanceUtils.fromMapBoolean(m, "cancelReplaceAllowed");
        return s;
    }

    @Override
    public String toString() {
        return "\"{ExchangeSymbol\":{" +
                "\"symbol\":\"" + symbol +
                "\", \"status\":\"" + status +
                "\", \"baseAsset\":\"" + baseAsset +
                "\", \"baseAssetPrecision\":" + baseAssetPrecision +
                ", \"quoteAsset\":\"" + quoteAsset +
                "\", \"quoteAssetPrecision\":" + quoteAssetPrecision +
                ", \"baseCommissionPrecision\":" + baseCommissionPrecision +
                ", \"quoteCommissionPrecision\":" + quoteCommissionPrecision +
                ", \"orderTypes\":" + Arrays.toString(orderTypes) +
                ", \"icebergAllowed\":" + icebergAllowed +
                ", \"ocoAllowed\":" + ocoAllowed +
                ", \"quoteOrderQtyMarketAllowed\":" + quoteOrderQtyMarketAllowed +
                ", \"allowTrailingStop\":" + allowTrailingStop +
                ", \"isSpotTradingAllowed\":" + isSpotTradingAllowed +
                ", \"isMarginTradingAllowed\":" + isMarginTradingAllowed +
                ", \"filters\":" + Arrays.toString(filters) +
                ", \"permissions\":" + Arrays.toString(permissions) +
                "}}";
    }
}
