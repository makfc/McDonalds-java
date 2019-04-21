package com.baidu.android.pushservice;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.baidu.android.pushservice.config.ModeConfig;
import com.baidu.android.pushservice.message.PublicMsg;
import com.baidu.android.pushservice.message.p040a.C1488f;
import com.baidu.android.pushservice.p026j.C1281c;
import com.baidu.android.pushservice.p026j.C1462d;
import com.baidu.android.pushservice.p028a.p029a.C1312e;
import com.baidu.android.pushservice.p028a.p030b.C1325c;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p036h.C1426b;
import com.baidu.android.pushservice.richmedia.C1303f;
import com.baidu.android.pushservice.richmedia.C1524a;
import com.baidu.android.pushservice.richmedia.C1525b;
import com.baidu.android.pushservice.richmedia.C1527c;
import com.baidu.android.pushservice.richmedia.C1527c.C1526a;
import com.baidu.android.pushservice.richmedia.C1529d;
import com.baidu.android.pushservice.richmedia.C1530e;
import com.baidu.android.pushservice.richmedia.MediaViewActivity;
import com.baidu.android.pushservice.util.C1533a;
import com.baidu.android.pushservice.util.C1568q;
import com.baidu.android.pushservice.util.C1568q.C1563h;
import com.baidu.android.pushservice.util.C1577u;
import com.baidu.android.pushservice.util.C1578v;
import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@SuppressLint({"NewApi"})
public class PushServiceReceiver extends BroadcastReceiver {
    /* renamed from: a */
    private final ReentrantLock f4648a = new ReentrantLock();

    /* renamed from: com.baidu.android.pushservice.PushServiceReceiver$a */
    private static class C1304a implements C1303f {
        /* renamed from: a */
        public Context f4641a = null;
        /* renamed from: b */
        public RemoteViews f4642b = null;
        /* renamed from: c */
        public int f4643c = 0;
        /* renamed from: d */
        public int f4644d = 0;
        /* renamed from: e */
        public int f4645e = 0;
        /* renamed from: f */
        public int f4646f = 0;
        /* renamed from: g */
        NotificationManager f4647g;

        /* renamed from: com.baidu.android.pushservice.PushServiceReceiver$a$1 */
        class C13011 implements Runnable {
            C13011() {
            }

            public void run() {
                Toast.makeText(C1304a.this.f4641a, "富媒体推送没有声明必须的Activity，请检查！", 1).show();
            }
        }

        C1304a(Context context, PublicMsg publicMsg) {
            this.f4641a = context;
            this.f4647g = (NotificationManager) context.getSystemService("notification");
        }

        /* renamed from: a */
        public void mo13546a(C1524a c1524a) {
            Resources resources = this.f4641a.getResources();
            String packageName = this.f4641a.getPackageName();
            if (resources != null) {
                int identifier = resources.getIdentifier("bpush_download_progress", "layout", packageName);
                this.f4642b = new RemoteViews(this.f4641a.getPackageName(), identifier);
                if (identifier != 0) {
                    this.f4643c = resources.getIdentifier("bpush_download_progress", "id", packageName);
                    this.f4644d = resources.getIdentifier("bpush_progress_percent", "id", packageName);
                    this.f4645e = resources.getIdentifier("bpush_progress_text", "id", packageName);
                    this.f4646f = resources.getIdentifier("bpush_download_icon", "id", packageName);
                    this.f4642b.setImageViewResource(this.f4646f, this.f4641a.getApplicationInfo().icon);
                }
            }
        }

        @SuppressLint({"NewApi"})
        /* renamed from: a */
        public void mo13547a(C1524a c1524a, C1525b c1525b) {
            String c = c1524a.f5340d.mo14050c();
            if (c1525b.f5342a != c1525b.f5343b && this.f4642b != null) {
                int i = (int) ((((double) c1525b.f5342a) * 100.0d) / ((double) c1525b.f5343b));
                this.f4642b.setTextViewText(this.f4644d, i + "%");
                this.f4642b.setTextViewText(this.f4645e, "正在下载富媒体:" + c);
                this.f4642b.setProgressBar(this.f4643c, 100, i, false);
                Notification build = VERSION.SDK_INT >= 16 ? new Builder(this.f4641a).setSmallIcon(17301633).setWhen(System.currentTimeMillis()).build() : new Notification(17301633, null, System.currentTimeMillis());
                build.contentView = this.f4642b;
                build.contentIntent = PendingIntent.getActivity(this.f4641a, 0, new Intent(), 0);
                build.flags |= 32;
                build.flags |= 2;
                this.f4647g.notify(c, 0, build);
            }
        }

