package com.amap.api.mapcore2d;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.net.Proxy;

/* compiled from: NetManger */
/* renamed from: com.amap.api.mapcore2d.ef */
public class C1029ef extends C1021ea {
    /* renamed from: a */
    private static C1029ef f2913a;
    /* renamed from: b */
    private C1034ek f2914b;
    /* renamed from: c */
    private Handler f2915c;

    /* compiled from: NetManger */
    /* renamed from: com.amap.api.mapcore2d.ef$1 */
    class C10271 extends C1025el {
        /* renamed from: a */
        final /* synthetic */ C0931eg f2910a;
        /* renamed from: b */
        final /* synthetic */ C1030eh f2911b;
        /* renamed from: c */
        final /* synthetic */ C1029ef f2912c;

        /* renamed from: a */
        public void mo10280a() {
            try {
                this.f2912c.m4285a(this.f2912c.mo10285b(this.f2910a, false), this.f2911b);
            } catch (C0956cl e) {
                this.f2912c.m4282a(e, this.f2911b);
            }
        }
    }

    /* compiled from: NetManger */
    /* renamed from: com.amap.api.mapcore2d.ef$a */
    static class C1028a extends Handler {
        /* synthetic */ C1028a(Looper looper, C10271 c10271) {
            this(looper);
        }

        private C1028a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            try {
                switch (message.what) {
                    case 0:
                        ((C1032ej) message.obj).f2919b.mo10287a();
                        return;
                    case 1:
                        C1032ej c1032ej = (C1032ej) message.obj;
                        c1032ej.f2919b.mo10288a(c1032ej.f2918a);
                        return;
                    default:
                        return;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public static C1029ef m4280a(boolean z) {
        return C1029ef.m4281a(z, 5);
    }

    /* renamed from: a */
    private static synchronized C1029ef m4281a(boolean z, int i) {
        C1029ef c1029ef;
        synchronized (C1029ef.class) {
            try {
                if (f2913a == null) {
                    f2913a = new C1029ef(z, i);
                } else if (z) {
                    if (f2913a.f2914b == null) {
                        f2913a.f2914b = C1034ek.m4294a(i);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            c1029ef = f2913a;
        }
        return c1029ef;
    }

    private C1029ef(boolean z, int i) {
        if (z) {
            try {
                this.f2914b = C1034ek.m4294a(i);
            } catch (Throwable th) {
                C0990dd.m4098b(th, "NetManger", "NetManger1");
                th.printStackTrace();
                return;
            }
        }
        if (Looper.myLooper() == null) {
            this.f2915c = new C1028a(Looper.getMainLooper(), null);
        } else {
            this.f2915c = new C1028a();
        }
    }

    /* renamed from: b */
    public byte[] mo10270b(C0931eg c0931eg) throws C0956cl {
        C0956cl e;
        try {
            C1031ei a = mo10268a(c0931eg, false);
            if (a != null) {
                return a.f2916a;
            }
            return null;
        } catch (C0956cl e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            C0990dd.m4095a().mo10187c(th, "NetManager", "makeSyncPostRequest");
            e2 = new C0956cl("未知的错误");
        }
    }

    /* renamed from: d */
    public byte[] mo10286d(C0931eg c0931eg) throws C0956cl {
        C0956cl e;
        try {
            C1031ei b = mo10285b(c0931eg, false);
            if (b != null) {
                return b.f2916a;
            }
            return null;
        } catch (C0956cl e2) {
            throw e2;
        } catch (Throwable th) {
            e2 = new C0956cl("未知的错误");
        }
    }

    /* renamed from: b */
    public C1031ei mo10285b(C0931eg c0931eg, boolean z) throws C0956cl {
        C0956cl e;
        try {
            Proxy proxy;
            mo10271c(c0931eg);
            if (c0931eg.f2563e == null) {
                proxy = null;
            } else {
                proxy = c0931eg.f2563e;
            }
            return new C1024ed(c0931eg.f2561c, c0931eg.f2562d, proxy, z).mo10274a(c0931eg.mo10073g(), c0931eg.mo10071e(), c0931eg.mo10072f());
        } catch (C0956cl e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            e2 = new C0956cl("未知的错误");
        }
    }

    /* renamed from: a */
    private void m4282a(C0956cl c0956cl, C1030eh c1030eh) {
        C1032ej c1032ej = new C1032ej();
        c1032ej.f2918a = c0956cl;
        c1032ej.f2919b = c1030eh;
        Message obtain = Message.obtain();
        obtain.obj = c1032ej;
        obtain.what = 1;
        this.f2915c.sendMessage(obtain);
    }

    /* renamed from: a */
    private void m4285a(C1031ei c1031ei, C1030eh c1030eh) {
        c1030eh.mo10289a(c1031ei.f2917b, c1031ei.f2916a);
        C1032ej c1032ej = new C1032ej();
        c1032ej.f2919b = c1030eh;
        Message obtain = Message.obtain();
        obtain.obj = c1032ej;
        obtain.what = 0;
        this.f2915c.sendMessage(obtain);
    }
}
