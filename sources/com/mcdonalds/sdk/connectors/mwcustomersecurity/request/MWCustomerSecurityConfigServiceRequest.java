package com.mcdonalds.sdk.connectors.mwcustomersecurity.request;

import com.mcdonalds.sdk.connectors.middleware.request.MWJSONRequestBody;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.MWCustomerSecurityConnector;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.response.MWCustomerSecurityConfigServiceResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWCustomerSecurityConfigServiceRequest implements RequestProvider<MWCustomerSecurityConfigServiceResponse, MWJSONRequestBody> {
    private final MWCustomerSecurityRequestHeaders mHeaderMap = new MWCustomerSecurityRequestHeaders(MWCustomerSecurityConnector.CONFIG_VERSIONING_SERVICE_BASE_URL);
    private final String mUrl;

    public MWCustomerSecurityConfigServiceRequest(MWCustomerSecurityConnector connector) {
        this.mUrl = connector.getVersioningServiceUrl();
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

    public Class<MWCustomerSecurityConfigServiceResponse> getResponseClass() {
        return MWCustomerSecurityConfigServiceResponse.class;
    }

    public List<CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWCustomerSecurityConfigServiceRequest{mHeaderMap=" + this.mHeaderMap + ", mUrl='" + this.mUrl + '\'' + '}';
    }
}
