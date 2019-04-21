package com.baidu.android.pushservice.p037i;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;
import com.baidu.android.pushservice.C1328a;
import com.baidu.android.pushservice.PushService;
import com.baidu.android.pushservice.PushSettings;
import com.baidu.android.pushservice.p026j.C1281c;
import com.baidu.android.pushservice.p026j.C1462d;
import com.baidu.android.pushservice.p034f.C1403b;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p037i.p038a.C1429a;
import com.baidu.android.pushservice.p037i.p038a.C1432b;
import com.baidu.android.pushservice.p039k.C1465b;
import com.baidu.android.pushservice.util.C1548l;
import com.baidu.android.pushservice.util.C1568q;
import com.facebook.internal.NativeProtocol;
import com.facebook.stetho.common.Utf8Charset;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.i.s */
public final class C1453s {
    /* renamed from: a */
    public static String f5124a = "";
    /* renamed from: b */
    private Context f5125b = null;
    /* renamed from: c */
    private C1454t f5126c = null;
    /* renamed from: d */
    private boolean f5127d;

    public C1453s(Context context) {
        this.f5125b = context.getApplicationContext();
        this.f5126c = C1454t.m6605a(context);
        this.f5127d = false;
    }

    /* renamed from: a */
    private boolean m6595a(String str, String str2, String str3) {
        Closeable closeable;
        Throwable th;
        Throwable e;
        if (!C1548l.m6948a(this.f5125b)) {
            return false;
        }
        InputStream content;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("stats", str2));
        arrayList.add(new BasicNameValuePair("pbVer", str3));
        arrayList.add(new BasicNameValuePair("os", "android"));
        closeable = null;
        long j = 1000;
        int i = 0;
        while (i < 3) {
            try {
                C1425a.m6441b("StatisticPoster", "Statistics request time=" + i + ", url=" + str);
                DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(str);
                httpPost.setEntity(new UrlEncodedFormEntity(arrayList, Utf8Charset.NAME));
                HttpResponse execute = !(defaultHttpClient instanceof HttpClient) ? defaultHttpClient.execute(httpPost) : HttpInstrumentation.execute(defaultHttpClient, httpPost);
                int statusCode = execute.getStatusLine().getStatusCode();
                content = execute.getEntity().getContent();
                try {
                    String a = C1432b.m6481a(content);
                    if (statusCode != 200) {
                        if (statusCode == 201) {
                            mo13923a(a);
                        } else if (statusCode == 403) {
                            mo13925b(a);
                        }
                        if (statusCode == 200 || statusCode == 201 || statusCode == 403) {
                            break;
                        }
                        j += (long) (i * 300);
                        Thread.sleep(j);
                        i++;
                        Object closeable2 = content;
                    } else {
                        C1403b.m6265a(content);
                        return true;
                    }
                } catch (Exception e2) {
                    th = e2;
                    closeable2 = content;
                    e = th;
                } catch (Throwable e22) {
                    th = e22;
                    closeable2 = content;
                    e = th;
                    C1403b.m6265a(closeable2);
                    throw e;
                }
            } catch (Exception e3) {
                e = e3;
            }
        }
        Closeable content2 = closeable2;
        C1403b.m6265a(content2);
        return false;
        try {
            C1425a.m6440a("StatisticPoster", e);
            C1403b.m6265a(closeable2);
            return false;
        } catch (Throwable th2) {
            e = th2;
            C1403b.m6265a(closeable2);
            throw e;
        }
    }

    /* renamed from: d */
    private boolean m6597d() {
        if (!C1432b.m6488d(this.f5125b) || this.f5127d || PushSettings.m5887f(this.f5125b)) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long d = PushSettings.m5885d(this.f5125b);
        C1425a.m6442c("StatisticPoster", "checkSendStatisticData currentTime:" + currentTimeMillis + " lastSendTime " + d);
        return (C1548l.m6949b(this.f5125b) || currentTimeMillis - d >= PushSettings.m5886e(this.f5125b)) ? C1568q.m7004b(this.f5125b, currentTimeMillis, d) : false;
    }

    /* renamed from: a */
    public String mo13921a() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("user_device", C1432b.m6490e(this.f5125b));
            jSONObject.put("user_network", C1432b.m6491f(this.f5125b));
            jSONObject2.put("channel_id", PushSettings.m5874a(this.f5125b));
            jSONObject2.put("push_running_version", C1328a.m6003a());
            jSONObject.put("push_channel", jSONObject2);
        } catch (JSONException e) {
            C1425a.m6440a("StatisticPoster", e);
        }
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
    }

    /* renamed from: a */
    public String mo13922a(long j, long j2, int i, int i2) {
        byte[] a;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", "1.0");
            String a2 = mo13921a();
            if (!TextUtils.isEmpty(a2)) {
                jSONObject.put("common", JSONObjectInstrumentation.init(a2));
            }
            a2 = this.f5126c.mo13928a(j, j2, i, i2);
            if (!TextUtils.isEmpty(a2)) {
                jSONObject.put("application_info", JSONArrayInstrumentation.init(a2));
            }
        } catch (JSONException e) {
        }
        try {
            a = C1429a.m6476a(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject));
            a[0] = (byte) 117;
            a[1] = (byte) 123;
        } catch (IOException e2) {
            a = null;
        }
        if (a == null) {
            return null;
        }
        String a3;
        try {
            a3 = C1465b.m6678a(a, "utf-8");
        } catch (UnsupportedEncodingException e3) {
            C1425a.m6444e("StatisticPoster", "error " + e3.getMessage());
            a3 = null;
        }
        return a3;
    }

    /* renamed from: a */
    public void mo13923a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject init = JSONObjectInstrumentation.init(str);
                int i = init.getInt("config_type");
                int i2 = init.getInt("interval");
                if (i == 0) {
                    if (i2 > 0) {
                        PushSettings.m5880b(this.f5125b, (long) (i2 * 1000));
                    }
                } else if (i == 1) {
                    this.f5127d = true;
                } else if (i == 2) {
                    if (i2 > 0) {
                        PushSettings.m5875a(this.f5125b, 1);
                        Intent intent = new Intent("com.baidu.android.pushservice.action.METHOD");
                        intent.putExtra("method", "com.baidu.android.pushservice.action.ENBALE_APPSTAT");
                        intent.setClass(this.f5125b, PushService.class);
                        PendingIntent service = PendingIntent.getService(this.f5125b.getApplicationContext(), 0, intent, 268435456);
                        long elapsedRealtime = SystemClock.elapsedRealtime() + ((long) i2);
                        AlarmManager alarmManager = (AlarmManager) this.f5125b.getSystemService("alarm");
                        alarmManager.cancel(service);
                        alarmManager.set(1, elapsedRealtime, service);
                    }
                } else if (i == 10) {
                    PushSettings.m5891j(this.f5125b);
                } else if (i == 11) {
                    PushSettings.m5892k(this.f5125b);
                }
            } catch (JSONException e) {
                C1425a.m6442c("StatisticPoster", "parse 201 exception" + e);
            }
        }
    }

    /* renamed from: b */
    public void mo13924b() {
        C1462d.m6637a().mo13938a(new C1281c("checkSendStatisticData", (short) 90) {
            /* renamed from: a */
            public void mo13487a() {
                if (C1453s.this.m6597d()) {
                    long currentTimeMillis = System.currentTimeMillis();
                    int i = (int) ((currentTimeMillis / 60000) % 5);
                    int i2 = ((int) (currentTimeMillis / 1000)) % 60;
                    if (i == 0 && i2 < 15) {
                        try {
                            Thread.sleep((long) ((Math.random() * 60.0d) * 1000.0d));
                        } catch (InterruptedException e) {
                            C1425a.m6444e("StatisticPoster", "InterruptedException: " + e);
                        }
                        if (!C1432b.m6488d(C1453s.this.f5125b)) {
                            return;
                        }
                    }
                    C1453s.this.mo13927c();
                }
            }
        });
    }

    /* renamed from: b */
    public void mo13925b(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject init = JSONObjectInstrumentation.init(str);
                int i = init.getInt(NativeProtocol.BRIDGE_ARG_ERROR_CODE);
                init.getString("error_msg");
                if (i == 50009) {
                    PushSettings.m5875a(this.f5125b, 1);
                }
            } catch (JSONException e) {
            }
        }
    }

    /* renamed from: b */
    public boolean mo13926b(long j, long j2, int i, int i2) {
        String a = mo13922a(j, j2, i, i2);
        String str = "https://statsonline.pushct.baidu.com/pushlog_special";
        try {
            if (!TextUtils.isEmpty(a)) {
                return m6595a(str, a, "1.0");
            }
        } catch (OutOfMemoryError e) {
            C1425a.m6444e("StatisticPoster", "OutOfMemoryError when posting");
        }
        return false;
    }

    /* renamed from: c */
    public synchronized void mo13927c() {
        long currentTimeMillis = System.currentTimeMillis();
        long d = PushSettings.m5885d(this.f5125b);
        int i = 0;
        int a = C1568q.m6980a(this.f5125b, currentTimeMillis, d);
        while (a >= 1000) {
            if (!mo13926b(currentTimeMillis, d, i, 1000)) {
                break;
            }
            i += 1000;
            a -= 1000;
        }
        boolean z = true;
        if (a <= 1000 && a > 0) {
            z = mo13926b(currentTimeMillis, d, i, a);
        }
        if (z) {
            PushSettings.m5876a(this.f5125b, System.currentTimeMillis());
            try {
                C1568q.m7007d(this.f5125b);
            } catch (Exception e) {
                C1425a.m6444e("StatisticPoster", "clearBehaviorInfo exception" + e);
            }
        }
        return;
    }
}
