package com.baidu.android.pushservice;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.util.C1578v;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/* renamed from: com.baidu.android.pushservice.d */
public class C1358d {
    /* renamed from: a */
    protected int f4786a;
    /* renamed from: b */
    protected int f4787b;
    /* renamed from: c */
    protected int f4788c;
    /* renamed from: d */
    protected Uri f4789d;
    /* renamed from: e */
    protected long[] f4790e;
    /* renamed from: f */
    protected String f4791f;
    /* renamed from: g */
    protected String f4792g;
    /* renamed from: h */
    protected boolean f4793h = false;
    /* renamed from: i */
    private final String f4794i;

    public C1358d(String str) {
        this.f4794i = str;
    }

    /* renamed from: a */
    private Bitmap m6162a(Drawable drawable, Context context) {
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Config.ARGB_8888 : Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        float f = context.getResources().getDisplayMetrics().density;
        drawable.setBounds(0, 0, (int) (48.0f * f), (int) (f * 48.0f));
        drawable.draw(canvas);
        return createBitmap;
    }

    /* renamed from: a */
    public void mo13710a(int i) {
        this.f4786a = i;
    }

    /* renamed from: a */
    public void mo13711a(final Context context, final PendingIntent pendingIntent, final String str) {
        int a = C1344c.m6061a(context, "bpush_lapp_notification_status_icon");
        if (a > 0) {
            mo13710a(a);
        } else {
            mo13710a(17301620);
        }
        if (VERSION.SDK_INT < 11 || TextUtils.isEmpty(this.f4794i)) {
            Notification notification = new Notification();
            if (this.f4793h) {
                notification.defaults = 0;
            } else {
                notification.defaults = -1;
                if (this.f4788c != 0) {
                    notification.defaults = this.f4788c;
                }
                if (this.f4789d != null) {
                    notification.sound = this.f4789d;
                }
                if (this.f4790e != null) {
                    notification.vibrate = this.f4790e;
                }
            }
            if (this.f4786a != 0) {
                notification.icon = this.f4786a;
            }
            if (this.f4787b != 0) {
                notification.flags = this.f4787b;
            }
            notification.setLatestEventInfo(context, this.f4791f, this.f4792g, pendingIntent);
            if (notification != null) {
                NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                notification.contentIntent = pendingIntent;
                notificationManager.notify(C1578v.m7091b(str), notification);
                return;
            }
            return;
        }
        new Thread(new Runnable() {
            public void run() {
                try {
                    Notification notification = new Builder(context).setContentTitle(C1358d.this.f4791f).setContentText(C1358d.this.f4792g).setSmallIcon(C1358d.this.f4786a).setLargeIcon(C1358d.this.m6162a(Drawable.createFromStream(new URL(C1358d.this.f4794i).openStream(), null), context)).getNotification();
                    if (C1358d.this.f4787b != 0) {
                        notification.flags = C1358d.this.f4787b;
                    }
                    if (C1358d.this.f4793h) {
                        notification.defaults = 0;
                    } else {
                        notification.defaults = -1;
                        if (C1358d.this.f4788c != 0) {
                            notification.defaults = C1358d.this.f4788c;
                        }
                        if (C1358d.this.f4789d != null) {
                            notification.sound = C1358d.this.f4789d;
                        }
                        if (C1358d.this.f4790e != null) {
                            notification.vibrate = C1358d.this.f4790e;
                        }
                    }
                    notification.contentIntent = pendingIntent;
                    ((NotificationManager) context.getSystemService("notification")).notify(str, 0, notification);
                } catch (MalformedURLException e) {
                    C1425a.m6440a("NewPushNotificationBuilder", e);
                } catch (IOException e2) {
                }
            }
        }, "DownNotiIcon").start();
    }

    /* renamed from: a */
    public void mo13712a(String str) {
        this.f4791f = str;
    }

    /* renamed from: a */
    public void mo13713a(boolean z) {
        this.f4793h = z;
    }

    /* renamed from: b */
    public void mo13714b(int i) {
        this.f4787b = i;
    }

    /* renamed from: b */
    public void mo13715b(String str) {
        this.f4792g = str;
    }

    /* renamed from: c */
    public void mo13716c(int i) {
        this.f4788c = i;
    }
}
