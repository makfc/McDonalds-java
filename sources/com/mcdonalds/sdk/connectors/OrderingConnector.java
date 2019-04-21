package com.mcdonalds.sdk.connectors;

import android.location.Location;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.middleware.response.MWBoundaryCrossCheckInResponse;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.modules.models.DeliveryStatusResponse;
import com.mcdonalds.sdk.modules.models.GeoFencingConfiguration;
import com.mcdonalds.sdk.modules.models.KioskCheckinResponse;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderResponse;
import com.mcdonalds.sdk.modules.models.OrderUnAttendedCheckIn;
import com.mcdonalds.sdk.modules.models.PaymentMethod;
import com.mcdonalds.sdk.modules.models.StoreCapabilties;
import com.mcdonalds.sdk.modules.storelocator.Store;
import java.util.Date;
import java.util.List;

public interface OrderingConnector {
    AsyncToken checkMobileOrderingSupport(Integer num, AsyncListener<Store> asyncListener);

    AsyncToken checkMobileOrderingSupportForStores(List<Store> list, Location location, AsyncListener<List<Store>> asyncListener);

    AsyncToken checkin(Order order, String str, String str2, AsyncListener<OrderResponse> asyncListener);

    AsyncToken checkinKiosk(Order order, String str, AsyncListener<KioskCheckinResponse> asyncListener);

    AsyncToken checkout(Order order, String str, CustomerAddress customerAddress, AsyncListener<OrderResponse> asyncListener);

    Order createNewOrder();

    void enteredStoreBoundaryForOrder(String str, String str2, AsyncListener<MWBoundaryCrossCheckInResponse> asyncListener);

    AsyncToken foundationalCheckIn(Order order, AsyncListener<OrderResponse> asyncListener);

    AsyncToken getDeliveryStatus(AsyncListener<List<DeliveryStatusResponse>> asyncListener);

    AsyncToken getDeliveryStatus(String str, AsyncListener<DeliveryStatusResponse> asyncListener);

    void getGeoFencingConfiguration(AsyncListener<GeoFencingConfiguration> asyncListener);

    int getMaxBasketQuantity();

    int getMaxMinutesToAdvOrder();

    int getMinMinutesToAdvOrder();

    @Deprecated
    void getPaymentMethods(AsyncListener<List<PaymentMethod>> asyncListener);

    void getStoreFromList(Date date, boolean z, double d, List<String> list, AsyncListener<Store> asyncListener);

    void getStoreOrderingCapabilties(String str, AsyncListener<StoreCapabilties> asyncListener);

    void getUpsellItems(Order order, AsyncListener<int[]> asyncListener);

    AsyncToken lookupDeliveryCharge(Order order, AsyncListener<OrderResponse> asyncListener);

    void orderUnAttendedCheckIn(String str, OrderUnAttendedCheckIn orderUnAttendedCheckIn, AsyncListener<OrderResponse> asyncListener);

    AsyncToken preparePayment(Order order, AsyncListener<OrderResponse> asyncListener);

    AsyncToken totalize(Order order, AsyncListener<OrderResponse> asyncListener);

    AsyncToken validate(Order order, AsyncListener<OrderResponse> asyncListener);
}
