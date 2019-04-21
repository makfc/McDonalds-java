package com.mcdonalds.sdk.services.analytics.conversionmaster;

import android.content.Context;
import com.admaster.square.api.ConvMobiSDK;
import com.admaster.square.utils.Order;
import java.util.List;

public class OrderAction implements Action {
    private List<OrderItem> mList;
    private int mOrderAmt;
    private String mOrderId;
    private String mUserId;

    public OrderAction(String userId, String orderId, int orderAmt, List<OrderItem> list) {
        this.mUserId = userId;
        this.mOrderId = orderId;
        this.mOrderAmt = orderAmt;
        this.mList = list;
    }

    public void doAction(Context c) {
        Order order = Order.createOrder(this.mOrderId, (float) this.mOrderAmt, "CNY");
        for (OrderItem item : this.mList) {
            order.addItem(item.getOrderItemCategory(), item.getOrderItemId(), item.getOrderItemName(), item.getOrderUnitPrice(), item.getOrderItemCount());
        }
        ConvMobiSDK.placeOrder(this.mUserId, order);
    }
}
