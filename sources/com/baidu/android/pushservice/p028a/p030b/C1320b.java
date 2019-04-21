package com.baidu.android.pushservice.p028a.p030b;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import com.baidu.android.pushservice.p036h.C1425a;

/* renamed from: com.baidu.android.pushservice.a.b.b */
public class C1320b extends C1318f {
    /* renamed from: d */
    private final RemoteViews f4684d;

    C1320b(RemoteViews remoteViews, RemoteViews remoteViews2, Context context) {
        super(remoteViews, context);
        this.f4684d = remoteViews2;
        this.f4680a.bigContentView = remoteViews2;
    }

    /* renamed from: a */
    public void mo13571a(Intent intent) {
        int i = 0;
        String stringExtra = intent.getStringExtra("action_type");
        try {
            i = Long.valueOf(System.currentTimeMillis()).intValue();
        } catch (Exception e) {
            C1425a.m6444e("AdvancedPicNotification", "error : " + e.getMessage());
        }
        PendingIntent service = PendingIntent.getService(this.f4682c, i, intent, 134217728);
        if (stringExtra.equals("01")) {
            super.mo13571a(intent);
        } else if (stringExtra.equals("02")) {
            this.f4680a.bigContentView.setOnClickPendingIntent(C1326e.m5987a(this.f4682c, "notification_big_icon"), service);
        }
    }

    /* renamed from: a */
    public void mo13580a(Bitmap bitmap) {
        if (bitmap != null) {
            this.f4684d.setImageViewBitmap(C1326e.m5987a(this.f4682c, "notification_big_icon"), bitmap);
        } else {
            this.f4684d.setImageViewResource(C1326e.m5987a(this.f4682c, "notification_big_icon"), mo13578b());
        }
    }
}
