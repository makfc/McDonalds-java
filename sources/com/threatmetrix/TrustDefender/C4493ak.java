package com.threatmetrix.TrustDefender;

/* renamed from: com.threatmetrix.TrustDefender.ak */
final class C4493ak {
    /* renamed from: a */
    private long f7405a = 0;
    /* renamed from: b */
    private long f7406b = 0;
    /* renamed from: c */
    private String f7407c = "";
    /* renamed from: d */
    private int f7408d = 0;

    C4493ak() {
    }

    /* renamed from: a */
    public final long mo34137a() {
        return this.f7405a;
    }

    /* renamed from: a */
    public final void mo34139a(long m_enabledOptions) {
        this.f7405a = m_enabledOptions;
    }

    /* renamed from: b */
    public final long mo34142b() {
        return this.f7406b;
    }

    /* renamed from: b */
    public final void mo34143b(long m_disabledOptions) {
        this.f7406b = m_disabledOptions;
    }

    /* renamed from: c */
    public final String mo34144c() {
        return this.f7407c;
    }

    /* renamed from: a */
    public final void mo34140a(String m_sdkVersion) {
        this.f7407c = m_sdkVersion;
    }

    /* renamed from: a */
    public final boolean mo34141a(long enabledOptions, long disabledOptions, String sdkVersion, int quietPeriod) {
        return (enabledOptions == this.f7405a && disabledOptions == this.f7406b && sdkVersion.equals(this.f7407c) && quietPeriod == this.f7408d) ? false : true;
    }

    /* renamed from: d */
    public final int mo34145d() {
        return this.f7408d;
    }

    /* renamed from: a */
    public final void mo34138a(int m_quietPeriod) {
        this.f7408d = m_quietPeriod;
    }
}
