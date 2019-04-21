package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.model.MWAddressElement;
import com.mcdonalds.sdk.connectors.middleware.response.MWValidateAddressResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWValidateAddressRequest extends MWRequest<MWValidateAddressResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/address/validation";
    private final MWRequestHeaders mHeaderMap;
    protected MWJSONRequestBody mPostBody;

    @Deprecated
    public MWValidateAddressRequest(MiddlewareConnector ignored, String ecpToken, String userName, List<MWAddressElement> addressElements) {
        this(ecpToken, userName, addressElements);
    }

    public MWValidateAddressRequest(String ecpToken, String userName, List<MWAddressElement> addressElements) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mPostBody = new MWJSONRequestBody();
        this.mPostBody.put("externalMarketCode", this.mPostBody.get("marketId"));
        this.mPostBody.put("userName", userName);
        this.mPostBody.put("addressElements", addressElements);
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
        return this.mPostBody.toJson();
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWValidateAddressResponse> getResponseClass() {
        return MWValidateAddressResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }
}
