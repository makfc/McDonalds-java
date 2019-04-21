package com.mcdonalds.sdk.connectors.middleware;

import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;
import com.facebook.internal.ServerProtocol;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.BaseConnector;
import com.mcdonalds.sdk.connectors.ConfigurationConnector;
import com.mcdonalds.sdk.connectors.CustomerConnector;
import com.mcdonalds.sdk.connectors.NutritionConnector;
import com.mcdonalds.sdk.connectors.OffersConnector;
import com.mcdonalds.sdk.connectors.OrderingConnector;
import com.mcdonalds.sdk.connectors.StoreLocatorConnector;
import com.mcdonalds.sdk.connectors.middleware.helpers.MWConfigurationConnectorHelper;
import com.mcdonalds.sdk.connectors.middleware.helpers.MWConnectorShared;
import com.mcdonalds.sdk.connectors.middleware.helpers.MWCustomerConnectorHelper;
import com.mcdonalds.sdk.connectors.middleware.helpers.MWDCSConnectorHelper;
import com.mcdonalds.sdk.connectors.middleware.helpers.MWNutritionConnectorHelper;
import com.mcdonalds.sdk.connectors.middleware.helpers.MWOfferConnectorHelper;
import com.mcdonalds.sdk.connectors.middleware.helpers.MWOrderingConnectorHelper;
import com.mcdonalds.sdk.connectors.middleware.helpers.MWStoreLocatorConnectorHelper;
import com.mcdonalds.sdk.connectors.middleware.model.MWPaymentURLPostInfo;
import com.mcdonalds.sdk.connectors.middleware.response.MWBoundaryCrossCheckInResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWJSONResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWSignInResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWWechatTokenResponse;
import com.mcdonalds.sdk.connectors.storelocator.StoreLocatorConnectorQueryParameters;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.customer.CustomerProfile.AccountVerificationType;
import com.mcdonalds.sdk.modules.models.AddressValidationResult;
import com.mcdonalds.sdk.modules.models.AdvertisablePromotion;
import com.mcdonalds.sdk.modules.models.AdvertisablePromotionEntity;
import com.mcdonalds.sdk.modules.models.AuthenticationParameters;
import com.mcdonalds.sdk.modules.models.Catalog;
import com.mcdonalds.sdk.modules.models.Category;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.modules.models.CustomerOrder;
import com.mcdonalds.sdk.modules.models.CustomerPaymentAccount;
import com.mcdonalds.sdk.modules.models.DeliveryStatusResponse;
import com.mcdonalds.sdk.modules.models.FavoriteItem;
import com.mcdonalds.sdk.modules.models.GeoFencingConfiguration;
import com.mcdonalds.sdk.modules.models.GetAddressElementsResult;
import com.mcdonalds.sdk.modules.models.KioskCheckinResponse;
import com.mcdonalds.sdk.modules.models.NotificationPreferences;
import com.mcdonalds.sdk.modules.models.NutritionRecipe;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.OfferBarCodeData;
import com.mcdonalds.sdk.modules.models.OfferCategory;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.OrderResponse;
import com.mcdonalds.sdk.modules.models.OrderUnAttendedCheckIn;
import com.mcdonalds.sdk.modules.models.PaymentCard;
import com.mcdonalds.sdk.modules.models.PaymentMethod;
import com.mcdonalds.sdk.modules.models.SocialNetwork;
import com.mcdonalds.sdk.modules.models.StoreCapabilties;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.AppParameters;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.network.RequestManager;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MiddlewareConnector extends BaseConnector implements ConfigurationConnector, CustomerConnector, NutritionConnector, OffersConnector, OrderingConnector, StoreLocatorConnector {
    public static final String CONFIG_BASE_API_KEYS_PATH = "connectors.Middleware.requestMcdApiKeys";
    public static final String CONFIG_BASE_PATH = "connectors.Middleware";
    public static final String CONFIG_BASE_VERSION_PATH = "connectors.Middleware.requestVersions";
    public static final String CONFIG_DEFAULT_VERSION = "connectors.Middleware.defaultRequestVersion";
    public static final String CONFIG_HEADER_API_KEY = "mcd_apikey";
    public static final String CONFIG_HEADER_GUEST_API_KEY = "guest_mcd_apikey";
    public static final String NAME = "middleware";
    private ConfigurationConnector mConfigurationConnector;
    private CustomerConnector mCustomerConnector;
    private NutritionConnector mNutritionConnector;
    private OffersConnector mOffersConnector = new MWOfferConnectorHelper(this.mSharedData);
    private OrderingConnector mOrderingConnector;
    private MWConnectorShared mSharedData = new MWConnectorShared(this);
    private StoreLocatorConnector mStoreLocator = new MWStoreLocatorConnectorHelper(this.mSharedData);
    private final boolean mUseDCS = Configuration.getSharedInstance().hasKey("connectors.Middleware.DCSSecurity");

    public MiddlewareConnector(Context context) {
        setContext(context);
        setConnection(RequestManager.register(getContext()));
        if (this.mUseDCS) {
            this.mCustomerConnector = new MWDCSConnectorHelper(this.mSharedData);
        } else {
            this.mCustomerConnector = new MWCustomerConnectorHelper(this.mSharedData);
        }
        this.mOrderingConnector = new MWOrderingConnectorHelper(this.mSharedData);
        this.mNutritionConnector = new MWNutritionConnectorHelper(this.mSharedData, context);
        this.mConfigurationConnector = new MWConfigurationConnectorHelper(this.mSharedData);
    }

    @Deprecated
    public static String getURLStringForEndpoint(String endpoint) {
        return Configuration.getSharedInstance().getValueForKey("connectors.Middleware.baseUrl") + endpoint;
    }

    public static String getURLStringForEndpoint(MethodType methodType, String endpoint) {
        String url = (String) Configuration.getSharedInstance().getValueForKey("connectors.Middleware.baseUrl");
        if (!url.substring(url.length() - 1).equalsIgnoreCase("/")) {
            url = url + "/";
        }
        String version = getVersion(methodType, endpoint);
        if (version != null) {
            url = url + version + "/";
        }
        return url + endpoint.replaceFirst("^/", "");
    }

    public static String getApiKey(String endpoint) {
        return Configuration.getSharedInstance().getStringForKey("connectors.Middleware.requestMcdApiKeys." + endpoint + CONFIG_HEADER_API_KEY);
    }

    public static String getGuestApiKey(String endpoint) {
        return Configuration.getSharedInstance().getStringForKey("connectors.Middleware.requestMcdApiKeys." + endpoint + "." + CONFIG_HEADER_GUEST_API_KEY);
    }

    public static String getVersion(MethodType methodType, String endpoint) {
        String method;
        switch (methodType) {
            case POST:
                method = "POST";
                break;
            case PUT:
                method = "PUT";
                break;
            case DELETE:
                method = "DELETE";
                break;
            default:
                method = "GET";
                break;
        }
        String version = Configuration.getSharedInstance().getStringForKey("connectors.Middleware.requestVersions." + endpoint + "." + method);
        String defaultVersion = Configuration.getSharedInstance().getStringForKey(CONFIG_DEFAULT_VERSION);
        if (version != null) {
            return version;
        }
        if (defaultVersion != null) {
            return Configuration.getSharedInstance().getStringForKey(CONFIG_DEFAULT_VERSION);
        }
        return null;
    }

    @Deprecated
    public void getPaymentMethods(AsyncListener<List<PaymentMethod>> asyncListener) {
    }

    public void signIn(AsyncListener<MWSignInResponse> listener) {
        this.mSharedData.signIn(listener, this.mUseDCS);
    }

    @Deprecated
    public boolean getEnableMultipleMenuTypes() {
        return isEnableMultipleMenuTypes();
    }

    public boolean isEnableMultipleMenuTypes() {
        String enableMultipleMenuTypesString = this.mSharedData.getAppParameter(AppParameters.ENABLE_MULTIPLE_MENU_TYPES);
        return enableMultipleMenuTypesString != null && enableMultipleMenuTypesString.equalsIgnoreCase(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
    }

    public AsyncToken authenticate(AuthenticationParameters parameters, AsyncListener<CustomerProfile> listener) {
        return this.mCustomerConnector.authenticate(parameters, listener);
    }

    public AsyncToken signOut(AsyncListener listener) {
        return this.mCustomerConnector.signOut(listener);
    }

    public AsyncToken register(CustomerProfile customer, AsyncListener<CustomerProfile> listener) {
        return this.mCustomerConnector.register(customer, listener);
    }

    public AsyncToken resendActivation(CustomerProfile customer, AsyncListener<Void> listener) {
        return this.mCustomerConnector.resendActivation(customer, listener);
    }

    public AsyncToken resendActivationCode(String code, AsyncListener<Void> listener) {
        return this.mCustomerConnector.resendActivationCode(code, listener);
    }

    public AsyncToken changePassword(String username, String oldPassword, String newPassword, String authorizationCode, AsyncListener<Void> listener) {
        return this.mCustomerConnector.changePassword(username, oldPassword, newPassword, authorizationCode, listener);
    }

    public AsyncToken resetPassword(String username, AsyncListener<Void> listener) {
        return this.mCustomerConnector.resetPassword(username, listener);
    }

    public AsyncToken resetPassword(String username, String emailAddress, String mobilePhone, AsyncListener<Void> listener) {
        return this.mCustomerConnector.resetPassword(username, emailAddress, mobilePhone, listener);
    }

    public AsyncToken updateProfile(CustomerProfile customer, AsyncListener<CustomerProfile> listener) {
        return this.mCustomerConnector.updateProfile(customer, listener);
    }

    public AsyncToken registerExtSocialNetworkForced(CustomerProfile customer, AsyncListener<MWJSONResponse> listener) {
        return this.mCustomerConnector.registerExtSocialNetworkForced(customer, listener);
    }

    public AsyncToken getSocialNetworkAccessToken(int socialLoginId, String code, AsyncListener<MWWechatTokenResponse> listener) {
        return this.mCustomerConnector.getSocialNetworkAccessToken(socialLoginId, code, listener);
    }

    public AsyncToken addLoginMethod(CustomerProfile customer, AsyncListener<MWJSONResponse> listener) {
        return this.mCustomerConnector.addLoginMethod(customer, listener);
    }

    public AsyncToken deregister(CustomerProfile customer, String cancellationReason, AsyncListener<String> listener) {
        return this.mCustomerConnector.deregister(customer, cancellationReason, listener);
    }

    public AsyncToken getCustomerData(String username, AsyncListener<CustomerProfile> listener) {
        return this.mCustomerConnector.getCustomerData(username, listener);
    }

    public AsyncToken setNotificationPreferences(CustomerProfile customer, NotificationPreferences preferences, AsyncListener<NotificationPreferences> listener) {
        return this.mCustomerConnector.setNotificationPreferences(customer, preferences, listener);
    }

    public AsyncToken trackNotification(CustomerProfile customer, String messageId, String deliveryID, int tagID, AsyncListener<Void> listener) {
        return this.mCustomerConnector.trackNotification(customer, messageId, deliveryID, tagID, listener);
    }

    public AsyncToken getSocialLoginCatalogUpdate(AsyncListener<List<SocialNetwork>> listener) {
        return this.mCustomerConnector.getSocialLoginCatalogUpdate(listener);
    }

    public AsyncToken sendRating(String username, int rating, AsyncListener<Boolean> listener) {
        return this.mCustomerConnector.sendRating(username, rating, listener);
    }

    public AsyncToken sendRating(String username, String comment, int rating, AsyncListener<Boolean> listener) {
        return this.mCustomerConnector.sendRating(username, comment, rating, listener);
    }

    public AsyncToken sendSMSCode(CustomerProfile customer, AsyncListener<Boolean> asyncListener) {
        return null;
    }

    public AsyncToken verifyAccount(CustomerProfile customer, AccountVerificationType verificationType, String verificationCode, AsyncListener<Boolean> asyncListener) {
        return null;
    }

    public AsyncToken getCatalogUpdated(String username, String storeId, AsyncListener<Catalog> listener) {
        return this.mCustomerConnector.getCatalogUpdated(username, storeId, listener);
    }

    public AsyncToken addFavoriteLocations(List<Store> favoriteLocations, String username, AsyncListener<List<Store>> listener) {
        return this.mCustomerConnector.addFavoriteLocations(favoriteLocations, username, listener);
    }

    public AsyncToken deleteFavoriteLocations(List<Integer> favoriteLocationIds, String username, AsyncListener<List<Store>> listener) {
        return this.mCustomerConnector.deleteFavoriteLocations(favoriteLocationIds, username, listener);
    }

    public AsyncToken retrieveFavoriteStores(String username, AsyncListener<List<Store>> listener) {
        return this.mCustomerConnector.retrieveFavoriteStores(username, listener);
    }

    public AsyncToken updateTermsAndConditions(CustomerProfile profile, boolean isPrivacyPolicyAccepted, boolean isTermsOfUseAccepted, AsyncListener<CustomerProfile> listener) {
        return this.mCustomerConnector.updateTermsAndConditions(profile, isPrivacyPolicyAccepted, isTermsOfUseAccepted, listener);
    }

    public AsyncToken renameFavoriteLocations(List<Store> favoriteLocations, String username, AsyncListener<List<Store>> listener) {
        return this.mCustomerConnector.renameFavoriteLocations(favoriteLocations, username, listener);
    }

    public AsyncToken getPaymentTypeRegistrationURL(int paymentID, Boolean oneTimePayment, CustomerProfile customerProfile, AsyncListener<String> listener) {
        return this.mCustomerConnector.getPaymentTypeRegistrationURL(paymentID, oneTimePayment, customerProfile, listener);
    }

    public AsyncToken paymentTypePostRegistrationURL(int paymentID, Boolean oneTimePayment, int storeId, CustomerProfile customerProfile, AsyncListener<MWPaymentURLPostInfo> listener) {
        return this.mCustomerConnector.paymentTypePostRegistrationURL(paymentID, oneTimePayment, storeId, customerProfile, listener);
    }

    public AsyncToken updatePayment(String userName, String paymentProviderData, boolean isPreferred, AsyncListener<CustomerProfile> listener) {
        return this.mCustomerConnector.updatePayment(userName, paymentProviderData, isPreferred, listener);
    }

    public AsyncToken getRecentOrders(String username, Integer numRecents, AsyncListener<List<CustomerOrder>> listener) {
        return this.mCustomerConnector.getRecentOrders(username, numRecents, listener);
    }

    public String getGcmSenderId() {
        return this.mCustomerConnector.getGcmSenderId();
    }

    public AsyncToken associateDevice(String username, String deviceToken, AsyncListener<Boolean> listener) {
        return this.mCustomerConnector.associateDevice(username, deviceToken, listener);
    }

    public int getMaxItemQuantity() {
        return this.mCustomerConnector.getMaxItemQuantity();
    }

    public int getMaxBasketQuantity() {
        return this.mOrderingConnector.getMaxBasketQuantity();
    }

    public int getMinMinutesToAdvOrder() {
        return this.mOrderingConnector.getMinMinutesToAdvOrder();
    }

    public int getMaxMinutesToAdvOrder() {
        return this.mOrderingConnector.getMaxMinutesToAdvOrder();
    }

    public AsyncToken getFavoriteProducts(String userName, AsyncListener<List<FavoriteItem>> listener) {
        return this.mCustomerConnector.getFavoriteProducts(userName, listener);
    }

    public AsyncToken addFavoriteProducts(String username, String favoriteName, List<OrderProduct> orders, Boolean isProduct, AsyncListener<List<FavoriteItem>> listener) {
        return this.mCustomerConnector.addFavoriteProducts(username, favoriteName, orders, isProduct, listener);
    }

    public AsyncToken deleteFavoriteProducts(String username, List<FavoriteItem> orders, AsyncListener<List<FavoriteItem>> listener) {
        return this.mCustomerConnector.deleteFavoriteProducts(username, orders, listener);
    }

    public AsyncToken getAddressBook(String username, AsyncListener<List<CustomerAddress>> listener) {
        return this.mCustomerConnector.getAddressBook(username, listener);
    }

    public AsyncToken getAddressElements(String username, AsyncListener<GetAddressElementsResult> listener) {
        return this.mCustomerConnector.getAddressElements(username, listener);
    }

    public AsyncToken getDefaultAddress(String username, AsyncListener<CustomerAddress> listener) {
        return this.mCustomerConnector.getDefaultAddress(username, listener);
    }

    public AsyncToken validateAddress(String username, CustomerAddress address, AsyncListener<AddressValidationResult> listener) {
        return this.mCustomerConnector.validateAddress(username, address, listener);
    }

    public AsyncToken updateAddressBook(String username, List<CustomerAddress> addresses, AsyncListener<Boolean> listener) {
        return this.mCustomerConnector.updateAddressBook(username, addresses, listener);
    }

    public AsyncToken addAddress(String username, CustomerAddress address, AsyncListener<Boolean> listener) {
        return this.mCustomerConnector.addAddress(username, address, listener);
    }

    public AsyncToken removeAddress(String username, CustomerAddress address, AsyncListener<Boolean> listener) {
        return this.mCustomerConnector.removeAddress(username, address, listener);
    }

    public AsyncToken requestStores(StoreLocatorConnectorQueryParameters parameters, AsyncListener<List<Store>> requestListener) {
        return this.mStoreLocator.requestStores(parameters, requestListener);
    }

    public AsyncToken requestStoreWithId(String storeId, AsyncListener<List<Store>> requestListener) {
        return this.mStoreLocator.requestStoreWithId(storeId, requestListener);
    }

    public AsyncToken requestStoresWithIds(List<String> storeIdList, AsyncListener<List<Store>> requestListener) {
        return this.mStoreLocator.requestStoresWithIds(storeIdList, requestListener);
    }

    public AsyncToken requestStoreFilters(AsyncListener<List<String>> requestListener) {
        return this.mStoreLocator.requestStoreFilters(requestListener);
    }

    public AsyncToken getDeliveryStore(CustomerAddress address, String username, Date deliveryTime, AsyncListener<Store> listener) {
        return getDeliveryStore(address, username, deliveryTime, false, listener);
    }

    public AsyncToken getDeliveryStore(CustomerAddress address, String username, Date deliveryTime, boolean isNormalOrder, AsyncListener<Store> listener) {
        return this.mStoreLocator.getDeliveryStore(address, username, deliveryTime, listener);
    }

    public AsyncToken updatePayments(String userName, List<PaymentCard> paymentCards, AsyncListener<CustomerProfile> listener) {
        return this.mCustomerConnector.updatePayments(userName, paymentCards, listener);
    }

    public AsyncToken getPaymentData(String paymentProviderData, AsyncListener<PaymentCard> listener) {
        return this.mCustomerConnector.getPaymentData(paymentProviderData, listener);
    }

    public AsyncToken getOfferCategories(AsyncListener<List<OfferCategory>> listener) {
        return this.mOffersConnector.getOfferCategories(listener);
    }

    public AsyncToken getCustomerOffers(String username, Double latitude, Double longitude, List<String> storeIds, AsyncListener<List<Offer>> listener) {
        return this.mOffersConnector.getCustomerOffers(username, latitude, longitude, storeIds, listener);
    }

    public AsyncToken getAdvertisablePromotions(String userName, int storeId, AsyncListener<List<AdvertisablePromotion>> listener) {
        return this.mOffersConnector.getAdvertisablePromotions(userName, storeId, listener);
    }

    public AsyncToken getAdvertisablePromotionEntities(String userName, int storeId, AsyncListener<List<AdvertisablePromotionEntity>> listener) {
        return this.mOffersConnector.getAdvertisablePromotionEntities(userName, storeId, listener);
    }

    public AsyncToken selectOffersForPurchase(String username, Integer offerId, AsyncListener listener) {
        return this.mOffersConnector.selectOffersForPurchase(username, offerId, listener);
    }

    public AsyncToken selectToRedeem(String username, List<String> offerIds, Integer storeId, AsyncListener<OfferBarCodeData> listener) {
        return this.mOffersConnector.selectToRedeem(username, offerIds, storeId, listener);
    }

    public AsyncToken getCustomerIdentificationCode(String username, Integer storeId, AsyncListener<OfferBarCodeData> listener) {
        return this.mOffersConnector.getCustomerIdentificationCode(username, storeId, listener);
    }

    public AsyncToken archiveOffer(Integer offerId, String username, AsyncListener<Boolean> listener) {
        return this.mOffersConnector.archiveOffer(offerId, username, listener);
    }

    public AsyncToken unarchiveOffer(String offerId, String username, AsyncListener<Boolean> asyncListener) {
        return null;
    }

    public AsyncToken subscribe(String username, AsyncListener<Boolean> asyncListener) {
        return null;
    }

    public AsyncToken unsubscribe(String username, AsyncListener<Boolean> asyncListener) {
        return null;
    }

    public Order createNewOrder() {
        return null;
    }

    public void getStoreOrderingCapabilties(String storeId, AsyncListener<StoreCapabilties> requestListener) {
        this.mOrderingConnector.getStoreOrderingCapabilties(storeId, requestListener);
    }

    public void getStoreFromList(Date deliveryTime, boolean isNormalOrder, double orderAmount, List<String> storeIds, AsyncListener<Store> listener) {
        this.mOrderingConnector.getStoreFromList(deliveryTime, isNormalOrder, orderAmount, storeIds, listener);
    }

    public void getGeoFencingConfiguration(AsyncListener<GeoFencingConfiguration> listener) {
        this.mConfigurationConnector.getGeoFencingConfiguration(listener);
    }

    public void enteredStoreBoundaryForOrder(String checkInCode, String storeId, AsyncListener<MWBoundaryCrossCheckInResponse> listener) {
        this.mOrderingConnector.enteredStoreBoundaryForOrder(checkInCode, storeId, listener);
    }

    public void orderUnAttendedCheckIn(String checkInCode, OrderUnAttendedCheckIn orderUnAttendedCheckIn, AsyncListener<OrderResponse> listener) {
        this.mOrderingConnector.orderUnAttendedCheckIn(checkInCode, orderUnAttendedCheckIn, listener);
    }

    public void getUpsellItems(Order order, AsyncListener<int[]> listener) {
        this.mOrderingConnector.getUpsellItems(order, listener);
    }

    public AsyncToken checkMobileOrderingSupport(Integer storeId, AsyncListener<Store> requestListener) {
        return this.mOrderingConnector.checkMobileOrderingSupport(storeId, requestListener);
    }

    public AsyncToken checkMobileOrderingSupportForStores(List<Store> stores, Location location, AsyncListener<List<Store>> requestListener) {
        return this.mOrderingConnector.checkMobileOrderingSupportForStores(stores, location, requestListener);
    }

    public AsyncToken totalize(Order order, AsyncListener<OrderResponse> requestListener) {
        return this.mOrderingConnector.totalize(order, requestListener);
    }

    public AsyncToken checkin(Order order, String checkinData, String password, AsyncListener<OrderResponse> requestListener) {
        return this.mOrderingConnector.checkin(order, checkinData, password, requestListener);
    }

    public AsyncToken checkinKiosk(Order order, String password, AsyncListener<KioskCheckinResponse> requestListener) {
        return this.mOrderingConnector.checkinKiosk(order, password, requestListener);
    }

    public AsyncToken preparePayment(Order order, AsyncListener<OrderResponse> requestListener) {
        return this.mOrderingConnector.preparePayment(order, requestListener);
    }

    public AsyncToken foundationalCheckIn(Order order, AsyncListener<OrderResponse> requestListener) {
        return this.mOrderingConnector.foundationalCheckIn(order, requestListener);
    }

    public AsyncToken validate(Order order, AsyncListener<OrderResponse> requestListener) {
        return this.mOrderingConnector.validate(order, requestListener);
    }

    public AsyncToken lookupDeliveryCharge(Order order, AsyncListener<OrderResponse> requestListener) {
        return this.mOrderingConnector.lookupDeliveryCharge(order, requestListener);
    }

    public AsyncToken checkout(Order order, String password, CustomerAddress address, AsyncListener<OrderResponse> requestListener) {
        return this.mOrderingConnector.checkout(order, password, address, requestListener);
    }

    public AsyncToken getDeliveryStatus(String orderNumber, AsyncListener<DeliveryStatusResponse> requestListener) {
        return this.mOrderingConnector.getDeliveryStatus(orderNumber, requestListener);
    }

    public AsyncToken getDeliveryStatus(AsyncListener<List<DeliveryStatusResponse>> requestListener) {
        return this.mOrderingConnector.getDeliveryStatus(requestListener);
    }

    public void getRecipeForId(String itemId, AsyncListener<NutritionRecipe> listener) {
        this.mNutritionConnector.getRecipeForId(itemId, listener);
    }

    public void getRecipeForExternalId(String externalId, AsyncListener<NutritionRecipe> listener) {
        this.mNutritionConnector.getRecipeForExternalId(externalId, listener);
    }

    public void getAllRecipes(AsyncListener<List<NutritionRecipe>> listener) {
        this.mNutritionConnector.getAllRecipes(listener);
    }

    public void getAllRecipesForCategory(String categoryId, AsyncListener<List<NutritionRecipe>> listener) {
        this.mNutritionConnector.getAllRecipesForCategory(categoryId, listener);
    }

    public void getAllCategories(AsyncListener<List<Category>> listener) {
        this.mNutritionConnector.getAllCategories(listener);
    }

    public void populateFullRecipeDetails(NutritionRecipe recipe, AsyncListener<NutritionRecipe> listener) {
        this.mNutritionConnector.populateFullRecipeDetails(recipe, listener);
    }

    public AsyncToken setOfferExpiration(Integer offerId, Date expStartDate, Date expEndDate, AsyncListener listener) {
        return this.mOffersConnector.setOfferExpiration(offerId, expStartDate, expEndDate, listener);
    }

    public AsyncToken setDefaultAddress(String username, CustomerAddress address, AsyncListener<Boolean> listener) {
        return this.mCustomerConnector.setDefaultAddress(username, address, listener);
    }

    public void setBaseImagePath(String mBaseImagePath) {
        this.mNutritionConnector.setBaseImagePath(mBaseImagePath);
    }

    public void getRecipesForCategory(String categoryId, AsyncListener<List<NutritionRecipe>> listener) {
        this.mNutritionConnector.getRecipesForCategory(categoryId, listener);
    }

    @Deprecated
    public AsyncToken enquireBalanceList(CustomerProfile profile, AsyncListener<List<Object>> listener) {
        listener.onResponse(null, null, new AsyncException("Not supported"));
        return new AsyncToken("Not supported");
    }

    @Deprecated
    public AsyncToken enquireBalance(CustomerProfile profile, int customerPaymentId, AsyncListener listener) {
        listener.onResponse(null, null, new AsyncException("Not supported"));
        return new AsyncToken("Not supported");
    }

    @Deprecated
    public AsyncToken eArchCardTenderAmounts(AsyncListener listener) {
        listener.onResponse(null, null, new AsyncException("Not supported"));
        return new AsyncToken("Not supported");
    }

    @Deprecated
    public AsyncToken eArchCardPurchase(CustomerProfile profile, PaymentCard paymentCard, String cardName, double amount, AsyncListener<CustomerPaymentAccount> listener) {
        listener.onResponse(null, null, new AsyncException("Not supported"));
        return new AsyncToken("Not supported");
    }

    @Deprecated
    public AsyncToken balanceMergeTransfer(CustomerProfile profile, Object sourceECard, Object destinationECard, AsyncListener<Boolean> listener) {
        listener.onResponse(null, null, new AsyncException("Not supported"));
        return new AsyncToken("Not supported");
    }

    @Deprecated
    public AsyncToken getAutoReloadConfiguration(CustomerProfile profile, Object card, AsyncListener<Object> listener) {
        listener.onResponse(null, null, new AsyncException("Not supported"));
        return new AsyncToken("Not supported");
    }

    @Deprecated
    public AsyncToken configureAutoReload(CustomerProfile profile, int sourceCardId, Object destinationCard, Object autoReloadType, double autoReloadAmount, double thresholdAmount, AsyncListener listener) {
        listener.onResponse(null, null, new AsyncException("Not supported"));
        return new AsyncToken("Not supported");
    }

    @Deprecated
    public AsyncToken generatePaymentBarCode(CustomerProfile profile, int paymentMethodId, AsyncListener listener) {
        listener.onResponse(null, null, new AsyncException("Not supported"));
        return new AsyncToken("Not supported");
    }

    @Deprecated
    public AsyncToken executePaymentByBarCode(String sessionId, Store store, String paymentBarCode, double amount, AsyncListener listener) {
        listener.onResponse(null, null, new AsyncException("Not supported"));
        return new AsyncToken("Not supported");
    }

    @Deprecated
    public AsyncToken getOrderByCode(String storeId, String barCode, AsyncListener listener) {
        listener.onResponse(null, null, new AsyncException("Not supported"));
        return new AsyncToken("Not supported");
    }

    @Deprecated
    public AsyncToken cancelPayment(String storeId, String orderId, AsyncListener listener) {
        listener.onResponse(null, null, new AsyncException("Not supported"));
        return new AsyncToken("Not supported");
    }

    public void getServerConfiguration(@NonNull AsyncListener<Map<String, Object>> listener) {
        this.mConfigurationConnector.getServerConfiguration(listener);
    }

    public AsyncToken registerCard(String url, String sessionId, String customerId, boolean isOneTimePayment, AsyncListener<PaymentCard> listener) {
        return this.mCustomerConnector.registerCard(url, sessionId, customerId, isOneTimePayment, listener);
    }
}
