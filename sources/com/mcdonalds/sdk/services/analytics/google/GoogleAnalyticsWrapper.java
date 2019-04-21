package com.mcdonalds.sdk.services.analytics.google;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.analytics.ExceptionReporter;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders.AppViewBuilder;
import com.google.android.gms.analytics.HitBuilders.EventBuilder;
import com.google.android.gms.analytics.HitBuilders.ExceptionBuilder;
import com.google.android.gms.analytics.HitBuilders.ItemBuilder;
import com.google.android.gms.analytics.HitBuilders.TransactionBuilder;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs;
import com.mcdonalds.sdk.services.analytics.AnalyticsWrapper;
import com.mcdonalds.sdk.services.analytics.tagmanager.TagManagerWrapper;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.log.MCDLog;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class GoogleAnalyticsWrapper extends AnalyticsWrapper {
    static final /* synthetic */ boolean $assertionsDisabled = (!GoogleAnalyticsWrapper.class.desiredAssertionStatus());
    public static final String CONFIG_KEY = "Google";
    private static final String TAG = "GoogleAnalyticsWrapper";
    private Tracker mAppTracker;
    private String mEnvironment = ((String) Configuration.getSharedInstance().getValueForKey("analytics.Google.environmentType"));
    private boolean mInitialized = false;
    private String mMarket = ((String) Configuration.getSharedInstance().getValueForKey("analytics.Google.marketId"));
    private boolean mShowLogs = Configuration.getSharedInstance().getBooleanForKey(TagManagerWrapper.KEY_LOG_ANALYTICS);
    private String mVersion = "1.0";

    public GoogleAnalyticsWrapper(Context context) {
        super(context);
        try {
            this.mVersion = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        if (!this.mInitialized) {
            String trackingId = (String) Configuration.getSharedInstance().getValueForKey("analytics.Google.trackingId");
            if (trackingId != null) {
                GoogleAnalytics googleAnalytics = GoogleAnalytics.getInstance(this.mContext);
                if (this.mShowLogs) {
                    googleAnalytics.getLogger().setLogLevel(0);
                }
                this.mAppTracker = googleAnalytics.newTracker(trackingId);
                this.mAppTracker.enableAdvertisingIdCollection(true);
                enableUncaughtExceptionHandling(this.mContext, this.mAppTracker);
                this.mInitialized = true;
            }
        }
    }

    public synchronized void log(AnalyticType event, AnalyticsArgs args) {
        if (event != null && args != null) {
            Object action;
            Object custom;
            SparseArray<String> dimensions;
            int index;
            int key;
            String keyValue;
            switch (event) {
                case ScreenLoad:
                    action = args.get("ACTION");
                    custom = args.get(AnalyticsArgs.DATAKEY_CUSTOM);
                    checkNotNullAndType(String.class, action);
                    if (this.mShowLogs) {
                        Log.d("GOOGLE ANALYTICS", "SCREEN: " + action);
                    }
                    this.mAppTracker.setScreenName((String) action);
                    AppViewBuilder appViewBuilder = new AppViewBuilder();
                    putCommonAppCustomDimensions(appViewBuilder);
                    if (custom != null) {
                        dimensions = (SparseArray) custom;
                        for (index = 0; index < dimensions.size(); index++) {
                            key = dimensions.keyAt(index);
                            keyValue = (String) dimensions.valueAt(index);
                            appViewBuilder.setCustomDimension(key, keyValue);
                            if (this.mShowLogs) {
                                Log.d("GOOGLE ANALYTICS CD ", String.valueOf(key) + " " + keyValue);
                            }
                        }
                    }
                    this.mAppTracker.send(appViewBuilder.build());
                    break;
                case Event:
                    Object category = args.get(AnalyticsArgs.DATAKEY_CATEGORY);
                    action = args.get("ACTION");
                    Object label = args.get(AnalyticsArgs.DATAKEY_LABEL);
                    Object value = args.get(AnalyticsArgs.DATAKEY_VALUE);
                    custom = args.get(AnalyticsArgs.DATAKEY_CUSTOM);
                    Object impression = args.get(AnalyticsArgs.DATAKEY_IMPRESSION);
                    checkNotNullAndType(String.class, category, action);
                    if (this.mShowLogs) {
                        Log.d("GOOGLE ANALYTICS", "CATEGORY: " + category + ", ACTION: " + action + ", LABEL: " + label);
                    }
                    EventBuilder eventBuilder = new EventBuilder();
                    eventBuilder.setCategory((String) category);
                    eventBuilder.setAction((String) action);
                    putCommonEventCustomDimensions(eventBuilder);
                    if (custom != null) {
                        dimensions = (SparseArray) custom;
                        for (index = 0; index < dimensions.size(); index++) {
                            key = dimensions.keyAt(index);
                            keyValue = (String) dimensions.valueAt(index);
                            if (keyValue != null) {
                                eventBuilder.setCustomDimension(key, keyValue);
                                if (this.mShowLogs) {
                                    Log.d("GOOGLE ANALYTICS CD", String.valueOf(key) + " " + keyValue);
                                }
                            }
                        }
                    }
                    if (label instanceof String) {
                        eventBuilder.setLabel((String) label);
                    }
                    if (value instanceof Long) {
                        eventBuilder.setValue(((Long) value).longValue());
                    }
                    if (impression != null) {
                        Offer offer = (Offer) impression;
                        eventBuilder.addPromotion(new Promotion().setId(offer.getOfferId().toString()).setName(offer.getName()).setCreative(String.format("%s - %s", new Object[]{offer.getLocalValidFrom(), offer.getLocalValidThrough()})).setPosition(AnalyticsArgs.ECOMMERCE_PROMOTION));
                    }
                    this.mAppTracker.send(eventBuilder.build());
                    break;
                case Exception:
                    checkNotNullAndType(Throwable.class, args.get(AnalyticsArgs.DATAKEY_THROWABLE));
                    StackTraceElement[] stackTraceElements = ((Throwable) exception).getStackTrace();
                    String methodName = "Unknown method";
                    String location = "Unknown location";
                    if (stackTraceElements.length > 0) {
                        methodName = stackTraceElements[0].getMethodName();
                        location = stackTraceElements[0].getFileName() + ":" + stackTraceElements[0].getLineNumber();
                    }
                    ExceptionBuilder exceptionBuilder = new ExceptionBuilder();
                    putCommonExceptionCustomDimensions(exceptionBuilder);
                    this.mAppTracker.send(exceptionBuilder.setDescription(methodName + " => " + location).setFatal(false).build());
                    break;
                case Custom:
                    action = args.get("ACTION");
                    checkNotNullAndType(SparseArray.class, args.get(AnalyticsArgs.DATAKEY_CUSTOM));
                    AppViewBuilder appView = new AppViewBuilder();
                    putCommonAppCustomDimensions(appView);
                    dimensions = (SparseArray) custom;
                    for (index = 0; index < dimensions.size(); index++) {
                        key = dimensions.keyAt(index);
                        keyValue = (String) dimensions.valueAt(index);
                        if (keyValue != null) {
                            appView.setCustomDimension(key, keyValue);
                            if (this.mShowLogs) {
                                Log.d("GOOGLE ANALYTICS CD", String.valueOf(key) + " " + keyValue);
                            }
                        }
                    }
                    if (action instanceof String) {
                        this.mAppTracker.setScreenName((String) action);
                    }
                    this.mAppTracker.send(appView.build());
                    break;
                case Transaction:
                    Object transaction = args.get(AnalyticsArgs.TRANSACTION_MAP);
                    Object products = args.get(AnalyticsArgs.PRODUCT_LIST);
                    checkNotNullAndType(Map.class, transaction);
                    checkNotNullAndType(List.class, products);
                    Map<String, Object> transactionMap = (Map) transaction;
                    List<Map<String, Object>> productList = (List) products;
                    if ($assertionsDisabled || transactionMap != null) {
                        this.mAppTracker.send(new TransactionBuilder().setTransactionId((String) transactionMap.get(AnalyticsArgs.TRANSACTION_ID)).setAffiliation((String) transactionMap.get(AnalyticsArgs.TRANSACTION_AFFILIATION)).setRevenue(((Double) transactionMap.get(AnalyticsArgs.TRANSACTION_REVENUE)).doubleValue()).setTax(((Double) transactionMap.get(AnalyticsArgs.TRANSACTION_TAX)).doubleValue()).setShipping(((Double) transactionMap.get(AnalyticsArgs.TRANSACTION_SHIPPING)).doubleValue()).setCurrencyCode((String) transactionMap.get(AnalyticsArgs.TRANSACTION_CURRENCY)).build());
                        for (Map<String, Object> productMap : productList) {
                            this.mAppTracker.send(new ItemBuilder().setTransactionId((String) productMap.get(AnalyticsArgs.TRANSACTION_ID)).setName((String) productMap.get("name")).setSku((String) productMap.get(AnalyticsArgs.TRANSACTION_ITEM_SKU)).setCategory((String) productMap.get("category")).setPrice(((Double) productMap.get(AnalyticsArgs.TRANSACTION_ITEM_PRICE)).doubleValue()).setQuantity((long) ((Integer) productMap.get(AnalyticsArgs.TRANSACTION_ITEM_QUANTITY)).intValue()).build());
                        }
                        break;
                    }
                    throw new AssertionError();
                    break;
                default:
                    break;
            }
        }
        MCDLog.error(TAG, "Attempted to log an analytic event without proper arguments");
    }

    private void putCommonAppCustomDimensions(AppViewBuilder appViewBuilder) {
        appViewBuilder.setCustomDimension(15, this.mVersion);
        appViewBuilder.setCustomDimension(7, Analytics.getTimestamp());
        appViewBuilder.setCustomDimension(14, this.mEnvironment);
        appViewBuilder.setCustomDimension(16, Locale.getDefault().getLanguage());
        appViewBuilder.setCustomDimension(4, this.mMarket);
        appViewBuilder.setCustomDimension(44, Locale.getDefault().getCountry());
    }

    private void putCommonEventCustomDimensions(EventBuilder eventBuilder) {
        eventBuilder.setCustomDimension(15, this.mVersion);
        eventBuilder.setCustomDimension(7, Analytics.getTimestamp());
        eventBuilder.setCustomDimension(14, this.mEnvironment);
        eventBuilder.setCustomDimension(16, Locale.getDefault().getLanguage());
        eventBuilder.setCustomDimension(4, this.mMarket);
        eventBuilder.setCustomDimension(44, Locale.getDefault().getCountry());
    }

    private void putCommonExceptionCustomDimensions(ExceptionBuilder exceptionBuilder) {
        exceptionBuilder.setCustomDimension(15, this.mVersion);
        exceptionBuilder.setCustomDimension(7, Analytics.getTimestamp());
        exceptionBuilder.setCustomDimension(14, this.mEnvironment);
        exceptionBuilder.setCustomDimension(16, Locale.getDefault().getLanguage());
        exceptionBuilder.setCustomDimension(4, this.mMarket);
        exceptionBuilder.setCustomDimension(44, Locale.getDefault().getCountry());
    }

    private void checkNotNullAndType(Class<?> type, Object... args) {
        for (Object arg : args) {
            if (arg == null || !type.isInstance(arg)) {
                throw new AsyncException("Invalid arguments");
            }
        }
    }

    private void enableUncaughtExceptionHandling(Context context, Tracker tracker) {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionReporter(tracker, Thread.getDefaultUncaughtExceptionHandler(), context));
    }
}
