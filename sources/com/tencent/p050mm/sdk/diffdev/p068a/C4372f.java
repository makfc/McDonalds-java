package com.tencent.p050mm.sdk.diffdev.p068a;

import android.os.AsyncTask;
import android.util.Log;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.tencent.p050mm.sdk.diffdev.OAuthErrCode;
import com.tencent.p050mm.sdk.diffdev.OAuthListener;

/* renamed from: com.tencent.mm.sdk.diffdev.a.f */
final class C4372f extends AsyncTask<Void, Void, C4371a> implements TraceFieldInterface {
    public Trace _nr_trace;
    /* renamed from: an */
    private OAuthListener f6840an;
    /* renamed from: aq */
    private String f6841aq;
    /* renamed from: aw */
    private int f6842aw;
    private String url;

    /* renamed from: com.tencent.mm.sdk.diffdev.a.f$a */
    static class C4371a {
        /* renamed from: ap */
        public OAuthErrCode f6837ap;
        /* renamed from: ax */
        public String f6838ax;
        /* renamed from: ay */
        public int f6839ay;

        C4371a() {
        }

        /* renamed from: e */
        public static com.tencent.p050mm.sdk.diffdev.p068a.C4372f.C4371a m7915e(byte[] r9) {
            /*
            r8 = 1;
            r7 = 0;
            r0 = new com.tencent.mm.sdk.diffdev.a.f$a;
            r0.<init>();
            if (r9 == 0) goto L_0x000c;
        L_0x0009:
            r1 = r9.length;
            if (r1 != 0) goto L_0x0018;
        L_0x000c:
            r1 = "MicroMsg.SDK.NoopingResult";
            r2 = "parse fail, buf is null";
            android.util.Log.e(r1, r2);
            r1 = com.tencent.p050mm.sdk.diffdev.OAuthErrCode.WechatAuth_Err_NetworkErr;
            r0.f6837ap = r1;
        L_0x0017:
            return r0;
        L_0x0018:
            r1 = new java.lang.String;	 Catch:{ Exception -> 0x0067 }
            r2 = "utf-8";
            r1.<init>(r9, r2);	 Catch:{ Exception -> 0x0067 }
            r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x004e }
            r1 = com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation.init(r1);	 Catch:{ Exception -> 0x004e }
            r2 = "wx_errcode";
            r2 = r1.getInt(r2);	 Catch:{ Exception -> 0x004e }
            r0.f6839ay = r2;	 Catch:{ Exception -> 0x004e }
            r2 = "MicroMsg.SDK.NoopingResult";
            r3 = "nooping uuidStatusCode = %d";
            r4 = 1;
            r4 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x004e }
            r5 = 0;
            r6 = r0.f6839ay;	 Catch:{ Exception -> 0x004e }
            r6 = java.lang.Integer.valueOf(r6);	 Catch:{ Exception -> 0x004e }
            r4[r5] = r6;	 Catch:{ Exception -> 0x004e }
            r3 = java.lang.String.format(r3, r4);	 Catch:{ Exception -> 0x004e }
            android.util.Log.d(r2, r3);	 Catch:{ Exception -> 0x004e }
            r2 = r0.f6839ay;	 Catch:{ Exception -> 0x004e }
            switch(r2) {
                case 402: goto L_0x0097;
                case 403: goto L_0x009d;
                case 404: goto L_0x008d;
                case 405: goto L_0x0080;
                case 408: goto L_0x0092;
                case 500: goto L_0x00a3;
                default: goto L_0x0049;
            };	 Catch:{ Exception -> 0x004e }
        L_0x0049:
            r1 = com.tencent.p050mm.sdk.diffdev.OAuthErrCode.WechatAuth_Err_NormalErr;	 Catch:{ Exception -> 0x004e }
            r0.f6837ap = r1;	 Catch:{ Exception -> 0x004e }
            goto L_0x0017;
        L_0x004e:
            r1 = move-exception;
            r2 = "MicroMsg.SDK.NoopingResult";
            r3 = "parse json fail, ex = %s";
            r4 = new java.lang.Object[r8];
            r1 = r1.getMessage();
            r4[r7] = r1;
            r1 = java.lang.String.format(r3, r4);
            android.util.Log.e(r2, r1);
            r1 = com.tencent.p050mm.sdk.diffdev.OAuthErrCode.WechatAuth_Err_NormalErr;
            r0.f6837ap = r1;
            goto L_0x0017;
        L_0x0067:
            r1 = move-exception;
            r2 = "MicroMsg.SDK.NoopingResult";
            r3 = "parse fail, build String fail, ex = %s";
            r4 = new java.lang.Object[r8];
            r1 = r1.getMessage();
            r4[r7] = r1;
            r1 = java.lang.String.format(r3, r4);
            android.util.Log.e(r2, r1);
            r1 = com.tencent.p050mm.sdk.diffdev.OAuthErrCode.WechatAuth_Err_NormalErr;
            r0.f6837ap = r1;
            goto L_0x0017;
        L_0x0080:
            r2 = com.tencent.p050mm.sdk.diffdev.OAuthErrCode.WechatAuth_Err_OK;	 Catch:{ Exception -> 0x004e }
            r0.f6837ap = r2;	 Catch:{ Exception -> 0x004e }
            r2 = "wx_code";
            r1 = r1.getString(r2);	 Catch:{ Exception -> 0x004e }
            r0.f6838ax = r1;	 Catch:{ Exception -> 0x004e }
            goto L_0x0017;
        L_0x008d:
            r1 = com.tencent.p050mm.sdk.diffdev.OAuthErrCode.WechatAuth_Err_OK;	 Catch:{ Exception -> 0x004e }
            r0.f6837ap = r1;	 Catch:{ Exception -> 0x004e }
            goto L_0x0017;
        L_0x0092:
            r1 = com.tencent.p050mm.sdk.diffdev.OAuthErrCode.WechatAuth_Err_OK;	 Catch:{ Exception -> 0x004e }
            r0.f6837ap = r1;	 Catch:{ Exception -> 0x004e }
            goto L_0x0017;
        L_0x0097:
            r1 = com.tencent.p050mm.sdk.diffdev.OAuthErrCode.WechatAuth_Err_Timeout;	 Catch:{ Exception -> 0x004e }
            r0.f6837ap = r1;	 Catch:{ Exception -> 0x004e }
            goto L_0x0017;
        L_0x009d:
            r1 = com.tencent.p050mm.sdk.diffdev.OAuthErrCode.WechatAuth_Err_Cancel;	 Catch:{ Exception -> 0x004e }
            r0.f6837ap = r1;	 Catch:{ Exception -> 0x004e }
            goto L_0x0017;
        L_0x00a3:
            r1 = com.tencent.p050mm.sdk.diffdev.OAuthErrCode.WechatAuth_Err_NormalErr;	 Catch:{ Exception -> 0x004e }
            r0.f6837ap = r1;	 Catch:{ Exception -> 0x004e }
            goto L_0x0017;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.p050mm.sdk.diffdev.p068a.C4372f$C4371a.m7915e(byte[]):com.tencent.mm.sdk.diffdev.a.f$a");
        }
    }

    public C4372f(String str, OAuthListener oAuthListener) {
        this.f6841aq = str;
        this.f6840an = oAuthListener;
        this.url = String.format("https://long.open.weixin.qq.com/connect/l/qrconnect?f=json&uuid=%s", new Object[]{str});
    }

    public void _nr_setTrace(Trace trace) {
        try {
            this._nr_trace = trace;
        } catch (Exception e) {
        }
    }

    /* Access modifiers changed, original: protected|final|synthetic */
    public final /* synthetic */ Object doInBackground(Object[] objArr) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "f#doInBackground", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "f#doInBackground", null);
            }
        }
        C4371a c4371a;
        if (this.f6841aq == null || this.f6841aq.length() == 0) {
            Log.e("MicroMsg.SDK.NoopingTask", "run fail, uuid is null");
            c4371a = new C4371a();
            c4371a.f6837ap = OAuthErrCode.WechatAuth_Err_NormalErr;
            TraceMachine.exitMethod();
            TraceMachine.unloadTraceContext(this);
            return c4371a;
        }
        while (!isCancelled()) {
            String str = this.url + (this.f6842aw == 0 ? "" : "&last=" + this.f6842aw);
            long currentTimeMillis = System.currentTimeMillis();
            byte[] b = C4370e.m7914b(str, 60000);
            long currentTimeMillis2 = System.currentTimeMillis();
            c4371a = C4371a.m7915e(b);
            Log.d("MicroMsg.SDK.NoopingTask", String.format("nooping, url = %s, errCode = %s, uuidStatusCode = %d, time consumed = %d(ms)", new Object[]{str, c4371a.f6837ap.toString(), Integer.valueOf(c4371a.f6839ay), Long.valueOf(currentTimeMillis2 - currentTimeMillis)}));
            if (c4371a.f6837ap == OAuthErrCode.WechatAuth_Err_OK) {
                this.f6842aw = c4371a.f6839ay;
                if (c4371a.f6839ay == C4373g.UUID_SCANED.getCode()) {
                    this.f6840an.onQrcodeScanned();
                } else if (c4371a.f6839ay != C4373g.UUID_KEEP_CONNECT.getCode() && c4371a.f6839ay == C4373g.UUID_CONFIRM.getCode()) {
                    if (c4371a.f6838ax == null || c4371a.f6838ax.length() == 0) {
                        Log.e("MicroMsg.SDK.NoopingTask", "nooping fail, confirm with an empty code!!!");
                        c4371a.f6837ap = OAuthErrCode.WechatAuth_Err_NormalErr;
                    }
                    TraceMachine.exitMethod();
                    TraceMachine.unloadTraceContext(this);
                    return c4371a;
                }
            }
            Log.e("MicroMsg.SDK.NoopingTask", String.format("nooping fail, errCode = %s, uuidStatusCode = %d", new Object[]{c4371a.f6837ap.toString(), Integer.valueOf(c4371a.f6839ay)}));
            TraceMachine.exitMethod();
            TraceMachine.unloadTraceContext(this);
            return c4371a;
        }
        Log.i("MicroMsg.SDK.NoopingTask", "IDiffDevOAuth.stopAuth / detach invoked");
        c4371a = new C4371a();
        c4371a.f6837ap = OAuthErrCode.WechatAuth_Err_Auth_Stopped;
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return c4371a;
    }

    /* Access modifiers changed, original: protected|final|synthetic */
    public final /* synthetic */ void onPostExecute(Object obj) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "f#onPostExecute", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "f#onPostExecute", null);
            }
        }
        C4371a c4371a = (C4371a) obj;
        this.f6840an.onAuthFinish(c4371a.f6837ap, c4371a.f6838ax);
        TraceMachine.exitMethod();
    }
}
