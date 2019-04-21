package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.deserializer.MWPointOfDistributionTypeAdapter;
import com.mcdonalds.sdk.connectors.middleware.deserializer.MWPriceTypeAdapter;
import com.mcdonalds.sdk.connectors.middleware.model.MWOrderView;
import com.mcdonalds.sdk.connectors.middleware.response.MWPreparePaymentResponse;
import com.mcdonalds.sdk.modules.models.AddressType;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import com.mcdonalds.sdk.utils.DateUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MWPreparePaymentForDeliveryRequest extends MWRequest<MWPreparePaymentResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/order/payment/delivery";
    private final MWRequestHeaders mHeaderMap;
    private final MWJSONRequestBody mPostBody;

    @Deprecated
    public MWPreparePaymentForDeliveryRequest(MiddlewareConnector ignored, String ecpToken, String username, String storeId, MWOrderView orderView) {
        this(ecpToken, username, storeId, orderView);
    }

    public MWPreparePaymentForDeliveryRequest(String ecpToken, String username, String storeId, MWOrderView orderView) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mPostBody = new MWJSONRequestBody(false);
        this.mPostBody.put("userName", username);
        this.mPostBody.put("storeId", storeId);
        this.mPostBody.put("orderView", orderView);
    }

    public MWPreparePaymentForDeliveryRequest(String ecpToken, Order order) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        String deliveryTimeString = DateUtils.formatToISO8631(order.getDeliveryDate(), true);
        this.mPostBody = new MWJSONRequestBody(false);
        this.mPostBody.put("userName", order.getProfile().getUserName());
        this.mPostBody.put("storeId", order.getStoreId());
        this.mPostBody.put("orderView", MWOrderView.fromOrder(order));
        this.mPostBody.put("addressType", Integer.valueOf(Arrays.asList(AddressType.values()).indexOf(order.getDeliveryAddress().getAddressType())));
        this.mPostBody.put("isNormalOrder", Boolean.valueOf(order.isNormalOrder()));
        this.mPostBody.put("requestedDeliveryTime", deliveryTimeString);
        this.mPostBody.put("tenderType", Integer.valueOf(order.getTenderType()));
        this.mPostBody.put("tenderAmount", Double.valueOf(order.getTenderAmount()));
        this.mPostBody.put("couponQuantity", Integer.valueOf(0));
        this.mPostBody.put("binNumber", null);
        this.mPostBody.put("orderChannel", "");
        this.mPostBody.put("orderSource", "");
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

    public Class<MWPreparePaymentResponse> getResponseClass() {
        return MWPreparePaymentResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        List<CustomTypeAdapter> adapters = new ArrayList();
        adapters.add(new MWPointOfDistributionTypeAdapter());
        adapters.add(new MWPriceTypeAdapter());
        return adapters;
    }
}
