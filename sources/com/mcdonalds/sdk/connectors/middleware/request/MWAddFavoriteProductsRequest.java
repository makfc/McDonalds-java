package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.model.MWCustomerFavoriteData;
import com.mcdonalds.sdk.connectors.middleware.response.MWAddFavoriteProductsResponse;
import com.mcdonalds.sdk.services.data.provider.Contract.Favorites;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWAddFavoriteProductsRequest extends MWRequest<MWAddFavoriteProductsResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/favorite";
    private final MWJSONRequestBody mBody;
    private final MWRequestHeaders mHeaderMap;

    @Deprecated
    public MWAddFavoriteProductsRequest(MiddlewareConnector ignored, String ecpToken, String username, List<MWCustomerFavoriteData> productsToFavorite) {
        this(ecpToken, username, productsToFavorite);
    }

    public MWAddFavoriteProductsRequest(String ecpToken, String username, List<MWCustomerFavoriteData> productsToFavorite) {
        this.mHeaderMap = getHeaderMap(ecpToken, "happybaby");
        this.mBody = new MWJSONRequestBody(false);
        this.mBody.put(Favorites.PATH, productsToFavorite);
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

    public Class<MWAddFavoriteProductsResponse> getResponseClass() {
        return MWAddFavoriteProductsResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWAddFavoriteProductsRequest{mHeaderMap=" + this.mHeaderMap + ", mBody=" + this.mBody + "}";
    }
}
