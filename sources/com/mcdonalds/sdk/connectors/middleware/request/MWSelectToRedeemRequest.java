package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWSelectToRedeemResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWSelectToRedeemRequest extends MWRequest<MWSelectToRedeemResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/offer/redemption";
    private final MWJSONRequestBody mBody;
    private final MWRequestHeaders mHeaderMap;

    @Deprecated
    public MWSelectToRedeemRequest(MiddlewareConnector ignored, String ecpToken, String username, List<String> offerIds, Integer storeId) {
        this(ecpToken, username, offerIds, storeId);
    }

    public MWSelectToRedeemRequest(String ecpToken, String username, List<String> offerIds, Integer storeId) {
        this.mBody = new MWJSONRequestBody();
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mBody.put("offersIds", offerIds);
        this.mBody.put("storeId", storeId);
        this.mBody.put("userName", username);
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

    public Class<MWSelectToRedeemResponse> getResponseClass() {
        return MWSelectToRedeemResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWSelectToRedeemRequest{mHeaderMap=" + this.mHeaderMap + ", mBody=" + this.mBody + "}";
    }
}
