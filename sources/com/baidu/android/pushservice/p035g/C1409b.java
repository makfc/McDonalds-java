package com.baidu.android.pushservice.p035g;

import android.os.RemoteException;
import com.baidu.android.pushservice.p027b.C1284b;
import com.baidu.android.pushservice.p036h.C1425a;

/* renamed from: com.baidu.android.pushservice.g.b */
public class C1409b implements C1408a {
    /* renamed from: a */
    private C1284b f4937a;

    public C1409b(C1284b c1284b) {
        this.f4937a = c1284b;
    }

    /* renamed from: a */
    public void mo13756a(int i) {
    }

    /* renamed from: a */
    public void mo13757a(int i, String str) {
        try {
            this.f4937a.mo13495b(i, str);
        } catch (Exception e) {
            C1425a.m6440a("LightApiListenerForAidl", e);
        }
    }

    /* renamed from: a */
    public void mo13758a(int i, String str, boolean z) {
    }

    /* renamed from: b */
    public void mo13759b(int i) {
    }

    /* renamed from: b */
    public void mo13760b(int i, String str) {
        if (this.f4937a != null) {
            try {
                this.f4937a.mo13496c(i, str);
            } catch (RemoteException e) {
                C1425a.m6440a("LightApiListenerForAidl", e);
            }
        }
    }

    /* renamed from: c */
    public void mo13761c(int i) {
    }

    /* renamed from: c */
    public void mo13762c(int i, String str) {
        if (this.f4937a != null) {
            try {
                this.f4937a.mo13495b(i, str);
            } catch (RemoteException e) {
                C1425a.m6440a("LightApiListenerForAidl", e);
            }
        }
    }

    /* renamed from: d */
    public void mo13763d(int i) {
    }

    /* renamed from: d */
    public void mo13764d(int i, String str) {
        if (this.f4937a != null) {
            try {
                this.f4937a.mo13494a(i, str);
            } catch (RemoteException e) {
                C1425a.m6440a("LightApiListenerForAidl", e);
            }
        }
    }

    /* renamed from: e */
    public void mo13765e(int i) {
    }

    /* renamed from: e */
    public void mo13766e(int i, String str) {
        try {
            this.f4937a.mo13495b(i, str);
        } catch (RemoteException e) {
            C1425a.m6440a("LightApiListenerForAidl", e);
        }
    }

    /* renamed from: f */
    public void mo13767f(int i, String str) {
    }

    /* renamed from: g */
    public void mo13768g(int i, String str) {
    }

    /* renamed from: h */
    public void mo13769h(int i, String str) {
    }
}
