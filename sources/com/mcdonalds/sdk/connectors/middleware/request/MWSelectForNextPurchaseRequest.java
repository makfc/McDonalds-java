package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.deserializer.ISO8601DateDeserializer;
import com.mcdonalds.sdk.connectors.middleware.response.MWJSONResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MWSelectForNextPurchaseRequest extends MWRequest<MWJSONResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/offer";
    private final MWJSONRequestBody mBody;
    private final List<CustomTypeAdapter> mCustomDeserializers;
    private final MWRequestHeaders mHeaderMap;

    @Deprecated
    public MWSelectForNextPurchaseRequest(MiddlewareConnector ignored, String ecpToken, Integer offerId, String username) {
        this(ecpToken, offerId, username);
    }

    public MWSelectForNextPurchaseRequest(String ecpToken, Integer offerId, String username) {
        this.mBody = new MWJSONRequestBody();
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mBody.put("offerId", offerId);
        this.mBody.put("userName", username);
        this.mCustomDeserializers = new ArrayList();
        this.mCustomDeserializers.add(new ISO8601DateDeserializer());
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

    public Class<MWJSONResponse> getResponseClass() {
        return MWJSONResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return this.mCustomDeserializers;
    }

    public String toString() {
        return "MWSelectForNextPurchaseRequest{mHeaderMap=" + this.mHeaderMap + ", mBody=" + this.mBody + ", mCustomDeserializers=" + this.mCustomDeserializers + "}";
    }
}
