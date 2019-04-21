package com.baidu.android.pushservice.util;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p037i.C1446k;
import com.baidu.android.pushservice.p037i.C1449n;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.io.File;
import java.io.FileFilter;

/* renamed from: com.baidu.android.pushservice.util.q */
public class C1568q {
    /* renamed from: a */
    private static C1561f f5509a = null;
    /* renamed from: b */
    private static C1560e f5510b = null;
    /* renamed from: c */
    private static Object f5511c = new Object();
    /* renamed from: d */
    private static int f5512d = 100;

    /* renamed from: com.baidu.android.pushservice.util.q$a */
    enum C1556a {
        actionId,
        actionName,
        timeStamp,
        networkStatus,
        msgType,
        msgId,
        msgLen,
        advertiseStyle,
        errorCode,
        appid,
        actionType
    }

    /* renamed from: com.baidu.android.pushservice.util.q$b */
    enum C1557b {
        alarmMsgInfoId,
        msgId,
        sendtime,
        showtime,
        expiretime,
        msgEnable,
        isAlarm
    }

    /* renamed from: com.baidu.android.pushservice.util.q$c */
    enum C1558c {
        appInfoId,
        appid,
        appType,
        rsaUserId,
        userId,
        packageName,
        appName,
        cFrom,
        versionCode,
        versionName,
        intergratedPushVersion
    }

    /* renamed from: com.baidu.android.pushservice.util.q$d */
    enum C1559d {
        actionId,
        actionName,
        timeStamp,
        networkStatus,
        msgType,
        msgId,
        msgLen,
        errorMsg,
        requestId,
        stableHeartInterval,
        errorCode,
        appid,
        channel,
        openByPackageName
    }

    /* renamed from: com.baidu.android.pushservice.util.q$e */
    private static class C1560e implements DatabaseErrorHandler {
        private C1560e() {
        }

        /* synthetic */ C1560e(C15551 c15551) {
            this();
        }

        @TargetApi(16)
        /* renamed from: a */
        private void m6978a(String str) {
            if (!str.equalsIgnoreCase(":memory:") && str.trim().length() != 0) {
                C1425a.m6444e("PushDatabase", "deleting the database file: " + str);
                try {
                    if (VERSION.SDK_INT > 18) {
                        SQLiteDatabase.deleteDatabase(new File(str));
                    } else {
                        new File(str).delete();
                    }
                } catch (Exception e) {
                    C1425a.m6443d("PushDatabase", "delete failed: " + e.getMessage());
                }
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:21:0x0072  */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x005a  */
        public void onCorruption(android.database.sqlite.SQLiteDatabase r5) {
            /*
            r4 = this;
            r0 = "PushDatabase";
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
            r4.m6978a(r0);
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
            r4.m6978a(r0);
            goto L_0x0038;
        L_0x004c:
            r0 = r5.getPath();
            r4.m6978a(r0);
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
            r4.m6978a(r0);
            goto L_0x005e;
        L_0x0072:
            r0 = r5.getPath();
            r4.m6978a(r0);
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
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1568q$C1560e.onCorruption(android.database.sqlite.SQLiteDatabase):void");
        }
    }

    /* renamed from: com.baidu.android.pushservice.util.q$f */
    private static class C1561f extends SQLiteOpenHelper {
        public C1561f(Context context, String str, int i) {
            super(context, str, null, i);
        }

        public C1561f(Context context, String str, int i, DatabaseErrorHandler databaseErrorHandler) {
            super(context, str, null, i, databaseErrorHandler);
        }

        /* renamed from: a */
        private void m6979a(SQLiteDatabase sQLiteDatabase) {
            try {
                String str = "DROP TABLE IF EXISTS MsgResultInfo";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "DROP TABLE IF EXISTS StatisticsInfo";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "DROP TABLE IF EXISTS FileDownloadingInfo";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "DROP TABLE IF EXISTS PushBehavior";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "DROP TABLE IF EXISTS AppInfo";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "DROP TABLE IF EXISTS AlarmMsgInfo";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "DROP TABLE IF EXISTS LappMsgInfo";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "DROP TABLE IF EXISTS NoDisturb";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "DROP TABLE IF EXISTS ADPushBehavior";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                String str2 = "DROP TABLE IF EXISTS MsgInfo";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str2);
                } else {
                    sQLiteDatabase.execSQL(str2);
                }
            } catch (Exception e) {
                C1425a.m6442c("PushDatabase", "dropTables Exception: " + e);
            }
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            try {
                String str = "DROP TABLE IF EXISTS MsgResultInfo";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "CREATE TABLE StatisticsInfo (" + C1567l.info_id.name() + " INTEGER PRIMARY KEY AUTOINCREMENT, " + C1567l.packageName.name() + " TEXT NOT NULL, " + C1567l.open_type.name() + " TEXT NOT NULL, " + C1567l.msgid.name() + " TEXT, " + C1567l.app_open_time.name() + " TEXT NOT NULL, " + C1567l.app_close_time.name() + " TEXT NOT NULL, " + C1567l.use_duration.name() + " TEXT NOT NULL, " + C1567l.extra.name() + " TEXT" + ");";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "CREATE TABLE PushBehavior (" + C1559d.actionId.name() + " INTEGER PRIMARY KEY AUTOINCREMENT, " + C1559d.actionName.name() + " TEXT NOT NULL, " + C1559d.timeStamp.name() + " LONG  NOT NULL, " + C1559d.networkStatus.name() + " TEXT, " + C1559d.msgType.name() + " INTEGER, " + C1559d.msgId.name() + " TEXT, " + C1559d.msgLen.name() + " INTEGER, " + C1559d.errorMsg.name() + " TEXT, " + C1559d.requestId.name() + " TEXT, " + C1559d.stableHeartInterval.name() + " INTEGER, " + C1559d.errorCode.name() + " INTEGER, " + C1559d.appid.name() + " TEXT, " + C1559d.channel.name() + " TEXT, " + C1559d.openByPackageName.name() + " Text" + ");";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "CREATE TABLE ADPushBehavior (" + C1556a.actionId.name() + " INTEGER PRIMARY KEY AUTOINCREMENT, " + C1556a.actionName.name() + " TEXT NOT NULL, " + C1556a.timeStamp.name() + " LONG  NOT NULL, " + C1556a.networkStatus.name() + " TEXT, " + C1556a.msgType.name() + " INTEGER, " + C1556a.msgId.name() + " TEXT, " + C1556a.msgLen.name() + " INTEGER, " + C1556a.advertiseStyle.name() + " TEXT, " + C1556a.errorCode.name() + " INTEGER, " + C1556a.appid.name() + " TEXT, " + C1556a.actionType.name() + " TEXT" + ");";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "CREATE TABLE MsgInfo (" + C1565j.MsgInfoId.name() + " INTEGER PRIMARY KEY AUTOINCREMENT, " + C1565j.msgId.name() + " TEXT NOT NULL, " + C1565j.timeStamp.name() + " LONG NOT NULL" + ");";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "CREATE TABLE AlarmMsgInfo (" + C1557b.alarmMsgInfoId.name() + " INTEGER PRIMARY KEY AUTOINCREMENT, " + C1557b.msgId.name() + " TEXT NOT NULL, " + C1557b.sendtime.name() + " LONG NOT NULL, " + C1557b.showtime.name() + " LONG NOT NULL, " + C1557b.expiretime.name() + " LONG NOT NULL, " + C1557b.msgEnable.name() + " INTEGER, " + C1557b.isAlarm.name() + " INTEGER" + ");";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "CREATE TABLE AppInfo (" + C1558c.appInfoId.name() + " INTEGER PRIMARY KEY AUTOINCREMENT, " + C1558c.appid.name() + " TEXT UNIQUE, " + C1558c.appType.name() + " INTEGER, " + C1558c.rsaUserId.name() + " TEXT, " + C1558c.userId.name() + " TEXT, " + C1558c.packageName.name() + " TEXT, " + C1558c.appName.name() + " TEXT, " + C1558c.cFrom.name() + " TEXT, " + C1558c.versionCode.name() + " TEXT, " + C1558c.versionName.name() + " TEXT, " + C1558c.intergratedPushVersion.name() + " TEXT" + ");";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "CREATE TABLE LappMsgInfo (" + C1564i.lappMsgId.name() + " INTEGER PRIMARY KEY AUTOINCREMENT, " + C1564i.appid.name() + " TEXT NOT NULL, " + C1564i.title.name() + " TEXT, " + C1564i.description.name() + " TEXT, " + C1564i.url.name() + " TEXT, " + C1564i.timestamp.name() + " LONG NOT NULL, " + C1564i.visited.name() + " INTEGER" + ");";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "CREATE TABLE FileDownloadingInfo (" + C1562g.belongTo.name() + " TEXT, " + C1562g.downloadUrl.name() + " TEXT PRIMARY KEY, " + C1562g.savePath.name() + " TEXT NOT NULL, " + C1562g.title.name() + " TEXT, " + C1562g.description.name() + " TEXT, " + C1562g.fileName.name() + " TEXT NOT NULL, " + C1562g.downloadBytes.name() + " INTEGER NOT NULL, " + C1562g.totalBytes.name() + " INTEGER NOT NULL, " + C1562g.downloadStatus.name() + " INTEGER NOT NULL," + C1562g.timeStamp.name() + " INTEGER NOT NULL" + ");";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                String str2 = "CREATE TABLE NoDisturb (" + C1566k.pkgName.name() + " TEXT NOT NULL, " + C1566k.startHour.name() + " INTEGER, " + C1566k.startMinute.name() + " INTEGER, " + C1566k.endHour.name() + " INTEGER, " + C1566k.endMinute.name() + " INTEGER" + ");";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str2);
                } else {
                    sQLiteDatabase.execSQL(str2);
                }
            } catch (Exception e) {
                C1425a.m6442c("PushDatabase", "DbOpenHelper onCreate E: " + e);
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            m6979a(sQLiteDatabase);
            onCreate(sQLiteDatabase);
        }
    }

    /* renamed from: com.baidu.android.pushservice.util.q$g */
    enum C1562g {
        belongTo,
        downloadUrl,
        title,
        description,
        savePath,
        fileName,
        downloadBytes,
        totalBytes,
        downloadStatus,
        timeStamp
    }

    /* renamed from: com.baidu.android.pushservice.util.q$h */
    public static class C1563h {
        /* renamed from: a */
        public String f5472a;
        /* renamed from: b */
        public String f5473b;
        /* renamed from: c */
        public String f5474c;
        /* renamed from: d */
        public String f5475d;
        /* renamed from: e */
        public String f5476e;
        /* renamed from: f */
        public String f5477f;
        /* renamed from: g */
        public int f5478g;
        /* renamed from: h */
        public int f5479h;
        /* renamed from: i */
        public int f5480i;
        /* renamed from: j */
        public long f5481j;
    }

    /* renamed from: com.baidu.android.pushservice.util.q$i */
    enum C1564i {
        lappMsgId,
        appid,
        title,
        description,
        url,
        timestamp,
        visited
    }

    /* renamed from: com.baidu.android.pushservice.util.q$j */
    enum C1565j {
        MsgInfoId,
        msgId,
        timeStamp
    }

    /* renamed from: com.baidu.android.pushservice.util.q$k */
    enum C1566k {
        pkgName,
        startHour,
        startMinute,
        endHour,
        endMinute
    }

