package com.mcdonalds.sdk.connectors.middleware.model;

import android.support.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.NotificationPreferences;
import java.util.ArrayList;
import java.util.List;

public class DCSSubscription {
    public static final String DESCRIPTION_EMAIL_NOTIFICATION = "EmailNotificationEnabled";
    public static final String DESCRIPTION_MOBILE_NOTIFICATION = "MobileNotificationEnabled";
    public static final String DESCRIPTION_OFFER_PROGRAM = "OfferProgram";
    public static final String DESCRIPTION_OPT_IN_COMMUNICATION_CHANNEL = "CommunicationChannel";
    public static final String DESCRIPTION_OPT_IN_CONTESTS = "Contests";
    public static final String DESCRIPTION_OPT_IN_OTHER_MARKETING_MESSAGES = "OtherMarketingMessages";
    public static final String DESCRIPTION_OPT_IN_PROGRAM_CHANGES = "ProgramChanges";
    public static final String DESCRIPTION_OPT_IN_SURVEYS = "Surveys";
    public static final String ID_EMAIL_NOTIFICATION = "10";
    public static final String ID_MOBILE_NOTIFICATION = "11";
    public static final String ID_OFFER_PROGRAM = "7";
    public static final String ID_OPT_IN_COMMUNICATION_CHANNEL = "1";
    public static final String ID_OPT_IN_CONTESTS = "4";
    public static final String ID_OPT_IN_OTHER_MARKETING_MESSAGES = "5";
    public static final String ID_OPT_IN_PROGRAM_CHANGES = "3";
    public static final String ID_OPT_IN_SURVEYS = "2";
    public static final String LEGACY_ID_EMAIL_NOTIFICATION = "5";
    public static final String LEGACY_ID_MOBILE_NOTIFICATION = "5";
    public static final String LEGACY_ID_OFFER_PROGRAM = "2";
    public static final String LEGACY_ID_OPT_IN_COMMUNICATION_CHANNEL = "1";
    public static final String LEGACY_ID_OPT_IN_CONTESTS = "4";
    public static final String LEGACY_ID_OPT_IN_OTHER_MARKETING_MESSAGES = "5";
    public static final String LEGACY_ID_OPT_IN_PROGRAM_CHANGES = "3";
    public static final String LEGACY_ID_OPT_IN_SURVEYS = "2";
    public static final String LEGACY_TYPE_OPT_IN = "optin";
    public static final String LEGACY_TYPE_PREF = "pref";
    public static final String LEGACY_TYPE_SUB = "sub";
    @SerializedName("channelId")
    public String channelId;
    @SerializedName("deviceId")
    public String deviceId;
    @SerializedName("legacyId")
    public String legacyId;
    @SerializedName("legacyType")
    public String legacyType;
    @SerializedName("optInDate")
    public String optInDate;
    @SerializedName("optInStatus")
    public String optInStatus;
    @SerializedName("optOutDate")
    public String optOutDate;
    @SerializedName("optOutReason")
    public String optOutReason;
    @SerializedName("sourceId")
    public String sourceId;
    @SerializedName("subscriptionDesc")
    public String subscriptionDesc;
    @SerializedName("subscriptionId")
    public String subscriptionId;

