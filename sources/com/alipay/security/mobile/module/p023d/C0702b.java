package com.alipay.security.mobile.module.p023d;

import com.alipay.security.mobile.module.http.p024v2.C0714a;
import com.alipay.security.mobile.module.p019a.C0690b;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import org.json.JSONObject;

/* renamed from: com.alipay.security.mobile.module.d.b */
public final class C0702b {
    /* renamed from: a */
    private File f735a = null;
    /* renamed from: b */
    private C0714a f736b = null;

    public C0702b(String str, C0714a c0714a) {
        this.f735a = new File(str);
        this.f736b = c0714a;
    }

    /* renamed from: a */
    private static String m1256a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "id");
            jSONObject.put("error", str);
            return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: b */
    private synchronized void m1258b() {
        int i = 0;
        synchronized (this) {
            if (this.f735a != null) {
                if (this.f735a.exists() && this.f735a.isDirectory() && this.f735a.list().length != 0) {
                    int i2;
                    String str;
                    ArrayList arrayList = new ArrayList();
                    for (Object add : this.f735a.list()) {
                        arrayList.add(add);
                    }
                    Collections.sort(arrayList);
                    String str2 = (String) arrayList.get(arrayList.size() - 1);
                    int size = arrayList.size();
                    int i3;
                    if (!str2.equals(new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + ".log")) {
                        i3 = size;
                        str = str2;
                        i2 = i3;
                    } else if (arrayList.size() >= 2) {
                        i3 = size - 1;
                        str = (String) arrayList.get(arrayList.size() - 2);
                        i2 = i3;
                    }
                    size = !this.f736b.mo8212a(C0702b.m1256a(C0690b.m1178a(this.f735a.getAbsolutePath(), str))) ? i2 - 1 : i2;
                    while (i < size) {
                        new File(this.f735a, (String) arrayList.get(i)).delete();
                        i++;
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo8177a() {
        new Thread(new C0703c(this)).start();
    }
}
