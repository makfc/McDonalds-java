package com.amap.api.mapcore.util;

import android.database.sqlite.SQLiteDatabase;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;

/* renamed from: com.amap.api.mapcore.util.bw */
public class OfflineDBCreator implements DBCreator {
    /* renamed from: a */
    private static volatile OfflineDBCreator f1448a;

    /* renamed from: a */
    public static OfflineDBCreator m1970a() {
        if (f1448a == null) {
            synchronized (OfflineDBCreator.class) {
                if (f1448a == null) {
                    f1448a = new OfflineDBCreator();
                }
            }
        }
        return f1448a;
    }

    private OfflineDBCreator() {
    }

    /* renamed from: a */
    public void mo8971a(SQLiteDatabase sQLiteDatabase) {
        try {
            String str = "CREATE TABLE IF NOT EXISTS update_item (_id integer primary key autoincrement, title  TEXT, url TEXT,mAdcode TEXT,fileName TEXT,version TEXT,lLocalLength INTEGER,lRemoteLength INTEGER,localPath TEXT,mIndex INTEGER,isProvince INTEGER NOT NULL,mCompleteCode INTEGER,mCityCode TEXT,mState INTEGER, UNIQUE(mAdcode));";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = "CREATE TABLE IF NOT EXISTS update_item_file (_id integer primary key autoincrement,mAdcode TTEXT, file TEXT);";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            String str2 = "CREATE TABLE IF NOT EXISTS update_item_download_info (_id integer primary key autoincrement,mAdcode TEXT,fileLength integer,splitter integer,startPos integer,endPos integer, UNIQUE(mAdcode));";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str2);
            } else {
                sQLiteDatabase.execSQL(str2);
            }
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "DB", "onCreate");
            th.printStackTrace();
        }
    }

    /* renamed from: b */
    public String mo8973b() {
        return "offlineDbV4.db";
    }

    /* renamed from: c */
    public int mo8974c() {
        return 1;
    }

    /* renamed from: a */
    public void mo8972a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
