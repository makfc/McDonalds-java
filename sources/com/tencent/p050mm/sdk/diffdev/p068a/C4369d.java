package com.tencent.p050mm.sdk.diffdev.p068a;

import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.tencent.p050mm.sdk.diffdev.OAuthErrCode;
import com.tencent.p050mm.sdk.diffdev.OAuthListener;
import java.io.File;
import java.util.concurrent.Executor;
import org.json.JSONObject;

/* renamed from: com.tencent.mm.sdk.diffdev.a.d */
public final class C4369d extends AsyncTask<Void, Void, C4368a> implements TraceFieldInterface {
    /* renamed from: ai */
    private static final boolean f6830ai;
    /* renamed from: aj */
    private static final String f6831aj = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/tencent/MicroMsg/oauth_qrcode.png");
    /* renamed from: ak */
    private static String f6832ak;
    public Trace _nr_trace;
    /* renamed from: al */
    private String f6833al;
    /* renamed from: am */
    private String f6834am;
    /* renamed from: an */
    private OAuthListener f6835an;
    /* renamed from: ao */
    private C4372f f6836ao;
    private String appId;
    private String scope;
    private String signature;

    /* renamed from: com.tencent.mm.sdk.diffdev.a.d$a */
    static class C4368a {
        /* renamed from: ap */
        public OAuthErrCode f6823ap;
        /* renamed from: aq */
        public String f6824aq;
        /* renamed from: ar */
        public String f6825ar;
        /* renamed from: as */
        public String f6826as;
        /* renamed from: at */
        public int f6827at;
        /* renamed from: au */
        public String f6828au;
        /* renamed from: av */
        public byte[] f6829av;

        private C4368a() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:23:0x0039 A:{SYNTHETIC, Splitter:B:23:0x0039} */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x002b A:{SYNTHETIC, Splitter:B:15:0x002b} */
        /* renamed from: a */
        private static boolean m7910a(java.lang.String r3, byte[] r4) {
            /*
            r2 = 0;
            r1 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x001d, all -> 0x0035 }
            r1.<init>(r3);	 Catch:{ Exception -> 0x001d, all -> 0x0035 }
            r1.write(r4);	 Catch:{ Exception -> 0x0044 }
            r1.flush();	 Catch:{ Exception -> 0x0044 }
            r1.close();	 Catch:{ IOException -> 0x0018 }
        L_0x000f:
            r0 = "MicroMsg.SDK.GetQRCodeResult";
            r1 = "writeToFile ok!";
            android.util.Log.d(r0, r1);
            r0 = 1;
        L_0x0017:
            return r0;
        L_0x0018:
            r0 = move-exception;
            r0.printStackTrace();
            goto L_0x000f;
        L_0x001d:
            r0 = move-exception;
            r1 = r2;
        L_0x001f:
            r0.printStackTrace();	 Catch:{ all -> 0x0042 }
            r0 = "MicroMsg.SDK.GetQRCodeResult";
            r2 = "write to file error";
            android.util.Log.w(r0, r2);	 Catch:{ all -> 0x0042 }
            if (r1 == 0) goto L_0x002e;
        L_0x002b:
            r1.close();	 Catch:{ IOException -> 0x0030 }
        L_0x002e:
            r0 = 0;
            goto L_0x0017;
        L_0x0030:
            r0 = move-exception;
            r0.printStackTrace();
            goto L_0x002e;
        L_0x0035:
            r0 = move-exception;
            r1 = r2;
        L_0x0037:
            if (r1 == 0) goto L_0x003c;
        L_0x0039:
            r1.close();	 Catch:{ IOException -> 0x003d }
        L_0x003c:
            throw r0;
        L_0x003d:
            r1 = move-exception;
            r1.printStackTrace();
            goto L_0x003c;
        L_0x0042:
            r0 = move-exception;
            goto L_0x0037;
        L_0x0044:
            r0 = move-exception;
            goto L_0x001f;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.p050mm.sdk.diffdev.p068a.C4369d$C4368a.m7910a(java.lang.String, byte[]):boolean");
        }

        /* renamed from: d */
        public static C4368a m7911d(byte[] bArr) {
            C4368a c4368a = new C4368a();
            if (bArr == null || bArr.length == 0) {
                Log.e("MicroMsg.SDK.GetQRCodeResult", "parse fail, buf is null");
                c4368a.f6823ap = OAuthErrCode.WechatAuth_Err_NetworkErr;
            } else {
                try {
                    try {
                        JSONObject init = JSONObjectInstrumentation.init(new String(bArr, "utf-8"));
                        int i = init.getInt("errcode");
                        if (i != 0) {
                            Log.e("MicroMsg.SDK.GetQRCodeResult", String.format("resp errcode = %d", new Object[]{Integer.valueOf(i)}));
                            c4368a.f6823ap = OAuthErrCode.WechatAuth_Err_NormalErr;
                            c4368a.f6827at = i;
                            c4368a.f6828au = init.optString("errmsg");
                        } else {
                            String string = init.getJSONObject("qrcode").getString("qrcodebase64");
                            if (string == null || string.length() == 0) {
                                Log.e("MicroMsg.SDK.GetQRCodeResult", "parse fail, qrcodeBase64 is null");
                                c4368a.f6823ap = OAuthErrCode.WechatAuth_Err_JsonDecodeErr;
                            } else {
                                byte[] decode = Base64.decode(string, 0);
                                if (decode == null || decode.length == 0) {
                                    Log.e("MicroMsg.SDK.GetQRCodeResult", "parse fail, qrcodeBuf is null");
                                    c4368a.f6823ap = OAuthErrCode.WechatAuth_Err_JsonDecodeErr;
                                } else if (C4369d.f6830ai) {
                                    File file = new File(C4369d.f6831aj);
                                    file.mkdirs();
                                    if (file.exists()) {
                                        file.delete();
                                    }
                                    if (C4368a.m7910a(C4369d.f6831aj, decode)) {
                                        c4368a.f6823ap = OAuthErrCode.WechatAuth_Err_OK;
                                        c4368a.f6826as = C4369d.f6831aj;
                                        c4368a.f6824aq = init.getString(AnalyticAttribute.UUID_ATTRIBUTE);
                                        c4368a.f6825ar = init.getString("appname");
                                        Log.d("MicroMsg.SDK.GetQRCodeResult", String.format("parse succ, save in external storage, uuid = %s, appname = %s, imgPath = %s", new Object[]{c4368a.f6824aq, c4368a.f6825ar, c4368a.f6826as}));
                                    } else {
                                        Log.e("MicroMsg.SDK.GetQRCodeResult", String.format("writeToFile fail, qrcodeBuf length = %d", new Object[]{Integer.valueOf(decode.length)}));
                                        c4368a.f6823ap = OAuthErrCode.WechatAuth_Err_NormalErr;
                                    }
                                } else {
                                    c4368a.f6823ap = OAuthErrCode.WechatAuth_Err_OK;
                                    c4368a.f6829av = decode;
                                    c4368a.f6824aq = init.getString(AnalyticAttribute.UUID_ATTRIBUTE);
                                    c4368a.f6825ar = init.getString("appname");
                                    Log.d("MicroMsg.SDK.GetQRCodeResult", String.format("parse succ, save in memory, uuid = %s, appname = %s, imgBufLength = %d", new Object[]{c4368a.f6824aq, c4368a.f6825ar, Integer.valueOf(c4368a.f6829av.length)}));
                                }
                            }
                        }
                    } catch (Exception e) {
                        Log.e("MicroMsg.SDK.GetQRCodeResult", String.format("parse json fail, ex = %s", new Object[]{e.getMessage()}));
                        c4368a.f6823ap = OAuthErrCode.WechatAuth_Err_NormalErr;
                    }
                } catch (Exception e2) {
                    Log.e("MicroMsg.SDK.GetQRCodeResult", String.format("parse fail, build String fail, ex = %s", new Object[]{e2.getMessage()}));
                    c4368a.f6823ap = OAuthErrCode.WechatAuth_Err_NormalErr;
                }
            }
            return c4368a;
        }
    }

