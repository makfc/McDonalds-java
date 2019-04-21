package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.OrderView;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.ArrayList;
import java.util.List;

public class MWOrderView {
    @SerializedName("AdditionalPayments")
    public List<MWOrderPayment> additionalPayments;
    @SerializedName("CouponValue")
    public double couponValue;
    @SerializedName("DeliveryFee")
    public MWDeliveryFee deliveryFee;
    @SerializedName("Deposits")
    public List<MWDeposit> deposits;
    @SerializedName("EstimatedDeliveryTime")
    public String estimatedDeliveryTime;
    @SerializedName("Fees")
    public List<MWFee> fees;
    @SerializedName("ConfirmationNeeded")
    public boolean isConfirmationNeeded;
    @SerializedName("IsEDTCalculationEnabled")
    public boolean isEDTCalculationEnabled;
    @SerializedName("IsLargeOrder")
    public boolean isLargeOrder;
    @SerializedName("IsNormalOrder")
    public boolean isNormalOrder;
    @SerializedName("LanguageName")
    public String language;
    @SerializedName("Market")
    public String market;
    @SerializedName("NickName")
    public String nickName;
    @SerializedName("OrderNumber")
    public String orderNumber;
    @SerializedName("TableService")
    public MWOrderTableService orderTableService;
    @SerializedName("OrderValue")
    public double orderValue;
    @SerializedName("Payment")
    public MWOrderPayment payment;
    @SerializedName("PriceType")
    public MWPriceType priceType;
    @SerializedName("Products")
    public List<MWProductView> products;
    @SerializedName("PromotionListView")
    public List<MWPromotionView> promotions;
    @SerializedName("StoreID")
    public String storeID;
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
    @SerializedName("UserName")
    public String userName;

    public static OrderView toOrderView(MWOrderView ecpOrderViewResult) {
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
        return order;
    }

    public static MWOrderView fromOrder(Order order, boolean includePayment) {
        MWOrderView orderView = new MWOrderView();
        orderView.products = new ArrayList();
        if (order.getProducts() != null && order.getProducts().size() > 0) {
            for (OrderProduct orderProduct : order.getProducts()) {
                MWProductView productView = new MWProductView();
                productView.populateWithOrder(orderProduct);
                orderView.products.add(productView);
            }
        }
        orderView.promotions = new ArrayList();
        if (order.getOffers() != null && order.getOffers().size() > 0) {
            orderView.promotions = new ArrayList();
            for (OrderOffer offer : order.getOffers()) {
                orderView.promotions.add(MWPromotionView.fromOrderOffer(offer));
            }
        }
        orderView.language = Configuration.getSharedInstance().getCurrentLanguageTag();
        orderView.market = Configuration.getSharedInstance().getStringForKey("connectors.Middleware.marketId");
        orderView.nickName = order.getProfile().getNickName() == null ? "" : order.getProfile().getNickName();
        orderView.userName = order.getProfile().getUserName();
        orderView.storeID = order.getStoreId();
        orderView.isNormalOrder = order.isNormalOrder();
        orderView.priceType = order.getPriceType() == null ? MWPriceType.EatIn : MWPriceType.fromOrderPriceType(order.getPriceType());
        if (!includePayment || order.getPayment() == null) {
            orderView.payment = null;
        } else {
            orderView.payment = MWOrderPayment.fromOrderPayment(order.getPayment());
        }
        if (order.getOrderTableService() != null) {
            orderView.orderTableService = MWOrderTableService.fromOrderTableService(order.getOrderTableService());
        } else {
            orderView.orderTableService = null;
        }
        return orderView;
    }

    public static MWOrderView fromOrder(Order order) {
        return fromOrder(order, true);
    }
}
