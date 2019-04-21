package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetAddressBookResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWGetAddressBookRequest extends MWRequest<MWGetAddressBookResponse, Void> {
    private static final String URL_PATH = "/customer/address/addressBook";
    private final MWRequestHeaders mHeaderMap;
    private final MWGETQueryArgs mQueryArgs;

    @Deprecated
    public MWGetAddressBookRequest(MiddlewareConnector ignored, String ecpToken, String username) {
        this(ecpToken, username);
    }

    public MWGetAddressBookRequest(String ecpToken, String username) {
        this.mHeaderMap = getHeaderMap(ecpToken);
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

    public Class<MWGetAddressBookResponse> getResponseClass() {
        return MWGetAddressBookResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWGetAddressBookRequest{mHeaderMap=" + this.mHeaderMap + ", mQueryArgs=" + this.mQueryArgs + "}";
    }
}
