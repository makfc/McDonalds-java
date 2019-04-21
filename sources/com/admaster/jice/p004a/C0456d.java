package com.admaster.jice.p004a;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.p000v4.util.Pair;
import android.support.p000v4.view.InputDeviceCompat;
import android.text.TextUtils;
import android.util.Log;
import com.admaster.jice.p007d.HttpURLRequest;
import com.admaster.jice.p007d.LOG;
import com.admaster.jice.p007d.ManagerUtils;
import com.admaster.jice.p007d.PrintUtil;
import com.facebook.stetho.common.Utf8Charset;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.payload.PayloadController;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONObject;

/* compiled from: JiceCore */
/* renamed from: com.admaster.jice.a.d */
class C0456d extends Handler {
    /* renamed from: a */
    final /* synthetic */ JiceCore f31a;
    /* renamed from: b */
    private Context f32b;
    /* renamed from: c */
    private final Lock f33c;
    /* renamed from: d */
    private Timer f34d = null;
    /* renamed from: e */
    private Timer f35e = null;
    /* renamed from: f */
    private C0460h f36f = null;
    /* renamed from: g */
    private C0460h f37g = null;
    /* renamed from: h */
    private final HttpURLRequest f38h;
    /* renamed from: i */
    private List<Pair<String, String>> f39i = null;
    /* renamed from: j */
    private List<Pair<String, String>> f40j = null;
    /* renamed from: k */
    private int f41k = 0;
    /* renamed from: l */
    private int f42l = 0;

    public C0456d(JiceCore jiceCore, Context context, Looper looper) {
        this.f31a = jiceCore;
        super(looper);
        this.f32b = context;
        this.f38h = new HttpURLRequest(context);
        if (this.f34d == null) {
            this.f34d = new Timer();
        }
        if (this.f35e == null) {
            this.f35e = new Timer();
        }
        this.f39i = new ArrayList();
        this.f40j = new ArrayList();
        jiceCore.f29g = new JicePushManager(jiceCore.f24b, context, jiceCore.f26d, this.f38h);
        jiceCore.f29g.mo7612a();
        this.f33c = new ReentrantLock();
        this.f33c.lock();
        m52b();
    }

    /* renamed from: b */
    private void m52b() {
        try {
            synchronized (this.f31a) {
                new Thread(new C0457e(this)).start();
            }
        } catch (Exception e) {
            LOG.m225a("JiceSDK", "init sys event failed:", e);
            PrintUtil.m246a(this.f32b, "EXCEPTION", "init sys event failed", e.toString(), "");
        }
    }

