package com.admaster.square.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/* renamed from: com.admaster.square.utils.m */
public class GSMInfoUtil {
    /* renamed from: a */
    public static String m444a(Context context) {
        try {
            String str = "";
            String str2 = "";
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager == null) {
                    return str;
                }
                String deviceId = telephonyManager.getDeviceId();
                if (deviceId == null) {
                    return str2;
                }
                return deviceId;
            } catch (Throwable th) {
                return str2;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: b */
    public static String m445b(Context context) {
        try {
            String str = "";
            String str2 = "";
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager == null) {
                    return str;
                }
                String networkOperator = telephonyManager.getNetworkOperator();
                if (networkOperator == null) {
                    return str2;
                }
                return networkOperator;
            } catch (Exception e) {
                return str2;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    /* renamed from: c */
    public static String m446c(Context context) {
        if (context != null) {
            try {
                String subscriberId = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
                if (!TextUtils.isEmpty(subscriberId)) {
                    return subscriberId;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
        return "";
    }
}
