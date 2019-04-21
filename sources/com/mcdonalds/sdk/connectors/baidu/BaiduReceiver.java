package com.mcdonalds.sdk.connectors.baidu;

import android.content.Context;
import android.os.Bundle;
import com.baidu.android.pushservice.PushMessageReceiver;
import com.mcdonalds.sdk.connectors.ConnectorManager;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.notification.NotificationModule;
import com.mcdonalds.sdk.modules.notification.PushConstants;
import java.util.List;

public class BaiduReceiver extends PushMessageReceiver {
    public void onBind(Context context, int errorCode, String appid, String userId, String channelId, String requestId) {
        ((BaiduConnector) ConnectorManager.getConnector(BaiduConnector.NAME)).setRegistrationId(channelId);
    }

    public void onUnbind(Context context, int i, String s) {
    }

    public void onSetTags(Context context, int i, List<String> list, List<String> list2, String s) {
    }

    public void onDelTags(Context context, int i, List<String> list, List<String> list2, String s) {
    }

    public void onListTags(Context context, int i, List<String> list, String s) {
    }

    public void onMessage(Context context, String message, String customContentString) {
        Bundle data = new Bundle();
        data.putString(PushConstants.TITLE, "McDonald's");
        data.putString(PushConstants.TEXT, message);
        NotificationModule notificationModule = (NotificationModule) ModuleManager.getModule("notification");
        if (notificationModule != null) {
            notificationModule.notifyNotificationReceived(context, data);
        }
    }

    public void onNotificationClicked(Context context, String s, String s1, String s2) {
    }

    public void onNotificationArrived(Context context, String s, String s1, String s2) {
    }
}
