package com.amap.api.services.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;

/* compiled from: DB */
/* renamed from: com.amap.api.services.core.ag */
public class C1083ag extends SQLiteOpenHelper {
    /* renamed from: a */
    static final String f3634a = "a";
    /* renamed from: b */
    static final String f3635b = "b";
    /* renamed from: c */
    static final String f3636c = "c";
    /* renamed from: d */
    static final String f3637d = "d";
    /* renamed from: e */
    static final String f3638e = "e";
    /* renamed from: f */
    static final String f3639f = "a1";
    /* renamed from: g */
    static final String f3640g = "a2";
    /* renamed from: h */
    static final String f3641h = "a3";
    /* renamed from: i */
    static final String f3642i = "a4";
    /* renamed from: j */
    static final String f3643j = "a5";
    /* renamed from: k */
    static final String f3644k = "a6";
    /* renamed from: l */
    static final String f3645l = "b1";
    /* renamed from: m */
    static final String f3646m = "b2";
    /* renamed from: n */
    static final String f3647n = "b3";
    /* renamed from: o */
    static final String f3648o = "c1";
    /* renamed from: p */
    static final String f3649p = "c2";
    /* renamed from: q */
    static final String f3650q = "c3";
    /* renamed from: r */
    private static final String f3651r = ("CREATE TABLE IF NOT EXISTS " + f3634a + " (_id integer primary key autoincrement, " + f3639f + "  varchar(20), " + f3640g + " varchar(10)," + f3641h + " varchar(50)," + f3642i + " varchar(100)," + f3643j + " varchar(20)," + f3644k + " integer);");
    /* renamed from: s */
    private static final String f3652s = ("CREATE TABLE IF NOT EXISTS %s (_id integer primary key autoincrement," + f3645l + " varchar(40), " + f3646m + " integer," + f3647n + "  integer," + f3639f + "  varchar(20));");
    /* renamed from: t */
    private static final String f3653t = ("CREATE TABLE IF NOT EXISTS " + f3638e + " (_id integer primary key autoincrement," + f3648o + " integer," + f3649p + " integer," + f3650q + " integer);");

    public C1083ag(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            String str = f3651r;
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = String.format(f3652s, new Object[]{f3635b});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = String.format(f3652s, new Object[]{f3636c});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = String.format(f3652s, new Object[]{f3637d});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            String str2 = f3653t;
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str2);
            } else {
                sQLiteDatabase.execSQL(str2);
            }
        } catch (Throwable th) {
            C1099ax.m4800a(th, "DB", "onCreate");
            th.printStackTrace();
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
