package com.mcdonalds.sdk.connectors.middleware.model;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.NotificationPreferences;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class DCSPreference {
    public static final String DEFAULT_SOURCE_ID = "MOT";
    public static final String DESC_EVERYDAY_OFFERS = "EverydayOffers";
    public static final String DESC_FOOD_PREFERENCE_BREAKFAST = "FoodPreferenceBreakfast";
    public static final String DESC_FOOD_PREFERENCE_CHICKEN = "FoodPreferenceChicken";
    public static final String DESC_FOOD_PREFERENCE_DRINK = "FoodPreferenceDrink";
    public static final String DESC_FOOD_PREFERENCE_FRY = "FoodPreferenceFry";
    public static final String DESC_FOOD_PREFERENCE_HAPPYMEAL = "FoodPreferenceHappymeal";
    public static final String DESC_FOOD_PREFERENCE_SALAD = "FoodPreferenceSalad";
    public static final String DESC_FOOD_PREFERENCE_SANDWICH = "FoodPreferenceSandwich";
    public static final String DESC_FOOD_PREFERENCE_WRAP = "FoodPreferenceWrap";
    public static final String DESC_LIMITED_TIME_OFFERS = "LimitedTimeOffers";
    public static final String DESC_OFFER_EXPIRATION_OPTION = "OfferExpirationOption";
    public static final String DESC_PREFERRED_LANGUAGE = "PreferredLanguage";
    public static final String DESC_PREFERRED_NOTIFICATION = "PreferredNotification";
    public static final String DESC_PREFERRED_OFFER_CATEGORIES = "PreferredOfferCategory";
    public static final String DESC_PUNCH_CARD_OFFERS = "PunchcardOffers";
    public static final String DESC_RECEIVE_PROMOTIONS = "DoesAcceptPromotion";
    public static final String DESC_YOUR_OFFERS = "YourOffers";
    public static final String ECP_LEGACY_STATUS_DISABLED = "False";
    public static final String ECP_LEGACY_STATUS_ENABLED = "True";
    public static final String ID_EVERYDAY_OFFERS = "9";
    public static final String ID_FOOD_PREFERENCE_BREAKFAST = "12";
    public static final String ID_FOOD_PREFERENCE_CHICKEN = "14";
    public static final String ID_FOOD_PREFERENCE_DRINK = "15";
    public static final String ID_FOOD_PREFERENCE_FRY = "16";
    public static final String ID_FOOD_PREFERENCE_HAPPYMEAL = "17";
    public static final String ID_FOOD_PREFERENCE_SALAD = "19";
    public static final String ID_FOOD_PREFERENCE_SANDWICH = "13";
    public static final String ID_FOOD_PREFERENCE_WRAP = "21";
    public static final String ID_LIMITED_TIME_OFFERS = "7";
    private static final Map<String, String> ID_MAP = new C25021();
    public static final String ID_OFFER_EXPIRATION_OPTION = "10";
    public static final String ID_PREFERRED_LANGUAGE = "1";
    public static final String ID_PREFERRED_NOTIFICATION = "3";
    public static final String ID_PREFERRED_OFFER_CATEGORIES = "18";
    public static final String ID_PUNCH_CARD_OFFERS = "8";
    public static final String ID_RECEIVE_PROMOTIONS = "2";
    public static final String ID_YOUR_OFFERS = "6";
    public static final String LEGACY_ID_EVERYDAY_OFFERS = "9";
    public static final String LEGACY_ID_LIMITED_TIME_OFFERS = "7";
    private static final Map<String, String> LEGACY_ID_MAP = new C25032();
    public static final String LEGACY_ID_OFFER_EXPIRATION_OPTION = "10";
    public static final String LEGACY_ID_PREFERRED_LANGUAGE = "1";
    public static final String LEGACY_ID_PREFERRED_NOTIFICATION = "3";
    public static final String LEGACY_ID_PREFERRED_OFFER_CATEGORIES = "18";
    public static final String LEGACY_ID_PUNCH_CARD_OFFERS = "8";
    public static final String LEGACY_ID_RECEIVE_PROMOTIONS = "2";
    public static final String LEGACY_ID_YOUR_OFFERS = "6";
    public static final String PREFERRED_NOTIFICATION_BY_EMAIL = "ByEmail";
    public static final int PREFERRED_NOTIFICATION_BY_EMAIL_INT = 1;
    public static final String PREFERRED_NOTIFICATION_BY_SMS = "BySMS";
    public static final int PREFERRED_NOTIFICATION_BY_SMS_INT = 2;
    public static final String TYPE_ECP_LEGACY = "ecpLegacy";
    public static final String TYPE_FOOD_PREFERENCE = "FoodPreference";
    private static final Map<String, String> TYPE_MAP = new C25043();
    @SerializedName("details")
    public DCSPreferenceDetails details;
    @SerializedName("isActive")
    public String isActive;
    @SerializedName("preferenceDesc")
    public String preferenceDesc;
    @SerializedName("preferenceId")
    public String preferenceId;
    @SerializedName("sourceId")
    public String sourceId;
    @SerializedName("type")
    public String type;

    public static class DCSPreferenceDetails {
        @SerializedName("enabled")
        public String enabled;

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            DCSPreferenceDetails details = (DCSPreferenceDetails) o;
            if (this.enabled != null) {
                return this.enabled.equals(details.enabled);
            }
            if (details.enabled != null) {
                return false;
            }
            return true;
        }
    }

    public static class EcpLegacyDCSPreferenceDetails extends DCSPreferenceDetails {
        @SerializedName("Email")
        public String email;
        @SerializedName("legacyId")
        public String legacyId;
        @SerializedName("MobileApp")
        public String mobileApp;

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            EcpLegacyDCSPreferenceDetails details = (EcpLegacyDCSPreferenceDetails) o;
            if (super.equals(details) && this.legacyId.equals(details.legacyId) && this.mobileApp.equals(details.mobileApp) && this.email.equals(details.email)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (((this.legacyId.hashCode() * 31) + this.mobileApp.hashCode()) * 31) + this.email.hashCode();
        }
    }

    public static class PreferredOfferCategoryDCSPreferenceDetails extends DCSPreferenceDetails {
        @SerializedName("Email")
        public List<String> email;
        @SerializedName("legacyId")
        public final String legacyId = "18";
        @SerializedName("MobileApp")
        public List<String> mobileApp;

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            PreferredOfferCategoryDCSPreferenceDetails details = (PreferredOfferCategoryDCSPreferenceDetails) o;
            if (super.equals(details)) {
                details.getClass();
                if ("18".equals("18") && this.mobileApp.equals(details.mobileApp) && this.email.equals(details.email)) {
                    return true;
                }
            }
            return false;
        }
    }

    /* renamed from: com.mcdonalds.sdk.connectors.middleware.model.DCSPreference$1 */
    static class C25021 extends HashMap<String, String> {
        C25021() {
            put(DCSPreference.DESC_YOUR_OFFERS, "6");
            put(DCSPreference.DESC_LIMITED_TIME_OFFERS, "7");
            put(DCSPreference.DESC_PUNCH_CARD_OFFERS, "8");
            put(DCSPreference.DESC_EVERYDAY_OFFERS, "9");
            put("FoodPreferenceBreakfast", DCSPreference.ID_FOOD_PREFERENCE_BREAKFAST);
            put("FoodPreferenceSandwich", DCSPreference.ID_FOOD_PREFERENCE_SANDWICH);
            put("FoodPreferenceChicken", DCSPreference.ID_FOOD_PREFERENCE_CHICKEN);
            put("FoodPreferenceDrink", DCSPreference.ID_FOOD_PREFERENCE_DRINK);
            put("FoodPreferenceFry", DCSPreference.ID_FOOD_PREFERENCE_FRY);
            put("FoodPreferenceHappymeal", DCSPreference.ID_FOOD_PREFERENCE_HAPPYMEAL);
            put("FoodPreferenceSalad", DCSPreference.ID_FOOD_PREFERENCE_SALAD);
            put("FoodPreferenceWrap", DCSPreference.ID_FOOD_PREFERENCE_WRAP);
        }
    }

    /* renamed from: com.mcdonalds.sdk.connectors.middleware.model.DCSPreference$2 */
    static class C25032 extends HashMap<String, String> {
        C25032() {
            put(DCSPreference.DESC_YOUR_OFFERS, "6");
            put(DCSPreference.DESC_LIMITED_TIME_OFFERS, "7");
            put(DCSPreference.DESC_PUNCH_CARD_OFFERS, "8");
            put(DCSPreference.DESC_EVERYDAY_OFFERS, "9");
        }
    }

    /* renamed from: com.mcdonalds.sdk.connectors.middleware.model.DCSPreference$3 */
    static class C25043 extends HashMap<String, String> {
        C25043() {
            put(DCSPreference.DESC_YOUR_OFFERS, DCSPreference.TYPE_ECP_LEGACY);
            put(DCSPreference.DESC_LIMITED_TIME_OFFERS, DCSPreference.TYPE_ECP_LEGACY);
            put(DCSPreference.DESC_PUNCH_CARD_OFFERS, DCSPreference.TYPE_ECP_LEGACY);
            put(DCSPreference.DESC_EVERYDAY_OFFERS, DCSPreference.TYPE_ECP_LEGACY);
            put("FoodPreferenceBreakfast", "FoodPreference");
            put("FoodPreferenceSandwich", "FoodPreference");
            put("FoodPreferenceChicken", "FoodPreference");
            put("FoodPreferenceDrink", "FoodPreference");
            put("FoodPreferenceFry", "FoodPreference");
            put("FoodPreferenceHappymeal", "FoodPreference");
            put("FoodPreferenceSalad", "FoodPreference");
            put("FoodPreferenceWrap", "FoodPreference");
        }
    }

    public static String getECPLegacyStatus(Boolean value) {
        if (value == null) {
            return null;
        }
        return value.booleanValue() ? ECP_LEGACY_STATUS_ENABLED : ECP_LEGACY_STATUS_DISABLED;
    }

    public static Boolean booleanFromLegacyStatus(String value) {
        boolean z = value != null && value.equals(ECP_LEGACY_STATUS_ENABLED);
        return Boolean.valueOf(z);
    }

    public static String preferredNotificationFromInt(int value) {
        switch (value) {
            case 1:
                return PREFERRED_NOTIFICATION_BY_EMAIL;
            case 2:
                return PREFERRED_NOTIFICATION_BY_SMS;
            default:
                return null;
        }
    }

    public static String preferredNotificationFromInteger(Integer value) {
        if (value == null) {
            return null;
        }
        switch (value.intValue()) {
            case 1:
                return PREFERRED_NOTIFICATION_BY_EMAIL;
            case 2:
                return PREFERRED_NOTIFICATION_BY_SMS;
            default:
                return null;
        }
    }

    public static Integer integerFromPreferredNotification(String value) {
        int i = -1;
        switch (value.hashCode()) {
            case 64639330:
                if (value.equals(PREFERRED_NOTIFICATION_BY_SMS)) {
                    i = 1;
                    break;
                }
                break;
            case 1976894821:
                if (value.equals(PREFERRED_NOTIFICATION_BY_EMAIL)) {
                    i = 0;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                return Integer.valueOf(1);
            case 1:
                return Integer.valueOf(2);
            default:
                return null;
        }
    }

    public static DCSPreference createLegacyPreference(String isActive, String preferenceId, String preferenceDescription, String legacyId, Boolean emailValue, Boolean mobileAppValue) {
        return createLegacyPreference(isActive, preferenceId, preferenceDescription, legacyId, getECPLegacyStatus(emailValue), getECPLegacyStatus(mobileAppValue));
    }

    public static DCSPreference createLegacyPreference(String isActive, String preferenceId, String preferenceDescription, String legacyId, String emailValue, String mobileAppValue) {
        if (emailValue == null && mobileAppValue == null) {
            return null;
        }
        EcpLegacyDCSPreferenceDetails details = new EcpLegacyDCSPreferenceDetails();
        details.legacyId = legacyId;
        details.email = emailValue;
        details.mobileApp = mobileAppValue;
        return createLegacyPreference(isActive, preferenceId, preferenceDescription, details);
    }

    public static DCSPreference createLegacyPreference(String isActive, String preferenceId, String preferenceDescription, DCSPreferenceDetails details) {
        return createPreference(isActive, preferenceId, preferenceDescription, TYPE_ECP_LEGACY, details);
    }

    public static DCSPreference createFoodPreference(String isActive, String preferenceId, String preferenceDescription, String enabled) {
        if (enabled == null) {
            return null;
        }
        DCSPreferenceDetails details = new DCSPreferenceDetails();
        details.enabled = enabled;
        return createPreference(isActive, preferenceId, preferenceDescription, "FoodPreference", details);
    }

    public static DCSPreference createPreference(String isActive, String preferenceId, String preferenceDescription, String preferenceType, DCSPreferenceDetails details) {
        DCSPreference dcsPreference = new DCSPreference();
        dcsPreference.isActive = isActive;
        dcsPreference.preferenceId = preferenceId;
        dcsPreference.preferenceDesc = preferenceDescription;
        dcsPreference.type = preferenceType;
        dcsPreference.sourceId = "MOT";
        dcsPreference.details = details;
        return dcsPreference;
    }

    static List<DCSPreference> createPreferenceList(CustomerProfile customerProfile) {
        DCSPreferenceDetails details;
        List<DCSPreference> dcsPreferences = new ArrayList();
        if (customerProfile.getPreferredLocale() != null) {
            Locale locale = customerProfile.getPreferredLocale();
            String langString = LocalDataManager.getSharedInstance().getDeviceLanguage();
            if (!TextUtils.isEmpty(locale.getCountry())) {
                langString = langString + "-" + locale.getCountry();
            }
            DCSPreference dcsPreference = createLegacyPreference(DCSProfile.INDICATOR_TRUE, "1", DESC_PREFERRED_LANGUAGE, "1", langString, langString);
            if (dcsPreference != null) {
                dcsPreferences.add(dcsPreference);
            }
        }
        Boolean receivePromotions = customerProfile.getReceivePromotions();
        DCSPreference dcsPreferenceReceivePromotions = createLegacyPreference(DCSProfile.indicatorForBoolean(receivePromotions), "2", DESC_RECEIVE_PROMOTIONS, "2", receivePromotions, receivePromotions);
        if (dcsPreferenceReceivePromotions != null) {
            dcsPreferences.add(dcsPreferenceReceivePromotions);
        }
        String option = preferredNotificationFromInteger(customerProfile.getPreferredNotification());
        DCSPreference dcsPreferencePreferredNotification = createLegacyPreference(DCSProfile.indicatorForBoolean(receivePromotions), "3", DESC_PREFERRED_NOTIFICATION, "3", option, option);
        if (dcsPreferencePreferredNotification != null) {
            dcsPreferences.add(dcsPreferencePreferredNotification);
        }
        List<Integer> preferredOfferCategories = customerProfile.getPreferredOfferCategories();
        if (preferredOfferCategories != null) {
            List<String> preferredOfferCategoriesStrings = new ArrayList();
            for (Integer preferredOfferCategory : preferredOfferCategories) {
                preferredOfferCategoriesStrings.add(String.valueOf(preferredOfferCategory));
            }
            details = new PreferredOfferCategoryDCSPreferenceDetails();
            details.email = preferredOfferCategoriesStrings;
            details.mobileApp = preferredOfferCategoriesStrings;
            DCSPreference dcsPreferenceYourOffers = createLegacyPreference(DCSProfile.indicatorForBoolean(receivePromotions), "18", DESC_PREFERRED_OFFER_CATEGORIES, details);
            if (dcsPreferenceYourOffers != null) {
                dcsPreferences.add(dcsPreferenceYourOffers);
            }
        }
        NotificationPreferences notificationPreferences = customerProfile.getNotificationPreferences();
        if (notificationPreferences != null) {
            Map<String, Boolean> preferenceMap = notificationPreferences.getPreferencesMap();
            if (preferenceMap != null) {
                for (Entry<String, Boolean> entry : preferenceMap.entrySet()) {
                    String key = (String) entry.getKey();
                    if (!key.equals(NotificationPreferences.KEY_APP_ENABLED)) {
                        if (!key.equals(NotificationPreferences.KEY_EMAIL_ENABLED)) {
                            Boolean value = (Boolean) entry.getValue();
                            if (key.contains(NotificationPreferences.KEY_APP_PREFIX)) {
                                addOrUpdateLegacyPreference(dcsPreferences, key, NotificationPreferences.KEY_APP_PREFIX, value);
                            } else {
                                if (key.contains(NotificationPreferences.KEY_EMAIL_PREFIX)) {
                                    addOrUpdateLegacyPreference(dcsPreferences, key, NotificationPreferences.KEY_EMAIL_PREFIX, value);
                                } else {
                                    details = new DCSPreferenceDetails();
                                    details.enabled = DCSProfile.indicatorForBoolean(value);
                                    dcsPreferences.add(createPreference(DCSProfile.INDICATOR_TRUE, idForKey(key), key, typeForKey(key), details));
                                }
                            }
                        }
                    }
                }
            }
        }
        return dcsPreferences;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003d  */
    private static void addOrUpdateLegacyPreference(java.util.List<com.mcdonalds.sdk.connectors.middleware.model.DCSPreference> r7, java.lang.String r8, java.lang.String r9, java.lang.Boolean r10) {
        /*
        r4 = "";
        r3 = r8.replace(r9, r4);
        if (r10 != 0) goto L_0x0009;
    L_0x0008:
        return;
    L_0x0009:
        r2 = 0;
        r4 = r7.iterator();
    L_0x000e:
        r5 = r4.hasNext();
        if (r5 == 0) goto L_0x003b;
    L_0x0014:
        r0 = r4.next();
        r0 = (com.mcdonalds.sdk.connectors.middleware.model.DCSPreference) r0;
        r5 = r0.details;
        r5 = r5 instanceof com.mcdonalds.sdk.connectors.middleware.model.DCSPreference.EcpLegacyDCSPreferenceDetails;
        if (r5 == 0) goto L_0x000e;
    L_0x0020:
        r5 = r0.preferenceDesc;
        r5 = r5.equals(r3);
        if (r5 == 0) goto L_0x000e;
    L_0x0028:
        r1 = r0.details;
        r1 = (com.mcdonalds.sdk.connectors.middleware.model.DCSPreference.EcpLegacyDCSPreferenceDetails) r1;
        r4 = "AppNotificationPreferences_";
        r4 = r9.equals(r4);
        if (r4 == 0) goto L_0x006a;
    L_0x0034:
        r4 = getECPLegacyStatus(r10);
        r1.mobileApp = r4;
    L_0x003a:
        r2 = 1;
    L_0x003b:
        if (r2 != 0) goto L_0x0008;
    L_0x003d:
        r1 = new com.mcdonalds.sdk.connectors.middleware.model.DCSPreference$EcpLegacyDCSPreferenceDetails;
        r1.<init>();
        r4 = legacyIdForKey(r3);
        r1.legacyId = r4;
        r4 = "AppNotificationPreferences_";
        r4 = r9.equals(r4);
        if (r4 == 0) goto L_0x0079;
    L_0x0050:
        r4 = getECPLegacyStatus(r10);
        r1.mobileApp = r4;
    L_0x0056:
        r4 = "Y";
        r5 = idForKey(r3);
        r6 = typeForKey(r3);
        r0 = createPreference(r4, r5, r3, r6, r1);
        if (r0 == 0) goto L_0x0008;
    L_0x0066:
        r7.add(r0);
        goto L_0x0008;
    L_0x006a:
        r4 = "EmailNotificationPreferences_";
        r4 = r9.equals(r4);
        if (r4 == 0) goto L_0x003a;
    L_0x0072:
        r4 = getECPLegacyStatus(r10);
        r1.email = r4;
        goto L_0x003a;
    L_0x0079:
        r4 = "EmailNotificationPreferences_";
        r4 = r9.equals(r4);
        if (r4 == 0) goto L_0x0056;
    L_0x0081:
        r4 = getECPLegacyStatus(r10);
        r1.email = r4;
        goto L_0x0056;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mcdonalds.sdk.connectors.middleware.model.DCSPreference.addOrUpdateLegacyPreference(java.util.List, java.lang.String, java.lang.String, java.lang.Boolean):void");
    }

    private static String idForKey(String key) {
        String value = getStringFromPreferenceMapItem(key, "id");
        return value != null ? value : (String) ID_MAP.get(key);
    }

    private static String legacyIdForKey(String key) {
        String value = getStringFromPreferenceMapItem(key, NotificationPreferences.PREFERENCE_ITEM_KEY_LEGACY_ID);
        return value != null ? value : (String) LEGACY_ID_MAP.get(key);
    }

    private static String typeForKey(String key) {
        String value = getStringFromPreferenceMapItem(key, "type");
        return value != null ? value : (String) TYPE_MAP.get(key);
    }

    @Nullable
    private static String getStringFromPreferenceMapItem(String key, String itemKey) {
        Map<String, Object> configMap = (Map) Configuration.getSharedInstance().getValueForKey("modules.customer.register.notificationPreferences");
        if (configMap == null) {
            return null;
        }
        Map<String, Object> entry = configMap.get(key);
        if (entry == null || !(entry instanceof Map)) {
            return null;
        }
        Object value = entry.get(itemKey);
        if (value != null) {
            return String.valueOf(value);
        }
        return null;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DCSPreference that = (DCSPreference) o;
        if (this.preferenceId == null ? that.preferenceId != null : !this.preferenceId.equals(that.preferenceId)) {
            if (this.isActive == null ? that.details != null : !this.isActive.equals(that.isActive)) {
                if (this.details != null) {
                    if (this.details.equals(that.details)) {
                        return true;
                    }
                } else if (that.details == null) {
                    return true;
                }
            }
        }
        return false;
    }
}
