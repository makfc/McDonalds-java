package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.deserializer.ISO8601DateDeserializer;
import com.mcdonalds.sdk.connectors.middleware.deserializer.MWPointOfDistributionTypeAdapter;
import com.mcdonalds.sdk.connectors.middleware.deserializer.MWPriceTypeAdapter;
import com.mcdonalds.sdk.connectors.middleware.model.MWOrderView;
import com.mcdonalds.sdk.connectors.middleware.response.MWValidateOrderResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import com.mcdonalds.sdk.utils.DateUtils;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MWValidateOrderRequest extends MWRequest<MWValidateOrderResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/order/validation";
    private final MWRequestHeaders mHeaderMap;
    private final MWJSONRequestBody mPostBody;

    @Deprecated
    public MWValidateOrderRequest(MiddlewareConnector ignored, String ecpToken, String username, String storeId, String emailAddress, int tenderType, double tenderAmount, Date deliveryTime, int addressType, MWOrderView orderView) {
        this(ecpToken, username, storeId, emailAddress, tenderType, tenderAmount, deliveryTime, true, addressType, orderView);
    }

    public MWValidateOrderRequest(String ecpToken, String username, String storeId, String emailAddress, int tenderType, double tenderAmount, Date deliveryTime, boolean isNormalOrder, int addressType, MWOrderView orderView) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mPostBody = new MWJSONRequestBody(false);
        String deliveryTimeString = DateUtils.formatToISO8631(deliveryTime, true);
        this.mPostBody.put("userName", username);
        this.mPostBody.put("storeId", storeId);
        this.mPostBody.put("notificationEmail", emailAddress);
        this.mPostBody.put("requestedDeliveryTime", deliveryTimeString);
        this.mPostBody.put("addressType", Integer.valueOf(addressType));
        this.mPostBody.put("isNormalOrder", Boolean.valueOf(isNormalOrder));
        this.mPostBody.put("isPaidOrder", Boolean.valueOf(true));
        this.mPostBody.put("tenderType", Integer.valueOf(tenderType));
        this.mPostBody.put("tenderAmount", Double.valueOf(tenderAmount));
        this.mPostBody.put("orderView", orderView);
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
        return this.mPostBody.toJson(getCustomTypeAdapters());
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWValidateOrderResponse> getResponseClass() {
        return MWValidateOrderResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return Arrays.asList(new CustomTypeAdapter[]{new ISO8601DateDeserializer(), new MWPriceTypeAdapter(), new MWPointOfDistributionTypeAdapter()});
    }

    public String toString() {
        return "MWValidateOrderRequest{mHeaderMap=" + this.mHeaderMap + ", mPostBody=" + getBody() + "}";
    }
}
