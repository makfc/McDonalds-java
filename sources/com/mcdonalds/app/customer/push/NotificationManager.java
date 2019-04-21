package com.mcdonalds.app.customer.push;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.p000v4.app.NotificationCompat.BigTextStyle;
import android.support.p000v4.app.NotificationCompat.Builder;
import com.ensighten.Ensighten;
import com.google.gson.Gson;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.customer.push.model.NotificationBody;
import com.mcdonalds.app.customer.push.model.NotificationCustomContent;
import com.mcdonalds.app.startup.SplashActivity;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.notification.NotificationModule;
import com.mcdonalds.sdk.modules.notification.NotificationModule.NotificationListener;
import com.mcdonalds.sdk.modules.notification.PushConstants;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.log.SafeLog;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;

public class NotificationManager {
    private static NotificationListener sListener = new C30982();

    /* renamed from: com.mcdonalds.app.customer.push.NotificationManager$2 */
    static class C30982 implements NotificationListener {
        C30982() {
        }

        public void onNotificationReceived(Context context, Bundle data) {
            Ensighten.evaluateEvent(this, "onNotificationReceived", new Object[]{context, data});
            NotificationManager.sendNotification(context, data);
        }
    }

    public static void register(final AsyncListener<Boolean> callback) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.NotificationManager", JiceArgs.EVENT_REGISTER, new Object[]{callback});
        NotificationModule notificationModule = (NotificationModule) ModuleManager.getModule("notification");
        if (notificationModule != null) {
            notificationModule.register();
            notificationModule.setNotificationListener(sListener);
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                Ensighten.evaluateEvent(this, "run", null);
                callback.onResponse(Boolean.valueOf(true), null, null);
            }
        });
    }

    public static void sendNotification(Context context, Bundle extras) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.NotificationManager", "sendNotification", new Object[]{context, extras});
        android.app.NotificationManager notificationManager = (android.app.NotificationManager) context.getSystemService("notification");
        String title = "";
        String message = "";
        int messageId = -1;
        String deliveryId = "";
        String messageIdKey = "";
        if (extras != null) {
            title = extras.getString(PushConstants.TITLE);
            message = extras.getString(PushConstants.TEXT);
            messageIdKey = PushConstants.VMOB_MESSAGE_ID;
            String messageIdString = extras.getString(PushConstants.VMOB_MESSAGE_ID);
            deliveryId = extras.getString(PushConstants.DELIVERY_ID);
            if (message != null && messageIdString == null && deliveryId == null) {
                messageIdKey = PushConstants.MESSAGE_ID;
                Gson gson = new Gson();
                Class cls = NotificationBody.class;
                NotificationBody body = !(gson instanceof Gson) ? gson.fromJson(message, cls) : GsonInstrumentation.fromJson(gson, message, cls);
                NotificationCustomContent customContent = body.getCustomContent();
                messageIdString = customContent.getMessageId();
                deliveryId = customContent.getDeliveryId();
                message = body.getDescription();
            } else if (message == null) {
                message = (String) extras.get(PushConstants.ADOBE_MESSAGE_KEY);
                title = (String) extras.get(PushConstants.ADOBE_TITLE_KEY);
                messageIdString = (String) extras.get(PushConstants.MESSAGE_ID);
                deliveryId = (String) extras.get(PushConstants.DELIVERY_ID);
            }
            Bundle bundle = (Bundle) extras.get("notification");
            if (bundle != null && message == null) {
                title = (String) bundle.get(PushConstants.TITLE_KEY);
                message = (String) bundle.get(PushConstants.BODY_KEY);
            }
            if (!(messageIdString == null || deliveryId == null)) {
                try {
                    messageId = Integer.parseInt(messageIdString);
                    CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
                    if (messageId == -1) {
                        messageId = Integer.parseInt(messageIdString);
                    }
                    customerModule.trackNotification(messageId, deliveryId, 1);
                } catch (NumberFormatException e) {
                    SafeLog.m7746e("Notification", "error", e);
                }
            }
        }
        Intent intent = new Intent(context, SplashActivity.class);
        title = context.getString(C2658R.string.app_name);
        Bundle notificationExtras = extras;
        if (notificationExtras == null) {
            notificationExtras = new Bundle();
        }
        notificationExtras.putBoolean(PushConstants.NOTIFICATION_CLICKED, true);
        notificationExtras.putInt(messageIdKey, messageId);
        notificationExtras.putString(PushConstants.DELIVERY_ID, deliveryId);
        intent.putExtras(notificationExtras);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 1, intent, 134217728);
        Builder mBuilder = new Builder(context).setContentTitle(title).setStyle(new BigTextStyle().bigText(message)).setContentText(message).setAutoCancel(true);
        if (VERSION.SDK_INT >= 21) {
            mBuilder.setSmallIcon(C2358R.C2359drawable.ic_launcher_white);
        } else {
            mBuilder.setSmallIcon(C2658R.mipmap.ic_launcher);
        }
        mBuilder.setContentIntent(contentIntent);
        notificationManager.notify((int) System.currentTimeMillis(), mBuilder.build());
    }
}
