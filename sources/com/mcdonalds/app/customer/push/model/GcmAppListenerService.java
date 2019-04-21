package com.mcdonalds.app.customer.push.model;

import android.os.Bundle;
import com.ensighten.Ensighten;
import com.mcdonalds.app.customer.push.NotificationManager;
import com.mcdonalds.sdk.connectors.google.cloudmessaging.GcmListenerService;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.notification.NotificationModule;

public class GcmAppListenerService extends GcmListenerService {
    public void onMessageReceived(String from, Bundle data) {
        Ensighten.evaluateEvent(this, "onMessageReceived", new Object[]{from, data});
        NotificationModule notificationModule = (NotificationModule) ModuleManager.getModule("notification");
        if (notificationModule == null || notificationModule.isListenerAvailable()) {
            super.onMessageReceived(from, data);
        } else {
            NotificationManager.sendNotification(this, data);
        }
    }
}
