package com.mcdonalds.sdk.connectors.baidu;

import android.content.Context;
import com.baidu.android.pushservice.PushManager;
import com.mcdonalds.sdk.connectors.BaseConnector;
import com.mcdonalds.sdk.connectors.NotificationConnector;
import com.mcdonalds.sdk.services.configuration.Configuration;

public class BaiduConnector extends BaseConnector implements NotificationConnector {
    public static final String NAME = "baidu";
    Context mContext;
    String mRegistrationId;

    public BaiduConnector(Context context) {
        this.mContext = context;
    }

    public synchronized void setRegistrationId(String registrationId) {
        this.mRegistrationId = registrationId;
        notify();
    }

    public synchronized String register() {
        String str;
        String apiKey = Configuration.getSharedInstance().getStringForKey("connectors.Baidu.apiKey");
        if (apiKey != null) {
            PushManager.startWork(this.mContext, 0, apiKey);
            try {
                wait();
                str = this.mRegistrationId;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        str = "";
        return str;
    }

    public void unregister() {
    }
}
