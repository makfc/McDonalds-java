package com.tencent.wxop.stat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;

/* renamed from: com.tencent.wxop.stat.bc */
class C4420bc extends SQLiteOpenHelper {
    /* renamed from: a */
    private String f7079a = "";
    /* renamed from: b */
    private Context f7080b = null;

    public C4420bc(Context context, String str) {
        super(context, str, null, 3);
        this.f7079a = str;
        this.f7080b = context.getApplicationContext();
        if (StatConfig.isDebugEnable()) {
            C4411au.f7042h.mo33952i("SQLiteOpenHelper " + this.f7079a);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0071  */
    /* renamed from: a */
    private void m8067a(android.database.sqlite.SQLiteDatabase r11) {
        /*
        r10 = this;
        r9 = 0;
        r2 = "user";
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r1 = r11 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Throwable -> 0x0077, all -> 0x006d }
        if (r1 != 0) goto L_0x0050;
    L_0x000d:
        r1 = r11;
        r2 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Throwable -> 0x0077, all -> 0x006d }
    L_0x0012:
        r1 = new android.content.ContentValues;	 Catch:{ Throwable -> 0x005f }
        r1.<init>();	 Catch:{ Throwable -> 0x005f }
        r3 = r2.moveToNext();	 Catch:{ Throwable -> 0x005f }
        if (r3 == 0) goto L_0x0037;
    L_0x001d:
        r3 = 0;
        r9 = r2.getString(r3);	 Catch:{ Throwable -> 0x005f }
        r3 = 1;
        r2.getInt(r3);	 Catch:{ Throwable -> 0x005f }
        r3 = 2;
        r2.getString(r3);	 Catch:{ Throwable -> 0x005f }
        r3 = 3;
        r2.getLong(r3);	 Catch:{ Throwable -> 0x005f }
        r3 = com.tencent.wxop.stat.common.C4439q.m8162b(r9);	 Catch:{ Throwable -> 0x005f }
        r4 = "uid";
        r1.put(r4, r3);	 Catch:{ Throwable -> 0x005f }
    L_0x0037:
        if (r9 == 0) goto L_0x004a;
    L_0x0039:
        r3 = "user";
        r4 = "uid=?";
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ Throwable -> 0x005f }
        r6 = 0;
        r5[r6] = r9;	 Catch:{ Throwable -> 0x005f }
        r6 = r11 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Throwable -> 0x005f }
        if (r6 != 0) goto L_0x0059;
    L_0x0047:
        r11.update(r3, r1, r4, r5);	 Catch:{ Throwable -> 0x005f }
    L_0x004a:
        if (r2 == 0) goto L_0x004f;
    L_0x004c:
        r2.close();
    L_0x004f:
        return;
    L_0x0050:
        r0 = r11;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Throwable -> 0x0077, all -> 0x006d }
        r1 = r0;
        r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Throwable -> 0x0077, all -> 0x006d }
        goto L_0x0012;
    L_0x0059:
        r11 = (android.database.sqlite.SQLiteDatabase) r11;	 Catch:{ Throwable -> 0x005f }
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r11, r3, r1, r4, r5);	 Catch:{ Throwable -> 0x005f }
        goto L_0x004a;
    L_0x005f:
        r1 = move-exception;
    L_0x0060:
        r3 = com.tencent.wxop.stat.C4411au.f7042h;	 Catch:{ all -> 0x0075 }
        r3.mo33949e(r1);	 Catch:{ all -> 0x0075 }
        if (r2 == 0) goto L_0x004f;
    L_0x0069:
        r2.close();
        goto L_0x004f;
    L_0x006d:
        r1 = move-exception;
        r2 = r9;
    L_0x006f:
        if (r2 == 0) goto L_0x0074;
    L_0x0071:
        r2.close();
    L_0x0074:
        throw r1;
    L_0x0075:
        r1 = move-exception;
        goto L_0x006f;
    L_0x0077:
        r1 = move-exception;
        r2 = r9;
        goto L_0x0060;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.C4420bc.m8067a(android.database.sqlite.SQLiteDatabase):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x008d  */
    /* renamed from: b */
    private void m8068b(android.database.sqlite.SQLiteDatabase r13) {
        /*
        r12 = this;
        r10 = 0;
        r3 = "events";
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r2 = r13 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Throwable -> 0x00a5, all -> 0x009f }
        if (r2 != 0) goto L_0x0049;
    L_0x000d:
        r2 = r13;
        r9 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Throwable -> 0x00a5, all -> 0x009f }
    L_0x0012:
        r2 = new java.util.ArrayList;	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
        r2.<init>();	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
    L_0x0017:
        r3 = r9.moveToNext();	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
        if (r3 == 0) goto L_0x0052;
    L_0x001d:
        r3 = 0;
        r4 = r9.getLong(r3);	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
        r3 = 1;
        r6 = r9.getString(r3);	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
        r3 = 2;
        r7 = r9.getInt(r3);	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
        r3 = 3;
        r8 = r9.getInt(r3);	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
        r3 = new com.tencent.wxop.stat.bd;	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
        r3.<init>(r4, r6, r7, r8);	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
        r2.add(r3);	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
        goto L_0x0017;
    L_0x003a:
        r2 = move-exception;
        r3 = r9;
    L_0x003c:
        r4 = com.tencent.wxop.stat.C4411au.f7042h;	 Catch:{ all -> 0x00a2 }
        r4.mo33949e(r2);	 Catch:{ all -> 0x00a2 }
        if (r3 == 0) goto L_0x0048;
    L_0x0045:
        r3.close();
    L_0x0048:
        return;
    L_0x0049:
        r0 = r13;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Throwable -> 0x00a5, all -> 0x009f }
        r2 = r0;
        r9 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r2, r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Throwable -> 0x00a5, all -> 0x009f }
        goto L_0x0012;
    L_0x0052:
        r3 = new android.content.ContentValues;	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
        r3.<init>();	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
        r4 = r2.iterator();	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
    L_0x005b:
        r2 = r4.hasNext();	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
        if (r2 == 0) goto L_0x0099;
    L_0x0061:
        r2 = r4.next();	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
        r2 = (com.tencent.wxop.stat.C4421bd) r2;	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
        r5 = "content";
        r6 = r2.f7082b;	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
        r6 = com.tencent.wxop.stat.common.C4439q.m8162b(r6);	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
        r3.put(r5, r6);	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
        r5 = "events";
        r6 = "event_id=?";
        r7 = 1;
        r7 = new java.lang.String[r7];	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
        r8 = 0;
        r10 = r2.f7081a;	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
        r2 = java.lang.Long.toString(r10);	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
        r7[r8] = r2;	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
        r2 = r13 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
        if (r2 != 0) goto L_0x0091;
    L_0x0086:
        r13.update(r5, r3, r6, r7);	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
        goto L_0x005b;
    L_0x008a:
        r2 = move-exception;
    L_0x008b:
        if (r9 == 0) goto L_0x0090;
    L_0x008d:
        r9.close();
    L_0x0090:
        throw r2;
    L_0x0091:
        r0 = r13;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
        r2 = r0;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r2, r5, r3, r6, r7);	 Catch:{ Throwable -> 0x003a, all -> 0x008a }
        goto L_0x005b;
    L_0x0099:
        if (r9 == 0) goto L_0x0048;
    L_0x009b:
        r9.close();
        goto L_0x0048;
    L_0x009f:
        r2 = move-exception;
        r9 = r10;
        goto L_0x008b;
    L_0x00a2:
        r2 = move-exception;
        r9 = r3;
        goto L_0x008b;
    L_0x00a5:
        r2 = move-exception;
        r3 = r10;
        goto L_0x003c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.C4420bc.m8068b(android.database.sqlite.SQLiteDatabase):void");
    }

    public synchronized void close() {
        super.close();
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        String str = "create table if not exists events(event_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, content TEXT, status INTEGER, send_count INTEGER, timestamp LONG)";
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
        } else {
            sQLiteDatabase.execSQL(str);
        }
        str = "create table if not exists user(uid TEXT PRIMARY KEY, user_type INTEGER, app_ver TEXT, ts INTEGER)";
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
        } else {
            sQLiteDatabase.execSQL(str);
        }
        str = "create table if not exists config(type INTEGER PRIMARY KEY NOT NULL, content TEXT, md5sum TEXT, version INTEGER)";
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
        } else {
            sQLiteDatabase.execSQL(str);
        }
        str = "create table if not exists keyvalues(key TEXT PRIMARY KEY NOT NULL, value TEXT)";
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
        } else {
            sQLiteDatabase.execSQL(str);
        }
        String str2 = "CREATE INDEX if not exists status_idx ON events(status)";
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            SQLiteInstrumentation.execSQL(sQLiteDatabase, str2);
        } else {
            sQLiteDatabase.execSQL(str2);
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        C4411au.f7042h.debug("upgrade DB from oldVersion " + i + " to newVersion " + i2);
        if (i == 1) {
            String str = "create table if not exists keyvalues(key TEXT PRIMARY KEY NOT NULL, value TEXT)";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            m8067a(sQLiteDatabase);
            m8068b(sQLiteDatabase);
        }
        if (i == 2) {
            m8067a(sQLiteDatabase);
            m8068b(sQLiteDatabase);
        }
    }
}