    @NonNull
    static List<DCSSubscription> createSubscriptionList(CustomerProfile customerProfile) {
        DCSSubscription subscription;
        List<DCSSubscription> dcsSubscriptions = new ArrayList();
        Boolean optInCommunicationsChannel = customerProfile.getOptInForCommunicationChannel();
        if (optInCommunicationsChannel != null) {
            subscription = new DCSSubscription();
            subscription.subscriptionId = "1";
            subscription.subscriptionDesc = DESCRIPTION_OPT_IN_COMMUNICATION_CHANNEL;
            subscription.legacyId = "1";
            subscription.legacyType = LEGACY_TYPE_OPT_IN;
            subscription.optInStatus = DCSProfile.indicatorForBoolean(optInCommunicationsChannel);
            dcsSubscriptions.add(subscription);
        }
        Boolean optInSurveys = customerProfile.getOptInForSurveys();
        if (optInSurveys != null) {
            subscription = new DCSSubscription();
            subscription.subscriptionId = "2";
            subscription.subscriptionDesc = DESCRIPTION_OPT_IN_SURVEYS;
            subscription.legacyId = "2";
            subscription.legacyType = LEGACY_TYPE_OPT_IN;
            subscription.optInStatus = DCSProfile.indicatorForBoolean(optInSurveys);
            dcsSubscriptions.add(subscription);
        }
        Boolean optInProgramChanges = customerProfile.getOptInForProgramChanges();
        if (optInProgramChanges != null) {
            subscription = new DCSSubscription();
            subscription.subscriptionId = "3";
            subscription.subscriptionDesc = DESCRIPTION_OPT_IN_PROGRAM_CHANGES;
            subscription.legacyId = "3";
            subscription.legacyType = LEGACY_TYPE_OPT_IN;
            subscription.optInStatus = DCSProfile.indicatorForBoolean(optInProgramChanges);
            dcsSubscriptions.add(subscription);
        }
        Boolean optInContests = customerProfile.getOptInForContests();
        if (optInContests != null) {
            subscription = new DCSSubscription();
            subscription.subscriptionId = "4";
            subscription.subscriptionDesc = DESCRIPTION_OPT_IN_CONTESTS;
            subscription.legacyId = "4";
            subscription.legacyType = LEGACY_TYPE_OPT_IN;
            subscription.optInStatus = DCSProfile.indicatorForBoolean(optInContests);
            dcsSubscriptions.add(subscription);
        }
        Boolean optInOtherMarketMessages = customerProfile.getOptInForContests();
        if (optInOtherMarketMessages != null) {
            subscription = new DCSSubscription();
            subscription.subscriptionId = "5";
            subscription.subscriptionDesc = DESCRIPTION_OPT_IN_OTHER_MARKETING_MESSAGES;
            subscription.legacyId = "5";
            subscription.legacyType = LEGACY_TYPE_OPT_IN;
            subscription.optInStatus = DCSProfile.indicatorForBoolean(optInOtherMarketMessages);
            dcsSubscriptions.add(subscription);
        }
        Boolean subscribedToOffers = customerProfile.getSubscribedToOffers();
        if (subscribedToOffers != null) {
            subscription = new DCSSubscription();
            subscription.subscriptionId = "7";
            subscription.subscriptionDesc = DESCRIPTION_OFFER_PROGRAM;
            subscription.legacyId = "2";
            subscription.legacyType = LEGACY_TYPE_SUB;
            subscription.optInStatus = DCSProfile.indicatorForBoolean(subscribedToOffers);
            dcsSubscriptions.add(subscription);
        }
        NotificationPreferences notificationPreferences = customerProfile.getNotificationPreferences();
        if (notificationPreferences != null) {
            Boolean appNotificationsEnabled = notificationPreferences.getAppNotificationPreferencesEnabled();
            if (appNotificationsEnabled != null) {
                DCSSubscription appNotificationSubscription = new DCSSubscription();
                appNotificationSubscription.subscriptionId = ID_MOBILE_NOTIFICATION;
                appNotificationSubscription.subscriptionDesc = DESCRIPTION_MOBILE_NOTIFICATION;
                appNotificationSubscription.legacyId = "5";
                appNotificationSubscription.legacyType = LEGACY_TYPE_PREF;
                appNotificationSubscription.optInStatus = DCSProfile.indicatorForBoolean(appNotificationsEnabled);
                dcsSubscriptions.add(appNotificationSubscription);
            }
            Boolean emailNotificationsEnabled = notificationPreferences.getEmailNotificationPreferencesEnabled();
            if (emailNotificationsEnabled != null) {
                DCSSubscription emailNotificationsSubscription = new DCSSubscription();
                emailNotificationsSubscription.subscriptionId = "10";
                emailNotificationsSubscription.subscriptionDesc = DESCRIPTION_EMAIL_NOTIFICATION;
                emailNotificationsSubscription.legacyId = "5";
                emailNotificationsSubscription.legacyType = LEGACY_TYPE_PREF;
                emailNotificationsSubscription.optInStatus = DCSProfile.indicatorForBoolean(emailNotificationsEnabled);
                dcsSubscriptions.add(emailNotificationsSubscription);
            }
        }
        return dcsSubscriptions;
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DCSSubscription that = (DCSSubscription) o;
        if (this.sourceId != null) {
            if (!this.sourceId.equals(that.sourceId)) {
                return false;
            }
        } else if (that.sourceId != null) {
            return false;
        }
        if (this.subscriptionId != null) {
            if (!this.subscriptionId.equals(that.subscriptionId)) {
                return false;
            }
        } else if (that.subscriptionId != null) {
            return false;
        }
        if (this.subscriptionDesc != null) {
            if (!this.subscriptionDesc.equals(that.subscriptionDesc)) {
                return false;
            }
        } else if (that.subscriptionDesc != null) {
            return false;
        }
        if (this.legacyId != null) {
            if (!this.legacyId.equals(that.legacyId)) {
                return false;
            }
        } else if (that.legacyId != null) {
            return false;
        }
        if (this.legacyType != null) {
            if (!this.legacyType.equals(that.legacyType)) {
                return false;
            }
        } else if (that.legacyType != null) {
            return false;
        }
        if (this.channelId != null) {
            if (!this.channelId.equals(that.channelId)) {
                return false;
            }
        } else if (that.channelId != null) {
            return false;
        }
        if (this.deviceId != null) {
            if (!this.deviceId.equals(that.deviceId)) {
                return false;
            }
        } else if (that.deviceId != null) {
            return false;
        }
        if (this.optInStatus != null) {
            if (!this.optInStatus.equals(that.optInStatus)) {
                return false;
            }
        } else if (that.optInStatus != null) {
            return false;
        }
        if (this.optInDate != null) {
            if (!this.optInDate.equals(that.optInDate)) {
                return false;
            }
        } else if (that.optInDate != null) {
            return false;
        }
        if (this.optOutDate != null) {
            if (!this.optOutDate.equals(that.optOutDate)) {
                return false;
            }
        } else if (that.optOutDate != null) {
            return false;
        }
        if (this.optOutReason != null) {
            z = this.optOutReason.equals(that.optOutReason);
        } else if (that.optOutReason != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int result;
        int hashCode;
        int i = 0;
        if (this.sourceId != null) {
            result = this.sourceId.hashCode();
        } else {
            result = 0;
        }
        int i2 = result * 31;
        if (this.subscriptionId != null) {
            hashCode = this.subscriptionId.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.subscriptionDesc != null) {
            hashCode = this.subscriptionDesc.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.legacyId != null) {
            hashCode = this.legacyId.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.legacyType != null) {
            hashCode = this.legacyType.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.channelId != null) {
            hashCode = this.channelId.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.deviceId != null) {
            hashCode = this.deviceId.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.optInStatus != null) {
            hashCode = this.optInStatus.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.optInDate != null) {
            hashCode = this.optInDate.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.optOutDate != null) {
            hashCode = this.optOutDate.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (i2 + hashCode) * 31;
        if (this.optOutReason != null) {
            i = this.optOutReason.hashCode();
        }
        return hashCode + i;
    }
}
