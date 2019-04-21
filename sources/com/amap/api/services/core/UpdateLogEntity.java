package com.amap.api.services.core;

import android.content.ContentValues;
import android.database.Cursor;

/* renamed from: com.amap.api.services.core.aq */
public class UpdateLogEntity implements SQlEntity<C1089ar> {
    /* renamed from: b */
    private static final String f3672b = C1083ag.f3648o;
    /* renamed from: c */
    private static final String f3673c = C1083ag.f3649p;
    /* renamed from: d */
    private static final String f3674d = C1083ag.f3650q;
    /* renamed from: a */
    private C1089ar f3675a = null;

    /* renamed from: b */
    public ContentValues mo11996b() {
        Throwable th;
        ContentValues contentValues = null;
        try {
            if (this.f3675a == null) {
                return null;
            }
            ContentValues contentValues2 = new ContentValues();
            try {
                contentValues2.put(f3672b, Boolean.valueOf(this.f3675a.mo12028a()));
                contentValues2.put(f3673c, Boolean.valueOf(this.f3675a.mo12030b()));
                contentValues2.put(f3674d, Boolean.valueOf(this.f3675a.mo12032c()));
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
    public C1089ar mo11997b(Cursor cursor) {
        C1089ar c1089ar;
        Throwable th;
        boolean z = true;
        try {
            boolean z2;
            boolean z3;
            int i = cursor.getInt(1);
            int i2 = cursor.getInt(2);
            int i3 = cursor.getInt(3);
            if (i == 0) {
                z2 = false;
            } else {
                z2 = true;
            }
            if (i2 == 0) {
                z3 = false;
            } else {
                z3 = true;
            }
            if (i3 == 0) {
                z = false;
            }
            c1089ar = new C1089ar();
            try {
                c1089ar.mo12027a(z2);
                c1089ar.mo12031c(z);
                c1089ar.mo12029b(z3);
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                return c1089ar;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            c1089ar = null;
            th = th4;
            th.printStackTrace();
            return c1089ar;
        }
        return c1089ar;
    }

    /* renamed from: a */
    public void mo11995a(C1089ar c1089ar) {
        this.f3675a = c1089ar;
    }

    /* renamed from: a */
    public String mo11994a() {
        return C1083ag.f3638e;
    }
}
