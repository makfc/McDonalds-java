package com.mcdonalds.app.ordering;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.ensighten.Ensighten;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.ServiceUtils;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.utils.Utils;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.delivery.DeliveryModule;
import com.mcdonalds.sdk.modules.models.ExpectedTenderType;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.Order.PriceType;
import com.mcdonalds.sdk.modules.models.OrderPayment;
import com.mcdonalds.sdk.modules.models.OrderResponse;
import com.mcdonalds.sdk.modules.models.OrderView;
import com.mcdonalds.sdk.modules.models.PointOfDistribution;
import com.mcdonalds.sdk.modules.offers.OffersModule;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.security.SecurityModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.configuration.DeliveryConfig;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class OrderingManager {
    private static OrderingManager sInstance;
    private int mProductErrorCode;

    public interface checkInResponseListener {
    }

    static /* synthetic */ void access$000(OrderingManager x0, OrderResponse x1, AsyncToken x2, AsyncException x3, AsyncListener x4) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.OrderingManager", "access$000", new Object[]{x0, x1, x2, x3, x4});
        x0.deliverOrderResponse(x1, x2, x3, x4);
    }

    private OrderingManager() {
    }

    public static OrderingManager getInstance() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.OrderingManager", "getInstance", null);
        if (sInstance == null) {
            sInstance = new OrderingManager();
        }
        return sInstance;
    }

    @NonNull
    public Order getCurrentOrder() {
        Ensighten.evaluateEvent(this, "getCurrentOrder", null);
        return OrderManager.getInstance().getCurrentOrder();
    }

    public PriceType getCurrentOrderPriceType() {
        Ensighten.evaluateEvent(this, "getCurrentOrderPriceType", null);
        return getCurrentOrder().getPriceType();
    }

    public void deleteCurrentOrder() {
        Ensighten.evaluateEvent(this, "deleteCurrentOrder", null);
        updateDeletedOrEmptyOrder();
        OrderManager.getInstance().deleteCurrentOrder();
    }

    private void updateDeletedOrEmptyOrder() {
        Ensighten.evaluateEvent(this, "updateDeletedOrEmptyOrder", null);
        setProductErrorCode(1);
    }

    public void updateCurrentOrder(Order newOrder) {
        Ensighten.evaluateEvent(this, "updateCurrentOrder", new Object[]{newOrder});
        DataLayerManager.getInstance().setTransaction(newOrder, AppUtils.getCurrentMenuType(), ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getCurrentStore());
        OrderManager.getInstance().updateCurrentOrder(newOrder);
    }

    public boolean isOrderingAvailable() {
        Ensighten.evaluateEvent(this, "isOrderingAvailable", null);
        return ModuleManager.getSharedInstance().isOrderingAvailable();
    }

    public void totalize(Store currentStore, AsyncListener<Boolean> requestListener) {
        Ensighten.evaluateEvent(this, "totalize", new Object[]{currentStore, requestListener});
        if (currentStore == null) {
            requestListener.onResponse(Boolean.valueOf(false), null, new AsyncException("You don't have a current store selected."));
        } else if (getCurrentOrder().isDelivery()) {
            totalizeDelivery(requestListener);
        } else {
            totalizePickUp(currentStore, requestListener);
        }
    }

    public void checkIn(@Nullable Order newOrder, @Nullable String code, @Nullable String password, final AsyncListener<OrderResponse> listener) {
        Ensighten.evaluateEvent(this, "checkIn", new Object[]{newOrder, code, password, listener});
        if (ConfigurationUtils.isDuplicateOrderCheckinFlow()) {
            LocalDataManager.getSharedInstance().setCheckinTimeStamp();
        }
        if (newOrder != null) {
            updateCurrentOrder(newOrder);
        }
        final Order order = getCurrentOrder();
        if (order.isDelivery()) {
            order.getPayment().setPOD(PointOfDistribution.Delivery);
            ((DeliveryModule) ModuleManager.getModule(DeliveryModule.NAME)).checkout(order, password, order.getDeliveryAddress(), new AsyncListener<OrderResponse>() {
                public void onResponse(OrderResponse response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    if (ConfigurationUtils.isDuplicateOrderCheckinFlow()) {
                        LocalDataManager.getSharedInstance().deleteObjectFromCache(LocalDataManager.KEY_CHECKIN_TIMER);
                    }
                    order.setCheckoutResult(response);
                    OrderingManager.access$000(OrderingManager.this, response, token, exception, listener);
                }
            });
            return;
        }
        ((OrderingModule) ModuleManager.getModule("ordering")).checkin(code, password, new AsyncListener<OrderResponse>() {
            public void onResponse(OrderResponse response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                if (ConfigurationUtils.isDuplicateOrderCheckinFlow()) {
                    LocalDataManager.getSharedInstance().deleteObjectFromCache(LocalDataManager.KEY_CHECKIN_TIMER);
                }
                order.setCheckinResult(response);
                OrderingManager.access$000(OrderingManager.this, response, token, exception, listener);
            }
        });
    }

    public boolean isLargeOrder(OrderView orderView) {
        Ensighten.evaluateEvent(this, "isLargeOrder", new Object[]{orderView});
        return getCurrentOrder().isDelivery() && orderView.isIsLargeOrder() && orderView.isConfirmationNeeded();
    }

    private void deliverOrderResponse(OrderResponse response, AsyncToken token, AsyncException exception, AsyncListener<OrderResponse> listener) {
        Ensighten.evaluateEvent(this, "deliverOrderResponse", new Object[]{response, token, exception, listener});
        if (exception != null) {
            listener.onResponse(response, token, exception);
            return;
        }
        if (response != null && response.getOrderDate() == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
            response.setOrderDate(calendar.getTime());
        }
        Analytics.trackTransaction(response, getCurrentOrder());
        listener.onResponse(response, token, exception);
        if (ModuleManager.isModuleEnabled(OffersModule.NAME).booleanValue()) {
            ServiceUtils.getSharedInstance().removeOffersCache();
            ServiceUtils.getSharedInstance().removeAdvertisableCache();
        }
    }

    public int getErrorCode() {
        Ensighten.evaluateEvent(this, "getErrorCode", null);
        if (getCurrentOrder().getTotalizeResult() != null) {
            return getCurrentOrder().getTotalizeResult().getErrorCode();
        }
        return 0;
    }

    @NonNull
    public List<String> getProblematicProductsCodes() {
        Ensighten.evaluateEvent(this, "getProblematicProductsCodes", null);
        List<Integer> codes = null;
        switch (getErrorCode()) {
            case OrderResponse.PRODUCT_OUT_OF_STOCK_CODE /*-1036*/:
                codes = getCurrentOrder().getTotalizeResult().getProductsOutOfStock();
                break;
            case OrderResponse.PRODUCT_UNAVAILABLE_CODE /*-1023*/:
                codes = getCurrentOrder().getTotalizeResult().getProductsUnavailable();
                break;
        }
        if (codes == null) {
            return new ArrayList(0);
        }
        List<String> problematicProductsCodes = new ArrayList(codes.size());
        int size = codes.size();
        for (int i = 0; i < size; i++) {
            problematicProductsCodes.add(((Integer) codes.get(i)).toString());
        }
        return problematicProductsCodes;
    }

    public void updateTender() {
        Ensighten.evaluateEvent(this, "updateTender", null);
        Order order = getCurrentOrder();
        if (order.isDelivery()) {
            order.setTenderType(getTenderType());
            if (order.getTotalizeResult() == null || order.getTotalizeResult().getOrderValue() == null || order.getTotalizeResult().getDeliveryFee() == null) {
                order.setTenderAmount(order.getTotalValue());
            } else {
                order.setTenderAmount(order.getTotalizeResult().getOrderValue().doubleValue() + order.getTotalizeResult().getDeliveryFee().doubleValue());
            }
        }
    }

    public int getTenderType() {
        Ensighten.evaluateEvent(this, "getTenderType", null);
        Order order = getCurrentOrder();
        if (order.getPayment() != null) {
            int paymentMethodId = order.getPayment().getPaymentMethodId();
            LinkedTreeMap<String, Object> configValue = (LinkedTreeMap) Configuration.getSharedInstance().getValueForKey("modules.delivery");
            Gson gson = new Gson();
            String linkedTreeMap = configValue.toString();
            Class cls = DeliveryConfig.class;
            for (ExpectedTenderType expectedTenderType : (!(gson instanceof Gson) ? gson.fromJson(linkedTreeMap, cls) : GsonInstrumentation.fromJson(gson, linkedTreeMap, cls)).expectedTenderTypes) {
                if (expectedTenderType.paymentMethodId == paymentMethodId) {
                    return expectedTenderType.tenderType;
                }
            }
        }
        return 0;
    }

    private void totalizeDelivery(final AsyncListener<Boolean> requestListener) {
        Ensighten.evaluateEvent(this, "totalizeDelivery", new Object[]{requestListener});
        DeliveryModule deliveryModule = (DeliveryModule) ModuleManager.getModule(DeliveryModule.NAME);
        if (!(getCurrentOrder() == null || getCurrentOrder().getDeliveryStore() == null)) {
            getCurrentOrder().setStoreId(String.valueOf(getCurrentOrder().getDeliveryStore().getStoreId()));
        }
        deliveryModule.validate(new AsyncListener<OrderResponse>() {
            public void onResponse(OrderResponse response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                if (exception == null) {
                    OrderingManager.this.getCurrentOrder().setTotalizeResult(response);
                }
                requestListener.onResponse(Boolean.valueOf(true), token, exception);
            }
        });
    }

    private void totalizePickUp(Store currentStore, final AsyncListener<Boolean> requestListener) {
        Ensighten.evaluateEvent(this, "totalizePickUp", new Object[]{currentStore, requestListener});
        OrderingModule orderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        getCurrentOrder().setStoreId(String.valueOf(currentStore.getStoreId()));
        orderingModule.totalize(new AsyncListener<OrderResponse>() {
            public void onResponse(OrderResponse response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                if (exception == null) {
                    OrderingManager.this.getCurrentOrder().setTotalizeResult(response);
                    OrderingManager.this.getCurrentOrder().setTotalizeBeforeCheckin(response);
                }
                requestListener.onResponse(Boolean.valueOf(true), token, exception);
            }
        });
    }

    public void preparePayment(final Order order, @NonNull final AsyncListener<OrderResponse> requestListener) {
        Ensighten.evaluateEvent(this, "preparePayment", new Object[]{order, requestListener});
        if (order.isZeroPriced()) {
            OrderPayment oldPayment = order.getPayment();
            OrderPayment payment = new OrderPayment();
            if (oldPayment != null) {
                payment.setPOD(oldPayment.getPOD());
            }
            payment.setPaymentMethodId(0);
            order.setPayment(payment);
            getInstance().updateCurrentOrder(order);
        } else if (order.getPayment() != null) {
            order.getPayment().setOrderPaymentId(null);
            getInstance().updateCurrentOrder(order);
        }
        ((OrderingModule) ModuleManager.getModule("ordering")).preparePayment(order, new AsyncListener<OrderResponse>() {
            public void onResponse(final OrderResponse orderResponse, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{orderResponse, token, exception});
                if (orderResponse != null) {
                    SecurityModule securityModule = (SecurityModule) ModuleManager.getModule(SecurityModule.NAME);
                    if (securityModule == null || orderResponse.getMerchantId() == null) {
                        requestListener.onResponse(orderResponse, token, exception);
                        return;
                    } else {
                        securityModule.retriveSecurityToken(orderResponse.getMerchantId(), new AsyncListener<String>() {
                            public void onResponse(String response, AsyncToken token, AsyncException exception) {
                                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                                if (response != null) {
                                    order.getPayment().setIpAddress(Utils.getLocalIpAddress());
                                    order.getPayment().setDeviceFingerPrintId(response);
                                }
                                requestListener.onResponse(orderResponse, token, exception);
                            }
                        });
                        return;
                    }
                }
                requestListener.onResponse(orderResponse, token, exception);
            }
        });
    }

    public boolean shouldHidePositive() {
        Ensighten.evaluateEvent(this, "shouldHidePositive", null);
        return getCurrentOrder().getTotalizeResult().getTotalValue().doubleValue() == 0.0d;
    }

    public void setProductErrorCode(int productErrorCode) {
        Ensighten.evaluateEvent(this, "setProductErrorCode", new Object[]{new Integer(productErrorCode)});
        this.mProductErrorCode = productErrorCode;
    }

    public int getProductErrorCode() {
        Ensighten.evaluateEvent(this, "getProductErrorCode", null);
        return this.mProductErrorCode;
    }
}
