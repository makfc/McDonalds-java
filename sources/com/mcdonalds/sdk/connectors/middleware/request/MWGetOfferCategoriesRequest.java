package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.deserializer.ISO8601DateDeserializer;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetOfferCategoriesResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MWGetOfferCategoriesRequest extends MWRequest<MWGetOfferCategoriesResponse, Void> {
    private static final String URL_PATH = "/offer/category";
    private final List<CustomTypeAdapter> mCustomDeserializers;
    private final MWRequestHeaders mHeaderMap;
    private final MWGETQueryArgs mQueryArgs;

    @Deprecated
    public MWGetOfferCategoriesRequest(MiddlewareConnector ignored, String ecpToken) {
        this(ecpToken);
    }

    public MWGetOfferCategoriesRequest(String ecpToken) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mHeaderMap.put(TransactionStateUtil.CONTENT_TYPE_HEADER, "text/json");
        this.mCustomDeserializers = new ArrayList();
        this.mCustomDeserializers.add(new ISO8601DateDeserializer());
        this.mQueryArgs = new MWGETQueryArgs();
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

    public Class<MWGetOfferCategoriesResponse> getResponseClass() {
        return MWGetOfferCategoriesResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return this.mCustomDeserializers;
    }

    public String toString() {
        return "MWGetOfferCategoriesRequest{mHeaderMap=" + this.mHeaderMap + ", mQueryArgs=" + this.mQueryArgs.toString() + ", mCustomDeserializers=" + this.mCustomDeserializers + "}";
    }
}
