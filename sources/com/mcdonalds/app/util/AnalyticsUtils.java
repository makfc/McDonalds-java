package com.mcdonalds.app.util;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.SparseArray;
import com.ensighten.Ensighten;
import com.mcdonalds.sdk.McDonalds;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.Pod;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.BusinessArgs;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.analytics.conversionmaster.Action;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AnalyticsUtils {
    private static String lastPage;

    public static void trackHamburgerMenu() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AnalyticsUtils", "trackHamburgerMenu", null);
        trackOnClickEvent("/side-menu", "Hamburger Button");
    }

    public static void trackStoreSelection(@NonNull Store store) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AnalyticsUtils", "trackStoreSelection", new Object[]{store});
        SparseArray customArgs = new SparseArray();
        customArgs.put(1, Integer.toString(store.getStoreId()));
        customArgs.put(53, store.getAddress1());
        trackOnClickEvent("/restaurant-locator", "Select", customArgs);
    }

    public static void trackSkipFavoritesButton() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AnalyticsUtils", "trackSkipFavoritesButton", null);
        trackOnClickEvent("/restaurant-locator", "Skip - Save to favorites");
    }

    public static void trackSearch(String analyticsTitle, String address) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AnalyticsUtils", "trackSearch", new Object[]{analyticsTitle, address});
        if (!TextUtils.isEmpty(address)) {
            Analytics.track(AnalyticType.Event, new ArgBuilder().setCategory(analyticsTitle).setAction("On click").setLabel("Search").addCustom(19, address).build());
        }
    }

    public static void trackFoodSearch(String analyticsTitle, String searchTerm) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AnalyticsUtils", "trackFoodSearch", new Object[]{analyticsTitle, searchTerm});
        if (!TextUtils.isEmpty(searchTerm)) {
            Analytics.track(AnalyticType.Event, new ArgBuilder().setCategory(analyticsTitle).setAction("On click").setLabel(searchTerm).build());
        }
    }

    public static void trackCheckout(String category, Order order, Store store) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AnalyticsUtils", "trackCheckout", new Object[]{category, order, store});
        SparseArray customArgs = new SparseArray();
        customArgs.put(8, String.valueOf(order.getTotalEnergy()));
        customArgs.put(9, store.getStoreType());
        customArgs.put(40, order.isDelivery() ? Pod.DELIVERY : Pod.PICKUP);
        customArgs.put(42, String.valueOf(order.getTotalValue()));
        customArgs.put(23, getDayPart(order.getDayPart()));
        String location = ArgBuilder.getFormattedLocation();
        if (location != null) {
            customArgs.put(6, location);
        }
        trackOnClickEvent(category, BusinessArgs.EVENT_CHECKOUT, customArgs);
        StringBuilder productCodes = new StringBuilder();
        StringBuilder productNames = new StringBuilder();
        for (OrderProduct product : order.getProducts()) {
            for (OrderProduct customization : product.getCustomizations().values()) {
                customArgs.put(37, product.getProduct().getName());
                customArgs.put(38, customization.getProduct().getName());
                customArgs.put(39, String.valueOf(customization.getQuantity()));
                Analytics.track(AnalyticType.Custom, new ArgBuilder().setCustom(customArgs).build());
            }
            Analytics.trackCustom(27, product.getProduct().getName());
            Analytics.track(AnalyticType.Event, new ArgBuilder().setBusiness(BusinessArgs.getProductFromBasket(product)).build());
            productCodes.append(product.getProductCode()).append("_");
            productNames.append(product.getProduct().getLongName()).append("_");
        }
        if (productCodes.length() > 0) {
            productCodes.deleteCharAt(productCodes.length() - 1);
        }
        if (productNames.length() > 0) {
            productNames.deleteCharAt(productNames.length() - 1);
        }
        HashMap<String, Object> jiceMap = new HashMap();
        jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_CHECKOUT);
        jiceMap.put(JiceArgs.LABEL_ITEM_ID, productCodes.toString());
        jiceMap.put(JiceArgs.LABEL_ITEM_NAME, productNames.toString());
        jiceMap.put(JiceArgs.LABEL_ITEM_IS_DELIVERY, String.valueOf(order.isDelivery()));
        jiceMap.put(JiceArgs.LABEL_ITEM_IS_PICKUP, String.valueOf(!order.isDelivery()));
        String currentDate = UIUtils.formatTime(McDonalds.getContext(), new Date());
        trackEvent(new ArgBuilder().setLabel("order_confirm").setMapping("order_id", currentDate).setMapping("curr_cd", Configuration.getSharedInstance().getCurrencyCode()).setMapping("order_amt", Double.valueOf(order.getTotalValue())).setMapping("local_timestamp", currentDate).setJice(jiceMap).build());
    }

    public static void trackOnClickEvent(String category, String label) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AnalyticsUtils", "trackOnClickEvent", new Object[]{category, label});
        trackEvent(category, "On click", label);
    }

    public static void trackOnClickEvent(String category, String label, Map<String, Object> jiceMap) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AnalyticsUtils", "trackOnClickEvent", new Object[]{category, label, jiceMap});
        trackOnClickEvent(category, label, (Map) jiceMap, null);
    }

    public static void trackOnClickEvent(String category, String label, Map<String, Object> jiceMap, Action a) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AnalyticsUtils", "trackOnClickEvent", new Object[]{category, label, jiceMap, a});
        Analytics.track(AnalyticType.Event, new ArgBuilder().setCategory(category).setAction("On click").setLabel(label).setJice(jiceMap).setConversionMaster(a).build());
    }

    public static void trackOnClickEvent(String category, String label, SparseArray<String> custom, Map<String, Object> jiceMap) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AnalyticsUtils", "trackOnClickEvent", new Object[]{category, label, custom, jiceMap});
        Analytics.track(AnalyticType.Event, new ArgBuilder().setCategory(category).setAction("On click").setLabel(label).setCustom(custom).setJice(jiceMap).build());
    }

    public static void trackOnClickEvent(String category, String label, SparseArray<String> custom) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AnalyticsUtils", "trackOnClickEvent", new Object[]{category, label, custom});
        Analytics.track(AnalyticType.Event, new ArgBuilder().setCategory(category).setAction("On click").setLabel(label).setCustom(custom).build());
    }

    public static void trackEvent(String category, String action, String label) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AnalyticsUtils", "trackEvent", new Object[]{category, action, label});
        Analytics.track(AnalyticType.Event, new ArgBuilder().setCategory(category).setAction(action).setLabel(label).build());
    }

    public static void trackEvent(String label) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AnalyticsUtils", "trackEvent", new Object[]{label});
        trackEvent(new ArgBuilder().setLabel(label).build());
    }

    public static void trackEvent(String label, String value) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AnalyticsUtils", "trackEvent", new Object[]{label, value});
        trackEvent(new ArgBuilder().setLabel(label).setValue(value).build());
    }

    public static void trackEvent(AnalyticsArgs args) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AnalyticsUtils", "trackEvent", new Object[]{args});
        Analytics.track(AnalyticType.Event, args);
    }

    public static String getDayPart(int menuTypeID) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AnalyticsUtils", "getDayPart", new Object[]{new Integer(menuTypeID)});
        return menuTypeID == 0 ? "Breakfast" : "Regular";
    }

    public static void trackOnSlideEvent(String category, String label) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AnalyticsUtils", "trackOnSlideEvent", new Object[]{category, label});
        trackEvent(category, "On Slide", label);
    }

    public static void trackScreenLoad(@NonNull String action) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AnalyticsUtils", "trackScreenLoad", new Object[]{action});
        lastPage = action;
        SparseArray<String> customArgs = new SparseArray();
        customArgs.put(13, action);
        Analytics.track(AnalyticType.ScreenLoad, new ArgBuilder().setCategory("Screen").setAction(action).setCustom(customArgs).build());
    }

    public static void trackAppFinish() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AnalyticsUtils", "trackAppFinish", null);
        trackEvent(new ArgBuilder().setLabel("last_page_view").setMapping("page_name", lastPage).build());
    }
}
