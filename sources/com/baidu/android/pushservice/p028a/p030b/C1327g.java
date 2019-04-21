package com.baidu.android.pushservice.p028a.p030b;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import com.baidu.android.pushservice.p036h.C1425a;

/* renamed from: com.baidu.android.pushservice.a.b.g */
public class C1327g implements C1317d {
    /* renamed from: a */
    private final Notification f4704a;
    /* renamed from: b */
    private final Context f4705b;

    public C1327g(Context context, String str, String str2) {
        this.f4705b = context;
        if (VERSION.SDK_INT >= 16) {
            this.f4704a = new Builder(context).setContentTitle(str).setContentText(str2).setSmallIcon(context.getApplicationInfo().icon).setWhen(System.currentTimeMillis()).build();
            return;
        }
        this.f4704a = new Notification(this.f4705b.getApplicationInfo().icon, "", System.currentTimeMillis());
        this.f4704a.setLatestEventInfo(context, str, str2, PendingIntent.getBroadcast(this.f4705b, 0, new Intent(), 0));
    }

    /* renamed from: a */
    public Notification mo13567a() {
        return this.f4704a;
    }

    /* renamed from: a */
    public void mo13568a(int i) {
    }

    /* renamed from: a */
    public void mo13569a(long j) {
        this.f4704a.when = System.currentTimeMillis();
    }

    /* renamed from: a */
    public void mo13570a(PendingIntent pendingIntent) {
        this.f4704a.deleteIntent = pendingIntent;
    }

    /* renamed from: a */
    public void mo13571a(Intent intent) {
        int i = 0;
        try {
            i = Long.valueOf(System.currentTimeMillis()).intValue();
        } catch (Exception e) {
            C1425a.m6440a("SystemNotification", e);
        }
        if (intent.getStringExtra("action_type").equals("01")) {
            this.f4704a.contentIntent = PendingIntent.getBroadcast(this.f4705b, i, intent, 134217728);
        }
    }

    /* renamed from: a */
    public void mo13572a(String str) {
    }

    /* renamed from: b */
    public void mo13573b(int i) {
    }

    /* renamed from: b */
    public void mo13574b(Bitmap bitmap) {
    }

    /* renamed from: b */
    public void mo13575b(String str) {
    }

    /* renamed from: c */
    public void mo13576c(int i) {
    }

    /* renamed from: c */
    public void mo13577c(Bitmap bitmap) {
    }
}
