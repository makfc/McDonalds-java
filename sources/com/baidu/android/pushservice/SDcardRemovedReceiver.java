package com.baidu.android.pushservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.util.C1568q;

public class SDcardRemovedReceiver extends BroadcastReceiver {
    /* renamed from: a */
    private static String f4655a = "SDRev";

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.MEDIA_BAD_REMOVAL") || intent.getAction().equals("android.intent.action.MEDIA_REMOVED")) {
            C1425a.m6442c(f4655a, "sdcard removed");
            C1568q.m6996a();
        }
    }
}
