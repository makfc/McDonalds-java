package com.mcdonalds.sdk.connectors.mwcustomersecurity.request;

import com.mcdonalds.sdk.connectors.middleware.request.MWJSONRequestBody;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.MWCustomerSecurityConnector;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.response.MWCustomerSecuritySocialAuthenticationResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MWCustomerSecuritySocialAuthenticationRequest implements RequestProvider<MWCustomerSecuritySocialAuthenticationResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/security/authentication";
    private final MWCustomerSecurityRequestHeaders mHeaderMap = new MWCustomerSecurityRequestHeaders();
    private MWJSONRequestBody mPostBody;
    private final String mUrl;

    public MWCustomerSecuritySocialAuthenticationRequest(MWCustomerSecurityConnector connector, String locale, String emailAddress, String firstName, String lastName, String socialProvider, String socialAuthenticationToken) {
        this.mPostBody = new MWJSONRequestBody(connector);
        this.mPostBody.put("locale", Locale.getDefault().toString().replace('_', '-'));
        this.mPostBody.put("emailAddress", emailAddress);
        this.mPostBody.put("firstName", firstName);
        this.mPostBody.put("lastName", lastName);
        this.mPostBody.put("provider", socialProvider);
        this.mPostBody.put("idpToken", socialAuthenticationToken);
        this.mUrl = connector.getURLStringForEndpoint(URL_PATH);
    }

    public MethodType getMethodType() {
        return MethodType.POST;
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
        return this.mPostBody.toJson();
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWCustomerSecuritySocialAuthenticationResponse> getResponseClass() {
        return MWCustomerSecuritySocialAuthenticationResponse.class;
    }

    public List<CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }
}
