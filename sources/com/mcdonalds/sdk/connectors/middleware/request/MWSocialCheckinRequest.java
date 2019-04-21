package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.deserializer.MWPointOfDistributionTypeAdapter;
import com.mcdonalds.sdk.connectors.middleware.deserializer.MWPriceTypeAdapter;
import com.mcdonalds.sdk.connectors.middleware.model.MWOrderView;
import com.mcdonalds.sdk.connectors.middleware.model.MWSocialLoginInfo;
import com.mcdonalds.sdk.connectors.middleware.response.MWCheckinResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MWSocialCheckinRequest extends MWRequest<MWCheckinResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/order/finalization/pickup/socialLogin";
    private final MWRequestHeaders mHeaderMap;
    private final MWJSONRequestBody mPostBody;

    @Deprecated
    public MWSocialCheckinRequest(MiddlewareConnector ignored, String ecpToken, MWSocialLoginInfo loginInfo, String storeId, MWOrderView orderView, String checkInData) {
        this(ecpToken, loginInfo, storeId, orderView, checkInData);
    }

    public MWSocialCheckinRequest(String ecpToken, MWSocialLoginInfo loginInfo, String storeId, MWOrderView orderView, String checkInData) {
        this(ecpToken, loginInfo, storeId, orderView, checkInData, "");
    }

    public MWSocialCheckinRequest(String ecpToken, MWSocialLoginInfo loginInfo, String storeId, MWOrderView orderView, String checkInData, String paymentResponse) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mPostBody = new MWJSONRequestBody(false);
        this.mPostBody.put("loginInfo", loginInfo);
        this.mPostBody.put("storeId", storeId);
        this.mPostBody.put("orderView", orderView);
        this.mPostBody.put("checkInData", checkInData);
        this.mPostBody.put("paymentResponse", paymentResponse);
    }

    public MethodType getMethodType() {
        return MethodType.POST;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    /* Access modifiers changed, original: 0000 */
    public String getEndpoint() {
        return URL_PATH;
    }

    public Map<String, String> getHeaders() {
        return this.mHeaderMap;
    }

    public String getBody() {
        return this.mPostBody.toJson(getCustomTypeAdapters());
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWCheckinResponse> getResponseClass() {
        return MWCheckinResponse.class;
    }

    public List<CustomTypeAdapter> getCustomTypeAdapters() {
        List<CustomTypeAdapter> adapters = new ArrayList();
        adapters.add(new MWPointOfDistributionTypeAdapter());
        adapters.add(new MWPriceTypeAdapter());
        return adapters;
    }

    public String toString() {
        return "MWSocialCheckinRequest{mHeaderMap=" + this.mHeaderMap + ", mPostBody=" + getBody() + "}";
    }
}
