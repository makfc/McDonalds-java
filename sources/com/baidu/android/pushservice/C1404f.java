package com.baidu.android.pushservice;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.baidu.android.pushservice.jni.PushSocket;
import com.baidu.android.pushservice.message.C1505e;
import com.baidu.android.pushservice.message.C1506f;
import com.baidu.android.pushservice.message.C1507g;
import com.baidu.android.pushservice.p026j.C1281c;
import com.baidu.android.pushservice.p026j.C1462d;
import com.baidu.android.pushservice.p033e.C1361a;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p036h.C1426b;
import com.baidu.android.pushservice.p037i.C1446k;
import com.baidu.android.pushservice.p037i.C1456u;
import com.baidu.android.pushservice.p037i.p038a.C1432b;
import com.baidu.android.pushservice.util.C1548l;
import com.baidu.android.pushservice.util.C1550n;
import com.baidu.android.pushservice.util.C1578v;
import com.newrelic.agent.android.payload.PayloadController;
import java.util.HashMap;
import java.util.Set;

/* renamed from: com.baidu.android.pushservice.f */
public final class C1404f {
    /* renamed from: a */
    static int f4905a = -1;
    /* renamed from: e */
    private static Boolean f4906e = Boolean.valueOf(false);
    /* renamed from: n */
    private static volatile C1404f f4907n;
    /* renamed from: A */
    private final int f4908A = 30;
    /* renamed from: B */
    private String f4909B;
    /* renamed from: b */
    Handler f4910b = new Handler();
    /* renamed from: c */
    C1505e f4911c;
    /* renamed from: d */
    private boolean f4912d = false;
    /* renamed from: f */
    private boolean f4913f = false;
    /* renamed from: g */
    private HashMap<Long, C1361a> f4914g = new HashMap();
    /* renamed from: h */
    private C1401b f4915h;
    /* renamed from: i */
    private C1400a f4916i;
    /* renamed from: j */
    private boolean f4917j = false;
    /* renamed from: k */
    private int f4918k = 0;
    /* renamed from: l */
    private Context f4919l;
    /* renamed from: m */
    private boolean f4920m = true;
    /* renamed from: o */
    private boolean f4921o;
    /* renamed from: p */
    private String f4922p = C1457i.m6630c();
    /* renamed from: q */
    private int f4923q = C1457i.m6631d();
    /* renamed from: r */
    private Thread f4924r;
    /* renamed from: s */
    private Runnable f4925s = new C13962();
    /* renamed from: t */
    private Runnable f4926t = new C13973();
    /* renamed from: u */
    private long f4927u = 0;
    /* renamed from: v */
    private int[] f4928v = new int[]{180, 300, 360, 420, 540, 720, 900};
    /* renamed from: w */
    private int f4929w = 0;
    /* renamed from: x */
    private int f4930x = 0;
    /* renamed from: y */
    private final int f4931y = 3;
    /* renamed from: z */
    private int f4932z = 0;

    /* renamed from: com.baidu.android.pushservice.f$1 */
    class C13951 implements Runnable {
        C13951() {
        }

