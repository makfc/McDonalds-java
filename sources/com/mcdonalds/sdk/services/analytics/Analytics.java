package com.mcdonalds.sdk.services.analytics;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import com.mcdonalds.sdk.McDonalds;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.OrderResponse;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ProductMapBuilder;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.TransactionMapBuilder;
import com.mcdonalds.sdk.services.analytics.conversionmaster.ConversionMasterWrapper;
import com.mcdonalds.sdk.services.analytics.enhancedKochava.EnhancedKochavaAnalyticsWrapper;
import com.mcdonalds.sdk.services.analytics.google.GoogleAnalyticsWrapper;
import com.mcdonalds.sdk.services.analytics.jice.JiceWrapper;
import com.mcdonalds.sdk.services.analytics.kochava.KochavaAnalyticsWrapper;
import com.mcdonalds.sdk.services.analytics.tagmanager.TagManagerWrapper;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.log.MCDLog;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Analytics {
    public static final String ANALYTICS_CONFIGS_PATH = "analytics";
    public static final String LOG_TAG = "Analytics";
    private static Analytics mSharedInstance;
    private Context mContext;
    private boolean mIsInitialized;
    private List<AnalyticsWrapper> mWrappers;

    private Analytics() {
        initialize(McDonalds.getContext());
    }

    public static Analytics getSharedInstance() {
        if (mSharedInstance == null) {
            mSharedInstance = new Analytics();
        } else if (!mSharedInstance.mIsInitialized) {
            return null;
        }
        return mSharedInstance;
    }

    public static void setSharedInstance(Analytics analytics) {
        mSharedInstance = analytics;
    }

    public static void destroy() {
        mSharedInstance = null;
    }

    public Analytics initialize(Context context) {
        this.mContext = context;
        this.mIsInitialized = true;
        this.mWrappers = new ArrayList();
        initWrappers();
        return this;
    }

    private void initWrappers() {
        Map<String, Object> analyticsWrappers = (Map) Configuration.getSharedInstance().getValueForKey(ANALYTICS_CONFIGS_PATH);
        if (analyticsWrappers != null) {
            for (String implementation : analyticsWrappers.keySet()) {
                AnalyticsWrapper wrapper = getWrapper(implementation);
                if (wrapper != null) {
                    wrapper.initialize();
                    this.mWrappers.add(wrapper);
                }
            }
        }
    }

    public static void track(AnalyticType type, AnalyticsArgs args) {
        if (getSharedInstance() != null) {
            getSharedInstance().trackInternal(type, args);
        }
    }

    public static void trackCustom(int key, String value) {
        SparseArray<String> customDimensions = new SparseArray();
        customDimensions.put(key, value);
        track(AnalyticType.Custom, new ArgBuilder().setCustom(customDimensions).build());
    }

    public static void trackTransaction(OrderResponse response, Order order) {
        if (order != null && response != null) {
            Map<String, Object> transactionMap = new TransactionMapBuilder().setTransactionId(response.getDisplayOrderNumber()).setTransactionAffiliation(response.getOrderView().getStoreID()).setTransactionRevenue(response.getTotalValue()).setTransactionTax(response.getTotalTax()).setTransactionShipping(Double.valueOf(0.0d)).setTransactionCurrencyCode("").build();
            List<Map<String, Object>> productList = new ArrayList();
            for (OrderProduct orderProduct : order.getProducts()) {
                productList.add(new ProductMapBuilder().setTransactionId(response.getDisplayOrderNumber()).setTransactionItemName(orderProduct.getProduct().getName()).setTransactionSKU(orderProduct.getProduct().getId()).setTransactionCategory("In app ordering").setTransactionPrice(Double.valueOf(orderProduct.getProduct().getPrice(order.getPriceType()))).setTransactionQuantity(Integer.valueOf(orderProduct.getQuantity())).setTransactionCurrency("").build());
            }
            track(AnalyticType.Transaction, new ArgBuilder().setTransactionMap(transactionMap).setProductList(productList).build());
        }
    }

    private void trackInternal(AnalyticType type, AnalyticsArgs data) {
        for (AnalyticsWrapper wrapper : this.mWrappers) {
            try {
                wrapper.log(type, data);
            } catch (Exception e) {
                MCDLog.error(LOG_TAG, e.getMessage());
            }
        }
    }

    public static String getTimestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z", Locale.getDefault()).format(new Date());
    }

    private AnalyticsWrapper getWrapper(String implementation) {
        int i = -1;
        switch (implementation.hashCode()) {
            case -1230904621:
                if (implementation.equals(TagManagerWrapper.CONFIG_KEY)) {
                    i = 1;
                    break;
                }
                break;
            case -585329427:
                if (implementation.equals(EnhancedKochavaAnalyticsWrapper.CONFIG_KEY)) {
                    i = 2;
                    break;
                }
                break;
            case 2308609:
                if (implementation.equals(JiceWrapper.CONFIG_KEY)) {
                    i = 4;
                    break;
                }
                break;
            case 796981048:
                if (implementation.equals(ConversionMasterWrapper.CONFIG_KEY)) {
                    i = 5;
                    break;
                }
                break;
            case 1115758915:
                if (implementation.equals(KochavaAnalyticsWrapper.CONFIG_KEY)) {
                    i = 3;
                    break;
                }
                break;
            case 2138589785:
                if (implementation.equals(GoogleAnalyticsWrapper.CONFIG_KEY)) {
                    i = 0;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                if (Configuration.getSharedInstance().getBooleanForKey("analytics.Google.disableClassicTagging")) {
                    return null;
                }
                return new GoogleAnalyticsWrapper(this.mContext);
            case 1:
                return new TagManagerWrapper(this.mContext);
            case 2:
                if (Configuration.getSharedInstance().getBooleanForKey(EnhancedKochavaAnalyticsWrapper.KEY_ENABLED)) {
                    return new EnhancedKochavaAnalyticsWrapper(this.mContext);
                }
                return null;
            case 3:
                return new KochavaAnalyticsWrapper(this.mContext);
            case 4:
                return new JiceWrapper(this.mContext);
            case 5:
                return new ConversionMasterWrapper(this.mContext);
            default:
                Log.e(LOG_TAG, String.format("No Valid AnalyticsWrapper found for type: %s", new Object[]{implementation}));
                return null;
        }
    }
}
