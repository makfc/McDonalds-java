package com.mcdonalds.sdk.connectors.middleware;

import android.content.Context;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.C3883R;
import com.mcdonalds.sdk.McDonalds;
import com.mcdonalds.sdk.connectors.middleware.response.MWJSONResponse;
import com.mcdonalds.sdk.services.error.ErrorManager;

public class MWException extends AsyncException {
    public static final int INVALID_API_KEY_ERROR_CODE = 1001;

    private MWException(int errorCode, String shortDescription, String message) {
        super(message);
        this.mErrorCode = errorCode;
        this.mShortDescription = shortDescription;
    }

    public static MWException fromErrorCode(int errorCode) {
        if (errorCode == 0) {
            return null;
        }
        MWException exception = new MWException(errorCode, getShortDescription(errorCode), getMessageString(getMessageResourceId(errorCode)));
        ErrorManager.getInstance().setLastError(exception);
        return exception;
    }

    public static MWException fromResponse(MWJSONResponse response) {
        int errorResourceId = 0;
        int errorCode = 0;
        if (response == null) {
            errorResourceId = C3883R.string.error_generic;
        } else if (response.getResultCode() < 0) {
            errorResourceId = getMessageResourceId(response.getResultCode());
            errorCode = response.getResultCode();
        }
        if (errorResourceId == 0) {
            return null;
        }
        MWException exception = new MWException(errorCode, getShortDescription(errorCode), getMessageString(errorResourceId));
        ErrorManager.getInstance().setLastError(exception);
        return exception;
    }

    public static MWException fromGeneric() {
        MWException exception = new MWException(0, getMessageString(C3883R.string.error_generic), getMessageString(C3883R.string.error_generic));
        ErrorManager.getInstance().setLastError(exception);
        return exception;
    }

    private static int getMessageResourceId(int errorCode) {
        Context context = McDonalds.getContext();
        if (context == null) {
            return C3883R.string.error_generic;
        }
        String resourceKey = "";
        if (errorCode == 1001) {
            resourceKey = "invalid_api_key_error_message";
        } else if (resourceKey.isEmpty()) {
            resourceKey = "ecp_error_" + Math.abs(errorCode);
        }
        int resourceId = getResourceId(context, resourceKey);
        if (resourceId == 0) {
            return getResourceId(context, "dcs_error_" + Math.abs(errorCode));
        }
        return resourceId;
    }

    private static int getResourceId(Context context, String resourceKey) {
        return context.getResources().getIdentifier(resourceKey, "string", context.getPackageName());
    }

    private static String getShortDescription(int errorCode) {
        Context context = McDonalds.getContext();
        if (context == null) {
            return "";
        }
        int resourceId = getResourceId(context, "ecp_error_" + Math.abs(errorCode) + "_short_description");
        if (resourceId <= 0) {
            resourceId = C3883R.string.error_generic;
        }
        return context.getString(resourceId);
    }

    private static String getMessageString(int resourceId) {
        Context context = McDonalds.getContext();
        if (context == null) {
            return "";
        }
        if (resourceId <= 0) {
            resourceId = C3883R.string.error_generic;
        }
        return context.getString(resourceId);
    }
}
