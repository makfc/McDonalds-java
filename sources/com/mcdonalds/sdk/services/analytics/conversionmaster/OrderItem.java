package com.mcdonalds.sdk.services.analytics.conversionmaster;

public class OrderItem {
    private String mOrderItemCategory;
    private int mOrderItemCount;
    private String mOrderItemId;
    private String mOrderItemName;
    private String mOrderUnitPrice;

    public OrderItem(String orderItemCategory, String orderItemId, String orderItemName, String orderUnitPrice, int orderItemCount) {
        this.mOrderItemCategory = orderItemCategory;
        this.mOrderItemId = orderItemId;
        this.mOrderItemName = orderItemName;
        this.mOrderUnitPrice = orderUnitPrice;
        this.mOrderItemCount = orderItemCount;
    }

    public String getOrderItemCategory() {
        return this.mOrderItemCategory;
    }

    public String getOrderItemId() {
        return this.mOrderItemId;
    }

    public String getOrderItemName() {
        return this.mOrderItemName;
    }

    public String getOrderUnitPrice() {
        return this.mOrderUnitPrice;
    }

    public int getOrderItemCount() {
        return this.mOrderItemCount;
    }
}
