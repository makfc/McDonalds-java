package com.mcdonalds.sdk.services.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mcdonalds.sdk.McDonalds;
import com.mcdonalds.sdk.connectors.middleware.helpers.MWCatalogManager;
import com.mcdonalds.sdk.connectors.middleware.model.MWCatalogVersionItem;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.listeners.OnSaveCompletedListener;
import com.mcdonalds.sdk.services.data.sync.CatalogVersionType;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.mcdonalds.sdk.services.log.SafeLog;
import com.mcdonalds.sdk.services.network.CacheUtils;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

public class LocalDataManager {
    private static final String CACHE_EXP_KEY_PREFIX = "com.mcdonalds.app.diskCacheExpiration.";
    public static final String FIRST_CATALOG_LOAD = "first_catalog_load";
    public static final String KEY_CACHE_LATEST_ORDER = "modules.ordering.cacheLatestOrderMinutes";
    public static final String KEY_CHECKIN_TIMER = "CHECKIN_TIMER";
    private static final String KEY_DISABLE_BACKGROUND_ORDER_CACHING = "modules.ordering.disableBackgroundOrderCaching";
    private static final String KEY_DUPLICATE_ORDER_CHECKIN_FLOW_ALERT_TIMER = "interface.ordering.duplicateOrderCheckinFlowAlertTimer";
    private static final String LOG_TAG = LocalDataManager.class.getSimpleName();
    private static final String NOTIFICATION_REG_ID = "NOTIFICATION_REG_ID";
    public static final String OFFERS_CACHE_KEY = "OFFERS_CACHE_KEY";
    private static final String PREF_CURRENT_ORDER = "PREF_CURRENT_ORDER";
    private static final String PREF_DEVICE_LANG = "PREF_DEVICE_LANG";
    private static final String PREF_EDT_STRING = "PREF_EDT_STRING";
    public static final String PREF_FIRST_LOGIN = "PREF_FIRST_LOGIN";
    public static final String PREF_FIRST_TIME_DELIVERY = "pref_first_time_delivery";
    public static final String PREF_FIRST_TIME_ORDERING = "pref_first_time_ordering";
    public static final String PREF_FIRST_TIME_QR_SCAN = "pref_first_time_qr_scan";
    public static final String PREF_FIRST_USE = "pref_first_time_used";
    public static final String PREF_LAST_ACTIVE = "last_active";
    private static final String PREF_LATEST_ORDER_IS_DRIVE_THRU = "PREF_LATEST_ORDER_IS_DRIVE_THRU";
    private static final String PREF_LATEST_ORDER_NUMBER = "PREF_LATEST_ORDER_NUMBER";
    public static final String PREF_LOCATION_SEARCH_TITLE = "PREF_LOCATION_SEARCH_TITLE";
    public static final String PREF_MARKET_CATALOG_PREFIX = "market_catalog";
    public static final String PREF_MODULE_NAME = "pref_module_name";
    private static final String PREF_NUTRITION_CACHE_DATE = "PREF_NUTRITION_CACHE_DATE";
    private static final String PREF_PUSH_OPT_IN_SHOWN = "PREF_PUSH_OPT_IN_SHOWN";
    private static final String PREF_REMEMBER_LOGIN = "PREF_REMEMBER_LOGIN";
    private static final String PREF_SAVED_LOGIN = "PREF_SAVED_LOGIN";
    private static final String PREF_SAVED_LOGIN_PASS = "PREF_SAVED_LOGIN_";
    private static final String PREF_SAVED_LOGIN_SOCIAL_NETWORK_ID = "PREF_SAVED_LOGIN_SOCIAL_NETWORK_ID";
    private static final String PREF_SAVED_LOGIN_SOCIAL_NETWORK_TYPE = "PREF_SAVED_LOGIN_SOCIAL_NETWORK_TYPE";
    private static final String PREF_STORE_CATALOGS = "PREF_STORE_CATALOGS_9_";
    public static final String PREF_STORE_CATALOG_PREFIX = "store_catalog";
    private static final String PREF_TUTORIAL_LAST_SHOWN_VERSION = "pref_tutorial_last_shown_version";
    private static final String TAG = LocalDataManager.class.getCanonicalName();
    private static final long THIRTY_MINS_IN_MILLIS = TimeUnit.MINUTES.toMillis(30);
    public static final String TIME_ZONE = "timezone";
    private static final String VERSION_PREFS = "VERSION_PREFS";
    private static LocalDataManager mSharedInstance = null;
    private DiskCacheManager mCacheManager = null;
    private Crypto mCrypto = null;
    private SharedPreferences mSharedPreferences = null;
    private SharedPreferences mVersionSharedPreferences;

