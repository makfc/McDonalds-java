package com.newrelic.agent.android.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.net.InetAddress;

public class Reachability {
    private static final int REACHABILITY_TIMEOUT = 500;

    public static boolean hasReachableNetworkConnection(Context context, String reachableHost) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService("connectivity");
            if (cm == null) {
                return false;
            }
            boolean isMobile;
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork.getType() == 0) {
                isMobile = true;
            } else {
                isMobile = false;
            }
            boolean isWiFi;
            if (activeNetwork.getType() == 1) {
                isWiFi = true;
            } else {
                isWiFi = false;
            }
            if ((isMobile || isWiFi) && activeNetwork.isConnectedOrConnecting()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            try {
                return InetAddress.getByName(reachableHost).isReachable(500);
            } catch (Exception e2) {
                return false;
            }
        }
    }

    public static boolean hasReachableWifiConnection(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService("connectivity");
            if (cm == null) {
                return false;
            }
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork == null) {
                return false;
            }
            boolean isWiFi;
            if (activeNetwork.getType() == 1) {
                isWiFi = true;
            } else {
                isWiFi = false;
            }
            if (isWiFi && activeNetwork.isConnectedOrConnecting()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean hasReachableMobileConnection(Context context) {
        try {
            NetworkInfo activeNetwork = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetwork == null) {
                return false;
            }
            boolean isMobile;
            if (activeNetwork.getType() == 0) {
                isMobile = true;
            } else {
                isMobile = false;
            }
            if (isMobile && activeNetwork.isConnectedOrConnecting()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
