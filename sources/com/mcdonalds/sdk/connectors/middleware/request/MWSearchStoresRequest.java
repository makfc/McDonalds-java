package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Parameters;
import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetStoresByLocationResponse;
import com.mcdonalds.sdk.modules.models.Facility;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWSearchStoresRequest extends MWRequest<MWGetStoresByLocationResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/restaurant/search";
    private final MWRequestHeaders mHeaderMap;
    private final MWJSONRequestBody mPostBody;

    @Deprecated
    public MWSearchStoresRequest(MiddlewareConnector ignored, String ecpToken, String searchString, Integer count, List<Integer> facilities) {
        this(ecpToken, searchString, count, facilities);
    }

    public MWSearchStoresRequest(String ecpToken, String searchString, Integer count, List<Integer> facilities) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mPostBody = new MWJSONRequestBody();
        this.mPostBody.put(Parameters.FILTER, searchString);
        this.mPostBody.put("numberOfResults", count);
        this.mPostBody.put("offSet", Integer.valueOf(0));
        this.mPostBody.put(Facility.TABLE_NAME, facilities);
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

    public Class<MWGetStoresByLocationResponse> getResponseClass() {
        return MWGetStoresByLocationResponse.class;
    }

    public List<CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWGetStoresByLocationRequest{mHeaderMap=" + this.mHeaderMap + ", mPostBody=" + this.mPostBody + "}";
    }
}
