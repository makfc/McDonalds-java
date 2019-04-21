package com.mcdonalds.app.offers;

import com.mcdonalds.sdk.modules.models.OrderProduct;

public interface OffersListener {
    void onOfferProductSelected(int i, OrderProduct orderProduct);
}
