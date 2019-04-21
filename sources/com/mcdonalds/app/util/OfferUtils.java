package com.mcdonalds.app.util;

import com.ensighten.Ensighten;
import com.mcdonalds.sdk.modules.models.CustomerOrder;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.AppParameters;
import java.util.ArrayList;
import java.util.List;

public class OfferUtils {
    public static List<CustomerOrder> filterIfFinalized(List<CustomerOrder> customerOrders) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.OfferUtils", "filterIfFinalized", new Object[]{customerOrders});
        boolean recentOrderIfFinalized = AppParameters.getBooleanForParameter(AppParameters.RECENT_ORDER_IF_FINALIZED);
        List<CustomerOrder> recentOrders = new ArrayList();
        if (!recentOrderIfFinalized) {
            return customerOrders;
        }
        for (CustomerOrder lastOrder : customerOrders) {
            if (lastOrder.isFinalized()) {
                recentOrders.add(lastOrder);
            }
        }
        return recentOrders;
    }

    public static boolean checkStore(Offer offer) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.OfferUtils", "checkStore", new Object[]{offer});
        Store store = OrderManager.getInstance().getCurrentStore();
        if ((store == null || !offer.getRestaurants().contains(Integer.valueOf(store.getStoreId()))) && !offer.getRestaurants().isEmpty()) {
            return false;
        }
        return true;
    }
}