        /* renamed from: a */
        public void mo13548a(C1524a c1524a, C1530e c1530e) {
            String c = c1524a.f5340d.mo14050c();
            this.f4647g.cancel(c, 0);
            C1563h a = C1568q.m6993a(this.f4641a, c);
            if (a != null && a.f5480i == C1524a.f5335f) {
                String str = a.f5476e;
                c = a.f5477f;
                if (!TextUtils.isEmpty(c) && c.length() > 0) {
                    c = str + "/" + c.substring(0, c.lastIndexOf(".")) + "/index.html";
                    Intent intent = new Intent();
                    intent.setClass(this.f4641a, MediaViewActivity.class);
                    intent.setData(Uri.fromFile(new File(c)));
                    intent.addFlags(268435456);
                    try {
                        this.f4641a.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        new Handler(Looper.getMainLooper()).post(new C13011());
                        C1426b.m6446a("PushServiceReceiver", e, this.f4641a);
                    }
                }
            }
        }

        /* renamed from: a */
        public void mo13549a(C1524a c1524a, Throwable th) {
            if (this.f4641a != null) {
                final String c = c1524a.f5340d.mo14050c();
                this.f4647g.cancel(c, 0);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        Toast makeText = Toast.makeText(C1304a.this.f4641a, "下载富媒体" + Uri.parse(c).getAuthority() + "失败", 1);
                        makeText.setGravity(17, 0, 0);
                        makeText.show();
                    }
                });
            }
        }

        /* renamed from: b */
        public void mo13550b(C1524a c1524a) {
            this.f4647g.cancel(c1524a.f5340d.mo14050c(), 0);
        }
    }

    /* renamed from: a */
    private Intent m5861a(Context context, Intent intent, String str) {
        if (C1328a.m6003a() >= (short) 32) {
            intent.setFlags(32);
            intent.setAction(str);
            intent.setClassName(context.getPackageName(), "com.baidu.android.pushservice.CommandService");
            intent.putExtra("command_type", "reflect_receiver");
        }
        return intent;
    }

    /* renamed from: a */
    private static Intent m5862a(String str) {
        Intent intent;
        Throwable e;
        try {
            intent = new Intent();
            try {
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                intent.setFlags(268435456);
            } catch (Exception e2) {
                e = e2;
                C1425a.m6440a("PushServiceReceiver", e);
                return intent;
            }
        } catch (Exception e3) {
            Throwable th = e3;
            intent = null;
            e = th;
            C1425a.m6440a("PushServiceReceiver", e);
            return intent;
        }
        return intent;
    }

    /* renamed from: a */
    public static void m5864a(Context context, PublicMsg publicMsg) {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            Toast makeText = Toast.makeText(context, "请插入SD卡", 1);
            makeText.setGravity(17, 0, 0);
            makeText.show();
        } else if (publicMsg != null && publicMsg.mUrl != null) {
            Uri parse = Uri.parse(publicMsg.mUrl);
            String path = parse.getPath();
            if (!TextUtils.isEmpty(parse.getPath())) {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "baidu/pushservice/files" + "/" + parse.getAuthority() + "/" + path.substring(0, path.lastIndexOf(47)));
                C1425a.m6442c("PushServiceReceiver", "<<< download url " + parse.toString());
                C1527c a = C1529d.m6882a(C1526a.REQ_TYPE_GET_ZIP, parse.toString());
                a.f5346a = publicMsg.mPkgName;
                a.f5347b = file.getAbsolutePath();
                a.f5348c = publicMsg.mTitle;
                a.f5349d = publicMsg.mDescription;
                new C1524a(context, new C1304a(context, publicMsg), a).start();
            }
        }
    }

    /* renamed from: a */
    private static void m5865a(Context context, String str, PublicMsg publicMsg) {
        Intent intent = new Intent();
        intent.setPackage(str);
        intent.putExtra("method", "com.baidu.android.pushservice.action.notification.ARRIVED");
        intent.putExtra("notification_title", publicMsg.mTitle);
        intent.putExtra("notification_content", publicMsg.mCustomContent);
        intent.putExtra("extra_extra_custom_content", publicMsg.mDescription);
        C1578v.m7094b(context, intent, "com.baidu.android.pushservice.action.RECEIVE", publicMsg.mPkgName);
    }

    /* renamed from: a */
    public static void m5866a(Context context, String str, String str2, PublicMsg publicMsg) {
        try {
            Intent parseUri;
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (!TextUtils.isEmpty(publicMsg.mPkgContent)) {
                try {
                    parseUri = Intent.parseUri(publicMsg.mPkgContent, 1);
                    C1425a.m6442c("PushServiceReceiver", "Open Special Activity   " + publicMsg.mPkgContent);
                } catch (URISyntaxException e) {
                    C1425a.m6440a("PushServiceReceiver", e);
                    parseUri = m5870b(context, publicMsg);
                }
            } else if (TextUtils.isEmpty(publicMsg.mUrl)) {
                parseUri = m5870b(context, publicMsg);
                C1425a.m6442c("PushServiceReceiver", "Start Application MainActivity");
            } else {
                parseUri = m5862a(publicMsg.mUrl);
                C1425a.m6442c("PushServiceReceiver", "Start URL   " + publicMsg.mUrl);
            }
            if (parseUri != null) {
                PendingIntent activity = PendingIntent.getActivity(context, 0, parseUri, 0);
                Notification a = C1394e.m6248a(context, 0, 7, publicMsg.mTitle, publicMsg.mDescription, false);
                if (a != null) {
                    a.contentIntent = activity;
                    notificationManager.notify(System.currentTimeMillis() + "", 0, a);
                }
            }
        } catch (Exception e2) {
            C1425a.m6440a("PushServiceReceiver", e2);
        }
    }

    /* renamed from: b */
    private static Intent m5870b(Context context, PublicMsg publicMsg) {
        Intent intent;
        Throwable e;
        try {
            intent = new Intent();
            try {
                intent.setClassName(context.getPackageName(), publicMsg.getLauncherActivityName(context, context.getPackageName()));
                intent.setFlags(268435456);
            } catch (Exception e2) {
                e = e2;
                C1425a.m6440a("PushServiceReceiver", e);
                return intent;
            }
        } catch (Exception e3) {
            Throwable th = e3;
            intent = null;
            e = th;
            C1425a.m6440a("PushServiceReceiver", e);
            return intent;
        }
        return intent;
    }

    /* renamed from: b */
    private static void m5871b(Context context, String str, String str2, PublicMsg publicMsg, String str3) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        Intent intent = new Intent("com.baidu.android.pushservice.action.media.CLICK");
        intent.setClassName(str, str2);
        intent.setData(Uri.parse("content://" + publicMsg.mMsgId));
        intent.putExtra("public_msg", publicMsg);
        intent.putExtra("app_id", str3);
        PendingIntent service = PendingIntent.getService(context, 0, intent, 0);
        C1425a.m6442c("PushServiceReceiver", "Set click broadcast, pkgname: " + publicMsg.mPkgName + " action: " + "com.baidu.android.pushservice.action.media.CLICK");
        Intent intent2 = new Intent();
        intent2.setClassName(str, str2);
        intent2.setAction("com.baidu.android.pushservice.action.media.DELETE");
        intent2.setData(Uri.parse("content://" + publicMsg.mMsgId));
        intent2.putExtra("public_msg", publicMsg);
        intent2.putExtra("app_id", str3);
        PendingIntent service2 = PendingIntent.getService(context, 0, intent2, 0);
        Notification a = C1394e.m6249a(context, 8888, publicMsg.mTitle, "富媒体消息：点击后下载与查看", C1578v.m7136o(context, publicMsg.mPkgName));
        a.contentIntent = service;
        a.deleteIntent = service2;
        notificationManager.notify(publicMsg.mMsgId, 0, a);
    }

    /* renamed from: b */
    private void m5872b(Context context, String str, String str2, PublicMsg publicMsg, String str3, String str4) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            C1425a.m6444e("PushServiceReceiver", "showAdvertiseNotifiation pkgName is invalid");
            return;
        }
        Intent a = m5861a(applicationContext, new Intent(), "com.baidu.android.pushservice.action.adnotification.ADDELETE");
        a.putExtra("app_id", str4);
        a.putExtra("msg_id", str3);
        a.putExtra("ad_msg", publicMsg);
        a.putExtra("action_type", "05");
        a.putExtra("click_url", publicMsg.mUrl);
        a.putExtra("advertise_Style", String.valueOf(publicMsg.mAdvertiseStyle));
        int i = 0;
        try {
            i = Long.valueOf(System.currentTimeMillis()).intValue();
        } catch (Exception e) {
            C1425a.m6444e("PushServiceReceiver", "error : " + e.getMessage());
        }
        PendingIntent service = PendingIntent.getService(context, i, a, 134217728);
        if (publicMsg.mAdvertiseStyle == 1 || publicMsg.mAdvertiseStyle == 2 || publicMsg.mAdvertiseStyle == 3) {
            Intent a2 = m5861a(applicationContext, new Intent(), "com.baidu.android.pushservice.action.adnotification.ADCLICK");
            a2.putExtra("app_id", str4);
            a2.putExtra("msg_id", str3);
            a2.putExtra("ad_msg", publicMsg);
            a2.putExtra("action_type", "01");
            a2.putExtra("click_url", publicMsg.mAdvertiseClickUrl);
            a2.putExtra("advertise_Style", String.valueOf(publicMsg.mAdvertiseStyle));
            C1325c.m5972a(publicMsg.mAdvertiseStyle, applicationContext, publicMsg.mDescription, publicMsg.mTitle, publicMsg.mAdvertiseLargeIconUrl, publicMsg.mAdvertiseSmallIconUrl, service, a2);
            C1425a.m6442c("PushServiceReceiver", "show normal advertise  notification, msgid = " + str3 + "  appid = " + str4 + "  Title = " + publicMsg.mTitle + "  Description = " + publicMsg.mDescription + "  largeIconUrl = " + publicMsg.mAdvertiseLargeIconUrl + "  smallIconUrl = " + publicMsg.mAdvertiseSmallIconUrl);
            C1578v.m7095b("pushadvertise:  show normal  advertise notification", context);
        } else if (publicMsg.mAdvertiseStyle == 4 || publicMsg.mAdvertiseStyle == 5) {
            Intent a3 = m5861a(applicationContext, new Intent(), "com.baidu.android.pushservice.action.adnotification.ADCLICK");
            a3.putExtra("app_id", str4);
            a3.putExtra("msg_id", str3);
            a3.putExtra("ad_msg", publicMsg);
            a3.putExtra("action_type", "01");
            a3.putExtra("click_url", publicMsg.mAdvertiseClickUrl);
            a3.putExtra("advertise_Style", String.valueOf(publicMsg.mAdvertiseStyle));
            Intent a4 = m5861a(applicationContext, new Intent(), "com.baidu.android.pushservice.action.adnotification.ADCLICK");
            a4.putExtra("app_id", str4);
            a4.putExtra("msg_id", str3);
            a4.putExtra("ad_msg", publicMsg);
            a4.putExtra("action_type", "02");
            a4.putExtra("click_url", publicMsg.mAdvertiseBigPictureClickUrl);
            a4.putExtra("advertise_Style", String.valueOf(publicMsg.mAdvertiseStyle));
            a = m5861a(applicationContext, new Intent(), "com.baidu.android.pushservice.action.adnotification.ADCLICK");
            a.putExtra("app_id", str4);
            a.putExtra("msg_id", str3);
            a.putExtra("ad_msg", publicMsg);
            a.putExtra("action_type", "03");
            a.putExtra("click_url", publicMsg.mAdvertiseDetailClickUrl);
            a.putExtra("advertise_Style", String.valueOf(publicMsg.mAdvertiseStyle));
            Intent a5 = m5861a(applicationContext, new Intent(), "com.baidu.android.pushservice.action.adnotification.ADCLICK");
            a5.putExtra("app_id", str4);
            a5.putExtra("msg_id", str3);
            a5.putExtra("ad_msg", publicMsg);
            a5.putExtra("action_type", "04");
            a5.putExtra("click_url", publicMsg.mAdvertiseDownloadClickUrl);
            a5.putExtra("advertise_Style", String.valueOf(publicMsg.mAdvertiseStyle));
            if (publicMsg.mAdvertiseStyle == 4) {
                C1325c.m5973a(4, applicationContext, publicMsg.mDescription, publicMsg.mTitle, publicMsg.mAdvertiseLargeIconUrl, publicMsg.mAdvertiseSmallIconUrl, publicMsg.mAdvertiseBigPictureUrl, service, a3, a4);
            } else if (publicMsg.mAdvertiseStyle == 5) {
                C1325c.m5973a(5, applicationContext, publicMsg.mDescription, publicMsg.mTitle, publicMsg.mAdvertiseLargeIconUrl, publicMsg.mAdvertiseSmallIconUrl, publicMsg.mAdvertiseBigPictureUrl, service, a3, a, a5, a4);
            }
            C1425a.m6442c("PushServiceReceiver", "show advanced advertise  notification, msgid = " + str3 + "  appid = " + str4 + "  Title = " + publicMsg.mTitle + "  Description = " + publicMsg.mDescription + "  largeIconUrl = " + publicMsg.mAdvertiseLargeIconUrl + "  smallIconUrl = " + publicMsg.mAdvertiseSmallIconUrl + " bigPictureUrl = " + publicMsg.mAdvertiseBigPictureUrl);
            C1578v.m7095b("pushadvertise:  show big picture  advertise notification", context);
        }
    }

    /* renamed from: c */
    private static void m5873c(Context context, String str, String str2, PublicMsg publicMsg, String str3, String str4) {
        Notification a;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        Intent intent = new Intent();
        intent.setClassName(str, str2);
        intent.setAction("com.baidu.android.pushservice.action.privatenotification.CLICK");
        intent.setData(Uri.parse("content://" + str3));
        intent.putExtra("public_msg", publicMsg);
        intent.putExtra("app_id", str4);
        intent.putExtra("msg_id", str3);
        PendingIntent service = PendingIntent.getService(context, 0, intent, 0);
        intent = new Intent();
        intent.setClassName(str, str2);
        intent.setAction("com.baidu.android.pushservice.action.privatenotification.DELETE");
        intent.setData(Uri.parse("content://" + str3));
        intent.putExtra("public_msg", publicMsg);
        intent.putExtra("app_id", str4);
        intent.putExtra("msg_id", str3);
        PendingIntent service2 = PendingIntent.getService(context, 0, intent, 0);
        boolean o = C1578v.m7136o(context, publicMsg.mPkgName);
        if (publicMsg.mNotificationBuilder == 0) {
            a = C1394e.m6248a(context, publicMsg.mNotificationBuilder, publicMsg.mNotificationBasicStyle, publicMsg.mTitle, publicMsg.mDescription, o);
        } else {
            a = C1394e.m6249a(context, publicMsg.mNotificationBuilder, publicMsg.mTitle, publicMsg.mDescription, o);
        }
        a.contentIntent = service;
        a.deleteIntent = service2;
        notificationManager.notify(str3, 0, a);
        m5865a(context, str, publicMsg);
    }

    public void onReceive(Context context, Intent intent) {
        boolean z = true;
        String action = intent.getAction();
        final Context context2;
        if ("android.intent.action.BOOT_COMPLETED".equals(action) || "android.net.conn.CONNECTIVITY_CHANGE".equals(action) || "android.intent.action.USER_PRESENT".equals(action) || "android.intent.action.MEDIA_CHECKING".equals(action) || "android.intent.action.ACTION_POWER_CONNECTED".equals(action) || "android.intent.action.ACTION_POWER_DISCONNECTED".equals(action) || "android.bluetooth.adapter.action.STATE_CHANGED".equals(action)) {
            C1425a.m6441b("PushServiceReceiver", "start PushSerevice for by " + action);
            if (!ModeConfig.isProxyMode(context)) {
                C1577u.m7051d(context);
            }
        } else if ("com.baidu.android.pushservice.action.notification.SHOW".equals(action)) {
            if (!ModeConfig.isProxyMode(context)) {
                final String stringExtra = intent.getStringExtra("pushService_package_name");
                final String stringExtra2 = intent.getStringExtra("service_name");
                final String stringExtra3 = intent.getStringExtra("notify_type");
                final String stringExtra4 = intent.getStringExtra("app_id");
                final byte[] byteArrayExtra = intent.getByteArrayExtra("baidu_message_body");
                final byte[] byteArrayExtra2 = intent.getByteArrayExtra("baidu_message_secur_info");
                int intExtra = intent.getIntExtra("baidu_message_type", -1);
                final String stringExtra5 = intent.getStringExtra("message_id");
                if (TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(stringExtra2) || byteArrayExtra == null || byteArrayExtra2 == null || intExtra == -1) {
                    String str = "PushServiceReceiver";
                    StringBuilder append = new StringBuilder().append("Extra not valid, servicePkgName=").append(stringExtra).append(" serviceName=").append(stringExtra2).append(" pMsg==null? ").append(" msgBody==null? ").append(byteArrayExtra == null).append(" checkInfo==null? ");
                    if (byteArrayExtra2 != null) {
                        z = false;
                    }
                    C1425a.m6442c(str, append.append(z).append(" msgType=").append(intExtra).toString());
                } else if (C1578v.m7140q(context, stringExtra5)) {
                    C1425a.m6444e("PushServiceReceiver", " receive message duplicated !");
                } else {
                    context2 = context;
                    C1462d.m6637a().mo13938a(new C1281c("showPrivateNotification", (short) 99) {
                        /* renamed from: a */
                        public void mo13487a() {
                            PublicMsg a = C1488f.m6750a(context2, stringExtra4, stringExtra5, byteArrayExtra2, byteArrayExtra);
                            if (a == null) {
                                C1425a.m6444e("PushServiceReceiver", "notification check fail !");
                            } else if ("private".equals(stringExtra3)) {
                                PushServiceReceiver.m5873c(context2, stringExtra, stringExtra2, a, stringExtra5, stringExtra4);
                            } else if ("rich_media".equals(stringExtra3)) {
                                PushServiceReceiver.m5871b(context2, stringExtra, stringExtra2, a, stringExtra4);
                            }
                        }
                    });
                }
            }
        } else if ("com.baidu.android.pushservice.action.media.CLICK".equals(action)) {
            C1426b.m6445a("PushServiceReceiver", "Rich media notification clicked", context.getApplicationContext());
            PublicMsg publicMsg = null;
            try {
                if (intent.hasExtra("public_msg")) {
                    publicMsg = (PublicMsg) intent.getParcelableExtra("public_msg");
                }
                m5864a(context, publicMsg);
            } catch (ClassCastException e) {
                C1425a.m6443d("PushServiceReceiver", "Rich media notification clicked, parse pMsg exception");
            }
        } else if ("com.baidu.android.pushservice.action.advertise.notification.SHOW".equals(action)) {
            context2 = context;
            final Intent intent2 = intent;
            C1462d.m6637a().mo13938a(new C1281c("showAdvertiseNotifiation", (short) 99) {
                /* renamed from: a */
                public void mo13487a() {
                    try {
                        PushServiceReceiver.this.f4648a.lock();
                        if (C1533a.m6893b(context2)) {
                            String stringExtra = intent2.getStringExtra("pushService_package_name");
                            String stringExtra2 = intent2.getStringExtra("service_name");
                            Parcelable parcelableExtra = intent2.getParcelableExtra("ad_msg");
                            PublicMsg publicMsg = null;
                            if (parcelableExtra != null && (parcelableExtra instanceof PublicMsg)) {
                                publicMsg = (PublicMsg) parcelableExtra;
                            }
                            if (TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(stringExtra2) || publicMsg == null) {
                                C1425a.m6442c("PushServiceReceiver", "Extra not valid, servicePkgName=" + stringExtra + " serviceName=" + stringExtra2 + " pMsg==null - " + (publicMsg == null));
                                return;
                            }
                            String stringExtra3 = intent2.getStringExtra("message_id");
                            String stringExtra4 = intent2.getStringExtra("app_id");
                            C1425a.m6442c("PushServiceReceiver", "show advertise notification, msgid = " + stringExtra3 + "  appid = " + stringExtra4 + "  servicePkgName = " + stringExtra + "  serviceName = " + stringExtra2);
                            PushServiceReceiver.this.m5872b(context2, stringExtra, stringExtra2, publicMsg, stringExtra3, stringExtra4);
                            C1578v.m7095b("pushadvertise:  show advertise notification", context2);
                            if (C1328a.m6006b() > 0) {
                                C1578v.m7064a(context2, intent2, "09");
                            }
                            PushServiceReceiver.this.f4648a.unlock();
                            return;
                        }
                        PushServiceReceiver.this.f4648a.unlock();
                    } catch (Exception e) {
                        C1425a.m6444e("PushServiceReceiver", "error is " + e.getMessage());
                    } finally {
                        PushServiceReceiver.this.f4648a.unlock();
                    }
                }
            });
        } else if ("com.baidu.android.pushservice.action.adnotification.ADCLICK".equals(action)) {
            C1426b.m6445a("PushServiceReceiver", "Handle ADNotification Click Action", context.getApplicationContext());
            action = intent.getStringExtra("click_url");
            C1425a.m6442c("PushServiceReceiver", "click_url = " + action);
            if (C1533a.m6895c(action)) {
                if (!(TextUtils.isEmpty(action) || TextUtils.isEmpty(action.trim()))) {
                    Uri parse = Uri.parse(action.trim());
                    if (C1312e.m5924a(parse)) {
                        C1578v.m7095b("pushadvertise:  open click url", context);
                        try {
                            Intent intent3;
                            if (VERSION.SDK_INT > 17) {
                                intent3 = new Intent("android.intent.action.VIEW", parse);
                                List queryIntentActivities = context.getPackageManager().queryIntentActivities(intent3, 32);
                                if (queryIntentActivities.size() > 0) {
                                    intent3.setClassName(((ResolveInfo) queryIntentActivities.get(0)).activityInfo.packageName, ((ResolveInfo) queryIntentActivities.get(0)).activityInfo.name);
                                    intent3.addFlags(268435456);
                                    context.startActivity(intent3);
                                } else {
                                    intent.setAction("com.baidu.android.pushservice.action.adnotification.ADCLICKFAILED");
                                    C1426b.m6447b("PushServiceReceiver", "There is no borwer on this phone!", context);
                                }
                            } else {
                                intent3 = new Intent(context, MediaViewActivity.class);
                                intent3.addFlags(268435456);
                                intent3.setData(parse);
                                context.startActivity(intent3);
                            }
                        } catch (Exception e2) {
                            C1425a.m6444e("PushServiceReceiver", "error = " + e2.getMessage());
                        }
                    }
                }
                try {
                    Object systemService = context.getSystemService("statusbar");
                    (VERSION.SDK_INT <= 16 ? systemService.getClass().getMethod("collapse", new Class[0]) : systemService.getClass().getMethod("collapsePanels", new Class[0])).invoke(systemService, new Object[0]);
                } catch (Exception e22) {
                    C1425a.m6444e("PushServiceReceiver", "error : " + e22.getMessage());
                }
                ((NotificationManager) context.getApplicationContext().getSystemService("notification")).cancel(intent.getStringExtra("msg_id"), 0);
                C1578v.m7095b("pushadvertise:  cancel advertise notification", context);
                action = C1578v.m7149v(context);
                if (!TextUtils.isEmpty(action) && C1578v.m7127k(context, action) >= 36) {
                    intent.setClassName(action, "com.baidu.android.pushservice.PushService");
                    C1425a.m6442c("PushServiceReceiver", "CLICK  " + intent.toUri(0));
                    context.startService(intent);
                }
            }
        } else if ("com.baidu.android.pushservice.action.adnotification.ADDELETE".equals(action)) {
            action = C1578v.m7149v(context);
            if (!TextUtils.isEmpty(action) && C1578v.m7127k(context, action) >= 36) {
                intent.setClassName(action, "com.baidu.android.pushservice.PushService");
                C1425a.m6442c("PushServiceReceiver", "DELETE  " + intent.toUri(0));
                context.startService(intent);
            }
        }
    }
}
