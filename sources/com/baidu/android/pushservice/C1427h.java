package com.baidu.android.pushservice;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.LocalServerSocket;
import android.os.Handler;
import android.text.TextUtils;
import com.baidu.android.pushservice.jni.PushSocket;
import com.baidu.android.pushservice.message.p040a.C1486e;
import com.baidu.android.pushservice.p026j.C1281c;
import com.baidu.android.pushservice.p026j.C1462d;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p036h.C1426b;
import com.baidu.android.pushservice.p037i.C1456u;
import com.baidu.android.pushservice.util.C1535c;
import com.baidu.android.pushservice.util.C1548l;
import com.baidu.android.pushservice.util.C1568q;
import com.baidu.android.pushservice.util.C1574s;
import com.baidu.android.pushservice.util.C1577u;
import com.baidu.android.pushservice.util.C1578v;
import java.io.IOException;

@SuppressLint({"WorldReadableFiles", "InlinedApi"})
/* renamed from: com.baidu.android.pushservice.h */
public class C1427h {
    /* renamed from: a */
    public static C1404f f4998a;
    /* renamed from: b */
    private static String f4999b = "PushSDK";
    /* renamed from: c */
    private static C1427h f5000c = null;
    /* renamed from: d */
    private static int f5001d = 180000;
    /* renamed from: e */
    private static int f5002e = 1800000;
    /* renamed from: g */
    private static Object f5003g = new Object();
    /* renamed from: h */
    private static LocalServerSocket f5004h;
    /* renamed from: l */
    private static Object f5005l = new Object();
    /* renamed from: f */
    private int f5006f;
    /* renamed from: i */
    private C1463j f5007i;
    /* renamed from: j */
    private Boolean f5008j = Boolean.valueOf(false);
    /* renamed from: k */
    private Boolean f5009k = Boolean.valueOf(false);
    /* renamed from: m */
    private Context f5010m;
    /* renamed from: n */
    private Handler f5011n;
    /* renamed from: o */
    private Runnable f5012o = new C14222();
    /* renamed from: p */
    private Runnable f5013p = new C14233();
    /* renamed from: q */
    private Runnable f5014q = new C14244();

    /* renamed from: com.baidu.android.pushservice.h$2 */
    class C14222 implements Runnable {
        C14222() {
        }

        public void run() {
            C1427h.this.mo13830a(new Intent());
        }
    }

    /* renamed from: com.baidu.android.pushservice.h$3 */
    class C14233 implements Runnable {
        C14233() {
        }

        public void run() {
            C1427h.this.mo13832d();
        }
    }

    /* renamed from: com.baidu.android.pushservice.h$4 */
    class C14244 implements Runnable {
        C14244() {
        }

        public void run() {
            synchronized (C1427h.f5003g) {
                if (C1427h.f4998a != null) {
                    C1427h.f4998a.mo13748b();
                }
            }
        }
    }

    private C1427h(Context context) {
        this.f5011n = new Handler(context.getMainLooper());
        this.f5010m = context.getApplicationContext();
        this.f5006f = f5001d;
        C1578v.m7125j(this.f5010m.getApplicationContext());
    }

    /* renamed from: a */
    public static synchronized C1427h m6451a(Context context) {
        C1427h c1427h;
        synchronized (C1427h.class) {
            if (f5000c == null) {
                f5000c = new C1427h(context);
            }
            c1427h = f5000c;
        }
        return c1427h;
    }

    /* renamed from: b */
    public static void m6452b() {
        if (f5000c != null) {
            f5000c.m6459h();
        }
        C1462d.m6637a().mo13939b();
    }

    /* renamed from: b */
    private boolean m6454b(Context context) {
        String v = C1578v.m7149v(context);
        String packageName = context.getPackageName();
        if (packageName.equals(v)) {
            C1426b.m6445a(f4999b, "Try use current push service, package name is: " + packageName, this.f5010m);
            return false;
        }
        C1426b.m6445a(f4999b, "Current push service : " + packageName + " should stop!!!" + " highest priority service is: " + v, this.f5010m);
        return true;
    }

