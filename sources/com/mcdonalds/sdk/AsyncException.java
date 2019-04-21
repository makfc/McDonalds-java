package com.mcdonalds.sdk;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.notifications.NotificationCenter;

public class AsyncException extends RuntimeException {
    public static final int DEFAULT_ERROR_CODE = 0;
    public static final String NOTIFICATION_API_INVALID_ERROR = "NOTIFICATION_API_INVALID_ERROR";
    public static final String NOTIFICATION_ASYNC_ERROR = "NOTIFICATION_ASYNC_ERROR";
    private Integer ecpResultCode;
    protected int mErrorCode;
    private String mRequestUrl;
    protected String mShortDescription;
    private Integer serverCode;

    public AsyncException(String detailMessage) {
        super(detailMessage);
    }

    public AsyncException(Integer ecpResultCode, Integer serverCode, String detailMessage) {
        this(detailMessage);
        this.ecpResultCode = ecpResultCode;
        this.serverCode = serverCode;
    }

    public AsyncException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public AsyncException(Exception exception) {
        super(exception.getLocalizedMessage(), exception);
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public String getShortDescription() {
        return this.mShortDescription;
    }

    public static void report(String detailMessage) {
        if (!TextUtils.isEmpty(detailMessage)) {
            report(new AsyncException(detailMessage));
        }
    }

    public static void report(AsyncException exception) {
        if (exception != null) {
            String str;
            String localizedMessage = exception.getLocalizedMessage();
            if (Configuration.getSharedInstance().getBooleanForKey("log.logsToConsole")) {
                String simpleName = AsyncException.class.getSimpleName();
                if (TextUtils.isEmpty(localizedMessage)) {
                    str = "<no localized message>";
                } else {
                    str = localizedMessage;
                }
                Log.e(simpleName, str, exception);
            }
            Bundle extras = new Bundle();
            str = NotificationCenter.EXTRA_MESSAGE;
            if (TextUtils.isEmpty(localizedMessage)) {
                localizedMessage = "";
            }
            extras.putString(str, localizedMessage);
            NotificationCenter nc = NotificationCenter.getSharedInstance();
            if (nc != null) {
                nc.postNotification(NOTIFICATION_ASYNC_ERROR, extras);
            }
        }
    }

    public static void reportAPIInvalidError(AsyncException exception) {
        if (exception != null) {
            String str;
            String localizedMessage = exception.getLocalizedMessage();
            if (Configuration.getSharedInstance().getBooleanForKey("log.logsToConsole")) {
                String simpleName = AsyncException.class.getSimpleName();
                if (TextUtils.isEmpty(localizedMessage)) {
                    str = "<no localized message>";
                } else {
                    str = localizedMessage;
                }
                Log.e(simpleName, str, exception);
            }
            Bundle extras = new Bundle();
            str = NotificationCenter.EXTRA_MESSAGE;
            if (TextUtils.isEmpty(localizedMessage)) {
                localizedMessage = "";
            }
            extras.putString(str, localizedMessage);
            NotificationCenter nc = NotificationCenter.getSharedInstance();
            if (nc != null) {
                nc.postNotification(NOTIFICATION_API_INVALID_ERROR, extras);
            }
        }
    }

    public Integer getServerCode() {
        return this.serverCode;
    }

    public Integer getEcpResultCode() {
        return this.ecpResultCode;
    }

    public void setEcpResultCode(Integer ecpResultCode) {
        this.ecpResultCode = ecpResultCode;
    }

    public String getRequestUrl() {
        return this.mRequestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.mRequestUrl = requestUrl;
    }
}
