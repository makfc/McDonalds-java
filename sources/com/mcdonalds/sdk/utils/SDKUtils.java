package com.mcdonalds.sdk.utils;

import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.middleware.model.MWForceUpdateResponse;
import com.mcdonalds.sdk.connectors.middleware.request.MWConfigurationUpdateRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWForceUpdateRequest;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.mcdonalds.sdk.services.network.RequestManagerServiceConnection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SDKUtils {
    public static final double EPSILON = 1.0E-4d;

    public static void checkForUpdates(RequestManagerServiceConnection connection, final String appBuildConfigVersionName, final AsyncListener<Boolean> listener) {
        connection.processRequest(new MWForceUpdateRequest(), new AsyncListener<MWForceUpdateResponse>() {
            public void onResponse(MWForceUpdateResponse response, AsyncToken token, AsyncException exception) {
                if (exception != null || response == null || response.versionInfo == null) {
                    MCDLog.info("Not able to determine if app version is current");
                    listener.onResponse(null, token, exception);
                    return;
                }
                String minimumVersion = response.versionInfo.minVersion;
                String currentVersion = response.versionInfo.currentVersion;
                String versionNow = "0.0.0";
                Matcher m = Pattern.compile("^.*?([0-9]+(\\.[0-9]+)*).*$").matcher(appBuildConfigVersionName);
                if (m.matches()) {
                    versionNow = m.group(1);
                }
                if (SDKUtils.compareVersions(versionNow, currentVersion)) {
                    MCDLog.info("app version is current");
                    listener.onResponse(null, token, null);
                } else if (SDKUtils.compareVersions(versionNow, minimumVersion)) {
                    MCDLog.info("app version is not current, but at least minimum");
                    listener.onResponse(Boolean.valueOf(false), token, null);
                } else {
                    MCDLog.info("app version is below minimum");
                    listener.onResponse(Boolean.valueOf(true), token, null);
                }
            }
        });
    }

    private static boolean compareVersions(String versionNow, String referenceVersion) {
        String[] versionNowComponents = versionNow.split("\\.");
        String[] referenceVersionComponents = referenceVersion.split("\\.");
        int length = Math.max(versionNowComponents.length, referenceVersionComponents.length);
        int i = 0;
        while (i < length) {
            int refDigit;
            int nowDigit = i < versionNowComponents.length ? Integer.parseInt(versionNowComponents[i]) : 0;
            if (i < referenceVersionComponents.length) {
                refDigit = Integer.parseInt(referenceVersionComponents[i]);
            } else {
                refDigit = 0;
            }
            if (nowDigit < refDigit) {
                return false;
            }
            if (nowDigit > refDigit) {
                return true;
            }
            i++;
        }
        return true;
    }

    public static void requestConfig(RequestManagerServiceConnection connection, final AsyncListener<Map> listener) {
        connection.processRequest(new MWConfigurationUpdateRequest(), new AsyncListener<Map>() {
            public void onResponse(Map response, AsyncToken token, AsyncException exception) {
                listener.onResponse(response, token, exception);
            }
        });
    }

    public static boolean doubleEquals(double left, double right) {
        return Math.abs(left - right) < 1.0E-4d;
    }
}
