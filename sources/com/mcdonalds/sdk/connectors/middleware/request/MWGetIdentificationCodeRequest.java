package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWSelectToRedeemResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWGetIdentificationCodeRequest extends MWRequest<MWSelectToRedeemResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/preference/identificationCode";
    private final MWRequestHeaders mHeaderMap;
    private final MWGETQueryArgs mQueryArgs;

    @Deprecated
    public MWGetIdentificationCodeRequest(MiddlewareConnector ignored, String ecpToken, String username, String storeId) {
        this(ecpToken, username, storeId);
    }

    public MWGetIdentificationCodeRequest(String ecpToken, String username, String storeId) {
        this.mQueryArgs = new MWGETQueryArgs();
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mQueryArgs.put("userName", username);
        this.mQueryArgs.put("storeId", storeId);
    }

    public MethodType getMethodType() {
        return MethodType.GET;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    /* Access modifiers changed, original: 0000 */
    public String getEndpoint() {
        return URL_PATH;
    }

    /* Access modifiers changed, original: 0000 */
    public String getQueryString() {
        return this.mQueryArgs.toString();
    }

    public Map<String, String> getHeaders() {
        return this.mHeaderMap;
    }

    public String getBody() {
        return null;
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWSelectToRedeemResponse> getResponseClass() {
        return MWSelectToRedeemResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWGetIdentificationCodeRequest{mHeaderMap=" + this.mHeaderMap + ", mBody=" + this.mQueryArgs + "}";
    }
}
