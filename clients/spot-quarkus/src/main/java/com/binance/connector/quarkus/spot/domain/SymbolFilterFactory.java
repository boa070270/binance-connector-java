package com.binance.connector.quarkus.spot.domain;

import com.binance.connector.quarkus.spot.domain.enums.SymbolFilterType;
import com.binance.connector.quarkus.spot.domain.filters.*;
import com.binance.connector.quarkus.spot.model.BinanceUtils;
import org.jboss.logging.Logger;

import java.util.Map;

public class SymbolFilterFactory {
    private static final Logger Log = Logger.getLogger(SymbolFilterFactory.class);

    static SymbolFilter fromMap(Map<String,?> m) {
        SymbolFilterType filterType = SymbolFilterType.valueOf((String) m.get("filterType"));
        try {
            switch (filterType) {
                case LOT_SIZE:
                    return new LotSize(
                            BinanceUtils.fromMapDouble(m, "minQty"),
                            BinanceUtils.fromMapDouble(m, "maxQty"),
                            BinanceUtils.fromMapDouble(m, "stepSize"));
                case NOTIONAL:
                    return new Notional(
                            BinanceUtils.fromMapDouble(m, "minNotional"),
                            BinanceUtils.fromMapBoolean(m, "applyMinToMarket"),
                            BinanceUtils.fromMapDouble(m, "maxNotional"),
                            BinanceUtils.fromMapBoolean(m, "applyMaxToMarket"),
                            BinanceUtils.fromMapInt(m, "avgPriceMins"));
                case MAX_POSITION:
                    return new MaxPosition(BinanceUtils.fromMapDouble(m, "maxPosition"));
                case ICEBERG_PARTS:
                    return new IcebergParts(BinanceUtils.fromMapInt(m, "limit"));
                case MARKET_LOT_SIZE:
                    return new MarketLotSize(
                            BinanceUtils.fromMapDouble(m, "minQty"),
                            BinanceUtils.fromMapDouble(m, "maxQty"),
                            BinanceUtils.fromMapDouble(m, "stepSize"));
                case MAX_NUM_ALGO_ORDERS:
                    return new MaxNumAlgoOrders(BinanceUtils.fromMapInt(m, "maxNumAlgoOrders"));
                case PRICE_FILTER:
                    return new PriceFilter(
                            BinanceUtils.fromMapDouble(m, "minPrice"),
                            BinanceUtils.fromMapDouble(m, "maxPrice"),
                            BinanceUtils.fromMapDouble(m, "tickSize"));
                case MAX_NUM_ICEBERG_ORDERS:
                    return new MaxNumIcebergOrders(BinanceUtils.fromMapInt(m, "maxNumIcebergOrders"));
                case MIN_NOTIONAL:
                    return new MinNotional(BinanceUtils.fromMapDouble(m, "minNotional"),
                            BinanceUtils.fromMapBoolean(m, "applyToMarket"),
                            BinanceUtils.fromMapInt(m, "avgPriceMins"));
                case MAX_NUM_ORDERS:
                    return new MaxNumOrders(BinanceUtils.fromMapInt(m, "maxNumOrders"));
                case PERCENT_PRICE:
                    return new PercentPrice(BinanceUtils.fromMapDouble(m, "multiplierUp"),
                            BinanceUtils.fromMapDouble(m, "multiplierDown"),
                            BinanceUtils.fromMapInt(m, "avgPriceMins"));
                case TRAILING_DELTA:
                    return new TrailingDelta(BinanceUtils.fromMapLong(m, "minTrailingAboveDelta"),
                            BinanceUtils.fromMapLong(m, "maxTrailingAboveDelta"),
                            BinanceUtils.fromMapLong(m, "minTrailingBelowDelta"),
                            BinanceUtils.fromMapLong(m, "maxTrailingBelowDelta"));
                case PERCENT_PRICE_BY_SIDE:
                    return new PercentPriceBySide(BinanceUtils.fromMapDouble(m, "bidMultiplierUp"),
                            BinanceUtils.fromMapDouble(m, "bidMultiplierDown"),
                            BinanceUtils.fromMapDouble(m, "askMultiplierUp"),
                            BinanceUtils.fromMapDouble(m, "askMultiplierDown"),
                            BinanceUtils.fromMapInt(m, "avgPriceMins"));
            }
        } catch (RuntimeException r) {
            Log.errorf(r, "Creating SymbolFilterType: %s", m);
        }
        throw new IllegalArgumentException("Unknown SymbolFilter");
    }
}
