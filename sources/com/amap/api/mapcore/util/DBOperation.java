package com.amap.api.mapcore.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: com.amap.api.mapcore.util.ek */
public class DBOperation {
    /* renamed from: d */
    private static Map<Class<? extends DBCreator>, DBCreator> f1880d = new HashMap();
    /* renamed from: a */
    private DB f1881a;
    /* renamed from: b */
    private SQLiteDatabase f1882b;
    /* renamed from: c */
    private DBCreator f1883c;

    /* renamed from: a */
    public static synchronized DBCreator m2614a(Class<? extends DBCreator> cls) throws IllegalAccessException, InstantiationException {
        DBCreator dBCreator;
        synchronized (DBOperation.class) {
            if (f1880d.get(cls) == null) {
                f1880d.put(cls, cls.newInstance());
            }
            dBCreator = (DBCreator) f1880d.get(cls);
        }
        return dBCreator;
    }

    public DBOperation(Context context, DBCreator dBCreator) {
        try {
            this.f1881a = new DB(context.getApplicationContext(), dBCreator.mo8973b(), null, dBCreator.mo8974c(), dBCreator);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.f1883c = dBCreator;
    }

    /* renamed from: a */
    public static String m2617a(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        Iterator it = map.keySet().iterator();
        while (true) {
            Object obj2 = obj;
            if (!it.hasNext()) {
                return stringBuilder.toString();
            }
            String str = (String) it.next();
            if (obj2 != null) {
                stringBuilder.append(str).append(" = '").append((String) map.get(str)).append("'");
                obj = null;
            } else {
                stringBuilder.append(" and ").append(str).append(" = '").append((String) map.get(str)).append("'");
                obj = obj2;
            }
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: a */
    public <T> void mo9328a(java.lang.String r6, java.lang.Class<T> r7) {
        /*
        r5 = this;
        r1 = r5.f1883c;
        monitor-enter(r1);
        r0 = r5.m2623b(r7);	 Catch:{ all -> 0x0020 }
        r2 = r5.m2616a(r0);	 Catch:{ all -> 0x0020 }
        r0 = android.text.TextUtils.isEmpty(r2);	 Catch:{ all -> 0x0020 }
        if (r0 == 0) goto L_0x0013;
    L_0x0011:
        monitor-exit(r1);	 Catch:{ all -> 0x0020 }
    L_0x0012:
        return;
    L_0x0013:
        r0 = 0;
        r0 = r5.m2622b(r0);	 Catch:{ all -> 0x0020 }
        r5.f1882b = r0;	 Catch:{ all -> 0x0020 }
        r0 = r5.f1882b;	 Catch:{ all -> 0x0020 }
        if (r0 != 0) goto L_0x0023;
    L_0x001e:
        monitor-exit(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x0012;
    L_0x0020:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0020 }
        throw r0;
    L_0x0023:
        r0 = r5.f1882b;	 Catch:{ Throwable -> 0x0041 }
        r3 = 0;
        r4 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Throwable -> 0x0041 }
        if (r4 != 0) goto L_0x003b;
    L_0x002a:
        r0.delete(r2, r6, r3);	 Catch:{ Throwable -> 0x0041 }
    L_0x002d:
        r0 = r5.f1882b;	 Catch:{ all -> 0x0020 }
        if (r0 == 0) goto L_0x0039;
    L_0x0031:
        r0 = r5.f1882b;	 Catch:{ all -> 0x0020 }
        r0.close();	 Catch:{ all -> 0x0020 }
        r0 = 0;
        r5.f1882b = r0;	 Catch:{ all -> 0x0020 }
    L_0x0039:
        monitor-exit(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x0012;
    L_0x003b:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Throwable -> 0x0041 }
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.delete(r0, r2, r6, r3);	 Catch:{ Throwable -> 0x0041 }
        goto L_0x002d;
    L_0x0041:
        r0 = move-exception;
        r2 = "DataBase";
        r3 = "deleteData";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r0, r2, r3);	 Catch:{ all -> 0x0056 }
        r0 = r5.f1882b;	 Catch:{ all -> 0x0020 }
        if (r0 == 0) goto L_0x0039;
    L_0x004d:
        r0 = r5.f1882b;	 Catch:{ all -> 0x0020 }
        r0.close();	 Catch:{ all -> 0x0020 }
        r0 = 0;
        r5.f1882b = r0;	 Catch:{ all -> 0x0020 }
        goto L_0x0039;
    L_0x0056:
        r0 = move-exception;
        r2 = r5.f1882b;	 Catch:{ all -> 0x0020 }
        if (r2 == 0) goto L_0x0063;
    L_0x005b:
        r2 = r5.f1882b;	 Catch:{ all -> 0x0020 }
        r2.close();	 Catch:{ all -> 0x0020 }
        r2 = 0;
        r5.f1882b = r2;	 Catch:{ all -> 0x0020 }
    L_0x0063:
        throw r0;	 Catch:{ all -> 0x0020 }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.DBOperation.mo9328a(java.lang.String, java.lang.Class):void");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: a */
    public <T> void mo9330a(java.lang.String r7, java.lang.Object r8, boolean r9) {
        /*
        r6 = this;
        r1 = r6.f1883c;
        monitor-enter(r1);
        if (r8 != 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
    L_0x0006:
        return;
    L_0x0007:
        r0 = r8.getClass();	 Catch:{ all -> 0x001b }
        r0 = r6.m2623b(r0);	 Catch:{ all -> 0x001b }
        r2 = r6.m2616a(r0);	 Catch:{ all -> 0x001b }
        r3 = android.text.TextUtils.isEmpty(r2);	 Catch:{ all -> 0x001b }
        if (r3 == 0) goto L_0x001e;
    L_0x0019:
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
        goto L_0x0006;
    L_0x001b:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
        throw r0;
    L_0x001e:
        r3 = r6.m2612a(r8, r0);	 Catch:{ all -> 0x001b }
        if (r3 != 0) goto L_0x0026;
    L_0x0024:
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
        goto L_0x0006;
    L_0x0026:
        r0 = r6.m2622b(r9);	 Catch:{ all -> 0x001b }
        r6.f1882b = r0;	 Catch:{ all -> 0x001b }
        r0 = r6.f1882b;	 Catch:{ all -> 0x001b }
        if (r0 != 0) goto L_0x0032;
    L_0x0030:
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
        goto L_0x0006;
    L_0x0032:
        r0 = r6.f1882b;	 Catch:{ Throwable -> 0x0050 }
        r4 = 0;
        r5 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Throwable -> 0x0050 }
        if (r5 != 0) goto L_0x004a;
    L_0x0039:
        r0.update(r2, r3, r7, r4);	 Catch:{ Throwable -> 0x0050 }
    L_0x003c:
        r0 = r6.f1882b;	 Catch:{ all -> 0x001b }
        if (r0 == 0) goto L_0x0048;
    L_0x0040:
        r0 = r6.f1882b;	 Catch:{ all -> 0x001b }
        r0.close();	 Catch:{ all -> 0x001b }
        r0 = 0;
        r6.f1882b = r0;	 Catch:{ all -> 0x001b }
    L_0x0048:
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
        goto L_0x0006;
    L_0x004a:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Throwable -> 0x0050 }
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r0, r2, r3, r7, r4);	 Catch:{ Throwable -> 0x0050 }
        goto L_0x003c;
    L_0x0050:
        r0 = move-exception;
        if (r9 != 0) goto L_0x0068;
    L_0x0053:
        r2 = "DataBase";
        r3 = "updateData";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r0, r2, r3);	 Catch:{ all -> 0x006c }
    L_0x005b:
        r0 = r6.f1882b;	 Catch:{ all -> 0x001b }
        if (r0 == 0) goto L_0x0048;
    L_0x005f:
        r0 = r6.f1882b;	 Catch:{ all -> 0x001b }
        r0.close();	 Catch:{ all -> 0x001b }
        r0 = 0;
        r6.f1882b = r0;	 Catch:{ all -> 0x001b }
        goto L_0x0048;
    L_0x0068:
        r0.printStackTrace();	 Catch:{ all -> 0x006c }
        goto L_0x005b;
    L_0x006c:
        r0 = move-exception;
        r2 = r6.f1882b;	 Catch:{ all -> 0x001b }
        if (r2 == 0) goto L_0x0079;
    L_0x0071:
        r2 = r6.f1882b;	 Catch:{ all -> 0x001b }
        r2.close();	 Catch:{ all -> 0x001b }
        r2 = 0;
        r6.f1882b = r2;	 Catch:{ all -> 0x001b }
    L_0x0079:
        throw r0;	 Catch:{ all -> 0x001b }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.DBOperation.mo9330a(java.lang.String, java.lang.Object, boolean):void");
    }

    /* renamed from: a */
    public <T> void mo9329a(String str, Object obj) {
        mo9330a(str, obj, false);
    }

    /* renamed from: a */
    public void mo9326a(Object obj, String str) {
        synchronized (this.f1883c) {
            List b = mo9332b(str, obj.getClass());
            if (b == null || b.size() == 0) {
                mo9325a(obj);
            } else {
                mo9329a(str, obj);
            }
        }
    }

    /* renamed from: a */
    public <T> void mo9325a(T t) {
        mo9327a((Object) t, false);
    }

    /* renamed from: a */
    public <T> void mo9327a(T t, boolean z) {
        synchronized (this.f1883c) {
            this.f1882b = m2622b(z);
            if (this.f1882b == null) {
                return;
            }
            try {
                m2618a(this.f1882b, (Object) t);
                if (this.f1882b != null) {
                    this.f1882b.close();
                    this.f1882b = null;
                }
            } catch (Throwable th) {
                if (this.f1882b != null) {
                    this.f1882b.close();
                    this.f1882b = null;
                }
            }
        }
    }

    /* renamed from: a */
    private <T> void m2618a(SQLiteDatabase sQLiteDatabase, T t) {
        EntityClass b = m2623b(t.getClass());
        String a = m2616a(b);
        if (!TextUtils.isEmpty(a) && t != null && sQLiteDatabase != null) {
            ContentValues a2 = m2612a((Object) t, b);
            if (a2 == null) {
                return;
            }
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.insert(sQLiteDatabase, a, null, a2);
            } else {
                sQLiteDatabase.insert(a, null, a2);
            }
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Missing block: B:28:?, code skipped:
            return;
     */
    /* renamed from: a */
    public <T> void mo9331a(java.util.List<T> r5) {
        /*
        r4 = this;
        r1 = r4.f1883c;
        monitor-enter(r1);
        if (r5 == 0) goto L_0x000b;
    L_0x0005:
        r0 = r5.size();	 Catch:{ all -> 0x001a }
        if (r0 != 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r1);	 Catch:{ all -> 0x001a }
    L_0x000c:
        return;
    L_0x000d:
        r0 = 0;
        r0 = r4.m2622b(r0);	 Catch:{ all -> 0x001a }
        r4.f1882b = r0;	 Catch:{ all -> 0x001a }
        r0 = r4.f1882b;	 Catch:{ all -> 0x001a }
        if (r0 != 0) goto L_0x001d;
    L_0x0018:
        monitor-exit(r1);	 Catch:{ all -> 0x001a }
        goto L_0x000c;
    L_0x001a:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001a }
        throw r0;
    L_0x001d:
        r0 = r4.f1882b;	 Catch:{ Throwable -> 0x0036 }
        r0.beginTransaction();	 Catch:{ Throwable -> 0x0036 }
        r0 = r5.iterator();	 Catch:{ Throwable -> 0x0036 }
    L_0x0026:
        r2 = r0.hasNext();	 Catch:{ Throwable -> 0x0036 }
        if (r2 == 0) goto L_0x004d;
    L_0x002c:
        r2 = r0.next();	 Catch:{ Throwable -> 0x0036 }
        r3 = r4.f1882b;	 Catch:{ Throwable -> 0x0036 }
        r4.m2618a(r3, r2);	 Catch:{ Throwable -> 0x0036 }
        goto L_0x0026;
    L_0x0036:
        r0 = move-exception;
        r2 = "DataBase";
        r3 = "insertListData";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r0, r2, r3);	 Catch:{ all -> 0x0060 }
        r0 = r4.f1882b;	 Catch:{ all -> 0x001a }
        r0.endTransaction();	 Catch:{ all -> 0x001a }
        r0 = r4.f1882b;	 Catch:{ all -> 0x001a }
        r0.close();	 Catch:{ all -> 0x001a }
        r0 = 0;
        r4.f1882b = r0;	 Catch:{ all -> 0x001a }
    L_0x004b:
        monitor-exit(r1);	 Catch:{ all -> 0x001a }
        goto L_0x000c;
    L_0x004d:
        r0 = r4.f1882b;	 Catch:{ Throwable -> 0x0036 }
        r0.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0036 }
        r0 = r4.f1882b;	 Catch:{ all -> 0x001a }
        r0.endTransaction();	 Catch:{ all -> 0x001a }
        r0 = r4.f1882b;	 Catch:{ all -> 0x001a }
        r0.close();	 Catch:{ all -> 0x001a }
        r0 = 0;
        r4.f1882b = r0;	 Catch:{ all -> 0x001a }
        goto L_0x004b;
    L_0x0060:
        r0 = move-exception;
        r2 = r4.f1882b;	 Catch:{ all -> 0x001a }
        r2.endTransaction();	 Catch:{ all -> 0x001a }
        r2 = r4.f1882b;	 Catch:{ all -> 0x001a }
        r2.close();	 Catch:{ all -> 0x001a }
        r2 = 0;
        r4.f1882b = r2;	 Catch:{ all -> 0x001a }
        throw r0;	 Catch:{ all -> 0x001a }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.DBOperation.mo9331a(java.util.List):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:54:0x0090 A:{SYNTHETIC, Splitter:B:54:0x0090} */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0097 A:{Catch:{ Throwable -> 0x00ae }} */
    /* JADX WARNING: Missing block: B:102:?, code skipped:
            return r8;
     */
    /* renamed from: a */
    public <T> java.util.List<T> mo9324a(java.lang.String r13, java.lang.Class<T> r14, boolean r15) {
        /*
        r12 = this;
        r9 = 0;
        r10 = r12.f1883c;
        monitor-enter(r10);
        r8 = new java.util.ArrayList;	 Catch:{ all -> 0x00a0 }
        r8.<init>();	 Catch:{ all -> 0x00a0 }
        r11 = r12.m2623b(r14);	 Catch:{ all -> 0x00a0 }
        r1 = r12.m2616a(r11);	 Catch:{ all -> 0x00a0 }
        r0 = r12.f1882b;	 Catch:{ all -> 0x00a0 }
        if (r0 != 0) goto L_0x001b;
    L_0x0015:
        r0 = r12.m2613a(r15);	 Catch:{ all -> 0x00a0 }
        r12.f1882b = r0;	 Catch:{ all -> 0x00a0 }
    L_0x001b:
        r0 = r12.f1882b;	 Catch:{ all -> 0x00a0 }
        if (r0 == 0) goto L_0x0027;
    L_0x001f:
        r0 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x00a0 }
        if (r0 != 0) goto L_0x0027;
    L_0x0025:
        if (r13 != 0) goto L_0x002a;
    L_0x0027:
        monitor-exit(r10);	 Catch:{ all -> 0x00a0 }
        r0 = r8;
    L_0x0029:
        return r0;
    L_0x002a:
        r0 = r12.f1882b;	 Catch:{ Throwable -> 0x0112, all -> 0x008c }
        r2 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r3 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Throwable -> 0x0112, all -> 0x008c }
        if (r3 != 0) goto L_0x0058;
    L_0x0035:
        r3 = r13;
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Throwable -> 0x0112, all -> 0x008c }
    L_0x003a:
        if (r1 != 0) goto L_0x0060;
    L_0x003c:
        r0 = r12.f1882b;	 Catch:{ Throwable -> 0x006e }
        r0.close();	 Catch:{ Throwable -> 0x006e }
        r0 = 0;
        r12.f1882b = r0;	 Catch:{ Throwable -> 0x006e }
        if (r1 == 0) goto L_0x0049;
    L_0x0046:
        r1.close();	 Catch:{ Throwable -> 0x00cf }
    L_0x0049:
        r0 = r12.f1882b;	 Catch:{ Throwable -> 0x00db }
        if (r0 == 0) goto L_0x0055;
    L_0x004d:
        r0 = r12.f1882b;	 Catch:{ Throwable -> 0x00db }
        r0.close();	 Catch:{ Throwable -> 0x00db }
        r0 = 0;
        r12.f1882b = r0;	 Catch:{ Throwable -> 0x00db }
    L_0x0055:
        monitor-exit(r10);	 Catch:{ all -> 0x00a0 }
        r0 = r8;
        goto L_0x0029;
    L_0x0058:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Throwable -> 0x0112, all -> 0x008c }
        r3 = r13;
        r1 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Throwable -> 0x0112, all -> 0x008c }
        goto L_0x003a;
    L_0x0060:
        r0 = r1.moveToNext();	 Catch:{ Throwable -> 0x006e }
        if (r0 == 0) goto L_0x00e7;
    L_0x0066:
        r0 = r12.m2615a(r1, r14, r11);	 Catch:{ Throwable -> 0x006e }
        r8.add(r0);	 Catch:{ Throwable -> 0x006e }
        goto L_0x0060;
    L_0x006e:
        r0 = move-exception;
    L_0x006f:
        if (r15 != 0) goto L_0x0078;
    L_0x0071:
        r2 = "DataBase";
        r3 = "searchListData";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r0, r2, r3);	 Catch:{ all -> 0x010f }
    L_0x0078:
        if (r1 == 0) goto L_0x007d;
    L_0x007a:
        r1.close();	 Catch:{ Throwable -> 0x00b9 }
    L_0x007d:
        r0 = r12.f1882b;	 Catch:{ Throwable -> 0x00c4 }
        if (r0 == 0) goto L_0x0089;
    L_0x0081:
        r0 = r12.f1882b;	 Catch:{ Throwable -> 0x00c4 }
        r0.close();	 Catch:{ Throwable -> 0x00c4 }
        r0 = 0;
        r12.f1882b = r0;	 Catch:{ Throwable -> 0x00c4 }
    L_0x0089:
        monitor-exit(r10);	 Catch:{ all -> 0x00a0 }
        r0 = r8;
        goto L_0x0029;
    L_0x008c:
        r0 = move-exception;
        r1 = r9;
    L_0x008e:
        if (r1 == 0) goto L_0x0093;
    L_0x0090:
        r1.close();	 Catch:{ Throwable -> 0x00a3 }
    L_0x0093:
        r1 = r12.f1882b;	 Catch:{ Throwable -> 0x00ae }
        if (r1 == 0) goto L_0x009f;
    L_0x0097:
        r1 = r12.f1882b;	 Catch:{ Throwable -> 0x00ae }
        r1.close();	 Catch:{ Throwable -> 0x00ae }
        r1 = 0;
        r12.f1882b = r1;	 Catch:{ Throwable -> 0x00ae }
    L_0x009f:
        throw r0;	 Catch:{ all -> 0x00a0 }
    L_0x00a0:
        r0 = move-exception;
        monitor-exit(r10);	 Catch:{ all -> 0x00a0 }
        throw r0;
    L_0x00a3:
        r1 = move-exception;
        if (r15 != 0) goto L_0x0093;
    L_0x00a6:
        r2 = "DataBase";
        r3 = "searchListData";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r1, r2, r3);	 Catch:{ all -> 0x00a0 }
        goto L_0x0093;
    L_0x00ae:
        r1 = move-exception;
        if (r15 != 0) goto L_0x009f;
    L_0x00b1:
        r2 = "DataBase";
        r3 = "searchListData";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r1, r2, r3);	 Catch:{ all -> 0x00a0 }
        goto L_0x009f;
    L_0x00b9:
        r0 = move-exception;
        if (r15 != 0) goto L_0x007d;
    L_0x00bc:
        r1 = "DataBase";
        r2 = "searchListData";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r0, r1, r2);	 Catch:{ all -> 0x00a0 }
        goto L_0x007d;
    L_0x00c4:
        r0 = move-exception;
        if (r15 != 0) goto L_0x0089;
    L_0x00c7:
        r1 = "DataBase";
        r2 = "searchListData";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r0, r1, r2);	 Catch:{ all -> 0x00a0 }
        goto L_0x0089;
    L_0x00cf:
        r0 = move-exception;
        if (r15 != 0) goto L_0x0049;
    L_0x00d2:
        r1 = "DataBase";
        r2 = "searchListData";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r0, r1, r2);	 Catch:{ all -> 0x00a0 }
        goto L_0x0049;
    L_0x00db:
        r0 = move-exception;
        if (r15 != 0) goto L_0x0055;
    L_0x00de:
        r1 = "DataBase";
        r2 = "searchListData";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r0, r1, r2);	 Catch:{ all -> 0x00a0 }
        goto L_0x0055;
    L_0x00e7:
        if (r1 == 0) goto L_0x00ec;
    L_0x00e9:
        r1.close();	 Catch:{ Throwable -> 0x0104 }
    L_0x00ec:
        r0 = r12.f1882b;	 Catch:{ Throwable -> 0x00f9 }
        if (r0 == 0) goto L_0x0089;
    L_0x00f0:
        r0 = r12.f1882b;	 Catch:{ Throwable -> 0x00f9 }
        r0.close();	 Catch:{ Throwable -> 0x00f9 }
        r0 = 0;
        r12.f1882b = r0;	 Catch:{ Throwable -> 0x00f9 }
        goto L_0x0089;
    L_0x00f9:
        r0 = move-exception;
        if (r15 != 0) goto L_0x0089;
    L_0x00fc:
        r1 = "DataBase";
        r2 = "searchListData";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r0, r1, r2);	 Catch:{ all -> 0x00a0 }
        goto L_0x0089;
    L_0x0104:
        r0 = move-exception;
        if (r15 != 0) goto L_0x00ec;
    L_0x0107:
        r1 = "DataBase";
        r2 = "searchListData";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r0, r1, r2);	 Catch:{ all -> 0x00a0 }
        goto L_0x00ec;
    L_0x010f:
        r0 = move-exception;
        goto L_0x008e;
    L_0x0112:
        r0 = move-exception;
        r1 = r9;
        goto L_0x006f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.DBOperation.mo9324a(java.lang.String, java.lang.Class, boolean):java.util.List");
    }

    /* renamed from: b */
    public <T> List<T> mo9332b(String str, Class<T> cls) {
        return mo9324a(str, (Class) cls, false);
    }

    /* renamed from: a */
    private <T> T m2615a(Cursor cursor, Class<T> cls, EntityClass entityClass) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Field[] a = m2621a((Class) cls, entityClass.mo9334b());
        Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
        declaredConstructor.setAccessible(true);
        Object newInstance = declaredConstructor.newInstance(new Object[0]);
        for (Field field : a) {
            field.setAccessible(true);
            Annotation annotation = field.getAnnotation(EntityField.class);
            if (annotation != null) {
                EntityField entityField = (EntityField) annotation;
                int b = entityField.mo9336b();
                int columnIndex = cursor.getColumnIndex(entityField.mo9335a());
                switch (b) {
                    case 1:
                        field.set(newInstance, Short.valueOf(cursor.getShort(columnIndex)));
                        break;
                    case 2:
                        field.set(newInstance, Integer.valueOf(cursor.getInt(columnIndex)));
                        break;
                    case 3:
                        field.set(newInstance, Float.valueOf(cursor.getFloat(columnIndex)));
                        break;
                    case 4:
                        field.set(newInstance, Double.valueOf(cursor.getDouble(columnIndex)));
                        break;
                    case 5:
                        field.set(newInstance, Long.valueOf(cursor.getLong(columnIndex)));
                        break;
                    case 6:
                        field.set(newInstance, cursor.getString(columnIndex));
                        break;
                    case 7:
                        field.set(newInstance, cursor.getBlob(columnIndex));
                        break;
                    default:
                        break;
                }
            }
        }
        return newInstance;
    }

    /* renamed from: a */
    private void m2619a(Object obj, Field field, ContentValues contentValues) {
        Annotation annotation = field.getAnnotation(EntityField.class);
        if (annotation != null) {
            EntityField entityField = (EntityField) annotation;
            switch (entityField.mo9336b()) {
                case 1:
                    try {
                        contentValues.put(entityField.mo9335a(), Short.valueOf(field.getShort(obj)));
                        return;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                case 2:
                    contentValues.put(entityField.mo9335a(), Integer.valueOf(field.getInt(obj)));
                    return;
                case 3:
                    contentValues.put(entityField.mo9335a(), Float.valueOf(field.getFloat(obj)));
                    return;
                case 4:
                    contentValues.put(entityField.mo9335a(), Double.valueOf(field.getDouble(obj)));
                    return;
                case 5:
                    contentValues.put(entityField.mo9335a(), Long.valueOf(field.getLong(obj)));
                    return;
                case 6:
                    String str = "";
                    contentValues.put(entityField.mo9335a(), (String) field.get(obj));
                    return;
                case 7:
                    contentValues.put(entityField.mo9335a(), (byte[]) field.get(obj));
                    return;
                default:
                    return;
            }
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private ContentValues m2612a(Object obj, EntityClass entityClass) {
        ContentValues contentValues = new ContentValues();
        for (Field field : m2621a(obj.getClass(), entityClass.mo9334b())) {
            field.setAccessible(true);
            m2619a(obj, field, contentValues);
        }
        return contentValues;
    }

    /* renamed from: a */
    private Field[] m2621a(Class<?> cls, boolean z) {
        if (cls == null) {
            return null;
        }
        if (z) {
            return cls.getSuperclass().getDeclaredFields();
        }
        return cls.getDeclaredFields();
    }

    /* renamed from: a */
    private SQLiteDatabase m2613a(boolean z) {
        try {
            if (this.f1882b == null) {
                this.f1882b = this.f1881a.getReadableDatabase();
            }
        } catch (Throwable th) {
            if (z) {
                th.printStackTrace();
            } else {
                BasicLogHandler.m2542a(th, "DBOperation", "getReadAbleDataBase");
            }
        }
        return this.f1882b;
    }

    /* renamed from: b */
    private SQLiteDatabase m2622b(boolean z) {
        try {
            if (this.f1882b == null || this.f1882b.isReadOnly()) {
                if (this.f1882b != null) {
                    this.f1882b.close();
                }
                this.f1882b = this.f1881a.getWritableDatabase();
            }
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "DBOperation", "getWriteDatabase");
        }
        return this.f1882b;
    }

    /* renamed from: a */
    private boolean m2620a(Annotation annotation) {
        return annotation != null;
    }

    /* renamed from: a */
    private <T> String m2616a(EntityClass entityClass) {
        if (entityClass == null) {
            return null;
        }
        return entityClass.mo9333a();
    }

    /* renamed from: b */
    private <T> EntityClass m2623b(Class<T> cls) {
        Annotation annotation = cls.getAnnotation(EntityClass.class);
        if (m2620a(annotation)) {
            return (EntityClass) annotation;
        }
        return null;
    }
}
