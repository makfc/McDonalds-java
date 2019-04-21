package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.app.statistic.C0590a;
import com.alipay.sdk.data.C0615a;
import com.alipay.sdk.data.C0617c;
import com.alipay.sdk.protocol.C0638b;
import com.alipay.sdk.sys.C0639a;
import com.alipay.sdk.sys.C0640b;
import com.alipay.sdk.util.C0646c;
import com.alipay.sdk.util.C0648e;
import com.alipay.sdk.util.C0648e.C0577a;
import com.alipay.sdk.util.C0654k;
import com.alipay.sdk.util.C0657m;
import com.alipay.sdk.widget.C0664a;
import com.facebook.internal.NativeProtocol;
import java.util.List;
import java.util.Map;

public class AuthTask {
    /* renamed from: a */
    static final Object f453a = C0648e.class;
    /* renamed from: b */
    private Activity f454b;
    /* renamed from: c */
    private C0664a f455c;

    public AuthTask(Activity activity) {
        this.f454b = activity;
        C0640b.m974a().mo8087a(this.f454b, C0617c.m879b());
        C0590a.m799a(activity);
        this.f455c = new C0664a(activity, "去支付宝授权");
    }

    /* renamed from: a */
    private C0577a m740a() {
        return new C0578a(this);
    }

    /* renamed from: b */
    private void m745b() {
        if (this.f455c != null) {
            this.f455c.mo8141b();
        }
    }

    /* renamed from: c */
    private void m746c() {
        if (this.f455c != null) {
            this.f455c.mo8142c();
        }
    }

    public synchronized Map<String, String> authV2(String str, boolean z) {
        return C0654k.m1043a(auth(str, z));
    }

    public synchronized String auth(String str, boolean z) {
        String c;
        if (z) {
            m745b();
        }
        C0640b.m974a().mo8087a(this.f454b, C0617c.m879b());
        c = C0588j.m793c();
        C0587i.m786a("");
        try {
            c = m741a(this.f454b, str);
            C0615a.m869e().mo8052a(this.f454b);
            m746c();
            C0590a.m805b(this.f454b, str);
        } catch (Exception e) {
            C0646c.m1016a(e);
            C0615a.m869e().mo8052a(this.f454b);
            m746c();
            C0590a.m805b(this.f454b, str);
        } catch (Throwable th) {
            C0615a.m869e().mo8052a(this.f454b);
            m746c();
            C0590a.m805b(this.f454b, str);
        }
        return c;
    }

