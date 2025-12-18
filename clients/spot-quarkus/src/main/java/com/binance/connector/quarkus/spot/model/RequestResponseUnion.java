package com.binance.connector.quarkus.spot.model;

import io.vertx.core.json.Json;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

public class RequestResponseUnion {
    public static final Map<String, DecodeJson<?>> RequestResponseAssociation = Map.ofEntries(
            Map.entry(AccountCommissionRequest.class.getSimpleName(), new DecodeJson<>(AccountCommissionResponse.class)),
            Map.entry(AccountRateLimitsOrdersRequest.class.getSimpleName(), new DecodeJson<>(AccountRateLimitsOrdersResponse.class)),
            Map.entry("account.status", new DecodeJson<>(AccountStatusResponse.class)),
            Map.entry(AggTradeRequest.class.getSimpleName(), new DecodeJson<>(AggTradeResponse.class)),
//            Map.entry(AllMarketRollingWindowTickerRequest.class.getSimpleName(), new DecodeJson<>(AllMarketRollingWindowTickerResponse.class))
            Map.entry(AllOrderListsRequest.class.getSimpleName(), new DecodeJson<>(AllOrderListsResponse.class)),
            Map.entry(AllOrdersRequest.class.getSimpleName(), new DecodeJson<>(AllOrdersResponse.class)),
            Map.entry(AvgPriceRequest.class.getSimpleName(), new DecodeJson<>(AvgPriceResponse.class)),
//            Map.entry(BaseRequestDTO.class.getSimpleName(), new DecodeJson<>(BaseResponseDTO.class)),
            Map.entry(BookTickerRequest.class.getSimpleName(), new DecodeJson<>(BookTickerResponse.class)),
            Map.entry(DepthRequest.class.getSimpleName(), new DecodeJson<>(DepthResponse.class)),
            Map.entry(DiffBookDepthRequest.class.getSimpleName(), new DecodeJson<>(DiffBookDepthResponse.class)),
            Map.entry("exchangeInfo", new DecodeJson<>(ExchangeInfoResponse::fromMap)),
            Map.entry(KlineOffsetRequest.class.getSimpleName(), new DecodeJson<>(KlineOffsetResponse.class)),
            Map.entry(KlineRequest.class.getSimpleName(), new DecodeJson<>(KlineResponse.class)),
            Map.entry(KlinesRequest.class.getSimpleName(), new DecodeJson<>(KlinesResponse.class)),
            Map.entry(MiniTickerRequest.class.getSimpleName(), new DecodeJson<>(MiniTickerResponse.class)),
            Map.entry(MyAllocationsRequest.class.getSimpleName(), new DecodeJson<>(MyAllocationsResponse.class)),
            Map.entry(MyFiltersRequest.class.getSimpleName(), new DecodeJson<>(MyFiltersResponse.class)),
            Map.entry(MyPreventedMatchesRequest.class.getSimpleName(), new DecodeJson<>(MyPreventedMatchesResponse.class)),
            Map.entry(MyTradesRequest.class.getSimpleName(), new DecodeJson<>(MyTradesResponse.class)),
            Map.entry(OpenOrderListsStatusRequest.class.getSimpleName(), new DecodeJson<>(OpenOrderListsStatusResponse.class)),
            Map.entry("openOrders.cancelAll", new DecodeJson<>(OpenOrdersCancelAllResponse.class)),
            Map.entry(OpenOrdersStatusRequest.class.getSimpleName(), new DecodeJson<>(OpenOrdersStatusResponse.class)),
            Map.entry("order.amend.keepPriority", new DecodeJson<>(OrderAmendKeepPriorityResponse.class)),
            Map.entry(OrderAmendmentsRequest.class.getSimpleName(), new DecodeJson<>(OrderAmendmentsResponse.class)),
            Map.entry("order.cancelReplace", new DecodeJson<>(OrderCancelReplaceResponse.class)),
            Map.entry("order.cancel", new DecodeJson<>(OrderCancelResponse.class)),
            Map.entry("orderList.cancel", new DecodeJson<>(OrderListCancelResponse.class)),
            Map.entry("orderList.place.oco", new DecodeJson<>(OrderListPlaceOcoResponse.class)),
            Map.entry("orderList.place.otoco", new DecodeJson<>(OrderListPlaceOtocoResponse.class)),
            Map.entry("orderList.place.oto", new DecodeJson<>(OrderListPlaceOtoResponse.class)),
            Map.entry("orderList.place", new DecodeJson<>(OrderListPlaceResponse.class)),
            Map.entry(OrderListStatusRequest.class.getSimpleName(), new DecodeJson<>(OrderListStatusResponse.class)),
            Map.entry("order.place", new DecodeJson<>(OrderPlaceResponse.class)),
            Map.entry(OrderStatusRequest.class.getSimpleName(), new DecodeJson<>(OrderStatusResponse.class)),
            Map.entry("order.test", new DecodeJson<>(OrderTestResponse.class)),
            Map.entry(PartialBookDepthRequest.class.getSimpleName(), new DecodeJson<>(PartialBookDepthResponse.class)),
            Map.entry(RollingWindowTickerRequest.class.getSimpleName(), new DecodeJson<>(RollingWindowTickerResponse.class)),
            Map.entry("session.logon", new DecodeJson<>(SessionLogonResponse.class)),
            Map.entry("session.status", new DecodeJson<>(SessionStatusResponse.class)),
            Map.entry("session.logout", new DecodeJson<>(SessionLogoutResponse.class)),
            Map.entry("sor.order.place", new DecodeJson<>(SorOrderPlaceResponse.class)),
            Map.entry("sor.order.test", new DecodeJson<>(SorOrderTestResponse.class)),
            Map.entry("userDataStream.subscribe", new DecodeJson<>(UserDataStreamSubscribeResponse.class)),
//            Map.entry(Ticker24hrRequest.class.getSimpleName(), new DecodeJson<>(Ticker24hrResponse.class)),
//            Map.entry(TickerBookRequest.class.getSimpleName(), new DecodeJson<>(TickerBookResponse.class)),
            Map.entry("ticker.price", new DecodeJson<>(TickerPriceResponse2.class)),
//            Map.entry(TickerRequest.class.getSimpleName(), new DecodeJson<>(TickerResponse.class)),
            Map.entry(TickerTradingDayRequest.class.getSimpleName(), new DecodeJson<>(TickerTradingDayResponse.class)),
            Map.entry(TradeRequest.class.getSimpleName(), new DecodeJson<>(TradeResponse.class)),
            Map.entry(TradesAggregateRequest.class.getSimpleName(), new DecodeJson<>(TradesAggregateResponse.class)),
            Map.entry(TradesHistoricalRequest.class.getSimpleName(), new DecodeJson<>(TradesHistoricalResponse.class)),
            Map.entry(TradesRecentRequest.class.getSimpleName(), new DecodeJson<>(TradesRecentResponse.class)),
            Map.entry(UiKlinesRequest.class.getSimpleName(), new DecodeJson<>(UiKlinesResponse.class)),
            Map.entry("time", new DecodeJson<>(TimeResponse.class)),
            Map.entry("ping", new DecodeJson<>(PingResponse.class)),
            Map.entry(UserDataStreamPingRequest.class.getSimpleName(), new DecodeJson<>(UserDataStreamPingResponse.class)),
            Map.entry(UserDataStreamStopRequest.class.getSimpleName(), new DecodeJson<>(UserDataStreamStopResponse.class)),
            Map.entry("userDataStream.unsubscribe", new DecodeJson<>(UserDataStreamUnsubscribeResponse.class))
    );
    public static class DecodeJson<T extends BaseDTO> {
        Class<T> result;
        Function<Map, T> map;
        DecodeJson(Class<T> clazz) {
            result = clazz;
        }
        DecodeJson(Function<Map, T> fMap) {
            map = fMap;
        }
        public T decode(String s) {
            if (map != null) {
                Map m = Json.decodeValue(s, Map.class);
                return map.apply(m);
            }
            return Json.decodeValue(s, result);
        }
    }
}
