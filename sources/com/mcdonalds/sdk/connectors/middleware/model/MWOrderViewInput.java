package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import java.util.ArrayList;
import java.util.List;

public class MWOrderViewInput {
    @SerializedName("Payment")
    public MWCheckoutWorkAroundOrderPayment payment;
    @SerializedName("Products")
    public List<MWProductViewInput> products;

    public static MWOrderViewInput fromOrder(Order order) {
        MWOrderViewInput orderView = new MWOrderViewInput();
        if (order.getProducts() != null && order.getProducts().size() > 0) {
            orderView.products = new ArrayList();
            for (OrderProduct orderProduct : order.getProducts()) {
                MWProductViewInput productViewInput = new MWProductViewInput();
                productViewInput.populateWithOrder(orderProduct);
                orderView.products.add(productViewInput);
            }
        }
        if (order.getPayment() != null) {
            orderView.payment = MWCheckoutWorkAroundOrderPayment.fromOrderPayment(order.getPayment());
        } else {
            orderView.payment = null;
        }
        return orderView;
    }
}
