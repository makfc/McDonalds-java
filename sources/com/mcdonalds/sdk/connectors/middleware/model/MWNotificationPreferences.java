package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.NotificationPreferences;

public class MWNotificationPreferences {
    @SerializedName("AppNotificationPreferences_Enabled")
    public Boolean appNotificationPreferences_Enabled;
    @SerializedName("AppNotificationPreferences_EverydayOffers")
    public Boolean appNotificationPreferences_EverydayOffers;
    @SerializedName("AppNotificationPreferences_LimitedTimeOffers")
    public Boolean appNotificationPreferences_LimitedTimeOffers;
    @SerializedName("AppNotificationPreferences_OfferExpirationOption")
    public int appNotificationPreferences_OfferExpirationOption;
    @SerializedName("AppNotificationPreferences_PunchcardOffers")
    public Boolean appNotificationPreferences_PunchcardOffers;
    @SerializedName("AppNotificationPreferences_YourOffers")
    public Boolean appNotificationPreferences_YourOffers;
    @SerializedName("EmailNotificationPreferences_Enabled")
    public Boolean emailNotificationPreferences_Enabled;
    @SerializedName("EmailNotificationPreferences_EverydayOffers")
    public Boolean emailNotificationPreferences_EverydayOffers;
    @SerializedName("EmailNotificationPreferences_LimitedTimeOffers")
    public Boolean emailNotificationPreferences_LimitedTimeOffers;
    @SerializedName("EmailNotificationPreferences_OfferExpirationOption")
    public int emailNotificationPreferences_OfferExpirationOption;
    @SerializedName("EmailNotificationPreferences_PunchcardOffers")
    public Boolean emailNotificationPreferences_PunchcardOffers;
    @SerializedName("EmailNotificationPreferences_YourOffers")
    public Boolean emailNotificationPreferences_YourOffers;

    public static MWNotificationPreferences fromNotificationPreferences(NotificationPreferences preferences) {
        MWNotificationPreferences ecpPreferences = new MWNotificationPreferences();
        if (preferences != null) {
            ecpPreferences.emailNotificationPreferences_OfferExpirationOption = preferences.getEmailNotificationPreferencesOfferExpirationOption();
            ecpPreferences.emailNotificationPreferences_EverydayOffers = Boolean.valueOf(preferences.getEmailNotificationPreferencesEverydayOffers());
            ecpPreferences.emailNotificationPreferences_LimitedTimeOffers = Boolean.valueOf(preferences.getEmailNotificationPreferencesLimitedTimeOffers());
            ecpPreferences.appNotificationPreferences_PunchcardOffers = Boolean.valueOf(preferences.getAppNotificationPreferencesPunchcardOffers());
            if (preferences.getAppNotificationPreferencesEnabled() != null) {
                ecpPreferences.appNotificationPreferences_Enabled = preferences.getAppNotificationPreferencesEnabled();
            } else {
                ecpPreferences.appNotificationPreferences_Enabled = Boolean.valueOf(false);
            }
            ecpPreferences.emailNotificationPreferences_PunchcardOffers = Boolean.valueOf(preferences.getEmailNotificationPreferencesPunchcardOffers());
            if (preferences.getEmailNotificationPreferencesEnabled() != null) {
                ecpPreferences.emailNotificationPreferences_Enabled = preferences.getEmailNotificationPreferencesEnabled();
            } else {
                ecpPreferences.emailNotificationPreferences_Enabled = Boolean.valueOf(false);
            }
            ecpPreferences.appNotificationPreferences_EverydayOffers = Boolean.valueOf(preferences.getAppNotificationPreferencesEverydayOffers());
            ecpPreferences.appNotificationPreferences_LimitedTimeOffers = Boolean.valueOf(preferences.getAppNotificationPreferencesLimitedTimeOffers());
            ecpPreferences.appNotificationPreferences_OfferExpirationOption = preferences.getAppNotificationPreferencesOfferExpirationOption();
            ecpPreferences.appNotificationPreferences_YourOffers = Boolean.valueOf(preferences.getAppNotificationPreferencesYourOffers());
            ecpPreferences.emailNotificationPreferences_YourOffers = Boolean.valueOf(preferences.getEmailNotificationPreferencesYourOffers());
        }
        return ecpPreferences;
    }

    public static NotificationPreferences toNotificationPreferences(MWNotificationPreferences preferences) {
        NotificationPreferences newPreferences = new NotificationPreferences();
        if (preferences != null) {
            newPreferences.setEmailNotificationPreferencesOfferExpirationOption(preferences.emailNotificationPreferences_OfferExpirationOption);
            newPreferences.setEmailNotificationPreferencesEverydayOffers(preferences.emailNotificationPreferences_EverydayOffers);
            newPreferences.setEmailNotificationPreferencesLimitedTimeOffers(preferences.emailNotificationPreferences_LimitedTimeOffers);
            newPreferences.setAppNotificationPreferencesPunchcardOffers(preferences.appNotificationPreferences_PunchcardOffers);
            newPreferences.setAppNotificationPreferencesEnabled(preferences.appNotificationPreferences_Enabled);
            newPreferences.setEmailNotificationPreferencesPunchcardOffers(preferences.emailNotificationPreferences_PunchcardOffers);
            newPreferences.setEmailNotificationPreferencesEnabled(preferences.emailNotificationPreferences_Enabled);
            newPreferences.setAppNotificationPreferencesEverydayOffers(preferences.appNotificationPreferences_EverydayOffers);
            newPreferences.setAppNotificationPreferencesLimitedTimeOffers(preferences.appNotificationPreferences_LimitedTimeOffers);
            newPreferences.setAppNotificationPreferencesOfferExpirationOption(preferences.appNotificationPreferences_OfferExpirationOption);
            newPreferences.setAppNotificationPreferencesYourOffers(preferences.appNotificationPreferences_YourOffers);
            newPreferences.setEmailNotificationPreferencesYourOffers(preferences.emailNotificationPreferences_YourOffers);
        }
        return newPreferences;
    }
}
