package com.mcdonalds.sdk.services.configuration;

import com.mcdonalds.sdk.modules.models.OrderOfferProduct;
import com.mcdonalds.sdk.modules.notification.NotificationModule;
import com.mcdonalds.sdk.modules.nutrition.NutritionModule;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DeprecatedConfiguration {
    public static Map<String, String> deprecatedConfigKeyMap;
    public static Map<String, String> reverseDeprecatedConfigKeyMap;

    /* renamed from: com.mcdonalds.sdk.services.configuration.DeprecatedConfiguration$1 */
    static class C41101 extends HashMap<String, String> {
        C41101() {
            put("interface.nutritionalInfo.tabbedNutritionalInfo.nutritionTab.hiddenColumns.hundredG", "interface.nutritionalInfo.tabbedNutritionalInfo.nutritionTab.hiddenColumns.hundred_g");
            put("modules.ordering.cacheLatestOrderMinutes", "modules.Ordering.cacheLatestOrderMinutes");
            put("modules.delivery.sendToDeliveryWebsite", "modules.Delivery.sendToDeliveryWebsite");
            put("modules.delivery.externalAddressProvider", "modules.Delivery.externalAddressProvider");
            put("modules.customer.connector", "modules.Customer.connector");
            put("modules.storeLocator.connector", "modules.StoreLocator.connector");
            put("modules.delivery", "modules.Delivery");
            put("modules.ordering.orderTotalHaveTax", "modules.Ordering.orderTotalHaveTax");
            put("modules.ordering.requestTaxInvoice", "modules.Ordering.requestTaxInvoice");
            put("modules.delivery.deliveryUrls", "modules.Delivery.deliveryUrls");
            put("modules.ordering.hideOrderTotal", "modules.Ordering.hideOrderTotal");
            put("modules.ordering.mainPods", "modules.Ordering.mainPods");
            put("modules.customer.pushNotificationTrackingBaseURL", "modules.Customer.pushNotificationTrackingBaseURL");
            put("modules.offers.imageHost", "modules.Offers.imageHost");
            put("modules.storeLocator.defaultSearchRadius", "modules.StoreLocator.defaultSearchRadius");
            put("modules.storeLocator.disablePreferenceNotificationOnCurrentStoreUpdate", "modules.StoreLocator.disablePreferenceNotificationOnCurrentStoreUpdate");
            put("modules.nutritionInfo.connector", "modules.Nutrition.connector");
            put("modules.ordering.personal", "modules.Ordering.personal");
            put("modules.ordering.orderRemark", "modules.Ordering.orderRemark");
            put("modules.ordering.invoiceRequested", "modules.Ordering.invoiceRequested");
            put("modules.ordering.invoiceTitle", "modules.Ordering.invoiceTitle");
            put(OrderOfferProduct.KEY_HUNDRED_PERCENT_OFFERS_CONSIDERED_ZERO_PRICED_OFFERS, "modules.Offers.100PercentOfferConsideredZeroPriceOffer");
            put("modules.ordering.shouldAddTaxToTotal", "modules.Ordering.shouldAddTaxToTotal");
            put(NotificationModule.CONNECTOR_KEY, "modules.Notification.connector");
            put("modules.ordering.allowDownCharge", "modules.Ordering.allowDownCharge");
            put("modules.delivery.allowDownCharge", "modules.Delivery.allowDownCharge");
            put("modules.ordering.advertisablePromotionsEnabled", "interface.ordering.advertisablePromotionsEnabled");
            put("modules.ordering.doNotShowTaxWithTotal", "modules.ordering.orderTotalHaveTax");
            put("modules.customer.maxStoreCacheSize", "modules.Customer.maxStoreCacheSize");
            put(NutritionModule.KEY_NUTRITION_THUMB_BASE_URL, "connectors.Middleware.nutritionInfo.nutritionImageBaseUrl");
            put("forceUpdate.baseUrl", "forceUpdate.forceUpdateURL");
            put("forceUpdate.headerArgs.apiKey", "forceUpdate.forceUpdateAPIKey");
        }
    }

    public static void init() {
        deprecatedConfigKeyMap = new C41101();
        reverseDeprecatedConfigKeyMap = new HashMap();
        for (Entry<String, String> e : deprecatedConfigKeyMap.entrySet()) {
            reverseDeprecatedConfigKeyMap.put(e.getValue(), e.getKey());
        }
    }
}
