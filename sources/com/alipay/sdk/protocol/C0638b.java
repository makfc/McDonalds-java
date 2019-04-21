package com.alipay.sdk.protocol;

import android.text.TextUtils;
import com.alipay.sdk.sys.C0640b;
import com.alipay.sdk.tid.C0643b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.alipay.sdk.protocol.b */
public class C0638b {
    /* renamed from: a */
    private C0637a f613a;
    /* renamed from: b */
    private String f614b;
    /* renamed from: c */
    private String[] f615c;

    public C0638b(String str, C0637a c0637a) {
        this.f614b = str;
        this.f613a = c0637a;
    }

    /* renamed from: a */
    public static void m961a(C0638b c0638b) {
        String[] c = c0638b.mo8084c();
        if (c.length == 3 && TextUtils.equals("tid", c[0])) {
            C0643b a = C0643b.m990a(C0640b.m974a().mo8088b());
            if (!TextUtils.isEmpty(c[1]) && !TextUtils.isEmpty(c[2])) {
                a.mo8097a(c[1], c[2]);
            }
        }
    }

    /* renamed from: a */
    public static List<C0638b> m960a(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        String[] b = C0638b.m963b(jSONObject.optString("name", ""));
        for (int i = 0; i < b.length; i++) {
            C0637a a = C0637a.m959a(b[i]);
            if (a != C0637a.None) {
                C0638b c0638b = new C0638b(b[i], a);
                c0638b.f615c = C0638b.m962a(b[i]);
                arrayList.add(c0638b);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private static String[] m962a(String str) {
        ArrayList arrayList = new ArrayList();
        int indexOf = str.indexOf(40);
        int lastIndexOf = str.lastIndexOf(41);
        if (indexOf == -1 || lastIndexOf == -1 || lastIndexOf <= indexOf) {
            return null;
        }
        String[] split = str.substring(indexOf + 1, lastIndexOf).split(",");
        if (split != null) {
            for (indexOf = 0; indexOf < split.length; indexOf++) {
                if (!TextUtils.isEmpty(split[indexOf])) {
                    arrayList.add(split[indexOf].trim().replaceAll("'", "").replaceAll("\"", ""));
                }
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    /* renamed from: b */
    private static String[] m963b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.split(";");
    }

    /* renamed from: b */
    public C0637a mo8083b() {
        return this.f613a;
    }

    /* renamed from: c */
    public String[] mo8084c() {
        return this.f615c;
    }
}
