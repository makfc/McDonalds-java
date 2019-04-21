package com.mcdonalds.sdk.connectors.middleware.request;

import android.support.annotation.Nullable;
import com.mcdonalds.sdk.connectors.middleware.deserializer.ISO8601DateDeserializer;
import com.mcdonalds.sdk.connectors.middleware.model.MWDeliveryStatusLookupCriteria;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetDeliveryStatusResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MWGetDeliveryStatusRequest extends MWRequest<MWGetDeliveryStatusResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/delivery/orderTracking";
    private final MWJSONRequestBody mBody;
    private final MWRequestHeaders mHeaderMap;

    public MWGetDeliveryStatusRequest(String ecpToken, String username, @Nullable String orderNumber) {
        MWDeliveryStatusLookupCriteria criteria = new MWDeliveryStatusLookupCriteria();
        if (orderNumber != null) {
            criteria.orderNumber = orderNumber;
        } else {
            criteria.username = username;
        }
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mBody = new MWJSONRequestBody();
        this.mBody.put("lookupCriteria", criteria);
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

    public Class<MWGetDeliveryStatusResponse> getResponseClass() {
        return MWGetDeliveryStatusResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return Collections.singletonList(new ISO8601DateDeserializer());
    }

    public String toString() {
        return "MWGetDeliveryStatusRequest{mHeaderMap=" + this.mHeaderMap + ", mBody=" + this.mBody + '}';
    }
}
