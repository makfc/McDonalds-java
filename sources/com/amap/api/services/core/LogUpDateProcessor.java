package com.amap.api.services.core;

import android.content.Context;
import com.facebook.stetho.common.Utf8Charset;
import com.mcdonalds.sdk.modules.models.TenderType;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.amap.api.services.core.bf */
abstract class LogUpDateProcessor {
    /* renamed from: a */
    private C1114bj f3697a;

    /* renamed from: a */
    public abstract String mo12041a();

    /* renamed from: a */
    public abstract boolean mo12042a(Context context);

    /* renamed from: b */
    public abstract int mo12043b();

    /* renamed from: a */
    public static LogUpDateProcessor m4804a(Context context, int i) {
        switch (i) {
            case 0:
                return new CrashLogUpDateProcessor(context);
            case 1:
                return new ExceptionLogUpDateProcessor(context);
            case 2:
                return new ANRLogUpDateProcessor(context);
            default:
                return null;
        }
    }

    protected LogUpDateProcessor(Context context) {
        try {
            this.f3697a = m4805a(context, mo12041a());
        } catch (Throwable th) {
            C1099ax.m4800a(th, "LogProcessor", "LogUpDateProcessor");
            th.printStackTrace();
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
    /* renamed from: b */
    public void mo12044b(android.content.Context r6) {
        /*
        r5 = this;
        r0 = r5.mo12042a(r6);	 Catch:{ Throwable -> 0x002e }
        if (r0 != 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r1 = android.os.Looper.getMainLooper();	 Catch:{ Throwable -> 0x002e }
        monitor-enter(r1);	 Catch:{ Throwable -> 0x002e }
        r0 = new com.amap.api.services.core.aj;	 Catch:{ all -> 0x002b }
        r0.<init>(r6);	 Catch:{ all -> 0x002b }
        r2 = r5.mo12043b();	 Catch:{ all -> 0x002b }
        r5.m4807a(r0, r2);	 Catch:{ all -> 0x002b }
        r2 = 0;
        r3 = r5.mo12043b();	 Catch:{ all -> 0x002b }
        r2 = r0.mo12006a(r2, r3);	 Catch:{ all -> 0x002b }
        if (r2 == 0) goto L_0x0029;
    L_0x0023:
        r3 = r2.size();	 Catch:{ all -> 0x002b }
        if (r3 != 0) goto L_0x003a;
    L_0x0029:
        monitor-exit(r1);	 Catch:{ all -> 0x002b }
        goto L_0x0006;
    L_0x002b:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x002b }
        throw r0;	 Catch:{ Throwable -> 0x002e }
    L_0x002e:
        r0 = move-exception;
        r1 = "LogProcessor";
        r2 = "processUpdateLog";
        com.amap.api.services.core.C1099ax.m4800a(r0, r1, r2);
        r0.printStackTrace();
        goto L_0x0006;
    L_0x003a:
        r3 = r5.m4806a(r2, r6);	 Catch:{ all -> 0x002b }
        if (r3 != 0) goto L_0x0042;
    L_0x0040:
        monitor-exit(r1);	 Catch:{ all -> 0x002b }
        goto L_0x0006;
    L_0x0042:
        r3 = r5.m4810b(r3);	 Catch:{ all -> 0x002b }
        r4 = 1;
        if (r3 != r4) goto L_0x0050;
    L_0x0049:
        r3 = r5.mo12043b();	 Catch:{ all -> 0x002b }
        r5.m4808a(r2, r0, r3);	 Catch:{ all -> 0x002b }
    L_0x0050:
        monitor-exit(r1);	 Catch:{ all -> 0x002b }
        goto L_0x0006;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.core.LogUpDateProcessor.mo12044b(android.content.Context):void");
    }

    /* renamed from: a */
    private boolean m4809a(String str) {
        boolean z = false;
        if (this.f3697a == null) {
            return z;
        }
        try {
            return this.f3697a.mo12081c(str);
        } catch (IOException e) {
            e.printStackTrace();
            return z;
        }
    }

    /* renamed from: a */
    private void m4807a(C1085aj c1085aj, int i) {
        try {
            m4808a(c1085aj.mo12006a(2, i), c1085aj, i);
        } catch (Throwable th) {
            C1099ax.m4800a(th, "LogProcessor", "processDeleteFail");
            th.printStackTrace();
        }
    }

    /* renamed from: b */
    private int m4810b(String str) {
        try {
            byte[] a = C1123br.m4953a(false).mo12094a(new C1108bg(C1082ad.m4713b(str.getBytes())));
            if (a == null) {
                return 0;
            }
            try {
                JSONObject init = JSONObjectInstrumentation.init(new String(a));
                String str2 = TenderType.COLUMN_CODE;
                if (init.has(str2)) {
                    return init.getInt(str2);
                }
                return 0;
            } catch (JSONException e) {
                C1099ax.m4800a(e, "LogProcessor", "processUpdate");
                e.printStackTrace();
                return 0;
            }
        } catch (C1133u e2) {
            C1099ax.m4800a(e2, "LogProcessor", "processUpdate");
            e2.printStackTrace();
            return 0;
        }
    }

    /* renamed from: a */
    private void m4808a(List<C1086al> list, C1085aj c1085aj, int i) {
        if (list != null && list.size() > 0) {
            for (C1086al c1086al : list) {
                if (m4809a(c1086al.mo12014b())) {
                    c1085aj.mo12008a(c1086al.mo12014b(), i);
                } else {
                    c1086al.mo12012a(2);
                    c1085aj.mo12007a(c1086al, c1086al.mo12011a());
                }
            }
        }
    }

    /* renamed from: c */
    private String m4811c(Context context) {
        try {
            String a = C1136x.m5091a(context);
            if ("".equals(a)) {
                return null;
            }
            return C1136x.m5097b(context, a.getBytes(Utf8Charset.NAME));
        } catch (UnsupportedEncodingException e) {
            C1099ax.m4800a(e, "LogProcessor", "getPublicInfo");
            e.printStackTrace();
            return null;
        } catch (Throwable e2) {
            C1099ax.m4800a(e2, "LogProcessor", "getPublicInfo");
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private String m4806a(List<C1086al> list, Context context) {
        Object obj;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"pinfo\":\"").append(m4811c(context)).append("\",\"els\":[");
        Object obj2 = 1;
        Iterator it = list.iterator();
        while (true) {
            obj = obj2;
            if (!it.hasNext()) {
                break;
            }
            C1086al c1086al = (C1086al) it.next();
            String c = m4812c(c1086al.mo12014b());
            if (!(c == null || "".equals(c))) {
                String str = c + "||" + c1086al.mo12018d();
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

    /* JADX WARNING: Unknown top exception splitter block from list: {B:58:0x0088=Splitter:B:58:0x0088, B:36:0x004c=Splitter:B:36:0x004c} */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0094 A:{SYNTHETIC, Splitter:B:61:0x0094} */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0099 A:{SYNTHETIC, Splitter:B:64:0x0099} */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0094 A:{SYNTHETIC, Splitter:B:61:0x0094} */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0099 A:{SYNTHETIC, Splitter:B:64:0x0099} */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00ad A:{SYNTHETIC, Splitter:B:71:0x00ad} */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x00b2 A:{SYNTHETIC, Splitter:B:74:0x00b2} */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00ad A:{SYNTHETIC, Splitter:B:71:0x00ad} */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x00b2 A:{SYNTHETIC, Splitter:B:74:0x00b2} */
    /* renamed from: c */
    private java.lang.String m4812c(java.lang.String r7) {
        /*
        r6 = this;
        r0 = 0;
        r1 = 0;
        r2 = 0;
        r3 = r6.f3697a;	 Catch:{ IOException -> 0x0124, Throwable -> 0x0085, all -> 0x00a7 }
        if (r3 != 0) goto L_0x0012;
    L_0x0007:
        if (r0 == 0) goto L_0x000c;
    L_0x0009:
        r2.close();	 Catch:{ IOException -> 0x00e6 }
    L_0x000c:
        if (r0 == 0) goto L_0x0011;
    L_0x000e:
        r1.close();	 Catch:{ IOException -> 0x00f3 }
    L_0x0011:
        return r0;
    L_0x0012:
        r3 = r6.f3697a;	 Catch:{ IOException -> 0x0124, Throwable -> 0x0085, all -> 0x00a7 }
        r3 = r3.mo12075a(r7);	 Catch:{ IOException -> 0x0124, Throwable -> 0x0085, all -> 0x00a7 }
        if (r3 != 0) goto L_0x0031;
    L_0x001a:
        if (r0 == 0) goto L_0x001f;
    L_0x001c:
        r2.close();	 Catch:{ IOException -> 0x00fd }
    L_0x001f:
        if (r0 == 0) goto L_0x0011;
    L_0x0021:
        r1.close();	 Catch:{ IOException -> 0x0025 }
        goto L_0x0011;
    L_0x0025:
        r1 = move-exception;
        r2 = "LogProcessor";
        r3 = "readLog2";
        com.amap.api.services.core.C1099ax.m4800a(r1, r2, r3);
    L_0x002d:
        r1.printStackTrace();
        goto L_0x0011;
    L_0x0031:
        r1 = 0;
        r3 = r3.mo12070a(r1);	 Catch:{ IOException -> 0x0124, Throwable -> 0x0085, all -> 0x00a7 }
        r2 = new java.io.ByteArrayOutputStream;	 Catch:{ IOException -> 0x0129, Throwable -> 0x011d, all -> 0x0117 }
        r2.<init>();	 Catch:{ IOException -> 0x0129, Throwable -> 0x011d, all -> 0x0117 }
        r1 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r1 = new byte[r1];	 Catch:{ IOException -> 0x004b, Throwable -> 0x0121 }
    L_0x003f:
        r4 = r3.read(r1);	 Catch:{ IOException -> 0x004b, Throwable -> 0x0121 }
        r5 = -1;
        if (r4 == r5) goto L_0x006a;
    L_0x0046:
        r5 = 0;
        r2.write(r1, r5, r4);	 Catch:{ IOException -> 0x004b, Throwable -> 0x0121 }
        goto L_0x003f;
    L_0x004b:
        r1 = move-exception;
    L_0x004c:
        r4 = "LogProcessor";
        r5 = "readLog";
        com.amap.api.services.core.C1099ax.m4800a(r1, r4, r5);	 Catch:{ all -> 0x011b }
        r1.printStackTrace();	 Catch:{ all -> 0x011b }
        if (r2 == 0) goto L_0x005b;
    L_0x0058:
        r2.close();	 Catch:{ IOException -> 0x00ce }
    L_0x005b:
        if (r3 == 0) goto L_0x0011;
    L_0x005d:
        r3.close();	 Catch:{ IOException -> 0x0061 }
        goto L_0x0011;
    L_0x0061:
        r1 = move-exception;
        r2 = "LogProcessor";
        r3 = "readLog2";
        com.amap.api.services.core.C1099ax.m4800a(r1, r2, r3);
        goto L_0x002d;
    L_0x006a:
        r1 = "utf-8";
        r0 = r2.toString(r1);	 Catch:{ IOException -> 0x004b, Throwable -> 0x0121 }
        if (r2 == 0) goto L_0x0076;
    L_0x0073:
        r2.close();	 Catch:{ IOException -> 0x010a }
    L_0x0076:
        if (r3 == 0) goto L_0x0011;
    L_0x0078:
        r3.close();	 Catch:{ IOException -> 0x007c }
        goto L_0x0011;
    L_0x007c:
        r1 = move-exception;
        r2 = "LogProcessor";
        r3 = "readLog2";
        com.amap.api.services.core.C1099ax.m4800a(r1, r2, r3);
        goto L_0x002d;
    L_0x0085:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
    L_0x0088:
        r4 = "LogProcessor";
        r5 = "readLog";
        com.amap.api.services.core.C1099ax.m4800a(r1, r4, r5);	 Catch:{ all -> 0x011b }
        r1.printStackTrace();	 Catch:{ all -> 0x011b }
        if (r2 == 0) goto L_0x0097;
    L_0x0094:
        r2.close();	 Catch:{ IOException -> 0x00da }
    L_0x0097:
        if (r3 == 0) goto L_0x0011;
    L_0x0099:
        r3.close();	 Catch:{ IOException -> 0x009e }
        goto L_0x0011;
    L_0x009e:
        r1 = move-exception;
        r2 = "LogProcessor";
        r3 = "readLog2";
        com.amap.api.services.core.C1099ax.m4800a(r1, r2, r3);
        goto L_0x002d;
    L_0x00a7:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r0 = r1;
    L_0x00ab:
        if (r2 == 0) goto L_0x00b0;
    L_0x00ad:
        r2.close();	 Catch:{ IOException -> 0x00b6 }
    L_0x00b0:
        if (r3 == 0) goto L_0x00b5;
    L_0x00b2:
        r3.close();	 Catch:{ IOException -> 0x00c2 }
    L_0x00b5:
        throw r0;
    L_0x00b6:
        r1 = move-exception;
        r2 = "LogProcessor";
        r4 = "readLog1";
        com.amap.api.services.core.C1099ax.m4800a(r1, r2, r4);
        r1.printStackTrace();
        goto L_0x00b0;
    L_0x00c2:
        r1 = move-exception;
        r2 = "LogProcessor";
        r3 = "readLog2";
        com.amap.api.services.core.C1099ax.m4800a(r1, r2, r3);
        r1.printStackTrace();
        goto L_0x00b5;
    L_0x00ce:
        r1 = move-exception;
        r2 = "LogProcessor";
        r4 = "readLog1";
        com.amap.api.services.core.C1099ax.m4800a(r1, r2, r4);
        r1.printStackTrace();
        goto L_0x005b;
    L_0x00da:
        r1 = move-exception;
        r2 = "LogProcessor";
        r4 = "readLog1";
        com.amap.api.services.core.C1099ax.m4800a(r1, r2, r4);
        r1.printStackTrace();
        goto L_0x0097;
    L_0x00e6:
        r2 = move-exception;
        r3 = "LogProcessor";
        r4 = "readLog1";
        com.amap.api.services.core.C1099ax.m4800a(r2, r3, r4);
        r2.printStackTrace();
        goto L_0x000c;
    L_0x00f3:
        r1 = move-exception;
        r2 = "LogProcessor";
        r3 = "readLog2";
        com.amap.api.services.core.C1099ax.m4800a(r1, r2, r3);
        goto L_0x002d;
    L_0x00fd:
        r2 = move-exception;
        r3 = "LogProcessor";
        r4 = "readLog1";
        com.amap.api.services.core.C1099ax.m4800a(r2, r3, r4);
        r2.printStackTrace();
        goto L_0x001f;
    L_0x010a:
        r1 = move-exception;
        r2 = "LogProcessor";
        r4 = "readLog1";
        com.amap.api.services.core.C1099ax.m4800a(r1, r2, r4);
        r1.printStackTrace();
        goto L_0x0076;
    L_0x0117:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x00ab;
    L_0x011b:
        r0 = move-exception;
        goto L_0x00ab;
    L_0x011d:
        r1 = move-exception;
        r2 = r0;
        goto L_0x0088;
    L_0x0121:
        r1 = move-exception;
        goto L_0x0088;
    L_0x0124:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        goto L_0x004c;
    L_0x0129:
        r1 = move-exception;
        r2 = r0;
        goto L_0x004c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.core.LogUpDateProcessor.m4812c(java.lang.String):java.lang.String");
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: c */
    public void mo12045c() {
        if (this.f3697a != null && !this.f3697a.mo12077a()) {
            try {
                this.f3697a.close();
            } catch (IOException e) {
                C1099ax.m4800a(e, "LogProcessor", "closeDiskLru");
                e.printStackTrace();
            } catch (Throwable e2) {
                C1099ax.m4800a(e2, "LogProcessor", "closeDiskLru");
                e2.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private C1114bj m4805a(Context context, String str) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(context.getFilesDir().getAbsolutePath());
            stringBuilder.append(C1107be.f3724a);
            stringBuilder.append(str);
            File file = new File(stringBuilder.toString());
            if (file.exists() || file.mkdirs()) {
                return C1114bj.m4910a(file, 1, 1, 20480);
            }
            return null;
        } catch (IOException e) {
            C1099ax.m4800a(e, "LogProcessor", "initDiskLru");
            e.printStackTrace();
            return null;
        } catch (Throwable e2) {
            C1099ax.m4800a(e2, "LogProcessor", "initDiskLru");
            e2.printStackTrace();
            return null;
        }
    }
}
