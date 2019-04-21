package com.baidu.android.pushservice.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import com.baidu.android.pushservice.p036h.C1425a;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.io.File;

/* renamed from: com.baidu.android.pushservice.util.p */
public class C1554p {
    /* renamed from: a */
    private static C1553b f5411a = null;
    /* renamed from: b */
    private static Object f5412b = new Object();

    /* renamed from: com.baidu.android.pushservice.util.p$a */
    enum C1552a {
        PushADInfoId,
        PushADSwitch,
        PushADMaxCount,
        PushADServerMaxCount,
        PushADCurCount,
        PushADCurTimeStamp
    }

    /* renamed from: com.baidu.android.pushservice.util.p$b */
    private static class C1553b extends SQLiteOpenHelper {
        public C1553b(Context context, String str, CursorFactory cursorFactory, int i) {
            super(context, str, cursorFactory, i);
        }

        /* renamed from: a */
        private void m6970a(SQLiteDatabase sQLiteDatabase) {
            try {
                String str = "DROP TABLE IF EXISTS PushADInfo";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
            } catch (Exception e) {
                C1425a.m6442c("PushClientDataBase", "dropTables Exception: " + e);
            }
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            try {
                String str = "CREATE TABLE PushADInfo (" + C1552a.PushADInfoId.name() + " INTEGER PRIMARY KEY AUTOINCREMENT, " + C1552a.PushADSwitch.name() + " INTEGER DEFAULT ((0)), " + C1552a.PushADMaxCount.name() + " INTEGER, " + C1552a.PushADServerMaxCount.name() + " INTEGER, " + C1552a.PushADCurCount.name() + " INTEGER, " + C1552a.PushADCurTimeStamp.name() + " LONG  NOT NULL" + ");";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                C1425a.m6442c("PushClientDataBase", "SQL :CREATE TABLE PushADInfo (" + C1552a.PushADInfoId.name() + " INTEGER PRIMARY KEY AUTOINCREMENT, " + C1552a.PushADSwitch.name() + " INTEGER, " + C1552a.PushADMaxCount.name() + " INTEGER, " + C1552a.PushADServerMaxCount.name() + " INTEGER, " + C1552a.PushADCurCount.name() + " INTEGER, " + C1552a.PushADCurTimeStamp.name() + " LONG  NOT NULL" + ");");
            } catch (Exception e) {
                C1425a.m6440a("PushClientDataBase", e);
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            m6970a(sQLiteDatabase);
            onCreate(sQLiteDatabase);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:59:0x016f A:{SYNTHETIC, Splitter:B:59:0x016f} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00d6 A:{Catch:{ Exception -> 0x0134 }} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00e9 A:{SYNTHETIC, Splitter:B:30:0x00e9} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x017c A:{SYNTHETIC, Splitter:B:64:0x017c} */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0187  */
    /* renamed from: a */
    public static synchronized long m6971a(android.content.Context r23, com.baidu.android.pushservice.p037i.C1447l r24) {
        /*
        r19 = com.baidu.android.pushservice.util.C1554p.class;
        monitor-enter(r19);
        r2 = com.baidu.android.pushservice.util.C1554p.m6972a(r23);	 Catch:{ all -> 0x018b }
        if (r2 != 0) goto L_0x000d;
    L_0x0009:
        r2 = -1;
    L_0x000b:
        monitor-exit(r19);
        return r2;
    L_0x000d:
        r22 = new android.content.ContentValues;	 Catch:{ all -> 0x018b }
        r22.<init>();	 Catch:{ all -> 0x018b }
        r3 = com.baidu.android.pushservice.util.C1554p.C1552a.PushADSwitch;	 Catch:{ all -> 0x018b }
        r3 = r3.name();	 Catch:{ all -> 0x018b }
        r0 = r24;
        r4 = r0.f5106a;	 Catch:{ all -> 0x018b }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x018b }
        r0 = r22;
        r0.put(r3, r4);	 Catch:{ all -> 0x018b }
        r3 = com.baidu.android.pushservice.util.C1554p.C1552a.PushADMaxCount;	 Catch:{ all -> 0x018b }
        r3 = r3.name();	 Catch:{ all -> 0x018b }
        r0 = r24;
        r4 = r0.f5107b;	 Catch:{ all -> 0x018b }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x018b }
        r0 = r22;
        r0.put(r3, r4);	 Catch:{ all -> 0x018b }
        r3 = com.baidu.android.pushservice.util.C1554p.C1552a.PushADServerMaxCount;	 Catch:{ all -> 0x018b }
        r3 = r3.name();	 Catch:{ all -> 0x018b }
        r0 = r24;
        r4 = r0.f5108c;	 Catch:{ all -> 0x018b }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x018b }
        r0 = r22;
        r0.put(r3, r4);	 Catch:{ all -> 0x018b }
        r3 = com.baidu.android.pushservice.util.C1554p.C1552a.PushADCurCount;	 Catch:{ all -> 0x018b }
        r3 = r3.name();	 Catch:{ all -> 0x018b }
        r0 = r24;
        r4 = r0.f5109d;	 Catch:{ all -> 0x018b }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x018b }
        r0 = r22;
        r0.put(r3, r4);	 Catch:{ all -> 0x018b }
        r3 = com.baidu.android.pushservice.util.C1554p.C1552a.PushADCurTimeStamp;	 Catch:{ all -> 0x018b }
        r3 = r3.name();	 Catch:{ all -> 0x018b }
        r0 = r24;
        r4 = r0.f5110e;	 Catch:{ all -> 0x018b }
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ all -> 0x018b }
        r0 = r22;
        r0.put(r3, r4);	 Catch:{ all -> 0x018b }
        r20 = -1;
        r18 = 0;
        r3 = "PushADInfo";
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0190, all -> 0x0177 }
        if (r10 != 0) goto L_0x00fa;
    L_0x0081:
        r6 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x0190, all -> 0x0177 }
    L_0x0085:
        if (r6 == 0) goto L_0x0118;
    L_0x0087:
        r3 = r6.getCount();	 Catch:{ Exception -> 0x0196 }
        if (r3 == 0) goto L_0x0118;
    L_0x008d:
        r4 = "PushADInfo";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0196 }
        r3.<init>();	 Catch:{ Exception -> 0x0196 }
        r5 = com.baidu.android.pushservice.util.C1554p.C1552a.PushADInfoId;	 Catch:{ Exception -> 0x0196 }
        r5 = r5.name();	 Catch:{ Exception -> 0x0196 }
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x0196 }
        r5 = "=1";
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x0196 }
        r5 = r3.toString();	 Catch:{ Exception -> 0x0196 }
        r7 = 0;
        r3 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0196 }
        if (r3 != 0) goto L_0x010d;
    L_0x00ad:
        r0 = r22;
        r3 = r2.update(r4, r0, r5, r7);	 Catch:{ Exception -> 0x0196 }
    L_0x00b3:
        r4 = (long) r3;
        r3 = "PushClientDataBase";
        r7 = "pushadvertiseinfo:  update into database";
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r3, r7);	 Catch:{ Exception -> 0x0134 }
        r3 = "pushadvertiseinfo:  update into database";
        r0 = r23;
        com.baidu.android.pushservice.util.C1578v.m7095b(r3, r0);	 Catch:{ Exception -> 0x0134 }
    L_0x00c2:
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0134 }
        r3.<init>();	 Catch:{ Exception -> 0x0134 }
        r7 = "updateADStatus pushadvertiseinfo:  insert into database,  adclientinfo = ";
        r7 = r3.append(r7);	 Catch:{ Exception -> 0x0134 }
        r3 = r24.mo13905a();	 Catch:{ Exception -> 0x0134 }
        r8 = r3 instanceof org.json.JSONObject;	 Catch:{ Exception -> 0x0134 }
        if (r8 != 0) goto L_0x016f;
    L_0x00d6:
        r3 = r3.toString();	 Catch:{ Exception -> 0x0134 }
    L_0x00da:
        r3 = r7.append(r3);	 Catch:{ Exception -> 0x0134 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0134 }
        r0 = r23;
        com.baidu.android.pushservice.util.C1578v.m7095b(r3, r0);	 Catch:{ Exception -> 0x0134 }
        if (r6 == 0) goto L_0x00f2;
    L_0x00e9:
        r3 = r6.isClosed();	 Catch:{ all -> 0x018b }
        if (r3 != 0) goto L_0x00f2;
    L_0x00ef:
        r6.close();	 Catch:{ all -> 0x018b }
    L_0x00f2:
        if (r2 == 0) goto L_0x019a;
    L_0x00f4:
        r2.close();	 Catch:{ all -> 0x018b }
        r2 = r4;
        goto L_0x000b;
    L_0x00fa:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0190, all -> 0x0177 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r6 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x0190, all -> 0x0177 }
        goto L_0x0085;
    L_0x010d:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0196 }
        r3 = r0;
        r0 = r22;
        r3 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r3, r4, r0, r5, r7);	 Catch:{ Exception -> 0x0196 }
        goto L_0x00b3;
    L_0x0118:
        r4 = "PushADInfo";
        r5 = 0;
        r3 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0196 }
        if (r3 != 0) goto L_0x0164;
    L_0x011f:
        r0 = r22;
        r4 = r2.insert(r4, r5, r0);	 Catch:{ Exception -> 0x0196 }
    L_0x0125:
        r3 = "PushClientDataBase";
        r7 = "pushadvertiseinfo:  insert into database";
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r3, r7);	 Catch:{ Exception -> 0x0134 }
        r3 = "pushadvertiseinfo:  insert into database";
        r0 = r23;
        com.baidu.android.pushservice.util.C1578v.m7095b(r3, r0);	 Catch:{ Exception -> 0x0134 }
        goto L_0x00c2;
    L_0x0134:
        r3 = move-exception;
    L_0x0135:
        r7 = "PushClientDataBase";
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x018e }
        r8.<init>();	 Catch:{ all -> 0x018e }
        r9 = "error ";
        r8 = r8.append(r9);	 Catch:{ all -> 0x018e }
        r3 = r3.getMessage();	 Catch:{ all -> 0x018e }
        r3 = r8.append(r3);	 Catch:{ all -> 0x018e }
        r3 = r3.toString();	 Catch:{ all -> 0x018e }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r7, r3);	 Catch:{ all -> 0x018e }
        if (r6 == 0) goto L_0x015c;
    L_0x0153:
        r3 = r6.isClosed();	 Catch:{ all -> 0x018b }
        if (r3 != 0) goto L_0x015c;
    L_0x0159:
        r6.close();	 Catch:{ all -> 0x018b }
    L_0x015c:
        if (r2 == 0) goto L_0x019a;
    L_0x015e:
        r2.close();	 Catch:{ all -> 0x018b }
        r2 = r4;
        goto L_0x000b;
    L_0x0164:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0196 }
        r3 = r0;
        r0 = r22;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.insert(r3, r4, r5, r0);	 Catch:{ Exception -> 0x0196 }
        goto L_0x0125;
    L_0x016f:
        r3 = (org.json.JSONObject) r3;	 Catch:{ Exception -> 0x0134 }
        r3 = com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation.toString(r3);	 Catch:{ Exception -> 0x0134 }
        goto L_0x00da;
    L_0x0177:
        r3 = move-exception;
        r6 = r18;
    L_0x017a:
        if (r6 == 0) goto L_0x0185;
    L_0x017c:
        r4 = r6.isClosed();	 Catch:{ all -> 0x018b }
        if (r4 != 0) goto L_0x0185;
    L_0x0182:
        r6.close();	 Catch:{ all -> 0x018b }
    L_0x0185:
        if (r2 == 0) goto L_0x018a;
    L_0x0187:
        r2.close();	 Catch:{ all -> 0x018b }
    L_0x018a:
        throw r3;	 Catch:{ all -> 0x018b }
    L_0x018b:
        r2 = move-exception;
        monitor-exit(r19);
        throw r2;
    L_0x018e:
        r3 = move-exception;
        goto L_0x017a;
    L_0x0190:
        r3 = move-exception;
        r6 = r18;
        r4 = r20;
        goto L_0x0135;
    L_0x0196:
        r3 = move-exception;
        r4 = r20;
        goto L_0x0135;
    L_0x019a:
        r2 = r4;
        goto L_0x000b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1554p.m6971a(android.content.Context, com.baidu.android.pushservice.i.l):long");
    }

    /* renamed from: a */
    public static SQLiteDatabase m6972a(Context context) {
        SQLiteDatabase sQLiteDatabase = null;
        C1553b d = C1554p.m6977d(context);
        if (d == null) {
            return sQLiteDatabase;
        }
        try {
            return d.getWritableDatabase();
        } catch (Exception e) {
            C1425a.m6444e("PushClientDataBase", "getDb Exception: " + e);
            return sQLiteDatabase;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0082 A:{SYNTHETIC, Splitter:B:20:0x0082} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00eb A:{SYNTHETIC, Splitter:B:47:0x00eb} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00f6  */
    /* renamed from: a */
    public static synchronized void m6973a(android.content.Context r20, int r21) {
        /*
        r19 = com.baidu.android.pushservice.util.C1554p.class;
        monitor-enter(r19);
        r2 = com.baidu.android.pushservice.util.C1554p.m6972a(r20);	 Catch:{ all -> 0x0092 }
        r18 = 0;
        if (r2 != 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r19);
        return;
    L_0x000d:
        r3 = "PushADInfo";
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00fe, all -> 0x00fa }
        if (r10 != 0) goto L_0x0095;
    L_0x0019:
        r4 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00fe, all -> 0x00fa }
    L_0x001d:
        if (r4 == 0) goto L_0x00c8;
    L_0x001f:
        r3 = r4.getCount();	 Catch:{ Exception -> 0x00b0 }
        if (r3 == 0) goto L_0x00c8;
    L_0x0025:
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00b0 }
        r3.<init>();	 Catch:{ Exception -> 0x00b0 }
        r5 = "UPDATE PushADInfo SET ";
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x00b0 }
        r5 = com.baidu.android.pushservice.util.C1554p.C1552a.PushADCurCount;	 Catch:{ Exception -> 0x00b0 }
        r5 = r5.name();	 Catch:{ Exception -> 0x00b0 }
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x00b0 }
        r5 = " = ";
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x00b0 }
        r0 = r21;
        r3 = r3.append(r0);	 Catch:{ Exception -> 0x00b0 }
        r5 = " WHERE ";
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x00b0 }
        r5 = com.baidu.android.pushservice.util.C1554p.C1552a.PushADInfoId;	 Catch:{ Exception -> 0x00b0 }
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x00b0 }
        r5 = " = ";
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x00b0 }
        r5 = 1;
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x00b0 }
        r5 = r3.toString();	 Catch:{ Exception -> 0x00b0 }
        r3 = "PushClientDataBase";
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00b0 }
        r6.<init>();	 Catch:{ Exception -> 0x00b0 }
        r7 = "sql is :  ";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x00b0 }
        r6 = r6.append(r5);	 Catch:{ Exception -> 0x00b0 }
        r6 = r6.toString();	 Catch:{ Exception -> 0x00b0 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r3, r6);	 Catch:{ Exception -> 0x00b0 }
        r3 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00b0 }
        if (r3 != 0) goto L_0x00a8;
    L_0x007d:
        r2.execSQL(r5);	 Catch:{ Exception -> 0x00b0 }
    L_0x0080:
        if (r4 == 0) goto L_0x008b;
    L_0x0082:
        r3 = r4.isClosed();	 Catch:{ all -> 0x0092 }
        if (r3 != 0) goto L_0x008b;
    L_0x0088:
        r4.close();	 Catch:{ all -> 0x0092 }
    L_0x008b:
        if (r2 == 0) goto L_0x000b;
    L_0x008d:
        r2.close();	 Catch:{ all -> 0x0092 }
        goto L_0x000b;
    L_0x0092:
        r2 = move-exception;
        monitor-exit(r19);
        throw r2;
    L_0x0095:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00fe, all -> 0x00fa }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x00fe, all -> 0x00fa }
        goto L_0x001d;
    L_0x00a8:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00b0 }
        r3 = r0;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.execSQL(r3, r5);	 Catch:{ Exception -> 0x00b0 }
        goto L_0x0080;
    L_0x00b0:
        r3 = move-exception;
    L_0x00b1:
        r5 = "PushClientDataBase";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r5, r3);	 Catch:{ all -> 0x00e8 }
        if (r4 == 0) goto L_0x00c1;
    L_0x00b8:
        r3 = r4.isClosed();	 Catch:{ all -> 0x0092 }
        if (r3 != 0) goto L_0x00c1;
    L_0x00be:
        r4.close();	 Catch:{ all -> 0x0092 }
    L_0x00c1:
        if (r2 == 0) goto L_0x000b;
    L_0x00c3:
        r2.close();	 Catch:{ all -> 0x0092 }
        goto L_0x000b;
    L_0x00c8:
        r3 = new com.baidu.android.pushservice.i.l;	 Catch:{ Exception -> 0x00b0 }
        r3.<init>();	 Catch:{ Exception -> 0x00b0 }
        r5 = 0;
        r3.f5106a = r5;	 Catch:{ Exception -> 0x00b0 }
        r5 = 10;
        r3.f5107b = r5;	 Catch:{ Exception -> 0x00b0 }
        r5 = 10;
        r3.f5108c = r5;	 Catch:{ Exception -> 0x00b0 }
        r0 = r21;
        r3.f5109d = r0;	 Catch:{ Exception -> 0x00b0 }
        r6 = com.baidu.android.pushservice.util.C1578v.m7101c();	 Catch:{ Exception -> 0x00b0 }
        r3.f5110e = r6;	 Catch:{ Exception -> 0x00b0 }
        r0 = r20;
        com.baidu.android.pushservice.util.C1554p.m6971a(r0, r3);	 Catch:{ Exception -> 0x00b0 }
        goto L_0x0080;
    L_0x00e8:
        r3 = move-exception;
    L_0x00e9:
        if (r4 == 0) goto L_0x00f4;
    L_0x00eb:
        r5 = r4.isClosed();	 Catch:{ all -> 0x0092 }
        if (r5 != 0) goto L_0x00f4;
    L_0x00f1:
        r4.close();	 Catch:{ all -> 0x0092 }
    L_0x00f4:
        if (r2 == 0) goto L_0x00f9;
    L_0x00f6:
        r2.close();	 Catch:{ all -> 0x0092 }
    L_0x00f9:
        throw r3;	 Catch:{ all -> 0x0092 }
    L_0x00fa:
        r3 = move-exception;
        r4 = r18;
        goto L_0x00e9;
    L_0x00fe:
        r3 = move-exception;
        r4 = r18;
        goto L_0x00b1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1554p.m6973a(android.content.Context, int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0082 A:{SYNTHETIC, Splitter:B:20:0x0082} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00e8 A:{SYNTHETIC, Splitter:B:47:0x00e8} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00f3  */
    /* renamed from: a */
    public static synchronized void m6974a(android.content.Context r21, long r22) {
        /*
        r19 = com.baidu.android.pushservice.util.C1554p.class;
        monitor-enter(r19);
        r2 = com.baidu.android.pushservice.util.C1554p.m6972a(r21);	 Catch:{ all -> 0x0092 }
        r18 = 0;
        if (r2 != 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r19);
        return;
    L_0x000d:
        r3 = "PushADInfo";
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00fb, all -> 0x00f7 }
        if (r10 != 0) goto L_0x0095;
    L_0x0019:
        r4 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00fb, all -> 0x00f7 }
    L_0x001d:
        if (r4 == 0) goto L_0x00c8;
    L_0x001f:
        r3 = r4.getCount();	 Catch:{ Exception -> 0x00b0 }
        if (r3 == 0) goto L_0x00c8;
    L_0x0025:
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00b0 }
        r3.<init>();	 Catch:{ Exception -> 0x00b0 }
        r5 = "UPDATE PushADInfo SET ";
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x00b0 }
        r5 = com.baidu.android.pushservice.util.C1554p.C1552a.PushADCurTimeStamp;	 Catch:{ Exception -> 0x00b0 }
        r5 = r5.name();	 Catch:{ Exception -> 0x00b0 }
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x00b0 }
        r5 = " = ";
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x00b0 }
        r0 = r22;
        r3 = r3.append(r0);	 Catch:{ Exception -> 0x00b0 }
        r5 = " WHERE ";
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x00b0 }
        r5 = com.baidu.android.pushservice.util.C1554p.C1552a.PushADInfoId;	 Catch:{ Exception -> 0x00b0 }
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x00b0 }
        r5 = " = ";
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x00b0 }
        r5 = 1;
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x00b0 }
        r5 = r3.toString();	 Catch:{ Exception -> 0x00b0 }
        r3 = "PushClientDataBase";
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00b0 }
        r6.<init>();	 Catch:{ Exception -> 0x00b0 }
        r7 = "sql is :  ";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x00b0 }
        r6 = r6.append(r5);	 Catch:{ Exception -> 0x00b0 }
        r6 = r6.toString();	 Catch:{ Exception -> 0x00b0 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r3, r6);	 Catch:{ Exception -> 0x00b0 }
        r3 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00b0 }
        if (r3 != 0) goto L_0x00a8;
    L_0x007d:
        r2.execSQL(r5);	 Catch:{ Exception -> 0x00b0 }
    L_0x0080:
        if (r4 == 0) goto L_0x008b;
    L_0x0082:
        r3 = r4.isClosed();	 Catch:{ all -> 0x0092 }
        if (r3 != 0) goto L_0x008b;
    L_0x0088:
        r4.close();	 Catch:{ all -> 0x0092 }
    L_0x008b:
        if (r2 == 0) goto L_0x000b;
    L_0x008d:
        r2.close();	 Catch:{ all -> 0x0092 }
        goto L_0x000b;
    L_0x0092:
        r2 = move-exception;
        monitor-exit(r19);
        throw r2;
    L_0x0095:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00fb, all -> 0x00f7 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x00fb, all -> 0x00f7 }
        goto L_0x001d;
    L_0x00a8:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00b0 }
        r3 = r0;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.execSQL(r3, r5);	 Catch:{ Exception -> 0x00b0 }
        goto L_0x0080;
    L_0x00b0:
        r3 = move-exception;
    L_0x00b1:
        r5 = "PushClientDataBase";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r5, r3);	 Catch:{ all -> 0x00e5 }
        if (r4 == 0) goto L_0x00c1;
    L_0x00b8:
        r3 = r4.isClosed();	 Catch:{ all -> 0x0092 }
        if (r3 != 0) goto L_0x00c1;
    L_0x00be:
        r4.close();	 Catch:{ all -> 0x0092 }
    L_0x00c1:
        if (r2 == 0) goto L_0x000b;
    L_0x00c3:
        r2.close();	 Catch:{ all -> 0x0092 }
        goto L_0x000b;
    L_0x00c8:
        r3 = new com.baidu.android.pushservice.i.l;	 Catch:{ Exception -> 0x00b0 }
        r3.<init>();	 Catch:{ Exception -> 0x00b0 }
        r5 = 0;
        r3.f5106a = r5;	 Catch:{ Exception -> 0x00b0 }
        r5 = 10;
        r3.f5107b = r5;	 Catch:{ Exception -> 0x00b0 }
        r5 = 10;
        r3.f5108c = r5;	 Catch:{ Exception -> 0x00b0 }
        r5 = 0;
        r3.f5109d = r5;	 Catch:{ Exception -> 0x00b0 }
        r0 = r22;
        r3.f5110e = r0;	 Catch:{ Exception -> 0x00b0 }
        r0 = r21;
        com.baidu.android.pushservice.util.C1554p.m6971a(r0, r3);	 Catch:{ Exception -> 0x00b0 }
        goto L_0x0080;
    L_0x00e5:
        r3 = move-exception;
    L_0x00e6:
        if (r4 == 0) goto L_0x00f1;
    L_0x00e8:
        r5 = r4.isClosed();	 Catch:{ all -> 0x0092 }
        if (r5 != 0) goto L_0x00f1;
    L_0x00ee:
        r4.close();	 Catch:{ all -> 0x0092 }
    L_0x00f1:
        if (r2 == 0) goto L_0x00f6;
    L_0x00f3:
        r2.close();	 Catch:{ all -> 0x0092 }
    L_0x00f6:
        throw r3;	 Catch:{ all -> 0x0092 }
    L_0x00f7:
        r3 = move-exception;
        r4 = r18;
        goto L_0x00e6;
    L_0x00fb:
        r3 = move-exception;
        r4 = r18;
        goto L_0x00b1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1554p.m6974a(android.content.Context, long):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x00a6 A:{SYNTHETIC, Splitter:B:22:0x00a6} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0111 A:{SYNTHETIC, Splitter:B:50:0x0111} */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x011c  */
    /* renamed from: a */
    public static synchronized void m6975a(android.content.Context r20, boolean r21) {
        /*
        r19 = com.baidu.android.pushservice.util.C1554p.class;
        monitor-enter(r19);
        r2 = com.baidu.android.pushservice.util.C1554p.m6972a(r20);	 Catch:{ all -> 0x00b6 }
        if (r2 != 0) goto L_0x000b;
    L_0x0009:
        monitor-exit(r19);
        return;
    L_0x000b:
        r18 = 0;
        r3 = "PushADInfo";
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0124, all -> 0x0120 }
        if (r10 != 0) goto L_0x00b9;
    L_0x0019:
        r4 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x0124, all -> 0x0120 }
    L_0x001d:
        if (r4 == 0) goto L_0x00ef;
    L_0x001f:
        r3 = r4.getCount();	 Catch:{ Exception -> 0x00d7 }
        if (r3 == 0) goto L_0x00ef;
    L_0x0025:
        if (r21 == 0) goto L_0x00cc;
    L_0x0027:
        r3 = 0;
    L_0x0028:
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00d7 }
        r5.<init>();	 Catch:{ Exception -> 0x00d7 }
        r6 = "UPDATE PushADInfo SET ";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x00d7 }
        r6 = com.baidu.android.pushservice.util.C1554p.C1552a.PushADSwitch;	 Catch:{ Exception -> 0x00d7 }
        r6 = r6.name();	 Catch:{ Exception -> 0x00d7 }
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x00d7 }
        r6 = " = ";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x00d7 }
        r5 = r5.append(r3);	 Catch:{ Exception -> 0x00d7 }
        r6 = " WHERE ";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x00d7 }
        r6 = com.baidu.android.pushservice.util.C1554p.C1552a.PushADInfoId;	 Catch:{ Exception -> 0x00d7 }
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x00d7 }
        r6 = " = ";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x00d7 }
        r6 = 1;
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x00d7 }
        r5 = r5.toString();	 Catch:{ Exception -> 0x00d7 }
        r6 = "PushClientDataBase";
        r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00d7 }
        r7.<init>();	 Catch:{ Exception -> 0x00d7 }
        r8 = "sql is :  ";
        r7 = r7.append(r8);	 Catch:{ Exception -> 0x00d7 }
        r7 = r7.append(r5);	 Catch:{ Exception -> 0x00d7 }
        r7 = r7.toString();	 Catch:{ Exception -> 0x00d7 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r6, r7);	 Catch:{ Exception -> 0x00d7 }
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00d7 }
        r6.<init>();	 Catch:{ Exception -> 0x00d7 }
        r7 = "updateADStatus  setPushADSwitch  index  =    ";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x00d7 }
        r3 = r6.append(r3);	 Catch:{ Exception -> 0x00d7 }
        r6 = " sql = ";
        r3 = r3.append(r6);	 Catch:{ Exception -> 0x00d7 }
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x00d7 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x00d7 }
        r0 = r20;
        com.baidu.android.pushservice.util.C1578v.m7095b(r3, r0);	 Catch:{ Exception -> 0x00d7 }
        r3 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00d7 }
        if (r3 != 0) goto L_0x00cf;
    L_0x00a1:
        r2.execSQL(r5);	 Catch:{ Exception -> 0x00d7 }
    L_0x00a4:
        if (r4 == 0) goto L_0x00af;
    L_0x00a6:
        r3 = r4.isClosed();	 Catch:{ all -> 0x00b6 }
        if (r3 != 0) goto L_0x00af;
    L_0x00ac:
        r4.close();	 Catch:{ all -> 0x00b6 }
    L_0x00af:
        if (r2 == 0) goto L_0x0009;
    L_0x00b1:
        r2.close();	 Catch:{ all -> 0x00b6 }
        goto L_0x0009;
    L_0x00b6:
        r2 = move-exception;
        monitor-exit(r19);
        throw r2;
    L_0x00b9:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0124, all -> 0x0120 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x0124, all -> 0x0120 }
        goto L_0x001d;
    L_0x00cc:
        r3 = 1;
        goto L_0x0028;
    L_0x00cf:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00d7 }
        r3 = r0;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.execSQL(r3, r5);	 Catch:{ Exception -> 0x00d7 }
        goto L_0x00a4;
    L_0x00d7:
        r3 = move-exception;
    L_0x00d8:
        r5 = "PushClientDataBase";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r5, r3);	 Catch:{ all -> 0x010e }
        if (r4 == 0) goto L_0x00e8;
    L_0x00df:
        r3 = r4.isClosed();	 Catch:{ all -> 0x00b6 }
        if (r3 != 0) goto L_0x00e8;
    L_0x00e5:
        r4.close();	 Catch:{ all -> 0x00b6 }
    L_0x00e8:
        if (r2 == 0) goto L_0x0009;
    L_0x00ea:
        r2.close();	 Catch:{ all -> 0x00b6 }
        goto L_0x0009;
    L_0x00ef:
        r3 = new com.baidu.android.pushservice.i.l;	 Catch:{ Exception -> 0x00d7 }
        r3.<init>();	 Catch:{ Exception -> 0x00d7 }
        r5 = 0;
        r3.f5106a = r5;	 Catch:{ Exception -> 0x00d7 }
        r5 = 10;
        r3.f5107b = r5;	 Catch:{ Exception -> 0x00d7 }
        r5 = 10;
        r3.f5108c = r5;	 Catch:{ Exception -> 0x00d7 }
        r5 = 0;
        r3.f5109d = r5;	 Catch:{ Exception -> 0x00d7 }
        r6 = com.baidu.android.pushservice.util.C1578v.m7101c();	 Catch:{ Exception -> 0x00d7 }
        r3.f5110e = r6;	 Catch:{ Exception -> 0x00d7 }
        r0 = r20;
        com.baidu.android.pushservice.util.C1554p.m6971a(r0, r3);	 Catch:{ Exception -> 0x00d7 }
        goto L_0x00a4;
    L_0x010e:
        r3 = move-exception;
    L_0x010f:
        if (r4 == 0) goto L_0x011a;
    L_0x0111:
        r5 = r4.isClosed();	 Catch:{ all -> 0x00b6 }
        if (r5 != 0) goto L_0x011a;
    L_0x0117:
        r4.close();	 Catch:{ all -> 0x00b6 }
    L_0x011a:
        if (r2 == 0) goto L_0x011f;
    L_0x011c:
        r2.close();	 Catch:{ all -> 0x00b6 }
    L_0x011f:
        throw r3;	 Catch:{ all -> 0x00b6 }
    L_0x0120:
        r3 = move-exception;
        r4 = r18;
        goto L_0x010f;
    L_0x0124:
        r3 = move-exception;
        r4 = r18;
        goto L_0x00d8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1554p.m6975a(android.content.Context, boolean):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ea A:{SYNTHETIC, Splitter:B:49:0x00ea} */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00f5  */
    /* renamed from: c */
    public static synchronized com.baidu.android.pushservice.p037i.C1448m m6976c(android.content.Context r21) {
        /*
        r20 = com.baidu.android.pushservice.util.C1554p.class;
        monitor-enter(r20);
        r2 = com.baidu.android.pushservice.util.C1554p.m6972a(r21);	 Catch:{ all -> 0x00e1 }
        if (r2 != 0) goto L_0x000c;
    L_0x0009:
        r2 = 0;
    L_0x000a:
        monitor-exit(r20);
        return r2;
    L_0x000c:
        r18 = new com.baidu.android.pushservice.i.m;	 Catch:{ all -> 0x00e1 }
        r18.<init>();	 Catch:{ all -> 0x00e1 }
        r19 = 0;
        r3 = "PushADInfo";
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00b0, all -> 0x00e4 }
        if (r10 != 0) goto L_0x009d;
    L_0x001f:
        r4 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00b0, all -> 0x00e4 }
    L_0x0023:
        r3 = r4.moveToFirst();	 Catch:{ Exception -> 0x00fb }
        if (r3 == 0) goto L_0x0088;
    L_0x0029:
        r3 = com.baidu.android.pushservice.util.C1554p.C1552a.PushADSwitch;	 Catch:{ Exception -> 0x00fb }
        r3 = r3.name();	 Catch:{ Exception -> 0x00fb }
        r3 = r4.getColumnIndex(r3);	 Catch:{ Exception -> 0x00fb }
        r3 = r4.getInt(r3);	 Catch:{ Exception -> 0x00fb }
        r0 = r18;
        r0.mo13907a(r3);	 Catch:{ Exception -> 0x00fb }
        r3 = com.baidu.android.pushservice.util.C1554p.C1552a.PushADMaxCount;	 Catch:{ Exception -> 0x00fb }
        r3 = r3.name();	 Catch:{ Exception -> 0x00fb }
        r3 = r4.getColumnIndex(r3);	 Catch:{ Exception -> 0x00fb }
        r3 = r4.getInt(r3);	 Catch:{ Exception -> 0x00fb }
        r0 = r18;
        r0.mo13910b(r3);	 Catch:{ Exception -> 0x00fb }
        r3 = com.baidu.android.pushservice.util.C1554p.C1552a.PushADServerMaxCount;	 Catch:{ Exception -> 0x00fb }
        r3 = r3.name();	 Catch:{ Exception -> 0x00fb }
        r3 = r4.getColumnIndex(r3);	 Catch:{ Exception -> 0x00fb }
        r3 = r4.getInt(r3);	 Catch:{ Exception -> 0x00fb }
        r0 = r18;
        r0.mo13912c(r3);	 Catch:{ Exception -> 0x00fb }
        r3 = com.baidu.android.pushservice.util.C1554p.C1552a.PushADCurCount;	 Catch:{ Exception -> 0x00fb }
        r3 = r3.name();	 Catch:{ Exception -> 0x00fb }
        r3 = r4.getColumnIndex(r3);	 Catch:{ Exception -> 0x00fb }
        r3 = r4.getInt(r3);	 Catch:{ Exception -> 0x00fb }
        r0 = r18;
        r0.mo13914d(r3);	 Catch:{ Exception -> 0x00fb }
        r3 = com.baidu.android.pushservice.util.C1554p.C1552a.PushADCurTimeStamp;	 Catch:{ Exception -> 0x00fb }
        r3 = r3.name();	 Catch:{ Exception -> 0x00fb }
        r3 = r4.getColumnIndex(r3);	 Catch:{ Exception -> 0x00fb }
        r6 = r4.getLong(r3);	 Catch:{ Exception -> 0x00fb }
        r0 = r18;
        r0.mo13908a(r6);	 Catch:{ Exception -> 0x00fb }
    L_0x0088:
        r3 = 0;
        if (r3 == 0) goto L_0x0094;
    L_0x008b:
        r3 = r4.isClosed();	 Catch:{ all -> 0x00e1 }
        if (r3 != 0) goto L_0x0094;
    L_0x0091:
        r4.close();	 Catch:{ all -> 0x00e1 }
    L_0x0094:
        if (r2 == 0) goto L_0x0099;
    L_0x0096:
        r2.close();	 Catch:{ all -> 0x00e1 }
    L_0x0099:
        r2 = r18;
        goto L_0x000a;
    L_0x009d:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00b0, all -> 0x00e4 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x00b0, all -> 0x00e4 }
        goto L_0x0023;
    L_0x00b0:
        r3 = move-exception;
        r4 = r19;
    L_0x00b3:
        r5 = "PushClientDataBase";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00f9 }
        r6.<init>();	 Catch:{ all -> 0x00f9 }
        r7 = "error ";
        r6 = r6.append(r7);	 Catch:{ all -> 0x00f9 }
        r3 = r3.getMessage();	 Catch:{ all -> 0x00f9 }
        r3 = r6.append(r3);	 Catch:{ all -> 0x00f9 }
        r3 = r3.toString();	 Catch:{ all -> 0x00f9 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r5, r3);	 Catch:{ all -> 0x00f9 }
        r3 = 0;
        if (r3 == 0) goto L_0x00db;
    L_0x00d2:
        r3 = r4.isClosed();	 Catch:{ all -> 0x00e1 }
        if (r3 != 0) goto L_0x00db;
    L_0x00d8:
        r4.close();	 Catch:{ all -> 0x00e1 }
    L_0x00db:
        if (r2 == 0) goto L_0x0099;
    L_0x00dd:
        r2.close();	 Catch:{ all -> 0x00e1 }
        goto L_0x0099;
    L_0x00e1:
        r2 = move-exception;
        monitor-exit(r20);
        throw r2;
    L_0x00e4:
        r3 = move-exception;
        r4 = r19;
    L_0x00e7:
        r5 = 0;
        if (r5 == 0) goto L_0x00f3;
    L_0x00ea:
        r5 = r4.isClosed();	 Catch:{ all -> 0x00e1 }
        if (r5 != 0) goto L_0x00f3;
    L_0x00f0:
        r4.close();	 Catch:{ all -> 0x00e1 }
    L_0x00f3:
        if (r2 == 0) goto L_0x00f8;
    L_0x00f5:
        r2.close();	 Catch:{ all -> 0x00e1 }
    L_0x00f8:
        throw r3;	 Catch:{ all -> 0x00e1 }
    L_0x00f9:
        r3 = move-exception;
        goto L_0x00e7;
    L_0x00fb:
        r3 = move-exception;
        goto L_0x00b3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1554p.m6976c(android.content.Context):com.baidu.android.pushservice.i.m");
    }

    /* renamed from: d */
    private static C1553b m6977d(Context context) {
        synchronized (f5412b) {
            if (f5411a == null) {
                File file = new File(Environment.getDataDirectory().getAbsolutePath() + "/data" + File.separator + context.getPackageName() + "/database");
                C1425a.m6442c("PushClientDataBase", "File Path is  " + Environment.getDataDirectory().getAbsolutePath() + "/data" + File.separator + context.getPackageName() + "/database");
                if (!file.exists()) {
                    file.mkdirs();
                }
                String str = file.getAbsolutePath() + File.separator + "pushclientinfo.db";
                C1425a.m6442c("PushClientDataBase", "dbname is :" + str);
                f5411a = new C1553b(context, str, null, 2);
            }
        }
        return f5411a;
    }
}
