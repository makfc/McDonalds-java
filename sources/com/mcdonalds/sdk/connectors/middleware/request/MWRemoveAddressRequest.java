package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.model.MWAddressBookEntry;
import com.mcdonalds.sdk.connectors.middleware.response.MWRemoveAddressResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWRemoveAddressRequest extends MWRequest<MWRemoveAddressResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/address";
    private final MWRequestHeaders mHeaderMap;
    private final MWGETQueryArgs mQueryArgs;

    @Deprecated
    public MWRemoveAddressRequest(MiddlewareConnector ignored, String ecpToken, String username, MWAddressBookEntry customerAddress) {
        this(ecpToken, username, customerAddress);
    }

    public MWRemoveAddressRequest(String ecpToken, String username, MWAddressBookEntry customerAddress) {
        this.mQueryArgs = new MWGETQueryArgs();
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mQueryArgs.put("userName", username);
        this.mQueryArgs.put("addressType", Integer.valueOf(customerAddress.addressTypeID));
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

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWRemoveAddressResponse> getResponseClass() {
        return MWRemoveAddressResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWRemoveAddressRequest{mHeaderMap=" + this.mHeaderMap + ", mBody=" + this.mQueryArgs.toString() + "}";
    }
}