    /* renamed from: com.mcdonalds.sdk.services.data.LocalDataManager$10 */
    class C411710 extends TypeToken<Long> {
        C411710() {
        }
    }

    /* renamed from: com.mcdonalds.sdk.services.data.LocalDataManager$1 */
    class C41181 extends TypeToken<String> {
        C41181() {
        }
    }

    /* renamed from: com.mcdonalds.sdk.services.data.LocalDataManager$2 */
    class C41192 extends TypeToken<List<MWCatalogVersionItem>> {
        C41192() {
        }
    }

    /* renamed from: com.mcdonalds.sdk.services.data.LocalDataManager$3 */
    class C41203 extends TypeToken<Boolean> {
        C41203() {
        }
    }

    /* renamed from: com.mcdonalds.sdk.services.data.LocalDataManager$4 */
    class C41214 extends TypeToken<List<Offer>> {
        C41214() {
        }
    }

    /* renamed from: com.mcdonalds.sdk.services.data.LocalDataManager$5 */
    class C41225 extends TypeToken<ArrayList<String>> {
        C41225() {
        }
    }

    /* renamed from: com.mcdonalds.sdk.services.data.LocalDataManager$8 */
    class C41258 extends TypeToken<Long> {
        C41258() {
        }
    }

    /* renamed from: com.mcdonalds.sdk.services.data.LocalDataManager$9 */
    class C41269 extends TypeToken<Long> {
        C41269() {
        }
    }

    public static LocalDataManager getSharedInstance() {
        if (mSharedInstance == null) {
            mSharedInstance = new LocalDataManager();
        }
        return mSharedInstance;
    }

    public static void setSharedInstance(LocalDataManager localDataManager) {
        mSharedInstance = localDataManager;
    }

    private LocalDataManager() {
        initialize(McDonalds.getContext());
    }

    public LocalDataManager initialize(Context context) {
        this.mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.mVersionSharedPreferences = context.getSharedPreferences(VERSION_PREFS, 0);
        this.mCacheManager = DiskCacheManager.getInstance(context);
        try {
            this.mCrypto = new Crypto(context);
        } catch (NoSuchAlgorithmException e) {
            MCDLog.error(TAG, e.getLocalizedMessage());
        } catch (IOException e2) {
            MCDLog.error(TAG, e2.getLocalizedMessage());
        }
        return this;
    }

    public void setLastActive(long val) {
        set("last_active", val);
    }

    public long getLastActive(long def) {
        return this.mSharedPreferences.getLong("last_active", def);
    }

    public void setFirstCatalogLoad(boolean firstCatalogLoad) {
        set("first_catalog_load", firstCatalogLoad);
    }

    public boolean isFirstCatalogLoad() {
        return getBoolean("first_catalog_load", true);
    }

    public void setStoreCatalogDownloaded(String storeId, boolean isDownloaded) {
        set(String.format("%s_%s", new Object[]{PREF_STORE_CATALOG_PREFIX, storeId}), isDownloaded);
    }

    public void setStoreCatalogVersion(String storeId, String field, String value) {
        set(String.format("%s_%s_%s", new Object[]{PREF_STORE_CATALOG_PREFIX, storeId, field}), value);
    }

    public String getStoreCatalogVersion(String storeId, String field) {
        return getString(String.format("%s_%s_%s", new Object[]{PREF_STORE_CATALOG_PREFIX, storeId, field}), CatalogManager.VERSION_0);
    }

    public boolean isStoreCatalogDownloaded(String storeId) {
        return getBoolean(String.format("%s_%s", new Object[]{PREF_STORE_CATALOG_PREFIX, storeId}), false);
    }

    public void setMarketCatalogVersions(String market, String field, String value) {
        set(String.format("%s_%s_%s", new Object[]{PREF_MARKET_CATALOG_PREFIX, market, field}), value);
    }

