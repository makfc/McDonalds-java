package com.baidu.android.pushservice;

import android.app.Notification;
import android.content.Context;
import java.io.Serializable;

public abstract class PushNotificationBuilder implements Serializable {
    protected int mNotificationDefaults;
    protected int mNotificationFlags;
    protected String mNotificationText;
    protected String mNotificationTitle;
    protected String mNotificationsound;
    protected int mStatusbarIcon;
    protected long[] mVibratePattern;

    public abstract Notification construct(Context context);

    public void setNotificationDefaults(int i) {
        this.mNotificationDefaults = i;
    }

    public void setNotificationFlags(int i) {
        this.mNotificationFlags = i;
    }

    public void setNotificationText(String str) {
        this.mNotificationText = str;
    }

    public void setNotificationTitle(String str) {
        this.mNotificationTitle = str;
    }

    public void setStatusbarIcon(int i) {
        this.mStatusbarIcon = i;
    }
}
