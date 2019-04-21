package com.kochava.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.concurrent.CountDownLatch;

public final class ReferralReceiver extends BroadcastReceiver {
    @NonNull
    /* renamed from: a */
    static final CountDownLatch f6537a = new CountDownLatch(1);

    public final void onReceive(@Nullable Context context, @Nullable Intent intent) {
        String str = "onReceive";
        if (!(context == null || intent == null)) {
            try {
                if ("com.android.vending.INSTALL_REFERRER".equals(intent.getAction())) {
                    str = intent.getStringExtra("referrer");
                    if (str == null || str.trim().isEmpty()) {
                        Tracker.m7517a(2, "RRC", "onReceive", "Invalid Referrer");
                        return;
                    }
                    Tracker.m7517a(3, "RRC", "onReceive", str);
                    SharedPreferences sharedPreferences = context.getSharedPreferences("kosp", 0);
                    if (sharedPreferences.getString("referrer_source", null) == null) {
                        sharedPreferences.edit().putString("referrer_source", "STR::gplay").putString("referrer", "STR::" + str).apply();
                    } else {
                        Tracker.m7517a(2, "RRC", "onReceive", "Skip: Previous referrer exists");
                    }
                    f6537a.countDown();
                    return;
                }
            } catch (Throwable th) {
                Tracker.m7517a(1, "RRC", "onReceive", "Unknown error when receiving install referrer", th);
            }
        }
        Tracker.m7517a(2, "RRC", "onReceive", "Invalid Intent/Action");
    }
}
