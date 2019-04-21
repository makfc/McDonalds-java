package com.baidu.android.pushservice.richmedia;

import android.content.Context;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.richmedia.C1527c.C1526a;
import com.baidu.android.pushservice.util.C1568q;
import com.baidu.android.pushservice.util.C1568q.C1563h;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;

/* renamed from: com.baidu.android.pushservice.richmedia.a */
public class C1524a extends Thread implements Comparable<C1524a> {
    /* renamed from: e */
    public static int f5334e = 1;
    /* renamed from: f */
    public static int f5335f = 2;
    /* renamed from: g */
    private static HashSet<C1527c> f5336g = new HashSet();
    /* renamed from: a */
    protected C1303f f5337a;
    /* renamed from: b */
    public WeakReference<Context> f5338b;
    /* renamed from: c */
    protected long f5339c;
    /* renamed from: d */
    public C1527c f5340d;
    /* renamed from: h */
    private boolean f5341h = false;

    public C1524a(Context context, C1303f c1303f, C1527c c1527c) {
        this.f5337a = c1303f;
        this.f5338b = new WeakReference(context);
        this.f5339c = System.currentTimeMillis();
        this.f5340d = c1527c;
    }

    /* renamed from: a */
    private int m6866a(String str) {
        try {
            return ((HttpURLConnection) HttpInstrumentation.openConnection(new URL(str).openConnection())).getContentLength();
        } catch (MalformedURLException e) {
            C1425a.m6444e("HttpTaskThread", "error " + e.getMessage());
        } catch (IOException e2) {
            C1425a.m6444e("HttpTaskThread", "error " + e2.getMessage());
        }
        return 0;
    }

