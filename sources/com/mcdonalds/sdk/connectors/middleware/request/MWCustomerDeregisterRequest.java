package com.mcdonalds.sdk.connectors.middleware.request;

import android.text.TextUtils;
import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.deserializer.ISO8601DateDeserializer;
import com.mcdonalds.sdk.connectors.middleware.response.MWCustomerDeregisterResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MWCustomerDeregisterRequest extends MWRequest<MWCustomerDeregisterResponse, Void> {
    private static final String URL_PATH = "/customer/registration";
    private final List<CustomTypeAdapter> mCustomDeserializers;
    private final MWRequestHeaders mHeaderMap;
    private final MWGETQueryArgs mQueryArgs;

    @Deprecated
    public MWCustomerDeregisterRequest(MiddlewareConnector ignored, String ecpToken, String userName, String password, String cancellationReason) {
        this(ecpToken, "", userName, password, cancellationReason);
    }

    public MWCustomerDeregisterRequest(String ecpToken, String customerId, String userName, String password, String cancellationReason) {
        this.mQueryArgs = new MWGETQueryArgs();
        this.mQueryArgs.put("customerId", customerId);
        this.mQueryArgs.put("userName", userName);
        this.mQueryArgs.put("password", password);
        if (!TextUtils.isEmpty(cancellationReason)) {
            this.mQueryArgs.put("cancellationReason", cancellationReason);
        }
        this.mCustomDeserializers = new ArrayList();
        this.mCustomDeserializers.add(new ISO8601DateDeserializer());
        this.mHeaderMap = getHeaderMap(ecpToken);
    }

    public MethodType getMethodType() {
        return MethodType.DELETE;
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
        return null;
    }

    public void setBody(Void body) {
    }

    public Class<MWCustomerDeregisterResponse> getResponseClass() {
        return MWCustomerDeregisterResponse.class;
    }

    /* Access modifiers changed, original: 0000 */
    public String getQueryString() {
        return this.mQueryArgs.toString();
    }

    public List<CustomTypeAdapter> getCustomTypeAdapters() {
        return this.mCustomDeserializers;
    }

    public String toString() {
        return "MWCustomerDeregisterRequest{mHeaderMap=" + this.mHeaderMap + ", mQueryArgs=" + this.mQueryArgs + ", mCustomDeserializers=" + this.mCustomDeserializers + "}";
    }
}
