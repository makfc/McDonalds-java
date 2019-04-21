package com.tencent.wxop.stat;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import com.tencent.wxop.stat.common.C4423a;
import com.tencent.wxop.stat.common.C4427e;
import com.tencent.wxop.stat.common.C4433k;
import com.tencent.wxop.stat.common.StatLogger;
import com.tencent.wxop.stat.p069a.C4377e;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.tencent.wxop.stat.au */
public class C4411au {
    /* renamed from: h */
    private static StatLogger f7042h = C4433k.m8111b();
    /* renamed from: i */
    private static Context f7043i = null;
    /* renamed from: j */
    private static C4411au f7044j = null;
    /* renamed from: a */
    volatile int f7045a = 0;
    /* renamed from: b */
    C4423a f7046b = null;
    /* renamed from: c */
    private C4420bc f7047c = null;
    /* renamed from: d */
    private C4420bc f7048d = null;
    /* renamed from: e */
    private C4427e f7049e = null;
    /* renamed from: f */
    private String f7050f = "";
    /* renamed from: g */
    private String f7051g = "";
    /* renamed from: k */
    private int f7052k = 0;
    /* renamed from: l */
    private ConcurrentHashMap<C4377e, String> f7053l = null;
    /* renamed from: m */
    private boolean f7054m = false;
    /* renamed from: n */
    private HashMap<String, String> f7055n = new HashMap();

    private C4411au(Context context) {
        try {
            this.f7049e = new C4427e();
            f7043i = context.getApplicationContext();
            this.f7053l = new ConcurrentHashMap();
            this.f7050f = C4433k.m8135r(context);
            this.f7051g = "pri_" + C4433k.m8135r(context);
            this.f7047c = new C4420bc(f7043i, this.f7050f);
            this.f7048d = new C4420bc(f7043i, this.f7051g);
            m8041a(true);
            m8041a(false);
            m8051f();
            mo33931b(f7043i);
            mo33933d();
            m8055j();
        } catch (Throwable th) {
            f7042h.mo33949e(th);
        }
    }

    /* renamed from: a */
    public static C4411au m8029a(Context context) {
        if (f7044j == null) {
            synchronized (C4411au.class) {
                if (f7044j == null) {
                    f7044j = new C4411au(context);
                }
            }
        }
        return f7044j;
    }

    /* renamed from: a */
    private String m8030a(List<C4421bd> list) {
        StringBuilder stringBuilder = new StringBuilder(list.size() * 3);
        stringBuilder.append("event_id in (");
        int i = 0;
        int size = list.size();
        Iterator it = list.iterator();
        while (true) {
            int i2 = i;
            if (it.hasNext()) {
                stringBuilder.append(((C4421bd) it.next()).f7081a);
                if (i2 != size - 1) {
                    stringBuilder.append(",");
                }
                i = i2 + 1;
            } else {
                stringBuilder.append(")");
                return stringBuilder.toString();
            }
        }
    }

