package com.baidu.android.pushservice.p037i;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.baidu.android.pushservice.PushSettings;
import com.baidu.android.pushservice.p026j.C1281c;
import com.baidu.android.pushservice.p026j.C1462d;
import com.baidu.android.pushservice.p037i.p038a.C1432b;
import com.baidu.android.pushservice.util.C1544h;
import com.baidu.android.pushservice.util.C1545i;
import com.baidu.android.pushservice.util.C1568q;
import com.baidu.android.pushservice.util.C1578v;

/* renamed from: com.baidu.android.pushservice.i.u */
public class C1456u {
    /* renamed from: a */
    private final Context f5133a;
    /* renamed from: b */
    private C1445j f5134b;
    /* renamed from: c */
    private final C1453s f5135c;

    public C1456u(Context context) {
        this.f5133a = context;
        this.f5135c = new C1453s(context);
        this.f5134b = C1445j.m6565a(context);
    }

    /* renamed from: a */
    public static long m6608a(Context context, C1436b c1436b) {
        return C1568q.m6984a(context, c1436b);
    }

    /* renamed from: a */
    public static long m6609a(Context context, C1438d c1438d) {
        return C1568q.m6986a(context, c1438d);
    }

    /* renamed from: a */
    public static long m6610a(Context context, C1441h c1441h) {
        return C1568q.m6987a(context, c1441h);
    }

    /* renamed from: a */
    public static long m6611a(Context context, C1446k c1446k) {
        return C1568q.m6988a(context, c1446k);
    }

    /* renamed from: a */
    public static long m6612a(Context context, C1449n c1449n) {
        return C1568q.m6989a(context, c1449n);
    }

    /* renamed from: a */
    public static long m6613a(Context context, C1450o c1450o) {
        return C1568q.m6990a(context, c1450o);
    }

    /* renamed from: a */
    public static long m6614a(Context context, String str, int i, String str2) {
        C1446k c1446k = new C1446k();
        c1446k.f5037g = System.currentTimeMillis();
        c1446k.f5038h = C1432b.m6486c(context);
        c1446k.f5039i = i;
        c1446k.f5042l = str2;
        c1446k.f5036f = str;
        return C1456u.m6611a(context, c1446k);
    }

    /* renamed from: a */
    public static void m6615a(final Context context, final String str) {
        C1462d.m6637a().mo13938a(new C1281c("insertNetworkInfo", (short) 95) {
            /* renamed from: a */
            public void mo13487a() {
                SharedPreferences sharedPreferences = context.getSharedPreferences("pst", 4);
                if (System.currentTimeMillis() - sharedPreferences.getLong(str, 0) >= 1800000) {
                    String str = "";
                    C1456u.m6614a(context, str, 0, str.equals("039912") ? C1578v.m7151w(context) : C1578v.m7154x(context));
                    Editor edit = sharedPreferences.edit();
                    edit.putLong(str, System.currentTimeMillis());
                    edit.commit();
                }
            }
        });
    }

    /* renamed from: b */
    public static long m6616b(Context context, C1446k c1446k) {
        return C1568q.m7001b(context, c1446k);
    }

    /* renamed from: b */
    private boolean m6617b() {
        if (PushSettings.m5890i(this.f5133a) || this.f5134b.mo13901c()) {
            return false;
        }
        return System.currentTimeMillis() - C1545i.m6934c(this.f5133a) > (PushSettings.m5889h(this.f5133a) ? (long) PushSettings.m5888g(this.f5133a) : 43200000);
    }

    /* renamed from: a */
    public void mo13929a() {
        if (this.f5135c != null) {
            this.f5135c.mo13924b();
        }
    }

    /* renamed from: a */
    public void mo13930a(boolean z, C1544h c1544h) {
        if (this.f5134b == null) {
            this.f5134b = C1445j.m6565a(this.f5133a);
        }
        this.f5134b.mo13903a(c1544h);
        if (z || m6617b()) {
            this.f5134b.mo13898b(z);
        }
    }
}
