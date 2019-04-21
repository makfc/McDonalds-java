package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.deserializer.ISO8601DateDeserializer;
import com.mcdonalds.sdk.connectors.middleware.model.MWCustomerData;
import com.mcdonalds.sdk.connectors.middleware.response.MWCustomerDeregisterResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MWSocialDeregisterRequest extends MWRequest<MWCustomerDeregisterResponse, Void> {
    private static final String URL_PATH = "/customer/socialLogin/deregister";
    private final List<CustomTypeAdapter> mCustomDeserializers;
    private final MWRequestHeaders mHeaderMap;
    private final MWJSONRequestBody mPostBody;

    @Deprecated
    public MWSocialDeregisterRequest(MiddlewareConnector ignored, String ecpToken, MWCustomerData customerData, String cancellationReason) {
        this(ecpToken, customerData, cancellationReason);
    }

    public MWSocialDeregisterRequest(String ecpToken, MWCustomerData customerData, String cancellationReason) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mPostBody = new MWJSONRequestBody();
        this.mPostBody.generateHash();
        this.mPostBody.put("loginInfo", customerData.loginInfo);
        this.mPostBody.put("cancellationReason", cancellationReason);
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
        return this.mPostBody.toJson(getCustomTypeAdapters());
    }

    public void setBody(Void body) {
    }

    public Class<MWCustomerDeregisterResponse> getResponseClass() {
        return MWCustomerDeregisterResponse.class;
    }

    public List<CustomTypeAdapter> getCustomTypeAdapters() {
        return this.mCustomDeserializers;
    }

    public String toString() {
        return "MWCustomerDeregisterRequest{mHeaderMap=" + this.mHeaderMap + ", mPostBody=" + this.mPostBody.toJson(getCustomTypeAdapters()) + ", mCustomDeserializers=" + this.mCustomDeserializers + "}";
    }
}
