package com.binance.connector.quarkus.spot.domain.filters;

import com.binance.connector.quarkus.spot.domain.SymbolFilter;
import com.binance.connector.quarkus.spot.domain.enums.SymbolFilterType;

/**
 * The ICEBERG_PARTS filter defines the maximum parts an iceberg order can have.
 * The number of ICEBERG_PARTS is defined as CEIL(qty / icebergQty).
 */
public class IcebergParts implements SymbolFilter {
    public final SymbolFilterType filterType = SymbolFilterType.ICEBERG_PARTS;
    public final Integer limit;

    public IcebergParts(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "\"IcebergParts\":{" +
                "\"limit\"=" + limit +
                ", \"filterType\"=\"" + filterType + '"' +
                '}';
    }

}
