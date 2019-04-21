package com.baidu.android.pushservice.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import android.os.Environment;
import com.baidu.android.pushservice.p036h.C1425a;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.io.File;

/* renamed from: com.baidu.android.pushservice.util.s */
public class C1574s {
    /* renamed from: a */
    private static C1572b f5533a = null;
    /* renamed from: b */
    private static C1571a f5534b = null;
    /* renamed from: c */
    private static Object f5535c = new Object();

    /* renamed from: com.baidu.android.pushservice.util.s$a */
    private static class C1571a implements DatabaseErrorHandler {
        private C1571a() {
        }

        @TargetApi(16)
        /* renamed from: a */
        private void m7014a(String str) {
            if (!str.equalsIgnoreCase(":memory:") && str.trim().length() != 0) {
                C1425a.m6444e("PushInfoDataBase", "deleting the database file: " + str);
                try {
                    if (VERSION.SDK_INT > 18) {
                        SQLiteDatabase.deleteDatabase(new File(str));
                    } else {
                        new File(str).delete();
                    }
                } catch (Exception e) {
                    C1425a.m6443d("PushInfoDataBase", "delete failed: " + e.getMessage());
                }
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:21:0x0072  */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x005a  */
        public void onCorruption(android.database.sqlite.SQLiteDatabase r5) {
            /*
            r4 = this;
            r0 = "PushInfoDataBase";
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r2 = "Corruption reported by sqlite on database: ";
            r1 = r1.append(r2);
            r2 = r5.getPath();
            r1 = r1.append(r2);
            r1 = r1.toString();
            com.baidu.android.pushservice.p036h.C1425a.m6444e(r0, r1);
            r0 = r5.isOpen();
            if (r0 != 0) goto L_0x002a;
        L_0x0022:
            r0 = r5.getPath();
            r4.m7014a(r0);
        L_0x0029:
            return;
        L_0x002a:
            r1 = 0;
            r1 = r5.getAttachedDbs();	 Catch:{ SQLiteException -> 0x007a, all -> 0x0054 }
        L_0x002f:
            r5.close();	 Catch:{ SQLiteException -> 0x007c, all -> 0x007e }
        L_0x0032:
            if (r1 == 0) goto L_0x004c;
        L_0x0034:
            r1 = r1.iterator();
        L_0x0038:
            r0 = r1.hasNext();
            if (r0 == 0) goto L_0x0029;
        L_0x003e:
            r0 = r1.next();
            r0 = (android.util.Pair) r0;
            r0 = r0.second;
            r0 = (java.lang.String) r0;
            r4.m7014a(r0);
            goto L_0x0038;
        L_0x004c:
            r0 = r5.getPath();
            r4.m7014a(r0);
            goto L_0x0029;
        L_0x0054:
            r0 = move-exception;
            r3 = r0;
            r0 = r1;
            r1 = r3;
        L_0x0058:
            if (r0 == 0) goto L_0x0072;
        L_0x005a:
            r2 = r0.iterator();
        L_0x005e:
            r0 = r2.hasNext();
            if (r0 == 0) goto L_0x0079;
        L_0x0064:
            r0 = r2.next();
            r0 = (android.util.Pair) r0;
            r0 = r0.second;
            r0 = (java.lang.String) r0;
            r4.m7014a(r0);
            goto L_0x005e;
        L_0x0072:
            r0 = r5.getPath();
            r4.m7014a(r0);
        L_0x0079:
            throw r1;
        L_0x007a:
            r0 = move-exception;
            goto L_0x002f;
        L_0x007c:
            r0 = move-exception;
            goto L_0x0032;
        L_0x007e:
            r0 = move-exception;
            r3 = r0;
            r0 = r1;
            r1 = r3;
            goto L_0x0058;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1574s$C1571a.onCorruption(android.database.sqlite.SQLiteDatabase):void");
        }
    }

    /* renamed from: com.baidu.android.pushservice.util.s$b */
    private static class C1572b extends SQLiteOpenHelper {
        public C1572b(Context context, String str, int i, DatabaseErrorHandler databaseErrorHandler) {
            super(context, str, null, i, databaseErrorHandler);
        }

        public C1572b(Context context, String str, CursorFactory cursorFactory, int i) {
            super(context, str, cursorFactory, i);
        }

        /* renamed from: a */
        private void m7015a(SQLiteDatabase sQLiteDatabase) {
            try {
                String str = "DROP TABLE IF EXISTS PushShareInfo";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
            } catch (Exception e) {
                C1425a.m6442c("PushInfoDataBase", "dropTables Exception: " + e);
            }
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            try {
                String str = "CREATE TABLE PushShareInfo (" + C1573c.PushInfoId.name() + " INTEGER PRIMARY KEY AUTOINCREMENT, " + C1573c.PushPriority.name() + " LONG  NOT NULL DEFAULT ((0)), " + C1573c.PushVersion.name() + " INTEGER DEFAULT ((0)), " + C1573c.PushChannelID.name() + " TEXT, " + C1573c.PushCurPkgName.name() + " TEXT, " + C1573c.PushWebAppBindInfo.name() + " TEXT, " + C1573c.PushLightAppBindInfo.name() + " TEXT, " + C1573c.PushSDKClientBindInfo.name() + " TEXT, " + C1573c.PushClientsBindInfo.name() + " TEXT, " + C1573c.PushSelfBindInfo.name() + " TEXT " + ");";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                C1425a.m6442c("PushInfoDataBase", "CREATE TABLE PushShareInfo (" + C1573c.PushInfoId.name() + " INTEGER PRIMARY KEY AUTOINCREMENT, " + C1573c.PushPriority.name() + " INTEGER DEFAULT ((0)), " + C1573c.PushVersion.name() + " INTEGER DEFAULT ((0)), " + C1573c.PushChannelID.name() + " TEXT, " + C1573c.PushCurPkgName.name() + " TEXT, " + C1573c.PushWebAppBindInfo.name() + " TEXT, " + C1573c.PushLightAppBindInfo.name() + " TEXT, " + C1573c.PushSDKClientBindInfo.name() + " TEXT, " + C1573c.PushClientsBindInfo.name() + " TEXT, " + C1573c.PushSelfBindInfo.name() + " TEXT " + ");");
            } catch (Exception e) {
                C1425a.m6440a("PushInfoDataBase", e);
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            m7015a(sQLiteDatabase);
            onCreate(sQLiteDatabase);
        }
    }

    /* renamed from: com.baidu.android.pushservice.util.s$c */
    enum C1573c {
        PushInfoId,
        PushPriority,
        PushVersion,
        PushChannelID,
        PushCurPkgName,
        PushWebAppBindInfo,
        PushLightAppBindInfo,
        PushSDKClientBindInfo,
        PushClientsBindInfo,
        PushSelfBindInfo
    }

    /* JADX WARNING: Removed duplicated region for block: B:71:0x0192 A:{SYNTHETIC, Splitter:B:71:0x0192} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x010a A:{Catch:{ Exception -> 0x016f }} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x011d A:{SYNTHETIC, Splitter:B:34:0x011d} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0128  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x019f A:{SYNTHETIC, Splitter:B:76:0x019f} */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x01aa  */
    /* renamed from: a */
    public static synchronized long m7016a(android.content.Context r24, com.baidu.android.pushservice.util.C1569r r25) {
        /*
        r19 = com.baidu.android.pushservice.util.C1574s.class;
        monitor-enter(r19);
        r22 = f5535c;	 Catch:{ all -> 0x0132 }
        monitor-enter(r22);	 Catch:{ all -> 0x0132 }
        r2 = com.baidu.android.pushservice.util.C1574s.m7017a(r24);	 Catch:{ all -> 0x012f }
        if (r2 != 0) goto L_0x0011;
    L_0x000c:
        r2 = -1;
        monitor-exit(r22);	 Catch:{ all -> 0x012f }
    L_0x000f:
        monitor-exit(r19);
        return r2;
    L_0x0011:
        r23 = new android.content.ContentValues;	 Catch:{ all -> 0x012f }
        r23.<init>();	 Catch:{ all -> 0x012f }
        r3 = com.baidu.android.pushservice.util.C1574s.C1573c.PushPriority;	 Catch:{ all -> 0x012f }
        r3 = r3.name();	 Catch:{ all -> 0x012f }
        r0 = r25;
        r4 = r0.f5513a;	 Catch:{ all -> 0x012f }
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ all -> 0x012f }
        r0 = r23;
        r0.put(r3, r4);	 Catch:{ all -> 0x012f }
        r3 = com.baidu.android.pushservice.util.C1574s.C1573c.PushVersion;	 Catch:{ all -> 0x012f }
        r3 = r3.name();	 Catch:{ all -> 0x012f }
        r0 = r25;
        r4 = r0.f5514b;	 Catch:{ all -> 0x012f }
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ all -> 0x012f }
        r0 = r23;
        r0.put(r3, r4);	 Catch:{ all -> 0x012f }
        r3 = com.baidu.android.pushservice.util.C1574s.C1573c.PushChannelID;	 Catch:{ all -> 0x012f }
        r3 = r3.name();	 Catch:{ all -> 0x012f }
        r0 = r25;
        r4 = r0.f5515c;	 Catch:{ all -> 0x012f }
        r0 = r23;
        r0.put(r3, r4);	 Catch:{ all -> 0x012f }
        r3 = com.baidu.android.pushservice.util.C1574s.C1573c.PushCurPkgName;	 Catch:{ all -> 0x012f }
        r3 = r3.name();	 Catch:{ all -> 0x012f }
        r0 = r25;
        r4 = r0.f5516d;	 Catch:{ all -> 0x012f }
        r0 = r23;
        r0.put(r3, r4);	 Catch:{ all -> 0x012f }
        r3 = com.baidu.android.pushservice.util.C1574s.C1573c.PushLightAppBindInfo;	 Catch:{ all -> 0x012f }
        r3 = r3.name();	 Catch:{ all -> 0x012f }
        r0 = r25;
        r4 = r0.f5518f;	 Catch:{ all -> 0x012f }
        r0 = r23;
        r0.put(r3, r4);	 Catch:{ all -> 0x012f }
        r3 = com.baidu.android.pushservice.util.C1574s.C1573c.PushWebAppBindInfo;	 Catch:{ all -> 0x012f }
        r3 = r3.name();	 Catch:{ all -> 0x012f }
        r0 = r25;
        r4 = r0.f5517e;	 Catch:{ all -> 0x012f }
        r0 = r23;
        r0.put(r3, r4);	 Catch:{ all -> 0x012f }
        r3 = com.baidu.android.pushservice.util.C1574s.C1573c.PushSDKClientBindInfo;	 Catch:{ all -> 0x012f }
        r3 = r3.name();	 Catch:{ all -> 0x012f }
        r0 = r25;
        r4 = r0.f5519g;	 Catch:{ all -> 0x012f }
        r0 = r23;
        r0.put(r3, r4);	 Catch:{ all -> 0x012f }
        r3 = com.baidu.android.pushservice.util.C1574s.C1573c.PushClientsBindInfo;	 Catch:{ all -> 0x012f }
        r3 = r3.name();	 Catch:{ all -> 0x012f }
        r0 = r25;
        r4 = r0.f5520h;	 Catch:{ all -> 0x012f }
        r0 = r23;
        r0.put(r3, r4);	 Catch:{ all -> 0x012f }
        r3 = com.baidu.android.pushservice.util.C1574s.C1573c.PushSelfBindInfo;	 Catch:{ all -> 0x012f }
        r3 = r3.name();	 Catch:{ all -> 0x012f }
        r0 = r25;
        r4 = r0.f5521i;	 Catch:{ all -> 0x012f }
        r0 = r23;
        r0.put(r3, r4);	 Catch:{ all -> 0x012f }
        r20 = -1;
        r18 = 0;
        r3 = "PushShareInfo";
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x01b0, all -> 0x019a }
        if (r10 != 0) goto L_0x0135;
    L_0x00b5:
        r4 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x01b0, all -> 0x019a }
    L_0x00b9:
        if (r4 == 0) goto L_0x0153;
    L_0x00bb:
        r3 = r4.getCount();	 Catch:{ Exception -> 0x01b6 }
        if (r3 == 0) goto L_0x0153;
    L_0x00c1:
        r5 = "PushShareInfo";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01b6 }
        r3.<init>();	 Catch:{ Exception -> 0x01b6 }
        r6 = com.baidu.android.pushservice.util.C1574s.C1573c.PushInfoId;	 Catch:{ Exception -> 0x01b6 }
        r6 = r6.name();	 Catch:{ Exception -> 0x01b6 }
        r3 = r3.append(r6);	 Catch:{ Exception -> 0x01b6 }
        r6 = "=1";
        r3 = r3.append(r6);	 Catch:{ Exception -> 0x01b6 }
        r6 = r3.toString();	 Catch:{ Exception -> 0x01b6 }
        r7 = 0;
        r3 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x01b6 }
        if (r3 != 0) goto L_0x0148;
    L_0x00e1:
        r0 = r23;
        r3 = r2.update(r5, r0, r6, r7);	 Catch:{ Exception -> 0x01b6 }
    L_0x00e7:
        r6 = (long) r3;
        r3 = "PushInfoDataBase";
        r5 = "pushadvertiseinfo:  update into database";
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r3, r5);	 Catch:{ Exception -> 0x016f }
        r3 = "pushadvertiseinfo:  update into database";
        r0 = r24;
        com.baidu.android.pushservice.util.C1578v.m7095b(r3, r0);	 Catch:{ Exception -> 0x016f }
    L_0x00f6:
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x016f }
        r3.<init>();	 Catch:{ Exception -> 0x016f }
        r5 = "updatePushInfo pushinfo:  insert into database,  clientinfo = ";
        r5 = r3.append(r5);	 Catch:{ Exception -> 0x016f }
        r3 = r25.mo14082a();	 Catch:{ Exception -> 0x016f }
        r8 = r3 instanceof org.json.JSONObject;	 Catch:{ Exception -> 0x016f }
        if (r8 != 0) goto L_0x0192;
    L_0x010a:
        r3 = r3.toString();	 Catch:{ Exception -> 0x016f }
    L_0x010e:
        r3 = r5.append(r3);	 Catch:{ Exception -> 0x016f }
        r3 = r3.toString();	 Catch:{ Exception -> 0x016f }
        r0 = r24;
        com.baidu.android.pushservice.util.C1578v.m7095b(r3, r0);	 Catch:{ Exception -> 0x016f }
        if (r4 == 0) goto L_0x0126;
    L_0x011d:
        r3 = r4.isClosed();	 Catch:{ all -> 0x012f }
        if (r3 != 0) goto L_0x0126;
    L_0x0123:
        r4.close();	 Catch:{ all -> 0x012f }
    L_0x0126:
        if (r2 == 0) goto L_0x01ba;
    L_0x0128:
        r2.close();	 Catch:{ all -> 0x012f }
        r2 = r6;
    L_0x012c:
        monitor-exit(r22);	 Catch:{ all -> 0x012f }
        goto L_0x000f;
    L_0x012f:
        r2 = move-exception;
        monitor-exit(r22);	 Catch:{ all -> 0x012f }
        throw r2;	 Catch:{ all -> 0x0132 }
    L_0x0132:
        r2 = move-exception;
        monitor-exit(r19);
        throw r2;
    L_0x0135:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x01b0, all -> 0x019a }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x01b0, all -> 0x019a }
        goto L_0x00b9;
    L_0x0148:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x01b6 }
        r3 = r0;
        r0 = r23;
        r3 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r3, r5, r0, r6, r7);	 Catch:{ Exception -> 0x01b6 }
        goto L_0x00e7;
    L_0x0153:
        r5 = "PushShareInfo";
        r6 = 0;
        r3 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x01b6 }
        if (r3 != 0) goto L_0x0187;
    L_0x015a:
        r0 = r23;
        r6 = r2.insert(r5, r6, r0);	 Catch:{ Exception -> 0x01b6 }
    L_0x0160:
        r3 = "PushInfoDataBase";
        r5 = "pushadvertiseinfo:  insert into database";
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r3, r5);	 Catch:{ Exception -> 0x016f }
        r3 = "pushadvertiseinfo:  insert into database";
        r0 = r24;
        com.baidu.android.pushservice.util.C1578v.m7095b(r3, r0);	 Catch:{ Exception -> 0x016f }
        goto L_0x00f6;
    L_0x016f:
        r3 = move-exception;
    L_0x0170:
        r5 = "PushInfoDataBase";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r5, r3);	 Catch:{ all -> 0x01ae }
        if (r4 == 0) goto L_0x0180;
    L_0x0177:
        r3 = r4.isClosed();	 Catch:{ all -> 0x012f }
        if (r3 != 0) goto L_0x0180;
    L_0x017d:
        r4.close();	 Catch:{ all -> 0x012f }
    L_0x0180:
        if (r2 == 0) goto L_0x01ba;
    L_0x0182:
        r2.close();	 Catch:{ all -> 0x012f }
        r2 = r6;
        goto L_0x012c;
    L_0x0187:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x01b6 }
        r3 = r0;
        r0 = r23;
        r6 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.insert(r3, r5, r6, r0);	 Catch:{ Exception -> 0x01b6 }
        goto L_0x0160;
    L_0x0192:
        r3 = (org.json.JSONObject) r3;	 Catch:{ Exception -> 0x016f }
        r3 = com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation.toString(r3);	 Catch:{ Exception -> 0x016f }
        goto L_0x010e;
    L_0x019a:
        r3 = move-exception;
        r4 = r18;
    L_0x019d:
        if (r4 == 0) goto L_0x01a8;
    L_0x019f:
        r5 = r4.isClosed();	 Catch:{ all -> 0x012f }
        if (r5 != 0) goto L_0x01a8;
    L_0x01a5:
        r4.close();	 Catch:{ all -> 0x012f }
    L_0x01a8:
        if (r2 == 0) goto L_0x01ad;
    L_0x01aa:
        r2.close();	 Catch:{ all -> 0x012f }
    L_0x01ad:
        throw r3;	 Catch:{ all -> 0x012f }
    L_0x01ae:
        r3 = move-exception;
        goto L_0x019d;
    L_0x01b0:
        r3 = move-exception;
        r4 = r18;
        r6 = r20;
        goto L_0x0170;
    L_0x01b6:
        r3 = move-exception;
        r6 = r20;
        goto L_0x0170;
    L_0x01ba:
        r2 = r6;
        goto L_0x012c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1574s.m7016a(android.content.Context, com.baidu.android.pushservice.util.r):long");
    }

    /* renamed from: a */
    public static SQLiteDatabase m7017a(Context context) {
        SQLiteDatabase sQLiteDatabase = null;
        C1572b h = C1574s.m7033h(context);
        if (h == null) {
            return sQLiteDatabase;
        }
        try {
            return h.getWritableDatabase();
        } catch (Exception e) {
            C1425a.m6440a("PushInfoDataBase", e);
            return sQLiteDatabase;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0060 A:{SYNTHETIC, Splitter:B:24:0x0060} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00d2 A:{SYNTHETIC, Splitter:B:57:0x00d2} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00dd  */
    /* renamed from: a */
    public static synchronized void m7018a(android.content.Context r21, long r22) {
        /*
        r19 = com.baidu.android.pushservice.util.C1574s.class;
        monitor-enter(r19);
        r20 = f5535c;	 Catch:{ all -> 0x0073 }
        monitor-enter(r20);	 Catch:{ all -> 0x0073 }
        r2 = com.baidu.android.pushservice.util.C1574s.m7017a(r21);	 Catch:{ all -> 0x0070 }
        if (r2 != 0) goto L_0x000f;
    L_0x000c:
        monitor-exit(r20);	 Catch:{ all -> 0x0070 }
    L_0x000d:
        monitor-exit(r19);
        return;
    L_0x000f:
        r18 = 0;
        r3 = "PushShareInfo";
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00e5, all -> 0x00e1 }
        if (r10 != 0) goto L_0x0076;
    L_0x001d:
        r4 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00e5, all -> 0x00e1 }
    L_0x0021:
        if (r4 == 0) goto L_0x00a7;
    L_0x0023:
        r3 = r4.getCount();	 Catch:{ Exception -> 0x0090 }
        if (r3 == 0) goto L_0x00a7;
    L_0x0029:
        r5 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0090 }
        r5.<init>();	 Catch:{ Exception -> 0x0090 }
        r3 = com.baidu.android.pushservice.util.C1574s.C1573c.PushPriority;	 Catch:{ Exception -> 0x0090 }
        r3 = r3.name();	 Catch:{ Exception -> 0x0090 }
        r6 = java.lang.Long.valueOf(r22);	 Catch:{ Exception -> 0x0090 }
        r5.put(r3, r6);	 Catch:{ Exception -> 0x0090 }
        r6 = "PushShareInfo";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0090 }
        r3.<init>();	 Catch:{ Exception -> 0x0090 }
        r7 = com.baidu.android.pushservice.util.C1574s.C1573c.PushInfoId;	 Catch:{ Exception -> 0x0090 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0090 }
        r3 = r3.append(r7);	 Catch:{ Exception -> 0x0090 }
        r7 = "=1";
        r3 = r3.append(r7);	 Catch:{ Exception -> 0x0090 }
        r7 = r3.toString();	 Catch:{ Exception -> 0x0090 }
        r8 = 0;
        r3 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0090 }
        if (r3 != 0) goto L_0x0088;
    L_0x005b:
        r2.update(r6, r5, r7, r8);	 Catch:{ Exception -> 0x0090 }
    L_0x005e:
        if (r4 == 0) goto L_0x0069;
    L_0x0060:
        r3 = r4.isClosed();	 Catch:{ all -> 0x0070 }
        if (r3 != 0) goto L_0x0069;
    L_0x0066:
        r4.close();	 Catch:{ all -> 0x0070 }
    L_0x0069:
        if (r2 == 0) goto L_0x006e;
    L_0x006b:
        r2.close();	 Catch:{ all -> 0x0070 }
    L_0x006e:
        monitor-exit(r20);	 Catch:{ all -> 0x0070 }
        goto L_0x000d;
    L_0x0070:
        r2 = move-exception;
        monitor-exit(r20);	 Catch:{ all -> 0x0070 }
        throw r2;	 Catch:{ all -> 0x0073 }
    L_0x0073:
        r2 = move-exception;
        monitor-exit(r19);
        throw r2;
    L_0x0076:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00e5, all -> 0x00e1 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x00e5, all -> 0x00e1 }
        goto L_0x0021;
    L_0x0088:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0090 }
        r3 = r0;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r3, r6, r5, r7, r8);	 Catch:{ Exception -> 0x0090 }
        goto L_0x005e;
    L_0x0090:
        r3 = move-exception;
    L_0x0091:
        r5 = "PushInfoDataBase";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r5, r3);	 Catch:{ all -> 0x00cf }
        if (r4 == 0) goto L_0x00a1;
    L_0x0098:
        r3 = r4.isClosed();	 Catch:{ all -> 0x0070 }
        if (r3 != 0) goto L_0x00a1;
    L_0x009e:
        r4.close();	 Catch:{ all -> 0x0070 }
    L_0x00a1:
        if (r2 == 0) goto L_0x006e;
    L_0x00a3:
        r2.close();	 Catch:{ all -> 0x0070 }
        goto L_0x006e;
    L_0x00a7:
        r3 = new com.baidu.android.pushservice.util.r;	 Catch:{ Exception -> 0x0090 }
        r3.<init>();	 Catch:{ Exception -> 0x0090 }
        r0 = r22;
        r3.f5513a = r0;	 Catch:{ Exception -> 0x0090 }
        r6 = 0;
        r3.f5514b = r6;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5515c = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5516d = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5518f = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5517e = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5519g = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5520h = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5521i = r5;	 Catch:{ Exception -> 0x0090 }
        r0 = r21;
        com.baidu.android.pushservice.util.C1574s.m7016a(r0, r3);	 Catch:{ Exception -> 0x0090 }
        goto L_0x005e;
    L_0x00cf:
        r3 = move-exception;
    L_0x00d0:
        if (r4 == 0) goto L_0x00db;
    L_0x00d2:
        r5 = r4.isClosed();	 Catch:{ all -> 0x0070 }
        if (r5 != 0) goto L_0x00db;
    L_0x00d8:
        r4.close();	 Catch:{ all -> 0x0070 }
    L_0x00db:
        if (r2 == 0) goto L_0x00e0;
    L_0x00dd:
        r2.close();	 Catch:{ all -> 0x0070 }
    L_0x00e0:
        throw r3;	 Catch:{ all -> 0x0070 }
    L_0x00e1:
        r3 = move-exception;
        r4 = r18;
        goto L_0x00d0;
    L_0x00e5:
        r3 = move-exception;
        r4 = r18;
        goto L_0x0091;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1574s.m7018a(android.content.Context, long):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x005e A:{SYNTHETIC, Splitter:B:24:0x005e} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00d1 A:{SYNTHETIC, Splitter:B:57:0x00d1} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00dc  */
    /* renamed from: a */
    public static synchronized void m7019a(android.content.Context r21, java.lang.String r22) {
        /*
        r19 = com.baidu.android.pushservice.util.C1574s.class;
        monitor-enter(r19);
        r20 = f5535c;	 Catch:{ all -> 0x0071 }
        monitor-enter(r20);	 Catch:{ all -> 0x0071 }
        r2 = com.baidu.android.pushservice.util.C1574s.m7017a(r21);	 Catch:{ all -> 0x006e }
        if (r2 != 0) goto L_0x000f;
    L_0x000c:
        monitor-exit(r20);	 Catch:{ all -> 0x006e }
    L_0x000d:
        monitor-exit(r19);
        return;
    L_0x000f:
        r18 = 0;
        r3 = "PushShareInfo";
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00e4, all -> 0x00e0 }
        if (r10 != 0) goto L_0x0074;
    L_0x001d:
        r4 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00e4, all -> 0x00e0 }
    L_0x0021:
        if (r4 == 0) goto L_0x00a5;
    L_0x0023:
        r3 = r4.getCount();	 Catch:{ Exception -> 0x008e }
        if (r3 == 0) goto L_0x00a5;
    L_0x0029:
        r5 = new android.content.ContentValues;	 Catch:{ Exception -> 0x008e }
        r5.<init>();	 Catch:{ Exception -> 0x008e }
        r3 = com.baidu.android.pushservice.util.C1574s.C1573c.PushChannelID;	 Catch:{ Exception -> 0x008e }
        r3 = r3.name();	 Catch:{ Exception -> 0x008e }
        r0 = r22;
        r5.put(r3, r0);	 Catch:{ Exception -> 0x008e }
        r6 = "PushShareInfo";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x008e }
        r3.<init>();	 Catch:{ Exception -> 0x008e }
        r7 = com.baidu.android.pushservice.util.C1574s.C1573c.PushInfoId;	 Catch:{ Exception -> 0x008e }
        r7 = r7.name();	 Catch:{ Exception -> 0x008e }
        r3 = r3.append(r7);	 Catch:{ Exception -> 0x008e }
        r7 = "=1";
        r3 = r3.append(r7);	 Catch:{ Exception -> 0x008e }
        r7 = r3.toString();	 Catch:{ Exception -> 0x008e }
        r8 = 0;
        r3 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x008e }
        if (r3 != 0) goto L_0x0086;
    L_0x0059:
        r2.update(r6, r5, r7, r8);	 Catch:{ Exception -> 0x008e }
    L_0x005c:
        if (r4 == 0) goto L_0x0067;
    L_0x005e:
        r3 = r4.isClosed();	 Catch:{ all -> 0x006e }
        if (r3 != 0) goto L_0x0067;
    L_0x0064:
        r4.close();	 Catch:{ all -> 0x006e }
    L_0x0067:
        if (r2 == 0) goto L_0x006c;
    L_0x0069:
        r2.close();	 Catch:{ all -> 0x006e }
    L_0x006c:
        monitor-exit(r20);	 Catch:{ all -> 0x006e }
        goto L_0x000d;
    L_0x006e:
        r2 = move-exception;
        monitor-exit(r20);	 Catch:{ all -> 0x006e }
        throw r2;	 Catch:{ all -> 0x0071 }
    L_0x0071:
        r2 = move-exception;
        monitor-exit(r19);
        throw r2;
    L_0x0074:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00e4, all -> 0x00e0 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x00e4, all -> 0x00e0 }
        goto L_0x0021;
    L_0x0086:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x008e }
        r3 = r0;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r3, r6, r5, r7, r8);	 Catch:{ Exception -> 0x008e }
        goto L_0x005c;
    L_0x008e:
        r3 = move-exception;
    L_0x008f:
        r5 = "PushInfoDataBase";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r5, r3);	 Catch:{ all -> 0x00ce }
        if (r4 == 0) goto L_0x009f;
    L_0x0096:
        r3 = r4.isClosed();	 Catch:{ all -> 0x006e }
        if (r3 != 0) goto L_0x009f;
    L_0x009c:
        r4.close();	 Catch:{ all -> 0x006e }
    L_0x009f:
        if (r2 == 0) goto L_0x006c;
    L_0x00a1:
        r2.close();	 Catch:{ all -> 0x006e }
        goto L_0x006c;
    L_0x00a5:
        r3 = new com.baidu.android.pushservice.util.r;	 Catch:{ Exception -> 0x008e }
        r3.<init>();	 Catch:{ Exception -> 0x008e }
        r6 = 0;
        r3.f5513a = r6;	 Catch:{ Exception -> 0x008e }
        r6 = 0;
        r3.f5514b = r6;	 Catch:{ Exception -> 0x008e }
        r0 = r22;
        r3.f5515c = r0;	 Catch:{ Exception -> 0x008e }
        r5 = 0;
        r3.f5516d = r5;	 Catch:{ Exception -> 0x008e }
        r5 = 0;
        r3.f5518f = r5;	 Catch:{ Exception -> 0x008e }
        r5 = 0;
        r3.f5517e = r5;	 Catch:{ Exception -> 0x008e }
        r5 = 0;
        r3.f5519g = r5;	 Catch:{ Exception -> 0x008e }
        r5 = 0;
        r3.f5520h = r5;	 Catch:{ Exception -> 0x008e }
        r5 = 0;
        r3.f5521i = r5;	 Catch:{ Exception -> 0x008e }
        r0 = r21;
        com.baidu.android.pushservice.util.C1574s.m7016a(r0, r3);	 Catch:{ Exception -> 0x008e }
        goto L_0x005c;
    L_0x00ce:
        r3 = move-exception;
    L_0x00cf:
        if (r4 == 0) goto L_0x00da;
    L_0x00d1:
        r5 = r4.isClosed();	 Catch:{ all -> 0x006e }
        if (r5 != 0) goto L_0x00da;
    L_0x00d7:
        r4.close();	 Catch:{ all -> 0x006e }
    L_0x00da:
        if (r2 == 0) goto L_0x00df;
    L_0x00dc:
        r2.close();	 Catch:{ all -> 0x006e }
    L_0x00df:
        throw r3;	 Catch:{ all -> 0x006e }
    L_0x00e0:
        r3 = move-exception;
        r4 = r18;
        goto L_0x00cf;
    L_0x00e4:
        r3 = move-exception;
        r4 = r18;
        goto L_0x008f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1574s.m7019a(android.content.Context, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x0089 A:{Catch:{ Exception -> 0x005c, all -> 0x0078 }} */
    /* renamed from: b */
    public static int m7020b(android.content.Context r20) {
        /*
        r19 = f5535c;
        monitor-enter(r19);
        r18 = 0;
        r1 = com.baidu.android.pushservice.util.C1574s.m7017a(r20);	 Catch:{ all -> 0x0048 }
        if (r1 != 0) goto L_0x000e;
    L_0x000b:
        r1 = 0;
        monitor-exit(r19);	 Catch:{ all -> 0x0048 }
    L_0x000d:
        return r1;
    L_0x000e:
        r17 = 0;
        r2 = "PushShareInfo";
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x005c, all -> 0x0078 }
        if (r9 != 0) goto L_0x004b;
    L_0x001c:
        r3 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x005c, all -> 0x0078 }
    L_0x0020:
        r2 = r3.moveToFirst();	 Catch:{ Exception -> 0x008f }
        if (r2 == 0) goto L_0x0096;
    L_0x0026:
        r2 = com.baidu.android.pushservice.util.C1574s.C1573c.PushPriority;	 Catch:{ Exception -> 0x008f }
        r2 = r2.name();	 Catch:{ Exception -> 0x008f }
        r2 = r3.getColumnIndex(r2);	 Catch:{ Exception -> 0x008f }
        r2 = r3.getInt(r2);	 Catch:{ Exception -> 0x008f }
    L_0x0034:
        r4 = 0;
        if (r4 == 0) goto L_0x0040;
    L_0x0037:
        r4 = r3.isClosed();	 Catch:{ all -> 0x0048 }
        if (r4 != 0) goto L_0x0040;
    L_0x003d:
        r3.close();	 Catch:{ all -> 0x0048 }
    L_0x0040:
        if (r1 == 0) goto L_0x0094;
    L_0x0042:
        r1.close();	 Catch:{ all -> 0x0048 }
        r1 = r2;
    L_0x0046:
        monitor-exit(r19);	 Catch:{ all -> 0x0048 }
        goto L_0x000d;
    L_0x0048:
        r1 = move-exception;
        monitor-exit(r19);	 Catch:{ all -> 0x0048 }
        throw r1;
    L_0x004b:
        r0 = r1;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x005c, all -> 0x0078 }
        r9 = r0;
        r10 = r2;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r3 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r9, r10, r11, r12, r13, r14, r15, r16);	 Catch:{ Exception -> 0x005c, all -> 0x0078 }
        goto L_0x0020;
    L_0x005c:
        r2 = move-exception;
        r3 = r17;
    L_0x005f:
        r4 = "PushInfoDataBase";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r4, r2);	 Catch:{ all -> 0x008d }
        r2 = 0;
        if (r2 == 0) goto L_0x0070;
    L_0x0067:
        r2 = r3.isClosed();	 Catch:{ all -> 0x0048 }
        if (r2 != 0) goto L_0x0070;
    L_0x006d:
        r3.close();	 Catch:{ all -> 0x0048 }
    L_0x0070:
        if (r1 == 0) goto L_0x0091;
    L_0x0072:
        r1.close();	 Catch:{ all -> 0x0048 }
        r1 = r18;
        goto L_0x0046;
    L_0x0078:
        r2 = move-exception;
        r3 = r17;
    L_0x007b:
        r4 = 0;
        if (r4 == 0) goto L_0x0087;
    L_0x007e:
        r4 = r3.isClosed();	 Catch:{ all -> 0x0048 }
        if (r4 != 0) goto L_0x0087;
    L_0x0084:
        r3.close();	 Catch:{ all -> 0x0048 }
    L_0x0087:
        if (r1 == 0) goto L_0x008c;
    L_0x0089:
        r1.close();	 Catch:{ all -> 0x0048 }
    L_0x008c:
        throw r2;	 Catch:{ all -> 0x0048 }
    L_0x008d:
        r2 = move-exception;
        goto L_0x007b;
    L_0x008f:
        r2 = move-exception;
        goto L_0x005f;
    L_0x0091:
        r1 = r18;
        goto L_0x0046;
    L_0x0094:
        r1 = r2;
        goto L_0x0046;
    L_0x0096:
        r2 = r18;
        goto L_0x0034;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1574s.m7020b(android.content.Context):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0060 A:{SYNTHETIC, Splitter:B:24:0x0060} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00d2 A:{SYNTHETIC, Splitter:B:57:0x00d2} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00dd  */
    /* renamed from: b */
    public static synchronized void m7021b(android.content.Context r21, long r22) {
        /*
        r19 = com.baidu.android.pushservice.util.C1574s.class;
        monitor-enter(r19);
        r20 = f5535c;	 Catch:{ all -> 0x0073 }
        monitor-enter(r20);	 Catch:{ all -> 0x0073 }
        r2 = com.baidu.android.pushservice.util.C1574s.m7017a(r21);	 Catch:{ all -> 0x0070 }
        if (r2 != 0) goto L_0x000f;
    L_0x000c:
        monitor-exit(r20);	 Catch:{ all -> 0x0070 }
    L_0x000d:
        monitor-exit(r19);
        return;
    L_0x000f:
        r18 = 0;
        r3 = "PushShareInfo";
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00e5, all -> 0x00e1 }
        if (r10 != 0) goto L_0x0076;
    L_0x001d:
        r4 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00e5, all -> 0x00e1 }
    L_0x0021:
        if (r4 == 0) goto L_0x00a7;
    L_0x0023:
        r3 = r4.getCount();	 Catch:{ Exception -> 0x0090 }
        if (r3 == 0) goto L_0x00a7;
    L_0x0029:
        r5 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0090 }
        r5.<init>();	 Catch:{ Exception -> 0x0090 }
        r3 = com.baidu.android.pushservice.util.C1574s.C1573c.PushVersion;	 Catch:{ Exception -> 0x0090 }
        r3 = r3.name();	 Catch:{ Exception -> 0x0090 }
        r6 = java.lang.Long.valueOf(r22);	 Catch:{ Exception -> 0x0090 }
        r5.put(r3, r6);	 Catch:{ Exception -> 0x0090 }
        r6 = "PushShareInfo";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0090 }
        r3.<init>();	 Catch:{ Exception -> 0x0090 }
        r7 = com.baidu.android.pushservice.util.C1574s.C1573c.PushInfoId;	 Catch:{ Exception -> 0x0090 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0090 }
        r3 = r3.append(r7);	 Catch:{ Exception -> 0x0090 }
        r7 = "=1";
        r3 = r3.append(r7);	 Catch:{ Exception -> 0x0090 }
        r7 = r3.toString();	 Catch:{ Exception -> 0x0090 }
        r8 = 0;
        r3 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0090 }
        if (r3 != 0) goto L_0x0088;
    L_0x005b:
        r2.update(r6, r5, r7, r8);	 Catch:{ Exception -> 0x0090 }
    L_0x005e:
        if (r4 == 0) goto L_0x0069;
    L_0x0060:
        r3 = r4.isClosed();	 Catch:{ all -> 0x0070 }
        if (r3 != 0) goto L_0x0069;
    L_0x0066:
        r4.close();	 Catch:{ all -> 0x0070 }
    L_0x0069:
        if (r2 == 0) goto L_0x006e;
    L_0x006b:
        r2.close();	 Catch:{ all -> 0x0070 }
    L_0x006e:
        monitor-exit(r20);	 Catch:{ all -> 0x0070 }
        goto L_0x000d;
    L_0x0070:
        r2 = move-exception;
        monitor-exit(r20);	 Catch:{ all -> 0x0070 }
        throw r2;	 Catch:{ all -> 0x0073 }
    L_0x0073:
        r2 = move-exception;
        monitor-exit(r19);
        throw r2;
    L_0x0076:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00e5, all -> 0x00e1 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x00e5, all -> 0x00e1 }
        goto L_0x0021;
    L_0x0088:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0090 }
        r3 = r0;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r3, r6, r5, r7, r8);	 Catch:{ Exception -> 0x0090 }
        goto L_0x005e;
    L_0x0090:
        r3 = move-exception;
    L_0x0091:
        r5 = "PushInfoDataBase";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r5, r3);	 Catch:{ all -> 0x00cf }
        if (r4 == 0) goto L_0x00a1;
    L_0x0098:
        r3 = r4.isClosed();	 Catch:{ all -> 0x0070 }
        if (r3 != 0) goto L_0x00a1;
    L_0x009e:
        r4.close();	 Catch:{ all -> 0x0070 }
    L_0x00a1:
        if (r2 == 0) goto L_0x006e;
    L_0x00a3:
        r2.close();	 Catch:{ all -> 0x0070 }
        goto L_0x006e;
    L_0x00a7:
        r3 = new com.baidu.android.pushservice.util.r;	 Catch:{ Exception -> 0x0090 }
        r3.<init>();	 Catch:{ Exception -> 0x0090 }
        r6 = 0;
        r3.f5513a = r6;	 Catch:{ Exception -> 0x0090 }
        r0 = r22;
        r3.f5514b = r0;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5515c = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5516d = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5518f = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5517e = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5519g = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5520h = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5521i = r5;	 Catch:{ Exception -> 0x0090 }
        r0 = r21;
        com.baidu.android.pushservice.util.C1574s.m7016a(r0, r3);	 Catch:{ Exception -> 0x0090 }
        goto L_0x005e;
    L_0x00cf:
        r3 = move-exception;
    L_0x00d0:
        if (r4 == 0) goto L_0x00db;
    L_0x00d2:
        r5 = r4.isClosed();	 Catch:{ all -> 0x0070 }
        if (r5 != 0) goto L_0x00db;
    L_0x00d8:
        r4.close();	 Catch:{ all -> 0x0070 }
    L_0x00db:
        if (r2 == 0) goto L_0x00e0;
    L_0x00dd:
        r2.close();	 Catch:{ all -> 0x0070 }
    L_0x00e0:
        throw r3;	 Catch:{ all -> 0x0070 }
    L_0x00e1:
        r3 = move-exception;
        r4 = r18;
        goto L_0x00d0;
    L_0x00e5:
        r3 = move-exception;
        r4 = r18;
        goto L_0x0091;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1574s.m7021b(android.content.Context, long):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x005e A:{SYNTHETIC, Splitter:B:23:0x005e} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00d1 A:{SYNTHETIC, Splitter:B:56:0x00d1} */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00dc  */
    /* renamed from: b */
    public static synchronized void m7022b(android.content.Context r21, java.lang.String r22) {
        /*
        r19 = com.baidu.android.pushservice.util.C1574s.class;
        monitor-enter(r19);
        r20 = f5535c;	 Catch:{ all -> 0x0071 }
        monitor-enter(r20);	 Catch:{ all -> 0x0071 }
        r2 = com.baidu.android.pushservice.util.C1574s.m7017a(r21);	 Catch:{ all -> 0x006e }
        r18 = 0;
        if (r2 != 0) goto L_0x0011;
    L_0x000e:
        monitor-exit(r20);	 Catch:{ all -> 0x006e }
    L_0x000f:
        monitor-exit(r19);
        return;
    L_0x0011:
        r3 = "PushShareInfo";
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00e4, all -> 0x00e0 }
        if (r10 != 0) goto L_0x0074;
    L_0x001d:
        r4 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00e4, all -> 0x00e0 }
    L_0x0021:
        if (r4 == 0) goto L_0x00a5;
    L_0x0023:
        r3 = r4.getCount();	 Catch:{ Exception -> 0x008e }
        if (r3 == 0) goto L_0x00a5;
    L_0x0029:
        r5 = new android.content.ContentValues;	 Catch:{ Exception -> 0x008e }
        r5.<init>();	 Catch:{ Exception -> 0x008e }
        r3 = com.baidu.android.pushservice.util.C1574s.C1573c.PushCurPkgName;	 Catch:{ Exception -> 0x008e }
        r3 = r3.name();	 Catch:{ Exception -> 0x008e }
        r0 = r22;
        r5.put(r3, r0);	 Catch:{ Exception -> 0x008e }
        r6 = "PushShareInfo";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x008e }
        r3.<init>();	 Catch:{ Exception -> 0x008e }
        r7 = com.baidu.android.pushservice.util.C1574s.C1573c.PushInfoId;	 Catch:{ Exception -> 0x008e }
        r7 = r7.name();	 Catch:{ Exception -> 0x008e }
        r3 = r3.append(r7);	 Catch:{ Exception -> 0x008e }
        r7 = "=1";
        r3 = r3.append(r7);	 Catch:{ Exception -> 0x008e }
        r7 = r3.toString();	 Catch:{ Exception -> 0x008e }
        r8 = 0;
        r3 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x008e }
        if (r3 != 0) goto L_0x0086;
    L_0x0059:
        r2.update(r6, r5, r7, r8);	 Catch:{ Exception -> 0x008e }
    L_0x005c:
        if (r4 == 0) goto L_0x0067;
    L_0x005e:
        r3 = r4.isClosed();	 Catch:{ all -> 0x006e }
        if (r3 != 0) goto L_0x0067;
    L_0x0064:
        r4.close();	 Catch:{ all -> 0x006e }
    L_0x0067:
        if (r2 == 0) goto L_0x006c;
    L_0x0069:
        r2.close();	 Catch:{ all -> 0x006e }
    L_0x006c:
        monitor-exit(r20);	 Catch:{ all -> 0x006e }
        goto L_0x000f;
    L_0x006e:
        r2 = move-exception;
        monitor-exit(r20);	 Catch:{ all -> 0x006e }
        throw r2;	 Catch:{ all -> 0x0071 }
    L_0x0071:
        r2 = move-exception;
        monitor-exit(r19);
        throw r2;
    L_0x0074:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00e4, all -> 0x00e0 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x00e4, all -> 0x00e0 }
        goto L_0x0021;
    L_0x0086:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x008e }
        r3 = r0;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r3, r6, r5, r7, r8);	 Catch:{ Exception -> 0x008e }
        goto L_0x005c;
    L_0x008e:
        r3 = move-exception;
    L_0x008f:
        r5 = "PushInfoDataBase";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r5, r3);	 Catch:{ all -> 0x00ce }
        if (r4 == 0) goto L_0x009f;
    L_0x0096:
        r3 = r4.isClosed();	 Catch:{ all -> 0x006e }
        if (r3 != 0) goto L_0x009f;
    L_0x009c:
        r4.close();	 Catch:{ all -> 0x006e }
    L_0x009f:
        if (r2 == 0) goto L_0x006c;
    L_0x00a1:
        r2.close();	 Catch:{ all -> 0x006e }
        goto L_0x006c;
    L_0x00a5:
        r3 = new com.baidu.android.pushservice.util.r;	 Catch:{ Exception -> 0x008e }
        r3.<init>();	 Catch:{ Exception -> 0x008e }
        r6 = 0;
        r3.f5513a = r6;	 Catch:{ Exception -> 0x008e }
        r6 = 0;
        r3.f5514b = r6;	 Catch:{ Exception -> 0x008e }
        r5 = 0;
        r3.f5515c = r5;	 Catch:{ Exception -> 0x008e }
        r0 = r22;
        r3.f5516d = r0;	 Catch:{ Exception -> 0x008e }
        r5 = 0;
        r3.f5518f = r5;	 Catch:{ Exception -> 0x008e }
        r5 = 0;
        r3.f5517e = r5;	 Catch:{ Exception -> 0x008e }
        r5 = 0;
        r3.f5519g = r5;	 Catch:{ Exception -> 0x008e }
        r5 = 0;
        r3.f5520h = r5;	 Catch:{ Exception -> 0x008e }
        r5 = 0;
        r3.f5521i = r5;	 Catch:{ Exception -> 0x008e }
        r0 = r21;
        com.baidu.android.pushservice.util.C1574s.m7016a(r0, r3);	 Catch:{ Exception -> 0x008e }
        goto L_0x005c;
    L_0x00ce:
        r3 = move-exception;
    L_0x00cf:
        if (r4 == 0) goto L_0x00da;
    L_0x00d1:
        r5 = r4.isClosed();	 Catch:{ all -> 0x006e }
        if (r5 != 0) goto L_0x00da;
    L_0x00d7:
        r4.close();	 Catch:{ all -> 0x006e }
    L_0x00da:
        if (r2 == 0) goto L_0x00df;
    L_0x00dc:
        r2.close();	 Catch:{ all -> 0x006e }
    L_0x00df:
        throw r3;	 Catch:{ all -> 0x006e }
    L_0x00e0:
        r3 = move-exception;
        r4 = r18;
        goto L_0x00cf;
    L_0x00e4:
        r3 = move-exception;
        r4 = r18;
        goto L_0x008f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1574s.m7022b(android.content.Context, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x0089 A:{Catch:{ Exception -> 0x005c, all -> 0x0078 }} */
    /* renamed from: c */
    public static java.lang.String m7023c(android.content.Context r20) {
        /*
        r19 = f5535c;
        monitor-enter(r19);
        r18 = 0;
        r1 = com.baidu.android.pushservice.util.C1574s.m7017a(r20);	 Catch:{ all -> 0x0048 }
        if (r1 != 0) goto L_0x000e;
    L_0x000b:
        r1 = 0;
        monitor-exit(r19);	 Catch:{ all -> 0x0048 }
    L_0x000d:
        return r1;
    L_0x000e:
        r17 = 0;
        r2 = "PushShareInfo";
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x005c, all -> 0x0078 }
        if (r9 != 0) goto L_0x004b;
    L_0x001c:
        r3 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x005c, all -> 0x0078 }
    L_0x0020:
        r2 = r3.moveToFirst();	 Catch:{ Exception -> 0x008f }
        if (r2 == 0) goto L_0x0096;
    L_0x0026:
        r2 = com.baidu.android.pushservice.util.C1574s.C1573c.PushChannelID;	 Catch:{ Exception -> 0x008f }
        r2 = r2.name();	 Catch:{ Exception -> 0x008f }
        r2 = r3.getColumnIndex(r2);	 Catch:{ Exception -> 0x008f }
        r2 = r3.getString(r2);	 Catch:{ Exception -> 0x008f }
    L_0x0034:
        r4 = 0;
        if (r4 == 0) goto L_0x0040;
    L_0x0037:
        r4 = r3.isClosed();	 Catch:{ all -> 0x0048 }
        if (r4 != 0) goto L_0x0040;
    L_0x003d:
        r3.close();	 Catch:{ all -> 0x0048 }
    L_0x0040:
        if (r1 == 0) goto L_0x0094;
    L_0x0042:
        r1.close();	 Catch:{ all -> 0x0048 }
        r1 = r2;
    L_0x0046:
        monitor-exit(r19);	 Catch:{ all -> 0x0048 }
        goto L_0x000d;
    L_0x0048:
        r1 = move-exception;
        monitor-exit(r19);	 Catch:{ all -> 0x0048 }
        throw r1;
    L_0x004b:
        r0 = r1;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x005c, all -> 0x0078 }
        r9 = r0;
        r10 = r2;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r3 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r9, r10, r11, r12, r13, r14, r15, r16);	 Catch:{ Exception -> 0x005c, all -> 0x0078 }
        goto L_0x0020;
    L_0x005c:
        r2 = move-exception;
        r3 = r17;
    L_0x005f:
        r4 = "PushInfoDataBase";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r4, r2);	 Catch:{ all -> 0x008d }
        r2 = 0;
        if (r2 == 0) goto L_0x0070;
    L_0x0067:
        r2 = r3.isClosed();	 Catch:{ all -> 0x0048 }
        if (r2 != 0) goto L_0x0070;
    L_0x006d:
        r3.close();	 Catch:{ all -> 0x0048 }
    L_0x0070:
        if (r1 == 0) goto L_0x0091;
    L_0x0072:
        r1.close();	 Catch:{ all -> 0x0048 }
        r1 = r18;
        goto L_0x0046;
    L_0x0078:
        r2 = move-exception;
        r3 = r17;
    L_0x007b:
        r4 = 0;
        if (r4 == 0) goto L_0x0087;
    L_0x007e:
        r4 = r3.isClosed();	 Catch:{ all -> 0x0048 }
        if (r4 != 0) goto L_0x0087;
    L_0x0084:
        r3.close();	 Catch:{ all -> 0x0048 }
    L_0x0087:
        if (r1 == 0) goto L_0x008c;
    L_0x0089:
        r1.close();	 Catch:{ all -> 0x0048 }
    L_0x008c:
        throw r2;	 Catch:{ all -> 0x0048 }
    L_0x008d:
        r2 = move-exception;
        goto L_0x007b;
    L_0x008f:
        r2 = move-exception;
        goto L_0x005f;
    L_0x0091:
        r1 = r18;
        goto L_0x0046;
    L_0x0094:
        r1 = r2;
        goto L_0x0046;
    L_0x0096:
        r2 = r18;
        goto L_0x0034;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1574s.m7023c(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0060 A:{SYNTHETIC, Splitter:B:24:0x0060} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00d3 A:{SYNTHETIC, Splitter:B:57:0x00d3} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00de  */
    /* renamed from: c */
    public static synchronized void m7024c(android.content.Context r21, java.lang.String r22) {
        /*
        r19 = com.baidu.android.pushservice.util.C1574s.class;
        monitor-enter(r19);
        r20 = f5535c;	 Catch:{ all -> 0x0073 }
        monitor-enter(r20);	 Catch:{ all -> 0x0073 }
        r2 = com.baidu.android.pushservice.util.C1574s.m7017a(r21);	 Catch:{ all -> 0x0070 }
        r18 = 0;
        if (r2 == 0) goto L_0x0010;
    L_0x000e:
        if (r22 != 0) goto L_0x0013;
    L_0x0010:
        monitor-exit(r20);	 Catch:{ all -> 0x0070 }
    L_0x0011:
        monitor-exit(r19);
        return;
    L_0x0013:
        r3 = "PushShareInfo";
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00e6, all -> 0x00e2 }
        if (r10 != 0) goto L_0x0076;
    L_0x001f:
        r4 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00e6, all -> 0x00e2 }
    L_0x0023:
        if (r4 == 0) goto L_0x00a7;
    L_0x0025:
        r3 = r4.getCount();	 Catch:{ Exception -> 0x0090 }
        if (r3 == 0) goto L_0x00a7;
    L_0x002b:
        r5 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0090 }
        r5.<init>();	 Catch:{ Exception -> 0x0090 }
        r3 = com.baidu.android.pushservice.util.C1574s.C1573c.PushWebAppBindInfo;	 Catch:{ Exception -> 0x0090 }
        r3 = r3.name();	 Catch:{ Exception -> 0x0090 }
        r0 = r22;
        r5.put(r3, r0);	 Catch:{ Exception -> 0x0090 }
        r6 = "PushShareInfo";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0090 }
        r3.<init>();	 Catch:{ Exception -> 0x0090 }
        r7 = com.baidu.android.pushservice.util.C1574s.C1573c.PushInfoId;	 Catch:{ Exception -> 0x0090 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0090 }
        r3 = r3.append(r7);	 Catch:{ Exception -> 0x0090 }
        r7 = "=1";
        r3 = r3.append(r7);	 Catch:{ Exception -> 0x0090 }
        r7 = r3.toString();	 Catch:{ Exception -> 0x0090 }
        r8 = 0;
        r3 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0090 }
        if (r3 != 0) goto L_0x0088;
    L_0x005b:
        r2.update(r6, r5, r7, r8);	 Catch:{ Exception -> 0x0090 }
    L_0x005e:
        if (r4 == 0) goto L_0x0069;
    L_0x0060:
        r3 = r4.isClosed();	 Catch:{ all -> 0x0070 }
        if (r3 != 0) goto L_0x0069;
    L_0x0066:
        r4.close();	 Catch:{ all -> 0x0070 }
    L_0x0069:
        if (r2 == 0) goto L_0x006e;
    L_0x006b:
        r2.close();	 Catch:{ all -> 0x0070 }
    L_0x006e:
        monitor-exit(r20);	 Catch:{ all -> 0x0070 }
        goto L_0x0011;
    L_0x0070:
        r2 = move-exception;
        monitor-exit(r20);	 Catch:{ all -> 0x0070 }
        throw r2;	 Catch:{ all -> 0x0073 }
    L_0x0073:
        r2 = move-exception;
        monitor-exit(r19);
        throw r2;
    L_0x0076:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00e6, all -> 0x00e2 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x00e6, all -> 0x00e2 }
        goto L_0x0023;
    L_0x0088:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0090 }
        r3 = r0;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r3, r6, r5, r7, r8);	 Catch:{ Exception -> 0x0090 }
        goto L_0x005e;
    L_0x0090:
        r3 = move-exception;
    L_0x0091:
        r5 = "PushInfoDataBase";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r5, r3);	 Catch:{ all -> 0x00d0 }
        if (r4 == 0) goto L_0x00a1;
    L_0x0098:
        r3 = r4.isClosed();	 Catch:{ all -> 0x0070 }
        if (r3 != 0) goto L_0x00a1;
    L_0x009e:
        r4.close();	 Catch:{ all -> 0x0070 }
    L_0x00a1:
        if (r2 == 0) goto L_0x006e;
    L_0x00a3:
        r2.close();	 Catch:{ all -> 0x0070 }
        goto L_0x006e;
    L_0x00a7:
        r3 = new com.baidu.android.pushservice.util.r;	 Catch:{ Exception -> 0x0090 }
        r3.<init>();	 Catch:{ Exception -> 0x0090 }
        r6 = 0;
        r3.f5513a = r6;	 Catch:{ Exception -> 0x0090 }
        r6 = 0;
        r3.f5514b = r6;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5515c = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5516d = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5518f = r5;	 Catch:{ Exception -> 0x0090 }
        r0 = r22;
        r3.f5517e = r0;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5519g = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5520h = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5521i = r5;	 Catch:{ Exception -> 0x0090 }
        r0 = r21;
        com.baidu.android.pushservice.util.C1574s.m7016a(r0, r3);	 Catch:{ Exception -> 0x0090 }
        goto L_0x005e;
    L_0x00d0:
        r3 = move-exception;
    L_0x00d1:
        if (r4 == 0) goto L_0x00dc;
    L_0x00d3:
        r5 = r4.isClosed();	 Catch:{ all -> 0x0070 }
        if (r5 != 0) goto L_0x00dc;
    L_0x00d9:
        r4.close();	 Catch:{ all -> 0x0070 }
    L_0x00dc:
        if (r2 == 0) goto L_0x00e1;
    L_0x00de:
        r2.close();	 Catch:{ all -> 0x0070 }
    L_0x00e1:
        throw r3;	 Catch:{ all -> 0x0070 }
    L_0x00e2:
        r3 = move-exception;
        r4 = r18;
        goto L_0x00d1;
    L_0x00e6:
        r3 = move-exception;
        r4 = r18;
        goto L_0x0091;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1574s.m7024c(android.content.Context, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x0089 A:{Catch:{ Exception -> 0x005c, all -> 0x0078 }} */
    /* renamed from: d */
    public static java.lang.String m7025d(android.content.Context r20) {
        /*
        r19 = f5535c;
        monitor-enter(r19);
        r18 = 0;
        r1 = com.baidu.android.pushservice.util.C1574s.m7017a(r20);	 Catch:{ all -> 0x0048 }
        if (r1 != 0) goto L_0x000e;
    L_0x000b:
        r1 = 0;
        monitor-exit(r19);	 Catch:{ all -> 0x0048 }
    L_0x000d:
        return r1;
    L_0x000e:
        r17 = 0;
        r2 = "PushShareInfo";
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x005c, all -> 0x0078 }
        if (r9 != 0) goto L_0x004b;
    L_0x001c:
        r3 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x005c, all -> 0x0078 }
    L_0x0020:
        r2 = r3.moveToFirst();	 Catch:{ Exception -> 0x008f }
        if (r2 == 0) goto L_0x0096;
    L_0x0026:
        r2 = com.baidu.android.pushservice.util.C1574s.C1573c.PushWebAppBindInfo;	 Catch:{ Exception -> 0x008f }
        r2 = r2.name();	 Catch:{ Exception -> 0x008f }
        r2 = r3.getColumnIndex(r2);	 Catch:{ Exception -> 0x008f }
        r2 = r3.getString(r2);	 Catch:{ Exception -> 0x008f }
    L_0x0034:
        r4 = 0;
        if (r4 == 0) goto L_0x0040;
    L_0x0037:
        r4 = r3.isClosed();	 Catch:{ all -> 0x0048 }
        if (r4 != 0) goto L_0x0040;
    L_0x003d:
        r3.close();	 Catch:{ all -> 0x0048 }
    L_0x0040:
        if (r1 == 0) goto L_0x0094;
    L_0x0042:
        r1.close();	 Catch:{ all -> 0x0048 }
        r1 = r2;
    L_0x0046:
        monitor-exit(r19);	 Catch:{ all -> 0x0048 }
        goto L_0x000d;
    L_0x0048:
        r1 = move-exception;
        monitor-exit(r19);	 Catch:{ all -> 0x0048 }
        throw r1;
    L_0x004b:
        r0 = r1;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x005c, all -> 0x0078 }
        r9 = r0;
        r10 = r2;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r3 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r9, r10, r11, r12, r13, r14, r15, r16);	 Catch:{ Exception -> 0x005c, all -> 0x0078 }
        goto L_0x0020;
    L_0x005c:
        r2 = move-exception;
        r3 = r17;
    L_0x005f:
        r4 = "PushInfoDataBase";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r4, r2);	 Catch:{ all -> 0x008d }
        r2 = 0;
        if (r2 == 0) goto L_0x0070;
    L_0x0067:
        r2 = r3.isClosed();	 Catch:{ all -> 0x0048 }
        if (r2 != 0) goto L_0x0070;
    L_0x006d:
        r3.close();	 Catch:{ all -> 0x0048 }
    L_0x0070:
        if (r1 == 0) goto L_0x0091;
    L_0x0072:
        r1.close();	 Catch:{ all -> 0x0048 }
        r1 = r18;
        goto L_0x0046;
    L_0x0078:
        r2 = move-exception;
        r3 = r17;
    L_0x007b:
        r4 = 0;
        if (r4 == 0) goto L_0x0087;
    L_0x007e:
        r4 = r3.isClosed();	 Catch:{ all -> 0x0048 }
        if (r4 != 0) goto L_0x0087;
    L_0x0084:
        r3.close();	 Catch:{ all -> 0x0048 }
    L_0x0087:
        if (r1 == 0) goto L_0x008c;
    L_0x0089:
        r1.close();	 Catch:{ all -> 0x0048 }
    L_0x008c:
        throw r2;	 Catch:{ all -> 0x0048 }
    L_0x008d:
        r2 = move-exception;
        goto L_0x007b;
    L_0x008f:
        r2 = move-exception;
        goto L_0x005f;
    L_0x0091:
        r1 = r18;
        goto L_0x0046;
    L_0x0094:
        r1 = r2;
        goto L_0x0046;
    L_0x0096:
        r2 = r18;
        goto L_0x0034;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1574s.m7025d(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0060 A:{SYNTHETIC, Splitter:B:24:0x0060} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00d3 A:{SYNTHETIC, Splitter:B:57:0x00d3} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00de  */
    /* renamed from: d */
    public static synchronized void m7026d(android.content.Context r21, java.lang.String r22) {
        /*
        r19 = com.baidu.android.pushservice.util.C1574s.class;
        monitor-enter(r19);
        r20 = f5535c;	 Catch:{ all -> 0x0073 }
        monitor-enter(r20);	 Catch:{ all -> 0x0073 }
        r2 = com.baidu.android.pushservice.util.C1574s.m7017a(r21);	 Catch:{ all -> 0x0070 }
        r18 = 0;
        if (r2 == 0) goto L_0x0010;
    L_0x000e:
        if (r22 != 0) goto L_0x0013;
    L_0x0010:
        monitor-exit(r20);	 Catch:{ all -> 0x0070 }
    L_0x0011:
        monitor-exit(r19);
        return;
    L_0x0013:
        r3 = "PushShareInfo";
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00e6, all -> 0x00e2 }
        if (r10 != 0) goto L_0x0076;
    L_0x001f:
        r4 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00e6, all -> 0x00e2 }
    L_0x0023:
        if (r4 == 0) goto L_0x00a7;
    L_0x0025:
        r3 = r4.getCount();	 Catch:{ Exception -> 0x0090 }
        if (r3 == 0) goto L_0x00a7;
    L_0x002b:
        r5 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0090 }
        r5.<init>();	 Catch:{ Exception -> 0x0090 }
        r3 = com.baidu.android.pushservice.util.C1574s.C1573c.PushLightAppBindInfo;	 Catch:{ Exception -> 0x0090 }
        r3 = r3.name();	 Catch:{ Exception -> 0x0090 }
        r0 = r22;
        r5.put(r3, r0);	 Catch:{ Exception -> 0x0090 }
        r6 = "PushShareInfo";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0090 }
        r3.<init>();	 Catch:{ Exception -> 0x0090 }
        r7 = com.baidu.android.pushservice.util.C1574s.C1573c.PushInfoId;	 Catch:{ Exception -> 0x0090 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0090 }
        r3 = r3.append(r7);	 Catch:{ Exception -> 0x0090 }
        r7 = "=1";
        r3 = r3.append(r7);	 Catch:{ Exception -> 0x0090 }
        r7 = r3.toString();	 Catch:{ Exception -> 0x0090 }
        r8 = 0;
        r3 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0090 }
        if (r3 != 0) goto L_0x0088;
    L_0x005b:
        r2.update(r6, r5, r7, r8);	 Catch:{ Exception -> 0x0090 }
    L_0x005e:
        if (r4 == 0) goto L_0x0069;
    L_0x0060:
        r3 = r4.isClosed();	 Catch:{ all -> 0x0070 }
        if (r3 != 0) goto L_0x0069;
    L_0x0066:
        r4.close();	 Catch:{ all -> 0x0070 }
    L_0x0069:
        if (r2 == 0) goto L_0x006e;
    L_0x006b:
        r2.close();	 Catch:{ all -> 0x0070 }
    L_0x006e:
        monitor-exit(r20);	 Catch:{ all -> 0x0070 }
        goto L_0x0011;
    L_0x0070:
        r2 = move-exception;
        monitor-exit(r20);	 Catch:{ all -> 0x0070 }
        throw r2;	 Catch:{ all -> 0x0073 }
    L_0x0073:
        r2 = move-exception;
        monitor-exit(r19);
        throw r2;
    L_0x0076:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00e6, all -> 0x00e2 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x00e6, all -> 0x00e2 }
        goto L_0x0023;
    L_0x0088:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0090 }
        r3 = r0;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r3, r6, r5, r7, r8);	 Catch:{ Exception -> 0x0090 }
        goto L_0x005e;
    L_0x0090:
        r3 = move-exception;
    L_0x0091:
        r5 = "PushInfoDataBase";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r5, r3);	 Catch:{ all -> 0x00d0 }
        if (r4 == 0) goto L_0x00a1;
    L_0x0098:
        r3 = r4.isClosed();	 Catch:{ all -> 0x0070 }
        if (r3 != 0) goto L_0x00a1;
    L_0x009e:
        r4.close();	 Catch:{ all -> 0x0070 }
    L_0x00a1:
        if (r2 == 0) goto L_0x006e;
    L_0x00a3:
        r2.close();	 Catch:{ all -> 0x0070 }
        goto L_0x006e;
    L_0x00a7:
        r3 = new com.baidu.android.pushservice.util.r;	 Catch:{ Exception -> 0x0090 }
        r3.<init>();	 Catch:{ Exception -> 0x0090 }
        r6 = 0;
        r3.f5513a = r6;	 Catch:{ Exception -> 0x0090 }
        r6 = 0;
        r3.f5514b = r6;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5515c = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5516d = r5;	 Catch:{ Exception -> 0x0090 }
        r0 = r22;
        r3.f5518f = r0;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5517e = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5519g = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5520h = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5521i = r5;	 Catch:{ Exception -> 0x0090 }
        r0 = r21;
        com.baidu.android.pushservice.util.C1574s.m7016a(r0, r3);	 Catch:{ Exception -> 0x0090 }
        goto L_0x005e;
    L_0x00d0:
        r3 = move-exception;
    L_0x00d1:
        if (r4 == 0) goto L_0x00dc;
    L_0x00d3:
        r5 = r4.isClosed();	 Catch:{ all -> 0x0070 }
        if (r5 != 0) goto L_0x00dc;
    L_0x00d9:
        r4.close();	 Catch:{ all -> 0x0070 }
    L_0x00dc:
        if (r2 == 0) goto L_0x00e1;
    L_0x00de:
        r2.close();	 Catch:{ all -> 0x0070 }
    L_0x00e1:
        throw r3;	 Catch:{ all -> 0x0070 }
    L_0x00e2:
        r3 = move-exception;
        r4 = r18;
        goto L_0x00d1;
    L_0x00e6:
        r3 = move-exception;
        r4 = r18;
        goto L_0x0091;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1574s.m7026d(android.content.Context, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x0089 A:{Catch:{ Exception -> 0x005c, all -> 0x0078 }} */
    /* renamed from: e */
    public static java.lang.String m7027e(android.content.Context r20) {
        /*
        r19 = f5535c;
        monitor-enter(r19);
        r18 = 0;
        r1 = com.baidu.android.pushservice.util.C1574s.m7017a(r20);	 Catch:{ all -> 0x0048 }
        if (r1 != 0) goto L_0x000e;
    L_0x000b:
        r1 = 0;
        monitor-exit(r19);	 Catch:{ all -> 0x0048 }
    L_0x000d:
        return r1;
    L_0x000e:
        r17 = 0;
        r2 = "PushShareInfo";
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x005c, all -> 0x0078 }
        if (r9 != 0) goto L_0x004b;
    L_0x001c:
        r3 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x005c, all -> 0x0078 }
    L_0x0020:
        r2 = r3.moveToFirst();	 Catch:{ Exception -> 0x008f }
        if (r2 == 0) goto L_0x0096;
    L_0x0026:
        r2 = com.baidu.android.pushservice.util.C1574s.C1573c.PushLightAppBindInfo;	 Catch:{ Exception -> 0x008f }
        r2 = r2.name();	 Catch:{ Exception -> 0x008f }
        r2 = r3.getColumnIndex(r2);	 Catch:{ Exception -> 0x008f }
        r2 = r3.getString(r2);	 Catch:{ Exception -> 0x008f }
    L_0x0034:
        r4 = 0;
        if (r4 == 0) goto L_0x0040;
    L_0x0037:
        r4 = r3.isClosed();	 Catch:{ all -> 0x0048 }
        if (r4 != 0) goto L_0x0040;
    L_0x003d:
        r3.close();	 Catch:{ all -> 0x0048 }
    L_0x0040:
        if (r1 == 0) goto L_0x0094;
    L_0x0042:
        r1.close();	 Catch:{ all -> 0x0048 }
        r1 = r2;
    L_0x0046:
        monitor-exit(r19);	 Catch:{ all -> 0x0048 }
        goto L_0x000d;
    L_0x0048:
        r1 = move-exception;
        monitor-exit(r19);	 Catch:{ all -> 0x0048 }
        throw r1;
    L_0x004b:
        r0 = r1;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x005c, all -> 0x0078 }
        r9 = r0;
        r10 = r2;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r3 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r9, r10, r11, r12, r13, r14, r15, r16);	 Catch:{ Exception -> 0x005c, all -> 0x0078 }
        goto L_0x0020;
    L_0x005c:
        r2 = move-exception;
        r3 = r17;
    L_0x005f:
        r4 = "PushInfoDataBase";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r4, r2);	 Catch:{ all -> 0x008d }
        r2 = 0;
        if (r2 == 0) goto L_0x0070;
    L_0x0067:
        r2 = r3.isClosed();	 Catch:{ all -> 0x0048 }
        if (r2 != 0) goto L_0x0070;
    L_0x006d:
        r3.close();	 Catch:{ all -> 0x0048 }
    L_0x0070:
        if (r1 == 0) goto L_0x0091;
    L_0x0072:
        r1.close();	 Catch:{ all -> 0x0048 }
        r1 = r18;
        goto L_0x0046;
    L_0x0078:
        r2 = move-exception;
        r3 = r17;
    L_0x007b:
        r4 = 0;
        if (r4 == 0) goto L_0x0087;
    L_0x007e:
        r4 = r3.isClosed();	 Catch:{ all -> 0x0048 }
        if (r4 != 0) goto L_0x0087;
    L_0x0084:
        r3.close();	 Catch:{ all -> 0x0048 }
    L_0x0087:
        if (r1 == 0) goto L_0x008c;
    L_0x0089:
        r1.close();	 Catch:{ all -> 0x0048 }
    L_0x008c:
        throw r2;	 Catch:{ all -> 0x0048 }
    L_0x008d:
        r2 = move-exception;
        goto L_0x007b;
    L_0x008f:
        r2 = move-exception;
        goto L_0x005f;
    L_0x0091:
        r1 = r18;
        goto L_0x0046;
    L_0x0094:
        r1 = r2;
        goto L_0x0046;
    L_0x0096:
        r2 = r18;
        goto L_0x0034;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1574s.m7027e(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0060 A:{SYNTHETIC, Splitter:B:24:0x0060} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00d3 A:{SYNTHETIC, Splitter:B:57:0x00d3} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00de  */
    /* renamed from: e */
    public static synchronized void m7028e(android.content.Context r21, java.lang.String r22) {
        /*
        r19 = com.baidu.android.pushservice.util.C1574s.class;
        monitor-enter(r19);
        r20 = f5535c;	 Catch:{ all -> 0x0073 }
        monitor-enter(r20);	 Catch:{ all -> 0x0073 }
        r2 = com.baidu.android.pushservice.util.C1574s.m7017a(r21);	 Catch:{ all -> 0x0070 }
        r18 = 0;
        if (r2 == 0) goto L_0x0010;
    L_0x000e:
        if (r22 != 0) goto L_0x0013;
    L_0x0010:
        monitor-exit(r20);	 Catch:{ all -> 0x0070 }
    L_0x0011:
        monitor-exit(r19);
        return;
    L_0x0013:
        r3 = "PushShareInfo";
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00e6, all -> 0x00e2 }
        if (r10 != 0) goto L_0x0076;
    L_0x001f:
        r4 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00e6, all -> 0x00e2 }
    L_0x0023:
        if (r4 == 0) goto L_0x00a7;
    L_0x0025:
        r3 = r4.getCount();	 Catch:{ Exception -> 0x0090 }
        if (r3 == 0) goto L_0x00a7;
    L_0x002b:
        r5 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0090 }
        r5.<init>();	 Catch:{ Exception -> 0x0090 }
        r3 = com.baidu.android.pushservice.util.C1574s.C1573c.PushSDKClientBindInfo;	 Catch:{ Exception -> 0x0090 }
        r3 = r3.name();	 Catch:{ Exception -> 0x0090 }
        r0 = r22;
        r5.put(r3, r0);	 Catch:{ Exception -> 0x0090 }
        r6 = "PushShareInfo";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0090 }
        r3.<init>();	 Catch:{ Exception -> 0x0090 }
        r7 = com.baidu.android.pushservice.util.C1574s.C1573c.PushInfoId;	 Catch:{ Exception -> 0x0090 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0090 }
        r3 = r3.append(r7);	 Catch:{ Exception -> 0x0090 }
        r7 = "=1";
        r3 = r3.append(r7);	 Catch:{ Exception -> 0x0090 }
        r7 = r3.toString();	 Catch:{ Exception -> 0x0090 }
        r8 = 0;
        r3 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0090 }
        if (r3 != 0) goto L_0x0088;
    L_0x005b:
        r2.update(r6, r5, r7, r8);	 Catch:{ Exception -> 0x0090 }
    L_0x005e:
        if (r4 == 0) goto L_0x0069;
    L_0x0060:
        r3 = r4.isClosed();	 Catch:{ all -> 0x0070 }
        if (r3 != 0) goto L_0x0069;
    L_0x0066:
        r4.close();	 Catch:{ all -> 0x0070 }
    L_0x0069:
        if (r2 == 0) goto L_0x006e;
    L_0x006b:
        r2.close();	 Catch:{ all -> 0x0070 }
    L_0x006e:
        monitor-exit(r20);	 Catch:{ all -> 0x0070 }
        goto L_0x0011;
    L_0x0070:
        r2 = move-exception;
        monitor-exit(r20);	 Catch:{ all -> 0x0070 }
        throw r2;	 Catch:{ all -> 0x0073 }
    L_0x0073:
        r2 = move-exception;
        monitor-exit(r19);
        throw r2;
    L_0x0076:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00e6, all -> 0x00e2 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x00e6, all -> 0x00e2 }
        goto L_0x0023;
    L_0x0088:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0090 }
        r3 = r0;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r3, r6, r5, r7, r8);	 Catch:{ Exception -> 0x0090 }
        goto L_0x005e;
    L_0x0090:
        r3 = move-exception;
    L_0x0091:
        r5 = "PushInfoDataBase";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r5, r3);	 Catch:{ all -> 0x00d0 }
        if (r4 == 0) goto L_0x00a1;
    L_0x0098:
        r3 = r4.isClosed();	 Catch:{ all -> 0x0070 }
        if (r3 != 0) goto L_0x00a1;
    L_0x009e:
        r4.close();	 Catch:{ all -> 0x0070 }
    L_0x00a1:
        if (r2 == 0) goto L_0x006e;
    L_0x00a3:
        r2.close();	 Catch:{ all -> 0x0070 }
        goto L_0x006e;
    L_0x00a7:
        r3 = new com.baidu.android.pushservice.util.r;	 Catch:{ Exception -> 0x0090 }
        r3.<init>();	 Catch:{ Exception -> 0x0090 }
        r6 = 0;
        r3.f5513a = r6;	 Catch:{ Exception -> 0x0090 }
        r6 = 0;
        r3.f5514b = r6;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5515c = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5516d = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5518f = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5517e = r5;	 Catch:{ Exception -> 0x0090 }
        r0 = r22;
        r3.f5519g = r0;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5520h = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5521i = r5;	 Catch:{ Exception -> 0x0090 }
        r0 = r21;
        com.baidu.android.pushservice.util.C1574s.m7016a(r0, r3);	 Catch:{ Exception -> 0x0090 }
        goto L_0x005e;
    L_0x00d0:
        r3 = move-exception;
    L_0x00d1:
        if (r4 == 0) goto L_0x00dc;
    L_0x00d3:
        r5 = r4.isClosed();	 Catch:{ all -> 0x0070 }
        if (r5 != 0) goto L_0x00dc;
    L_0x00d9:
        r4.close();	 Catch:{ all -> 0x0070 }
    L_0x00dc:
        if (r2 == 0) goto L_0x00e1;
    L_0x00de:
        r2.close();	 Catch:{ all -> 0x0070 }
    L_0x00e1:
        throw r3;	 Catch:{ all -> 0x0070 }
    L_0x00e2:
        r3 = move-exception;
        r4 = r18;
        goto L_0x00d1;
    L_0x00e6:
        r3 = move-exception;
        r4 = r18;
        goto L_0x0091;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1574s.m7028e(android.content.Context, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x0089 A:{Catch:{ Exception -> 0x005c, all -> 0x0078 }} */
    /* renamed from: f */
    public static java.lang.String m7029f(android.content.Context r20) {
        /*
        r19 = f5535c;
        monitor-enter(r19);
        r18 = 0;
        r1 = com.baidu.android.pushservice.util.C1574s.m7017a(r20);	 Catch:{ all -> 0x0048 }
        if (r1 != 0) goto L_0x000e;
    L_0x000b:
        r1 = 0;
        monitor-exit(r19);	 Catch:{ all -> 0x0048 }
    L_0x000d:
        return r1;
    L_0x000e:
        r17 = 0;
        r2 = "PushShareInfo";
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x005c, all -> 0x0078 }
        if (r9 != 0) goto L_0x004b;
    L_0x001c:
        r3 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x005c, all -> 0x0078 }
    L_0x0020:
        r2 = r3.moveToFirst();	 Catch:{ Exception -> 0x008f }
        if (r2 == 0) goto L_0x0096;
    L_0x0026:
        r2 = com.baidu.android.pushservice.util.C1574s.C1573c.PushSDKClientBindInfo;	 Catch:{ Exception -> 0x008f }
        r2 = r2.name();	 Catch:{ Exception -> 0x008f }
        r2 = r3.getColumnIndex(r2);	 Catch:{ Exception -> 0x008f }
        r2 = r3.getString(r2);	 Catch:{ Exception -> 0x008f }
    L_0x0034:
        r4 = 0;
        if (r4 == 0) goto L_0x0040;
    L_0x0037:
        r4 = r3.isClosed();	 Catch:{ all -> 0x0048 }
        if (r4 != 0) goto L_0x0040;
    L_0x003d:
        r3.close();	 Catch:{ all -> 0x0048 }
    L_0x0040:
        if (r1 == 0) goto L_0x0094;
    L_0x0042:
        r1.close();	 Catch:{ all -> 0x0048 }
        r1 = r2;
    L_0x0046:
        monitor-exit(r19);	 Catch:{ all -> 0x0048 }
        goto L_0x000d;
    L_0x0048:
        r1 = move-exception;
        monitor-exit(r19);	 Catch:{ all -> 0x0048 }
        throw r1;
    L_0x004b:
        r0 = r1;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x005c, all -> 0x0078 }
        r9 = r0;
        r10 = r2;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r3 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r9, r10, r11, r12, r13, r14, r15, r16);	 Catch:{ Exception -> 0x005c, all -> 0x0078 }
        goto L_0x0020;
    L_0x005c:
        r2 = move-exception;
        r3 = r17;
    L_0x005f:
        r4 = "PushInfoDataBase";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r4, r2);	 Catch:{ all -> 0x008d }
        r2 = 0;
        if (r2 == 0) goto L_0x0070;
    L_0x0067:
        r2 = r3.isClosed();	 Catch:{ all -> 0x0048 }
        if (r2 != 0) goto L_0x0070;
    L_0x006d:
        r3.close();	 Catch:{ all -> 0x0048 }
    L_0x0070:
        if (r1 == 0) goto L_0x0091;
    L_0x0072:
        r1.close();	 Catch:{ all -> 0x0048 }
        r1 = r18;
        goto L_0x0046;
    L_0x0078:
        r2 = move-exception;
        r3 = r17;
    L_0x007b:
        r4 = 0;
        if (r4 == 0) goto L_0x0087;
    L_0x007e:
        r4 = r3.isClosed();	 Catch:{ all -> 0x0048 }
        if (r4 != 0) goto L_0x0087;
    L_0x0084:
        r3.close();	 Catch:{ all -> 0x0048 }
    L_0x0087:
        if (r1 == 0) goto L_0x008c;
    L_0x0089:
        r1.close();	 Catch:{ all -> 0x0048 }
    L_0x008c:
        throw r2;	 Catch:{ all -> 0x0048 }
    L_0x008d:
        r2 = move-exception;
        goto L_0x007b;
    L_0x008f:
        r2 = move-exception;
        goto L_0x005f;
    L_0x0091:
        r1 = r18;
        goto L_0x0046;
    L_0x0094:
        r1 = r2;
        goto L_0x0046;
    L_0x0096:
        r2 = r18;
        goto L_0x0034;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1574s.m7029f(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0060 A:{SYNTHETIC, Splitter:B:24:0x0060} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00d3 A:{SYNTHETIC, Splitter:B:57:0x00d3} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00de  */
    /* renamed from: f */
    public static synchronized void m7030f(android.content.Context r21, java.lang.String r22) {
        /*
        r19 = com.baidu.android.pushservice.util.C1574s.class;
        monitor-enter(r19);
        r20 = f5535c;	 Catch:{ all -> 0x0073 }
        monitor-enter(r20);	 Catch:{ all -> 0x0073 }
        r2 = com.baidu.android.pushservice.util.C1574s.m7017a(r21);	 Catch:{ all -> 0x0070 }
        r18 = 0;
        if (r2 == 0) goto L_0x0010;
    L_0x000e:
        if (r22 != 0) goto L_0x0013;
    L_0x0010:
        monitor-exit(r20);	 Catch:{ all -> 0x0070 }
    L_0x0011:
        monitor-exit(r19);
        return;
    L_0x0013:
        r3 = "PushShareInfo";
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00e6, all -> 0x00e2 }
        if (r10 != 0) goto L_0x0076;
    L_0x001f:
        r4 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00e6, all -> 0x00e2 }
    L_0x0023:
        if (r4 == 0) goto L_0x00a7;
    L_0x0025:
        r3 = r4.getCount();	 Catch:{ Exception -> 0x0090 }
        if (r3 == 0) goto L_0x00a7;
    L_0x002b:
        r5 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0090 }
        r5.<init>();	 Catch:{ Exception -> 0x0090 }
        r3 = com.baidu.android.pushservice.util.C1574s.C1573c.PushClientsBindInfo;	 Catch:{ Exception -> 0x0090 }
        r3 = r3.name();	 Catch:{ Exception -> 0x0090 }
        r0 = r22;
        r5.put(r3, r0);	 Catch:{ Exception -> 0x0090 }
        r6 = "PushShareInfo";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0090 }
        r3.<init>();	 Catch:{ Exception -> 0x0090 }
        r7 = com.baidu.android.pushservice.util.C1574s.C1573c.PushInfoId;	 Catch:{ Exception -> 0x0090 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0090 }
        r3 = r3.append(r7);	 Catch:{ Exception -> 0x0090 }
        r7 = "=1";
        r3 = r3.append(r7);	 Catch:{ Exception -> 0x0090 }
        r7 = r3.toString();	 Catch:{ Exception -> 0x0090 }
        r8 = 0;
        r3 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0090 }
        if (r3 != 0) goto L_0x0088;
    L_0x005b:
        r2.update(r6, r5, r7, r8);	 Catch:{ Exception -> 0x0090 }
    L_0x005e:
        if (r4 == 0) goto L_0x0069;
    L_0x0060:
        r3 = r4.isClosed();	 Catch:{ all -> 0x0070 }
        if (r3 != 0) goto L_0x0069;
    L_0x0066:
        r4.close();	 Catch:{ all -> 0x0070 }
    L_0x0069:
        if (r2 == 0) goto L_0x006e;
    L_0x006b:
        r2.close();	 Catch:{ all -> 0x0070 }
    L_0x006e:
        monitor-exit(r20);	 Catch:{ all -> 0x0070 }
        goto L_0x0011;
    L_0x0070:
        r2 = move-exception;
        monitor-exit(r20);	 Catch:{ all -> 0x0070 }
        throw r2;	 Catch:{ all -> 0x0073 }
    L_0x0073:
        r2 = move-exception;
        monitor-exit(r19);
        throw r2;
    L_0x0076:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00e6, all -> 0x00e2 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x00e6, all -> 0x00e2 }
        goto L_0x0023;
    L_0x0088:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0090 }
        r3 = r0;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r3, r6, r5, r7, r8);	 Catch:{ Exception -> 0x0090 }
        goto L_0x005e;
    L_0x0090:
        r3 = move-exception;
    L_0x0091:
        r5 = "PushInfoDataBase";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r5, r3);	 Catch:{ all -> 0x00d0 }
        if (r4 == 0) goto L_0x00a1;
    L_0x0098:
        r3 = r4.isClosed();	 Catch:{ all -> 0x0070 }
        if (r3 != 0) goto L_0x00a1;
    L_0x009e:
        r4.close();	 Catch:{ all -> 0x0070 }
    L_0x00a1:
        if (r2 == 0) goto L_0x006e;
    L_0x00a3:
        r2.close();	 Catch:{ all -> 0x0070 }
        goto L_0x006e;
    L_0x00a7:
        r3 = new com.baidu.android.pushservice.util.r;	 Catch:{ Exception -> 0x0090 }
        r3.<init>();	 Catch:{ Exception -> 0x0090 }
        r6 = 0;
        r3.f5513a = r6;	 Catch:{ Exception -> 0x0090 }
        r6 = 0;
        r3.f5514b = r6;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5515c = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5516d = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5518f = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5517e = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5519g = r5;	 Catch:{ Exception -> 0x0090 }
        r0 = r22;
        r3.f5520h = r0;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5521i = r5;	 Catch:{ Exception -> 0x0090 }
        r0 = r21;
        com.baidu.android.pushservice.util.C1574s.m7016a(r0, r3);	 Catch:{ Exception -> 0x0090 }
        goto L_0x005e;
    L_0x00d0:
        r3 = move-exception;
    L_0x00d1:
        if (r4 == 0) goto L_0x00dc;
    L_0x00d3:
        r5 = r4.isClosed();	 Catch:{ all -> 0x0070 }
        if (r5 != 0) goto L_0x00dc;
    L_0x00d9:
        r4.close();	 Catch:{ all -> 0x0070 }
    L_0x00dc:
        if (r2 == 0) goto L_0x00e1;
    L_0x00de:
        r2.close();	 Catch:{ all -> 0x0070 }
    L_0x00e1:
        throw r3;	 Catch:{ all -> 0x0070 }
    L_0x00e2:
        r3 = move-exception;
        r4 = r18;
        goto L_0x00d1;
    L_0x00e6:
        r3 = move-exception;
        r4 = r18;
        goto L_0x0091;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1574s.m7030f(android.content.Context, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x0089 A:{Catch:{ Exception -> 0x005c, all -> 0x0078 }} */
    /* renamed from: g */
    public static java.lang.String m7031g(android.content.Context r20) {
        /*
        r19 = f5535c;
        monitor-enter(r19);
        r18 = 0;
        r1 = com.baidu.android.pushservice.util.C1574s.m7017a(r20);	 Catch:{ all -> 0x0048 }
        if (r1 != 0) goto L_0x000e;
    L_0x000b:
        r1 = 0;
        monitor-exit(r19);	 Catch:{ all -> 0x0048 }
    L_0x000d:
        return r1;
    L_0x000e:
        r17 = 0;
        r2 = "PushShareInfo";
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x005c, all -> 0x0078 }
        if (r9 != 0) goto L_0x004b;
    L_0x001c:
        r3 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x005c, all -> 0x0078 }
    L_0x0020:
        r2 = r3.moveToFirst();	 Catch:{ Exception -> 0x008f }
        if (r2 == 0) goto L_0x0096;
    L_0x0026:
        r2 = com.baidu.android.pushservice.util.C1574s.C1573c.PushClientsBindInfo;	 Catch:{ Exception -> 0x008f }
        r2 = r2.name();	 Catch:{ Exception -> 0x008f }
        r2 = r3.getColumnIndex(r2);	 Catch:{ Exception -> 0x008f }
        r2 = r3.getString(r2);	 Catch:{ Exception -> 0x008f }
    L_0x0034:
        r4 = 0;
        if (r4 == 0) goto L_0x0040;
    L_0x0037:
        r4 = r3.isClosed();	 Catch:{ all -> 0x0048 }
        if (r4 != 0) goto L_0x0040;
    L_0x003d:
        r3.close();	 Catch:{ all -> 0x0048 }
    L_0x0040:
        if (r1 == 0) goto L_0x0094;
    L_0x0042:
        r1.close();	 Catch:{ all -> 0x0048 }
        r1 = r2;
    L_0x0046:
        monitor-exit(r19);	 Catch:{ all -> 0x0048 }
        goto L_0x000d;
    L_0x0048:
        r1 = move-exception;
        monitor-exit(r19);	 Catch:{ all -> 0x0048 }
        throw r1;
    L_0x004b:
        r0 = r1;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x005c, all -> 0x0078 }
        r9 = r0;
        r10 = r2;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r3 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r9, r10, r11, r12, r13, r14, r15, r16);	 Catch:{ Exception -> 0x005c, all -> 0x0078 }
        goto L_0x0020;
    L_0x005c:
        r2 = move-exception;
        r3 = r17;
    L_0x005f:
        r4 = "PushInfoDataBase";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r4, r2);	 Catch:{ all -> 0x008d }
        r2 = 0;
        if (r2 == 0) goto L_0x0070;
    L_0x0067:
        r2 = r3.isClosed();	 Catch:{ all -> 0x0048 }
        if (r2 != 0) goto L_0x0070;
    L_0x006d:
        r3.close();	 Catch:{ all -> 0x0048 }
    L_0x0070:
        if (r1 == 0) goto L_0x0091;
    L_0x0072:
        r1.close();	 Catch:{ all -> 0x0048 }
        r1 = r18;
        goto L_0x0046;
    L_0x0078:
        r2 = move-exception;
        r3 = r17;
    L_0x007b:
        r4 = 0;
        if (r4 == 0) goto L_0x0087;
    L_0x007e:
        r4 = r3.isClosed();	 Catch:{ all -> 0x0048 }
        if (r4 != 0) goto L_0x0087;
    L_0x0084:
        r3.close();	 Catch:{ all -> 0x0048 }
    L_0x0087:
        if (r1 == 0) goto L_0x008c;
    L_0x0089:
        r1.close();	 Catch:{ all -> 0x0048 }
    L_0x008c:
        throw r2;	 Catch:{ all -> 0x0048 }
    L_0x008d:
        r2 = move-exception;
        goto L_0x007b;
    L_0x008f:
        r2 = move-exception;
        goto L_0x005f;
    L_0x0091:
        r1 = r18;
        goto L_0x0046;
    L_0x0094:
        r1 = r2;
        goto L_0x0046;
    L_0x0096:
        r2 = r18;
        goto L_0x0034;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1574s.m7031g(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0060 A:{SYNTHETIC, Splitter:B:24:0x0060} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00d3 A:{SYNTHETIC, Splitter:B:57:0x00d3} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00de  */
    /* renamed from: g */
    public static synchronized void m7032g(android.content.Context r21, java.lang.String r22) {
        /*
        r19 = com.baidu.android.pushservice.util.C1574s.class;
        monitor-enter(r19);
        r20 = f5535c;	 Catch:{ all -> 0x0073 }
        monitor-enter(r20);	 Catch:{ all -> 0x0073 }
        r2 = com.baidu.android.pushservice.util.C1574s.m7017a(r21);	 Catch:{ all -> 0x0070 }
        r18 = 0;
        if (r2 == 0) goto L_0x0010;
    L_0x000e:
        if (r22 != 0) goto L_0x0013;
    L_0x0010:
        monitor-exit(r20);	 Catch:{ all -> 0x0070 }
    L_0x0011:
        monitor-exit(r19);
        return;
    L_0x0013:
        r3 = "PushShareInfo";
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00e6, all -> 0x00e2 }
        if (r10 != 0) goto L_0x0076;
    L_0x001f:
        r4 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00e6, all -> 0x00e2 }
    L_0x0023:
        if (r4 == 0) goto L_0x00a7;
    L_0x0025:
        r3 = r4.getCount();	 Catch:{ Exception -> 0x0090 }
        if (r3 == 0) goto L_0x00a7;
    L_0x002b:
        r5 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0090 }
        r5.<init>();	 Catch:{ Exception -> 0x0090 }
        r3 = com.baidu.android.pushservice.util.C1574s.C1573c.PushSelfBindInfo;	 Catch:{ Exception -> 0x0090 }
        r3 = r3.name();	 Catch:{ Exception -> 0x0090 }
        r0 = r22;
        r5.put(r3, r0);	 Catch:{ Exception -> 0x0090 }
        r6 = "PushShareInfo";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0090 }
        r3.<init>();	 Catch:{ Exception -> 0x0090 }
        r7 = com.baidu.android.pushservice.util.C1574s.C1573c.PushInfoId;	 Catch:{ Exception -> 0x0090 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0090 }
        r3 = r3.append(r7);	 Catch:{ Exception -> 0x0090 }
        r7 = "=1";
        r3 = r3.append(r7);	 Catch:{ Exception -> 0x0090 }
        r7 = r3.toString();	 Catch:{ Exception -> 0x0090 }
        r8 = 0;
        r3 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0090 }
        if (r3 != 0) goto L_0x0088;
    L_0x005b:
        r2.update(r6, r5, r7, r8);	 Catch:{ Exception -> 0x0090 }
    L_0x005e:
        if (r4 == 0) goto L_0x0069;
    L_0x0060:
        r3 = r4.isClosed();	 Catch:{ all -> 0x0070 }
        if (r3 != 0) goto L_0x0069;
    L_0x0066:
        r4.close();	 Catch:{ all -> 0x0070 }
    L_0x0069:
        if (r2 == 0) goto L_0x006e;
    L_0x006b:
        r2.close();	 Catch:{ all -> 0x0070 }
    L_0x006e:
        monitor-exit(r20);	 Catch:{ all -> 0x0070 }
        goto L_0x0011;
    L_0x0070:
        r2 = move-exception;
        monitor-exit(r20);	 Catch:{ all -> 0x0070 }
        throw r2;	 Catch:{ all -> 0x0073 }
    L_0x0073:
        r2 = move-exception;
        monitor-exit(r19);
        throw r2;
    L_0x0076:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00e6, all -> 0x00e2 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x00e6, all -> 0x00e2 }
        goto L_0x0023;
    L_0x0088:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0090 }
        r3 = r0;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r3, r6, r5, r7, r8);	 Catch:{ Exception -> 0x0090 }
        goto L_0x005e;
    L_0x0090:
        r3 = move-exception;
    L_0x0091:
        r5 = "PushInfoDataBase";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r5, r3);	 Catch:{ all -> 0x00d0 }
        if (r4 == 0) goto L_0x00a1;
    L_0x0098:
        r3 = r4.isClosed();	 Catch:{ all -> 0x0070 }
        if (r3 != 0) goto L_0x00a1;
    L_0x009e:
        r4.close();	 Catch:{ all -> 0x0070 }
    L_0x00a1:
        if (r2 == 0) goto L_0x006e;
    L_0x00a3:
        r2.close();	 Catch:{ all -> 0x0070 }
        goto L_0x006e;
    L_0x00a7:
        r3 = new com.baidu.android.pushservice.util.r;	 Catch:{ Exception -> 0x0090 }
        r3.<init>();	 Catch:{ Exception -> 0x0090 }
        r6 = 0;
        r3.f5513a = r6;	 Catch:{ Exception -> 0x0090 }
        r6 = 0;
        r3.f5514b = r6;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5515c = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5516d = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5518f = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5517e = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5519g = r5;	 Catch:{ Exception -> 0x0090 }
        r5 = 0;
        r3.f5520h = r5;	 Catch:{ Exception -> 0x0090 }
        r0 = r22;
        r3.f5521i = r0;	 Catch:{ Exception -> 0x0090 }
        r0 = r21;
        com.baidu.android.pushservice.util.C1574s.m7016a(r0, r3);	 Catch:{ Exception -> 0x0090 }
        goto L_0x005e;
    L_0x00d0:
        r3 = move-exception;
    L_0x00d1:
        if (r4 == 0) goto L_0x00dc;
    L_0x00d3:
        r5 = r4.isClosed();	 Catch:{ all -> 0x0070 }
        if (r5 != 0) goto L_0x00dc;
    L_0x00d9:
        r4.close();	 Catch:{ all -> 0x0070 }
    L_0x00dc:
        if (r2 == 0) goto L_0x00e1;
    L_0x00de:
        r2.close();	 Catch:{ all -> 0x0070 }
    L_0x00e1:
        throw r3;	 Catch:{ all -> 0x0070 }
    L_0x00e2:
        r3 = move-exception;
        r4 = r18;
        goto L_0x00d1;
    L_0x00e6:
        r3 = move-exception;
        r4 = r18;
        goto L_0x0091;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1574s.m7032g(android.content.Context, java.lang.String):void");
    }

    /* renamed from: h */
    private static C1572b m7033h(Context context) {
        synchronized (f5535c) {
            if (f5533a == null) {
                File file = new File(Environment.getDataDirectory().getAbsolutePath() + "/data" + File.separator + context.getPackageName() + "/database");
                C1425a.m6442c("PushInfoDataBase", "File Path is  " + Environment.getDataDirectory().getAbsolutePath() + "/data" + File.separator + context.getPackageName() + "/database");
                if (!file.exists()) {
                    file.mkdirs();
                }
                String str = file.getAbsolutePath() + File.separator + "pushinfo.db";
                C1425a.m6442c("PushInfoDataBase", "dbname is :" + str);
                if (VERSION.SDK_INT >= 11) {
                    f5534b = new C1571a();
                    f5533a = new C1572b(context, str, 2, f5534b);
                } else {
                    f5533a = new C1572b(context, str, null, 2);
                }
            }
        }
        return f5533a;
    }
}
