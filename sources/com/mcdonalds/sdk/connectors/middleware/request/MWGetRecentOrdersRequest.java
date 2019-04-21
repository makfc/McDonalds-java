package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetRecentOrdersResponse;
import com.mcdonalds.sdk.connectors.utils.CustomDateDeserializer;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MWGetRecentOrdersRequest extends MWRequest<MWGetRecentOrdersResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/recentOrder";
    private final MWRequestHeaders mHeaderMap;
    private MWGETQueryArgs mQueryArgs;

    @Deprecated
    public MWGetRecentOrdersRequest(MiddlewareConnector ignored, String ecpToken, String userName, Integer numberOfRecents) {
        this(ecpToken, userName, numberOfRecents);
    }

    public MWGetRecentOrdersRequest(String ecpToken, String userName, Integer numberOfRecents) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mQueryArgs = new MWGETQueryArgs();
        this.mQueryArgs.put("userName", userName);
        this.mQueryArgs.put("numberOfRecents", numberOfRecents);
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

    public Class<MWGetRecentOrdersResponse> getResponseClass() {
        return MWGetRecentOrdersResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return Collections.singletonList(new CustomDateDeserializer());
    }
}
