package com.amap.api.services.core;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import org.apache.http.HttpEntity;

/* compiled from: NetManger */
/* renamed from: com.amap.api.services.core.br */
public class C1123br {
    /* renamed from: a */
    private static C1123br f3782a;
    /* renamed from: b */
    private C1092au f3783b;
    /* renamed from: c */
    private Handler f3784c;

    /* compiled from: NetManger */
    /* renamed from: com.amap.api.services.core.br$1 */
    class C11211 extends C1095aw {
        /* renamed from: b */
        final /* synthetic */ C1075bs f3779b;
        /* renamed from: c */
        final /* synthetic */ C1124bt f3780c;
        /* renamed from: d */
        final /* synthetic */ C1123br f3781d;

        /* renamed from: a */
        public void mo12035a() {
            try {
                this.f3781d.m4958a(this.f3781d.mo12095b(this.f3779b), this.f3780c);
            } catch (C1133u e) {
                this.f3781d.m4957a(e, this.f3780c);
            }
        }
    }

    /* compiled from: NetManger */
    /* renamed from: com.amap.api.services.core.br$a */
    static class C1122a extends Handler {
        /* synthetic */ C1122a(Looper looper, C11211 c11211) {
            this(looper);
        }

        private C1122a(Looper looper) {
            super(looper);
            Looper.prepare();
        }

        public void handleMessage(Message message) {
            try {
                switch (message.what) {
                    case 0:
                        ((C1126bv) message.obj).f3786b.mo12097a();
                        return;
                    case 1:
                        C1126bv c1126bv = (C1126bv) message.obj;
                        c1126bv.f3786b.mo12098a(c1126bv.f3785a);
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
    public static C1123br m4953a(boolean z) {
        return C1123br.m4954a(z, 5);
    }

    /* renamed from: a */
    private static synchronized C1123br m4954a(boolean z, int i) {
        C1123br c1123br;
        synchronized (C1123br.class) {
            try {
                if (f3782a == null) {
                    f3782a = new C1123br(z, i);
                } else if (z) {
                    if (f3782a.f3783b == null) {
                        f3782a.f3783b = C1092au.m4785a(i);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            c1123br = f3782a;
        }
        return c1123br;
    }

    private C1123br(boolean z, int i) {
        if (z) {
            try {
                this.f3783b = C1092au.m4785a(i);
            } catch (Throwable th) {
                C1099ax.m4800a(th, "NetManger", "NetManger1");
                th.printStackTrace();
                return;
            }
        }
        if (Looper.myLooper() == null) {
            this.f3784c = new C1122a(Looper.getMainLooper(), null);
        } else {
            this.f3784c = new C1122a();
        }
    }

    /* renamed from: a */
    public byte[] mo12094a(C1075bs c1075bs) throws C1133u {
        C1133u e;
        try {
            Proxy proxy;
            m4961d(c1075bs);
            if (c1075bs.f3611g == null) {
                proxy = null;
            } else {
                proxy = new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c1075bs.f3611g.getHostName(), c1075bs.f3611g.getPort()));
            }
            return m4959a(c1075bs, new C1120bp(c1075bs.f3609e, c1075bs.f3610f, proxy));
        } catch (C1133u e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            e2 = new C1133u("未知的错误");
        }
    }

    /* renamed from: b */
    public byte[] mo12095b(C1075bs c1075bs) throws C1133u {
        C1133u e;
        try {
            Proxy proxy;
            m4961d(c1075bs);
            if (c1075bs.f3611g == null) {
                proxy = null;
            } else {
                proxy = new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c1075bs.f3611g.getHostName(), c1075bs.f3611g.getPort()));
            }
            return m4960b(c1075bs, new C1120bp(c1075bs.f3609e, c1075bs.f3610f, proxy));
        } catch (C1133u e2) {
            throw e2;
        } catch (Throwable th) {
            e2 = new C1133u("未知的错误");
        }
    }

    /* renamed from: c */
    public byte[] mo12096c(C1075bs c1075bs) throws C1133u {
        C1133u e;
        try {
            Proxy proxy;
            m4961d(c1075bs);
            if (c1075bs.f3611g == null) {
                proxy = null;
            } else {
                proxy = new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(c1075bs.f3611g.getHostName(), c1075bs.f3611g.getPort()));
            }
            return m4959a(c1075bs, new C1120bp(c1075bs.f3609e, c1075bs.f3610f, proxy, true));
        } catch (C1133u e2) {
            throw e2;
        } catch (Throwable th) {
            e2 = new C1133u("未知的错误");
        }
    }

    /* renamed from: a */
    private byte[] m4959a(C1075bs c1075bs, C1120bp c1120bp) throws C1133u {
        C1133u e;
        HttpEntity e2 = c1075bs.mo11974e();
        byte[] e_ = c1075bs.mo11975e_();
        if (e2 == null && e_ == null) {
            try {
                return c1120bp.mo12092b(c1075bs.mo11971b(), c1075bs.mo11973d_(), c1075bs.mo11972c_());
            } catch (C1133u e3) {
                throw e3;
            } catch (Throwable th) {
                th.printStackTrace();
                e3 = new C1133u("未知的错误");
            }
        } else if (e_ != null) {
            return c1120bp.mo12091a(c1075bs.mo11971b(), c1075bs.mo11973d_(), e_);
        } else {
            return c1120bp.mo12090a(c1075bs.mo11971b(), c1075bs.mo11973d_(), e2);
        }
    }

    /* renamed from: b */
    private byte[] m4960b(C1075bs c1075bs, C1120bp c1120bp) throws C1133u {
        C1133u e;
        try {
            return c1120bp.mo12089a(c1075bs.mo11971b(), c1075bs.mo11973d_(), c1075bs.mo11972c_());
        } catch (C1133u e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            e2 = new C1133u("未知的错误");
        }
    }

    /* renamed from: d */
    private void m4961d(C1075bs c1075bs) throws C1133u {
        if (c1075bs == null) {
            throw new C1133u("requeust is null");
        } else if (c1075bs.mo11971b() == null || "".equals(c1075bs.mo11971b())) {
            throw new C1133u("request url is empty");
        }
    }

    /* renamed from: a */
    private void m4957a(C1133u c1133u, C1124bt c1124bt) {
        C1126bv c1126bv = new C1126bv();
        c1126bv.f3785a = c1133u;
        c1126bv.f3786b = c1124bt;
        Message message = new Message();
        message.obj = c1126bv;
        message.what = 1;
        this.f3784c.sendMessage(message);
    }

    /* renamed from: a */
    private void m4958a(byte[] bArr, C1124bt c1124bt) {
        c1124bt.mo12099a(bArr);
        C1126bv c1126bv = new C1126bv();
        c1126bv.f3786b = c1124bt;
        Message message = new Message();
        message.obj = c1126bv;
        message.what = 0;
        this.f3784c.sendMessage(message);
    }
}
