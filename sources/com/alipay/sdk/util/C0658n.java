package com.alipay.sdk.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;

/* renamed from: com.alipay.sdk.util.n */
final class C0658n implements DownloadListener {
    /* renamed from: a */
    final /* synthetic */ Context f667a;

    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.setFlags(268435456);
            this.f667a.startActivity(intent);
        } catch (Throwable th) {
        }
    }
}