    /* renamed from: a */
    private String m741a(Activity activity, String str) {
        String a = new C0639a(this.f454b).mo8085a(str);
        List d = C0615a.m869e().mo8055d();
        if (!C0615a.m869e().f573l || d == null) {
            d = C0587i.f495a;
        }
        if (C0657m.m1065b(this.f454b, d)) {
            String a2 = new C0648e(activity, m740a()).mo8110a(a);
            if (TextUtils.equals(a2, "failed")) {
                C0590a.m801a("biz", "LogBindCalledH5", "");
                return m744b(activity, a);
            } else if (TextUtils.isEmpty(a2)) {
                return C0588j.m793c();
            } else {
                return a2;
            }
        }
        C0590a.m801a("biz", "LogCalledH5", "");
        return m744b(activity, a);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0052  */
    /* JADX WARNING: Missing block: B:7:0x003a, code skipped:
            r0 = m742a((com.alipay.sdk.protocol.C0638b) r3.get(r2));
     */
    /* renamed from: b */
    private java.lang.String m744b(android.app.Activity r6, java.lang.String r7) {
        /*
        r5 = this;
        r5.m745b();
        r1 = 0;
        r0 = new com.alipay.sdk.packet.impl.a;	 Catch:{ IOException -> 0x006b, Throwable -> 0x0080 }
        r0.<init>();	 Catch:{ IOException -> 0x006b, Throwable -> 0x0080 }
        r0 = r0.mo8074a(r6, r7);	 Catch:{ IOException -> 0x006b, Throwable -> 0x0080 }
        r0 = r0.mo8067c();	 Catch:{ IOException -> 0x006b, Throwable -> 0x0080 }
        r2 = "form";
        r0 = r0.optJSONObject(r2);	 Catch:{ IOException -> 0x006b, Throwable -> 0x0080 }
        r2 = "onload";
        r0 = r0.optJSONObject(r2);	 Catch:{ IOException -> 0x006b, Throwable -> 0x0080 }
        r3 = com.alipay.sdk.protocol.C0638b.m960a(r0);	 Catch:{ IOException -> 0x006b, Throwable -> 0x0080 }
        r5.m746c();	 Catch:{ IOException -> 0x006b, Throwable -> 0x0080 }
        r0 = 0;
        r2 = r0;
    L_0x0026:
        r0 = r3.size();	 Catch:{ IOException -> 0x006b, Throwable -> 0x0080 }
        if (r2 >= r0) goto L_0x004c;
    L_0x002c:
        r0 = r3.get(r2);	 Catch:{ IOException -> 0x006b, Throwable -> 0x0080 }
        r0 = (com.alipay.sdk.protocol.C0638b) r0;	 Catch:{ IOException -> 0x006b, Throwable -> 0x0080 }
        r0 = r0.mo8083b();	 Catch:{ IOException -> 0x006b, Throwable -> 0x0080 }
        r4 = com.alipay.sdk.protocol.C0637a.WapPay;	 Catch:{ IOException -> 0x006b, Throwable -> 0x0080 }
        if (r0 != r4) goto L_0x0048;
    L_0x003a:
        r0 = r3.get(r2);	 Catch:{ IOException -> 0x006b, Throwable -> 0x0080 }
        r0 = (com.alipay.sdk.protocol.C0638b) r0;	 Catch:{ IOException -> 0x006b, Throwable -> 0x0080 }
        r0 = r5.m742a(r0);	 Catch:{ IOException -> 0x006b, Throwable -> 0x0080 }
        r5.m746c();
    L_0x0047:
        return r0;
    L_0x0048:
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x0026;
    L_0x004c:
        r5.m746c();
        r0 = r1;
    L_0x0050:
        if (r0 != 0) goto L_0x005c;
    L_0x0052:
        r0 = com.alipay.sdk.app.C0589k.FAILED;
        r0 = r0.mo8007a();
        r0 = com.alipay.sdk.app.C0589k.m796b(r0);
    L_0x005c:
        r1 = r0.mo8007a();
        r0 = r0.mo8008b();
        r2 = "";
        r0 = com.alipay.sdk.app.C0588j.m789a(r1, r0, r2);
        goto L_0x0047;
    L_0x006b:
        r0 = move-exception;
        r1 = com.alipay.sdk.app.C0589k.NETWORK_ERROR;	 Catch:{ all -> 0x008d }
        r1 = r1.mo8007a();	 Catch:{ all -> 0x008d }
        r1 = com.alipay.sdk.app.C0589k.m796b(r1);	 Catch:{ all -> 0x008d }
        r2 = "net";
        com.alipay.sdk.app.statistic.C0590a.m804a(r2, r0);	 Catch:{ all -> 0x008d }
        r5.m746c();
        r0 = r1;
        goto L_0x0050;
    L_0x0080:
        r0 = move-exception;
        r2 = "biz";
        r3 = "H5AuthDataAnalysisError";
        com.alipay.sdk.app.statistic.C0590a.m802a(r2, r3, r0);	 Catch:{ all -> 0x008d }
        r5.m746c();
        r0 = r1;
        goto L_0x0050;
    L_0x008d:
        r0 = move-exception;
        r5.m746c();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.AuthTask.m744b(android.app.Activity, java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    private String m742a(C0638b c0638b) {
        String[] c = c0638b.mo8084c();
        Bundle bundle = new Bundle();
        bundle.putString(NativeProtocol.IMAGE_URL_KEY, c[0]);
        Intent intent = new Intent(this.f454b, H5AuthActivity.class);
        intent.putExtras(bundle);
        this.f454b.startActivity(intent);
        synchronized (f453a) {
            try {
                f453a.wait();
            } catch (InterruptedException e) {
                return C0588j.m793c();
            }
        }
        String a = C0588j.m788a();
        if (TextUtils.isEmpty(a)) {
            return C0588j.m793c();
        }
        return a;
    }
}
