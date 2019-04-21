package com.crashlytics.android.answers;

import com.mcdonalds.sdk.services.analytics.JiceArgs;

public class LoginEvent extends PredefinedEvent<LoginEvent> {
    /* Access modifiers changed, original: 0000 */
    public String getPredefinedType() {
        return JiceArgs.EVENT_DASHBOARD_LOGIN;
    }
}