        public void run() {
            C1404f.f4905a = PushSocket.createSocket(C1404f.this.f4922p, C1404f.this.f4923q);
            if (C1328a.m6006b() > 0) {
                C1446k c1446k = new C1446k();
                c1446k.f5036f = "039907";
                c1446k.f5037g = System.currentTimeMillis();
                c1446k.f5038h = C1432b.m6486c(C1404f.this.f4919l);
                if (C1404f.f4905a >= 0) {
                    c1446k.f5039i = 0;
                } else {
                    c1446k.f5039i = C1404f.f4905a;
                }
                C1456u.m6616b(C1404f.this.f4919l, c1446k);
            }
            if (C1404f.f4905a == -1 || C1404f.f4905a == -2) {
                int lastSocketError = PushSocket.getLastSocketError();
                C1426b.m6447b("PushConnection", "Create socket err, errno: " + lastSocketError + "socketfd: " + C1404f.f4905a, C1404f.this.f4919l.getApplicationContext());
                if (C1457i.m6630c().equals(C1404f.this.f4922p)) {
                    C1404f.this.m6279a("030301", lastSocketError);
                } else {
                    C1404f.this.m6279a("030303", 10002);
                }
                if (C1404f.f4905a == -2) {
                    String a = C1457i.m6621a(C1404f.this.f4919l, C1404f.this.f4920m);
                    C1404f.this.f4920m = false;
                    if (!TextUtils.isEmpty(a)) {
                        C1404f.this.f4922p = a;
                    }
                }
                if (C1404f.f4905a == -1 && lastSocketError == 110) {
                    C1404f.this.f4923q = 80;
                }
                C1404f.f4906e = Boolean.valueOf(false);
                C1404f.this.m6293i();
                C1578v.m7095b("PushConnection Create socket err " + C1404f.this.f4919l.getPackageName() + " lastSocketError " + lastSocketError + " socketfd " + C1404f.f4905a + System.currentTimeMillis(), C1404f.this.f4919l.getApplicationContext());
                return;
            }
            C1426b.m6445a("PushConnection", "create Socket ok", C1404f.this.f4919l.getApplicationContext());
            C1578v.m7095b("create Socket ok socketfd" + C1404f.f4905a, C1404f.this.f4919l);
            C1404f.this.f4911c = new C1507g(C1404f.this.f4919l.getApplicationContext());
            C1404f.this.f4912d = true;
            if (C1404f.this.f4916i != null) {
                C1404f.this.f4916i.interrupt();
            }
            if (C1404f.this.f4915h != null) {
                C1404f.this.f4915h.interrupt();
            }
            C1404f.this.f4913f = false;
            C1404f.this.f4916i = new C1400a();
            C1404f.this.f4916i.start();
            C1404f.this.f4915h = new C1401b();
            C1404f.this.f4915h.start();
            C1404f.this.f4911c.mo13978a(C1404f.f4905a);
            if (!C1457i.m6630c().equals(C1404f.this.f4922p)) {
                C1404f.this.m6279a("030302", 0);
            }
            C1404f.f4906e = Boolean.valueOf(false);
            C1404f.this.f4920m = true;
            C1404f.this.f4922p = C1457i.m6630c();
            C1457i.m6628b(C1404f.this.f4919l);
        }
    }

    /* renamed from: com.baidu.android.pushservice.f$2 */
    class C13962 implements Runnable {
        C13962() {
        }

        public void run() {
            C1404f.this.m6291h();
        }
    }

    /* renamed from: com.baidu.android.pushservice.f$3 */
    class C13973 implements Runnable {
        C13973() {
        }

        public void run() {
            C1426b.m6448c("PushConnection", " -- Send Timeout --", C1404f.this.f4919l.getApplicationContext());
            if (C1404f.this.f4921o) {
                C1404f.this.f4921o = false;
            }
            C1404f.this.mo13746a(false);
            C1404f.this.m6293i();
            C1578v.m7095b("PushConnection Send Timeout " + C1404f.this.f4919l.getPackageName() + " lastSocketError " + PushSocket.getLastSocketError() + " socketfd " + C1404f.f4905a + System.currentTimeMillis(), C1404f.this.f4919l.getApplicationContext());
            if (C1328a.m6006b() > 0) {
                C1446k c1446k = new C1446k();
                c1446k.f5036f = "039911";
                c1446k.f5037g = System.currentTimeMillis();
                c1446k.f5038h = C1432b.m6486c(C1404f.this.f4919l);
                c1446k.f5039i = C1404f.f4905a;
                C1456u.m6616b(C1404f.this.f4919l, c1446k);
            }
        }
    }

    /* renamed from: com.baidu.android.pushservice.f$a */
    class C1400a extends Thread {
        C1400a() {
            setName("PushService-PushConnection-readThread");
        }

