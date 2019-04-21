package com.facebook;

import android.content.Context;
import android.os.Bundle;
import com.facebook.internal.Logger;
import java.math.BigDecimal;
import java.util.Currency;

@Deprecated
public class InsightsLogger {
    private static final String EVENT_NAME_LOG_CONVERSION_PIXEL = "fb_log_offsite_pixel";
    private static final String EVENT_PARAMETER_PIXEL_ID = "fb_offsite_pixel_id";
    private static final String EVENT_PARAMETER_PIXEL_VALUE = "fb_offsite_pixel_value";
    private AppEventsLogger appEventsLogger;

    private InsightsLogger(Context context, String applicationId, Session session) {
        this.appEventsLogger = AppEventsLogger.newLogger(context, applicationId, session);
    }

    public static InsightsLogger newLogger(Context context, String clientToken) {
        return new InsightsLogger(context, null, null);
    }

    public static InsightsLogger newLogger(Context context, String clientToken, String applicationId) {
        return new InsightsLogger(context, applicationId, null);
    }

    public static InsightsLogger newLogger(Context context, String clientToken, String applicationId, Session session) {
        return new InsightsLogger(context, applicationId, session);
    }

    public void logPurchase(BigDecimal purchaseAmount, Currency currency) {
        logPurchase(purchaseAmount, currency, null);
    }

    public void logPurchase(BigDecimal purchaseAmount, Currency currency, Bundle parameters) {
        this.appEventsLogger.logPurchase(purchaseAmount, currency, parameters);
    }

    public void logConversionPixel(String pixelId, double valueOfPixel) {
        if (pixelId == null) {
            Logger.log(LoggingBehavior.DEVELOPER_ERRORS, "Insights", "pixelID cannot be null");
            return;
        }
        Bundle parameters = new Bundle();
        parameters.putString(EVENT_PARAMETER_PIXEL_ID, pixelId);
        parameters.putDouble(EVENT_PARAMETER_PIXEL_VALUE, valueOfPixel);
        this.appEventsLogger.logEvent(EVENT_NAME_LOG_CONVERSION_PIXEL, valueOfPixel, parameters);
        AppEventsLogger.eagerFlush();
    }
}
