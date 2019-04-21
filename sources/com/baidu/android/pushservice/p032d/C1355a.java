package com.baidu.android.pushservice.p032d;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.baidu.android.pushservice.p036h.C1425a;
import com.facebook.internal.NativeProtocol;
import com.mcdonalds.sdk.modules.notification.PushConstants;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.newrelic.agent.android.agentdata.HexAttributes;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import com.newrelic.agent.android.tracing.ActivityTrace;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.d.a */
public class C1355a {
    /* renamed from: a */
    private Context f4780a;
    /* renamed from: b */
    private final AtomicInteger f4781b = new AtomicInteger();
    /* renamed from: c */
    private SQLiteDatabase f4782c;
    /* renamed from: d */
    private final ReentrantLock f4783d = new ReentrantLock();

    /* renamed from: com.baidu.android.pushservice.d.a$a */
    private static class C1354a {
        /* renamed from: a */
        static final C1355a f4779a = new C1355a();
    }

    /* renamed from: a */
    public static C1355a m6114a() {
        return C1354a.f4779a;
    }

    /* renamed from: a */
    private String m6115a(Cursor cursor, int i, int i2) {
        int i3 = 0;
        if (cursor == null) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray();
            int i4 = 0;
            while (cursor.moveToNext()) {
                if (-1 != i) {
                    i3++;
                    if (i3 < i) {
                        continue;
                    }
                }
                JSONObject a = m6117a(cursor);
                if (-1 == i2) {
                    jSONArray.put(a);
                } else {
                    i4++;
                    if (i4 <= i2) {
                        jSONArray.put(a);
                    }
                }
            }
            String jSONArray2 = !(jSONArray instanceof JSONArray) ? jSONArray.toString() : JSONArrayInstrumentation.toString(jSONArray);
            cursor.close();
            return jSONArray2;
        } catch (JSONException e) {
            C1425a.m6444e("DatabaseManager", "error " + e.getMessage());
            cursor.close();
            return null;
        } catch (Throwable th) {
            cursor.close();
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0088  */
    /* renamed from: a */
    private java.util.ArrayList<java.lang.String> m6116a(android.database.sqlite.SQLiteDatabase r13, java.lang.String r14) {
        /*
        r12 = this;
        r9 = 0;
        r10 = 0;
        r11 = new java.util.ArrayList;
        r11.<init>();
        if (r13 == 0) goto L_0x008e;
    L_0x0009:
        r2 = "subscribe";
        r1 = 1;
        r3 = new java.lang.String[r1];	 Catch:{ Exception -> 0x0068 }
        r1 = 0;
        r4 = "host_channel";
        r3[r1] = r4;	 Catch:{ Exception -> 0x0068 }
        r4 = "appid=?";
        r1 = 1;
        r5 = new java.lang.String[r1];	 Catch:{ Exception -> 0x0068 }
        r1 = 0;
        r5[r1] = r14;	 Catch:{ Exception -> 0x0068 }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r1 = r13 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0068 }
        if (r1 != 0) goto L_0x005f;
    L_0x0022:
        r1 = r13;
        r1 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0068 }
    L_0x0027:
        r2 = r1;
    L_0x0028:
        if (r2 == 0) goto L_0x0086;
    L_0x002a:
        r1 = r2.moveToNext();	 Catch:{ Exception -> 0x008c }
        if (r1 == 0) goto L_0x0086;
    L_0x0030:
        r1 = 0;
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x008c }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Exception -> 0x008c }
        if (r1 != 0) goto L_0x0086;
    L_0x003b:
        r1 = 0;
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x008c }
        if (r1 == 0) goto L_0x0086;
    L_0x0042:
        r1 = r1.length();	 Catch:{ Exception -> 0x008c }
        if (r1 <= 0) goto L_0x0086;
    L_0x0048:
        r1 = 0;
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x008c }
        r3 = ":";
        r3 = r1.split(r3);	 Catch:{ Exception -> 0x008c }
        r4 = r3.length;	 Catch:{ Exception -> 0x008c }
        r1 = r10;
    L_0x0055:
        if (r1 >= r4) goto L_0x0086;
    L_0x0057:
        r5 = r3[r1];	 Catch:{ Exception -> 0x008c }
        r11.add(r5);	 Catch:{ Exception -> 0x008c }
        r1 = r1 + 1;
        goto L_0x0055;
    L_0x005f:
        r0 = r13;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0068 }
        r1 = r0;
        r1 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0068 }
        goto L_0x0027;
    L_0x0068:
        r1 = move-exception;
        r2 = r9;
    L_0x006a:
        r3 = "DatabaseManager";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "error ";
        r4 = r4.append(r5);
        r1 = r1.getMessage();
        r1 = r4.append(r1);
        r1 = r1.toString();
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r3, r1);
    L_0x0086:
        if (r2 == 0) goto L_0x008b;
    L_0x0088:
        r2.close();
    L_0x008b:
        return r11;
    L_0x008c:
        r1 = move-exception;
        goto L_0x006a;
    L_0x008e:
        r2 = r9;
        goto L_0x0028;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p032d.C1355a.m6116a(android.database.sqlite.SQLiteDatabase, java.lang.String):java.util.ArrayList");
    }

    /* renamed from: a */
    private JSONObject m6117a(Cursor cursor) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        String string = cursor.getString(cursor.getColumnIndex("msgid"));
        String string2 = cursor.getString(cursor.getColumnIndex("appid"));
        String string3 = cursor.getString(cursor.getColumnIndex(PushConstants.TITLE_KEY));
        String string4 = cursor.getString(cursor.getColumnIndex("content"));
        String string5 = cursor.getString(cursor.getColumnIndex("link"));
        int i = cursor.getInt(cursor.getColumnIndex("status"));
        int i2 = cursor.getInt(cursor.getColumnIndex("type"));
        long j = cursor.getLong(cursor.getColumnIndex("time"));
        jSONObject.put("id", string);
        jSONObject.put("appid", string2);
        jSONObject.put(PushConstants.TITLE_KEY, string3);
        jSONObject.put("content", string4);
        jSONObject.put("link", string5);
        jSONObject.put("status", i);
        jSONObject.put("type", i2);
        jSONObject.put("time", j);
        return jSONObject;
    }

    /* JADX WARNING: Removed duplicated region for block: B:61:0x014c A:{Catch:{ Exception -> 0x0120, all -> 0x0149 }} */
    /* renamed from: a */
    public synchronized int mo13665a(int r12, java.lang.String r13) {
        /*
        r11 = this;
        r8 = -1;
        r9 = 0;
        monitor-enter(r11);
        r0 = r11.mo13682b();	 Catch:{ all -> 0x004e }
        r1 = 0;
        if (r0 == 0) goto L_0x0010;
    L_0x000a:
        r2 = android.text.TextUtils.isEmpty(r13);	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        if (r2 == 0) goto L_0x001b;
    L_0x0010:
        if (r9 == 0) goto L_0x0015;
    L_0x0012:
        r1.close();	 Catch:{ all -> 0x004e }
    L_0x0015:
        r11.mo13690c();	 Catch:{ all -> 0x004e }
        r0 = r8;
    L_0x0019:
        monitor-exit(r11);
        return r0;
    L_0x001b:
        r1 = "0";
        r1 = r13.equals(r1);	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        if (r1 == 0) goto L_0x0092;
    L_0x0023:
        if (r12 != 0) goto L_0x0058;
    L_0x0025:
        r1 = "message";
        r2 = 0;
        r3 = "status=?";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r5 = 0;
        r6 = 1;
        r6 = java.lang.String.valueOf(r6);	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r4[r5] = r6;	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r10 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        if (r10 != 0) goto L_0x0051;
    L_0x003c:
        r0 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
    L_0x0040:
        r1 = r0;
    L_0x0041:
        r0 = r1.getCount();	 Catch:{ Exception -> 0x0156 }
        if (r1 == 0) goto L_0x004a;
    L_0x0047:
        r1.close();	 Catch:{ all -> 0x004e }
    L_0x004a:
        r11.mo13690c();	 Catch:{ all -> 0x004e }
        goto L_0x0019;
    L_0x004e:
        r0 = move-exception;
        monitor-exit(r11);
        throw r0;
    L_0x0051:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r0 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        goto L_0x0040;
    L_0x0058:
        r1 = "message";
        r2 = 0;
        r3 = "status=? AND type=?";
        r4 = 2;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r5 = 0;
        r6 = 1;
        r6 = java.lang.String.valueOf(r6);	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r4[r5] = r6;	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r5 = 1;
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r6.<init>();	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r7 = "";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r6 = r6.append(r12);	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r6 = r6.toString();	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r4[r5] = r6;	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r10 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        if (r10 != 0) goto L_0x008b;
    L_0x0085:
        r0 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
    L_0x0089:
        r1 = r0;
        goto L_0x0041;
    L_0x008b:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r0 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        goto L_0x0089;
    L_0x0092:
        if (r12 != 0) goto L_0x00cf;
    L_0x0094:
        r1 = "message";
        r2 = 0;
        r3 = "status=? AND appid=? ";
        r4 = 2;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r5 = 0;
        r6 = 1;
        r6 = java.lang.String.valueOf(r6);	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r4[r5] = r6;	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r5 = 1;
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r6.<init>();	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r7 = "";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r6 = r6.append(r13);	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r6 = r6.toString();	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r4[r5] = r6;	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r10 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        if (r10 != 0) goto L_0x00c8;
    L_0x00c1:
        r0 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
    L_0x00c5:
        r1 = r0;
        goto L_0x0041;
    L_0x00c8:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r0 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        goto L_0x00c5;
    L_0x00cf:
        r1 = "message";
        r2 = 0;
        r3 = "status=? AND appid=? AND type=?";
        r4 = 3;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r5 = 0;
        r6 = 1;
        r6 = java.lang.String.valueOf(r6);	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r4[r5] = r6;	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r5 = 1;
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r6.<init>();	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r7 = "";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r6 = r6.append(r13);	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r6 = r6.toString();	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r4[r5] = r6;	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r5 = 2;
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r6.<init>();	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r7 = "";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r6 = r6.append(r12);	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r6 = r6.toString();	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r4[r5] = r6;	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r10 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        if (r10 != 0) goto L_0x0119;
    L_0x0112:
        r0 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
    L_0x0116:
        r1 = r0;
        goto L_0x0041;
    L_0x0119:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        r0 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0120, all -> 0x0149 }
        goto L_0x0116;
    L_0x0120:
        r0 = move-exception;
        r1 = r9;
    L_0x0122:
        r2 = "DatabaseManager";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0153 }
        r3.<init>();	 Catch:{ all -> 0x0153 }
        r4 = "error ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0153 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0153 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0153 }
        r0 = r0.toString();	 Catch:{ all -> 0x0153 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r2, r0);	 Catch:{ all -> 0x0153 }
        if (r1 == 0) goto L_0x0143;
    L_0x0140:
        r1.close();	 Catch:{ all -> 0x004e }
    L_0x0143:
        r11.mo13690c();	 Catch:{ all -> 0x004e }
        r0 = r8;
        goto L_0x0019;
    L_0x0149:
        r0 = move-exception;
    L_0x014a:
        if (r9 == 0) goto L_0x014f;
    L_0x014c:
        r9.close();	 Catch:{ all -> 0x004e }
    L_0x014f:
        r11.mo13690c();	 Catch:{ all -> 0x004e }
        throw r0;	 Catch:{ all -> 0x004e }
    L_0x0153:
        r0 = move-exception;
        r9 = r1;
        goto L_0x014a;
    L_0x0156:
        r0 = move-exception;
        goto L_0x0122;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p032d.C1355a.mo13665a(int, java.lang.String):int");
    }

    /* renamed from: a */
    public synchronized int mo13666a(String str, String str2) {
        int i;
        SQLiteDatabase b = mo13682b();
        if (b == null) {
            mo13690c();
            i = -1;
        } else {
            try {
                String str3;
                String str4;
                String[] strArr;
                if (!TextUtils.isEmpty(str)) {
                    str3 = "subscribe";
                    str4 = "apikey=?";
                    strArr = new String[]{str};
                    i = !(b instanceof SQLiteDatabase) ? b.delete(str3, str4, strArr) : SQLiteInstrumentation.delete(b, str3, str4, strArr);
                } else if (TextUtils.isEmpty(str2)) {
                    mo13690c();
                    i = -1;
                } else {
                    str3 = "subscribe";
                    str4 = "appid=?";
                    strArr = new String[]{str2};
                    i = !(b instanceof SQLiteDatabase) ? b.delete(str3, str4, strArr) : SQLiteInstrumentation.delete(b, str3, str4, strArr);
                }
            } catch (Exception e) {
                i = "unSubscribe " + e.getMessage();
                C1425a.m6444e("DatabaseManager", i);
                i = -1;
                return i;
            } finally {
                mo13690c();
            }
        }
        return i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:54:0x00ed A:{Catch:{ Exception -> 0x00ba, all -> 0x00e5 }} */
    /* renamed from: a */
    public synchronized long mo13667a(java.lang.String r21, java.lang.String r22, java.lang.String r23, java.lang.String r24) {
        /*
        r20 = this;
        monitor-enter(r20);
        r2 = r20.mo13682b();	 Catch:{ all -> 0x0085 }
        r18 = 0;
        if (r2 == 0) goto L_0x000f;
    L_0x0009:
        r3 = android.text.TextUtils.isEmpty(r21);	 Catch:{ Exception -> 0x00ba, all -> 0x00e5 }
        if (r3 == 0) goto L_0x001b;
    L_0x000f:
        r2 = -1;
        r20.mo13690c();	 Catch:{ all -> 0x0085 }
        if (r18 == 0) goto L_0x0019;
    L_0x0016:
        r18.close();	 Catch:{ all -> 0x0085 }
    L_0x0019:
        monitor-exit(r20);
        return r2;
    L_0x001b:
        r19 = new android.content.ContentValues;	 Catch:{ Exception -> 0x00ba, all -> 0x00e5 }
        r19.<init>();	 Catch:{ Exception -> 0x00ba, all -> 0x00e5 }
        r3 = "appid";
        r0 = r19;
        r1 = r21;
        r0.put(r3, r1);	 Catch:{ Exception -> 0x00ba, all -> 0x00e5 }
        r3 = "apikey";
        r0 = r19;
        r1 = r22;
        r0.put(r3, r1);	 Catch:{ Exception -> 0x00ba, all -> 0x00e5 }
        r3 = "app_name";
        r0 = r19;
        r1 = r23;
        r0.put(r3, r1);	 Catch:{ Exception -> 0x00ba, all -> 0x00e5 }
        r3 = "app_logo";
        r0 = r19;
        r1 = r24;
        r0.put(r3, r1);	 Catch:{ Exception -> 0x00ba, all -> 0x00e5 }
        r3 = "app_info";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x00ba, all -> 0x00e5 }
        r5 = 0;
        r6 = "appid";
        r4[r5] = r6;	 Catch:{ Exception -> 0x00ba, all -> 0x00e5 }
        r5 = "appid=?";
        r6 = 1;
        r6 = new java.lang.String[r6];	 Catch:{ Exception -> 0x00ba, all -> 0x00e5 }
        r7 = 0;
        r6[r7] = r21;	 Catch:{ Exception -> 0x00ba, all -> 0x00e5 }
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00ba, all -> 0x00e5 }
        if (r10 != 0) goto L_0x0088;
    L_0x005d:
        r4 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00ba, all -> 0x00e5 }
    L_0x0061:
        r3 = r4.moveToNext();	 Catch:{ Exception -> 0x00f3 }
        if (r3 == 0) goto L_0x00a3;
    L_0x0067:
        r3 = "app_info";
        r5 = "appid=?";
        r6 = 1;
        r6 = new java.lang.String[r6];	 Catch:{ Exception -> 0x00f3 }
        r7 = 0;
        r6[r7] = r21;	 Catch:{ Exception -> 0x00f3 }
        r7 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00f3 }
        if (r7 != 0) goto L_0x009a;
    L_0x0075:
        r0 = r19;
        r2 = r2.update(r3, r0, r5, r6);	 Catch:{ Exception -> 0x00f3 }
    L_0x007b:
        r2 = (long) r2;
    L_0x007c:
        r20.mo13690c();	 Catch:{ all -> 0x0085 }
        if (r4 == 0) goto L_0x0019;
    L_0x0081:
        r4.close();	 Catch:{ all -> 0x0085 }
        goto L_0x0019;
    L_0x0085:
        r2 = move-exception;
        monitor-exit(r20);
        throw r2;
    L_0x0088:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00ba, all -> 0x00e5 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x00ba, all -> 0x00e5 }
        goto L_0x0061;
    L_0x009a:
        r2 = (android.database.sqlite.SQLiteDatabase) r2;	 Catch:{ Exception -> 0x00f3 }
        r0 = r19;
        r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r2, r3, r0, r5, r6);	 Catch:{ Exception -> 0x00f3 }
        goto L_0x007b;
    L_0x00a3:
        r3 = "app_info";
        r5 = 0;
        r6 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00f3 }
        if (r6 != 0) goto L_0x00b1;
    L_0x00aa:
        r0 = r19;
        r2 = r2.insert(r3, r5, r0);	 Catch:{ Exception -> 0x00f3 }
        goto L_0x007c;
    L_0x00b1:
        r2 = (android.database.sqlite.SQLiteDatabase) r2;	 Catch:{ Exception -> 0x00f3 }
        r0 = r19;
        r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.insert(r2, r3, r5, r0);	 Catch:{ Exception -> 0x00f3 }
        goto L_0x007c;
    L_0x00ba:
        r2 = move-exception;
        r4 = r18;
    L_0x00bd:
        r3 = "DatabaseManager";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00f1 }
        r5.<init>();	 Catch:{ all -> 0x00f1 }
        r6 = "addAppInfo ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x00f1 }
        r2 = r2.getMessage();	 Catch:{ all -> 0x00f1 }
        r2 = r5.append(r2);	 Catch:{ all -> 0x00f1 }
        r2 = r2.toString();	 Catch:{ all -> 0x00f1 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r3, r2);	 Catch:{ all -> 0x00f1 }
        r2 = -1;
        r20.mo13690c();	 Catch:{ all -> 0x0085 }
        if (r4 == 0) goto L_0x0019;
    L_0x00e0:
        r4.close();	 Catch:{ all -> 0x0085 }
        goto L_0x0019;
    L_0x00e5:
        r2 = move-exception;
        r4 = r18;
    L_0x00e8:
        r20.mo13690c();	 Catch:{ all -> 0x0085 }
        if (r4 == 0) goto L_0x00f0;
    L_0x00ed:
        r4.close();	 Catch:{ all -> 0x0085 }
    L_0x00f0:
        throw r2;	 Catch:{ all -> 0x0085 }
    L_0x00f1:
        r2 = move-exception;
        goto L_0x00e8;
    L_0x00f3:
        r2 = move-exception;
        goto L_0x00bd;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p032d.C1355a.mo13667a(java.lang.String, java.lang.String, java.lang.String, java.lang.String):long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:68:0x015b A:{Catch:{ Exception -> 0x0128, all -> 0x0153 }} */
    /* renamed from: a */
    public synchronized long mo13668a(java.lang.String r22, java.lang.String r23, java.lang.String r24, java.lang.String r25, java.lang.String r26) {
        /*
        r21 = this;
        monitor-enter(r21);
        r2 = r21.mo13682b();	 Catch:{ all -> 0x00fc }
        r18 = 0;
        if (r2 == 0) goto L_0x000f;
    L_0x0009:
        r3 = android.text.TextUtils.isEmpty(r22);	 Catch:{ Exception -> 0x0128, all -> 0x0153 }
        if (r3 == 0) goto L_0x001b;
    L_0x000f:
        r2 = -1;
        r21.mo13690c();	 Catch:{ all -> 0x00fc }
        if (r18 == 0) goto L_0x0019;
    L_0x0016:
        r18.close();	 Catch:{ all -> 0x00fc }
    L_0x0019:
        monitor-exit(r21);
        return r2;
    L_0x001b:
        r20 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0128, all -> 0x0153 }
        r20.<init>();	 Catch:{ Exception -> 0x0128, all -> 0x0153 }
        r3 = "appid";
        r0 = r20;
        r1 = r22;
        r0.put(r3, r1);	 Catch:{ Exception -> 0x0128, all -> 0x0153 }
        r3 = "apikey";
        r0 = r20;
        r1 = r23;
        r0.put(r3, r1);	 Catch:{ Exception -> 0x0128, all -> 0x0153 }
        r3 = "app_name";
        r0 = r20;
        r1 = r24;
        r0.put(r3, r1);	 Catch:{ Exception -> 0x0128, all -> 0x0153 }
        r3 = "app_logo";
        r0 = r20;
        r1 = r25;
        r0.put(r3, r1);	 Catch:{ Exception -> 0x0128, all -> 0x0153 }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0128, all -> 0x0153 }
        r3 = "sub_time";
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ Exception -> 0x0128, all -> 0x0153 }
        r0 = r20;
        r0.put(r3, r4);	 Catch:{ Exception -> 0x0128, all -> 0x0153 }
        r3 = "is_bind";
        r4 = 1;
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Exception -> 0x0128, all -> 0x0153 }
        r0 = r20;
        r0.put(r3, r4);	 Catch:{ Exception -> 0x0128, all -> 0x0153 }
        if (r26 != 0) goto L_0x0163;
    L_0x0061:
        r19 = "other";
    L_0x0063:
        r3 = "subscribe";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0128, all -> 0x0153 }
        r5 = 0;
        r6 = "host_channel";
        r4[r5] = r6;	 Catch:{ Exception -> 0x0128, all -> 0x0153 }
        r5 = "appid=?";
        r6 = 1;
        r6 = new java.lang.String[r6];	 Catch:{ Exception -> 0x0128, all -> 0x0153 }
        r7 = 0;
        r6[r7] = r22;	 Catch:{ Exception -> 0x0128, all -> 0x0153 }
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0128, all -> 0x0153 }
        if (r10 != 0) goto L_0x00c4;
    L_0x007c:
        r4 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x0128, all -> 0x0153 }
    L_0x0080:
        r3 = r4.moveToNext();	 Catch:{ Exception -> 0x0161 }
        if (r3 == 0) goto L_0x0108;
    L_0x0086:
        r3 = 0;
        r3 = r4.getString(r3);	 Catch:{ Exception -> 0x0161 }
        if (r3 == 0) goto L_0x00d6;
    L_0x008d:
        r5 = r3.length();	 Catch:{ Exception -> 0x0161 }
        if (r5 <= 0) goto L_0x00d6;
    L_0x0093:
        r5 = ":";
        r6 = r3.split(r5);	 Catch:{ Exception -> 0x0161 }
        r7 = r6.length;	 Catch:{ Exception -> 0x0161 }
        r3 = 0;
        r5 = r3;
        r3 = r19;
    L_0x009e:
        if (r5 >= r7) goto L_0x00d6;
    L_0x00a0:
        r8 = r6[r5];	 Catch:{ Exception -> 0x0161 }
        r0 = r19;
        r9 = r8.equals(r0);	 Catch:{ Exception -> 0x0161 }
        if (r9 != 0) goto L_0x00c1;
    L_0x00aa:
        r9 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0161 }
        r9.<init>();	 Catch:{ Exception -> 0x0161 }
        r3 = r9.append(r3);	 Catch:{ Exception -> 0x0161 }
        r9 = ":";
        r3 = r3.append(r9);	 Catch:{ Exception -> 0x0161 }
        r3 = r3.append(r8);	 Catch:{ Exception -> 0x0161 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0161 }
    L_0x00c1:
        r5 = r5 + 1;
        goto L_0x009e;
    L_0x00c4:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0128, all -> 0x0153 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x0128, all -> 0x0153 }
        goto L_0x0080;
    L_0x00d6:
        r5 = "host_channel";
        r0 = r20;
        r0.put(r5, r3);	 Catch:{ Exception -> 0x0161 }
        r3 = "subscribe";
        r5 = "appid=?";
        r6 = 1;
        r6 = new java.lang.String[r6];	 Catch:{ Exception -> 0x0161 }
        r7 = 0;
        r6[r7] = r22;	 Catch:{ Exception -> 0x0161 }
        r7 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0161 }
        if (r7 != 0) goto L_0x00ff;
    L_0x00eb:
        r0 = r20;
        r2 = r2.update(r3, r0, r5, r6);	 Catch:{ Exception -> 0x0161 }
    L_0x00f1:
        r2 = (long) r2;
    L_0x00f2:
        r21.mo13690c();	 Catch:{ all -> 0x00fc }
        if (r4 == 0) goto L_0x0019;
    L_0x00f7:
        r4.close();	 Catch:{ all -> 0x00fc }
        goto L_0x0019;
    L_0x00fc:
        r2 = move-exception;
        monitor-exit(r21);
        throw r2;
    L_0x00ff:
        r2 = (android.database.sqlite.SQLiteDatabase) r2;	 Catch:{ Exception -> 0x0161 }
        r0 = r20;
        r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r2, r3, r0, r5, r6);	 Catch:{ Exception -> 0x0161 }
        goto L_0x00f1;
    L_0x0108:
        r3 = "host_channel";
        r0 = r20;
        r1 = r19;
        r0.put(r3, r1);	 Catch:{ Exception -> 0x0161 }
        r3 = "subscribe";
        r5 = 0;
        r6 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0161 }
        if (r6 != 0) goto L_0x011f;
    L_0x0118:
        r0 = r20;
        r2 = r2.insert(r3, r5, r0);	 Catch:{ Exception -> 0x0161 }
        goto L_0x00f2;
    L_0x011f:
        r2 = (android.database.sqlite.SQLiteDatabase) r2;	 Catch:{ Exception -> 0x0161 }
        r0 = r20;
        r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.insert(r2, r3, r5, r0);	 Catch:{ Exception -> 0x0161 }
        goto L_0x00f2;
    L_0x0128:
        r2 = move-exception;
        r4 = r18;
    L_0x012b:
        r3 = "DatabaseManager";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x015f }
        r5.<init>();	 Catch:{ all -> 0x015f }
        r6 = "subscribe ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x015f }
        r2 = r2.getMessage();	 Catch:{ all -> 0x015f }
        r2 = r5.append(r2);	 Catch:{ all -> 0x015f }
        r2 = r2.toString();	 Catch:{ all -> 0x015f }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r3, r2);	 Catch:{ all -> 0x015f }
        r2 = -1;
        r21.mo13690c();	 Catch:{ all -> 0x00fc }
        if (r4 == 0) goto L_0x0019;
    L_0x014e:
        r4.close();	 Catch:{ all -> 0x00fc }
        goto L_0x0019;
    L_0x0153:
        r2 = move-exception;
        r4 = r18;
    L_0x0156:
        r21.mo13690c();	 Catch:{ all -> 0x00fc }
        if (r4 == 0) goto L_0x015e;
    L_0x015b:
        r4.close();	 Catch:{ all -> 0x00fc }
    L_0x015e:
        throw r2;	 Catch:{ all -> 0x00fc }
    L_0x015f:
        r2 = move-exception;
        goto L_0x0156;
    L_0x0161:
        r2 = move-exception;
        goto L_0x012b;
    L_0x0163:
        r19 = r26;
        goto L_0x0063;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p032d.C1355a.mo13668a(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):long");
    }

    /* renamed from: a */
    public synchronized long mo13669a(String str, String str2, String str3, String str4, String str5, int i, int i2) {
        long j;
        SQLiteDatabase b = mo13682b();
        if (b == null) {
            mo13690c();
            j = -1;
        } else {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("msgid", str);
                contentValues.put("appid", str2);
                contentValues.put(PushConstants.TITLE_KEY, str3);
                contentValues.put("content", str4);
                contentValues.put("link", str5);
                contentValues.put("status", Integer.valueOf(i2));
                contentValues.put("type", Integer.valueOf(i));
                contentValues.put("time", Long.valueOf(System.currentTimeMillis()));
                String str6 = HexAttributes.HEX_ATTR_MESSAGE;
                j = !(b instanceof SQLiteDatabase) ? b.insert(str6, null, contentValues) : SQLiteInstrumentation.insert(b, str6, null, contentValues);
            } catch (Exception e) {
                j = "error " + e.getMessage();
                C1425a.m6444e("DatabaseManager", j);
                j = -1;
                return j;
            } finally {
                mo13690c();
            }
        }
        return j;
    }

    /* renamed from: a */
    public synchronized String mo13670a(ArrayList<String> arrayList) {
        String str;
        SQLiteDatabase b = mo13682b();
        if (b == null || arrayList == null) {
            str = null;
            mo13690c();
        } else {
            try {
                JSONArray jSONArray = new JSONArray();
                if (arrayList.size() > 0) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        String str2 = "subscribe";
                        String str3 = "appid=?";
                        String[] strArr = new String[]{(String) it.next()};
                        Cursor query = !(b instanceof SQLiteDatabase) ? b.query(str2, null, str3, strArr, null, null, null) : SQLiteInstrumentation.query(b, str2, null, str3, strArr, null, null, null);
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put("appid", r17);
                            if (query.moveToNext()) {
                                str3 = query.getString(query.getColumnIndex(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING));
                                String string = query.getString(query.getColumnIndex("app_logo"));
                                String string2 = query.getString(query.getColumnIndex("apikey"));
                                int i = query.getInt(query.getColumnIndex("is_bind"));
                                jSONObject.put("found", true);
                                jSONObject.put(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING, str3);
                                jSONObject.put("app_logo", string);
                                jSONObject.put("api_key", string2);
                                jSONObject.put("is_bind", i);
                            } else {
                                jSONObject.put("found", false);
                            }
                            jSONArray.put(jSONObject);
                            if (query != null) {
                                query.close();
                            }
                        } catch (JSONException e) {
                            C1425a.m6444e("DatabaseManager", "error " + e.getMessage());
                            if (query != null) {
                                query.close();
                            }
                        } catch (Throwable th) {
                            if (query != null) {
                                query.close();
                            }
                        }
                    }
                }
                str = !(jSONArray instanceof JSONArray) ? jSONArray.toString() : JSONArrayInstrumentation.toString(jSONArray);
                mo13690c();
            } catch (Exception e2) {
                C1425a.m6444e("DatabaseManager", "error " + e2.getMessage());
                str = null;
                mo13690c();
            } catch (Throwable th2) {
                mo13690c();
            }
        }
        return str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x0108 A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:4:0x0019, PHI: r19 } */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x010e  */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:46:0x0108, code skipped:
            r2 = th;
     */
    /* JADX WARNING: Missing block: B:50:0x010e, code skipped:
            r19.close();
     */
    /* JADX WARNING: Missing block: B:67:0x0131, code skipped:
            r2 = e;
     */
    /* JADX WARNING: Missing block: B:68:0x0132, code skipped:
            r3 = null;
     */
    /* renamed from: a */
    public synchronized java.util.ArrayList<java.lang.String> mo13671a(java.lang.String r25, int r26) {
        /*
        r24 = this;
        monitor-enter(r24);
        r2 = r24.mo13682b();	 Catch:{ all -> 0x0112 }
        r18 = new java.util.ArrayList;	 Catch:{ all -> 0x0112 }
        r18.<init>();	 Catch:{ all -> 0x0112 }
        r20 = new java.util.ArrayList;	 Catch:{ all -> 0x0112 }
        r20.<init>();	 Catch:{ all -> 0x0112 }
        r0 = r24;
        r1 = r25;
        r21 = r0.m6116a(r2, r1);	 Catch:{ all -> 0x0112 }
        r19 = 0;
        r5 = "msg_switch=?";
        r3 = 1;
        r6 = new java.lang.String[r3];	 Catch:{ Exception -> 0x0131, all -> 0x0108 }
        r3 = 0;
        r4 = "0";
        r6[r3] = r4;	 Catch:{ Exception -> 0x0131, all -> 0x0108 }
        if (r2 == 0) goto L_0x0041;
    L_0x0025:
        r3 = "register";
        r4 = 2;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0131, all -> 0x0108 }
        r7 = 0;
        r8 = "channel";
        r4[r7] = r8;	 Catch:{ Exception -> 0x0131, all -> 0x0108 }
        r7 = 1;
        r8 = "pkg_name";
        r4[r7] = r8;	 Catch:{ Exception -> 0x0131, all -> 0x0108 }
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0131, all -> 0x0108 }
        if (r10 != 0) goto L_0x00da;
    L_0x003b:
        r3 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x0131, all -> 0x0108 }
    L_0x003f:
        r19 = r3;
    L_0x0041:
        r3 = 0;
        if (r19 == 0) goto L_0x0136;
    L_0x0044:
        r4 = r19.moveToNext();	 Catch:{ Exception -> 0x00af, all -> 0x0108 }
        if (r4 == 0) goto L_0x0115;
    L_0x004a:
        r3 = "pkg_name";
        r0 = r19;
        r3 = r0.getColumnIndex(r3);	 Catch:{ Exception -> 0x00af, all -> 0x0108 }
        r0 = r19;
        r22 = r0.getString(r3);	 Catch:{ Exception -> 0x00af, all -> 0x0108 }
        r3 = "channel";
        r0 = r19;
        r3 = r0.getColumnIndex(r3);	 Catch:{ Exception -> 0x00af, all -> 0x0108 }
        r0 = r19;
        r23 = r0.getString(r3);	 Catch:{ Exception -> 0x00af, all -> 0x0108 }
        r5 = "pkg_name=? AND app_id=?";
        r3 = 2;
        r6 = new java.lang.String[r3];	 Catch:{ Exception -> 0x00af, all -> 0x0108 }
        r3 = 0;
        r6[r3] = r22;	 Catch:{ Exception -> 0x00af, all -> 0x0108 }
        r3 = 1;
        r6[r3] = r25;	 Catch:{ Exception -> 0x00af, all -> 0x0108 }
        r3 = "blacklist";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x00af, all -> 0x0108 }
        r7 = 0;
        r8 = "type";
        r4[r7] = r8;	 Catch:{ Exception -> 0x00af, all -> 0x0108 }
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00af, all -> 0x0108 }
        if (r10 != 0) goto L_0x00ed;
    L_0x0083:
        r3 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00af, all -> 0x0108 }
    L_0x0087:
        r4 = r3.moveToNext();	 Catch:{ Exception -> 0x00af, all -> 0x0108 }
        if (r4 == 0) goto L_0x009d;
    L_0x008d:
        r4 = 0;
        r4 = r3.getInt(r4);	 Catch:{ Exception -> 0x00af, all -> 0x0108 }
        if (r4 == 0) goto L_0x0044;
    L_0x0094:
        r4 = 0;
        r4 = r3.getInt(r4);	 Catch:{ Exception -> 0x00af, all -> 0x0108 }
        r0 = r26;
        if (r4 == r0) goto L_0x0044;
    L_0x009d:
        r0 = r21;
        r1 = r23;
        r4 = r0.contains(r1);	 Catch:{ Exception -> 0x00af, all -> 0x0108 }
        if (r4 == 0) goto L_0x00ff;
    L_0x00a7:
        r0 = r18;
        r1 = r22;
        r0.add(r1);	 Catch:{ Exception -> 0x00af, all -> 0x0108 }
        goto L_0x0044;
    L_0x00af:
        r2 = move-exception;
        r3 = r19;
    L_0x00b2:
        r4 = "DatabaseManager";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x012d }
        r5.<init>();	 Catch:{ all -> 0x012d }
        r6 = "error ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x012d }
        r2 = r2.getMessage();	 Catch:{ all -> 0x012d }
        r2 = r5.append(r2);	 Catch:{ all -> 0x012d }
        r2 = r2.toString();	 Catch:{ all -> 0x012d }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r4, r2);	 Catch:{ all -> 0x012d }
        r24.mo13690c();	 Catch:{ all -> 0x0112 }
        if (r3 == 0) goto L_0x00d6;
    L_0x00d3:
        r3.close();	 Catch:{ all -> 0x0112 }
    L_0x00d6:
        r2 = r18;
    L_0x00d8:
        monitor-exit(r24);
        return r2;
    L_0x00da:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0131, all -> 0x0108 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r3 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x0131, all -> 0x0108 }
        goto L_0x003f;
    L_0x00ed:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00af, all -> 0x0108 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r3 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x00af, all -> 0x0108 }
        goto L_0x0087;
    L_0x00ff:
        r0 = r20;
        r1 = r22;
        r0.add(r1);	 Catch:{ Exception -> 0x00af, all -> 0x0108 }
        goto L_0x0044;
    L_0x0108:
        r2 = move-exception;
    L_0x0109:
        r24.mo13690c();	 Catch:{ all -> 0x0112 }
        if (r19 == 0) goto L_0x0111;
    L_0x010e:
        r19.close();	 Catch:{ all -> 0x0112 }
    L_0x0111:
        throw r2;	 Catch:{ all -> 0x0112 }
    L_0x0112:
        r2 = move-exception;
        monitor-exit(r24);
        throw r2;
    L_0x0115:
        r2 = r3;
    L_0x0116:
        r0 = r18;
        r1 = r20;
        r0.addAll(r1);	 Catch:{ Exception -> 0x00af, all -> 0x0108 }
        if (r2 == 0) goto L_0x0122;
    L_0x011f:
        r2.close();	 Catch:{ Exception -> 0x00af, all -> 0x0108 }
    L_0x0122:
        r24.mo13690c();	 Catch:{ all -> 0x0112 }
        if (r19 == 0) goto L_0x012a;
    L_0x0127:
        r19.close();	 Catch:{ all -> 0x0112 }
    L_0x012a:
        r2 = r18;
        goto L_0x00d8;
    L_0x012d:
        r2 = move-exception;
        r19 = r3;
        goto L_0x0109;
    L_0x0131:
        r2 = move-exception;
        r3 = r19;
        goto L_0x00b2;
    L_0x0136:
        r2 = r3;
        goto L_0x0116;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p032d.C1355a.mo13671a(java.lang.String, int):java.util.ArrayList");
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x00c6  */
    /* renamed from: a */
    public synchronized java.util.HashMap<java.lang.String, java.lang.String> mo13672a(java.lang.String r15, int r16, boolean r17, int r18, int r19) {
        /*
        r14 = this;
        monitor-enter(r14);
        r2 = r14.mo13682b();	 Catch:{ all -> 0x00ae }
        r11 = 0;
        if (r2 == 0) goto L_0x000e;
    L_0x0008:
        r3 = android.text.TextUtils.isEmpty(r15);	 Catch:{ all -> 0x00ae }
        if (r3 == 0) goto L_0x0011;
    L_0x000e:
        r2 = 0;
    L_0x000f:
        monitor-exit(r14);
        return r2;
    L_0x0011:
        r5 = 0;
        r6 = 0;
        r12 = 0;
        r10 = new java.util.HashMap;	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r10.<init>();	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r3 = "0";
        r3 = r15.equals(r3);	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        if (r3 == 0) goto L_0x00ca;
    L_0x0021:
        if (r16 != 0) goto L_0x006e;
    L_0x0023:
        if (r17 == 0) goto L_0x0032;
    L_0x0025:
        r5 = "status=?";
        r3 = 1;
        r6 = new java.lang.String[r3];	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r3 = 0;
        r4 = 1;
        r4 = java.lang.String.valueOf(r4);	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r6[r3] = r4;	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
    L_0x0032:
        r3 = "message";
        r4 = 0;
        r7 = 0;
        r8 = 0;
        r9 = "time DESC";
        r13 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        if (r13 != 0) goto L_0x0162;
    L_0x003d:
        r3 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
    L_0x0041:
        if (r19 < 0) goto L_0x0174;
    L_0x0043:
        r2 = r3.getCount();	 Catch:{ Exception -> 0x0171 }
        r2 = r2 - r18;
        r2 = r2 - r19;
        if (r2 <= 0) goto L_0x016a;
    L_0x004d:
        r2 = 1;
    L_0x004e:
        r0 = r18;
        r1 = r19;
        r4 = r14.m6115a(r3, r0, r1);	 Catch:{ Exception -> 0x0171 }
        r5 = "msg";
        r10.put(r5, r4);	 Catch:{ Exception -> 0x0171 }
        r4 = "ismore";
        r2 = java.lang.String.valueOf(r2);	 Catch:{ Exception -> 0x0171 }
        r10.put(r4, r2);	 Catch:{ Exception -> 0x0171 }
        r14.mo13690c();	 Catch:{ all -> 0x00ae }
        if (r3 == 0) goto L_0x006c;
    L_0x0069:
        r3.close();	 Catch:{ all -> 0x00ae }
    L_0x006c:
        r2 = r10;
        goto L_0x000f;
    L_0x006e:
        if (r17 == 0) goto L_0x00b1;
    L_0x0070:
        r5 = "status=? AND type = ?";
        r3 = 2;
        r6 = new java.lang.String[r3];	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r3 = 0;
        r4 = 1;
        r4 = java.lang.String.valueOf(r4);	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r6[r3] = r4;	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r3 = 1;
        r4 = java.lang.String.valueOf(r16);	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r6[r3] = r4;	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        goto L_0x0032;
    L_0x0085:
        r2 = move-exception;
        r3 = r11;
    L_0x0087:
        r4 = "DatabaseManager";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x016d }
        r5.<init>();	 Catch:{ all -> 0x016d }
        r6 = "error ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x016d }
        r2 = r2.getMessage();	 Catch:{ all -> 0x016d }
        r2 = r5.append(r2);	 Catch:{ all -> 0x016d }
        r2 = r2.toString();	 Catch:{ all -> 0x016d }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r4, r2);	 Catch:{ all -> 0x016d }
        r2 = 0;
        r14.mo13690c();	 Catch:{ all -> 0x00ae }
        if (r3 == 0) goto L_0x000f;
    L_0x00a9:
        r3.close();	 Catch:{ all -> 0x00ae }
        goto L_0x000f;
    L_0x00ae:
        r2 = move-exception;
        monitor-exit(r14);
        throw r2;
    L_0x00b1:
        r5 = "type = ?";
        r3 = 1;
        r6 = new java.lang.String[r3];	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r3 = 0;
        r4 = java.lang.String.valueOf(r16);	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r6[r3] = r4;	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        goto L_0x0032;
    L_0x00c0:
        r2 = move-exception;
    L_0x00c1:
        r14.mo13690c();	 Catch:{ all -> 0x00ae }
        if (r11 == 0) goto L_0x00c9;
    L_0x00c6:
        r11.close();	 Catch:{ all -> 0x00ae }
    L_0x00c9:
        throw r2;	 Catch:{ all -> 0x00ae }
    L_0x00ca:
        if (r16 != 0) goto L_0x0110;
    L_0x00cc:
        if (r17 == 0) goto L_0x00f3;
    L_0x00ce:
        r5 = "appid = ? AND status =? ";
        r3 = 2;
        r6 = new java.lang.String[r3];	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r3 = 0;
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r4.<init>();	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r7 = "";
        r4 = r4.append(r7);	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r4 = r4.append(r15);	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r6[r3] = r4;	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r3 = 1;
        r4 = 1;
        r4 = java.lang.String.valueOf(r4);	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r6[r3] = r4;	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        goto L_0x0032;
    L_0x00f3:
        r5 = "appid = ?";
        r3 = 1;
        r6 = new java.lang.String[r3];	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r3 = 0;
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r4.<init>();	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r7 = "";
        r4 = r4.append(r7);	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r4 = r4.append(r15);	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r6[r3] = r4;	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        goto L_0x0032;
    L_0x0110:
        if (r17 == 0) goto L_0x013e;
    L_0x0112:
        r5 = "appid = ? AND status=? AND type = ?";
        r3 = 3;
        r6 = new java.lang.String[r3];	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r3 = 0;
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r4.<init>();	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r7 = "";
        r4 = r4.append(r7);	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r4 = r4.append(r15);	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r6[r3] = r4;	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r3 = 1;
        r4 = 1;
        r4 = java.lang.String.valueOf(r4);	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r6[r3] = r4;	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r3 = 2;
        r4 = java.lang.String.valueOf(r16);	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r6[r3] = r4;	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        goto L_0x0032;
    L_0x013e:
        r5 = "appid = ? AND type = ?";
        r3 = 2;
        r6 = new java.lang.String[r3];	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r3 = 0;
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r4.<init>();	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r7 = "";
        r4 = r4.append(r7);	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r4 = r4.append(r15);	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r6[r3] = r4;	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r3 = 1;
        r4 = java.lang.String.valueOf(r16);	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r6[r3] = r4;	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        goto L_0x0032;
    L_0x0162:
        r2 = (android.database.sqlite.SQLiteDatabase) r2;	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        r3 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r2, r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x0085, all -> 0x00c0 }
        goto L_0x0041;
    L_0x016a:
        r2 = 0;
        goto L_0x004e;
    L_0x016d:
        r2 = move-exception;
        r11 = r3;
        goto L_0x00c1;
    L_0x0171:
        r2 = move-exception;
        goto L_0x0087;
    L_0x0174:
        r2 = r12;
        goto L_0x004e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p032d.C1355a.mo13672a(java.lang.String, int, boolean, int, int):java.util.HashMap");
    }

    /* renamed from: a */
    public void mo13673a(Context context) {
        this.f4780a = context;
        mo13693d();
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00fb  */
    /* renamed from: a */
    public synchronized void mo13674a(java.lang.String r21, java.lang.String r22, java.lang.String r23) {
        /*
        r20 = this;
        monitor-enter(r20);
        r2 = r20.mo13682b();	 Catch:{ all -> 0x0022 }
        r18 = 0;
        if (r2 != 0) goto L_0x0013;
    L_0x0009:
        r20.mo13690c();	 Catch:{ all -> 0x0022 }
        if (r18 == 0) goto L_0x0011;
    L_0x000e:
        r18.close();	 Catch:{ all -> 0x0022 }
    L_0x0011:
        monitor-exit(r20);
        return;
    L_0x0013:
        if (r21 == 0) goto L_0x0019;
    L_0x0015:
        if (r22 == 0) goto L_0x0019;
    L_0x0017:
        if (r23 != 0) goto L_0x0025;
    L_0x0019:
        r20.mo13690c();	 Catch:{ all -> 0x0022 }
        if (r18 == 0) goto L_0x0011;
    L_0x001e:
        r18.close();	 Catch:{ all -> 0x0022 }
        goto L_0x0011;
    L_0x0022:
        r2 = move-exception;
        monitor-exit(r20);
        throw r2;
    L_0x0025:
        r19 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0109, all -> 0x0107 }
        r19.<init>();	 Catch:{ Exception -> 0x0109, all -> 0x0107 }
        r3 = "noti_id";
        r0 = r19;
        r1 = r23;
        r0.put(r3, r1);	 Catch:{ Exception -> 0x0109, all -> 0x0107 }
        r3 = "app_id";
        r0 = r19;
        r1 = r22;
        r0.put(r3, r1);	 Catch:{ Exception -> 0x0109, all -> 0x0107 }
        r3 = "msg_id";
        r0 = r19;
        r1 = r21;
        r0.put(r3, r1);	 Catch:{ Exception -> 0x0109, all -> 0x0107 }
        r3 = "time_stamp";
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0109, all -> 0x0107 }
        r4.<init>();	 Catch:{ Exception -> 0x0109, all -> 0x0107 }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0109, all -> 0x0107 }
        r4 = r4.append(r6);	 Catch:{ Exception -> 0x0109, all -> 0x0107 }
        r5 = "";
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x0109, all -> 0x0107 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0109, all -> 0x0107 }
        r0 = r19;
        r0.put(r3, r4);	 Catch:{ Exception -> 0x0109, all -> 0x0107 }
        r3 = "notification";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0109, all -> 0x0107 }
        r5 = 0;
        r6 = "noti_id";
        r4[r5] = r6;	 Catch:{ Exception -> 0x0109, all -> 0x0107 }
        r5 = "noti_id=?";
        r6 = 1;
        r6 = new java.lang.String[r6];	 Catch:{ Exception -> 0x0109, all -> 0x0107 }
        r7 = 0;
        r6[r7] = r23;	 Catch:{ Exception -> 0x0109, all -> 0x0107 }
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0109, all -> 0x0107 }
        if (r10 != 0) goto L_0x00a5;
    L_0x007c:
        r3 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x0109, all -> 0x0107 }
    L_0x0080:
        if (r3 == 0) goto L_0x00e6;
    L_0x0082:
        r4 = r3.moveToNext();	 Catch:{ Exception -> 0x00bf }
        if (r4 == 0) goto L_0x00e6;
    L_0x0088:
        r4 = "notification";
        r5 = "noti_id=?";
        r6 = 1;
        r6 = new java.lang.String[r6];	 Catch:{ Exception -> 0x00bf }
        r7 = 0;
        r6[r7] = r23;	 Catch:{ Exception -> 0x00bf }
        r7 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00bf }
        if (r7 != 0) goto L_0x00b7;
    L_0x0096:
        r0 = r19;
        r2.update(r4, r0, r5, r6);	 Catch:{ Exception -> 0x00bf }
    L_0x009b:
        r20.mo13690c();	 Catch:{ all -> 0x0022 }
        if (r3 == 0) goto L_0x0011;
    L_0x00a0:
        r3.close();	 Catch:{ all -> 0x0022 }
        goto L_0x0011;
    L_0x00a5:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0109, all -> 0x0107 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r3 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x0109, all -> 0x0107 }
        goto L_0x0080;
    L_0x00b7:
        r2 = (android.database.sqlite.SQLiteDatabase) r2;	 Catch:{ Exception -> 0x00bf }
        r0 = r19;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r2, r4, r0, r5, r6);	 Catch:{ Exception -> 0x00bf }
        goto L_0x009b;
    L_0x00bf:
        r2 = move-exception;
    L_0x00c0:
        r4 = "DatabaseManager";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00f3 }
        r5.<init>();	 Catch:{ all -> 0x00f3 }
        r6 = "error ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x00f3 }
        r2 = r2.getMessage();	 Catch:{ all -> 0x00f3 }
        r2 = r5.append(r2);	 Catch:{ all -> 0x00f3 }
        r2 = r2.toString();	 Catch:{ all -> 0x00f3 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r4, r2);	 Catch:{ all -> 0x00f3 }
        r20.mo13690c();	 Catch:{ all -> 0x0022 }
        if (r3 == 0) goto L_0x0011;
    L_0x00e1:
        r3.close();	 Catch:{ all -> 0x0022 }
        goto L_0x0011;
    L_0x00e6:
        r4 = "notification";
        r5 = 0;
        r6 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00bf }
        if (r6 != 0) goto L_0x00ff;
    L_0x00ed:
        r0 = r19;
        r2.insert(r4, r5, r0);	 Catch:{ Exception -> 0x00bf }
        goto L_0x009b;
    L_0x00f3:
        r2 = move-exception;
        r18 = r3;
    L_0x00f6:
        r20.mo13690c();	 Catch:{ all -> 0x0022 }
        if (r18 == 0) goto L_0x00fe;
    L_0x00fb:
        r18.close();	 Catch:{ all -> 0x0022 }
    L_0x00fe:
        throw r2;	 Catch:{ all -> 0x0022 }
    L_0x00ff:
        r2 = (android.database.sqlite.SQLiteDatabase) r2;	 Catch:{ Exception -> 0x00bf }
        r0 = r19;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.insert(r2, r4, r5, r0);	 Catch:{ Exception -> 0x00bf }
        goto L_0x009b;
    L_0x0107:
        r2 = move-exception;
        goto L_0x00f6;
    L_0x0109:
        r2 = move-exception;
        r3 = r18;
        goto L_0x00c0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p032d.C1355a.mo13674a(java.lang.String, java.lang.String, java.lang.String):void");
    }

    /* renamed from: a */
    public synchronized void mo13675a(String str, String str2, boolean z) {
        int i = 1;
        synchronized (this) {
            SQLiteDatabase b = mo13682b();
            Cursor cursor = null;
            if (b == null) {
                if (cursor != null) {
                    cursor.close();
                }
                mo13690c();
            } else {
                try {
                    ContentValues contentValues = new ContentValues();
                    if (!z) {
                        i = 0;
                    }
                    contentValues.put("is_bind", Integer.valueOf(i));
                    String str3;
                    String str4;
                    String[] strArr;
                    if (!TextUtils.isEmpty(str)) {
                        str3 = "subscribe";
                        str4 = "apikey=?";
                        strArr = new String[]{str};
                        if (b instanceof SQLiteDatabase) {
                            SQLiteInstrumentation.update(b, str3, contentValues, str4, strArr);
                        } else {
                            b.update(str3, contentValues, str4, strArr);
                        }
                    } else if (!TextUtils.isEmpty(str2)) {
                        str3 = "subscribe";
                        str4 = "appid=?";
                        strArr = new String[]{str2};
                        if (b instanceof SQLiteDatabase) {
                            SQLiteInstrumentation.update(b, str3, contentValues, str4, strArr);
                        } else {
                            b.update(str3, contentValues, str4, strArr);
                        }
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    mo13690c();
                } catch (Exception e) {
                    C1425a.m6444e("DatabaseManager", "updateBindState " + e.getMessage());
                    if (cursor != null) {
                        cursor.close();
                    }
                    mo13690c();
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    mo13690c();
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:60:0x00f9 A:{SYNTHETIC, Splitter:B:60:0x00f9} */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x01a5 A:{SYNTHETIC, Splitter:B:100:0x01a5} */
    /* renamed from: a */
    public synchronized void mo13676a(java.util.List<java.lang.String> r21, java.util.List<java.lang.String> r22) {
        /*
        r20 = this;
        monitor-enter(r20);
        r1 = r20.mo13682b();	 Catch:{ all -> 0x00a7 }
        r0 = r20;
        r2 = r0.f4780a;	 Catch:{ all -> 0x00a7 }
        r3 = "notification";
        r2 = r2.getSystemService(r3);	 Catch:{ all -> 0x00a7 }
        r0 = r2;
        r0 = (android.app.NotificationManager) r0;	 Catch:{ all -> 0x00a7 }
        r17 = r0;
        if (r1 != 0) goto L_0x001b;
    L_0x0016:
        r20.mo13690c();	 Catch:{ all -> 0x00a7 }
    L_0x0019:
        monitor-exit(r20);
        return;
    L_0x001b:
        if (r21 == 0) goto L_0x00fd;
    L_0x001d:
        r2 = r21.size();	 Catch:{ Exception -> 0x0085 }
        if (r2 <= 0) goto L_0x00fd;
    L_0x0023:
        r19 = r21.iterator();	 Catch:{ Exception -> 0x0085 }
    L_0x0027:
        r2 = r19.hasNext();	 Catch:{ Exception -> 0x0085 }
        if (r2 == 0) goto L_0x01b8;
    L_0x002d:
        r2 = r19.next();	 Catch:{ Exception -> 0x0085 }
        r0 = r2;
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0085 }
        r6 = r0;
        r18 = 0;
        r2 = "notification";
        r3 = 1;
        r3 = new java.lang.String[r3];	 Catch:{ Exception -> 0x01ca, all -> 0x01c5 }
        r4 = 0;
        r5 = "noti_id";
        r3[r4] = r5;	 Catch:{ Exception -> 0x01ca, all -> 0x01c5 }
        r4 = "msg_id=?";
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ Exception -> 0x01ca, all -> 0x01c5 }
        r7 = 0;
        r5[r7] = r6;	 Catch:{ Exception -> 0x01ca, all -> 0x01c5 }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x01ca, all -> 0x01c5 }
        if (r9 != 0) goto L_0x00aa;
    L_0x0050:
        r3 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x01ca, all -> 0x01c5 }
    L_0x0054:
        r2 = r3.moveToNext();	 Catch:{ Exception -> 0x00c5 }
        if (r2 == 0) goto L_0x007f;
    L_0x005a:
        r2 = 0;
        r2 = r3.getString(r2);	 Catch:{ Exception -> 0x00c5 }
        r4 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x00c5 }
        r5 = 11;
        if (r4 < r5) goto L_0x00bb;
    L_0x0065:
        r4 = com.baidu.android.pushservice.util.C1578v.m7091b(r2);	 Catch:{ Exception -> 0x00c5 }
        r0 = r17;
        r0.cancel(r2, r4);	 Catch:{ Exception -> 0x00c5 }
    L_0x006e:
        r4 = "notification";
        r5 = "noti_id=?";
        r6 = 1;
        r6 = new java.lang.String[r6];	 Catch:{ Exception -> 0x00c5 }
        r7 = 0;
        r6[r7] = r2;	 Catch:{ Exception -> 0x00c5 }
        r2 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00c5 }
        if (r2 != 0) goto L_0x00ee;
    L_0x007c:
        r1.delete(r4, r5, r6);	 Catch:{ Exception -> 0x00c5 }
    L_0x007f:
        if (r3 == 0) goto L_0x0027;
    L_0x0081:
        r3.close();	 Catch:{ Exception -> 0x0085 }
        goto L_0x0027;
    L_0x0085:
        r1 = move-exception;
        r2 = "DatabaseManager";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00e9 }
        r3.<init>();	 Catch:{ all -> 0x00e9 }
        r4 = "error ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00e9 }
        r1 = r1.getMessage();	 Catch:{ all -> 0x00e9 }
        r1 = r3.append(r1);	 Catch:{ all -> 0x00e9 }
        r1 = r1.toString();	 Catch:{ all -> 0x00e9 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r2, r1);	 Catch:{ all -> 0x00e9 }
        r20.mo13690c();	 Catch:{ all -> 0x00a7 }
        goto L_0x0019;
    L_0x00a7:
        r1 = move-exception;
        monitor-exit(r20);
        throw r1;
    L_0x00aa:
        r0 = r1;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x01ca, all -> 0x01c5 }
        r9 = r0;
        r10 = r2;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r3 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r9, r10, r11, r12, r13, r14, r15, r16);	 Catch:{ Exception -> 0x01ca, all -> 0x01c5 }
        goto L_0x0054;
    L_0x00bb:
        r4 = com.baidu.android.pushservice.util.C1578v.m7091b(r2);	 Catch:{ Exception -> 0x00c5 }
        r0 = r17;
        r0.cancel(r4);	 Catch:{ Exception -> 0x00c5 }
        goto L_0x006e;
    L_0x00c5:
        r2 = move-exception;
    L_0x00c6:
        r4 = "DatabaseManager";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00f6 }
        r5.<init>();	 Catch:{ all -> 0x00f6 }
        r6 = "error ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x00f6 }
        r2 = r2.getMessage();	 Catch:{ all -> 0x00f6 }
        r2 = r5.append(r2);	 Catch:{ all -> 0x00f6 }
        r2 = r2.toString();	 Catch:{ all -> 0x00f6 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r4, r2);	 Catch:{ all -> 0x00f6 }
        if (r3 == 0) goto L_0x0027;
    L_0x00e4:
        r3.close();	 Catch:{ Exception -> 0x0085 }
        goto L_0x0027;
    L_0x00e9:
        r1 = move-exception;
        r20.mo13690c();	 Catch:{ all -> 0x00a7 }
        throw r1;	 Catch:{ all -> 0x00a7 }
    L_0x00ee:
        r0 = r1;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00c5 }
        r2 = r0;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.delete(r2, r4, r5, r6);	 Catch:{ Exception -> 0x00c5 }
        goto L_0x007f;
    L_0x00f6:
        r1 = move-exception;
    L_0x00f7:
        if (r3 == 0) goto L_0x00fc;
    L_0x00f9:
        r3.close();	 Catch:{ Exception -> 0x0085 }
    L_0x00fc:
        throw r1;	 Catch:{ Exception -> 0x0085 }
    L_0x00fd:
        if (r22 == 0) goto L_0x01b8;
    L_0x00ff:
        r2 = r22.size();	 Catch:{ Exception -> 0x0085 }
        if (r2 <= 0) goto L_0x01b8;
    L_0x0105:
        r19 = r22.iterator();	 Catch:{ Exception -> 0x0085 }
    L_0x0109:
        r2 = r19.hasNext();	 Catch:{ Exception -> 0x0085 }
        if (r2 == 0) goto L_0x01b8;
    L_0x010f:
        r2 = r19.next();	 Catch:{ Exception -> 0x0085 }
        r0 = r2;
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0085 }
        r6 = r0;
        r18 = 0;
        r2 = "notification";
        r3 = 1;
        r3 = new java.lang.String[r3];	 Catch:{ Exception -> 0x01c1, all -> 0x01bd }
        r4 = 0;
        r5 = "noti_id";
        r3[r4] = r5;	 Catch:{ Exception -> 0x01c1, all -> 0x01bd }
        r4 = "app_id=?";
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ Exception -> 0x01c1, all -> 0x01bd }
        r7 = 0;
        r5[r7] = r6;	 Catch:{ Exception -> 0x01c1, all -> 0x01bd }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x01c1, all -> 0x01bd }
        if (r9 != 0) goto L_0x0186;
    L_0x0132:
        r2 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x01c1, all -> 0x01bd }
        r3 = r2;
    L_0x0137:
        r2 = r3.moveToNext();	 Catch:{ Exception -> 0x0163 }
        if (r2 == 0) goto L_0x01b1;
    L_0x013d:
        r2 = 0;
        r2 = r3.getString(r2);	 Catch:{ Exception -> 0x0163 }
        r4 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x0163 }
        r5 = 11;
        if (r4 < r5) goto L_0x0198;
    L_0x0148:
        r4 = com.baidu.android.pushservice.util.C1578v.m7091b(r2);	 Catch:{ Exception -> 0x0163 }
        r0 = r17;
        r0.cancel(r2, r4);	 Catch:{ Exception -> 0x0163 }
    L_0x0151:
        r4 = "notification";
        r5 = "noti_id=?";
        r6 = 1;
        r6 = new java.lang.String[r6];	 Catch:{ Exception -> 0x0163 }
        r7 = 0;
        r6[r7] = r2;	 Catch:{ Exception -> 0x0163 }
        r2 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0163 }
        if (r2 != 0) goto L_0x01a9;
    L_0x015f:
        r1.delete(r4, r5, r6);	 Catch:{ Exception -> 0x0163 }
        goto L_0x0137;
    L_0x0163:
        r2 = move-exception;
    L_0x0164:
        r4 = "DatabaseManager";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01a2 }
        r5.<init>();	 Catch:{ all -> 0x01a2 }
        r6 = "error ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x01a2 }
        r2 = r2.getMessage();	 Catch:{ all -> 0x01a2 }
        r2 = r5.append(r2);	 Catch:{ all -> 0x01a2 }
        r2 = r2.toString();	 Catch:{ all -> 0x01a2 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r4, r2);	 Catch:{ all -> 0x01a2 }
        if (r3 == 0) goto L_0x0109;
    L_0x0182:
        r3.close();	 Catch:{ Exception -> 0x0085 }
        goto L_0x0109;
    L_0x0186:
        r0 = r1;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x01c1, all -> 0x01bd }
        r9 = r0;
        r10 = r2;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r9, r10, r11, r12, r13, r14, r15, r16);	 Catch:{ Exception -> 0x01c1, all -> 0x01bd }
        r3 = r2;
        goto L_0x0137;
    L_0x0198:
        r4 = com.baidu.android.pushservice.util.C1578v.m7091b(r2);	 Catch:{ Exception -> 0x0163 }
        r0 = r17;
        r0.cancel(r4);	 Catch:{ Exception -> 0x0163 }
        goto L_0x0151;
    L_0x01a2:
        r1 = move-exception;
    L_0x01a3:
        if (r3 == 0) goto L_0x01a8;
    L_0x01a5:
        r3.close();	 Catch:{ Exception -> 0x0085 }
    L_0x01a8:
        throw r1;	 Catch:{ Exception -> 0x0085 }
    L_0x01a9:
        r0 = r1;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0163 }
        r2 = r0;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.delete(r2, r4, r5, r6);	 Catch:{ Exception -> 0x0163 }
        goto L_0x0137;
    L_0x01b1:
        if (r3 == 0) goto L_0x0109;
    L_0x01b3:
        r3.close();	 Catch:{ Exception -> 0x0085 }
        goto L_0x0109;
    L_0x01b8:
        r20.mo13690c();	 Catch:{ all -> 0x00a7 }
        goto L_0x0019;
    L_0x01bd:
        r1 = move-exception;
        r3 = r18;
        goto L_0x01a3;
    L_0x01c1:
        r2 = move-exception;
        r3 = r18;
        goto L_0x0164;
    L_0x01c5:
        r1 = move-exception;
        r3 = r18;
        goto L_0x00f7;
    L_0x01ca:
        r2 = move-exception;
        r3 = r18;
        goto L_0x00c6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p032d.C1355a.mo13676a(java.util.List, java.util.List):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:61:0x0112 A:{Catch:{ all -> 0x010f }} */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0112 A:{Catch:{ all -> 0x010f }} */
    /* renamed from: a */
    public synchronized boolean mo13677a(java.lang.String r20) {
        /*
        r19 = this;
        monitor-enter(r19);
        r1 = r19.mo13682b();	 Catch:{ all -> 0x0052 }
        r18 = 0;
        if (r1 != 0) goto L_0x0014;
    L_0x0009:
        r1 = 0;
        if (r18 == 0) goto L_0x000f;
    L_0x000c:
        r18.close();	 Catch:{ all -> 0x0052 }
    L_0x000f:
        r19.mo13690c();	 Catch:{ all -> 0x0052 }
    L_0x0012:
        monitor-exit(r19);
        return r1;
    L_0x0014:
        r2 = "subscribe";
        r3 = 1;
        r3 = new java.lang.String[r3];	 Catch:{ Exception -> 0x00e7 }
        r4 = 0;
        r5 = "apikey";
        r3[r4] = r5;	 Catch:{ Exception -> 0x00e7 }
        r4 = "app_logo=?";
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ Exception -> 0x00e7 }
        r6 = 0;
        r5[r6] = r20;	 Catch:{ Exception -> 0x00e7 }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00e7 }
        if (r9 != 0) goto L_0x0055;
    L_0x002d:
        r18 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x00e7 }
    L_0x0031:
        r17 = 0;
        r2 = r18.moveToNext();	 Catch:{ Exception -> 0x00e7 }
        if (r2 == 0) goto L_0x0066;
    L_0x0039:
        r2 = 0;
        r0 = r18;
        r2 = r0.getString(r2);	 Catch:{ Exception -> 0x00e7 }
        r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x00e7 }
        if (r2 != 0) goto L_0x0066;
    L_0x0046:
        r1 = 1;
        r2 = r18;
    L_0x0049:
        if (r2 == 0) goto L_0x004e;
    L_0x004b:
        r2.close();	 Catch:{ all -> 0x0052 }
    L_0x004e:
        r19.mo13690c();	 Catch:{ all -> 0x0052 }
        goto L_0x0012;
    L_0x0052:
        r1 = move-exception;
        monitor-exit(r19);
        throw r1;
    L_0x0055:
        r0 = r1;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00e7 }
        r9 = r0;
        r10 = r2;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r18 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r9, r10, r11, r12, r13, r14, r15, r16);	 Catch:{ Exception -> 0x00e7 }
        goto L_0x0031;
    L_0x0066:
        r18.close();	 Catch:{ Exception -> 0x00e7 }
        r2 = "weak_subscribe";
        r3 = 1;
        r3 = new java.lang.String[r3];	 Catch:{ Exception -> 0x00e7 }
        r4 = 0;
        r5 = "apikey";
        r3[r4] = r5;	 Catch:{ Exception -> 0x00e7 }
        r4 = "app_logo=?";
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ Exception -> 0x00e7 }
        r6 = 0;
        r5[r6] = r20;	 Catch:{ Exception -> 0x00e7 }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00e7 }
        if (r9 != 0) goto L_0x009b;
    L_0x0083:
        r9 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x00e7 }
    L_0x0087:
        r2 = r9.moveToNext();	 Catch:{ Exception -> 0x0121, all -> 0x0119 }
        if (r2 == 0) goto L_0x00ac;
    L_0x008d:
        r2 = 0;
        r2 = r9.getString(r2);	 Catch:{ Exception -> 0x0121, all -> 0x0119 }
        r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x0121, all -> 0x0119 }
        if (r2 != 0) goto L_0x00ac;
    L_0x0098:
        r1 = 1;
        r2 = r9;
        goto L_0x0049;
    L_0x009b:
        r0 = r1;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00e7 }
        r9 = r0;
        r10 = r2;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r9 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r9, r10, r11, r12, r13, r14, r15, r16);	 Catch:{ Exception -> 0x00e7 }
        goto L_0x0087;
    L_0x00ac:
        r9.close();	 Catch:{ Exception -> 0x0121, all -> 0x0119 }
        r2 = "app_info";
        r3 = 1;
        r3 = new java.lang.String[r3];	 Catch:{ Exception -> 0x0121, all -> 0x0119 }
        r4 = 0;
        r5 = "apikey";
        r3[r4] = r5;	 Catch:{ Exception -> 0x0121, all -> 0x0119 }
        r4 = "app_logo=?";
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ Exception -> 0x0121, all -> 0x0119 }
        r6 = 0;
        r5[r6] = r20;	 Catch:{ Exception -> 0x0121, all -> 0x0119 }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r10 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0121, all -> 0x0119 }
        if (r10 != 0) goto L_0x00e0;
    L_0x00c8:
        r2 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0121, all -> 0x0119 }
    L_0x00cc:
        r1 = r2.moveToNext();	 Catch:{ Exception -> 0x0125, all -> 0x011d }
        if (r1 == 0) goto L_0x0129;
    L_0x00d2:
        r1 = 0;
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x0125, all -> 0x011d }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Exception -> 0x0125, all -> 0x011d }
        if (r1 != 0) goto L_0x0129;
    L_0x00dd:
        r1 = 1;
        goto L_0x0049;
    L_0x00e0:
        r1 = (android.database.sqlite.SQLiteDatabase) r1;	 Catch:{ Exception -> 0x0121, all -> 0x0119 }
        r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0121, all -> 0x0119 }
        goto L_0x00cc;
    L_0x00e7:
        r1 = move-exception;
    L_0x00e8:
        r2 = "DatabaseManager";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x010f }
        r3.<init>();	 Catch:{ all -> 0x010f }
        r4 = "isLightAppInfoGeted ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x010f }
        r1 = r1.getMessage();	 Catch:{ all -> 0x010f }
        r1 = r3.append(r1);	 Catch:{ all -> 0x010f }
        r1 = r1.toString();	 Catch:{ all -> 0x010f }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r2, r1);	 Catch:{ all -> 0x010f }
        r1 = 0;
        if (r18 == 0) goto L_0x010a;
    L_0x0107:
        r18.close();	 Catch:{ all -> 0x0052 }
    L_0x010a:
        r19.mo13690c();	 Catch:{ all -> 0x0052 }
        goto L_0x0012;
    L_0x010f:
        r1 = move-exception;
    L_0x0110:
        if (r18 == 0) goto L_0x0115;
    L_0x0112:
        r18.close();	 Catch:{ all -> 0x0052 }
    L_0x0115:
        r19.mo13690c();	 Catch:{ all -> 0x0052 }
        throw r1;	 Catch:{ all -> 0x0052 }
    L_0x0119:
        r1 = move-exception;
        r18 = r9;
        goto L_0x0110;
    L_0x011d:
        r1 = move-exception;
        r18 = r2;
        goto L_0x0110;
    L_0x0121:
        r1 = move-exception;
        r18 = r9;
        goto L_0x00e8;
    L_0x0125:
        r1 = move-exception;
        r18 = r2;
        goto L_0x00e8;
    L_0x0129:
        r1 = r17;
        goto L_0x0049;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p032d.C1355a.mo13677a(java.lang.String):boolean");
    }

    /* renamed from: a */
    public synchronized boolean mo13678a(String str, String str2, int i) {
        boolean z = false;
        synchronized (this) {
            SQLiteDatabase b = mo13682b();
            if (b != null) {
                try {
                    if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                        C1425a.m6442c("DatabaseManager", "addBlackList appid or pkgName can not be null");
                    } else {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("app_id", str);
                        contentValues.put("pkg_name", str2);
                        contentValues.put("type", Integer.valueOf(i));
                        String str3 = "blacklist";
                        String str4 = "app_id=? AND pkg_name=?";
                        String[] strArr = new String[]{str, str2};
                        boolean z2 = ((long) (!(b instanceof SQLiteDatabase) ? b.update(str3, contentValues, str4, strArr) : SQLiteInstrumentation.update((SQLiteDatabase) b, str3, contentValues, str4, strArr))) > 0;
                        mo13690c();
                        z = z2;
                    }
                } catch (Exception e) {
                    C1425a.m6444e("DatabaseManager", "error " + e.getMessage());
                    return z;
                } finally {
                    mo13690c();
                }
            }
        }
        return z;
    }

    /* renamed from: a */
    public synchronized boolean mo13679a(String str, boolean z) {
        boolean z2 = false;
        synchronized (this) {
            SQLiteDatabase b = mo13682b();
            if (b != null) {
                try {
                    if (TextUtils.isEmpty(str)) {
                        C1425a.m6442c("DatabaseManager", "setAppMsgReceiveStatus pkgName can not be null");
                    } else {
                        ContentValues contentValues = new ContentValues();
                        if (z) {
                            contentValues.put("msg_switch", Integer.valueOf(0));
                        } else {
                            contentValues.put("msg_switch", Integer.valueOf(1));
                        }
                        String str2 = JiceArgs.EVENT_REGISTER;
                        String str3 = "pkg_name=?";
                        String[] strArr = new String[]{str};
                        boolean z3 = (!(b instanceof SQLiteDatabase) ? b.update(str2, contentValues, str3, strArr) : SQLiteInstrumentation.update((SQLiteDatabase) b, str2, contentValues, str3, strArr)) > 0;
                        mo13690c();
                        z2 = z3;
                    }
                } catch (Exception e) {
                    C1425a.m6444e("DatabaseManager", "error " + e.getMessage());
                } finally {
                    mo13690c();
                }
            }
        }
        return z2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:89:0x01c6 A:{SYNTHETIC, Splitter:B:89:0x01c6} */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01c6 A:{SYNTHETIC, Splitter:B:89:0x01c6} */
    /* renamed from: b */
    public synchronized int mo13680b(int r21, java.lang.String r22) {
        /*
        r20 = this;
        monitor-enter(r20);
        r1 = r20.mo13682b();	 Catch:{ all -> 0x00a7 }
        r17 = -1;
        r18 = 0;
        if (r1 == 0) goto L_0x0011;
    L_0x000b:
        r2 = android.text.TextUtils.isEmpty(r22);	 Catch:{ Exception -> 0x00bb, all -> 0x01c1 }
        if (r2 == 0) goto L_0x0023;
    L_0x0011:
        if (r18 == 0) goto L_0x001c;
    L_0x0013:
        r1 = r18.isClosed();	 Catch:{ all -> 0x00a7 }
        if (r1 != 0) goto L_0x001c;
    L_0x0019:
        r18.close();	 Catch:{ all -> 0x00a7 }
    L_0x001c:
        r20.mo13690c();	 Catch:{ all -> 0x00a7 }
        r1 = r17;
    L_0x0021:
        monitor-exit(r20);
        return r1;
    L_0x0023:
        r19 = new android.content.ContentValues;	 Catch:{ Exception -> 0x00bb, all -> 0x01c1 }
        r19.<init>();	 Catch:{ Exception -> 0x00bb, all -> 0x01c1 }
        r2 = "status";
        r3 = 0;
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Exception -> 0x00bb, all -> 0x01c1 }
        r0 = r19;
        r0.put(r2, r3);	 Catch:{ Exception -> 0x00bb, all -> 0x01c1 }
        r2 = "0";
        r0 = r22;
        r2 = r0.equals(r2);	 Catch:{ Exception -> 0x00bb, all -> 0x01c1 }
        if (r2 == 0) goto L_0x00aa;
    L_0x003e:
        r4 = "status=?";
        r2 = 1;
        r5 = new java.lang.String[r2];	 Catch:{ Exception -> 0x00bb, all -> 0x01c1 }
        r2 = 0;
        r3 = 1;
        r3 = java.lang.String.valueOf(r3);	 Catch:{ Exception -> 0x00bb, all -> 0x01c1 }
        r5[r2] = r3;	 Catch:{ Exception -> 0x00bb, all -> 0x01c1 }
    L_0x004b:
        r2 = "message";
        r3 = 1;
        r3 = new java.lang.String[r3];	 Catch:{ Exception -> 0x00bb, all -> 0x01c1 }
        r6 = 0;
        r7 = "msgid";
        r3[r6] = r7;	 Catch:{ Exception -> 0x00bb, all -> 0x01c1 }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00bb, all -> 0x01c1 }
        if (r9 != 0) goto L_0x00c2;
    L_0x005c:
        r2 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x00bb, all -> 0x01c1 }
    L_0x0060:
        r3 = new java.util.ArrayList;	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r3.<init>();	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        if (r2 == 0) goto L_0x00d3;
    L_0x0067:
        r4 = r2.moveToNext();	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        if (r4 == 0) goto L_0x00d3;
    L_0x006d:
        r4 = 0;
        r4 = r2.getString(r4);	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r3.add(r4);	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        goto L_0x0067;
    L_0x0076:
        r1 = move-exception;
        r3 = r2;
        r2 = r1;
        r1 = r17;
    L_0x007b:
        r4 = "DatabaseManager";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01d5 }
        r5.<init>();	 Catch:{ all -> 0x01d5 }
        r6 = "error ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x01d5 }
        r2 = r2.getMessage();	 Catch:{ all -> 0x01d5 }
        r2 = r5.append(r2);	 Catch:{ all -> 0x01d5 }
        r2 = r2.toString();	 Catch:{ all -> 0x01d5 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r4, r2);	 Catch:{ all -> 0x01d5 }
        if (r3 == 0) goto L_0x00a2;
    L_0x0099:
        r2 = r3.isClosed();	 Catch:{ all -> 0x00a7 }
        if (r2 != 0) goto L_0x00a2;
    L_0x009f:
        r3.close();	 Catch:{ all -> 0x00a7 }
    L_0x00a2:
        r20.mo13690c();	 Catch:{ all -> 0x00a7 }
        goto L_0x0021;
    L_0x00a7:
        r1 = move-exception;
        monitor-exit(r20);
        throw r1;
    L_0x00aa:
        r4 = "status=? AND appid=?";
        r2 = 2;
        r5 = new java.lang.String[r2];	 Catch:{ Exception -> 0x00bb, all -> 0x01c1 }
        r2 = 0;
        r3 = 1;
        r3 = java.lang.String.valueOf(r3);	 Catch:{ Exception -> 0x00bb, all -> 0x01c1 }
        r5[r2] = r3;	 Catch:{ Exception -> 0x00bb, all -> 0x01c1 }
        r2 = 1;
        r5[r2] = r22;	 Catch:{ Exception -> 0x00bb, all -> 0x01c1 }
        goto L_0x004b;
    L_0x00bb:
        r1 = move-exception;
        r2 = r1;
        r3 = r18;
        r1 = r17;
        goto L_0x007b;
    L_0x00c2:
        r0 = r1;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00bb, all -> 0x01c1 }
        r9 = r0;
        r10 = r2;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r9, r10, r11, r12, r13, r14, r15, r16);	 Catch:{ Exception -> 0x00bb, all -> 0x01c1 }
        goto L_0x0060;
    L_0x00d3:
        r4 = "0";
        r0 = r22;
        r4 = r0.equals(r4);	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        if (r4 == 0) goto L_0x0145;
    L_0x00dd:
        if (r21 != 0) goto L_0x0119;
    L_0x00df:
        r4 = "message";
        r5 = "status=?";
        r6 = 1;
        r6 = new java.lang.String[r6];	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r7 = 0;
        r8 = 1;
        r8 = java.lang.String.valueOf(r8);	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r6[r7] = r8;	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r7 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        if (r7 != 0) goto L_0x0110;
    L_0x00f2:
        r0 = r19;
        r1 = r1.update(r4, r0, r5, r6);	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
    L_0x00f8:
        r17 = r1;
    L_0x00fa:
        r1 = -1;
        r0 = r17;
        if (r0 != r1) goto L_0x01b9;
    L_0x00ff:
        r1 = 0;
    L_0x0100:
        if (r2 == 0) goto L_0x010b;
    L_0x0102:
        r3 = r2.isClosed();	 Catch:{ all -> 0x00a7 }
        if (r3 != 0) goto L_0x010b;
    L_0x0108:
        r2.close();	 Catch:{ all -> 0x00a7 }
    L_0x010b:
        r20.mo13690c();	 Catch:{ all -> 0x00a7 }
        goto L_0x0021;
    L_0x0110:
        r1 = (android.database.sqlite.SQLiteDatabase) r1;	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r0 = r19;
        r1 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r1, r4, r0, r5, r6);	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        goto L_0x00f8;
    L_0x0119:
        r4 = "message";
        r5 = "status=? AND type=?";
        r6 = 2;
        r6 = new java.lang.String[r6];	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r7 = 0;
        r8 = 1;
        r8 = java.lang.String.valueOf(r8);	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r6[r7] = r8;	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r7 = 1;
        r8 = java.lang.String.valueOf(r21);	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r6[r7] = r8;	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r7 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        if (r7 != 0) goto L_0x013c;
    L_0x0133:
        r0 = r19;
        r1 = r1.update(r4, r0, r5, r6);	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
    L_0x0139:
        r17 = r1;
        goto L_0x00fa;
    L_0x013c:
        r1 = (android.database.sqlite.SQLiteDatabase) r1;	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r0 = r19;
        r1 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r1, r4, r0, r5, r6);	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        goto L_0x0139;
    L_0x0145:
        if (r21 != 0) goto L_0x017c;
    L_0x0147:
        r4 = "message";
        r5 = "appid=?";
        r6 = 1;
        r6 = new java.lang.String[r6];	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r7 = 0;
        r8 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r8.<init>();	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r9 = "";
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r0 = r22;
        r8 = r8.append(r0);	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r8 = r8.toString();	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r6[r7] = r8;	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r7 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        if (r7 != 0) goto L_0x0173;
    L_0x016a:
        r0 = r19;
        r1 = r1.update(r4, r0, r5, r6);	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
    L_0x0170:
        r17 = r1;
        goto L_0x00fa;
    L_0x0173:
        r1 = (android.database.sqlite.SQLiteDatabase) r1;	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r0 = r19;
        r1 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r1, r4, r0, r5, r6);	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        goto L_0x0170;
    L_0x017c:
        r4 = "message";
        r5 = "appid=? AND type=?";
        r6 = 2;
        r6 = new java.lang.String[r6];	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r7 = 0;
        r8 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r8.<init>();	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r9 = "";
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r0 = r22;
        r8 = r8.append(r0);	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r8 = r8.toString();	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r6[r7] = r8;	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r7 = 1;
        r8 = java.lang.String.valueOf(r21);	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r6[r7] = r8;	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r7 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        if (r7 != 0) goto L_0x01b0;
    L_0x01a6:
        r0 = r19;
        r1 = r1.update(r4, r0, r5, r6);	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
    L_0x01ac:
        r17 = r1;
        goto L_0x00fa;
    L_0x01b0:
        r1 = (android.database.sqlite.SQLiteDatabase) r1;	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        r0 = r19;
        r1 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r1, r4, r0, r5, r6);	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        goto L_0x01ac;
    L_0x01b9:
        if (r17 <= 0) goto L_0x01d8;
    L_0x01bb:
        r1 = r3.size();	 Catch:{ Exception -> 0x0076, all -> 0x01d3 }
        goto L_0x0100;
    L_0x01c1:
        r1 = move-exception;
        r2 = r18;
    L_0x01c4:
        if (r2 == 0) goto L_0x01cf;
    L_0x01c6:
        r3 = r2.isClosed();	 Catch:{ all -> 0x00a7 }
        if (r3 != 0) goto L_0x01cf;
    L_0x01cc:
        r2.close();	 Catch:{ all -> 0x00a7 }
    L_0x01cf:
        r20.mo13690c();	 Catch:{ all -> 0x00a7 }
        throw r1;	 Catch:{ all -> 0x00a7 }
    L_0x01d3:
        r1 = move-exception;
        goto L_0x01c4;
    L_0x01d5:
        r1 = move-exception;
        r2 = r3;
        goto L_0x01c4;
    L_0x01d8:
        r1 = r17;
        goto L_0x0100;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p032d.C1355a.mo13680b(int, java.lang.String):int");
    }

    /* renamed from: b */
    public synchronized int mo13681b(ArrayList<String> arrayList) {
        int i;
        SQLiteDatabase b = mo13682b();
        if (b == null || arrayList == null) {
            mo13690c();
            i = -1;
        } else {
            try {
                i = arrayList.size();
                if (arrayList.size() > 0) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        String str = (String) it.next();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("status", Integer.valueOf(0));
                        String str2 = HexAttributes.HEX_ATTR_MESSAGE;
                        String str3 = "msgid=?";
                        String[] strArr = new String[]{str};
                        i = (!(b instanceof SQLiteDatabase) ? b.update(str2, contentValues, str3, strArr) : SQLiteInstrumentation.update((SQLiteDatabase) b, str2, contentValues, str3, strArr)) != 1 ? i - 1 : i;
                    }
                }
            } catch (Exception e) {
                i = new StringBuilder().append("error ");
                C1425a.m6444e("DatabaseManager", i.append(e.getMessage()).toString());
                i = -1;
                return i;
            } finally {
                mo13690c();
            }
        }
        return i;
    }

    /* renamed from: b */
    public synchronized SQLiteDatabase mo13682b() {
        if (this.f4781b.incrementAndGet() == 1) {
            this.f4782c = C1357b.m6160a(this.f4780a);
        }
        return this.f4782c;
    }

    /* JADX WARNING: Removed duplicated region for block: B:99:0x01ca  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007f A:{Catch:{ Exception -> 0x01c6, all -> 0x01c2 }} */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0186 A:{Catch:{ NameNotFoundException -> 0x0169 }} */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01b0 A:{SYNTHETIC, Splitter:B:87:0x01b0} */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01bb A:{Catch:{ NameNotFoundException -> 0x0169 }} */
    /* renamed from: b */
    public synchronized boolean mo13683b(java.lang.String r24) {
        /*
        r23 = this;
        monitor-enter(r23);
        r2 = r23.mo13682b();	 Catch:{ all -> 0x0122 }
        r19 = 0;
        r18 = 0;
        if (r2 != 0) goto L_0x0016;
    L_0x000b:
        r2 = 0;
        if (r18 == 0) goto L_0x0011;
    L_0x000e:
        r18.close();	 Catch:{ all -> 0x0122 }
    L_0x0011:
        r23.mo13690c();	 Catch:{ all -> 0x0122 }
    L_0x0014:
        monitor-exit(r23);
        return r2;
    L_0x0016:
        r3 = "subscribe";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0190 }
        r5 = 0;
        r6 = "host_channel";
        r4[r5] = r6;	 Catch:{ Exception -> 0x0190 }
        r5 = "appid=?";
        r6 = 1;
        r6 = new java.lang.String[r6];	 Catch:{ Exception -> 0x0190 }
        r7 = 0;
        r6[r7] = r24;	 Catch:{ Exception -> 0x0190 }
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0190 }
        if (r10 != 0) goto L_0x0125;
    L_0x002f:
        r18 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x0190 }
    L_0x0033:
        r20 = "";
        r3 = r18.moveToNext();	 Catch:{ Exception -> 0x0190 }
        if (r3 == 0) goto L_0x0042;
    L_0x003b:
        r3 = 0;
        r0 = r18;
        r20 = r0.getString(r3);	 Catch:{ Exception -> 0x0190 }
    L_0x0042:
        r3 = android.text.TextUtils.isEmpty(r20);	 Catch:{ Exception -> 0x0190 }
        if (r3 == 0) goto L_0x01cc;
    L_0x0048:
        r18.close();	 Catch:{ Exception -> 0x0190 }
        r3 = "weak_subscribe";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0190 }
        r5 = 0;
        r6 = "host_channel";
        r4[r5] = r6;	 Catch:{ Exception -> 0x0190 }
        r5 = "appid=?";
        r6 = 1;
        r6 = new java.lang.String[r6];	 Catch:{ Exception -> 0x0190 }
        r7 = 0;
        r6[r7] = r24;	 Catch:{ Exception -> 0x0190 }
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0190 }
        if (r10 != 0) goto L_0x0138;
    L_0x0065:
        r18 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x0190 }
    L_0x0069:
        r3 = r18.moveToNext();	 Catch:{ Exception -> 0x0190 }
        if (r3 == 0) goto L_0x01cc;
    L_0x006f:
        r3 = 0;
        r0 = r18;
        r3 = r0.getString(r3);	 Catch:{ Exception -> 0x0190 }
        r4 = r3;
        r3 = r18;
    L_0x0079:
        r4 = android.text.TextUtils.isEmpty(r4);	 Catch:{ Exception -> 0x01c6, all -> 0x01c2 }
        if (r4 != 0) goto L_0x01ca;
    L_0x007f:
        r4 = 0;
        r4 = r3.getString(r4);	 Catch:{ Exception -> 0x01c6, all -> 0x01c2 }
        r5 = ":";
        r21 = r4.split(r5);	 Catch:{ Exception -> 0x01c6, all -> 0x01c2 }
        r0 = r21;
        r0 = r0.length;	 Catch:{ Exception -> 0x01c6, all -> 0x01c2 }
        r22 = r0;
        r4 = 0;
        r20 = r4;
        r18 = r3;
    L_0x0094:
        r0 = r20;
        r1 = r22;
        if (r0 >= r1) goto L_0x0182;
    L_0x009a:
        r7 = r21[r20];	 Catch:{ Exception -> 0x0190 }
        r18.close();	 Catch:{ Exception -> 0x0190 }
        r3 = "register";
        r4 = 2;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0190 }
        r5 = 0;
        r6 = "pkg_name";
        r4[r5] = r6;	 Catch:{ Exception -> 0x0190 }
        r5 = 1;
        r6 = "msg_switch";
        r4[r5] = r6;	 Catch:{ Exception -> 0x0190 }
        r5 = "channel=?";
        r6 = 1;
        r6 = new java.lang.String[r6];	 Catch:{ Exception -> 0x0190 }
        r8 = 0;
        r6[r8] = r7;	 Catch:{ Exception -> 0x0190 }
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0190 }
        if (r10 != 0) goto L_0x014b;
    L_0x00bd:
        r18 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x0190 }
    L_0x00c1:
        r3 = r18.moveToNext();	 Catch:{ Exception -> 0x0190 }
        if (r3 == 0) goto L_0x017c;
    L_0x00c7:
        r3 = "pkg_name";
        r0 = r18;
        r3 = r0.getColumnIndex(r3);	 Catch:{ Exception -> 0x0190 }
        r0 = r18;
        r3 = r0.getString(r3);	 Catch:{ Exception -> 0x0190 }
        r4 = android.text.TextUtils.isEmpty(r3);	 Catch:{ Exception -> 0x0190 }
        if (r4 != 0) goto L_0x017c;
    L_0x00db:
        r4 = "com.baidu.searchbox";
        r4 = r3.startsWith(r4);	 Catch:{ Exception -> 0x0190 }
        if (r4 != 0) goto L_0x00eb;
    L_0x00e3:
        r4 = "com.baidu.voiceassistant";
        r4 = r3.startsWith(r4);	 Catch:{ Exception -> 0x0190 }
        if (r4 == 0) goto L_0x017c;
    L_0x00eb:
        r0 = r23;
        r2 = r0.f4780a;	 Catch:{ Exception -> 0x0190 }
        r2 = r2.getPackageManager();	 Catch:{ Exception -> 0x0190 }
        r4 = 1;
        r2 = r2.getPackageInfo(r3, r4);	 Catch:{ NameNotFoundException -> 0x0169 }
        r2 = r2.versionCode;	 Catch:{ NameNotFoundException -> 0x0169 }
        r3 = 16787720; // 0x1002908 float:2.3539325E-38 double:8.2942357E-317;
        if (r2 <= r3) goto L_0x015e;
    L_0x00ff:
        r3 = "DatabaseManager";
        r4 = new java.lang.StringBuilder;	 Catch:{ NameNotFoundException -> 0x0169 }
        r4.<init>();	 Catch:{ NameNotFoundException -> 0x0169 }
        r5 = " lightapp msg blocked by searchbox ";
        r4 = r4.append(r5);	 Catch:{ NameNotFoundException -> 0x0169 }
        r2 = r4.append(r2);	 Catch:{ NameNotFoundException -> 0x0169 }
        r2 = r2.toString();	 Catch:{ NameNotFoundException -> 0x0169 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r3, r2);	 Catch:{ NameNotFoundException -> 0x0169 }
        r2 = 1;
        if (r18 == 0) goto L_0x011d;
    L_0x011a:
        r18.close();	 Catch:{ all -> 0x0122 }
    L_0x011d:
        r23.mo13690c();	 Catch:{ all -> 0x0122 }
        goto L_0x0014;
    L_0x0122:
        r2 = move-exception;
        monitor-exit(r23);
        throw r2;
    L_0x0125:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0190 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r18 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x0190 }
        goto L_0x0033;
    L_0x0138:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0190 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r18 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x0190 }
        goto L_0x0069;
    L_0x014b:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0190 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r18 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x0190 }
        goto L_0x00c1;
    L_0x015e:
        r2 = 0;
        if (r18 == 0) goto L_0x0164;
    L_0x0161:
        r18.close();	 Catch:{ all -> 0x0122 }
    L_0x0164:
        r23.mo13690c();	 Catch:{ all -> 0x0122 }
        goto L_0x0014;
    L_0x0169:
        r2 = move-exception;
        r2 = "DatabaseManager";
        r3 = " searchbox not found ";
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r2, r3);	 Catch:{ Exception -> 0x0190 }
        r2 = 0;
        if (r18 == 0) goto L_0x0177;
    L_0x0174:
        r18.close();	 Catch:{ all -> 0x0122 }
    L_0x0177:
        r23.mo13690c();	 Catch:{ all -> 0x0122 }
        goto L_0x0014;
    L_0x017c:
        r3 = r20 + 1;
        r20 = r3;
        goto L_0x0094;
    L_0x0182:
        r2 = r18;
    L_0x0184:
        if (r2 == 0) goto L_0x0189;
    L_0x0186:
        r2.close();	 Catch:{ all -> 0x0122 }
    L_0x0189:
        r23.mo13690c();	 Catch:{ all -> 0x0122 }
        r2 = r19;
        goto L_0x0014;
    L_0x0190:
        r2 = move-exception;
    L_0x0191:
        r3 = "DatabaseManager";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01b8 }
        r4.<init>();	 Catch:{ all -> 0x01b8 }
        r5 = "isLightAppSubscribedByApiKey ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x01b8 }
        r2 = r2.getMessage();	 Catch:{ all -> 0x01b8 }
        r2 = r4.append(r2);	 Catch:{ all -> 0x01b8 }
        r2 = r2.toString();	 Catch:{ all -> 0x01b8 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r3, r2);	 Catch:{ all -> 0x01b8 }
        r2 = 0;
        if (r18 == 0) goto L_0x01b3;
    L_0x01b0:
        r18.close();	 Catch:{ all -> 0x0122 }
    L_0x01b3:
        r23.mo13690c();	 Catch:{ all -> 0x0122 }
        goto L_0x0014;
    L_0x01b8:
        r2 = move-exception;
    L_0x01b9:
        if (r18 == 0) goto L_0x01be;
    L_0x01bb:
        r18.close();	 Catch:{ all -> 0x0122 }
    L_0x01be:
        r23.mo13690c();	 Catch:{ all -> 0x0122 }
        throw r2;	 Catch:{ all -> 0x0122 }
    L_0x01c2:
        r2 = move-exception;
        r18 = r3;
        goto L_0x01b9;
    L_0x01c6:
        r2 = move-exception;
        r18 = r3;
        goto L_0x0191;
    L_0x01ca:
        r2 = r3;
        goto L_0x0184;
    L_0x01cc:
        r4 = r20;
        r3 = r18;
        goto L_0x0079;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p032d.C1355a.mo13683b(java.lang.String):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x00c2 A:{Catch:{ Exception -> 0x0130, all -> 0x011e }} */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0124 A:{Catch:{ Exception -> 0x0130, all -> 0x011e }} */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x011e A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:6:0x000b, PHI: r17 , Catch:{ Exception -> 0x0130, all -> 0x011e }} */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0124 A:{Catch:{ Exception -> 0x0130, all -> 0x011e }} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:59:0x00f4, code skipped:
            r1 = e;
     */
    /* JADX WARNING: Missing block: B:60:0x00f5, code skipped:
            r2 = r17;
     */
    /* JADX WARNING: Missing block: B:68:0x011e, code skipped:
            r1 = th;
     */
    /* renamed from: b */
    public synchronized boolean mo13684b(java.lang.String r20, int r21) {
        /*
        r19 = this;
        monitor-enter(r19);
        r18 = 0;
        r1 = r19.mo13682b();	 Catch:{ all -> 0x00c7 }
        r17 = 0;
        if (r1 == 0) goto L_0x0011;
    L_0x000b:
        r2 = android.text.TextUtils.isEmpty(r20);	 Catch:{ Exception -> 0x00f4, all -> 0x011e }
        if (r2 == 0) goto L_0x001c;
    L_0x0011:
        r1 = 0;
        r19.mo13690c();	 Catch:{ all -> 0x00c7 }
        if (r17 == 0) goto L_0x001a;
    L_0x0017:
        r17.close();	 Catch:{ all -> 0x00c7 }
    L_0x001a:
        monitor-exit(r19);
        return r1;
    L_0x001c:
        r2 = "subscribe";
        r3 = 1;
        r3 = new java.lang.String[r3];	 Catch:{ Exception -> 0x00f4, all -> 0x011e }
        r4 = 0;
        r5 = "host_channel";
        r3[r4] = r5;	 Catch:{ Exception -> 0x00f4, all -> 0x011e }
        r4 = "appid=?";
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ Exception -> 0x00f4, all -> 0x011e }
        r6 = 0;
        r5[r6] = r20;	 Catch:{ Exception -> 0x00f4, all -> 0x011e }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00f4, all -> 0x011e }
        if (r9 != 0) goto L_0x00ca;
    L_0x0035:
        r17 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x00f4, all -> 0x011e }
    L_0x0039:
        r2 = r17.moveToNext();	 Catch:{ Exception -> 0x0130, all -> 0x011e }
        if (r2 == 0) goto L_0x0144;
    L_0x003f:
        r2 = 0;
        r0 = r17;
        r2 = r0.getString(r2);	 Catch:{ Exception -> 0x0130, all -> 0x011e }
        if (r2 == 0) goto L_0x0144;
    L_0x0048:
        r3 = r2.length();	 Catch:{ Exception -> 0x0130, all -> 0x011e }
        if (r3 <= 0) goto L_0x0144;
    L_0x004e:
        r3 = ":";
        r2 = r2.split(r3);	 Catch:{ Exception -> 0x0130, all -> 0x011e }
        r3 = r2.length;	 Catch:{ Exception -> 0x0130, all -> 0x011e }
        if (r3 <= 0) goto L_0x0144;
    L_0x0057:
        r3 = 0;
        r6 = r2[r3];	 Catch:{ Exception -> 0x0130, all -> 0x011e }
        r17.close();	 Catch:{ Exception -> 0x0130, all -> 0x011e }
        r2 = "register";
        r3 = 1;
        r3 = new java.lang.String[r3];	 Catch:{ Exception -> 0x0130, all -> 0x011e }
        r4 = 0;
        r5 = "pkg_name";
        r3[r4] = r5;	 Catch:{ Exception -> 0x0130, all -> 0x011e }
        r4 = "channel=?";
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ Exception -> 0x0130, all -> 0x011e }
        r7 = 0;
        r5[r7] = r6;	 Catch:{ Exception -> 0x0130, all -> 0x011e }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0130, all -> 0x011e }
        if (r9 != 0) goto L_0x00dc;
    L_0x0076:
        r9 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0130, all -> 0x011e }
    L_0x007a:
        r2 = r9.moveToNext();	 Catch:{ Exception -> 0x0134, all -> 0x0128 }
        if (r2 == 0) goto L_0x013e;
    L_0x0080:
        r2 = 0;
        r2 = r9.getString(r2);	 Catch:{ Exception -> 0x0134, all -> 0x0128 }
        r9.close();	 Catch:{ Exception -> 0x0134, all -> 0x0128 }
        r3 = 2;
        r5 = new java.lang.String[r3];	 Catch:{ Exception -> 0x0134, all -> 0x0128 }
        r3 = 0;
        r5[r3] = r20;	 Catch:{ Exception -> 0x0134, all -> 0x0128 }
        r3 = 1;
        r5[r3] = r2;	 Catch:{ Exception -> 0x0134, all -> 0x0128 }
        r2 = "blacklist";
        r3 = 1;
        r3 = new java.lang.String[r3];	 Catch:{ Exception -> 0x0134, all -> 0x0128 }
        r4 = 0;
        r6 = "type";
        r3[r4] = r6;	 Catch:{ Exception -> 0x0134, all -> 0x0128 }
        r4 = "app_id=? AND pkg_name=? ";
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r10 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0134, all -> 0x0128 }
        if (r10 != 0) goto L_0x00ed;
    L_0x00a5:
        r2 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0134, all -> 0x0128 }
    L_0x00a9:
        r1 = r2.moveToNext();	 Catch:{ Exception -> 0x0137 }
        if (r1 == 0) goto L_0x0139;
    L_0x00af:
        r1 = 0;
        r1 = r2.getInt(r1);	 Catch:{ Exception -> 0x0137 }
        r0 = r21;
        if (r1 == r0) goto L_0x00ba;
    L_0x00b8:
        if (r1 != 0) goto L_0x0139;
    L_0x00ba:
        r1 = 1;
        r17 = r2;
    L_0x00bd:
        r19.mo13690c();	 Catch:{ all -> 0x00c7 }
        if (r17 == 0) goto L_0x001a;
    L_0x00c2:
        r17.close();	 Catch:{ all -> 0x00c7 }
        goto L_0x001a;
    L_0x00c7:
        r1 = move-exception;
        monitor-exit(r19);
        throw r1;
    L_0x00ca:
        r0 = r1;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00f4, all -> 0x011e }
        r9 = r0;
        r10 = r2;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r9, r10, r11, r12, r13, r14, r15, r16);	 Catch:{ Exception -> 0x00f4, all -> 0x011e }
        goto L_0x0039;
    L_0x00dc:
        r0 = r1;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0130, all -> 0x011e }
        r9 = r0;
        r10 = r2;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r9 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r9, r10, r11, r12, r13, r14, r15, r16);	 Catch:{ Exception -> 0x0130, all -> 0x011e }
        goto L_0x007a;
    L_0x00ed:
        r1 = (android.database.sqlite.SQLiteDatabase) r1;	 Catch:{ Exception -> 0x0134, all -> 0x0128 }
        r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0134, all -> 0x0128 }
        goto L_0x00a9;
    L_0x00f4:
        r1 = move-exception;
        r2 = r17;
    L_0x00f7:
        r3 = "DatabaseManager";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x012c }
        r4.<init>();	 Catch:{ all -> 0x012c }
        r5 = "error ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x012c }
        r1 = r1.getMessage();	 Catch:{ all -> 0x012c }
        r1 = r4.append(r1);	 Catch:{ all -> 0x012c }
        r1 = r1.toString();	 Catch:{ all -> 0x012c }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r3, r1);	 Catch:{ all -> 0x012c }
        r1 = 0;
        r19.mo13690c();	 Catch:{ all -> 0x00c7 }
        if (r2 == 0) goto L_0x001a;
    L_0x0119:
        r2.close();	 Catch:{ all -> 0x00c7 }
        goto L_0x001a;
    L_0x011e:
        r1 = move-exception;
    L_0x011f:
        r19.mo13690c();	 Catch:{ all -> 0x00c7 }
        if (r17 == 0) goto L_0x0127;
    L_0x0124:
        r17.close();	 Catch:{ all -> 0x00c7 }
    L_0x0127:
        throw r1;	 Catch:{ all -> 0x00c7 }
    L_0x0128:
        r1 = move-exception;
        r17 = r9;
        goto L_0x011f;
    L_0x012c:
        r1 = move-exception;
        r17 = r2;
        goto L_0x011f;
    L_0x0130:
        r1 = move-exception;
        r2 = r17;
        goto L_0x00f7;
    L_0x0134:
        r1 = move-exception;
        r2 = r9;
        goto L_0x00f7;
    L_0x0137:
        r1 = move-exception;
        goto L_0x00f7;
    L_0x0139:
        r17 = r2;
        r1 = r18;
        goto L_0x00bd;
    L_0x013e:
        r17 = r9;
        r1 = r18;
        goto L_0x00bd;
    L_0x0144:
        r1 = r18;
        goto L_0x00bd;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p032d.C1355a.mo13684b(java.lang.String, int):boolean");
    }

    /* renamed from: b */
    public synchronized boolean mo13685b(String str, String str2) {
        boolean z = false;
        synchronized (this) {
            SQLiteDatabase b = mo13682b();
            if (b != null) {
                try {
                    if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
                        C1425a.m6442c("DatabaseManager", "register pkgName or channel can not be null");
                    } else {
                        long insert;
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("pkg_name", str2);
                        contentValues.put("channel", str);
                        contentValues.put("msg_count", Integer.valueOf(0));
                        contentValues.put("msg_switch", Integer.valueOf(0));
                        contentValues.put("reg_time", Long.valueOf(System.currentTimeMillis()));
                        if (b != null) {
                            String str3 = JiceArgs.EVENT_REGISTER;
                            insert = !(b instanceof SQLiteDatabase) ? b.insert(str3, null, contentValues) : SQLiteInstrumentation.insert(b, str3, null, contentValues);
                        } else {
                            insert = -1;
                        }
                        boolean z2 = insert > 0;
                        mo13690c();
                        z = z2;
                    }
                } catch (Exception e) {
                    C1425a.m6444e("DatabaseManager", "error " + e.getMessage());
                    return z;
                } finally {
                    mo13690c();
                }
            }
        }
        return z;
    }

    /* renamed from: b */
    public synchronized boolean mo13686b(String str, String str2, int i) {
        boolean z = false;
        synchronized (this) {
            SQLiteDatabase b = mo13682b();
            try {
                if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                    C1425a.m6442c("DatabaseManager", "addBlackList appid or pkgName can not be null");
                    mo13690c();
                } else {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("app_id", str);
                    contentValues.put("pkg_name", str2);
                    contentValues.put("type", Integer.valueOf(i));
                    String str3 = "blacklist";
                    boolean z2 = (!(b instanceof SQLiteDatabase) ? b.insert(str3, null, contentValues) : SQLiteInstrumentation.insert((SQLiteDatabase) b, str3, null, contentValues)) > 0;
                    mo13690c();
                    z = z2;
                }
            } catch (Exception e) {
                C1425a.m6444e("DatabaseManager", "error " + e.getMessage());
                mo13690c();
            } catch (Throwable th) {
                mo13690c();
            }
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:59:0x0124 A:{Catch:{ Exception -> 0x00f5, all -> 0x011f }} */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0124 A:{Catch:{ Exception -> 0x00f5, all -> 0x011f }} */
    /* renamed from: b */
    public synchronized boolean mo13687b(java.lang.String r21, java.lang.String r22, java.lang.String r23, java.lang.String r24) {
        /*
        r20 = this;
        monitor-enter(r20);
        r2 = r20.mo13682b();	 Catch:{ all -> 0x0097 }
        if (r2 != 0) goto L_0x000a;
    L_0x0007:
        r2 = 0;
    L_0x0008:
        monitor-exit(r20);
        return r2;
    L_0x000a:
        r3 = android.text.TextUtils.isEmpty(r22);	 Catch:{ all -> 0x0097 }
        if (r3 != 0) goto L_0x0016;
    L_0x0010:
        r3 = android.text.TextUtils.isEmpty(r21);	 Catch:{ all -> 0x0097 }
        if (r3 == 0) goto L_0x001f;
    L_0x0016:
        r2 = "DatabaseManager";
        r3 = "register pkgName or channel can not be null";
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r2, r3);	 Catch:{ all -> 0x0097 }
        r2 = 0;
        goto L_0x0008;
    L_0x001f:
        r18 = 0;
        r4 = -1;
        r19 = new android.content.ContentValues;	 Catch:{ Exception -> 0x00f5, all -> 0x011f }
        r19.<init>();	 Catch:{ Exception -> 0x00f5, all -> 0x011f }
        r3 = "pkg_name";
        r0 = r19;
        r1 = r22;
        r0.put(r3, r1);	 Catch:{ Exception -> 0x00f5, all -> 0x011f }
        r3 = "channel";
        r0 = r19;
        r1 = r21;
        r0.put(r3, r1);	 Catch:{ Exception -> 0x00f5, all -> 0x011f }
        r3 = "host_name";
        r0 = r19;
        r1 = r23;
        r0.put(r3, r1);	 Catch:{ Exception -> 0x00f5, all -> 0x011f }
        r3 = "host_version";
        r0 = r19;
        r1 = r24;
        r0.put(r3, r1);	 Catch:{ Exception -> 0x00f5, all -> 0x011f }
        if (r2 == 0) goto L_0x0133;
    L_0x004e:
        r3 = "register";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x00f5, all -> 0x011f }
        r5 = 0;
        r6 = "pkg_name";
        r4[r5] = r6;	 Catch:{ Exception -> 0x00f5, all -> 0x011f }
        r5 = "pkg_name=?";
        r6 = 1;
        r6 = new java.lang.String[r6];	 Catch:{ Exception -> 0x00f5, all -> 0x011f }
        r7 = 0;
        r6[r7] = r22;	 Catch:{ Exception -> 0x00f5, all -> 0x011f }
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00f5, all -> 0x011f }
        if (r10 != 0) goto L_0x009a;
    L_0x0067:
        r4 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00f5, all -> 0x011f }
    L_0x006b:
        r3 = r4.moveToNext();	 Catch:{ Exception -> 0x0130, all -> 0x012b }
        if (r3 == 0) goto L_0x00b5;
    L_0x0071:
        r3 = "register";
        r5 = "pkg_name=?";
        r6 = 1;
        r6 = new java.lang.String[r6];	 Catch:{ Exception -> 0x0130, all -> 0x012b }
        r7 = 0;
        r6[r7] = r22;	 Catch:{ Exception -> 0x0130, all -> 0x012b }
        r7 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0130, all -> 0x012b }
        if (r7 != 0) goto L_0x00ac;
    L_0x007f:
        r0 = r19;
        r2 = r2.update(r3, r0, r5, r6);	 Catch:{ Exception -> 0x0130, all -> 0x012b }
    L_0x0085:
        r2 = (long) r2;
    L_0x0086:
        r6 = 0;
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 <= 0) goto L_0x00f3;
    L_0x008c:
        r2 = 1;
    L_0x008d:
        if (r4 == 0) goto L_0x0092;
    L_0x008f:
        r4.close();	 Catch:{ all -> 0x0097 }
    L_0x0092:
        r20.mo13690c();	 Catch:{ all -> 0x0097 }
        goto L_0x0008;
    L_0x0097:
        r2 = move-exception;
        monitor-exit(r20);
        throw r2;
    L_0x009a:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00f5, all -> 0x011f }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x00f5, all -> 0x011f }
        goto L_0x006b;
    L_0x00ac:
        r2 = (android.database.sqlite.SQLiteDatabase) r2;	 Catch:{ Exception -> 0x0130, all -> 0x012b }
        r0 = r19;
        r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r2, r3, r0, r5, r6);	 Catch:{ Exception -> 0x0130, all -> 0x012b }
        goto L_0x0085;
    L_0x00b5:
        r3 = "msg_count";
        r5 = 0;
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ Exception -> 0x0130, all -> 0x012b }
        r0 = r19;
        r0.put(r3, r5);	 Catch:{ Exception -> 0x0130, all -> 0x012b }
        r3 = "msg_switch";
        r5 = 0;
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ Exception -> 0x0130, all -> 0x012b }
        r0 = r19;
        r0.put(r3, r5);	 Catch:{ Exception -> 0x0130, all -> 0x012b }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0130, all -> 0x012b }
        r3 = "reg_time";
        r5 = java.lang.Long.valueOf(r6);	 Catch:{ Exception -> 0x0130, all -> 0x012b }
        r0 = r19;
        r0.put(r3, r5);	 Catch:{ Exception -> 0x0130, all -> 0x012b }
        r3 = "register";
        r5 = 0;
        r6 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0130, all -> 0x012b }
        if (r6 != 0) goto L_0x00ea;
    L_0x00e3:
        r0 = r19;
        r2 = r2.insert(r3, r5, r0);	 Catch:{ Exception -> 0x0130, all -> 0x012b }
        goto L_0x0086;
    L_0x00ea:
        r2 = (android.database.sqlite.SQLiteDatabase) r2;	 Catch:{ Exception -> 0x0130, all -> 0x012b }
        r0 = r19;
        r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.insert(r2, r3, r5, r0);	 Catch:{ Exception -> 0x0130, all -> 0x012b }
        goto L_0x0086;
    L_0x00f3:
        r2 = 0;
        goto L_0x008d;
    L_0x00f5:
        r2 = move-exception;
        r3 = r18;
    L_0x00f8:
        r4 = "DatabaseManager";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x012d }
        r5.<init>();	 Catch:{ all -> 0x012d }
        r6 = "error ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x012d }
        r2 = r2.getMessage();	 Catch:{ all -> 0x012d }
        r2 = r5.append(r2);	 Catch:{ all -> 0x012d }
        r2 = r2.toString();	 Catch:{ all -> 0x012d }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r4, r2);	 Catch:{ all -> 0x012d }
        r2 = 0;
        if (r3 == 0) goto L_0x011a;
    L_0x0117:
        r3.close();	 Catch:{ all -> 0x0097 }
    L_0x011a:
        r20.mo13690c();	 Catch:{ all -> 0x0097 }
        goto L_0x0008;
    L_0x011f:
        r2 = move-exception;
        r4 = r18;
    L_0x0122:
        if (r4 == 0) goto L_0x0127;
    L_0x0124:
        r4.close();	 Catch:{ all -> 0x0097 }
    L_0x0127:
        r20.mo13690c();	 Catch:{ all -> 0x0097 }
        throw r2;	 Catch:{ all -> 0x0097 }
    L_0x012b:
        r2 = move-exception;
        goto L_0x0122;
    L_0x012d:
        r2 = move-exception;
        r4 = r3;
        goto L_0x0122;
    L_0x0130:
        r2 = move-exception;
        r3 = r4;
        goto L_0x00f8;
    L_0x0133:
        r2 = r4;
        r4 = r18;
        goto L_0x0086;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p032d.C1355a.mo13687b(java.lang.String, java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    /* renamed from: c */
    public synchronized int mo13688c(int i, String str) {
        int delete;
        String[] strArr = null;
        synchronized (this) {
            SQLiteDatabase b = mo13682b();
            if (b != null) {
                try {
                    if (!TextUtils.isEmpty(str)) {
                        String str2;
                        if (str.equals("0")) {
                            if (i != 0) {
                                str2 = "type = ?";
                                strArr = new String[]{String.valueOf(i)};
                            } else {
                                str2 = null;
                            }
                        } else if (i == 0) {
                            str2 = "appid = ?";
                            strArr = new String[]{"" + str};
                        } else {
                            str2 = "appid= ? AND type = ?";
                            strArr = new String[]{"" + str, String.valueOf(i)};
                        }
                        String str3 = HexAttributes.HEX_ATTR_MESSAGE;
                        delete = !(b instanceof SQLiteDatabase) ? b.delete(str3, str2, strArr) : SQLiteInstrumentation.delete(b, str3, str2, strArr);
                    }
                } catch (Exception e) {
                    delete = "error " + e.getMessage();
                    C1425a.m6444e("DatabaseManager", delete);
                    delete = -1;
                    return delete;
                } finally {
                    mo13690c();
                }
            }
            mo13690c();
            delete = -1;
        }
        return delete;
    }

    /* renamed from: c */
    public synchronized int mo13689c(ArrayList<String> arrayList) {
        int i;
        int i2 = 0;
        synchronized (this) {
            SQLiteDatabase b = mo13682b();
            i = -1;
            if (b == null || arrayList == null) {
                mo13690c();
            } else {
                try {
                    if (arrayList.size() > 0) {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            String str = (String) it.next();
                            String str2 = HexAttributes.HEX_ATTR_MESSAGE;
                            String str3 = "msgid=?";
                            String[] strArr = new String[]{str};
                            i2 = 1 == (!(b instanceof SQLiteDatabase) ? b.delete(str2, str3, strArr) : SQLiteInstrumentation.delete((SQLiteDatabase) b, str2, str3, strArr)) ? i2 + 1 : i2;
                        }
                    }
                    i = i2;
                } catch (Exception e) {
                    Exception exception = e;
                    i = 0;
                    C1425a.m6444e("DatabaseManager", "error " + exception.getMessage());
                    return i;
                } finally {
                    mo13690c();
                }
            }
        }
        return i;
    }

    /* renamed from: c */
    public synchronized void mo13690c() {
        try {
            this.f4783d.lock();
            if (this.f4781b.decrementAndGet() == 0 && this.f4782c != null) {
                this.f4782c.close();
            }
            this.f4783d.unlock();
        } catch (Exception e) {
            C1425a.m6444e("DatabaseManager", "error : " + e.getMessage());
            this.f4783d.unlock();
        } catch (Throwable th) {
            this.f4783d.unlock();
        }
        return;
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x007e A:{Catch:{ Exception -> 0x0051, all -> 0x0078 }} */
    /* renamed from: c */
    public synchronized boolean mo13691c(java.lang.String r13) {
        /*
        r12 = this;
        r8 = 1;
        r10 = 0;
        r9 = 0;
        monitor-enter(r12);
        r0 = r12.mo13682b();	 Catch:{ all -> 0x004e }
        if (r0 != 0) goto L_0x000c;
    L_0x000a:
        monitor-exit(r12);
        return r9;
    L_0x000c:
        r1 = "register";
        r2 = 1;
        r2 = new java.lang.String[r2];	 Catch:{ Exception -> 0x0051, all -> 0x0078 }
        r3 = 0;
        r4 = "pkg_name";
        r2[r3] = r4;	 Catch:{ Exception -> 0x0051, all -> 0x0078 }
        r3 = "pkg_name=?";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0051, all -> 0x0078 }
        r5 = 0;
        r4[r5] = r13;	 Catch:{ Exception -> 0x0051, all -> 0x0078 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r11 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0051, all -> 0x0078 }
        if (r11 != 0) goto L_0x003c;
    L_0x0025:
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0051, all -> 0x0078 }
    L_0x0029:
        if (r1 == 0) goto L_0x0045;
    L_0x002b:
        r0 = r1.getCount();	 Catch:{ Exception -> 0x0085 }
        if (r0 <= 0) goto L_0x0043;
    L_0x0031:
        r0 = r8;
    L_0x0032:
        r12.mo13690c();	 Catch:{ all -> 0x004e }
        if (r1 == 0) goto L_0x003a;
    L_0x0037:
        r1.close();	 Catch:{ all -> 0x004e }
    L_0x003a:
        r9 = r0;
        goto L_0x000a;
    L_0x003c:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0051, all -> 0x0078 }
        r1 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0051, all -> 0x0078 }
        goto L_0x0029;
    L_0x0043:
        r0 = r9;
        goto L_0x0032;
    L_0x0045:
        r12.mo13690c();	 Catch:{ all -> 0x004e }
        if (r1 == 0) goto L_0x000a;
    L_0x004a:
        r1.close();	 Catch:{ all -> 0x004e }
        goto L_0x000a;
    L_0x004e:
        r0 = move-exception;
        monitor-exit(r12);
        throw r0;
    L_0x0051:
        r0 = move-exception;
        r1 = r10;
    L_0x0053:
        r2 = "DatabaseManager";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0082 }
        r3.<init>();	 Catch:{ all -> 0x0082 }
        r4 = "error ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0082 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0082 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0082 }
        r0 = r0.toString();	 Catch:{ all -> 0x0082 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r2, r0);	 Catch:{ all -> 0x0082 }
        r12.mo13690c();	 Catch:{ all -> 0x004e }
        if (r1 == 0) goto L_0x000a;
    L_0x0074:
        r1.close();	 Catch:{ all -> 0x004e }
        goto L_0x000a;
    L_0x0078:
        r0 = move-exception;
    L_0x0079:
        r12.mo13690c();	 Catch:{ all -> 0x004e }
        if (r10 == 0) goto L_0x0081;
    L_0x007e:
        r10.close();	 Catch:{ all -> 0x004e }
    L_0x0081:
        throw r0;	 Catch:{ all -> 0x004e }
    L_0x0082:
        r0 = move-exception;
        r10 = r1;
        goto L_0x0079;
    L_0x0085:
        r0 = move-exception;
        goto L_0x0053;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p032d.C1355a.mo13691c(java.lang.String):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x007e A:{Catch:{ Exception -> 0x0054, all -> 0x007b }} */
    /* renamed from: c */
    public synchronized boolean mo13692c(java.lang.String r13, java.lang.String r14) {
        /*
        r12 = this;
        r8 = 1;
        r10 = 0;
        r9 = 0;
        monitor-enter(r12);
        r0 = r12.mo13682b();	 Catch:{ all -> 0x0051 }
        if (r0 != 0) goto L_0x000c;
    L_0x000a:
        monitor-exit(r12);
        return r9;
    L_0x000c:
        r1 = "blacklist";
        r2 = 1;
        r2 = new java.lang.String[r2];	 Catch:{ Exception -> 0x0054, all -> 0x007b }
        r3 = 0;
        r4 = "app_id";
        r2[r3] = r4;	 Catch:{ Exception -> 0x0054, all -> 0x007b }
        r3 = "app_id=? AND pkg_name=?";
        r4 = 2;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0054, all -> 0x007b }
        r5 = 0;
        r4[r5] = r13;	 Catch:{ Exception -> 0x0054, all -> 0x007b }
        r5 = 1;
        r4[r5] = r14;	 Catch:{ Exception -> 0x0054, all -> 0x007b }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r11 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0054, all -> 0x007b }
        if (r11 != 0) goto L_0x003f;
    L_0x0028:
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0054, all -> 0x007b }
    L_0x002c:
        if (r1 == 0) goto L_0x0048;
    L_0x002e:
        r0 = r1.getCount();	 Catch:{ Exception -> 0x0088 }
        if (r0 <= 0) goto L_0x0046;
    L_0x0034:
        r0 = r8;
    L_0x0035:
        if (r1 == 0) goto L_0x003a;
    L_0x0037:
        r1.close();	 Catch:{ all -> 0x0051 }
    L_0x003a:
        r12.mo13690c();	 Catch:{ all -> 0x0051 }
        r9 = r0;
        goto L_0x000a;
    L_0x003f:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0054, all -> 0x007b }
        r1 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0054, all -> 0x007b }
        goto L_0x002c;
    L_0x0046:
        r0 = r9;
        goto L_0x0035;
    L_0x0048:
        if (r1 == 0) goto L_0x004d;
    L_0x004a:
        r1.close();	 Catch:{ all -> 0x0051 }
    L_0x004d:
        r12.mo13690c();	 Catch:{ all -> 0x0051 }
        goto L_0x000a;
    L_0x0051:
        r0 = move-exception;
        monitor-exit(r12);
        throw r0;
    L_0x0054:
        r0 = move-exception;
        r1 = r10;
    L_0x0056:
        r2 = "DatabaseManager";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0085 }
        r3.<init>();	 Catch:{ all -> 0x0085 }
        r4 = "error ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0085 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0085 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0085 }
        r0 = r0.toString();	 Catch:{ all -> 0x0085 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r2, r0);	 Catch:{ all -> 0x0085 }
        if (r1 == 0) goto L_0x0077;
    L_0x0074:
        r1.close();	 Catch:{ all -> 0x0051 }
    L_0x0077:
        r12.mo13690c();	 Catch:{ all -> 0x0051 }
        goto L_0x000a;
    L_0x007b:
        r0 = move-exception;
    L_0x007c:
        if (r10 == 0) goto L_0x0081;
    L_0x007e:
        r10.close();	 Catch:{ all -> 0x0051 }
    L_0x0081:
        r12.mo13690c();	 Catch:{ all -> 0x0051 }
        throw r0;	 Catch:{ all -> 0x0051 }
    L_0x0085:
        r0 = move-exception;
        r10 = r1;
        goto L_0x007c;
    L_0x0088:
        r0 = move-exception;
        goto L_0x0056;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p032d.C1355a.mo13692c(java.lang.String, java.lang.String):boolean");
    }

    /* renamed from: d */
    public synchronized void mo13693d() {
        SQLiteDatabase b = mo13682b();
        if (b != null) {
            Cursor cursor = null;
            try {
                String str;
                String[] strArr;
                String str2;
                String str3 = HexAttributes.HEX_ATTR_MESSAGE;
                String[] strArr2 = new String[]{"count(*)"};
                cursor = !(b instanceof SQLiteDatabase) ? b.query(str3, strArr2, null, null, null, null, null) : SQLiteInstrumentation.query(b, str3, strArr2, null, null, null, null, null);
                if (cursor != null && cursor.moveToNext() && cursor.getInt(0) > ActivityTrace.MAX_TRACES) {
                    cursor.close();
                    str3 = HexAttributes.HEX_ATTR_MESSAGE;
                    strArr2 = new String[]{"appid", "count(*)"};
                    String str4 = "appid";
                    cursor = !(b instanceof SQLiteDatabase) ? b.query(str3, strArr2, null, null, str4, null, null) : SQLiteInstrumentation.query(b, str3, strArr2, null, null, str4, null, null);
                    Cursor cursor2 = null;
                    while (cursor.moveToNext()) {
                        str3 = HexAttributes.HEX_ATTR_MESSAGE;
                        strArr2 = new String[]{"time"};
                        str = "appid =? ";
                        strArr = new String[]{cursor.getString(0)};
                        String str5 = "time DESC";
                        cursor2 = !(b instanceof SQLiteDatabase) ? b.query(str3, strArr2, str, strArr, null, null, str5, null) : SQLiteInstrumentation.query(b, str3, strArr2, str, strArr, null, null, str5, null);
                        if (cursor2.moveToPosition(49)) {
                            str = HexAttributes.HEX_ATTR_MESSAGE;
                            String str6 = "appid=? AND time<?";
                            String[] strArr3 = new String[]{cursor.getString(0), cursor2.getString(0)};
                            if (b instanceof SQLiteDatabase) {
                                SQLiteInstrumentation.delete(b, str, str6, strArr3);
                            } else {
                                b.delete(str, str6, strArr3);
                            }
                        }
                    }
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                }
                cursor.close();
                str3 = "weak_subscribe";
                strArr2 = new String[]{"count(*)"};
                cursor = !(b instanceof SQLiteDatabase) ? b.query(str3, strArr2, null, null, null, null, null) : SQLiteInstrumentation.query(b, str3, strArr2, null, null, null, null, null);
                if (cursor != null && cursor.moveToNext() && cursor.getInt(0) > 1000) {
                    cursor.close();
                    str2 = "weak_subscribe";
                    str = "sub_time<?";
                    strArr = new String[]{(System.currentTimeMillis() - -813934592) + ""};
                    if (b instanceof SQLiteDatabase) {
                        SQLiteInstrumentation.delete(b, str2, str, strArr);
                    } else {
                        b.delete(str2, str, strArr);
                    }
                }
                str3 = "notification";
                str2 = "time_stamp<?";
                String[] strArr4 = new String[]{(System.currentTimeMillis() - 259200000) + ""};
                if (b instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.delete(b, str3, str2, strArr4);
                } else {
                    b.delete(str3, str2, strArr4);
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e) {
                C1425a.m6442c("DatabaseManager", "checkDB E: " + e);
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
            mo13690c();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ce A:{Catch:{ Exception -> 0x0093, all -> 0x00c8 }} */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ce A:{Catch:{ Exception -> 0x0093, all -> 0x00c8 }} */
    /* renamed from: d */
    public synchronized void mo13694d(java.util.ArrayList<java.lang.String> r22) {
        /*
        r21 = this;
        monitor-enter(r21);
        r2 = r21.mo13682b();	 Catch:{ all -> 0x00bb }
        if (r2 != 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r21);
        return;
    L_0x0009:
        r19 = 0;
        r3 = 0;
        r20 = r3;
    L_0x000e:
        r3 = r22.size();	 Catch:{ Exception -> 0x00d9, all -> 0x00d2 }
        r0 = r20;
        if (r0 >= r3) goto L_0x00be;
    L_0x0016:
        r0 = r22;
        r1 = r20;
        r3 = r0.get(r1);	 Catch:{ Exception -> 0x00d9, all -> 0x00d2 }
        r0 = r3;
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x00d9, all -> 0x00d2 }
        r18 = r0;
        r3 = "register";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x00d9, all -> 0x00d2 }
        r5 = 0;
        r6 = "msg_count";
        r4[r5] = r6;	 Catch:{ Exception -> 0x00d9, all -> 0x00d2 }
        r5 = "pkg_name=?";
        r6 = 1;
        r6 = new java.lang.String[r6];	 Catch:{ Exception -> 0x00d9, all -> 0x00d2 }
        r7 = 0;
        r6[r7] = r18;	 Catch:{ Exception -> 0x00d9, all -> 0x00d2 }
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00d9, all -> 0x00d2 }
        if (r10 != 0) goto L_0x0079;
    L_0x003c:
        r4 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00d9, all -> 0x00d2 }
    L_0x0040:
        r3 = 0;
        r5 = r4.moveToNext();	 Catch:{ Exception -> 0x0093, all -> 0x00c8 }
        if (r5 == 0) goto L_0x0051;
    L_0x0047:
        r3 = "msg_count";
        r3 = r4.getColumnIndex(r3);	 Catch:{ Exception -> 0x0093, all -> 0x00c8 }
        r3 = r4.getInt(r3);	 Catch:{ Exception -> 0x0093, all -> 0x00c8 }
    L_0x0051:
        r5 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0093, all -> 0x00c8 }
        r5.<init>();	 Catch:{ Exception -> 0x0093, all -> 0x00c8 }
        r6 = "msg_count";
        r3 = r3 + 1;
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Exception -> 0x0093, all -> 0x00c8 }
        r5.put(r6, r3);	 Catch:{ Exception -> 0x0093, all -> 0x00c8 }
        r6 = "register";
        r7 = "pkg_name=?";
        r3 = 1;
        r8 = new java.lang.String[r3];	 Catch:{ Exception -> 0x0093, all -> 0x00c8 }
        r3 = 0;
        r8[r3] = r18;	 Catch:{ Exception -> 0x0093, all -> 0x00c8 }
        r3 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0093, all -> 0x00c8 }
        if (r3 != 0) goto L_0x008b;
    L_0x006f:
        r2.update(r6, r5, r7, r8);	 Catch:{ Exception -> 0x0093, all -> 0x00c8 }
    L_0x0072:
        r3 = r20 + 1;
        r20 = r3;
        r19 = r4;
        goto L_0x000e;
    L_0x0079:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00d9, all -> 0x00d2 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x00d9, all -> 0x00d2 }
        goto L_0x0040;
    L_0x008b:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0093, all -> 0x00c8 }
        r3 = r0;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r3, r6, r5, r7, r8);	 Catch:{ Exception -> 0x0093, all -> 0x00c8 }
        goto L_0x0072;
    L_0x0093:
        r2 = move-exception;
        r3 = r4;
    L_0x0095:
        r4 = "DatabaseManager";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00d6 }
        r5.<init>();	 Catch:{ all -> 0x00d6 }
        r6 = "error ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x00d6 }
        r2 = r2.getMessage();	 Catch:{ all -> 0x00d6 }
        r2 = r5.append(r2);	 Catch:{ all -> 0x00d6 }
        r2 = r2.toString();	 Catch:{ all -> 0x00d6 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r4, r2);	 Catch:{ all -> 0x00d6 }
        r21.mo13690c();	 Catch:{ all -> 0x00bb }
        if (r3 == 0) goto L_0x0007;
    L_0x00b6:
        r3.close();	 Catch:{ all -> 0x00bb }
        goto L_0x0007;
    L_0x00bb:
        r2 = move-exception;
        monitor-exit(r21);
        throw r2;
    L_0x00be:
        r21.mo13690c();	 Catch:{ all -> 0x00bb }
        if (r19 == 0) goto L_0x0007;
    L_0x00c3:
        r19.close();	 Catch:{ all -> 0x00bb }
        goto L_0x0007;
    L_0x00c8:
        r2 = move-exception;
    L_0x00c9:
        r21.mo13690c();	 Catch:{ all -> 0x00bb }
        if (r4 == 0) goto L_0x00d1;
    L_0x00ce:
        r4.close();	 Catch:{ all -> 0x00bb }
    L_0x00d1:
        throw r2;	 Catch:{ all -> 0x00bb }
    L_0x00d2:
        r2 = move-exception;
        r4 = r19;
        goto L_0x00c9;
    L_0x00d6:
        r2 = move-exception;
        r4 = r3;
        goto L_0x00c9;
    L_0x00d9:
        r2 = move-exception;
        r3 = r19;
        goto L_0x0095;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p032d.C1355a.mo13694d(java.util.ArrayList):void");
    }

    /* renamed from: d */
    public synchronized boolean mo13695d(String str) {
        boolean z = false;
        synchronized (this) {
            SQLiteDatabase b = mo13682b();
            if (b != null) {
                try {
                    if (TextUtils.isEmpty(str)) {
                        C1425a.m6442c("DatabaseManager", "updateRegister pkgName can not be null");
                    } else {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("reg_time", Long.valueOf(System.currentTimeMillis()));
                        long j = -1;
                        if (b != null) {
                            String str2 = JiceArgs.EVENT_REGISTER;
                            String str3 = "pkg_name=?";
                            String[] strArr = new String[]{str};
                            j = (long) (!(b instanceof SQLiteDatabase) ? b.update(str2, contentValues, str3, strArr) : SQLiteInstrumentation.update(b, str2, contentValues, str3, strArr));
                        }
                        boolean z2 = j > 0;
                        mo13690c();
                        z = z2;
                    }
                } catch (Exception e) {
                    C1425a.m6444e("DatabaseManager", "error " + e.getMessage());
                    return z;
                } finally {
                    mo13690c();
                }
            }
        }
        return z;
    }

    /* renamed from: d */
    public synchronized boolean mo13696d(String str, String str2) {
        boolean z = false;
        synchronized (this) {
            SQLiteDatabase b = mo13682b();
            if (b != null) {
                try {
                    if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                        C1425a.m6442c("DatabaseManager", "deleteBlackList appid or pkgName can not be null");
                    } else {
                        String str3 = "blacklist";
                        String str4 = "app_id = ? AND pkg_name =?";
                        String[] strArr = new String[]{str, str2};
                        boolean z2 = (!(b instanceof SQLiteDatabase) ? b.delete(str3, str4, strArr) : SQLiteInstrumentation.delete((SQLiteDatabase) b, str3, str4, strArr)) > 0;
                        mo13690c();
                        z = z2;
                    }
                } catch (Exception e) {
                    C1425a.m6444e("DatabaseManager", "error " + e.getMessage());
                    return z;
                } finally {
                    mo13690c();
                }
            }
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x00d7 A:{SYNTHETIC, Splitter:B:48:0x00d7} */
    /* renamed from: e */
    public synchronized java.lang.String mo13697e() {
        /*
        r12 = this;
        r10 = 0;
        monitor-enter(r12);
        r1 = r12.mo13682b();	 Catch:{ all -> 0x00c7 }
        r2 = 0;
        if (r1 != 0) goto L_0x0014;
    L_0x0009:
        if (r10 == 0) goto L_0x000e;
    L_0x000b:
        r2.close();	 Catch:{ all -> 0x00c7 }
    L_0x000e:
        r12.mo13690c();	 Catch:{ all -> 0x00c7 }
        r1 = r10;
    L_0x0012:
        monitor-exit(r12);
        return r1;
    L_0x0014:
        r9 = new org.json.JSONArray;	 Catch:{ JSONException -> 0x00e0, all -> 0x00d3 }
        r9.<init>();	 Catch:{ JSONException -> 0x00e0, all -> 0x00d3 }
        r2 = "subscribe";
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r11 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ JSONException -> 0x00e0, all -> 0x00d3 }
        if (r11 != 0) goto L_0x00ac;
    L_0x0025:
        r1 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ JSONException -> 0x00e0, all -> 0x00d3 }
        r2 = r1;
    L_0x002a:
        r1 = r2.moveToNext();	 Catch:{ JSONException -> 0x0084 }
        if (r1 == 0) goto L_0x00b5;
    L_0x0030:
        r1 = "appid";
        r1 = r2.getColumnIndex(r1);	 Catch:{ JSONException -> 0x0084 }
        r1 = r2.getString(r1);	 Catch:{ JSONException -> 0x0084 }
        r3 = "app_name";
        r3 = r2.getColumnIndex(r3);	 Catch:{ JSONException -> 0x0084 }
        r3 = r2.getString(r3);	 Catch:{ JSONException -> 0x0084 }
        r4 = "app_logo";
        r4 = r2.getColumnIndex(r4);	 Catch:{ JSONException -> 0x0084 }
        r4 = r2.getString(r4);	 Catch:{ JSONException -> 0x0084 }
        r5 = "apikey";
        r5 = r2.getColumnIndex(r5);	 Catch:{ JSONException -> 0x0084 }
        r5 = r2.getString(r5);	 Catch:{ JSONException -> 0x0084 }
        r6 = "is_bind";
        r6 = r2.getColumnIndex(r6);	 Catch:{ JSONException -> 0x0084 }
        r6 = r2.getInt(r6);	 Catch:{ JSONException -> 0x0084 }
        r7 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0084 }
        r7.<init>();	 Catch:{ JSONException -> 0x0084 }
        r8 = "appid";
        r7.put(r8, r1);	 Catch:{ JSONException -> 0x0084 }
        r1 = "app_name";
        r7.put(r1, r3);	 Catch:{ JSONException -> 0x0084 }
        r1 = "app_logo";
        r7.put(r1, r4);	 Catch:{ JSONException -> 0x0084 }
        r1 = "api_key";
        r7.put(r1, r5);	 Catch:{ JSONException -> 0x0084 }
        r1 = "is_bind";
        r7.put(r1, r6);	 Catch:{ JSONException -> 0x0084 }
        r9.put(r7);	 Catch:{ JSONException -> 0x0084 }
        goto L_0x002a;
    L_0x0084:
        r1 = move-exception;
    L_0x0085:
        r3 = "DatabaseManager";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00de }
        r4.<init>();	 Catch:{ all -> 0x00de }
        r5 = "getSubscribedApps ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x00de }
        r1 = r1.getMessage();	 Catch:{ all -> 0x00de }
        r1 = r4.append(r1);	 Catch:{ all -> 0x00de }
        r1 = r1.toString();	 Catch:{ all -> 0x00de }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r3, r1);	 Catch:{ all -> 0x00de }
        if (r2 == 0) goto L_0x00a6;
    L_0x00a3:
        r2.close();	 Catch:{ all -> 0x00c7 }
    L_0x00a6:
        r12.mo13690c();	 Catch:{ all -> 0x00c7 }
        r1 = r10;
        goto L_0x0012;
    L_0x00ac:
        r1 = (android.database.sqlite.SQLiteDatabase) r1;	 Catch:{ JSONException -> 0x00e0, all -> 0x00d3 }
        r1 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ JSONException -> 0x00e0, all -> 0x00d3 }
        r2 = r1;
        goto L_0x002a;
    L_0x00b5:
        r1 = r9 instanceof org.json.JSONArray;	 Catch:{ JSONException -> 0x0084 }
        if (r1 != 0) goto L_0x00ca;
    L_0x00b9:
        r1 = r9.toString();	 Catch:{ JSONException -> 0x0084 }
    L_0x00bd:
        if (r2 == 0) goto L_0x00c2;
    L_0x00bf:
        r2.close();	 Catch:{ all -> 0x00c7 }
    L_0x00c2:
        r12.mo13690c();	 Catch:{ all -> 0x00c7 }
        goto L_0x0012;
    L_0x00c7:
        r1 = move-exception;
        monitor-exit(r12);
        throw r1;
    L_0x00ca:
        r0 = r9;
        r0 = (org.json.JSONArray) r0;	 Catch:{ JSONException -> 0x0084 }
        r1 = r0;
        r1 = com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation.toString(r1);	 Catch:{ JSONException -> 0x0084 }
        goto L_0x00bd;
    L_0x00d3:
        r1 = move-exception;
        r2 = r10;
    L_0x00d5:
        if (r2 == 0) goto L_0x00da;
    L_0x00d7:
        r2.close();	 Catch:{ all -> 0x00c7 }
    L_0x00da:
        r12.mo13690c();	 Catch:{ all -> 0x00c7 }
        throw r1;	 Catch:{ all -> 0x00c7 }
    L_0x00de:
        r1 = move-exception;
        goto L_0x00d5;
    L_0x00e0:
        r1 = move-exception;
        r2 = r10;
        goto L_0x0085;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p032d.C1355a.mo13697e():java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x008f A:{Catch:{ Exception -> 0x0064, all -> 0x008b }} */
    /* renamed from: e */
    public synchronized java.lang.String mo13698e(java.lang.String r11) {
        /*
        r10 = this;
        r8 = 0;
        monitor-enter(r10);
        r0 = r10.mo13682b();	 Catch:{ all -> 0x0050 }
        r1 = 0;
        if (r0 == 0) goto L_0x000f;
    L_0x0009:
        r2 = android.text.TextUtils.isEmpty(r11);	 Catch:{ Exception -> 0x0064, all -> 0x008b }
        if (r2 == 0) goto L_0x001a;
    L_0x000f:
        if (r8 == 0) goto L_0x0014;
    L_0x0011:
        r1.close();	 Catch:{ all -> 0x0050 }
    L_0x0014:
        r10.mo13690c();	 Catch:{ all -> 0x0050 }
        r0 = r8;
    L_0x0018:
        monitor-exit(r10);
        return r0;
    L_0x001a:
        r1 = "register";
        r2 = 1;
        r2 = new java.lang.String[r2];	 Catch:{ Exception -> 0x0064, all -> 0x008b }
        r3 = 0;
        r4 = "pkg_name";
        r2[r3] = r4;	 Catch:{ Exception -> 0x0064, all -> 0x008b }
        r3 = "channel= ? ";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0064, all -> 0x008b }
        r5 = 0;
        r4[r5] = r11;	 Catch:{ Exception -> 0x0064, all -> 0x008b }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r9 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0064, all -> 0x008b }
        if (r9 != 0) goto L_0x0053;
    L_0x0033:
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0064, all -> 0x008b }
    L_0x0037:
        r0 = r1.moveToNext();	 Catch:{ Exception -> 0x0098 }
        if (r0 == 0) goto L_0x005a;
    L_0x003d:
        r0 = "pkg_name";
        r0 = r1.getColumnIndex(r0);	 Catch:{ Exception -> 0x0098 }
        r0 = r1.getString(r0);	 Catch:{ Exception -> 0x0098 }
        if (r1 == 0) goto L_0x004c;
    L_0x0049:
        r1.close();	 Catch:{ all -> 0x0050 }
    L_0x004c:
        r10.mo13690c();	 Catch:{ all -> 0x0050 }
        goto L_0x0018;
    L_0x0050:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
    L_0x0053:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0064, all -> 0x008b }
        r1 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0064, all -> 0x008b }
        goto L_0x0037;
    L_0x005a:
        if (r1 == 0) goto L_0x005f;
    L_0x005c:
        r1.close();	 Catch:{ all -> 0x0050 }
    L_0x005f:
        r10.mo13690c();	 Catch:{ all -> 0x0050 }
    L_0x0062:
        r0 = r8;
        goto L_0x0018;
    L_0x0064:
        r0 = move-exception;
        r1 = r8;
    L_0x0066:
        r2 = "DatabaseManager";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0096 }
        r3.<init>();	 Catch:{ all -> 0x0096 }
        r4 = "error ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0096 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0096 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0096 }
        r0 = r0.toString();	 Catch:{ all -> 0x0096 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r2, r0);	 Catch:{ all -> 0x0096 }
        if (r1 == 0) goto L_0x0087;
    L_0x0084:
        r1.close();	 Catch:{ all -> 0x0050 }
    L_0x0087:
        r10.mo13690c();	 Catch:{ all -> 0x0050 }
        goto L_0x0062;
    L_0x008b:
        r0 = move-exception;
        r1 = r8;
    L_0x008d:
        if (r1 == 0) goto L_0x0092;
    L_0x008f:
        r1.close();	 Catch:{ all -> 0x0050 }
    L_0x0092:
        r10.mo13690c();	 Catch:{ all -> 0x0050 }
        throw r0;	 Catch:{ all -> 0x0050 }
    L_0x0096:
        r0 = move-exception;
        goto L_0x008d;
    L_0x0098:
        r0 = move-exception;
        goto L_0x0066;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p032d.C1355a.mo13698e(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00aa A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:11:0x0032} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00a6  */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:33:0x0099, code skipped:
            r2.close();
     */
    /* JADX WARNING: Missing block: B:41:0x00a6, code skipped:
            r8.close();
     */
    /* JADX WARNING: Missing block: B:43:0x00aa, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:44:0x00ab, code skipped:
            r8 = r1;
     */
    /* JADX WARNING: Missing block: B:49:0x00b5, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:50:0x00b6, code skipped:
            r10 = r0;
            r0 = r2;
            r2 = r1;
            r1 = r10;
     */
    /* renamed from: f */
    public synchronized com.baidu.android.pushservice.p037i.C1442i mo13699f(java.lang.String r12) {
        /*
        r11 = this;
        r8 = 0;
        monitor-enter(r11);
        r0 = r11.mo13682b();	 Catch:{ all -> 0x009d }
        if (r0 == 0) goto L_0x00bd;
    L_0x0008:
        r1 = "register";
        r2 = 3;
        r2 = new java.lang.String[r2];	 Catch:{ Exception -> 0x0074, all -> 0x00a0 }
        r3 = 0;
        r4 = "channel";
        r2[r3] = r4;	 Catch:{ Exception -> 0x0074, all -> 0x00a0 }
        r3 = 1;
        r4 = "host_name";
        r2[r3] = r4;	 Catch:{ Exception -> 0x0074, all -> 0x00a0 }
        r3 = 2;
        r4 = "host_version";
        r2[r3] = r4;	 Catch:{ Exception -> 0x0074, all -> 0x00a0 }
        r3 = "pkg_name=?";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0074, all -> 0x00a0 }
        r5 = 0;
        r4[r5] = r12;	 Catch:{ Exception -> 0x0074, all -> 0x00a0 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r9 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0074, all -> 0x00a0 }
        if (r9 != 0) goto L_0x006d;
    L_0x002b:
        r0 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0074, all -> 0x00a0 }
    L_0x002f:
        r1 = r0;
    L_0x0030:
        if (r1 == 0) goto L_0x00bb;
    L_0x0032:
        r0 = r1.moveToNext();	 Catch:{ Exception -> 0x00b0, all -> 0x00aa }
        if (r0 == 0) goto L_0x00bb;
    L_0x0038:
        r2 = new com.baidu.android.pushservice.i.i;	 Catch:{ Exception -> 0x00b0, all -> 0x00aa }
        r2.<init>();	 Catch:{ Exception -> 0x00b0, all -> 0x00aa }
        r0 = "channel";
        r0 = r1.getColumnIndex(r0);	 Catch:{ Exception -> 0x00b5, all -> 0x00aa }
        r0 = r1.getString(r0);	 Catch:{ Exception -> 0x00b5, all -> 0x00aa }
        r2.mo13892a(r0);	 Catch:{ Exception -> 0x00b5, all -> 0x00aa }
        r0 = "host_name";
        r0 = r1.getColumnIndex(r0);	 Catch:{ Exception -> 0x00b5, all -> 0x00aa }
        r0 = r1.getString(r0);	 Catch:{ Exception -> 0x00b5, all -> 0x00aa }
        r2.f5090a = r0;	 Catch:{ Exception -> 0x00b5, all -> 0x00aa }
        r0 = "host_version";
        r0 = r1.getColumnIndex(r0);	 Catch:{ Exception -> 0x00b5, all -> 0x00aa }
        r0 = r1.getString(r0);	 Catch:{ Exception -> 0x00b5, all -> 0x00aa }
        r2.f5091b = r0;	 Catch:{ Exception -> 0x00b5, all -> 0x00aa }
        r0 = r2;
    L_0x0063:
        r11.mo13690c();	 Catch:{ all -> 0x009d }
        if (r1 == 0) goto L_0x006b;
    L_0x0068:
        r1.close();	 Catch:{ all -> 0x009d }
    L_0x006b:
        monitor-exit(r11);
        return r0;
    L_0x006d:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0074, all -> 0x00a0 }
        r0 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0074, all -> 0x00a0 }
        goto L_0x002f;
    L_0x0074:
        r0 = move-exception;
        r1 = r0;
        r2 = r8;
        r0 = r8;
    L_0x0078:
        r3 = "DatabaseManager";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ad }
        r4.<init>();	 Catch:{ all -> 0x00ad }
        r5 = "error ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x00ad }
        r1 = r1.getMessage();	 Catch:{ all -> 0x00ad }
        r1 = r4.append(r1);	 Catch:{ all -> 0x00ad }
        r1 = r1.toString();	 Catch:{ all -> 0x00ad }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r3, r1);	 Catch:{ all -> 0x00ad }
        r11.mo13690c();	 Catch:{ all -> 0x009d }
        if (r2 == 0) goto L_0x006b;
    L_0x0099:
        r2.close();	 Catch:{ all -> 0x009d }
        goto L_0x006b;
    L_0x009d:
        r0 = move-exception;
        monitor-exit(r11);
        throw r0;
    L_0x00a0:
        r0 = move-exception;
    L_0x00a1:
        r11.mo13690c();	 Catch:{ all -> 0x009d }
        if (r8 == 0) goto L_0x00a9;
    L_0x00a6:
        r8.close();	 Catch:{ all -> 0x009d }
    L_0x00a9:
        throw r0;	 Catch:{ all -> 0x009d }
    L_0x00aa:
        r0 = move-exception;
        r8 = r1;
        goto L_0x00a1;
    L_0x00ad:
        r0 = move-exception;
        r8 = r2;
        goto L_0x00a1;
    L_0x00b0:
        r0 = move-exception;
        r2 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x0078;
    L_0x00b5:
        r0 = move-exception;
        r10 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r10;
        goto L_0x0078;
    L_0x00bb:
        r0 = r8;
        goto L_0x0063;
    L_0x00bd:
        r1 = r8;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p032d.C1355a.mo13699f(java.lang.String):com.baidu.android.pushservice.i.i");
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x0082 A:{SYNTHETIC, Splitter:B:41:0x0082} */
    /* renamed from: f */
    public synchronized java.util.ArrayList<java.lang.String> mo13700f() {
        /*
        r10 = this;
        r8 = 0;
        monitor-enter(r10);
        r0 = r10.mo13682b();	 Catch:{ all -> 0x007b }
        r1 = 0;
        if (r0 != 0) goto L_0x0014;
    L_0x0009:
        if (r8 == 0) goto L_0x000e;
    L_0x000b:
        r1.close();	 Catch:{ all -> 0x007b }
    L_0x000e:
        r10.mo13690c();	 Catch:{ all -> 0x007b }
        r0 = r8;
    L_0x0012:
        monitor-exit(r10);
        return r0;
    L_0x0014:
        r1 = "subscribe";
        r2 = 1;
        r2 = new java.lang.String[r2];	 Catch:{ Exception -> 0x008b, all -> 0x007e }
        r3 = 0;
        r4 = "appid";
        r2[r3] = r4;	 Catch:{ Exception -> 0x008b, all -> 0x007e }
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r9 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x008b, all -> 0x007e }
        if (r9 != 0) goto L_0x006b;
    L_0x0027:
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x008b, all -> 0x007e }
    L_0x002b:
        r0 = new java.util.ArrayList;	 Catch:{ Exception -> 0x0044 }
        r0.<init>();	 Catch:{ Exception -> 0x0044 }
    L_0x0030:
        r2 = r1.moveToNext();	 Catch:{ Exception -> 0x0044 }
        if (r2 == 0) goto L_0x0072;
    L_0x0036:
        r2 = "appid";
        r2 = r1.getColumnIndex(r2);	 Catch:{ Exception -> 0x0044 }
        r2 = r1.getString(r2);	 Catch:{ Exception -> 0x0044 }
        r0.add(r2);	 Catch:{ Exception -> 0x0044 }
        goto L_0x0030;
    L_0x0044:
        r0 = move-exception;
    L_0x0045:
        r2 = "DatabaseManager";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0089 }
        r3.<init>();	 Catch:{ all -> 0x0089 }
        r4 = "error ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0089 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0089 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0089 }
        r0 = r0.toString();	 Catch:{ all -> 0x0089 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r2, r0);	 Catch:{ all -> 0x0089 }
        if (r1 == 0) goto L_0x0066;
    L_0x0063:
        r1.close();	 Catch:{ all -> 0x007b }
    L_0x0066:
        r10.mo13690c();	 Catch:{ all -> 0x007b }
        r0 = r8;
        goto L_0x0012;
    L_0x006b:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x008b, all -> 0x007e }
        r1 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x008b, all -> 0x007e }
        goto L_0x002b;
    L_0x0072:
        if (r1 == 0) goto L_0x0077;
    L_0x0074:
        r1.close();	 Catch:{ all -> 0x007b }
    L_0x0077:
        r10.mo13690c();	 Catch:{ all -> 0x007b }
        goto L_0x0012;
    L_0x007b:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
    L_0x007e:
        r0 = move-exception;
        r1 = r8;
    L_0x0080:
        if (r1 == 0) goto L_0x0085;
    L_0x0082:
        r1.close();	 Catch:{ all -> 0x007b }
    L_0x0085:
        r10.mo13690c();	 Catch:{ all -> 0x007b }
        throw r0;	 Catch:{ all -> 0x007b }
    L_0x0089:
        r0 = move-exception;
        goto L_0x0080;
    L_0x008b:
        r0 = move-exception;
        r1 = r8;
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p032d.C1355a.mo13700f():java.util.ArrayList");
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x009d A:{Catch:{ Exception -> 0x006e, all -> 0x0097 }} */
    /* renamed from: g */
    public synchronized int mo13701g(java.lang.String r12) {
        /*
        r11 = this;
        r9 = 0;
        r8 = -1;
        monitor-enter(r11);
        r0 = r11.mo13682b();	 Catch:{ all -> 0x005a }
        if (r0 != 0) goto L_0x000c;
    L_0x0009:
        r0 = r8;
    L_0x000a:
        monitor-exit(r11);
        return r0;
    L_0x000c:
        r1 = 0;
        r2 = android.text.TextUtils.isEmpty(r12);	 Catch:{ Exception -> 0x006e, all -> 0x0097 }
        if (r2 == 0) goto L_0x0024;
    L_0x0013:
        r0 = "DatabaseManager";
        r2 = "getNewMsgNum pkgName can not be null";
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r2);	 Catch:{ Exception -> 0x006e, all -> 0x0097 }
        r11.mo13690c();	 Catch:{ all -> 0x005a }
        if (r9 == 0) goto L_0x0022;
    L_0x001f:
        r1.close();	 Catch:{ all -> 0x005a }
    L_0x0022:
        r0 = r8;
        goto L_0x000a;
    L_0x0024:
        r1 = "register";
        r2 = 1;
        r2 = new java.lang.String[r2];	 Catch:{ Exception -> 0x006e, all -> 0x0097 }
        r3 = 0;
        r4 = "msg_count";
        r2[r3] = r4;	 Catch:{ Exception -> 0x006e, all -> 0x0097 }
        r3 = "pkg_name=?";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x006e, all -> 0x0097 }
        r5 = 0;
        r4[r5] = r12;	 Catch:{ Exception -> 0x006e, all -> 0x0097 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r10 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x006e, all -> 0x0097 }
        if (r10 != 0) goto L_0x005d;
    L_0x003d:
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x006e, all -> 0x0097 }
    L_0x0041:
        r0 = "msg_count";
        r0 = r1.getColumnIndex(r0);	 Catch:{ Exception -> 0x00a4 }
        r2 = r1.moveToNext();	 Catch:{ Exception -> 0x00a4 }
        if (r2 == 0) goto L_0x0064;
    L_0x004d:
        r0 = r1.getInt(r0);	 Catch:{ Exception -> 0x00a4 }
        r11.mo13690c();	 Catch:{ all -> 0x005a }
        if (r1 == 0) goto L_0x000a;
    L_0x0056:
        r1.close();	 Catch:{ all -> 0x005a }
        goto L_0x000a;
    L_0x005a:
        r0 = move-exception;
        monitor-exit(r11);
        throw r0;
    L_0x005d:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x006e, all -> 0x0097 }
        r1 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x006e, all -> 0x0097 }
        goto L_0x0041;
    L_0x0064:
        r11.mo13690c();	 Catch:{ all -> 0x005a }
        if (r1 == 0) goto L_0x006c;
    L_0x0069:
        r1.close();	 Catch:{ all -> 0x005a }
    L_0x006c:
        r0 = r8;
        goto L_0x000a;
    L_0x006e:
        r0 = move-exception;
        r1 = r9;
    L_0x0070:
        r2 = "DatabaseManager";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00a1 }
        r3.<init>();	 Catch:{ all -> 0x00a1 }
        r4 = "error ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00a1 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x00a1 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x00a1 }
        r0 = r0.toString();	 Catch:{ all -> 0x00a1 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r2, r0);	 Catch:{ all -> 0x00a1 }
        r11.mo13690c();	 Catch:{ all -> 0x005a }
        if (r1 == 0) goto L_0x0094;
    L_0x0091:
        r1.close();	 Catch:{ all -> 0x005a }
    L_0x0094:
        r0 = r8;
        goto L_0x000a;
    L_0x0097:
        r0 = move-exception;
    L_0x0098:
        r11.mo13690c();	 Catch:{ all -> 0x005a }
        if (r9 == 0) goto L_0x00a0;
    L_0x009d:
        r9.close();	 Catch:{ all -> 0x005a }
    L_0x00a0:
        throw r0;	 Catch:{ all -> 0x005a }
    L_0x00a1:
        r0 = move-exception;
        r9 = r1;
        goto L_0x0098;
    L_0x00a4:
        r0 = move-exception;
        goto L_0x0070;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p032d.C1355a.mo13701g(java.lang.String):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0091 A:{Catch:{ Exception -> 0x009a, all -> 0x008a }} */
    /* renamed from: g */
    public synchronized java.util.HashMap<java.lang.String, java.lang.Integer> mo13702g() {
        /*
        r11 = this;
        r8 = 0;
        monitor-enter(r11);
        r0 = r11.mo13682b();	 Catch:{ all -> 0x0095 }
        r9 = new java.util.HashMap;	 Catch:{ all -> 0x0095 }
        r9.<init>();	 Catch:{ all -> 0x0095 }
        if (r0 == 0) goto L_0x009d;
    L_0x000d:
        r1 = "register";
        r2 = 2;
        r2 = new java.lang.String[r2];	 Catch:{ Exception -> 0x009a, all -> 0x008a }
        r3 = 0;
        r4 = "pkg_name";
        r2[r3] = r4;	 Catch:{ Exception -> 0x009a, all -> 0x008a }
        r3 = 1;
        r4 = "msg_switch";
        r2[r3] = r4;	 Catch:{ Exception -> 0x009a, all -> 0x008a }
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = "reg_time DESC";
        r10 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x009a, all -> 0x008a }
        if (r10 != 0) goto L_0x0079;
    L_0x0026:
        r0 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x009a, all -> 0x008a }
    L_0x002a:
        r1 = r0;
    L_0x002b:
        if (r1 == 0) goto L_0x0080;
    L_0x002d:
        r0 = r1.moveToNext();	 Catch:{ Exception -> 0x0051 }
        if (r0 == 0) goto L_0x0080;
    L_0x0033:
        r0 = "pkg_name";
        r0 = r1.getColumnIndex(r0);	 Catch:{ Exception -> 0x0051 }
        r2 = "msg_switch";
        r2 = r1.getColumnIndex(r2);	 Catch:{ Exception -> 0x0051 }
        r0 = r1.getString(r0);	 Catch:{ Exception -> 0x0051 }
        r2 = r1.getInt(r2);	 Catch:{ Exception -> 0x0051 }
        if (r2 != 0) goto L_0x002d;
    L_0x0049:
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x0051 }
        r9.put(r0, r2);	 Catch:{ Exception -> 0x0051 }
        goto L_0x002d;
    L_0x0051:
        r0 = move-exception;
    L_0x0052:
        r2 = "DatabaseManager";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0098 }
        r3.<init>();	 Catch:{ all -> 0x0098 }
        r4 = "error ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0098 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0098 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0098 }
        r0 = r0.toString();	 Catch:{ all -> 0x0098 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r2, r0);	 Catch:{ all -> 0x0098 }
        r11.mo13690c();	 Catch:{ all -> 0x0095 }
        if (r1 == 0) goto L_0x0076;
    L_0x0073:
        r1.close();	 Catch:{ all -> 0x0095 }
    L_0x0076:
        r0 = r8;
    L_0x0077:
        monitor-exit(r11);
        return r0;
    L_0x0079:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x009a, all -> 0x008a }
        r0 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x009a, all -> 0x008a }
        goto L_0x002a;
    L_0x0080:
        r11.mo13690c();	 Catch:{ all -> 0x0095 }
        if (r1 == 0) goto L_0x0088;
    L_0x0085:
        r1.close();	 Catch:{ all -> 0x0095 }
    L_0x0088:
        r0 = r9;
        goto L_0x0077;
    L_0x008a:
        r0 = move-exception;
        r1 = r8;
    L_0x008c:
        r11.mo13690c();	 Catch:{ all -> 0x0095 }
        if (r1 == 0) goto L_0x0094;
    L_0x0091:
        r1.close();	 Catch:{ all -> 0x0095 }
    L_0x0094:
        throw r0;	 Catch:{ all -> 0x0095 }
    L_0x0095:
        r0 = move-exception;
        monitor-exit(r11);
        throw r0;
    L_0x0098:
        r0 = move-exception;
        goto L_0x008c;
    L_0x009a:
        r0 = move-exception;
        r1 = r8;
        goto L_0x0052;
    L_0x009d:
        r1 = r8;
        goto L_0x002b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p032d.C1355a.mo13702g():java.util.HashMap");
    }

    /* renamed from: h */
    public synchronized boolean mo13703h(String str) {
        boolean z = false;
        synchronized (this) {
            SQLiteDatabase b = mo13682b();
            if (b != null) {
                try {
                    if (TextUtils.isEmpty(str)) {
                        C1425a.m6442c("DatabaseManager", "clearNewMsgNum pkgName can not be null");
                    } else {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("msg_count", Integer.valueOf(0));
                        String str2 = JiceArgs.EVENT_REGISTER;
                        String str3 = "pkg_name=?";
                        String[] strArr = new String[]{str};
                        boolean z2 = (!(b instanceof SQLiteDatabase) ? b.update(str2, contentValues, str3, strArr) : SQLiteInstrumentation.update((SQLiteDatabase) b, str2, contentValues, str3, strArr)) > 0;
                        mo13690c();
                        z = z2;
                    }
                } catch (Exception e) {
                    C1425a.m6444e("DatabaseManager", "error " + e.getMessage());
                    return z;
                } finally {
                    mo13690c();
                }
            }
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0097 A:{Catch:{ Exception -> 0x00a5, all -> 0x0091 }} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0073 A:{Catch:{ Exception -> 0x00a5, all -> 0x0091 }} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0097 A:{Catch:{ Exception -> 0x00a5, all -> 0x0091 }} */
    /* renamed from: i */
    public synchronized java.util.HashMap<java.lang.String, java.lang.Integer> mo13704i(java.lang.String r13) {
        /*
        r12 = this;
        r8 = 0;
        monitor-enter(r12);
        r0 = r12.mo13682b();	 Catch:{ all -> 0x009b }
        r9 = new java.util.HashMap;	 Catch:{ all -> 0x009b }
        r9.<init>();	 Catch:{ all -> 0x009b }
        r1 = android.text.TextUtils.isEmpty(r13);	 Catch:{ Exception -> 0x00a5, all -> 0x0091 }
        if (r1 != 0) goto L_0x007f;
    L_0x0011:
        if (r0 == 0) goto L_0x00a8;
    L_0x0013:
        r1 = "blacklist";
        r2 = 0;
        r3 = "app_id = ? ";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x00a5, all -> 0x0091 }
        r5 = 0;
        r4[r5] = r13;	 Catch:{ Exception -> 0x00a5, all -> 0x0091 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r10 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00a5, all -> 0x0091 }
        if (r10 != 0) goto L_0x0078;
    L_0x0025:
        r0 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x00a5, all -> 0x0091 }
    L_0x0029:
        if (r0 == 0) goto L_0x0087;
    L_0x002b:
        r1 = r0.moveToNext();	 Catch:{ Exception -> 0x004e, all -> 0x009e }
        if (r1 == 0) goto L_0x0087;
    L_0x0031:
        r1 = "pkg_name";
        r1 = r0.getColumnIndex(r1);	 Catch:{ Exception -> 0x004e, all -> 0x009e }
        r2 = "type";
        r2 = r0.getColumnIndex(r2);	 Catch:{ Exception -> 0x004e, all -> 0x009e }
        r1 = r0.getString(r1);	 Catch:{ Exception -> 0x004e, all -> 0x009e }
        r2 = r0.getInt(r2);	 Catch:{ Exception -> 0x004e, all -> 0x009e }
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x004e, all -> 0x009e }
        r9.put(r1, r2);	 Catch:{ Exception -> 0x004e, all -> 0x009e }
        goto L_0x002b;
    L_0x004e:
        r1 = move-exception;
        r11 = r1;
        r1 = r0;
        r0 = r11;
    L_0x0052:
        r2 = "DatabaseManager";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00a2 }
        r3.<init>();	 Catch:{ all -> 0x00a2 }
        r4 = "error ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00a2 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x00a2 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x00a2 }
        r0 = r0.toString();	 Catch:{ all -> 0x00a2 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r2, r0);	 Catch:{ all -> 0x00a2 }
        r12.mo13690c();	 Catch:{ all -> 0x009b }
        if (r1 == 0) goto L_0x0076;
    L_0x0073:
        r1.close();	 Catch:{ all -> 0x009b }
    L_0x0076:
        monitor-exit(r12);
        return r8;
    L_0x0078:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00a5, all -> 0x0091 }
        r0 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x00a5, all -> 0x0091 }
        goto L_0x0029;
    L_0x007f:
        r0 = "DatabaseManager";
        r1 = "getBlackList appid can not be null";
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r1);	 Catch:{ Exception -> 0x00a5, all -> 0x0091 }
        r0 = r8;
    L_0x0087:
        r12.mo13690c();	 Catch:{ all -> 0x009b }
        if (r0 == 0) goto L_0x008f;
    L_0x008c:
        r0.close();	 Catch:{ all -> 0x009b }
    L_0x008f:
        r8 = r9;
        goto L_0x0076;
    L_0x0091:
        r0 = move-exception;
    L_0x0092:
        r12.mo13690c();	 Catch:{ all -> 0x009b }
        if (r8 == 0) goto L_0x009a;
    L_0x0097:
        r8.close();	 Catch:{ all -> 0x009b }
    L_0x009a:
        throw r0;	 Catch:{ all -> 0x009b }
    L_0x009b:
        r0 = move-exception;
        monitor-exit(r12);
        throw r0;
    L_0x009e:
        r1 = move-exception;
        r8 = r0;
        r0 = r1;
        goto L_0x0092;
    L_0x00a2:
        r0 = move-exception;
        r8 = r1;
        goto L_0x0092;
    L_0x00a5:
        r0 = move-exception;
        r1 = r8;
        goto L_0x0052;
    L_0x00a8:
        r0 = r8;
        goto L_0x0029;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p032d.C1355a.mo13704i(java.lang.String):java.util.HashMap");
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x00fe  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00fe  */
    /* renamed from: j */
    public java.lang.String mo13705j(java.lang.String r20) {
        /*
        r19 = this;
        r1 = r19.mo13682b();
        r18 = 0;
        r17 = 0;
        if (r1 == 0) goto L_0x0012;
    L_0x000a:
        if (r20 == 0) goto L_0x0012;
    L_0x000c:
        r2 = android.text.TextUtils.isEmpty(r20);	 Catch:{ Exception -> 0x00d3 }
        if (r2 == 0) goto L_0x001c;
    L_0x0012:
        r2 = 0;
        if (r17 == 0) goto L_0x0018;
    L_0x0015:
        r17.close();
    L_0x0018:
        r19.mo13690c();
    L_0x001b:
        return r2;
    L_0x001c:
        r2 = "subscribe";
        r3 = 0;
        r4 = "appid=?";
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ Exception -> 0x00d3 }
        r6 = 0;
        r5[r6] = r20;	 Catch:{ Exception -> 0x00d3 }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00d3 }
        if (r9 != 0) goto L_0x0052;
    L_0x002e:
        r17 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x00d3 }
    L_0x0032:
        r2 = r17.moveToNext();	 Catch:{ Exception -> 0x00d3 }
        if (r2 == 0) goto L_0x0063;
    L_0x0038:
        r1 = "app_logo";
        r0 = r17;
        r1 = r0.getColumnIndex(r1);	 Catch:{ Exception -> 0x00d3 }
        r0 = r17;
        r1 = r0.getString(r1);	 Catch:{ Exception -> 0x00d3 }
        r2 = r1;
        r1 = r17;
    L_0x0049:
        if (r1 == 0) goto L_0x004e;
    L_0x004b:
        r1.close();
    L_0x004e:
        r19.mo13690c();
        goto L_0x001b;
    L_0x0052:
        r0 = r1;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00d3 }
        r9 = r0;
        r10 = r2;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r9, r10, r11, r12, r13, r14, r15, r16);	 Catch:{ Exception -> 0x00d3 }
        goto L_0x0032;
    L_0x0063:
        r17.close();	 Catch:{ Exception -> 0x00d3 }
        r2 = "weak_subscribe";
        r3 = 0;
        r4 = "appid=?";
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ Exception -> 0x00d3 }
        r6 = 0;
        r5[r6] = r20;	 Catch:{ Exception -> 0x00d3 }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x00d3 }
        if (r9 != 0) goto L_0x0090;
    L_0x0079:
        r9 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x00d3 }
    L_0x007d:
        r2 = r9.moveToNext();	 Catch:{ Exception -> 0x010e, all -> 0x0105 }
        if (r2 == 0) goto L_0x00a1;
    L_0x0083:
        r1 = "app_logo";
        r1 = r9.getColumnIndex(r1);	 Catch:{ Exception -> 0x010e, all -> 0x0105 }
        r1 = r9.getString(r1);	 Catch:{ Exception -> 0x010e, all -> 0x0105 }
        r2 = r1;
        r1 = r9;
        goto L_0x0049;
    L_0x0090:
        r0 = r1;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x00d3 }
        r9 = r0;
        r10 = r2;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r9 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r9, r10, r11, r12, r13, r14, r15, r16);	 Catch:{ Exception -> 0x00d3 }
        goto L_0x007d;
    L_0x00a1:
        r9.close();	 Catch:{ Exception -> 0x010e, all -> 0x0105 }
        r2 = "app_info";
        r3 = 0;
        r4 = "appid=?";
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ Exception -> 0x010e, all -> 0x0105 }
        r6 = 0;
        r5[r6] = r20;	 Catch:{ Exception -> 0x010e, all -> 0x0105 }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r10 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x010e, all -> 0x0105 }
        if (r10 != 0) goto L_0x00cc;
    L_0x00b6:
        r1 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x010e, all -> 0x0105 }
    L_0x00ba:
        r2 = r1.moveToNext();	 Catch:{ Exception -> 0x0112, all -> 0x0109 }
        if (r2 == 0) goto L_0x0117;
    L_0x00c0:
        r2 = "app_logo";
        r2 = r1.getColumnIndex(r2);	 Catch:{ Exception -> 0x0112, all -> 0x0109 }
        r2 = r1.getString(r2);	 Catch:{ Exception -> 0x0112, all -> 0x0109 }
        goto L_0x0049;
    L_0x00cc:
        r1 = (android.database.sqlite.SQLiteDatabase) r1;	 Catch:{ Exception -> 0x010e, all -> 0x0105 }
        r1 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x010e, all -> 0x0105 }
        goto L_0x00ba;
    L_0x00d3:
        r1 = move-exception;
    L_0x00d4:
        r2 = "DatabaseManager";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00fb }
        r3.<init>();	 Catch:{ all -> 0x00fb }
        r4 = "error ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00fb }
        r1 = r1.getMessage();	 Catch:{ all -> 0x00fb }
        r1 = r3.append(r1);	 Catch:{ all -> 0x00fb }
        r1 = r1.toString();	 Catch:{ all -> 0x00fb }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r2, r1);	 Catch:{ all -> 0x00fb }
        r2 = 0;
        if (r17 == 0) goto L_0x00f6;
    L_0x00f3:
        r17.close();
    L_0x00f6:
        r19.mo13690c();
        goto L_0x001b;
    L_0x00fb:
        r1 = move-exception;
    L_0x00fc:
        if (r17 == 0) goto L_0x0101;
    L_0x00fe:
        r17.close();
    L_0x0101:
        r19.mo13690c();
        throw r1;
    L_0x0105:
        r1 = move-exception;
        r17 = r9;
        goto L_0x00fc;
    L_0x0109:
        r2 = move-exception;
        r17 = r1;
        r1 = r2;
        goto L_0x00fc;
    L_0x010e:
        r1 = move-exception;
        r17 = r9;
        goto L_0x00d4;
    L_0x0112:
        r2 = move-exception;
        r17 = r1;
        r1 = r2;
        goto L_0x00d4;
    L_0x0117:
        r2 = r18;
        goto L_0x0049;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p032d.C1355a.mo13705j(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x0101  */
    /* renamed from: k */
    public synchronized java.lang.String mo13706k(java.lang.String r13) {
        /*
        r12 = this;
        r10 = 0;
        monitor-enter(r12);
        r1 = r12.mo13682b();	 Catch:{ all -> 0x00ee }
        r2 = 0;
        r9 = new org.json.JSONArray;	 Catch:{ all -> 0x00ee }
        r9.<init>();	 Catch:{ all -> 0x00ee }
        if (r1 != 0) goto L_0x0019;
    L_0x000e:
        r12.mo13690c();	 Catch:{ all -> 0x00ee }
        if (r10 == 0) goto L_0x0016;
    L_0x0013:
        r2.close();	 Catch:{ all -> 0x00ee }
    L_0x0016:
        r1 = r10;
    L_0x0017:
        monitor-exit(r12);
        return r1;
    L_0x0019:
        if (r13 == 0) goto L_0x00b0;
    L_0x001b:
        r2 = "blacklist";
        r3 = 3;
        r3 = new java.lang.String[r3];	 Catch:{ Exception -> 0x0107, all -> 0x00fa }
        r4 = 0;
        r5 = "app_id";
        r3[r4] = r5;	 Catch:{ Exception -> 0x0107, all -> 0x00fa }
        r4 = 1;
        r5 = "pkg_name";
        r3[r4] = r5;	 Catch:{ Exception -> 0x0107, all -> 0x00fa }
        r4 = 2;
        r5 = "type";
        r3[r4] = r5;	 Catch:{ Exception -> 0x0107, all -> 0x00fa }
        r4 = "pkg_name=?";
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ Exception -> 0x0107, all -> 0x00fa }
        r6 = 0;
        r5[r6] = r13;	 Catch:{ Exception -> 0x0107, all -> 0x00fa }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r11 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0107, all -> 0x00fa }
        if (r11 != 0) goto L_0x00a9;
    L_0x003f:
        r1 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0107, all -> 0x00fa }
    L_0x0043:
        r2 = r1;
    L_0x0044:
        r1 = r2.moveToNext();	 Catch:{ Exception -> 0x0081 }
        if (r1 == 0) goto L_0x00dc;
    L_0x004a:
        r1 = "app_id";
        r1 = r2.getColumnIndex(r1);	 Catch:{ Exception -> 0x0081 }
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x0081 }
        r3 = "pkg_name";
        r3 = r2.getColumnIndex(r3);	 Catch:{ Exception -> 0x0081 }
        r3 = r2.getString(r3);	 Catch:{ Exception -> 0x0081 }
        r4 = "type";
        r4 = r2.getColumnIndex(r4);	 Catch:{ Exception -> 0x0081 }
        r4 = r2.getInt(r4);	 Catch:{ Exception -> 0x0081 }
        r5 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0081 }
        r5.<init>();	 Catch:{ Exception -> 0x0081 }
        r6 = "appid";
        r5.put(r6, r1);	 Catch:{ Exception -> 0x0081 }
        r1 = "pkg_name";
        r5.put(r1, r3);	 Catch:{ Exception -> 0x0081 }
        r1 = "msg_type";
        r5.put(r1, r4);	 Catch:{ Exception -> 0x0081 }
        r9.put(r5);	 Catch:{ Exception -> 0x0081 }
        goto L_0x0044;
    L_0x0081:
        r1 = move-exception;
    L_0x0082:
        r3 = "DatabaseManager";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0105 }
        r4.<init>();	 Catch:{ all -> 0x0105 }
        r5 = "error ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0105 }
        r1 = r1.getMessage();	 Catch:{ all -> 0x0105 }
        r1 = r4.append(r1);	 Catch:{ all -> 0x0105 }
        r1 = r1.toString();	 Catch:{ all -> 0x0105 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r3, r1);	 Catch:{ all -> 0x0105 }
        r12.mo13690c();	 Catch:{ all -> 0x00ee }
        if (r2 == 0) goto L_0x00a6;
    L_0x00a3:
        r2.close();	 Catch:{ all -> 0x00ee }
    L_0x00a6:
        r1 = r10;
        goto L_0x0017;
    L_0x00a9:
        r1 = (android.database.sqlite.SQLiteDatabase) r1;	 Catch:{ Exception -> 0x0107, all -> 0x00fa }
        r1 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0107, all -> 0x00fa }
        goto L_0x0043;
    L_0x00b0:
        r2 = "blacklist";
        r3 = 3;
        r3 = new java.lang.String[r3];	 Catch:{ Exception -> 0x0107, all -> 0x00fa }
        r4 = 0;
        r5 = "app_id";
        r3[r4] = r5;	 Catch:{ Exception -> 0x0107, all -> 0x00fa }
        r4 = 1;
        r5 = "pkg_name";
        r3[r4] = r5;	 Catch:{ Exception -> 0x0107, all -> 0x00fa }
        r4 = 2;
        r5 = "type";
        r3[r4] = r5;	 Catch:{ Exception -> 0x0107, all -> 0x00fa }
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r11 = r1 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0107, all -> 0x00fa }
        if (r11 != 0) goto L_0x00d5;
    L_0x00ce:
        r1 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0107, all -> 0x00fa }
    L_0x00d2:
        r2 = r1;
        goto L_0x0044;
    L_0x00d5:
        r1 = (android.database.sqlite.SQLiteDatabase) r1;	 Catch:{ Exception -> 0x0107, all -> 0x00fa }
        r1 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0107, all -> 0x00fa }
        goto L_0x00d2;
    L_0x00dc:
        r1 = r9 instanceof org.json.JSONArray;	 Catch:{ Exception -> 0x0081 }
        if (r1 != 0) goto L_0x00f1;
    L_0x00e0:
        r1 = r9.toString();	 Catch:{ Exception -> 0x0081 }
    L_0x00e4:
        r12.mo13690c();	 Catch:{ all -> 0x00ee }
        if (r2 == 0) goto L_0x0017;
    L_0x00e9:
        r2.close();	 Catch:{ all -> 0x00ee }
        goto L_0x0017;
    L_0x00ee:
        r1 = move-exception;
        monitor-exit(r12);
        throw r1;
    L_0x00f1:
        r0 = r9;
        r0 = (org.json.JSONArray) r0;	 Catch:{ Exception -> 0x0081 }
        r1 = r0;
        r1 = com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation.toString(r1);	 Catch:{ Exception -> 0x0081 }
        goto L_0x00e4;
    L_0x00fa:
        r1 = move-exception;
        r2 = r10;
    L_0x00fc:
        r12.mo13690c();	 Catch:{ all -> 0x00ee }
        if (r2 == 0) goto L_0x0104;
    L_0x0101:
        r2.close();	 Catch:{ all -> 0x00ee }
    L_0x0104:
        throw r1;	 Catch:{ all -> 0x00ee }
    L_0x0105:
        r1 = move-exception;
        goto L_0x00fc;
    L_0x0107:
        r1 = move-exception;
        r2 = r10;
        goto L_0x0082;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p032d.C1355a.mo13706k(java.lang.String):java.lang.String");
    }
}
