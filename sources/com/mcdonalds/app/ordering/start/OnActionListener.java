package com.mcdonalds.app.ordering.start;

public interface OnActionListener {
    void onDisplayDeliveryAddresses();

    void onDisplayDeliveryDateTime();

    void onDisplayPickupFindAnotherStore();

    void onSelectedStoreChanged();
}
