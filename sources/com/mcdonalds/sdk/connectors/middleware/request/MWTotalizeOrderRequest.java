package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.deserializer.MWPointOfDistributionTypeAdapter;
import com.mcdonalds.sdk.connectors.middleware.deserializer.MWPriceTypeAdapter;
import com.mcdonalds.sdk.connectors.middleware.model.MWOrderView;
import com.mcdonalds.sdk.connectors.middleware.response.MWTotalizeResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MWTotalizeOrderRequest extends MWRequest<MWTotalizeResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/order/total";
    private final MWRequestHeaders mHeaderMap;
    protected final MWJSONRequestBody mPostBody;

    @Deprecated
    public MWTotalizeOrderRequest(MiddlewareConnector ignored, String ecpToken, String username, String storeId, MWOrderView orderView) {
        this(ecpToken, username, storeId, orderView);
    }

    public MWTotalizeOrderRequest(String ecpToken, String username, String storeId, MWOrderView orderView) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mPostBody = new MWJSONRequestBody(false);
        this.mPostBody.put("userName", username);
        this.mPostBody.put("storeId", storeId);
        this.mPostBody.put("orderView", orderView);
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

    public Class<MWTotalizeResponse> getResponseClass() {
        return MWTotalizeResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return Arrays.asList(new CustomTypeAdapter[]{new MWPriceTypeAdapter(), new MWPointOfDistributionTypeAdapter()});
    }

    public String toString() {
        return "MWTotalizeOrderRequest{mHeaderMap=" + this.mHeaderMap + ", mPostBody=" + getBody() + "}";
    }
}
