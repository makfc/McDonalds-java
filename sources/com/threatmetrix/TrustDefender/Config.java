package com.threatmetrix.TrustDefender;

import android.content.Context;
import java.util.concurrent.TimeUnit;

public class Config extends C4463b {
    /* renamed from: a */
    private int f7221a = 30;
    /* renamed from: b */
    private int f7222b = 0;
    /* renamed from: c */
    private int f7223c = 30000;
    /* renamed from: d */
    private boolean f7224d = true;
    /* renamed from: e */
    private boolean f7225e = false;
    /* renamed from: f */
    private int f7226f = 0;
    /* renamed from: g */
    private int f7227g = 10000;
    /* renamed from: h */
    private boolean f7228h = false;
    /* renamed from: i */
    private int f7229i = ((int) TimeUnit.MINUTES.toSeconds(3));
    /* renamed from: j */
    private int f7230j = 1;
    /* renamed from: k */
    private long f7231k = 261374;
    /* renamed from: l */
    private long f7232l = 900000;
    /* renamed from: m */
    private long f7233m = 3600000;
    /* renamed from: n */
    private boolean f7234n = false;
    /* renamed from: o */
    private boolean f7235o = false;
    /* renamed from: p */
    private boolean f7236p = false;
    /* renamed from: q */
    private boolean f7237q = false;
    /* renamed from: r */
    private String f7238r = null;
    /* renamed from: s */
    private String f7239s = C4506ar.m8419i();
    /* renamed from: t */
    private String f7240t = null;
    /* renamed from: u */
    private Context f7241u = null;

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final boolean mo33999a() {
        return this.f7228h;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: b */
    public final boolean mo34000b() {
        return this.f7225e;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: c */
    public final int mo34001c() {
        return this.f7221a;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: d */
    public final String mo34002d() {
        return this.f7238r;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: e */
    public final boolean mo34003e() {
        return this.f7234n;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: f */
    public final long mo34004f() {
        return this.f7232l;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: g */
    public final long mo34005g() {
        return this.f7233m;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: h */
    public final int mo34006h() {
        return this.f7230j;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: i */
    public final long mo34007i() {
        long options = this.f7231k;
        if (this.f7236p) {
            options &= -39;
        }
        if (this.f7237q) {
            return options & -12289;
        }
        return options;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: j */
    public final Context mo34008j() {
        return this.f7241u;
    }

    public Config setContext(Context context) {
        this.f7241u = context;
        return this;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: k */
    public final int mo34009k() {
        return this.f7226f;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: l */
    public final int mo34010l() {
        return this.f7227g;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: m */
    public final int mo34011m() {
        return this.f7222b;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: n */
    public final int mo34012n() {
        return this.f7223c;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: o */
    public final boolean mo34013o() {
        return this.f7224d;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: p */
    public final String mo34014p() {
        return this.f7239s;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: q */
    public final boolean mo34015q() {
        return this.f7235o;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: r */
    public final int mo34016r() {
        return this.f7229i;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: s */
    public final String mo34017s() {
        return this.f7240t;
    }

    public Config setOrgId(String m_orgId) {
        this.f7240t = m_orgId;
        return this;
    }
}
