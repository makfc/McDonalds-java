package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.model.MWAddressBookEntry;
import com.mcdonalds.sdk.connectors.middleware.response.MWSetDefaultAddressResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWSetDefaultAddressRequest implements RequestProvider<MWSetDefaultAddressResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/address";
    private final MWJSONRequestBody mBody = new MWJSONRequestBody();
    private final MWRequestHeaders mHeaderMap;
    private String mUrl;

    public MWSetDefaultAddressRequest(String ecpToken, String username, MWAddressBookEntry customerAddress) {
        this.mHeaderMap = new MWRequestHeaders(ecpToken);
        this.mBody.put("userName", username);
        this.mBody.put("addressBookEntry", customerAddress);
        this.mUrl = MiddlewareConnector.getURLStringForEndpoint(URL_PATH);
    }

    public MethodType getMethodType() {
        return MethodType.POST;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    public String getURLString() {
        return this.mUrl;
    }

    public Map<String, String> getHeaders() {
        return this.mHeaderMap;
    }

    public String getBody() {
        return this.mBody.toJson();
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWSetDefaultAddressResponse> getResponseClass() {
        return MWSetDefaultAddressResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWUpdateDefaultAddressRequest{mHeaderMap=" + this.mHeaderMap + ", mBody=" + this.mBody + ", mUrl=\"" + this.mUrl + "\"}";
    }
}
