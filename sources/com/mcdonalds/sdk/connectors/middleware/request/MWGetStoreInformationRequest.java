package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetStoreInformationResponse;
import com.mcdonalds.sdk.services.network.CatalogVersionTypeTypeAdapter;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MWGetStoreInformationRequest extends MWRequest<MWGetStoreInformationResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "restaurant/information";
    private final MWRequestHeaders mHeaderMap;
    private final MWGETQueryArgs mQueryArgs;

    @Deprecated
    public MWGetStoreInformationRequest(MiddlewareConnector ignored, String ecpToken, Integer storeNumber) {
        this(ecpToken, storeNumber);
    }

    public MWGetStoreInformationRequest(String ecpToken, Integer storeNumber) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mQueryArgs = new MWGETQueryArgs();
        this.mQueryArgs.put("storeNumber", storeNumber);
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

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWGetStoreInformationResponse> getResponseClass() {
        return MWGetStoreInformationResponse.class;
    }

    public List<CustomTypeAdapter> getCustomTypeAdapters() {
        return Arrays.asList(new CustomTypeAdapter[]{new CatalogVersionTypeTypeAdapter()});
    }
}
