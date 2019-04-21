package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.alipay.sdk.app.PayResultActivity.C0575a;
import com.alipay.sdk.app.statistic.C0590a;
import com.alipay.sdk.cons.C0611a;
import com.alipay.sdk.data.C0615a;
import com.alipay.sdk.data.C0615a.C0614a;
import com.alipay.sdk.data.C0617c;
import com.alipay.sdk.encrypt.C0619a;
import com.alipay.sdk.protocol.C0637a;
import com.alipay.sdk.protocol.C0638b;
import com.alipay.sdk.sys.C0639a;
import com.alipay.sdk.sys.C0640b;
import com.alipay.sdk.tid.C0643b;
import com.alipay.sdk.util.C0646c;
import com.alipay.sdk.util.C0648e;
import com.alipay.sdk.util.C0648e.C0577a;
import com.alipay.sdk.util.C0651h;
import com.alipay.sdk.util.C0654k;
import com.alipay.sdk.util.C0657m;
import com.alipay.sdk.util.C0657m.C0656a;
import com.alipay.sdk.util.H5PayResultModel;
import com.alipay.sdk.widget.C0664a;
import com.facebook.internal.NativeProtocol;
import com.mcdonalds.sdk.modules.notification.PushConstants;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.payload.PayloadController;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONObject;

public class PayTask {
    /* renamed from: a */
    static final Object f469a = C0648e.class;
    /* renamed from: h */
    private static long f470h = 0;
    /* renamed from: j */
    private static long f471j = -1;
    /* renamed from: b */
    private Activity f472b;
    /* renamed from: c */
    private C0664a f473c;
    /* renamed from: d */
    private String f474d = "wappaygw.alipay.com/service/rest.htm";
    /* renamed from: e */
    private String f475e = "mclient.alipay.com/service/rest.htm";
    /* renamed from: f */
    private String f476f = "mclient.alipay.com/home/exterfaceAssign.htm";
    /* renamed from: g */
    private Map<String, C0576a> f477g = new HashMap();

    /* renamed from: com.alipay.sdk.app.PayTask$a */
    private class C0576a {
        /* renamed from: b */
        private String f467b;
        /* renamed from: c */
        private String f468c;

        private C0576a() {
            this.f467b = "";
            this.f468c = "";
        }

        /* synthetic */ C0576a(PayTask payTask, C0585g c0585g) {
            this();
        }

        /* renamed from: a */
        public String mo7981a() {
            return this.f467b;
        }

        /* renamed from: a */
        public void mo7982a(String str) {
            this.f467b = str;
        }

        /* renamed from: b */
        public String mo7983b() {
            return this.f468c;
        }

        /* renamed from: b */
        public void mo7984b(String str) {
            this.f468c = str;
        }
    }

    public PayTask(Activity activity) {
        this.f472b = activity;
        C0640b.m974a().mo8087a(this.f472b, C0617c.m879b());
        C0590a.m799a(activity);
        this.f473c = new C0664a(activity, "去支付宝付款");
    }

    public synchronized String pay(String str, boolean z) {
        String d;
        if (m771d()) {
            d = C0588j.m794d();
        } else {
            if (z) {
                m769b();
            }
            if (str.contains("payment_inst=")) {
                d = str.substring(str.indexOf("payment_inst=") + 13);
                int indexOf = d.indexOf(38);
                if (indexOf > 0) {
                    d = d.substring(0, indexOf);
                }
                C0587i.m786a(d.replaceAll("\"", "").toLowerCase(Locale.getDefault()).replaceAll("alipay", ""));
            } else {
                C0587i.m786a("");
            }
            if (str.contains("service=alipay.acquire.mr.ord.createandpay")) {
                C0611a.f568s = true;
            }
            if (C0611a.f568s) {
                if (str.startsWith("https://wappaygw.alipay.com/home/exterfaceAssign.htm?")) {
                    str = str.substring(str.indexOf("https://wappaygw.alipay.com/home/exterfaceAssign.htm?") + "https://wappaygw.alipay.com/home/exterfaceAssign.htm?".length());
                } else if (str.startsWith("https://mclient.alipay.com/home/exterfaceAssign.htm?")) {
                    str = str.substring(str.indexOf("https://mclient.alipay.com/home/exterfaceAssign.htm?") + "https://mclient.alipay.com/home/exterfaceAssign.htm?".length());
                }
            }
            d = "";
            try {
                d = m762a(str);
                C0651h.m1033a(this.f472b.getApplicationContext(), d);
            } catch (Throwable th) {
                Throwable th2 = th;
                d = C0588j.m793c();
                C0646c.m1016a(th2);
                return d;
            } finally {
                C0615a.m869e().mo8052a(this.f472b.getApplicationContext());
                m770c();
                C0590a.m805b(this.f472b.getApplicationContext(), str);
            }
        }
        return d;
    }

