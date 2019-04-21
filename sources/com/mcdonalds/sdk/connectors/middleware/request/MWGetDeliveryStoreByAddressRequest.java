package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.deserializer.ISO8601DateDeserializer;
import com.mcdonalds.sdk.connectors.middleware.model.MWAddressElement;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetDeliveryStoreByAddressResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MWGetDeliveryStoreByAddressRequest extends MWRequest<MWGetDeliveryStoreByAddressResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/restaurant/deliveryLocationByAddress";
    private final MWJSONRequestBody mBody;
    private final MWRequestHeaders mHeaderMap;

    @Deprecated
    public MWGetDeliveryStoreByAddressRequest(MiddlewareConnector ignored, String ecpToken, String username, Date deliveryTime, List<MWAddressElement> addressElements) {
        this(ecpToken, username, deliveryTime, addressElements);
    }

    public MWGetDeliveryStoreByAddressRequest(String ecpToken, String username, Date deliveryTime, List<MWAddressElement> addressElements) {
        this.mBody = new MWJSONRequestBody();
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mBody.put("externalMarketCode", this.mBody.get("marketId"));
        this.mBody.put("userName", username);
        this.mBody.put("deliveryTime", deliveryTime);
        this.mBody.put("addressElements", addressElements);
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
        return this.mBody.toJson(getCustomTypeAdapters());
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWGetDeliveryStoreByAddressResponse> getResponseClass() {
        return MWGetDeliveryStoreByAddressResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return Arrays.asList(new ISO8601DateDeserializer[]{new ISO8601DateDeserializer()});
    }

    public String toString() {
        return "MWGetAddressBookRequest{mHeaderMap=" + this.mHeaderMap + ", mBody=" + this.mBody.toJson(getCustomTypeAdapters()) + "}";
    }
}
