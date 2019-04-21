package com.mcdonalds.sdk.connectors.mwcustomersecurity.request;

import android.util.Log;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.MWCustomerSecurityConnector;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.response.MWCustomerSecurityAccountVerificationSMSResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWCustomerSecurityAccountVerificationSMSRequest implements RequestProvider<MWCustomerSecurityAccountVerificationSMSResponse, MWCustomerSecurityJSONRequestBody> {
    private static final String URL_PATH = "/customer/security/account/registrationVerification?verificationType=sms";
    private final MWCustomerSecurityRequestHeaders mHeaderMap = new MWCustomerSecurityRequestHeaders();
    private MWCustomerSecurityJSONRequestBody mPostBody;
    private final String mUrl;

    public MWCustomerSecurityAccountVerificationSMSRequest(MWCustomerSecurityConnector connector, String accessToken, String smsCode) {
        this.mPostBody = new MWCustomerSecurityJSONRequestBody(connector);
        this.mPostBody.put("accessToken", accessToken);
        this.mPostBody.put("verificationCode", smsCode);
        this.mUrl = connector.getURLStringForEndpoint(URL_PATH);
        Log.d("SMS_REQUEST_TEST", toString());
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
    }

    public Class<MWCustomerSecurityAccountVerificationSMSResponse> getResponseClass() {
        return MWCustomerSecurityAccountVerificationSMSResponse.class;
    }

    public List<CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWCustomerRegisterRequest{mHeaderMap=" + this.mHeaderMap + ", mPostBody=" + this.mPostBody + ", mUrl=\"" + this.mUrl + "\"}";
    }
}
