package com.amap.api.services.core;

import android.content.Context;
import java.util.List;

/* compiled from: LogDBOperation */
/* renamed from: com.amap.api.services.core.aj */
public class C1085aj {
    /* renamed from: a */
    private C1084ah f3656a;

    public C1085aj(Context context) {
        this.f3656a = new C1084ah(context);
    }

    /* renamed from: a */
    private LogEntity m4738a(int i) {
        switch (i) {
            case 0:
                return new CrashLogEntity();
            case 1:
                return new ExceptionLogEntity();
            case 2:
                return new AnrLogEntity();
            default:
                return null;
        }
    }

    /* renamed from: a */
    public void mo12008a(String str, int i) {
        try {
            m4741c(str, i);
        } catch (Throwable th) {
            C1099ax.m4800a(th, "LogDB", "delLog");
            th.printStackTrace();
        }
    }

    /* renamed from: b */
    public void mo12010b(String str, int i) {
        try {
            m4741c(str, i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: c */
    private void m4741c(String str, int i) {
        this.f3656a.mo12003a(LogEntity.m4723a(str), m4738a(i));
    }

    /* renamed from: a */
    public void mo12007a(C1086al c1086al, int i) {
        try {
            LogEntity a = m4738a(i);
            a.mo11995a(c1086al);
            this.f3656a.mo12004b(LogEntity.m4723a(c1086al.mo12014b()), a);
        } catch (Throwable th) {
            C1099ax.m4800a(th, "LogDB", "updateLogInfo");
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public List<C1086al> mo12006a(int i, int i2) {
        List<C1086al> list = null;
        try {
            LogEntity a = m4738a(i2);
            return this.f3656a.mo12005c(LogEntity.m4722a(i), a);
        } catch (Throwable th) {
            C1099ax.m4800a(th, "LogDB", "ByState");
            th.printStackTrace();
            return list;
        }
    }

    /* renamed from: b */
    public void mo12009b(C1086al c1086al, int i) {
        try {
            LogEntity a = m4738a(i);
            switch (i) {
                case 0:
                    m4739a(c1086al, a);
                    return;
                case 1:
                    m4740b(c1086al, a);
                    return;
                case 2:
                    m4740b(c1086al, a);
                    return;
                default:
                    return;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        th.printStackTrace();
    }

    /* renamed from: a */
    private void m4739a(C1086al c1086al, LogEntity logEntity) {
        logEntity.mo11995a(c1086al);
        this.f3656a.mo12002a(logEntity);
    }

    /* renamed from: b */
    private void m4740b(C1086al c1086al, LogEntity logEntity) {
        String a = LogEntity.m4723a(c1086al.mo12014b());
        List c = this.f3656a.mo12005c(a, logEntity);
        if (c == null || c.size() == 0) {
            logEntity.mo11995a(c1086al);
            this.f3656a.mo12002a(logEntity);
            return;
        }
        C1086al c1086al2 = (C1086al) c.get(0);
        if (c1086al.mo12011a() == 0) {
            c1086al2.mo12015b(c1086al2.mo12018d() + 1);
        } else {
            c1086al2.mo12015b(0);
        }
        logEntity.mo11995a(c1086al2);
        this.f3656a.mo12004b(a, logEntity);
    }
}
