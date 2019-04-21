package com.baidu.android.pushservice.util;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.baidu.android.pushservice.message.C1508h;
import com.baidu.android.pushservice.p026j.C1281c;
import com.baidu.android.pushservice.p026j.C1462d;
import com.baidu.android.pushservice.p036h.C1425a;

/* renamed from: com.baidu.android.pushservice.util.f */
public class C1539f {
    /* renamed from: c */
    private static int f5376c = 1000;
    /* renamed from: f */
    private static final Object f5377f = new Object();
    /* renamed from: a */
    private long f5378a = System.currentTimeMillis();
    /* renamed from: b */
    private C1536d f5379b;
    /* renamed from: d */
    private Context f5380d;
    /* renamed from: e */
    private Intent f5381e;
    /* renamed from: g */
    private Intent f5382g;

    public C1539f(Context context, Intent intent) {
        this.f5380d = context;
        this.f5381e = intent;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public long mo14061a() {
        return this.f5378a;
    }

    /* renamed from: a */
    public void mo14062a(Intent intent) {
        if (this.f5379b != null) {
            this.f5379b.mo14060a(0, intent);
        }
        this.f5382g = intent;
        synchronized (f5377f) {
            f5377f.notifyAll();
        }
    }

    /* renamed from: b */
    public C1508h mo14063b() {
        this.f5381e.putExtra("bd.cross.request.SOURCE_PACKAGE", this.f5380d.getPackageName());
        this.f5381e.putExtra("bd.cross.request.ID", this.f5378a);
        this.f5381e.putExtra("bd.cross.request.NEED_CALLBACK", true);
        this.f5381e.putExtra("bd.cross.request.SENDING", true);
        C1537e.m6909a(this);
        this.f5380d.startService(this.f5381e);
        C1508h c1508h = new C1508h();
        C1425a.m6442c("CrossAppRequest", "send crossapprequest: " + this.f5381e.toUri(0));
        C1462d.m6637a().mo13938a(new C1281c("timeOutRunnable-" + this.f5378a, (short) 50) {
            /* renamed from: a */
            public void mo13487a() {
                try {
                    Thread.sleep((long) C1539f.f5376c);
                    synchronized (C1539f.f5377f) {
                        C1539f.f5377f.notifyAll();
                    }
                } catch (InterruptedException e) {
                    C1425a.m6438a("CrossAppRequest", "result return, interrupted by callback");
                }
            }
        });
        if (this.f5379b == null) {
            synchronized (f5377f) {
                try {
                    f5377f.wait();
                } catch (Exception e) {
                    C1425a.m6438a("CrossAppRequest", "wait exception: " + e);
                }
            }
            mo14064c();
            if (this.f5382g != null) {
                c1508h.mo13991a(this.f5382g.getIntExtra("bd.cross.request.RESULT_CODE", 10));
                if (this.f5382g.hasExtra("bd.cross.request.RESULT_DATA")) {
                    String stringExtra = this.f5382g.getStringExtra("bd.cross.request.RESULT_DATA");
                    if (!TextUtils.isEmpty(stringExtra)) {
                        c1508h.mo13992a(stringExtra.getBytes());
                    }
                }
            } else {
                c1508h.mo13991a(11);
            }
        }
        return c1508h;
    }

    /* Access modifiers changed, original: declared_synchronized */
    /* renamed from: c */
    public synchronized void mo14064c() {
        this.f5379b = null;
        this.f5380d = null;
        C1537e.m6907a(this.f5378a);
    }
}
