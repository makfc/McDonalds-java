package com.baidu.android.pushservice.p028a.p030b;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import com.baidu.android.pushservice.p036h.C1425a;
import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: com.baidu.android.pushservice.a.b.a */
public class C1319a extends C1318f {
    /* renamed from: d */
    private final RemoteViews f4683d;

    C1319a(RemoteViews remoteViews, RemoteViews remoteViews2, Context context) {
        super(remoteViews, context);
        this.f4683d = remoteViews2;
        this.f4680a.bigContentView = remoteViews2;
    }

    /* renamed from: a */
    public void mo13568a(int i) {
        super.mo13568a(i);
        this.f4683d.setTextColor(C1326e.m5987a(this.f4682c, "notification_content"), i);
    }

    /* renamed from: a */
    public void mo13569a(long j) {
        super.mo13569a(j);
        this.f4683d.setTextViewText(C1326e.m5987a(this.f4682c, "notification_when"), new SimpleDateFormat("HH:mm").format(new Date(j)));
    }

    /* renamed from: a */
    public void mo13571a(Intent intent) {
        int i = 0;
        try {
            i = Long.valueOf(System.currentTimeMillis()).intValue();
        } catch (Exception e) {
            C1425a.m6440a("AdvancedBigStyleNotification", e);
        }
        String stringExtra = intent.getStringExtra("action_type");
        PendingIntent service = PendingIntent.getService(this.f4682c, i, intent, 134217728);
        if (stringExtra.equals("01")) {
            super.mo13571a(intent);
            this.f4680a.bigContentView.setOnClickPendingIntent(C1326e.m5987a(this.f4682c, "content_head_bar"), service);
        } else if (stringExtra.equals("02")) {
            this.f4680a.bigContentView.setOnClickPendingIntent(C1326e.m5987a(this.f4682c, "notification_big_icon"), service);
        } else if (stringExtra.equals("03")) {
            this.f4680a.bigContentView.setOnClickPendingIntent(C1326e.m5987a(this.f4682c, "notification_detail_btn"), service);
        } else if (stringExtra.equals("04")) {
            this.f4680a.bigContentView.setOnClickPendingIntent(C1326e.m5987a(this.f4682c, "notification_download_btn"), service);
        }
    }

    /* renamed from: a */
    public void mo13579a(Bitmap bitmap) {
        if (bitmap != null) {
            this.f4683d.setImageViewBitmap(C1326e.m5987a(this.f4682c, "notification_big_icon"), bitmap);
        } else {
            this.f4683d.setImageViewResource(C1326e.m5987a(this.f4682c, "notification_big_icon"), mo13578b());
        }
    }

    /* renamed from: a */
    public void mo13572a(String str) {
        super.mo13572a(str);
        this.f4683d.setTextViewText(C1326e.m5987a(this.f4682c, "notification_title"), str);
    }

    /* renamed from: b */
    public void mo13573b(int i) {
        super.mo13573b(i);
        this.f4683d.setTextColor(C1326e.m5987a(this.f4682c, "notification_title"), i);
    }

    /* renamed from: b */
    public void mo13574b(Bitmap bitmap) {
        super.mo13574b(bitmap);
        if (bitmap != null) {
            this.f4683d.setImageViewBitmap(C1326e.m5987a(this.f4682c, "notification_icon"), bitmap);
        } else {
            this.f4683d.setImageViewResource(C1326e.m5987a(this.f4682c, "notification_icon"), mo13578b());
        }
    }

    /* renamed from: b */
    public void mo13575b(String str) {
        super.mo13575b(str);
        this.f4683d.setTextViewText(C1326e.m5987a(this.f4682c, "notification_content"), str);
    }

    /* renamed from: c */
    public void mo13576c(int i) {
        super.mo13576c(i);
        this.f4683d.setInt(C1326e.m5987a(this.f4682c, "notification_background"), "setBackgroundColor", i);
    }

    /* renamed from: c */
    public void mo13577c(Bitmap bitmap) {
        super.mo13577c(bitmap);
        if (bitmap != null) {
            this.f4683d.setImageViewBitmap(C1326e.m5987a(this.f4682c, "notification_img"), bitmap);
        } else {
            this.f4683d.setViewVisibility(C1326e.m5987a(this.f4682c, "notification_img"), 4);
        }
    }
}
