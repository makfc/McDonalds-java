package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.deserializer.ISO8601DateDeserializer;
import com.mcdonalds.sdk.connectors.middleware.deserializer.MWPointOfDistributionTypeAdapter;
import com.mcdonalds.sdk.connectors.middleware.deserializer.MWPriceTypeAdapter;
import com.mcdonalds.sdk.connectors.middleware.model.MWOrderView;
import com.mcdonalds.sdk.connectors.middleware.model.MWOrderViewInput;
import com.mcdonalds.sdk.connectors.middleware.response.MWDeliveryCheckOutResponse;
import com.mcdonalds.sdk.modules.models.AddressType;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import com.mcdonalds.sdk.utils.DateUtils;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MWDeliveryCheckOutRequest extends MWRequest<MWDeliveryCheckOutResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/order/finalization/delivery";
    private final MWJSONRequestBody mBody;
    private final MWRequestHeaders mHeaderMap;

    @Deprecated
    public MWDeliveryCheckOutRequest(MiddlewareConnector ignored, String ecpToken, String username, String storeId, String notificationEmail, String notificationPhoneNumber, Date requestedDeliveryTime, int addressType, int tenderType, double tenderAmount, MWOrderViewInput orderView) {
        this(ecpToken, username, storeId, notificationEmail, notificationPhoneNumber, requestedDeliveryTime, addressType, tenderType, tenderAmount, orderView);
    }

    @Deprecated
    public MWDeliveryCheckOutRequest(String ecpToken, String username, String storeId, String notificationEmail, String notificationPhoneNumber, Date requestedDeliveryTime, int addressType, int tenderType, double tenderAmount, MWOrderViewInput orderView) {
        this.mBody = new MWJSONRequestBody();
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mBody.put("userName", username);
        this.mBody.put("storeId", storeId);
        this.mBody.put("notificationEmail", notificationEmail);
        this.mBody.put("notificationPhoneNumber", notificationPhoneNumber);
        this.mBody.put("requestedDeliveryTime", requestedDeliveryTime);
        this.mBody.put("addressType", Integer.valueOf(addressType));
        this.mBody.put("orderView", orderView);
        this.mBody.put("cashlessAuthorization", null);
        this.mBody.put("updateDefaultAddress", Boolean.valueOf(true));
        this.mBody.put("recentOrderToSave", null);
        this.mBody.put("tenderType", Integer.valueOf(tenderType));
        this.mBody.put("tenderAmount", Double.valueOf(tenderAmount));
    }

    public MWDeliveryCheckOutRequest(String ecpToken, Order order) {
        this.mBody = new MWJSONRequestBody();
        this.mHeaderMap = getHeaderMap(ecpToken);
        String deliveryTimeString = DateUtils.formatToISO8631(order.getDeliveryDate(), true);
        this.mBody.put("userName", order.getProfile().getUserName());
        this.mBody.put("storeId", order.getStoreId());
        this.mBody.put("notificationEmail", order.getProfile().getEmailAddress());
        this.mBody.put("notificationPhoneNumber", order.getProfile().getMobileNumber());
        this.mBody.put("requestedDeliveryTime", deliveryTimeString);
        this.mBody.put("addressType", Integer.valueOf(Arrays.asList(AddressType.values()).indexOf(order.getDeliveryAddress().getAddressType())));
        this.mBody.put("isNormalOrder", Boolean.valueOf(order.isNormalOrder()));
        this.mBody.put("orderView", MWOrderView.fromOrder(order));
        this.mBody.put("cashlessAuthorization", null);
        this.mBody.put("updateDefaultAddress", Boolean.valueOf(true));
        this.mBody.put("recentOrderToSave", null);
        this.mBody.put("tenderType", Integer.valueOf(order.getTenderType()));
        this.mBody.put("tenderAmount", Double.valueOf(order.getTenderAmount()));
        if (order.invoiceRequested() && order.isOrderRemarkAvailable()) {
            this.mBody.put("orderRemark", order.getInvoiceInfo() + "***" + order.getOrderRemark());
        } else if (order.invoiceRequested()) {
            this.mBody.put("orderRemark", order.getInvoiceInfo());
        } else if (order.isOrderRemarkAvailable()) {
            this.mBody.put("orderRemark", order.getOrderRemarkString());
        }
        if (order.getPaymentResult() != null) {
            this.mBody.put("paymentResponse", order.getPaymentResult());
        }
        order.clearPaymentResult();
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

    public Class<MWDeliveryCheckOutResponse> getResponseClass() {
        return MWDeliveryCheckOutResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return Arrays.asList(new CustomTypeAdapter[]{new ISO8601DateDeserializer(), new MWPointOfDistributionTypeAdapter(), new MWPriceTypeAdapter()});
    }

    public String toString() {
        return "MWDeliveryCheckOutResponse{mHeaderMap=" + this.mHeaderMap + ", mBody=" + this.mBody + "}";
    }
}
