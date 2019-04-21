package com.baidu.android.pushservice.p037i;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.android.pushservice.C1475k;
import com.baidu.android.pushservice.PushSettings;
import com.baidu.android.pushservice.p026j.C1281c;
import com.baidu.android.pushservice.p026j.C1462d;
import com.baidu.android.pushservice.p033e.C1370b;
import com.baidu.android.pushservice.p034f.C1403b;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p037i.p038a.C1432b;
import com.baidu.android.pushservice.util.C1548l;
import com.facebook.internal.NativeProtocol;
import com.facebook.stetho.common.Utf8Charset;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.Closeable;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.i.q */
public abstract class C1443q {
    /* renamed from: a */
    protected Context f5096a;
    /* renamed from: b */
    protected String f5097b;
    /* renamed from: c */
    private boolean f5098c = false;
    /* renamed from: d */
    private boolean f5099d;

    public C1443q(Context context) {
        this.f5096a = context.getApplicationContext();
        this.f5099d = false;
    }

    /* renamed from: c */
    private void m6553c(boolean z) {
        Object e;
        Exception exception;
        Throwable th;
        Throwable th2;
        Object obj;
        if (TextUtils.isEmpty(this.f5097b)) {
            C1425a.m6444e("Statistics-BaseSender", "mUrl is null");
            return;
        }
        InputStream inputStream = null;
        Closeable obj2;
        try {
            String a = mo13893a(z);
            if (!TextUtils.isEmpty(a)) {
                if (!mo13899b()) {
                    this.f5097b += C1475k.m6721a(this.f5096a).mo13946a();
                }
                List arrayList = new ArrayList();
                C1370b.m6205a(arrayList);
                mo13895a(a, arrayList);
                int i = 0;
                long j = 1000;
                while (true) {
                    int i2 = i;
                    obj2 = inputStream;
                    if (i2 >= 3) {
                        Closeable inputStream2 = obj2;
                        break;
                    }
                    int statusCode;
                    try {
                        C1425a.m6442c("Statistics-BaseSender", "a request send, url=" + this.f5097b);
                        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
                        HttpPost httpPost = new HttpPost(this.f5097b);
                        httpPost.setEntity(new UrlEncodedFormEntity(arrayList, Utf8Charset.NAME));
                        HttpResponse execute = !(defaultHttpClient instanceof HttpClient) ? defaultHttpClient.execute(httpPost) : HttpInstrumentation.execute(defaultHttpClient, httpPost);
                        statusCode = execute.getStatusLine().getStatusCode();
                        inputStream2 = execute.getEntity().getContent();
                    } catch (Exception e2) {
                        e = e2;
                    }
                    try {
                        a = C1432b.m6481a(inputStream2);
                        C1425a.m6442c("Statistics-BaseSender", "return response, code= " + statusCode + ", result=" + a);
                        if (statusCode == 200) {
                            mo13897b(a);
                        } else if (statusCode == 201) {
                            mo13900c(a);
                        } else if (statusCode == 403) {
                            mo13902d(a);
                        }
                        if (statusCode == 200 || statusCode == 201 || statusCode == 403) {
                            break;
                        }
                        j += (long) (i2 * 300);
                        Thread.sleep(j);
                        i = i2 + 1;
                    } catch (Exception e3) {
                        exception = e3;
                        obj2 = inputStream2;
                        e = exception;
                    } catch (Throwable th3) {
                        th = th3;
                        obj2 = inputStream2;
                        th2 = th;
                        C1403b.m6265a(obj2);
                        throw th2;
                    }
                }
            }
            C1403b.m6265a(inputStream2);
        } catch (Exception e32) {
            exception = e32;
            obj2 = null;
            Exception e4 = exception;
            try {
                C1425a.m6444e("Statistics-BaseSender", "startSendLoop Exception: " + e4);
                C1403b.m6265a(obj2);
            } catch (Throwable th4) {
                th2 = th4;
                C1403b.m6265a(obj2);
                throw th2;
            }
        } catch (Throwable th32) {
            th = th32;
            obj2 = null;
            th2 = th;
            C1403b.m6265a(obj2);
            throw th2;
        }
    }

    /* renamed from: a */
    public abstract String mo13893a(boolean z);

    /* renamed from: a */
    public abstract void mo13894a(String str);

    /* renamed from: a */
    public abstract void mo13895a(String str, List<NameValuePair> list);

    /* renamed from: a */
    public abstract boolean mo13896a();

    /* renamed from: b */
    public void mo13897b(String str) {
        mo13894a(str);
    }

    /* renamed from: b */
    public synchronized void mo13898b(final boolean z) {
        if (!this.f5098c) {
            if (!mo13896a()) {
                C1425a.m6443d("Statistics-BaseSender", "No new data producted, do nothing!");
            } else if (C1475k.m6721a(this.f5096a).mo13950c()) {
                this.f5098c = true;
                C1462d.m6637a().mo13938a(new C1281c("PushService-stats-sender", (short) 90) {
                    /* renamed from: a */
                    public void mo13487a() {
                        if (C1548l.m6952e(C1443q.this.f5096a)) {
                            C1443q.this.m6553c(z);
                            C1443q.this.f5098c = false;
                            return;
                        }
                        C1425a.m6443d("Statistics-BaseSender", "Network is not reachable!");
                    }
                });
            } else {
                C1425a.m6444e("Statistics-BaseSender", "Fail Send Statistics. Token invalid!");
            }
        }
    }

    /* renamed from: b */
    public abstract boolean mo13899b();

    /* renamed from: c */
    public void mo13900c(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject init = JSONObjectInstrumentation.init(str);
                int i = init.getInt("config_type");
                int i2 = init.getInt("interval");
                C1425a.m6442c("Statistics-BaseSender", "lbs config_type = " + i + " interval = " + i2);
                if (i != 0) {
                    if (i == 1 || i != 2) {
                    }
                } else if (i2 > 0) {
                    PushSettings.m5879b(this.f5096a, i2);
                }
            } catch (JSONException e) {
                C1425a.m6444e("Statistics-BaseSender", "parse 201 exception" + e);
            }
        }
    }

    /* renamed from: c */
    public boolean mo13901c() {
        return this.f5099d;
    }

    /* renamed from: d */
    public void mo13902d(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject init = JSONObjectInstrumentation.init(str);
                int i = init.getInt(NativeProtocol.BRIDGE_ARG_ERROR_CODE);
                C1425a.m6442c("Statistics-BaseSender", "error code = " + i + " error_msg = " + init.getString("error_msg"));
                if (i == 50009) {
                    PushSettings.m5891j(this.f5096a);
                }
            } catch (JSONException e) {
                C1425a.m6444e("Statistics-BaseSender", " parse 403 exception" + e);
            }
        }
    }
}
