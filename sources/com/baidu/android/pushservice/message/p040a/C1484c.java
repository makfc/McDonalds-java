package com.baidu.android.pushservice.message.p040a;

import android.content.Context;
import com.baidu.android.pushservice.message.C1508h;
import com.baidu.android.pushservice.message.C1512k;

/* renamed from: com.baidu.android.pushservice.message.a.c */
public class C1484c extends C1481d {
    /* renamed from: b */
    private static final String f5201b = C1484c.class.getSimpleName();
    /* renamed from: c */
    private Context f5202c;

    public C1484c(Context context) {
        super(context);
        this.f5202c = context.getApplicationContext();
    }

    /* renamed from: a */
    public C1508h mo13966a(C1512k c1512k, byte[] bArr) {
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0066  */
    /* renamed from: a */
    public com.baidu.android.pushservice.message.C1508h mo13967a(java.lang.String r11, java.lang.String r12, int r13, byte[] r14, byte[] r15) {
        /*
        r10 = this;
        r7 = 0;
        r6 = 0;
        r0 = 1;
        r8 = new com.baidu.android.pushservice.message.h;
        r8.<init>();
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0074 }
        r1 = new java.lang.String;	 Catch:{ JSONException -> 0x0074 }
        r1.<init>(r15);	 Catch:{ JSONException -> 0x0074 }
        r1 = com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation.init(r1);	 Catch:{ JSONException -> 0x0074 }
        r2 = "action";
        r2 = r1.getString(r2);	 Catch:{ JSONException -> 0x0103 }
        r9 = r1;
        r1 = r0;
        r0 = r9;
    L_0x001c:
        if (r1 == 0) goto L_0x00e8;
    L_0x001e:
        r1 = android.text.TextUtils.isEmpty(r2);
        if (r1 != 0) goto L_0x00e8;
    L_0x0024:
        r1 = "push.NOTIFICATION";
        r1 = r2.equalsIgnoreCase(r1);
        if (r1 == 0) goto L_0x00b8;
    L_0x002c:
        r1 = "description";
        r2 = r0.getString(r1);	 Catch:{ JSONException -> 0x0098 }
        r1 = "title";
        r1 = r0.isNull(r1);	 Catch:{ JSONException -> 0x0098 }
        if (r1 != 0) goto L_0x010c;
    L_0x003a:
        r1 = "title";
        r1 = r0.getString(r1);	 Catch:{ JSONException -> 0x0098 }
    L_0x0040:
        r3 = "iconUrl";
        r3 = r0.isNull(r3);	 Catch:{ JSONException -> 0x0098 }
        if (r3 != 0) goto L_0x0109;
    L_0x0048:
        r3 = "iconUrl";
        r3 = r0.getString(r3);	 Catch:{ JSONException -> 0x0098 }
    L_0x004e:
        r4 = "url";
        r4 = r0.isNull(r4);	 Catch:{ JSONException -> 0x0098 }
        if (r4 != 0) goto L_0x0106;
    L_0x0057:
        r4 = "url";
        r4 = r0.getString(r4);	 Catch:{ JSONException -> 0x0098 }
    L_0x005e:
        r0 = r10.f5198a;	 Catch:{ JSONException -> 0x0098 }
        r5 = r12;
        com.baidu.android.pushservice.message.p040a.C1489g.m6763a(r0, r1, r2, r3, r4, r5);	 Catch:{ JSONException -> 0x0098 }
    L_0x0064:
        if (r6 == 0) goto L_0x00fd;
    L_0x0066:
        r0 = 32;
        r6.setFlags(r0);
        r0 = r10.f5198a;
        r0.sendBroadcast(r6);
        r8.mo13991a(r7);
    L_0x0073:
        return r8;
    L_0x0074:
        r0 = move-exception;
        r1 = r6;
    L_0x0076:
        r2 = f5201b;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Supper message parsing action Fail:\r\n";
        r3 = r3.append(r4);
        r0 = r0.getMessage();
        r0 = r3.append(r0);
        r0 = r0.toString();
        r3 = r10.f5202c;
        com.baidu.android.pushservice.p036h.C1426b.m6447b(r2, r0, r3);
        r0 = r1;
        r2 = r6;
        r1 = r7;
        goto L_0x001c;
    L_0x0098:
        r0 = move-exception;
        r1 = f5201b;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Supper message parsing notification action Fail:\r\n";
        r2 = r2.append(r3);
        r0 = r0.getMessage();
        r0 = r2.append(r0);
        r0 = r0.toString();
        r2 = r10.f5202c;
        com.baidu.android.pushservice.p036h.C1426b.m6447b(r1, r0, r2);
        goto L_0x0064;
    L_0x00b8:
        r1 = "message";
        r0 = r0.getString(r1);	 Catch:{ JSONException -> 0x00c9 }
    L_0x00be:
        r6 = new android.content.Intent;
        r6.<init>(r2);
        r1 = "message";
        r6.putExtra(r1, r0);
        goto L_0x0064;
    L_0x00c9:
        r0 = move-exception;
        r1 = f5201b;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Supper message parsing default action Fail:\r\n";
        r3 = r3.append(r4);
        r0 = r0.getMessage();
        r0 = r3.append(r0);
        r0 = r0.toString();
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r1, r0);
        r0 = r6;
        goto L_0x00be;
    L_0x00e8:
        r6 = new android.content.Intent;
        r0 = "com.baidu.pushservice.action.supper.MESSAGE";
        r6.<init>(r0);
        r0 = "message";
        r6.putExtra(r0, r15);
        r0 = ">>> Deliver baidu supper msg with g action: com.baidu.pushservice.action.supper.MESSAGE";
        r1 = r10.f5198a;
        com.baidu.android.pushservice.util.C1578v.m7095b(r0, r1);
        goto L_0x0064;
    L_0x00fd:
        r0 = 2;
        r8.mo13991a(r0);
        goto L_0x0073;
    L_0x0103:
        r0 = move-exception;
        goto L_0x0076;
    L_0x0106:
        r4 = r6;
        goto L_0x005e;
    L_0x0109:
        r3 = r6;
        goto L_0x004e;
    L_0x010c:
        r1 = r6;
        goto L_0x0040;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.message.p040a.C1484c.mo13967a(java.lang.String, java.lang.String, int, byte[], byte[]):com.baidu.android.pushservice.message.h");
    }
}
