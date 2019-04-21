package com.baidu.android.pushservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import com.baidu.android.pushservice.p027b.C1284b;
import com.baidu.android.pushservice.p027b.C1295a.C1296a;
import com.baidu.android.pushservice.p035g.C1409b;
import com.baidu.android.pushservice.p035g.C1418d;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p036h.C1426b;
import com.baidu.android.pushservice.util.C1537e;
import com.baidu.android.pushservice.util.C1574s;
import com.baidu.android.pushservice.util.C1578v;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

public class PushService extends Service {
    /* renamed from: a */
    private boolean f4619a = false;
    /* renamed from: b */
    private Handler f4620b = new Handler();
    /* renamed from: c */
    private SDcardRemovedReceiver f4621c;
    /* renamed from: d */
    private boolean f4622d = false;
    /* renamed from: e */
    private Runnable f4623e = new C12941();
    /* renamed from: f */
    private int f4624f = 0;
    /* renamed from: g */
    private final C1296a f4625g = new C12982();

    /* renamed from: com.baidu.android.pushservice.PushService$1 */
    class C12941 implements Runnable {
        C12941() {
        }

        public void run() {
            int i = 1;
            PushService.this.stopSelf();
            C1427h.m6452b();
            int i2 = PushService.this.f4624f > 0 ? 1 : 0;
            if (PushService.this.getPackageName().equals(C1578v.m7149v(PushService.this.getApplicationContext()))) {
                i = 0;
            }
            if ((i2 & i) != 0) {
                PushService.this.onDestroy();
            }
        }
    }

    /* renamed from: com.baidu.android.pushservice.PushService$2 */
    class C12982 extends C1296a {
        C12982() {
        }

        /* renamed from: a */
        public int mo13514a(String str, int i) throws RemoteException {
            C1425a.m6442c("PushService", "getUnreadMsgNumber from PushService, appid=" + str + " type=" + i);
            return C1418d.m6363a(PushService.this).mo13789a(i, str, null);
        }

        /* renamed from: a */
        public String mo13515a() throws RemoteException {
            C1425a.m6442c("PushService", "getSubcribedApps from PushService");
            return C1418d.m6363a(PushService.this).mo13791a(null);
        }

        /* renamed from: a */
        public String mo13516a(String str) throws RemoteException {
            C1425a.m6442c("PushService", "getSubscribedAppinfos from PushService, appids=" + str);
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.isEmpty(str)) {
                try {
                    JSONArray init = JSONArrayInstrumentation.init(str);
                    if (init != null) {
                        for (int i = 0; i < init.length(); i++) {
                            arrayList.add("" + init.getInt(i));
                        }
                    }
                } catch (JSONException e) {
                    C1425a.m6444e("PushService", "error " + e.getMessage());
                }
            }
            return arrayList.size() > 0 ? C1418d.m6363a(PushService.this).mo13793a(arrayList, null) : null;
        }

        /* renamed from: a */
        public String mo13517a(String str, int i, boolean z, int i2, int i3) throws RemoteException {
            C1425a.m6442c("PushService", "getMsgs from PushService, appid=" + str + " type=" + i + " unreadOnly=" + z + " start=" + i2 + " limit=" + i3);
            return C1418d.m6363a(PushService.this).mo13792a(str, i, z, i2, i3, null);
        }

        /* renamed from: a */
        public void mo13518a(String str, String str2, C1284b c1284b) throws RemoteException {
            C1425a.m6442c("PushService", "unsubscribeLight from PushService, appid=" + str + " channel=" + str2);
            C1418d.m6363a(PushService.this).mo13796a(str, str2, new C1409b(c1284b));
        }

        /* renamed from: a */
        public void mo13519a(String str, String str2, boolean z, C1284b c1284b) throws RemoteException {
            C1425a.m6442c("PushService", "subscribeLight from PushService, appid=" + str + " channel=" + str2);
            C1418d.m6363a(PushService.this).mo13801a(str, str2, z, new C1409b(c1284b));
        }

        /* renamed from: a */
        public void mo13520a(String str, String str2, boolean z, String str3, C1284b c1284b) throws RemoteException {
            C1425a.m6442c("PushService", "subscribeLight from PushService, appidOrApikey=" + str + " channel=" + str2);
            C1418d.m6363a(PushService.this).mo13802a(str, str2, z, str3, new C1409b(c1284b));
        }

