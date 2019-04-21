package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWBoundaryCrossCheckInResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWBoundaryCrossCheckInRequest implements RequestProvider<MWBoundaryCrossCheckInResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/order/pickup/%s";
    private MWRequestHeaders mHeaderMap;
    private MWJSONRequestBody mPostBody = new MWJSONRequestBody(false);
    private String mURL;

    public MWBoundaryCrossCheckInRequest(String ecpToken, String checkInCode, String storeID) {
        this.mHeaderMap = new MWRequestHeaders(ecpToken);
        this.mPostBody.put("posStoreNumber", storeID);
        this.mURL = MiddlewareConnector.getURLStringForEndpoint(String.format(URL_PATH, new Object[]{checkInCode}));
    }

    public MethodType getMethodType() {
        return MethodType.POST;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    public String getURLString() {
        return this.mURL;
    }

    public Map<String, String> getHeaders() {
        return this.mHeaderMap;
    }

    public String getBody() {
        return this.mPostBody.toJson();
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWBoundaryCrossCheckInResponse> getResponseClass() {
        return MWBoundaryCrossCheckInResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }
}
