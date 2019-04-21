package com.mcdonalds.sdk.services.analytics.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.tagmanager.ContainerHolder;
import com.google.android.gms.tagmanager.DataLayer;
import com.google.android.gms.tagmanager.TagManager;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.Promotion;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.AnalyticsWrapper;
import com.mcdonalds.sdk.services.analytics.tagmanager.Parameters.Events;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.log.SafeLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TagManagerWrapper extends AnalyticsWrapper implements ResultCallback<ContainerHolder> {
    public static final String CONFIG_KEY = "TagManager";
    public static final String KEY_CONTAINER_ID = "analytics.TagManager.containerId";
    public static final String KEY_ENVIRONMENT_TYPE = "analytics.TagManager.environmentType";
    public static final String KEY_LOG_ANALYTICS = "log.analytics";
    public static final String KEY_MARKET_ID = "analytics.TagManager.marketId";
    public static final String LOG_TAG = TagManagerWrapper.class.getSimpleName();
    Map<String, Object> mCustomParams;
    private String mEnvironment = ((String) Configuration.getSharedInstance().getValueForKey(KEY_ENVIRONMENT_TYPE));
    private boolean mInitialized;
    private boolean mInitializing;
    private String mMarket = ((String) Configuration.getSharedInstance().getValueForKey(KEY_MARKET_ID));
    private List<PendingEvent> mPendingEvents;
    private TagManager mTagManager;
    private String mVersion = "1.0";

    private static final class PendingEvent {
        private AnalyticsArgs args;
        private AnalyticType type;

        public PendingEvent(AnalyticType type, AnalyticsArgs args) {
            this.type = type;
            this.args = args;
        }

        public AnalyticType getType() {
            return this.type;
        }

        public AnalyticsArgs getArgs() {
            return this.args;
        }
    }

    public TagManagerWrapper(Context context) {
        super(context);
        try {
            this.mVersion = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        this.mPendingEvents = new ArrayList();
        boolean debuggable = Configuration.getSharedInstance().getBooleanForKey(KEY_LOG_ANALYTICS);
        this.mTagManager = TagManager.getInstance(context);
        this.mTagManager.setVerboseLoggingEnabled(debuggable);
    }

    public void initialize() {
        if (!this.mInitialized && !this.mInitializing) {
            this.mInitializing = true;
            String containerId = (String) Configuration.getSharedInstance().getValueForKey(KEY_CONTAINER_ID);
            this.mTagManager.loadContainerPreferFresh(containerId, this.mContext.getResources().getIdentifier(containerId.replace("-", "").toLowerCase(), "raw", this.mContext.getPackageName())).setResultCallback(this);
        }
    }

    public void onResult(ContainerHolder containerHolder) {
        this.mInitializing = false;
        this.mInitialized = true;
        dispatchPendingEvents();
    }

    private void dispatchPendingEvents() {
        if (!this.mPendingEvents.isEmpty()) {
            Iterator<PendingEvent> i = this.mPendingEvents.iterator();
            while (i.hasNext()) {
                PendingEvent event = (PendingEvent) i.next();
                log(event.getType(), event.getArgs());
                i.remove();
            }
        }
    }

    public void log(AnalyticType event, AnalyticsArgs args) {
        if (this.mInitializing) {
            this.mPendingEvents.add(new PendingEvent(event, args));
            return;
        }
        putDefaultCustoms();
        switch (event) {
            case ScreenLoad:
                trackScreen(args);
                return;
            case Event:
                trackEvent(args);
                return;
            case Custom:
                trackCustom(args, true);
                return;
            case Transaction:
                trackTransaction(args);
                return;
            default:
                return;
        }
    }

    private void trackScreen(AnalyticsArgs args) {
        String screenName = (String) args.get("ACTION");
        if (screenName != null) {
            Map<String, Object> update = DataLayer.mapOf(Parameters.SCREEN_NAME, screenName);
            Log.d(LOG_TAG, "SCREEN: " + screenName);
            trackCustom(args, false);
            update.putAll(this.mCustomParams);
            this.mTagManager.getDataLayer().pushEvent(Events.OPEN_SCREEN, update);
            clearDataLayerVariables(new ArrayList(update.keySet()));
        }
    }

    private void trackEvent(AnalyticsArgs args) {
        String action = (String) args.get("ACTION");
        String category = (String) args.get(AnalyticsArgs.DATAKEY_CATEGORY);
        String label = (String) args.get(AnalyticsArgs.DATAKEY_LABEL);
        Offer promotion = (Offer) args.get(AnalyticsArgs.DATAKEY_IMPRESSION);
        if (action != null) {
            Map<String, Object> update = DataLayer.mapOf(Parameters.ACTION, action, "category", category, "label", label);
            Log.d(LOG_TAG, "CATEGORY: " + category + ", ACTION: " + action + ", LABEL: " + label);
            trackCustom(args, false);
            update.putAll(this.mCustomParams);
            trackECommerce(update, promotion);
            this.mTagManager.getDataLayer().pushEvent(Events.ACTION, update);
            clearDataLayerVariables(new ArrayList(update.keySet()));
        }
    }

    private void trackECommerce(Map<String, Object> map, Object eCommerce) {
        if (eCommerce != null) {
            Offer offer = (Offer) eCommerce;
            Object[] objArr = new Object[2];
            objArr[0] = "promoView";
            Object[] objArr2 = new Object[2];
            objArr2[0] = Promotion.TABLE_NAME;
            Object[] objArr3 = new Object[1];
            r5 = new Object[8];
            r5[5] = String.format("%s - %s", new Object[]{offer.getLocalValidFrom(), offer.getLocalValidThrough()});
            r5[6] = "position";
            r5[7] = AnalyticsArgs.ECOMMERCE_PROMOTION;
            objArr3[0] = DataLayer.mapOf(r5);
            objArr2[1] = DataLayer.listOf(objArr3);
            objArr[1] = DataLayer.mapOf(objArr2);
            map.put("ecommerce", DataLayer.mapOf(objArr));
        }
    }

    private void trackCustom(AnalyticsArgs args, boolean shouldSend) {
        try {
            SparseArray<String> custom = (SparseArray) args.get(AnalyticsArgs.DATAKEY_CUSTOM);
            if (custom != null) {
                for (int i = 0; i < custom.size(); i++) {
                    int key = custom.keyAt(i);
                    String value = (String) custom.get(key);
                    if (value != null) {
                        this.mCustomParams.put(Integer.toString(key), value);
                        Log.d(LOG_TAG, String.format("%s %s", new Object[]{Integer.valueOf(key), value}));
                    }
                }
                if (shouldSend) {
                    this.mTagManager.getDataLayer().pushEvent(Events.OPEN_SCREEN, this.mCustomParams);
                }
            }
        } catch (ClassCastException e) {
            SafeLog.m7746e(LOG_TAG, "error logging custom", e);
        }
    }

    private void putDefaultCustoms() {
        this.mCustomParams = new HashMap();
        this.mCustomParams.put(Integer.toString(15), this.mVersion);
        this.mCustomParams.put(Integer.toString(7), Analytics.getTimestamp());
        this.mCustomParams.put(Integer.toString(14), this.mEnvironment);
        this.mCustomParams.put(Integer.toString(16), LocalDataManager.getSharedInstance().getDeviceLanguage());
        this.mCustomParams.put(Integer.toString(4), this.mMarket);
        this.mCustomParams.put(Integer.toString(44), Locale.getDefault().getCountry());
        String location = ArgBuilder.getFormattedLocation();
        if (location != null) {
            this.mCustomParams.put(Integer.toString(6), location);
        }
    }

    private void trackTransaction(AnalyticsArgs args) {
        try {
            List<Object> products = (List) args.get(AnalyticsArgs.PRODUCT_LIST);
            Map<String, Object> transaction = (Map) args.get(AnalyticsArgs.TRANSACTION_MAP);
            if (transaction != null) {
                if (!(products == null || products.isEmpty())) {
                    transaction.put(AnalyticsArgs.TRANSACTION_PRODUCTS, products);
                }
                this.mTagManager.getDataLayer().pushEvent(Events.TRANSACTION, DataLayer.mapOf(transaction));
                clearDataLayerVariables(new ArrayList(transaction.keySet()));
            }
        } catch (ClassCastException e) {
            SafeLog.m7746e(LOG_TAG, "error logging transaction", e);
        }
    }

    private void clearDataLayerVariables(List<String> variables) {
        Map<String, Object> update = new HashMap();
        for (String var : variables) {
            update.put(var, null);
        }
        this.mTagManager.getDataLayer().push(update);
    }
}
