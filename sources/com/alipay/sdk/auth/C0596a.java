package com.alipay.sdk.auth;

import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;

/* renamed from: com.alipay.sdk.auth.a */
class C0596a implements DownloadListener {
    /* renamed from: a */
    final /* synthetic */ AuthActivity f534a;

    C0596a(AuthActivity authActivity) {
        this.f534a = authActivity;
    }

    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            this.f534a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Throwable th) {
        }
    }
}
