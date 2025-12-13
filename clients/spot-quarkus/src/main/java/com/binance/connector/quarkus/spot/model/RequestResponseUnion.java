package com.binance.connector.quarkus.spot.model;

import io.vertx.core.json.Json;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

public class RequestResponseUnion {
    static final Map<String, DecodeJson<?>> TYPES = Map.ofEntries(
            Map.entry(AccountCommissionRequest.class.getSimpleName(), new DecodeJson<>(AccountCommissionResponse.class)),
            Map.entry(AccountRateLimitsOrdersRequest.class.getSimpleName(), new DecodeJson<>(AccountRateLimitsOrdersResponse.class)),
            Map.entry(AccountStatusRequest.class.getSimpleName(), new DecodeJson<>(AccountStatusResponse.class)),
            Map.entry(AggTradeRequest.class.getSimpleName(), new DecodeJson<>(AggTradeResponse.class)),
//            Map.entry(AllMarketRollingWindowTickerRequest.class.getSimpleName(), new DecodeJson<>(AllMarketRollingWindowTickerResponse.class))
            Map.entry(AllOrderListsRequest.class.getSimpleName(), new DecodeJson<>(AllOrderListsResponse.class)),
            Map.entry(AllOrdersRequest.class.getSimpleName(), new DecodeJson<>(AllOrdersResponse.class)),
            Map.entry(AvgPriceRequest.class.getSimpleName(), new DecodeJson<>(AvgPriceResponse.class)),
//            Map.entry(BaseRequestDTO.class.getSimpleName(), new DecodeJson<>(BaseResponseDTO.class)),
            Map.entry(BookTickerRequest.class.getSimpleName(), new DecodeJson<>(BookTickerResponse.class)),
            Map.entry(DepthRequest.class.getSimpleName(), new DecodeJson<>(DepthResponse.class)),
            Map.entry(DiffBookDepthRequest.class.getSimpleName(), new DecodeJson<>(DiffBookDepthResponse.class)),
            Map.entry(ExchangeInfoRequest.class.getSimpleName(), new DecodeJson<>(ExchangeInfoResponse.class)),
            Map.entry(KlineOffsetRequest.class.getSimpleName(), new DecodeJson<>(KlineOffsetResponse.class)),
            Map.entry(KlineRequest.class.getSimpleName(), new DecodeJson<>(KlineResponse.class)),
            Map.entry(KlinesRequest.class.getSimpleName(), new DecodeJson<>(KlinesResponse.class)),
            Map.entry(MiniTickerRequest.class.getSimpleName(), new DecodeJson<>(MiniTickerResponse.class)),
            Map.entry(MyAllocationsRequest.class.getSimpleName(), new DecodeJson<>(MyAllocationsResponse.class)),
            Map.entry(MyFiltersRequest.class.getSimpleName(), new DecodeJson<>(MyFiltersResponse.class)),
            Map.entry(MyPreventedMatchesRequest.class.getSimpleName(), new DecodeJson<>(MyPreventedMatchesResponse.class)),
            Map.entry(MyTradesRequest.class.getSimpleName(), new DecodeJson<>(MyTradesResponse.class)),
            Map.entry(OpenOrderListsStatusRequest.class.getSimpleName(), new DecodeJson<>(OpenOrderListsStatusResponse.class)),
            Map.entry(OpenOrdersCancelAllRequest.class.getSimpleName(), new DecodeJson<>(OpenOrdersCancelAllResponse.class)),
            Map.entry(OpenOrdersStatusRequest.class.getSimpleName(), new DecodeJson<>(OpenOrdersStatusResponse.class)),
            Map.entry(OrderAmendKeepPriorityRequest.class.getSimpleName(), new DecodeJson<>(OrderAmendKeepPriorityResponse.class)),
            Map.entry(OrderAmendmentsRequest.class.getSimpleName(), new DecodeJson<>(OrderAmendmentsResponse.class)),
            Map.entry(OrderCancelReplaceRequest.class.getSimpleName(), new DecodeJson<>(OrderCancelReplaceResponse.class)),
            Map.entry(OrderCancelRequest.class.getSimpleName(), new DecodeJson<>(OrderCancelResponse.class)),
            Map.entry(OrderListCancelRequest.class.getSimpleName(), new DecodeJson<>(OrderListCancelResponse.class)),
            Map.entry(OrderListPlaceOcoRequest.class.getSimpleName(), new DecodeJson<>(OrderListPlaceOcoResponse.class)),
            Map.entry(OrderListPlaceOtocoRequest.class.getSimpleName(), new DecodeJson<>(OrderListPlaceOtocoResponse.class)),
            Map.entry(OrderListPlaceOtoRequest.class.getSimpleName(), new DecodeJson<>(OrderListPlaceOtoResponse.class)),
            Map.entry(OrderListPlaceRequest.class.getSimpleName(), new DecodeJson<>(OrderListPlaceResponse.class)),
            Map.entry(OrderListStatusRequest.class.getSimpleName(), new DecodeJson<>(OrderListStatusResponse.class)),
            Map.entry(OrderPlaceRequest.class.getSimpleName(), new DecodeJson<>(OrderPlaceResponse.class)),
            Map.entry(OrderStatusRequest.class.getSimpleName(), new DecodeJson<>(OrderStatusResponse.class)),
            Map.entry(OrderTestRequest.class.getSimpleName(), new DecodeJson<>(OrderTestResponse.class)),
            Map.entry(PartialBookDepthRequest.class.getSimpleName(), new DecodeJson<>(PartialBookDepthResponse.class)),
            Map.entry(RollingWindowTickerRequest.class.getSimpleName(), new DecodeJson<>(RollingWindowTickerResponse.class)),
            Map.entry(SessionLogonRequest.class.getSimpleName(), new DecodeJson<>(SessionLogonResponse.class)),
            Map.entry(SorOrderPlaceRequest.class.getSimpleName(), new DecodeJson<>(SorOrderPlaceResponse.class)),
            Map.entry(SorOrderTestRequest.class.getSimpleName(), new DecodeJson<>(SorOrderTestResponse.class)),
//            Map.entry(Ticker24hrRequest.class.getSimpleName(), new DecodeJson<>(Ticker24hrResponse.class)),
//            Map.entry(TickerBookRequest.class.getSimpleName(), new DecodeJson<>(TickerBookResponse.class)),
//            Map.entry(TickerPriceRequest.class.getSimpleName(), new DecodeJson<>(TickerPriceResponse.class)),
//            Map.entry(TickerRequest.class.getSimpleName(), new DecodeJson<>(TickerResponse.class)),
            Map.entry(TickerTradingDayRequest.class.getSimpleName(), new DecodeJson<>(TickerTradingDayResponse.class)),
            Map.entry(TradeRequest.class.getSimpleName(), new DecodeJson<>(TradeResponse.class)),
            Map.entry(TradesAggregateRequest.class.getSimpleName(), new DecodeJson<>(TradesAggregateResponse.class)),
            Map.entry(TradesHistoricalRequest.class.getSimpleName(), new DecodeJson<>(TradesHistoricalResponse.class)),
            Map.entry(TradesRecentRequest.class.getSimpleName(), new DecodeJson<>(TradesRecentResponse.class)),
            Map.entry(UiKlinesRequest.class.getSimpleName(), new DecodeJson<>(UiKlinesResponse.class)),
            Map.entry(UserDataStreamPingRequest.class.getSimpleName(), new DecodeJson<>(UserDataStreamPingResponse.class)),
            Map.entry(UserDataStreamStopRequest.class.getSimpleName(), new DecodeJson<>(UserDataStreamStopResponse.class)),
            Map.entry(UserDataStreamUnsubscribeRequest.class.getSimpleName(), new DecodeJson<>(UserDataStreamUnsubscribeResponse.class))
    );
    static class DecodeJson<T extends BaseDTO> {
        Class<T> result;
        DecodeJson(Class<T> clazz) {
            result = clazz;
        }
        T decode(String s) {
            return Json.decodeValue(s, result);
        }
    }
}