    /* renamed from: h */
    private void m6459h() {
        C1426b.m6445a(f4999b, "destroy", this.f5010m);
        synchronized (f5005l) {
            try {
                if (f5004h != null) {
                    f5004h.close();
                    f5004h = null;
                }
            } catch (IOException e) {
                C1425a.m6444e(f4999b, "error " + e.getMessage());
            }
            if (f4998a != null) {
                synchronized (f5003g) {
                    f4998a.mo13749c();
                    f4998a = null;
                }
            }
            try {
                C1568q.m6996a();
            } catch (Exception e2) {
                C1425a.m6444e(f4999b, "error " + e2.getMessage());
            }
            f5000c = null;
        }
    }

    /* renamed from: i */
    private void m6460i() {
        synchronized (f5003g) {
            f4998a = C1404f.m6274a(this.f5010m);
        }
    }

    /* renamed from: j */
    private void m6461j() {
        m6462k();
        long currentTimeMillis = ((long) this.f5006f) + System.currentTimeMillis();
        int i = ((int) (currentTimeMillis / 1000)) % 60;
        if (((int) ((currentTimeMillis / 60000) % 5)) == 0 && i < 15) {
            currentTimeMillis += ((long) (Math.random() * ((double) (this.f5006f - 20000)))) + 15000;
        }
        try {
            ((AlarmManager) this.f5010m.getSystemService("alarm")).setRepeating(0, currentTimeMillis, (long) this.f5006f, m6469r());
        } catch (Exception e) {
            C1425a.m6440a(f4999b, e);
        }
    }

    /* renamed from: k */
    private void m6462k() {
        try {
            ((AlarmManager) this.f5010m.getSystemService("alarm")).cancel(m6469r());
        } catch (Exception e) {
            C1425a.m6440a(f4999b, e);
        }
    }

