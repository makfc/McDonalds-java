package com.newrelic.agent.android.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.newrelic.agent.android.api.common.WanType;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.text.MessageFormat;

public final class Connectivity {
    private static final String ANDROID = "Android";
    private static AgentLog log = AgentLogManager.getAgentLog();

    public static String carrierNameFromContext(Context context) {
        try {
            NetworkInfo networkInfo = getNetworkInfo(context);
            if (!isConnected(networkInfo)) {
                return "none";
            }
            if (isWan(networkInfo)) {
                return carrierNameFromTelephonyManager(context);
            }
            if (isWifi(networkInfo)) {
                return "wifi";
            }
            log.warning(MessageFormat.format("Unknown network type: {0} [{1}]", new Object[]{networkInfo.getTypeName(), Integer.valueOf(networkInfo.getType())}));
            return "unknown";
        } catch (SecurityException e) {
            return "unknown";
        }
    }

    public static String wanType(Context context) {
        try {
            NetworkInfo networkInfo = getNetworkInfo(context);
            if (!isConnected(networkInfo)) {
                return "none";
            }
            if (isWifi(networkInfo)) {
                return "wifi";
            }
            if (isWan(networkInfo)) {
                return connectionNameFromNetworkSubtype(networkInfo.getSubtype());
            }
            return "unknown";
        } catch (SecurityException e) {
            return "unknown";
        }
    }

    private static boolean isConnected(NetworkInfo networkInfo) {
        return networkInfo != null && networkInfo.isConnected();
    }

    private static boolean isWan(NetworkInfo networkInfo) {
        switch (networkInfo.getType()) {
            case 0:
            case 2:
            case 3:
            case 4:
            case 5:
                return true;
            default:
                return false;
        }
    }

    private static boolean isWifi(NetworkInfo networkInfo) {
        switch (networkInfo.getType()) {
            case 1:
            case 6:
            case 7:
            case 9:
                return true;
            default:
                return false;
        }
    }

    private static NetworkInfo getNetworkInfo(Context context) throws SecurityException {
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (SecurityException e) {
            log.warning("Cannot determine network state. Enable android.permission.ACCESS_NETWORK_STATE in your manifest.");
            throw e;
        }
    }

    private static String carrierNameFromTelephonyManager(Context context) {
        String networkOperator = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
        if (networkOperator == null || networkOperator.isEmpty()) {
            return "unknown";
        }
        boolean smellsLikeAnEmulator = Build.PRODUCT.equals("google_sdk") || Build.PRODUCT.equals("sdk") || Build.PRODUCT.equals("sdk_x86") || Build.FINGERPRINT.startsWith("generic");
        if (networkOperator.equals(ANDROID) && smellsLikeAnEmulator) {
            return "wifi";
        }
        return networkOperator;
    }

    private static String connectionNameFromNetworkSubtype(int subType) {
        switch (subType) {
            case 1:
                return WanType.GPRS;
            case 2:
                return WanType.EDGE;
            case 3:
                return WanType.UMTS;
            case 4:
                return WanType.CDMA;
            case 5:
                return WanType.EVDO_REV_0;
            case 6:
                return WanType.EVDO_REV_A;
            case 7:
                return WanType.RTT;
            case 8:
                return WanType.HSDPA;
            case 9:
                return WanType.HSUPA;
            case 10:
                return WanType.HSPA;
            case 11:
                return WanType.IDEN;
            case 12:
                return WanType.EVDO_REV_B;
            case 13:
                return WanType.LTE;
            case 14:
                return WanType.HRPD;
            case 15:
                return WanType.HSPAP;
            default:
                return "unknown";
        }
    }
}
