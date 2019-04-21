package com.baidu.android.pushservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.baidu.android.pushservice.message.PublicMsg;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p037i.C1451p;
import com.baidu.android.pushservice.util.C1543g;
import com.baidu.android.pushservice.util.C1578v;
import java.lang.reflect.Method;

public class CommandService extends Service {
    /* renamed from: a */
    private void m5754a(Intent intent) {
        String d = C1578v.m7110d(this, getPackageName(), intent.getAction());
        C1578v.m7095b("CommandService#onStartCommand#reflectReceiver#recevier = " + d, (Context) this);
        if (TextUtils.isEmpty(d)) {
            C1425a.m6441b("CommandService", " reflectReceiver error: receiver for: " + intent.getAction() + " not found, package: " + getPackageName());
            intent.setPackage(getPackageName());
            sendBroadcast(intent);
            if ("com.baidu.android.pushservice.action.advertise.notification.SHOW".equals(intent.getAction())) {
                C1578v.m7064a(getApplicationContext(), intent, "07");
                return;
            }
            return;
        }
        try {
            Class cls = Class.forName(d);
            Object newInstance = cls.getConstructor(new Class[0]).newInstance(new Object[0]);
            String[] strArr = new String[]{"android.content.Context", "android.content.Intent"};
            Method method = cls.getMethod("onReceive", new Class[]{Context.class, Intent.class});
            intent.setClassName(getPackageName(), d);
            method.invoke(newInstance, new Object[]{getApplicationContext(), intent});
        } catch (Exception e) {
            C1425a.m6440a("CommandService", e);
        }
        if (C1328a.m6006b() > 0) {
            if ("com.baidu.android.pushservice.action.advertise.notification.SHOW".equals(intent.getAction())) {
                C1578v.m7064a(getApplicationContext(), intent, "08");
            }
        }
    }

    /* renamed from: b */
    private void m5755b(Intent intent) {
        String stringExtra = intent.getStringExtra("bd.cross.request.SOURCE_SERVICE");
        String stringExtra2 = intent.getStringExtra("bd.cross.request.SOURCE_PACKAGE");
        if (!TextUtils.isEmpty(stringExtra) && !TextUtils.isEmpty(stringExtra2)) {
            intent.setPackage(stringExtra2);
            intent.setClassName(stringExtra2, stringExtra);
            intent.setAction("com.baidu.android.pushservice.action.CROSS_REQUEST");
            intent.putExtra("bd.cross.request.SENDING", false);
            getApplicationContext().startService(intent);
        }
    }

    public IBinder onBind(Intent intent) {
        C1425a.m6442c("CommandService", "onBind initSuc: ");
        return null;
    }

    public void onCreate() {
        C1425a.m6442c("CommandService", "-- CommandService oncreate -- ");
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        Object obj = null;
        if (intent == null) {
            stopSelf();
        } else {
            String action = intent.getAction();
            C1425a.m6442c("CommandService", "action = " + action);
            C1578v.m7095b("CommandService#onStartCommand#action = " + action, (Context) this);
            try {
                String stringExtra;
                String stringExtra2;
                if ("com.baidu.android.pushservice.action.passthrough.notification.CLICK".equals(action) || "com.baidu.android.pushservice.action.passthrough.notification.DELETE".equals(action) || "com.baidu.android.pushservice.action.passthrough.notification.NOTIFIED".equals(action)) {
                    C1578v.m7095b("push_passthrough: receive  click delete and notified action", getApplicationContext());
                    C1425a.m6442c("CommandService", "handle passthrough notification " + action);
                    stringExtra = intent.hasExtra("app_id") ? intent.getStringExtra("app_id") : null;
                    if (intent.hasExtra("msg_id")) {
                        stringExtra2 = intent.getStringExtra("msg_id");
                    }
                    C1451p.m6591a(getApplicationContext(), stringExtra2, stringExtra, action);
                    stopSelf();
                } else if ("com.baidu.android.pushservice.action.privatenotification.CLICK".equals(action) || "com.baidu.android.pushservice.action.privatenotification.DELETE".equals(action)) {
                    PublicMsg publicMsg = (PublicMsg) intent.getParcelableExtra("public_msg");
                    stringExtra = intent.getStringExtra("app_id");
                    String stringExtra3 = intent.getStringExtra("msg_id");
                    publicMsg.handlePrivateNotification(getApplicationContext(), action, stringExtra3, stringExtra);
                    int intExtra = intent.getIntExtra("hw_push_type", 0);
                    stringExtra = intent.getStringExtra("hw_gid");
                    if ("com.baidu.android.pushservice.action.privatenotification.CLICK".equals(action)) {
                        C1543g.m6923a(getApplicationContext(), stringExtra3, intExtra, stringExtra + "", 1);
                    } else if ("com.baidu.android.pushservice.action.privatenotification.DELETE".equals(action)) {
                        C1543g.m6922a(getApplicationContext(), stringExtra3, intExtra, stringExtra + "");
                    }
                    stopSelf();
                } else {
                    if (intent.hasExtra("command_type")) {
                        obj = intent.getStringExtra("command_type");
                        C1425a.m6442c("CommandService", "-- command_type -- " + obj);
                    }
                    if ("reflect_receiver".equals(obj)) {
                        m5754a(intent);
                    } else if (intent.hasExtra("bd.cross.request.COMMAND_TYPE")) {
                        stringExtra2 = intent.getStringExtra("bd.cross.request.COMMAND_TYPE");
                        if ("bd.cross.command.MESSAGE_DELIVER".equals(stringExtra2)) {
                            m5754a(intent);
                        } else if ("bd.cross.command.MESSAGE_ACK".equals(stringExtra2)) {
                            m5755b(intent);
                        }
                    }
                    stopSelf();
                }
            } catch (RuntimeException e) {
                C1425a.m6444e("CommandService", "runtime e: " + e);
                stopSelf();
            }
        }
        return 2;
    }

    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