    public synchronized Map<String, String> payV2(String str, boolean z) {
        return C0654k.m1043a(pay(str, z));
    }

    public synchronized String fetchTradeToken() {
        return C0651h.m1031a(this.f472b.getApplicationContext());
    }

    public synchronized boolean payInterceptorWithUrl(String str, boolean z, H5PayCallback h5PayCallback) {
        String fetchOrderInfoFromH5PayUrl;
        fetchOrderInfoFromH5PayUrl = fetchOrderInfoFromH5PayUrl(str);
        if (!TextUtils.isEmpty(fetchOrderInfoFromH5PayUrl)) {
            new Thread(new C0585g(this, fetchOrderInfoFromH5PayUrl, z, h5PayCallback)).start();
        }
        return !TextUtils.isEmpty(fetchOrderInfoFromH5PayUrl);
    }

    public synchronized String fetchOrderInfoFromH5PayUrl(String str) {
        String str2;
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject;
            String trim = str.trim();
            str2 = "";
            if (trim.startsWith("https://" + this.f474d) || trim.startsWith("http://" + this.f474d)) {
                str2 = trim.replaceFirst("(http|https)://" + this.f474d + "\\?", "").trim();
                if (!TextUtils.isEmpty(str2)) {
                    str2 = "_input_charset=\"utf-8\"&ordertoken=\"" + C0657m.m1055a("<request_token>", "</request_token>", (String) C0657m.m1063b(str2).get("req_data")) + "\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"" + new C0639a(this.f472b).mo8086a("sc", "h5tonative") + "\"";
                }
            }
            try {
                if (trim.startsWith("https://" + this.f475e) || trim.startsWith("http://" + this.f475e)) {
                    str2 = trim.replaceFirst("(http|https)://" + this.f475e + "\\?", "").trim();
                    if (!TextUtils.isEmpty(str2)) {
                        str2 = "_input_charset=\"utf-8\"&ordertoken=\"" + C0657m.m1055a("<request_token>", "</request_token>", (String) C0657m.m1063b(str2).get("req_data")) + "\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"" + new C0639a(this.f472b).mo8086a("sc", "h5tonative") + "\"";
                    }
                }
                if ((trim.startsWith("https://" + this.f476f) || trim.startsWith("http://" + this.f476f)) && ((trim.contains("alipay.wap.create.direct.pay.by.user") || trim.contains("create_forex_trade_wap")) && !TextUtils.isEmpty(trim.replaceFirst("(http|https)://" + this.f476f + "\\?", "").trim()))) {
                    jSONObject = new JSONObject();
                    jSONObject.put(NativeProtocol.IMAGE_URL_KEY, str);
                    jSONObject.put("bizcontext", new C0639a(this.f472b).mo8086a("sc", "h5tonative"));
                    str2 = "new_external_info==" + (!(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject));
                }
            } catch (Throwable th) {
                C0646c.m1016a(th);
            }
            if (Pattern.compile("^(http|https)://(maliprod\\.alipay\\.com\\/w\\/trade_pay\\.do.?|mali\\.alipay\\.com\\/w\\/trade_pay\\.do.?|mclient\\.alipay\\.com\\/w\\/trade_pay\\.do.?)").matcher(str).find()) {
                str2 = C0657m.m1055a("?", "", str);
                if (!TextUtils.isEmpty(str2)) {
                    Map b = C0657m.m1063b(str2);
                    StringBuilder stringBuilder = new StringBuilder();
                    if (m767a(false, true, "trade_no", stringBuilder, b, "trade_no", "alipay_trade_no")) {
                        m767a(true, false, "pay_phase_id", stringBuilder, b, "payPhaseId", "pay_phase_id", "out_relation_id");
                        stringBuilder.append("&biz_sub_type=\"TRADE\"");
                        stringBuilder.append("&biz_type=\"trade\"");
                        str2 = (String) b.get(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING);
                        if (TextUtils.isEmpty(str2) && !TextUtils.isEmpty((CharSequence) b.get("cid"))) {
                            str2 = "ali1688";
                        } else if (TextUtils.isEmpty(str2) && !(TextUtils.isEmpty((CharSequence) b.get("sid")) && TextUtils.isEmpty((CharSequence) b.get("s_id")))) {
                            str2 = "tb";
                        }
                        stringBuilder.append("&app_name=\"" + str2 + "\"");
                        if (m767a(true, true, "extern_token", stringBuilder, b, "extern_token", "cid", "sid", "s_id")) {
                            m767a(true, false, "appenv", stringBuilder, b, "appenv");
                            stringBuilder.append("&pay_channel_id=\"alipay_sdk\"");
                            C0576a c0576a = new C0576a(this, null);
                            c0576a.mo7982a((String) b.get("return_url"));
                            c0576a.mo7984b((String) b.get("pay_order_id"));
                            str2 = stringBuilder.toString() + "&bizcontext=\"" + new C0639a(this.f472b).mo8086a("sc", "h5tonative") + "\"";
                            this.f477g.put(str2, c0576a);
                        } else {
                            str2 = "";
                        }
                    }
                }
            }
            if (trim.contains("mclient.alipay.com/cashier/mobilepay.htm") || (EnvUtils.isSandBox() && trim.contains("mobileclientgw.alipaydev.com/cashier/mobilepay.htm"))) {
                String a = new C0639a(this.f472b).mo8086a("sc", "h5tonative");
                jSONObject = new JSONObject();
                jSONObject.put(NativeProtocol.IMAGE_URL_KEY, trim);
                jSONObject.put("bizcontext", a);
                a = "new_external_info==%s";
                Object[] objArr = new Object[1];
                objArr[0] = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
                str2 = String.format(a, objArr);
            }
        }
        str2 = "";
        return str2;
    }

    public static synchronized boolean fetchSdkConfig(Context context) {
        boolean z = false;
        synchronized (PayTask.class) {
            try {
                C0640b.m974a().mo8087a(context, C0617c.m879b());
                long elapsedRealtime = SystemClock.elapsedRealtime() / 1000;
                if (elapsedRealtime - f470h >= ((long) C0615a.m869e().mo8054c())) {
                    f470h = elapsedRealtime;
                    C0615a.m869e().mo8052a(context.getApplicationContext());
                    z = true;
                }
            } catch (Exception e) {
                C0646c.m1016a(e);
            }
        }
        return z;
    }

    /* renamed from: a */
    private boolean m767a(boolean z, boolean z2, String str, StringBuilder stringBuilder, Map<String, String> map, String... strArr) {
        CharSequence charSequence;
        String str2 = "";
        for (Object obj : strArr) {
            if (!TextUtils.isEmpty((CharSequence) map.get(obj))) {
                charSequence = (String) map.get(obj);
                break;
            }
        }
        Object charSequence2 = str2;
        if (TextUtils.isEmpty(charSequence2)) {
            if (z2) {
                return false;
            }
        } else if (z) {
            stringBuilder.append("&").append(str).append("=\"").append(charSequence2).append("\"");
        } else {
            stringBuilder.append(str).append("=\"").append(charSequence2).append("\"");
        }
        return true;
    }

    public synchronized H5PayResultModel h5Pay(String str, boolean z) {
        H5PayResultModel h5PayResultModel;
        synchronized (this) {
            H5PayResultModel h5PayResultModel2 = new H5PayResultModel();
            try {
                str.trim();
                String[] split = pay(str, z).split(";");
                HashMap hashMap = new HashMap();
                for (String str2 : split) {
                    String substring = str2.substring(0, str2.indexOf("={"));
                    hashMap.put(substring, m763a(str2, substring));
                }
                if (hashMap.containsKey("resultStatus")) {
                    h5PayResultModel2.setResultCode((String) hashMap.get("resultStatus"));
                }
                if (hashMap.containsKey("callBackUrl")) {
                    h5PayResultModel2.setReturnUrl((String) hashMap.get("callBackUrl"));
                } else if (hashMap.containsKey("result")) {
                    String str3 = (String) hashMap.get("result");
                    if (str3.length() > 15) {
                        C0576a c0576a = (C0576a) this.f477g.get(str);
                        if (c0576a != null) {
                            if (TextUtils.isEmpty(c0576a.mo7983b())) {
                                h5PayResultModel2.setReturnUrl(c0576a.mo7981a());
                            } else {
                                h5PayResultModel2.setReturnUrl(C0615a.m869e().mo8053b().replace("$OrderId$", c0576a.mo7983b()));
                            }
                            this.f477g.remove(str);
                            h5PayResultModel = h5PayResultModel2;
                        } else {
                            CharSequence a = C0657m.m1055a("&callBackUrl=\"", "\"", str3);
                            if (TextUtils.isEmpty(a)) {
                                a = C0657m.m1055a("&call_back_url=\"", "\"", str3);
                                if (TextUtils.isEmpty(a)) {
                                    a = C0657m.m1055a("&return_url=\"", "\"", str3);
                                    if (TextUtils.isEmpty(a)) {
                                        a = URLDecoder.decode(C0657m.m1055a("&return_url=", "&", str3), "utf-8");
                                        if (TextUtils.isEmpty(a)) {
                                            a = URLDecoder.decode(C0657m.m1055a("&callBackUrl=", "&", str3), "utf-8");
                                        }
                                    }
                                }
                            }
                            if (TextUtils.isEmpty(a) && !TextUtils.isEmpty(str3) && str3.contains("call_back_url")) {
                                str3 = C0657m.m1062b("call_back_url=\"", "\"", str3);
                            } else {
                                CharSequence charSequence = a;
                            }
                            if (TextUtils.isEmpty(str3)) {
                                str3 = C0615a.m869e().mo8053b();
                            }
                            h5PayResultModel2.setReturnUrl(str3);
                        }
                    } else {
                        C0576a c0576a2 = (C0576a) this.f477g.get(str);
                        if (c0576a2 != null) {
                            h5PayResultModel2.setReturnUrl(c0576a2.mo7981a());
                            this.f477g.remove(str);
                            h5PayResultModel = h5PayResultModel2;
                        }
                    }
                }
            } catch (Throwable th) {
                C0646c.m1016a(th);
            }
            h5PayResultModel = h5PayResultModel2;
        }
        return h5PayResultModel;
    }

    /* renamed from: a */
    private String m763a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str3.length() + str.indexOf(str3), str.lastIndexOf("}"));
    }

    /* renamed from: a */
    private C0577a m759a() {
        return new C0586h(this);
    }

    /* renamed from: b */
    private void m769b() {
        if (this.f473c != null) {
            this.f473c.mo8141b();
        }
    }

    /* renamed from: c */
    private void m770c() {
        if (this.f473c != null) {
            this.f473c.mo8142c();
            this.f473c = null;
        }
    }

    /* renamed from: a */
    private String m762a(String str) {
        String str2 = "";
        String a = new C0639a(this.f472b).mo8085a(str);
        if (a.contains("paymethod=\"expressGateway\"")) {
            return m768b(a);
        }
        List d = C0615a.m869e().mo8055d();
        if (!C0615a.m869e().f573l || d == null) {
            d = C0587i.f495a;
        }
        if (C0657m.m1065b(this.f472b, d)) {
            C0648e c0648e = new C0648e(this.f472b, m759a());
            String a2 = c0648e.mo8110a(a);
            c0648e.mo8111a();
            if (TextUtils.equals(a2, "failed")) {
                C0590a.m801a("biz", "LogBindCalledH5", "");
                return m768b(a);
            } else if (TextUtils.isEmpty(a2)) {
                return C0588j.m793c();
            } else {
                if (!a2.contains("{\"isLogin\":\"false\"}")) {
                    return a2;
                }
                C0590a.m801a("biz", "LogHkLoginByIntent", "");
                return m764a(a, d, a2, this.f472b);
            }
        }
        C0590a.m801a("biz", "LogCalledH5", "");
        return m768b(a);
    }

    /* renamed from: a */
    private static String m764a(String str, List<C0614a> list, String str2, Activity activity) {
        C0656a a = C0657m.m1049a((Context) activity, (List) list);
        if (a == null || a.mo8114a() || a.mo8115b() || !TextUtils.equals(a.f662a.packageName, "hk.alipay.wallet")) {
            return str2;
        }
        C0646c.m1017b("msp", "PayTask:payResult: NOT_LOGIN");
        String valueOf = String.valueOf(str.hashCode());
        PayResultActivity.f465b.put(valueOf, new Object());
        Intent intent = new Intent(activity, PayResultActivity.class);
        intent.putExtra("orderSuffix", str);
        intent.putExtra("externalPkgName", activity.getPackageName());
        intent.putExtra("phonecashier.pay.hash", valueOf);
        activity.startActivity(intent);
        synchronized (PayResultActivity.f465b.get(valueOf)) {
            try {
                C0646c.m1017b("msp", "PayTask:payResult: wait");
                PayResultActivity.f465b.get(valueOf).wait();
            } catch (InterruptedException e) {
                C0646c.m1017b("msp", "PayTask:payResult: InterruptedException:" + e);
                return C0588j.m793c();
            }
        }
        str2 = C0575a.f464b;
        C0646c.m1017b("msp", "PayTask:payResult: result:" + str2);
        return str2;
    }

    public String getVersion() {
        return "15.5.9";
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008d  */
    /* renamed from: b */
    private java.lang.String m768b(java.lang.String r9) {
        /*
        r8 = this;
        r1 = 0;
        r2 = 0;
        r8.m769b();
        r0 = new com.alipay.sdk.packet.impl.e;	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        r0.<init>();	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        r3 = r8.f472b;	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        r3 = r3.getApplicationContext();	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        r0 = r0.mo8074a(r3, r9);	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        r4 = r0.mo8067c();	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        r0 = "end_code";
        r3 = 0;
        r5 = r4.optString(r0, r3);	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        r0 = "form";
        r0 = r4.optJSONObject(r0);	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        r3 = "onload";
        r0 = r0.optJSONObject(r3);	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        r6 = com.alipay.sdk.protocol.C0638b.m960a(r0);	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        r3 = r2;
    L_0x0030:
        r0 = r6.size();	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        if (r3 >= r0) goto L_0x0051;
    L_0x0036:
        r0 = r6.get(r3);	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        r0 = (com.alipay.sdk.protocol.C0638b) r0;	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        r0 = r0.mo8083b();	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        r7 = com.alipay.sdk.protocol.C0637a.Update;	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        if (r0 != r7) goto L_0x004d;
    L_0x0044:
        r0 = r6.get(r3);	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        r0 = (com.alipay.sdk.protocol.C0638b) r0;	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        com.alipay.sdk.protocol.C0638b.m961a(r0);	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
    L_0x004d:
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x0030;
    L_0x0051:
        r8.m766a(r4);	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        r8.m770c();	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
    L_0x0057:
        r0 = r6.size();	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        if (r2 >= r0) goto L_0x0087;
    L_0x005d:
        r0 = r6.get(r2);	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        r0 = (com.alipay.sdk.protocol.C0638b) r0;	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        r3 = r0.mo8083b();	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        r4 = com.alipay.sdk.protocol.C0637a.WapPay;	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        if (r3 != r4) goto L_0x0073;
    L_0x006b:
        r0 = r8.m760a(r0);	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        r8.m770c();
    L_0x0072:
        return r0;
    L_0x0073:
        r3 = r0.mo8083b();	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        r4 = com.alipay.sdk.protocol.C0637a.OpenWeb;	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        if (r3 != r4) goto L_0x0083;
    L_0x007b:
        r0 = r8.m761a(r0, r5);	 Catch:{ IOException -> 0x00a6, Throwable -> 0x00bb }
        r8.m770c();
        goto L_0x0072;
    L_0x0083:
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x0057;
    L_0x0087:
        r8.m770c();
        r0 = r1;
    L_0x008b:
        if (r0 != 0) goto L_0x0097;
    L_0x008d:
        r0 = com.alipay.sdk.app.C0589k.FAILED;
        r0 = r0.mo8007a();
        r0 = com.alipay.sdk.app.C0589k.m796b(r0);
    L_0x0097:
        r1 = r0.mo8007a();
        r0 = r0.mo8008b();
        r2 = "";
        r0 = com.alipay.sdk.app.C0588j.m789a(r1, r0, r2);
        goto L_0x0072;
    L_0x00a6:
        r0 = move-exception;
        r1 = com.alipay.sdk.app.C0589k.NETWORK_ERROR;	 Catch:{ all -> 0x00cb }
        r1 = r1.mo8007a();	 Catch:{ all -> 0x00cb }
        r1 = com.alipay.sdk.app.C0589k.m796b(r1);	 Catch:{ all -> 0x00cb }
        r2 = "net";
        com.alipay.sdk.app.statistic.C0590a.m804a(r2, r0);	 Catch:{ all -> 0x00cb }
        r8.m770c();
        r0 = r1;
        goto L_0x008b;
    L_0x00bb:
        r0 = move-exception;
        com.alipay.sdk.util.C0646c.m1016a(r0);	 Catch:{ all -> 0x00cb }
        r2 = "biz";
        r3 = "H5PayDataAnalysisError";
        com.alipay.sdk.app.statistic.C0590a.m802a(r2, r3, r0);	 Catch:{ all -> 0x00cb }
        r8.m770c();
        r0 = r1;
        goto L_0x008b;
    L_0x00cb:
        r0 = move-exception;
        r8.m770c();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.PayTask.m768b(java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    private void m766a(JSONObject jSONObject) {
        try {
            String optString = jSONObject.optString("tid");
            String optString2 = jSONObject.optString("client_key");
            if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2)) {
                C0643b.m990a(C0640b.m974a().mo8088b()).mo8097a(optString, optString2);
            }
        } catch (Throwable th) {
            C0590a.m802a("biz", "ParserTidClientKeyEx", th);
        }
    }

    /* renamed from: a */
    private String m761a(C0638b c0638b, String str) {
        boolean z = false;
        String[] c = c0638b.mo8084c();
        Intent intent = new Intent(this.f472b, H5PayActivity.class);
        try {
            boolean b;
            String a;
            JSONObject d = C0657m.m1074d(new String(C0619a.m896a(c[2])));
            intent.putExtra(NativeProtocol.IMAGE_URL_KEY, c[0]);
            intent.putExtra(PushConstants.TITLE_KEY, c[1]);
            intent.putExtra("version", "v2");
            intent.putExtra("method", d.optString("method", "POST"));
            C0588j.m791a(false);
            C0588j.m790a(null);
            this.f472b.startActivity(intent);
            synchronized (f469a) {
                try {
                    f469a.wait();
                    b = C0588j.m792b();
                    a = C0588j.m788a();
                    C0588j.m791a(false);
                    C0588j.m790a(null);
                } catch (InterruptedException e) {
                    C0646c.m1016a(e);
                    return C0588j.m793c();
                }
            }
            CharSequence charSequence = "";
            if (b) {
                try {
                    String a2;
                    List a3 = C0638b.m960a(C0657m.m1074d(new String(C0619a.m896a(a))));
                    while (true) {
                        boolean z2 = z;
                        if (z2 >= a3.size()) {
                            CharSequence a22 = charSequence;
                            break;
                        }
                        C0638b c0638b2 = (C0638b) a3.get(z2);
                        if (c0638b2.mo8083b() == C0637a.SetResult) {
                            String[] c2 = c0638b2.mo8084c();
                            a22 = C0588j.m789a(Integer.valueOf(c2[1]).intValue(), c2[0], C0657m.m1077e(c2[2]));
                            break;
                        }
                        z = z2 + 1;
                    }
                    charSequence = a22;
                } catch (Throwable th) {
                    C0646c.m1016a(th);
                    C0590a.m803a("biz", "H5PayDataAnalysisError", th, a);
                }
            }
            if (!TextUtils.isEmpty(charSequence)) {
                return charSequence;
            }
            try {
                return C0588j.m789a(Integer.valueOf(str).intValue(), "", "");
            } catch (Throwable th2) {
                C0590a.m803a("biz", "H5PayDataAnalysisError", th2, "endCode: " + str);
                return C0588j.m789a(8000, "", "");
            }
        } catch (Throwable th22) {
            C0646c.m1016a(th22);
            C0590a.m803a("biz", "H5PayDataAnalysisError", th22, Arrays.toString(c));
            return C0588j.m793c();
        }
    }

    /* renamed from: a */
    private String m760a(C0638b c0638b) {
        String[] c = c0638b.mo8084c();
        Intent intent = new Intent(this.f472b, H5PayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(NativeProtocol.IMAGE_URL_KEY, c[0]);
        if (c.length == 2) {
            bundle.putString("cookie", c[1]);
        }
        intent.putExtras(bundle);
        this.f472b.startActivity(intent);
        synchronized (f469a) {
            try {
                f469a.wait();
            } catch (InterruptedException e) {
                C0646c.m1016a(e);
                return C0588j.m793c();
            }
        }
        String a = C0588j.m788a();
        if (TextUtils.isEmpty(a)) {
            return C0588j.m793c();
        }
        return a;
    }

    /* renamed from: d */
    private static boolean m771d() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - f471j < PayloadController.PAYLOAD_COLLECTOR_TIMEOUT) {
            return true;
        }
        f471j = elapsedRealtime;
        return false;
    }
}
