package com.baidu.android.pushservice.p033e;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.baidu.android.pushservice.C1328a;
import com.baidu.android.pushservice.C1457i;
import com.baidu.android.pushservice.C1475k;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.p026j.C1281c;
import com.baidu.android.pushservice.p026j.C1462d;
import com.baidu.android.pushservice.p031c.C1332b;
import com.baidu.android.pushservice.p031c.C1339h;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p036h.C1426b;
import com.baidu.android.pushservice.p037i.C1438d;
import com.baidu.android.pushservice.p037i.C1446k;
import com.baidu.android.pushservice.p037i.C1449n;
import com.baidu.android.pushservice.p037i.C1456u;
import com.baidu.android.pushservice.p037i.p038a.C1432b;
import com.baidu.android.pushservice.util.C1548l;
import com.baidu.android.pushservice.util.C1577u;
import com.baidu.android.pushservice.util.C1578v;
import com.facebook.internal.NativeProtocol;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.e.a */
public abstract class C1361a extends C1281c {
    /* renamed from: a */
    protected Context f4799a;
    /* renamed from: b */
    protected C1378l f4800b;
    /* renamed from: c */
    protected String f4801c = C1457i.m6633f();
    /* renamed from: d */
    private C1360a f4802d = new C1360a();

    /* renamed from: com.baidu.android.pushservice.e.a$a */
    public class C1360a {
        /* renamed from: a */
        public void mo13717a(Boolean bool) {
            C1425a.m6442c("AbstractProcessor", "isGetChannelToken result:  " + bool);
            C1578v.m7095b("RequetChannelListener#isGetChannelToken#isSucceed=" + bool, C1361a.this.f4799a);
            if (bool.booleanValue()) {
                C1426b.m6448c("AbstractProcessor", "netWorkConnect connectResult: " + C1361a.this.mo13725c(), C1361a.this.f4799a);
            } else if (!C1475k.m6721a(C1361a.this.f4799a).mo13950c()) {
                C1361a.this.mo13718a(10002);
                C1578v.m7095b("RequetChannelListener#isGetChannelToken#isSucceed=false, errorcode=10002", C1361a.this.f4799a);
            }
        }
    }

    public C1361a(C1378l c1378l, Context context) {
        this.f4800b = c1378l;
        this.f4799a = context.getApplicationContext();
        mo13488a((short) 100);
        mo13489c("http-" + c1378l.f4829a);
    }

