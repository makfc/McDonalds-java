package com.mcdonalds.app.util;

import android.util.SparseArray;
import com.ensighten.Ensighten;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.StoreFavoriteInfo;
import com.mcdonalds.sdk.modules.storelocator.Store;
import java.util.ArrayList;
import java.util.List;

public class FavoriteStoreUtils {

    /* renamed from: com.mcdonalds.app.util.FavoriteStoreUtils$1 */
    static class C38521 implements AsyncListener<List<Store>> {
        C38521() {
        }

        public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
        }
    }

    public static List<Store> checkIfFavoriteStoresAreOpen(SparseArray<StoreFavoriteInfo> favoriteInfoSparseArray, List<Store> stores) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.FavoriteStoreUtils", "checkIfFavoriteStoresAreOpen", new Object[]{favoriteInfoSparseArray, stores});
        List<Integer> deleteFavoriteStoreIds = new ArrayList();
        List<Integer> availableFavoriteStoreIds = new ArrayList();
        List<Store> availableStoresList = new ArrayList();
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        if (!ConfigurationUtils.shouldFilterStoreResultsUsingGeneralStatus()) {
            return stores;
        }
        for (Store store : stores) {
            if (store.isGeneralStatusIsOpen()) {
                availableFavoriteStoreIds.add(Integer.valueOf(store.getStoreId()));
                availableStoresList.add(store);
            } else {
                deleteFavoriteStoreIds.add(Integer.valueOf(store.getStoreId()));
                availableStoresList.remove(store);
            }
        }
        if (stores.size() != favoriteInfoSparseArray.size()) {
            for (int i = 0; i < favoriteInfoSparseArray.size(); i++) {
                StoreFavoriteInfo storeFavoriteInfo = (StoreFavoriteInfo) favoriteInfoSparseArray.valueAt(i);
                if (!(availableFavoriteStoreIds.contains(Integer.valueOf(storeFavoriteInfo.getStoreId())) || deleteFavoriteStoreIds.contains(Integer.valueOf(storeFavoriteInfo.getStoreId())))) {
                    deleteFavoriteStoreIds.add(Integer.valueOf(storeFavoriteInfo.getStoreId()));
                }
            }
        }
        if (deleteFavoriteStoreIds.isEmpty()) {
            return availableStoresList;
        }
        customerModule.deleteFavoriteStores(deleteFavoriteStoreIds, customerModule.getCurrentProfile(), new C38521());
        return availableStoresList;
    }
}