        public void run() {
            while (!C1404f.this.f4913f) {
                byte[] a;
                try {
                    a = PushSocket.m6673a(C1404f.this.f4919l, C1404f.f4905a);
                } catch (Exception e) {
                    Throwable th = e;
                    a = null;
                    if (C1328a.m6006b() > 0) {
                        C1456u.m6614a(C1404f.this.f4919l, "039908", PushSocket.getLastSocketError(), C1578v.m7070a(th));
                    }
                    C1426b.m6447b("PushConnection", "Get message exception", C1404f.this.f4919l.getApplicationContext());
                }
                C1404f.this.f4910b.removeCallbacks(C1404f.this.f4926t);
                if (C1404f.this.f4921o) {
                    C1404f.this.f4921o = false;
                    C1404f.this.mo13746a(true);
                }
                if (a == null || a.length == 0) {
                    int lastSocketError = PushSocket.getLastSocketError();
                    C1426b.m6445a("PushConnection", "Receive err,errno:" + lastSocketError, C1404f.this.f4919l.getApplicationContext());
                    C1404f.this.m6279a("039913", lastSocketError);
                    C1404f.this.m6293i();
                    C1578v.m7095b("PushConnection Receive err " + C1404f.this.f4919l.getPackageName() + " lastSocketError " + lastSocketError + " socketfd " + C1404f.f4905a + System.currentTimeMillis(), C1404f.this.f4919l.getApplicationContext());
                } else {
                    try {
                        C1506f a2 = C1404f.this.f4911c.mo13976a(a, a.length);
                        if (a2 != null) {
                            try {
                                C1425a.m6442c("PushConnection", "ReadThread receive msg :" + a2.toString());
                                C1404f.this.f4911c.mo13981b(a2);
                            } catch (Exception e2) {
                                C1426b.m6447b("PushConnection", "Handle message exception " + C1578v.m7070a(e2), C1404f.this.f4919l.getApplicationContext());
                                C1578v.m7095b("PushConnection Handle message exception " + C1404f.this.f4919l.getPackageName() + C1578v.m7070a(e2) + " lastSocketError " + PushSocket.getLastSocketError() + " socketfd " + C1404f.f4905a + System.currentTimeMillis(), C1404f.this.f4919l.getApplicationContext());
                                if (C1328a.m6006b() > 0) {
                                    C1456u.m6614a(C1404f.this.f4919l, "039910", PushSocket.getLastSocketError(), C1578v.m7070a(e2));
                                }
                                C1404f.this.m6293i();
                            }
                        }
                        C1404f.this.f4918k = 0;
                    } catch (Exception e22) {
                        C1426b.m6448c("PushConnection", "Read message exception " + C1578v.m7070a(e22), C1404f.this.f4919l.getApplicationContext());
                        if (C1328a.m6006b() > 0) {
                            C1456u.m6614a(C1404f.this.f4919l, "039909", PushSocket.getLastSocketError(), C1578v.m7070a(e22));
                        }
                        C1404f.this.m6293i();
                        C1578v.m7095b("PushConnection Read message exception " + C1404f.this.f4919l.getPackageName() + C1578v.m7070a(e22) + " lastSocketError " + PushSocket.getLastSocketError() + " socketfd " + C1404f.f4905a + System.currentTimeMillis(), C1404f.this.f4919l.getApplicationContext());
                    }
                }
            }
        }
    }

    /* renamed from: com.baidu.android.pushservice.f$b */
    class C1401b extends Thread {
        C1401b() {
            setName("PushService-PushConnection-SendThread");
        }

        public void run() {
            while (!C1404f.this.f4913f) {
                C1506f c1506f;
                synchronized (C1404f.this.f4911c.mo13977a()) {
                    if (C1404f.this.f4911c.mo13977a().size() == 0) {
                        try {
                            C1404f.this.f4911c.mo13977a().wait();
                        } catch (InterruptedException e) {
                            C1425a.m6444e("PushConnection", "SendThread wait exception: " + e.getMessage());
                        }
                    }
                    c1506f = C1404f.this.f4911c.mo13977a().size() > 0 ? (C1506f) C1404f.this.f4911c.mo13977a().removeFirst() : null;
                }
                if (!C1404f.this.f4913f) {
                    if (!(c1506f == null || c1506f.mo13985a() == null)) {
                        C1425a.m6442c("PushConnection", "SendThread send msg :" + c1506f.toString());
                        if (c1506f.mo13986b()) {
                            if (c1506f.mo13987c()) {
                                C1404f.this.f4921o = true;
                            } else {
                                C1404f.this.f4921o = false;
                            }
                            C1404f.this.f4910b.removeCallbacks(C1404f.this.f4926t);
                            C1404f.this.f4910b.postDelayed(C1404f.this.f4926t, 60000);
                        }
                        if (PushSocket.sendMsg(C1404f.f4905a, c1506f.mo13985a(), c1506f.mo13985a().length) == -1) {
                            C1425a.m6442c("PushConnection", "sendMsg err, errno:" + PushSocket.getLastSocketError());
                            C1404f.this.m6293i();
                            C1578v.m7095b("PushConnection sendMsg err " + C1404f.this.f4919l.getPackageName() + " lastSocketError " + PushSocket.getLastSocketError() + " socketfd " + C1404f.f4905a + System.currentTimeMillis(), C1404f.this.f4919l.getApplicationContext());
                        }
                    }
                } else {
                    return;
                }
            }
        }
    }

