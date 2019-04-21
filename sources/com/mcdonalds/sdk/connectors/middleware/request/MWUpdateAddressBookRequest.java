package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.model.MWAddressBookEntry;
import com.mcdonalds.sdk.connectors.middleware.response.MWUpdateAddressBookResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWUpdateAddressBookRequest extends MWRequest<MWUpdateAddressBookResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/address/addressBook";
    private final MWJSONRequestBody mBody;
    private final MWRequestHeaders mHeaderMap;

    @Deprecated
    public MWUpdateAddressBookRequest(MiddlewareConnector ignored, String ecpToken, String username, List<MWAddressBookEntry> customerAddresses) {
        this(ecpToken, username, customerAddresses);
    }

    public MWUpdateAddressBookRequest(String ecpToken, String username, List<MWAddressBookEntry> customerAddresses) {
        this.mBody = new MWJSONRequestBody();
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mBody.put("externalMarketCode", this.mBody.get("marketId"));
        this.mBody.put("userName", username);
        this.mBody.put("addresses", customerAddresses);
    }

    public MethodType getMethodType() {
        return MethodType.PUT;
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

    public Class<MWUpdateAddressBookResponse> getResponseClass() {
        return MWUpdateAddressBookResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWUpdateAddressBookRequest{mHeaderMap=" + this.mHeaderMap + ", mBody=" + this.mBody + "}";
    }
}
