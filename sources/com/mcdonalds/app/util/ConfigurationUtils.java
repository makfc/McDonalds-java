package com.mcdonalds.app.util;

import android.text.TextUtils;
import com.ensighten.Ensighten;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.ArrayList;

public class ConfigurationUtils {
    public static boolean isRegularPaymentFlow() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ConfigurationUtils", "isRegularPaymentFlow", null);
        String paymentFlow = (String) Configuration.getSharedInstance().getValueForKey("interface.checkoutFlow");
        return paymentFlow == null || paymentFlow.equals("regular_flow");
    }

    public static boolean isOneTimePaymentFlow() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ConfigurationUtils", "isOneTimePaymentFlow", null);
        return "one_time_payment_flow".equals(Configuration.getSharedInstance().getValueForKey("interface.checkoutFlow"));
    }

    public static boolean isOneClickPaymentFlow() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ConfigurationUtils", "isOneClickPaymentFlow", null);
        return "one_click_flow".equals(Configuration.getSharedInstance().getValueForKey("interface.checkoutFlow"));
    }

    public static boolean isGmaLiteFlow() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ConfigurationUtils", "isGmaLiteFlow", null);
        String customerConnector = (String) Configuration.getSharedInstance().getValueForKey("modules.customer.connector");
        return !TextUtils.isEmpty(customerConnector) && customerConnector.equals("MWCustomerSecurity");
    }

    public static String getCustomerSupportUrl() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ConfigurationUtils", "getCustomerSupportUrl", null);
        String customerSupportUrl = (String) Configuration.getSharedInstance().getValueForKey("interface.customerSupport.url");
        return TextUtils.isEmpty(customerSupportUrl) ? "" : customerSupportUrl;
    }

    public static boolean isInterimCheckinFlow() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ConfigurationUtils", "isInterimCheckinFlow", null);
        return "interim_checkin_flow".equals(Configuration.getSharedInstance().getValueForKey("interface.checkoutFlow"));
    }

    public static String getHomeScreenFragment() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ConfigurationUtils", "getHomeScreenFragment", null);
        String home = Configuration.getSharedInstance().getStringForKey("interface.home");
        if (TextUtils.isEmpty(home)) {
            return "dashboard";
        }
        return home.replace(URLNavigationActivity.URI_SCHEME, "");
    }

    public static boolean shouldShowCountryCode() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ConfigurationUtils", "shouldShowCountryCode", null);
        return Configuration.getSharedInstance().getBooleanForKey("interface.register.phoneNumberShowCountryCode");
    }

    public static boolean shouldFilterStoreResultsUsingGeneralStatus() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ConfigurationUtils", "shouldFilterStoreResultsUsingGeneralStatus", null);
        return Configuration.getSharedInstance().getBooleanForKey("interface.storelocator.filterSearchStoreResultsUsingGeneralStatus");
    }

    public static boolean shouldSkipOutOfStockErrorHandling(Order order) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ConfigurationUtils", "shouldSkipOutOfStockErrorHandling", new Object[]{order});
        ArrayList<Double> oosPODs = (ArrayList) Configuration.getSharedInstance().getValueForKey("interface.ordering.skipOutOfStockForPOD");
        if (order.getPayment().getPOD() == null || !ListUtils.isNotEmpty(oosPODs)) {
            return false;
        }
        return oosPODs.contains(Double.valueOf((double) order.getPayment().getPOD().integerValue().intValue()));
    }

    public static boolean shouldShowUpLiftPrice() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ConfigurationUtils", "shouldShowUpLiftPrice", null);
        return Configuration.getSharedInstance().getBooleanForKey("interface.showUpliftPrice");
    }

    public static boolean shouldEnableEditForOrderDiscountOffer() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ConfigurationUtils", "shouldEnableEditForOrderDiscountOffer", null);
        return Configuration.getSharedInstance().getBooleanForKey("interface.ordering.enableEditForOrderDiscountOffer");
    }

    public static boolean shouldAlwaysAskCVV() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ConfigurationUtils", "shouldAlwaysAskCVV", null);
        return Configuration.getSharedInstance().getBooleanForKey("interface.ordering.shouldAlwaysAskCVV");
    }

    public static int getCVVMaxLength() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ConfigurationUtils", "getCVVMaxLength", null);
        if (Configuration.getSharedInstance().hasKey("interface.ordering.CVVMaxLength")) {
            return Configuration.getSharedInstance().getIntForKey("interface.ordering.CVVMaxLength");
        }
        return 4;
    }

    public static boolean isDuplicateOrderCheckinFlow() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ConfigurationUtils", "isDuplicateOrderCheckinFlow", null);
        return Configuration.getSharedInstance().getBooleanForKey("interface.ordering.duplicateOrderCheckinFlow");
    }

    public static boolean isDuplicateOrderCheckinAllowOrdering() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ConfigurationUtils", "isDuplicateOrderCheckinAllowOrdering", null);
        return Configuration.getSharedInstance().getBooleanForKey("interface.ordering.duplicateOrderCheckinAllowOrderingFlow");
    }
}