    private C1404f(Context context) {
        this.f4919l = context;
        int g = mo13753g();
        if (g >= 0 && g < this.f4928v.length) {
            this.f4929w = g;
        }
        m6299l();
        C1427h.m6451a(this.f4919l).mo13828a(this.f4928v[this.f4929w]);
        this.f4909B = C1548l.m6951d(this.f4919l);
    }

    /* renamed from: a */
    public static C1404f m6274a(Context context) {
        if (f4907n == null) {
            f4907n = new C1404f(context);
        }
        return f4907n;
    }

    /* renamed from: a */
    private void m6279a(String str, int i) {
        final String str2 = str;
        final int i2 = i;
        C1462d.m6637a().mo13938a(new C1281c("insertAgentBehavior", (short) 95) {
            /* renamed from: a */
            public void mo13487a() {
                try {
                    C1446k c1446k = new C1446k();
                    c1446k.f5036f = str2;
                    c1446k.f5037g = System.currentTimeMillis();
                    c1446k.f5038h = C1432b.m6486c(C1404f.this.f4919l);
                    c1446k.f5039i = i2;
                    if (str2.equals("030303")) {
                        c1446k.f5042l = C1578v.m7151w(C1404f.this.f4919l);
                    } else if (str2.equals("030301")) {
                        c1446k.f5042l = C1578v.m7154x(C1404f.this.f4919l);
                    }
                    C1456u.m6616b(C1404f.this.f4919l, c1446k);
                } catch (Exception e) {
                    C1426b.m6448c("PushConnection", "insertAgent exception", C1404f.this.f4919l.getApplicationContext());
                }
            }
        });
    }

    /* renamed from: h */
    private synchronized void m6291h() {
        if (this.f4912d || f4906e.booleanValue()) {
            C1426b.m6448c("PushConnection", "Connect return. mConnected:" + this.f4912d + " mConnectting:" + f4906e, this.f4919l.getApplicationContext());
        } else if (C1475k.m6721a(this.f4919l).mo13950c()) {
            C1578v.m7095b("PushConnection connectImpl from " + this.f4919l.getPackageName() + " at Time " + System.currentTimeMillis(), this.f4919l);
            f4906e = Boolean.valueOf(true);
            f4905a = -1;
            C13951 c13951 = new C13951();
            if (this.f4924r != null) {
                this.f4924r.interrupt();
            }
            this.f4924r = new Thread(c13951);
            this.f4924r.setName("PushService-PushService-connect");
            this.f4924r.start();
        } else {
            C1426b.m6445a("PushConnection", "re-token", this.f4919l.getApplicationContext());
            C1427h.m6451a(this.f4919l).mo13832d();
        }
    }

    /* renamed from: i */
    private void m6293i() {
        C1426b.m6448c("PushConnection", "disconnectedByPeer, mStoped == " + this.f4917j, this.f4919l.getApplicationContext());
        C1578v.m7095b("PushConnection destroy from " + this.f4919l.getPackageName() + " at Time " + System.currentTimeMillis(), this.f4919l);
        m6295j();
        if (!this.f4917j) {
            this.f4918k++;
            if (this.f4918k < 3) {
                this.f4910b.removeCallbacks(this.f4925s);
                int i = ((this.f4918k - 1) * 30) * 1000;
                if (this.f4918k == 1) {
                    i = 3000;
                }
                this.f4910b.postDelayed(this.f4925s, (long) i);
                C1426b.m6448c("PushConnection", "Schedule retry-- retry times: " + this.f4918k + " time delay: " + i, this.f4919l.getApplicationContext());
            }
        }
    }

