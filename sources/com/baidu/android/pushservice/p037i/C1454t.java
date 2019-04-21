package com.baidu.android.pushservice.p037i;

import android.content.Context;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.util.C1568q;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.i.t */
public final class C1454t {
    /* renamed from: a */
    private static final String f5128a = C1454t.class.getSimpleName();
    /* renamed from: b */
    private static volatile C1454t f5129b = null;
    /* renamed from: c */
    private Context f5130c = null;

    private C1454t(Context context) {
        this.f5130c = context.getApplicationContext();
        if (this.f5130c == null) {
            C1425a.m6444e(f5128a, "Error occurs with mContext");
        }
    }

    /* renamed from: a */
    public static C1454t m6605a(Context context) {
        if (f5129b == null) {
            f5129b = new C1454t(context);
        }
        C1425a.m6442c(f5128a, "Current packet name: " + context.getPackageName());
        return f5129b;
    }

    /* renamed from: a */
    public String mo13928a(long j, long j2, int i, int i2) {
        List list;
        String str = "";
        JSONArray jSONArray = new JSONArray();
        List<C1449n> a = C1568q.m6994a(this.f5130c);
        ArrayList<C1446k> arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        ArrayList<C1441h> arrayList2 = new ArrayList();
        List<C1433a> b = C1568q.m7003b(this.f5130c, j, j2, i, i2);
        if (b != null) {
            for (C1433a c1433a : b) {
                if (c1433a.mo13834a().startsWith(C1435r.f5048r)) {
                    if (!hashMap2.containsKey(c1433a.mo13838b())) {
                        hashMap2.put(c1433a.mo13838b(), new ArrayList());
                    }
                    list = (List) hashMap2.get(c1433a.mo13838b());
                    if (list != null) {
                        list.add(c1433a.mo13847e());
                    }
                } else if (c1433a.mo13834a().startsWith(C1435r.f5050t)) {
                    arrayList.add(c1433a.mo13844d());
                }
            }
        }
        List<C1440g> a2 = C1568q.m6995a(this.f5130c, j, j2, i, i2);
        if (a2 != null) {
            for (C1440g c1440g : a2) {
                if (c1440g.mo13870a().startsWith(C1435r.f5050t)) {
                    arrayList.add(c1440g.mo13880d());
                } else if (c1440g.mo13870a().startsWith(C1435r.f5048r)) {
                    if (!hashMap.containsKey(c1440g.mo13874b())) {
                        hashMap.put(c1440g.mo13874b(), new ArrayList());
                    }
                    list = (List) hashMap.get(c1440g.mo13874b());
                    if (list != null) {
                        list.add(c1440g.mo13883e());
                    }
                } else if (c1440g.mo13870a().startsWith(C1435r.f5049s)) {
                    if (!hashMap3.containsKey(c1440g.mo13874b())) {
                        hashMap3.put(c1440g.mo13874b(), new ArrayList());
                    }
                    list = (List) hashMap3.get(c1440g.mo13874b());
                    if (list != null) {
                        list.add(c1440g.mo13886f());
                    }
                } else if (c1440g.mo13870a().startsWith(C1435r.f5051u)) {
                    arrayList2.add(c1440g.mo13888g());
                }
            }
        }
        try {
            JSONObject jSONObject;
            JSONArray jSONArray2;
            if (arrayList.size() > 0) {
                jSONObject = new JSONObject();
                jSONObject.put("app_appid", "9527");
                jSONArray2 = new JSONArray();
                for (C1446k a3 : arrayList) {
                    jSONArray2.put(a3.mo13904a());
                }
                jSONObject.put("push_action", jSONArray2);
                jSONArray.put(jSONObject);
            }
            if (a != null) {
                for (C1449n c1449n : a) {
                    JSONObject a4 = c1449n.mo13917a(this.f5130c);
                    JSONArray jSONArray3 = new JSONArray();
                    List<C1450o> list2 = (List) hashMap.get(c1449n.mo13852a());
                    List<C1436b> list3 = (List) hashMap2.get(c1449n.mo13852a());
                    List<C1438d> list4 = (List) hashMap3.get(c1449n.mo13852a());
                    if (list2 != null) {
                        try {
                            if (list2.size() != 0) {
                                for (C1450o a5 : list2) {
                                    jSONArray3.put(a5.mo13920a());
                                }
                            }
                        } catch (JSONException e) {
                            C1425a.m6444e(f5128a, "error: JSONException");
                        }
                    }
                    if (list4 != null) {
                        if (list4.size() != 0) {
                            for (C1438d a6 : list4) {
                                jSONArray3.put(a6.mo13851a());
                            }
                        }
                    }
                    if (!(list3 == null || list3.size() == 0)) {
                        for (C1436b a7 : list3) {
                            jSONArray3.put(a7.mo13850a());
                        }
                    }
                    if (jSONArray3.length() > 0) {
                        a4.put("push_action", jSONArray3);
                    }
                    jSONArray.put(a4);
                }
            }
            if (arrayList2 != null) {
                if (arrayList2.size() > 0) {
                    jSONObject = new JSONObject();
                    jSONObject.put("app_appid", "9528");
                    jSONArray2 = new JSONArray();
                    for (C1441h a8 : arrayList2) {
                        jSONArray2.put(a8.mo13891a());
                    }
                    jSONObject.put("crash_info", jSONArray2);
                    jSONArray.put(jSONObject);
                }
            }
        } catch (JSONException e2) {
            C1425a.m6444e(f5128a, "error:" + e2.getMessage());
        }
        str = jSONArray.length() == 0 ? "" : !(jSONArray instanceof JSONArray) ? jSONArray.toString() : JSONArrayInstrumentation.toString(jSONArray);
        C1425a.m6442c(f5128a, "stat:" + str);
        return str;
    }
}