    /* renamed from: a */
    private void m6174a(String str, int i) {
        final String str2 = str;
        final int i2 = i;
        C1462d.m6637a().mo13938a(new C1281c("insertHttpBehavior", (short) 95) {
            /* renamed from: a */
            public void mo13487a() {
                try {
                    C1446k c1446k = new C1446k();
                    c1446k.f5036f = str2;
                    c1446k.f5037g = System.currentTimeMillis();
                    c1446k.f5038h = C1432b.m6486c(C1361a.this.f4799a);
                    c1446k.f5039i = i2;
                    if (str2.equals("030403")) {
                        c1446k.f5042l = C1578v.m7151w(C1361a.this.f4799a);
                    } else if (str2.equals("030401")) {
                        c1446k.f5042l = C1578v.m7154x(C1361a.this.f4799a);
                    }
                    C1456u.m6616b(C1361a.this.f4799a, c1446k);
                } catch (Exception e) {
                    C1425a.m6444e("AbstractProcessor", "insertHttpBehavior exception");
                }
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x0140 A:{SYNTHETIC, Splitter:B:47:0x0140} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0132 A:{Catch:{ all -> 0x0163 }} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x016d A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:15:0x0074} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0132 A:{Catch:{ all -> 0x0163 }} */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0140 A:{SYNTHETIC, Splitter:B:47:0x0140} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x016d A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:15:0x0074} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:40:0x010f, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:41:0x0110, code skipped:
            r3 = r4;
            r4 = 0;
     */
    /* JADX WARNING: Missing block: B:45:0x0132, code skipped:
            mo13718a(10003);
     */
    /* JADX WARNING: Missing block: B:48:?, code skipped:
            com.baidu.android.pushservice.util.C1578v.m7095b("tryConnect failed setResult UnKnown " + r0.getMessage(), r10.f4799a);
            mo13718a(20001);
     */
    /* JADX WARNING: Missing block: B:53:0x016d, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:56:0x0173, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:57:0x0174, code skipped:
            r3 = r4;
            r4 = r5;
     */
    /* renamed from: b */
    private int m6175b(int r11) {
        /*
        r10 = this;
        r3 = 10002; // 0x2712 float:1.4016E-41 double:4.9416E-320;
        r1 = 1;
        r2 = 0;
        if (r11 <= 0) goto L_0x0073;
    L_0x0006:
        r4 = r10.f4799a;
        if (r11 != r1) goto L_0x0012;
    L_0x000a:
        r0 = r1;
    L_0x000b:
        r0 = com.baidu.android.pushservice.C1457i.m6626b(r4, r0);
        if (r0 != 0) goto L_0x0014;
    L_0x0011:
        return r3;
    L_0x0012:
        r0 = r2;
        goto L_0x000b;
    L_0x0014:
        r4 = r10.f4801c;
        r5 = "http://";
        r4 = r4.startsWith(r5);
        if (r4 == 0) goto L_0x0073;
    L_0x001e:
        r4 = r10.f4801c;
        r5 = "http://";
        r6 = "";
        r4 = r4.replace(r5, r6);
        r10.f4801c = r4;
        r4 = r10.f4801c;
        r5 = "/";
        r4 = r4.indexOf(r5);
        if (r4 <= 0) goto L_0x003c;
    L_0x0034:
        r5 = r10.f4801c;
        r4 = r5.substring(r4);
        r10.f4801c = r4;
    L_0x003c:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "http://";
        r4 = r4.append(r5);
        r0 = r4.append(r0);
        r4 = r10.f4801c;
        r0 = r0.append(r4);
        r0 = r0.toString();
        r10.f4801c = r0;
        r0 = "AbstractProcessor";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = " --- abstract request URL: ";
        r4 = r4.append(r5);
        r5 = r10.f4801c;
        r4 = r4.append(r5);
        r4 = r4.toString();
        r5 = r10.f4799a;
        com.baidu.android.pushservice.p036h.C1426b.m6445a(r0, r4, r5);
    L_0x0073:
        r4 = 0;
        r0 = new java.util.HashMap;	 Catch:{ Exception -> 0x010f, all -> 0x016d }
        r0.<init>();	 Catch:{ Exception -> 0x010f, all -> 0x016d }
        r10.mo13722a(r0);	 Catch:{ Exception -> 0x010f, all -> 0x016d }
        r5 = r10.f4801c;	 Catch:{ Exception -> 0x010f, all -> 0x016d }
        r6 = "POST";
        r0 = com.baidu.android.pushservice.p034f.C1403b.m6259a(r5, r6, r0);	 Catch:{ Exception -> 0x010f, all -> 0x016d }
        r6 = r0.mo13745b();	 Catch:{ Exception -> 0x010f, all -> 0x016d }
        r4 = r0.mo13742a();	 Catch:{ Exception -> 0x010f, all -> 0x016d }
        r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r6 != r0) goto L_0x00d1;
    L_0x0090:
        r0 = com.baidu.android.pushservice.p037i.p038a.C1432b.m6481a(r4);	 Catch:{ Exception -> 0x016f, all -> 0x016d }
        r5 = "AbstractProcessor";
        r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x016f, all -> 0x016d }
        r7.<init>();	 Catch:{ Exception -> 0x016f, all -> 0x016d }
        r8 = "<<< networkRegister return string :  ";
        r7 = r7.append(r8);	 Catch:{ Exception -> 0x016f, all -> 0x016d }
        r7 = r7.append(r0);	 Catch:{ Exception -> 0x016f, all -> 0x016d }
        r7 = r7.toString();	 Catch:{ Exception -> 0x016f, all -> 0x016d }
        com.baidu.android.pushservice.p036h.C1425a.m6441b(r5, r7);	 Catch:{ Exception -> 0x016f, all -> 0x016d }
        r0 = r10.mo13723b(r0);	 Catch:{ Exception -> 0x016f, all -> 0x016d }
        r5 = 0;
        r0 = r0.getBytes();	 Catch:{ Exception -> 0x016f, all -> 0x016d }
        r10.mo13719a(r5, r0);	 Catch:{ Exception -> 0x016f, all -> 0x016d }
        r5 = r2;
        r0 = r2;
    L_0x00ba:
        if (r4 == 0) goto L_0x00be;
    L_0x00bc:
        if (r6 != 0) goto L_0x00c7;
    L_0x00be:
        r0 = 2;
        if (r11 < r0) goto L_0x00c6;
    L_0x00c1:
        r0 = 10002; // 0x2712 float:1.4016E-41 double:4.9416E-320;
        r10.mo13718a(r0);	 Catch:{ Exception -> 0x0173, all -> 0x016d }
    L_0x00c6:
        r0 = r3;
    L_0x00c7:
        r1 = new java.io.Closeable[r1];
        r1[r2] = r4;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r1);
    L_0x00ce:
        r3 = r0;
        goto L_0x0011;
    L_0x00d1:
        r0 = "AbstractProcessor";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x016f, all -> 0x016d }
        r5.<init>();	 Catch:{ Exception -> 0x016f, all -> 0x016d }
        r7 = "networkRegister request failed:  ";
        r5 = r5.append(r7);	 Catch:{ Exception -> 0x016f, all -> 0x016d }
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x016f, all -> 0x016d }
        r5 = r5.toString();	 Catch:{ Exception -> 0x016f, all -> 0x016d }
        com.baidu.android.pushservice.p036h.C1425a.m6441b(r0, r5);	 Catch:{ Exception -> 0x016f, all -> 0x016d }
        r0 = 503; // 0x1f7 float:7.05E-43 double:2.485E-321;
        if (r6 != r0) goto L_0x0177;
    L_0x00ed:
        r5 = r1;
    L_0x00ee:
        r0 = com.baidu.android.pushservice.p037i.p038a.C1432b.m6481a(r4);	 Catch:{ Exception -> 0x0173, all -> 0x016d }
        r7 = "AbstractProcessor";
        r8 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0173, all -> 0x016d }
        r8.<init>();	 Catch:{ Exception -> 0x0173, all -> 0x016d }
        r9 = "<<< networkRegister return string :  ";
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x0173, all -> 0x016d }
        r8 = r8.append(r0);	 Catch:{ Exception -> 0x0173, all -> 0x016d }
        r8 = r8.toString();	 Catch:{ Exception -> 0x0173, all -> 0x016d }
        com.baidu.android.pushservice.p036h.C1425a.m6441b(r7, r8);	 Catch:{ Exception -> 0x0173, all -> 0x016d }
        r10.mo13721a(r0);	 Catch:{ Exception -> 0x0173, all -> 0x016d }
        r0 = r6;
        goto L_0x00ba;
    L_0x010f:
        r0 = move-exception;
        r3 = r4;
        r4 = r2;
    L_0x0112:
        r5 = "AbstractProcessor";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0163 }
        r6.<init>();	 Catch:{ all -> 0x0163 }
        r7 = "error : ";
        r6 = r6.append(r7);	 Catch:{ all -> 0x0163 }
        r7 = r0.getMessage();	 Catch:{ all -> 0x0163 }
        r6 = r6.append(r7);	 Catch:{ all -> 0x0163 }
        r6 = r6.toString();	 Catch:{ all -> 0x0163 }
        r7 = r10.f4799a;	 Catch:{ all -> 0x0163 }
        com.baidu.android.pushservice.p036h.C1426b.m6447b(r5, r6, r7);	 Catch:{ all -> 0x0163 }
        if (r4 == 0) goto L_0x0140;
    L_0x0132:
        r0 = 10003; // 0x2713 float:1.4017E-41 double:4.942E-320;
        r10.mo13718a(r0);	 Catch:{ all -> 0x0163 }
    L_0x0137:
        r0 = -1;
        r1 = new java.io.Closeable[r1];
        r1[r2] = r3;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r1);
        goto L_0x00ce;
    L_0x0140:
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0163 }
        r4.<init>();	 Catch:{ all -> 0x0163 }
        r5 = "tryConnect failed setResult UnKnown ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0163 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0163 }
        r0 = r4.append(r0);	 Catch:{ all -> 0x0163 }
        r0 = r0.toString();	 Catch:{ all -> 0x0163 }
        r4 = r10.f4799a;	 Catch:{ all -> 0x0163 }
        com.baidu.android.pushservice.util.C1578v.m7095b(r0, r4);	 Catch:{ all -> 0x0163 }
        r0 = 20001; // 0x4e21 float:2.8027E-41 double:9.882E-320;
        r10.mo13718a(r0);	 Catch:{ all -> 0x0163 }
        goto L_0x0137;
    L_0x0163:
        r0 = move-exception;
        r4 = r3;
    L_0x0165:
        r1 = new java.io.Closeable[r1];
        r1[r2] = r4;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r1);
        throw r0;
    L_0x016d:
        r0 = move-exception;
        goto L_0x0165;
    L_0x016f:
        r0 = move-exception;
        r3 = r4;
        r4 = r2;
        goto L_0x0112;
    L_0x0173:
        r0 = move-exception;
        r3 = r4;
        r4 = r5;
        goto L_0x0112;
    L_0x0177:
        r5 = r2;
        goto L_0x00ee;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p033e.C1361a.m6175b(int):int");
    }

    /* renamed from: b */
    private void m6176b(int i, byte[] bArr) {
        Intent intent = new Intent("com.baidu.android.pushservice.action.internal.RECEIVE");
        intent.putExtra("method", this.f4800b.f4829a);
        intent.putExtra("error_msg", i);
        intent.putExtra("content", bArr);
        intent.putExtra("appid", this.f4800b.f4834f);
        intent.setFlags(32);
        mo13720a(intent);
        C1425a.m6442c("AbstractProcessor", "> sendInternalMethodResult  ,method:" + this.f4800b.f4829a + " ,errorCode : " + i + " ,content : " + new String(bArr));
        this.f4799a.sendBroadcast(intent);
    }

    /* renamed from: d */
    private boolean m6177d(String str) {
        for (String equals : new String[]{"method_deal_lapp_bind_intent", "method_lapp_unbind", "method_set_lapp_tags", "method_del_lapp_tags", "method_list_lapp_tags"}) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public void mo13487a() {
        mo13724b();
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13718a(int i) {
        mo13719a(i, PushConstants.m5756a(i).getBytes());
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13719a(int i, byte[] bArr) {
        if (TextUtils.isEmpty(this.f4800b.f4830b) || !this.f4800b.f4830b.equals("internal")) {
            Intent intent = new Intent();
            if (this.f4800b.f4829a.equals("method_deal_webapp_bind_intent")) {
                intent.setAction("com.baidu.android.pushservice.action.web.RECEIVE");
            } else if (this.f4800b.f4829a.equals("method_deal_lapp_bind_intent") || this.f4800b.f4829a.equals("method_lapp_unbind") || this.f4800b.f4829a.equals("method_set_lapp_tags") || this.f4800b.f4829a.equals("method_del_lapp_tags") || this.f4800b.f4829a.equals("method_list_lapp_tags")) {
                intent.setAction("com.baidu.android.pushservice.action.lapp.RECEIVE");
            } else if (this.f4800b.f4829a.equals("method_sdk_bind")) {
                intent.setAction("com.baidu.android.pushservice.action.sdk.RECEIVE");
            } else {
                intent.setAction("com.baidu.android.pushservice.action.RECEIVE");
            }
            intent.putExtra("method", this.f4800b.f4829a);
            intent.putExtra("error_msg", i);
            intent.putExtra("content", bArr);
            intent.setFlags(32);
            mo13720a(intent);
            Object obj = null;
            if (this.f4800b.f4829a.equals("method_bind")) {
                intent.putExtra("access_token", this.f4800b.f4832d);
                intent.putExtra("secret_key", this.f4800b.f4837i);
                intent.putExtra("real_bind", "real_bind");
                C1438d c1438d = new C1438d();
                c1438d.f5036f = "020101";
                c1438d.f5037g = System.currentTimeMillis();
                c1438d.f5038h = C1432b.m6486c(this.f4799a);
                C1449n c1449n = new C1449n();
                try {
                    JSONObject init = JSONObjectInstrumentation.init(new String(bArr));
                    c1438d.f5064b = init.getString("request_id");
                    if (i != 0) {
                        c1438d.f5063a = init.getString("error_msg");
                    }
                    String string = init.getJSONObject("response_params").getString("appid");
                    C1339h d = C1332b.m6020a(this.f4799a).mo13600d(string);
                    c1438d.f5040j = string;
                    String string2 = init.getJSONObject("response_params").getString("user_id");
                    c1449n.mo13854a(string);
                    c1449n.mo13859c(C1578v.m7069a(string2));
                    c1449n.mo13857b(string2);
                    if (d != null) {
                        c1449n.mo13861d(d.mo13589c());
                        c1449n = C1578v.m7066a(c1449n, this.f4799a, d.mo13589c());
                    }
                } catch (JSONException e) {
                    obj = 1;
                    if (bArr != null) {
                        C1425a.m6444e("AbstractProcessor", "bind failed msg: " + new String(bArr));
                    } else {
                        C1426b.m6447b("AbstractProcessor", "bind failed", this.f4799a);
                    }
                }
                c1438d.f5039i = i;
                try {
                    C1456u.m6609a(this.f4799a, c1438d);
                    if (obj == null) {
                        C1456u.m6612a(this.f4799a, c1449n);
                    }
                } catch (Exception e2) {
                    C1426b.m6447b("AbstractProcessor", "error " + e2.getMessage(), this.f4799a);
                }
                if (C1328a.m6006b() > 0) {
                    C1446k c1446k = new C1446k();
                    c1446k.f5037g = System.currentTimeMillis();
                    c1446k.f5038h = C1432b.m6486c(this.f4799a);
                    c1446k.f5041k = this.f4800b.f4837i;
                    c1446k.f5036f = "039904";
                    c1446k.f5039i = i;
                    if (bArr != null && bArr.length > 0) {
                        c1446k.f5042l = new String(bArr);
                    }
                    C1456u.m6611a(this.f4799a, c1446k);
                }
            } else if (bArr != null && (this.f4800b.f4829a.equals("method_unbind") || this.f4800b.f4829a.equals("com.baidu.android.pushservice.action.UNBINDAPP"))) {
                C1438d c1438d2 = new C1438d();
                if (this.f4800b.f4829a.equals("method_unbind")) {
                    c1438d2.f5036f = "020301";
                } else {
                    c1438d2.f5036f = "020601";
                }
                c1438d2.f5037g = System.currentTimeMillis();
                c1438d2.f5038h = C1432b.m6486c(this.f4799a);
                c1438d2.f5040j = this.f4800b.f4834f;
                try {
                    c1438d2.f5064b = JSONObjectInstrumentation.init(new String(bArr)).getString("request_id");
                } catch (JSONException e3) {
                    C1426b.m6447b("AbstractProcessor", "unbind failed msg: " + new String(bArr), this.f4799a);
                    c1438d2.f5063a = new String(bArr);
                }
                try {
                    C1456u.m6609a(this.f4799a, c1438d2);
                } catch (Exception e22) {
                    C1426b.m6447b("AbstractProcessor", "error " + e22.getMessage(), this.f4799a);
                }
            }
            if (!TextUtils.isEmpty(this.f4800b.f4833e) || m6177d(this.f4800b.f4829a)) {
                if (!m6177d(this.f4800b.f4829a)) {
                    intent.setPackage(this.f4800b.f4833e);
                }
                String str = "> sendResult to " + this.f4800b.f4837i + ", method:" + this.f4800b.f4829a + ", errorCode : " + i + ", content : " + new String(bArr);
                C1425a.m6442c("AbstractProcessor", str);
                C1578v.m7095b(str, this.f4799a);
                if (!this.f4800b.f4829a.equals("com.baidu.android.pushservice.action.UNBINDAPP") && TextUtils.isEmpty(this.f4800b.f4838j)) {
                    C1578v.m7094b(this.f4799a, intent, intent.getAction(), this.f4800b.f4833e);
                    return;
                }
                return;
            }
            return;
        }
        m6176b(i, bArr);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13720a(Intent intent) {
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13721a(String str) {
        if (str != null) {
            if (!str.startsWith("{\"")) {
                str = str.substring(str.indexOf("{\""));
            }
            try {
                JSONObject init = JSONObjectInstrumentation.init(str);
                int i = init.getInt(NativeProtocol.BRIDGE_ARG_ERROR_CODE);
                String string = init.getString("error_msg");
                String string2 = init.getString("request_id");
                init = new JSONObject();
                init.put("error_msg", string);
                init.put("request_id", string2);
                mo13719a(i, (!(init instanceof JSONObject) ? init.toString() : JSONObjectInstrumentation.toString(init)).getBytes());
            } catch (JSONException e) {
                C1426b.m6447b("AbstractProcessor", "error : " + e.getMessage(), this.f4799a);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13722a(HashMap<String, String> hashMap) {
        C1370b.m6206b(hashMap);
        if (!TextUtils.isEmpty(this.f4800b.f4836h)) {
            hashMap.put("bduss", this.f4800b.f4836h);
            hashMap.put("appid", this.f4800b.f4834f);
        } else if (!TextUtils.isEmpty(this.f4800b.f4832d)) {
            hashMap.put("access_token", this.f4800b.f4832d);
        } else if (!TextUtils.isEmpty(this.f4800b.f4837i)) {
            hashMap.put("apikey", this.f4800b.f4837i);
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public String mo13723b(String str) {
        return str;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public void mo13724b() {
        if (this.f4800b == null || TextUtils.isEmpty(this.f4800b.f4829a)) {
            C1578v.m7095b("AbstractProcessor#execute#mEvent = null or mEvent.method = null", this.f4799a);
        } else if (!this.f4800b.f4829a.equals("com.baidu.android.pushservice.action.UNBIND") && !this.f4800b.f4829a.equals("method_deal_webapp_bind_intent") && !this.f4800b.f4829a.equals("method_deal_lapp_bind_intent") && !this.f4800b.f4829a.equals("method_sdk_unbind") && !this.f4800b.f4829a.equals("method_lapp_unbind") && !this.f4800b.f4829a.equals("method_set_lapp_tags") && !this.f4800b.f4829a.equals("method_del_lapp_tags") && !this.f4800b.f4829a.equals("method_list_lapp_tags") && TextUtils.isEmpty(this.f4800b.f4833e) && !this.f4800b.f4829a.equals("com.baidu.android.pushservice.action.UNBINDAPP")) {
            C1425a.m6444e("AbstractProcessor", "Unknown method = " + this.f4800b.f4829a);
            C1578v.m7095b("AbstractProcessor#execute#Unknown method", this.f4799a);
        } else if (C1548l.m6952e(this.f4799a)) {
            if (C1328a.m6006b() > 0) {
                C1456u.m6615a(this.f4799a, "039914");
            }
            C1475k a = C1475k.m6721a(this.f4799a);
            synchronized (a) {
                if (a.mo13951d() || !a.mo13950c()) {
                    a.mo13947a(this.f4799a, false, this.f4802d);
                    C1425a.m6442c("AbstractProcessor", "requestToken   AbstractProcessor  ");
                    C1578v.m7095b("AbstractProcessor#requestToken#" + this.f4800b.toString(), this.f4799a);
                } else {
                    C1426b.m6448c("AbstractProcessor", "netWorkConnect connectResult: " + mo13725c(), this.f4799a);
                }
            }
        } else {
            C1426b.m6447b("AbstractProcessor", "Network is not useful!", this.f4799a);
            C1578v.m7095b("AbstractProcessor#execute#Network is unuseful!", this.f4799a);
            if (C1328a.m6006b() > 0) {
                C1456u.m6615a(this.f4799a, "039912");
            }
            mo13718a(10001);
            C1577u.m7045a(this.f4799a, new Intent());
            C1425a.m6441b("AbstractProcessor", "startPushService BaseApiProcess");
        }
    }

    /* renamed from: c */
    public boolean mo13725c() {
        boolean z = false;
        if (!TextUtils.isEmpty(this.f4801c)) {
            C1425a.m6442c("AbstractProcessor", "Request Url = " + this.f4801c);
            int i = 0;
            while (i <= 2) {
                int b = m6175b(i);
                if (b != 0) {
                    if (b != 10002) {
                        break;
                    }
                    if (i > 0) {
                        m6174a("030403", b);
                    } else {
                        m6174a("030401", b);
                    }
                    i++;
                } else {
                    z = true;
                    if (i > 0) {
                        m6174a("030402", b);
                    }
                }
            }
        } else {
            C1426b.m6447b("AbstractProcessor", "mUrl is null", this.f4799a);
        }
        return z;
    }
}
