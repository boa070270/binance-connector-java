package com.binance.connector.quarkus.spot.domain;

public class UsedWeightResponse {
    //TODO the api doesn't allow use correct limits, it takes directly "x-mbx-used-weight-1m" from header
    //TODO I need take from header all possible weight, and then change this class
    public Long sapiUsedIpWeight1m; //"x-sapi-used-ip-weight-1m"
    public Long mbxUsedWeight; //"x-mbx-used-weight", response.header("x-mbx-used-weight"));
    public Long mbxUsedWeight1m; //"x-mbx-used-weight-1m", response.header("x-mbx-used-weight-1m"));

    public UsedWeightResponse() {}
    public UsedWeightResponse(Long sapiUsedIpWeight1m, Long mbxUsedWeight, Long mbxUsedWeight1m) {
        this.sapiUsedIpWeight1m = sapiUsedIpWeight1m;
        this.mbxUsedWeight = mbxUsedWeight;
        this.mbxUsedWeight1m = mbxUsedWeight1m;
    }
}
