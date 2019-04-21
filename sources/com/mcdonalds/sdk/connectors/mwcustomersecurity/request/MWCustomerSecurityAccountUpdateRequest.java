package com.mcdonalds.sdk.connectors.mwcustomersecurity.request;

import com.mcdonalds.sdk.connectors.mwcustomersecurity.MWCustomerSecurityConnector;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.response.MWCustomerSecurityAccountUpdateResponse;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class MWCustomerSecurityAccountUpdateRequest implements RequestProvider<MWCustomerSecurityAccountUpdateResponse, MWCustomerSecurityJSONRequestBody> {
    private static final String URL_PATH = "/customer/security/account";
    private final MWCustomerSecurityRequestHeaders mHeaderMap = new MWCustomerSecurityRequestHeaders();
    protected MWCustomerSecurityJSONRequestBody mPostBody;
    private String mUrl;

    public MWCustomerSecurityAccountUpdateRequest(MWCustomerSecurityConnector connector, CustomerProfile customer, String addressCountry, String accessToken) {
        this.mPostBody = new MWCustomerSecurityJSONRequestBody(connector);
        this.mPostBody.put("accessToken", accessToken);
        this.mUrl = connector.getURLStringForEndpoint(URL_PATH);
        if (customer.isPasswordChangeRequired()) {
            this.mPostBody.put("password", customer.getPassword());
            this.mPostBody.put("newPassword", customer.getNewPassword());
            this.mPostBody.put("newPasswordConfirm", customer.getNewPassword());
            this.mUrl += "?type=password";
        }
        if (customer.getFirstName() != null) {
            this.mPostBody.put("firstName", customer.getFirstName());
        }
        if (customer.getLastName() != null) {
            this.mPostBody.put("lastName", customer.getLastName());
        }
        if (customer.getMobileNumber() != null) {
            this.mPostBody.put("mobilePhone", customer.getMobileNumber());
        }
        if (customer.getZipCode() != null) {
            this.mPostBody.put("zipCode", customer.getZipCode());
        }
        if (customer.shouldChangeBirthdate()) {
            String birthDate = "";
            if (customer.getBirthDate() != null) {
                birthDate = new SimpleDateFormat("yyyy-MM-dd").format(customer.getBirthDate().getTime());
            }
            this.mPostBody.put("birthDate", birthDate);
        }
        if (customer.getGender() != null) {
            this.mPostBody.put("gender", customer.getGender());
        }
        if (customer.getmTermsAndConditionVersion() != null) {
            this.mPostBody.put("tncMobile", customer.getmTermsAndConditionVersion());
        }
        if (customer.getmPrivacyPolicyVersion() != null) {
            this.mPostBody.put("ppMobile", customer.getmPrivacyPolicyVersion());
        }
        if (customer.getDeactivateAccountTimeStamp() != null) {
            this.mPostBody.put("deactivateAccount", customer.getDeactivateAccountTimeStamp());
        }
        this.mPostBody.put("addressCountry", addressCountry);
        MCDLog.debug("Update Profile Request: " + this.mPostBody.toString());
    }

    public MethodType getMethodType() {
        return MethodType.PUT;
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
        this.mPostBody = body;
    }

    public Class<MWCustomerSecurityAccountUpdateResponse> getResponseClass() {
        return MWCustomerSecurityAccountUpdateResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWCustomerSecurityAccountUpdateRequest{mHeaderMap=" + this.mHeaderMap + ", mPostBody=" + this.mPostBody + "}";
    }
}
