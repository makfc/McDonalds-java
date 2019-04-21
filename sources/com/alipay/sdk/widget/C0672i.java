package com.alipay.sdk.widget;

import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;

/* renamed from: com.alipay.sdk.widget.i */
class C0672i implements DownloadListener {
    /* renamed from: a */
    final /* synthetic */ C0671h f697a;

    C0672i(C0671h c0671h) {
        this.f697a = c0671h;
    }

    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.setFlags(268435456);
            this.f697a.f694a.startActivity(intent);
        } catch (Throwable th) {
        }
    }
}
