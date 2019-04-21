package com.amap.api.location;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import com.amap.api.location.AMapLocationManager.C0723a;
import com.amap.api.location.core.AMapLocException;
import com.aps.APS;
import com.aps.AmapLoc;
import com.aps.Fence;
import com.aps.IAPS;

/* renamed from: com.amap.api.location.c */
public class IAPSManager implements Runnable {
    /* renamed from: a */
    IAPS f868a = null;
    /* renamed from: b */
    volatile boolean f869b = false;
    /* renamed from: c */
    boolean f870c = true;
    /* renamed from: d */
    private volatile boolean f871d = false;
    /* renamed from: e */
    private Context f872e;
    /* renamed from: f */
    private long f873f = 2000;
    /* renamed from: g */
    private C0723a f874g;
    /* renamed from: h */
    private AMapLocationManager f875h;
    /* renamed from: i */
    private boolean f876i = false;

    IAPSManager(Context context, C0723a c0723a, AMapLocationManager aMapLocationManager) {
        this.f875h = aMapLocationManager;
        mo8380b(false);
        this.f872e = context;
        this.f868a = new APS();
        this.f874g = c0723a;
    }

    /* Access modifiers changed, original: declared_synchronized */
    /* renamed from: a */
    public synchronized void mo8375a(boolean z) {
        this.f869b = z;
    }

    /* renamed from: a */
    public synchronized boolean mo8376a() {
        return this.f869b;
    }

    /* Access modifiers changed, original: declared_synchronized */
    /* renamed from: b */
    public synchronized void mo8377b() {
        mo8375a(true);
        if (!this.f871d) {
            mo8381c();
        }
        if (this.f875h != null) {
            this.f875h.mo8358b();
        }
        this.f876i = false;
    }

    /* Access modifiers changed, original: declared_synchronized */
    /* renamed from: c */
    public synchronized void mo8381c() {
        if (this.f868a != null) {
            this.f868a.mo13180c();
        }
        this.f868a = null;
    }

