package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.model.MWCatalogVersion;
import com.mcdonalds.sdk.connectors.middleware.response.MWJSONResponse;
import com.mcdonalds.sdk.connectors.utils.CustomDateDeserializer;
import com.mcdonalds.sdk.services.network.CatalogVersionTypeTypeAdapter;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MWGetCatalogUpdatedRequest extends MWRequest<MWJSONResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/catalog/update";
    private final MWRequestHeaders mHeaderMap;
    private MWJSONRequestBody mPostBody;

    @Deprecated
    public MWGetCatalogUpdatedRequest(MiddlewareConnector ignored, String ecpToken, MWCatalogVersion catalogVersion, String userName) {
        this(ecpToken, catalogVersion, userName);
    }

    public MWGetCatalogUpdatedRequest(String ecpToken, MWCatalogVersion catalogVersion, String userName) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mPostBody = new MWJSONRequestBody();
        this.mPostBody.put("staticDataTypeID", new Integer[]{Integer.valueOf(2), Integer.valueOf(4)});
        if (userName != null) {
            this.mPostBody.put("userName", userName);
        }
        this.mPostBody.put("catalogVersion", catalogVersion);
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
        return this.mPostBody.toJson(Arrays.asList(new CatalogVersionTypeTypeAdapter[]{new CatalogVersionTypeTypeAdapter()}));
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWJSONResponse> getResponseClass() {
        return MWJSONResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return Arrays.asList(new CustomDateDeserializer[]{new CustomDateDeserializer()});
    }
}
