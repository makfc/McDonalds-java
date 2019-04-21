package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWSignInResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWSignOutRequest extends MWRequest<MWSignInResponse, Void> {
    private static final String URL_PATH = "/customer/session";
    private final MWRequestHeaders mHeaderMap;
    private final MWGETQueryArgs mQueryArgs;

    @Deprecated
    public MWSignOutRequest(MiddlewareConnector ignored, String ecpToken) {
        this(ecpToken);
    }

    public MWSignOutRequest(String ecpToken) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mQueryArgs = new MWGETQueryArgs();
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

    public Class<MWSignInResponse> getResponseClass() {
        return MWSignInResponse.class;
    }

    public List<CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }
}