    /* Access modifiers changed, original: declared_synchronized */
    /* renamed from: b */
    public synchronized void mo8380b(boolean z) {
        this.f871d = z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00ab A:{ExcHandler: JSONException (r0_21 'e' org.json.JSONException), Splitter:B:16:0x006b} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00ab A:{ExcHandler: JSONException (r0_21 'e' org.json.JSONException), Splitter:B:16:0x006b} */
    /* JADX WARNING: Missing block: B:33:0x00ab, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:35:?, code skipped:
            r0.printStackTrace();
     */
    /* renamed from: d */
    public synchronized void mo8382d() {
        /*
        r5 = this;
        monitor-enter(r5);
        r0 = r5.f876i;	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        if (r0 == 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r5);
        return;
    L_0x0007:
        android.os.Looper.prepare();	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r0 = r5.f872e;	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        com.amap.api.location.core.ClientInfoUtil.m1421a(r0);	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r0 = r5.f868a;	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        if (r0 == 0) goto L_0x001a;
    L_0x0013:
        r0 = r5.f868a;	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r1 = r5.f872e;	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r0.mo13172a(r1);	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
    L_0x001a:
        r0 = r5.f868a;	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        if (r0 == 0) goto L_0x004a;
    L_0x001e:
        r0 = r5.f868a;	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r1.<init>();	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r2 = "api_serverSDK_130905##S128DF1572465B890OE3F7A13167KLEI##";
        r1 = r1.append(r2);	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r2 = r5.f872e;	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r2 = com.amap.api.location.core.ClientInfoUtil.m1439h(r2);	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r1 = r1.append(r2);	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r2 = ",";
        r1 = r1.append(r2);	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r2 = com.amap.api.location.core.ClientInfoUtil.m1425b();	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r1 = r1.append(r2);	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r0.mo13175a(r1);	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
    L_0x004a:
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r1.<init>();	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r0 = "key";
        r2 = r5.f872e;	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r2 = com.amap.api.location.core.ClientInfoUtil.m1439h(r2);	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r0 = "X-INFO";
        r2 = r5.f872e;	 Catch:{ Throwable -> 0x00a6, JSONException -> 0x00ab }
        r2 = com.amap.api.location.core.ClientInfoUtil.m1421a(r2);	 Catch:{ Throwable -> 0x00a6, JSONException -> 0x00ab }
        r3 = "loc";
        r2 = r2.mo8404a(r3);	 Catch:{ Throwable -> 0x00a6, JSONException -> 0x00ab }
        r1.put(r0, r2);	 Catch:{ Throwable -> 0x00a6, JSONException -> 0x00ab }
    L_0x006b:
        r2 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r2.<init>();	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r0 = r5.f872e;	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r0 = com.amap.api.location.core.ClientInfoUtil.m1421a(r0);	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r0 = r0.mo8406c();	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r3 = "ex";
        r4 = "UTF-8";
        r0 = r0.getBytes(r4);	 Catch:{ UnsupportedEncodingException -> 0x00b0 }
        r0 = com.amap.api.location.core.Base64Util.m1419a(r0);	 Catch:{ UnsupportedEncodingException -> 0x00b0 }
        r2.put(r3, r0);	 Catch:{ UnsupportedEncodingException -> 0x00b0 }
    L_0x0089:
        r0 = "X-BIZ";
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r0 = "User-Agent";
        r2 = "AMAP_Location_SDK_Android 1.4.1";
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r0 = r5.f868a;	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        if (r0 == 0) goto L_0x009e;
    L_0x0099:
        r0 = r5.f868a;	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        r0.mo13176a(r1);	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
    L_0x009e:
        r0 = 1;
        r5.f876i = r0;	 Catch:{ all -> 0x00a3 }
        goto L_0x0005;
    L_0x00a3:
        r0 = move-exception;
        monitor-exit(r5);
        throw r0;
    L_0x00a6:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        goto L_0x006b;
    L_0x00ab:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x00a3 }
        goto L_0x009e;
    L_0x00b0:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ JSONException -> 0x00ab, Throwable -> 0x00b5 }
        goto L_0x0089;
    L_0x00b5:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x00a3 }
        goto L_0x009e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.location.IAPSManager.mo8382d():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:149:0x0213 A:{SYNTHETIC, Splitter:B:149:0x0213} */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x023a A:{Catch:{ Throwable -> 0x00b1, Throwable -> 0x020a }} */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x0261 A:{Catch:{ Throwable -> 0x00b1, Throwable -> 0x020a }} */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x0204 A:{Catch:{ Throwable -> 0x018e, all -> 0x0210, all -> 0x02f4, Throwable -> 0x00a5 }} */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x026a A:{Catch:{ Throwable -> 0x018e, all -> 0x0210, all -> 0x02f4, Throwable -> 0x00a5 }} */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public void run() {
        /*
        r5 = this;
        r4 = -1;
        r0 = r5.mo8376a();	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x000b;
    L_0x0007:
        r5.mo8381c();	 Catch:{ Throwable -> 0x020a }
    L_0x000a:
        return;
    L_0x000b:
        r0 = r5.f876i;	 Catch:{ Throwable -> 0x020a }
        if (r0 != 0) goto L_0x0016;
    L_0x000f:
        r0 = r5.f871d;	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x0016;
    L_0x0013:
        r5.mo8382d();	 Catch:{ Throwable -> 0x020a }
    L_0x0016:
        r0 = r5.f871d;	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x00a6;
    L_0x001a:
        r0 = java.lang.Thread.currentThread();	 Catch:{ Throwable -> 0x020a }
        r0 = r0.isInterrupted();	 Catch:{ Throwable -> 0x020a }
        if (r0 != 0) goto L_0x00a6;
    L_0x0024:
        r0 = r5.mo8376a();	 Catch:{ Throwable -> 0x020a }
        if (r0 != 0) goto L_0x00a6;
    L_0x002a:
        r1 = 0;
        r0 = r5.f875h;	 Catch:{ Throwable -> 0x018e }
        r0 = r0.f841d;	 Catch:{ Throwable -> 0x018e }
        if (r0 == 0) goto L_0x0037;
    L_0x0031:
        r0 = r5.m1382g();	 Catch:{ Throwable -> 0x018e }
        if (r0 == 0) goto L_0x003d;
    L_0x0037:
        r0 = r5.f875h;	 Catch:{ Throwable -> 0x018e }
        r0 = r0.f843f;	 Catch:{ Throwable -> 0x018e }
        if (r0 != 0) goto L_0x00b7;
    L_0x003d:
        r0 = 1;
        r5.f870c = r0;	 Catch:{ Throwable -> 0x0277 }
        r2 = r5.f873f;	 Catch:{ Throwable -> 0x0277 }
        java.lang.Thread.sleep(r2);	 Catch:{ Throwable -> 0x0277 }
        if (r1 == 0) goto L_0x0068;
    L_0x0047:
        r0 = r5.f875h;	 Catch:{ Throwable -> 0x020a }
        r0 = r0.f843f;	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x0068;
    L_0x004d:
        r0 = r5.f875h;	 Catch:{ Throwable -> 0x020a }
        r0 = r0.f841d;	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x0059;
    L_0x0053:
        r0 = r5.m1382g();	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x0068;
    L_0x0059:
        r0 = android.os.Message.obtain();	 Catch:{ Throwable -> 0x020a }
        r0.obj = r1;	 Catch:{ Throwable -> 0x020a }
        r2 = 100;
        r0.what = r2;	 Catch:{ Throwable -> 0x020a }
        r2 = r5.f874g;	 Catch:{ Throwable -> 0x020a }
        r2.sendMessage(r0);	 Catch:{ Throwable -> 0x020a }
    L_0x0068:
        r0 = com.amap.api.location.core.AuthManager.m1397a();	 Catch:{ Throwable -> 0x020a }
        if (r0 != r4) goto L_0x0091;
    L_0x006e:
        r0 = r5.f872e;	 Catch:{ Throwable -> 0x020a }
        com.amap.api.location.core.AuthManager.m1401a(r0);	 Catch:{ Throwable -> 0x020a }
        r0 = r5.f872e;	 Catch:{ Throwable -> 0x020a }
        com.amap.api.location.core.AuthManager.m1404b(r0);	 Catch:{ Throwable -> 0x020a }
        r0 = com.amap.api.location.core.AuthManager.m1416i();	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x0091;
    L_0x007e:
        r0 = android.os.Message.obtain();	 Catch:{ Throwable -> 0x020a }
        r0.obj = r1;	 Catch:{ Throwable -> 0x020a }
        r1 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        r0.what = r1;	 Catch:{ Throwable -> 0x020a }
        r1 = r5.f874g;	 Catch:{ Throwable -> 0x020a }
        if (r1 == 0) goto L_0x0091;
    L_0x008c:
        r1 = r5.f874g;	 Catch:{ Throwable -> 0x020a }
        r1.sendMessage(r0);	 Catch:{ Throwable -> 0x020a }
    L_0x0091:
        r0 = r5.f868a;	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x009a;
    L_0x0095:
        r0 = r5.f868a;	 Catch:{ Throwable -> 0x020a }
        r0.mo13177b();	 Catch:{ Throwable -> 0x020a }
    L_0x009a:
        r0 = r5.f870c;	 Catch:{ Throwable -> 0x00a5 }
        if (r0 == 0) goto L_0x02df;
    L_0x009e:
        r0 = r5.f873f;	 Catch:{ Throwable -> 0x00a5 }
        java.lang.Thread.sleep(r0);	 Catch:{ Throwable -> 0x00a5 }
        goto L_0x0016;
    L_0x00a5:
        r0 = move-exception;
    L_0x00a6:
        r0 = r5.mo8376a();	 Catch:{ Throwable -> 0x00b1 }
        if (r0 == 0) goto L_0x000a;
    L_0x00ac:
        r5.mo8381c();	 Catch:{ Throwable -> 0x00b1 }
        goto L_0x000a;
    L_0x00b1:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x000a;
    L_0x00b7:
        r0 = r5.f875h;	 Catch:{ Throwable -> 0x018e }
        if (r0 == 0) goto L_0x0124;
    L_0x00bb:
        r0 = r5.f875h;	 Catch:{ Throwable -> 0x018e }
        r0 = r0.f838a;	 Catch:{ Throwable -> 0x018e }
        r0 = r0.size();	 Catch:{ Throwable -> 0x018e }
        if (r0 >= 0) goto L_0x0124;
    L_0x00c5:
        if (r1 == 0) goto L_0x00e8;
    L_0x00c7:
        r0 = r5.f875h;	 Catch:{ Throwable -> 0x020a }
        r0 = r0.f843f;	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x00e8;
    L_0x00cd:
        r0 = r5.f875h;	 Catch:{ Throwable -> 0x020a }
        r0 = r0.f841d;	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x00d9;
    L_0x00d3:
        r0 = r5.m1382g();	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x00e8;
    L_0x00d9:
        r0 = android.os.Message.obtain();	 Catch:{ Throwable -> 0x020a }
        r0.obj = r1;	 Catch:{ Throwable -> 0x020a }
        r2 = 100;
        r0.what = r2;	 Catch:{ Throwable -> 0x020a }
        r2 = r5.f874g;	 Catch:{ Throwable -> 0x020a }
        r2.sendMessage(r0);	 Catch:{ Throwable -> 0x020a }
    L_0x00e8:
        r0 = com.amap.api.location.core.AuthManager.m1397a();	 Catch:{ Throwable -> 0x020a }
        if (r0 != r4) goto L_0x0111;
    L_0x00ee:
        r0 = r5.f872e;	 Catch:{ Throwable -> 0x020a }
        com.amap.api.location.core.AuthManager.m1401a(r0);	 Catch:{ Throwable -> 0x020a }
        r0 = r5.f872e;	 Catch:{ Throwable -> 0x020a }
        com.amap.api.location.core.AuthManager.m1404b(r0);	 Catch:{ Throwable -> 0x020a }
        r0 = com.amap.api.location.core.AuthManager.m1416i();	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x0111;
    L_0x00fe:
        r0 = android.os.Message.obtain();	 Catch:{ Throwable -> 0x020a }
        r0.obj = r1;	 Catch:{ Throwable -> 0x020a }
        r1 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        r0.what = r1;	 Catch:{ Throwable -> 0x020a }
        r1 = r5.f874g;	 Catch:{ Throwable -> 0x020a }
        if (r1 == 0) goto L_0x0111;
    L_0x010c:
        r1 = r5.f874g;	 Catch:{ Throwable -> 0x020a }
        r1.sendMessage(r0);	 Catch:{ Throwable -> 0x020a }
    L_0x0111:
        r0 = r5.f868a;	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x011a;
    L_0x0115:
        r0 = r5.f868a;	 Catch:{ Throwable -> 0x020a }
        r0.mo13177b();	 Catch:{ Throwable -> 0x020a }
    L_0x011a:
        r0 = r5.f870c;	 Catch:{ Throwable -> 0x00a5 }
        if (r0 == 0) goto L_0x02e6;
    L_0x011e:
        r0 = r5.f873f;	 Catch:{ Throwable -> 0x00a5 }
        java.lang.Thread.sleep(r0);	 Catch:{ Throwable -> 0x00a5 }
        goto L_0x00a6;
    L_0x0124:
        r0 = r5.m1380e();	 Catch:{ Throwable -> 0x018e }
        if (r0 == 0) goto L_0x012e;
    L_0x012a:
        r1 = r5.m1379a(r0);	 Catch:{ Throwable -> 0x018e }
    L_0x012e:
        if (r1 == 0) goto L_0x0151;
    L_0x0130:
        r0 = r5.f875h;	 Catch:{ Throwable -> 0x020a }
        r0 = r0.f843f;	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x0151;
    L_0x0136:
        r0 = r5.f875h;	 Catch:{ Throwable -> 0x020a }
        r0 = r0.f841d;	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x0142;
    L_0x013c:
        r0 = r5.m1382g();	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x0151;
    L_0x0142:
        r0 = android.os.Message.obtain();	 Catch:{ Throwable -> 0x020a }
        r0.obj = r1;	 Catch:{ Throwable -> 0x020a }
        r2 = 100;
        r0.what = r2;	 Catch:{ Throwable -> 0x020a }
        r2 = r5.f874g;	 Catch:{ Throwable -> 0x020a }
        r2.sendMessage(r0);	 Catch:{ Throwable -> 0x020a }
    L_0x0151:
        r0 = com.amap.api.location.core.AuthManager.m1397a();	 Catch:{ Throwable -> 0x020a }
        if (r0 != r4) goto L_0x017a;
    L_0x0157:
        r0 = r5.f872e;	 Catch:{ Throwable -> 0x020a }
        com.amap.api.location.core.AuthManager.m1401a(r0);	 Catch:{ Throwable -> 0x020a }
        r0 = r5.f872e;	 Catch:{ Throwable -> 0x020a }
        com.amap.api.location.core.AuthManager.m1404b(r0);	 Catch:{ Throwable -> 0x020a }
        r0 = com.amap.api.location.core.AuthManager.m1416i();	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x017a;
    L_0x0167:
        r0 = android.os.Message.obtain();	 Catch:{ Throwable -> 0x020a }
        r0.obj = r1;	 Catch:{ Throwable -> 0x020a }
        r1 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        r0.what = r1;	 Catch:{ Throwable -> 0x020a }
        r1 = r5.f874g;	 Catch:{ Throwable -> 0x020a }
        if (r1 == 0) goto L_0x017a;
    L_0x0175:
        r1 = r5.f874g;	 Catch:{ Throwable -> 0x020a }
        r1.sendMessage(r0);	 Catch:{ Throwable -> 0x020a }
    L_0x017a:
        r0 = r5.f868a;	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x0183;
    L_0x017e:
        r0 = r5.f868a;	 Catch:{ Throwable -> 0x020a }
        r0.mo13177b();	 Catch:{ Throwable -> 0x020a }
    L_0x0183:
        r0 = r5.f870c;	 Catch:{ Throwable -> 0x00a5 }
        if (r0 == 0) goto L_0x02ed;
    L_0x0187:
        r0 = r5.f873f;	 Catch:{ Throwable -> 0x00a5 }
        java.lang.Thread.sleep(r0);	 Catch:{ Throwable -> 0x00a5 }
        goto L_0x0016;
    L_0x018e:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0210 }
        r2 = new com.amap.api.location.AMapLocation;	 Catch:{ all -> 0x0210 }
        r0 = "";
        r2.<init>(r0);	 Catch:{ all -> 0x0210 }
        r0 = new com.amap.api.location.core.AMapLocException;	 Catch:{ all -> 0x02f4 }
        r1 = "服务器异常";
        r0.<init>(r1);	 Catch:{ all -> 0x02f4 }
        r2.setAMapException(r0);	 Catch:{ all -> 0x02f4 }
        if (r2 == 0) goto L_0x01c7;
    L_0x01a6:
        r0 = r5.f875h;	 Catch:{ Throwable -> 0x020a }
        r0 = r0.f843f;	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x01c7;
    L_0x01ac:
        r0 = r5.f875h;	 Catch:{ Throwable -> 0x020a }
        r0 = r0.f841d;	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x01b8;
    L_0x01b2:
        r0 = r5.m1382g();	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x01c7;
    L_0x01b8:
        r0 = android.os.Message.obtain();	 Catch:{ Throwable -> 0x020a }
        r0.obj = r2;	 Catch:{ Throwable -> 0x020a }
        r1 = 100;
        r0.what = r1;	 Catch:{ Throwable -> 0x020a }
        r1 = r5.f874g;	 Catch:{ Throwable -> 0x020a }
        r1.sendMessage(r0);	 Catch:{ Throwable -> 0x020a }
    L_0x01c7:
        r0 = com.amap.api.location.core.AuthManager.m1397a();	 Catch:{ Throwable -> 0x020a }
        if (r0 != r4) goto L_0x01f0;
    L_0x01cd:
        r0 = r5.f872e;	 Catch:{ Throwable -> 0x020a }
        com.amap.api.location.core.AuthManager.m1401a(r0);	 Catch:{ Throwable -> 0x020a }
        r0 = r5.f872e;	 Catch:{ Throwable -> 0x020a }
        com.amap.api.location.core.AuthManager.m1404b(r0);	 Catch:{ Throwable -> 0x020a }
        r0 = com.amap.api.location.core.AuthManager.m1416i();	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x01f0;
    L_0x01dd:
        r0 = android.os.Message.obtain();	 Catch:{ Throwable -> 0x020a }
        r0.obj = r2;	 Catch:{ Throwable -> 0x020a }
        r1 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        r0.what = r1;	 Catch:{ Throwable -> 0x020a }
        r1 = r5.f874g;	 Catch:{ Throwable -> 0x020a }
        if (r1 == 0) goto L_0x01f0;
    L_0x01eb:
        r1 = r5.f874g;	 Catch:{ Throwable -> 0x020a }
        r1.sendMessage(r0);	 Catch:{ Throwable -> 0x020a }
    L_0x01f0:
        r0 = r5.f868a;	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x01f9;
    L_0x01f4:
        r0 = r5.f868a;	 Catch:{ Throwable -> 0x020a }
        r0.mo13177b();	 Catch:{ Throwable -> 0x020a }
    L_0x01f9:
        r0 = r5.f870c;	 Catch:{ Throwable -> 0x00a5 }
        if (r0 == 0) goto L_0x0270;
    L_0x01fd:
        r0 = r5.f873f;	 Catch:{ Throwable -> 0x00a5 }
        java.lang.Thread.sleep(r0);	 Catch:{ Throwable -> 0x00a5 }
        goto L_0x0016;
    L_0x0204:
        r2 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        java.lang.Thread.sleep(r2);	 Catch:{ Throwable -> 0x00a5 }
    L_0x0209:
        throw r0;	 Catch:{ Throwable -> 0x020a }
    L_0x020a:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00a6;
    L_0x0210:
        r0 = move-exception;
    L_0x0211:
        if (r1 == 0) goto L_0x0234;
    L_0x0213:
        r2 = r5.f875h;	 Catch:{ Throwable -> 0x020a }
        r2 = r2.f843f;	 Catch:{ Throwable -> 0x020a }
        if (r2 == 0) goto L_0x0234;
    L_0x0219:
        r2 = r5.f875h;	 Catch:{ Throwable -> 0x020a }
        r2 = r2.f841d;	 Catch:{ Throwable -> 0x020a }
        if (r2 == 0) goto L_0x0225;
    L_0x021f:
        r2 = r5.m1382g();	 Catch:{ Throwable -> 0x020a }
        if (r2 == 0) goto L_0x0234;
    L_0x0225:
        r2 = android.os.Message.obtain();	 Catch:{ Throwable -> 0x020a }
        r2.obj = r1;	 Catch:{ Throwable -> 0x020a }
        r3 = 100;
        r2.what = r3;	 Catch:{ Throwable -> 0x020a }
        r3 = r5.f874g;	 Catch:{ Throwable -> 0x020a }
        r3.sendMessage(r2);	 Catch:{ Throwable -> 0x020a }
    L_0x0234:
        r2 = com.amap.api.location.core.AuthManager.m1397a();	 Catch:{ Throwable -> 0x020a }
        if (r2 != r4) goto L_0x025d;
    L_0x023a:
        r2 = r5.f872e;	 Catch:{ Throwable -> 0x020a }
        com.amap.api.location.core.AuthManager.m1401a(r2);	 Catch:{ Throwable -> 0x020a }
        r2 = r5.f872e;	 Catch:{ Throwable -> 0x020a }
        com.amap.api.location.core.AuthManager.m1404b(r2);	 Catch:{ Throwable -> 0x020a }
        r2 = com.amap.api.location.core.AuthManager.m1416i();	 Catch:{ Throwable -> 0x020a }
        if (r2 == 0) goto L_0x025d;
    L_0x024a:
        r2 = android.os.Message.obtain();	 Catch:{ Throwable -> 0x020a }
        r2.obj = r1;	 Catch:{ Throwable -> 0x020a }
        r1 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        r2.what = r1;	 Catch:{ Throwable -> 0x020a }
        r1 = r5.f874g;	 Catch:{ Throwable -> 0x020a }
        if (r1 == 0) goto L_0x025d;
    L_0x0258:
        r1 = r5.f874g;	 Catch:{ Throwable -> 0x020a }
        r1.sendMessage(r2);	 Catch:{ Throwable -> 0x020a }
    L_0x025d:
        r1 = r5.f868a;	 Catch:{ Throwable -> 0x020a }
        if (r1 == 0) goto L_0x0266;
    L_0x0261:
        r1 = r5.f868a;	 Catch:{ Throwable -> 0x020a }
        r1.mo13177b();	 Catch:{ Throwable -> 0x020a }
    L_0x0266:
        r1 = r5.f870c;	 Catch:{ Throwable -> 0x00a5 }
        if (r1 == 0) goto L_0x0204;
    L_0x026a:
        r2 = r5.f873f;	 Catch:{ Throwable -> 0x00a5 }
        java.lang.Thread.sleep(r2);	 Catch:{ Throwable -> 0x00a5 }
        goto L_0x0209;
    L_0x0270:
        r0 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        java.lang.Thread.sleep(r0);	 Catch:{ Throwable -> 0x00a5 }
        goto L_0x0016;
    L_0x0277:
        r0 = move-exception;
        if (r1 == 0) goto L_0x029b;
    L_0x027a:
        r0 = r5.f875h;	 Catch:{ Throwable -> 0x020a }
        r0 = r0.f843f;	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x029b;
    L_0x0280:
        r0 = r5.f875h;	 Catch:{ Throwable -> 0x020a }
        r0 = r0.f841d;	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x028c;
    L_0x0286:
        r0 = r5.m1382g();	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x029b;
    L_0x028c:
        r0 = android.os.Message.obtain();	 Catch:{ Throwable -> 0x020a }
        r0.obj = r1;	 Catch:{ Throwable -> 0x020a }
        r2 = 100;
        r0.what = r2;	 Catch:{ Throwable -> 0x020a }
        r2 = r5.f874g;	 Catch:{ Throwable -> 0x020a }
        r2.sendMessage(r0);	 Catch:{ Throwable -> 0x020a }
    L_0x029b:
        r0 = com.amap.api.location.core.AuthManager.m1397a();	 Catch:{ Throwable -> 0x020a }
        if (r0 != r4) goto L_0x02c4;
    L_0x02a1:
        r0 = r5.f872e;	 Catch:{ Throwable -> 0x020a }
        com.amap.api.location.core.AuthManager.m1401a(r0);	 Catch:{ Throwable -> 0x020a }
        r0 = r5.f872e;	 Catch:{ Throwable -> 0x020a }
        com.amap.api.location.core.AuthManager.m1404b(r0);	 Catch:{ Throwable -> 0x020a }
        r0 = com.amap.api.location.core.AuthManager.m1416i();	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x02c4;
    L_0x02b1:
        r0 = android.os.Message.obtain();	 Catch:{ Throwable -> 0x020a }
        r0.obj = r1;	 Catch:{ Throwable -> 0x020a }
        r1 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        r0.what = r1;	 Catch:{ Throwable -> 0x020a }
        r1 = r5.f874g;	 Catch:{ Throwable -> 0x020a }
        if (r1 == 0) goto L_0x02c4;
    L_0x02bf:
        r1 = r5.f874g;	 Catch:{ Throwable -> 0x020a }
        r1.sendMessage(r0);	 Catch:{ Throwable -> 0x020a }
    L_0x02c4:
        r0 = r5.f868a;	 Catch:{ Throwable -> 0x020a }
        if (r0 == 0) goto L_0x02cd;
    L_0x02c8:
        r0 = r5.f868a;	 Catch:{ Throwable -> 0x020a }
        r0.mo13177b();	 Catch:{ Throwable -> 0x020a }
    L_0x02cd:
        r0 = r5.f870c;	 Catch:{ Throwable -> 0x00a5 }
        if (r0 == 0) goto L_0x02d8;
    L_0x02d1:
        r0 = r5.f873f;	 Catch:{ Throwable -> 0x00a5 }
        java.lang.Thread.sleep(r0);	 Catch:{ Throwable -> 0x00a5 }
        goto L_0x00a6;
    L_0x02d8:
        r0 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        java.lang.Thread.sleep(r0);	 Catch:{ Throwable -> 0x00a5 }
        goto L_0x00a6;
    L_0x02df:
        r0 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        java.lang.Thread.sleep(r0);	 Catch:{ Throwable -> 0x00a5 }
        goto L_0x0016;
    L_0x02e6:
        r0 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        java.lang.Thread.sleep(r0);	 Catch:{ Throwable -> 0x00a5 }
        goto L_0x00a6;
    L_0x02ed:
        r0 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        java.lang.Thread.sleep(r0);	 Catch:{ Throwable -> 0x00a5 }
        goto L_0x0016;
    L_0x02f4:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0211;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.location.IAPSManager.run():void");
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8372a(long j) {
        if (j > this.f873f) {
            this.f873f = j;
        }
    }

    /* renamed from: e */
    private AmapLoc m1380e() throws Exception {
        AmapLoc f = m1381f();
        if (f != null) {
            return f;
        }
        f = new AmapLoc();
        f.mo13195a(new AMapLocException(AMapLocException.ERROR_UNKNOWN));
        this.f870c = false;
        return f;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0016 A:{ExcHandler: AMapLocException (r0_5 'e' com.amap.api.location.core.AMapLocException), Splitter:B:1:0x0002} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:10:0x0016, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:11:0x0017, code skipped:
            r1 = r0;
            r0 = new com.aps.AmapLoc();
            r0.mo13195a(r1);
            r4.f870c = false;
     */
    /* JADX WARNING: Missing block: B:12:0x0023, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:13:0x0024, code skipped:
            r3 = r0;
            r0 = null;
            r1 = r3;
     */
    /* JADX WARNING: Missing block: B:18:?, code skipped:
            return r0;
     */
    /* renamed from: f */
    private com.aps.AmapLoc m1381f() {
        /*
        r4 = this;
        r2 = 0;
        r1 = 0;
        r0 = r4.f868a;	 Catch:{ AMapLocException -> 0x0016, Throwable -> 0x0023 }
        if (r0 == 0) goto L_0x002f;
    L_0x0006:
        r0 = r4.f868a;	 Catch:{ AMapLocException -> 0x0016, Throwable -> 0x0023 }
        r0 = r0.mo13170a();	 Catch:{ AMapLocException -> 0x0016, Throwable -> 0x0023 }
    L_0x000c:
        if (r0 != 0) goto L_0x0012;
    L_0x000e:
        r1 = 0;
        r4.f870c = r1;	 Catch:{ AMapLocException -> 0x0016, Throwable -> 0x002d }
    L_0x0011:
        return r0;
    L_0x0012:
        r1 = 1;
        r4.f870c = r1;	 Catch:{ AMapLocException -> 0x0016, Throwable -> 0x002d }
        goto L_0x0011;
    L_0x0016:
        r0 = move-exception;
        r1 = r0;
        r0 = new com.aps.c;
        r0.<init>();
        r0.mo13195a(r1);
        r4.f870c = r2;
        goto L_0x0011;
    L_0x0023:
        r0 = move-exception;
        r3 = r0;
        r0 = r1;
        r1 = r3;
    L_0x0027:
        r4.f870c = r2;
        r1.printStackTrace();
        goto L_0x0011;
    L_0x002d:
        r1 = move-exception;
        goto L_0x0027;
    L_0x002f:
        r0 = r1;
        goto L_0x000c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.location.IAPSManager.m1381f():com.aps.c");
    }

    /* renamed from: g */
    private boolean m1382g() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - this.f875h.f846i <= 10000 || elapsedRealtime - this.f875h.f842e <= 10000) {
            return false;
        }
        this.f875h.f841d = false;
        return true;
    }

    /* renamed from: a */
    private AMapLocation m1379a(AmapLoc amapLoc) {
        AMapLocation aMapLocation = new AMapLocation("");
        aMapLocation.setProvider(LocationProviderProxy.AMapNetwork);
        aMapLocation.setLatitude(amapLoc.mo13212h());
        aMapLocation.setLongitude(amapLoc.mo13210g());
        aMapLocation.setAccuracy(amapLoc.mo13214i());
        aMapLocation.setTime(amapLoc.mo13216j());
        aMapLocation.setPoiId(amapLoc.mo13198b());
        aMapLocation.setFloor(amapLoc.mo13202c());
        aMapLocation.setCountry(amapLoc.mo13228p());
        aMapLocation.setRoad(amapLoc.mo13233s());
        aMapLocation.setPoiName(amapLoc.mo13235u());
        aMapLocation.setAMapException(amapLoc.mo13191a());
        Bundle bundle = new Bundle();
        bundle.putString("citycode", amapLoc.mo13222m());
        bundle.putString("desc", amapLoc.mo13224n());
        bundle.putString("adcode", amapLoc.mo13226o());
        aMapLocation.setExtras(bundle);
        String m = amapLoc.mo13222m();
        String n = amapLoc.mo13224n();
        String o = amapLoc.mo13226o();
        aMapLocation.setCityCode(m);
        aMapLocation.setAdCode(o);
        if (o == null || o.trim().length() <= 0) {
            aMapLocation.setAddress(n);
        } else {
            aMapLocation.setAddress(n.replace(" ", ""));
        }
        aMapLocation.setCity(amapLoc.mo13232r());
        aMapLocation.setDistrict(amapLoc.mo13204d());
        aMapLocation.mo8269a(amapLoc.mo13234t());
        aMapLocation.setProvince(amapLoc.mo13230q());
        return aMapLocation;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8374a(Fence fence, PendingIntent pendingIntent) {
        this.f868a.mo13174a(fence, pendingIntent);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public void mo8379b(Fence fence, PendingIntent pendingIntent) {
        this.f868a.mo13179b(fence, pendingIntent);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8373a(PendingIntent pendingIntent) {
        this.f868a.mo13171a(pendingIntent);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public void mo8378b(PendingIntent pendingIntent) {
        this.f868a.mo13178b(pendingIntent);
    }
}
