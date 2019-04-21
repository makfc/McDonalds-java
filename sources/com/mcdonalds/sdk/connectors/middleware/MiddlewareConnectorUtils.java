package com.mcdonalds.sdk.connectors.middleware;

import android.text.TextUtils;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.connectors.middleware.helpers.MWCatalogManager;
import com.mcdonalds.sdk.connectors.middleware.response.MWJSONResponse;
import com.mcdonalds.sdk.services.configuration.Configuration;

public class MiddlewareConnectorUtils {
    public static AsyncException exceptionFromResults(MWJSONResponse response, AsyncException exception) {
        if (exception != null) {
            return exception;
        }
        if (response != null && response.getResultCode() != 1) {
            return MWException.fromErrorCode(response.getResultCode());
        }
        if (response == null) {
            return MWException.fromResponse(null);
        }
        return null;
    }

    public static boolean isUsingECP3() {
        String ECPVersionConfig = (String) Configuration.getSharedInstance().getValueForKey(MWCatalogManager.ECP_VERSION_KEY);
        if (TextUtils.isEmpty(ECPVersionConfig) || Integer.parseInt(String.valueOf(ECPVersionConfig.charAt(0))) <= 2) {
            return false;
        }
        return true;
    }

    public static boolean isUsingECP311() {
        String ECPVersionConfig = (String) Configuration.getSharedInstance().getValueForKey(MWCatalogManager.ECP_VERSION_KEY);
        return !TextUtils.isEmpty(ECPVersionConfig) && ECPVersionConfig.equalsIgnoreCase("3.1.1");
    }
}