    /* renamed from: com.baidu.android.pushservice.util.q$l */
    enum C1567l {
        info_id,
        packageName,
        open_type,
        msgid,
        app_open_time,
        app_close_time,
        use_duration,
        extra
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: a */
    public static int m6980a(android.content.Context r11, long r12, long r14) {
        /*
        r4 = 0;
        r3 = 0;
        r6 = f5511c;
        monitor-enter(r6);
        r2 = com.baidu.android.pushservice.util.C1568q.m7009e(r11);	 Catch:{ all -> 0x00cb }
        if (r2 != 0) goto L_0x000e;
    L_0x000b:
        monitor-exit(r6);	 Catch:{ all -> 0x00cb }
        r1 = r3;
    L_0x000d:
        return r1;
    L_0x000e:
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00cb }
        r1.<init>();	 Catch:{ all -> 0x00cb }
        r5 = "SELECT COUNT(*) FROM PushBehavior WHERE ";
        r1 = r1.append(r5);	 Catch:{ all -> 0x00cb }
        r5 = com.baidu.android.pushservice.util.C1568q.C1559d.timeStamp;	 Catch:{ all -> 0x00cb }
        r5 = r5.name();	 Catch:{ all -> 0x00cb }
        r1 = r1.append(r5);	 Catch:{ all -> 0x00cb }
        r5 = " < ";
        r1 = r1.append(r5);	 Catch:{ all -> 0x00cb }
        r1 = r1.append(r12);	 Catch:{ all -> 0x00cb }
        r5 = " AND ";
        r1 = r1.append(r5);	 Catch:{ all -> 0x00cb }
        r5 = com.baidu.android.pushservice.util.C1568q.C1559d.timeStamp;	 Catch:{ all -> 0x00cb }
        r5 = r5.name();	 Catch:{ all -> 0x00cb }
        r1 = r1.append(r5);	 Catch:{ all -> 0x00cb }
        r5 = " >= ";
        r1 = r1.append(r5);	 Catch:{ all -> 0x00cb }
        r1 = r1.append(r14);	 Catch:{ all -> 0x00cb }
        r5 = " ;";
        r1 = r1.append(r5);	 Catch:{ all -> 0x00cb }
        r5 = r1.toString();	 Catch:{ all -> 0x00cb }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00cb }
        r1.<init>();	 Catch:{ all -> 0x00cb }
        r7 = "SELECT COUNT(*) FROM ADPushBehavior WHERE ";
        r1 = r1.append(r7);	 Catch:{ all -> 0x00cb }
        r7 = com.baidu.android.pushservice.util.C1568q.C1556a.timeStamp;	 Catch:{ all -> 0x00cb }
        r7 = r7.name();	 Catch:{ all -> 0x00cb }
        r1 = r1.append(r7);	 Catch:{ all -> 0x00cb }
        r7 = " < ";
        r1 = r1.append(r7);	 Catch:{ all -> 0x00cb }
        r1 = r1.append(r12);	 Catch:{ all -> 0x00cb }
        r7 = " AND ";
        r1 = r1.append(r7);	 Catch:{ all -> 0x00cb }
        r7 = com.baidu.android.pushservice.util.C1568q.C1556a.timeStamp;	 Catch:{ all -> 0x00cb }
        r7 = r7.name();	 Catch:{ all -> 0x00cb }
        r1 = r1.append(r7);	 Catch:{ all -> 0x00cb }
        r7 = " >= ";
        r1 = r1.append(r7);	 Catch:{ all -> 0x00cb }
        r1 = r1.append(r14);	 Catch:{ all -> 0x00cb }
        r7 = " ;";
        r1 = r1.append(r7);	 Catch:{ all -> 0x00cb }
        r7 = r1.toString();	 Catch:{ all -> 0x00cb }
        r8 = 0;
        r1 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00d7 }
        if (r1 != 0) goto L_0x00ce;
    L_0x0099:
        r4 = r2.rawQuery(r5, r8);	 Catch:{ Exception -> 0x00d7 }
    L_0x009d:
        r4.moveToFirst();	 Catch:{ Exception -> 0x00d7 }
        r1 = 0;
        r1 = r4.getInt(r1);	 Catch:{ Exception -> 0x00d7 }
        if (r4 == 0) goto L_0x0142;
    L_0x00a7:
        r4.close();	 Catch:{ all -> 0x00cb }
        r5 = r1;
    L_0x00ab:
        r8 = 0;
        r1 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x010b }
        if (r1 != 0) goto L_0x0102;
    L_0x00b0:
        r4 = r2.rawQuery(r7, r8);	 Catch:{ Exception -> 0x010b }
    L_0x00b4:
        r4.moveToFirst();	 Catch:{ Exception -> 0x010b }
        r1 = 0;
        r3 = r4.getInt(r1);	 Catch:{ Exception -> 0x010b }
        if (r4 == 0) goto L_0x00c1;
    L_0x00be:
        r4.close();	 Catch:{ all -> 0x00cb }
    L_0x00c1:
        if (r2 == 0) goto L_0x00c6;
    L_0x00c3:
        r2.close();	 Catch:{ all -> 0x00cb }
    L_0x00c6:
        r1 = r5 + r3;
        monitor-exit(r6);	 Catch:{ all -> 0x00cb }
        goto L_0x000d;
    L_0x00cb:
        r1 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x00cb }
        throw r1;
    L_0x00ce:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00d7 }
        r1 = r0;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.rawQuery(r1, r5, r8);	 Catch:{ Exception -> 0x00d7 }
        goto L_0x009d;
    L_0x00d7:
        r1 = move-exception;
        r5 = "PushDatabase";
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00fb }
        r8.<init>();	 Catch:{ all -> 0x00fb }
        r9 = "e getBehaviorEnumCount";
        r8 = r8.append(r9);	 Catch:{ all -> 0x00fb }
        r1 = r1.getMessage();	 Catch:{ all -> 0x00fb }
        r1 = r8.append(r1);	 Catch:{ all -> 0x00fb }
        r1 = r1.toString();	 Catch:{ all -> 0x00fb }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r5, r1);	 Catch:{ all -> 0x00fb }
        if (r4 == 0) goto L_0x013f;
    L_0x00f6:
        r4.close();	 Catch:{ all -> 0x00cb }
        r5 = r3;
        goto L_0x00ab;
    L_0x00fb:
        r1 = move-exception;
        if (r4 == 0) goto L_0x0101;
    L_0x00fe:
        r4.close();	 Catch:{ all -> 0x00cb }
    L_0x0101:
        throw r1;	 Catch:{ all -> 0x00cb }
    L_0x0102:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x010b }
        r1 = r0;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.rawQuery(r1, r7, r8);	 Catch:{ Exception -> 0x010b }
        goto L_0x00b4;
    L_0x010b:
        r1 = move-exception;
        r7 = "PushDatabase";
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0133 }
        r8.<init>();	 Catch:{ all -> 0x0133 }
        r9 = "e getBehaviorEnumCount 2";
        r8 = r8.append(r9);	 Catch:{ all -> 0x0133 }
        r1 = r1.getMessage();	 Catch:{ all -> 0x0133 }
        r1 = r8.append(r1);	 Catch:{ all -> 0x0133 }
        r1 = r1.toString();	 Catch:{ all -> 0x0133 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r7, r1);	 Catch:{ all -> 0x0133 }
        if (r4 == 0) goto L_0x012d;
    L_0x012a:
        r4.close();	 Catch:{ all -> 0x00cb }
    L_0x012d:
        if (r2 == 0) goto L_0x00c6;
    L_0x012f:
        r2.close();	 Catch:{ all -> 0x00cb }
        goto L_0x00c6;
    L_0x0133:
        r1 = move-exception;
        if (r4 == 0) goto L_0x0139;
    L_0x0136:
        r4.close();	 Catch:{ all -> 0x00cb }
    L_0x0139:
        if (r2 == 0) goto L_0x013e;
    L_0x013b:
        r2.close();	 Catch:{ all -> 0x00cb }
    L_0x013e:
        throw r1;	 Catch:{ all -> 0x00cb }
    L_0x013f:
        r5 = r3;
        goto L_0x00ab;
    L_0x0142:
        r5 = r1;
        goto L_0x00ab;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1568q.m6980a(android.content.Context, long, long):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:57:0x010e A:{Catch:{ Exception -> 0x0119, all -> 0x0109 }} */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0113 A:{Catch:{ Exception -> 0x0119, all -> 0x0109 }} */
    /* JADX WARNING: Missing block: B:67:?, code skipped:
            return -2;
     */
    /* renamed from: a */
    public static int m6981a(android.content.Context r20, java.lang.String r21, int r22) {
        /*
        r19 = f5511c;
        monitor-enter(r19);
        r18 = 0;
        r1 = com.baidu.android.pushservice.util.C1568q.m7009e(r20);	 Catch:{ all -> 0x00eb }
        if (r1 != 0) goto L_0x000e;
    L_0x000b:
        r1 = -1;
        monitor-exit(r19);	 Catch:{ all -> 0x00eb }
    L_0x000d:
        return r1;
    L_0x000e:
        r17 = 0;
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0119, all -> 0x0109 }
        r2.<init>();	 Catch:{ Exception -> 0x0119, all -> 0x0109 }
        r3 = com.baidu.android.pushservice.util.C1568q.C1557b.msgId;	 Catch:{ Exception -> 0x0119, all -> 0x0109 }
        r3 = r3.name();	 Catch:{ Exception -> 0x0119, all -> 0x0109 }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0119, all -> 0x0109 }
        r3 = " = ";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0119, all -> 0x0109 }
        r0 = r21;
        r2 = r2.append(r0);	 Catch:{ Exception -> 0x0119, all -> 0x0109 }
        r3 = ";";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0119, all -> 0x0109 }
        r4 = r2.toString();	 Catch:{ Exception -> 0x0119, all -> 0x0109 }
        r2 = "AlarmMsgInfo";
        r3 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0119, all -> 0x0109 }
        if (r9 != 0) goto L_0x005b;
    L_0x0040:
        r3 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0119, all -> 0x0109 }
    L_0x0044:
        if (r3 != 0) goto L_0x006c;
    L_0x0046:
        r2 = "PushDatabase";
        r4 = "no msgid info table!!";
        com.baidu.android.pushservice.p036h.C1425a.m6443d(r2, r4);	 Catch:{ Exception -> 0x00f6 }
        r2 = -2;
        if (r3 == 0) goto L_0x0053;
    L_0x0050:
        r3.close();	 Catch:{ all -> 0x00eb }
    L_0x0053:
        if (r1 == 0) goto L_0x0058;
    L_0x0055:
        r1.close();	 Catch:{ all -> 0x00eb }
    L_0x0058:
        monitor-exit(r19);	 Catch:{ all -> 0x00eb }
        r1 = r2;
        goto L_0x000d;
    L_0x005b:
        r0 = r1;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0119, all -> 0x0109 }
        r9 = r0;
        r10 = r2;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r3 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r9, r10, r11, r12, r13, r14, r15, r16);	 Catch:{ Exception -> 0x0119, all -> 0x0109 }
        goto L_0x0044;
    L_0x006c:
        r2 = r3.getCount();	 Catch:{ Exception -> 0x00f6 }
        if (r2 <= 0) goto L_0x00dc;
    L_0x0072:
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00f6 }
        r2.<init>();	 Catch:{ Exception -> 0x00f6 }
        r4 = "UPDATE AlarmMsgInfo SET ";
        r2 = r2.append(r4);	 Catch:{ Exception -> 0x00f6 }
        r4 = com.baidu.android.pushservice.util.C1568q.C1557b.msgEnable;	 Catch:{ Exception -> 0x00f6 }
        r4 = r4.name();	 Catch:{ Exception -> 0x00f6 }
        r2 = r2.append(r4);	 Catch:{ Exception -> 0x00f6 }
        r4 = " = ";
        r2 = r2.append(r4);	 Catch:{ Exception -> 0x00f6 }
        r0 = r22;
        r2 = r2.append(r0);	 Catch:{ Exception -> 0x00f6 }
        r4 = " WHERE ";
        r2 = r2.append(r4);	 Catch:{ Exception -> 0x00f6 }
        r4 = com.baidu.android.pushservice.util.C1568q.C1557b.msgId;	 Catch:{ Exception -> 0x00f6 }
        r2 = r2.append(r4);	 Catch:{ Exception -> 0x00f6 }
        r4 = " = ";
        r2 = r2.append(r4);	 Catch:{ Exception -> 0x00f6 }
        r0 = r21;
        r2 = r2.append(r0);	 Catch:{ Exception -> 0x00f6 }
        r4 = r2.toString();	 Catch:{ Exception -> 0x00f6 }
        r2 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00f6 }
        if (r2 != 0) goto L_0x00ee;
    L_0x00b3:
        r1.execSQL(r4);	 Catch:{ Exception -> 0x00f6 }
    L_0x00b6:
        r2 = "PushDatabase";
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00f6 }
        r4.<init>();	 Catch:{ Exception -> 0x00f6 }
        r5 = "setAlarmMsgEnable  msgID = ";
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x00f6 }
        r0 = r21;
        r4 = r4.append(r0);	 Catch:{ Exception -> 0x00f6 }
        r5 = "  enable = ";
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x00f6 }
        r0 = r22;
        r4 = r4.append(r0);	 Catch:{ Exception -> 0x00f6 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x00f6 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r2, r4);	 Catch:{ Exception -> 0x00f6 }
    L_0x00dc:
        if (r3 == 0) goto L_0x00e1;
    L_0x00de:
        r3.close();	 Catch:{ all -> 0x00eb }
    L_0x00e1:
        if (r1 == 0) goto L_0x011f;
    L_0x00e3:
        r1.close();	 Catch:{ all -> 0x00eb }
        r1 = r18;
    L_0x00e8:
        monitor-exit(r19);	 Catch:{ all -> 0x00eb }
        goto L_0x000d;
    L_0x00eb:
        r1 = move-exception;
        monitor-exit(r19);	 Catch:{ all -> 0x00eb }
        throw r1;
    L_0x00ee:
        r0 = r1;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00f6 }
        r2 = r0;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.execSQL(r2, r4);	 Catch:{ Exception -> 0x00f6 }
        goto L_0x00b6;
    L_0x00f6:
        r2 = move-exception;
    L_0x00f7:
        r4 = -3;
        r5 = "PushDatabase";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r5, r2);	 Catch:{ all -> 0x0117 }
        if (r3 == 0) goto L_0x0102;
    L_0x00ff:
        r3.close();	 Catch:{ all -> 0x00eb }
    L_0x0102:
        if (r1 == 0) goto L_0x011d;
    L_0x0104:
        r1.close();	 Catch:{ all -> 0x00eb }
        r1 = r4;
        goto L_0x00e8;
    L_0x0109:
        r2 = move-exception;
        r3 = r17;
    L_0x010c:
        if (r3 == 0) goto L_0x0111;
    L_0x010e:
        r3.close();	 Catch:{ all -> 0x00eb }
    L_0x0111:
        if (r1 == 0) goto L_0x0116;
    L_0x0113:
        r1.close();	 Catch:{ all -> 0x00eb }
    L_0x0116:
        throw r2;	 Catch:{ all -> 0x00eb }
    L_0x0117:
        r2 = move-exception;
        goto L_0x010c;
    L_0x0119:
        r2 = move-exception;
        r3 = r17;
        goto L_0x00f7;
    L_0x011d:
        r1 = r4;
        goto L_0x00e8;
    L_0x011f:
        r1 = r18;
        goto L_0x00e8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1568q.m6981a(android.content.Context, java.lang.String, int):int");
    }

    /* renamed from: a */
    public static int m6982a(Context context, String str, C1563h c1563h) {
        int i = 0;
        synchronized (f5511c) {
            SQLiteDatabase e = C1568q.m7009e(context);
            if (e == null) {
            } else {
                String[] strArr = new String[]{str};
                ContentValues contentValues = new ContentValues();
                contentValues.put(C1562g.belongTo.name(), c1563h.f5472a);
                contentValues.put(C1562g.downloadUrl.name(), c1563h.f5473b);
                contentValues.put(C1562g.title.name(), c1563h.f5474c);
                contentValues.put(C1562g.description.name(), c1563h.f5475d);
                contentValues.put(C1562g.savePath.name(), c1563h.f5476e);
                contentValues.put(C1562g.fileName.name(), c1563h.f5477f);
                contentValues.put(C1562g.downloadBytes.name(), Integer.valueOf(c1563h.f5478g));
                contentValues.put(C1562g.totalBytes.name(), Integer.valueOf(c1563h.f5479h));
                contentValues.put(C1562g.downloadStatus.name(), Integer.valueOf(c1563h.f5480i));
                contentValues.put(C1562g.timeStamp.name(), Long.valueOf(System.currentTimeMillis()));
                try {
                    String str2 = "FileDownloadingInfo";
                    String str3 = C1562g.downloadUrl.name() + "=?";
                    i = !(e instanceof SQLiteDatabase) ? e.update(str2, contentValues, str3, strArr) : SQLiteInstrumentation.update(e, str2, contentValues, str3, strArr);
                } catch (Exception e2) {
                    C1425a.m6444e("PushDatabase", "updateFileDownloadingInfo Exception: " + e2);
                    i = -1;
                }
                e.close();
            }
        }
        return i;
    }

    /* renamed from: a */
    private static int m6983a(SQLiteDatabase sQLiteDatabase, int i, C1449n c1449n) {
        if (sQLiteDatabase == null) {
            return 0;
        }
        String[] strArr = new String[]{"" + i};
        ContentValues contentValues = new ContentValues();
        contentValues.put(C1558c.appInfoId.name(), Integer.valueOf(i));
        contentValues.put(C1558c.appid.name(), c1449n.mo13852a());
        contentValues.put(C1558c.appType.name(), Integer.valueOf(c1449n.mo13919j()));
        contentValues.put(C1558c.rsaUserId.name(), c1449n.mo13855b());
        contentValues.put(C1558c.userId.name(), c1449n.mo13858c());
        contentValues.put(C1558c.packageName.name(), c1449n.mo13860d());
        contentValues.put(C1558c.appName.name(), c1449n.mo13862e());
        if (!TextUtils.isEmpty(c1449n.mo13864f())) {
            contentValues.put(C1558c.cFrom.name(), c1449n.mo13864f());
        }
        contentValues.put(C1558c.versionCode.name(), Integer.valueOf(c1449n.mo13866g()));
        contentValues.put(C1558c.versionName.name(), c1449n.mo13868h());
        contentValues.put(C1558c.intergratedPushVersion.name(), Integer.valueOf(c1449n.mo13869i()));
        try {
            String str = "AppInfo";
            String str2 = C1558c.appInfoId.name() + "=?";
            return !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.update(str, contentValues, str2, strArr) : SQLiteInstrumentation.update(sQLiteDatabase, str, contentValues, str2, strArr);
        } catch (Exception e) {
            C1425a.m6444e("PushDatabase", "updateAppInfo exception " + e);
            return -1;
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: a */
    public static long m6984a(android.content.Context r10, com.baidu.android.pushservice.p037i.C1436b r11) {
        /*
        r4 = -1;
        r6 = f5511c;
        monitor-enter(r6);
        r3 = com.baidu.android.pushservice.util.C1568q.m7009e(r10);	 Catch:{ all -> 0x00cf }
        if (r3 != 0) goto L_0x000e;
    L_0x000b:
        monitor-exit(r6);	 Catch:{ all -> 0x00cf }
        r2 = r4;
    L_0x000d:
        return r2;
    L_0x000e:
        r7 = new android.content.ContentValues;	 Catch:{ all -> 0x00cf }
        r7.<init>();	 Catch:{ all -> 0x00cf }
        r2 = com.baidu.android.pushservice.util.C1568q.C1556a.actionName;	 Catch:{ all -> 0x00cf }
        r2 = r2.name();	 Catch:{ all -> 0x00cf }
        r8 = r11.f5036f;	 Catch:{ all -> 0x00cf }
        r7.put(r2, r8);	 Catch:{ all -> 0x00cf }
        r2 = com.baidu.android.pushservice.util.C1568q.C1556a.timeStamp;	 Catch:{ all -> 0x00cf }
        r2 = r2.name();	 Catch:{ all -> 0x00cf }
        r8 = r11.f5037g;	 Catch:{ all -> 0x00cf }
        r8 = java.lang.Long.valueOf(r8);	 Catch:{ all -> 0x00cf }
        r7.put(r2, r8);	 Catch:{ all -> 0x00cf }
        r2 = com.baidu.android.pushservice.util.C1568q.C1556a.networkStatus;	 Catch:{ all -> 0x00cf }
        r2 = r2.name();	 Catch:{ all -> 0x00cf }
        r8 = r11.f5038h;	 Catch:{ all -> 0x00cf }
        r7.put(r2, r8);	 Catch:{ all -> 0x00cf }
        r2 = r11.f5040j;	 Catch:{ all -> 0x00cf }
        if (r2 == 0) goto L_0x0047;
    L_0x003c:
        r2 = com.baidu.android.pushservice.util.C1568q.C1556a.appid;	 Catch:{ all -> 0x00cf }
        r2 = r2.name();	 Catch:{ all -> 0x00cf }
        r8 = r11.f5040j;	 Catch:{ all -> 0x00cf }
        r7.put(r2, r8);	 Catch:{ all -> 0x00cf }
    L_0x0047:
        r2 = com.baidu.android.pushservice.util.C1568q.C1556a.advertiseStyle;	 Catch:{ all -> 0x00cf }
        r2 = r2.name();	 Catch:{ all -> 0x00cf }
        r8 = r11.f5055d;	 Catch:{ all -> 0x00cf }
        r7.put(r2, r8);	 Catch:{ all -> 0x00cf }
        r2 = com.baidu.android.pushservice.util.C1568q.C1556a.msgType;	 Catch:{ all -> 0x00cf }
        r2 = r2.name();	 Catch:{ all -> 0x00cf }
        r8 = r11.f5054c;	 Catch:{ all -> 0x00cf }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ all -> 0x00cf }
        r7.put(r2, r8);	 Catch:{ all -> 0x00cf }
        r2 = com.baidu.android.pushservice.util.C1568q.C1556a.msgId;	 Catch:{ all -> 0x00cf }
        r2 = r2.name();	 Catch:{ all -> 0x00cf }
        r8 = r11.f5052a;	 Catch:{ all -> 0x00cf }
        r7.put(r2, r8);	 Catch:{ all -> 0x00cf }
        r2 = com.baidu.android.pushservice.util.C1568q.C1556a.msgLen;	 Catch:{ all -> 0x00cf }
        r2 = r2.name();	 Catch:{ all -> 0x00cf }
        r8 = r11.f5053b;	 Catch:{ all -> 0x00cf }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ all -> 0x00cf }
        r7.put(r2, r8);	 Catch:{ all -> 0x00cf }
        r2 = com.baidu.android.pushservice.util.C1568q.C1556a.errorCode;	 Catch:{ all -> 0x00cf }
        r2 = r2.name();	 Catch:{ all -> 0x00cf }
        r8 = r11.f5039i;	 Catch:{ all -> 0x00cf }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ all -> 0x00cf }
        r7.put(r2, r8);	 Catch:{ all -> 0x00cf }
        r2 = com.baidu.android.pushservice.util.C1568q.C1556a.actionType;	 Catch:{ all -> 0x00cf }
        r2 = r2.name();	 Catch:{ all -> 0x00cf }
        r8 = r11.f5056e;	 Catch:{ all -> 0x00cf }
        r7.put(r2, r8);	 Catch:{ all -> 0x00cf }
        r8 = "ADPushBehavior";
        r9 = 0;
        r2 = r3 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00b5 }
        if (r2 != 0) goto L_0x00ac;
    L_0x009c:
        r4 = r3.insert(r8, r9, r7);	 Catch:{ Exception -> 0x00b5 }
    L_0x00a0:
        r2 = "pushadvertise:  insert into database";
        com.baidu.android.pushservice.util.C1578v.m7095b(r2, r10);	 Catch:{ Exception -> 0x00b5 }
    L_0x00a5:
        r3.close();	 Catch:{ all -> 0x00cf }
        monitor-exit(r6);	 Catch:{ all -> 0x00cf }
        r2 = r4;
        goto L_0x000d;
    L_0x00ac:
        r0 = r3;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00b5 }
        r2 = r0;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.insert(r2, r8, r9, r7);	 Catch:{ Exception -> 0x00b5 }
        goto L_0x00a0;
    L_0x00b5:
        r2 = move-exception;
        r7 = "PushDatabase";
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00cf }
        r8.<init>();	 Catch:{ all -> 0x00cf }
        r9 = "exception ";
        r8 = r8.append(r9);	 Catch:{ all -> 0x00cf }
        r2 = r8.append(r2);	 Catch:{ all -> 0x00cf }
        r2 = r2.toString();	 Catch:{ all -> 0x00cf }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r7, r2);	 Catch:{ all -> 0x00cf }
        goto L_0x00a5;
    L_0x00cf:
        r2 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x00cf }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1568q.m6984a(android.content.Context, com.baidu.android.pushservice.i.b):long");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: a */
    public static long m6985a(android.content.Context r10, com.baidu.android.pushservice.p037i.C1437c r11) {
        /*
        r4 = -1;
        r6 = f5511c;
        monitor-enter(r6);
        r3 = com.baidu.android.pushservice.util.C1568q.m7009e(r10);	 Catch:{ all -> 0x0096 }
        if (r3 != 0) goto L_0x000e;
    L_0x000b:
        monitor-exit(r6);	 Catch:{ all -> 0x0096 }
        r2 = r4;
    L_0x000d:
        return r2;
    L_0x000e:
        r7 = new android.content.ContentValues;	 Catch:{ all -> 0x0096 }
        r7.<init>();	 Catch:{ all -> 0x0096 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1557b.msgId;	 Catch:{ all -> 0x0096 }
        r2 = r2.name();	 Catch:{ all -> 0x0096 }
        r8 = r11.f5057a;	 Catch:{ all -> 0x0096 }
        r7.put(r2, r8);	 Catch:{ all -> 0x0096 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1557b.sendtime;	 Catch:{ all -> 0x0096 }
        r2 = r2.name();	 Catch:{ all -> 0x0096 }
        r8 = r11.f5058b;	 Catch:{ all -> 0x0096 }
        r8 = java.lang.Long.valueOf(r8);	 Catch:{ all -> 0x0096 }
        r7.put(r2, r8);	 Catch:{ all -> 0x0096 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1557b.showtime;	 Catch:{ all -> 0x0096 }
        r2 = r2.name();	 Catch:{ all -> 0x0096 }
        r8 = r11.f5059c;	 Catch:{ all -> 0x0096 }
        r8 = java.lang.Long.valueOf(r8);	 Catch:{ all -> 0x0096 }
        r7.put(r2, r8);	 Catch:{ all -> 0x0096 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1557b.expiretime;	 Catch:{ all -> 0x0096 }
        r2 = r2.name();	 Catch:{ all -> 0x0096 }
        r8 = r11.f5060d;	 Catch:{ all -> 0x0096 }
        r8 = java.lang.Long.valueOf(r8);	 Catch:{ all -> 0x0096 }
        r7.put(r2, r8);	 Catch:{ all -> 0x0096 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1557b.isAlarm;	 Catch:{ all -> 0x0096 }
        r2 = r2.name();	 Catch:{ all -> 0x0096 }
        r8 = r11.f5061e;	 Catch:{ all -> 0x0096 }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ all -> 0x0096 }
        r7.put(r2, r8);	 Catch:{ all -> 0x0096 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1557b.msgEnable;	 Catch:{ all -> 0x0096 }
        r2 = r2.name();	 Catch:{ all -> 0x0096 }
        r8 = r11.f5062f;	 Catch:{ all -> 0x0096 }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ all -> 0x0096 }
        r7.put(r2, r8);	 Catch:{ all -> 0x0096 }
        r8 = "AlarmMsgInfo";
        r9 = 0;
        r2 = r3 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x008f }
        if (r2 != 0) goto L_0x0086;
    L_0x0070:
        r4 = r3.insert(r8, r9, r7);	 Catch:{ Exception -> 0x008f }
    L_0x0074:
        r2 = "PushDatabase";
        r7 = "AlarmMsgInfoEnum:  insert into database";
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r2, r7);	 Catch:{ Exception -> 0x008f }
        r2 = "AlarmMsgInfoEnum:  insert into database";
        com.baidu.android.pushservice.util.C1578v.m7095b(r2, r10);	 Catch:{ Exception -> 0x008f }
    L_0x0080:
        r3.close();	 Catch:{ all -> 0x0096 }
        monitor-exit(r6);	 Catch:{ all -> 0x0096 }
        r2 = r4;
        goto L_0x000d;
    L_0x0086:
        r0 = r3;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x008f }
        r2 = r0;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.insert(r2, r8, r9, r7);	 Catch:{ Exception -> 0x008f }
        goto L_0x0074;
    L_0x008f:
        r2 = move-exception;
        r7 = "PushDatabase";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r7, r2);	 Catch:{ all -> 0x0096 }
        goto L_0x0080;
    L_0x0096:
        r2 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0096 }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1568q.m6985a(android.content.Context, com.baidu.android.pushservice.i.c):long");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: a */
    public static long m6986a(android.content.Context r10, com.baidu.android.pushservice.p037i.C1438d r11) {
        /*
        r6 = f5511c;
        monitor-enter(r6);
        r3 = com.baidu.android.pushservice.util.C1568q.m7009e(r10);	 Catch:{ all -> 0x00ac }
        if (r3 != 0) goto L_0x000d;
    L_0x0009:
        r2 = -1;
        monitor-exit(r6);	 Catch:{ all -> 0x00ac }
    L_0x000c:
        return r2;
    L_0x000d:
        r7 = new android.content.ContentValues;	 Catch:{ all -> 0x00ac }
        r7.<init>();	 Catch:{ all -> 0x00ac }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.actionName;	 Catch:{ all -> 0x00ac }
        r2 = r2.name();	 Catch:{ all -> 0x00ac }
        r4 = r11.f5036f;	 Catch:{ all -> 0x00ac }
        r7.put(r2, r4);	 Catch:{ all -> 0x00ac }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.timeStamp;	 Catch:{ all -> 0x00ac }
        r2 = r2.name();	 Catch:{ all -> 0x00ac }
        r4 = r11.f5037g;	 Catch:{ all -> 0x00ac }
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ all -> 0x00ac }
        r7.put(r2, r4);	 Catch:{ all -> 0x00ac }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.networkStatus;	 Catch:{ all -> 0x00ac }
        r2 = r2.name();	 Catch:{ all -> 0x00ac }
        r4 = r11.f5038h;	 Catch:{ all -> 0x00ac }
        r7.put(r2, r4);	 Catch:{ all -> 0x00ac }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.appid;	 Catch:{ all -> 0x00ac }
        r2 = r2.name();	 Catch:{ all -> 0x00ac }
        r4 = r11.f5040j;	 Catch:{ all -> 0x00ac }
        r7.put(r2, r4);	 Catch:{ all -> 0x00ac }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.errorMsg;	 Catch:{ all -> 0x00ac }
        r2 = r2.name();	 Catch:{ all -> 0x00ac }
        r4 = r11.f5063a;	 Catch:{ all -> 0x00ac }
        r7.put(r2, r4);	 Catch:{ all -> 0x00ac }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.requestId;	 Catch:{ all -> 0x00ac }
        r2 = r2.name();	 Catch:{ all -> 0x00ac }
        r4 = r11.f5064b;	 Catch:{ all -> 0x00ac }
        r7.put(r2, r4);	 Catch:{ all -> 0x00ac }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.errorCode;	 Catch:{ all -> 0x00ac }
        r2 = r2.name();	 Catch:{ all -> 0x00ac }
        r4 = r11.f5039i;	 Catch:{ all -> 0x00ac }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x00ac }
        r7.put(r2, r4);	 Catch:{ all -> 0x00ac }
        r2 = r11.f5065c;	 Catch:{ all -> 0x00ac }
        if (r2 == 0) goto L_0x0076;
    L_0x006b:
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.channel;	 Catch:{ all -> 0x00ac }
        r2 = r2.name();	 Catch:{ all -> 0x00ac }
        r4 = r11.f5065c;	 Catch:{ all -> 0x00ac }
        r7.put(r2, r4);	 Catch:{ all -> 0x00ac }
    L_0x0076:
        r4 = 0;
        r8 = "PushBehavior";
        r9 = 0;
        r2 = r3 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0092 }
        if (r2 != 0) goto L_0x0089;
    L_0x007f:
        r4 = r3.insert(r8, r9, r7);	 Catch:{ Exception -> 0x0092 }
    L_0x0083:
        r3.close();	 Catch:{ all -> 0x00ac }
        monitor-exit(r6);	 Catch:{ all -> 0x00ac }
        r2 = r4;
        goto L_0x000c;
    L_0x0089:
        r0 = r3;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0092 }
        r2 = r0;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.insert(r2, r8, r9, r7);	 Catch:{ Exception -> 0x0092 }
        goto L_0x0083;
    L_0x0092:
        r2 = move-exception;
        r7 = "PushDatabase";
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ac }
        r8.<init>();	 Catch:{ all -> 0x00ac }
        r9 = "insertApiBehavior E: ";
        r8 = r8.append(r9);	 Catch:{ all -> 0x00ac }
        r2 = r8.append(r2);	 Catch:{ all -> 0x00ac }
        r2 = r2.toString();	 Catch:{ all -> 0x00ac }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r7, r2);	 Catch:{ all -> 0x00ac }
        goto L_0x0083;
    L_0x00ac:
        r2 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x00ac }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1568q.m6986a(android.content.Context, com.baidu.android.pushservice.i.d):long");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: a */
    public static long m6987a(android.content.Context r10, com.baidu.android.pushservice.p037i.C1441h r11) {
        /*
        r6 = f5511c;
        monitor-enter(r6);
        r3 = com.baidu.android.pushservice.util.C1568q.m7009e(r10);	 Catch:{ all -> 0x0083 }
        if (r3 != 0) goto L_0x000d;
    L_0x0009:
        r2 = -1;
        monitor-exit(r6);	 Catch:{ all -> 0x0083 }
    L_0x000c:
        return r2;
    L_0x000d:
        r7 = new android.content.ContentValues;	 Catch:{ all -> 0x0083 }
        r7.<init>();	 Catch:{ all -> 0x0083 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.actionName;	 Catch:{ all -> 0x0083 }
        r2 = r2.name();	 Catch:{ all -> 0x0083 }
        r4 = r11.f5036f;	 Catch:{ all -> 0x0083 }
        r7.put(r2, r4);	 Catch:{ all -> 0x0083 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.timeStamp;	 Catch:{ all -> 0x0083 }
        r2 = r2.name();	 Catch:{ all -> 0x0083 }
        r4 = r11.f5037g;	 Catch:{ all -> 0x0083 }
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ all -> 0x0083 }
        r7.put(r2, r4);	 Catch:{ all -> 0x0083 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.networkStatus;	 Catch:{ all -> 0x0083 }
        r2 = r2.name();	 Catch:{ all -> 0x0083 }
        r4 = r11.f5038h;	 Catch:{ all -> 0x0083 }
        r7.put(r2, r4);	 Catch:{ all -> 0x0083 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.errorMsg;	 Catch:{ all -> 0x0083 }
        r2 = r2.name();	 Catch:{ all -> 0x0083 }
        r4 = r11.f5089a;	 Catch:{ all -> 0x0083 }
        r7.put(r2, r4);	 Catch:{ all -> 0x0083 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.appid;	 Catch:{ all -> 0x0083 }
        r2 = r2.name();	 Catch:{ all -> 0x0083 }
        r4 = r11.f5040j;	 Catch:{ all -> 0x0083 }
        r7.put(r2, r4);	 Catch:{ all -> 0x0083 }
        r4 = 0;
        r8 = "PushBehavior";
        r9 = 0;
        r2 = r3 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0069 }
        if (r2 != 0) goto L_0x0060;
    L_0x0056:
        r4 = r3.insert(r8, r9, r7);	 Catch:{ Exception -> 0x0069 }
    L_0x005a:
        r3.close();	 Catch:{ all -> 0x0083 }
        monitor-exit(r6);	 Catch:{ all -> 0x0083 }
        r2 = r4;
        goto L_0x000c;
    L_0x0060:
        r0 = r3;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0069 }
        r2 = r0;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.insert(r2, r8, r9, r7);	 Catch:{ Exception -> 0x0069 }
        goto L_0x005a;
    L_0x0069:
        r2 = move-exception;
        r7 = "PushDatabase";
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0083 }
        r8.<init>();	 Catch:{ all -> 0x0083 }
        r9 = "insertCrashBehavior E: ";
        r8 = r8.append(r9);	 Catch:{ all -> 0x0083 }
        r2 = r8.append(r2);	 Catch:{ all -> 0x0083 }
        r2 = r2.toString();	 Catch:{ all -> 0x0083 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r7, r2);	 Catch:{ all -> 0x0083 }
        goto L_0x005a;
    L_0x0083:
        r2 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0083 }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1568q.m6987a(android.content.Context, com.baidu.android.pushservice.i.h):long");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: a */
    public static long m6988a(android.content.Context r10, com.baidu.android.pushservice.p037i.C1446k r11) {
        /*
        r6 = f5511c;
        monitor-enter(r6);
        r3 = com.baidu.android.pushservice.util.C1568q.m7009e(r10);	 Catch:{ all -> 0x00a1 }
        if (r3 != 0) goto L_0x000d;
    L_0x0009:
        r2 = -1;
        monitor-exit(r6);	 Catch:{ all -> 0x00a1 }
    L_0x000c:
        return r2;
    L_0x000d:
        r7 = new android.content.ContentValues;	 Catch:{ all -> 0x00a1 }
        r7.<init>();	 Catch:{ all -> 0x00a1 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.actionName;	 Catch:{ all -> 0x00a1 }
        r2 = r2.name();	 Catch:{ all -> 0x00a1 }
        r4 = r11.f5036f;	 Catch:{ all -> 0x00a1 }
        r7.put(r2, r4);	 Catch:{ all -> 0x00a1 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.timeStamp;	 Catch:{ all -> 0x00a1 }
        r2 = r2.name();	 Catch:{ all -> 0x00a1 }
        r4 = r11.f5037g;	 Catch:{ all -> 0x00a1 }
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ all -> 0x00a1 }
        r7.put(r2, r4);	 Catch:{ all -> 0x00a1 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.networkStatus;	 Catch:{ all -> 0x00a1 }
        r2 = r2.name();	 Catch:{ all -> 0x00a1 }
        r4 = r11.f5038h;	 Catch:{ all -> 0x00a1 }
        r7.put(r2, r4);	 Catch:{ all -> 0x00a1 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.errorMsg;	 Catch:{ all -> 0x00a1 }
        r2 = r2.name();	 Catch:{ all -> 0x00a1 }
        r4 = r11.f5042l;	 Catch:{ all -> 0x00a1 }
        r7.put(r2, r4);	 Catch:{ all -> 0x00a1 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.stableHeartInterval;	 Catch:{ all -> 0x00a1 }
        r2 = r2.name();	 Catch:{ all -> 0x00a1 }
        r4 = r11.f5105a;	 Catch:{ all -> 0x00a1 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x00a1 }
        r7.put(r2, r4);	 Catch:{ all -> 0x00a1 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.errorCode;	 Catch:{ all -> 0x00a1 }
        r2 = r2.name();	 Catch:{ all -> 0x00a1 }
        r4 = r11.f5039i;	 Catch:{ all -> 0x00a1 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x00a1 }
        r7.put(r2, r4);	 Catch:{ all -> 0x00a1 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.appid;	 Catch:{ all -> 0x00a1 }
        r2 = r2.name();	 Catch:{ all -> 0x00a1 }
        r4 = r11.f5040j;	 Catch:{ all -> 0x00a1 }
        r7.put(r2, r4);	 Catch:{ all -> 0x00a1 }
        r4 = 0;
        r8 = "PushBehavior";
        r9 = 0;
        r2 = r3 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0087 }
        if (r2 != 0) goto L_0x007e;
    L_0x0074:
        r4 = r3.insert(r8, r9, r7);	 Catch:{ Exception -> 0x0087 }
    L_0x0078:
        r3.close();	 Catch:{ all -> 0x00a1 }
        monitor-exit(r6);	 Catch:{ all -> 0x00a1 }
        r2 = r4;
        goto L_0x000c;
    L_0x007e:
        r0 = r3;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0087 }
        r2 = r0;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.insert(r2, r8, r9, r7);	 Catch:{ Exception -> 0x0087 }
        goto L_0x0078;
    L_0x0087:
        r2 = move-exception;
        r7 = "PushDatabase";
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00a1 }
        r8.<init>();	 Catch:{ all -> 0x00a1 }
        r9 = "insertPromptBehavior E: ";
        r8 = r8.append(r9);	 Catch:{ all -> 0x00a1 }
        r2 = r8.append(r2);	 Catch:{ all -> 0x00a1 }
        r2 = r2.toString();	 Catch:{ all -> 0x00a1 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r7, r2);	 Catch:{ all -> 0x00a1 }
        goto L_0x0078;
    L_0x00a1:
        r2 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x00a1 }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1568q.m6988a(android.content.Context, com.baidu.android.pushservice.i.k):long");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: a */
    public static long m6989a(android.content.Context r10, com.baidu.android.pushservice.p037i.C1449n r11) {
        /*
        r4 = 0;
        r6 = f5511c;
        monitor-enter(r6);
        r3 = com.baidu.android.pushservice.util.C1568q.m7009e(r10);	 Catch:{ all -> 0x00fa }
        if (r3 != 0) goto L_0x000f;
    L_0x000b:
        r2 = -1;
        monitor-exit(r6);	 Catch:{ all -> 0x00fa }
    L_0x000e:
        return r2;
    L_0x000f:
        r2 = com.baidu.android.pushservice.util.C1568q.m6992a(r3, r11);	 Catch:{ all -> 0x00fa }
        if (r2 == 0) goto L_0x001b;
    L_0x0015:
        r3.close();	 Catch:{ all -> 0x00fa }
        monitor-exit(r6);	 Catch:{ all -> 0x00fa }
        r2 = r4;
        goto L_0x000e;
    L_0x001b:
        r7 = new android.content.ContentValues;	 Catch:{ all -> 0x00fa }
        r7.<init>();	 Catch:{ all -> 0x00fa }
        r2 = com.baidu.android.pushservice.util.C1568q.C1558c.appid;	 Catch:{ all -> 0x00fa }
        r2 = r2.name();	 Catch:{ all -> 0x00fa }
        r8 = r11.mo13852a();	 Catch:{ all -> 0x00fa }
        r7.put(r2, r8);	 Catch:{ all -> 0x00fa }
        r2 = com.baidu.android.pushservice.util.C1568q.C1558c.appType;	 Catch:{ all -> 0x00fa }
        r2 = r2.name();	 Catch:{ all -> 0x00fa }
        r8 = r11.mo13919j();	 Catch:{ all -> 0x00fa }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ all -> 0x00fa }
        r7.put(r2, r8);	 Catch:{ all -> 0x00fa }
        r2 = com.baidu.android.pushservice.util.C1568q.C1558c.rsaUserId;	 Catch:{ all -> 0x00fa }
        r2 = r2.name();	 Catch:{ all -> 0x00fa }
        r8 = r11.mo13855b();	 Catch:{ all -> 0x00fa }
        r7.put(r2, r8);	 Catch:{ all -> 0x00fa }
        r2 = com.baidu.android.pushservice.util.C1568q.C1558c.userId;	 Catch:{ all -> 0x00fa }
        r2 = r2.name();	 Catch:{ all -> 0x00fa }
        r8 = r11.mo13858c();	 Catch:{ all -> 0x00fa }
        r7.put(r2, r8);	 Catch:{ all -> 0x00fa }
        r2 = com.baidu.android.pushservice.util.C1568q.C1558c.packageName;	 Catch:{ all -> 0x00fa }
        r2 = r2.name();	 Catch:{ all -> 0x00fa }
        r8 = r11.mo13860d();	 Catch:{ all -> 0x00fa }
        r7.put(r2, r8);	 Catch:{ all -> 0x00fa }
        r2 = com.baidu.android.pushservice.util.C1568q.C1558c.appName;	 Catch:{ all -> 0x00fa }
        r2 = r2.name();	 Catch:{ all -> 0x00fa }
        r8 = r11.mo13862e();	 Catch:{ all -> 0x00fa }
        r7.put(r2, r8);	 Catch:{ all -> 0x00fa }
        r2 = com.baidu.android.pushservice.util.C1568q.C1558c.cFrom;	 Catch:{ all -> 0x00fa }
        r2 = r2.name();	 Catch:{ all -> 0x00fa }
        r8 = r11.mo13864f();	 Catch:{ all -> 0x00fa }
        r7.put(r2, r8);	 Catch:{ all -> 0x00fa }
        r2 = com.baidu.android.pushservice.util.C1568q.C1558c.versionCode;	 Catch:{ all -> 0x00fa }
        r2 = r2.name();	 Catch:{ all -> 0x00fa }
        r8 = r11.mo13866g();	 Catch:{ all -> 0x00fa }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ all -> 0x00fa }
        r7.put(r2, r8);	 Catch:{ all -> 0x00fa }
        r2 = com.baidu.android.pushservice.util.C1568q.C1558c.versionName;	 Catch:{ all -> 0x00fa }
        r2 = r2.name();	 Catch:{ all -> 0x00fa }
        r8 = r11.mo13868h();	 Catch:{ all -> 0x00fa }
        r7.put(r2, r8);	 Catch:{ all -> 0x00fa }
        r2 = com.baidu.android.pushservice.util.C1568q.C1558c.intergratedPushVersion;	 Catch:{ all -> 0x00fa }
        r2 = r2.name();	 Catch:{ all -> 0x00fa }
        r8 = r11.mo13869i();	 Catch:{ all -> 0x00fa }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ all -> 0x00fa }
        r7.put(r2, r8);	 Catch:{ all -> 0x00fa }
        r2 = r11.mo13852a();	 Catch:{ Exception -> 0x00e0 }
        r2 = java.lang.Long.valueOf(r2);	 Catch:{ Exception -> 0x00e0 }
        r8 = r2.longValue();	 Catch:{ Exception -> 0x00e0 }
        r2 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1));
        if (r2 >= 0) goto L_0x00c5;
    L_0x00be:
        r3.close();	 Catch:{ Exception -> 0x00e0 }
        monitor-exit(r6);	 Catch:{ all -> 0x00fa }
        r2 = r4;
        goto L_0x000e;
    L_0x00c5:
        r8 = "AppInfo";
        r9 = 0;
        r2 = r3 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00e0 }
        if (r2 != 0) goto L_0x00d7;
    L_0x00cc:
        r4 = r3.insert(r8, r9, r7);	 Catch:{ Exception -> 0x00e0 }
    L_0x00d0:
        r3.close();	 Catch:{ all -> 0x00fa }
        monitor-exit(r6);	 Catch:{ all -> 0x00fa }
        r2 = r4;
        goto L_0x000e;
    L_0x00d7:
        r0 = r3;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00e0 }
        r2 = r0;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.insert(r2, r8, r9, r7);	 Catch:{ Exception -> 0x00e0 }
        goto L_0x00d0;
    L_0x00e0:
        r2 = move-exception;
        r7 = "PushDatabase";
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00fa }
        r8.<init>();	 Catch:{ all -> 0x00fa }
        r9 = "insertAppInfo E: ";
        r8 = r8.append(r9);	 Catch:{ all -> 0x00fa }
        r2 = r8.append(r2);	 Catch:{ all -> 0x00fa }
        r2 = r2.toString();	 Catch:{ all -> 0x00fa }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r7, r2);	 Catch:{ all -> 0x00fa }
        goto L_0x00d0;
    L_0x00fa:
        r2 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x00fa }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1568q.m6989a(android.content.Context, com.baidu.android.pushservice.i.n):long");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: a */
    public static long m6990a(android.content.Context r10, com.baidu.android.pushservice.p037i.C1450o r11) {
        /*
        r4 = -1;
        r6 = f5511c;
        monitor-enter(r6);
        r3 = com.baidu.android.pushservice.util.C1568q.m7009e(r10);	 Catch:{ all -> 0x00c3 }
        if (r3 != 0) goto L_0x000e;
    L_0x000b:
        monitor-exit(r6);	 Catch:{ all -> 0x00c3 }
        r2 = r4;
    L_0x000d:
        return r2;
    L_0x000e:
        r7 = new android.content.ContentValues;	 Catch:{ all -> 0x00c3 }
        r7.<init>();	 Catch:{ all -> 0x00c3 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.actionName;	 Catch:{ all -> 0x00c3 }
        r2 = r2.name();	 Catch:{ all -> 0x00c3 }
        r8 = r11.f5036f;	 Catch:{ all -> 0x00c3 }
        r7.put(r2, r8);	 Catch:{ all -> 0x00c3 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.timeStamp;	 Catch:{ all -> 0x00c3 }
        r2 = r2.name();	 Catch:{ all -> 0x00c3 }
        r8 = r11.f5037g;	 Catch:{ all -> 0x00c3 }
        r8 = java.lang.Long.valueOf(r8);	 Catch:{ all -> 0x00c3 }
        r7.put(r2, r8);	 Catch:{ all -> 0x00c3 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.networkStatus;	 Catch:{ all -> 0x00c3 }
        r2 = r2.name();	 Catch:{ all -> 0x00c3 }
        r8 = r11.f5038h;	 Catch:{ all -> 0x00c3 }
        r7.put(r2, r8);	 Catch:{ all -> 0x00c3 }
        r2 = r11.f5040j;	 Catch:{ all -> 0x00c3 }
        if (r2 == 0) goto L_0x0047;
    L_0x003c:
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.appid;	 Catch:{ all -> 0x00c3 }
        r2 = r2.name();	 Catch:{ all -> 0x00c3 }
        r8 = r11.f5040j;	 Catch:{ all -> 0x00c3 }
        r7.put(r2, r8);	 Catch:{ all -> 0x00c3 }
    L_0x0047:
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.msgType;	 Catch:{ all -> 0x00c3 }
        r2 = r2.name();	 Catch:{ all -> 0x00c3 }
        r8 = r11.f5121c;	 Catch:{ all -> 0x00c3 }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ all -> 0x00c3 }
        r7.put(r2, r8);	 Catch:{ all -> 0x00c3 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.msgId;	 Catch:{ all -> 0x00c3 }
        r2 = r2.name();	 Catch:{ all -> 0x00c3 }
        r8 = r11.f5119a;	 Catch:{ all -> 0x00c3 }
        r7.put(r2, r8);	 Catch:{ all -> 0x00c3 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.msgLen;	 Catch:{ all -> 0x00c3 }
        r2 = r2.name();	 Catch:{ all -> 0x00c3 }
        r8 = r11.f5120b;	 Catch:{ all -> 0x00c3 }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ all -> 0x00c3 }
        r7.put(r2, r8);	 Catch:{ all -> 0x00c3 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.errorCode;	 Catch:{ all -> 0x00c3 }
        r2 = r2.name();	 Catch:{ all -> 0x00c3 }
        r8 = r11.f5039i;	 Catch:{ all -> 0x00c3 }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ all -> 0x00c3 }
        r7.put(r2, r8);	 Catch:{ all -> 0x00c3 }
        r2 = r11.f5122d;	 Catch:{ all -> 0x00c3 }
        if (r2 == 0) goto L_0x008e;
    L_0x0083:
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.openByPackageName;	 Catch:{ all -> 0x00c3 }
        r2 = r2.name();	 Catch:{ all -> 0x00c3 }
        r8 = r11.f5122d;	 Catch:{ all -> 0x00c3 }
        r7.put(r2, r8);	 Catch:{ all -> 0x00c3 }
    L_0x008e:
        r8 = "PushBehavior";
        r9 = 0;
        r2 = r3 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00a9 }
        if (r2 != 0) goto L_0x00a0;
    L_0x0095:
        r4 = r3.insert(r8, r9, r7);	 Catch:{ Exception -> 0x00a9 }
    L_0x0099:
        r3.close();	 Catch:{ all -> 0x00c3 }
        monitor-exit(r6);	 Catch:{ all -> 0x00c3 }
        r2 = r4;
        goto L_0x000d;
    L_0x00a0:
        r0 = r3;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00a9 }
        r2 = r0;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.insert(r2, r8, r9, r7);	 Catch:{ Exception -> 0x00a9 }
        goto L_0x0099;
    L_0x00a9:
        r2 = move-exception;
        r7 = "PushDatabase";
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c3 }
        r8.<init>();	 Catch:{ all -> 0x00c3 }
        r9 = "exception ";
        r8 = r8.append(r9);	 Catch:{ all -> 0x00c3 }
        r2 = r8.append(r2);	 Catch:{ all -> 0x00c3 }
        r2 = r2.toString();	 Catch:{ all -> 0x00c3 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r7, r2);	 Catch:{ all -> 0x00c3 }
        goto L_0x0099;
    L_0x00c3:
        r2 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x00c3 }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1568q.m6990a(android.content.Context, com.baidu.android.pushservice.i.o):long");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: a */
    public static long m6991a(android.content.Context r10, com.baidu.android.pushservice.util.C1568q.C1563h r11) {
        /*
        r6 = f5511c;
        monitor-enter(r6);
        r3 = com.baidu.android.pushservice.util.C1568q.m7009e(r10);	 Catch:{ all -> 0x00c9 }
        if (r3 != 0) goto L_0x000d;
    L_0x0009:
        r2 = -1;
        monitor-exit(r6);	 Catch:{ all -> 0x00c9 }
    L_0x000c:
        return r2;
    L_0x000d:
        r7 = new android.content.ContentValues;	 Catch:{ all -> 0x00c9 }
        r7.<init>();	 Catch:{ all -> 0x00c9 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1562g.belongTo;	 Catch:{ all -> 0x00c9 }
        r2 = r2.name();	 Catch:{ all -> 0x00c9 }
        r4 = r11.f5472a;	 Catch:{ all -> 0x00c9 }
        r7.put(r2, r4);	 Catch:{ all -> 0x00c9 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1562g.downloadUrl;	 Catch:{ all -> 0x00c9 }
        r2 = r2.name();	 Catch:{ all -> 0x00c9 }
        r4 = r11.f5473b;	 Catch:{ all -> 0x00c9 }
        r7.put(r2, r4);	 Catch:{ all -> 0x00c9 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1562g.title;	 Catch:{ all -> 0x00c9 }
        r2 = r2.name();	 Catch:{ all -> 0x00c9 }
        r4 = r11.f5474c;	 Catch:{ all -> 0x00c9 }
        r7.put(r2, r4);	 Catch:{ all -> 0x00c9 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1562g.description;	 Catch:{ all -> 0x00c9 }
        r2 = r2.name();	 Catch:{ all -> 0x00c9 }
        r4 = r11.f5475d;	 Catch:{ all -> 0x00c9 }
        r7.put(r2, r4);	 Catch:{ all -> 0x00c9 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1562g.savePath;	 Catch:{ all -> 0x00c9 }
        r2 = r2.name();	 Catch:{ all -> 0x00c9 }
        r4 = r11.f5476e;	 Catch:{ all -> 0x00c9 }
        r7.put(r2, r4);	 Catch:{ all -> 0x00c9 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1562g.fileName;	 Catch:{ all -> 0x00c9 }
        r2 = r2.name();	 Catch:{ all -> 0x00c9 }
        r4 = r11.f5477f;	 Catch:{ all -> 0x00c9 }
        r7.put(r2, r4);	 Catch:{ all -> 0x00c9 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1562g.downloadBytes;	 Catch:{ all -> 0x00c9 }
        r2 = r2.name();	 Catch:{ all -> 0x00c9 }
        r4 = r11.f5478g;	 Catch:{ all -> 0x00c9 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x00c9 }
        r7.put(r2, r4);	 Catch:{ all -> 0x00c9 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1562g.totalBytes;	 Catch:{ all -> 0x00c9 }
        r2 = r2.name();	 Catch:{ all -> 0x00c9 }
        r4 = r11.f5479h;	 Catch:{ all -> 0x00c9 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x00c9 }
        r7.put(r2, r4);	 Catch:{ all -> 0x00c9 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1562g.downloadStatus;	 Catch:{ all -> 0x00c9 }
        r2 = r2.name();	 Catch:{ all -> 0x00c9 }
        r4 = r11.f5480i;	 Catch:{ all -> 0x00c9 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x00c9 }
        r7.put(r2, r4);	 Catch:{ all -> 0x00c9 }
        r2 = com.baidu.android.pushservice.util.C1568q.C1562g.timeStamp;	 Catch:{ all -> 0x00c9 }
        r2 = r2.name();	 Catch:{ all -> 0x00c9 }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x00c9 }
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ all -> 0x00c9 }
        r7.put(r2, r4);	 Catch:{ all -> 0x00c9 }
        r4 = 0;
        r8 = "FileDownloadingInfo";
        r9 = 0;
        r2 = r3 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00af }
        if (r2 != 0) goto L_0x00a6;
    L_0x009b:
        r4 = r3.insert(r8, r9, r7);	 Catch:{ Exception -> 0x00af }
    L_0x009f:
        r3.close();	 Catch:{ all -> 0x00c9 }
        monitor-exit(r6);	 Catch:{ all -> 0x00c9 }
        r2 = r4;
        goto L_0x000c;
    L_0x00a6:
        r0 = r3;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00af }
        r2 = r0;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.insert(r2, r8, r9, r7);	 Catch:{ Exception -> 0x00af }
        goto L_0x009f;
    L_0x00af:
        r2 = move-exception;
        r7 = "PushDatabase";
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c9 }
        r8.<init>();	 Catch:{ all -> 0x00c9 }
        r9 = "exception ";
        r8 = r8.append(r9);	 Catch:{ all -> 0x00c9 }
        r2 = r8.append(r2);	 Catch:{ all -> 0x00c9 }
        r2 = r2.toString();	 Catch:{ all -> 0x00c9 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r7, r2);	 Catch:{ all -> 0x00c9 }
        goto L_0x009f;
    L_0x00c9:
        r2 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x00c9 }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1568q.m6991a(android.content.Context, com.baidu.android.pushservice.util.q$h):long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0136  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0136  */
    /* renamed from: a */
    private static com.baidu.android.pushservice.p037i.C1449n m6992a(android.database.sqlite.SQLiteDatabase r9, com.baidu.android.pushservice.p037i.C1449n r10) {
        /*
        r2 = 0;
        if (r9 != 0) goto L_0x0005;
    L_0x0003:
        r1 = r2;
    L_0x0004:
        return r1;
    L_0x0005:
        r3 = new com.baidu.android.pushservice.i.n;
        r1 = r10.mo13852a();
        r3.<init>(r1);
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r4 = "SELECT * FROM AppInfo WHERE ";
        r1 = r1.append(r4);
        r4 = com.baidu.android.pushservice.util.C1568q.C1558c.appid;
        r4 = r4.name();
        r1 = r1.append(r4);
        r4 = " = ";
        r1 = r1.append(r4);
        r4 = r10.mo13852a();
        r1 = r1.append(r4);
        r4 = ";";
        r1 = r1.append(r4);
        r4 = r1.toString();
        r5 = 0;
        r1 = r9 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0112, all -> 0x0132 }
        if (r1 != 0) goto L_0x0100;
    L_0x0040:
        r4 = r9.rawQuery(r4, r5);	 Catch:{ Exception -> 0x0112, all -> 0x0132 }
    L_0x0044:
        r1 = 0;
        r5 = r4.moveToNext();	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        if (r5 == 0) goto L_0x00f6;
    L_0x004b:
        r1 = 1;
        r5 = com.baidu.android.pushservice.util.C1568q.C1558c.appInfoId;	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r5 = r5.name();	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r5 = r4.getColumnIndex(r5);	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r5 = r4.getInt(r5);	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r6 = r10.mo13858c();	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r7 = com.baidu.android.pushservice.util.C1568q.C1558c.userId;	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r7 = r7.name();	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r7 = r4.getColumnIndex(r7);	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r7 = r4.getString(r7);	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r6 = android.text.TextUtils.equals(r6, r7);	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        if (r6 == 0) goto L_0x00f3;
    L_0x0072:
        r6 = r10.mo13864f();	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r7 = com.baidu.android.pushservice.util.C1568q.C1558c.cFrom;	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r7 = r7.name();	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r7 = r4.getColumnIndex(r7);	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r7 = r4.getString(r7);	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r6 = android.text.TextUtils.equals(r6, r7);	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        if (r6 == 0) goto L_0x00f3;
    L_0x008a:
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r6.<init>();	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r7 = r10.mo13866g();	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r7 = "";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r6 = r6.toString();	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r7.<init>();	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r8 = com.baidu.android.pushservice.util.C1568q.C1558c.versionCode;	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r8 = r8.name();	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r8 = r4.getColumnIndex(r8);	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r8 = r4.getInt(r8);	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r7 = r7.append(r8);	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r8 = "";
        r7 = r7.append(r8);	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r7 = r7.toString();	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r6 = android.text.TextUtils.equals(r6, r7);	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        if (r6 == 0) goto L_0x00f3;
    L_0x00c8:
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r6.<init>();	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r7 = r10.mo13869i();	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r7 = "";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r6 = r6.toString();	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r7 = com.baidu.android.pushservice.util.C1568q.C1558c.intergratedPushVersion;	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r7 = r7.name();	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r7 = r4.getColumnIndex(r7);	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r7 = r4.getString(r7);	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        r6 = android.text.TextUtils.equals(r6, r7);	 Catch:{ Exception -> 0x013f, all -> 0x013a }
        if (r6 != 0) goto L_0x00f6;
    L_0x00f3:
        com.baidu.android.pushservice.util.C1568q.m6983a(r9, r5, r10);	 Catch:{ Exception -> 0x013f, all -> 0x013a }
    L_0x00f6:
        if (r1 == 0) goto L_0x010a;
    L_0x00f8:
        if (r4 == 0) goto L_0x00fd;
    L_0x00fa:
        r4.close();
    L_0x00fd:
        r1 = r3;
        goto L_0x0004;
    L_0x0100:
        r0 = r9;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0112, all -> 0x0132 }
        r1 = r0;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.rawQuery(r1, r4, r5);	 Catch:{ Exception -> 0x0112, all -> 0x0132 }
        goto L_0x0044;
    L_0x010a:
        if (r4 == 0) goto L_0x010f;
    L_0x010c:
        r4.close();
    L_0x010f:
        r1 = r2;
        goto L_0x0004;
    L_0x0112:
        r1 = move-exception;
        r3 = r2;
    L_0x0114:
        r4 = "PushDatabase";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x013c }
        r5.<init>();	 Catch:{ all -> 0x013c }
        r6 = "needToInsertUpdate Exception: ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x013c }
        r1 = r5.append(r1);	 Catch:{ all -> 0x013c }
        r1 = r1.toString();	 Catch:{ all -> 0x013c }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r4, r1);	 Catch:{ all -> 0x013c }
        if (r3 == 0) goto L_0x010f;
    L_0x012e:
        r3.close();
        goto L_0x010f;
    L_0x0132:
        r1 = move-exception;
        r4 = r2;
    L_0x0134:
        if (r4 == 0) goto L_0x0139;
    L_0x0136:
        r4.close();
    L_0x0139:
        throw r1;
    L_0x013a:
        r1 = move-exception;
        goto L_0x0134;
    L_0x013c:
        r1 = move-exception;
        r4 = r3;
        goto L_0x0134;
    L_0x013f:
        r1 = move-exception;
        r3 = r4;
        goto L_0x0114;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1568q.m6992a(android.database.sqlite.SQLiteDatabase, com.baidu.android.pushservice.i.n):com.baidu.android.pushservice.i.n");
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x0137 A:{SYNTHETIC, Splitter:B:37:0x0137} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x013c A:{Catch:{ Exception -> 0x0118, all -> 0x0141 }} */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0146 A:{Catch:{ Exception -> 0x0118, all -> 0x0141 }} */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x014b A:{Catch:{ Exception -> 0x0118, all -> 0x0141 }} */
    /* renamed from: a */
    public static com.baidu.android.pushservice.util.C1568q.C1563h m6993a(android.content.Context r21, java.lang.String r22) {
        /*
        r20 = f5511c;
        monitor-enter(r20);
        r2 = com.baidu.android.pushservice.util.C1568q.m7009e(r21);	 Catch:{ all -> 0x0102 }
        if (r2 != 0) goto L_0x000c;
    L_0x0009:
        r2 = 0;
        monitor-exit(r20);	 Catch:{ all -> 0x0102 }
    L_0x000b:
        return r2;
    L_0x000c:
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0102 }
        r3.<init>();	 Catch:{ all -> 0x0102 }
        r4 = "(";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0102 }
        r4 = com.baidu.android.pushservice.util.C1568q.C1562g.downloadUrl;	 Catch:{ all -> 0x0102 }
        r4 = r4.name();	 Catch:{ all -> 0x0102 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x0102 }
        r4 = "==?";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0102 }
        r4 = ")";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0102 }
        r5 = r3.toString();	 Catch:{ all -> 0x0102 }
        r3 = 1;
        r6 = new java.lang.String[r3];	 Catch:{ all -> 0x0102 }
        r3 = 0;
        r6[r3] = r22;	 Catch:{ all -> 0x0102 }
        r19 = 0;
        r18 = 0;
        r3 = "FileDownloadingInfo";
        r4 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0118, all -> 0x0141 }
        if (r10 != 0) goto L_0x0105;
    L_0x0045:
        r4 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x0118, all -> 0x0141 }
    L_0x0049:
        r3 = r4.moveToFirst();	 Catch:{ Exception -> 0x0151 }
        if (r3 == 0) goto L_0x0159;
    L_0x004f:
        r5 = new com.baidu.android.pushservice.util.q$h;	 Catch:{ Exception -> 0x0151 }
        r5.<init>();	 Catch:{ Exception -> 0x0151 }
        r3 = com.baidu.android.pushservice.util.C1568q.C1562g.belongTo;	 Catch:{ Exception -> 0x0155 }
        r3 = r3.name();	 Catch:{ Exception -> 0x0155 }
        r3 = r4.getColumnIndex(r3);	 Catch:{ Exception -> 0x0155 }
        r3 = r4.getString(r3);	 Catch:{ Exception -> 0x0155 }
        r5.f5472a = r3;	 Catch:{ Exception -> 0x0155 }
        r3 = com.baidu.android.pushservice.util.C1568q.C1562g.downloadUrl;	 Catch:{ Exception -> 0x0155 }
        r3 = r3.name();	 Catch:{ Exception -> 0x0155 }
        r3 = r4.getColumnIndex(r3);	 Catch:{ Exception -> 0x0155 }
        r3 = r4.getString(r3);	 Catch:{ Exception -> 0x0155 }
        r5.f5473b = r3;	 Catch:{ Exception -> 0x0155 }
        r3 = com.baidu.android.pushservice.util.C1568q.C1562g.title;	 Catch:{ Exception -> 0x0155 }
        r3 = r3.name();	 Catch:{ Exception -> 0x0155 }
        r3 = r4.getColumnIndex(r3);	 Catch:{ Exception -> 0x0155 }
        r3 = r4.getString(r3);	 Catch:{ Exception -> 0x0155 }
        r5.f5474c = r3;	 Catch:{ Exception -> 0x0155 }
        r3 = com.baidu.android.pushservice.util.C1568q.C1562g.description;	 Catch:{ Exception -> 0x0155 }
        r3 = r3.name();	 Catch:{ Exception -> 0x0155 }
        r3 = r4.getColumnIndex(r3);	 Catch:{ Exception -> 0x0155 }
        r3 = r4.getString(r3);	 Catch:{ Exception -> 0x0155 }
        r5.f5475d = r3;	 Catch:{ Exception -> 0x0155 }
        r3 = com.baidu.android.pushservice.util.C1568q.C1562g.savePath;	 Catch:{ Exception -> 0x0155 }
        r3 = r3.name();	 Catch:{ Exception -> 0x0155 }
        r3 = r4.getColumnIndex(r3);	 Catch:{ Exception -> 0x0155 }
        r3 = r4.getString(r3);	 Catch:{ Exception -> 0x0155 }
        r5.f5476e = r3;	 Catch:{ Exception -> 0x0155 }
        r3 = com.baidu.android.pushservice.util.C1568q.C1562g.fileName;	 Catch:{ Exception -> 0x0155 }
        r3 = r3.name();	 Catch:{ Exception -> 0x0155 }
        r3 = r4.getColumnIndex(r3);	 Catch:{ Exception -> 0x0155 }
        r3 = r4.getString(r3);	 Catch:{ Exception -> 0x0155 }
        r5.f5477f = r3;	 Catch:{ Exception -> 0x0155 }
        r3 = com.baidu.android.pushservice.util.C1568q.C1562g.downloadBytes;	 Catch:{ Exception -> 0x0155 }
        r3 = r3.name();	 Catch:{ Exception -> 0x0155 }
        r3 = r4.getColumnIndex(r3);	 Catch:{ Exception -> 0x0155 }
        r3 = r4.getInt(r3);	 Catch:{ Exception -> 0x0155 }
        r5.f5478g = r3;	 Catch:{ Exception -> 0x0155 }
        r3 = com.baidu.android.pushservice.util.C1568q.C1562g.totalBytes;	 Catch:{ Exception -> 0x0155 }
        r3 = r3.name();	 Catch:{ Exception -> 0x0155 }
        r3 = r4.getColumnIndex(r3);	 Catch:{ Exception -> 0x0155 }
        r3 = r4.getInt(r3);	 Catch:{ Exception -> 0x0155 }
        r5.f5479h = r3;	 Catch:{ Exception -> 0x0155 }
        r3 = com.baidu.android.pushservice.util.C1568q.C1562g.downloadStatus;	 Catch:{ Exception -> 0x0155 }
        r3 = r3.name();	 Catch:{ Exception -> 0x0155 }
        r3 = r4.getColumnIndex(r3);	 Catch:{ Exception -> 0x0155 }
        r3 = r4.getInt(r3);	 Catch:{ Exception -> 0x0155 }
        r5.f5480i = r3;	 Catch:{ Exception -> 0x0155 }
        r3 = com.baidu.android.pushservice.util.C1568q.C1562g.timeStamp;	 Catch:{ Exception -> 0x0155 }
        r3 = r3.name();	 Catch:{ Exception -> 0x0155 }
        r3 = r4.getColumnIndex(r3);	 Catch:{ Exception -> 0x0155 }
        r6 = r4.getLong(r3);	 Catch:{ Exception -> 0x0155 }
        r5.f5481j = r6;	 Catch:{ Exception -> 0x0155 }
    L_0x00f4:
        if (r4 == 0) goto L_0x00f9;
    L_0x00f6:
        r4.close();	 Catch:{ all -> 0x0102 }
    L_0x00f9:
        if (r2 == 0) goto L_0x0157;
    L_0x00fb:
        r2.close();	 Catch:{ all -> 0x0102 }
        r2 = r5;
    L_0x00ff:
        monitor-exit(r20);	 Catch:{ all -> 0x0102 }
        goto L_0x000b;
    L_0x0102:
        r2 = move-exception;
        monitor-exit(r20);	 Catch:{ all -> 0x0102 }
        throw r2;
    L_0x0105:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0118, all -> 0x0141 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x0118, all -> 0x0141 }
        goto L_0x0049;
    L_0x0118:
        r3 = move-exception;
        r4 = r18;
        r5 = r19;
    L_0x011d:
        r6 = "PushDatabase";
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x014f }
        r7.<init>();	 Catch:{ all -> 0x014f }
        r8 = "exception ";
        r7 = r7.append(r8);	 Catch:{ all -> 0x014f }
        r3 = r7.append(r3);	 Catch:{ all -> 0x014f }
        r3 = r3.toString();	 Catch:{ all -> 0x014f }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r6, r3);	 Catch:{ all -> 0x014f }
        if (r4 == 0) goto L_0x013a;
    L_0x0137:
        r4.close();	 Catch:{ all -> 0x0102 }
    L_0x013a:
        if (r2 == 0) goto L_0x0157;
    L_0x013c:
        r2.close();	 Catch:{ all -> 0x0102 }
        r2 = r5;
        goto L_0x00ff;
    L_0x0141:
        r3 = move-exception;
        r4 = r18;
    L_0x0144:
        if (r4 == 0) goto L_0x0149;
    L_0x0146:
        r4.close();	 Catch:{ all -> 0x0102 }
    L_0x0149:
        if (r2 == 0) goto L_0x014e;
    L_0x014b:
        r2.close();	 Catch:{ all -> 0x0102 }
    L_0x014e:
        throw r3;	 Catch:{ all -> 0x0102 }
    L_0x014f:
        r3 = move-exception;
        goto L_0x0144;
    L_0x0151:
        r3 = move-exception;
        r5 = r19;
        goto L_0x011d;
    L_0x0155:
        r3 = move-exception;
        goto L_0x011d;
    L_0x0157:
        r2 = r5;
        goto L_0x00ff;
    L_0x0159:
        r5 = r19;
        goto L_0x00f4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1568q.m6993a(android.content.Context, java.lang.String):com.baidu.android.pushservice.util.q$h");
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x011c A:{SYNTHETIC, Splitter:B:41:0x011c} */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0121  */
    /* renamed from: a */
    public static java.util.List<com.baidu.android.pushservice.p037i.C1449n> m6994a(android.content.Context r10) {
        /*
        r3 = 0;
        r5 = f5511c;
        monitor-enter(r5);
        r2 = com.baidu.android.pushservice.util.C1568q.m7009e(r10);	 Catch:{ all -> 0x0116 }
        if (r2 != 0) goto L_0x000d;
    L_0x000a:
        monitor-exit(r5);	 Catch:{ all -> 0x0116 }
        r1 = r3;
    L_0x000c:
        return r1;
    L_0x000d:
        r4 = new java.util.ArrayList;	 Catch:{ all -> 0x0116 }
        r4.<init>();	 Catch:{ all -> 0x0116 }
        r6 = "SELECT * FROM AppInfo;";
        r7 = 0;
        r1 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x012a }
        if (r1 != 0) goto L_0x0101;
    L_0x0019:
        r1 = r2.rawQuery(r6, r7);	 Catch:{ Exception -> 0x012a }
    L_0x001d:
        r3 = r1.moveToNext();	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        if (r3 == 0) goto L_0x010b;
    L_0x0023:
        r3 = new com.baidu.android.pushservice.i.n;	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r3.<init>();	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = com.baidu.android.pushservice.util.C1568q.C1558c.appid;	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r6.name();	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r1.getColumnIndex(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r1.getString(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r3.mo13854a(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = com.baidu.android.pushservice.util.C1568q.C1558c.appType;	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r6.name();	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r1.getColumnIndex(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r1.getInt(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r3.mo13918c(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = com.baidu.android.pushservice.util.C1568q.C1558c.rsaUserId;	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r6.name();	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r1.getColumnIndex(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r1.getString(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r3.mo13857b(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = com.baidu.android.pushservice.util.C1568q.C1558c.userId;	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r6.name();	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r1.getColumnIndex(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r1.getString(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r3.mo13859c(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = com.baidu.android.pushservice.util.C1568q.C1558c.packageName;	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r6.name();	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r1.getColumnIndex(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r1.getString(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r3.mo13861d(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = com.baidu.android.pushservice.util.C1568q.C1558c.appName;	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r6.name();	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r1.getColumnIndex(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r1.getString(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r3.mo13863e(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = com.baidu.android.pushservice.util.C1568q.C1558c.cFrom;	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r6.name();	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r1.getColumnIndex(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r1.getString(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r3.mo13865f(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = com.baidu.android.pushservice.util.C1568q.C1558c.versionCode;	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r6.name();	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r1.getColumnIndex(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r1.getInt(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r3.mo13853a(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = com.baidu.android.pushservice.util.C1568q.C1558c.versionName;	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r6.name();	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r1.getColumnIndex(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r1.getString(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r3.mo13867g(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = com.baidu.android.pushservice.util.C1568q.C1558c.intergratedPushVersion;	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r6.name();	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r1.getColumnIndex(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r6 = r1.getInt(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r3.mo13856b(r6);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        r4.add(r3);	 Catch:{ Exception -> 0x00d7, all -> 0x0125 }
        goto L_0x001d;
    L_0x00d7:
        r3 = move-exception;
        r9 = r3;
        r3 = r1;
        r1 = r9;
    L_0x00db:
        r6 = "PushDatabase";
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0119 }
        r7.<init>();	 Catch:{ all -> 0x0119 }
        r8 = "exception ";
        r7 = r7.append(r8);	 Catch:{ all -> 0x0119 }
        r1 = r7.append(r1);	 Catch:{ all -> 0x0119 }
        r1 = r1.toString();	 Catch:{ all -> 0x0119 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r6, r1);	 Catch:{ all -> 0x0119 }
        if (r3 == 0) goto L_0x00f8;
    L_0x00f5:
        r3.close();	 Catch:{ all -> 0x0116 }
    L_0x00f8:
        if (r2 == 0) goto L_0x00fd;
    L_0x00fa:
        r2.close();	 Catch:{ all -> 0x0116 }
    L_0x00fd:
        monitor-exit(r5);	 Catch:{ all -> 0x0116 }
        r1 = r4;
        goto L_0x000c;
    L_0x0101:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x012a }
        r1 = r0;
        r1 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.rawQuery(r1, r6, r7);	 Catch:{ Exception -> 0x012a }
        goto L_0x001d;
    L_0x010b:
        if (r1 == 0) goto L_0x0110;
    L_0x010d:
        r1.close();	 Catch:{ all -> 0x0116 }
    L_0x0110:
        if (r2 == 0) goto L_0x00fd;
    L_0x0112:
        r2.close();	 Catch:{ all -> 0x0116 }
        goto L_0x00fd;
    L_0x0116:
        r1 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0116 }
        throw r1;
    L_0x0119:
        r1 = move-exception;
    L_0x011a:
        if (r3 == 0) goto L_0x011f;
    L_0x011c:
        r3.close();	 Catch:{ all -> 0x0116 }
    L_0x011f:
        if (r2 == 0) goto L_0x0124;
    L_0x0121:
        r2.close();	 Catch:{ all -> 0x0116 }
    L_0x0124:
        throw r1;	 Catch:{ all -> 0x0116 }
    L_0x0125:
        r3 = move-exception;
        r9 = r3;
        r3 = r1;
        r1 = r9;
        goto L_0x011a;
    L_0x012a:
        r1 = move-exception;
        goto L_0x00db;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1568q.m6994a(android.content.Context):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x01bd A:{SYNTHETIC, Splitter:B:41:0x01bd} */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x01c2  */
    /* renamed from: a */
    public static java.util.List<com.baidu.android.pushservice.p037i.C1440g> m6995a(android.content.Context r11, long r12, long r14, int r16, int r17) {
        /*
        r4 = 0;
        r6 = f5511c;
        monitor-enter(r6);
        r3 = com.baidu.android.pushservice.util.C1568q.m7009e(r11);	 Catch:{ all -> 0x01b7 }
        if (r3 != 0) goto L_0x000d;
    L_0x000a:
        monitor-exit(r6);	 Catch:{ all -> 0x01b7 }
        r2 = r4;
    L_0x000c:
        return r2;
    L_0x000d:
        r5 = new java.util.ArrayList;	 Catch:{ all -> 0x01b7 }
        r5.<init>();	 Catch:{ all -> 0x01b7 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01b7 }
        r2.<init>();	 Catch:{ all -> 0x01b7 }
        r7 = "SELECT * FROM PushBehavior WHERE ";
        r2 = r2.append(r7);	 Catch:{ all -> 0x01b7 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1559d.timeStamp;	 Catch:{ all -> 0x01b7 }
        r7 = r7.name();	 Catch:{ all -> 0x01b7 }
        r2 = r2.append(r7);	 Catch:{ all -> 0x01b7 }
        r7 = " < ";
        r2 = r2.append(r7);	 Catch:{ all -> 0x01b7 }
        r2 = r2.append(r12);	 Catch:{ all -> 0x01b7 }
        r7 = " AND ";
        r2 = r2.append(r7);	 Catch:{ all -> 0x01b7 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1559d.timeStamp;	 Catch:{ all -> 0x01b7 }
        r7 = r7.name();	 Catch:{ all -> 0x01b7 }
        r2 = r2.append(r7);	 Catch:{ all -> 0x01b7 }
        r7 = " >= ";
        r2 = r2.append(r7);	 Catch:{ all -> 0x01b7 }
        r2 = r2.append(r14);	 Catch:{ all -> 0x01b7 }
        r7 = " LIMIT ";
        r2 = r2.append(r7);	 Catch:{ all -> 0x01b7 }
        r0 = r17;
        r2 = r2.append(r0);	 Catch:{ all -> 0x01b7 }
        r7 = " OFFSET ";
        r2 = r2.append(r7);	 Catch:{ all -> 0x01b7 }
        r0 = r16;
        r2 = r2.append(r0);	 Catch:{ all -> 0x01b7 }
        r7 = ";";
        r2 = r2.append(r7);	 Catch:{ all -> 0x01b7 }
        r7 = r2.toString();	 Catch:{ all -> 0x01b7 }
        r8 = 0;
        r2 = r3 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x01cb }
        if (r2 != 0) goto L_0x01a2;
    L_0x0072:
        r2 = r3.rawQuery(r7, r8);	 Catch:{ Exception -> 0x01cb }
    L_0x0076:
        r4 = r2.moveToNext();	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        if (r4 == 0) goto L_0x01ac;
    L_0x007c:
        r4 = new com.baidu.android.pushservice.i.g;	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r4.<init>();	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1559d.actionId;	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getInt(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r4.mo13871a(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1559d.actionName;	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getString(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r4.mo13873a(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1559d.appid;	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getString(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r4.mo13887f(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1559d.channel;	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getString(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r4.mo13889g(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1559d.errorCode;	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getInt(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r4.mo13884e(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1559d.errorMsg;	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getString(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r4.mo13882d(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1559d.msgId;	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getString(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r4.mo13879c(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1559d.msgLen;	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getInt(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r4.mo13878c(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1559d.msgType;	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getInt(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r4.mo13875b(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1559d.networkStatus;	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getString(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r4.mo13876b(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1559d.openByPackageName;	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getString(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r4.mo13890h(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1559d.requestId;	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getString(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r4.mo13885e(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1559d.stableHeartInterval;	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getInt(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r4.mo13881d(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1559d.timeStamp;	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r8 = r2.getLong(r7);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r4.mo13872a(r8);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        r5.add(r4);	 Catch:{ Exception -> 0x0174, all -> 0x01c6 }
        goto L_0x0076;
    L_0x0174:
        r4 = move-exception;
        r10 = r4;
        r4 = r2;
        r2 = r10;
    L_0x0178:
        r7 = "PushDatabase";
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01ba }
        r8.<init>();	 Catch:{ all -> 0x01ba }
        r9 = "e getBehaviorEnumClassList ";
        r8 = r8.append(r9);	 Catch:{ all -> 0x01ba }
        r2 = r2.getMessage();	 Catch:{ all -> 0x01ba }
        r2 = r8.append(r2);	 Catch:{ all -> 0x01ba }
        r2 = r2.toString();	 Catch:{ all -> 0x01ba }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r7, r2);	 Catch:{ all -> 0x01ba }
        if (r4 == 0) goto L_0x0199;
    L_0x0196:
        r4.close();	 Catch:{ all -> 0x01b7 }
    L_0x0199:
        if (r3 == 0) goto L_0x019e;
    L_0x019b:
        r3.close();	 Catch:{ all -> 0x01b7 }
    L_0x019e:
        monitor-exit(r6);	 Catch:{ all -> 0x01b7 }
        r2 = r5;
        goto L_0x000c;
    L_0x01a2:
        r0 = r3;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x01cb }
        r2 = r0;
        r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.rawQuery(r2, r7, r8);	 Catch:{ Exception -> 0x01cb }
        goto L_0x0076;
    L_0x01ac:
        if (r2 == 0) goto L_0x01b1;
    L_0x01ae:
        r2.close();	 Catch:{ all -> 0x01b7 }
    L_0x01b1:
        if (r3 == 0) goto L_0x019e;
    L_0x01b3:
        r3.close();	 Catch:{ all -> 0x01b7 }
        goto L_0x019e;
    L_0x01b7:
        r2 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x01b7 }
        throw r2;
    L_0x01ba:
        r2 = move-exception;
    L_0x01bb:
        if (r4 == 0) goto L_0x01c0;
    L_0x01bd:
        r4.close();	 Catch:{ all -> 0x01b7 }
    L_0x01c0:
        if (r3 == 0) goto L_0x01c5;
    L_0x01c2:
        r3.close();	 Catch:{ all -> 0x01b7 }
    L_0x01c5:
        throw r2;	 Catch:{ all -> 0x01b7 }
    L_0x01c6:
        r4 = move-exception;
        r10 = r4;
        r4 = r2;
        r2 = r10;
        goto L_0x01bb;
    L_0x01cb:
        r2 = move-exception;
        goto L_0x0178;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1568q.m6995a(android.content.Context, long, long, int, int):java.util.List");
    }

    /* renamed from: a */
    public static void m6996a() {
        synchronized (f5511c) {
            try {
                if (f5509a != null) {
                    f5509a.close();
                    f5509a = null;
                }
            } catch (Exception e) {
                f5509a = null;
                C1425a.m6444e("PushDatabase", "close db: " + e);
            }
        }
    }

    /* renamed from: a */
    private static void m6997a(final String str, Context context) {
        File parentFile = context.getDatabasePath("pushstat_5.2.0.db").getParentFile();
        if (parentFile != null && parentFile.isDirectory()) {
            File[] listFiles = parentFile.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    if (file == null) {
                        return false;
                    }
                    String name = file.getName();
                    return (name == null || !name.contains("pushstat") || name.contains(str)) ? false : true;
                }
            });
            if (listFiles != null) {
                for (File file : listFiles) {
                    if (!file.isDirectory()) {
                        file.delete();
                    }
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ab A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:4:0x004f, PHI: r4 } */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:28:0x00ab, code skipped:
            r1 = th;
     */
    /* JADX WARNING: Missing block: B:34:0x00b5, code skipped:
            r1 = e;
     */
    /* JADX WARNING: Missing block: B:35:0x00b6, code skipped:
            r2 = r4;
     */
    /* renamed from: a */
    private static boolean m6998a(android.database.sqlite.SQLiteDatabase r7, com.baidu.android.pushservice.p037i.C1446k r8) {
        /*
        r4 = 0;
        r3 = 0;
        r2 = 1;
        if (r7 != 0) goto L_0x0007;
    L_0x0005:
        r1 = r2;
    L_0x0006:
        return r1;
    L_0x0007:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r5 = "SELECT * FROM PushBehavior WHERE ";
        r1 = r1.append(r5);
        r5 = com.baidu.android.pushservice.util.C1568q.C1559d.actionName;
        r5 = r5.name();
        r1 = r1.append(r5);
        r5 = " = '";
        r1 = r1.append(r5);
        r5 = r8.f5036f;
        r1 = r1.append(r5);
        r5 = "' AND ";
        r1 = r1.append(r5);
        r5 = com.baidu.android.pushservice.util.C1568q.C1559d.errorCode;
        r5 = r5.name();
        r1 = r1.append(r5);
        r5 = " = ";
        r1 = r1.append(r5);
        r5 = r8.f5039i;
        r1 = r1.append(r5);
        r5 = ";";
        r1 = r1.append(r5);
        r5 = r1.toString();
        r6 = 0;
        r1 = r7 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x008b, all -> 0x00ab }
        if (r1 != 0) goto L_0x007a;
    L_0x0053:
        r4 = r7.rawQuery(r5, r6);	 Catch:{ Exception -> 0x008b, all -> 0x00ab }
    L_0x0057:
        r1 = r4.moveToNext();	 Catch:{ Exception -> 0x00b5, all -> 0x00ab }
        if (r1 == 0) goto L_0x00b8;
    L_0x005d:
        r1 = com.baidu.android.pushservice.util.C1568q.C1559d.stableHeartInterval;	 Catch:{ Exception -> 0x00b5, all -> 0x00ab }
        r1 = r1.name();	 Catch:{ Exception -> 0x00b5, all -> 0x00ab }
        r1 = r4.getColumnIndex(r1);	 Catch:{ Exception -> 0x00b5, all -> 0x00ab }
        r1 = r4.getInt(r1);	 Catch:{ Exception -> 0x00b5, all -> 0x00ab }
        r8.f5105a = r1;	 Catch:{ Exception -> 0x00b5, all -> 0x00ab }
        com.baidu.android.pushservice.util.C1568q.m7000b(r7, r8);	 Catch:{ Exception -> 0x00b5, all -> 0x00ab }
        r1 = r2;
    L_0x0071:
        if (r1 == 0) goto L_0x0083;
    L_0x0073:
        if (r4 == 0) goto L_0x0078;
    L_0x0075:
        r4.close();
    L_0x0078:
        r1 = r2;
        goto L_0x0006;
    L_0x007a:
        r0 = r7;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x008b, all -> 0x00ab }
        r1 = r0;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.rawQuery(r1, r5, r6);	 Catch:{ Exception -> 0x008b, all -> 0x00ab }
        goto L_0x0057;
    L_0x0083:
        if (r4 == 0) goto L_0x0088;
    L_0x0085:
        r4.close();
    L_0x0088:
        r1 = r3;
        goto L_0x0006;
    L_0x008b:
        r1 = move-exception;
        r2 = r4;
    L_0x008d:
        r4 = "PushDatabase";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00b2 }
        r5.<init>();	 Catch:{ all -> 0x00b2 }
        r6 = "needToInsertUpdatePromptBehavior Exception: ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x00b2 }
        r1 = r5.append(r1);	 Catch:{ all -> 0x00b2 }
        r1 = r1.toString();	 Catch:{ all -> 0x00b2 }
        com.baidu.android.pushservice.p036h.C1425a.m6441b(r4, r1);	 Catch:{ all -> 0x00b2 }
        if (r2 == 0) goto L_0x0088;
    L_0x00a7:
        r2.close();
        goto L_0x0088;
    L_0x00ab:
        r1 = move-exception;
    L_0x00ac:
        if (r4 == 0) goto L_0x00b1;
    L_0x00ae:
        r4.close();
    L_0x00b1:
        throw r1;
    L_0x00b2:
        r1 = move-exception;
        r4 = r2;
        goto L_0x00ac;
    L_0x00b5:
        r1 = move-exception;
        r2 = r4;
        goto L_0x008d;
    L_0x00b8:
        r1 = r3;
        goto L_0x0071;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1568q.m6998a(android.database.sqlite.SQLiteDatabase, com.baidu.android.pushservice.i.k):boolean");
    }

    /* renamed from: b */
    public static int m6999b(Context context, String str) {
        int i = 0;
        synchronized (f5511c) {
            SQLiteDatabase e = C1568q.m7009e(context);
            if (e == null) {
            } else {
                String[] strArr = new String[]{str};
                try {
                    String str2 = "FileDownloadingInfo";
                    String str3 = C1562g.downloadUrl.name() + "=?";
                    i = !(e instanceof SQLiteDatabase) ? e.delete(str2, str3, strArr) : SQLiteInstrumentation.delete(e, str2, str3, strArr);
                } catch (Exception e2) {
                    C1425a.m6444e("PushDatabase", "exception " + e2);
                    i = -1;
                }
                e.close();
            }
        }
        return i;
    }

    /* renamed from: b */
    private static int m7000b(SQLiteDatabase sQLiteDatabase, C1446k c1446k) {
        if (sQLiteDatabase == null) {
            return 0;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(C1559d.actionName.name(), c1446k.f5036f);
        contentValues.put(C1559d.timeStamp.name(), Long.valueOf(c1446k.f5037g));
        contentValues.put(C1559d.networkStatus.name(), c1446k.f5038h);
        contentValues.put(C1559d.stableHeartInterval.name(), Integer.valueOf(c1446k.f5105a + 1));
        contentValues.put(C1559d.errorCode.name(), Integer.valueOf(c1446k.f5039i));
        contentValues.put(C1559d.appid.name(), c1446k.f5040j);
        try {
            String str = "PushBehavior";
            String str2 = C1559d.actionName.name() + " = " + "'" + c1446k.f5036f + "' AND " + C1559d.errorCode.name() + " = " + c1446k.f5039i + ";";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.update(sQLiteDatabase, str, contentValues, str2, null);
                return -1;
            }
            sQLiteDatabase.update(str, contentValues, str2, null);
            return -1;
        } catch (Exception e) {
            C1425a.m6441b("PushDatabase", "updatePromptBehavior Exception: " + e);
            return -1;
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: b */
    public static long m7001b(android.content.Context r10, com.baidu.android.pushservice.p037i.C1446k r11) {
        /*
        r4 = 0;
        r6 = f5511c;
        monitor-enter(r6);
        r3 = com.baidu.android.pushservice.util.C1568q.m7009e(r10);	 Catch:{ all -> 0x009e }
        if (r3 != 0) goto L_0x000f;
    L_0x000b:
        r2 = -1;
        monitor-exit(r6);	 Catch:{ all -> 0x009e }
    L_0x000e:
        return r2;
    L_0x000f:
        r2 = com.baidu.android.pushservice.util.C1568q.m6998a(r3, r11);	 Catch:{ all -> 0x009e }
        if (r2 == 0) goto L_0x0018;
    L_0x0015:
        monitor-exit(r6);	 Catch:{ all -> 0x009e }
        r2 = r4;
        goto L_0x000e;
    L_0x0018:
        r7 = new android.content.ContentValues;	 Catch:{ all -> 0x009e }
        r7.<init>();	 Catch:{ all -> 0x009e }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.actionName;	 Catch:{ all -> 0x009e }
        r2 = r2.name();	 Catch:{ all -> 0x009e }
        r8 = r11.f5036f;	 Catch:{ all -> 0x009e }
        r7.put(r2, r8);	 Catch:{ all -> 0x009e }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.timeStamp;	 Catch:{ all -> 0x009e }
        r2 = r2.name();	 Catch:{ all -> 0x009e }
        r8 = r11.f5037g;	 Catch:{ all -> 0x009e }
        r8 = java.lang.Long.valueOf(r8);	 Catch:{ all -> 0x009e }
        r7.put(r2, r8);	 Catch:{ all -> 0x009e }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.networkStatus;	 Catch:{ all -> 0x009e }
        r2 = r2.name();	 Catch:{ all -> 0x009e }
        r8 = r11.f5038h;	 Catch:{ all -> 0x009e }
        r7.put(r2, r8);	 Catch:{ all -> 0x009e }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.stableHeartInterval;	 Catch:{ all -> 0x009e }
        r2 = r2.name();	 Catch:{ all -> 0x009e }
        r8 = 1;
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ all -> 0x009e }
        r7.put(r2, r8);	 Catch:{ all -> 0x009e }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.errorCode;	 Catch:{ all -> 0x009e }
        r2 = r2.name();	 Catch:{ all -> 0x009e }
        r8 = r11.f5039i;	 Catch:{ all -> 0x009e }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ all -> 0x009e }
        r7.put(r2, r8);	 Catch:{ all -> 0x009e }
        r2 = com.baidu.android.pushservice.util.C1568q.C1559d.appid;	 Catch:{ all -> 0x009e }
        r2 = r2.name();	 Catch:{ all -> 0x009e }
        r8 = r11.f5040j;	 Catch:{ all -> 0x009e }
        r7.put(r2, r8);	 Catch:{ all -> 0x009e }
        r8 = "PushBehavior";
        r9 = 0;
        r2 = r3 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0084 }
        if (r2 != 0) goto L_0x007b;
    L_0x0071:
        r4 = r3.insert(r8, r9, r7);	 Catch:{ Exception -> 0x0084 }
    L_0x0075:
        r3.close();	 Catch:{ all -> 0x009e }
        monitor-exit(r6);	 Catch:{ all -> 0x009e }
        r2 = r4;
        goto L_0x000e;
    L_0x007b:
        r0 = r3;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0084 }
        r2 = r0;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.insert(r2, r8, r9, r7);	 Catch:{ Exception -> 0x0084 }
        goto L_0x0075;
    L_0x0084:
        r2 = move-exception;
        r7 = "PushDatabase";
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x009e }
        r8.<init>();	 Catch:{ all -> 0x009e }
        r9 = "insertAgentOrHttpBehavior E: ";
        r8 = r8.append(r9);	 Catch:{ all -> 0x009e }
        r2 = r8.append(r2);	 Catch:{ all -> 0x009e }
        r2 = r2.toString();	 Catch:{ all -> 0x009e }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r7, r2);	 Catch:{ all -> 0x009e }
        goto L_0x0075;
    L_0x009e:
        r2 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x009e }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1568q.m7001b(android.content.Context, com.baidu.android.pushservice.i.k):long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x013f A:{SYNTHETIC, Splitter:B:40:0x013f} */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0144  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x013f A:{SYNTHETIC, Splitter:B:40:0x013f} */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0144  */
    /* renamed from: b */
    public static java.util.List<com.baidu.android.pushservice.util.C1568q.C1563h> m7002b(android.content.Context r22) {
        /*
        r20 = f5511c;
        monitor-enter(r20);
        r18 = new java.util.ArrayList;	 Catch:{ all -> 0x0139 }
        r18.<init>();	 Catch:{ all -> 0x0139 }
        r2 = com.baidu.android.pushservice.util.C1568q.m7009e(r22);	 Catch:{ all -> 0x0139 }
        if (r2 != 0) goto L_0x0012;
    L_0x000e:
        monitor-exit(r20);	 Catch:{ all -> 0x0139 }
        r2 = r18;
    L_0x0011:
        return r2;
    L_0x0012:
        r19 = 0;
        r3 = "FileDownloadingInfo";
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0151, all -> 0x013c }
        r9.<init>();	 Catch:{ Exception -> 0x0151, all -> 0x013c }
        r10 = com.baidu.android.pushservice.util.C1568q.C1562g.timeStamp;	 Catch:{ Exception -> 0x0151, all -> 0x013c }
        r10 = r10.name();	 Catch:{ Exception -> 0x0151, all -> 0x013c }
        r9 = r9.append(r10);	 Catch:{ Exception -> 0x0151, all -> 0x013c }
        r10 = " DESC";
        r9 = r9.append(r10);	 Catch:{ Exception -> 0x0151, all -> 0x013c }
        r9 = r9.toString();	 Catch:{ Exception -> 0x0151, all -> 0x013c }
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0151, all -> 0x013c }
        if (r10 != 0) goto L_0x011b;
    L_0x0038:
        r3 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x0151, all -> 0x013c }
    L_0x003c:
        r4 = r3.moveToNext();	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        if (r4 == 0) goto L_0x012e;
    L_0x0042:
        r4 = new com.baidu.android.pushservice.util.q$h;	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r4.<init>();	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = com.baidu.android.pushservice.util.C1568q.C1562g.belongTo;	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r5.name();	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r3.getColumnIndex(r5);	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r3.getString(r5);	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r4.f5472a = r5;	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = com.baidu.android.pushservice.util.C1568q.C1562g.downloadUrl;	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r5.name();	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r3.getColumnIndex(r5);	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r3.getString(r5);	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r4.f5473b = r5;	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = com.baidu.android.pushservice.util.C1568q.C1562g.title;	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r5.name();	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r3.getColumnIndex(r5);	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r3.getString(r5);	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r4.f5474c = r5;	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = com.baidu.android.pushservice.util.C1568q.C1562g.description;	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r5.name();	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r3.getColumnIndex(r5);	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r3.getString(r5);	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r4.f5475d = r5;	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = com.baidu.android.pushservice.util.C1568q.C1562g.savePath;	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r5.name();	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r3.getColumnIndex(r5);	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r3.getString(r5);	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r4.f5476e = r5;	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = com.baidu.android.pushservice.util.C1568q.C1562g.fileName;	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r5.name();	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r3.getColumnIndex(r5);	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r3.getString(r5);	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r4.f5477f = r5;	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = com.baidu.android.pushservice.util.C1568q.C1562g.downloadBytes;	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r5.name();	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r3.getColumnIndex(r5);	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r3.getInt(r5);	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r4.f5478g = r5;	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = com.baidu.android.pushservice.util.C1568q.C1562g.totalBytes;	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r5.name();	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r3.getColumnIndex(r5);	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r3.getInt(r5);	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r4.f5479h = r5;	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = com.baidu.android.pushservice.util.C1568q.C1562g.downloadStatus;	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r5.name();	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r3.getColumnIndex(r5);	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r3.getInt(r5);	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r4.f5480i = r5;	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = com.baidu.android.pushservice.util.C1568q.C1562g.timeStamp;	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r5.name();	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r5 = r3.getColumnIndex(r5);	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r6 = r3.getLong(r5);	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r4.f5481j = r6;	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        r0 = r18;
        r0.add(r4);	 Catch:{ Exception -> 0x00ee, all -> 0x0148 }
        goto L_0x003c;
    L_0x00ee:
        r4 = move-exception;
        r21 = r4;
        r4 = r3;
        r3 = r21;
    L_0x00f4:
        r5 = "PushDatabase";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x014d }
        r6.<init>();	 Catch:{ all -> 0x014d }
        r7 = "exception ";
        r6 = r6.append(r7);	 Catch:{ all -> 0x014d }
        r3 = r6.append(r3);	 Catch:{ all -> 0x014d }
        r3 = r3.toString();	 Catch:{ all -> 0x014d }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r5, r3);	 Catch:{ all -> 0x014d }
        if (r4 == 0) goto L_0x0111;
    L_0x010e:
        r4.close();	 Catch:{ all -> 0x0139 }
    L_0x0111:
        if (r2 == 0) goto L_0x0116;
    L_0x0113:
        r2.close();	 Catch:{ all -> 0x0139 }
    L_0x0116:
        monitor-exit(r20);	 Catch:{ all -> 0x0139 }
        r2 = r18;
        goto L_0x0011;
    L_0x011b:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0151, all -> 0x013c }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r3 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x0151, all -> 0x013c }
        goto L_0x003c;
    L_0x012e:
        if (r3 == 0) goto L_0x0133;
    L_0x0130:
        r3.close();	 Catch:{ all -> 0x0139 }
    L_0x0133:
        if (r2 == 0) goto L_0x0116;
    L_0x0135:
        r2.close();	 Catch:{ all -> 0x0139 }
        goto L_0x0116;
    L_0x0139:
        r2 = move-exception;
        monitor-exit(r20);	 Catch:{ all -> 0x0139 }
        throw r2;
    L_0x013c:
        r3 = move-exception;
    L_0x013d:
        if (r19 == 0) goto L_0x0142;
    L_0x013f:
        r19.close();	 Catch:{ all -> 0x0139 }
    L_0x0142:
        if (r2 == 0) goto L_0x0147;
    L_0x0144:
        r2.close();	 Catch:{ all -> 0x0139 }
    L_0x0147:
        throw r3;	 Catch:{ all -> 0x0139 }
    L_0x0148:
        r4 = move-exception;
        r19 = r3;
        r3 = r4;
        goto L_0x013d;
    L_0x014d:
        r3 = move-exception;
        r19 = r4;
        goto L_0x013d;
    L_0x0151:
        r3 = move-exception;
        r4 = r19;
        goto L_0x00f4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1568q.m7002b(android.content.Context):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x018a A:{SYNTHETIC, Splitter:B:41:0x018a} */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x018f  */
    /* renamed from: b */
    public static java.util.List<com.baidu.android.pushservice.p037i.C1433a> m7003b(android.content.Context r11, long r12, long r14, int r16, int r17) {
        /*
        r4 = 0;
        r6 = f5511c;
        monitor-enter(r6);
        r3 = com.baidu.android.pushservice.util.C1568q.m7009e(r11);	 Catch:{ all -> 0x0184 }
        if (r3 != 0) goto L_0x000d;
    L_0x000a:
        monitor-exit(r6);	 Catch:{ all -> 0x0184 }
        r2 = r4;
    L_0x000c:
        return r2;
    L_0x000d:
        r5 = new java.util.ArrayList;	 Catch:{ all -> 0x0184 }
        r5.<init>();	 Catch:{ all -> 0x0184 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0184 }
        r2.<init>();	 Catch:{ all -> 0x0184 }
        r7 = "SELECT * FROM ADPushBehavior WHERE ";
        r2 = r2.append(r7);	 Catch:{ all -> 0x0184 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1556a.timeStamp;	 Catch:{ all -> 0x0184 }
        r7 = r7.name();	 Catch:{ all -> 0x0184 }
        r2 = r2.append(r7);	 Catch:{ all -> 0x0184 }
        r7 = " < ";
        r2 = r2.append(r7);	 Catch:{ all -> 0x0184 }
        r2 = r2.append(r12);	 Catch:{ all -> 0x0184 }
        r7 = " AND ";
        r2 = r2.append(r7);	 Catch:{ all -> 0x0184 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1556a.timeStamp;	 Catch:{ all -> 0x0184 }
        r7 = r7.name();	 Catch:{ all -> 0x0184 }
        r2 = r2.append(r7);	 Catch:{ all -> 0x0184 }
        r7 = " >= ";
        r2 = r2.append(r7);	 Catch:{ all -> 0x0184 }
        r2 = r2.append(r14);	 Catch:{ all -> 0x0184 }
        r7 = " LIMIT ";
        r2 = r2.append(r7);	 Catch:{ all -> 0x0184 }
        r0 = r17;
        r2 = r2.append(r0);	 Catch:{ all -> 0x0184 }
        r7 = " OFFSET ";
        r2 = r2.append(r7);	 Catch:{ all -> 0x0184 }
        r0 = r16;
        r2 = r2.append(r0);	 Catch:{ all -> 0x0184 }
        r7 = ";";
        r2 = r2.append(r7);	 Catch:{ all -> 0x0184 }
        r7 = r2.toString();	 Catch:{ all -> 0x0184 }
        r8 = 0;
        r2 = r3 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0198 }
        if (r2 != 0) goto L_0x016f;
    L_0x0072:
        r2 = r3.rawQuery(r7, r8);	 Catch:{ Exception -> 0x0198 }
    L_0x0076:
        r4 = r2.moveToNext();	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        if (r4 == 0) goto L_0x0179;
    L_0x007c:
        r4 = new com.baidu.android.pushservice.i.a;	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r4.<init>();	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1556a.actionId;	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r2.getInt(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r4.mo13835a(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1556a.actionName;	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r2.getString(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r4.mo13837a(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1556a.appid;	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r2.getString(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r4.mo13848e(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1556a.errorCode;	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r2.getInt(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r4.mo13845d(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1556a.msgId;	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r2.getString(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r4.mo13843c(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1556a.msgLen;	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r2.getInt(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r4.mo13842c(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1556a.msgType;	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r2.getInt(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r4.mo13839b(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1556a.networkStatus;	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r2.getString(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r4.mo13840b(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1556a.actionType;	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r2.getString(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r4.mo13849f(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1556a.advertiseStyle;	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r2.getString(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r4.mo13846d(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = com.baidu.android.pushservice.util.C1568q.C1556a.timeStamp;	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r7.name();	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r8 = r2.getLong(r7);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r4.mo13836a(r8);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        r5.add(r4);	 Catch:{ Exception -> 0x0141, all -> 0x0193 }
        goto L_0x0076;
    L_0x0141:
        r4 = move-exception;
        r10 = r4;
        r4 = r2;
        r2 = r10;
    L_0x0145:
        r7 = "PushDatabase";
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0187 }
        r8.<init>();	 Catch:{ all -> 0x0187 }
        r9 = "e getADBehaviorEnumClassList ";
        r8 = r8.append(r9);	 Catch:{ all -> 0x0187 }
        r2 = r2.getMessage();	 Catch:{ all -> 0x0187 }
        r2 = r8.append(r2);	 Catch:{ all -> 0x0187 }
        r2 = r2.toString();	 Catch:{ all -> 0x0187 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r7, r2);	 Catch:{ all -> 0x0187 }
        if (r4 == 0) goto L_0x0166;
    L_0x0163:
        r4.close();	 Catch:{ all -> 0x0184 }
    L_0x0166:
        if (r3 == 0) goto L_0x016b;
    L_0x0168:
        r3.close();	 Catch:{ all -> 0x0184 }
    L_0x016b:
        monitor-exit(r6);	 Catch:{ all -> 0x0184 }
        r2 = r5;
        goto L_0x000c;
    L_0x016f:
        r0 = r3;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0198 }
        r2 = r0;
        r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.rawQuery(r2, r7, r8);	 Catch:{ Exception -> 0x0198 }
        goto L_0x0076;
    L_0x0179:
        if (r2 == 0) goto L_0x017e;
    L_0x017b:
        r2.close();	 Catch:{ all -> 0x0184 }
    L_0x017e:
        if (r3 == 0) goto L_0x016b;
    L_0x0180:
        r3.close();	 Catch:{ all -> 0x0184 }
        goto L_0x016b;
    L_0x0184:
        r2 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0184 }
        throw r2;
    L_0x0187:
        r2 = move-exception;
    L_0x0188:
        if (r4 == 0) goto L_0x018d;
    L_0x018a:
        r4.close();	 Catch:{ all -> 0x0184 }
    L_0x018d:
        if (r3 == 0) goto L_0x0192;
    L_0x018f:
        r3.close();	 Catch:{ all -> 0x0184 }
    L_0x0192:
        throw r2;	 Catch:{ all -> 0x0184 }
    L_0x0193:
        r4 = move-exception;
        r10 = r4;
        r4 = r2;
        r2 = r10;
        goto L_0x0188;
    L_0x0198:
        r2 = move-exception;
        goto L_0x0145;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1568q.m7003b(android.content.Context, long, long, int, int):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:83:0x018e A:{Catch:{ Exception -> 0x00ae, all -> 0x00de }} */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0193 A:{Catch:{ Exception -> 0x00ae, all -> 0x00de }} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00f2 A:{SYNTHETIC, Splitter:B:53:0x00f2} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00aa A:{SKIP, Catch:{ Exception -> 0x00ae, all -> 0x00de }} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00e4 A:{Catch:{ Exception -> 0x00ae, all -> 0x00de }} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00e4 A:{Catch:{ Exception -> 0x00ae, all -> 0x00de }} */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: b */
    public static boolean m7004b(android.content.Context r11, long r12, long r14) {
        /*
        r4 = 1;
        r5 = 0;
        r3 = 0;
        r6 = f5511c;
        monitor-enter(r6);
        r2 = com.baidu.android.pushservice.util.C1568q.m7009e(r11);	 Catch:{ all -> 0x00ef }
        if (r2 != 0) goto L_0x000f;
    L_0x000c:
        monitor-exit(r6);	 Catch:{ all -> 0x00ef }
        r1 = r3;
    L_0x000e:
        return r1;
    L_0x000f:
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ef }
        r1.<init>();	 Catch:{ all -> 0x00ef }
        r7 = "SELECT ";
        r1 = r1.append(r7);	 Catch:{ all -> 0x00ef }
        r7 = com.baidu.android.pushservice.util.C1568q.C1559d.actionName;	 Catch:{ all -> 0x00ef }
        r7 = r7.name();	 Catch:{ all -> 0x00ef }
        r1 = r1.append(r7);	 Catch:{ all -> 0x00ef }
        r7 = " FROM ";
        r1 = r1.append(r7);	 Catch:{ all -> 0x00ef }
        r7 = "PushBehavior";
        r1 = r1.append(r7);	 Catch:{ all -> 0x00ef }
        r7 = " WHERE ";
        r1 = r1.append(r7);	 Catch:{ all -> 0x00ef }
        r7 = com.baidu.android.pushservice.util.C1568q.C1559d.timeStamp;	 Catch:{ all -> 0x00ef }
        r7 = r7.name();	 Catch:{ all -> 0x00ef }
        r1 = r1.append(r7);	 Catch:{ all -> 0x00ef }
        r7 = " < ";
        r1 = r1.append(r7);	 Catch:{ all -> 0x00ef }
        r1 = r1.append(r12);	 Catch:{ all -> 0x00ef }
        r7 = " AND ";
        r1 = r1.append(r7);	 Catch:{ all -> 0x00ef }
        r7 = com.baidu.android.pushservice.util.C1568q.C1559d.timeStamp;	 Catch:{ all -> 0x00ef }
        r7 = r7.name();	 Catch:{ all -> 0x00ef }
        r1 = r1.append(r7);	 Catch:{ all -> 0x00ef }
        r7 = " >= ";
        r1 = r1.append(r7);	 Catch:{ all -> 0x00ef }
        r1 = r1.append(r14);	 Catch:{ all -> 0x00ef }
        r7 = " ;";
        r1 = r1.append(r7);	 Catch:{ all -> 0x00ef }
        r7 = r1.toString();	 Catch:{ all -> 0x00ef }
        r8 = 0;
        r1 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00ae, all -> 0x00de }
        if (r1 != 0) goto L_0x0092;
    L_0x0073:
        r1 = r2.rawQuery(r7, r8);	 Catch:{ Exception -> 0x00ae, all -> 0x00de }
    L_0x0077:
        r5 = r1.moveToNext();	 Catch:{ Exception -> 0x01a2, all -> 0x0199 }
        if (r5 == 0) goto L_0x009b;
    L_0x007d:
        r5 = 0;
        r5 = r1.getString(r5);	 Catch:{ Exception -> 0x01a2, all -> 0x0199 }
        r7 = android.text.TextUtils.isEmpty(r5);	 Catch:{ Exception -> 0x01a2, all -> 0x0199 }
        if (r7 != 0) goto L_0x0077;
    L_0x0088:
        r7 = com.baidu.android.pushservice.p037i.C1435r.f5050t;	 Catch:{ Exception -> 0x01a2, all -> 0x0199 }
        r5 = r5.startsWith(r7);	 Catch:{ Exception -> 0x01a2, all -> 0x0199 }
        if (r5 != 0) goto L_0x0077;
    L_0x0090:
        r3 = r4;
        goto L_0x0077;
    L_0x0092:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00ae, all -> 0x00de }
        r1 = r0;
        r1 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.rawQuery(r1, r7, r8);	 Catch:{ Exception -> 0x00ae, all -> 0x00de }
        goto L_0x0077;
    L_0x009b:
        if (r1 == 0) goto L_0x00a0;
    L_0x009d:
        r1.close();	 Catch:{ all -> 0x00ef }
    L_0x00a0:
        if (r3 == 0) goto L_0x01b0;
    L_0x00a2:
        if (r2 == 0) goto L_0x01b0;
    L_0x00a4:
        r2.close();	 Catch:{ all -> 0x00ef }
        r5 = r1;
    L_0x00a8:
        if (r3 == 0) goto L_0x00f2;
    L_0x00aa:
        monitor-exit(r6);	 Catch:{ all -> 0x00ef }
        r1 = r3;
        goto L_0x000e;
    L_0x00ae:
        r1 = move-exception;
        r10 = r5;
        r5 = r3;
        r3 = r10;
    L_0x00b2:
        r7 = "PushDatabase";
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x019f }
        r8.<init>();	 Catch:{ all -> 0x019f }
        r9 = "e isNeedUpload ";
        r8 = r8.append(r9);	 Catch:{ all -> 0x019f }
        r1 = r1.getMessage();	 Catch:{ all -> 0x019f }
        r1 = r8.append(r1);	 Catch:{ all -> 0x019f }
        r1 = r1.toString();	 Catch:{ all -> 0x019f }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r7, r1);	 Catch:{ all -> 0x019f }
        if (r3 == 0) goto L_0x00d3;
    L_0x00d0:
        r3.close();	 Catch:{ all -> 0x00ef }
    L_0x00d3:
        if (r5 == 0) goto L_0x01ab;
    L_0x00d5:
        if (r2 == 0) goto L_0x01ab;
    L_0x00d7:
        r2.close();	 Catch:{ all -> 0x00ef }
        r10 = r3;
        r3 = r5;
        r5 = r10;
        goto L_0x00a8;
    L_0x00de:
        r1 = move-exception;
        r10 = r5;
        r5 = r3;
        r3 = r10;
    L_0x00e2:
        if (r3 == 0) goto L_0x00e7;
    L_0x00e4:
        r3.close();	 Catch:{ all -> 0x00ef }
    L_0x00e7:
        if (r5 == 0) goto L_0x00ee;
    L_0x00e9:
        if (r2 == 0) goto L_0x00ee;
    L_0x00eb:
        r2.close();	 Catch:{ all -> 0x00ef }
    L_0x00ee:
        throw r1;	 Catch:{ all -> 0x00ef }
    L_0x00ef:
        r1 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x00ef }
        throw r1;
    L_0x00f2:
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ef }
        r1.<init>();	 Catch:{ all -> 0x00ef }
        r7 = "SELECT COUNT(*) FROM ADPushBehavior WHERE ";
        r1 = r1.append(r7);	 Catch:{ all -> 0x00ef }
        r7 = com.baidu.android.pushservice.util.C1568q.C1556a.timeStamp;	 Catch:{ all -> 0x00ef }
        r7 = r7.name();	 Catch:{ all -> 0x00ef }
        r1 = r1.append(r7);	 Catch:{ all -> 0x00ef }
        r7 = " < ";
        r1 = r1.append(r7);	 Catch:{ all -> 0x00ef }
        r1 = r1.append(r12);	 Catch:{ all -> 0x00ef }
        r7 = " AND ";
        r1 = r1.append(r7);	 Catch:{ all -> 0x00ef }
        r7 = com.baidu.android.pushservice.util.C1568q.C1556a.timeStamp;	 Catch:{ all -> 0x00ef }
        r7 = r7.name();	 Catch:{ all -> 0x00ef }
        r1 = r1.append(r7);	 Catch:{ all -> 0x00ef }
        r7 = " >= ";
        r1 = r1.append(r7);	 Catch:{ all -> 0x00ef }
        r1 = r1.append(r14);	 Catch:{ all -> 0x00ef }
        r7 = " ;";
        r1 = r1.append(r7);	 Catch:{ all -> 0x00ef }
        r7 = r1.toString();	 Catch:{ all -> 0x00ef }
        r8 = 0;
        r1 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0160, all -> 0x018a }
        if (r1 != 0) goto L_0x0157;
    L_0x013a:
        r5 = r2.rawQuery(r7, r8);	 Catch:{ Exception -> 0x0160, all -> 0x018a }
    L_0x013e:
        r5.moveToFirst();	 Catch:{ Exception -> 0x0160, all -> 0x018a }
        r1 = 0;
        r1 = r5.getInt(r1);	 Catch:{ Exception -> 0x0160, all -> 0x018a }
        if (r1 <= 0) goto L_0x0149;
    L_0x0148:
        r3 = r4;
    L_0x0149:
        if (r5 == 0) goto L_0x014e;
    L_0x014b:
        r5.close();	 Catch:{ all -> 0x00ef }
    L_0x014e:
        if (r2 == 0) goto L_0x01a9;
    L_0x0150:
        r2.close();	 Catch:{ all -> 0x00ef }
        r1 = r3;
    L_0x0154:
        monitor-exit(r6);	 Catch:{ all -> 0x00ef }
        goto L_0x000e;
    L_0x0157:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0160, all -> 0x018a }
        r1 = r0;
        r5 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.rawQuery(r1, r7, r8);	 Catch:{ Exception -> 0x0160, all -> 0x018a }
        goto L_0x013e;
    L_0x0160:
        r1 = move-exception;
        r4 = r5;
        r5 = "PushDatabase";
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0197 }
        r7.<init>();	 Catch:{ all -> 0x0197 }
        r8 = "e isNeedUpload";
        r7 = r7.append(r8);	 Catch:{ all -> 0x0197 }
        r1 = r1.getMessage();	 Catch:{ all -> 0x0197 }
        r1 = r7.append(r1);	 Catch:{ all -> 0x0197 }
        r1 = r1.toString();	 Catch:{ all -> 0x0197 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r5, r1);	 Catch:{ all -> 0x0197 }
        if (r4 == 0) goto L_0x0183;
    L_0x0180:
        r4.close();	 Catch:{ all -> 0x00ef }
    L_0x0183:
        if (r2 == 0) goto L_0x01a9;
    L_0x0185:
        r2.close();	 Catch:{ all -> 0x00ef }
        r1 = r3;
        goto L_0x0154;
    L_0x018a:
        r1 = move-exception;
        r4 = r5;
    L_0x018c:
        if (r4 == 0) goto L_0x0191;
    L_0x018e:
        r4.close();	 Catch:{ all -> 0x00ef }
    L_0x0191:
        if (r2 == 0) goto L_0x0196;
    L_0x0193:
        r2.close();	 Catch:{ all -> 0x00ef }
    L_0x0196:
        throw r1;	 Catch:{ all -> 0x00ef }
    L_0x0197:
        r1 = move-exception;
        goto L_0x018c;
    L_0x0199:
        r4 = move-exception;
        r5 = r3;
        r3 = r1;
        r1 = r4;
        goto L_0x00e2;
    L_0x019f:
        r1 = move-exception;
        goto L_0x00e2;
    L_0x01a2:
        r5 = move-exception;
        r10 = r5;
        r5 = r3;
        r3 = r1;
        r1 = r10;
        goto L_0x00b2;
    L_0x01a9:
        r1 = r3;
        goto L_0x0154;
    L_0x01ab:
        r10 = r3;
        r3 = r5;
        r5 = r10;
        goto L_0x00a8;
    L_0x01b0:
        r5 = r1;
        goto L_0x00a8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1568q.m7004b(android.content.Context, long, long):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x0117 A:{SYNTHETIC, Splitter:B:52:0x0117} */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x011c  */
    /* JADX WARNING: Missing block: B:60:?, code skipped:
            return null;
     */
    /* renamed from: c */
    public static com.baidu.android.pushservice.p037i.C1437c m7005c(android.content.Context r21, java.lang.String r22) {
        /*
        r20 = f5511c;
        monitor-enter(r20);
        r2 = com.baidu.android.pushservice.util.C1568q.m7009e(r21);	 Catch:{ all -> 0x010f }
        if (r2 != 0) goto L_0x000c;
    L_0x0009:
        r2 = 0;
        monitor-exit(r20);	 Catch:{ all -> 0x010f }
    L_0x000b:
        return r2;
    L_0x000c:
        r18 = new com.baidu.android.pushservice.i.c;	 Catch:{ all -> 0x010f }
        r18.<init>();	 Catch:{ all -> 0x010f }
        r19 = 0;
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00fc, all -> 0x0112 }
        r3.<init>();	 Catch:{ Exception -> 0x00fc, all -> 0x0112 }
        r4 = com.baidu.android.pushservice.util.C1568q.C1557b.msgId;	 Catch:{ Exception -> 0x00fc, all -> 0x0112 }
        r4 = r4.name();	 Catch:{ Exception -> 0x00fc, all -> 0x0112 }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x00fc, all -> 0x0112 }
        r4 = " = ";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x00fc, all -> 0x0112 }
        r0 = r22;
        r3 = r3.append(r0);	 Catch:{ Exception -> 0x00fc, all -> 0x0112 }
        r4 = ";";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x00fc, all -> 0x0112 }
        r5 = r3.toString();	 Catch:{ Exception -> 0x00fc, all -> 0x0112 }
        r3 = "AlarmMsgInfo";
        r4 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00fc, all -> 0x0112 }
        if (r10 != 0) goto L_0x005e;
    L_0x0043:
        r4 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00fc, all -> 0x0112 }
    L_0x0047:
        if (r4 != 0) goto L_0x0070;
    L_0x0049:
        r3 = "PushDatabase";
        r5 = "no msgid info table!!";
        com.baidu.android.pushservice.p036h.C1425a.m6443d(r3, r5);	 Catch:{ Exception -> 0x0122 }
        r3 = 0;
        if (r4 == 0) goto L_0x0056;
    L_0x0053:
        r4.close();	 Catch:{ all -> 0x010f }
    L_0x0056:
        if (r2 == 0) goto L_0x005b;
    L_0x0058:
        r2.close();	 Catch:{ all -> 0x010f }
    L_0x005b:
        monitor-exit(r20);	 Catch:{ all -> 0x010f }
        r2 = r3;
        goto L_0x000b;
    L_0x005e:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00fc, all -> 0x0112 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x00fc, all -> 0x0112 }
        goto L_0x0047;
    L_0x0070:
        r3 = r4.getCount();	 Catch:{ Exception -> 0x0122 }
        if (r3 <= 0) goto L_0x00ed;
    L_0x0076:
        r3 = "PushDatabase";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0122 }
        r5.<init>();	 Catch:{ Exception -> 0x0122 }
        r6 = "getAlarmMsgInfo  msgID = ";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0122 }
        r0 = r22;
        r5 = r5.append(r0);	 Catch:{ Exception -> 0x0122 }
        r5 = r5.toString();	 Catch:{ Exception -> 0x0122 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r3, r5);	 Catch:{ Exception -> 0x0122 }
        r4.moveToFirst();	 Catch:{ Exception -> 0x0122 }
        r3 = com.baidu.android.pushservice.util.C1568q.C1557b.sendtime;	 Catch:{ Exception -> 0x0122 }
        r3 = r3.name();	 Catch:{ Exception -> 0x0122 }
        r3 = r4.getColumnIndex(r3);	 Catch:{ Exception -> 0x0122 }
        r6 = r4.getLong(r3);	 Catch:{ Exception -> 0x0122 }
        r0 = r18;
        r0.f5058b = r6;	 Catch:{ Exception -> 0x0122 }
        r3 = com.baidu.android.pushservice.util.C1568q.C1557b.showtime;	 Catch:{ Exception -> 0x0122 }
        r3 = r3.name();	 Catch:{ Exception -> 0x0122 }
        r3 = r4.getColumnIndex(r3);	 Catch:{ Exception -> 0x0122 }
        r6 = r4.getLong(r3);	 Catch:{ Exception -> 0x0122 }
        r0 = r18;
        r0.f5059c = r6;	 Catch:{ Exception -> 0x0122 }
        r3 = com.baidu.android.pushservice.util.C1568q.C1557b.expiretime;	 Catch:{ Exception -> 0x0122 }
        r3 = r3.name();	 Catch:{ Exception -> 0x0122 }
        r3 = r4.getColumnIndex(r3);	 Catch:{ Exception -> 0x0122 }
        r6 = r4.getLong(r3);	 Catch:{ Exception -> 0x0122 }
        r0 = r18;
        r0.f5060d = r6;	 Catch:{ Exception -> 0x0122 }
        r3 = com.baidu.android.pushservice.util.C1568q.C1557b.isAlarm;	 Catch:{ Exception -> 0x0122 }
        r3 = r3.name();	 Catch:{ Exception -> 0x0122 }
        r3 = r4.getColumnIndex(r3);	 Catch:{ Exception -> 0x0122 }
        r3 = r4.getInt(r3);	 Catch:{ Exception -> 0x0122 }
        r0 = r18;
        r0.f5061e = r3;	 Catch:{ Exception -> 0x0122 }
        r3 = com.baidu.android.pushservice.util.C1568q.C1557b.msgEnable;	 Catch:{ Exception -> 0x0122 }
        r3 = r3.name();	 Catch:{ Exception -> 0x0122 }
        r3 = r4.getColumnIndex(r3);	 Catch:{ Exception -> 0x0122 }
        r3 = r4.getInt(r3);	 Catch:{ Exception -> 0x0122 }
        r0 = r18;
        r0.f5062f = r3;	 Catch:{ Exception -> 0x0122 }
    L_0x00ed:
        if (r4 == 0) goto L_0x00f2;
    L_0x00ef:
        r4.close();	 Catch:{ all -> 0x010f }
    L_0x00f2:
        if (r2 == 0) goto L_0x00f7;
    L_0x00f4:
        r2.close();	 Catch:{ all -> 0x010f }
    L_0x00f7:
        monitor-exit(r20);	 Catch:{ all -> 0x010f }
        r2 = r18;
        goto L_0x000b;
    L_0x00fc:
        r3 = move-exception;
        r4 = r19;
    L_0x00ff:
        r5 = "PushDatabase";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r5, r3);	 Catch:{ all -> 0x0120 }
        if (r4 == 0) goto L_0x0109;
    L_0x0106:
        r4.close();	 Catch:{ all -> 0x010f }
    L_0x0109:
        if (r2 == 0) goto L_0x00f7;
    L_0x010b:
        r2.close();	 Catch:{ all -> 0x010f }
        goto L_0x00f7;
    L_0x010f:
        r2 = move-exception;
        monitor-exit(r20);	 Catch:{ all -> 0x010f }
        throw r2;
    L_0x0112:
        r3 = move-exception;
        r4 = r19;
    L_0x0115:
        if (r4 == 0) goto L_0x011a;
    L_0x0117:
        r4.close();	 Catch:{ all -> 0x010f }
    L_0x011a:
        if (r2 == 0) goto L_0x011f;
    L_0x011c:
        r2.close();	 Catch:{ all -> 0x010f }
    L_0x011f:
        throw r3;	 Catch:{ all -> 0x010f }
    L_0x0120:
        r3 = move-exception;
        goto L_0x0115;
    L_0x0122:
        r3 = move-exception;
        goto L_0x00ff;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1568q.m7005c(android.content.Context, java.lang.String):com.baidu.android.pushservice.i.c");
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00e2 A:{Catch:{ all -> 0x00df }} */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e7 A:{Catch:{ all -> 0x00df }} */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: c */
    public static void m7006c(android.content.Context r11) {
        /*
        r4 = 0;
        r5 = f5511c;
        monitor-enter(r5);
        r3 = com.baidu.android.pushservice.util.C1568q.m7009e(r11);	 Catch:{ all -> 0x00c7 }
        if (r3 != 0) goto L_0x000c;
    L_0x000a:
        monitor-exit(r5);	 Catch:{ all -> 0x00c7 }
    L_0x000b:
        return;
    L_0x000c:
        r6 = "SELECT * FROM AlarmMsgInfo;";
        r7 = 0;
        r2 = r3 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00f0 }
        if (r2 != 0) goto L_0x00ca;
    L_0x0013:
        r2 = r3.rawQuery(r6, r7);	 Catch:{ Exception -> 0x00f0 }
    L_0x0017:
        r4 = r2.moveToNext();	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        if (r4 == 0) goto L_0x00d4;
    L_0x001d:
        r4 = new com.baidu.android.pushservice.i.c;	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r4.<init>();	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = com.baidu.android.pushservice.util.C1568q.C1557b.msgId;	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = r6.name();	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = r2.getColumnIndex(r6);	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = r2.getString(r6);	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r4.f5057a = r6;	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = com.baidu.android.pushservice.util.C1568q.C1557b.sendtime;	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = r6.name();	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = r2.getColumnIndex(r6);	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = r2.getLong(r6);	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r4.f5058b = r6;	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = com.baidu.android.pushservice.util.C1568q.C1557b.showtime;	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = r6.name();	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = r2.getColumnIndex(r6);	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = r2.getLong(r6);	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r4.f5059c = r6;	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = com.baidu.android.pushservice.util.C1568q.C1557b.expiretime;	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = r6.name();	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = r2.getColumnIndex(r6);	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = r2.getLong(r6);	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r4.f5060d = r6;	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = com.baidu.android.pushservice.util.C1568q.C1557b.isAlarm;	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = r6.name();	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = r2.getColumnIndex(r6);	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = r2.getInt(r6);	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r4.f5061e = r6;	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = com.baidu.android.pushservice.util.C1568q.C1557b.msgEnable;	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = r6.name();	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = r2.getColumnIndex(r6);	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = r2.getInt(r6);	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r4.f5062f = r6;	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = r4.f5062f;	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        if (r6 == 0) goto L_0x0090;
    L_0x0086:
        r6 = r4.f5060d;	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r8 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r6 >= 0) goto L_0x0017;
    L_0x0090:
        r6 = r4.f5057a;	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        com.baidu.android.pushservice.util.C1568q.m7008d(r11, r6);	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r6 = "PushDatabase";
        r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r7.<init>();	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r8 = "deleteInvalidAlarmMsg   msgID = ";
        r7 = r7.append(r8);	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r4 = r4.f5057a;	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r4 = r7.append(r4);	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        r4 = r4.toString();	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r6, r4);	 Catch:{ Exception -> 0x00b1, all -> 0x00eb }
        goto L_0x0017;
    L_0x00b1:
        r4 = move-exception;
        r10 = r4;
        r4 = r2;
        r2 = r10;
    L_0x00b5:
        r6 = "PushDatabase";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r6, r2);	 Catch:{ all -> 0x00df }
        if (r4 == 0) goto L_0x00bf;
    L_0x00bc:
        r4.close();	 Catch:{ all -> 0x00c7 }
    L_0x00bf:
        if (r3 == 0) goto L_0x00c4;
    L_0x00c1:
        r3.close();	 Catch:{ all -> 0x00c7 }
    L_0x00c4:
        monitor-exit(r5);	 Catch:{ all -> 0x00c7 }
        goto L_0x000b;
    L_0x00c7:
        r2 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x00c7 }
        throw r2;
    L_0x00ca:
        r0 = r3;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00f0 }
        r2 = r0;
        r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.rawQuery(r2, r6, r7);	 Catch:{ Exception -> 0x00f0 }
        goto L_0x0017;
    L_0x00d4:
        if (r2 == 0) goto L_0x00d9;
    L_0x00d6:
        r2.close();	 Catch:{ all -> 0x00c7 }
    L_0x00d9:
        if (r3 == 0) goto L_0x00c4;
    L_0x00db:
        r3.close();	 Catch:{ all -> 0x00c7 }
        goto L_0x00c4;
    L_0x00df:
        r2 = move-exception;
    L_0x00e0:
        if (r4 == 0) goto L_0x00e5;
    L_0x00e2:
        r4.close();	 Catch:{ all -> 0x00c7 }
    L_0x00e5:
        if (r3 == 0) goto L_0x00ea;
    L_0x00e7:
        r3.close();	 Catch:{ all -> 0x00c7 }
    L_0x00ea:
        throw r2;	 Catch:{ all -> 0x00c7 }
    L_0x00eb:
        r4 = move-exception;
        r10 = r4;
        r4 = r2;
        r2 = r10;
        goto L_0x00e0;
    L_0x00f0:
        r2 = move-exception;
        goto L_0x00b5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1568q.m7006c(android.content.Context):void");
    }

    /* renamed from: d */
    public static long m7007d(Context context) {
        long j;
        synchronized (f5511c) {
            SQLiteDatabase e = C1568q.m7009e(context);
            if (e == null) {
                j = 0;
            } else {
                try {
                    String str = "PushBehavior";
                    if (e instanceof SQLiteDatabase) {
                        SQLiteInstrumentation.delete(e, str, null, null);
                    } else {
                        e.delete(str, null, null);
                    }
                    str = "ADPushBehavior";
                    if (e instanceof SQLiteDatabase) {
                        SQLiteInstrumentation.delete(e, str, null, null);
                    } else {
                        e.delete(str, null, null);
                    }
                    str = "AppInfo";
                    if (e instanceof SQLiteDatabase) {
                        SQLiteInstrumentation.delete(e, str, null, null);
                    } else {
                        e.delete(str, null, null);
                    }
                } catch (Exception e2) {
                    C1425a.m6444e("PushDatabase", "clearBehaviorInfo Exception: " + e2);
                }
                e.close();
                j = (long) -1;
            }
        }
        return j;
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x00d7 A:{Catch:{ Exception -> 0x00e2, all -> 0x00d2 }} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00dc A:{Catch:{ Exception -> 0x00e2, all -> 0x00d2 }} */
    /* JADX WARNING: Missing block: B:58:?, code skipped:
            return;
     */
    /* renamed from: d */
    public static void m7008d(android.content.Context r19, java.lang.String r20) {
        /*
        r18 = f5511c;
        monitor-enter(r18);
        r1 = com.baidu.android.pushservice.util.C1568q.m7009e(r19);	 Catch:{ all -> 0x0056 }
        if (r1 != 0) goto L_0x000b;
    L_0x0009:
        monitor-exit(r18);	 Catch:{ all -> 0x0056 }
    L_0x000a:
        return;
    L_0x000b:
        r17 = 0;
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00e2, all -> 0x00d2 }
        r2.<init>();	 Catch:{ Exception -> 0x00e2, all -> 0x00d2 }
        r3 = com.baidu.android.pushservice.util.C1568q.C1557b.msgId;	 Catch:{ Exception -> 0x00e2, all -> 0x00d2 }
        r3 = r3.name();	 Catch:{ Exception -> 0x00e2, all -> 0x00d2 }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x00e2, all -> 0x00d2 }
        r3 = " = ";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x00e2, all -> 0x00d2 }
        r0 = r20;
        r2 = r2.append(r0);	 Catch:{ Exception -> 0x00e2, all -> 0x00d2 }
        r3 = ";";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x00e2, all -> 0x00d2 }
        r4 = r2.toString();	 Catch:{ Exception -> 0x00e2, all -> 0x00d2 }
        r2 = "AlarmMsgInfo";
        r3 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00e2, all -> 0x00d2 }
        if (r9 != 0) goto L_0x0059;
    L_0x003d:
        r3 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x00e2, all -> 0x00d2 }
    L_0x0041:
        if (r3 != 0) goto L_0x006a;
    L_0x0043:
        r2 = "PushDatabase";
        r4 = "no msgid info table!!";
        com.baidu.android.pushservice.p036h.C1425a.m6443d(r2, r4);	 Catch:{ Exception -> 0x00c1 }
        if (r3 == 0) goto L_0x004f;
    L_0x004c:
        r3.close();	 Catch:{ all -> 0x0056 }
    L_0x004f:
        if (r1 == 0) goto L_0x0054;
    L_0x0051:
        r1.close();	 Catch:{ all -> 0x0056 }
    L_0x0054:
        monitor-exit(r18);	 Catch:{ all -> 0x0056 }
        goto L_0x000a;
    L_0x0056:
        r1 = move-exception;
        monitor-exit(r18);	 Catch:{ all -> 0x0056 }
        throw r1;
    L_0x0059:
        r0 = r1;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00e2, all -> 0x00d2 }
        r9 = r0;
        r10 = r2;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r3 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r9, r10, r11, r12, r13, r14, r15, r16);	 Catch:{ Exception -> 0x00e2, all -> 0x00d2 }
        goto L_0x0041;
    L_0x006a:
        r4 = "AlarmMsgInfo";
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00c1 }
        r2.<init>();	 Catch:{ Exception -> 0x00c1 }
        r5 = com.baidu.android.pushservice.util.C1568q.C1557b.msgId;	 Catch:{ Exception -> 0x00c1 }
        r5 = r5.name();	 Catch:{ Exception -> 0x00c1 }
        r2 = r2.append(r5);	 Catch:{ Exception -> 0x00c1 }
        r5 = "= ?";
        r2 = r2.append(r5);	 Catch:{ Exception -> 0x00c1 }
        r5 = r2.toString();	 Catch:{ Exception -> 0x00c1 }
        r2 = 1;
        r6 = new java.lang.String[r2];	 Catch:{ Exception -> 0x00c1 }
        r2 = 0;
        r6[r2] = r20;	 Catch:{ Exception -> 0x00c1 }
        r2 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00c1 }
        if (r2 != 0) goto L_0x00b9;
    L_0x008f:
        r1.delete(r4, r5, r6);	 Catch:{ Exception -> 0x00c1 }
    L_0x0092:
        r2 = "PushDatabase";
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00c1 }
        r4.<init>();	 Catch:{ Exception -> 0x00c1 }
        r5 = "deleteAlarmMsg  msgID = ";
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x00c1 }
        r0 = r20;
        r4 = r4.append(r0);	 Catch:{ Exception -> 0x00c1 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x00c1 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r2, r4);	 Catch:{ Exception -> 0x00c1 }
        if (r3 == 0) goto L_0x00b1;
    L_0x00ae:
        r3.close();	 Catch:{ all -> 0x0056 }
    L_0x00b1:
        if (r1 == 0) goto L_0x00b6;
    L_0x00b3:
        r1.close();	 Catch:{ all -> 0x0056 }
    L_0x00b6:
        monitor-exit(r18);	 Catch:{ all -> 0x0056 }
        goto L_0x000a;
    L_0x00b9:
        r0 = r1;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00c1 }
        r2 = r0;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.delete(r2, r4, r5, r6);	 Catch:{ Exception -> 0x00c1 }
        goto L_0x0092;
    L_0x00c1:
        r2 = move-exception;
    L_0x00c2:
        r4 = "PushDatabase";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r4, r2);	 Catch:{ all -> 0x00e0 }
        if (r3 == 0) goto L_0x00cc;
    L_0x00c9:
        r3.close();	 Catch:{ all -> 0x0056 }
    L_0x00cc:
        if (r1 == 0) goto L_0x00b6;
    L_0x00ce:
        r1.close();	 Catch:{ all -> 0x0056 }
        goto L_0x00b6;
    L_0x00d2:
        r2 = move-exception;
        r3 = r17;
    L_0x00d5:
        if (r3 == 0) goto L_0x00da;
    L_0x00d7:
        r3.close();	 Catch:{ all -> 0x0056 }
    L_0x00da:
        if (r1 == 0) goto L_0x00df;
    L_0x00dc:
        r1.close();	 Catch:{ all -> 0x0056 }
    L_0x00df:
        throw r2;	 Catch:{ all -> 0x0056 }
    L_0x00e0:
        r2 = move-exception;
        goto L_0x00d5;
    L_0x00e2:
        r2 = move-exception;
        r3 = r17;
        goto L_0x00c2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1568q.m7008d(android.content.Context, java.lang.String):void");
    }

    /* renamed from: e */
    private static SQLiteDatabase m7009e(Context context) {
        SQLiteDatabase sQLiteDatabase = null;
        C1561f f = C1568q.m7011f(context);
        if (f == null) {
            return sQLiteDatabase;
        }
        try {
            return f.getWritableDatabase();
        } catch (Exception e) {
            C1425a.m6441b("PushDatabase", "getDb Exception: " + e);
            return sQLiteDatabase;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:82:0x01b4 A:{SYNTHETIC, Splitter:B:82:0x01b4} */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01bf  */
    /* JADX WARNING: Missing block: B:100:?, code skipped:
            return true;
     */
    /* JADX WARNING: Missing block: B:101:?, code skipped:
            return false;
     */
    /* JADX WARNING: Missing block: B:103:?, code skipped:
            return true;
     */
    /* renamed from: e */
    public static boolean m7010e(android.content.Context r22, java.lang.String r23) {
        /*
        r20 = f5511c;
        monitor-enter(r20);
        r18 = 1;
        r2 = com.baidu.android.pushservice.util.C1568q.m7009e(r22);	 Catch:{ all -> 0x01c3 }
        r19 = 0;
        if (r2 != 0) goto L_0x0011;
    L_0x000d:
        monitor-exit(r20);	 Catch:{ all -> 0x01c3 }
        r2 = r18;
    L_0x0010:
        return r2;
    L_0x0011:
        r21 = new android.content.ContentValues;	 Catch:{ Exception -> 0x01db, all -> 0x01d7 }
        r21.<init>();	 Catch:{ Exception -> 0x01db, all -> 0x01d7 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01db, all -> 0x01d7 }
        r3.<init>();	 Catch:{ Exception -> 0x01db, all -> 0x01d7 }
        r4 = com.baidu.android.pushservice.util.C1568q.C1565j.msgId;	 Catch:{ Exception -> 0x01db, all -> 0x01d7 }
        r4 = r4.name();	 Catch:{ Exception -> 0x01db, all -> 0x01d7 }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x01db, all -> 0x01d7 }
        r4 = " = ";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x01db, all -> 0x01d7 }
        r0 = r23;
        r3 = r3.append(r0);	 Catch:{ Exception -> 0x01db, all -> 0x01d7 }
        r4 = ";";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x01db, all -> 0x01d7 }
        r5 = r3.toString();	 Catch:{ Exception -> 0x01db, all -> 0x01d7 }
        r3 = "MsgInfo";
        r4 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x01db, all -> 0x01d7 }
        if (r10 != 0) goto L_0x0067;
    L_0x0046:
        r4 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x01db, all -> 0x01d7 }
    L_0x004a:
        if (r4 != 0) goto L_0x0079;
    L_0x004c:
        r3 = "PushDatabase";
        r5 = "no msgid info table!!";
        com.baidu.android.pushservice.p036h.C1425a.m6443d(r3, r5);	 Catch:{ Exception -> 0x00e9 }
        if (r4 == 0) goto L_0x005e;
    L_0x0055:
        r3 = r4.isClosed();	 Catch:{ all -> 0x01c3 }
        if (r3 != 0) goto L_0x005e;
    L_0x005b:
        r4.close();	 Catch:{ all -> 0x01c3 }
    L_0x005e:
        if (r2 == 0) goto L_0x0063;
    L_0x0060:
        r2.close();	 Catch:{ all -> 0x01c3 }
    L_0x0063:
        monitor-exit(r20);	 Catch:{ all -> 0x01c3 }
        r2 = r18;
        goto L_0x0010;
    L_0x0067:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x01db, all -> 0x01d7 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x01db, all -> 0x01d7 }
        goto L_0x004a;
    L_0x0079:
        r3 = r4.getCount();	 Catch:{ Exception -> 0x00e9 }
        if (r3 <= 0) goto L_0x011b;
    L_0x007f:
        r3 = "PushDatabase";
        r5 = "msgid is duplicate";
        com.baidu.android.pushservice.p036h.C1425a.m6443d(r3, r5);	 Catch:{ Exception -> 0x00e9 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00e9 }
        r3.<init>();	 Catch:{ Exception -> 0x00e9 }
        r5 = "UPDATE MsgInfo SET ";
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x00e9 }
        r5 = com.baidu.android.pushservice.util.C1568q.C1565j.timeStamp;	 Catch:{ Exception -> 0x00e9 }
        r5 = r5.name();	 Catch:{ Exception -> 0x00e9 }
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x00e9 }
        r5 = " = ";
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x00e9 }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x00e9 }
        r3 = r3.append(r6);	 Catch:{ Exception -> 0x00e9 }
        r5 = " WHERE ";
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x00e9 }
        r5 = com.baidu.android.pushservice.util.C1568q.C1565j.msgId;	 Catch:{ Exception -> 0x00e9 }
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x00e9 }
        r5 = " = ";
        r3 = r3.append(r5);	 Catch:{ Exception -> 0x00e9 }
        r0 = r23;
        r3 = r3.append(r0);	 Catch:{ Exception -> 0x00e9 }
        r5 = r3.toString();	 Catch:{ Exception -> 0x00e9 }
        r3 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00e9 }
        if (r3 != 0) goto L_0x00e1;
    L_0x00c9:
        r2.execSQL(r5);	 Catch:{ Exception -> 0x00e9 }
    L_0x00cc:
        r3 = 0;
        if (r4 == 0) goto L_0x00d8;
    L_0x00cf:
        r5 = r4.isClosed();	 Catch:{ all -> 0x01c3 }
        if (r5 != 0) goto L_0x00d8;
    L_0x00d5:
        r4.close();	 Catch:{ all -> 0x01c3 }
    L_0x00d8:
        if (r2 == 0) goto L_0x00dd;
    L_0x00da:
        r2.close();	 Catch:{ all -> 0x01c3 }
    L_0x00dd:
        monitor-exit(r20);	 Catch:{ all -> 0x01c3 }
        r2 = r3;
        goto L_0x0010;
    L_0x00e1:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00e9 }
        r3 = r0;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.execSQL(r3, r5);	 Catch:{ Exception -> 0x00e9 }
        goto L_0x00cc;
    L_0x00e9:
        r3 = move-exception;
    L_0x00ea:
        r5 = "PushDatabase";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01b1 }
        r6.<init>();	 Catch:{ all -> 0x01b1 }
        r7 = "exception ";
        r6 = r6.append(r7);	 Catch:{ all -> 0x01b1 }
        r3 = r3.getMessage();	 Catch:{ all -> 0x01b1 }
        r3 = r6.append(r3);	 Catch:{ all -> 0x01b1 }
        r3 = r3.toString();	 Catch:{ all -> 0x01b1 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r5, r3);	 Catch:{ all -> 0x01b1 }
        if (r4 == 0) goto L_0x0111;
    L_0x0108:
        r3 = r4.isClosed();	 Catch:{ all -> 0x01c3 }
        if (r3 != 0) goto L_0x0111;
    L_0x010e:
        r4.close();	 Catch:{ all -> 0x01c3 }
    L_0x0111:
        if (r2 == 0) goto L_0x0116;
    L_0x0113:
        r2.close();	 Catch:{ all -> 0x01c3 }
    L_0x0116:
        monitor-exit(r20);	 Catch:{ all -> 0x01c3 }
        r2 = r18;
        goto L_0x0010;
    L_0x011b:
        r21.clear();	 Catch:{ Exception -> 0x00e9 }
        r3 = com.baidu.android.pushservice.util.C1568q.C1565j.msgId;	 Catch:{ Exception -> 0x00e9 }
        r3 = r3.name();	 Catch:{ Exception -> 0x00e9 }
        r0 = r21;
        r1 = r23;
        r0.put(r3, r1);	 Catch:{ Exception -> 0x00e9 }
        r3 = com.baidu.android.pushservice.util.C1568q.C1565j.timeStamp;	 Catch:{ Exception -> 0x00e9 }
        r3 = r3.name();	 Catch:{ Exception -> 0x00e9 }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x00e9 }
        r5 = java.lang.Long.valueOf(r6);	 Catch:{ Exception -> 0x00e9 }
        r0 = r21;
        r0.put(r3, r5);	 Catch:{ Exception -> 0x00e9 }
        r5 = "MsgInfo";
        r6 = 0;
        r3 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00e9 }
        if (r3 != 0) goto L_0x01a7;
    L_0x0145:
        r0 = r21;
        r2.insert(r5, r6, r0);	 Catch:{ Exception -> 0x00e9 }
    L_0x014a:
        r3 = "PushDatabase";
        r5 = "insert normal msgid";
        com.baidu.android.pushservice.p036h.C1425a.m6441b(r3, r5);	 Catch:{ Exception -> 0x00e9 }
        r5 = "SELECT COUNT(*) FROM MsgInfo;";
        r6 = 0;
        r3 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00e9 }
        if (r3 != 0) goto L_0x01c6;
    L_0x0158:
        r4 = r2.rawQuery(r5, r6);	 Catch:{ Exception -> 0x00e9 }
    L_0x015c:
        r4.moveToFirst();	 Catch:{ Exception -> 0x00e9 }
        r3 = 0;
        r3 = r4.getInt(r3);	 Catch:{ Exception -> 0x00e9 }
        r5 = "PushDatabase";
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00e9 }
        r6.<init>();	 Catch:{ Exception -> 0x00e9 }
        r7 = "msgID count = ";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x00e9 }
        r6 = r6.append(r3);	 Catch:{ Exception -> 0x00e9 }
        r6 = r6.toString();	 Catch:{ Exception -> 0x00e9 }
        com.baidu.android.pushservice.p036h.C1425a.m6441b(r5, r6);	 Catch:{ Exception -> 0x00e9 }
        r5 = f5512d;	 Catch:{ Exception -> 0x00e9 }
        if (r3 <= r5) goto L_0x0192;
    L_0x0180:
        r5 = "MsgInfo";
        r6 = 0;
        r7 = 0;
        r3 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00e9 }
        if (r3 != 0) goto L_0x01cf;
    L_0x0188:
        r2.delete(r5, r6, r7);	 Catch:{ Exception -> 0x00e9 }
    L_0x018b:
        r3 = "PushDatabase";
        r5 = "delete msgid info table!!";
        com.baidu.android.pushservice.p036h.C1425a.m6441b(r3, r5);	 Catch:{ Exception -> 0x00e9 }
    L_0x0192:
        if (r4 == 0) goto L_0x019d;
    L_0x0194:
        r3 = r4.isClosed();	 Catch:{ all -> 0x01c3 }
        if (r3 != 0) goto L_0x019d;
    L_0x019a:
        r4.close();	 Catch:{ all -> 0x01c3 }
    L_0x019d:
        if (r2 == 0) goto L_0x01a2;
    L_0x019f:
        r2.close();	 Catch:{ all -> 0x01c3 }
    L_0x01a2:
        monitor-exit(r20);	 Catch:{ all -> 0x01c3 }
        r2 = r18;
        goto L_0x0010;
    L_0x01a7:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00e9 }
        r3 = r0;
        r0 = r21;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.insert(r3, r5, r6, r0);	 Catch:{ Exception -> 0x00e9 }
        goto L_0x014a;
    L_0x01b1:
        r3 = move-exception;
    L_0x01b2:
        if (r4 == 0) goto L_0x01bd;
    L_0x01b4:
        r5 = r4.isClosed();	 Catch:{ all -> 0x01c3 }
        if (r5 != 0) goto L_0x01bd;
    L_0x01ba:
        r4.close();	 Catch:{ all -> 0x01c3 }
    L_0x01bd:
        if (r2 == 0) goto L_0x01c2;
    L_0x01bf:
        r2.close();	 Catch:{ all -> 0x01c3 }
    L_0x01c2:
        throw r3;	 Catch:{ all -> 0x01c3 }
    L_0x01c3:
        r2 = move-exception;
        monitor-exit(r20);	 Catch:{ all -> 0x01c3 }
        throw r2;
    L_0x01c6:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00e9 }
        r3 = r0;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.rawQuery(r3, r5, r6);	 Catch:{ Exception -> 0x00e9 }
        goto L_0x015c;
    L_0x01cf:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00e9 }
        r3 = r0;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.delete(r3, r5, r6, r7);	 Catch:{ Exception -> 0x00e9 }
        goto L_0x018b;
    L_0x01d7:
        r3 = move-exception;
        r4 = r19;
        goto L_0x01b2;
    L_0x01db:
        r3 = move-exception;
        r4 = r19;
        goto L_0x00ea;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1568q.m7010e(android.content.Context, java.lang.String):boolean");
    }

    /* renamed from: f */
    private static C1561f m7011f(Context context) {
        synchronized (f5511c) {
            if (f5509a == null) {
                String path = context.getDatabasePath("pushstat_5.2.0.db").getPath();
                C1568q.m6997a("pushstat_5.2.0.db", context);
                if (VERSION.SDK_INT >= 11) {
                    f5510b = new C1560e();
                    f5509a = new C1561f(context, path, 2, f5510b);
                } else {
                    f5509a = new C1561f(context, path, 2);
                }
            }
        }
        return f5509a;
    }

    /* JADX WARNING: Removed duplicated region for block: B:63:0x013f A:{Catch:{ Exception -> 0x0110, all -> 0x013a }} */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0144 A:{Catch:{ Exception -> 0x0110, all -> 0x013a }} */
    /* JADX WARNING: Missing block: B:70:?, code skipped:
            return r2;
     */
    /* JADX WARNING: Missing block: B:71:?, code skipped:
            return r2;
     */
    /* renamed from: f */
    public static int[] m7012f(android.content.Context r19, java.lang.String r20) {
        /*
        r18 = f5511c;
        monitor-enter(r18);
        r1 = com.baidu.android.pushservice.util.C1568q.m7009e(r19);	 Catch:{ all -> 0x010d }
        if (r1 != 0) goto L_0x000c;
    L_0x0009:
        r1 = 0;
        monitor-exit(r18);	 Catch:{ all -> 0x010d }
    L_0x000b:
        return r1;
    L_0x000c:
        r17 = 0;
        r2 = "NoDisturb";
        r3 = 0;
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0110, all -> 0x013a }
        r4.<init>();	 Catch:{ Exception -> 0x0110, all -> 0x013a }
        r5 = com.baidu.android.pushservice.util.C1568q.C1566k.pkgName;	 Catch:{ Exception -> 0x0110, all -> 0x013a }
        r5 = r5.name();	 Catch:{ Exception -> 0x0110, all -> 0x013a }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x0110, all -> 0x013a }
        r5 = "= ?";
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x0110, all -> 0x013a }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0110, all -> 0x013a }
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ Exception -> 0x0110, all -> 0x013a }
        r6 = 0;
        r5[r6] = r20;	 Catch:{ Exception -> 0x0110, all -> 0x013a }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0110, all -> 0x013a }
        if (r9 != 0) goto L_0x00d0;
    L_0x0037:
        r3 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0110, all -> 0x013a }
    L_0x003b:
        if (r3 == 0) goto L_0x00ff;
    L_0x003d:
        r2 = r3.moveToNext();	 Catch:{ Exception -> 0x014a }
        if (r2 == 0) goto L_0x00ff;
    L_0x0043:
        r2 = com.baidu.android.pushservice.util.C1568q.C1566k.startHour;	 Catch:{ Exception -> 0x014a }
        r2 = r2.name();	 Catch:{ Exception -> 0x014a }
        r2 = r3.getColumnIndex(r2);	 Catch:{ Exception -> 0x014a }
        r4 = r3.getInt(r2);	 Catch:{ Exception -> 0x014a }
        r2 = com.baidu.android.pushservice.util.C1568q.C1566k.startMinute;	 Catch:{ Exception -> 0x014a }
        r2 = r2.name();	 Catch:{ Exception -> 0x014a }
        r2 = r3.getColumnIndex(r2);	 Catch:{ Exception -> 0x014a }
        r5 = r3.getInt(r2);	 Catch:{ Exception -> 0x014a }
        r2 = com.baidu.android.pushservice.util.C1568q.C1566k.endHour;	 Catch:{ Exception -> 0x014a }
        r2 = r2.name();	 Catch:{ Exception -> 0x014a }
        r2 = r3.getColumnIndex(r2);	 Catch:{ Exception -> 0x014a }
        r6 = r3.getInt(r2);	 Catch:{ Exception -> 0x014a }
        r2 = com.baidu.android.pushservice.util.C1568q.C1566k.endMinute;	 Catch:{ Exception -> 0x014a }
        r2 = r2.name();	 Catch:{ Exception -> 0x014a }
        r2 = r3.getColumnIndex(r2);	 Catch:{ Exception -> 0x014a }
        r7 = r3.getInt(r2);	 Catch:{ Exception -> 0x014a }
        r2 = "PushDatabase";
        r8 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x014a }
        r8.<init>();	 Catch:{ Exception -> 0x014a }
        r0 = r20;
        r8 = r8.append(r0);	 Catch:{ Exception -> 0x014a }
        r9 = " disturb data is found! startHour: ";
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x014a }
        r8 = r8.append(r4);	 Catch:{ Exception -> 0x014a }
        r9 = " startMinute: ";
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x014a }
        r8 = r8.append(r5);	 Catch:{ Exception -> 0x014a }
        r9 = " endHour: ";
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x014a }
        r8 = r8.append(r6);	 Catch:{ Exception -> 0x014a }
        r9 = " endMinute: ";
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x014a }
        r8 = r8.append(r7);	 Catch:{ Exception -> 0x014a }
        r8 = r8.toString();	 Catch:{ Exception -> 0x014a }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r2, r8);	 Catch:{ Exception -> 0x014a }
        if (r4 != 0) goto L_0x00e2;
    L_0x00b9:
        if (r5 != 0) goto L_0x00e2;
    L_0x00bb:
        if (r6 != 0) goto L_0x00e2;
    L_0x00bd:
        if (r7 != 0) goto L_0x00e2;
    L_0x00bf:
        r2 = 0;
        r2 = new int[r2];	 Catch:{ Exception -> 0x014a }
        if (r3 == 0) goto L_0x00c7;
    L_0x00c4:
        r3.close();	 Catch:{ all -> 0x010d }
    L_0x00c7:
        if (r1 == 0) goto L_0x00cc;
    L_0x00c9:
        r1.close();	 Catch:{ all -> 0x010d }
    L_0x00cc:
        monitor-exit(r18);	 Catch:{ all -> 0x010d }
        r1 = r2;
        goto L_0x000b;
    L_0x00d0:
        r0 = r1;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0110, all -> 0x013a }
        r9 = r0;
        r10 = r2;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r3 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r9, r10, r11, r12, r13, r14, r15, r16);	 Catch:{ Exception -> 0x0110, all -> 0x013a }
        goto L_0x003b;
    L_0x00e2:
        r2 = 4;
        r2 = new int[r2];	 Catch:{ Exception -> 0x014a }
        r8 = 0;
        r2[r8] = r4;	 Catch:{ Exception -> 0x014a }
        r4 = 1;
        r2[r4] = r5;	 Catch:{ Exception -> 0x014a }
        r4 = 2;
        r2[r4] = r6;	 Catch:{ Exception -> 0x014a }
        r4 = 3;
        r2[r4] = r7;	 Catch:{ Exception -> 0x014a }
        if (r3 == 0) goto L_0x00f6;
    L_0x00f3:
        r3.close();	 Catch:{ all -> 0x010d }
    L_0x00f6:
        if (r1 == 0) goto L_0x00fb;
    L_0x00f8:
        r1.close();	 Catch:{ all -> 0x010d }
    L_0x00fb:
        monitor-exit(r18);	 Catch:{ all -> 0x010d }
        r1 = r2;
        goto L_0x000b;
    L_0x00ff:
        if (r3 == 0) goto L_0x0104;
    L_0x0101:
        r3.close();	 Catch:{ all -> 0x010d }
    L_0x0104:
        if (r1 == 0) goto L_0x0109;
    L_0x0106:
        r1.close();	 Catch:{ all -> 0x010d }
    L_0x0109:
        r1 = 0;
        monitor-exit(r18);	 Catch:{ all -> 0x010d }
        goto L_0x000b;
    L_0x010d:
        r1 = move-exception;
        monitor-exit(r18);	 Catch:{ all -> 0x010d }
        throw r1;
    L_0x0110:
        r2 = move-exception;
        r3 = r17;
    L_0x0113:
        r4 = "PushDatabase";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0148 }
        r5.<init>();	 Catch:{ all -> 0x0148 }
        r6 = "error ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x0148 }
        r2 = r2.getMessage();	 Catch:{ all -> 0x0148 }
        r2 = r5.append(r2);	 Catch:{ all -> 0x0148 }
        r2 = r2.toString();	 Catch:{ all -> 0x0148 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r4, r2);	 Catch:{ all -> 0x0148 }
        if (r3 == 0) goto L_0x0134;
    L_0x0131:
        r3.close();	 Catch:{ all -> 0x010d }
    L_0x0134:
        if (r1 == 0) goto L_0x0109;
    L_0x0136:
        r1.close();	 Catch:{ all -> 0x010d }
        goto L_0x0109;
    L_0x013a:
        r2 = move-exception;
        r3 = r17;
    L_0x013d:
        if (r3 == 0) goto L_0x0142;
    L_0x013f:
        r3.close();	 Catch:{ all -> 0x010d }
    L_0x0142:
        if (r1 == 0) goto L_0x0147;
    L_0x0144:
        r1.close();	 Catch:{ all -> 0x010d }
    L_0x0147:
        throw r2;	 Catch:{ all -> 0x010d }
    L_0x0148:
        r2 = move-exception;
        goto L_0x013d;
    L_0x014a:
        r2 = move-exception;
        goto L_0x0113;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1568q.m7012f(android.content.Context, java.lang.String):int[]");
    }
}
