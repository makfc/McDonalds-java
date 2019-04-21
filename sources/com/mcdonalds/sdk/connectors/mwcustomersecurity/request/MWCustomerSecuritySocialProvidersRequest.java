package com.mcdonalds.sdk.connectors.mwcustomersecurity.request;

import android.util.Log;
import com.mcdonalds.sdk.connectors.middleware.request.MWJSONRequestBody;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.MWCustomerSecurityConnector;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.response.MWCustomerSecuritySocialProvidersResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWCustomerSecuritySocialProvidersRequest implements RequestProvider<MWCustomerSecuritySocialProvidersResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/security/socialProviders";
    private final MWCustomerSecurityRequestHeaders mHeaderMap = new MWCustomerSecurityRequestHeaders();
    private MWJSONRequestBody mPostBody;
    private final String mUrl;

    public MWCustomerSecuritySocialProvidersRequest(MWCustomerSecurityConnector connector) {
        Log.d("HEADER_MAP", "" + this.mHeaderMap.toString());
        this.mUrl = connector.getURLStringForEndpoint(URL_PATH);
    }

    public MethodType getMethodType() {
        return MethodType.GET;
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
        return null;
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWCustomerSecuritySocialProvidersResponse> getResponseClass() {
        return MWCustomerSecuritySocialProvidersResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }
}
