package com.baidu.android.pushservice.p033e;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.baidu.android.pushservice.p031c.C1332b;
import com.baidu.android.pushservice.p031c.C1339h;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p039k.C1472f;
import com.baidu.android.pushservice.util.C1577u;
import com.facebook.stetho.common.Utf8Charset;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/* renamed from: com.baidu.android.pushservice.e.b */
public final class C1370b {
    /* renamed from: a */
    public static String m6202a(HashMap<String, String> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return "params is null or empty";
        }
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = hashMap.entrySet().iterator();
        int i = 0;
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return stringBuffer.toString();
            }
            if (i2 != 0) {
                stringBuffer.append(", ");
            }
            Entry entry = (Entry) it.next();
            Object key = entry.getKey();
            stringBuffer.append(key).append("=").append(entry.getValue());
            i = i2 + 1;
        }
    }

    /* renamed from: a */
    public static void m6203a(Context context) {
        C1370b.m6204a(context, (ArrayList) C1332b.m6020a(context).f4721a.clone());
    }

    /* renamed from: a */
    private static void m6204a(Context context, ArrayList<C1339h> arrayList) {
        if (arrayList != null) {
            PackageManager packageManager = context.getPackageManager();
            synchronized (arrayList) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    C1339h c1339h = (C1339h) it.next();
                    if (c1339h != null) {
                        String c = c1339h.mo13589c();
                        PackageInfo packageInfo = null;
                        try {
                            packageInfo = packageManager.getPackageInfo(c, 0);
                        } catch (NameNotFoundException e) {
                            if (!TextUtils.isEmpty(c)) {
                                C1425a.m6443d("ApiUtils", c + "not found at packageManager");
                            }
                        }
                        if (packageInfo == null) {
                            Intent intent = new Intent("com.baidu.android.pushservice.action.METHOD");
                            intent.putExtra("method", "com.baidu.android.pushservice.action.UNBINDAPP");
                            intent.putExtra("package_name", c1339h.mo13589c());
                            C1577u.m7049b(context, intent);
                        } else {
                            continue;
                        }
                    }
                }
            }
        }
    }

    @SuppressLint({"InlinedApi"})
    /* renamed from: a */
    public static void m6205a(List<NameValuePair> list) {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        list.add(new BasicNameValuePair(AnalyticAttribute.EVENT_TIMESTAMP_ATTRIBUTE, currentTimeMillis + ""));
        list.add(new BasicNameValuePair("expires", (86400 + currentTimeMillis) + ""));
        list.add(new BasicNameValuePair("v", "1"));
        try {
            list.add(new BasicNameValuePair("vcode", C1472f.m6716a(URLEncoder.encode(currentTimeMillis + "bccs", Utf8Charset.NAME).getBytes(), false)));
        } catch (UnsupportedEncodingException e) {
            C1425a.m6440a("ApiUtils", e);
        }
    }

    @SuppressLint({"InlinedApi"})
    /* renamed from: b */
    public static void m6206b(HashMap<String, String> hashMap) {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        hashMap.put(AnalyticAttribute.EVENT_TIMESTAMP_ATTRIBUTE, currentTimeMillis + "");
        hashMap.put("expires", (86400 + currentTimeMillis) + "");
        hashMap.put("v", "1");
        try {
            hashMap.put("vcode", C1472f.m6716a(URLEncoder.encode(currentTimeMillis + "bccs", Utf8Charset.NAME).getBytes(), false));
        } catch (UnsupportedEncodingException e) {
            C1425a.m6440a("ApiUtils", e);
        }
    }
}
