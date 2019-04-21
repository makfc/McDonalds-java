package com.mcdonalds.sdk.modules.customer;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import com.google.gson.reflect.TypeToken;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.ConnectorManager;
import com.mcdonalds.sdk.connectors.CustomerConnector;
import com.mcdonalds.sdk.connectors.middleware.model.MWPaymentURLPostInfo;
import com.mcdonalds.sdk.connectors.middleware.model.MWWechatInfoData;
import com.mcdonalds.sdk.connectors.middleware.response.MWJSONResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWWechatTokenResponse;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.MWCustomerSecurityConnector;
import com.mcdonalds.sdk.modules.BaseModule;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerProfile.AccountVerificationType;
import com.mcdonalds.sdk.modules.delivery.DeliveryModule;
import com.mcdonalds.sdk.modules.models.AddressValidationResult;
import com.mcdonalds.sdk.modules.models.AuthenticationParameters;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.modules.models.CustomerOrder;
import com.mcdonalds.sdk.modules.models.FavoriteItem;
import com.mcdonalds.sdk.modules.models.FeedBackType;
import com.mcdonalds.sdk.modules.models.GetAddressElementsResult;
import com.mcdonalds.sdk.modules.models.NotificationPreferences;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.PaymentCard;
import com.mcdonalds.sdk.modules.models.SocialNetwork;
import com.mcdonalds.sdk.modules.models.StoreFavoriteInfo;
import com.mcdonalds.sdk.modules.notification.NotificationModule;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.configuration.AppParameters;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.CatalogManager;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.data.provider.Contract;
import com.mcdonalds.sdk.services.data.provider.Contract.CurrentStore;
import com.mcdonalds.sdk.services.data.provider.Contract.Favorites;
import com.mcdonalds.sdk.services.data.repository.FeedBackTypeRepository;
import com.mcdonalds.sdk.services.data.repository.SocialNetworkRepository;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.mcdonalds.sdk.services.network.SessionManager;
import com.mcdonalds.sdk.services.notifications.NotificationCenter;
import com.mcdonalds.sdk.utils.ListUtils;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerModule extends BaseModule {
    public static final String ACCOUNT_TYPE = "com.mcdonalds.gma.hongkong.account";
    private static final String ASYNC_TOKEN_PREFIX = CustomerModule.class.getSimpleName();
    public static final String AUTHORITY = "com.mcdonalds.gma.hongkong.provider";
    private static final long CACHE_EXP_INTERVAL = 86400;
    public static final String CACHE_KEY_CURRENT_PROFILE = "CACHE_KEY_CURRENT_PROFILE";
    public static final String CACHE_KEY_CURRENT_STORE = "CACHE_KEY_CURRENT_STORE";
    private static final String CLEAR_BASKET_ON_LOGOUT_KEY = "interface.ordering.clearBasketOnLogout";
    private static final String CUSTOMER_CONNECTOR = "modules.customer.connector";
    private static final Type CUSTOMER_TYPE = new C25292().getType();
    private static final String DISABLE_STORE_PREF_KEY = "modules.storeLocator.disablePreferenceNotificationOnCurrentStoreUpdate";
    public static final String DUMMY_ACCOUNT_NAME = "Synchronization";
    private static final String IGNORE_MAX_PAYMENT_CARDS = "modules.customer.ignoreMaxPaymentCardsFromBackend";
    private static final String MAX_PAYMENT_CARDS_KEY = "modules.customer.maxPaymentCards";
    public static final String MCD_LOGIN_STATE_CHANGED = "MCD_LOGIN_STATE_CHANGED";
    public static final String MCD_SOCIAL_LOGIN_TOKEN_CHANGED = "MCD_SOCIAL_LOGIN_TOKEN_CHANGED";
    private static final String MESSAGE_ID_PREFIX = "h";
    public static final String NAME = "customer";
    public static final String PREFERENCE_LOGGED_USERS = "logged_users";
    private static final Type STORE_TYPE = new C25181().getType();
    private AccountManager mAccountManager;
    private final String mConnectorImpl = ((String) Configuration.getSharedInstance().getValueForKey(CUSTOMER_CONNECTOR));
    private Context mContext;
    private CustomerProfile mCurrentProfile;
    private Store mCurrentStore;
    private final List<Store> mFavoriteStores = new ArrayList();
    private boolean mLoggedIn;
    private Store mNearByStore;
    private Store mPickupStore;
    private final List<CustomerOrder> mRecentOrders = new ArrayList();
    private final Map<AsyncToken, AsyncToken> mTokenMap = new HashMap();

    /* renamed from: com.mcdonalds.sdk.modules.customer.CustomerModule$1 */
    static class C25181 extends TypeToken<Store> {
        C25181() {
        }
    }

    /* renamed from: com.mcdonalds.sdk.modules.customer.CustomerModule$2 */
    static class C25292 extends TypeToken<CustomerProfile> {
        C25292() {
        }
    }

    /* renamed from: com.mcdonalds.sdk.modules.customer.CustomerModule$3 */
    class C25403 implements AsyncListener<NotificationPreferences> {
        C25403() {
        }

        public void onResponse(NotificationPreferences response, AsyncToken token, AsyncException exception) {
        }
    }

    public Store getNearByStore() {
        return this.mNearByStore;
    }

    public void setNearByStore(Store mNearByStore) {
        this.mNearByStore = mNearByStore;
    }

    @Deprecated
    public CustomerModule(Context context) {
        this.mContext = context;
        this.mAccountManager = (AccountManager) this.mContext.getSystemService("account");
        getSyncAccount();
    }

    public CustomerProfile getCurrentProfile() {
        if (this.mCurrentProfile == null) {
            this.mCurrentProfile = (CustomerProfile) LocalDataManager.getSharedInstance().getObjectFromCache(CACHE_KEY_CURRENT_PROFILE, CUSTOMER_TYPE);
        }
        return this.mCurrentProfile;
    }

    public void setCurrentProfile(CustomerProfile currentProfile) {
        String previousPassword = this.mCurrentProfile == null ? null : this.mCurrentProfile.getPassword();
        List<FavoriteItem> favoriteItems = this.mCurrentProfile == null ? new ArrayList() : this.mCurrentProfile.getFavoriteItems();
        if (!(currentProfile == null || this.mCurrentProfile == null || !this.mCurrentProfile.isUsingSocialLogin())) {
            currentProfile.setUsingSocialLogin(this.mCurrentProfile.isUsingSocialLogin());
            currentProfile.setSocialUserID(this.mCurrentProfile.getSocialUserID());
            currentProfile.setSocialServiceAuthenticationID(this.mCurrentProfile.getSocialServiceAuthenticationID());
            currentProfile.setSocialAuthenticationToken(this.mCurrentProfile.getSocialAuthenticationToken());
        }
        this.mCurrentProfile = currentProfile;
        if (this.mCurrentProfile != null && this.mCurrentProfile.getPassword() == null) {
            this.mCurrentProfile.setPassword(previousPassword);
        }
        if (!(this.mCurrentProfile == null || !ListUtils.isEmpty(this.mCurrentProfile.getFavoriteItems()) || ListUtils.isEmpty(favoriteItems))) {
            this.mCurrentProfile.setFavoriteItems(favoriteItems);
        }
        if (this.mCurrentProfile == null) {
            LocalDataManager.getSharedInstance().remove(CACHE_KEY_CURRENT_PROFILE, true);
        }
        updateCurrentProfileInCache();
        if (this.mCurrentProfile == null) {
            return;
        }
        if (this.mCurrentProfile.getCustomerId() == 0 || this.mCurrentProfile.getUserName() == null) {
            Thread.dumpStack();
        }
    }

    private void updateCurrentProfileInCache() {
        LocalDataManager.getSharedInstance().addObjectToCache(CACHE_KEY_CURRENT_PROFILE, this.mCurrentProfile, -1);
    }

    public boolean isLoggedIn() {
        return this.mLoggedIn;
    }

    public void setLoggedInState(boolean isLoggedIn) {
        this.mLoggedIn = isLoggedIn;
    }

    public boolean isFirstTimeLoginOnDevice(CustomerProfile profile) {
        LocalDataManager manager = LocalDataManager.getSharedInstance();
        String current = Long.valueOf(profile.getCustomerId()).toString();
        String usersString = manager.getFirstLogin();
        if (usersString == null) {
            manager.setFirstLogin(current);
            return true;
        }
        for (String user : usersString.split(",")) {
            if (user.equals(current)) {
                return false;
            }
        }
        manager.setFirstLogin(String.format("%s,%s", new Object[]{usersString, current}));
        return true;
    }

    private void setCurrentProfile(CustomerProfile profile, boolean loggedIn) {
        LocalDataManager.getSharedInstance().setPrefSavedLogin(profile.getEmailAddress());
        setCurrentProfile(profile);
        this.mLoggedIn = loggedIn;
    }

    public List<Store> getFavoriteStores() {
        if (this.mFavoriteStores != null) {
            return Collections.unmodifiableList(this.mFavoriteStores);
        }
        return null;
    }

    public void setFavoriteStores(List<Store> favoriteStores) {
        this.mFavoriteStores.clear();
        this.mFavoriteStores.addAll(favoriteStores);
    }

    public boolean hasRecentOrders() {
        return !this.mRecentOrders.isEmpty();
    }

    public List<CustomerOrder> getRecentOrders() {
        if (this.mRecentOrders != null) {
            return Collections.unmodifiableList(this.mRecentOrders);
        }
        return null;
    }

    public Store getCurrentStore() {
        if (this.mCurrentStore == null) {
            this.mCurrentStore = (Store) LocalDataManager.getSharedInstance().getObjectFromCache(CACHE_KEY_CURRENT_STORE, STORE_TYPE);
        }
        return this.mCurrentStore;
    }

    public void setCurrentStore(Store currentStore) {
        if (currentStore != null && !currentStore.equals(getCurrentStore())) {
            this.mCurrentStore = currentStore;
            updateCurrentStoreInCache();
            requestSync();
            boolean shouldUpdateCustomerNotificationPreferenceOnStoreChange = !Configuration.getSharedInstance().getBooleanForKey(DISABLE_STORE_PREF_KEY);
            if (getCurrentProfile() != null && shouldUpdateCustomerNotificationPreferenceOnStoreChange) {
                setNotificationPreferences(getCurrentProfile().getNotificationPreferences(), new C25403());
            }
        }
    }

    public void clearCurrentStore() {
        this.mCurrentStore = null;
        LocalDataManager.getSharedInstance().deleteObjectFromCache(CACHE_KEY_CURRENT_STORE);
    }

    public void updateCurrentStoreInfo() {
        updateCurrentStoreInfo(null);
    }

    public void updateCurrentStoreInfo(@Nullable final AsyncListener<Store> listener) {
        Store store = getCurrentStore();
        if (store != null) {
            OrderingModule orderingModule = (OrderingModule) ModuleManager.getModule("ordering");
            if (orderingModule != null) {
                orderingModule.getStoreInformation(store, new AsyncListener<Store>() {
                    public void onResponse(Store store, AsyncToken asyncToken, AsyncException e) {
                        if (e == null) {
                            CustomerModule.this.mCurrentStore.mergeWithEcpInfo(store);
                            CustomerModule.this.updateCurrentStoreInCache();
                            CustomerModule.this.mContext.getContentResolver().notifyChange(CurrentStore.CONTENT_URI, null, false);
                        }
                        if (listener != null) {
                            listener.onResponse(store, asyncToken, e);
                        }
                    }
                });
            }
        }
    }

    private void updateCurrentStoreInCache() {
        LocalDataManager.getSharedInstance().addObjectToCache(CACHE_KEY_CURRENT_STORE, this.mCurrentStore, -1);
    }

    public boolean isSyncActive() {
        return CatalogManager.getSyncStatus() == 0;
    }

    public void addSyncListener(ContentObserver listener) {
        this.mContext.getContentResolver().registerContentObserver(Contract.CONTENT_URI, false, listener);
    }

    public void removeSyncListener(ContentObserver listener) {
        this.mContext.getContentResolver().unregisterContentObserver(listener);
    }

    public void requestSync() {
        CatalogManager.requestSync();
    }

    public void requestSocialNetworkSync() {
        CatalogManager.requestSocialNetworkSync();
    }

    public AsyncToken resetPassword(String username, @NonNull final AsyncListener<Void> listener) {
        final AsyncToken moduleToken = new AsyncToken("resetPassword");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).resetPassword(username, new AsyncListener<Void>() {
            public void onResponse(Void response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    CustomerModule.this.logout(listener);
                } else {
                    listener.onResponse(response, moduleToken, exception);
                }
                CustomerModule.this.removeTokenMapping(moduleToken, token);
            }
        }));
        return moduleToken;
    }

    public AsyncToken resetPassword(String username, String emailAddress, String mobilePhone, @NonNull final AsyncListener<Void> listener) {
        final AsyncToken moduleToken = new AsyncToken("resetPassword");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).resetPassword(username, emailAddress, mobilePhone, new AsyncListener<Void>() {
            public void onResponse(Void response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    CustomerModule.this.logout(listener);
                } else {
                    listener.onResponse(response, moduleToken, exception);
                }
                CustomerModule.this.removeTokenMapping(moduleToken, token);
            }
        }));
        return moduleToken;
    }

    public AsyncToken resendActivation(CustomerProfile profile, @NonNull AsyncListener<Void> listener) {
        AsyncToken moduleToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "#resendActivation");
        ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).resendActivation(profile, listener);
        return moduleToken;
    }

    public AsyncToken resendActivationCode(String code, @NonNull AsyncListener<Void> listener) {
        AsyncToken moduleToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "#resendActivationCode");
        ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).resendActivationCode(code, listener);
        return moduleToken;
    }

    public AsyncToken register(CustomerProfile profile, @NonNull final AsyncListener<CustomerProfile> listener) {
        if (profile.getNotificationPreferences() == null) {
            profile.setNotificationPreferences(NotificationPreferences.fromConfig());
        } else {
            profile.getNotificationPreferences().updateWithConfig();
        }
        final AsyncToken moduleToken = new AsyncToken(JiceArgs.EVENT_REGISTER);
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).register(profile, new AsyncListener<CustomerProfile>() {
            public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    CustomerModule.this.setCurrentProfile(response);
                }
                CustomerModule.this.removeTokenMapping(moduleToken, token);
                listener.onResponse(response, moduleToken, exception);
            }
        }));
        return moduleToken;
    }

    public AsyncToken authenticate(AuthenticationParameters parameters, @NonNull final AsyncListener<CustomerProfile> listener) {
        final AsyncToken moduleToken = new AsyncToken("authenticateWithParameters");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).authenticate(parameters, new AsyncListener<CustomerProfile>() {
            public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
                CustomerModule.this.removeTokenMapping(moduleToken, token);
                if (exception != null || (!(response == null || response.isEmailActivated()) || ((response != null && response.isPasswordChangeRequired()) || !(response == null || !response.isUsingSocialLogin() || response.isSocialAccountLoginRegistered())))) {
                    CustomerModule.this.mLoggedIn = false;
                } else {
                    CustomerModule.this.mLoggedIn = true;
                    CustomerModule.this.setCurrentProfile(response);
                }
                NotificationCenter.getSharedInstance().postNotification(CustomerModule.MCD_LOGIN_STATE_CHANGED);
                listener.onResponse(response, moduleToken, exception);
            }
        }));
        return moduleToken;
    }

    public AsyncToken deregister(String reason, @NonNull final AsyncListener<String> listener) {
        final AsyncToken moduleToken = new AsyncToken("deregister");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).deregister(getCurrentProfile(), reason, new AsyncListener<String>() {
            public void onResponse(String response, AsyncToken token, AsyncException exception) {
                if (!response.isEmpty()) {
                    CustomerModule.this.setCurrentProfile(null);
                }
                CustomerModule.this.removeTokenMapping(moduleToken, token);
                listener.onResponse(response, moduleToken, exception);
            }
        }));
        return moduleToken;
    }

    public AsyncToken changePassword(String newPassword, @Nullable String authorizationCode, @NonNull final AsyncListener<Void> listener) {
        if (getCurrentProfile() == null) {
            listener.onResponse(null, null, null);
            return null;
        }
        final AsyncToken moduleToken = new AsyncToken("changePassword");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).changePassword(getCurrentProfile().getUserName(), getCurrentProfile().getPassword(), newPassword, authorizationCode, new AsyncListener<Void>() {
            public void onResponse(Void response, AsyncToken token, AsyncException exception) {
                if (exception != null) {
                    AsyncException.report(exception);
                }
                CustomerModule.this.removeTokenMapping(moduleToken, token);
                listener.onResponse(response, moduleToken, exception);
            }
        }));
        return moduleToken;
    }

    public AsyncToken refreshCustomerData(@NonNull final AsyncListener<CustomerProfile> listener) {
        final AsyncToken moduleToken = new AsyncToken("refreshCustomerData");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).getCustomerData(getCurrentProfile().getUserName(), new AsyncListener<CustomerProfile>() {
            public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    CustomerModule.this.setCurrentProfile(response);
                    listener.onResponse(CustomerModule.this.getCurrentProfile(), moduleToken, null);
                } else {
                    listener.onResponse(null, moduleToken, exception);
                }
                CustomerModule.this.removeTokenMapping(moduleToken, token);
            }
        }));
        return moduleToken;
    }

    public AsyncToken registerExtSocialNetworkForced(CustomerProfile profile, final AsyncListener<MWJSONResponse> listener) {
        final AsyncToken moduleToken = new AsyncToken("registerExtSocialNetworkForced");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).registerExtSocialNetworkForced(profile, new AsyncListener<MWJSONResponse>() {
            public void onResponse(MWJSONResponse response, AsyncToken token, AsyncException exception) {
                listener.onResponse(response, moduleToken, exception);
                CustomerModule.this.removeTokenMapping(moduleToken, token);
            }
        }));
        return moduleToken;
    }

    public AsyncToken addLoginMethod(CustomerProfile profile, final AsyncListener<MWJSONResponse> listener) {
        final AsyncToken moduleToken = new AsyncToken("addLoginMethod");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).addLoginMethod(profile, new AsyncListener<MWJSONResponse>() {
            public void onResponse(MWJSONResponse response, AsyncToken token, AsyncException exception) {
                listener.onResponse(response, moduleToken, exception);
                CustomerModule.this.removeTokenMapping(moduleToken, token);
            }
        }));
        return moduleToken;
    }

    public AsyncToken getSocialNetworkAccessToken(int socialId, String code, final AsyncListener<MWWechatInfoData> listener) {
        final AsyncToken moduleToken = new AsyncToken("addLoginMethod");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).getSocialNetworkAccessToken(socialId, code, new AsyncListener<MWWechatTokenResponse>() {
            public void onResponse(MWWechatTokenResponse response, AsyncToken token, AsyncException exception) {
                if (response == null || exception != null) {
                    AsyncException.report(exception);
                } else {
                    listener.onResponse(response.getData(), moduleToken, exception);
                }
                CustomerModule.this.removeTokenMapping(moduleToken, token);
            }
        }));
        return moduleToken;
    }

    public AsyncToken updateCustomerProfile(CustomerProfile profile, @NonNull AsyncListener<CustomerProfile> listener) {
        final AsyncToken moduleToken = new AsyncToken("updateCustomerProfile");
        final CustomerConnector customerConnector = (CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl);
        final CustomerProfile customerProfile = profile;
        final AsyncListener<CustomerProfile> asyncListener = listener;
        addTokenMapping(moduleToken, customerConnector.updateProfile(profile, new AsyncListener<CustomerProfile>() {
            public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
                if (exception != null) {
                    AsyncException.report(exception);
                } else {
                    CustomerModule.this.removeTokenMapping(moduleToken, token);
                    if (customerProfile.isUsingSocialLogin()) {
                        response.setUsingSocialLogin(true);
                        response.setUsingSocialLoginWithoutEmail(customerProfile.isUsingSocialLoginWithoutEmail());
                        response.setSocialServiceAuthenticationID(customerProfile.getSocialServiceAuthenticationID());
                        response.setSocialAuthenticationToken(customerProfile.getSocialAuthenticationToken());
                        response.setSocialUserID(customerProfile.getSocialUserID());
                    }
                    if (!(customerConnector instanceof MWCustomerSecurityConnector)) {
                        CustomerModule.this.setCurrentProfile(response);
                    }
                }
                asyncListener.onResponse(response, moduleToken, exception);
            }
        }));
        return moduleToken;
    }

    @Deprecated
    public AsyncToken setNotificationPreferences(CustomerProfile profile, NotificationPreferences notifications, @NonNull AsyncListener<NotificationPreferences> listener) {
        return setNotificationPreferences(notifications, listener);
    }

    public AsyncToken setNotificationPreferences(NotificationPreferences notifications, @NonNull final AsyncListener<NotificationPreferences> listener) {
        final AsyncToken moduleToken = new AsyncToken("setPreferencesMap");
        CustomerConnector customerConnector = (CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl);
        NotificationModule notificationModule = (NotificationModule) ModuleManager.getModule("notification");
        if (notificationModule != null && notificationModule.getRegistrationId() == null) {
            listener.onResponse(null, moduleToken, new AsyncException("No registration id"));
        }
        addTokenMapping(moduleToken, customerConnector.setNotificationPreferences(this.mCurrentProfile, notifications, new AsyncListener<NotificationPreferences>() {
            public void onResponse(NotificationPreferences response, AsyncToken token, AsyncException exception) {
                if (exception != null) {
                    AsyncException.report(exception);
                } else {
                    CustomerModule.this.removeTokenMapping(moduleToken, token);
                }
                listener.onResponse(response, moduleToken, exception);
            }
        }));
        return moduleToken;
    }

    public AsyncToken trackNotification(int messageId, final String deliveryId, final int tagId) {
        AsyncToken moduleToken = new AsyncToken("trackNotification");
        CustomerConnector customerConnector = (CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl);
        final String hexMessageId = MESSAGE_ID_PREFIX + Integer.toHexString(messageId);
        addTokenMapping(moduleToken, customerConnector.trackNotification(getCurrentProfile(), hexMessageId, deliveryId, tagId, new AsyncListener<Void>() {
            public void onResponse(Void response, AsyncToken token, AsyncException exception) {
                CustomerModule.this.logNotificationTracked(hexMessageId, deliveryId, tagId);
            }
        }));
        return moduleToken;
    }

    private void logNotificationTracked(String messageId, String deliveryId, int tagId) {
        MCDLog.info(String.format("Notification Tracking sent.\n Message ID: %s,\n Delivery ID: %s,\n Tag ID: %s", new Object[]{messageId, deliveryId, Integer.valueOf(tagId)}));
    }

    public AsyncToken getSocialLoginCatalog(@NonNull final AsyncListener<List<SocialNetwork>> listener) {
        final AsyncToken moduleToken = new AsyncToken("getSocialLoginCatalog");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).getSocialLoginCatalogUpdate(new AsyncListener<List<SocialNetwork>>() {
            public void onResponse(List<SocialNetwork> response, AsyncToken token, AsyncException exception) {
                CustomerModule.this.removeTokenMapping(moduleToken, token);
                listener.onResponse(response, moduleToken, exception);
            }
        }));
        return moduleToken;
    }

    public AsyncToken getCatalogUpdated(CustomerProfile profile, @NonNull final AsyncListener<?> listener) {
        if (!isSyncActive()) {
            requestSync();
        }
        ContentObserver observer = new ContentObserver(new Handler()) {
            public boolean deliverSelfNotifications() {
                return super.deliverSelfNotifications();
            }

            public void onChange(boolean selfChange) {
                if (!CustomerModule.this.isSyncActive()) {
                    listener.onResponse(null, null, null);
                    CustomerModule.this.removeSyncListener(this);
                }
            }
        };
        if (isSyncActive()) {
            addSyncListener(observer);
        } else {
            listener.onResponse(null, null, null);
        }
        return null;
    }

    public AsyncToken getCustomerData(String username, @NonNull final AsyncListener<CustomerProfile> listener) {
        final AsyncToken moduleToken = new AsyncToken("updateCustomerProfile");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).getCustomerData(username, new AsyncListener<CustomerProfile>() {
            public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
                if (exception != null) {
                    AsyncException.report(exception);
                }
                CustomerModule.this.removeTokenMapping(moduleToken, token);
                CustomerModule.this.setCurrentProfile(response);
                listener.onResponse(response, moduleToken, exception);
            }
        }));
        return moduleToken;
    }

    public boolean needsFavoriteProductsRefresh() {
        return this.mCurrentProfile != null && this.mCurrentProfile.needsFavoriteItemsRefresh();
    }

    public AsyncToken updateFavoriteProducts(final AsyncListener<List<FavoriteItem>> listener) {
        if (this.mCurrentProfile == null || !ModuleManager.isModuleEnabled("ordering").booleanValue()) {
            AsyncException.report("Illegal Arguments");
            if (listener == null) {
                return null;
            }
            listener.onResponse(null, null, null);
            return null;
        }
        final AsyncToken asyncToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "#getFavoriteProducts");
        ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).getFavoriteProducts(this.mCurrentProfile.getUserName(), new AsyncListener<List<FavoriteItem>>() {
            public void onResponse(List<FavoriteItem> response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    if (CustomerModule.this.mCurrentProfile != null) {
                        CustomerModule.this.mCurrentProfile.setFavoriteItems(response);
                        CustomerModule.this.updateCurrentProfileInCache();
                        CustomerModule.this.mContext.getContentResolver().notifyChange(Favorites.CONTENT_URI, null);
                    }
                    if (listener != null) {
                        listener.onResponse(response, asyncToken, null);
                    }
                } else if (listener != null) {
                    listener.onResponse(response, asyncToken, exception);
                }
            }
        });
        return asyncToken;
    }

    @Deprecated
    public AsyncToken getFavoriteProducts(CustomerProfile customerProfile, @NonNull AsyncListener<List<FavoriteItem>> listener) {
        listener.onResponse(getFavoriteProducts(), null, null);
        return null;
    }

    public List<FavoriteItem> getFavoriteProducts() {
        return this.mCurrentProfile == null ? new ArrayList() : this.mCurrentProfile.getFavoriteItems();
    }

    public AsyncToken addFavoriteProducts(CustomerProfile profile, List<OrderProduct> favoriteProducts, String favoriteName, boolean isProduct, @NonNull final AsyncListener<Boolean> listener) {
        return addFavoriteProductsReturningItems(profile, favoriteProducts, favoriteName, isProduct, new AsyncListener<List<FavoriteItem>>() {
            public void onResponse(List<FavoriteItem> list, AsyncToken token, AsyncException exception) {
                listener.onResponse(Boolean.valueOf(exception == null), token, exception);
            }
        });
    }

    public AsyncToken addFavoriteProductsReturningItems(CustomerProfile profile, List<OrderProduct> favoriteProducts, String favoriteName, boolean isProduct, @NonNull final AsyncListener<List<FavoriteItem>> listener) {
        final AsyncToken asyncToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "#addFavoriteProducts");
        ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).addFavoriteProducts(this.mCurrentProfile.getUserName(), favoriteName, favoriteProducts, Boolean.valueOf(isProduct), new AsyncListener<List<FavoriteItem>>() {
            public void onResponse(List<FavoriteItem> response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    CustomerModule.this.mCurrentProfile.setFavoriteItems(response);
                    CustomerModule.this.updateCurrentProfileInCache();
                    CustomerModule.this.mContext.getContentResolver().notifyChange(Favorites.CONTENT_URI, null);
                    listener.onResponse(response, asyncToken, null);
                    return;
                }
                listener.onResponse(response, asyncToken, exception);
            }
        });
        return asyncToken;
    }

    public AsyncToken deleteFavoriteProducts(CustomerProfile profile, List<FavoriteItem> favoriteItems, @NonNull AsyncListener<Boolean> listener) {
        final AsyncToken asyncToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "#deleteFavoriteProducts");
        final List<FavoriteItem> list = favoriteItems;
        final AsyncListener<Boolean> asyncListener = listener;
        final CustomerProfile customerProfile = profile;
        ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).deleteFavoriteProducts(this.mCurrentProfile.getUserName(), favoriteItems, new AsyncListener<List<FavoriteItem>>() {
            public void onResponse(List<FavoriteItem> response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    Log.d(asyncToken.getPrefix(), response.toString());
                    CustomerModule.this.mCurrentProfile.getFavoriteItems().removeAll(list);
                    CustomerModule.this.updateCurrentProfileInCache();
                    CustomerModule.this.mContext.getContentResolver().notifyChange(Favorites.CONTENT_URI, null);
                    asyncListener.onResponse(Boolean.valueOf(true), asyncToken, null);
                    customerProfile.setFavoriteItems(response);
                }
            }
        });
        return asyncToken;
    }

    public AsyncToken addFavoriteStores(final List<Store> favoriteStores, CustomerProfile profile, @NonNull final AsyncListener<List<Store>> listener) {
        if (profile == null || favoriteStores == null) {
            listener.onResponse(null, null, new AsyncException("Illegal Arguments"));
            return null;
        }
        final AsyncToken moduleToken = new AsyncToken("addFavoriteStores");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).addFavoriteLocations(favoriteStores, profile.getUserName(), new AsyncListener<List<Store>>() {
            public void onResponse(List<Store> storeList, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    if (storeList != null) {
                        int i;
                        Store store;
                        SparseArray<Store> storesInfo = new SparseArray();
                        int size = storeList.size();
                        for (i = 0; i < size; i++) {
                            store = (Store) storeList.get(i);
                            storesInfo.put(store.getStoreId(), store);
                        }
                        size = favoriteStores.size();
                        for (i = 0; i < size; i++) {
                            store = (Store) favoriteStores.get(i);
                            Store favStoreInfo = (Store) storesInfo.get(store.getStoreId());
                            if (favStoreInfo != null) {
                                store.setStoreFavoriteName(favStoreInfo.getStoreFavoriteName());
                                store.setStoreFavoriteId(favStoreInfo.getStoreFavoriteId());
                                CustomerModule.this.mFavoriteStores.add(store);
                            }
                        }
                    } else {
                        exception = new AsyncException("Error adding store to Favorites");
                    }
                    listener.onResponse(CustomerModule.this.mFavoriteStores, moduleToken, exception);
                } else {
                    listener.onResponse(null, moduleToken, exception);
                }
                CustomerModule.this.removeTokenMapping(moduleToken, token);
            }
        }));
        return moduleToken;
    }

    public AsyncToken retrieveFavoriteStores(CustomerProfile profile, @NonNull final AsyncListener<List<StoreFavoriteInfo>> listener) {
        if (profile == null) {
            listener.onResponse(null, null, new AsyncException("Illegal Arguments"));
            return null;
        }
        final AsyncToken moduleToken = new AsyncToken("retrieveFavoriteStores");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).retrieveFavoriteStores(profile.getUserName(), new AsyncListener<List<Store>>() {
            public void onResponse(List<Store> storeList, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    CustomerModule.this.mFavoriteStores.clear();
                    List<StoreFavoriteInfo> storeInfo = new ArrayList();
                    int size = storeList.size();
                    for (int i = 0; i < size; i++) {
                        Store store = (Store) storeList.get(i);
                        StoreFavoriteInfo info = new StoreFavoriteInfo();
                        info.setFavoriteId(store.getStoreFavoriteId().intValue());
                        info.setFavoriteNickName(store.getStoreFavoriteName());
                        info.setStoreId(store.getStoreId());
                        storeInfo.add(info);
                    }
                    listener.onResponse(storeInfo, moduleToken, null);
                } else {
                    listener.onResponse(null, moduleToken, exception);
                }
                CustomerModule.this.removeTokenMapping(moduleToken, token);
            }
        }));
        return moduleToken;
    }

    public AsyncToken renameFavoriteStores(List<Store> favoriteStores, CustomerProfile profile, @NonNull final AsyncListener<List<Store>> listener) {
        if (profile == null || favoriteStores == null) {
            listener.onResponse(null, null, new AsyncException("Illegal Arguments"));
            return null;
        }
        final AsyncToken moduleToken = new AsyncToken("addFavoriteStores");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).renameFavoriteLocations(favoriteStores, profile.getUserName(), new AsyncListener<List<Store>>() {
            public void onResponse(List<Store> storeList, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    int i;
                    SparseArray<Store> storesInfo = new SparseArray();
                    int size = storeList.size();
                    for (i = 0; i < size; i++) {
                        Store store = (Store) storeList.get(i);
                        storesInfo.put(store.getStoreId(), store);
                    }
                    size = CustomerModule.this.mFavoriteStores.size();
                    for (i = 0; i < size; i++) {
                        Store favStore = (Store) CustomerModule.this.mFavoriteStores.get(i);
                        Store resultStore = (Store) storesInfo.get(favStore.getStoreId());
                        if (resultStore != null) {
                            favStore.setStoreFavoriteName(resultStore.getStoreFavoriteName());
                        }
                    }
                    listener.onResponse(CustomerModule.this.getFavoriteStores(), token, null);
                } else {
                    listener.onResponse(null, moduleToken, exception);
                }
                CustomerModule.this.removeTokenMapping(moduleToken, token);
            }
        }));
        return moduleToken;
    }

    public AsyncToken deleteFavoriteStores(List<Integer> favoriteStoresIds, CustomerProfile profile, @NonNull final AsyncListener<List<Store>> listener) {
        if (profile == null || favoriteStoresIds == null) {
            listener.onResponse(null, null, new AsyncException("Illegal Arguments"));
            return null;
        }
        final AsyncToken moduleToken = new AsyncToken("addFavoriteStores");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).deleteFavoriteLocations(favoriteStoresIds, profile.getUserName(), new AsyncListener<List<Store>>() {
            public void onResponse(List<Store> storeList, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    int i;
                    SparseArray<Store> storesInfo = new SparseArray();
                    int size = storeList.size();
                    for (i = 0; i < size; i++) {
                        Store store = (Store) storeList.get(i);
                        storesInfo.put(store.getStoreId(), store);
                    }
                    List<Store> currentFavorites = new ArrayList(CustomerModule.this.mFavoriteStores);
                    CustomerModule.this.mFavoriteStores.clear();
                    CustomerModule.this.mCurrentStore.setStoreFavoriteId(null);
                    CustomerModule.this.mCurrentStore.setStoreFavoriteName(null);
                    size = currentFavorites.size();
                    for (i = 0; i < size; i++) {
                        Store favStore = (Store) currentFavorites.get(i);
                        if (!(storesInfo.get(favStore.getStoreId()) == null || CustomerModule.this.mFavoriteStores.contains(favStore))) {
                            CustomerModule.this.mFavoriteStores.add(favStore);
                            if (CustomerModule.this.mCurrentStore.getStoreId() == favStore.getStoreId()) {
                                CustomerModule.this.mCurrentStore.setStoreFavoriteName(favStore.getStoreFavoriteName());
                                CustomerModule.this.mCurrentStore.setStoreFavoriteId(favStore.getStoreFavoriteId());
                            }
                        }
                    }
                    listener.onResponse(CustomerModule.this.getFavoriteStores(), moduleToken, null);
                } else {
                    listener.onResponse(CustomerModule.this.getFavoriteStores(), moduleToken, exception);
                }
                CustomerModule.this.removeTokenMapping(moduleToken, token);
            }
        }));
        return moduleToken;
    }

    public AsyncToken getPaymentTypeRegistrationURL(int paymentID, Boolean oneTimePayment, @NonNull final AsyncListener<String> listener) {
        final AsyncToken moduleToken = new AsyncToken("getPaymentTypeRegistrationURL");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).getPaymentTypeRegistrationURL(paymentID, oneTimePayment, getCurrentProfile(), new AsyncListener<String>() {
            public void onResponse(String response, AsyncToken token, AsyncException exception) {
                CustomerModule.this.removeTokenMapping(moduleToken, token);
                listener.onResponse(response, moduleToken, exception);
            }
        }));
        return moduleToken;
    }

    public AsyncToken paymentTypePostRegistrationURL(int paymentID, Boolean oneTimePayment, @NonNull final AsyncListener<MWPaymentURLPostInfo> listener) {
        final AsyncToken moduleToken = new AsyncToken("PaymentTypePostRegistrationURL");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).paymentTypePostRegistrationURL(paymentID, oneTimePayment, getCurrentStore() == null ? 0 : getCurrentStore().getStoreId(), getCurrentProfile(), new AsyncListener<MWPaymentURLPostInfo>() {
            public void onResponse(MWPaymentURLPostInfo response, AsyncToken token, AsyncException exception) {
                CustomerModule.this.removeTokenMapping(moduleToken, token);
                listener.onResponse(response, moduleToken, exception);
            }
        }));
        return moduleToken;
    }

    public AsyncToken updatePayment(String paymentProviderData, boolean isPreferred, @NonNull final AsyncListener<CustomerProfile> listener) {
        final AsyncToken moduleToken = new AsyncToken("updatePayment");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).updatePayment(getCurrentProfile().getUserName(), paymentProviderData, isPreferred, new AsyncListener<CustomerProfile>() {
            public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    CustomerModule.this.setCurrentProfile(response);
                }
                CustomerModule.this.removeTokenMapping(moduleToken, token);
                listener.onResponse(CustomerModule.this.getCurrentProfile(), moduleToken, exception);
            }
        }));
        return moduleToken;
    }

    public AsyncToken updatePayments(List<PaymentCard> paymentCards, @NonNull final AsyncListener<CustomerProfile> listener) {
        if (paymentCards == null) {
            listener.onResponse(null, null, new AsyncException("Illegal Arguments"));
            return null;
        }
        final AsyncToken moduleToken = new AsyncToken("updatePayment");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).updatePayments(getCurrentProfile().getUserName(), paymentCards, new AsyncListener<CustomerProfile>() {
            public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
                CustomerModule.this.removeTokenMapping(moduleToken, token);
                CustomerModule.this.setCurrentProfile(response);
                listener.onResponse(response, moduleToken, exception);
            }
        }));
        return moduleToken;
    }

    public AsyncToken getPaymentData(String paymentData, @NonNull AsyncListener<PaymentCard> listener) {
        return ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).getPaymentData(paymentData, listener);
    }

    public AsyncToken getRecentOrders(CustomerProfile profile, Integer numRecents, @NonNull final AsyncListener<List<CustomerOrder>> listener) {
        if (profile == null || !ModuleManager.isModuleEnabled("ordering").booleanValue()) {
            listener.onResponse(null, null, new AsyncException("Illegal Arguments"));
            return null;
        }
        final AsyncToken moduleToken = new AsyncToken("recentOrders");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).getRecentOrders(profile.getUserName(), numRecents, new AsyncListener<List<CustomerOrder>>() {
            public void onResponse(List<CustomerOrder> response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    CustomerModule.this.mRecentOrders.clear();
                    CustomerModule.this.mRecentOrders.addAll(response);
                    listener.onResponse(response, moduleToken, null);
                } else {
                    listener.onResponse(null, moduleToken, exception);
                }
                CustomerModule.this.removeTokenMapping(moduleToken, token);
            }
        }));
        return moduleToken;
    }

    public AsyncToken validateAddress(CustomerAddress address, CustomerProfile profile, @NonNull final AsyncListener<AddressValidationResult> listener) {
        if (profile == null || address == null) {
            listener.onResponse(null, null, new AsyncException("Illegal Arguments"));
            return null;
        }
        final AsyncToken moduleToken = new AsyncToken("validateAddress");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).validateAddress(profile.getUserName(), address, new AsyncListener<AddressValidationResult>() {
            public void onResponse(AddressValidationResult response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    listener.onResponse(response, moduleToken, null);
                } else {
                    listener.onResponse(null, moduleToken, exception);
                }
            }
        }));
        return moduleToken;
    }

    public AsyncToken getAddressElements(CustomerProfile profile, @NonNull final AsyncListener<GetAddressElementsResult> listener) {
        if (profile == null) {
            listener.onResponse(null, null, new AsyncException("Illegal Arguments"));
            return null;
        }
        final AsyncToken moduleToken = new AsyncToken("getAddressElements");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).getAddressElements(profile.getUserName(), new AsyncListener<GetAddressElementsResult>() {
            public void onResponse(GetAddressElementsResult response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    listener.onResponse(response, moduleToken, null);
                } else {
                    listener.onResponse(null, moduleToken, exception);
                }
                CustomerModule.this.removeTokenMapping(moduleToken, token);
            }
        }));
        return moduleToken;
    }

    public AsyncToken getAddressBook(CustomerProfile profile, @NonNull final AsyncListener<List<CustomerAddress>> listener) {
        if (profile == null) {
            listener.onResponse(null, null, new AsyncException("Illegal Arguments"));
            return null;
        }
        final AsyncToken moduleToken = new AsyncToken("getAddressBook");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).getAddressBook(profile.getUserName(), new AsyncListener<List<CustomerAddress>>() {
            public void onResponse(List<CustomerAddress> response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    listener.onResponse(response, moduleToken, null);
                } else {
                    listener.onResponse(null, moduleToken, exception);
                }
                CustomerModule.this.removeTokenMapping(moduleToken, token);
            }
        }));
        return moduleToken;
    }

    @Deprecated
    public AsyncToken getDefaultAddress(CustomerProfile profile, @NonNull AsyncListener<CustomerAddress> asyncListener) {
        return null;
    }

    public AsyncToken getDefaultAddress(@NonNull final AsyncListener<CustomerAddress> listener) {
        CustomerProfile profile = getCurrentProfile();
        if (profile == null) {
            listener.onResponse(null, null, new AsyncException("Illegal Arguments"));
            return null;
        }
        final AsyncToken moduleToken = new AsyncToken("getDefaultAddress");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).getDefaultAddress(profile.getUserName(), new AsyncListener<CustomerAddress>() {
            public void onResponse(CustomerAddress response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    listener.onResponse(response, moduleToken, null);
                } else {
                    listener.onResponse(null, moduleToken, exception);
                }
                CustomerModule.this.removeTokenMapping(moduleToken, token);
            }
        }));
        return moduleToken;
    }

    public AsyncToken updateAddressBook(List<CustomerAddress> addresses, CustomerProfile profile, @NonNull final AsyncListener<Boolean> listener) {
        if (addresses == null || profile == null) {
            listener.onResponse(null, null, new AsyncException("Illegal Arguments"));
            return null;
        }
        final AsyncToken moduleToken = new AsyncToken("updateAddressBook");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).updateAddressBook(profile.getUserName(), addresses, new AsyncListener<Boolean>() {
            public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    listener.onResponse(response, moduleToken, null);
                } else {
                    listener.onResponse(null, moduleToken, exception);
                }
                CustomerModule.this.removeTokenMapping(moduleToken, token);
            }
        }));
        return moduleToken;
    }

    public AsyncToken addAddress(CustomerAddress address, CustomerProfile profile, @NonNull final AsyncListener<Boolean> listener) {
        if (profile == null || address == null) {
            listener.onResponse(null, null, new AsyncException("Illegal Arguments"));
            return null;
        }
        final AsyncToken moduleToken = new AsyncToken("addAddress");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).addAddress(profile.getUserName(), address, new AsyncListener<Boolean>() {
            public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    listener.onResponse(response, moduleToken, null);
                } else {
                    listener.onResponse(null, moduleToken, exception);
                }
                CustomerModule.this.removeTokenMapping(moduleToken, token);
            }
        }));
        return moduleToken;
    }

    public AsyncToken removeAddress(CustomerAddress address, CustomerProfile profile, @NonNull final AsyncListener<Boolean> listener) {
        if (profile == null || address == null) {
            listener.onResponse(null, null, new AsyncException("Illegal Arguments"));
            return null;
        }
        final AsyncToken moduleToken = new AsyncToken("removeAddress");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).removeAddress(profile.getUserName(), address, new AsyncListener<Boolean>() {
            public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    listener.onResponse(response, moduleToken, null);
                } else {
                    listener.onResponse(null, moduleToken, exception);
                }
                CustomerModule.this.removeTokenMapping(moduleToken, token);
            }
        }));
        return moduleToken;
    }

    private void addTokenMapping(AsyncToken moduleToken, AsyncToken connectorToken) {
        this.mTokenMap.put(moduleToken, connectorToken);
    }

    private void removeTokenMapping(AsyncToken moduleToken, AsyncToken connectorToken) {
        AsyncToken foundToken = (AsyncToken) this.mTokenMap.get(moduleToken);
        if (foundToken != null && foundToken.equals(connectorToken)) {
            this.mTokenMap.remove(moduleToken);
        }
    }

    public void logout(final AsyncListener<Void> listener) {
        CustomerConnector customerConnector = (CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl);
        final boolean shouldClearBasketOnLogout = Configuration.getSharedInstance().getBooleanForKey(CLEAR_BASKET_ON_LOGOUT_KEY);
        customerConnector.signOut(new AsyncListener() {
            public void onResponse(Object response, AsyncToken token, AsyncException exception) {
                CustomerModule.this.setCurrentProfile(null);
                CustomerModule.this.mFavoriteStores.clear();
                CustomerModule.this.mRecentOrders.clear();
                CustomerModule.this.mLoggedIn = false;
                ((DeliveryModule) ModuleManager.getModule(DeliveryModule.NAME)).clearModuleCacheData();
                LocalDataManager.getSharedInstance().removeSavedLogin();
                SessionManager.getInstance().clearToken();
                if (shouldClearBasketOnLogout) {
                    OrderManager.getInstance().deleteCurrentOrderAndAddress();
                }
                NotificationCenter.getSharedInstance().postNotification(CustomerModule.MCD_LOGIN_STATE_CHANGED);
                if (listener != null) {
                    listener.onResponse(null, token, exception);
                }
            }
        });
    }

    public Account getSyncAccount() {
        Account[] accounts = this.mAccountManager.getAccountsByType("com.mcdonalds.gma.hongkong.account");
        if (accounts.length > 0) {
            return accounts[0];
        }
        createSyncAccount();
        return getSyncAccount();
    }

    public void removeSyncAccount() {
        Account[] accounts = this.mAccountManager.getAccountsByType("com.mcdonalds.gma.hongkong.account");
        if (accounts.length > 0) {
            this.mAccountManager.removeAccount(accounts[0], null, null);
        }
    }

    public void createSyncAccount() {
        this.mAccountManager.addAccountExplicitly(new Account(DUMMY_ACCOUNT_NAME, "com.mcdonalds.gma.hongkong.account"), null, null);
        requestSync();
        requestSocialNetworkSync();
    }

    public int getMaxItemQuantity() {
        return ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).getMaxItemQuantity();
    }

    public AsyncToken associateDevice(String deviceToken, @NonNull final AsyncListener<Boolean> listener) {
        final AsyncToken asyncToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "#associateDevice");
        ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).associateDevice(getCurrentProfile().getUserName(), deviceToken, new AsyncListener<Boolean>() {
            public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                if (exception != null) {
                    listener.onResponse(Boolean.valueOf(false), asyncToken, exception);
                } else if (response == null) {
                    listener.onResponse(null, asyncToken, null);
                } else if (response.booleanValue()) {
                    listener.onResponse(Boolean.valueOf(true), asyncToken, null);
                } else {
                    listener.onResponse(Boolean.valueOf(false), asyncToken, null);
                }
            }
        });
        return asyncToken;
    }

    public AsyncToken sendRating(int rating, String comment, @NonNull final AsyncListener<Boolean> listener) {
        final AsyncToken asyncToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "#rating");
        ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).sendRating(getCurrentProfile().getUserName(), comment, rating, new AsyncListener<Boolean>() {
            public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                listener.onResponse(Boolean.valueOf(response != null), asyncToken, exception);
            }
        });
        return asyncToken;
    }

    public String getGcmSenderId() {
        return ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).getGcmSenderId();
    }

    public AsyncToken sendSMSCode(CustomerProfile profile, @NonNull final AsyncListener<Boolean> listener) {
        final AsyncToken moduleToken = new AsyncToken("sendSMSCode");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).sendSMSCode(profile, new AsyncListener<Boolean>() {
            public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                CustomerModule.this.removeTokenMapping(moduleToken, token);
                listener.onResponse(response, token, exception);
            }
        }));
        return moduleToken;
    }

    public AsyncToken verifySMS(String smsCode, CustomerProfile profile, @NonNull final AsyncListener<Boolean> listener) {
        final AsyncToken moduleToken = new AsyncToken("verifyAccount");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).verifyAccount(profile, AccountVerificationType.SMS, smsCode, new AsyncListener<Boolean>() {
            public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                listener.onResponse(response, token, exception);
                CustomerModule.this.removeTokenMapping(moduleToken, token);
            }
        }));
        return moduleToken;
    }

    public AsyncToken verifyEmail(String emailVerificationCode, CustomerProfile profile, @NonNull final AsyncListener<Boolean> listener) {
        final AsyncToken moduleToken = new AsyncToken("verifyAccount");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).verifyAccount(profile, AccountVerificationType.EMAIL, emailVerificationCode, new AsyncListener<Boolean>() {
            public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                listener.onResponse(response, token, exception);
                CustomerModule.this.removeTokenMapping(moduleToken, token);
            }
        }));
        return moduleToken;
    }

    public AsyncToken setDefaultAddress(CustomerAddress address, CustomerProfile profile, @NonNull final AsyncListener<Boolean> listener) {
        if (address == null || profile == null) {
            listener.onResponse(null, null, new AsyncException("Illegal Arguments"));
            return null;
        }
        final AsyncToken moduleToken = new AsyncToken("setDefaultAddress");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).setDefaultAddress(profile.getUserName(), address, new AsyncListener<Boolean>() {
            public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    listener.onResponse(response, moduleToken, null);
                } else {
                    listener.onResponse(null, moduleToken, exception);
                }
                CustomerModule.this.removeTokenMapping(moduleToken, token);
            }
        }));
        return moduleToken;
    }

    public FeedBackType getFeedBackTypeForRating() {
        return FeedBackTypeRepository.getForSendRating(this.mContext);
    }

    public SocialNetwork getSocialNetworkById(int id) {
        return SocialNetworkRepository.getById(this.mContext, id);
    }

    public AsyncToken registerCard(String url, String sessionId, String customerId, boolean isOneTimePayment, @NonNull final AsyncListener<PaymentCard> listener) {
        final AsyncToken moduleToken = new AsyncToken("registerCard");
        addTokenMapping(moduleToken, ((CustomerConnector) ConnectorManager.getConnector(this.mConnectorImpl)).registerCard(url, sessionId, customerId, isOneTimePayment, new AsyncListener<PaymentCard>() {
            public void onResponse(PaymentCard response, AsyncToken token, AsyncException exception) {
                if (!(response == null || response.getIsOneTimePayment() == null || response.getIsOneTimePayment().booleanValue())) {
                    CustomerProfile profile = CustomerModule.this.getCurrentProfile();
                    if (profile != null) {
                        List<PaymentCard> cards = profile.getCardItems();
                        if (cards == null) {
                            cards = new ArrayList();
                        }
                        cards.add(response);
                        profile.setCardItems(cards);
                    }
                }
                listener.onResponse(response, token, exception);
                CustomerModule.this.removeTokenMapping(moduleToken, token);
            }
        }));
        return moduleToken;
    }

    public boolean shouldSaveCard() {
        CustomerProfile profile = ((CustomerModule) ModuleManager.getModule(NAME)).getCurrentProfile();
        if (Configuration.getSharedInstance().getBooleanForKey(IGNORE_MAX_PAYMENT_CARDS)) {
            return true;
        }
        if (profile == null || profile.getCardItems().size() >= getMaxAllowedCards()) {
            return false;
        }
        return true;
    }

    public int getMaxAllowedCards() {
        String appParameterMaxAllowedCard = AppParameters.getAppParameter(AppParameters.MAX_PAYMENT_CARDS);
        if (!appParameterMaxAllowedCard.isEmpty()) {
            return Integer.parseInt(appParameterMaxAllowedCard);
        }
        if (Configuration.getSharedInstance().getIntForKey(MAX_PAYMENT_CARDS_KEY) != 0) {
            return Configuration.getSharedInstance().getIntForKey(MAX_PAYMENT_CARDS_KEY);
        }
        return 10;
    }
}
