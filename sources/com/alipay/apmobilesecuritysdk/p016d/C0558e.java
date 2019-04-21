package com.alipay.apmobilesecuritysdk.p016d;

import android.content.Context;
import com.alipay.security.mobile.module.p019a.p020a.C0687b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/* renamed from: com.alipay.apmobilesecuritysdk.d.e */
public final class C0558e {
    /* renamed from: a */
    private static Map<String, String> f414a = null;
    /* renamed from: b */
    private static final String[] f415b = new String[]{"AD1", "AD2", "AD3", "AD8", "AD9", "AD10", "AD11", "AD12", "AD14", "AD15", "AD16", "AD18", "AD20", "AD21", "AD23", "AD24", "AD26", "AD27", "AD28", "AD29", "AD30", "AD31", "AD34", "AA1", "AA2", "AA3", "AA4", "AC4", "AC10", "AE1", "AE2", "AE3", "AE4", "AE5", "AE6", "AE7", "AE8", "AE9", "AE10", "AE11", "AE12", "AE13", "AE14", "AE15"};

    /* renamed from: a */
    private static String m653a(Map<String, String> map) {
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= arrayList.size()) {
                return stringBuffer.toString();
            }
            String str = (String) arrayList.get(i2);
            String str2 = (String) map.get(str);
            if (str2 == null) {
                str2 = "";
            }
            stringBuffer.append((i2 == 0 ? "" : "&") + str + "=" + str2);
            i = i2 + 1;
        }
    }

    /* renamed from: a */
    public static synchronized Map<String, String> m654a(Context context, Map<String, String> map) {
        Map map2;
        synchronized (C0558e.class) {
            if (f414a == null) {
                C0558e.m657c(context, map);
            }
            f414a.putAll(C0557d.m651a());
            map2 = f414a;
        }
        return map2;
    }

    /* renamed from: a */
    public static synchronized void m655a() {
        synchronized (C0558e.class) {
            f414a = null;
        }
    }

    /* renamed from: b */
    public static synchronized String m656b(Context context, Map<String, String> map) {
        TreeMap treeMap;
        synchronized (C0558e.class) {
            C0558e.m654a(context, map);
            treeMap = new TreeMap();
            for (Object obj : f415b) {
                if (f414a.containsKey(obj)) {
                    treeMap.put(obj, f414a.get(obj));
                }
            }
        }
        return C0687b.m1157a(C0558e.m653a(treeMap));
    }

    /* renamed from: c */
    private static synchronized void m657c(Context context, Map<String, String> map) {
        synchronized (C0558e.class) {
            TreeMap treeMap = new TreeMap();
            f414a = treeMap;
            treeMap.putAll(C0555b.m649a(context, map));
            f414a.putAll(C0557d.m652a(context));
            f414a.putAll(C0556c.m650a(context));
            f414a.putAll(C0554a.m648a(context, map));
        }
    }
}
