package com.mcdonalds.sdk.services.analytics;

import android.content.Context;

public abstract class AnalyticsWrapper {
    protected Context mContext;

    public abstract void initialize();

    public abstract void log(AnalyticType analyticType, AnalyticsArgs analyticsArgs);

    protected AnalyticsWrapper(Context context) {
        this.mContext = context;
    }
}
