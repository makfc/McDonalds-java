package com.mcdonalds.sdk.connectors.google.cloudmessaging;

import android.os.Bundle;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.notification.NotificationModule;

public class GcmListenerService extends com.google.android.gms.gcm.GcmListenerService {
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);
        if (!data.isEmpty()) {
            NotificationModule notificationModule = (NotificationModule) ModuleManager.getModule("notification");
            if (notificationModule != null) {
                notificationModule.notifyNotificationReceived(this, data);
            }
        }
    }
}
