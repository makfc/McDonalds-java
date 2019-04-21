package com.baidu.android.pushservice;

import android.app.Notification;
import android.app.Notification.Builder;
import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;

public class BasicPushNotificationBuilder extends PushNotificationBuilder {
    public Notification construct(Context context) {
        Notification build;
        if (VERSION.SDK_INT >= 16) {
            Builder builder = new Builder(context);
            if (this.mNotificationDefaults != 0) {
                builder.setDefaults(this.mNotificationDefaults);
            }
            if (this.mNotificationsound != null) {
                builder.setSound(Uri.parse(this.mNotificationsound));
            }
            if (this.mVibratePattern != null) {
                builder.setVibrate(this.mVibratePattern);
            }
            if (this.mStatusbarIcon != 0) {
                builder.setSmallIcon(this.mStatusbarIcon);
            }
            builder.setContentTitle(this.mNotificationTitle);
            builder.setContentText(this.mNotificationText);
            build = builder.build();
            if (this.mNotificationFlags != 0) {
                build.flags = this.mNotificationFlags;
            }
        } else {
            build = new Notification();
            if (this.mNotificationDefaults != 0) {
                build.defaults = this.mNotificationDefaults;
            }
            if (this.mNotificationsound != null) {
                build.sound = Uri.parse(this.mNotificationsound);
            }
            if (this.mVibratePattern != null) {
                build.vibrate = this.mVibratePattern;
            }
            if (this.mStatusbarIcon != 0) {
                build.icon = this.mStatusbarIcon;
            }
            if (this.mNotificationFlags != 0) {
                build.flags = this.mNotificationFlags;
            }
            build.setLatestEventInfo(context, this.mNotificationTitle, this.mNotificationText, null);
        }
        return build;
    }
}
