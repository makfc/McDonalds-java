package com.baidu.android.pushservice;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class PushInfoProvider extends ContentProvider {
    /* renamed from: b */
    private static Object f4585b = new Object();
    /* renamed from: c */
    private static Context f4586c;
    /* renamed from: d */
    private static final UriMatcher f4587d = new UriMatcher(-1);
    /* renamed from: a */
    SQLiteDatabase f4588a;

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public boolean onCreate() {
        f4586c = getContext();
        f4587d.addURI(f4586c.getPackageName() + ".bdpush", "pushinfo", 1);
        f4587d.addURI(f4586c.getPackageName() + ".bdpush", "pushad", 2);
        return true;
    }

    public android.database.Cursor query(android.net.Uri r12, java.lang.String[] r13, java.lang.String r14, java.lang.String[] r15, java.lang.String r16) {
        /*
        r11 = this;
        r8 = 0;
        r9 = f4585b;
        monitor-enter(r9);
        r0 = f4587d;	 Catch:{ Exception -> 0x0048 }
        r0 = r0.match(r12);	 Catch:{ Exception -> 0x0048 }
        switch(r0) {
            case 1: goto L_0x003f;
            case 2: goto L_0x0050;
            default: goto L_0x000d;
        };	 Catch:{ Exception -> 0x0048 }
    L_0x000d:
        r0 = r11.f4588a;	 Catch:{ Exception -> 0x0048 }
        if (r0 == 0) goto L_0x0067;
    L_0x0011:
        r0 = r11.f4588a;	 Catch:{ Exception -> 0x0048 }
        r1 = "PushShareInfo";
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r10 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0048 }
        if (r10 != 0) goto L_0x005c;
    L_0x001f:
        r0 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0048 }
    L_0x0023:
        if (r0 == 0) goto L_0x003d;
    L_0x0025:
        r1 = "PushInfoProvider";
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0063 }
        r2.<init>();	 Catch:{ Exception -> 0x0063 }
        r3 = "return contentprovider Cursor : ";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0063 }
        r2 = r2.append(r0);	 Catch:{ Exception -> 0x0063 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x0063 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r1, r2);	 Catch:{ Exception -> 0x0063 }
    L_0x003d:
        monitor-exit(r9);	 Catch:{ all -> 0x0059 }
        return r0;
    L_0x003f:
        r0 = f4586c;	 Catch:{ Exception -> 0x0048 }
        r0 = com.baidu.android.pushservice.util.C1574s.m7017a(r0);	 Catch:{ Exception -> 0x0048 }
        r11.f4588a = r0;	 Catch:{ Exception -> 0x0048 }
        goto L_0x000d;
    L_0x0048:
        r0 = move-exception;
    L_0x0049:
        r1 = "PushInfoProvider";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r1, r0);	 Catch:{ all -> 0x0059 }
        r0 = r8;
        goto L_0x003d;
    L_0x0050:
        r0 = f4586c;	 Catch:{ Exception -> 0x0048 }
        r0 = com.baidu.android.pushservice.util.C1554p.m6972a(r0);	 Catch:{ Exception -> 0x0048 }
        r11.f4588a = r0;	 Catch:{ Exception -> 0x0048 }
        goto L_0x000d;
    L_0x0059:
        r0 = move-exception;
        monitor-exit(r9);	 Catch:{ all -> 0x0059 }
        throw r0;
    L_0x005c:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0048 }
        r0 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0048 }
        goto L_0x0023;
    L_0x0063:
        r1 = move-exception;
        r8 = r0;
        r0 = r1;
        goto L_0x0049;
    L_0x0067:
        r0 = r8;
        goto L_0x003d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.PushInfoProvider.query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x00d2 A:{SYNTHETIC, Splitter:B:49:0x00d2} */
    /* JADX WARNING: Missing block: B:6:0x0011, code skipped:
            r3 = null;
            r4 = -1;
     */
    /* JADX WARNING: Missing block: B:7:0x0013, code skipped:
            if (r3 == null) goto L_0x0018;
     */
    /* JADX WARNING: Missing block: B:9:?, code skipped:
            r3.close();
     */
    public int update(android.net.Uri r16, android.content.ContentValues r17, java.lang.String r18, java.lang.String[] r19) {
        /*
        r15 = this;
        r12 = -1;
        r11 = f4585b;
        monitor-enter(r11);
        r10 = 0;
        r2 = f4587d;	 Catch:{ Exception -> 0x00e2, all -> 0x00df }
        r0 = r16;
        r2 = r2.match(r0);	 Catch:{ Exception -> 0x00e2, all -> 0x00df }
        switch(r2) {
            case 1: goto L_0x001b;
            default: goto L_0x0011;
        };
    L_0x0011:
        r3 = r10;
        r4 = r12;
    L_0x0013:
        if (r3 == 0) goto L_0x0018;
    L_0x0015:
        r3.close();	 Catch:{ all -> 0x0085 }
    L_0x0018:
        monitor-exit(r11);	 Catch:{ all -> 0x0085 }
        r2 = (int) r4;
        return r2;
    L_0x001b:
        r2 = f4586c;	 Catch:{ Exception -> 0x00e2, all -> 0x00df }
        r2 = com.baidu.android.pushservice.util.C1574s.m7017a(r2);	 Catch:{ Exception -> 0x00e2, all -> 0x00df }
        r15.f4588a = r2;	 Catch:{ Exception -> 0x00e2, all -> 0x00df }
        r2 = r15.f4588a;	 Catch:{ Exception -> 0x00e2, all -> 0x00df }
        if (r2 == 0) goto L_0x0011;
    L_0x0027:
        r2 = r15.f4588a;	 Catch:{ Exception -> 0x00e2, all -> 0x00df }
        r3 = "PushShareInfo";
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r14 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00e2, all -> 0x00df }
        if (r14 != 0) goto L_0x0088;
    L_0x0035:
        r3 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00e2, all -> 0x00df }
    L_0x0039:
        if (r3 == 0) goto L_0x009a;
    L_0x003b:
        r2 = r3.getCount();	 Catch:{ Exception -> 0x00e6 }
        if (r2 == 0) goto L_0x009a;
    L_0x0041:
        r2 = r15.f4588a;	 Catch:{ Exception -> 0x00e6 }
        r4 = "PushShareInfo";
        r5 = 0;
        r6 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00e6 }
        if (r6 != 0) goto L_0x008f;
    L_0x004a:
        r0 = r17;
        r1 = r18;
        r2 = r2.update(r4, r0, r1, r5);	 Catch:{ Exception -> 0x00e6 }
    L_0x0052:
        r4 = (long) r2;
        r2 = "PushInfoProvider";
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0079 }
        r6.<init>();	 Catch:{ Exception -> 0x0079 }
        r7 = "update  selection = ";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x0079 }
        r0 = r18;
        r6 = r6.append(r0);	 Catch:{ Exception -> 0x0079 }
        r7 = "  ret = ";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x0079 }
        r6 = r6.append(r4);	 Catch:{ Exception -> 0x0079 }
        r6 = r6.toString();	 Catch:{ Exception -> 0x0079 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r2, r6);	 Catch:{ Exception -> 0x0079 }
        goto L_0x0013;
    L_0x0079:
        r2 = move-exception;
    L_0x007a:
        r6 = "PushInfoProvider";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r6, r2);	 Catch:{ all -> 0x00cf }
        if (r3 == 0) goto L_0x0018;
    L_0x0081:
        r3.close();	 Catch:{ all -> 0x0085 }
        goto L_0x0018;
    L_0x0085:
        r2 = move-exception;
        monitor-exit(r11);	 Catch:{ all -> 0x0085 }
        throw r2;
    L_0x0088:
        r2 = (android.database.sqlite.SQLiteDatabase) r2;	 Catch:{ Exception -> 0x00e2, all -> 0x00df }
        r3 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r2, r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00e2, all -> 0x00df }
        goto L_0x0039;
    L_0x008f:
        r2 = (android.database.sqlite.SQLiteDatabase) r2;	 Catch:{ Exception -> 0x00e6 }
        r0 = r17;
        r1 = r18;
        r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r2, r4, r0, r1, r5);	 Catch:{ Exception -> 0x00e6 }
        goto L_0x0052;
    L_0x009a:
        r2 = r15.f4588a;	 Catch:{ Exception -> 0x00e6 }
        r4 = "PushShareInfo";
        r5 = 0;
        r6 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00e6 }
        if (r6 != 0) goto L_0x00d6;
    L_0x00a3:
        r0 = r17;
        r4 = r2.insert(r4, r5, r0);	 Catch:{ Exception -> 0x00e6 }
    L_0x00a9:
        r2 = "PushInfoProvider";
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0079 }
        r6.<init>();	 Catch:{ Exception -> 0x0079 }
        r7 = "insert  selection = ";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x0079 }
        r0 = r18;
        r6 = r6.append(r0);	 Catch:{ Exception -> 0x0079 }
        r7 = "  ret = ";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x0079 }
        r6 = r6.append(r4);	 Catch:{ Exception -> 0x0079 }
        r6 = r6.toString();	 Catch:{ Exception -> 0x0079 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r2, r6);	 Catch:{ Exception -> 0x0079 }
        goto L_0x0013;
    L_0x00cf:
        r2 = move-exception;
    L_0x00d0:
        if (r3 == 0) goto L_0x00d5;
    L_0x00d2:
        r3.close();	 Catch:{ all -> 0x0085 }
    L_0x00d5:
        throw r2;	 Catch:{ all -> 0x0085 }
    L_0x00d6:
        r2 = (android.database.sqlite.SQLiteDatabase) r2;	 Catch:{ Exception -> 0x00e6 }
        r0 = r17;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.insert(r2, r4, r5, r0);	 Catch:{ Exception -> 0x00e6 }
        goto L_0x00a9;
    L_0x00df:
        r2 = move-exception;
        r3 = r10;
        goto L_0x00d0;
    L_0x00e2:
        r2 = move-exception;
        r3 = r10;
        r4 = r12;
        goto L_0x007a;
    L_0x00e6:
        r2 = move-exception;
        r4 = r12;
        goto L_0x007a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.PushInfoProvider.update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[]):int");
    }
}
