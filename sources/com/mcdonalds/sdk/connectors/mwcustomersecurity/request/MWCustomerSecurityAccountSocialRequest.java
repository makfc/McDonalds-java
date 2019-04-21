package com.mcdonalds.sdk.connectors.mwcustomersecurity.request;

import android.text.TextUtils;
import com.amap.api.services.district.DistrictSearchQuery;
import com.mcdonalds.sdk.connectors.middleware.model.MWCustomerData;
import com.mcdonalds.sdk.connectors.middleware.request.MWJSONRequestBody;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.MWCustomerSecurityConnector;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.response.MWCustomerSecurityAccountNativeResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MWCustomerSecurityAccountSocialRequest implements RequestProvider<MWCustomerSecurityAccountNativeResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/security/account";
    private final MWCustomerSecurityRequestHeaders mHeaderMap = new MWCustomerSecurityRequestHeaders();
    private MWJSONRequestBody mPostBody;
    private final String mUrl;

    public MWCustomerSecurityAccountSocialRequest(MWCustomerSecurityConnector connector, MWCustomerData customerData, String locale, String socialAuthToken, boolean privacyPolicyAccepted) {
        this.mPostBody = new MWJSONRequestBody(connector);
        String lastName = TextUtils.isEmpty(customerData.lastName) ? "-" : customerData.lastName;
        if (customerData.loginPreference != 0) {
            this.mPostBody.put("loginPreference", Integer.valueOf(customerData.loginPreference));
        }
        this.mPostBody.put("locale", Locale.getDefault().toString().replace('_', '-'));
        this.mPostBody.put("firstName", customerData.firstName);
        this.mPostBody.put("lastName", lastName);
        this.mPostBody.put("emailAddress", customerData.emailAddress);
        this.mPostBody.put("authToken", socialAuthToken);
        this.mPostBody.put(DistrictSearchQuery.KEYWORDS_COUNTRY, (String) this.mHeaderMap.get("MarketId"));
        this.mPostBody.put("zipCode", customerData.zipCode);
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

    public Class<MWCustomerSecurityAccountNativeResponse> getResponseClass() {
        return MWCustomerSecurityAccountNativeResponse.class;
    }

    public List<CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWCustomerRegisterRequest{mHeaderMap=" + this.mHeaderMap + ", mPostBody=" + this.mPostBody + ", mUrl=\"" + this.mUrl + "\"}";
    }
}
