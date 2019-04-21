package com.alipay.sdk.auth;

import android.app.Activity;

/* renamed from: com.alipay.sdk.auth.h */
final class C0604h implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Activity f544a;
    /* renamed from: b */
    final /* synthetic */ StringBuilder f545b;
    /* renamed from: c */
    final /* synthetic */ APAuthInfo f546c;

    /* JADX WARNING: Failed to extract finally block: empty outs */
    public void run() {
        /*
        r5 = this;
        r2 = 0;
        r1 = 0;
        r0 = new com.alipay.sdk.packet.impl.a;	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r0.<init>();	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r3 = r5.f544a;	 Catch:{ Throwable -> 0x0059 }
        r4 = r5.f545b;	 Catch:{ Throwable -> 0x0059 }
        r4 = r4.toString();	 Catch:{ Throwable -> 0x0059 }
        r0 = r0.mo8074a(r3, r4);	 Catch:{ Throwable -> 0x0059 }
    L_0x0013:
        r2 = com.alipay.sdk.auth.C0603g.f542c;	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        if (r2 == 0) goto L_0x0024;
    L_0x0019:
        r2 = com.alipay.sdk.auth.C0603g.f542c;	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r2.mo8142c();	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r2 = 0;
        com.alipay.sdk.auth.C0603g.f542c = r2;	 Catch:{ Exception -> 0x010b, all -> 0x011b }
    L_0x0024:
        if (r0 != 0) goto L_0x0061;
    L_0x0026:
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r0.<init>();	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r1 = r5.f546c;	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r1 = r1.getRedirectUri();	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r1 = "?resultCode=202";
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r0 = r0.toString();	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        com.alipay.sdk.auth.C0603g.f543d = r0;	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r0 = r5.f544a;	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r1 = com.alipay.sdk.auth.C0603g.f543d;	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        com.alipay.sdk.auth.C0603g.m841a(r0, r1);	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r0 = com.alipay.sdk.auth.C0603g.f542c;
        if (r0 == 0) goto L_0x0058;
    L_0x0051:
        r0 = com.alipay.sdk.auth.C0603g.f542c;
        r0.mo8142c();
    L_0x0058:
        return;
    L_0x0059:
        r0 = move-exception;
        r3 = "msp";
        com.alipay.sdk.util.C0646c.m1015a(r3, r0);	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r0 = r2;
        goto L_0x0013;
    L_0x0061:
        r0 = r0.mo8067c();	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r2 = "form";
        r0 = r0.optJSONObject(r2);	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r2 = "onload";
        r0 = r0.optJSONObject(r2);	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r2 = com.alipay.sdk.protocol.C0638b.m960a(r0);	 Catch:{ Exception -> 0x010b, all -> 0x011b }
    L_0x0075:
        r0 = r2.size();	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        if (r1 >= r0) goto L_0x0099;
    L_0x007b:
        r0 = r2.get(r1);	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r0 = (com.alipay.sdk.protocol.C0638b) r0;	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r0 = r0.mo8083b();	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r3 = com.alipay.sdk.protocol.C0637a.WapPay;	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        if (r0 != r3) goto L_0x00d6;
    L_0x0089:
        r0 = r2.get(r1);	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r0 = (com.alipay.sdk.protocol.C0638b) r0;	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r0 = r0.mo8084c();	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r1 = 0;
        r0 = r0[r1];	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        com.alipay.sdk.auth.C0603g.f543d = r0;	 Catch:{ Exception -> 0x010b, all -> 0x011b }
    L_0x0099:
        r0 = com.alipay.sdk.auth.C0603g.f543d;	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        if (r0 == 0) goto L_0x00da;
    L_0x00a3:
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r0.<init>();	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r1 = r5.f546c;	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r1 = r1.getRedirectUri();	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r1 = "?resultCode=202";
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r0 = r0.toString();	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        com.alipay.sdk.auth.C0603g.f543d = r0;	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r0 = r5.f544a;	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r1 = com.alipay.sdk.auth.C0603g.f543d;	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        com.alipay.sdk.auth.C0603g.m841a(r0, r1);	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r0 = com.alipay.sdk.auth.C0603g.f542c;
        if (r0 == 0) goto L_0x0058;
    L_0x00ce:
        r0 = com.alipay.sdk.auth.C0603g.f542c;
        r0.mo8142c();
        goto L_0x0058;
    L_0x00d6:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x0075;
    L_0x00da:
        r0 = new android.content.Intent;	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r1 = r5.f544a;	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r2 = com.alipay.sdk.auth.AuthActivity.class;
        r0.<init>(r1, r2);	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r1 = "params";
        r2 = com.alipay.sdk.auth.C0603g.f543d;	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r0.putExtra(r1, r2);	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r1 = "redirectUri";
        r2 = r5.f546c;	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r2 = r2.getRedirectUri();	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r0.putExtra(r1, r2);	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r1 = r5.f544a;	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r1.startActivity(r0);	 Catch:{ Exception -> 0x010b, all -> 0x011b }
        r0 = com.alipay.sdk.auth.C0603g.f542c;
        if (r0 == 0) goto L_0x0058;
    L_0x0102:
        r0 = com.alipay.sdk.auth.C0603g.f542c;
        r0.mo8142c();
        goto L_0x0058;
    L_0x010b:
        r0 = move-exception;
        r0 = com.alipay.sdk.auth.C0603g.f542c;
        if (r0 == 0) goto L_0x0058;
    L_0x0112:
        r0 = com.alipay.sdk.auth.C0603g.f542c;
        r0.mo8142c();
        goto L_0x0058;
    L_0x011b:
        r0 = move-exception;
        r1 = com.alipay.sdk.auth.C0603g.f542c;
        if (r1 == 0) goto L_0x0129;
    L_0x0122:
        r1 = com.alipay.sdk.auth.C0603g.f542c;
        r1.mo8142c();
    L_0x0129:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.auth.C0604h.run():void");
    }
}
