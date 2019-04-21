package com.mcdonalds.sdk.connectors.middleware.model;

import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.McDonalds;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.notification.NotificationModule;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DCSDevice {
    private static String DEVICE_ID_TYPE = "AndroidId";
    private static String DEVICE_NAME = "PersonalMobile";
    private static String DEVICE_OS = "Android";
    private static String SOURCE_ID = "MOT";
    @SerializedName("brand")
    public String brand;
    @SerializedName("deviceId")
    public String deviceId;
    @SerializedName("deviceIdType")
    public String deviceIdType;
    @SerializedName("isActive")
    public String isActive;
    @SerializedName("language")
    public String language;
    @SerializedName("manufacturer")
    public String manufacturer;
    @SerializedName("model")
    public String model;
    @SerializedName("name")
    public String name;
    @SerializedName("os")
    /* renamed from: os */
    public String f6063os;
    @SerializedName("osVersion")
    public String osVersion;
    @SerializedName("personalName")
    public String personalName;
    @SerializedName("phoneNumber")
    public String phoneNumber;
    @SerializedName("pushNotificationId")
    public String pushNotificationId;
    @SerializedName("sourceId")
    public String sourceId;
    @SerializedName("timezone")
    public String timezone;
    @SerializedName("token")
    public String token;

    public static DCSDevice fromDeviceInfo() {
        NotificationModule notificationModule = (NotificationModule) ModuleManager.getModule("notification");
        DCSDevice device = new DCSDevice();
        device.deviceIdType = DEVICE_ID_TYPE;
        device.token = notificationModule.getRegistrationId();
        device.deviceId = Secure.getString(McDonalds.getContext().getContentResolver(), "android_id");
        device.brand = Build.BRAND;
        device.model = Build.MODEL;
        device.manufacturer = Build.MANUFACTURER;
        device.language = Configuration.getSharedInstance().getCurrentLanguageTag();
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        TimeZone currentTimeZone = calendar.getTimeZone();
        device.timezone = currentTimeZone.getDisplayName(currentTimeZone.inDaylightTime(now), 0);
        device.personalName = DEVICE_NAME;
        device.f6063os = DEVICE_OS;
        device.osVersion = VERSION.RELEASE;
        device.isActive = DCSProfile.INDICATOR_TRUE;
        device.sourceId = SOURCE_ID;
        return device;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DCSDevice device = (DCSDevice) o;
        if (this.deviceId != null) {
            return this.deviceId.equals(device.deviceId);
        }
        if (device.deviceId != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.deviceId != null ? this.deviceId.hashCode() : 0;
    }
}
