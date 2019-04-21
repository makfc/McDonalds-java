package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.deserializer.MWPointOfDistributionTypeAdapter;
import com.mcdonalds.sdk.connectors.middleware.deserializer.MWPriceTypeAdapter;
import com.mcdonalds.sdk.connectors.middleware.model.MWOrderView;
import com.mcdonalds.sdk.connectors.middleware.response.MWFoundationalCheckInResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MWFoundationalCheckInRequest implements RequestProvider<MWFoundationalCheckInResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/order/pickup";
    private final MWRequestHeaders mHeaderMap;
    private final MWJSONRequestBody mPostBody = new MWJSONRequestBody(false);
    private final String mUrl;

    public MWFoundationalCheckInRequest(String ecpToken, String username, String storeId, MWOrderView orderView) {
        this.mHeaderMap = new MWRequestHeaders(ecpToken);
        this.mPostBody.put("userName", username);
        this.mPostBody.put("storeId", storeId);
        this.mPostBody.put("orderView", orderView);
        this.mUrl = MiddlewareConnector.getURLStringForEndpoint(URL_PATH);
    }

    public MethodType getMethodType() {
        return MethodType.POST;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    public String getURLString() {
        return this.mUrl;
    }

    public Map<String, String> getHeaders() {
        return this.mHeaderMap;
    }

    public String getBody() {
        return this.mPostBody.toJson(getCustomTypeAdapters());
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWFoundationalCheckInResponse> getResponseClass() {
        return MWFoundationalCheckInResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        List<CustomTypeAdapter> adapters = new ArrayList();
        adapters.add(new MWPointOfDistributionTypeAdapter());
        adapters.add(new MWPriceTypeAdapter());
        return adapters;
    }

    public String toString() {
        return "MWFoundationalCheckInRequest{mHeaderMap=" + this.mHeaderMap.toString() + ", mPostBody=" + getBody() + ", mUrl=\"" + this.mUrl + "\"}";
    }
}
