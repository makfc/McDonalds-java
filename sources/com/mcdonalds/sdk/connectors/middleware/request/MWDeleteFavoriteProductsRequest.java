package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWDeleteFavoriteProductsResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWDeleteFavoriteProductsRequest extends MWRequest<MWDeleteFavoriteProductsResponse, Void> {
    private static final String URL_PATH = "/customer/favorite";
    private final MWRequestHeaders mHeaderMap;
    private final MWGETQueryArgs mQueryArgs;

    @Deprecated
    public MWDeleteFavoriteProductsRequest(MiddlewareConnector ignored, String ecpToken, String username, List<String> productsToDelete) {
        this(ecpToken, username, productsToDelete);
    }

    public MWDeleteFavoriteProductsRequest(String ecpToken, String username, List<String> productsToDelete) {
        this.mHeaderMap = getHeaderMap(ecpToken, "happybaby");
        this.mQueryArgs = new MWGETQueryArgs();
        this.mQueryArgs.put("favoriteIds", productsToDelete);
        this.mQueryArgs.put("userName", username);
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

    public Class<MWDeleteFavoriteProductsResponse> getResponseClass() {
        return MWDeleteFavoriteProductsResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWAddFavoriteProductsRequest{mHeaderMap=" + this.mHeaderMap + ", mQueryARgs=" + this.mQueryArgs + "}";
    }
}