        /* renamed from: a */
        public boolean mo13521a(String str, String str2) throws RemoteException {
            C1425a.m6442c("PushService", "register from PushService, channel=" + str + " packageName=" + str2);
            return C1418d.m6363a(PushService.this).mo13806a(str, str2);
        }

        /* renamed from: a */
        public boolean mo13522a(String str, String str2, int i) throws RemoteException {
            C1425a.m6442c("PushService", "updateBlacklist from PushService, packageName=" + str + " appid=" + str2 + " type" + i);
            return C1418d.m6363a(PushService.this).mo13807a(str, str2, i);
        }

        /* renamed from: a */
        public boolean mo13523a(String str, String str2, String str3, String str4) throws RemoteException {
            C1425a.m6442c("PushService", "register from PushService, channel=" + str + " packageName=" + str2 + " hostName= " + str3 + " hostVersion= " + str4);
            return C1418d.m6363a(PushService.this).mo13816b(str, str2, str3, str4);
        }

        /* renamed from: a */
        public boolean mo13524a(String str, boolean z) {
            C1425a.m6442c("PushService", "setNotifySwitch from PushService, pkgName=" + str + " on=" + z);
            return C1418d.m6363a(PushService.this).mo13808a(str, z);
        }

        /* renamed from: b */
        public int mo13525b(String str) throws RemoteException {
            C1425a.m6442c("PushService", "setMsgRead from PushService, msgids=" + str);
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.isEmpty(str)) {
                try {
                    JSONArray init = JSONArrayInstrumentation.init(str);
                    if (init != null) {
                        for (int i = 0; i < init.length(); i++) {
                            arrayList.add(init.getString(i));
                        }
                    }
                } catch (JSONException e) {
                    C1425a.m6444e("PushService", "error " + e.getMessage());
                }
            }
            return arrayList.size() > 0 ? C1418d.m6363a(PushService.this).mo13810b(arrayList, null) : -1;
        }

        /* renamed from: b */
        public int mo13526b(String str, int i) throws RemoteException {
            C1425a.m6442c("PushService", "setMsgRead from PushService, appid=" + str + " type=" + i);
            return C1418d.m6363a(PushService.this).mo13809b(i, str, null);
        }

        /* renamed from: b */
        public String mo13527b() throws RemoteException {
            C1425a.m6442c("PushService", "getSubscribedAppids from PushService");
            return C1418d.m6363a(PushService.this).mo13811b(null);
        }

        /* renamed from: b */
        public void mo13528b(String str, String str2, C1284b c1284b) throws RemoteException {
            C1425a.m6442c("PushService", "unbindlight from PushService, appid=" + str + " channel=" + str2);
            C1418d.m6363a(PushService.this).mo13813b(str, str2, new C1409b(c1284b));
        }

        /* renamed from: b */
        public boolean mo13529b(String str, String str2) throws RemoteException {
            C1425a.m6442c("PushService", "removeBlacklist from PushService, packageName=" + str + " appid=" + str2);
            return C1418d.m6363a(PushService.this).mo13815b(str, str2);
        }

        /* renamed from: c */
        public int mo13530c() throws RemoteException {
            C1425a.m6442c("PushService", "getPushVersion " + C1328a.m6003a());
            return C1328a.m6003a();
        }

        /* renamed from: c */
        public int mo13531c(String str) throws RemoteException {
            C1425a.m6442c("PushService", "deleteMsg from PushService, msgids=" + str);
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.isEmpty(str)) {
                try {
                    JSONArray init = JSONArrayInstrumentation.init(str);
                    if (init != null) {
                        for (int i = 0; i < init.length(); i++) {
                            arrayList.add(init.getString(i));
                        }
                    }
                } catch (JSONException e) {
                    C1425a.m6444e("PushService", "error " + e.getMessage());
                }
            }
            return arrayList.size() > 0 ? C1418d.m6363a(PushService.this).mo13819c(arrayList, null) : -1;
        }

        /* renamed from: c */
        public int mo13532c(String str, int i) throws RemoteException {
            C1425a.m6442c("PushService", "deleteAllMsg from PushService, appid=" + str + " type=" + i);
            return C1418d.m6363a(PushService.this).mo13817c(i, str, null);
        }

        /* renamed from: d */
        public int mo13533d(String str) throws RemoteException {
            C1425a.m6442c("PushService", "getNewMsgNum from PushService, packageName=" + str);
            return C1418d.m6363a(PushService.this).mo13818c(str);
        }

        /* renamed from: e */
        public boolean mo13534e(String str) throws RemoteException {
            C1425a.m6442c("PushService", "clearNewMsgNum from PushService, packageName=" + str);
            return C1418d.m6363a(PushService.this).mo13821d(str);
        }

        /* renamed from: f */
        public String mo13535f(String str) throws RemoteException {
            return C1418d.m6363a(PushService.this).mo13824g(str);
        }
    }

    /* renamed from: a */
    private void m5848a(boolean z, boolean z2) {
        this.f4619a = z;
        C1426b.m6445a("PushService", "stopSelf : exitOnDestroy=" + z + " --- immediate=" + z2, getApplicationContext());
        if (z2) {
            this.f4623e.run();
            return;
        }
        this.f4620b.removeCallbacks(this.f4623e);
        this.f4620b.postDelayed(this.f4623e, 1000);
    }

    public IBinder onBind(Intent intent) {
        this.f4624f++;
        C1425a.m6442c("PushService", "onBind(" + this.f4624f + "), intent=" + intent + " cur: " + getApplicationContext().getPackageName() + " initSuc: " + this.f4622d);
        return this.f4622d ? this.f4625g : null;
    }

    public void onCreate() {
        super.onCreate();
        C1426b.m6445a("PushService", "onCreate from : " + getPackageName(), getApplicationContext());
        C1578v.m7095b("PushService onCreate from : " + getPackageName() + " at Time :" + System.currentTimeMillis(), getApplicationContext());
        this.f4622d = C1427h.m6451a((Context) this).mo13829a();
        if (this.f4622d) {
            try {
                this.f4621c = new SDcardRemovedReceiver();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.MEDIA_BAD_REMOVAL");
                intentFilter.addAction("android.intent.action.MEDIA_REMOVED");
                registerReceiver(this.f4621c, intentFilter);
                return;
            } catch (Exception e) {
                C1425a.m6440a("PushService", e);
                return;
            }
        }
        m5848a(true, true);
    }

    public void onDestroy() {
        super.onDestroy();
        C1574s.m7022b(getApplicationContext(), null);
        C1426b.m6445a("PushService", "onDestroy from : " + getPackageName(), getApplicationContext());
        C1578v.m7095b("PushService onDestroy from : " + getPackageName() + " at Time :" + System.currentTimeMillis(), getApplicationContext());
        try {
            unregisterReceiver(this.f4621c);
        } catch (Exception e) {
            C1425a.m6442c("TAG", "sdcard receiver unregister failed");
        }
        C1427h.m6452b();
        if (this.f4619a) {
            Process.killProcess(Process.myPid());
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            intent = new Intent();
            C1426b.m6448c("PushService", "--- onStart by null intent!", getApplicationContext());
        }
        try {
            C1425a.m6442c("PushService", "-- onStartCommand -- " + intent.toUri(0));
            C1578v.m7095b("PushService onStartCommand from " + getPackageName() + " Intent " + intent.toUri(0) + " at Time " + System.currentTimeMillis(), getApplicationContext());
        } catch (Exception e) {
            C1425a.m6440a("PushService", e);
        }
        this.f4620b.removeCallbacks(this.f4623e);
        if ("com.baidu.android.pushservice.action.CROSS_REQUEST".equals(intent.getAction())) {
            C1537e.m6908a(getApplicationContext(), intent);
        }
        try {
            this.f4622d = C1427h.m6451a((Context) this).mo13830a(intent);
            if (this.f4622d) {
                return 1;
            }
            m5848a(true, true);
            return 2;
        } catch (Exception e2) {
            C1425a.m6444e("PushService", "error : " + e2);
            m5848a(true, true);
            return 2;
        }
    }

    public boolean onUnbind(Intent intent) {
        this.f4624f--;
        C1425a.m6442c("PushService", "onUnbind(" + this.f4624f + "), intent=" + intent);
        return super.onUnbind(intent);
    }
}
