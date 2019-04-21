package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Looper;
import com.amap.api.mapcore.util.DiskLruCache.C0840a;
import com.amap.api.mapcore.util.DiskLruCache.C0841b;
import com.amap.api.mapcore.util.SDKInfo.C0824a;
import com.mcdonalds.sdk.modules.models.TenderType;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.amap.api.mapcore.util.ei */
public abstract class LogProcessor {
    /* renamed from: a */
    private SDKInfo f1867a;
    /* renamed from: b */
    private int f1868b;
    /* renamed from: c */
    private FileOperationListener f1869c;
    /* renamed from: d */
    private DiskLruCache f1870d;

    /* compiled from: LogProcessor */
    /* renamed from: com.amap.api.mapcore.util.ei$a */
    class C0831a implements FileOperationListener {
        /* renamed from: b */
        private LogDBOperation f1872b;

        C0831a(LogDBOperation logDBOperation) {
            this.f1872b = logDBOperation;
        }

        /* renamed from: a */
        public void mo9323a(String str) {
            try {
                this.f1872b.mo9350b(str, Log.m2546a(LogProcessor.this.mo9319b()));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public abstract String mo9314a(List<SDKInfo> list);

    /* renamed from: a */
    public abstract boolean mo9318a(Context context);

    protected LogProcessor(int i) {
        this.f1868b = i;
    }

    /* renamed from: d */
    private void m2585d(Context context) {
        try {
            this.f1870d = m2578b(context, mo9311a());
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "LogProcessor", "LogUpDateProcessor");
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo9317a(SDKInfo sDKInfo, Context context, Throwable th, String str, String str2, String str3) {
        mo9316a(sDKInfo);
        String d = m2583d();
        String a = ClientInfo.m2399a(context, sDKInfo);
        String a2 = AppInfo.m2381a(context);
        String c = m2582c(th);
        if (c != null && !"".equals(c)) {
            int b = mo9319b();
            StringBuilder stringBuilder = new StringBuilder();
            if (str2 != null) {
                stringBuilder.append("class:").append(str2);
            }
            if (str3 != null) {
                stringBuilder.append(" method:").append(str3).append("$").append("<br/>");
            }
            stringBuilder.append(str);
            String a3 = mo9312a(str);
            String a4 = m2571a(a2, a, d, b, c, stringBuilder.toString());
            if (a4 != null && !"".equals(a4)) {
                String a5 = m2570a(context, a4);
                String a6 = mo9311a();
                synchronized (Looper.getMainLooper()) {
                    LogDBOperation logDBOperation = new LogDBOperation(context);
                    boolean a7 = m2576a(context, a3, a6, a5, logDBOperation);
                    m2574a(logDBOperation, sDKInfo.mo9292a(), a3, b, a7);
                }
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo9315a(Context context, Throwable th, String str, String str2) {
        List<SDKInfo> e = m2586e(context);
        if (e != null && e.size() != 0) {
            String a = mo9313a(th);
            if (a != null && !"".equals(a)) {
                for (SDKInfo sDKInfo : e) {
                    if (LogProcessor.m2577a(sDKInfo.mo9297e(), a)) {
                        mo9317a(sDKInfo, context, th, a, str, str2);
                        return;
                    }
                }
                if (a.contains("com.amap.api.col")) {
                    try {
                        mo9317a(new C0824a("collection", "1.0", "AMap_collection_1.0").mo9290a(new String[]{"com.amap.api.collection"}).mo9291a(), context, th, a, str, str2);
                    } catch (AMapCoreException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public void mo9320b(Context context) {
        List e = m2586e(context);
        if (e != null && e.size() != 0) {
            String a = mo9314a(e);
            if (a != null && !"".equals(a)) {
                String d = m2583d();
                String a2 = ClientInfo.m2399a(context, this.f1867a);
                int b = mo9319b();
                String a3 = m2571a(AppInfo.m2381a(context), a2, d, b, "ANR", a);
                if (a3 != null && !"".equals(a3)) {
                    String a4 = mo9312a(a);
                    String a5 = m2570a(context, a3);
                    String a6 = mo9311a();
                    synchronized (Looper.getMainLooper()) {
                        LogDBOperation logDBOperation = new LogDBOperation(context);
                        boolean a7 = m2576a(context, a4, a6, a5, logDBOperation);
                        m2574a(logDBOperation, this.f1867a.mo9292a(), a4, b, a7);
                    }
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo9316a(SDKInfo sDKInfo) {
        this.f1867a = sDKInfo;
    }

    /* renamed from: e */
    private List<SDKInfo> m2586e(Context context) {
        List<SDKInfo> a;
        Throwable th;
        Throwable th2;
        Throwable th3;
        List<SDKInfo> list = null;
        try {
            synchronized (Looper.getMainLooper()) {
                try {
                    a = new SDKDBOperation(context, false).mo9351a();
                    try {
                    } catch (Throwable th22) {
                        th = th22;
                        list = a;
                        th3 = th;
                        try {
                            throw th3;
                        } catch (Throwable th32) {
                            th = th32;
                            a = list;
                            th22 = th;
                        }
                    }
                } catch (Throwable th4) {
                    th32 = th4;
                }
            }
        } catch (Throwable th322) {
            th = th322;
            a = null;
            th22 = th;
            th22.printStackTrace();
            return a;
        }
    }

    /* renamed from: a */
    private void m2574a(LogDBOperation logDBOperation, String str, String str2, int i, boolean z) {
        LogInfo b = Log.m2550b(i);
        b.mo9338a(0);
        b.mo9342b(str);
        b.mo9339a(str2);
        logDBOperation.mo9347a(b);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public String mo9312a(String str) {
        return C0822ds.m2466c(str);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public FileOperationListener mo9310a(LogDBOperation logDBOperation) {
        try {
            if (this.f1869c == null) {
                this.f1869c = new C0831a(logDBOperation);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return this.f1869c;
    }

    /* renamed from: a */
    private String m2571a(String str, String str2, String str3, int i, String str4, String str5) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str2).append(",").append("\"timestamp\":\"");
        stringBuffer.append(str3);
        stringBuffer.append("\",\"et\":\"");
        stringBuffer.append(i);
        stringBuffer.append("\",\"classname\":\"");
        stringBuffer.append(str4);
        stringBuffer.append("\",");
        stringBuffer.append("\"detail\":\"");
        stringBuffer.append(str5);
        stringBuffer.append("\"");
        return stringBuffer.toString();
    }

    /* renamed from: a */
    private String m2570a(Context context, String str) {
        String str2 = null;
        try {
            return ClientInfo.m2409e(context, Utils.m2515a(str));
        } catch (Throwable th) {
            th.printStackTrace();
            return str2;
        }
    }

    /* renamed from: d */
    private String m2583d() {
        return Utils.m2506a(new Date().getTime());
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public String mo9313a(Throwable th) {
        String str = null;
        try {
            return LogProcessor.m2579b(th);
        } catch (Throwable th2) {
            th2.printStackTrace();
            return str;
        }
    }

    /* renamed from: c */
    private String m2582c(Throwable th) {
        return th.toString();
    }

    /* renamed from: a */
    private boolean m2576a(Context context, String str, String str2, String str3, LogDBOperation logDBOperation) {
        Throwable th;
        OutputStream outputStream = null;
        DiskLruCache diskLruCache = null;
        C0841b c0841b = null;
        Throwable th2;
        try {
            boolean z;
            File file = new File(Log.m2547a(context, str2));
            if (file.exists() || file.mkdirs()) {
                diskLruCache = DiskLruCache.m2767a(file, 1, 1, 20480);
                diskLruCache.mo9400a(mo9310a(logDBOperation));
                c0841b = diskLruCache.mo9399a(str);
                if (c0841b != null) {
                    z = false;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Throwable th3) {
                            th3.printStackTrace();
                        }
                    }
                    if (c0841b != null) {
                        try {
                            c0841b.close();
                        } catch (Throwable th4) {
                            th4.printStackTrace();
                        }
                    }
                    if (diskLruCache == null || diskLruCache.mo9401a()) {
                        return false;
                    }
                    try {
                        diskLruCache.close();
                        return false;
                    } catch (Throwable th5) {
                        th4 = th5;
                    }
                } else {
                    byte[] a = Utils.m2515a(str3);
                    C0840a b = diskLruCache.mo9402b(str);
                    outputStream = b.mo9391a(0);
                    outputStream.write(a);
                    b.mo9392a();
                    diskLruCache.mo9403b();
                    z = true;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Throwable th32) {
                            th32.printStackTrace();
                        }
                    }
                    if (c0841b != null) {
                        try {
                            c0841b.close();
                        } catch (Throwable th42) {
                            th42.printStackTrace();
                        }
                    }
                    if (diskLruCache == null || diskLruCache.mo9401a()) {
                        return true;
                    }
                    try {
                        diskLruCache.close();
                        return true;
                    } catch (Throwable th6) {
                        th42 = th6;
                    }
                }
            } else {
                z = false;
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable th322) {
                        th322.printStackTrace();
                    }
                }
                if (c0841b != null) {
                    try {
                        c0841b.close();
                    } catch (Throwable th422) {
                        th422.printStackTrace();
                    }
                }
                if (diskLruCache == null || diskLruCache.mo9401a()) {
                    return false;
                }
                try {
                    diskLruCache.close();
                    return false;
                } catch (Throwable th7) {
                    th422 = th7;
                }
            }
            th2.printStackTrace();
            return false;
            if (c0841b != null) {
                c0841b.close();
            }
            if (!(diskLruCache == null || diskLruCache.mo9401a())) {
                diskLruCache.close();
            }
            return false;
            if (c0841b != null) {
                c0841b.close();
            }
            if (!(diskLruCache == null || diskLruCache.mo9401a())) {
                diskLruCache.close();
            }
            return false;
            diskLruCache.close();
            return false;
            th422.printStackTrace();
            return z;
            diskLruCache.close();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (Throwable th8) {
            th2 = th8;
        }
    }

    /* renamed from: a */
    public static boolean m2577a(String[] strArr, String str) {
        if (strArr == null || str == null) {
            return false;
        }
        try {
            String[] split = str.split("<br/>");
            for (CharSequence charSequence : strArr) {
                for (String str2 : split) {
                    if (str2.contains("at") && str2.contains(charSequence)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Missing block: B:28:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:31:?, code skipped:
            return;
     */
    /* renamed from: c */
    public void mo9322c(android.content.Context r6) {
        /*
        r5 = this;
        r5.m2585d(r6);	 Catch:{ Throwable -> 0x0035 }
        r0 = r5.mo9318a(r6);	 Catch:{ Throwable -> 0x0035 }
        if (r0 != 0) goto L_0x000a;
    L_0x0009:
        return;
    L_0x000a:
        r1 = android.os.Looper.getMainLooper();	 Catch:{ Throwable -> 0x0035 }
        monitor-enter(r1);	 Catch:{ Throwable -> 0x0035 }
        r0 = new com.amap.api.mapcore.util.es;	 Catch:{ all -> 0x0032 }
        r0.<init>(r6);	 Catch:{ all -> 0x0032 }
        r2 = r5.mo9319b();	 Catch:{ all -> 0x0032 }
        r5.m2573a(r0, r2);	 Catch:{ all -> 0x0032 }
        r2 = 0;
        r3 = r5.mo9319b();	 Catch:{ all -> 0x0032 }
        r3 = com.amap.api.mapcore.util.Log.m2546a(r3);	 Catch:{ all -> 0x0032 }
        r2 = r0.mo9346a(r2, r3);	 Catch:{ all -> 0x0032 }
        if (r2 == 0) goto L_0x0030;
    L_0x002a:
        r3 = r2.size();	 Catch:{ all -> 0x0032 }
        if (r3 != 0) goto L_0x003e;
    L_0x0030:
        monitor-exit(r1);	 Catch:{ all -> 0x0032 }
        goto L_0x0009;
    L_0x0032:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0032 }
        throw r0;	 Catch:{ Throwable -> 0x0035 }
    L_0x0035:
        r0 = move-exception;
        r1 = "LogProcessor";
        r2 = "processUpdateLog";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r0, r1, r2);
        goto L_0x0009;
    L_0x003e:
        r3 = r5.m2572a(r2, r6);	 Catch:{ all -> 0x0032 }
        if (r3 != 0) goto L_0x0046;
    L_0x0044:
        monitor-exit(r1);	 Catch:{ all -> 0x0032 }
        goto L_0x0009;
    L_0x0046:
        r3 = r5.m2581c(r3);	 Catch:{ all -> 0x0032 }
        r4 = 1;
        if (r3 != r4) goto L_0x0054;
    L_0x004d:
        r3 = r5.mo9319b();	 Catch:{ all -> 0x0032 }
        r5.m2575a(r2, r0, r3);	 Catch:{ all -> 0x0032 }
    L_0x0054:
        monitor-exit(r1);	 Catch:{ all -> 0x0032 }
        goto L_0x0009;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.LogProcessor.mo9322c(android.content.Context):void");
    }

    /* renamed from: b */
    private boolean m2580b(String str) {
        boolean z = false;
        if (this.f1870d == null) {
            return z;
        }
        try {
            return this.f1870d.mo9405c(str);
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "LogUpdateProcessor", "deleteLogData");
            return z;
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public String mo9311a() {
        return Log.m2552c(this.f1868b);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public int mo9319b() {
        return this.f1868b;
    }

    /* renamed from: a */
    private void m2573a(LogDBOperation logDBOperation, int i) {
        try {
            m2575a(logDBOperation.mo9346a(2, Log.m2546a(i)), logDBOperation, i);
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "LogProcessor", "processDeleteFail");
        }
    }

    /* renamed from: c */
    private int m2581c(String str) {
        int i = 0;
        try {
            byte[] b = BaseNetManager.m2800a().mo9415b(new LogUpdateRequest(Utils.m2520c(Utils.m2515a(str))));
            if (b == null) {
                return 0;
            }
            try {
                JSONObject init = JSONObjectInstrumentation.init(Utils.m2509a(b));
                String str2 = TenderType.COLUMN_CODE;
                if (init.has(str2)) {
                    return init.getInt(str2);
                }
                return 0;
            } catch (JSONException e) {
                BasicLogHandler.m2542a(e, "LogProcessor", "processUpdate");
                return 1;
            }
        } catch (AMapCoreException e2) {
            if (e2.mo9284b() != 27) {
                i = 1;
            }
            BasicLogHandler.m2542a(e2, "LogProcessor", "processUpdate");
            return i;
        }
    }

    /* renamed from: a */
    private void m2575a(List<? extends LogInfo> list, LogDBOperation logDBOperation, int i) {
        if (list != null && list.size() > 0) {
            for (LogInfo logInfo : list) {
                if (m2580b(logInfo.mo9340b())) {
                    logDBOperation.mo9348a(logInfo.mo9340b(), logInfo.getClass());
                } else {
                    logInfo.mo9338a(2);
                    logDBOperation.mo9349b(logInfo);
                }
            }
        }
    }

    /* renamed from: f */
    private String m2587f(Context context) {
        try {
            String a = ClientInfo.m2397a(context);
            if ("".equals(a)) {
                return null;
            }
            return ClientInfo.m2405b(context, Utils.m2515a(a));
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "LogProcessor", "getPublicInfo");
            return null;
        }
    }

    /* renamed from: a */
    private String m2572a(List<? extends LogInfo> list, Context context) {
        Object obj;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"pinfo\":\"").append(m2587f(context)).append("\",\"els\":[");
        Object obj2 = 1;
        Iterator it = list.iterator();
        while (true) {
            obj = obj2;
            if (!it.hasNext()) {
                break;
            }
            LogInfo logInfo = (LogInfo) it.next();
            String d = m2584d(logInfo.mo9340b());
            if (!(d == null || "".equals(d))) {
                String str = d + "||" + logInfo.mo9343c();
                if (obj != null) {
                    obj = null;
                } else {
                    stringBuilder.append(",");
                }
                stringBuilder.append("{\"log\":\"").append(str).append("\"}");
            }
            obj2 = obj;
        }
        if (obj != null) {
            return null;
        }
        stringBuilder.append("]}");
        return stringBuilder.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:59:0x0080 A:{SYNTHETIC, Splitter:B:59:0x0080} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0085 A:{SYNTHETIC, Splitter:B:62:0x0085} */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0080 A:{SYNTHETIC, Splitter:B:59:0x0080} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0085 A:{SYNTHETIC, Splitter:B:62:0x0085} */
    /* renamed from: d */
    private java.lang.String m2584d(java.lang.String r7) {
        /*
        r6 = this;
        r0 = 0;
        r1 = 0;
        r2 = 0;
        r3 = r6.f1870d;	 Catch:{ Throwable -> 0x00ce, all -> 0x007a }
        if (r3 != 0) goto L_0x0012;
    L_0x0007:
        if (r0 == 0) goto L_0x000c;
    L_0x0009:
        r2.close();	 Catch:{ IOException -> 0x00a4 }
    L_0x000c:
        if (r0 == 0) goto L_0x0011;
    L_0x000e:
        r1.close();	 Catch:{ IOException -> 0x00ae }
    L_0x0011:
        return r0;
    L_0x0012:
        r3 = r6.f1870d;	 Catch:{ Throwable -> 0x00ce, all -> 0x007a }
        r3 = r3.mo9399a(r7);	 Catch:{ Throwable -> 0x00ce, all -> 0x007a }
        if (r3 != 0) goto L_0x002e;
    L_0x001a:
        if (r0 == 0) goto L_0x001f;
    L_0x001c:
        r2.close();	 Catch:{ IOException -> 0x00b5 }
    L_0x001f:
        if (r0 == 0) goto L_0x0011;
    L_0x0021:
        r1.close();	 Catch:{ IOException -> 0x0025 }
        goto L_0x0011;
    L_0x0025:
        r1 = move-exception;
        r2 = "LogProcessor";
        r3 = "readLog2";
    L_0x002a:
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r1, r2, r3);
        goto L_0x0011;
    L_0x002e:
        r1 = 0;
        r3 = r3.mo9394a(r1);	 Catch:{ Throwable -> 0x00ce, all -> 0x007a }
        r2 = new java.io.ByteArrayOutputStream;	 Catch:{ Throwable -> 0x00d3, all -> 0x00c8 }
        r2.<init>();	 Catch:{ Throwable -> 0x00d3, all -> 0x00c8 }
        r1 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r1 = new byte[r1];	 Catch:{ Throwable -> 0x0048 }
    L_0x003c:
        r4 = r3.read(r1);	 Catch:{ Throwable -> 0x0048 }
        r5 = -1;
        if (r4 == r5) goto L_0x0061;
    L_0x0043:
        r5 = 0;
        r2.write(r1, r5, r4);	 Catch:{ Throwable -> 0x0048 }
        goto L_0x003c;
    L_0x0048:
        r1 = move-exception;
    L_0x0049:
        r4 = "LogProcessor";
        r5 = "readLog";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r1, r4, r5);	 Catch:{ all -> 0x00cc }
        if (r2 == 0) goto L_0x0055;
    L_0x0052:
        r2.close();	 Catch:{ IOException -> 0x009b }
    L_0x0055:
        if (r3 == 0) goto L_0x0011;
    L_0x0057:
        r3.close();	 Catch:{ IOException -> 0x005b }
        goto L_0x0011;
    L_0x005b:
        r1 = move-exception;
        r2 = "LogProcessor";
        r3 = "readLog2";
        goto L_0x002a;
    L_0x0061:
        r1 = r2.toByteArray();	 Catch:{ Throwable -> 0x0048 }
        r0 = com.amap.api.mapcore.util.Utils.m2509a(r1);	 Catch:{ Throwable -> 0x0048 }
        if (r2 == 0) goto L_0x006e;
    L_0x006b:
        r2.close();	 Catch:{ IOException -> 0x00bf }
    L_0x006e:
        if (r3 == 0) goto L_0x0011;
    L_0x0070:
        r3.close();	 Catch:{ IOException -> 0x0074 }
        goto L_0x0011;
    L_0x0074:
        r1 = move-exception;
        r2 = "LogProcessor";
        r3 = "readLog2";
        goto L_0x002a;
    L_0x007a:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r0 = r1;
    L_0x007e:
        if (r2 == 0) goto L_0x0083;
    L_0x0080:
        r2.close();	 Catch:{ IOException -> 0x0089 }
    L_0x0083:
        if (r3 == 0) goto L_0x0088;
    L_0x0085:
        r3.close();	 Catch:{ IOException -> 0x0092 }
    L_0x0088:
        throw r0;
    L_0x0089:
        r1 = move-exception;
        r2 = "LogProcessor";
        r4 = "readLog1";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r1, r2, r4);
        goto L_0x0083;
    L_0x0092:
        r1 = move-exception;
        r2 = "LogProcessor";
        r3 = "readLog2";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r1, r2, r3);
        goto L_0x0088;
    L_0x009b:
        r1 = move-exception;
        r2 = "LogProcessor";
        r4 = "readLog1";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r1, r2, r4);
        goto L_0x0055;
    L_0x00a4:
        r2 = move-exception;
        r3 = "LogProcessor";
        r4 = "readLog1";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r2, r3, r4);
        goto L_0x000c;
    L_0x00ae:
        r1 = move-exception;
        r2 = "LogProcessor";
        r3 = "readLog2";
        goto L_0x002a;
    L_0x00b5:
        r2 = move-exception;
        r3 = "LogProcessor";
        r4 = "readLog1";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r2, r3, r4);
        goto L_0x001f;
    L_0x00bf:
        r1 = move-exception;
        r2 = "LogProcessor";
        r4 = "readLog1";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r1, r2, r4);
        goto L_0x006e;
    L_0x00c8:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x007e;
    L_0x00cc:
        r0 = move-exception;
        goto L_0x007e;
    L_0x00ce:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        goto L_0x0049;
    L_0x00d3:
        r1 = move-exception;
        r2 = r0;
        goto L_0x0049;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.LogProcessor.m2584d(java.lang.String):java.lang.String");
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: c */
    public void mo9321c() {
        try {
            if (this.f1870d != null && !this.f1870d.mo9401a()) {
                this.f1870d.close();
            }
        } catch (IOException e) {
            BasicLogHandler.m2542a(e, "LogProcessor", "closeDiskLru");
        } catch (Throwable e2) {
            BasicLogHandler.m2542a(e2, "LogProcessor", "closeDiskLru");
        }
    }

    /* renamed from: b */
    private DiskLruCache m2578b(Context context, String str) {
        try {
            File file = new File(Log.m2547a(context, str));
            if (file.exists() || file.mkdirs()) {
                return DiskLruCache.m2767a(file, 1, 1, 20480);
            }
            return null;
        } catch (IOException e) {
            BasicLogHandler.m2542a(e, "LogProcessor", "initDiskLru");
            return null;
        } catch (Throwable e2) {
            BasicLogHandler.m2542a(e2, "LogProcessor", "initDiskLru");
            return null;
        }
    }

    /* renamed from: b */
    public static String m2579b(Throwable th) {
        String a = Utils.m2507a(th);
        if (a != null) {
            return a.replaceAll("\n", "<br/>");
        }
        return null;
    }
}
