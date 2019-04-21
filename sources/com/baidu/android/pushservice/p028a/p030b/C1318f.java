package com.baidu.android.pushservice.p028a.p030b;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import com.baidu.android.pushservice.p036h.C1425a;
import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: com.baidu.android.pushservice.a.b.f */
public class C1318f implements C1317d {
    /* renamed from: d */
    private static String f4679d = "NormalNotification";
    /* renamed from: a */
    protected Notification f4680a = new Notification();
    /* renamed from: b */
    protected RemoteViews f4681b;
    /* renamed from: c */
    protected Context f4682c;

    C1318f(RemoteViews remoteViews, Context context) {
        this.f4681b = remoteViews;
        this.f4682c = context;
        this.f4680a.icon = mo13578b();
        this.f4680a.when = System.currentTimeMillis();
        this.f4680a.contentView = this.f4681b;
    }

    /* renamed from: a */
    public Notification mo13567a() {
        return this.f4680a;
    }

    /* renamed from: a */
    public void mo13568a(int i) {
        this.f4681b.setTextColor(C1326e.m5987a(this.f4682c, "notification_content"), i);
    }

    /* renamed from: a */
    public void mo13569a(long j) {
        this.f4681b.setTextViewText(C1326e.m5987a(this.f4682c, "notification_when"), new SimpleDateFormat("HH:mm").format(new Date(j)));
    }

    /* renamed from: a */
    public void mo13570a(PendingIntent pendingIntent) {
        this.f4680a.deleteIntent = pendingIntent;
    }

    /* renamed from: a */
    public void mo13571a(Intent intent) {
        int i = 0;
        try {
            i = Long.valueOf(System.currentTimeMillis()).intValue();
        } catch (Exception e) {
            C1425a.m6440a(f4679d, e);
        }
        if (intent.getStringExtra("action_type").equals("01")) {
            this.f4680a.contentView.setOnClickPendingIntent(C1326e.m5987a(this.f4682c, "content_head_bar"), PendingIntent.getService(this.f4682c, i, intent, 134217728));
        }
    }

    /* renamed from: a */
    public void mo13572a(String str) {
        this.f4681b.setTextViewText(C1326e.m5987a(this.f4682c, "notification_title"), str);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public int mo13578b() {
        return this.f4682c.getApplicationInfo().icon;
    }

    /* renamed from: b */
    public void mo13573b(int i) {
        this.f4681b.setTextColor(C1326e.m5987a(this.f4682c, "notification_title"), i);
    }

    /* renamed from: b */
    public void mo13574b(Bitmap bitmap) {
        if (bitmap != null) {
            this.f4681b.setImageViewBitmap(C1326e.m5987a(this.f4682c, "notification_icon"), bitmap);
        } else {
            this.f4681b.setImageViewResource(C1326e.m5987a(this.f4682c, "notification_icon"), mo13578b());
        }
    }

    /* renamed from: b */
    public void mo13575b(String str) {
        this.f4681b.setTextViewText(C1326e.m5987a(this.f4682c, "notification_content"), str);
    }

    /* renamed from: c */
    public void mo13576c(int i) {
        this.f4681b.setInt(C1326e.m5987a(this.f4682c, "notification_background"), "setBackgroundColor", i);
    }

    /* renamed from: c */
    public void mo13577c(Bitmap bitmap) {
        if (bitmap != null) {
            this.f4681b.setImageViewBitmap(C1326e.m5987a(this.f4682c, "notification_img"), bitmap);
        } else {
            this.f4681b.setViewVisibility(C1326e.m5987a(this.f4682c, "notification_img"), 4);
        }
    }
}
