package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.deserializer.ISO8601DateDeserializer;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetCustomerOffersResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MWGetCustomerOffersRequest extends MWRequest<MWGetCustomerOffersResponse, Void> {
    private static final String URL_PATH = "/customer/offer";
    private final List<CustomTypeAdapter> mCustomDeserializers;
    private final MWRequestHeaders mHeaderMap;
    private final MWGETQueryArgs mQueryArgs;

    @Deprecated
    public MWGetCustomerOffersRequest(MiddlewareConnector ignored, String ecpToken, Double latitude, Double longitude, String username) {
        this(ecpToken, latitude, longitude, null, username);
    }

    public MWGetCustomerOffersRequest(String ecpToken, Double latitude, Double longitude, List<String> storeIds, String username) {
        this.mQueryArgs = new MWGETQueryArgs();
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mQueryArgs.put("userName", username);
        this.mQueryArgs.put("latitude", latitude);
        this.mQueryArgs.put("longitude", longitude);
        if (!(storeIds == null || storeIds.isEmpty())) {
            this.mQueryArgs.put("storeId", storeIds);
        }
        this.mCustomDeserializers = new ArrayList();
        this.mCustomDeserializers.add(new ISO8601DateDeserializer());
    }

    public MethodType getMethodType() {
        return MethodType.GET;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    /* Access modifiers changed, original: 0000 */
    public String getEndpoint() {
        return URL_PATH;
    }

    /* Access modifiers changed, original: 0000 */
    public String getQueryString() {
        return this.mQueryArgs.toString();
    }

    public Map<String, String> getHeaders() {
        return this.mHeaderMap;
    }

    public String getBody() {
        return null;
    }

    public void setBody(Void body) {
    }

    public Class<MWGetCustomerOffersResponse> getResponseClass() {
        return MWGetCustomerOffersResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return this.mCustomDeserializers;
    }

    public String toString() {
        return "MWGetCustomerOffersRequest{mHeaderMap=" + this.mHeaderMap + ", mQueryArgs=" + this.mQueryArgs.toString() + ", mCustomDeserializers=" + this.mCustomDeserializers + "}";
    }
}
