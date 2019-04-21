package com.baidu.android.pushservice;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.baidu.android.pushservice.message.PublicMsg;
import com.baidu.android.pushservice.p034f.C1403b;
import com.baidu.android.pushservice.p035g.C1419f;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.util.C1578v;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/* renamed from: com.baidu.android.pushservice.c */
public class C1344c {
    /* renamed from: k */
    private static C1344c f4744k;
    /* renamed from: a */
    protected int f4745a;
    /* renamed from: b */
    protected int f4746b;
    /* renamed from: c */
    protected int f4747c;
    /* renamed from: d */
    protected Uri f4748d;
    /* renamed from: e */
    protected long[] f4749e;
    /* renamed from: f */
    protected String f4750f;
    /* renamed from: g */
    protected String f4751g;
    /* renamed from: h */
    protected boolean f4752h = true;
    /* renamed from: i */
    private HashMap<String, Integer> f4753i = new HashMap();
    /* renamed from: j */
    private String f4754j;

    private C1344c() {
    }

    /* renamed from: a */
    public static int m6061a(Context context, String str) {
        return context.getResources().getIdentifier(str, "drawable", context.getPackageName());
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    private Notification m6062a(Context context, String str, String str2, Bitmap bitmap) {
        Bitmap a = C1419f.m6410a(context, bitmap);
        Builder smallIcon = new Builder(context).setSmallIcon(this.f4745a);
        if (m6069a(context)) {
            int b = m6070b(context, "bpush_lapp_notification_layout");
            if (b <= 0) {
                return null;
            }
            int c = m6072c(context, "bpush_lapp_notification_big_icon_imageview");
            int c2 = m6072c(context, "bpush_lapp_notification_title_textview");
            int c3 = m6072c(context, "bpush_lapp_notification_content_textview");
            int c4 = m6072c(context, "bpush_lapp_notification_time_textview");
            String format = new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis()));
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), b);
            remoteViews.setImageViewBitmap(c, a);
            remoteViews.setTextViewText(c2, str);
            remoteViews.setTextViewText(c3, str2);
            remoteViews.setTextViewText(c4, format);
            smallIcon.setContent(remoteViews);
            smallIcon.setSmallIcon(this.f4745a);
            return smallIcon.getNotification();
        }
        smallIcon.setContentTitle(this.f4750f);
        smallIcon.setContentText(this.f4751g);
        smallIcon.setLargeIcon(a).getNotification();
        return smallIcon.getNotification();
    }

    /* renamed from: a */
    public static C1344c m6064a() {
        if (f4744k == null) {
            f4744k = new C1344c();
        }
        return f4744k;
    }

    /* renamed from: a */
    private void m6067a(Bitmap bitmap, String str) {
        File file = new File(String.format("%s/baidu/hybrid/noti_icons/", new Object[]{Environment.getExternalStorageDirectory().getAbsolutePath()}));
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, str);
        if (file2.exists()) {
            file2.delete();
        }
        try {
            file2.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bitmap.compress(CompressFormat.PNG, 90, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            C1425a.m6444e("LappPushNotificationBuilder", "error " + e.getMessage());
        }
    }

    /* renamed from: a */
    private boolean m6069a(Context context) {
        String toLowerCase = Build.MANUFACTURER.toLowerCase();
        if (TextUtils.isEmpty(toLowerCase) || (!toLowerCase.equals("xiaomi") && !toLowerCase.equals("oppo") && !toLowerCase.equals("lenovo") && !toLowerCase.equals("meizu"))) {
            return false;
        }
        C1425a.m6442c("LappPushNotificationBuilder", " manufaturer: " + toLowerCase + ", use resource layout for lightapp notification");
        return true;
    }

    /* renamed from: b */
    private int m6070b(Context context, String str) {
        return context.getResources().getIdentifier(str, "layout", context.getPackageName());
    }

    /* renamed from: c */
    private int m6072c(Context context, String str) {
        return context.getResources().getIdentifier(str, "id", context.getPackageName());
    }

    /* renamed from: a */
    public void mo13619a(int i) {
        this.f4745a = i;
    }

    /* renamed from: a */
    public void mo13620a(Context context, PendingIntent pendingIntent, PendingIntent pendingIntent2, PublicMsg publicMsg, boolean z) {
        File file;
        int a = C1344c.m6061a(context, "bpush_lapp_notification_status_icon");
        if (a > 0) {
            mo13619a(a);
        } else {
            mo13619a(17301620);
        }
        boolean equals = Environment.getExternalStorageState().equals("mounted");
        if (equals) {
            file = new File(String.format("%s/baidu/hybrid/noti_icons/", new Object[]{Environment.getExternalStorageDirectory().getAbsolutePath()}));
            if (file.exists()) {
                for (File file2 : file2.listFiles()) {
                    if (file2.getName().startsWith(publicMsg.mAppId)) {
                        break;
                    }
                }
            }
        }
        file2 = null;
        if (!equals || VERSION.SDK_INT < 11) {
            Notification notification = new Notification();
            if (this.f4752h) {
                notification.defaults = 0;
            } else {
                notification.defaults = -1;
                if (this.f4747c != 0) {
                    notification.defaults = this.f4747c;
                }
                if (this.f4748d != null) {
                    notification.sound = this.f4748d;
                }
                if (this.f4749e != null) {
                    notification.vibrate = this.f4749e;
                }
            }
            if (this.f4745a != 0) {
                notification.icon = this.f4745a;
            }
            if (this.f4746b != 0) {
                notification.flags = this.f4746b;
            }
            notification.setLatestEventInfo(context, this.f4750f, this.f4751g, pendingIntent);
            if (notification != null) {
                NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                notification.contentIntent = pendingIntent;
                notification.deleteIntent = pendingIntent2;
                if (z) {
                    notificationManager.notify(C1578v.m7091b(publicMsg.mAppId), notification);
                    return;
                } else {
                    notificationManager.notify(C1578v.m7091b(publicMsg.mMsgId), notification);
                    return;
                }
            }
            return;
        }
        final PublicMsg publicMsg2 = publicMsg;
        final Context context2 = context;
        final PendingIntent pendingIntent3 = pendingIntent;
        final PendingIntent pendingIntent4 = pendingIntent2;
        final boolean z2 = z;
        new Thread(new Runnable() {
            public void run() {
                IOException e;
                Object obj;
                Throwable th;
                Closeable closeable = null;
                try {
                    Bitmap decodeStream;
                    HashMap b = C1578v.m7093b();
                    if (b != null) {
                        C1344c.this.f4753i = b;
                    } else {
                        C1344c.this.f4753i.clear();
                    }
                    if (C1344c.this.f4753i.containsKey(publicMsg2.mAppId)) {
                        C1344c.this.f4753i.put(publicMsg2.mAppId, Integer.valueOf(((Integer) C1344c.this.f4753i.get(publicMsg2.mAppId)).intValue() + 1));
                    } else {
                        C1344c.this.f4753i.put(publicMsg2.mAppId, Integer.valueOf(1));
                    }
                    C1578v.m7078a(C1344c.this.f4753i);
                    if (file2 != null) {
                        FileInputStream fileInputStream = new FileInputStream(file2);
                        try {
                            closeable = fileInputStream;
                            decodeStream = BitmapFactoryInstrumentation.decodeStream(fileInputStream);
                        } catch (IOException e2) {
                            e = e2;
                            obj = fileInputStream;
                            try {
                                C1425a.m6444e("LappPushNotificationBuilder", "error " + e.getMessage());
                                C1403b.m6265a(closeable);
                                return;
                            } catch (Throwable th2) {
                                th = th2;
                                C1403b.m6265a(closeable);
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            obj = fileInputStream;
                            C1403b.m6265a(closeable);
                            throw th;
                        }
                    }
                    if (!TextUtils.isEmpty(C1344c.this.f4754j)) {
                        try {
                            decodeStream = BitmapFactoryInstrumentation.decodeStream(new URL(C1344c.this.f4754j).openStream());
                        } catch (MalformedURLException e3) {
                            C1425a.m6444e("LappPushNotificationBuilder", "error " + e3.getMessage());
                            decodeStream = null;
                        } catch (IOException e4) {
                            C1425a.m6444e("LappPushNotificationBuilder", "error " + e4.getMessage());
                        }
                    }
                    decodeStream = null;
                    if (decodeStream != null) {
                        Notification a = C1344c.this.m6062a(context2, C1344c.this.f4750f, C1344c.this.f4751g, decodeStream);
                        if (a == null) {
                            C1344c.this.m6067a(decodeStream, publicMsg2.mAppId + ".bdi");
                            C1403b.m6265a(closeable);
                            return;
                        }
                        if (C1344c.this.f4746b != 0) {
                            a.flags = C1344c.this.f4746b;
                        }
                        if (C1344c.this.f4752h) {
                            a.defaults = 0;
                        } else {
                            a.defaults = -1;
                            if (C1344c.this.f4747c != 0) {
                                a.defaults = C1344c.this.f4747c;
                            }
                            if (C1344c.this.f4748d != null) {
                                a.sound = C1344c.this.f4748d;
                            }
                            if (C1344c.this.f4749e != null) {
                                a.vibrate = C1344c.this.f4749e;
                            }
                        }
                        a.contentIntent = pendingIntent3;
                        a.deleteIntent = pendingIntent4;
                        NotificationManager notificationManager = (NotificationManager) context2.getSystemService("notification");
                        if (z2) {
                            notificationManager.notify(publicMsg2.mAppId, C1578v.m7091b(publicMsg2.mAppId), a);
                        } else {
                            notificationManager.notify(publicMsg2.mMsgId, C1578v.m7091b(publicMsg2.mMsgId), a);
                        }
                        C1344c.this.m6067a(decodeStream, publicMsg2.mAppId + ".bdi");
                    }
                    C1403b.m6265a(closeable);
                } catch (IOException e5) {
                    e4 = e5;
                }
            }
        }, "DownNotiIcon").start();
    }

    /* renamed from: a */
    public void mo13621a(String str) {
        this.f4750f = str;
    }

    /* renamed from: a */
    public void mo13622a(boolean z) {
        this.f4752h = z;
    }

    /* renamed from: b */
    public void mo13623b(int i) {
        this.f4746b = i;
    }

    /* renamed from: b */
    public void mo13624b(String str) {
        this.f4751g = str;
    }

    /* renamed from: c */
    public void mo13625c(int i) {
        this.f4747c = i;
    }

    /* renamed from: c */
    public void mo13626c(String str) {
        this.f4754j = str;
    }
}
