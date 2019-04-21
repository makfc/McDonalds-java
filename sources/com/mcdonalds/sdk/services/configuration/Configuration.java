package com.mcdonalds.sdk.services.configuration;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;
import android.support.p000v4.app.NotificationCompat.Builder;
import android.support.p000v4.content.LocalBroadcastManager;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.C3883R;
import com.mcdonalds.sdk.McDonalds;
import com.mcdonalds.sdk.services.network.RequestManagerServiceConnection;
import com.mcdonalds.sdk.utils.MapUtils;
import com.mcdonalds.sdk.utils.SDKUtils;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class Configuration {
    public static final String DEBUG_CONFIG_KEY = "DEBUG";
    public static final String LOCALIZATION_CONFIG_KEY = "localization";
    public static final String MCD_NOTIFICATION_CONFIGURATION_CHANGED = "MCD_NOTIFICATION_CONFIGURATION_CHANGED";
    public static final String MERGED_CONFIG = "MERGED_CONFIG";
    public static final String NOTIFICATION_CONNECTIVITY_CHANGED = "NOTIFICATION_CONNECTIVITY_CHANGED";
    public static final String PREF_CONFIG_KEY = "PREF_CONFIG_KEY";
    public static final String PREF_CURRENT_CONFIG = "PREF_CURRENT_CONFIG";
    public static final String SHOW_DEPRECATED_KEYS_ALERT_KEY = "showAlertForDeprecatedKeys";
    private static Configuration mSharedInstance;
    private Locale currentLocale;
    private Map<String, Object> mConfigMap;
    private Context mContext;
    private NumberFormat mCurrencyFormat;
    private String mCurrentCountry;
    private String mEasyAddressLanguageTag;
    private boolean mIsInitialized;
    private String mLanguage;
    private String mLanguageTag;
    private LocalizationConfig mLocalization;
    private String mNutritionLanguage;

    public interface NetworkUpdateListener {
        void onComplete();
    }

    private Configuration(Context context) {
        initialize(context);
    }

    public Configuration(Map<String, Object> configMap) {
        this.mConfigMap = configMap;
    }

    public static Configuration getSharedInstance() {
        if (mSharedInstance == null) {
            return getSharedInstance(McDonalds.getContext());
        }
        if (mSharedInstance.isInitialized()) {
            return mSharedInstance;
        }
        throw new RuntimeException("You must initialize this class first");
    }

    public static Configuration getSharedInstance(Context context) {
        if (mSharedInstance == null) {
            mSharedInstance = new Configuration(context);
        } else if (!mSharedInstance.isInitialized()) {
            throw new RuntimeException("You must initialize this class first");
        }
        return mSharedInstance;
    }

    public static void setSharedInstance(Configuration configuration) {
        mSharedInstance = configuration;
    }

    public Configuration initialize(Context context) {
        if (!this.mIsInitialized) {
            this.mContext = context;
            this.mIsInitialized = true;
            DeprecatedConfiguration.init();
        }
        return this;
    }

    public boolean isInitialized() {
        return this.mIsInitialized;
    }

    public String getCurrentLanguageTag() {
        return this.mLanguageTag;
    }

    public String getCurrentLanguage() {
        return this.mLanguage;
    }

    public String getNutritionLanguageName() {
        if (getCurrentLocale().getLanguage().equals("zh")) {
            return ((Language) this.mLocalization.getNutritionLanguageNames().get(0)).getName();
        }
        return ((Language) this.mLocalization.getNutritionLanguageNames().get(1)).getName();
    }

    public NumberFormat getCurrencyFormatter() {
        return this.mCurrencyFormat;
    }

    public String getCurrencyCode() {
        return this.mCurrencyFormat.getCurrency().getCurrencyCode();
    }

    public void setPreferredLanguage() {
        if (hasKey(LOCALIZATION_CONFIG_KEY)) {
            Object fromJson;
            LinkedTreeMap<String, Object> configValue = (LinkedTreeMap) getSharedInstance().getValueForKey(LOCALIZATION_CONFIG_KEY);
            Gson gson = new Gson();
            String linkedTreeMap = configValue.toString();
            Class cls = LocalizationConfig.class;
            if (gson instanceof Gson) {
                fromJson = GsonInstrumentation.fromJson(gson, linkedTreeMap, cls);
            } else {
                fromJson = gson.fromJson(linkedTreeMap, cls);
            }
            this.mLocalization = (LocalizationConfig) fromJson;
            String currencyFormatLanguage = this.mLocalization.getDefaultCurrencyFormatLanguage();
            Locale userLocale = getCurrentLocale();
            Language matching = this.mLocalization.getLanguage(userLocale.getLanguage());
            if (matching != null) {
                this.mNutritionLanguage = matching.getNutritionLanguage();
                if (matching.isSingleCountry()) {
                    this.mLanguageTag = (String) matching.getTags().get(0);
                } else {
                    String userTag = getLanguageTag(userLocale.getLanguage(), userLocale.getCountry());
                    for (String tag : matching.getTags()) {
                        if (tag.equals(userTag)) {
                            this.mLanguageTag = userTag;
                            return;
                        }
                    }
                }
                this.mLanguageTag = (String) matching.getTags().get(0);
                splitLanguageTag();
                if (this.mCurrentCountry.equals("CHS")) {
                    this.mCurrencyFormat = NumberFormat.getCurrencyInstance(new Locale(this.mLanguage, "CN"));
                    this.mEasyAddressLanguageTag = getLanguageTag(this.mLanguage, "CN");
                    return;
                }
                this.mCurrencyFormat = NumberFormat.getCurrencyInstance(new Locale("zh", this.mCurrentCountry));
                this.mEasyAddressLanguageTag = getLanguageTag(this.mLanguage, this.mCurrentCountry);
                return;
            }
            this.mLanguageTag = this.mLocalization.getDefaultLanguage();
            this.mEasyAddressLanguageTag = this.mLocalization.getDefaultEasyAddressLanguageName();
            this.mNutritionLanguage = this.mLocalization.getDefaultNutritionLanguageName();
            splitLanguageTag();
            if (currencyFormatLanguage != null) {
                int dashIndex = currencyFormatLanguage.lastIndexOf("-");
                this.mCurrencyFormat = NumberFormat.getCurrencyInstance(new Locale(currencyFormatLanguage.substring(0, dashIndex), currencyFormatLanguage.substring(dashIndex + 1)));
                return;
            }
            this.mCurrencyFormat = NumberFormat.getCurrencyInstance(new Locale(this.mLanguage, this.mCurrentCountry));
            return;
        }
        this.mLanguageTag = "en-US";
        this.mNutritionLanguage = "en";
        this.mEasyAddressLanguageTag = "en-CN";
        this.mCurrencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
    }

    private void splitLanguageTag() {
        int dashIndex = this.mLanguageTag.lastIndexOf("-") + 1;
        if (dashIndex < this.mLanguageTag.length() && dashIndex > 0) {
            this.mCurrentCountry = this.mLanguageTag.substring(dashIndex);
            this.mLanguage = this.mLanguageTag.substring(0, dashIndex - 1);
        }
    }

    private String getLanguageTag(String language, String country) {
        return String.format("%s-%s", new Object[]{language, country});
    }

    public String getMarket() {
        return (String) getValueForKey("connectors.Middleware.marketId");
    }

    public String getConfigName() {
        return (String) getValueForKey("shortDescription");
    }

    public String getCurrentCountry() {
        return this.mCurrentCountry;
    }

    public LocalizationConfig getLocalization() {
        return this.mLocalization;
    }

    public Locale getCurrentLocale() {
        if (!this.mIsInitialized) {
            return Locale.getDefault();
        }
        this.currentLocale = this.mContext.getResources().getConfiguration().locale;
        return this.currentLocale;
    }

    public boolean isLocaleChanged(Locale curLocale) {
        if (this.currentLocale == null || !this.currentLocale.equals(curLocale)) {
            return true;
        }
        return false;
    }

    public void loadConfigurationWithJsonString(@NonNull String configurationString) {
        String versionName;
        Gson gson = new Gson();
        Class cls = HashMap.class;
        this.mConfigMap = (Map) (!(gson instanceof Gson) ? gson.fromJson(configurationString, cls) : GsonInstrumentation.fromJson(gson, configurationString, cls));
        setPreferredLanguage();
        broadcastConfigChanged();
        try {
            versionName = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionName;
        } catch (Exception e) {
            versionName = "unknown";
        }
        Boolean showBuildVersion = (Boolean) getValueForKey("showBuildVersion");
        Boolean showShortDescription = (Boolean) getValueForKey("showShortDescription");
        if (showBuildVersion == null || !showBuildVersion.booleanValue() || showShortDescription == null || !showShortDescription.booleanValue()) {
            ((NotificationManager) this.mContext.getSystemService("notification")).cancelAll();
            return;
        }
        Builder builder = new Builder(this.mContext);
        builder.setContentTitle((CharSequence) this.mConfigMap.get("longDescription"));
        builder.setContentText("Build " + versionName);
        builder.setTicker("Config & Build Information");
        builder.setSmallIcon(C3883R.C3881drawable.icon_red_circle_plus);
        builder.setAutoCancel(true);
        builder.setSmallIcon(C3883R.C3881drawable.icon_red_circle_plus);
        builder.setOngoing(true);
        Notification notification = builder.build();
        ((NotificationManager) this.mContext.getSystemService("notification")).cancelAll();
    }

    private void broadcastConfigChanged() {
        LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(new Intent(MCD_NOTIFICATION_CONFIGURATION_CHANGED));
    }

    public static Map<String, Object> mergeConfiguration(Map<String, Object> newMap, Map<String, Object> oldMap) {
        return MapUtils.merge(oldMap, newMap);
    }

    public boolean hasKey(String key) {
        return getValueForKey(key) != null;
    }

    public <T> T getValueForKey(String key) {
        if (this.mConfigMap == null) {
            throw new RuntimeException("Error accessing configuration. Your application must provide a configuration. Please see the Getting Started Guide for more information.");
        }
        T configValue = null;
        if (DeprecatedConfiguration.deprecatedConfigKeyMap == null || DeprecatedConfiguration.deprecatedConfigKeyMap.get(key) == null) {
            if (DeprecatedConfiguration.reverseDeprecatedConfigKeyMap != null && DeprecatedConfiguration.reverseDeprecatedConfigKeyMap.containsKey(key)) {
                configValue = checkMapForKey(DeprecatedConfiguration.reverseDeprecatedConfigKeyMap, key, false);
            }
        } else if (MapUtils.get(this.mConfigMap, (String) DeprecatedConfiguration.deprecatedConfigKeyMap.get(key)) != null) {
            configValue = checkMapForKey(DeprecatedConfiguration.deprecatedConfigKeyMap, key, true);
        }
        if (configValue == null) {
            return MapUtils.get(this.mConfigMap, key);
        }
        return configValue;
    }

    private <T> T checkMapForKey(Map<String, String> deprecatedConfigMap, String key, boolean oldConfig) {
        T retValue = MapUtils.get(this.mConfigMap, key);
        if (retValue == null) {
            retValue = MapUtils.get(this.mConfigMap, (String) deprecatedConfigMap.get(key));
        }
        Object showWarningObj = this.mConfigMap.get(SHOW_DEPRECATED_KEYS_ALERT_KEY);
        if (showWarningObj != null && (showWarningObj instanceof Boolean) && ((Boolean) showWarningObj).booleanValue()) {
        }
        return retValue;
    }

    public double getDoubleForKey(String key) {
        Object result = getValueForKey(key);
        if (result instanceof Double) {
            return ((Double) result).doubleValue();
        }
        return 0.0d;
    }

    public long getLongForKey(String key) {
        Object result = getValueForKey(key);
        if (result instanceof Number) {
            return ((Number) result).longValue();
        }
        return 0;
    }

    public int getIntForKey(String key) {
        Object result = getValueForKey(key);
        if (result instanceof Number) {
            return ((Number) result).intValue();
        }
        return 0;
    }

    public boolean getBooleanForKey(String key) {
        Object result = getValueForKey(key);
        return result != null && (result instanceof Boolean) && ((Boolean) result).booleanValue();
    }

    public String getStringForKey(String key) {
        Object result = getValueForKey(key);
        if (result instanceof String) {
            return (String) result;
        }
        return null;
    }

    public String getStringForKey(String key, String defaultValue) {
        String value = getStringForKey(key);
        return value != null ? value : defaultValue;
    }

    public String getLocalizedStringForKey(String key) {
        Object result = getValueForKey(key);
        if (result instanceof String) {
            return localizedStringForKey((String) result);
        }
        return null;
    }

    public String localizedStringForKey(String key) {
        if (key == null) {
            return null;
        }
        if (key.startsWith("raw:")) {
            return key.substring(4);
        }
        int resourceId = this.mContext.getResources().getIdentifier(key, "string", this.mContext.getPackageName());
        if (resourceId > 0) {
            return this.mContext.getString(resourceId);
        }
        return key;
    }

    public String getNutritionLanguage() {
        return this.mNutritionLanguage;
    }

    public String getEasyAddressLanguageTag() {
        if (hasKey(LOCALIZATION_CONFIG_KEY)) {
            LocalizationConfig localization;
            LinkedTreeMap<String, Object> configValue = (LinkedTreeMap) getSharedInstance().getValueForKey(LOCALIZATION_CONFIG_KEY);
            Gson gson = new Gson();
            String linkedTreeMap = configValue.toString();
            Class cls = LocalizationConfig.class;
            if (gson instanceof Gson) {
                localization = GsonInstrumentation.fromJson(gson, linkedTreeMap, cls);
            } else {
                localization = gson.fromJson(linkedTreeMap, cls);
            }
            localization = localization;
            Locale userLocale = getCurrentLocale();
            Language matching = localization.getLanguage(userLocale.getLanguage());
            if (matching != null) {
                String languageTag = (String) matching.getTags().get(0);
                if (!matching.isSingleCountry()) {
                    String userTag = getLanguageTag(userLocale.getLanguage(), userLocale.getCountry());
                    for (String tag : matching.getTags()) {
                        if (tag.equals(userTag)) {
                            languageTag = userTag;
                            break;
                        }
                    }
                }
                String currentCountry = "";
                String language = "";
                int dashIndex = languageTag.lastIndexOf("-") + 1;
                if (dashIndex < languageTag.length() && dashIndex > 0) {
                    currentCountry = languageTag.substring(dashIndex);
                    language = languageTag.substring(0, dashIndex - 1);
                }
                if (currentCountry.equals("CHS")) {
                    this.mEasyAddressLanguageTag = getLanguageTag(language, "CN");
                } else {
                    this.mEasyAddressLanguageTag = getLanguageTag(language, currentCountry);
                }
            } else {
                this.mEasyAddressLanguageTag = localization.getDefaultEasyAddressLanguageName();
            }
        } else {
            this.mEasyAddressLanguageTag = "en-CN";
        }
        return this.mEasyAddressLanguageTag;
    }

    public boolean isZipCode(String text) {
        String regexExpression = (String) getValueForKey("textValidation.postalCodeRegex");
        if (regexExpression != null) {
            return Pattern.compile(regexExpression).matcher(text.trim()).matches();
        }
        return true;
    }

    public void networkUpdate(RequestManagerServiceConnection connection, final NetworkUpdateListener listener) {
        SDKUtils.requestConfig(connection, new AsyncListener<Map>() {
            public void onResponse(Map response, AsyncToken token, AsyncException exception) {
                if (response == null || !Configuration.needsMerge(response, Configuration.this.mConfigMap)) {
                    listener.onComplete();
                    return;
                }
                Configuration.this.mConfigMap = Configuration.mergeConfiguration(response, Configuration.this.mConfigMap);
                Configuration.this.saveConfig(Configuration.this.mConfigMap);
                Configuration.this.broadcastConfigChanged();
            }
        });
    }

    private static boolean needsMerge(Map<String, Object> newMap, Map<String, Object> oldMap) {
        if (newMap == null) {
            return false;
        }
        for (String key : newMap.keySet()) {
            Object value = newMap.get(key);
            if (value instanceof Map) {
                Object oldValue = oldMap.get(key);
                if (!(oldValue instanceof Map)) {
                    return true;
                }
                if (needsMerge((Map) value, (Map) oldValue)) {
                    return true;
                }
            } else if (!oldMap.containsKey(key)) {
                return true;
            } else {
                if (oldMap.containsKey(key) && value != null && !value.equals(oldMap.get(key))) {
                    return true;
                }
                if (!(!oldMap.containsKey(key) || oldMap.get(key) == null || oldMap.get(key).equals(value))) {
                    return true;
                }
            }
        }
        return false;
    }

    private void saveConfig(Map<String, Object> configMap) {
        Editor editor = this.mContext.getApplicationContext().getSharedPreferences("config", 0).edit();
        editor.putString(PREF_CONFIG_KEY, MERGED_CONFIG);
        Gson gson = new Gson();
        editor.putString(PREF_CURRENT_CONFIG, !(gson instanceof Gson) ? gson.toJson((Object) configMap) : GsonInstrumentation.toJson(gson, (Object) configMap));
        editor.commit();
    }

    public boolean getBooleanForKey(String key, boolean defaultValue) {
        if (hasKey(key)) {
            return getBooleanForKey(key);
        }
        return defaultValue;
    }
}
