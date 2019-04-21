package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWFavoriteLocationsResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWRenameFavoriteLocationsRequest extends MWRequest<MWFavoriteLocationsResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/favorite/location";
    private final MWRequestHeaders mHeaderMap;
    private final MWJSONRequestBody mPostBody;

    @Deprecated
    public MWRenameFavoriteLocationsRequest(MiddlewareConnector ignored, String ecpToken, String username, List<Map<String, Object>> locations) {
        this(ecpToken, username, locations);
    }

    public MWRenameFavoriteLocationsRequest(String ecpToken, String username, List<Map<String, Object>> locations) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mPostBody = new MWJSONRequestBody();
        this.mPostBody.put("userName", username);
        this.mPostBody.put("locations", locations);
    }

    public MethodType getMethodType() {
        return MethodType.PUT;
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

    public Class<MWFavoriteLocationsResponse> getResponseClass() {
        return MWFavoriteLocationsResponse.class;
    }

    public List<CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }
}
