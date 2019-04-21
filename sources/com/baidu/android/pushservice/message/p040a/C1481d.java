package com.baidu.android.pushservice.message.p040a;

import android.content.Context;
import com.baidu.android.pushservice.message.C1508h;
import com.baidu.android.pushservice.message.C1512k;

/* renamed from: com.baidu.android.pushservice.message.a.d */
public abstract class C1481d {
    /* renamed from: b */
    private static final String f5197b = C1481d.class.getSimpleName();
    /* renamed from: a */
    protected Context f5198a;

    public C1481d(Context context) {
        this.f5198a = context;
    }

    /* renamed from: a */
    public abstract C1508h mo13966a(C1512k c1512k, byte[] bArr);

    /* renamed from: a */
    public abstract C1508h mo13967a(String str, String str2, int i, byte[] bArr, byte[] bArr2);

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:11:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x0021  */
    /* renamed from: a */
    public boolean mo13968a(byte[] r7) {
        /*
        r6 = this;
        r2 = 1;
        r1 = 0;
        r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0023 }
        r0 = new java.lang.String;	 Catch:{ Exception -> 0x0023 }
        r0.<init>(r7);	 Catch:{ Exception -> 0x0023 }
        r0 = com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation.init(r0);	 Catch:{ Exception -> 0x0023 }
        r3 = "bccs_fb";
        r3 = r0.isNull(r3);	 Catch:{ Exception -> 0x0023 }
        if (r3 != 0) goto L_0x0040;
    L_0x0015:
        r3 = "bccs_fb";
        r0 = r0.getString(r3);	 Catch:{ Exception -> 0x0023 }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ Exception -> 0x0023 }
    L_0x001f:
        if (r0 != r2) goto L_0x0022;
    L_0x0021:
        r1 = r2;
    L_0x0022:
        return r1;
    L_0x0023:
        r0 = move-exception;
        r3 = f5197b;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "Message parsing feedback fail:\r\n";
        r4 = r4.append(r5);
        r0 = r0.getMessage();
        r0 = r4.append(r0);
        r0 = r0.toString();
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r3, r0);
    L_0x0040:
        r0 = r1;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.message.p040a.C1481d.mo13968a(byte[]):boolean");
    }
}
