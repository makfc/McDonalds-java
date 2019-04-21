package com.amap.api.services.core;

import android.content.ContentValues;
import android.database.Cursor;

/* renamed from: com.amap.api.services.core.ak */
public abstract class LogEntity implements SQlEntity<C1086al> {
    /* renamed from: a */
    private static final String f3629a = C1083ag.f3645l;
    /* renamed from: b */
    private static final String f3630b = C1083ag.f3646m;
    /* renamed from: c */
    private static final String f3631c = C1083ag.f3647n;
    /* renamed from: d */
    private static final String f3632d = C1083ag.f3639f;
    /* renamed from: e */
    private C1086al f3633e = null;

    /* renamed from: b */
    public ContentValues mo11996b() {
        Throwable th;
        ContentValues contentValues = null;
        try {
            if (this.f3633e == null) {
                return null;
            }
            ContentValues contentValues2 = new ContentValues();
            try {
                contentValues2.put(f3629a, this.f3633e.mo12014b());
                contentValues2.put(f3630b, Integer.valueOf(this.f3633e.mo12011a()));
                contentValues2.put(f3632d, C1090as.m4783a(this.f3633e.mo12017c()));
                contentValues2.put(f3631c, Integer.valueOf(this.f3633e.mo12018d()));
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
    public C1086al mo11997b(Cursor cursor) {
        Throwable th;
        C1086al c1086al = null;
        if (cursor == null) {
            return null;
        }
        try {
            String string = cursor.getString(1);
            int i = cursor.getInt(2);
            String string2 = cursor.getString(4);
            int i2 = cursor.getInt(3);
            C1086al c1086al2 = new C1086al();
            try {
                c1086al2.mo12013a(string);
                c1086al2.mo12012a(i);
                c1086al2.mo12016b(C1090as.m4784b(string2));
                c1086al2.mo12015b(i2);
                return c1086al2;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                c1086al = c1086al2;
                th = th3;
                th.printStackTrace();
                return c1086al;
            }
        } catch (Throwable th4) {
            th = th4;
            th.printStackTrace();
            return c1086al;
        }
    }

    /* renamed from: a */
    public void mo11995a(C1086al c1086al) {
        this.f3633e = c1086al;
    }

    /* renamed from: a */
    public static String m4723a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(f3629a).append("='").append(str).append("'");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public static String m4722a(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(f3630b).append("=").append(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
