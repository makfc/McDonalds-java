package com.mcdonalds.sdk.services.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.p000v4.content.LocalBroadcastManager;
import com.mcdonalds.sdk.McDonalds;

public class NotificationCenter {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private static NotificationCenter mSharedInstance = null;
    private Context mContext = null;

    public NotificationCenter(Context context) {
        this.mContext = context;
    }

    public static NotificationCenter getSharedInstance() {
        return getSharedInstance(McDonalds.getContext());
    }

    public static NotificationCenter getSharedInstance(Context context) {
        if (mSharedInstance == null) {
            mSharedInstance = new NotificationCenter(context);
        }
        return mSharedInstance;
    }

    public static void setSharedInstance(NotificationCenter notificationCenter) {
        mSharedInstance = notificationCenter;
    }

    public void postNotification(String notificationName) {
        if (this.mContext != null) {
            LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(new Intent(notificationName));
        }
    }

    public void postNotification(String notificationName, Bundle extras) {
        if (this.mContext != null) {
            Intent notificationIntent = new Intent(notificationName);
            notificationIntent.putExtras(extras);
            LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(notificationIntent);
        }
    }

    public BroadcastReceiver addObserver(String notificationName, BroadcastReceiver receiver) {
        if (this.mContext != null) {
            LocalBroadcastManager.getInstance(this.mContext).registerReceiver(receiver, new IntentFilter(notificationName));
        }
        return receiver;
    }

    public void removeObserver(BroadcastReceiver receiver) {
        if (this.mContext != null) {
            LocalBroadcastManager.getInstance(this.mContext).unregisterReceiver(receiver);
        }
    }
}
