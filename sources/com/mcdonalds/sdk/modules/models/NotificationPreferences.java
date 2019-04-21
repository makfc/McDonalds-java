package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.utils.MapUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class NotificationPreferences extends AppModel implements Parcelable {
    public static final Creator<NotificationPreferences> CREATOR = new C40561();
    public static final String KEY_APP_ENABLED = "AppNotificationPreferences_Enabled";
    public static final String KEY_APP_EVERYDAY_OFFERS = "AppNotificationPreferences_EverydayOffers";
    public static final String KEY_APP_LIMITED_TIME_OFFERS = "AppNotificationPreferences_LimitedTimeOffers";
    public static final String KEY_APP_OFFER_EXPIRATION_OPTION = "AppNotificationPreferences_OfferExpirationOption";
    public static final String KEY_APP_PREFIX = "AppNotificationPreferences_";
    public static final String KEY_APP_PUNCHCARD_OFFERS = "AppNotificationPreferences_PunchcardOffers";
    public static final String KEY_APP_YOUR_OFFERS = "AppNotificationPreferences_YourOffers";
    public static final String KEY_EMAIL_ENABLED = "EmailNotificationPreferences_Enabled";
    public static final String KEY_EMAIL_EVERYDAY_OFFERS = "EmailNotificationPreferences_EverydayOffers";
    public static final String KEY_EMAIL_LIMITED_TIME_OFFERS = "EmailNotificationPreferences_LimitedTimeOffers";
    public static final String KEY_EMAIL_OFFER_EXPIRATION_OPTION = "EmailNotificationPreferences_OfferExpirationOption";
    public static final String KEY_EMAIL_PREFIX = "EmailNotificationPreferences_";
    public static final String KEY_EMAIL_PUNCHCARD_OFFERS = "EmailNotificationPreferences_PunchcardOffers";
    public static final String KEY_EMAIL_YOUR_OFFERS = "EmailNotificationPreferences_YourOffers";
    public static final String KEY_FOOD_PREFERENCES_BREAKFAST = "FoodPreferenceBreakfast";
    public static final String KEY_FOOD_PREFERENCES_CHICKEN = "FoodPreferenceChicken";
    public static final String KEY_FOOD_PREFERENCES_DRINK = "FoodPreferenceDrink";
    public static final String KEY_FOOD_PREFERENCES_FRY = "FoodPreferenceFry";
    public static final String KEY_FOOD_PREFERENCES_HAPPY_MEAL = "FoodPreferenceHappymeal";
    public static final String KEY_FOOD_PREFERENCES_SALAD = "FoodPreferenceSalad";
    public static final String KEY_FOOD_PREFERENCES_SANDWICH = "FoodPreferenceSandwich";
    public static final String KEY_FOOD_PREFERENCES_WRAP = "FoodPreferenceWrap";
    public static final String KEY_FOOD_PREFERENCE_PREFIX = "FoodPreference";
    public static final String KEY_NOTIFICATION_PREFERENCES = "modules.customer.register.notificationPreferences";
    public static final String PREFERENCE_ITEM_KEY_ID = "id";
    public static final String PREFERENCE_ITEM_KEY_LEGACY_ID = "legacyId";
    public static final String PREFERENCE_ITEM_KEY_STATUS = "status";
    public static final String PREFERENCE_ITEM_KEY_TYPE = "type";
    private int mAppNotificationPreferencesOfferExpirationOption;
    private int mEmailNotificationPreferencesOfferExpirationOption;
    private Map<String, Boolean> mPreferencesMap;

    /* renamed from: com.mcdonalds.sdk.modules.models.NotificationPreferences$1 */
    static class C40561 implements Creator<NotificationPreferences> {
        C40561() {
        }

        public NotificationPreferences createFromParcel(Parcel in) {
            return new NotificationPreferences(in);
        }

        public NotificationPreferences[] newArray(int size) {
            return new NotificationPreferences[size];
        }
    }

    public void updateWithConfig() {
        Map<String, Object> configMap = (Map) Configuration.getSharedInstance().getValueForKey("modules.customer.register.notificationPreferences");
        if (configMap != null) {
            for (Entry<String, Object> entry : configMap.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    set((String) entry.getKey(), (Boolean) ((Map) entry.getValue()).get("status"));
                } else if (entry.getValue() instanceof Boolean) {
                    set((String) entry.getKey(), (Boolean) entry.getValue());
                }
            }
            if (MapUtils.isEmpty(this.mPreferencesMap)) {
                this.mPreferencesMap = new HashMap();
            }
        }
    }

    public static NotificationPreferences fromConfig() {
        NotificationPreferences notificationPreferences = new NotificationPreferences();
        notificationPreferences.updateWithConfig();
        return notificationPreferences;
    }

    public Boolean getAppNotificationPreferencesEnabled() {
        return get(KEY_APP_ENABLED);
    }

    public void setAppNotificationPreferencesEnabled(Boolean appNotificationPreferencesEnabled) {
        set(KEY_APP_ENABLED, appNotificationPreferencesEnabled);
    }

    public boolean getAppNotificationPreferencesEverydayOffers() {
        return getBoolean(KEY_APP_EVERYDAY_OFFERS);
    }

    public void setAppNotificationPreferencesEverydayOffers(Boolean appNotificationPreferencesEverydayOffers) {
        set(KEY_APP_EVERYDAY_OFFERS, appNotificationPreferencesEverydayOffers);
    }

    public boolean getAppNotificationPreferencesLimitedTimeOffers() {
        return getBoolean(KEY_APP_LIMITED_TIME_OFFERS);
    }

    public void setAppNotificationPreferencesLimitedTimeOffers(Boolean appNotificationPreferencesLimitedTimeOffers) {
        set(KEY_APP_LIMITED_TIME_OFFERS, appNotificationPreferencesLimitedTimeOffers);
    }

    public int getAppNotificationPreferencesOfferExpirationOption() {
        return this.mAppNotificationPreferencesOfferExpirationOption;
    }

    public void setAppNotificationPreferencesOfferExpirationOption(int appNotificationPreferencesOfferExpirationOption) {
        this.mAppNotificationPreferencesOfferExpirationOption = appNotificationPreferencesOfferExpirationOption;
    }

    public boolean getAppNotificationPreferencesPunchcardOffers() {
        return getBoolean(KEY_APP_PUNCHCARD_OFFERS);
    }

    public void setAppNotificationPreferencesPunchcardOffers(Boolean appNotificationPreferencesPunchcardOffers) {
        set(KEY_APP_PUNCHCARD_OFFERS, appNotificationPreferencesPunchcardOffers);
    }

    public boolean getAppNotificationPreferencesYourOffers() {
        return getBoolean(KEY_APP_YOUR_OFFERS);
    }

    public void setAppNotificationPreferencesYourOffers(Boolean appNotificationPreferencesYourOffers) {
        set(KEY_APP_YOUR_OFFERS, appNotificationPreferencesYourOffers);
    }

    public Boolean getEmailNotificationPreferencesEnabled() {
        return get(KEY_EMAIL_ENABLED);
    }

    public void setEmailNotificationPreferencesEnabled(Boolean emailNotificationPreferencesEnabled) {
        set(KEY_EMAIL_ENABLED, emailNotificationPreferencesEnabled);
    }

    public boolean getEmailNotificationPreferencesEverydayOffers() {
        return getBoolean(KEY_EMAIL_EVERYDAY_OFFERS);
    }

    public void setEmailNotificationPreferencesEverydayOffers(Boolean emailNotificationPreferencesEverydayOffers) {
        set(KEY_EMAIL_EVERYDAY_OFFERS, emailNotificationPreferencesEverydayOffers);
    }

    public boolean getEmailNotificationPreferencesLimitedTimeOffers() {
        return getBoolean(KEY_EMAIL_LIMITED_TIME_OFFERS);
    }

    public void setEmailNotificationPreferencesLimitedTimeOffers(Boolean emailNotificationPreferencesLimitedTimeOffers) {
        set(KEY_EMAIL_LIMITED_TIME_OFFERS, emailNotificationPreferencesLimitedTimeOffers);
    }

    public int getEmailNotificationPreferencesOfferExpirationOption() {
        return this.mEmailNotificationPreferencesOfferExpirationOption;
    }

    public void setEmailNotificationPreferencesOfferExpirationOption(int emailNotificationPreferencesOfferExpirationOption) {
        this.mEmailNotificationPreferencesOfferExpirationOption = emailNotificationPreferencesOfferExpirationOption;
    }

    public boolean getEmailNotificationPreferencesPunchcardOffers() {
        return getBoolean(KEY_EMAIL_PUNCHCARD_OFFERS);
    }

    public void setEmailNotificationPreferencesPunchcardOffers(Boolean emailNotificationPreferencesPunchcardOffers) {
        set(KEY_EMAIL_PUNCHCARD_OFFERS, emailNotificationPreferencesPunchcardOffers);
    }

    public boolean getEmailNotificationPreferencesYourOffers() {
        return getBoolean(KEY_EMAIL_YOUR_OFFERS);
    }

    public void setEmailNotificationPreferencesYourOffers(Boolean emailNotificationPreferencesYourOffers) {
        set(KEY_EMAIL_YOUR_OFFERS, emailNotificationPreferencesYourOffers);
    }

    public Boolean getFoodPreferenceBreakfast() {
        return get("FoodPreferenceBreakfast");
    }

    public void setFoodPreferenceBreakfast(Boolean foodPreferenceBreakfast) {
        set("FoodPreferenceBreakfast", foodPreferenceBreakfast);
    }

    public Boolean getFoodPreferenceSandwich() {
        return get("FoodPreferenceSandwich");
    }

    public void setFoodPreferenceSandwich(Boolean foodPreferenceSandwich) {
        set("FoodPreferenceSandwich", foodPreferenceSandwich);
    }

    public Boolean getFoodPreferenceChicken() {
        return get("FoodPreferenceChicken");
    }

    public void setFoodPreferenceChicken(Boolean foodPreferenceChicken) {
        set("FoodPreferenceChicken", foodPreferenceChicken);
    }

    public Boolean getFoodPreferenceDrink() {
        return get("FoodPreferenceDrink");
    }

    public void setFoodPreferenceDrink(Boolean foodPreferenceDrink) {
        set("FoodPreferenceDrink", foodPreferenceDrink);
    }

    public Boolean getFoodPreferenceFry() {
        return get("FoodPreferenceFry");
    }

    public void setFoodPreferenceFry(Boolean foodPreferenceFry) {
        set("FoodPreferenceFry", foodPreferenceFry);
    }

    public Boolean getFoodPreferenceHappymeal() {
        return get("FoodPreferenceHappymeal");
    }

    public void setFoodPreferenceHappymeal(Boolean foodPreferenceHappymeal) {
        set("FoodPreferenceHappymeal", foodPreferenceHappymeal);
    }

    public Boolean getFoodPreferenceSalad() {
        return get("FoodPreferenceSalad");
    }

    public void setFoodPreferenceSalad(Boolean foodPreferenceSalad) {
        set("FoodPreferenceSalad", foodPreferenceSalad);
    }

    public Boolean getFoodPreferenceWrap() {
        return get("FoodPreferenceWrap");
    }

    public void setFoodPreferenceWrap(Boolean foodPreferenceWrap) {
        set("FoodPreferenceWrap", foodPreferenceWrap);
    }

    public boolean getBoolean(String key) {
        Boolean value = get(key);
        return value != null && value.booleanValue();
    }

    public Boolean get(String key) {
        return (Boolean) MapUtils.get(this.mPreferencesMap, key);
    }

    public void set(String key, Boolean value) {
        if (this.mPreferencesMap == null) {
            this.mPreferencesMap = new HashMap();
        }
        this.mPreferencesMap.put(key, value);
    }

    public Map<String, Boolean> getPreferencesMap() {
        return this.mPreferencesMap;
    }

    protected NotificationPreferences(Parcel in) {
        this.mAppNotificationPreferencesOfferExpirationOption = in.readInt();
        this.mEmailNotificationPreferencesOfferExpirationOption = in.readInt();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mAppNotificationPreferencesOfferExpirationOption);
        dest.writeInt(this.mEmailNotificationPreferencesOfferExpirationOption);
    }

    public int describeContents() {
        return 0;
    }
}