    public String getMarketCatalogVersions(String market, String field) {
        return getString(String.format("%s_%s_%s", new Object[]{PREF_MARKET_CATALOG_PREFIX, market, field}), CatalogManager.VERSION_0);
    }

    public void setFirstTimeOrdering(boolean firstTimeOrdering) {
        set(PREF_FIRST_TIME_ORDERING, firstTimeOrdering);
    }

    public void setFirstTimeDelivery(boolean firstTimeDelivery) {
        set(PREF_FIRST_TIME_DELIVERY, firstTimeDelivery);
    }

    public void setModuleName(String moduleName) {
        set(PREF_MODULE_NAME, moduleName);
    }

    public boolean isFirstTimeDelivery() {
        return getBoolean(PREF_FIRST_TIME_DELIVERY, true);
    }

    public String getModuleName() {
        return getString(PREF_MODULE_NAME, "def");
    }

    public String getFirstLogin() {
        return getString(PREF_FIRST_LOGIN, null);
    }

    public void setFirstLogin(String firstLoginUser) {
        set(PREF_FIRST_LOGIN, firstLoginUser);
    }

    public String getDeviceLanguage() {
        return getString(PREF_DEVICE_LANG, null);
    }

    public void setDeviceLanguage(String deviceLang) {
        set(PREF_DEVICE_LANG, deviceLang);
    }

    public boolean isFirstTimeOrdering() {
        return getBoolean(PREF_FIRST_TIME_ORDERING, true);
    }

    public void setFirstTimeQrScan(boolean firstTimeQrScan) {
        set(PREF_FIRST_TIME_QR_SCAN, firstTimeQrScan);
    }

    public boolean isFirstTimeQrScan() {
        return getBoolean(PREF_FIRST_TIME_QR_SCAN, true);
    }

    public void setNotificationRegId(String regId) {
        set(NOTIFICATION_REG_ID, regId);
    }

    public String getNotificationRegId() {
        return getString(NOTIFICATION_REG_ID, "");
    }

    public void setPrefSavedLogin(String username) {
        set(PREF_SAVED_LOGIN, username);
    }

    public void setPrefSavedLoginPass(String password) {
        set(PREF_SAVED_LOGIN_PASS, password);
    }

    public void setPrefSavedSocialNetworkId(int socialNetworkId) {
        set(PREF_SAVED_LOGIN_SOCIAL_NETWORK_ID, socialNetworkId);
    }

    public String getPrefSavedLogin() {
        return getString(PREF_SAVED_LOGIN, null);
    }

    public String getPrefSavedLoginPass() {
        return getString(PREF_SAVED_LOGIN_PASS, null);
    }

    public int getPrefSavedSocialNetworkId() {
        return this.mSharedPreferences.getInt(PREF_SAVED_LOGIN_SOCIAL_NETWORK_ID, -1);
    }

    public int getPrefSavedLoginSocialNetworkType() {
        return this.mSharedPreferences.getInt(PREF_SAVED_LOGIN_SOCIAL_NETWORK_TYPE, -1);
    }

    public void setPrefSavedLoginSocialNetworkType(int networkType) {
        set(PREF_SAVED_LOGIN_SOCIAL_NETWORK_TYPE, networkType);
    }

    public void removeSavedLogin() {
        remove(PREF_SAVED_LOGIN);
        remove(PREF_SAVED_LOGIN_PASS);
        remove(PREF_SAVED_LOGIN_SOCIAL_NETWORK_TYPE);
        remove(PREF_SAVED_LOGIN_SOCIAL_NETWORK_ID);
        remove(NOTIFICATION_REG_ID);
        remove(PREF_CURRENT_ORDER);
        deleteObjectFromCache(CustomerModule.CACHE_KEY_CURRENT_PROFILE);
        deleteObjectFromCache(PREF_LATEST_ORDER_NUMBER);
        deleteObjectFromCache(CustomerModule.CACHE_KEY_CURRENT_PROFILE);
    }

    public void setPrefRememberLogin(boolean shouldRemember) {
        set(PREF_REMEMBER_LOGIN, shouldRemember);
    }

    public boolean getPrefRememberLogin() {
        return getBoolean(PREF_REMEMBER_LOGIN, false);
    }

