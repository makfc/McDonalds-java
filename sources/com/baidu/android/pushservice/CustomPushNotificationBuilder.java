package com.baidu.android.pushservice;

import android.app.Notification;
import android.app.Notification.Builder;
import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.widget.RemoteViews;

public class CustomPushNotificationBuilder extends PushNotificationBuilder {
    private int mLayoutIconDrawable;
    private int mLayoutIconId;
    private int mLayoutId;
    private int mLayoutTextId;
    private int mLayoutTitleId;

    public Notification construct(Context context) {
        Notification build;
        RemoteViews remoteViews;
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
            if (this.mLayoutId != 0) {
                remoteViews = new RemoteViews(context.getPackageName(), this.mLayoutId);
                if (this.mLayoutIconDrawable != 0) {
                    remoteViews.setImageViewResource(this.mLayoutIconId, this.mLayoutIconDrawable);
                }
                if (this.mNotificationTitle != null) {
                    remoteViews.setTextViewText(this.mLayoutTitleId, this.mNotificationTitle);
                }
                if (this.mNotificationText != null) {
                    remoteViews.setTextViewText(this.mLayoutTextId, this.mNotificationText);
                }
                builder.setContent(remoteViews);
            } else {
                builder.setContentTitle(this.mNotificationTitle);
                builder.setContentText(this.mNotificationText);
            }
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
            if (this.mLayoutId != 0) {
                remoteViews = new RemoteViews(context.getPackageName(), this.mLayoutId);
                if (this.mLayoutIconDrawable != 0) {
                    remoteViews.setImageViewResource(this.mLayoutIconId, this.mLayoutIconDrawable);
                }
                if (this.mNotificationTitle != null) {
                    remoteViews.setTextViewText(this.mLayoutTitleId, this.mNotificationTitle);
                }
                if (this.mNotificationText != null) {
                    remoteViews.setTextViewText(this.mLayoutTextId, this.mNotificationText);
                }
                build.contentView = remoteViews;
            } else {
                build.setLatestEventInfo(context, this.mNotificationTitle, this.mNotificationText, null);
            }
        }
        return build;
    }
}
