package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWJSONResponse;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWCustomerResendActivationRequest extends MWRequest<MWJSONResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/activationNotification";
    private final MWJSONRequestBody mBody;
    private final MWRequestHeaders mHeaderMap;

    @Deprecated
    public MWCustomerResendActivationRequest(MiddlewareConnector ignored, String ecpToken, String username) {
        this(ecpToken, username);
    }

    public MWCustomerResendActivationRequest(String ecpToken, String username) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mBody = new MWJSONRequestBody();
        this.mBody.put("userName", username);
    }

    @Deprecated
    public MWCustomerResendActivationRequest(MiddlewareConnector ignored, String ecpToken, CustomerProfile profile) {
        this(ecpToken, profile);
    }

    public MWCustomerResendActivationRequest(String ecpToken, CustomerProfile profile) {
        this(ecpToken, profile.getUserName());
        if (profile.getActivationOption() != 0) {
            this.mBody.put("firstName", profile.getFirstName());
            this.mBody.put("lastName", profile.getLastName());
            this.mBody.put("loginPreference", Integer.valueOf(profile.getActivationOption()));
        }
    }

    public MethodType getMethodType() {
        return MethodType.POST;
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
        return this.mBody.toJson();
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWJSONResponse> getResponseClass() {
        return MWJSONResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWCustomerResendActivationRequest{mHeaderMap=" + this.mHeaderMap + ", mBody=" + this.mBody + "}";
    }
}
