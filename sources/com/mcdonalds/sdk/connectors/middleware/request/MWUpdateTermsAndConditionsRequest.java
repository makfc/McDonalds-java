package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.model.MWCustomerData;
import com.mcdonalds.sdk.connectors.middleware.response.MWUpdateTermsAndConditionsResponse;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWUpdateTermsAndConditionsRequest implements RequestProvider<MWUpdateTermsAndConditionsResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/terms/acceptance";
    private final MWRequestHeaders mHeaderMap;
    protected MWJSONRequestBody mPostBody = new MWJSONRequestBody();
    private final String mUrl;

    public MWUpdateTermsAndConditionsRequest(String ecpToken, CustomerProfile profile, Boolean isPrivacyPolicyAccepted, Boolean isTermsOfUseAccepted) {
        this.mHeaderMap = new MWRequestHeaders(ecpToken);
        this.mPostBody.put("userName", profile.getUserName());
        this.mPostBody.put("password", profile.getPassword());
        this.mPostBody.put("isPrivacyPolicyAccepted", isPrivacyPolicyAccepted);
        this.mPostBody.put("isTermsOfUseAccepted", isTermsOfUseAccepted);
        if (profile.isUsingSocialLogin()) {
            this.mPostBody.put("loginInfo", MWCustomerData.fromCustomer(profile).loginInfo);
        }
        this.mUrl = MiddlewareConnector.getURLStringForEndpoint(URL_PATH);
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
        this.mPostBody = body;
    }

    public Class<MWUpdateTermsAndConditionsResponse> getResponseClass() {
        return MWUpdateTermsAndConditionsResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWUpdateTermsAndConditionsRequest{mHeaderMap=" + this.mHeaderMap + ", mPostBody=" + this.mPostBody + "}";
    }
}
