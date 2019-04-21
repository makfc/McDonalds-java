package com.mcdonalds.app.util;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.p000v4.media.TransportMediator;
import android.text.TextUtils;
import com.ensighten.Ensighten;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.alert.AlertActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.storelocator.maps.model.LatLng;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.MenuType;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.nutrition.NutritionModule;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.MenuTypeCalendarItem;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.location.LocationServicesManager;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AppUtils {
    public static final Double RATE_KJ_KCAL = Double.valueOf(4.184d);

    /* renamed from: com.mcdonalds.app.util.AppUtils$1 */
    static class C38411 implements Runnable {
        final /* synthetic */ AsyncListener val$listener;

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            this.val$listener.onResponse(null, null, null);
        }
    }

    /* renamed from: com.mcdonalds.app.util.AppUtils$2 */
    static class C38422 implements AsyncListener<List<Void>> {
        final /* synthetic */ AsyncListener val$listener;
        final /* synthetic */ Map val$ret;

        public void onResponse(List response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception == null) {
                this.val$listener.onResponse(this.val$ret, null, null);
            } else {
                this.val$listener.onResponse(null, null, exception);
            }
        }
    }

    public static LatLng getLocationFromString(String latitude, String longitude) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AppUtils", "getLocationFromString", new Object[]{latitude, longitude});
        if (TextUtils.isEmpty(latitude) || TextUtils.isEmpty(longitude)) {
            return null;
        }
        try {
            return new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Location getUserLocation() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AppUtils", "getUserLocation", null);
        Location location = LocationServicesManager.getInstance().getCurrentUserLocation();
        DataLayerManager.getInstance().setLocation(location);
        return location;
    }

    public static LatLng getLocationFromString(String location) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AppUtils", "getLocationFromString", new Object[]{location});
        if (location == null) {
            return null;
        }
        String[] values = location.replaceAll("\\s", "").split(",");
        try {
            return new LatLng(Double.parseDouble(values[0]), Double.parseDouble(values[1]));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static String stringByAddingPercentEscapesUsingEncoding(String input, String encoding) throws UnsupportedEncodingException {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AppUtils", "stringByAddingPercentEscapesUsingEncoding", new Object[]{input, encoding});
        byte[] inputBytes = input.getBytes(encoding);
        StringBuilder stringBuilder = new StringBuilder(inputBytes.length);
        int i = 0;
        while (i < inputBytes.length) {
            int charByte = inputBytes[i] < (byte) 0 ? inputBytes[i] + 256 : inputBytes[i];
            if (charByte <= 32 || charByte >= TransportMediator.KEYCODE_MEDIA_PAUSE || charByte == 34 || charByte == 37 || charByte == 60 || charByte == 62 || charByte == 32 || charByte == 91 || charByte == 92 || charByte == 93 || charByte == 94 || charByte == 96 || charByte == 123 || charByte == 124 || charByte == 125) {
                stringBuilder.append(String.format("%%%02X", new Object[]{Integer.valueOf(charByte)}));
            } else {
                stringBuilder.append((char) charByte);
            }
            i++;
        }
        return stringBuilder.toString();
    }

    public static boolean isNetworkConnected(Context context) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AppUtils", "isNetworkConnected", new Object[]{context});
        NetworkInfo info = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (info == null || !info.isConnected()) {
            return false;
        }
        return true;
    }

    public static String getLocalizedUrl(String key, String localizedBaseKey, String defaultBaseKey) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AppUtils", "getLocalizedUrl", new Object[]{key, localizedBaseKey, defaultBaseKey});
        String locale = LocalDataManager.getSharedInstance().getDeviceLanguage();
        if (TextUtils.isEmpty(locale)) {
            locale = Locale.getDefault().getLanguage();
        }
        String localizedUrl = (String) Configuration.getSharedInstance().getValueForKey(MessageFormat.format(localizedBaseKey, new Object[]{key, locale}));
        if (!TextUtils.isEmpty(localizedUrl)) {
            return localizedUrl;
        }
        return (String) Configuration.getSharedInstance().getValueForKey(MessageFormat.format(defaultBaseKey, new Object[]{key}));
    }

    public static String getLocalisedLegalUrl(String key) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AppUtils", "getLocalisedLegalUrl", new Object[]{key});
        return getLocalizedUrl(key, "interface.legalCopyLocalized.{0}.{1}", "interface.legalCopy.{0}");
    }

    public static String getEnergyTextForOrderProduct(OrderProduct product, String energyUnit) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AppUtils", "getEnergyTextForOrderProduct", new Object[]{product, energyUnit});
        double totalEnergy = product.getTotalEnergy();
        double secondaryEnergy = 0.0d;
        String secondaryEnergyUnit = Configuration.getSharedInstance().getStringForKey("interface.nutritionalInfo.secondaryEnergyUnit");
        if (!TextUtils.isEmpty(secondaryEnergyUnit)) {
            secondaryEnergy = product.getTotalSecondaryEnergy();
        }
        return getEnergyText(totalEnergy, secondaryEnergy, energyUnit, secondaryEnergyUnit);
    }

    public static String getEnergyTextForOrderOffer(OrderOffer orderOffer, String energyUnit) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AppUtils", "getEnergyTextForOrderOffer", new Object[]{orderOffer, energyUnit});
        double totalEnergy = orderOffer.getTotalEnergy();
        double secondaryEnergy = 0.0d;
        String secondaryEnergyUnit = Configuration.getSharedInstance().getStringForKey("interface.nutritionalInfo.secondaryEnergyUnit");
        if (!TextUtils.isEmpty(secondaryEnergyUnit)) {
            secondaryEnergy = orderOffer.getTotalSecondaryEnergy();
        }
        return getEnergyText(totalEnergy, secondaryEnergy, energyUnit, secondaryEnergyUnit);
    }

    public static String getEnergyTextForOrder(Order order, String energyUnit) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AppUtils", "getEnergyTextForOrder", new Object[]{order, energyUnit});
        double totalEnergy = order.getTotalEnergy();
        double secondaryEnergy = 0.0d;
        String secondaryEnergyUnit = Configuration.getSharedInstance().getStringForKey("interface.nutritionalInfo.secondaryEnergyUnit");
        if (!TextUtils.isEmpty(secondaryEnergyUnit)) {
            secondaryEnergy = order.getTotalSecondaryEnergy();
        }
        return getEnergyText(totalEnergy, secondaryEnergy, energyUnit, secondaryEnergyUnit);
    }

    private static String getEnergyText(double totalEnergy, double secondaryEnergy, String energyUnit, String secondaryEnergyUnit) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AppUtils", "getEnergyText", new Object[]{new Double(totalEnergy), new Double(secondaryEnergy), energyUnit, secondaryEnergyUnit});
        if (TextUtils.isEmpty(secondaryEnergyUnit)) {
            return Math.round(totalEnergy) + " " + energyUnit;
        }
        long energyKCAL;
        long energyKJ = Math.round(totalEnergy);
        if (secondaryEnergy == 0.0d) {
            energyKCAL = Math.round(((double) energyKJ) / RATE_KJ_KCAL.doubleValue());
        } else {
            energyKCAL = Math.round(secondaryEnergy);
        }
        return energyKJ + " " + Configuration.getSharedInstance().getStringForKey("interface.nutritionalInfo.energyUnit") + " / " + energyKCAL + " " + secondaryEnergyUnit;
    }

    public static String getDayPartInitialTime(int dayPart, Store store) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AppUtils", "getDayPartInitialTime", new Object[]{new Integer(dayPart), store});
        if (store == null) {
            return "";
        }
        String breakfastTime = "5:00";
        String lunchTime = "10:30";
        String dinnerTime = "23:00";
        MenuTypeCalendarItem menuTypeCalendarItem = store.getMenuTypeCalendarItem(dayPart, OrderingManager.getInstance().getCurrentOrder().isDelivery());
        if (menuTypeCalendarItem != null) {
            return menuTypeCalendarItem.getFromTime();
        }
        if (dayPart == 0) {
            return breakfastTime;
        }
        if (dayPart == 1) {
            return lunchTime;
        }
        return dinnerTime;
    }

    public static MenuType getCurrentMenuType() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AppUtils", "getCurrentMenuType", null);
        Store store = OrderManager.getInstance().getCurrentStore();
        MenuType currentDayPart = null;
        if (store != null && ModuleManager.getSharedInstance().isOrderingAvailable()) {
            OrderingModule orderingModule = (OrderingModule) ModuleManager.getModule("ordering");
            int currentDayPartId = store.getCurrentMenuTypeID(OrderManager.getInstance().getCurrentOrder().isDelivery());
            List<MenuType> menuTypes = orderingModule.getMenuTypes();
            if (menuTypes != null) {
                for (MenuType menuType : menuTypes) {
                    if (menuType.getID() == currentDayPartId) {
                        currentDayPart = menuType;
                    }
                }
            }
        }
        return currentDayPart;
    }

    public static void showLargeOrderAlert(URLNavigationActivity activity) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AppUtils", "showLargeOrderAlert", new Object[]{activity});
        if (Configuration.getSharedInstance().getBooleanForKey("interface.confirmationNeededOrders.callCenterCallsUser")) {
            activity.startActivity(AlertActivity.class, "large_order_alert");
        } else {
            activity.startActivity(AlertActivity.class, "large_order_call_alert");
        }
    }

    public static boolean hideTermsAndConditionsView() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AppUtils", "hideTermsAndConditionsView", null);
        return Configuration.getSharedInstance().getBooleanForKey("interface.register.hideTermsAndConditionsScreen");
    }

    public static boolean hideNutritionOnOrderingPages() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AppUtils", "hideNutritionOnOrderingPages", null);
        return !ModuleManager.isModuleEnabled(NutritionModule.NAME).booleanValue() || Configuration.getSharedInstance().getBooleanForKey("interface.nutritionalInfo.hideOnOrderingPages");
    }

    public static boolean hideNutritionIconOnOrderingPages() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AppUtils", "hideNutritionIconOnOrderingPages", null);
        return !ModuleManager.isModuleEnabled(NutritionModule.NAME).booleanValue() || Configuration.getSharedInstance().getBooleanForKey("interface.ordering.hideNutritionInfo");
    }

    public static boolean isTrue(Boolean unsafeBoolean) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.AppUtils", "isTrue", new Object[]{unsafeBoolean});
        return Boolean.TRUE.equals(unsafeBoolean);
    }
}
