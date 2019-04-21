package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.deserializer.ISO8601DateDeserializer;
import com.mcdonalds.sdk.connectors.middleware.response.MWLookupDeliveryChargeResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MWLookupDeliveryChargeRequest extends MWRequest<MWLookupDeliveryChargeResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/delivery/charge";
    private final MWJSONRequestBody mBody;
    private final MWRequestHeaders mHeaderMap;

    @Deprecated
    public MWLookupDeliveryChargeRequest(MiddlewareConnector ignored, String ecpToken, String username, String storeId, double orderValue) {
        this(ecpToken, username, storeId, orderValue);
    }

    public MWLookupDeliveryChargeRequest(String ecpToken, String username, String storeId, double orderValue) {
        this.mBody = new MWJSONRequestBody();
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mBody.put("userName", username);
        this.mBody.put("storeId", storeId);
        if (orderValue <= 0.0d) {
            orderValue = 1.0d;
        }
        this.mBody.put("orderValue", Double.valueOf(orderValue));
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
        return this.mBody.toJson();
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWLookupDeliveryChargeResponse> getResponseClass() {
        return MWLookupDeliveryChargeResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return Arrays.asList(new ISO8601DateDeserializer[]{new ISO8601DateDeserializer()});
    }

    public String toString() {
        return "MWLookupDeliveryChargeRequest{mHeaderMap=" + this.mHeaderMap + ", mBody=" + this.mBody + "}";
    }
}
