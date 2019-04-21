package com.admaster.jice.p004a;

import android.content.Intent;
import com.admaster.jice.api.JicePushActivity;
import com.admaster.jice.p005b.JicePushConfig;

/* compiled from: JicePushManager */
/* renamed from: com.admaster.jice.a.l */
class C0463l implements Runnable {
    /* renamed from: a */
    final /* synthetic */ JicePushManager f65a;
    /* renamed from: b */
    private final /* synthetic */ JicePushConfig f66b;

    C0463l(JicePushManager jicePushManager, JicePushConfig jicePushConfig) {
        this.f65a = jicePushManager;
        this.f66b = jicePushConfig;
    }

    public void run() {
        Intent intent = new Intent(this.f65a.f58g, JicePushActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("pushviewconfig", this.f66b);
        JicePushActivity.m143a(this.f65a.f52a);
        this.f65a.f58g.startActivity(intent);
    }
}
