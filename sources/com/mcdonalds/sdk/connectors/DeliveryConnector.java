package com.mcdonalds.sdk.connectors;

import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.modules.storelocator.Store;
import java.util.Date;

public interface DeliveryConnector {
    AsyncToken getDeliveryStore(CustomerAddress customerAddress, String str, Date date, AsyncListener<Store> asyncListener);

    AsyncToken getDeliveryStore(CustomerAddress customerAddress, String str, Date date, boolean z, AsyncListener<Store> asyncListener);
}
