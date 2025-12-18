package com.binance.connector.quarkus.spot.domain;

import com.binance.connector.quarkus.spot.domain.filters.AbstractRateLimit;
import com.binance.connector.quarkus.spot.model.BaseDTO;
import com.binance.connector.quarkus.spot.model.BinanceUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ExchangeInfo extends BaseDTO {
    public String timezone;
    public Long serverTime;
    public AbstractRateLimit[] rateLimits;
    public ExchangeFilter[] exchangeFilters;
    public ExchangeSymbol[] symbols;

    public static ExchangeInfo fromMap(Map<String,?> map) {
        ExchangeInfo info = new ExchangeInfo();
        info.timezone = (String) map.get("timezone");
        info.serverTime = BinanceUtils.fromMapLong(map,"serverTime");
        info.rateLimits = BinanceUtils.listToArray((List<Map<String,?>>) map.get("rateLimits"), AbstractRateLimit::fromMap, AbstractRateLimit[]::new, new AbstractRateLimit[0]);
        info.exchangeFilters = BinanceUtils.listToArray((List<Map<String,?>>)map.get("exchangeFilters"), ExchangeFilter::fromMap, ExchangeFilter[]::new, new ExchangeFilter[0]);
        info.symbols = BinanceUtils.listToArray((List<Map<String,?>>)map.get("symbols"), ExchangeSymbol::fromMap, ExchangeSymbol[]::new, new ExchangeSymbol[0]);
        return info;
    }
    @Override
    public String toString() {
        return "\"ExchangeInfo\":{" +
                "\"timezone\":\"" + timezone +
                "\", \"serverTime\":\"" + serverTime +
                "\", \"rateLimits\":" + Arrays.toString(rateLimits) +
                "\", \"exchangeFilters\":" + Arrays.toString(exchangeFilters) +
                "\", \"symbols\":" + Arrays.toString(symbols) +
                "}}";
    }
}
