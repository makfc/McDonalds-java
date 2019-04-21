package com.amap.api.services.core;

import android.content.ContentValues;
import android.database.Cursor;
import com.amap.api.services.core.C1081ac.C1080a;

/* renamed from: com.amap.api.services.core.an */
public class SDKEntity implements SQlEntity<C1081ac> {
    /* renamed from: a */
    private static String f3663a = C1083ag.f3639f;
    /* renamed from: b */
    private static String f3664b = C1083ag.f3640g;
    /* renamed from: c */
    private static String f3665c = C1083ag.f3644k;
    /* renamed from: d */
    private static String f3666d = C1083ag.f3641h;
    /* renamed from: e */
    private static String f3667e = C1083ag.f3642i;
    /* renamed from: f */
    private static String f3668f = C1083ag.f3643j;
    /* renamed from: g */
    private C1081ac f3669g = null;

    /* renamed from: b */
    public ContentValues mo11996b() {
        Throwable th;
        ContentValues contentValues = null;
        try {
            if (this.f3669g == null) {
                return null;
            }
            ContentValues contentValues2 = new ContentValues();
            try {
                contentValues2.put(f3663a, C1090as.m4783a(this.f3669g.mo11988a()));
                contentValues2.put(f3664b, C1090as.m4783a(this.f3669g.mo11989b()));
                contentValues2.put(f3665c, Boolean.valueOf(this.f3669g.mo11992e()));
                contentValues2.put(f3666d, C1090as.m4783a(this.f3669g.mo11990c()));
                contentValues2.put(f3668f, C1090as.m4783a(this.f3669g.mo11991d()));
                contentValues2.put(f3667e, C1090as.m4783a(m4759a(this.f3669g.mo11993f())));
                return contentValues2;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                contentValues = contentValues2;
                th = th3;
                th.printStackTrace();
                return contentValues;
            }
        } catch (Throwable th4) {
            th = th4;
            th.printStackTrace();
            return contentValues;
        }
    }

    /* renamed from: a */
    public C1081ac mo11997b(Cursor cursor) {
        boolean z = true;
        try {
            String b = C1090as.m4784b(cursor.getString(1));
            String b2 = C1090as.m4784b(cursor.getString(2));
            String b3 = C1090as.m4784b(cursor.getString(3));
            String[] b4 = m4760b(C1090as.m4784b(cursor.getString(4)));
            String b5 = C1090as.m4784b(cursor.getString(5));
            if (cursor.getInt(6) == 0) {
                z = false;
            }
            return new C1080a(b, b2, b3).mo11985a(z).mo11984a(b5).mo11986a(b4).mo11987a();
        } catch (C1133u e) {
            e.printStackTrace();
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public void mo11995a(C1081ac c1081ac) {
        this.f3669g = c1081ac;
    }

    /* renamed from: a */
    public String mo11994a() {
        return C1083ag.f3634a;
    }

    /* renamed from: b */
    private String[] m4760b(String str) {
        try {
            return str.split(";");
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private String m4759a(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        try {
            StringBuilder stringBuilder = new StringBuilder();
            for (String append : strArr) {
                stringBuilder.append(append).append(";");
            }
            return stringBuilder.toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static String m4758a(String str) {
        return f3663a + "='" + C1090as.m4783a(str) + "'";
    }

    /* renamed from: c */
    public static String m4761c() {
        return f3665c + "=1";
    }
}
