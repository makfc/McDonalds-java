package com.mcdonalds.sdk.connectors.mwcustomersecurity.request;

import android.text.TextUtils;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.MWCustomerSecurityConnector;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.response.MWCustomerSecurityAccountNativeResponse;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class MWCustomerSecurityAccountNativeRequest implements RequestProvider<MWCustomerSecurityAccountNativeResponse, MWCustomerSecurityJSONRequestBody> {
    private static final String URL_PATH = "/customer/security/account";
    private final MWCustomerSecurityRequestHeaders mHeaderMap = new MWCustomerSecurityRequestHeaders();
    private MWCustomerSecurityJSONRequestBody mPostBody;
    private MWCustomerSecurityUrlQueryArgs mQueryParam;
    private final String mUrl;

    public MWCustomerSecurityAccountNativeRequest(MWCustomerSecurityConnector connector, CustomerProfile customer, String termsAndConditionVersion, String privacyPolicyVersion) {
        this.mPostBody = new MWCustomerSecurityJSONRequestBody(connector);
        String lastName = TextUtils.isEmpty(customer.getLastName()) ? "-" : customer.getLastName();
        this.mPostBody.put("firstName", customer.getFirstName());
        this.mPostBody.put("lastName", lastName);
        this.mPostBody.put("emailAddress", customer.getEmailAddress());
        this.mPostBody.put("password", customer.getPassword());
        this.mPostBody.put("confirmPassword", customer.getPassword());
        if (customer.getBirthDate() != null) {
            this.mPostBody.put("birthDate", new SimpleDateFormat("yyyy-MM-dd").format(customer.getBirthDate().getTime()));
        }
        if (customer.getGender() != null) {
            this.mPostBody.put("gender", customer.getGender());
        }
        this.mPostBody.put("mobilePhone", customer.getMobileNumber());
        this.mPostBody.put("zipCode", customer.getZipCode());
        this.mPostBody.put("addressCountry", (String) this.mHeaderMap.get("MarketId"));
        this.mPostBody.put("tncMobile", termsAndConditionVersion);
        this.mPostBody.put("ppMobile", privacyPolicyVersion);
        this.mQueryParam = new MWCustomerSecurityUrlQueryArgs(connector, URL_PATH);
        this.mQueryParam.put("type", "traditional");
        this.mUrl = this.mQueryParam.toString();
        MCDLog.debug("Request: " + toString());
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

    public void setBody(MWCustomerSecurityJSONRequestBody body) {
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
