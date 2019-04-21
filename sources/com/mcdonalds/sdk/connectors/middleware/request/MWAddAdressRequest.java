package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.model.MWAddressBookEntry;
import com.mcdonalds.sdk.connectors.middleware.response.MWAddAddressResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWAddAdressRequest extends MWRequest<MWAddAddressResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/address";
    private final MWJSONRequestBody mBody;
    private final MWRequestHeaders mHeaderMap;

    @Deprecated
    public MWAddAdressRequest(MiddlewareConnector ignored, String ecpToken, String username, MWAddressBookEntry customerAddress) {
        this(ecpToken, username, customerAddress);
    }

    public MWAddAdressRequest(String ecpToken, String username, MWAddressBookEntry customerAddress) {
        this.mBody = new MWJSONRequestBody();
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mBody.put("userName", username);
        this.mBody.put("addressBookEntry", customerAddress);
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

    public Class<MWAddAddressResponse> getResponseClass() {
        return MWAddAddressResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWUpdateAddressBookRequest{mHeaderMap=" + this.mHeaderMap + ", mBody=" + this.mBody + "}";
    }
}
