package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWFavoriteLocationsResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWDeleteFavoriteLocationsRequest extends MWRequest<MWFavoriteLocationsResponse, Void> {
    private static final String URL_PATH = "/customer/favorite/location";
    private final MWRequestHeaders mHeaderMap;
    private final MWGETQueryArgs mQueryArgs;

    @Deprecated
    public MWDeleteFavoriteLocationsRequest(MiddlewareConnector ignored, String ecpToken, String username, List<Integer> locationIds) {
        this(ecpToken, username, locationIds);
    }

    public MWDeleteFavoriteLocationsRequest(String ecpToken, String username, List<Integer> locationIds) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mQueryArgs = new MWGETQueryArgs();
        this.mQueryArgs.put("userName", username);
        this.mQueryArgs.put("locationIds", locationIds);
    }

    public MethodType getMethodType() {
        return MethodType.DELETE;
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

    /* Access modifiers changed, original: 0000 */
    public String getQueryString() {
        return this.mQueryArgs.toString();
    }

    public String getBody() {
        return null;
    }

    public void setBody(Void body) {
    }

    public Class<MWFavoriteLocationsResponse> getResponseClass() {
        return MWFavoriteLocationsResponse.class;
    }

    public List<CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }
}
