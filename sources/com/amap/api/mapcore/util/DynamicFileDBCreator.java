package com.amap.api.mapcore.util;

import android.database.sqlite.SQLiteDatabase;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;

/* renamed from: com.amap.api.mapcore.util.fc */
public class DynamicFileDBCreator implements DBCreator {
    /* renamed from: a */
    private static DynamicFileDBCreator f1923a;

    /* renamed from: a */
    public static synchronized DynamicFileDBCreator m2706a() {
        DynamicFileDBCreator dynamicFileDBCreator;
        synchronized (DynamicFileDBCreator.class) {
            if (f1923a == null) {
                f1923a = new DynamicFileDBCreator();
            }
            dynamicFileDBCreator = f1923a;
        }
        return dynamicFileDBCreator;
    }

    private DynamicFileDBCreator() {
    }

    /* renamed from: b */
    public String mo8973b() {
        return "dynamicamapfile.db";
    }

    /* renamed from: c */
    public int mo8974c() {
        return 1;
    }

    /* renamed from: a */
    public void mo8971a(SQLiteDatabase sQLiteDatabase) {
        try {
            String str = "CREATE TABLE IF NOT EXISTS file (_id integer primary key autoincrement, sdkname  varchar(20), filename varchar(100),md5 varchar(20),version varchar(20),dynamicversion varchar(20),status varchar(20),reservedfield varchar(20));";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "DynamicFileDBCreator", "onCreate");
        }
    }

    /* renamed from: a */
    public void mo8972a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
