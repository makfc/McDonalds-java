package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.deserializer.MWPointOfDistributionTypeAdapter;
import com.mcdonalds.sdk.connectors.middleware.deserializer.MWPriceTypeAdapter;
import com.mcdonalds.sdk.connectors.middleware.model.MWOrderView;
import com.mcdonalds.sdk.connectors.middleware.response.MWOrderAddonsResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import com.mcdonalds.sdk.utils.DateUtils;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MWOrderAddonsRequest extends MWRequest<MWOrderAddonsResponse, MWJSONRequestBody> {
    private static final String DELIVERY_URL_PATH = "/order/finalization/delivery/addons";
    private static final String URL_PATH = "/order/finalization/pickup/addons";
    private String mEndpoint;
    private final MWRequestHeaders mHeaderMap;
    protected final MWJSONRequestBody mPostBody = new MWJSONRequestBody(false);

    public MWOrderAddonsRequest(String ecpToken, String username, String storeId, boolean isDelivery, Date deliveryTime, boolean isNormalOrder, MWOrderView orderView) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        String deliveryTimeString = DateUtils.formatToISO8631(deliveryTime, true);
        this.mPostBody.put("userName", username);
        this.mPostBody.put("storeId", storeId);
        this.mPostBody.put("requestedDeliveryTime", deliveryTimeString);
        this.mPostBody.put("isNormalOrder", Boolean.valueOf(isNormalOrder));
        this.mPostBody.put("orderView", orderView);
        if (isDelivery) {
            this.mEndpoint = DELIVERY_URL_PATH;
        } else {
            this.mEndpoint = URL_PATH;
        }
    }

    public MethodType getMethodType() {
        return MethodType.POST;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    /* Access modifiers changed, original: 0000 */
    public String getEndpoint() {
        return this.mEndpoint;
    }

    public Map<String, String> getHeaders() {
        return this.mHeaderMap;
    }

    public String getBody() {
        return this.mPostBody.toJson(getCustomTypeAdapters());
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWOrderAddonsResponse> getResponseClass() {
        return MWOrderAddonsResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return Arrays.asList(new CustomTypeAdapter[]{new MWPriceTypeAdapter(), new MWPointOfDistributionTypeAdapter()});
    }

    public String toString() {
        return "MWTotalizeOrderRequest{mHeaderMap=" + this.mHeaderMap + ", mPostBody=" + getBody() + ", mEndpoint=\"" + this.mEndpoint + "\"}";
    }
}
