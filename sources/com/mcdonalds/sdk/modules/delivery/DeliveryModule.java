package com.mcdonalds.sdk.modules.delivery;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.ConnectorManager;
import com.mcdonalds.sdk.connectors.DeliveryConnector;
import com.mcdonalds.sdk.connectors.OrderingConnector;
import com.mcdonalds.sdk.modules.BaseModule;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.modules.models.DeliveryStatusResponse;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderResponse;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.sync.SyncAdapter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeliveryModule extends BaseModule {
    public static final String CONNECTOR_KEY = "connector";
    public static final String NAME = "delivery";
    public static final String ORDERING_NAME = "ordering";
    private final String mDeliveryConnectorImpl;
    private boolean mNeedsToUpdateDeliveryTracking;
    private final String mOrderingConnectorImpl;
    private final Map<String, DeliveryStatusResponse> mScheduledOrders;

    @Deprecated
    public DeliveryModule(Context context) {
        this();
    }

    public DeliveryModule() {
        this.mNeedsToUpdateDeliveryTracking = true;
        this.mOrderingConnectorImpl = (String) Configuration.getSharedInstance().getValueForKey("modules.ordering.connector");
        this.mDeliveryConnectorImpl = (String) Configuration.getSharedInstance().getValueForKey("modules.delivery.connector");
        this.mScheduledOrders = new HashMap();
    }

    private Order getCurrentOrder() {
        return ModuleManager.getSharedInstance().getCurrentOrder();
    }

    public AsyncToken getDeliveryStore(CustomerAddress deliveryAddress, Date deliveryTime, CustomerProfile profile, @NonNull AsyncListener<Store> listener) {
        return getDeliveryStore(deliveryAddress, deliveryTime, profile, OrderManager.getInstance().getCurrentOrder().isNormalOrder(), listener);
    }

    public AsyncToken getDeliveryStore(CustomerAddress deliveryAddress, Date deliveryTime, CustomerProfile profile, boolean isNormalOrder, @NonNull final AsyncListener<Store> listener) {
        final AsyncToken moduleToken = new AsyncToken("getDeliveryStore");
        ((DeliveryConnector) ConnectorManager.getConnector(this.mDeliveryConnectorImpl)).getDeliveryStore(deliveryAddress, profile.getUserName(), deliveryTime, isNormalOrder, new AsyncListener<Store>() {
            public void onResponse(Store response, AsyncToken token, AsyncException exception) {
                if (response != null) {
                    DeliveryModule.this.requestSync(response);
                }
                listener.onResponse(response, moduleToken, exception);
            }
        });
        return moduleToken;
    }

    private void requestSync(Store store) {
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean("force", true);
        settingsBundle.putBoolean("expedited", true);
        settingsBundle.putInt(SyncAdapter.SYNC_OPTION_STORE_ID, store.getStoreId());
        ContentResolver.requestSync(((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getSyncAccount(), "com.mcdonalds.gma.hongkong.provider", settingsBundle);
    }

    public AsyncToken validate(@NonNull final AsyncListener<OrderResponse> requestListener) {
        final AsyncToken moduleToken = new AsyncToken("validate");
        ((OrderingConnector) ConnectorManager.getConnector(this.mOrderingConnectorImpl)).validate(getCurrentOrder(), new AsyncListener<OrderResponse>() {
            public void onResponse(OrderResponse response, AsyncToken token, AsyncException exception) {
                requestListener.onResponse(response, moduleToken, exception);
            }
        });
        return moduleToken;
    }

    public AsyncToken lookupDeliveryCharge(Order order, @NonNull final AsyncListener<OrderResponse> requestListener) {
        final AsyncToken moduleToken = new AsyncToken("lookupDeliveryCharge");
        ((OrderingConnector) ConnectorManager.getConnector(this.mOrderingConnectorImpl)).lookupDeliveryCharge(order, new AsyncListener<OrderResponse>() {
            public void onResponse(OrderResponse response, AsyncToken token, AsyncException exception) {
                requestListener.onResponse(response, moduleToken, exception);
            }
        });
        return moduleToken;
    }

    public AsyncToken checkout(Order order, String password, CustomerAddress address, @NonNull final AsyncListener<OrderResponse> requestListener) {
        final AsyncToken moduleToken = new AsyncToken(JiceArgs.EVENT_CHECKOUT);
        ((OrderingConnector) ConnectorManager.getConnector(this.mOrderingConnectorImpl)).checkout(order, password, address, new AsyncListener<OrderResponse>() {
            public void onResponse(OrderResponse response, AsyncToken token, AsyncException exception) {
                requestListener.onResponse(response, moduleToken, exception);
            }
        });
        return moduleToken;
    }

    public boolean needsToUpdateDeliveryTracking() {
        return this.mNeedsToUpdateDeliveryTracking;
    }

    public void setNeedsToUpdateDeliveryTracking(boolean needsToUpdateDeliveryTracking) {
        this.mNeedsToUpdateDeliveryTracking = needsToUpdateDeliveryTracking;
    }

    public AsyncToken getDeliveryStatus(final String orderNumber, @NonNull final AsyncListener<DeliveryStatusResponse> listener) {
        AsyncToken moduleToken = new AsyncToken("getDeliveryStatus");
        if (this.mNeedsToUpdateDeliveryTracking) {
            getDeliveryStatusFromNetwork(new AsyncListener<List<DeliveryStatusResponse>>() {
                public void onResponse(List<DeliveryStatusResponse> list, AsyncToken token, AsyncException exception) {
                    DeliveryModule.this.mNeedsToUpdateDeliveryTracking = false;
                    DeliveryModule.this.getDeliveryStatus(orderNumber, listener);
                }
            });
        } else {
            listener.onResponse((DeliveryStatusResponse) this.mScheduledOrders.get(orderNumber), null, null);
        }
        return moduleToken;
    }

    public AsyncToken getDeliveryStatus(final AsyncListener<List<DeliveryStatusResponse>> listener) {
        AsyncToken moduleToken = new AsyncToken("getDeliveryStatus");
        if (this.mNeedsToUpdateDeliveryTracking) {
            getDeliveryStatusFromNetwork(new AsyncListener<List<DeliveryStatusResponse>>() {
                public void onResponse(List<DeliveryStatusResponse> list, AsyncToken token, AsyncException exception) {
                    DeliveryModule.this.mNeedsToUpdateDeliveryTracking = false;
                    DeliveryModule.this.getDeliveryStatus(listener);
                }
            });
            return moduleToken;
        }
        List<DeliveryStatusResponse> scheduledOrdersList = new ArrayList();
        for (String orderId : this.mScheduledOrders.keySet()) {
            scheduledOrdersList.add(this.mScheduledOrders.get(orderId));
        }
        listener.onResponse(scheduledOrdersList, null, null);
        return null;
    }

    public void clearModuleCacheData() {
        this.mNeedsToUpdateDeliveryTracking = true;
        this.mScheduledOrders.clear();
    }

    private AsyncToken getDeliveryStatusFromNetwork(final AsyncListener<List<DeliveryStatusResponse>> listener) {
        AsyncToken moduleToken = new AsyncToken("getDeliveryStatus");
        ((OrderingConnector) ConnectorManager.getConnector(this.mOrderingConnectorImpl)).getDeliveryStatus(new AsyncListener<List<DeliveryStatusResponse>>() {
            public void onResponse(List<DeliveryStatusResponse> response, AsyncToken token, AsyncException exception) {
                DeliveryModule.this.mScheduledOrders.clear();
                for (DeliveryStatusResponse deliveryStatusResponse : response) {
                    DeliveryModule.this.mScheduledOrders.put(deliveryStatusResponse.getOrderNumber(), deliveryStatusResponse);
                }
                listener.onResponse(response, token, exception);
            }
        });
        return moduleToken;
    }

    public Boolean supportsDayParts() {
        return Boolean.valueOf(true);
    }

    public int getMaxBasketQuantity() {
        return ((OrderingConnector) ConnectorManager.getConnector(this.mOrderingConnectorImpl)).getMaxBasketQuantity();
    }
}