    public void setNutritionCacheDate(Date date) {
        if (date != null) {
            set(PREF_NUTRITION_CACHE_DATE, date.getTime());
        }
    }

    public Date getNutritionCacheDate() {
        return new Date(this.mSharedPreferences.getLong(PREF_NUTRITION_CACHE_DATE, 0));
    }

    public Order getCurrentOrder() {
        File file = orderCacheFile();
        Order order = null;
        if (!file.exists()) {
            return null;
        }
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
            order = (Order) inputStream.readObject();
            inputStream.close();
            return order;
        } catch (Exception e) {
            e.printStackTrace();
            return order;
        }
    }

    public void setCurrentOrder(Order order) {
        Exception e;
        FileOutputStream fileOutputStream;
        if (!Configuration.getSharedInstance().getBooleanForKey(KEY_DISABLE_BACKGROUND_ORDER_CACHING)) {
            File file = orderCacheFile();
            if (order == null) {
                try {
                    file.delete();
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            Order safeOrder = Order.cloneOrderForEditing(order);
            safeOrder.setPayment(null);
            try {
                FileOutputStream fout = new FileOutputStream(orderCacheFile(), false);
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(fout);
                    try {
                        oos.writeObject(safeOrder);
                        oos.close();
                    } catch (Exception e3) {
                        e2 = e3;
                        fileOutputStream = fout;
                        ObjectOutputStream objectOutputStream = oos;
                    }
                } catch (Exception e4) {
                    e2 = e4;
                    fileOutputStream = fout;
                    e2.printStackTrace();
                }
            } catch (Exception e5) {
                e2 = e5;
                e2.printStackTrace();
            }
        }
    }

    private File orderCacheFile() {
        return CacheUtils.getOrderCacheFile();
    }

    public String getLatestOrderNumber() {
        return (String) getObjectFromCache(PREF_LATEST_ORDER_NUMBER, new C41181().getType());
    }

    public void setLatestOrderNumber(String orderNumber) {
        int cacheTimeInMinutes = Configuration.getSharedInstance().getIntForKey("modules.ordering.cacheLatestOrderMinutes");
        if (cacheTimeInMinutes > 0) {
            addObjectToCache(PREF_LATEST_ORDER_NUMBER, orderNumber, TimeUnit.MINUTES.toMillis((long) cacheTimeInMinutes));
        } else {
            addObjectToCache(PREF_LATEST_ORDER_NUMBER, orderNumber, THIRTY_MINS_IN_MILLIS);
        }
    }

    public List<MWCatalogVersionItem> getStoreCatalogTimestamps(int storeId) {
        return (List) getObjectFromCache(PREF_STORE_CATALOGS + String.valueOf(storeId), new C41192().getType());
    }

    public void cleanStoreCatalogTimestamps(int storeId) {
        deleteObjectFromCache(PREF_STORE_CATALOGS + String.valueOf(storeId));
    }

    public void setStoreCatalogTimestamps(int storeId, List<MWCatalogVersionItem> items) {
        addObjectToCache(PREF_STORE_CATALOGS + String.valueOf(storeId), items, TimeUnit.MINUTES.toMillis(2));
    }

    public boolean isStoreCatalogOutDated(int storeId, List<MWCatalogVersionItem> latestVersions) {
        for (CatalogVersionType desiredItem : MWCatalogManager.desiredStoreCatalogTypes()) {
            String versionCached = getStoreCatalogVersion(Integer.toString(storeId), desiredItem.getName());
            for (MWCatalogVersionItem latestVersionItem : latestVersions) {
                if (latestVersionItem.type.equals(desiredItem) && !versionCached.equals(latestVersionItem.version)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isLatestOrderDriveThru() {
        Boolean isDriveThru = (Boolean) getObjectFromCache(PREF_LATEST_ORDER_IS_DRIVE_THRU, new C41203().getType());
        return isDriveThru == null ? false : isDriveThru.booleanValue();
    }

    public void setLatestOrderIsDriveThru(boolean isDriveThru) {
        int cacheTimeInMinutes = Configuration.getSharedInstance().getIntForKey("modules.ordering.cacheLatestOrderMinutes");
        if (cacheTimeInMinutes > 0) {
            addObjectToCache(PREF_LATEST_ORDER_IS_DRIVE_THRU, Boolean.valueOf(isDriveThru), TimeUnit.MINUTES.toMillis((long) cacheTimeInMinutes));
        } else {
            addObjectToCache(PREF_LATEST_ORDER_IS_DRIVE_THRU, Boolean.valueOf(isDriveThru), THIRTY_MINS_IN_MILLIS);
        }
    }

    public void setEdtString(String edtString) {
        set(PREF_EDT_STRING, edtString);
    }

    public String getEdtString() {
        return getString(PREF_EDT_STRING, null);
    }

    public List<Offer> getCachedOffers(String username, String storeId) {
        return (List) getObjectFromCache(username + "_" + storeId + "_" + OFFERS_CACHE_KEY, new C41214().getType());
    }

    public void setCachedOffers(String username, String storeId, List<Offer> offers) {
        addObjectToCache(username + "_" + storeId + "_" + OFFERS_CACHE_KEY, offers, TimeUnit.MINUTES.toMillis(15));
    }

    public int getPushNotificationOptInShownNum() {
        return this.mSharedPreferences.getInt(PREF_PUSH_OPT_IN_SHOWN, 0);
    }

    public void setPushNotificationOptInShownNum(int num) {
        set(PREF_PUSH_OPT_IN_SHOWN, num);
    }

    public String getTutorialLastShownVersionName() {
        return this.mVersionSharedPreferences.getString(PREF_TUTORIAL_LAST_SHOWN_VERSION, null);
    }

    public void setTutorialLastShownVersionName(String versionName) {
        Editor editor = this.mVersionSharedPreferences.edit();
        editor.putString(PREF_TUTORIAL_LAST_SHOWN_VERSION, versionName);
        editor.apply();
    }

    public void clear() {
        this.mSharedPreferences.edit().clear().commit();
    }

    public void clearCatalogPreference() {
        Map<String, ?> AllEntries = this.mSharedPreferences.getAll();
        Editor editor = this.mSharedPreferences.edit();
        for (Entry<String, ?> entry : AllEntries.entrySet()) {
            String entryKey = (String) entry.getKey();
            if (entryKey.contains(PREF_MARKET_CATALOG_PREFIX) || entryKey.contains(PREF_STORE_CATALOG_PREFIX)) {
                editor.remove(entryKey);
            }
        }
        editor.apply();
    }

    public void clearStoreCatalogPreference() {
        Map<String, ?> AllEntries = this.mSharedPreferences.getAll();
        Editor editor = this.mSharedPreferences.edit();
        for (Entry<String, ?> entry : AllEntries.entrySet()) {
            String entryKey = (String) entry.getKey();
            if (entryKey.contains(PREF_STORE_CATALOG_PREFIX)) {
                editor.remove(entryKey);
            }
        }
        editor.apply();
    }

    public String getString(String key, String def) {
        String decrypted = def;
        try {
            String encrypted = this.mSharedPreferences.getString(key, null);
            if (encrypted != null) {
                return this.mCrypto.decrypt(encrypted);
            }
            return decrypted;
        } catch (Exception e) {
            return decrypted;
        }
    }

    public boolean getBoolean(String key, boolean def) {
        boolean decrypted = def;
        try {
            String encrypted = this.mSharedPreferences.getString(key, null);
            if (encrypted != null) {
                return Boolean.valueOf(this.mCrypto.decrypt(encrypted)).booleanValue();
            }
            return decrypted;
        } catch (Exception e) {
            return decrypted;
        }
    }

    public List<String> getStringList(String key, List<String> list) {
        try {
            String decryptedJson = this.mCrypto.decrypt(this.mSharedPreferences.getString(key, null));
            Gson gson = new GsonBuilder().create();
            Type type = new C41225().getType();
            return (List) (!(gson instanceof Gson) ? gson.fromJson(decryptedJson, type) : GsonInstrumentation.fromJson(gson, decryptedJson, type));
        } catch (Exception e) {
            return null;
        }
    }

    public void remove(String key) {
        remove(key, false);
    }

    @SuppressLint({"CommitPrefEdits"})
    public void remove(String key, boolean immediately) {
        Editor editor = this.mSharedPreferences.edit().remove(key);
        if (immediately) {
            editor.commit();
        } else {
            editor.apply();
        }
    }

    public void set(String key, String value) {
        Editor editor = this.mSharedPreferences.edit();
        if (value != null) {
            try {
                editor.putString(key, this.mCrypto.encrypt(value));
                editor.apply();
            } catch (GeneralSecurityException e) {
                SafeLog.m7746e(LOG_TAG, "error trying to set value", e);
            }
        }
    }

    public void set(String key, boolean value) {
        Editor editor = this.mSharedPreferences.edit();
        try {
            editor.putString(key, this.mCrypto.encrypt(String.valueOf(value)));
            editor.apply();
        } catch (GeneralSecurityException e) {
            SafeLog.m7746e(LOG_TAG, "error trying to set value", e);
        }
    }

    public void set(String key, int value) {
        Editor editor = this.mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void set(String key, List<String> values) {
        Editor editor = this.mSharedPreferences.edit();
        if (values != null) {
            Gson gson = new Gson();
            String value = !(gson instanceof Gson) ? gson.toJson((Object) values) : GsonInstrumentation.toJson(gson, (Object) values);
            if (value != null) {
                try {
                    value = this.mCrypto.encrypt(value);
                } catch (GeneralSecurityException e) {
                    SafeLog.m7746e(LOG_TAG, "error trying to set value", e);
                }
            }
            editor.putString(key, value);
        } else {
            editor.remove(key);
        }
        editor.apply();
    }

    public void set(String key, long value) {
        Editor editor = this.mSharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public synchronized void addObjectToCache(String key, Object value, long expirationInterval) {
        addObjectToCache(key, value, expirationInterval, false, null);
    }

    public synchronized void addObjectToCache(String key, Object value, long expirationInterval, boolean serialize) {
        addObjectToCache(key, value, expirationInterval, serialize, null);
    }

    public synchronized void addObjectToCache(String key, Object value, long expirationInterval, boolean serialize, OnSaveCompletedListener listener) {
        if (value != null) {
            final String str = key;
            final long j = expirationInterval;
            final boolean z = serialize;
            final Object obj = value;
            final OnSaveCompletedListener onSaveCompletedListener = listener;
            C41236 c41236 = new TraceFieldInterface() {
                public Trace _nr_trace;

                public void _nr_setTrace(Trace trace) {
                    try {
                        this._nr_trace = trace;
                    } catch (Exception e) {
                    }
                }

                /* Access modifiers changed, original: protected|varargs */
                public Void doInBackground(Void... params) {
                    try {
                        String cacheExpKey = LocalDataManager.this.getCacheExpKey(str);
                        if (j > 0) {
                            LocalDataManager.this.mCacheManager.put(cacheExpKey, Long.valueOf(System.currentTimeMillis() + j), z);
                        } else {
                            LocalDataManager.this.mCacheManager.delete(cacheExpKey);
                        }
                        LocalDataManager.this.mCacheManager.put(str, obj, z);
                    } catch (Exception e) {
                        SafeLog.m7746e(LocalDataManager.LOG_TAG, "error trying to add object to cache", e);
                    }
                    return null;
                }

                /* Access modifiers changed, original: protected */
                public void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    if (onSaveCompletedListener != null) {
                        onSaveCompletedListener.onSaveCompleted();
                    }
                }
            };
            Void[] voidArr = new Void[0];
            if (c41236 instanceof AsyncTask) {
                AsyncTaskInstrumentation.execute(c41236, voidArr);
            } else {
                c41236.execute(voidArr);
            }
        }
    }

    public synchronized void addObjectToCacheInstantly(String key, Object value, long expirationInterval) {
        try {
            String cacheExpKey = getCacheExpKey(key);
            if (expirationInterval > 0) {
                this.mCacheManager.put(cacheExpKey, Long.valueOf(System.currentTimeMillis() + expirationInterval));
            } else {
                this.mCacheManager.delete(cacheExpKey);
            }
            this.mCacheManager.put(key, value);
        } catch (Exception e) {
            SafeLog.m7746e(LOG_TAG, "error trying to add object to cache", e);
        }
        return;
    }

    public synchronized void updateObjectInCache(String key, Object value) {
        updateObjectInCache(key, value, false);
    }

    public synchronized void updateObjectInCache(final String key, final Object value, final boolean serialize) {
        if (isCacheValid(key, serialize)) {
            C41247 c41247 = new TraceFieldInterface() {
                public Trace _nr_trace;

                public void _nr_setTrace(Trace trace) {
                    try {
                        this._nr_trace = trace;
                    } catch (Exception e) {
                    }
                }

                /* Access modifiers changed, original: protected|varargs */
                public Void doInBackground(Void... params) {
                    try {
                        LocalDataManager.this.mCacheManager.put(key, value, serialize);
                    } catch (Exception e) {
                        Thread.dumpStack();
                    }
                    return null;
                }

                /* Access modifiers changed, original: protected */
                public void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    MCDLog.debug("Object updated in Cache");
                }
            };
            Void[] voidArr = new Void[0];
            if (c41247 instanceof AsyncTask) {
                AsyncTaskInstrumentation.execute(c41247, voidArr);
            } else {
                c41247.execute(voidArr);
            }
        } else {
            throw new RuntimeException("Expired cache key, try adding the object to the cache");
        }
    }

    public synchronized boolean deleteObjectFromCache(String key) {
        boolean z = false;
        synchronized (this) {
            try {
                if (this.mCacheManager.delete(key) && this.mCacheManager.delete(getCacheExpKey(key))) {
                    z = true;
                }
            } catch (IOException e) {
                Thread.dumpStack();
            }
        }
        return z;
    }

    public synchronized <T> T getObjectFromCache(String key, Type type) {
        return getObjectFromCache(key, type, false);
    }

    public synchronized <T> T getObjectFromCache(String key, Type type, boolean serialize) {
        T t = null;
        synchronized (this) {
            try {
                Long expirationTime = (Long) this.mCacheManager.get(getCacheExpKey(key), new C41258().getType(), serialize);
                if (expirationTime == null || System.currentTimeMillis() < expirationTime.longValue()) {
                    t = this.mCacheManager.get(key, type, serialize);
                } else {
                    this.mCacheManager.delete(key);
                }
            } catch (NullPointerException e) {
                Thread.dumpStack();
            } catch (IOException e2) {
                Thread.dumpStack();
            }
        }
        return t;
    }

    public synchronized boolean hasObjectInCache(String key, boolean serialize) {
        boolean hasObject;
        hasObject = false;
        try {
            Long expirationTime = (Long) this.mCacheManager.get(getCacheExpKey(key), new C41269().getType(), serialize);
            if (expirationTime == null || System.currentTimeMillis() < expirationTime.longValue()) {
                hasObject = this.mCacheManager.hasObjectForKey(key);
            } else {
                hasObject = false;
                this.mCacheManager.delete(key);
            }
        } catch (NullPointerException e) {
            Thread.dumpStack();
        } catch (IOException e2) {
            Thread.dumpStack();
        }
        return hasObject;
    }

    public synchronized boolean hasObjectInCache(String key) {
        return hasObjectInCache(key, false);
    }

    public synchronized void clearCache() {
        try {
            this.mCacheManager.clear();
        } catch (IOException e) {
            Thread.dumpStack();
        }
        return;
    }

    private String getCacheExpKey(String key) {
        return CACHE_EXP_KEY_PREFIX + key;
    }

    private boolean isCacheValid(String key, boolean serialize) {
        try {
            Long expirationTime = (Long) this.mCacheManager.get(getCacheExpKey(key), new C411710().getType(), serialize);
            if (expirationTime == null || System.currentTimeMillis() < expirationTime.longValue()) {
                return true;
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public void setCheckinTimeStamp() {
        long time;
        if (Configuration.getSharedInstance().getValueForKey(KEY_DUPLICATE_ORDER_CHECKIN_FLOW_ALERT_TIMER) != null) {
            time = TimeUnit.SECONDS.toMillis(((Double) Configuration.getSharedInstance().getValueForKey(KEY_DUPLICATE_ORDER_CHECKIN_FLOW_ALERT_TIMER)).longValue());
        } else {
            time = 300000;
        }
        getSharedInstance().addObjectToCacheInstantly(KEY_CHECKIN_TIMER, KEY_CHECKIN_TIMER, time);
    }
}
