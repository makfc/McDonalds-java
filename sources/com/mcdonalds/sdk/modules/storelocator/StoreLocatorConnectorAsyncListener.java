package com.mcdonalds.sdk.modules.storelocator;

import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import java.util.ArrayList;
import java.util.List;

public class StoreLocatorConnectorAsyncListener implements AsyncListener<List<Store>> {
    private List<Store> mCachedObjects;
    private AsyncListener<List<Store>> mListener;
    private StoreLocatorModule mStoreLocatorModule;

    public StoreLocatorConnectorAsyncListener(StoreLocatorModule storeLocatorModule, AsyncListener<List<Store>> listener) {
        this(storeLocatorModule, new ArrayList(), listener);
    }

    public StoreLocatorConnectorAsyncListener(StoreLocatorModule storeLocatorModule, List<Store> cachedObjects, AsyncListener<List<Store>> listener) {
        this.mStoreLocatorModule = storeLocatorModule;
        this.mCachedObjects = cachedObjects;
        this.mListener = listener;
    }

    public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
        AsyncToken moduleToken = this.mStoreLocatorModule.moduleToken(token);
        if (exception != null) {
            this.mListener.onResponse(null, moduleToken, exception);
        } else if (response != null) {
            this.mStoreLocatorModule.cacheStores(response);
            if (this.mCachedObjects == null) {
                this.mCachedObjects = new ArrayList();
            }
            this.mCachedObjects.addAll(response);
            this.mListener.onResponse(this.mCachedObjects, moduleToken, null);
        } else {
            this.mListener.onResponse(null, moduleToken, null);
        }
        this.mStoreLocatorModule.removeToken(token);
    }
}
