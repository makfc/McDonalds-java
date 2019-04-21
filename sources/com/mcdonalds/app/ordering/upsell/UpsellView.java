package com.mcdonalds.app.ordering.upsell;

import com.mcdonalds.sdk.modules.models.Order.PriceType;
import com.mcdonalds.sdk.modules.models.Product;
import java.util.List;

public interface UpsellView {
    void displayItems(List<Product> list, PriceType priceType);

    void markItemSelected(int i);

    void markItemUnselected(int i);

    void showCart();

    void updateItemQuantity(int i, int i2);
}