    /* renamed from: a */
    private C1563h m6867a(Context context, String str) {
        List b = C1568q.m7002b(context);
        if (b != null) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= b.size()) {
                    break;
                } else if (((C1563h) b.get(i2)).f5473b.equalsIgnoreCase(str)) {
                    return (C1563h) b.get(i2);
                } else {
                    i = i2 + 1;
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    private void m6868a(C1530e c1530e) {
        try {
            if (this.f5337a == null || c1530e == null) {
                C1524a.m6872b(this.f5340d);
                return;
            }
            if (c1530e.f5362c == 0) {
                String str = c1530e.f5364e;
                if (c1530e.f5360a == C1526a.REQ_TYPE_GET_ZIP && str != null) {
                    String substring = str.substring(0, str.lastIndexOf("."));
                    File file = new File(str);
                    C1524a.m6869a(file, substring);
                    file.delete();
                    c1530e.f5364e = substring;
                }
                this.f5337a.mo13548a(this, c1530e);
            } else if (c1530e.f5362c == 1) {
                this.f5337a.mo13549a(this, new Throwable("error: response http error errorCode=" + c1530e.f5361b));
            } else if (c1530e.f5362c == 3) {
                this.f5337a.mo13549a(this, new Throwable("error: request error,request is null or fileName is null."));
            } else if (c1530e.f5362c == 2) {
                this.f5337a.mo13550b(this);
            } else if (c1530e.f5362c == -1) {
                this.f5337a.mo13549a(this, new Throwable("IOException"));
            }
            C1524a.m6872b(this.f5340d);
        } catch (Throwable th) {
            C1524a.m6872b(this.f5340d);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:56:0x012d A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:8:0x0018, PHI: r1 r2 } */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:56:0x012d, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:67:0x0149, code skipped:
            r0 = e;
     */
    /* renamed from: a */
    private static void m6869a(java.io.File r12, java.lang.String r13) {
        /*
        r8 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r4 = 0;
        r6 = 0;
        r3 = 0;
        r2 = 0;
        r1 = 0;
        r7 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x0139, all -> 0x0102 }
        r7.<init>(r12);	 Catch:{ Exception -> 0x0139, all -> 0x0102 }
        r4 = new java.io.BufferedInputStream;	 Catch:{ Exception -> 0x013e, all -> 0x011e }
        r4.<init>(r7);	 Catch:{ Exception -> 0x013e, all -> 0x011e }
        r5 = new java.util.zip.ZipInputStream;	 Catch:{ Exception -> 0x0143, all -> 0x0124 }
        r5.<init>(r4);	 Catch:{ Exception -> 0x0143, all -> 0x0124 }
        r1 = r2;
        r2 = r3;
    L_0x0018:
        r3 = r5.getNextEntry();	 Catch:{ Exception -> 0x00ad, all -> 0x012d }
        if (r3 == 0) goto L_0x00ec;
    L_0x001e:
        r0 = "DownloadCompleteReceiver: ";
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        r6.<init>();	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        r9 = "unzip----=";
        r6 = r6.append(r9);	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        r6 = r6.append(r3);	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        r6 = r6.toString();	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        com.baidu.android.pushservice.p036h.C1425a.m6441b(r0, r6);	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        r6 = new byte[r8];	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        r9 = r3.getName();	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        r0 = 0;
        r10 = r9.length();	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        if (r10 <= 0) goto L_0x004a;
    L_0x0044:
        r0 = "/";
        r0 = r9.split(r0);	 Catch:{ Exception -> 0x0149, all -> 0x012d }
    L_0x004a:
        r9 = new java.io.File;	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        r10 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        r10.<init>();	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        r10 = r10.append(r13);	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        r11 = "/";
        r10 = r10.append(r11);	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        r11 = r0.length;	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        r11 = r11 + -1;
        r0 = r0[r11];	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        r0 = r10.append(r0);	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        r9.<init>(r0);	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        r0 = r3.isDirectory();	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        if (r0 != 0) goto L_0x0018;
    L_0x0071:
        r0 = new java.io.File;	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        r3 = r9.getParent();	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        r0.<init>(r3);	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        r3 = r0.exists();	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        if (r3 != 0) goto L_0x0083;
    L_0x0080:
        r0.mkdirs();	 Catch:{ Exception -> 0x0149, all -> 0x012d }
    L_0x0083:
        r0 = r9.exists();	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        if (r0 != 0) goto L_0x008c;
    L_0x0089:
        r9.createNewFile();	 Catch:{ Exception -> 0x0149, all -> 0x012d }
    L_0x008c:
        r3 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        r3.<init>(r9);	 Catch:{ Exception -> 0x0149, all -> 0x012d }
        r2 = new java.io.BufferedOutputStream;	 Catch:{ Exception -> 0x014c, all -> 0x012f }
        r2.<init>(r3, r8);	 Catch:{ Exception -> 0x014c, all -> 0x012f }
    L_0x0096:
        r0 = 0;
        r0 = r5.read(r6, r0, r8);	 Catch:{ Exception -> 0x00a3, all -> 0x0129 }
        r1 = -1;
        if (r0 == r1) goto L_0x00e5;
    L_0x009e:
        r1 = 0;
        r2.write(r6, r1, r0);	 Catch:{ Exception -> 0x00a3, all -> 0x0129 }
        goto L_0x0096;
    L_0x00a3:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
    L_0x00a6:
        r3 = "HttpTaskThread";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r3, r0);	 Catch:{ Exception -> 0x00ad, all -> 0x012d }
        goto L_0x0018;
    L_0x00ad:
        r0 = move-exception;
        r3 = r2;
        r2 = r1;
        r1 = r4;
        r4 = r5;
        r5 = r7;
    L_0x00b3:
        r6 = "HttpTaskThread";
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0132 }
        r7.<init>();	 Catch:{ all -> 0x0132 }
        r8 = "error ";
        r7 = r7.append(r8);	 Catch:{ all -> 0x0132 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0132 }
        r0 = r7.append(r0);	 Catch:{ all -> 0x0132 }
        r0 = r0.toString();	 Catch:{ all -> 0x0132 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r6, r0);	 Catch:{ all -> 0x0132 }
        r0 = 5;
        r0 = new java.io.Closeable[r0];
        r6 = 0;
        r0[r6] = r5;
        r5 = 1;
        r0[r5] = r4;
        r4 = 2;
        r0[r4] = r3;
        r3 = 3;
        r0[r3] = r2;
        r2 = 4;
        r0[r2] = r1;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r0);
    L_0x00e4:
        return;
    L_0x00e5:
        r2.flush();	 Catch:{ Exception -> 0x00a3, all -> 0x0129 }
        r1 = r2;
        r2 = r3;
        goto L_0x0018;
    L_0x00ec:
        r0 = 5;
        r0 = new java.io.Closeable[r0];
        r3 = 0;
        r0[r3] = r7;
        r3 = 1;
        r0[r3] = r5;
        r3 = 2;
        r0[r3] = r2;
        r2 = 3;
        r0[r2] = r1;
        r1 = 4;
        r0[r1] = r4;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r0);
        goto L_0x00e4;
    L_0x0102:
        r0 = move-exception;
        r5 = r6;
        r7 = r4;
        r4 = r1;
        r1 = r2;
        r2 = r3;
    L_0x0108:
        r3 = 5;
        r3 = new java.io.Closeable[r3];
        r6 = 0;
        r3[r6] = r7;
        r6 = 1;
        r3[r6] = r5;
        r5 = 2;
        r3[r5] = r2;
        r2 = 3;
        r3[r2] = r1;
        r1 = 4;
        r3[r1] = r4;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r3);
        throw r0;
    L_0x011e:
        r0 = move-exception;
        r4 = r1;
        r5 = r6;
        r1 = r2;
        r2 = r3;
        goto L_0x0108;
    L_0x0124:
        r0 = move-exception;
        r1 = r2;
        r5 = r6;
        r2 = r3;
        goto L_0x0108;
    L_0x0129:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x0108;
    L_0x012d:
        r0 = move-exception;
        goto L_0x0108;
    L_0x012f:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0108;
    L_0x0132:
        r0 = move-exception;
        r7 = r5;
        r5 = r4;
        r4 = r1;
        r1 = r2;
        r2 = r3;
        goto L_0x0108;
    L_0x0139:
        r0 = move-exception;
        r5 = r4;
        r4 = r6;
        goto L_0x00b3;
    L_0x013e:
        r0 = move-exception;
        r4 = r6;
        r5 = r7;
        goto L_0x00b3;
    L_0x0143:
        r0 = move-exception;
        r1 = r4;
        r5 = r7;
        r4 = r6;
        goto L_0x00b3;
    L_0x0149:
        r0 = move-exception;
        goto L_0x00a6;
    L_0x014c:
        r0 = move-exception;
        r2 = r3;
        goto L_0x00a6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.richmedia.C1524a.m6869a(java.io.File, java.lang.String):void");
    }

    /* renamed from: a */
    private static synchronized boolean m6870a(C1527c c1527c) {
        boolean add;
        synchronized (C1524a.class) {
            add = f5336g.add(c1527c);
        }
        return add;
    }

    /* JADX WARNING: Removed duplicated region for block: B:58:0x0247 A:{Catch:{ Exception -> 0x0219 }} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x01b6 A:{Catch:{ Exception -> 0x0219 }} */
    /* renamed from: b */
    private com.baidu.android.pushservice.richmedia.C1530e m6871b() {
        /*
        r11 = this;
        r10 = -1;
        r0 = 0;
        r5 = 0;
        r2 = new com.baidu.android.pushservice.richmedia.e;
        r2.<init>();
        r1 = r11.f5340d;
        r2.f5363d = r1;
        r1 = r11.f5340d;
        if (r1 == 0) goto L_0x01d2;
    L_0x0010:
        r1 = r11.f5340d;
        r1 = r1.mo14045a();
        r2.f5360a = r1;
        r1 = r11.f5340d;
        r1 = r1.f5347b;
        if (r1 == 0) goto L_0x0267;
    L_0x001e:
        r1 = r11.f5340d;
        r1 = com.baidu.android.pushservice.richmedia.C1524a.m6870a(r1);
        if (r1 != 0) goto L_0x004f;
    L_0x0026:
        r1 = "HttpTaskThread";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Request url: ";
        r2 = r2.append(r3);
        r3 = r11.f5340d;
        r3 = r3.mo14050c();
        r2 = r2.append(r3);
        r3 = " failed, already in queue";
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.baidu.android.pushservice.p036h.C1425a.m6443d(r1, r2);
        r11.f5337a = r0;
        r11.f5340d = r0;
    L_0x004e:
        return r0;
    L_0x004f:
        r0 = r11.f5338b;
        r0 = r0.get();
        r0 = (android.content.Context) r0;
        r1 = r11.f5340d;
        r1 = r1.mo14050c();
        r0 = r11.m6867a(r0, r1);
        if (r0 != 0) goto L_0x00e8;
    L_0x0063:
        r1 = new com.baidu.android.pushservice.util.q$h;
        r1.<init>();
        r0 = r11.f5340d;
        r0 = r0.mo14050c();
        r1.f5473b = r0;
        r0 = r11.f5340d;
        r0 = r0.f5346a;
        r1.f5472a = r0;
        r0 = r11.f5340d;
        r0 = r0.f5348c;
        r1.f5474c = r0;
        r0 = r11.f5340d;
        r0 = r0.f5349d;
        r1.f5475d = r0;
        r1.f5478g = r5;
        r0 = r1.f5473b;
        r0 = r11.m6866a(r0);
        r1.f5479h = r0;
        r0 = f5334e;
        r1.f5480i = r0;
        r0 = r1.f5473b;
        r3 = r1.f5473b;
        r4 = 47;
        r3 = r3.lastIndexOf(r4);
        r3 = r3 + 1;
        r0 = r0.substring(r3);
        r1.f5477f = r0;
        r0 = r11.f5340d;
        r0 = r0.f5347b;
        r1.f5476e = r0;
        r0 = r11.f5338b;	 Catch:{ Exception -> 0x00df }
        r0 = r0.get();	 Catch:{ Exception -> 0x00df }
        r0 = (android.content.Context) r0;	 Catch:{ Exception -> 0x00df }
        com.baidu.android.pushservice.util.C1568q.m6991a(r0, r1);	 Catch:{ Exception -> 0x00df }
    L_0x00b3:
        r0 = r1.f5480i;
        r3 = f5335f;
        if (r0 != r3) goto L_0x00f2;
    L_0x00b9:
        r2.f5362c = r5;
        r0 = r11.f5340d;
        r2.f5363d = r0;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r3 = r1.f5476e;
        r0 = r0.append(r3);
        r3 = "/";
        r0 = r0.append(r3);
        r1 = r1.f5477f;
        r0 = r0.append(r1);
        r0 = r0.toString();
        r2.f5364e = r0;
        r0 = r2;
        goto L_0x004e;
    L_0x00df:
        r0 = move-exception;
        r0 = "HttpTaskThread";
        r3 = "HttpTask insertFileDownloadingInfo";
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r3);
        goto L_0x00b3;
    L_0x00e8:
        r1 = r0.f5473b;
        r1 = r11.m6866a(r1);
        r0.f5479h = r1;
        r1 = r0;
        goto L_0x00b3;
    L_0x00f2:
        r0 = "HttpTaskThread";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Request url: ";
        r3 = r3.append(r4);
        r4 = r11.f5340d;
        r4 = r4.mo14050c();
        r3 = r3.append(r4);
        r4 = " success";
        r3 = r3.append(r4);
        r3 = r3.toString();
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r3);
        r0 = r11.f5337a;
        if (r0 == 0) goto L_0x011f;
    L_0x011a:
        r0 = r11.f5337a;
        r0.mo13546a(r11);
    L_0x011f:
        r0 = r11.f5340d;	 Catch:{ Exception -> 0x0219 }
        r0 = r0.mo14050c();	 Catch:{ Exception -> 0x0219 }
        r3 = r11.f5340d;	 Catch:{ Exception -> 0x0219 }
        r3 = r3.mo14048b();	 Catch:{ Exception -> 0x0219 }
        r4 = r11.f5340d;	 Catch:{ Exception -> 0x0219 }
        r4 = r4.f5351f;	 Catch:{ Exception -> 0x0219 }
        r0 = com.baidu.android.pushservice.p034f.C1403b.m6259a(r0, r3, r4);	 Catch:{ Exception -> 0x0219 }
        r3 = r0.mo13745b();	 Catch:{ Exception -> 0x0219 }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r3 != r4) goto L_0x025c;
    L_0x013b:
        r4 = r0.mo13742a();	 Catch:{ Exception -> 0x0219 }
        r0 = new java.io.File;	 Catch:{ Exception -> 0x0219 }
        r3 = r1.f5476e;	 Catch:{ Exception -> 0x0219 }
        r0.<init>(r3);	 Catch:{ Exception -> 0x0219 }
        r3 = r0.exists();	 Catch:{ Exception -> 0x0219 }
        if (r3 != 0) goto L_0x014f;
    L_0x014c:
        r0.mkdirs();	 Catch:{ Exception -> 0x0219 }
    L_0x014f:
        r5 = new java.io.File;	 Catch:{ Exception -> 0x0219 }
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0219 }
        r0.<init>();	 Catch:{ Exception -> 0x0219 }
        r3 = r1.f5476e;	 Catch:{ Exception -> 0x0219 }
        r0 = r0.append(r3);	 Catch:{ Exception -> 0x0219 }
        r3 = "/";
        r0 = r0.append(r3);	 Catch:{ Exception -> 0x0219 }
        r3 = r1.f5477f;	 Catch:{ Exception -> 0x0219 }
        r0 = r0.append(r3);	 Catch:{ Exception -> 0x0219 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0219 }
        r5.<init>(r0);	 Catch:{ Exception -> 0x0219 }
        r0 = r5.exists();	 Catch:{ Exception -> 0x0219 }
        if (r0 != 0) goto L_0x0178;
    L_0x0175:
        r5.createNewFile();	 Catch:{ Exception -> 0x0219 }
    L_0x0178:
        r6 = new java.io.RandomAccessFile;	 Catch:{ Exception -> 0x0219 }
        r0 = "rw";
        r6.<init>(r5, r0);	 Catch:{ Exception -> 0x0219 }
        r0 = r1.f5478g;	 Catch:{ Exception -> 0x0219 }
        r8 = (long) r0;	 Catch:{ Exception -> 0x0219 }
        r6.seek(r8);	 Catch:{ Exception -> 0x0219 }
        r0 = 102400; // 0x19000 float:1.43493E-40 double:5.05923E-319;
        r0 = new byte[r0];	 Catch:{ Exception -> 0x0219 }
        r3 = r1.f5478g;	 Catch:{ Exception -> 0x0219 }
        r7 = new com.baidu.android.pushservice.richmedia.b;	 Catch:{ Exception -> 0x0219 }
        r7.<init>();	 Catch:{ Exception -> 0x0219 }
        r8 = r1.f5479h;	 Catch:{ Exception -> 0x0219 }
        r8 = (long) r8;	 Catch:{ Exception -> 0x0219 }
        r7.f5343b = r8;	 Catch:{ Exception -> 0x0219 }
        r8 = (long) r3;	 Catch:{ Exception -> 0x0219 }
        r7.f5342a = r8;	 Catch:{ Exception -> 0x0219 }
        r11.mo14042a(r7);	 Catch:{ Exception -> 0x0219 }
    L_0x019c:
        r7 = r11.f5341h;	 Catch:{ IOException -> 0x01ef }
        if (r7 != 0) goto L_0x01a6;
    L_0x01a0:
        r7 = r4.read(r0);	 Catch:{ IOException -> 0x01ef }
        if (r7 != r10) goto L_0x01d5;
    L_0x01a6:
        r0 = 2;
        r0 = new java.io.Closeable[r0];	 Catch:{ Exception -> 0x0219 }
        r7 = 0;
        r0[r7] = r4;	 Catch:{ Exception -> 0x0219 }
        r4 = 1;
        r0[r4] = r6;	 Catch:{ Exception -> 0x0219 }
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r0);	 Catch:{ Exception -> 0x0219 }
    L_0x01b2:
        r0 = r11.f5341h;	 Catch:{ Exception -> 0x0219 }
        if (r0 != 0) goto L_0x0247;
    L_0x01b6:
        r1.f5478g = r3;	 Catch:{ Exception -> 0x0219 }
        r0 = f5335f;	 Catch:{ Exception -> 0x0219 }
        r1.f5480i = r0;	 Catch:{ Exception -> 0x0219 }
        r0 = r11.f5338b;	 Catch:{ Exception -> 0x0219 }
        r0 = r0.get();	 Catch:{ Exception -> 0x0219 }
        r0 = (android.content.Context) r0;	 Catch:{ Exception -> 0x0219 }
        r3 = r1.f5473b;	 Catch:{ Exception -> 0x0219 }
        com.baidu.android.pushservice.util.C1568q.m6982a(r0, r3, r1);	 Catch:{ Exception -> 0x0219 }
        r0 = 0;
        r2.f5362c = r0;	 Catch:{ Exception -> 0x0219 }
        r0 = r5.getAbsolutePath();	 Catch:{ Exception -> 0x0219 }
        r2.f5364e = r0;	 Catch:{ Exception -> 0x0219 }
    L_0x01d2:
        r0 = r2;
        goto L_0x004e;
    L_0x01d5:
        r8 = 0;
        r6.write(r0, r8, r7);	 Catch:{ IOException -> 0x01ef }
        r3 = r3 + r7;
        r7 = new com.baidu.android.pushservice.richmedia.b;	 Catch:{ IOException -> 0x01ef }
        r7.<init>();	 Catch:{ IOException -> 0x01ef }
        r8 = r1.f5479h;	 Catch:{ IOException -> 0x01ef }
        r8 = (long) r8;	 Catch:{ IOException -> 0x01ef }
        r7.f5343b = r8;	 Catch:{ IOException -> 0x01ef }
        r8 = (long) r3;	 Catch:{ IOException -> 0x01ef }
        r7.f5342a = r8;	 Catch:{ IOException -> 0x01ef }
        r11.mo14042a(r7);	 Catch:{ IOException -> 0x01ef }
        r7 = r1.f5479h;	 Catch:{ IOException -> 0x01ef }
        if (r3 != r7) goto L_0x019c;
    L_0x01ee:
        goto L_0x01a6;
    L_0x01ef:
        r0 = move-exception;
        r7 = "HttpTaskThread";
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0239 }
        r8.<init>();	 Catch:{ all -> 0x0239 }
        r9 = "error : ";
        r8 = r8.append(r9);	 Catch:{ all -> 0x0239 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0239 }
        r0 = r8.append(r0);	 Catch:{ all -> 0x0239 }
        r0 = r0.toString();	 Catch:{ all -> 0x0239 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r7, r0);	 Catch:{ all -> 0x0239 }
        r0 = 2;
        r0 = new java.io.Closeable[r0];	 Catch:{ Exception -> 0x0219 }
        r7 = 0;
        r0[r7] = r4;	 Catch:{ Exception -> 0x0219 }
        r4 = 1;
        r0[r4] = r6;	 Catch:{ Exception -> 0x0219 }
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r0);	 Catch:{ Exception -> 0x0219 }
        goto L_0x01b2;
    L_0x0219:
        r0 = move-exception;
        r1 = "HttpTaskThread";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "download file Exception:";
        r3 = r3.append(r4);
        r0 = r0.getMessage();
        r0 = r3.append(r0);
        r0 = r0.toString();
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r1, r0);
        r2.f5362c = r10;
        goto L_0x01d2;
    L_0x0239:
        r0 = move-exception;
        r1 = 2;
        r1 = new java.io.Closeable[r1];	 Catch:{ Exception -> 0x0219 }
        r3 = 0;
        r1[r3] = r4;	 Catch:{ Exception -> 0x0219 }
        r3 = 1;
        r1[r3] = r6;	 Catch:{ Exception -> 0x0219 }
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r1);	 Catch:{ Exception -> 0x0219 }
        throw r0;	 Catch:{ Exception -> 0x0219 }
    L_0x0247:
        r0 = r11.f5338b;	 Catch:{ Exception -> 0x0219 }
        r0 = r0.get();	 Catch:{ Exception -> 0x0219 }
        r0 = (android.content.Context) r0;	 Catch:{ Exception -> 0x0219 }
        r1 = r1.f5473b;	 Catch:{ Exception -> 0x0219 }
        com.baidu.android.pushservice.util.C1568q.m6999b(r0, r1);	 Catch:{ Exception -> 0x0219 }
        r0 = 2;
        r2.f5362c = r0;	 Catch:{ Exception -> 0x0219 }
        r5.delete();	 Catch:{ Exception -> 0x0219 }
        goto L_0x01d2;
    L_0x025c:
        r1 = 1;
        r2.f5362c = r1;	 Catch:{ Exception -> 0x0219 }
        r0 = r0.mo13745b();	 Catch:{ Exception -> 0x0219 }
        r2.f5361b = r0;	 Catch:{ Exception -> 0x0219 }
        goto L_0x01d2;
    L_0x0267:
        r0 = "HttpTaskThread";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r3 = "download file Request error: ";
        r1 = r1.append(r3);
        r3 = r11.f5340d;
        r1 = r1.append(r3);
        r1 = r1.toString();
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r1);
        r0 = 3;
        r2.f5362c = r0;
        goto L_0x01d2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.richmedia.C1524a.m6871b():com.baidu.android.pushservice.richmedia.e");
    }

    /* renamed from: b */
    private static synchronized boolean m6872b(C1527c c1527c) {
        boolean remove;
        synchronized (C1524a.class) {
            remove = f5336g.remove(c1527c);
        }
        return remove;
    }

    /* renamed from: a */
    public int compareTo(C1524a c1524a) {
        if (c1524a == null) {
            return -1;
        }
        long a = c1524a.mo14041a();
        return this.f5339c <= a ? this.f5339c < a ? 1 : 0 : -1;
    }

    /* renamed from: a */
    public long mo14041a() {
        return this.f5339c;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo14042a(C1525b c1525b) {
        if (this.f5337a != null) {
            this.f5337a.mo13547a(this, c1525b);
        }
    }

    public void run() {
        m6868a(m6871b());
    }
}
