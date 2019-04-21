package com.admaster.jice.p004a;

/* compiled from: JicePushManager */
/* renamed from: com.admaster.jice.a.k */
class C0462k implements JiceViewVisitor {
    /* renamed from: a */
    final /* synthetic */ JicePushManager f64a;

    C0462k(JicePushManager jicePushManager) {
        this.f64a = jicePushManager;
    }

    /* renamed from: a */
    public void mo7616a(String str) {
        if (this.f64a.f55d != null) {
            this.f64a.f55d.onJiceViewError(str);
        }
    }

    /* renamed from: a */
    public void mo7615a() {
        if (this.f64a.f55d != null && this.f64a.f56e) {
            this.f64a.f55d.onJiceViewDismissed();
        }
        this.f64a.f56e = false;
    }

    /* renamed from: a */
    public void mo7617a(String str, String str2) {
        if (this.f64a.f55d != null && this.f64a.f56e) {
            this.f64a.f55d.onJiceViewClicked(str2);
        }
        this.f64a.f56e = false;
        this.f64a.m77a(C0461i.JICECLICKPUSH.toString(), str);
    }

    /* renamed from: b */
    public void mo7618b(String str) {
        if (this.f64a.f55d != null) {
            this.f64a.f55d.onJiceViewShowed();
        }
        this.f64a.m77a(C0461i.JICESHOWPUSH.toString(), str);
    }
}
