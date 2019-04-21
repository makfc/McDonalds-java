package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWFavoriteLocationsResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import java.util.List;
import java.util.Map;

public class MWGetFavoriteLocationsRequest extends MWRequest<MWFavoriteLocationsResponse, Void> {
    private static final String URL_PATH = "/customer/favorite/location";
    private final MWRequestHeaders mHeaderMap;
    private final MWGETQueryArgs mQueryArgs;

    @Deprecated
    public MWGetFavoriteLocationsRequest(MiddlewareConnector ignored, String ecpToken, String username) {
        this(ecpToken, username);
    }

    public MWGetFavoriteLocationsRequest(String ecpToken, String username) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mHeaderMap.put(TransactionStateUtil.CONTENT_TYPE_HEADER, "text/json");
        this.mQueryArgs = new MWGETQueryArgs();
        this.mQueryArgs.put("userName", username);
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

    public Class<MWFavoriteLocationsResponse> getResponseClass() {
        return MWFavoriteLocationsResponse.class;
    }

    public List<CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }
}
