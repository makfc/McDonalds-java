package com.amap.api.location.core;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.aps.APS;
import com.aps.AmapLoc;
import com.aps.Const;
import com.aps.IAPS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketService extends Service {
    /* renamed from: a */
    IAPS f886a;
    /* renamed from: b */
    String f887b = null;
    /* renamed from: c */
    C0726a f888c = null;
    /* renamed from: d */
    ServerSocket f889d = null;
    /* renamed from: e */
    boolean f890e = false;
    /* renamed from: f */
    Socket f891f = null;
    /* renamed from: g */
    AmapLoc f892g;
    /* renamed from: h */
    private boolean f893h = false;

    /* renamed from: com.amap.api.location.core.SocketService$a */
    class C0726a extends Thread {
        /* renamed from: a */
        int f883a = 43689;
        /* renamed from: b */
        SocketService f884b;

        C0726a(SocketService socketService) {
            this.f884b = socketService;
        }

        public void run() {
            try {
                if (this.f884b != null) {
                    this.f884b.apsInit();
                }
                SocketService.this.f889d = new ServerSocket(this.f883a);
                while (SocketService.this.f890e) {
                    SocketService.this.f891f = SocketService.this.f889d.accept();
                    SocketService.this.m1396a(SocketService.this.f891f);
                }
            } catch (IOException e) {
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.f886a = new APS();
        startSocket();
        this.f887b = getApplicationContext().getPackageName();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }

    public void onDestroy() {
        stopScocket();
        this.f886a.mo13180c();
        super.onDestroy();
    }

    public void startSocket() {
        try {
            if (!this.f890e) {
                this.f890e = true;
                if (this.f888c == null) {
                    this.f888c = new C0726a(this);
                }
                this.f888c.start();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void stopScocket() {
        try {
            if (this.f889d != null) {
                this.f889d.close();
            }
            if (this.f891f != null) {
                this.f891f.close();
            }
        } catch (IOException e) {
        }
        try {
            this.f888c = null;
            this.f890e = false;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m1396a(Socket socket) {
        Throwable th;
        PrintStream printStream;
        PrintStream printStream2;
        Object obj;
        String str = null;
        int i = 0;
        if (socket != null) {
            int i2 = 30000;
            String str2 = "jsonp1";
            System.currentTimeMillis();
            BufferedReader obj2;
            try {
                obj2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                try {
                    String readLine = obj2.readLine();
                    if (readLine != null && readLine.length() > 0) {
                        String[] split = readLine.split(" ");
                        if (split != null && split.length > 1) {
                            split = split[1].split("\\?");
                            if (split != null && split.length > 1) {
                                split = split[1].split("&");
                                if (split != null && split.length > 0) {
                                    String str3 = str2;
                                    int i3 = 30000;
                                    String str4 = str3;
                                    while (i < split.length) {
                                        try {
                                            String[] split2 = split[i].split("=");
                                            if (split2 != null && split2.length > 1) {
                                                if ("to".equals(split2[0])) {
                                                    i3 = Integer.parseInt(split2[1]);
                                                }
                                                if ("callback".equals(split2[0])) {
                                                    str4 = split2[1];
                                                }
                                                if ("_".equals(split2[0])) {
                                                    Long.parseLong(split2[1]);
                                                }
                                            }
                                            i++;
                                        } catch (Throwable th2) {
                                            Throwable th3 = th2;
                                            str2 = str4;
                                            th = th3;
                                            try {
                                                str = str2 + "&&" + str2 + "({'package':'" + this.f887b + "','error_code':35,'error':'params error'})";
                                                th.printStackTrace();
                                                try {
                                                    printStream = new PrintStream(socket.getOutputStream(), true);
                                                    printStream.println("HTTP/1.0 200 OK");
                                                    printStream.println("Content-Length:" + str.getBytes().length);
                                                    printStream.println();
                                                    printStream.println(str);
                                                    printStream.close();
                                                    obj2.close();
                                                    socket.close();
                                                } catch (Exception e) {
                                                    obj2.close();
                                                    socket.close();
                                                    return;
                                                } catch (Throwable th4) {
                                                    try {
                                                        obj2.close();
                                                        socket.close();
                                                    } catch (Exception e2) {
                                                    }
                                                    throw th4;
                                                }
                                            } catch (Throwable th5) {
                                                th4 = th5;
                                                try {
                                                    printStream2 = new PrintStream(socket.getOutputStream(), true);
                                                    printStream2.println("HTTP/1.0 200 OK");
                                                    printStream2.println("Content-Length:" + str.getBytes().length);
                                                    printStream2.println();
                                                    printStream2.println(str);
                                                    printStream2.close();
                                                    try {
                                                        obj2.close();
                                                        socket.close();
                                                    } catch (Exception e3) {
                                                    }
                                                } catch (Exception e4) {
                                                    obj2.close();
                                                    socket.close();
                                                    throw th4;
                                                } catch (Throwable th42) {
                                                    try {
                                                        obj2.close();
                                                        socket.close();
                                                    } catch (Exception e5) {
                                                    }
                                                    throw th42;
                                                }
                                                throw th42;
                                            }
                                        }
                                    }
                                    str3 = str4;
                                    i2 = i3;
                                    str2 = str3;
                                }
                            }
                        }
                    }
                    i = Const.f4440g;
                    Const.f4440g = i2;
                    long currentTimeMillis = System.currentTimeMillis();
                    if ((this.f892g == null || currentTimeMillis - this.f892g.mo13216j() > 5000) && !CoreUtil.m1462c(this)) {
                        this.f892g = this.f886a.mo13170a();
                        this.f886a.mo13177b();
                        Const.f4440g = i;
                    }
                } catch (AMapLocException e6) {
                    str = str2 + "&&" + str2 + "({'package':'" + this.f887b + "','error_code':" + e6.getErrorCode() + ",'error':'" + e6.getErrorMessage() + "'})";
                    this.f886a.mo13177b();
                    Const.f4440g = i;
                } catch (Exception e7) {
                    e7.printStackTrace();
                    this.f886a.mo13177b();
                    Const.f4440g = i;
                } catch (Throwable th6) {
                    th42 = th6;
                }
                if (str == null) {
                    if (this.f892g == null) {
                        str = str2 + "&&" + str2 + "({'package':'" + this.f887b + "','error_code':31,'error':'unknown error'})";
                    } else {
                        AmapLoc amapLoc = this.f892g;
                        str = str2 + "&&" + str2 + "({'package':'" + this.f887b + "','error_code':0,'error':'','location':{'y':" + amapLoc.mo13212h() + ",'precision':" + amapLoc.mo13214i() + ",'x':" + amapLoc.mo13210g() + "},'version_code':'" + "1.4.1" + "','version':'" + "1.4.1" + "'})";
                    }
                    if (CoreUtil.m1462c(this)) {
                        str = str2 + "&&" + str2 + "({'package':'" + this.f887b + "','error_code':36,'error':'app is background'})";
                    }
                }
                try {
                    printStream = new PrintStream(socket.getOutputStream(), true);
                    printStream.println("HTTP/1.0 200 OK");
                    printStream.println("Content-Length:" + str.getBytes().length);
                    printStream.println();
                    printStream.println(str);
                    printStream.close();
                    try {
                        obj2.close();
                        socket.close();
                    } catch (Exception e8) {
                    }
                } catch (Exception e9) {
                    obj2.close();
                    socket.close();
                } catch (Throwable th422) {
                    try {
                        obj2.close();
                        socket.close();
                    } catch (Exception e10) {
                    }
                    throw th422;
                }
            } catch (Throwable th7) {
                th422 = th7;
                obj2 = str;
                printStream2 = new PrintStream(socket.getOutputStream(), true);
                printStream2.println("HTTP/1.0 200 OK");
                printStream2.println("Content-Length:" + str.getBytes().length);
                printStream2.println();
                printStream2.println(str);
                printStream2.close();
                obj2.close();
                socket.close();
                throw th422;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0095 A:{ExcHandler: JSONException (r0_18 'e' org.json.JSONException), Splitter:B:0:0x0000} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0095 A:{ExcHandler: JSONException (r0_18 'e' org.json.JSONException), Splitter:B:0:0x0000} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:21:0x0095, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:22:0x0096, code skipped:
            r0.printStackTrace();
     */
    /* JADX WARNING: Missing block: B:26:0x00a0, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:27:0x00a1, code skipped:
            r0.printStackTrace();
     */
    /* JADX WARNING: Missing block: B:32:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:33:?, code skipped:
            return;
     */
    public void apsInit() {
        /*
        r5 = this;
        r0 = r5.f893h;	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        if (r0 == 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        android.os.Looper.prepare();	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        com.amap.api.location.core.ClientInfoUtil.m1421a(r5);	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        r0 = r5.f886a;	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        if (r0 == 0) goto L_0x0014;
    L_0x000f:
        r0 = r5.f886a;	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        r0.mo13172a(r5);	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
    L_0x0014:
        r0 = r5.f886a;	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        if (r0 == 0) goto L_0x0042;
    L_0x0018:
        r0 = r5.f886a;	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        r1.<init>();	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        r2 = "api_serverSDK_130905##S128DF1572465B890OE3F7A13167KLEI##";
        r1 = r1.append(r2);	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        r2 = com.amap.api.location.core.ClientInfoUtil.m1439h(r5);	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        r1 = r1.append(r2);	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        r2 = ",";
        r1 = r1.append(r2);	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        r2 = com.amap.api.location.core.ClientInfoUtil.m1425b();	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        r1 = r1.append(r2);	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        r0.mo13175a(r1);	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
    L_0x0042:
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        r1.<init>();	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        r0 = "key";
        r2 = com.amap.api.location.core.ClientInfoUtil.m1439h(r5);	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        r0 = "X-INFO";
        r2 = com.amap.api.location.core.ClientInfoUtil.m1421a(r5);	 Catch:{ Throwable -> 0x009b, JSONException -> 0x0095 }
        r3 = "loc";
        r2 = r2.mo8404a(r3);	 Catch:{ Throwable -> 0x009b, JSONException -> 0x0095 }
        r1.put(r0, r2);	 Catch:{ Throwable -> 0x009b, JSONException -> 0x0095 }
    L_0x005f:
        r2 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        r2.<init>();	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        r0 = com.amap.api.location.core.ClientInfoUtil.m1421a(r5);	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        r0 = r0.mo8406c();	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        r3 = "ex";
        r4 = "UTF-8";
        r0 = r0.getBytes(r4);	 Catch:{ UnsupportedEncodingException -> 0x00a6 }
        r0 = com.amap.api.location.core.Base64Util.m1419a(r0);	 Catch:{ UnsupportedEncodingException -> 0x00a6 }
        r2.put(r3, r0);	 Catch:{ UnsupportedEncodingException -> 0x00a6 }
    L_0x007b:
        r0 = "X-BIZ";
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        r0 = "User-Agent";
        r2 = "AMAP_Location_SDK_Android 1.4.1";
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        r0 = r5.f886a;	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        if (r0 == 0) goto L_0x0090;
    L_0x008b:
        r0 = r5.f886a;	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        r0.mo13176a(r1);	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
    L_0x0090:
        r0 = 1;
        r5.f893h = r0;	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        goto L_0x0004;
    L_0x0095:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0004;
    L_0x009b:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        goto L_0x005f;
    L_0x00a0:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0004;
    L_0x00a6:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ JSONException -> 0x0095, Throwable -> 0x00a0 }
        goto L_0x007b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.location.core.SocketService.apsInit():void");
    }
}
