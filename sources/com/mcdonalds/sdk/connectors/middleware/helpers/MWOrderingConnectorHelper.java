package com.mcdonalds.sdk.connectors.middleware.helpers;

import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.OrderingConnector;
import com.mcdonalds.sdk.connectors.middleware.MWException;
import com.mcdonalds.sdk.connectors.middleware.model.MWCatalogVersionItem;
import com.mcdonalds.sdk.connectors.middleware.model.MWErrorResponse;
import com.mcdonalds.sdk.connectors.middleware.model.MWOrderBarCodeView;
import com.mcdonalds.sdk.connectors.middleware.model.MWOrderUnAttendedCheckIn;
import com.mcdonalds.sdk.connectors.middleware.model.MWOrderView;
import com.mcdonalds.sdk.connectors.middleware.model.MWOrderViewResult;
import com.mcdonalds.sdk.connectors.middleware.model.MWPrepareDeliveryFeeResult;
import com.mcdonalds.sdk.connectors.middleware.model.MWPreparePaymentResult;
import com.mcdonalds.sdk.connectors.middleware.model.MWRestaurant;
import com.mcdonalds.sdk.connectors.middleware.request.MWBoundaryCrossCheckInRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWCheckinRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWDeliveryCheckOutRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWFoundationalCheckInRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWGeoFencingConfigurationRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWGetDeliveryLocationByStoreRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWGetDeliveryStatusRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWGetStoreInformationRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWGetStoresByLocationRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWKioskCheckinRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWLookupDeliveryChargeRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWOrderAddonsRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWOrderUnAttendedCheckInRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWPreparePaymentForDeliveryRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWPreparePaymentRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWTotalizeOrderRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWValidateOrderRequest;
import com.mcdonalds.sdk.connectors.middleware.response.MWBoundaryCrossCheckInResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWCheckinResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWDeliveryCheckOutResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWDeliveryStatusResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWFoundationalCheckInResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWGeoFencingConfigurationResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetDeliveryStatusResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetStoreInformationResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetStoresByLocationResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWKioskCheckin;
import com.mcdonalds.sdk.connectors.middleware.response.MWKioskCheckinResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWLookupDeliveryChargeResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWOrderAddonsResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWOrderUnAttendedCheckInResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWPreparePaymentResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWTotalizeResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWValidateOrderResponse;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.AddressType;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.modules.models.DeliveryStatusResponse;
import com.mcdonalds.sdk.modules.models.GeoFencingConfiguration;
import com.mcdonalds.sdk.modules.models.KioskCheckinResponse;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderResponse;
import com.mcdonalds.sdk.modules.models.OrderUnAttendedCheckIn;
import com.mcdonalds.sdk.modules.models.PaymentMethod;
import com.mcdonalds.sdk.modules.models.PointOfDistribution;
import com.mcdonalds.sdk.modules.models.StoreCapabilties;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.modules.storelocator.StoreLocatorModule;
import com.mcdonalds.sdk.services.configuration.AppParameters;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.SessionManager;
import com.mcdonalds.sdk.utils.ListUtils;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MWOrderingConnectorHelper implements OrderingConnector {
    private static final int INVALID_PAYMENT_DATA_ID = -1;
    public static final String KEY_CACHE_LATEST_ORDER = "modules.ordering.cacheLatestOrderMinutes";
    public static final String KEY_CASH = "supportedPaymentMethods.cash.expectedPaymentMethodID";
    public static final String KEY_CREDIT_CARD = "supportedPaymentMethods.creditCard.expectedPaymentMethodID";
    public static final String KEY_OTHER = "supportedPaymentMethods.other.expectedPaymentMethodID";
    public static final String KEY_SUPPORTED_PAYMENTS = "supportedPaymentMethods.paymentMethodIDs";
    public static final String KEY_THIRD_PARTY = "supportedPaymentMethods.thirdParty.expectedPaymentMethodID";
    private static final String LOG_TAG = MWOrderingConnectorHelper.class.getName();
    private static final int MAX_BASKET_QUANTITY = 40;
    private static final int MAX_DIM_DEPTH = 2;
    private static final int MAX_MINUTES_TO_ADV_ORDER = 10080;
    private static final int MIN_MINUTES_TO_ADV_ORDER = 120;
    private static final Integer OFFER_NOT_VALID_AT_LOCATION = Integer.valueOf(OrderResponse.PROMOTION_NOT_AVAILABLE_CODE);
    public static final int PARTIAL_PAYMENT_ERROR_CODE = -6020;
    private static final Integer PRODUCT_OUT_OF_STOCK_CODE = Integer.valueOf(-1036);
    private static final int RESULT_CODE_OK = 1;
    private static final int RESULT_CODE_ORDER_ERRORS = -6008;
    private static final int RESULT_CODE_OUT_OF_STOCK = -1036;
    private static final int RESULT_CODE_UNAUTHENTICATED = -1037;
    private static final int RESULT_CODE_UNAVAILABLE_AT_RESTAURANT = -1035;
    private static final int RESULT_CODE_VALIDATION_ERRORS = -1109;
    private MWConnectorShared mSharedData;

    public MWOrderingConnectorHelper(MWConnectorShared sharedData) {
        this.mSharedData = sharedData;
    }

    public Order createNewOrder() {
        return null;
    }

    public void getStoreOrderingCapabilties(String storeId, final AsyncListener<StoreCapabilties> requestListener) {
        this.mSharedData.getNetworkConnection().processRequest(new MWGetStoreInformationRequest(SessionManager.getInstance().getToken(), Integer.valueOf(storeId)), new AsyncListener<MWGetStoreInformationResponse>() {
            public void onResponse(MWGetStoreInformationResponse response, AsyncToken token, AsyncException exception) {
                if (response != null) {
                    AsyncException localException = exception;
                    if (response.getResultCode() != 1) {
                        localException = MWException.fromErrorCode(response.getResultCode());
                    }
                    StoreCapabilties capabilities = null;
                    if (response.getData() != null) {
                        capabilities = new StoreCapabilties(((MWRestaurant) response.getData()).pointsOfDistribution);
                    }
                    requestListener.onResponse(capabilities, null, localException);
                }
            }
        });
    }

    public void getStoreFromList(Date deliveryTime, boolean isNormalOrder, double orderAmount, List<String> storeIds, final AsyncListener<Store> listener) {
        final AsyncToken requestToken = new AsyncToken("getStoreFromList");
        this.mSharedData.getNetworkConnection().processRequest(new MWGetDeliveryLocationByStoreRequest(SessionManager.getInstance().getToken(), deliveryTime, isNormalOrder, orderAmount, storeIds), new AsyncListener<MWGetStoreInformationResponse>() {
            public void onResponse(MWGetStoreInformationResponse response, AsyncToken token, AsyncException exception) {
                if (exception != null) {
                    listener.onResponse(null, requestToken, exception);
                } else if (response.getResultCode() != 1) {
                    listener.onResponse(null, requestToken, MWException.fromErrorCode(response.getResultCode()));
                } else {
                    MWRestaurant restaurant = (MWRestaurant) response.getData();
                    if (!ListUtils.isEmpty(restaurant.MWCatalogVersions)) {
                        int storeId = restaurant.storeNumber;
                        List<MWCatalogVersionItem> catalogVersionItems = restaurant.MWCatalogVersions;
                        LocalDataManager.getSharedInstance().setStoreCatalogTimestamps(storeId, catalogVersionItems);
                        if (LocalDataManager.getSharedInstance().isStoreCatalogOutDated(storeId, catalogVersionItems)) {
                            LocalDataManager.getSharedInstance().setStoreCatalogDownloaded(Integer.toString(storeId), false);
                        }
                    }
                    final Store store = ((MWRestaurant) response.getData()).toStore(MWOrderingConnectorHelper.this.mSharedData.getContext());
                    ((StoreLocatorModule) ModuleManager.getModule(StoreLocatorModule.NAME)).getStoreForId(Integer.toString(store.getStoreId()), new AsyncListener<List<Store>>() {
                        public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
                            if (exception == null) {
                                for (Store autoNaviStore : response) {
                                    if (autoNaviStore.getStoreId() == store.getStoreId()) {
                                        store.setHasMobileOffers(Boolean.valueOf(autoNaviStore.hasMobileOffers()));
                                        store.setHasMobileOrdering(autoNaviStore.hasMobileOrdering());
                                    }
                                }
                                listener.onResponse(store, requestToken, null);
                                return;
                            }
                            AsyncException.report(exception);
                        }
                    });
                }
            }
        });
    }

    public void orderUnAttendedCheckIn(String checkInCode, OrderUnAttendedCheckIn orderUnAttendedCheckIn, final AsyncListener<OrderResponse> listener) {
        this.mSharedData.getNetworkConnection().processRequest(new MWOrderUnAttendedCheckInRequest(SessionManager.getInstance().getToken(), checkInCode, MWOrderUnAttendedCheckIn.fromUnAttendedCheckIn(orderUnAttendedCheckIn)), new AsyncListener<MWOrderUnAttendedCheckInResponse>() {

            /* renamed from: com.mcdonalds.sdk.connectors.middleware.helpers.MWOrderingConnectorHelper$3$1 */
            class C24941 extends TypeToken<MWErrorResponse<MWOrderUnAttendedCheckInResponse>> {
                C24941() {
                }
            }

            public void onResponse(MWOrderUnAttendedCheckInResponse response, AsyncToken token, AsyncException exception) {
                if (listener != null) {
                    OrderResponse orderResponse = null;
                    if (exception != null && exception.getEcpResultCode() != null && exception.getEcpResultCode().intValue() == MWOrderingConnectorHelper.PARTIAL_PAYMENT_ERROR_CODE) {
                        try {
                            Gson gson = new Gson();
                            String message = exception.getMessage();
                            Type type = new C24941().getType();
                            MWOrderUnAttendedCheckInResponse unAttendedCheckInResponse = (!(gson instanceof Gson) ? gson.fromJson(message, type) : GsonInstrumentation.fromJson(gson, message, type)).data.backEndResponse;
                            if (unAttendedCheckInResponse != null) {
                                orderResponse = OrderResponse.fromFoundationalCheckIn(MWFoundationalCheckInResponse.toOrderView(unAttendedCheckInResponse));
                            }
                        } catch (NullPointerException e) {
                            Log.e(MWOrderingConnectorHelper.LOG_TAG, "NullPointerException while parsing the Partial payment data");
                        } catch (JsonSyntaxException e2) {
                            Log.e(MWOrderingConnectorHelper.LOG_TAG, "JsonSyntaxException while parsing the Partial payment data");
                        }
                    } else if (response != null) {
                        orderResponse = OrderResponse.fromFoundationalCheckIn(MWFoundationalCheckInResponse.toOrderView(response));
                    }
                    listener.onResponse(orderResponse, token, exception);
                }
            }
        });
    }

    public void getGeoFencingConfiguration(final AsyncListener<GeoFencingConfiguration> listener) {
        this.mSharedData.getNetworkConnection().processRequest(new MWGeoFencingConfigurationRequest(), new AsyncListener<MWGeoFencingConfigurationResponse>() {
            public void onResponse(MWGeoFencingConfigurationResponse response, AsyncToken token, AsyncException exception) {
                if (listener != null) {
                    listener.onResponse(GeoFencingConfiguration.fromMWGeoFencingConfiguration(response), token, exception);
                }
            }
        });
    }

    public void enteredStoreBoundaryForOrder(String checkInCode, String storeId, final AsyncListener<MWBoundaryCrossCheckInResponse> listener) {
        this.mSharedData.getNetworkConnection().processRequest(new MWBoundaryCrossCheckInRequest(SessionManager.getInstance().getToken(), checkInCode, storeId), new AsyncListener<MWBoundaryCrossCheckInResponse>() {
            public void onResponse(MWBoundaryCrossCheckInResponse response, AsyncToken token, AsyncException exception) {
                if (listener != null) {
                    listener.onResponse(response, token, exception);
                }
            }
        });
    }

    public void getUpsellItems(Order order, final AsyncListener<int[]> listener) {
        final AsyncToken requestToken = new AsyncToken("getUpsellItems");
        this.mSharedData.getNetworkConnection().processRequest(new MWOrderAddonsRequest(SessionManager.getInstance().getToken(), ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getCurrentProfile().getUserName(), order.getStoreId(), order.isDelivery(), order.getDeliveryDate(), order.isNormalOrder(), MWOrderView.fromOrder(order, false)), new AsyncListener<MWOrderAddonsResponse>() {
            public void onResponse(MWOrderAddonsResponse response, AsyncToken token, AsyncException exception) {
                if (response != null) {
                    listener.onResponse(response.getData(), requestToken, exception);
                } else {
                    listener.onResponse(null, requestToken, exception);
                }
            }
        });
    }

    public AsyncToken checkMobileOrderingSupport(Integer storeId, final AsyncListener<Store> requestListener) {
        final AsyncToken requestToken = new AsyncToken("checkMobileOrderingSupport");
        this.mSharedData.getNetworkConnection().processRequest(new MWGetStoreInformationRequest(SessionManager.getInstance().getToken(), storeId), new AsyncListener<MWGetStoreInformationResponse>() {
            public void onResponse(MWGetStoreInformationResponse response, AsyncToken token, AsyncException exception) {
                if (exception != null) {
                    requestListener.onResponse(null, requestToken, exception);
                } else if (response.getResultCode() != 1) {
                    requestListener.onResponse(null, requestToken, MWException.fromErrorCode(response.getResultCode()));
                } else if (response.getData() != null) {
                    MWRestaurant restaurant = (MWRestaurant) response.getData();
                    if (!ListUtils.isEmpty(restaurant.MWCatalogVersions)) {
                        int storeId = restaurant.storeNumber;
                        List<MWCatalogVersionItem> catalogVersionItems = restaurant.MWCatalogVersions;
                        LocalDataManager.getSharedInstance().setStoreCatalogTimestamps(storeId, catalogVersionItems);
                        if (LocalDataManager.getSharedInstance().isStoreCatalogOutDated(storeId, catalogVersionItems)) {
                            LocalDataManager.getSharedInstance().setStoreCatalogDownloaded(Integer.toString(storeId), false);
                        }
                    }
                    requestListener.onResponse(restaurant.toStore(MWOrderingConnectorHelper.this.mSharedData.getContext()), requestToken, null);
                } else {
                    requestListener.onResponse(null, requestToken, new AsyncException());
                }
            }
        });
        return requestToken;
    }

    public AsyncToken checkMobileOrderingSupportForStores(final List<Store> stores, Location location, final AsyncListener<List<Store>> requestListener) {
        final AsyncToken requestToken = new AsyncToken("checkMobileOrderingSupportForStores");
        if (stores == null || stores.isEmpty()) {
            throw new RuntimeException("Store list is empty to checkMobileOrderingSupportForStores");
        }
        if (location != null) {
            this.mSharedData.getNetworkConnection().processRequest(new MWGetStoresByLocationRequest(SessionManager.getInstance().getToken(), Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), Integer.valueOf(50), null), new AsyncListener<MWGetStoresByLocationResponse>() {
                public void onResponse(MWGetStoresByLocationResponse response, AsyncToken token, AsyncException exception) {
                    AsyncException localException = exception;
                    if (!(response == null || response.getResultCode() == 1)) {
                        localException = MWException.fromErrorCode(response.getResultCode());
                    }
                    if (localException != null) {
                        requestListener.onResponse(null, requestToken, localException);
                    } else if (response == null) {
                        requestListener.onResponse(null, requestToken, MWException.fromGeneric());
                    } else {
                        int i;
                        List<MWRestaurant> mwRestaurants = (List) response.getData();
                        int size = mwRestaurants.size();
                        List<Integer> foundIds = new ArrayList(size);
                        for (i = 0; i < size; i++) {
                            foundIds.add(Integer.valueOf(((MWRestaurant) mwRestaurants.get(i)).storeNumber));
                        }
                        size = stores.size();
                        for (i = 0; i < size; i++) {
                            Store store = (Store) stores.get(i);
                            store.setHasMobileOrdering(Boolean.valueOf(foundIds.contains(Integer.valueOf(store.getStoreId()))));
                        }
                        requestListener.onResponse(stores, requestToken, null);
                    }
                }
            });
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    requestListener.onResponse(null, null, null);
                }
            });
        }
        return requestToken;
    }

    public AsyncToken totalize(Order order, final AsyncListener<OrderResponse> requestListener) {
        final AsyncToken requestToken = new AsyncToken("totalize");
        order.setTotalizeResult(null);
        if (order.getProfile() == null) {
            requestListener.onResponse(null, requestToken, MWException.fromErrorCode(RESULT_CODE_UNAUTHENTICATED));
        } else {
            MWTotalizeOrderRequest totalizeRequest = new MWTotalizeOrderRequest(SessionManager.getInstance().getToken(), order.getProfile().getUserName(), order.getStoreId(), MWOrderView.fromOrder(order, false));
            final List<Integer> allowedResultCodes = Arrays.asList(new Integer[]{Integer.valueOf(1), Integer.valueOf(RESULT_CODE_ORDER_ERRORS), Integer.valueOf(RESULT_CODE_VALIDATION_ERRORS), Integer.valueOf(-1035), Integer.valueOf(-1036)});
            this.mSharedData.getNetworkConnection().processRequest(totalizeRequest, new AsyncListener<MWTotalizeResponse>() {
                public void onResponse(MWTotalizeResponse response, AsyncToken token, AsyncException exception) {
                    OrderResponse orderResponse = null;
                    AsyncException localException = exception;
                    if (response != null) {
                        if (!allowedResultCodes.contains(Integer.valueOf(response.getResultCode()))) {
                            localException = MWException.fromErrorCode(response.getResultCode());
                        }
                        if (response.getData() != null) {
                            orderResponse = OrderResponse.fromTotalize(MWOrderViewResult.toOrderView(((MWOrderBarCodeView) response.getData()).orderViewResult));
                        } else if (response.getResultCode() == -1035) {
                            localException = MWException.fromErrorCode(response.getResultCode());
                        }
                    }
                    requestListener.onResponse(orderResponse, requestToken, localException);
                }
            });
        }
        return requestToken;
    }

    public AsyncToken preparePayment(Order order, final AsyncListener<OrderResponse> requestListener) {
        RequestProvider preparePaymentRequest;
        final AsyncToken requestToken = new AsyncToken("preparePayment");
        if (order.getPayment() != null) {
            order.getPayment().setOrderPaymentId(null);
            order.getPayment().setPaymentDataId(-1);
        }
        if (order.isDelivery()) {
            preparePaymentRequest = new MWPreparePaymentForDeliveryRequest(SessionManager.getInstance().getToken(), order);
        } else {
            preparePaymentRequest = new MWPreparePaymentRequest(SessionManager.getInstance().getToken(), order.getProfile().getUserName(), order.getStoreId(), MWOrderView.fromOrder(order));
        }
        final List<Integer> allowedResultCodes = Arrays.asList(new Integer[]{Integer.valueOf(1), Integer.valueOf(RESULT_CODE_ORDER_ERRORS), Integer.valueOf(-1036), Integer.valueOf(-1035)});
        this.mSharedData.getNetworkConnection().processRequest(preparePaymentRequest, new AsyncListener<MWPreparePaymentResponse>() {
            public void onResponse(MWPreparePaymentResponse response, AsyncToken token, AsyncException exception) {
                OrderResponse orderResponse = null;
                AsyncException localException = exception;
                if (response != null) {
                    if (!allowedResultCodes.contains(Integer.valueOf(response.getResultCode()))) {
                        localException = MWException.fromErrorCode(response.getResultCode());
                    }
                    if (response.getData() != null) {
                        orderResponse = MWPreparePaymentResult.toOrderResponse((MWPreparePaymentResult) response.getData());
                        if (!orderResponse.getProductsOutOfStock().isEmpty()) {
                            localException = MWException.fromErrorCode(MWOrderingConnectorHelper.PRODUCT_OUT_OF_STOCK_CODE.intValue());
                        } else if (!orderResponse.getPromotionsNotAvailable().isEmpty()) {
                            localException = MWException.fromErrorCode(MWOrderingConnectorHelper.OFFER_NOT_VALID_AT_LOCATION.intValue());
                        }
                    } else if (response.getResultCode() == -1035) {
                        localException = MWException.fromErrorCode(response.getResultCode());
                    }
                }
                requestListener.onResponse(orderResponse, requestToken, localException);
            }
        });
        return requestToken;
    }

    public AsyncToken foundationalCheckIn(Order order, final AsyncListener<OrderResponse> requestListener) {
        final AsyncToken requestToken = new AsyncToken("foundationalCheckIn");
        this.mSharedData.getNetworkConnection().processRequest(new MWFoundationalCheckInRequest(SessionManager.getInstance().getToken(), order.getProfile().getUserName(), order.getStoreId(), MWOrderView.fromOrder(order)), new AsyncListener<MWFoundationalCheckInResponse>() {
            public void onResponse(MWFoundationalCheckInResponse response, AsyncToken token, AsyncException exception) {
                if (response == null || response.orderView == null) {
                    requestListener.onResponse(null, requestToken, exception);
                    return;
                }
                requestListener.onResponse(OrderResponse.fromFoundationalCheckIn(MWFoundationalCheckInResponse.toOrderView(response)), requestToken, exception);
            }
        });
        return requestToken;
    }

    public AsyncToken checkin(Order order, String checkinData, String password, final AsyncListener<OrderResponse> requestListener) {
        final AsyncToken requestToken = new AsyncToken("checkin");
        this.mSharedData.getNetworkConnection().processRequest(new MWCheckinRequest(SessionManager.getInstance().getToken(), order.getProfile().getUserName(), order.getStoreId(), MWOrderView.fromOrder(order), checkinData, password, order.getPaymentResult()), new AsyncListener<MWCheckinResponse>() {
            public void onResponse(MWCheckinResponse response, AsyncToken token, AsyncException exception) {
                if (exception != null) {
                    requestListener.onResponse(null, requestToken, exception);
                } else if (response == null) {
                    requestListener.onResponse(null, requestToken, null);
                } else if (response.getResultCode() != 1) {
                    requestListener.onResponse(null, requestToken, MWException.fromErrorCode(response.getResultCode()));
                } else {
                    OrderResponse orderResponse = OrderResponse.fromCheckin(MWOrderViewResult.toOrderView((MWOrderViewResult) response.getData()));
                    LocalDataManager.getSharedInstance().setLatestOrderNumber(orderResponse.getDisplayOrderNumber());
                    LocalDataManager.getSharedInstance().setLatestOrderIsDriveThru(orderResponse.getPOD() == PointOfDistribution.DriveThru);
                    requestListener.onResponse(orderResponse, requestToken, null);
                }
            }
        });
        order.clearPaymentResult();
        return requestToken;
    }

    public AsyncToken checkinKiosk(Order order, String password, final AsyncListener<KioskCheckinResponse> requestListener) {
        final AsyncToken requestToken = new AsyncToken("#checkinKiosk");
        this.mSharedData.getNetworkConnection().processRequest(new MWKioskCheckinRequest(SessionManager.getInstance().getToken(), order.getProfile().getUserName(), order.getStoreId(), MWOrderView.fromOrder(order), password), new AsyncListener<MWKioskCheckinResponse>() {
            public void onResponse(MWKioskCheckinResponse response, AsyncToken token, AsyncException exception) {
                if (exception != null) {
                    requestListener.onResponse(null, requestToken, exception);
                } else if (response.getResultCode() != 1) {
                    requestListener.onResponse(null, requestToken, MWException.fromErrorCode(response.getResultCode()));
                } else {
                    MWKioskCheckin responseData = (MWKioskCheckin) response.getData();
                    byte[] decodedString = Base64.decode(responseData.getBarcode(), 0);
                    requestListener.onResponse(new KioskCheckinResponse(BitmapFactoryInstrumentation.decodeByteArray(decodedString, 0, decodedString.length), responseData.getRandomCode()), requestToken, null);
                }
            }
        });
        return requestToken;
    }

    public AsyncToken validate(Order order, final AsyncListener<OrderResponse> requestListener) {
        final AsyncToken requestToken = new AsyncToken("validate");
        this.mSharedData.getNetworkConnection().processRequest(new MWValidateOrderRequest(SessionManager.getInstance().getToken(), order.getProfile().getUserName(), order.getStoreId(), order.getProfile().getEmailAddress(), order.getTenderType(), order.getTenderAmount(), new Date(order.getDeliveryDate().getTime()), order.isNormalOrder(), Arrays.asList(AddressType.values()).indexOf(order.getDeliveryAddress().getAddressType()), MWOrderView.fromOrder(order)), new AsyncListener<MWValidateOrderResponse>() {
            public void onResponse(MWValidateOrderResponse response, AsyncToken token, AsyncException exception) {
                if (exception != null) {
                    requestListener.onResponse(null, requestToken, exception);
                } else if (response.getResultCode() != 1) {
                    requestListener.onResponse(null, requestToken, MWException.fromErrorCode(response.getResultCode()));
                } else {
                    requestListener.onResponse(OrderResponse.fromTotalize(MWOrderViewResult.toOrderView((MWOrderViewResult) response.getData())), requestToken, null);
                }
            }
        });
        return requestToken;
    }

    public AsyncToken lookupDeliveryCharge(Order order, final AsyncListener<OrderResponse> requestListener) {
        final AsyncToken requestToken = new AsyncToken("lookupDeliveryCharge");
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        if (order.getProfile() == null && customerModule.getCurrentProfile() != null) {
            order.setProfile(customerModule.getCurrentProfile());
        }
        Store store = order.getDeliveryStore();
        if (store == null) {
            requestListener.onResponse(null, requestToken, new AsyncException());
        } else {
            this.mSharedData.getNetworkConnection().processRequest(new MWLookupDeliveryChargeRequest(SessionManager.getInstance().getToken(), order.getProfile().getUserName(), Integer.toString(store.getStoreId()), order.getTotalValue()), new AsyncListener<MWLookupDeliveryChargeResponse>() {
                public void onResponse(MWLookupDeliveryChargeResponse response, AsyncToken token, AsyncException exception) {
                    if (exception != null) {
                        requestListener.onResponse(null, requestToken, exception);
                    } else if (response.getResultCode() != 1) {
                        requestListener.onResponse(null, requestToken, MWException.fromErrorCode(response.getResultCode()));
                    } else {
                        requestListener.onResponse(MWPrepareDeliveryFeeResult.toOrderResponse((MWPrepareDeliveryFeeResult) response.getData()), requestToken, null);
                    }
                }
            });
        }
        return requestToken;
    }

    public AsyncToken checkout(Order order, String password, CustomerAddress address, final AsyncListener<OrderResponse> requestListener) {
        final AsyncToken requestToken = new AsyncToken("lookupDeliveryCharge");
        this.mSharedData.getNetworkConnection().processRequest(new MWDeliveryCheckOutRequest(SessionManager.getInstance().getToken(), order), new AsyncListener<MWDeliveryCheckOutResponse>() {
            public void onResponse(MWDeliveryCheckOutResponse response, AsyncToken token, AsyncException exception) {
                if (exception != null) {
                    requestListener.onResponse(null, requestToken, exception);
                } else if (response.getResultCode() != 1) {
                    requestListener.onResponse(null, requestToken, MWException.fromErrorCode(response.getResultCode()));
                } else {
                    requestListener.onResponse(OrderResponse.fromCheckout(MWOrderViewResult.toOrderView((MWOrderViewResult) response.getData())), requestToken, null);
                }
            }
        });
        return requestToken;
    }

    public AsyncToken getDeliveryStatus(String orderNumber, final AsyncListener<DeliveryStatusResponse> requestListener) {
        AsyncToken requestToken = new AsyncToken("getDeliveryStatus");
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        String username = "";
        if (customerModule.getCurrentProfile() != null) {
            username = customerModule.getCurrentProfile().getUserName();
        }
        this.mSharedData.getNetworkConnection().processRequest(new MWGetDeliveryStatusRequest(SessionManager.getInstance().getToken(), username, orderNumber), new AsyncListener<MWGetDeliveryStatusResponse>() {
            public void onResponse(MWGetDeliveryStatusResponse response, AsyncToken token, AsyncException exception) {
                DeliveryStatusResponse statusResponse = null;
                if (!(response.getData() == null || ((List) response.getData()).isEmpty())) {
                    statusResponse = ((MWDeliveryStatusResponse) ((List) response.getData()).get(0)).toDeliveryStatusResponse();
                }
                requestListener.onResponse(statusResponse, token, exception);
            }
        });
        return requestToken;
    }

    public AsyncToken getDeliveryStatus(final AsyncListener<List<DeliveryStatusResponse>> requestListener) {
        AsyncToken requestToken = new AsyncToken("getDeliveryStatus");
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        String username = "";
        if (customerModule.getCurrentProfile() != null) {
            username = customerModule.getCurrentProfile().getUserName();
        }
        final List<Integer> inProgressStatus = Arrays.asList(new Integer[]{Integer.valueOf(200), Integer.valueOf(300), Integer.valueOf(100), Integer.valueOf(150), Integer.valueOf(50), Integer.valueOf(MWDeliveryStatusResponse.STATUS_ORDER_DELIVERED)});
        this.mSharedData.getNetworkConnection().processRequest(new MWGetDeliveryStatusRequest(SessionManager.getInstance().getToken(), username, null), new AsyncListener<MWGetDeliveryStatusResponse>() {
            public void onResponse(MWGetDeliveryStatusResponse response, AsyncToken token, AsyncException exception) {
                List<DeliveryStatusResponse> statusResponse = new ArrayList();
                if (!(response == null || response.getData() == null)) {
                    for (MWDeliveryStatusResponse item : (List) response.getData()) {
                        if (inProgressStatus.contains(Integer.valueOf(item.status))) {
                            statusResponse.add(item.toDeliveryStatusResponse());
                        }
                    }
                }
                requestListener.onResponse(statusResponse, token, exception);
            }
        });
        return requestToken;
    }

    public int getMaxBasketQuantity() {
        String maxItemQuantity = this.mSharedData.getAppParameter(AppParameters.MAX_QTTY_ONBASKET);
        return maxItemQuantity == null ? 40 : Integer.parseInt(maxItemQuantity);
    }

    public int getMinMinutesToAdvOrder() {
        String minMinutesToAdvOrder = this.mSharedData.getAppParameter(AppParameters.MIN_MINUTES_TOADV_ORDER);
        return minMinutesToAdvOrder == null ? MIN_MINUTES_TO_ADV_ORDER : Integer.parseInt(minMinutesToAdvOrder);
    }

    public int getMaxMinutesToAdvOrder() {
        String maxMinutesToAdvOrder = this.mSharedData.getAppParameter(AppParameters.MAX_MINUTES_TOADV_ORDER);
        return maxMinutesToAdvOrder == null ? MAX_MINUTES_TO_ADV_ORDER : Integer.parseInt(maxMinutesToAdvOrder);
    }

    @Deprecated
    public void getPaymentMethods(AsyncListener<List<PaymentMethod>> asyncListener) {
    }
}
