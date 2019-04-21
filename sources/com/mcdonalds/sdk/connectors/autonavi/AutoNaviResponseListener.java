package com.mcdonalds.sdk.connectors.autonavi;

import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.storelocator.Store;
import java.util.ArrayList;
import java.util.List;

public class AutoNaviResponseListener implements AsyncListener<AutoNaviResponse> {
    private AsyncListener<List<Store>> mStoresListener;
    private AsyncToken mToken;

    public AutoNaviResponseListener(AsyncToken token, AsyncListener<List<Store>> storesListener) {
        this.mStoresListener = storesListener;
        this.mToken = token;
    }

    public void onResponse(AutoNaviResponse response, AsyncToken token, AsyncException exception) {
        if (exception != null) {
            this.mStoresListener.onResponse(null, this.mToken, exception);
            return;
        }
        List<Store> stores = new ArrayList();
        List<AutoNaviStore> autoNaviStores = response.getStores();
        if (autoNaviStores != null) {
            int size = autoNaviStores.size();
            for (int i = 0; i < size; i++) {
                stores.add(((AutoNaviStore) autoNaviStores.get(i)).toStore());
            }
        }
        this.mStoresListener.onResponse(stores, this.mToken, null);
    }
}
