package com.amap.api.mapcore2d;

import android.content.Context;
import android.os.Looper;
import com.amap.api.mapcore2d.C0977cv.C0976a;
import com.amap.api.mapcore2d.C1017dw.C1014a;
import com.amap.api.mapcore2d.C1017dw.C1015b;
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

/* compiled from: LogProcessor */
/* renamed from: com.amap.api.mapcore2d.dh */
public abstract class C0991dh {
    /* renamed from: a */
    private C0977cv f2822a;
    /* renamed from: b */
    private int f2823b;
    /* renamed from: c */
    private C0996dx f2824c;
    /* renamed from: d */
    private C1017dw f2825d;

    /* compiled from: LogProcessor */
    /* renamed from: com.amap.api.mapcore2d.dh$a */
    class C0992a implements C0996dx {
        /* renamed from: b */
        private C1007dr f2827b;

        C0992a(C1007dr c1007dr) {
            this.f2827b = c1007dr;
        }

        /* renamed from: a */
        public void mo10202a(String str) {
            try {
                this.f2827b.mo10231b(str, C0985db.m4080a(C0991dh.this.mo10198b()));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public abstract String mo10193a(List<C0977cv> list);

    /* renamed from: a */
    public abstract boolean mo10197a(Context context);

    protected C0991dh(int i) {
        this.f2823b = i;
    }

    /* renamed from: d */
    private void m4119d(Context context) {
        try {
            this.f2825d = m4112b(context, mo10190a());
        } catch (Throwable th) {
            C0982da.m4076a(th, "LogProcessor", "LogUpDateProcessor");
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo10196a(C0977cv c0977cv, Context context, Throwable th, String str, String str2, String str3) {
        mo10195a(c0977cv);
        String d = m4117d();
        String a = C0966cp.m3938a(context, c0977cv);
        String a2 = C0957cm.m3900a(context);
        String c = m4116c(th);
        if (c != null && !"".equals(c)) {
            int b = mo10198b();
            StringBuilder stringBuilder = new StringBuilder();
            if (str2 != null) {
                stringBuilder.append("class:").append(str2);
            }
            if (str3 != null) {
                stringBuilder.append(" method:").append(str3).append("$").append("<br/>");
            }
            stringBuilder.append(str);
            String a3 = mo10191a(str);
            String a4 = m4105a(a2, a, d, b, c, stringBuilder.toString());
            if (a4 != null && !"".equals(a4)) {
                String a5 = m4104a(context, a4);
                String a6 = mo10190a();
                synchronized (Looper.getMainLooper()) {
                    C1007dr c1007dr = new C1007dr(context);
                    boolean a7 = m4110a(context, a3, a6, a5, c1007dr);
                    m4108a(c1007dr, c0977cv.mo10172a(), a3, b, a7);
                }
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo10194a(Context context, Throwable th, String str, String str2) {
        List<C0977cv> e = m4120e(context);
        if (e != null && e.size() != 0) {
            String a = mo10192a(th);
            if (a != null && !"".equals(a)) {
                for (C0977cv c0977cv : e) {
                    if (C0991dh.m4111a(c0977cv.mo10177e(), a)) {
                        mo10196a(c0977cv, context, th, a, str, str2);
                        return;
                    }
                }
                if (a.contains("com.amap.api.col")) {
                    try {
                        mo10196a(new C0976a("collection", "1.0", "AMap_collection_1.0").mo10170a(new String[]{"com.amap.api.collection"}).mo10171a(), context, th, a, str, str2);
                    } catch (C0956cl e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public void mo10199b(Context context) {
        List e = m4120e(context);
        if (e != null && e.size() != 0) {
            String a = mo10193a(e);
            if (a != null && !"".equals(a)) {
                String d = m4117d();
                String a2 = C0966cp.m3938a(context, this.f2822a);
                int b = mo10198b();
                String a3 = m4105a(C0957cm.m3900a(context), a2, d, b, "ANR", a);
                if (a3 != null && !"".equals(a3)) {
                    String a4 = mo10191a(a);
                    String a5 = m4104a(context, a3);
                    String a6 = mo10190a();
                    synchronized (Looper.getMainLooper()) {
                        C1007dr c1007dr = new C1007dr(context);
                        boolean a7 = m4110a(context, a4, a6, a5, c1007dr);
                        m4108a(c1007dr, this.f2822a.mo10172a(), a4, b, a7);
                    }
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo10195a(C0977cv c0977cv) {
        this.f2822a = c0977cv;
    }

    /* renamed from: e */
    private List<C0977cv> m4120e(Context context) {
        List<C0977cv> a;
        Throwable th;
        Throwable th2;
        Throwable th3;
        List<C0977cv> list = null;
        try {
            synchronized (Looper.getMainLooper()) {
                try {
                    a = new C1008dt(context, false).mo10232a();
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
    private void m4108a(C1007dr c1007dr, String str, String str2, int i, boolean z) {
        C1001ds b = C0985db.m4084b(i);
        b.mo10219a(0);
        b.mo10223b(str);
        b.mo10220a(str2);
        c1007dr.mo10228a(b);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public String mo10191a(String str) {
        return C0970cs.m3997c(str);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public C0996dx mo10189a(C1007dr c1007dr) {
        try {
            if (this.f2824c == null) {
                this.f2824c = new C0992a(c1007dr);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return this.f2824c;
    }

    /* renamed from: a */
    private String m4105a(String str, String str2, String str3, int i, String str4, String str5) {
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
    private String m4104a(Context context, String str) {
        String str2 = null;
        try {
            return C0966cp.m3951e(context, C0978cw.m4050a(str));
        } catch (Throwable th) {
            th.printStackTrace();
            return str2;
        }
    }

    /* renamed from: d */
    private String m4117d() {
        return C0978cw.m4041a(new Date().getTime());
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public String mo10192a(Throwable th) {
        String str = null;
        try {
            return C0991dh.m4113b(th);
        } catch (Throwable th2) {
            th2.printStackTrace();
            return str;
        }
    }

    /* renamed from: c */
    private String m4116c(Throwable th) {
        return th.toString();
    }

    /* renamed from: a */
    private boolean m4110a(Context context, String str, String str2, String str3, C1007dr c1007dr) {
        Throwable th;
        OutputStream outputStream = null;
        C1017dw c1017dw = null;
        C1015b c1015b = null;
        Throwable th2;
        try {
            boolean z;
            File file = new File(C0985db.m4081a(context, str2));
            if (file.exists() || file.mkdirs()) {
                c1017dw = C1017dw.m4228a(file, 1, 1, 20480);
                c1017dw.mo10258a(mo10189a(c1007dr));
                c1015b = c1017dw.mo10257a(str);
                if (c1015b != null) {
                    z = false;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Throwable th3) {
                            th3.printStackTrace();
                        }
                    }
                    if (c1015b != null) {
                        try {
                            c1015b.close();
                        } catch (Throwable th4) {
                            th4.printStackTrace();
                        }
                    }
                    if (c1017dw == null || c1017dw.mo10259a()) {
                        return false;
                    }
                    try {
                        c1017dw.close();
                        return false;
                    } catch (Throwable th5) {
                        th4 = th5;
                    }
                } else {
                    byte[] a = C0978cw.m4050a(str3);
                    C1014a b = c1017dw.mo10260b(str);
                    outputStream = b.mo10249a(0);
                    outputStream.write(a);
                    b.mo10250a();
                    c1017dw.mo10261b();
                    z = true;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Throwable th32) {
                            th32.printStackTrace();
                        }
                    }
                    if (c1015b != null) {
                        try {
                            c1015b.close();
                        } catch (Throwable th42) {
                            th42.printStackTrace();
                        }
                    }
                    if (c1017dw == null || c1017dw.mo10259a()) {
                        return true;
                    }
                    try {
                        c1017dw.close();
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
                if (c1015b != null) {
                    try {
                        c1015b.close();
                    } catch (Throwable th422) {
                        th422.printStackTrace();
                    }
                }
                if (c1017dw == null || c1017dw.mo10259a()) {
                    return false;
                }
                try {
                    c1017dw.close();
                    return false;
                } catch (Throwable th7) {
                    th422 = th7;
                }
            }
            th2.printStackTrace();
            return false;
            if (c1015b != null) {
                c1015b.close();
            }
            if (!(c1017dw == null || c1017dw.mo10259a())) {
                c1017dw.close();
            }
            return false;
            if (c1015b != null) {
                c1015b.close();
            }
            if (!(c1017dw == null || c1017dw.mo10259a())) {
                c1017dw.close();
            }
            return false;
            c1017dw.close();
            return false;
            th422.printStackTrace();
            return z;
            c1017dw.close();
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
    public static boolean m4111a(String[] strArr, String str) {
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
    public void mo10201c(android.content.Context r6) {
        /*
        r5 = this;
        r5.m4119d(r6);	 Catch:{ Throwable -> 0x0035 }
        r0 = r5.mo10197a(r6);	 Catch:{ Throwable -> 0x0035 }
        if (r0 != 0) goto L_0x000a;
    L_0x0009:
        return;
    L_0x000a:
        r1 = android.os.Looper.getMainLooper();	 Catch:{ Throwable -> 0x0035 }
        monitor-enter(r1);	 Catch:{ Throwable -> 0x0035 }
        r0 = new com.amap.api.mapcore2d.dr;	 Catch:{ all -> 0x0032 }
        r0.<init>(r6);	 Catch:{ all -> 0x0032 }
        r2 = r5.mo10198b();	 Catch:{ all -> 0x0032 }
        r5.m4107a(r0, r2);	 Catch:{ all -> 0x0032 }
        r2 = 0;
        r3 = r5.mo10198b();	 Catch:{ all -> 0x0032 }
        r3 = com.amap.api.mapcore2d.C0985db.m4080a(r3);	 Catch:{ all -> 0x0032 }
        r2 = r0.mo10227a(r2, r3);	 Catch:{ all -> 0x0032 }
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
        com.amap.api.mapcore2d.C0982da.m4076a(r0, r1, r2);
        goto L_0x0009;
    L_0x003e:
        r3 = r5.m4106a(r2, r6);	 Catch:{ all -> 0x0032 }
        if (r3 != 0) goto L_0x0046;
    L_0x0044:
        monitor-exit(r1);	 Catch:{ all -> 0x0032 }
        goto L_0x0009;
    L_0x0046:
        r3 = r5.m4115c(r3);	 Catch:{ all -> 0x0032 }
        r4 = 1;
        if (r3 != r4) goto L_0x0054;
    L_0x004d:
        r3 = r5.mo10198b();	 Catch:{ all -> 0x0032 }
        r5.m4109a(r2, r0, r3);	 Catch:{ all -> 0x0032 }
    L_0x0054:
        monitor-exit(r1);	 Catch:{ all -> 0x0032 }
        goto L_0x0009;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore2d.C0991dh.mo10201c(android.content.Context):void");
    }

    /* renamed from: b */
    private boolean m4114b(String str) {
        boolean z = false;
        if (this.f2825d == null) {
            return z;
        }
        try {
            return this.f2825d.mo10263c(str);
        } catch (Throwable th) {
            C0982da.m4076a(th, "LogUpdateProcessor", "deleteLogData");
            return z;
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public String mo10190a() {
        return C0985db.m4086c(this.f2823b);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public int mo10198b() {
        return this.f2823b;
    }

    /* renamed from: a */
    private void m4107a(C1007dr c1007dr, int i) {
        try {
            m4109a(c1007dr.mo10227a(2, C0985db.m4080a(i)), c1007dr, i);
        } catch (Throwable th) {
            C0982da.m4076a(th, "LogProcessor", "processDeleteFail");
        }
    }

    /* renamed from: c */
    private int m4115c(String str) {
        int i = 0;
        try {
            byte[] b = C1021ea.m4260a().mo10270b(new C0986dc(C0978cw.m4054c(C0978cw.m4050a(str))));
            if (b == null) {
                return 0;
            }
            try {
                JSONObject init = JSONObjectInstrumentation.init(C0978cw.m4044a(b));
                String str2 = TenderType.COLUMN_CODE;
                if (init.has(str2)) {
                    return init.getInt(str2);
                }
                return 0;
            } catch (JSONException e) {
                C0982da.m4076a(e, "LogProcessor", "processUpdate");
                return 1;
            }
        } catch (C0956cl e2) {
            if (e2.mo10155b() != 27) {
                i = 1;
            }
            C0982da.m4076a(e2, "LogProcessor", "processUpdate");
            return i;
        }
    }

    /* renamed from: a */
    private void m4109a(List<? extends C1001ds> list, C1007dr c1007dr, int i) {
        if (list != null && list.size() > 0) {
            for (C1001ds c1001ds : list) {
                if (m4114b(c1001ds.mo10221b())) {
                    c1007dr.mo10229a(c1001ds.mo10221b(), c1001ds.getClass());
                } else {
                    c1001ds.mo10219a(2);
                    c1007dr.mo10230b(c1001ds);
                }
            }
        }
    }

    /* renamed from: f */
    private String m4121f(Context context) {
        try {
            String b = C0966cp.m3946b(context);
            if ("".equals(b)) {
                return null;
            }
            return C0966cp.m3947b(context, C0978cw.m4050a(b));
        } catch (Throwable th) {
            C0982da.m4076a(th, "LogProcessor", "getPublicInfo");
            return null;
        }
    }

    /* renamed from: a */
    private String m4106a(List<? extends C1001ds> list, Context context) {
        Object obj;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"pinfo\":\"").append(m4121f(context)).append("\",\"els\":[");
        Object obj2 = 1;
        Iterator it = list.iterator();
        while (true) {
            obj = obj2;
            if (!it.hasNext()) {
                break;
            }
            C1001ds c1001ds = (C1001ds) it.next();
            String d = m4118d(c1001ds.mo10221b());
            if (!(d == null || "".equals(d))) {
                String str = d + "||" + c1001ds.mo10224c();
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
    private java.lang.String m4118d(java.lang.String r7) {
        /*
        r6 = this;
        r0 = 0;
        r1 = 0;
        r2 = 0;
        r3 = r6.f2825d;	 Catch:{ Throwable -> 0x00ce, all -> 0x007a }
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
        r3 = r6.f2825d;	 Catch:{ Throwable -> 0x00ce, all -> 0x007a }
        r3 = r3.mo10257a(r7);	 Catch:{ Throwable -> 0x00ce, all -> 0x007a }
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
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r2, r3);
        goto L_0x0011;
    L_0x002e:
        r1 = 0;
        r3 = r3.mo10252a(r1);	 Catch:{ Throwable -> 0x00ce, all -> 0x007a }
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
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r4, r5);	 Catch:{ all -> 0x00cc }
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
        r0 = com.amap.api.mapcore2d.C0978cw.m4044a(r1);	 Catch:{ Throwable -> 0x0048 }
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
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r2, r4);
        goto L_0x0083;
    L_0x0092:
        r1 = move-exception;
        r2 = "LogProcessor";
        r3 = "readLog2";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r2, r3);
        goto L_0x0088;
    L_0x009b:
        r1 = move-exception;
        r2 = "LogProcessor";
        r4 = "readLog1";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r2, r4);
        goto L_0x0055;
    L_0x00a4:
        r2 = move-exception;
        r3 = "LogProcessor";
        r4 = "readLog1";
        com.amap.api.mapcore2d.C0982da.m4076a(r2, r3, r4);
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
        com.amap.api.mapcore2d.C0982da.m4076a(r2, r3, r4);
        goto L_0x001f;
    L_0x00bf:
        r1 = move-exception;
        r2 = "LogProcessor";
        r4 = "readLog1";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r2, r4);
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
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore2d.C0991dh.m4118d(java.lang.String):java.lang.String");
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: c */
    public void mo10200c() {
        try {
            if (this.f2825d != null && !this.f2825d.mo10259a()) {
                this.f2825d.close();
            }
        } catch (IOException e) {
            C0982da.m4076a(e, "LogProcessor", "closeDiskLru");
        } catch (Throwable e2) {
            C0982da.m4076a(e2, "LogProcessor", "closeDiskLru");
        }
    }

    /* renamed from: b */
    private C1017dw m4112b(Context context, String str) {
        try {
            File file = new File(C0985db.m4081a(context, str));
            if (file.exists() || file.mkdirs()) {
                return C1017dw.m4228a(file, 1, 1, 20480);
            }
            return null;
        } catch (IOException e) {
            C0982da.m4076a(e, "LogProcessor", "initDiskLru");
            return null;
        } catch (Throwable e2) {
            C0982da.m4076a(e2, "LogProcessor", "initDiskLru");
            return null;
        }
    }

    /* renamed from: b */
    public static String m4113b(Throwable th) {
        String a = C0978cw.m4042a(th);
        if (a != null) {
            return a.replaceAll("\n", "<br/>");
        }
        return null;
    }
}
