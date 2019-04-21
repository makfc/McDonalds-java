package com.mcdonalds.sdk.modules.notification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.mcdonalds.sdk.modules.BaseModule;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.notifications.NotificationIntentService;

public class NotificationModule extends BaseModule {
    public static final String CONNECTOR_KEY = "modules.notification.connector";
    public static final String NAME = "notification";
    Context mContext;
    NotificationListener mNotificationListener;

    public interface NotificationListener {
        void onNotificationReceived(Context context, Bundle bundle);
    }

    public NotificationModule(Context context) {
        this.mContext = context;
    }

    public void setNotificationListener(NotificationListener notificationListener) {
        this.mNotificationListener = notificationListener;
    }

    public void register() {
        sendIntent(0);
    }

    public String getRegistrationId() {
        return LocalDataManager.getSharedInstance().getNotificationRegId();
    }

    public void unregister() {
        sendIntent(1);
    }

    public void notifyNotificationReceived(Context context, Bundle data) {
        if (this.mNotificationListener != null) {
            this.mNotificationListener.onNotificationReceived(context, data);
        }
    }

    public boolean isListenerAvailable() {
        if (this.mNotificationListener != null) {
            return true;
        }
        return false;
    }

    private void sendIntent(int action) {
        Intent intent = new Intent(this.mContext, NotificationIntentService.class);
        Bundle extras = new Bundle();
        extras.putInt(NotificationIntentService.PARAM_ACTION, action);
        intent.putExtras(extras);
        this.mContext.startService(intent);
    }
}
