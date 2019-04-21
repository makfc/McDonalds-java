package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWArchiveOfferResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWArchiveOfferRequest extends MWRequest<MWArchiveOfferResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/offer/archive";
    private final MWJSONRequestBody mBody;
    private final MWRequestHeaders mHeaderMap;

    @Deprecated
    public MWArchiveOfferRequest(MiddlewareConnector ignored, String ecpToken, String username, Integer offerId) {
        this(ecpToken, username, offerId);
    }

    public MWArchiveOfferRequest(String ecpToken, String username, Integer offerId) {
        this.mBody = new MWJSONRequestBody();
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mBody.put("content", Integer.valueOf(0));
        this.mBody.put("offerId", offerId);
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

    public Class<MWArchiveOfferResponse> getResponseClass() {
        return MWArchiveOfferResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWArchiveOfferRequest{mHeaderMap=" + this.mHeaderMap + ", mBody=" + this.mBody + "}";
    }
}
