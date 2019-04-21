package com.admaster.jice.api;

import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: JicePushActivity */
/* renamed from: com.admaster.jice.api.a */
class C0473a implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ JicePushActivity f112a;

    C0473a(JicePushActivity jicePushActivity) {
        this.f112a = jicePushActivity;
    }

    public void onClick(View view) {
        if (view == this.f112a.f105f) {
            if (JicePushActivity.f100d != null) {
                JicePushActivity.f100d.mo7617a(String.valueOf(this.f112a.f102b.getPushId()), this.f112a.f102b.getTargetUrl());
            }
        } else if (JicePushActivity.f100d != null) {
            JicePushActivity.f100d.mo7615a();
        }
        this.f112a.m146b();
    }
}