    /* renamed from: a */
    private void m50a(boolean z) {
        try {
            this.f34d.schedule(new C0458f(this), 1000, 15000);
            this.f35e.schedule(new C0459g(this), PayloadController.PAYLOAD_COLLECTOR_TIMEOUT, 15000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: a */
    private void m48a(com.admaster.jice.p004a.C0460h r7, com.admaster.jice.p004a.C0455b r8) {
        /*
        r6 = this;
        r1 = com.admaster.jice.p004a.C0456d.class;
        monitor-enter(r1);
        if (r7 == 0) goto L_0x003b;
    L_0x0005:
        r0 = r7.getState();	 Catch:{ all -> 0x004e }
        r2 = java.lang.Thread.State.NEW;	 Catch:{ all -> 0x004e }
        if (r0 == r2) goto L_0x001b;
    L_0x000d:
        r0 = r7.isAlive();	 Catch:{ all -> 0x004e }
        if (r0 != 0) goto L_0x001b;
    L_0x0013:
        r0 = r7.getState();	 Catch:{ all -> 0x004e }
        r2 = java.lang.Thread.State.TERMINATED;	 Catch:{ all -> 0x004e }
        if (r0 == r2) goto L_0x003b;
    L_0x001b:
        r0 = r6.f31a;	 Catch:{ all -> 0x004e }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x004e }
        r3 = "************************thread working return:";
        r2.<init>(r3);	 Catch:{ all -> 0x004e }
        r3 = r7.getState();	 Catch:{ all -> 0x004e }
        r2 = r2.append(r3);	 Catch:{ all -> 0x004e }
        r3 = "************************";
        r2 = r2.append(r3);	 Catch:{ all -> 0x004e }
        r2 = r2.toString();	 Catch:{ all -> 0x004e }
        r0.m28a(r2);	 Catch:{ all -> 0x004e }
        monitor-exit(r1);	 Catch:{ all -> 0x004e }
    L_0x003a:
        return;
    L_0x003b:
        r0 = r6.f38h;	 Catch:{ all -> 0x004e }
        r2 = r6.f32b;	 Catch:{ all -> 0x004e }
        r0 = r0.mo7713a(r2);	 Catch:{ all -> 0x004e }
        if (r0 != 0) goto L_0x0051;
    L_0x0045:
        r0 = r6.f31a;	 Catch:{ all -> 0x004e }
        r2 = "************************no network return************************";
        r0.m28a(r2);	 Catch:{ all -> 0x004e }
        monitor-exit(r1);	 Catch:{ all -> 0x004e }
        goto L_0x003a;
    L_0x004e:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x004e }
        throw r0;
    L_0x0051:
        r0 = com.admaster.jice.p005b.HttpConfig.m161b();	 Catch:{ all -> 0x004e }
        if (r0 == 0) goto L_0x0068;
    L_0x0057:
        r0 = r6.f32b;	 Catch:{ all -> 0x004e }
        r0 = com.admaster.jice.p007d.ManagerUtils.m239d(r0);	 Catch:{ all -> 0x004e }
        if (r0 != 0) goto L_0x0068;
    L_0x005f:
        r0 = r6.f31a;	 Catch:{ all -> 0x004e }
        r2 = "************************send condition is WIFI only return************************";
        r0.m28a(r2);	 Catch:{ all -> 0x004e }
        monitor-exit(r1);	 Catch:{ all -> 0x004e }
        goto L_0x003a;
    L_0x0068:
        r0 = com.admaster.jice.p004a.C0455b.NORMAL;	 Catch:{ all -> 0x004e }
        if (r8 != r0) goto L_0x009f;
    L_0x006c:
        r0 = r6.f39i;	 Catch:{ all -> 0x004e }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x004e }
        if (r0 == 0) goto L_0x007d;
    L_0x0074:
        r0 = r6.f31a;	 Catch:{ all -> 0x004e }
        r2 = "************************ normalstore empty return ************************";
        r0.m28a(r2);	 Catch:{ all -> 0x004e }
        monitor-exit(r1);	 Catch:{ all -> 0x004e }
        goto L_0x003a;
    L_0x007d:
        r0 = r6.f41k;	 Catch:{ all -> 0x004e }
        if (r0 <= 0) goto L_0x00d3;
    L_0x0081:
        r0 = r6.f31a;	 Catch:{ all -> 0x004e }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x004e }
        r3 = "************************ normal has unfinished task return:";
        r2.<init>(r3);	 Catch:{ all -> 0x004e }
        r3 = r6.f41k;	 Catch:{ all -> 0x004e }
        r2 = r2.append(r3);	 Catch:{ all -> 0x004e }
        r3 = "************************";
        r2 = r2.append(r3);	 Catch:{ all -> 0x004e }
        r2 = r2.toString();	 Catch:{ all -> 0x004e }
        r0.m28a(r2);	 Catch:{ all -> 0x004e }
        monitor-exit(r1);	 Catch:{ all -> 0x004e }
        goto L_0x003a;
    L_0x009f:
        r0 = r6.f40j;	 Catch:{ all -> 0x004e }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x004e }
        if (r0 == 0) goto L_0x00b0;
    L_0x00a7:
        r0 = r6.f31a;	 Catch:{ all -> 0x004e }
        r2 = "************************ failedstore empty return ************************";
        r0.m28a(r2);	 Catch:{ all -> 0x004e }
        monitor-exit(r1);	 Catch:{ all -> 0x004e }
        goto L_0x003a;
    L_0x00b0:
        r0 = r6.f42l;	 Catch:{ all -> 0x004e }
        if (r0 <= 0) goto L_0x00d3;
    L_0x00b4:
        r0 = r6.f31a;	 Catch:{ all -> 0x004e }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x004e }
        r3 = "************************ failed has unfinished task return:";
        r2.<init>(r3);	 Catch:{ all -> 0x004e }
        r3 = r6.f42l;	 Catch:{ all -> 0x004e }
        r2 = r2.append(r3);	 Catch:{ all -> 0x004e }
        r3 = " ************************";
        r2 = r2.append(r3);	 Catch:{ all -> 0x004e }
        r2 = r2.toString();	 Catch:{ all -> 0x004e }
        r0.m28a(r2);	 Catch:{ all -> 0x004e }
        monitor-exit(r1);	 Catch:{ all -> 0x004e }
        goto L_0x003a;
    L_0x00d3:
        r0 = com.admaster.jice.p004a.C0455b.NORMAL;	 Catch:{ Exception -> 0x0121 }
        if (r8 != r0) goto L_0x011a;
    L_0x00d7:
        r0 = r6.f39i;	 Catch:{ Exception -> 0x0121 }
        r0 = r6.m43a(r0);	 Catch:{ Exception -> 0x0121 }
    L_0x00dd:
        r2 = r6.f31a;	 Catch:{ Exception -> 0x0121 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0121 }
        r4 = "************************  execute task on:";
        r3.<init>(r4);	 Catch:{ Exception -> 0x0121 }
        r3 = r3.append(r8);	 Catch:{ Exception -> 0x0121 }
        r4 = " size:";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0121 }
        r4 = r0.size();	 Catch:{ Exception -> 0x0121 }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0121 }
        r4 = "  eventTask:";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0121 }
        r3 = r3.append(r7);	 Catch:{ Exception -> 0x0121 }
        r4 = " ************************";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0121 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0121 }
        r2.m28a(r3);	 Catch:{ Exception -> 0x0121 }
        r2 = new com.admaster.jice.a.h;	 Catch:{ Exception -> 0x0121 }
        r2.<init>(r6, r8, r0);	 Catch:{ Exception -> 0x0121 }
        r2.start();	 Catch:{ Exception -> 0x0121 }
    L_0x0117:
        monitor-exit(r1);	 Catch:{ all -> 0x004e }
        goto L_0x003a;
    L_0x011a:
        r0 = r6.f40j;	 Catch:{ Exception -> 0x0121 }
        r0 = r6.m43a(r0);	 Catch:{ Exception -> 0x0121 }
        goto L_0x00dd;
    L_0x0121:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x004e }
        r2 = r6.f32b;	 Catch:{ all -> 0x004e }
        r3 = "EXCEPTION";
        r4 = "handlerTaskQueue exception";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x004e }
        r5.<init>();	 Catch:{ all -> 0x004e }
        r0 = r0.toString();	 Catch:{ all -> 0x004e }
        r0 = r5.append(r0);	 Catch:{ all -> 0x004e }
        r0 = r0.toString();	 Catch:{ all -> 0x004e }
        r5 = "";
        com.admaster.jice.p007d.PrintUtil.m246a(r2, r3, r4, r0, r5);	 Catch:{ all -> 0x004e }
        goto L_0x0117;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.admaster.jice.p004a.C0456d.m48a(com.admaster.jice.a.h, com.admaster.jice.a.b):void");
    }

    /* renamed from: a */
    private List<Pair<String, String>> m43a(List<Pair<String, String>> list) {
        if (list == null || list.size() <= 30) {
            return new ArrayList(list);
        }
        return new ArrayList(list.subList(0, 30));
    }

    /* renamed from: a */
    public void mo7604a() {
        this.f33c.unlock();
    }

    public void handleMessage(Message message) {
        this.f33c.lock();
        try {
            switch (message.what) {
                case 256:
                    m55c();
                    break;
                case InputDeviceCompat.SOURCE_KEYBOARD /*257*/:
                    m45a((Bundle) message.obj);
                    break;
                case 258:
                    m49a((JSONObject) message.obj);
                    break;
                case 259:
                    m44a((Uri) message.obj);
                    break;
            }
            this.f33c.unlock();
        } catch (Exception e) {
            LOG.m225a("JiceSDK", "JiceHandler Message error", e);
            PrintUtil.m246a(this.f32b, "EXCEPTION", "JiceHandler Message error", e.toString(), "");
            this.f33c.unlock();
        } catch (Throwable th) {
            this.f33c.unlock();
            throw th;
        }
    }

    /* renamed from: a */
    private void m49a(JSONObject jSONObject) {
        try {
            String b = this.f31a.f27e.mo7637b();
            if (TextUtils.isEmpty(b)) {
                StoreManager e = this.f31a.f27e;
                if (jSONObject instanceof JSONObject) {
                    b = JSONObjectInstrumentation.toString(jSONObject);
                } else {
                    b = jSONObject.toString();
                }
                e.mo7638b(b);
                return;
            }
            String string = jSONObject.getString("id");
            JSONObject init = JSONObjectInstrumentation.init(b);
            if (init.getString("id").equals(string)) {
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    b = (String) keys.next();
                    init.put(b, jSONObject.get(b));
                }
                this.f31a.f27e.mo7638b(!(init instanceof JSONObject) ? init.toString() : JSONObjectInstrumentation.toString(init));
                return;
            }
            this.f31a.f27e.mo7639c();
            this.f31a.f27e.mo7638b(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m45a(Bundle bundle) {
        try {
            String string = bundle.getString("eventname", "");
            String string2 = bundle.getString("eventlabel", "defaultlabel");
            String str = null;
            if (TextUtils.isEmpty(string) || string2.equals("defaultlabel")) {
                string2 = "";
                str = "handlerCustomEvent bundle get eventname is empty or eventlabel is defaultlabel";
            }
            str = m42a(string, string2, str);
            string2 = m39a(System.currentTimeMillis());
            this.f31a.f28f.mo7598a(C0455b.NORMAL, str, string2);
            this.f39i.add(new Pair(str, string2));
            m48a(this.f36f, C0455b.NORMAL);
        } catch (Exception e) {
            LOG.m227b("JiceSDK", "build custom event failed:", e);
            PrintUtil.m246a(this.f32b, "EXCEPTION", "build custom event failed", e.toString(), "");
        }
    }

    /* renamed from: c */
    private void m55c() {
        try {
            String str;
            String str2;
            Map a = this.f31a.f28f.mo7596a(C0455b.NORMAL);
            if (a != null) {
                for (Entry entry : a.entrySet()) {
                    str = (String) entry.getKey();
                    str2 = (String) entry.getValue();
                    if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                        Log.e("JiceError", "initLocalEvent->normalsets expire:" + str2 + "   event:" + str);
                    }
                    this.f39i.add(new Pair(str, str2));
                }
            }
            a = this.f31a.f28f.mo7596a(C0455b.FAILED);
            if (a != null) {
                for (Entry entry2 : a.entrySet()) {
                    str = (String) entry2.getKey();
                    str2 = (String) entry2.getValue();
                    if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                        Log.e("JiceError", "initLocalEvent->failedsets expire:" + str2 + "   event:" + str);
                    }
                    this.f40j.add(new Pair(str, str2));
                }
            }
        } catch (Exception e) {
            LOG.m225a("JiceSDK", "load cache event to list failed:", e);
            PrintUtil.m246a(this.f32b, "EXCEPTION", "load cache event to list failed", e.toString(), "");
        }
        this.f31a.m28a("initLocalEvent finished   normalist:" + this.f39i.size() + "  failedlist:" + this.f40j.size());
        m50a(true);
    }

    /* renamed from: a */
    private void m44a(Uri uri) {
        try {
            String decode = URLDecoder.decode(uri.toString(), Utf8Charset.NAME);
            if (!TextUtils.isEmpty(decode)) {
                uri = Uri.parse(decode);
            }
            decode = uri.getQueryParameter("campaignId");
            this.f31a.m28a("handlerTestPushConfig:" + uri.toString() + "   campaignId:" + decode);
            if (TextUtils.isEmpty(decode)) {
                LOG.m224a("JiceSDK", "the campaignid is empty! please check Android scheme url on Jice Web System.");
            } else {
                new JiceTestPushManager(this.f32b, this.f38h, decode, this.f31a.f26d).mo7631a();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private String m39a(long j) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            Calendar instance = Calendar.getInstance();
            instance.set(instance.get(1), instance.get(2), instance.get(5), 23, 59, 59);
            long timeInMillis = instance.getTimeInMillis();
            stringBuffer.append(String.valueOf(j));
            stringBuffer.append("#");
            stringBuffer.append(String.valueOf(timeInMillis));
            stringBuffer.append("#");
            stringBuffer.append("0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    private String m42a(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            Object jSONObject2;
            JSONObject c = ManagerUtils.m238c(this.f32b, this.f31a.f26d);
            if (TextUtils.isEmpty(str)) {
                PrintUtil.m246a(this.f32b, "EXCEPTION", "buildEventData receive eventname is empty", "eventname:" + str, "eventlabel:" + str2);
            }
            jSONObject.put("time", System.currentTimeMillis());
            jSONObject.put("system", c);
            jSONObject.put("eventname", str);
            if (TextUtils.isEmpty(str2)) {
                jSONObject.put("eventlabel", new JSONObject());
            } else {
                jSONObject.put("eventlabel", JSONObjectInstrumentation.init(str2));
            }
            String b = this.f31a.f27e.mo7637b();
            if (TextUtils.isEmpty(b)) {
                jSONObject2 = new JSONObject();
            } else {
                jSONObject2 = JSONObjectInstrumentation.init(b);
            }
            jSONObject.put("profile", jSONObject2);
            if (!TextUtils.isEmpty(str3)) {
                jSONObject.put("errormsg", str3);
            }
        } catch (Exception e) {
            e.printStackTrace();
            PrintUtil.m246a(this.f32b, "EXCEPTION", "buildEventData failed", e.toString(), "");
        }
        if (jSONObject instanceof JSONObject) {
            return JSONObjectInstrumentation.toString(jSONObject);
        }
        return jSONObject.toString();
    }
}