    /* renamed from: a */
    private synchronized void m8031a(int i, boolean z) {
        try {
            if (this.f7045a > 0 && i > 0 && !StatServiceImpl.m7942a()) {
                if (StatConfig.isDebugEnable()) {
                    f7042h.mo33952i("Load " + this.f7045a + " unsent events");
                }
                List arrayList = new ArrayList(i);
                m8047b(arrayList, i, z);
                if (arrayList.size() > 0) {
                    if (StatConfig.isDebugEnable()) {
                        f7042h.mo33952i("Peek " + arrayList.size() + " unsent events.");
                    }
                    m8039a(arrayList, 2, z);
                    C4445i.m8180b(f7043i).mo33982b(arrayList, new C4418ba(this, arrayList, z));
                }
            }
        } catch (Throwable th) {
            f7042h.mo33949e(th);
        }
        return;
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x00fe  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0096  */
    /* renamed from: a */
    private void m8032a(com.tencent.wxop.stat.p069a.C4377e r10, com.tencent.wxop.stat.C4407h r11, boolean r12) {
        /*
        r9 = this;
        r3 = 0;
        r3 = r9.m8048c(r12);	 Catch:{ Throwable -> 0x00d9 }
        r3.beginTransaction();	 Catch:{ Throwable -> 0x00d9 }
        if (r12 != 0) goto L_0x0032;
    L_0x000a:
        r2 = r9.f7045a;	 Catch:{ Throwable -> 0x00d9 }
        r4 = com.tencent.wxop.stat.StatConfig.getMaxStoreEventCount();	 Catch:{ Throwable -> 0x00d9 }
        if (r2 <= r4) goto L_0x0032;
    L_0x0012:
        r2 = f7042h;	 Catch:{ Throwable -> 0x00d9 }
        r4 = "Too many events stored in db.";
        r2.warn(r4);	 Catch:{ Throwable -> 0x00d9 }
        r4 = r9.f7045a;	 Catch:{ Throwable -> 0x00d9 }
        r2 = r9.f7047c;	 Catch:{ Throwable -> 0x00d9 }
        r2 = r2.getWritableDatabase();	 Catch:{ Throwable -> 0x00d9 }
        r5 = "events";
        r6 = "event_id in (select event_id from events where timestamp in (select min(timestamp) from events) limit 1)";
        r7 = 0;
        r8 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Throwable -> 0x00d9 }
        if (r8 != 0) goto L_0x00c0;
    L_0x002a:
        r2 = r2.delete(r5, r6, r7);	 Catch:{ Throwable -> 0x00d9 }
    L_0x002e:
        r2 = r4 - r2;
        r9.f7045a = r2;	 Catch:{ Throwable -> 0x00d9 }
    L_0x0032:
        r4 = new android.content.ContentValues;	 Catch:{ Throwable -> 0x00d9 }
        r4.<init>();	 Catch:{ Throwable -> 0x00d9 }
        r2 = r10.mo33890g();	 Catch:{ Throwable -> 0x00d9 }
        r5 = com.tencent.wxop.stat.StatConfig.isDebugEnable();	 Catch:{ Throwable -> 0x00d9 }
        if (r5 == 0) goto L_0x0055;
    L_0x0041:
        r5 = f7042h;	 Catch:{ Throwable -> 0x00d9 }
        r6 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00d9 }
        r7 = "insert 1 event, content:";
        r6.<init>(r7);	 Catch:{ Throwable -> 0x00d9 }
        r6 = r6.append(r2);	 Catch:{ Throwable -> 0x00d9 }
        r6 = r6.toString();	 Catch:{ Throwable -> 0x00d9 }
        r5.mo33952i(r6);	 Catch:{ Throwable -> 0x00d9 }
    L_0x0055:
        r2 = com.tencent.wxop.stat.common.C4439q.m8162b(r2);	 Catch:{ Throwable -> 0x00d9 }
        r5 = "content";
        r4.put(r5, r2);	 Catch:{ Throwable -> 0x00d9 }
        r2 = "send_count";
        r5 = "0";
        r4.put(r2, r5);	 Catch:{ Throwable -> 0x00d9 }
        r2 = "status";
        r5 = 1;
        r5 = java.lang.Integer.toString(r5);	 Catch:{ Throwable -> 0x00d9 }
        r4.put(r2, r5);	 Catch:{ Throwable -> 0x00d9 }
        r2 = "timestamp";
        r6 = r10.mo33886c();	 Catch:{ Throwable -> 0x00d9 }
        r5 = java.lang.Long.valueOf(r6);	 Catch:{ Throwable -> 0x00d9 }
        r4.put(r2, r5);	 Catch:{ Throwable -> 0x00d9 }
        r5 = "events";
        r6 = 0;
        r2 = r3 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Throwable -> 0x00d9 }
        if (r2 != 0) goto L_0x00c8;
    L_0x0083:
        r4 = r3.insert(r5, r6, r4);	 Catch:{ Throwable -> 0x00d9 }
    L_0x0087:
        r3.setTransactionSuccessful();	 Catch:{ Throwable -> 0x00d9 }
        if (r3 == 0) goto L_0x0117;
    L_0x008c:
        r3.endTransaction();	 Catch:{ Throwable -> 0x00d1 }
        r2 = r4;
    L_0x0090:
        r4 = 0;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 <= 0) goto L_0x00fe;
    L_0x0096:
        r2 = r9.f7045a;
        r2 = r2 + 1;
        r9.f7045a = r2;
        r2 = com.tencent.wxop.stat.StatConfig.isDebugEnable();
        if (r2 == 0) goto L_0x00ba;
    L_0x00a2:
        r2 = f7042h;
        r3 = new java.lang.StringBuilder;
        r4 = "directStoreEvent insert event to db, event:";
        r3.<init>(r4);
        r4 = r10.mo33890g();
        r3 = r3.append(r4);
        r3 = r3.toString();
        r2.mo33946d(r3);
    L_0x00ba:
        if (r11 == 0) goto L_0x00bf;
    L_0x00bc:
        r11.mo33923a();
    L_0x00bf:
        return;
    L_0x00c0:
        r2 = (android.database.sqlite.SQLiteDatabase) r2;	 Catch:{ Throwable -> 0x00d9 }
        r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.delete(r2, r5, r6, r7);	 Catch:{ Throwable -> 0x00d9 }
        goto L_0x002e;
    L_0x00c8:
        r0 = r3;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Throwable -> 0x00d9 }
        r2 = r0;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.insert(r2, r5, r6, r4);	 Catch:{ Throwable -> 0x00d9 }
        goto L_0x0087;
    L_0x00d1:
        r2 = move-exception;
        r3 = f7042h;
        r3.mo33949e(r2);
        r2 = r4;
        goto L_0x0090;
    L_0x00d9:
        r2 = move-exception;
        r4 = -1;
        r6 = f7042h;	 Catch:{ all -> 0x00f0 }
        r6.mo33949e(r2);	 Catch:{ all -> 0x00f0 }
        if (r3 == 0) goto L_0x0117;
    L_0x00e3:
        r3.endTransaction();	 Catch:{ Throwable -> 0x00e8 }
        r2 = r4;
        goto L_0x0090;
    L_0x00e8:
        r2 = move-exception;
        r3 = f7042h;
        r3.mo33949e(r2);
        r2 = r4;
        goto L_0x0090;
    L_0x00f0:
        r2 = move-exception;
        if (r3 == 0) goto L_0x00f6;
    L_0x00f3:
        r3.endTransaction();	 Catch:{ Throwable -> 0x00f7 }
    L_0x00f6:
        throw r2;
    L_0x00f7:
        r3 = move-exception;
        r4 = f7042h;
        r4.mo33949e(r3);
        goto L_0x00f6;
    L_0x00fe:
        r2 = f7042h;
        r3 = new java.lang.StringBuilder;
        r4 = "Failed to store event:";
        r3.<init>(r4);
        r4 = r10.mo33890g();
        r3 = r3.append(r4);
        r3 = r3.toString();
        r2.error(r3);
        goto L_0x00bf;
    L_0x0117:
        r2 = r4;
        goto L_0x0090;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.C4411au.m8032a(com.tencent.wxop.stat.a.e, com.tencent.wxop.stat.h, boolean):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x00ed A:{SYNTHETIC, Splitter:B:55:0x00ed} */
    /* renamed from: a */
    private synchronized void m8039a(java.util.List<com.tencent.wxop.stat.C4421bd> r8, int r9, boolean r10) {
        /*
        r7 = this;
        r3 = 0;
        monitor-enter(r7);
        r1 = r8.size();	 Catch:{ all -> 0x0087 }
        if (r1 != 0) goto L_0x000a;
    L_0x0008:
        monitor-exit(r7);
        return;
    L_0x000a:
        r4 = r7.m8042b(r10);	 Catch:{ all -> 0x0087 }
        r2 = r7.m8048c(r10);	 Catch:{ Throwable -> 0x00fb, all -> 0x00f8 }
        r1 = 2;
        if (r9 != r1) goto L_0x008a;
    L_0x0015:
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00cd }
        r4 = "update events set status=";
        r1.<init>(r4);	 Catch:{ Throwable -> 0x00cd }
        r1 = r1.append(r9);	 Catch:{ Throwable -> 0x00cd }
        r4 = ", send_count=send_count+1  where ";
        r1 = r1.append(r4);	 Catch:{ Throwable -> 0x00cd }
        r4 = r7.m8030a(r8);	 Catch:{ Throwable -> 0x00cd }
        r1 = r1.append(r4);	 Catch:{ Throwable -> 0x00cd }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x00cd }
        r4 = r1;
    L_0x0033:
        r1 = com.tencent.wxop.stat.StatConfig.isDebugEnable();	 Catch:{ Throwable -> 0x00cd }
        if (r1 == 0) goto L_0x004d;
    L_0x0039:
        r1 = f7042h;	 Catch:{ Throwable -> 0x00cd }
        r5 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00cd }
        r6 = "update sql:";
        r5.<init>(r6);	 Catch:{ Throwable -> 0x00cd }
        r5 = r5.append(r4);	 Catch:{ Throwable -> 0x00cd }
        r5 = r5.toString();	 Catch:{ Throwable -> 0x00cd }
        r1.mo33952i(r5);	 Catch:{ Throwable -> 0x00cd }
    L_0x004d:
        r2.beginTransaction();	 Catch:{ Throwable -> 0x00cd }
        r1 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Throwable -> 0x00cd }
        if (r1 != 0) goto L_0x00c5;
    L_0x0054:
        r2.execSQL(r4);	 Catch:{ Throwable -> 0x00cd }
    L_0x0057:
        if (r3 == 0) goto L_0x0077;
    L_0x0059:
        r1 = f7042h;	 Catch:{ Throwable -> 0x00cd }
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00cd }
        r5 = "update for delete sql:";
        r4.<init>(r5);	 Catch:{ Throwable -> 0x00cd }
        r4 = r4.append(r3);	 Catch:{ Throwable -> 0x00cd }
        r4 = r4.toString();	 Catch:{ Throwable -> 0x00cd }
        r1.mo33952i(r4);	 Catch:{ Throwable -> 0x00cd }
        r1 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Throwable -> 0x00cd }
        if (r1 != 0) goto L_0x00e2;
    L_0x0071:
        r2.execSQL(r3);	 Catch:{ Throwable -> 0x00cd }
    L_0x0074:
        r7.m8051f();	 Catch:{ Throwable -> 0x00cd }
    L_0x0077:
        r2.setTransactionSuccessful();	 Catch:{ Throwable -> 0x00cd }
        if (r2 == 0) goto L_0x0008;
    L_0x007c:
        r2.endTransaction();	 Catch:{ Throwable -> 0x0080 }
        goto L_0x0008;
    L_0x0080:
        r1 = move-exception;
        r2 = f7042h;	 Catch:{ all -> 0x0087 }
        r2.mo33949e(r1);	 Catch:{ all -> 0x0087 }
        goto L_0x0008;
    L_0x0087:
        r1 = move-exception;
        monitor-exit(r7);
        throw r1;
    L_0x008a:
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00cd }
        r5 = "update events set status=";
        r1.<init>(r5);	 Catch:{ Throwable -> 0x00cd }
        r1 = r1.append(r9);	 Catch:{ Throwable -> 0x00cd }
        r5 = " where ";
        r1 = r1.append(r5);	 Catch:{ Throwable -> 0x00cd }
        r5 = r7.m8030a(r8);	 Catch:{ Throwable -> 0x00cd }
        r1 = r1.append(r5);	 Catch:{ Throwable -> 0x00cd }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x00cd }
        r5 = r7.f7052k;	 Catch:{ Throwable -> 0x00cd }
        r5 = r5 % 3;
        if (r5 != 0) goto L_0x00bc;
    L_0x00ad:
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00cd }
        r5 = "delete from events where send_count>";
        r3.<init>(r5);	 Catch:{ Throwable -> 0x00cd }
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00cd }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x00cd }
    L_0x00bc:
        r4 = r7.f7052k;	 Catch:{ Throwable -> 0x00cd }
        r4 = r4 + 1;
        r7.f7052k = r4;	 Catch:{ Throwable -> 0x00cd }
        r4 = r1;
        goto L_0x0033;
    L_0x00c5:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Throwable -> 0x00cd }
        r1 = r0;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.execSQL(r1, r4);	 Catch:{ Throwable -> 0x00cd }
        goto L_0x0057;
    L_0x00cd:
        r1 = move-exception;
    L_0x00ce:
        r3 = f7042h;	 Catch:{ all -> 0x00ea }
        r3.mo33949e(r1);	 Catch:{ all -> 0x00ea }
        if (r2 == 0) goto L_0x0008;
    L_0x00d5:
        r2.endTransaction();	 Catch:{ Throwable -> 0x00da }
        goto L_0x0008;
    L_0x00da:
        r1 = move-exception;
        r2 = f7042h;	 Catch:{ all -> 0x0087 }
        r2.mo33949e(r1);	 Catch:{ all -> 0x0087 }
        goto L_0x0008;
    L_0x00e2:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Throwable -> 0x00cd }
        r1 = r0;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.execSQL(r1, r3);	 Catch:{ Throwable -> 0x00cd }
        goto L_0x0074;
    L_0x00ea:
        r1 = move-exception;
    L_0x00eb:
        if (r2 == 0) goto L_0x00f0;
    L_0x00ed:
        r2.endTransaction();	 Catch:{ Throwable -> 0x00f1 }
    L_0x00f0:
        throw r1;	 Catch:{ all -> 0x0087 }
    L_0x00f1:
        r2 = move-exception;
        r3 = f7042h;	 Catch:{ all -> 0x0087 }
        r3.mo33949e(r2);	 Catch:{ all -> 0x0087 }
        goto L_0x00f0;
    L_0x00f8:
        r1 = move-exception;
        r2 = r3;
        goto L_0x00eb;
    L_0x00fb:
        r1 = move-exception;
        r2 = r3;
        goto L_0x00ce;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.C4411au.m8039a(java.util.List, int, boolean):void");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:28:0x00c5, B:49:0x00f6] */
    /* JADX WARNING: Missing block: B:30:0x00ca, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:32:?, code skipped:
            f7042h.mo33949e(r2);
     */
    /* JADX WARNING: Missing block: B:53:0x00fa, code skipped:
            r3 = move-exception;
     */
    /* JADX WARNING: Missing block: B:54:0x00fb, code skipped:
            f7042h.mo33949e(r3);
     */
    /* renamed from: a */
    private synchronized void m8040a(java.util.List<com.tencent.wxop.stat.C4421bd> r11, boolean r12) {
        /*
        r10 = this;
        r3 = 0;
        monitor-enter(r10);
        r2 = r11.size();	 Catch:{ all -> 0x00d2 }
        if (r2 != 0) goto L_0x000a;
    L_0x0008:
        monitor-exit(r10);
        return;
    L_0x000a:
        r2 = com.tencent.wxop.stat.StatConfig.isDebugEnable();	 Catch:{ all -> 0x00d2 }
        if (r2 == 0) goto L_0x0032;
    L_0x0010:
        r2 = f7042h;	 Catch:{ all -> 0x00d2 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00d2 }
        r5 = "Delete ";
        r4.<init>(r5);	 Catch:{ all -> 0x00d2 }
        r5 = r11.size();	 Catch:{ all -> 0x00d2 }
        r4 = r4.append(r5);	 Catch:{ all -> 0x00d2 }
        r5 = " events, important:";
        r4 = r4.append(r5);	 Catch:{ all -> 0x00d2 }
        r4 = r4.append(r12);	 Catch:{ all -> 0x00d2 }
        r4 = r4.toString();	 Catch:{ all -> 0x00d2 }
        r2.mo33952i(r4);	 Catch:{ all -> 0x00d2 }
    L_0x0032:
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00d2 }
        r2 = r11.size();	 Catch:{ all -> 0x00d2 }
        r2 = r2 * 3;
        r5.<init>(r2);	 Catch:{ all -> 0x00d2 }
        r2 = "event_id in (";
        r5.append(r2);	 Catch:{ all -> 0x00d2 }
        r2 = 0;
        r6 = r11.size();	 Catch:{ all -> 0x00d2 }
        r7 = r11.iterator();	 Catch:{ all -> 0x00d2 }
        r4 = r2;
    L_0x004c:
        r2 = r7.hasNext();	 Catch:{ all -> 0x00d2 }
        if (r2 == 0) goto L_0x006a;
    L_0x0052:
        r2 = r7.next();	 Catch:{ all -> 0x00d2 }
        r2 = (com.tencent.wxop.stat.C4421bd) r2;	 Catch:{ all -> 0x00d2 }
        r8 = r2.f7081a;	 Catch:{ all -> 0x00d2 }
        r5.append(r8);	 Catch:{ all -> 0x00d2 }
        r2 = r6 + -1;
        if (r4 == r2) goto L_0x0066;
    L_0x0061:
        r2 = ",";
        r5.append(r2);	 Catch:{ all -> 0x00d2 }
    L_0x0066:
        r2 = r4 + 1;
        r4 = r2;
        goto L_0x004c;
    L_0x006a:
        r2 = ")";
        r5.append(r2);	 Catch:{ all -> 0x00d2 }
        r3 = r10.m8048c(r12);	 Catch:{ Throwable -> 0x00de }
        r3.beginTransaction();	 Catch:{ Throwable -> 0x00de }
        r4 = "events";
        r7 = r5.toString();	 Catch:{ Throwable -> 0x00de }
        r8 = 0;
        r2 = r3 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Throwable -> 0x00de }
        if (r2 != 0) goto L_0x00d5;
    L_0x0081:
        r2 = r3.delete(r4, r7, r8);	 Catch:{ Throwable -> 0x00de }
    L_0x0085:
        r4 = com.tencent.wxop.stat.StatConfig.isDebugEnable();	 Catch:{ Throwable -> 0x00de }
        if (r4 == 0) goto L_0x00b7;
    L_0x008b:
        r4 = f7042h;	 Catch:{ Throwable -> 0x00de }
        r7 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00de }
        r8 = "delete ";
        r7.<init>(r8);	 Catch:{ Throwable -> 0x00de }
        r6 = r7.append(r6);	 Catch:{ Throwable -> 0x00de }
        r7 = " event ";
        r6 = r6.append(r7);	 Catch:{ Throwable -> 0x00de }
        r5 = r5.toString();	 Catch:{ Throwable -> 0x00de }
        r5 = r6.append(r5);	 Catch:{ Throwable -> 0x00de }
        r6 = ", success delete:";
        r5 = r5.append(r6);	 Catch:{ Throwable -> 0x00de }
        r5 = r5.append(r2);	 Catch:{ Throwable -> 0x00de }
        r5 = r5.toString();	 Catch:{ Throwable -> 0x00de }
        r4.mo33952i(r5);	 Catch:{ Throwable -> 0x00de }
    L_0x00b7:
        r4 = r10.f7045a;	 Catch:{ Throwable -> 0x00de }
        r2 = r4 - r2;
        r10.f7045a = r2;	 Catch:{ Throwable -> 0x00de }
        r3.setTransactionSuccessful();	 Catch:{ Throwable -> 0x00de }
        r10.m8051f();	 Catch:{ Throwable -> 0x00de }
        if (r3 == 0) goto L_0x0008;
    L_0x00c5:
        r3.endTransaction();	 Catch:{ Throwable -> 0x00ca }
        goto L_0x0008;
    L_0x00ca:
        r2 = move-exception;
        r3 = f7042h;	 Catch:{ all -> 0x00d2 }
        r3.mo33949e(r2);	 Catch:{ all -> 0x00d2 }
        goto L_0x0008;
    L_0x00d2:
        r2 = move-exception;
        monitor-exit(r10);
        throw r2;
    L_0x00d5:
        r0 = r3;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Throwable -> 0x00de }
        r2 = r0;
        r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.delete(r2, r4, r7, r8);	 Catch:{ Throwable -> 0x00de }
        goto L_0x0085;
    L_0x00de:
        r2 = move-exception;
        r4 = f7042h;	 Catch:{ all -> 0x00f3 }
        r4.mo33949e(r2);	 Catch:{ all -> 0x00f3 }
        if (r3 == 0) goto L_0x0008;
    L_0x00e6:
        r3.endTransaction();	 Catch:{ Throwable -> 0x00eb }
        goto L_0x0008;
    L_0x00eb:
        r2 = move-exception;
        r3 = f7042h;	 Catch:{ all -> 0x00d2 }
        r3.mo33949e(r2);	 Catch:{ all -> 0x00d2 }
        goto L_0x0008;
    L_0x00f3:
        r2 = move-exception;
        if (r3 == 0) goto L_0x00f9;
    L_0x00f6:
        r3.endTransaction();	 Catch:{ Throwable -> 0x00fa }
    L_0x00f9:
        throw r2;	 Catch:{ all -> 0x00d2 }
    L_0x00fa:
        r3 = move-exception;
        r4 = f7042h;	 Catch:{ all -> 0x00d2 }
        r4.mo33949e(r3);	 Catch:{ all -> 0x00d2 }
        goto L_0x00f9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.C4411au.m8040a(java.util.List, boolean):void");
    }

    /* renamed from: a */
    private void m8041a(boolean z) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = m8048c(z);
            sQLiteDatabase.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put("status", Integer.valueOf(1));
            String str = "events";
            String str2 = "status=?";
            String[] strArr = new String[]{Long.toString(2)};
            int update = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.update(str, contentValues, str2, strArr) : SQLiteInstrumentation.update(sQLiteDatabase, str, contentValues, str2, strArr);
            if (StatConfig.isDebugEnable()) {
                f7042h.mo33952i("update " + update + " unsent events.");
            }
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Throwable th) {
                    f7042h.mo33949e(th);
                }
            }
        } catch (Throwable th2) {
            f7042h.mo33949e(th2);
        }
    }

