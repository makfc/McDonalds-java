package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetStoresByLocationResponse;
import com.mcdonalds.sdk.modules.models.Facility;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWGetStoresByLocationRequest extends MWRequest<MWGetStoresByLocationResponse, Void> {
    private static final String URL_PATH = "/restaurant/location";
    private final MWRequestHeaders mHeaderMap;
    private final MWGETQueryArgs mQueryArgs;

    @Deprecated
    public MWGetStoresByLocationRequest(MiddlewareConnector ignored, String ecpToken, Double latitude, Double longitude, Integer count, List<Integer> facilities) {
        this(ecpToken, latitude, longitude, count, facilities);
    }

    public MWGetStoresByLocationRequest(String ecpToken, Double latitude, Double longitude, Integer count, List<Integer> facilities) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mQueryArgs = new MWGETQueryArgs();
        this.mQueryArgs.put("latitude", latitude);
        this.mQueryArgs.put("longitude", longitude);
        this.mQueryArgs.put("numberOfResults", count);
        this.mQueryArgs.put("offSet", Integer.valueOf(0));
        this.mQueryArgs.put(Facility.TABLE_NAME, facilities);
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

    public void setBody(Void body) {
    }

    public Class<MWGetStoresByLocationResponse> getResponseClass() {
        return MWGetStoresByLocationResponse.class;
    }

    public List<CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWGetStoresByLocationRequest{mHeaderMap=" + this.mHeaderMap + ", mQueryArgs=" + this.mQueryArgs + "}";
    }
}
