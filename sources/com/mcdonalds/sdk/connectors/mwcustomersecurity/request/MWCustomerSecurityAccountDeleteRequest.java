package com.mcdonalds.sdk.connectors.mwcustomersecurity.request;

import com.mcdonalds.sdk.connectors.mwcustomersecurity.MWCustomerSecurityConnector;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.response.MWCustomerSecurityAuthenticationResponse;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWCustomerSecurityAccountDeleteRequest implements RequestProvider<MWCustomerSecurityAuthenticationResponse, MWCustomerSecurityJSONRequestBody> {
    private static final String URL_PATH = "/customer/security/account";
    private final MWCustomerSecurityRequestHeaders mHeaderMap = new MWCustomerSecurityRequestHeaders();
    private MWCustomerSecurityUrlQueryArgs mQueryArgs;
    private final String mUrl;

    public MWCustomerSecurityAccountDeleteRequest(MWCustomerSecurityConnector connector, CustomerProfile profile, String accessToken) {
        this.mHeaderMap.put("accessToken", accessToken);
        this.mQueryArgs = new MWCustomerSecurityUrlQueryArgs(connector, URL_PATH);
        this.mQueryArgs.put("id", profile.getEmailAddress());
        this.mUrl = this.mQueryArgs.toString();
    }

    public MethodType getMethodType() {
        return MethodType.DELETE;
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

    public void setBody(MWCustomerSecurityJSONRequestBody body) {
    }

    public Class<MWCustomerSecurityAuthenticationResponse> getResponseClass() {
        return MWCustomerSecurityAuthenticationResponse.class;
    }

    public List<CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWCustomerSecurityAccountDeleteRequest{mHeaderMap=" + this.mHeaderMap + ", mUrl='" + this.mUrl + '\'' + '}';
    }
}