    static {
        boolean z = Environment.getExternalStorageState().equals("mounted") && new File(Environment.getExternalStorageDirectory().getAbsolutePath()).canWrite();
        f6830ai = z;
        f6832ak = null;
        f6832ak = "http://open.weixin.qq.com/connect/sdk/qrconnect?appid=%s&noncestr=%s&timestamp=%s&scope=%s&signature=%s";
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
            TraceMachine.enterMethod(this._nr_trace, "d#doInBackground", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "d#doInBackground", null);
            }
        }
        Log.i("MicroMsg.SDK.GetQRCodeTask", "external storage available = " + f6830ai);
        String format = String.format(f6832ak, new Object[]{this.appId, this.f6833al, this.f6834am, this.scope, this.signature});
        long currentTimeMillis = System.currentTimeMillis();
        byte[] b = C4370e.m7914b(format, -1);
        Log.d("MicroMsg.SDK.GetQRCodeTask", String.format("doInBackground, url = %s, time consumed = %d(ms)", new Object[]{format, Long.valueOf(System.currentTimeMillis() - currentTimeMillis)}));
        C4368a d = C4368a.m7911d(b);
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return d;
    }

    /* Access modifiers changed, original: protected|final|synthetic */
    public final /* synthetic */ void onPostExecute(Object obj) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "d#onPostExecute", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "d#onPostExecute", null);
            }
        }
        C4368a c4368a = (C4368a) obj;
        if (c4368a.f6823ap == OAuthErrCode.WechatAuth_Err_OK) {
            Log.d("MicroMsg.SDK.GetQRCodeTask", "onPostExecute, get qrcode success");
            this.f6835an.onAuthGotQrcode(c4368a.f6826as, c4368a.f6829av);
            this.f6836ao = new C4372f(c4368a.f6824aq, this.f6835an);
            C4372f c4372f = this.f6836ao;
            if (VERSION.SDK_INT >= 11) {
                Executor executor = AsyncTask.THREAD_POOL_EXECUTOR;
                Void[] voidArr = new Void[0];
                if (c4372f instanceof AsyncTask) {
                    AsyncTaskInstrumentation.executeOnExecutor(c4372f, executor, voidArr);
                } else {
                    c4372f.executeOnExecutor(executor, voidArr);
                }
                TraceMachine.exitMethod();
                return;
            }
            Void[] voidArr2 = new Void[0];
            if (c4372f instanceof AsyncTask) {
                AsyncTaskInstrumentation.execute(c4372f, voidArr2);
            } else {
                c4372f.execute(voidArr2);
            }
            TraceMachine.exitMethod();
            return;
        }
        Log.e("MicroMsg.SDK.GetQRCodeTask", String.format("onPostExecute, get qrcode fail, OAuthErrCode = %s", new Object[]{c4368a.f6823ap}));
        this.f6835an.onAuthFinish(c4368a.f6823ap, null);
        TraceMachine.exitMethod();
    }
}
