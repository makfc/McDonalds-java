package com.mcdonalds.app.ordering.methodselection;

import com.mcdonalds.sdk.modules.storelocator.Store;
import java.util.List;

public interface OrderMethodSelectionView {
    void hideActivityIndicator();

    void save();

    void setAsapDelivery(boolean z);

    void setAsapDeliveryFirst(boolean z);

    void setSaveButtonState(boolean z);

    void showActivityIndicator(int i);

    void showDelivery();

    void showDeliveryTimeSelector();

    void showPickup();

    void updateAsapDeliveryDate(String str);

    void updateScheduledDeliveryDate(String str);

    void updateSelectedAddress(String str);

    void updateSelectedStore(Store store);

    void updateStores(List<Store> list);
}
