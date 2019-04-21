package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.OrderView;
import com.mcdonalds.sdk.modules.models.PaymentCard;
import com.mcdonalds.sdk.modules.models.ProductView;
import com.mcdonalds.sdk.modules.models.PromotionView;
import com.mcdonalds.sdk.utils.DateUtils;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MWDeliveryOrderViewResult {
    @SerializedName("CouponValue")
    public double couponValue;
    @SerializedName("SuggestedDeliveryFee")
    public MWDeliveryFee deliveryFee;
    @SerializedName("Deposits")
    public List<MWDeposit> deposits;
    @SerializedName("EstimatedDeliveryTime")
    public String estimatedDeliveryTime;
    @SerializedName("ConfirmationNeeded")
    public boolean isConfirmationNeeded;
    @SerializedName("IsEDTCalculationEnabled")
    public boolean isEDTCalculationEnabled;
    @SerializedName("IsLargeOrder")
    public boolean isLargeOrder;
    @SerializedName("AreMultiRidersAllowed")
    public boolean isMultiRidersAllowed;
    @SerializedName("IsPaidOrder")
    public boolean isPaidOrder;
    @SerializedName("NickName")
    public String nickName;
    @SerializedName("OrderDateInStoreLocalTime")
    public String orderDate;
    @SerializedName("OrderDate")
    public String orderDt;
    @SerializedName("OrderNumber")
    public String orderNumber;
    @SerializedName("OrderPaymentId")
    public String orderPaymentId;
    @SerializedName("OrderStatus")
    public int orderStatus;
    @SerializedName("OrderValue")
    public double orderValue;
    @SerializedName("Payments")
    public List<MWOrderPaymentResult> payments;
    @SerializedName("ProductionResponse")
    public MWOrderProductionResponse productionResponse;
    @SerializedName("Products")
    public List<MWProductView> products;
    @SerializedName("PromotionListView")
    public List<MWPromotionView> promotions;
    @SerializedName("RestaurantData")
    public MWRestaurant restaurant;
    @SerializedName("StoreId")
    public String storeID;
    @SerializedName("TenderType")
    public Byte tenderType;
    @SerializedName("TotalDiscount")
    public double totalDiscount;
    @SerializedName("TotalDue")
    public double totalDue;
    @SerializedName("TotalEnergy")
    public int totalEnergy;
    @SerializedName("TotalTax")
    public double totalTax;
    @SerializedName("TotalValue")
    public double totalValue;

    public static OrderView toOrderView(MWDeliveryOrderViewResult ecpOrderViewResult) {
        int i;
        OrderView order = new OrderView();
        order.setStoreID(ecpOrderViewResult.storeID);
        order.setNickName(ecpOrderViewResult.nickName);
        order.setOrderNumber(ecpOrderViewResult.orderNumber);
        order.setOrderValue(Double.valueOf(ecpOrderViewResult.orderValue));
        order.setTotalValue(Double.valueOf(ecpOrderViewResult.totalValue));
        order.setTotalTax(Double.valueOf(ecpOrderViewResult.totalTax));
        order.setTotalEnergy(Integer.valueOf(ecpOrderViewResult.totalEnergy));
        order.setTotalDiscount(Double.valueOf(ecpOrderViewResult.totalDiscount));
        order.setTotalDue(Double.valueOf(ecpOrderViewResult.totalDue));
        if (ecpOrderViewResult.deliveryFee != null) {
            order.setDeliveryFee(Double.valueOf(Double.parseDouble(ecpOrderViewResult.deliveryFee.price)));
        }
        if (ecpOrderViewResult.productionResponse != null) {
            order.setOrderProductionResponse(MWOrderProductionResponse.toOrderProductionResponse(ecpOrderViewResult.productionResponse));
        }
        List<ProductView> productList = new ArrayList();
        for (i = 0; i < ecpOrderViewResult.products.size(); i++) {
            productList.add(MWProductView.toProductView((MWProductView) ecpOrderViewResult.products.get(i)));
        }
        order.setProducts(productList);
        List<PromotionView> promotionsList = new ArrayList();
        if (ecpOrderViewResult.promotions != null) {
            for (i = 0; i < ecpOrderViewResult.promotions.size(); i++) {
                promotionsList.add(MWPromotionView.toPromotionView((MWPromotionView) ecpOrderViewResult.promotions.get(i)));
            }
        }
        order.setPromotions(promotionsList);
        if (ecpOrderViewResult.payments != null) {
            Iterator it = ecpOrderViewResult.payments.iterator();
            if (it.hasNext()) {
                MWOrderPaymentResult mwOrderPaymentResult = (MWOrderPaymentResult) it.next();
                PaymentCard paymentCard = new PaymentCard();
                paymentCard.setAlias(mwOrderPaymentResult.cardAlias);
                paymentCard.setExpiration(mwOrderPaymentResult.cardExpiration);
                paymentCard.setHolderName(mwOrderPaymentResult.cardHolderName);
                paymentCard.setIsPreferred(Boolean.valueOf(mwOrderPaymentResult.isPreferred));
                paymentCard.setIsValid(mwOrderPaymentResult.isValid);
                paymentCard.setNickName(mwOrderPaymentResult.nickName);
                paymentCard.setPaymentMethodId(Integer.valueOf(mwOrderPaymentResult.paymentMethodId));
                order.setPaymentCard(paymentCard);
            }
        }
        try {
            order.setOrderDt(ecpOrderViewResult.orderDt);
            if (ecpOrderViewResult.orderDate != null) {
                order.setOrderDate(DateUtils.parseFromISO8631(ecpOrderViewResult.orderDate, false));
            }
            if (ecpOrderViewResult.estimatedDeliveryTime != null) {
                order.setEstimatedDeliveryTime(DateUtils.parseFromISO8631(ecpOrderViewResult.estimatedDeliveryTime, false));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        order.setIsLargeOrder(ecpOrderViewResult.isLargeOrder);
        order.setConfirmationNeeded(ecpOrderViewResult.isConfirmationNeeded);
        return order;
    }
}