    /* renamed from: b */
    private int m8042b(boolean z) {
        return !z ? StatConfig.getMaxSendRetryCount() : StatConfig.getMaxImportantDataSendRetryCount();
    }

    /* renamed from: b */
    public static C4411au m8043b() {
        return f7044j;
    }

    /* renamed from: b */
    private void m8044b(int i, boolean z) {
        int g = i == -1 ? !z ? m8052g() : m8053h() : i;
        if (g > 0) {
            int sendPeriodMinutes = (StatConfig.getSendPeriodMinutes() * 60) * StatConfig.getNumEventsCommitPerSec();
            if (g > sendPeriodMinutes && sendPeriodMinutes > 0) {
                g = sendPeriodMinutes;
            }
            int a = StatConfig.m7916a();
            int i2 = g / a;
            int i3 = g % a;
            if (StatConfig.isDebugEnable()) {
                f7042h.mo33952i("sentStoreEventsByDb sendNumbers=" + g + ",important=" + z + ",maxSendNumPerFor1Period=" + sendPeriodMinutes + ",maxCount=" + i2 + ",restNumbers=" + i3);
            }
            for (g = 0; g < i2; g++) {
                m8031a(a, z);
            }
            if (i3 > 0) {
                m8031a(i3, z);
            }
        }
    }

