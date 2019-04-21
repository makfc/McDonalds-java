package com.admaster.jice.p004a;

/* compiled from: JicePushView */
/* renamed from: com.admaster.jice.a.s */
class C0469s implements Runnable {
    /* renamed from: a */
    final /* synthetic */ JicePushView f86a;

    C0469s(JicePushView jicePushView) {
        this.f86a = jicePushView;
    }

    public void run() {
        this.f86a.f73b.removeView(this.f86a.f74c);
        this.f86a.f75d = false;
        if (JicePushView.f71j != null) {
            JicePushView.f71j.mo7615a();
        }
        this.f86a.mo7626d();
    }
}
