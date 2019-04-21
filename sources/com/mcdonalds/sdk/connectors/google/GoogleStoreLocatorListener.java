package com.mcdonalds.sdk.connectors.google;

import android.content.Context;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.storelocator.Store;
import java.util.ArrayList;
import java.util.List;

class GoogleStoreLocatorListener implements AsyncListener<GoogleStoreResponse> {
    private final Context mContext;
    private AsyncListener<List<Store>> mListener;
    private AsyncToken mToken;

    GoogleStoreLocatorListener(Context context, AsyncToken token, AsyncListener<List<Store>> listener) {
        this.mContext = context;
        this.mToken = token;
        this.mListener = listener;
    }

    public void onResponse(GoogleStoreResponse response, AsyncToken token, AsyncException exception) {
        if (exception != null) {
            this.mListener.onResponse(null, this.mToken, exception);
            return;
        }
        List<Store> stores = new ArrayList();
        if (response.isSingleStore()) {
            stores.add(GoogleConnector.googleStoreToStore(response, this.mContext));
        } else {
            List<GoogleStore> googleStores = response.getGoogleStores();
            int size = googleStores.size();
            for (int i = 0; i < size; i++) {
                stores.add(GoogleConnector.googleStoreToStore((GoogleStore) googleStores.get(i), this.mContext));
            }
        }
        this.mListener.onResponse(stores, this.mToken, null);
    }
}