    /* renamed from: l */
    private void m6463l() {
        C1462d.m6637a().mo13938a(new C1281c("tryConnect", (short) 98) {
            /* JADX WARNING: Missing block: B:30:?, code skipped:
            return;
     */
            /* JADX WARNING: Missing block: B:31:?, code skipped:
            return;
     */
            /* renamed from: a */
            public void mo13487a() {
                /*
                r5 = this;
                r0 = com.baidu.android.pushservice.C1427h.f5000c;
                if (r0 != 0) goto L_0x0007;
            L_0x0006:
                return;
            L_0x0007:
                r1 = com.baidu.android.pushservice.C1427h.f5000c;
                monitor-enter(r1);
                r0 = com.baidu.android.pushservice.C1427h.this;	 Catch:{ all -> 0x004c }
                r0 = r0.f5010m;	 Catch:{ all -> 0x004c }
                r0 = com.baidu.android.pushservice.util.C1548l.m6952e(r0);	 Catch:{ all -> 0x004c }
                r2 = com.baidu.android.pushservice.C1427h.f4999b;	 Catch:{ all -> 0x004c }
                r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x004c }
                r3.<init>();	 Catch:{ all -> 0x004c }
                r4 = "tryConnect networkConnected :";
                r3 = r3.append(r4);	 Catch:{ all -> 0x004c }
                r3 = r3.append(r0);	 Catch:{ all -> 0x004c }
                r3 = r3.toString();	 Catch:{ all -> 0x004c }
                r4 = com.baidu.android.pushservice.C1427h.this;	 Catch:{ all -> 0x004c }
                r4 = r4.f5010m;	 Catch:{ all -> 0x004c }
                com.baidu.android.pushservice.p036h.C1426b.m6445a(r2, r3, r4);	 Catch:{ all -> 0x004c }
                if (r0 != 0) goto L_0x004f;
            L_0x0039:
                r0 = com.baidu.android.pushservice.C1328a.m6006b();	 Catch:{ all -> 0x004c }
                if (r0 <= 0) goto L_0x004a;
            L_0x003f:
                r0 = com.baidu.android.pushservice.C1427h.this;	 Catch:{ all -> 0x004c }
                r0 = r0.f5010m;	 Catch:{ all -> 0x004c }
                r2 = "039912";
                com.baidu.android.pushservice.p037i.C1456u.m6615a(r0, r2);	 Catch:{ all -> 0x004c }
            L_0x004a:
                monitor-exit(r1);	 Catch:{ all -> 0x004c }
                goto L_0x0006;
            L_0x004c:
                r0 = move-exception;
                monitor-exit(r1);	 Catch:{ all -> 0x004c }
                throw r0;
            L_0x004f:
                r0 = com.baidu.android.pushservice.C1328a.m6006b();	 Catch:{ all -> 0x004c }
                if (r0 <= 0) goto L_0x0060;
            L_0x0055:
                r0 = com.baidu.android.pushservice.C1427h.this;	 Catch:{ all -> 0x004c }
                r0 = r0.f5010m;	 Catch:{ all -> 0x004c }
                r2 = "039914";
                com.baidu.android.pushservice.p037i.C1456u.m6615a(r0, r2);	 Catch:{ all -> 0x004c }
            L_0x0060:
                r0 = com.baidu.android.pushservice.C1427h.f4998a;	 Catch:{ all -> 0x004c }
                if (r0 == 0) goto L_0x0090;
            L_0x0064:
                r0 = com.baidu.android.pushservice.C1427h.f4998a;	 Catch:{ all -> 0x004c }
                r0 = r0.mo13747a();	 Catch:{ all -> 0x004c }
                if (r0 != 0) goto L_0x0090;
            L_0x006c:
                r0 = com.baidu.android.pushservice.C1427h.this;	 Catch:{ all -> 0x004c }
                r0 = r0.f5010m;	 Catch:{ all -> 0x004c }
                r0 = com.baidu.android.pushservice.C1475k.m6721a(r0);	 Catch:{ all -> 0x004c }
                r0 = r0.mo13950c();	 Catch:{ all -> 0x004c }
                if (r0 != 0) goto L_0x0093;
            L_0x007c:
                r0 = com.baidu.android.pushservice.C1427h.f4999b;	 Catch:{ all -> 0x004c }
                r2 = "Channel token is not available, start NETWORK REGISTER SERVICE .";
                r3 = com.baidu.android.pushservice.C1427h.this;	 Catch:{ all -> 0x004c }
                r3 = r3.f5010m;	 Catch:{ all -> 0x004c }
                com.baidu.android.pushservice.p036h.C1426b.m6449d(r0, r2, r3);	 Catch:{ all -> 0x004c }
                r0 = com.baidu.android.pushservice.C1427h.this;	 Catch:{ all -> 0x004c }
                r0.m6466o();	 Catch:{ all -> 0x004c }
            L_0x0090:
                monitor-exit(r1);	 Catch:{ all -> 0x004c }
                goto L_0x0006;
            L_0x0093:
                r0 = com.baidu.android.pushservice.C1427h.this;	 Catch:{ all -> 0x004c }
                r0.m6467p();	 Catch:{ all -> 0x004c }
                goto L_0x0090;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.C1427h$C14211.mo13487a():void");
            }
        });
    }

    /* renamed from: m */
    private boolean m6464m() {
        if (f5004h == null) {
            try {
                C1574s.m7022b(this.f5010m, null);
                f5004h = new LocalServerSocket(C1578v.m7137p(this.f5010m));
                m6468q();
            } catch (Exception e) {
                C1426b.m6445a(f4999b, "--- Socket Adress (" + C1578v.m7137p(this.f5010m) + ") in use --- @ " + this.f5010m.getPackageName(), this.f5010m);
                C1577u.m7048b(this.f5010m);
                return false;
            }
        }
        C1574s.m7022b(this.f5010m, this.f5010m.getPackageName());
        return true;
    }

    /* renamed from: n */
    private boolean m6465n() {
        C1486e.m6743a(this.f5010m);
        boolean a = C1548l.m6948a(this.f5010m);
        C1426b.m6445a(f4999b, "heartbeat networkConnected :" + a, this.f5010m);
        String n = C1578v.m7134n(this.f5010m, C1578v.m7147u(this.f5010m));
        if (C1578v.m7105c(this.f5010m) || !(TextUtils.isEmpty(n) || this.f5010m.getPackageName().equals(n))) {
            m6462k();
            return false;
        } else if (a) {
            if (C1328a.m6006b() > 0) {
                C1456u.m6615a(this.f5010m, "039914");
            }
            if (f4998a == null) {
                return true;
            }
            if (f4998a.mo13747a()) {
                f4998a.mo13750d();
                Intent intent = new Intent("com.baidu.android.pushservice.action.METHOD");
                intent.putExtra("method", "com.baidu.android.pushservice.action.SEND_APPSTAT");
                intent.setClass(this.f5010m, PushService.class);
                this.f5007i.mo13940a(intent);
            } else if (C1475k.m6721a(this.f5010m).mo13950c()) {
                m6467p();
            } else {
                C1426b.m6448c(f4999b, "Channel token is not available, start NETWORK REGISTER SERVICE .", this.f5010m);
                m6466o();
            }
            C1578v.m7095b("heartbeat PushConnection isConnected " + f4998a.mo13747a() + " at Time " + System.currentTimeMillis(), this.f5010m.getApplicationContext());
            return true;
        } else {
            if (f4998a != null) {
                f4998a.mo13746a(true);
            }
            if (C1328a.m6006b() <= 0) {
                return true;
            }
            C1456u.m6615a(this.f5010m, "039912");
            return true;
        }
    }

    /* renamed from: o */
    private void m6466o() {
        this.f5011n.removeCallbacks(this.f5013p);
        this.f5011n.postDelayed(this.f5013p, 500);
    }

    /* renamed from: p */
    private void m6467p() {
        if (f5004h != null || m6464m()) {
            this.f5011n.removeCallbacks(this.f5014q);
            this.f5011n.postDelayed(this.f5014q, 1000);
        }
    }

    /* renamed from: q */
    private void m6468q() {
        C1535c.m6902a(this.f5010m, "com.baidu.push.cur_prio", C1328a.m6003a());
        C1535c.m6903a(this.f5010m, "com.baidu.push.cur_pkg", this.f5010m.getPackageName());
    }

    /* renamed from: r */
    private PendingIntent m6469r() {
        Intent intent = new Intent();
        intent.putExtra("AlarmAlert", "OK");
        intent.setFlags(32);
        intent.setClass(this.f5010m, PushService.class);
        return PendingIntent.getService(this.f5010m.getApplicationContext(), 0, intent, 134217728);
    }

    /* renamed from: a */
    public void mo13828a(int i) {
        C1426b.m6445a(f4999b, "heartbeat set : " + i + " secs", this.f5010m);
        if (i > 0) {
            this.f5006f = i * 1000;
        }
        m6461j();
    }

    /* renamed from: a */
    public boolean mo13829a() {
        C1426b.m6445a(f4999b, "Create PushSDK from : " + this.f5010m.getPackageName(), this.f5010m);
        m6462k();
        this.f5009k = Boolean.valueOf(true);
        if (C1578v.m7105c(this.f5010m.getApplicationContext()) || m6454b(this.f5010m)) {
            C1426b.m6445a(f4999b, "onCreate shouldStopSelf", this.f5010m);
            return false;
        }
        synchronized (f5005l) {
            if (PushSocket.f5158a) {
                if (!m6464m()) {
                    if (!this.f5010m.getPackageName().equals(C1578v.m7134n(this.f5010m, C1578v.m7147u(this.f5010m)))) {
                        return false;
                    }
                }
                C1457i.m6623a(this.f5010m);
                Thread.setDefaultUncaughtExceptionHandler(new C1329b(this.f5010m.getApplicationContext()));
                m6460i();
                this.f5007i = C1463j.m6642a(this.f5010m);
                PushSettings.m5893l(this.f5010m);
                if (f5004h != null) {
                    this.f5011n.postDelayed(this.f5012o, 500);
                    m6463l();
                }
                this.f5008j = Boolean.valueOf(true);
                return true;
            }
            return false;
        }
    }

    /* JADX WARNING: Missing block: B:63:?, code skipped:
            return false;
     */
    /* renamed from: a */
    public boolean mo13830a(android.content.Intent r9) {
        /*
        r8 = this;
        r1 = 0;
        r0 = 1;
        r2 = f4999b;
        r3 = "PushSDK handleOnStart go";
        r4 = r8.f5010m;
        com.baidu.android.pushservice.p036h.C1426b.m6445a(r2, r3, r4);
        if (r9 != 0) goto L_0x001b;
    L_0x000d:
        r9 = new android.content.Intent;
        r9.<init>();
        r2 = f4999b;
        r3 = "--- handleOnStart by null intent!";
        r4 = r8.f5010m;
        com.baidu.android.pushservice.p036h.C1426b.m6448c(r2, r3, r4);
    L_0x001b:
        r2 = r8.f5009k;
        r2 = r2.booleanValue();
        if (r2 != 0) goto L_0x0029;
    L_0x0023:
        r2 = r8.mo13829a();
        if (r2 == 0) goto L_0x0039;
    L_0x0029:
        r2 = r8.f5009k;
        r2 = r2.booleanValue();
        if (r2 == 0) goto L_0x003b;
    L_0x0031:
        r2 = r8.f5008j;
        r2 = r2.booleanValue();
        if (r2 != 0) goto L_0x003b;
    L_0x0039:
        r0 = r1;
    L_0x003a:
        return r0;
    L_0x003b:
        r4 = f5005l;
        monitor-enter(r4);
        r2 = r8.f5011n;	 Catch:{ all -> 0x0075 }
        r3 = r8.f5012o;	 Catch:{ all -> 0x0075 }
        r2.removeCallbacks(r3);	 Catch:{ all -> 0x0075 }
        r2 = f4999b;	 Catch:{ all -> 0x0075 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0075 }
        r3.<init>();	 Catch:{ all -> 0x0075 }
        r5 = "-- handleOnStart -- ";
        r3 = r3.append(r5);	 Catch:{ all -> 0x0075 }
        r3 = r3.append(r9);	 Catch:{ all -> 0x0075 }
        r3 = r3.toString();	 Catch:{ all -> 0x0075 }
        com.baidu.android.pushservice.p036h.C1425a.m6441b(r2, r3);	 Catch:{ all -> 0x0075 }
        r2 = f5004h;	 Catch:{ all -> 0x0075 }
        if (r2 != 0) goto L_0x007a;
    L_0x0061:
        r1 = "com.baidu.android.pushservice.action.METHOD";
        r2 = r9.getAction();	 Catch:{ all -> 0x0075 }
        r1 = r1.equals(r2);	 Catch:{ all -> 0x0075 }
        if (r1 == 0) goto L_0x0078;
    L_0x006d:
        r0 = r8.f5007i;	 Catch:{ all -> 0x0075 }
        r0 = r0.mo13940a(r9);	 Catch:{ all -> 0x0075 }
        monitor-exit(r4);	 Catch:{ all -> 0x0075 }
        goto L_0x003a;
    L_0x0075:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0075 }
        throw r0;
    L_0x0078:
        monitor-exit(r4);	 Catch:{ all -> 0x0075 }
        goto L_0x003a;
    L_0x007a:
        r2 = "AlarmAlert";
        r2 = r9.getStringExtra(r2);	 Catch:{ all -> 0x0075 }
        if (r2 == 0) goto L_0x0088;
    L_0x0082:
        r0 = r8.m6465n();	 Catch:{ all -> 0x0075 }
        monitor-exit(r4);	 Catch:{ all -> 0x0075 }
        goto L_0x003a;
    L_0x0088:
        if (r9 == 0) goto L_0x00f7;
    L_0x008a:
        r2 = "pushservice_restart_v2";
        r3 = "method";
        r3 = r9.getStringExtra(r3);	 Catch:{ all -> 0x0075 }
        r2 = r2.equals(r3);	 Catch:{ all -> 0x0075 }
        if (r2 == 0) goto L_0x00e3;
    L_0x0098:
        r2 = f5004h;	 Catch:{ all -> 0x0075 }
        if (r2 == 0) goto L_0x00e3;
    L_0x009c:
        r2 = "priority2";
        r6 = 0;
        r2 = r9.getLongExtra(r2, r6);	 Catch:{ all -> 0x0075 }
        r5 = r8.f5010m;	 Catch:{ all -> 0x0075 }
        r5 = com.baidu.android.pushservice.config.ModeConfig.getInstance(r5);	 Catch:{ all -> 0x0075 }
        r6 = android.os.Build.MANUFACTURER;	 Catch:{ all -> 0x0075 }
        r5.caculateCurrentConfig(r6);	 Catch:{ all -> 0x0075 }
        r5 = r8.f5010m;	 Catch:{ all -> 0x0075 }
        r6 = com.baidu.android.pushservice.util.C1578v.m7128k(r5);	 Catch:{ all -> 0x0075 }
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 <= 0) goto L_0x00df;
    L_0x00b9:
        r2 = r8.f5010m;	 Catch:{ all -> 0x0075 }
        r2 = com.baidu.android.pushservice.config.ModeConfig.getInstance(r2);	 Catch:{ all -> 0x0075 }
        r2 = r2.getCurrentMode();	 Catch:{ all -> 0x0075 }
        r3 = com.baidu.android.pushservice.config.ModeConfig.MODE_C_H;	 Catch:{ all -> 0x0075 }
        if (r2 == r3) goto L_0x00df;
    L_0x00c7:
        r3 = r0;
    L_0x00c8:
        r2 = r8.f5010m;	 Catch:{ all -> 0x0075 }
        r2 = com.baidu.android.pushservice.config.ModeConfig.getInstance(r2);	 Catch:{ all -> 0x0075 }
        r2 = r2.getCurrentMode();	 Catch:{ all -> 0x0075 }
        r5 = com.baidu.android.pushservice.config.ModeConfig.MODE_C_C;	 Catch:{ all -> 0x0075 }
        if (r2 != r5) goto L_0x00e1;
    L_0x00d6:
        r2 = r0;
    L_0x00d7:
        if (r3 != 0) goto L_0x00db;
    L_0x00d9:
        if (r2 == 0) goto L_0x00f7;
    L_0x00db:
        monitor-exit(r4);	 Catch:{ all -> 0x0075 }
        r0 = r1;
        goto L_0x003a;
    L_0x00df:
        r3 = r1;
        goto L_0x00c8;
    L_0x00e1:
        r2 = r1;
        goto L_0x00d7;
    L_0x00e3:
        r1 = r8.f5007i;	 Catch:{ all -> 0x0075 }
        r1 = r1.mo13940a(r9);	 Catch:{ all -> 0x0075 }
        if (r1 == 0) goto L_0x00f7;
    L_0x00eb:
        r1 = f4999b;	 Catch:{ all -> 0x0075 }
        r2 = "-- handleOnStart -- intent handled  by mRegistrationService ";
        r3 = r8.f5010m;	 Catch:{ all -> 0x0075 }
        com.baidu.android.pushservice.p036h.C1426b.m6448c(r1, r2, r3);	 Catch:{ all -> 0x0075 }
        monitor-exit(r4);	 Catch:{ all -> 0x0075 }
        goto L_0x003a;
    L_0x00f7:
        r8.m6463l();	 Catch:{ all -> 0x0075 }
        monitor-exit(r4);	 Catch:{ all -> 0x0075 }
        goto L_0x003a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.C1427h.mo13830a(android.content.Intent):boolean");
    }

    /* renamed from: c */
    public C1463j mo13831c() {
        return this.f5007i;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: d */
    public void mo13832d() {
        C1426b.m6445a(f4999b, ">> sendRequestTokenIntent", this.f5010m);
        C1577u.m7049b(this.f5010m, new Intent("com.baidu.pushservice.action.TOKEN"));
    }
}
