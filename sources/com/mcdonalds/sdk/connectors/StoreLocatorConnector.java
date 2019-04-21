package com.mcdonalds.sdk.connectors;

import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.storelocator.StoreLocatorConnectorQueryParameters;
import com.mcdonalds.sdk.modules.storelocator.Store;
import java.util.List;

public interface StoreLocatorConnector extends DeliveryConnector {
    AsyncToken requestStoreFilters(AsyncListener<List<String>> asyncListener);

    AsyncToken requestStoreWithId(String str, AsyncListener<List<Store>> asyncListener);

    AsyncToken requestStores(StoreLocatorConnectorQueryParameters storeLocatorConnectorQueryParameters, AsyncListener<List<Store>> asyncListener);

    AsyncToken requestStoresWithIds(List<String> list, AsyncListener<List<Store>> asyncListener);
}