    /* renamed from: j */
    private void m6295j() {
        C1426b.m6448c("PushConnection", "destroy", this.f4919l.getApplicationContext());
        if (this.f4910b != null) {
            this.f4910b.removeCallbacks(this.f4926t);
        }
        this.f4913f = true;
        this.f4912d = false;
        if (this.f4911c != null) {
            try {
                synchronized (this.f4911c.mo13977a()) {
                    this.f4911c.mo13977a().notifyAll();
                }
            } catch (Exception e) {
                C1425a.m6444e("PushConnection", "notifyAll Exception on destroy: " + e.getMessage());
            }
        }
        PushSocket.m6671a(f4905a);
        if (this.f4911c != null) {
            this.f4911c.mo13980b();
        }
    }

    /* renamed from: k */
    private void m6298k() {
        Set<Long> keySet = this.f4914g.keySet();
        long currentTimeMillis = System.currentTimeMillis();
        C1463j c = C1427h.m6451a(this.f4919l).mo13831c();
        if (c != null) {
            for (Long longValue : keySet) {
                long longValue2 = longValue.longValue();
                if (longValue2 < currentTimeMillis) {
                    c.mo13941a((C1361a) this.f4914g.get(Long.valueOf(longValue2)));
                    this.f4914g.remove(Long.valueOf(longValue2));
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00b2 A:{SYNTHETIC, Splitter:B:29:0x00b2} */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00de A:{SYNTHETIC, Splitter:B:36:0x00de} */
    /* renamed from: l */
    private void m6299l() {
        /*
        r6 = this;
        r0 = 0;
        r3 = new java.io.File;
        r1 = android.os.Environment.getExternalStorageDirectory();
        r2 = "baidu/pushservice/pushservice.cfg";
        r3.<init>(r1, r2);
        r1 = r3.exists();
        if (r1 == 0) goto L_0x0067;
    L_0x0012:
        r4 = new java.util.Properties;
        r4.<init>();
        r2 = 0;
        r1 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x008c, all -> 0x00da }
        r1.<init>(r3);	 Catch:{ Exception -> 0x008c, all -> 0x00da }
        r4.load(r1);	 Catch:{ Exception -> 0x0108 }
        r2 = "rtcseed";
        r2 = r4.getProperty(r2);	 Catch:{ Exception -> 0x0108 }
        if (r2 == 0) goto L_0x004e;
    L_0x0028:
        r3 = r2.length();	 Catch:{ Exception -> 0x0108 }
        if (r3 <= 0) goto L_0x004e;
    L_0x002e:
        r3 = new org.json.JSONArray;	 Catch:{ Exception -> 0x0108 }
        r2 = com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation.init(r2);	 Catch:{ Exception -> 0x0108 }
    L_0x0034:
        r3 = r2.length();	 Catch:{ Exception -> 0x0108 }
        if (r0 >= r3) goto L_0x004e;
    L_0x003a:
        r3 = r6.f4928v;	 Catch:{ Exception -> 0x0108 }
        r5 = r2.getInt(r0);	 Catch:{ Exception -> 0x0108 }
        r3[r0] = r5;	 Catch:{ Exception -> 0x0108 }
        r3 = 0;
        r6.f4929w = r3;	 Catch:{ Exception -> 0x0108 }
        r3 = 0;
        r6.f4930x = r3;	 Catch:{ Exception -> 0x0108 }
        r3 = 0;
        r6.f4932z = r3;	 Catch:{ Exception -> 0x0108 }
        r0 = r0 + 1;
        goto L_0x0034;
    L_0x004e:
        r0 = "originseed";
        r0 = r4.getProperty(r0);	 Catch:{ Exception -> 0x0108 }
        if (r0 == 0) goto L_0x0062;
    L_0x0056:
        r2 = r0.length();	 Catch:{ Exception -> 0x0108 }
        if (r2 <= 0) goto L_0x0062;
    L_0x005c:
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ Exception -> 0x0108 }
        r6.f4929w = r0;	 Catch:{ Exception -> 0x0108 }
    L_0x0062:
        if (r1 == 0) goto L_0x0067;
    L_0x0064:
        r1.close();	 Catch:{ IOException -> 0x0068 }
    L_0x0067:
        return;
    L_0x0068:
        r0 = move-exception;
        r1 = "PushConnection";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "error ";
        r2 = r2.append(r3);
        r0 = r0.getMessage();
        r0 = r2.append(r0);
        r0 = r0.toString();
        r2 = r6.f4919l;
        r2 = r2.getApplicationContext();
        com.baidu.android.pushservice.p036h.C1426b.m6447b(r1, r0, r2);
        goto L_0x0067;
    L_0x008c:
        r0 = move-exception;
        r1 = r2;
    L_0x008e:
        r2 = "PushConnection";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0106 }
        r3.<init>();	 Catch:{ all -> 0x0106 }
        r4 = "getTestConfig exception ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0106 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0106 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0106 }
        r0 = r0.toString();	 Catch:{ all -> 0x0106 }
        r3 = r6.f4919l;	 Catch:{ all -> 0x0106 }
        r3 = r3.getApplicationContext();	 Catch:{ all -> 0x0106 }
        com.baidu.android.pushservice.p036h.C1426b.m6447b(r2, r0, r3);	 Catch:{ all -> 0x0106 }
        if (r1 == 0) goto L_0x0067;
    L_0x00b2:
        r1.close();	 Catch:{ IOException -> 0x00b6 }
        goto L_0x0067;
    L_0x00b6:
        r0 = move-exception;
        r1 = "PushConnection";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "error ";
        r2 = r2.append(r3);
        r0 = r0.getMessage();
        r0 = r2.append(r0);
        r0 = r0.toString();
        r2 = r6.f4919l;
        r2 = r2.getApplicationContext();
        com.baidu.android.pushservice.p036h.C1426b.m6447b(r1, r0, r2);
        goto L_0x0067;
    L_0x00da:
        r0 = move-exception;
        r1 = r2;
    L_0x00dc:
        if (r1 == 0) goto L_0x00e1;
    L_0x00de:
        r1.close();	 Catch:{ IOException -> 0x00e2 }
    L_0x00e1:
        throw r0;
    L_0x00e2:
        r1 = move-exception;
        r2 = "PushConnection";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "error ";
        r3 = r3.append(r4);
        r1 = r1.getMessage();
        r1 = r3.append(r1);
        r1 = r1.toString();
        r3 = r6.f4919l;
        r3 = r3.getApplicationContext();
        com.baidu.android.pushservice.p036h.C1426b.m6447b(r2, r1, r3);
        goto L_0x00e1;
    L_0x0106:
        r0 = move-exception;
        goto L_0x00dc;
    L_0x0108:
        r0 = move-exception;
        goto L_0x008e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.C1404f.m6299l():void");
    }

    /* renamed from: a */
    public void mo13746a(boolean z) {
        String d = C1548l.m6951d(this.f4919l);
        String str;
        if (TextUtils.equals(this.f4909B, d)) {
            int e = mo13751e();
            if (!z) {
                this.f4930x = 0;
                this.f4932z = 0;
                if (!C1548l.m6948a(this.f4919l)) {
                    this.f4929w++;
                } else if (this.f4929w > 0) {
                    this.f4929w--;
                    mo13752f();
                }
            } else if (C1548l.m6948a(this.f4919l)) {
                mo13752f();
                this.f4930x++;
                if (this.f4930x >= 3) {
                    this.f4930x = 0;
                    if (this.f4929w < this.f4928v.length - 1) {
                        this.f4930x = 0;
                        this.f4929w++;
                    }
                }
                if (this.f4932z >= 30) {
                    this.f4932z = 0;
                    C1446k c1446k = new C1446k();
                    c1446k.f5036f = "030101";
                    c1446k.f5037g = System.currentTimeMillis();
                    c1446k.f5038h = C1432b.m6486c(this.f4919l);
                    c1446k.f5105a = mo13751e();
                    C1456u.m6611a(this.f4919l, c1446k);
                }
            } else {
                this.f4929w++;
            }
            str = "RTC stat change from " + e + " to " + mo13751e();
            C1425a.m6442c("PushConnection", str);
            C1578v.m7095b(str, this.f4919l);
        } else {
            this.f4929w = mo13753g();
            this.f4930x = 0;
            str = "RTC stat change " + mo13751e() + " because of network changing";
            C1425a.m6442c("PushConnection", str);
            C1578v.m7095b(str, this.f4919l);
        }
        this.f4909B = d;
        C1427h.m6451a(this.f4919l).mo13828a(mo13751e());
    }

    /* renamed from: a */
    public boolean mo13747a() {
        return this.f4912d;
    }

    /* renamed from: b */
    public void mo13748b() {
        this.f4918k = 0;
        this.f4917j = false;
        m6291h();
    }

    /* renamed from: c */
    public void mo13749c() {
        C1426b.m6448c("PushConnection", "---stop---", this.f4919l.getApplicationContext());
        C1578v.m7095b("PushConnection stop from " + this.f4919l.getPackageName() + " at Time " + System.currentTimeMillis(), this.f4919l);
        this.f4913f = true;
        this.f4917j = true;
        this.f4910b.removeCallbacks(this.f4925s);
        m6295j();
        f4907n = null;
    }

    /* renamed from: d */
    public void mo13750d() {
        if (this.f4911c != null) {
            if (System.currentTimeMillis() - this.f4927u > PayloadController.PAYLOAD_REQUEUE_PERIOD_MS) {
                C1462d.m6637a().mo13938a(new C1281c("heartbeat", (short) 98) {
                    /* renamed from: a */
                    public void mo13487a() {
                        long currentTimeMillis = System.currentTimeMillis();
                        int i = ((int) (currentTimeMillis / 1000)) % 60;
                        if (((int) ((currentTimeMillis / 60000) % 5)) == 0 && i < 15) {
                            long random = (long) ((Math.random() * 60.0d) * 1000.0d);
                            C1425a.m6442c("PushConnection", "sleep for current: " + currentTimeMillis + " delta: " + random);
                            try {
                                Thread.sleep(random);
                            } catch (InterruptedException e) {
                                C1425a.m6444e("PushConnection", "InterruptedException: " + e);
                            }
                        }
                        C1404f.this.f4911c.mo13982c();
                        C1404f.this.f4927u = System.currentTimeMillis();
                        C1426b.m6448c("PushConnection", "sendHeartbeatMessage", C1404f.this.f4919l.getApplicationContext());
                    }
                });
            } else {
                C1426b.m6448c("PushConnection", "sendHeartbeatMessage ingnored， because too frequent.", this.f4919l.getApplicationContext());
            }
        }
        m6298k();
    }

    /* renamed from: e */
    public int mo13751e() {
        if (this.f4929w < 0) {
            this.f4929w = 0;
        } else if (this.f4929w >= this.f4928v.length) {
            this.f4929w = this.f4928v.length - 1;
        }
        return this.f4928v[this.f4929w];
    }

    /* renamed from: f */
    public void mo13752f() {
        if (C1548l.m6949b(this.f4919l)) {
            C1550n.m6956a(this.f4919l, "com.baidu.pushservice.CUR_PERIOD_MOBILE", this.f4929w);
        } else {
            C1550n.m6956a(this.f4919l, "com.baidu.pushservice.CUR_PERIOD_MOBILE", this.f4929w);
        }
    }

    /* renamed from: g */
    public int mo13753g() {
        if (C1548l.m6948a(this.f4919l)) {
            return C1548l.m6949b(this.f4919l) ? C1550n.m6960b(this.f4919l, "com.baidu.pushservice.CUR_PERIOD_MOBILE", 0) : C1550n.m6960b(this.f4919l, "com.baidu.pushservice.CUR_PERIOD_MOBILE", 0);
        } else {
            C1425a.m6444e("PushConnection", "getCachedPeriod mContext == null");
            return 0;
        }
    }
}
