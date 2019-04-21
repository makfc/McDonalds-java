package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWJSONResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MWOfferExpirationRequest implements RequestProvider<MWJSONResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/offer/expiration";
    private final MWJSONRequestBody mBody = new MWJSONRequestBody();
    private final List<CustomTypeAdapter> mCustomDeserializers;
    private final MWRequestHeaders mHeaderMap;
    private String mUrl;

    public MWOfferExpirationRequest(String ecpToken, Integer offerId, Date expStartDate, Date expEndDate) {
        this.mHeaderMap = new MWRequestHeaders(ecpToken);
        this.mBody.put("offerId", offerId);
        this.mBody.put("startDate", "/Date(" + expStartDate.getTime() + ")/");
        this.mBody.put("endDate", "/Date(" + expEndDate.getTime() + ")/");
        this.mCustomDeserializers = new ArrayList();
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
        return this.mBody.toJson();
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWJSONResponse> getResponseClass() {
        return MWJSONResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return this.mCustomDeserializers;
    }

    public String toString() {
        return "MWOfferExpirationRequest{mHeaderMap=" + this.mHeaderMap + ", mBody=" + this.mBody + ", mCustomDeserializers=" + this.mCustomDeserializers + ", mUrl=\"" + this.mUrl + "\"}";
    }
}