    /* renamed from: b */
    private synchronized void m8045b(C4377e c4377e, C4407h c4407h, boolean z, boolean z2) {
        if (StatConfig.getMaxStoreEventCount() > 0) {
            if (StatConfig.f6899m <= 0 || z || z2) {
                m8032a(c4377e, c4407h, z);
            } else if (StatConfig.f6899m > 0) {
                if (StatConfig.isDebugEnable()) {
                    f7042h.mo33952i("cacheEventsInMemory.size():" + this.f7053l.size() + ",numEventsCachedInMemory:" + StatConfig.f6899m + ",numStoredEvents:" + this.f7045a);
                    f7042h.mo33952i("cache event:" + c4377e.mo33890g());
                }
                this.f7053l.put(c4377e, "");
                if (this.f7053l.size() >= StatConfig.f6899m) {
                    m8054i();
                }
                if (c4407h != null) {
                    if (this.f7053l.size() > 0) {
                        m8054i();
                    }
                    c4407h.mo33923a();
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x010e A:{SYNTHETIC, Splitter:B:48:0x010e} */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0121 A:{SYNTHETIC, Splitter:B:56:0x0121} */
    /* renamed from: b */
    private synchronized void m8046b(com.tencent.wxop.stat.C4443f r15) {
        /*
        r14 = this;
        r9 = 1;
        r10 = 0;
        r8 = 0;
        monitor-enter(r14);
        r11 = r15.mo33975a();	 Catch:{ Throwable -> 0x0135, all -> 0x011d }
        r1 = com.tencent.wxop.stat.common.C4433k.m8105a(r11);	 Catch:{ Throwable -> 0x0135, all -> 0x011d }
        r12 = new android.content.ContentValues;	 Catch:{ Throwable -> 0x0135, all -> 0x011d }
        r12.<init>();	 Catch:{ Throwable -> 0x0135, all -> 0x011d }
        r2 = "content";
        r0 = r15.f7171b;	 Catch:{ Throwable -> 0x0135, all -> 0x011d }
        r3 = r0 instanceof org.json.JSONObject;	 Catch:{ Throwable -> 0x0135, all -> 0x011d }
        if (r3 != 0) goto L_0x00b6;
    L_0x0019:
        r0 = r0.toString();	 Catch:{ Throwable -> 0x0135, all -> 0x011d }
    L_0x001d:
        r12.put(r2, r0);	 Catch:{ Throwable -> 0x0135, all -> 0x011d }
        r0 = "md5sum";
        r12.put(r0, r1);	 Catch:{ Throwable -> 0x0135, all -> 0x011d }
        r15.f7172c = r1;	 Catch:{ Throwable -> 0x0135, all -> 0x011d }
        r0 = "version";
        r1 = r15.f7173d;	 Catch:{ Throwable -> 0x0135, all -> 0x011d }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ Throwable -> 0x0135, all -> 0x011d }
        r12.put(r0, r1);	 Catch:{ Throwable -> 0x0135, all -> 0x011d }
        r0 = r14.f7047c;	 Catch:{ Throwable -> 0x0135, all -> 0x011d }
        r0 = r0.getReadableDatabase();	 Catch:{ Throwable -> 0x0135, all -> 0x011d }
        r1 = "config";
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r13 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Throwable -> 0x0135, all -> 0x011d }
        if (r13 != 0) goto L_0x00be;
    L_0x0044:
        r0 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Throwable -> 0x0135, all -> 0x011d }
        r1 = r0;
    L_0x0049:
        r0 = r1.moveToNext();	 Catch:{ Throwable -> 0x0106 }
        if (r0 == 0) goto L_0x013b;
    L_0x004f:
        r0 = 0;
        r0 = r1.getInt(r0);	 Catch:{ Throwable -> 0x0106 }
        r2 = r15.f7170a;	 Catch:{ Throwable -> 0x0106 }
        if (r0 != r2) goto L_0x0049;
    L_0x0058:
        r0 = r9;
    L_0x0059:
        r2 = r14.f7047c;	 Catch:{ Throwable -> 0x0106 }
        r2 = r2.getWritableDatabase();	 Catch:{ Throwable -> 0x0106 }
        r2.beginTransaction();	 Catch:{ Throwable -> 0x0106 }
        if (r9 != r0) goto L_0x00cd;
    L_0x0064:
        r0 = r14.f7047c;	 Catch:{ Throwable -> 0x0106 }
        r0 = r0.getWritableDatabase();	 Catch:{ Throwable -> 0x0106 }
        r2 = "config";
        r3 = "type=?";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Throwable -> 0x0106 }
        r5 = 0;
        r6 = r15.f7170a;	 Catch:{ Throwable -> 0x0106 }
        r6 = java.lang.Integer.toString(r6);	 Catch:{ Throwable -> 0x0106 }
        r4[r5] = r6;	 Catch:{ Throwable -> 0x0106 }
        r5 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Throwable -> 0x0106 }
        if (r5 != 0) goto L_0x00c6;
    L_0x007e:
        r0 = r0.update(r2, r12, r3, r4);	 Catch:{ Throwable -> 0x0106 }
    L_0x0082:
        r2 = (long) r0;	 Catch:{ Throwable -> 0x0106 }
    L_0x0083:
        r4 = -1;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 != 0) goto L_0x00f1;
    L_0x0089:
        r0 = f7042h;	 Catch:{ Throwable -> 0x0106 }
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0106 }
        r3 = "Failed to store cfg:";
        r2.<init>(r3);	 Catch:{ Throwable -> 0x0106 }
        r2 = r2.append(r11);	 Catch:{ Throwable -> 0x0106 }
        r2 = r2.toString();	 Catch:{ Throwable -> 0x0106 }
        r0.mo33948e(r2);	 Catch:{ Throwable -> 0x0106 }
    L_0x009d:
        r0 = r14.f7047c;	 Catch:{ Throwable -> 0x0106 }
        r0 = r0.getWritableDatabase();	 Catch:{ Throwable -> 0x0106 }
        r0.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0106 }
        if (r1 == 0) goto L_0x00ab;
    L_0x00a8:
        r1.close();	 Catch:{ all -> 0x012e }
    L_0x00ab:
        r0 = r14.f7047c;	 Catch:{ Exception -> 0x0138 }
        r0 = r0.getWritableDatabase();	 Catch:{ Exception -> 0x0138 }
        r0.endTransaction();	 Catch:{ Exception -> 0x0138 }
    L_0x00b4:
        monitor-exit(r14);
        return;
    L_0x00b6:
        r0 = (org.json.JSONObject) r0;	 Catch:{ Throwable -> 0x0135, all -> 0x011d }
        r0 = com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation.toString(r0);	 Catch:{ Throwable -> 0x0135, all -> 0x011d }
        goto L_0x001d;
    L_0x00be:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Throwable -> 0x0135, all -> 0x011d }
        r0 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Throwable -> 0x0135, all -> 0x011d }
        r1 = r0;
        goto L_0x0049;
    L_0x00c6:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Throwable -> 0x0106 }
        r0 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r0, r2, r12, r3, r4);	 Catch:{ Throwable -> 0x0106 }
        goto L_0x0082;
    L_0x00cd:
        r0 = "type";
        r2 = r15.f7170a;	 Catch:{ Throwable -> 0x0106 }
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Throwable -> 0x0106 }
        r12.put(r0, r2);	 Catch:{ Throwable -> 0x0106 }
        r0 = r14.f7047c;	 Catch:{ Throwable -> 0x0106 }
        r0 = r0.getWritableDatabase();	 Catch:{ Throwable -> 0x0106 }
        r2 = "config";
        r3 = 0;
        r4 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Throwable -> 0x0106 }
        if (r4 != 0) goto L_0x00ea;
    L_0x00e5:
        r2 = r0.insert(r2, r3, r12);	 Catch:{ Throwable -> 0x0106 }
        goto L_0x0083;
    L_0x00ea:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Throwable -> 0x0106 }
        r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.insert(r0, r2, r3, r12);	 Catch:{ Throwable -> 0x0106 }
        goto L_0x0083;
    L_0x00f1:
        r0 = f7042h;	 Catch:{ Throwable -> 0x0106 }
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0106 }
        r3 = "Sucessed to store cfg:";
        r2.<init>(r3);	 Catch:{ Throwable -> 0x0106 }
        r2 = r2.append(r11);	 Catch:{ Throwable -> 0x0106 }
        r2 = r2.toString();	 Catch:{ Throwable -> 0x0106 }
        r0.mo33946d(r2);	 Catch:{ Throwable -> 0x0106 }
        goto L_0x009d;
    L_0x0106:
        r0 = move-exception;
    L_0x0107:
        r2 = f7042h;	 Catch:{ all -> 0x0133 }
        r2.mo33949e(r0);	 Catch:{ all -> 0x0133 }
        if (r1 == 0) goto L_0x0111;
    L_0x010e:
        r1.close();	 Catch:{ all -> 0x012e }
    L_0x0111:
        r0 = r14.f7047c;	 Catch:{ Exception -> 0x011b }
        r0 = r0.getWritableDatabase();	 Catch:{ Exception -> 0x011b }
        r0.endTransaction();	 Catch:{ Exception -> 0x011b }
        goto L_0x00b4;
    L_0x011b:
        r0 = move-exception;
        goto L_0x00b4;
    L_0x011d:
        r0 = move-exception;
        r1 = r8;
    L_0x011f:
        if (r1 == 0) goto L_0x0124;
    L_0x0121:
        r1.close();	 Catch:{ all -> 0x012e }
    L_0x0124:
        r1 = r14.f7047c;	 Catch:{ Exception -> 0x0131 }
        r1 = r1.getWritableDatabase();	 Catch:{ Exception -> 0x0131 }
        r1.endTransaction();	 Catch:{ Exception -> 0x0131 }
    L_0x012d:
        throw r0;	 Catch:{ all -> 0x012e }
    L_0x012e:
        r0 = move-exception;
        monitor-exit(r14);
        throw r0;
    L_0x0131:
        r1 = move-exception;
        goto L_0x012d;
    L_0x0133:
        r0 = move-exception;
        goto L_0x011f;
    L_0x0135:
        r0 = move-exception;
        r1 = r8;
        goto L_0x0107;
    L_0x0138:
        r0 = move-exception;
        goto L_0x00b4;
    L_0x013b:
        r0 = r10;
        goto L_0x0059;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.C4411au.m8046b(com.tencent.wxop.stat.f):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a1  */
    /* renamed from: b */
    private void m8047b(java.util.List<com.tencent.wxop.stat.C4421bd> r13, int r14, boolean r15) {
        /*
        r12 = this;
        r9 = 0;
        r0 = r12.m8049d(r15);	 Catch:{ Throwable -> 0x00ac, all -> 0x009e }
        r1 = "events";
        r2 = 0;
        r3 = "status=?";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Throwable -> 0x00ac, all -> 0x009e }
        r5 = 0;
        r6 = 1;
        r6 = java.lang.Integer.toString(r6);	 Catch:{ Throwable -> 0x00ac, all -> 0x009e }
        r4[r5] = r6;	 Catch:{ Throwable -> 0x00ac, all -> 0x009e }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = java.lang.Integer.toString(r14);	 Catch:{ Throwable -> 0x00ac, all -> 0x009e }
        r10 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Throwable -> 0x00ac, all -> 0x009e }
        if (r10 != 0) goto L_0x0091;
    L_0x0020:
        r0 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Throwable -> 0x00ac, all -> 0x009e }
    L_0x0024:
        r1 = r0.moveToNext();	 Catch:{ Throwable -> 0x0082, all -> 0x00a5 }
        if (r1 == 0) goto L_0x0098;
    L_0x002a:
        r1 = 0;
        r2 = r0.getLong(r1);	 Catch:{ Throwable -> 0x0082, all -> 0x00a5 }
        r1 = 1;
        r4 = r0.getString(r1);	 Catch:{ Throwable -> 0x0082, all -> 0x00a5 }
        r1 = com.tencent.wxop.stat.StatConfig.f6893g;	 Catch:{ Throwable -> 0x0082, all -> 0x00a5 }
        if (r1 != 0) goto L_0x003c;
    L_0x0038:
        r4 = com.tencent.wxop.stat.common.C4439q.m8157a(r4);	 Catch:{ Throwable -> 0x0082, all -> 0x00a5 }
    L_0x003c:
        r1 = 2;
        r5 = r0.getInt(r1);	 Catch:{ Throwable -> 0x0082, all -> 0x00a5 }
        r1 = 3;
        r6 = r0.getInt(r1);	 Catch:{ Throwable -> 0x0082, all -> 0x00a5 }
        r1 = new com.tencent.wxop.stat.bd;	 Catch:{ Throwable -> 0x0082, all -> 0x00a5 }
        r1.<init>(r2, r4, r5, r6);	 Catch:{ Throwable -> 0x0082, all -> 0x00a5 }
        r4 = com.tencent.wxop.stat.StatConfig.isDebugEnable();	 Catch:{ Throwable -> 0x0082, all -> 0x00a5 }
        if (r4 == 0) goto L_0x007e;
    L_0x0051:
        r4 = f7042h;	 Catch:{ Throwable -> 0x0082, all -> 0x00a5 }
        r5 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0082, all -> 0x00a5 }
        r7 = "peek event, id=";
        r5.<init>(r7);	 Catch:{ Throwable -> 0x0082, all -> 0x00a5 }
        r2 = r5.append(r2);	 Catch:{ Throwable -> 0x0082, all -> 0x00a5 }
        r3 = ",send_count=";
        r2 = r2.append(r3);	 Catch:{ Throwable -> 0x0082, all -> 0x00a5 }
        r2 = r2.append(r6);	 Catch:{ Throwable -> 0x0082, all -> 0x00a5 }
        r3 = ",timestamp=";
        r2 = r2.append(r3);	 Catch:{ Throwable -> 0x0082, all -> 0x00a5 }
        r3 = 4;
        r6 = r0.getLong(r3);	 Catch:{ Throwable -> 0x0082, all -> 0x00a5 }
        r2 = r2.append(r6);	 Catch:{ Throwable -> 0x0082, all -> 0x00a5 }
        r2 = r2.toString();	 Catch:{ Throwable -> 0x0082, all -> 0x00a5 }
        r4.mo33952i(r2);	 Catch:{ Throwable -> 0x0082, all -> 0x00a5 }
    L_0x007e:
        r13.add(r1);	 Catch:{ Throwable -> 0x0082, all -> 0x00a5 }
        goto L_0x0024;
    L_0x0082:
        r1 = move-exception;
        r11 = r1;
        r1 = r0;
        r0 = r11;
    L_0x0086:
        r2 = f7042h;	 Catch:{ all -> 0x00a9 }
        r2.mo33949e(r0);	 Catch:{ all -> 0x00a9 }
        if (r1 == 0) goto L_0x0090;
    L_0x008d:
        r1.close();
    L_0x0090:
        return;
    L_0x0091:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Throwable -> 0x00ac, all -> 0x009e }
        r0 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Throwable -> 0x00ac, all -> 0x009e }
        goto L_0x0024;
    L_0x0098:
        if (r0 == 0) goto L_0x0090;
    L_0x009a:
        r0.close();
        goto L_0x0090;
    L_0x009e:
        r0 = move-exception;
    L_0x009f:
        if (r9 == 0) goto L_0x00a4;
    L_0x00a1:
        r9.close();
    L_0x00a4:
        throw r0;
    L_0x00a5:
        r1 = move-exception;
        r9 = r0;
        r0 = r1;
        goto L_0x009f;
    L_0x00a9:
        r0 = move-exception;
        r9 = r1;
        goto L_0x009f;
    L_0x00ac:
        r0 = move-exception;
        r1 = r9;
        goto L_0x0086;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.C4411au.m8047b(java.util.List, int, boolean):void");
    }

    /* renamed from: c */
    private SQLiteDatabase m8048c(boolean z) {
        return !z ? this.f7047c.getWritableDatabase() : this.f7048d.getWritableDatabase();
    }

    /* renamed from: d */
    private SQLiteDatabase m8049d(boolean z) {
        return !z ? this.f7047c.getReadableDatabase() : this.f7048d.getReadableDatabase();
    }

    /* renamed from: f */
    private void m8051f() {
        this.f7045a = m8052g() + m8053h();
    }

    /* renamed from: g */
    private int m8052g() {
        return (int) DatabaseUtils.queryNumEntries(this.f7047c.getReadableDatabase(), "events");
    }

    /* renamed from: h */
    private int m8053h() {
        return (int) DatabaseUtils.queryNumEntries(this.f7048d.getReadableDatabase(), "events");
    }

    /* JADX WARNING: Missing block: B:63:?, code skipped:
            return;
     */
    /* renamed from: i */
    private void m8054i() {
        /*
        r11 = this;
        r3 = 0;
        r2 = r11.f7054m;
        if (r2 == 0) goto L_0x0006;
    L_0x0005:
        return;
    L_0x0006:
        r4 = r11.f7053l;
        monitor-enter(r4);
        r2 = r11.f7053l;	 Catch:{ all -> 0x0013 }
        r2 = r2.size();	 Catch:{ all -> 0x0013 }
        if (r2 != 0) goto L_0x0016;
    L_0x0011:
        monitor-exit(r4);	 Catch:{ all -> 0x0013 }
        goto L_0x0005;
    L_0x0013:
        r2 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0013 }
        throw r2;
    L_0x0016:
        r2 = 1;
        r11.f7054m = r2;	 Catch:{ all -> 0x0013 }
        r2 = com.tencent.wxop.stat.StatConfig.isDebugEnable();	 Catch:{ all -> 0x0013 }
        if (r2 == 0) goto L_0x0051;
    L_0x001f:
        r2 = f7042h;	 Catch:{ all -> 0x0013 }
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0013 }
        r6 = "insert ";
        r5.<init>(r6);	 Catch:{ all -> 0x0013 }
        r6 = r11.f7053l;	 Catch:{ all -> 0x0013 }
        r6 = r6.size();	 Catch:{ all -> 0x0013 }
        r5 = r5.append(r6);	 Catch:{ all -> 0x0013 }
        r6 = " events ,numEventsCachedInMemory:";
        r5 = r5.append(r6);	 Catch:{ all -> 0x0013 }
        r6 = com.tencent.wxop.stat.StatConfig.f6899m;	 Catch:{ all -> 0x0013 }
        r5 = r5.append(r6);	 Catch:{ all -> 0x0013 }
        r6 = ",numStoredEvents:";
        r5 = r5.append(r6);	 Catch:{ all -> 0x0013 }
        r6 = r11.f7045a;	 Catch:{ all -> 0x0013 }
        r5 = r5.append(r6);	 Catch:{ all -> 0x0013 }
        r5 = r5.toString();	 Catch:{ all -> 0x0013 }
        r2.mo33952i(r5);	 Catch:{ all -> 0x0013 }
    L_0x0051:
        r2 = r11.f7047c;	 Catch:{ Throwable -> 0x00ce }
        r3 = r2.getWritableDatabase();	 Catch:{ Throwable -> 0x00ce }
        r3.beginTransaction();	 Catch:{ Throwable -> 0x00ce }
        r2 = r11.f7053l;	 Catch:{ Throwable -> 0x00ce }
        r2 = r2.entrySet();	 Catch:{ Throwable -> 0x00ce }
        r5 = r2.iterator();	 Catch:{ Throwable -> 0x00ce }
    L_0x0064:
        r2 = r5.hasNext();	 Catch:{ Throwable -> 0x00ce }
        if (r2 == 0) goto L_0x012c;
    L_0x006a:
        r2 = r5.next();	 Catch:{ Throwable -> 0x00ce }
        r2 = (java.util.Map.Entry) r2;	 Catch:{ Throwable -> 0x00ce }
        r2 = r2.getKey();	 Catch:{ Throwable -> 0x00ce }
        r2 = (com.tencent.wxop.stat.p069a.C4377e) r2;	 Catch:{ Throwable -> 0x00ce }
        r6 = new android.content.ContentValues;	 Catch:{ Throwable -> 0x00ce }
        r6.<init>();	 Catch:{ Throwable -> 0x00ce }
        r7 = r2.mo33890g();	 Catch:{ Throwable -> 0x00ce }
        r8 = com.tencent.wxop.stat.StatConfig.isDebugEnable();	 Catch:{ Throwable -> 0x00ce }
        if (r8 == 0) goto L_0x0099;
    L_0x0085:
        r8 = f7042h;	 Catch:{ Throwable -> 0x00ce }
        r9 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00ce }
        r10 = "insert content:";
        r9.<init>(r10);	 Catch:{ Throwable -> 0x00ce }
        r9 = r9.append(r7);	 Catch:{ Throwable -> 0x00ce }
        r9 = r9.toString();	 Catch:{ Throwable -> 0x00ce }
        r8.mo33952i(r9);	 Catch:{ Throwable -> 0x00ce }
    L_0x0099:
        r7 = com.tencent.wxop.stat.common.C4439q.m8162b(r7);	 Catch:{ Throwable -> 0x00ce }
        r8 = "content";
        r6.put(r8, r7);	 Catch:{ Throwable -> 0x00ce }
        r7 = "send_count";
        r8 = "0";
        r6.put(r7, r8);	 Catch:{ Throwable -> 0x00ce }
        r7 = "status";
        r8 = 1;
        r8 = java.lang.Integer.toString(r8);	 Catch:{ Throwable -> 0x00ce }
        r6.put(r7, r8);	 Catch:{ Throwable -> 0x00ce }
        r7 = "timestamp";
        r8 = r2.mo33886c();	 Catch:{ Throwable -> 0x00ce }
        r2 = java.lang.Long.valueOf(r8);	 Catch:{ Throwable -> 0x00ce }
        r6.put(r7, r2);	 Catch:{ Throwable -> 0x00ce }
        r7 = "events";
        r8 = 0;
        r2 = r3 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Throwable -> 0x00ce }
        if (r2 != 0) goto L_0x011a;
    L_0x00c7:
        r3.insert(r7, r8, r6);	 Catch:{ Throwable -> 0x00ce }
    L_0x00ca:
        r5.remove();	 Catch:{ Throwable -> 0x00ce }
        goto L_0x0064;
    L_0x00ce:
        r2 = move-exception;
        r5 = f7042h;	 Catch:{ all -> 0x0122 }
        r5.mo33949e(r2);	 Catch:{ all -> 0x0122 }
        if (r3 == 0) goto L_0x00dc;
    L_0x00d6:
        r3.endTransaction();	 Catch:{ Throwable -> 0x013f }
        r11.m8051f();	 Catch:{ Throwable -> 0x013f }
    L_0x00dc:
        r2 = 0;
        r11.f7054m = r2;	 Catch:{ all -> 0x0013 }
        r2 = com.tencent.wxop.stat.StatConfig.isDebugEnable();	 Catch:{ all -> 0x0013 }
        if (r2 == 0) goto L_0x0117;
    L_0x00e5:
        r2 = f7042h;	 Catch:{ all -> 0x0013 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0013 }
        r5 = "after insert, cacheEventsInMemory.size():";
        r3.<init>(r5);	 Catch:{ all -> 0x0013 }
        r5 = r11.f7053l;	 Catch:{ all -> 0x0013 }
        r5 = r5.size();	 Catch:{ all -> 0x0013 }
        r3 = r3.append(r5);	 Catch:{ all -> 0x0013 }
        r5 = ",numEventsCachedInMemory:";
        r3 = r3.append(r5);	 Catch:{ all -> 0x0013 }
        r5 = com.tencent.wxop.stat.StatConfig.f6899m;	 Catch:{ all -> 0x0013 }
        r3 = r3.append(r5);	 Catch:{ all -> 0x0013 }
        r5 = ",numStoredEvents:";
        r3 = r3.append(r5);	 Catch:{ all -> 0x0013 }
        r5 = r11.f7045a;	 Catch:{ all -> 0x0013 }
        r3 = r3.append(r5);	 Catch:{ all -> 0x0013 }
        r3 = r3.toString();	 Catch:{ all -> 0x0013 }
        r2.mo33952i(r3);	 Catch:{ all -> 0x0013 }
    L_0x0117:
        monitor-exit(r4);	 Catch:{ all -> 0x0013 }
        goto L_0x0005;
    L_0x011a:
        r0 = r3;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Throwable -> 0x00ce }
        r2 = r0;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.insert(r2, r7, r8, r6);	 Catch:{ Throwable -> 0x00ce }
        goto L_0x00ca;
    L_0x0122:
        r2 = move-exception;
        if (r3 == 0) goto L_0x012b;
    L_0x0125:
        r3.endTransaction();	 Catch:{ Throwable -> 0x0146 }
        r11.m8051f();	 Catch:{ Throwable -> 0x0146 }
    L_0x012b:
        throw r2;	 Catch:{ all -> 0x0013 }
    L_0x012c:
        r3.setTransactionSuccessful();	 Catch:{ Throwable -> 0x00ce }
        if (r3 == 0) goto L_0x00dc;
    L_0x0131:
        r3.endTransaction();	 Catch:{ Throwable -> 0x0138 }
        r11.m8051f();	 Catch:{ Throwable -> 0x0138 }
        goto L_0x00dc;
    L_0x0138:
        r2 = move-exception;
        r3 = f7042h;	 Catch:{ all -> 0x0013 }
        r3.mo33949e(r2);	 Catch:{ all -> 0x0013 }
        goto L_0x00dc;
    L_0x013f:
        r2 = move-exception;
        r3 = f7042h;	 Catch:{ all -> 0x0013 }
        r3.mo33949e(r2);	 Catch:{ all -> 0x0013 }
        goto L_0x00dc;
    L_0x0146:
        r3 = move-exception;
        r5 = f7042h;	 Catch:{ all -> 0x0013 }
        r5.mo33949e(r3);	 Catch:{ all -> 0x0013 }
        goto L_0x012b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.C4411au.m8054i():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004c  */
    /* renamed from: j */
    private void m8055j() {
        /*
        r11 = this;
        r8 = 0;
        r0 = r11.f7047c;	 Catch:{ Throwable -> 0x0057, all -> 0x0049 }
        r0 = r0.getReadableDatabase();	 Catch:{ Throwable -> 0x0057, all -> 0x0049 }
        r1 = "keyvalues";
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r9 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Throwable -> 0x0057, all -> 0x0049 }
        if (r9 != 0) goto L_0x003c;
    L_0x0013:
        r0 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Throwable -> 0x0057, all -> 0x0049 }
    L_0x0017:
        r1 = r0.moveToNext();	 Catch:{ Throwable -> 0x002d, all -> 0x0050 }
        if (r1 == 0) goto L_0x0043;
    L_0x001d:
        r1 = r11.f7055n;	 Catch:{ Throwable -> 0x002d, all -> 0x0050 }
        r2 = 0;
        r2 = r0.getString(r2);	 Catch:{ Throwable -> 0x002d, all -> 0x0050 }
        r3 = 1;
        r3 = r0.getString(r3);	 Catch:{ Throwable -> 0x002d, all -> 0x0050 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x002d, all -> 0x0050 }
        goto L_0x0017;
    L_0x002d:
        r1 = move-exception;
        r10 = r1;
        r1 = r0;
        r0 = r10;
    L_0x0031:
        r2 = f7042h;	 Catch:{ all -> 0x0054 }
        r2.mo33949e(r0);	 Catch:{ all -> 0x0054 }
        if (r1 == 0) goto L_0x003b;
    L_0x0038:
        r1.close();
    L_0x003b:
        return;
    L_0x003c:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Throwable -> 0x0057, all -> 0x0049 }
        r0 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Throwable -> 0x0057, all -> 0x0049 }
        goto L_0x0017;
    L_0x0043:
        if (r0 == 0) goto L_0x003b;
    L_0x0045:
        r0.close();
        goto L_0x003b;
    L_0x0049:
        r0 = move-exception;
    L_0x004a:
        if (r8 == 0) goto L_0x004f;
    L_0x004c:
        r8.close();
    L_0x004f:
        throw r0;
    L_0x0050:
        r1 = move-exception;
        r8 = r0;
        r0 = r1;
        goto L_0x004a;
    L_0x0054:
        r0 = move-exception;
        r8 = r1;
        goto L_0x004a;
    L_0x0057:
        r0 = move-exception;
        r1 = r8;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.C4411au.m8055j():void");
    }

    /* renamed from: a */
    public int mo33925a() {
        return this.f7045a;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo33926a(int i) {
        this.f7049e.mo33966a(new C4419bb(this, i));
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo33927a(C4377e c4377e, C4407h c4407h, boolean z, boolean z2) {
        if (this.f7049e != null) {
            this.f7049e.mo33966a(new C4415ay(this, c4377e, c4407h, z, z2));
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo33928a(C4443f c4443f) {
        if (c4443f != null) {
            this.f7049e.mo33966a(new C4416az(this, c4443f));
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo33929a(List<C4421bd> list, int i, boolean z, boolean z2) {
        if (this.f7049e != null) {
            this.f7049e.mo33966a(new C4412av(this, list, i, z, z2));
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo33930a(List<C4421bd> list, boolean z, boolean z2) {
        if (this.f7049e != null) {
            this.f7049e.mo33966a(new C4413aw(this, list, z, z2));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x0239 A:{SYNTHETIC, Splitter:B:100:0x0239} */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0239 A:{SYNTHETIC, Splitter:B:100:0x0239} */
    /* renamed from: b */
    public synchronized com.tencent.wxop.stat.common.C4423a mo33931b(android.content.Context r20) {
        /*
        r19 = this;
        monitor-enter(r19);
        r0 = r19;
        r2 = r0.f7046b;	 Catch:{ all -> 0x022c }
        if (r2 == 0) goto L_0x000d;
    L_0x0007:
        r0 = r19;
        r2 = r0.f7046b;	 Catch:{ all -> 0x022c }
    L_0x000b:
        monitor-exit(r19);
        return r2;
    L_0x000d:
        r11 = 0;
        r0 = r19;
        r2 = r0.f7047c;	 Catch:{ Throwable -> 0x0264, all -> 0x025e }
        r2 = r2.getWritableDatabase();	 Catch:{ Throwable -> 0x0264, all -> 0x025e }
        r2.beginTransaction();	 Catch:{ Throwable -> 0x0264, all -> 0x025e }
        r2 = com.tencent.wxop.stat.StatConfig.isDebugEnable();	 Catch:{ Throwable -> 0x0264, all -> 0x025e }
        if (r2 == 0) goto L_0x0026;
    L_0x001f:
        r2 = f7042h;	 Catch:{ Throwable -> 0x0264, all -> 0x025e }
        r3 = "try to load user info from db.";
        r2.mo33952i(r3);	 Catch:{ Throwable -> 0x0264, all -> 0x025e }
    L_0x0026:
        r0 = r19;
        r2 = r0.f7047c;	 Catch:{ Throwable -> 0x0264, all -> 0x025e }
        r2 = r2.getReadableDatabase();	 Catch:{ Throwable -> 0x0264, all -> 0x025e }
        r3 = "user";
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = 0;
        r12 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Throwable -> 0x0264, all -> 0x025e }
        if (r12 != 0) goto L_0x01d0;
    L_0x003b:
        r5 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ Throwable -> 0x0264, all -> 0x025e }
    L_0x003f:
        r2 = 0;
        r3 = r5.moveToNext();	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        if (r3 == 0) goto L_0x0139;
    L_0x0046:
        r2 = 0;
        r10 = r5.getString(r2);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r7 = com.tencent.wxop.stat.common.C4439q.m8157a(r10);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r2 = 1;
        r9 = r5.getInt(r2);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r2 = 2;
        r3 = r5.getString(r2);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r2 = 3;
        r12 = r5.getLong(r2);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r6 = 1;
        r14 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r16 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r14 = r14 / r16;
        r2 = 1;
        if (r9 == r2) goto L_0x0277;
    L_0x006a:
        r16 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r12 = r12 * r16;
        r2 = com.tencent.wxop.stat.common.C4433k.m8103a(r12);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r12 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r12 = r12 * r14;
        r4 = com.tencent.wxop.stat.common.C4433k.m8103a(r12);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r2 = r2.equals(r4);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        if (r2 != 0) goto L_0x0277;
    L_0x007f:
        r2 = 1;
    L_0x0080:
        r4 = com.tencent.wxop.stat.common.C4433k.m8131n(r20);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r3 = r3.equals(r4);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        if (r3 != 0) goto L_0x0274;
    L_0x008a:
        r2 = r2 | 2;
        r8 = r2;
    L_0x008d:
        r2 = ",";
        r11 = r7.split(r2);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r2 = 0;
        if (r11 == 0) goto L_0x01d8;
    L_0x0096:
        r3 = r11.length;	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        if (r3 <= 0) goto L_0x01d8;
    L_0x0099:
        r3 = 0;
        r4 = r11[r3];	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        if (r4 == 0) goto L_0x00a6;
    L_0x009e:
        r3 = r4.length();	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r12 = 11;
        if (r3 >= r12) goto L_0x026d;
    L_0x00a6:
        r3 = com.tencent.wxop.stat.common.C4439q.m8156a(r20);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        if (r3 == 0) goto L_0x026a;
    L_0x00ac:
        r12 = r3.length();	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r13 = 10;
        if (r12 <= r13) goto L_0x026a;
    L_0x00b4:
        r2 = 1;
    L_0x00b5:
        r4 = r7;
        r7 = r3;
    L_0x00b7:
        if (r11 == 0) goto L_0x01e0;
    L_0x00b9:
        r3 = r11.length;	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r12 = 2;
        if (r3 < r12) goto L_0x01e0;
    L_0x00bd:
        r3 = 1;
        r3 = r11[r3];	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r4.<init>();	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r4 = r4.append(r7);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r11 = ",";
        r4 = r4.append(r11);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r4 = r4.append(r3);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r4 = r4.toString();	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
    L_0x00d7:
        r11 = new com.tencent.wxop.stat.common.a;	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r11.<init>(r7, r3, r8);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r0 = r19;
        r0.f7046b = r11;	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r3 = new android.content.ContentValues;	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r3.<init>();	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r4 = com.tencent.wxop.stat.common.C4439q.m8162b(r4);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r7 = "uid";
        r3.put(r7, r4);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r4 = "user_type";
        r7 = java.lang.Integer.valueOf(r8);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r3.put(r4, r7);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r4 = "app_ver";
        r7 = com.tencent.wxop.stat.common.C4433k.m8131n(r20);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r3.put(r4, r7);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r4 = "ts";
        r7 = java.lang.Long.valueOf(r14);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r3.put(r4, r7);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        if (r2 == 0) goto L_0x0124;
    L_0x010b:
        r0 = r19;
        r2 = r0.f7047c;	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r2 = r2.getWritableDatabase();	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r4 = "user";
        r7 = "uid=?";
        r11 = 1;
        r11 = new java.lang.String[r11];	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r12 = 0;
        r11[r12] = r10;	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        if (r10 != 0) goto L_0x0206;
    L_0x0121:
        r2.update(r4, r3, r7, r11);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
    L_0x0124:
        if (r8 == r9) goto L_0x0138;
    L_0x0126:
        r0 = r19;
        r2 = r0.f7047c;	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r2 = r2.getWritableDatabase();	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r4 = "user";
        r7 = 0;
        r8 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        if (r8 != 0) goto L_0x022f;
    L_0x0135:
        r2.replace(r4, r7, r3);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
    L_0x0138:
        r2 = r6;
    L_0x0139:
        if (r2 != 0) goto L_0x01af;
    L_0x013b:
        r3 = com.tencent.wxop.stat.common.C4433k.m8112b(r20);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r4 = com.tencent.wxop.stat.common.C4433k.m8114c(r20);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        if (r4 == 0) goto L_0x0267;
    L_0x0145:
        r2 = r4.length();	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        if (r2 <= 0) goto L_0x0267;
    L_0x014b:
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r2.<init>();	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r2 = r2.append(r3);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r6 = ",";
        r2 = r2.append(r6);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r2 = r2.append(r4);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r2 = r2.toString();	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
    L_0x0162:
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r8 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r6 = r6 / r8;
        r8 = com.tencent.wxop.stat.common.C4433k.m8131n(r20);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r9 = new android.content.ContentValues;	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r9.<init>();	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r2 = com.tencent.wxop.stat.common.C4439q.m8162b(r2);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r10 = "uid";
        r9.put(r10, r2);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r2 = "user_type";
        r10 = 0;
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r9.put(r2, r10);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r2 = "app_ver";
        r9.put(r2, r8);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r2 = "ts";
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r9.put(r2, r6);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r0 = r19;
        r2 = r0.f7047c;	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r2 = r2.getWritableDatabase();	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r6 = "user";
        r7 = 0;
        r8 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        if (r8 != 0) goto L_0x0248;
    L_0x01a2:
        r2.insert(r6, r7, r9);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
    L_0x01a5:
        r2 = new com.tencent.wxop.stat.common.a;	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r6 = 0;
        r2.<init>(r3, r4, r6);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r0 = r19;
        r0.f7046b = r2;	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
    L_0x01af:
        r0 = r19;
        r2 = r0.f7047c;	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r2 = r2.getWritableDatabase();	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r2.setTransactionSuccessful();	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        if (r5 == 0) goto L_0x01bf;
    L_0x01bc:
        r5.close();	 Catch:{ Throwable -> 0x024f }
    L_0x01bf:
        r0 = r19;
        r2 = r0.f7047c;	 Catch:{ Throwable -> 0x024f }
        r2 = r2.getWritableDatabase();	 Catch:{ Throwable -> 0x024f }
        r2.endTransaction();	 Catch:{ Throwable -> 0x024f }
    L_0x01ca:
        r0 = r19;
        r2 = r0.f7046b;	 Catch:{ all -> 0x022c }
        goto L_0x000b;
    L_0x01d0:
        r2 = (android.database.sqlite.SQLiteDatabase) r2;	 Catch:{ Throwable -> 0x0264, all -> 0x025e }
        r5 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r2, r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ Throwable -> 0x0264, all -> 0x025e }
        goto L_0x003f;
    L_0x01d8:
        r4 = com.tencent.wxop.stat.common.C4433k.m8112b(r20);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r2 = 1;
        r7 = r4;
        goto L_0x00b7;
    L_0x01e0:
        r3 = com.tencent.wxop.stat.common.C4433k.m8114c(r20);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        if (r3 == 0) goto L_0x00d7;
    L_0x01e6:
        r11 = r3.length();	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        if (r11 <= 0) goto L_0x00d7;
    L_0x01ec:
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r2.<init>();	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r2 = r2.append(r7);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r4 = ",";
        r2 = r2.append(r4);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r2 = r2.append(r3);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r4 = r2.toString();	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        r2 = 1;
        goto L_0x00d7;
    L_0x0206:
        r2 = (android.database.sqlite.SQLiteDatabase) r2;	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r2, r4, r3, r7, r11);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        goto L_0x0124;
    L_0x020d:
        r2 = move-exception;
        r3 = r5;
    L_0x020f:
        r4 = f7042h;	 Catch:{ all -> 0x0261 }
        r4.mo33949e(r2);	 Catch:{ all -> 0x0261 }
        if (r3 == 0) goto L_0x0219;
    L_0x0216:
        r3.close();	 Catch:{ Throwable -> 0x0225 }
    L_0x0219:
        r0 = r19;
        r2 = r0.f7047c;	 Catch:{ Throwable -> 0x0225 }
        r2 = r2.getWritableDatabase();	 Catch:{ Throwable -> 0x0225 }
        r2.endTransaction();	 Catch:{ Throwable -> 0x0225 }
        goto L_0x01ca;
    L_0x0225:
        r2 = move-exception;
        r3 = f7042h;	 Catch:{ all -> 0x022c }
        r3.mo33949e(r2);	 Catch:{ all -> 0x022c }
        goto L_0x01ca;
    L_0x022c:
        r2 = move-exception;
        monitor-exit(r19);
        throw r2;
    L_0x022f:
        r2 = (android.database.sqlite.SQLiteDatabase) r2;	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.replace(r2, r4, r7, r3);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        goto L_0x0138;
    L_0x0236:
        r2 = move-exception;
    L_0x0237:
        if (r5 == 0) goto L_0x023c;
    L_0x0239:
        r5.close();	 Catch:{ Throwable -> 0x0257 }
    L_0x023c:
        r0 = r19;
        r3 = r0.f7047c;	 Catch:{ Throwable -> 0x0257 }
        r3 = r3.getWritableDatabase();	 Catch:{ Throwable -> 0x0257 }
        r3.endTransaction();	 Catch:{ Throwable -> 0x0257 }
    L_0x0247:
        throw r2;	 Catch:{ all -> 0x022c }
    L_0x0248:
        r2 = (android.database.sqlite.SQLiteDatabase) r2;	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.insert(r2, r6, r7, r9);	 Catch:{ Throwable -> 0x020d, all -> 0x0236 }
        goto L_0x01a5;
    L_0x024f:
        r2 = move-exception;
        r3 = f7042h;	 Catch:{ all -> 0x022c }
        r3.mo33949e(r2);	 Catch:{ all -> 0x022c }
        goto L_0x01ca;
    L_0x0257:
        r3 = move-exception;
        r4 = f7042h;	 Catch:{ all -> 0x022c }
        r4.mo33949e(r3);	 Catch:{ all -> 0x022c }
        goto L_0x0247;
    L_0x025e:
        r2 = move-exception;
        r5 = r11;
        goto L_0x0237;
    L_0x0261:
        r2 = move-exception;
        r5 = r3;
        goto L_0x0237;
    L_0x0264:
        r2 = move-exception;
        r3 = r11;
        goto L_0x020f;
    L_0x0267:
        r2 = r3;
        goto L_0x0162;
    L_0x026a:
        r3 = r4;
        goto L_0x00b5;
    L_0x026d:
        r18 = r4;
        r4 = r7;
        r7 = r18;
        goto L_0x00b7;
    L_0x0274:
        r8 = r2;
        goto L_0x008d;
    L_0x0277:
        r2 = r9;
        goto L_0x0080;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.C4411au.mo33931b(android.content.Context):com.tencent.wxop.stat.common.a");
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: c */
    public void mo33932c() {
        if (StatConfig.isEnableStatService()) {
            try {
                this.f7049e.mo33966a(new C4414ax(this));
            } catch (Throwable th) {
                f7042h.mo33949e(th);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0069  */
    /* renamed from: d */
    public void mo33933d() {
        /*
        r11 = this;
        r8 = 0;
        r0 = r11.f7047c;	 Catch:{ Throwable -> 0x0074, all -> 0x0066 }
        r0 = r0.getReadableDatabase();	 Catch:{ Throwable -> 0x0074, all -> 0x0066 }
        r1 = "config";
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r9 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Throwable -> 0x0074, all -> 0x0066 }
        if (r9 != 0) goto L_0x0059;
    L_0x0013:
        r0 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Throwable -> 0x0074, all -> 0x0066 }
    L_0x0017:
        r1 = r0.moveToNext();	 Catch:{ Throwable -> 0x004a, all -> 0x006d }
        if (r1 == 0) goto L_0x0060;
    L_0x001d:
        r1 = 0;
        r1 = r0.getInt(r1);	 Catch:{ Throwable -> 0x004a, all -> 0x006d }
        r2 = 1;
        r2 = r0.getString(r2);	 Catch:{ Throwable -> 0x004a, all -> 0x006d }
        r3 = 2;
        r3 = r0.getString(r3);	 Catch:{ Throwable -> 0x004a, all -> 0x006d }
        r4 = 3;
        r4 = r0.getInt(r4);	 Catch:{ Throwable -> 0x004a, all -> 0x006d }
        r5 = new com.tencent.wxop.stat.f;	 Catch:{ Throwable -> 0x004a, all -> 0x006d }
        r5.<init>(r1);	 Catch:{ Throwable -> 0x004a, all -> 0x006d }
        r5.f7170a = r1;	 Catch:{ Throwable -> 0x004a, all -> 0x006d }
        r1 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x004a, all -> 0x006d }
        r1 = com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation.init(r2);	 Catch:{ Throwable -> 0x004a, all -> 0x006d }
        r5.f7171b = r1;	 Catch:{ Throwable -> 0x004a, all -> 0x006d }
        r5.f7172c = r3;	 Catch:{ Throwable -> 0x004a, all -> 0x006d }
        r5.f7173d = r4;	 Catch:{ Throwable -> 0x004a, all -> 0x006d }
        r1 = f7043i;	 Catch:{ Throwable -> 0x004a, all -> 0x006d }
        com.tencent.wxop.stat.StatConfig.m7921a(r1, r5);	 Catch:{ Throwable -> 0x004a, all -> 0x006d }
        goto L_0x0017;
    L_0x004a:
        r1 = move-exception;
        r10 = r1;
        r1 = r0;
        r0 = r10;
    L_0x004e:
        r2 = f7042h;	 Catch:{ all -> 0x0071 }
        r2.mo33949e(r0);	 Catch:{ all -> 0x0071 }
        if (r1 == 0) goto L_0x0058;
    L_0x0055:
        r1.close();
    L_0x0058:
        return;
    L_0x0059:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Throwable -> 0x0074, all -> 0x0066 }
        r0 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Throwable -> 0x0074, all -> 0x0066 }
        goto L_0x0017;
    L_0x0060:
        if (r0 == 0) goto L_0x0058;
    L_0x0062:
        r0.close();
        goto L_0x0058;
    L_0x0066:
        r0 = move-exception;
    L_0x0067:
        if (r8 == 0) goto L_0x006c;
    L_0x0069:
        r8.close();
    L_0x006c:
        throw r0;
    L_0x006d:
        r1 = move-exception;
        r8 = r0;
        r0 = r1;
        goto L_0x0067;
    L_0x0071:
        r0 = move-exception;
        r8 = r1;
        goto L_0x0067;
    L_0x0074:
        r0 = move-exception;
        r1 = r8;
        goto L_0x004e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wxop.stat.C4411au.mo33933d():void");
    }
}
