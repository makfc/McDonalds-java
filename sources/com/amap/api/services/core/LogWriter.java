package com.amap.api.services.core;

import android.content.Context;
import android.os.Looper;
import com.amap.api.services.core.C1114bj.C1111a;
import com.facebook.stetho.common.Utf8Charset;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/* renamed from: com.amap.api.services.core.bh */
abstract class LogWriter {
    /* renamed from: a */
    private C1081ac f3701a;

    /* renamed from: a */
    public abstract int mo12047a();

    /* renamed from: a */
    public abstract C1101bm mo12048a(C1085aj c1085aj);

    /* renamed from: a */
    public abstract String mo12049a(String str);

    /* renamed from: a */
    public abstract String mo12051a(List<C1081ac> list);

    /* renamed from: b */
    public abstract String mo12056b();

    LogWriter() {
    }

    /* renamed from: a */
    static LogWriter m4823a(int i) {
        switch (i) {
            case 0:
                return new CrashLogWriter();
            case 1:
                return new ExceptionLogWriter();
            case 2:
                return new ANRLogWriter();
            default:
                return null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo12053a(Context context, Throwable th, String str, String str2) {
        List<C1081ac> b = m4830b(context);
        if (b != null && b.size() != 0) {
            String a = mo12050a(th);
            if (a != null && !"".equals(a)) {
                for (C1081ac c1081ac : b) {
                    if (mo12055a(c1081ac.mo11993f(), a)) {
                        mo12054a(c1081ac);
                        String c = m4831c();
                        String a2 = m4824a(context, c1081ac);
                        String c2 = m4832c(context);
                        String b2 = m4829b(th);
                        if (b2 != null && !"".equals(b2)) {
                            int a3 = mo12047a();
                            StringBuilder stringBuilder = new StringBuilder();
                            if (str != null) {
                                stringBuilder.append("class:").append(str);
                            }
                            if (str2 != null) {
                                stringBuilder.append(" method:").append(str2).append("$").append("<br/>");
                            }
                            stringBuilder.append(a);
                            String a4 = mo12049a(a);
                            String a5 = m4826a(c2, a2, c, a3, b2, stringBuilder.toString());
                            if (a5 != null && !"".equals(a5)) {
                                String a6 = m4825a(context, a5);
                                String b3 = mo12056b();
                                synchronized (Looper.getMainLooper()) {
                                    C1085aj c1085aj = new C1085aj(context);
                                    boolean a7 = m4828a(context, a4, b3, a6, c1085aj);
                                    m4827a(c1085aj, c1081ac.mo11988a(), a4, a3, a7);
                                }
                            } else {
                                return;
                            }
                        }
                        return;
                    }
                }
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo12052a(Context context) {
        List b = m4830b(context);
        if (b != null && b.size() != 0) {
            String a = mo12051a(b);
            if (a != null && !"".equals(a)) {
                String c = m4831c();
                String a2 = m4824a(context, this.f3701a);
                int a3 = mo12047a();
                String a4 = m4826a(m4832c(context), a2, c, a3, "ANR", a);
                if (a4 != null && !"".equals(a4)) {
                    String a5 = mo12049a(a);
                    String a6 = m4825a(context, a4);
                    String b2 = mo12056b();
                    synchronized (Looper.getMainLooper()) {
                        C1085aj c1085aj = new C1085aj(context);
                        boolean a7 = m4828a(context, a5, b2, a6, c1085aj);
                        m4827a(c1085aj, this.f3701a.mo11988a(), a5, a3, a7);
                    }
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo12054a(C1081ac c1081ac) {
        this.f3701a = c1081ac;
    }

    /* renamed from: b */
    private List<C1081ac> m4830b(Context context) {
        List<C1081ac> a;
        Throwable th;
        Throwable th2;
        Throwable th3;
        List<C1081ac> list = null;
        try {
            synchronized (Looper.getMainLooper()) {
                try {
                    a = new C1087am(context).mo12019a();
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
    private void m4827a(C1085aj c1085aj, String str, String str2, int i, boolean z) {
        C1086al c1086al = new C1086al();
        c1086al.mo12012a(0);
        c1086al.mo12016b(str);
        c1086al.mo12013a(str2);
        c1085aj.mo12009b(c1086al, i);
    }

    /* renamed from: a */
    private String m4826a(String str, String str2, String str3, int i, String str4, String str5) {
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
    private String m4825a(Context context, String str) {
        String str2 = null;
        try {
            return C1136x.m5095a(context, str.getBytes(Utf8Charset.NAME));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str2;
        } catch (Throwable th) {
            th.printStackTrace();
            return str2;
        }
    }

    /* renamed from: c */
    private String m4831c() {
        return C1109bi.m4885a(new Date().getTime());
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public String mo12050a(Throwable th) {
        String str = null;
        try {
            return C1109bi.m4886a(th);
        } catch (Throwable th2) {
            th2.printStackTrace();
            return str;
        }
    }

    /* renamed from: b */
    private String m4829b(Throwable th) {
        return th.toString();
    }

    /* renamed from: a */
    private String m4824a(Context context, C1081ac c1081ac) {
        return C1136x.m5092a(context, c1081ac);
    }

    /* renamed from: c */
    private String m4832c(Context context) {
        return C1134v.m5086e(context);
    }

    /* renamed from: a */
    private boolean m4828a(Context context, String str, String str2, String str3, C1085aj c1085aj) {
        OutputStream outputStream = null;
        C1114bj c1114bj = null;
        Throwable th;
        Throwable th2;
        try {
            boolean z;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(context.getFilesDir().getAbsolutePath());
            stringBuilder.append(C1107be.f3724a);
            stringBuilder.append(str2);
            File file = new File(stringBuilder.toString());
            if (file.exists() || file.mkdirs()) {
                c1114bj = C1114bj.m4910a(file, 1, 1, 20480);
                c1114bj.mo12076a(mo12048a(c1085aj));
                if (c1114bj.mo12075a(str) != null) {
                    z = false;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Throwable th3) {
                            th3.printStackTrace();
                        }
                    }
                    if (c1114bj == null || c1114bj.mo12077a()) {
                        return false;
                    }
                    try {
                        c1114bj.close();
                        return false;
                    } catch (Throwable th4) {
                        th = th4;
                    }
                } else {
                    byte[] bytes = str3.getBytes(Utf8Charset.NAME);
                    C1111a b = c1114bj.mo12078b(str);
                    outputStream = b.mo12067a(0);
                    outputStream.write(bytes);
                    b.mo12068a();
                    c1114bj.mo12079b();
                    z = true;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Throwable th32) {
                            th32.printStackTrace();
                        }
                    }
                    if (c1114bj == null || c1114bj.mo12077a()) {
                        return true;
                    }
                    try {
                        c1114bj.close();
                        return true;
                    } catch (Throwable th5) {
                        th = th5;
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
                if (c1114bj == null || c1114bj.mo12077a()) {
                    return false;
                }
                try {
                    c1114bj.close();
                    return false;
                } catch (Throwable th6) {
                    th = th6;
                }
            }
            if (!(c1114bj == null || c1114bj.mo12077a())) {
                c1114bj.close();
            }
            return false;
            th2.printStackTrace();
            return false;
            if (!(c1114bj == null || c1114bj.mo12077a())) {
                c1114bj.close();
            }
            return false;
            th.printStackTrace();
            return z;
        } catch (IOException e) {
            e.printStackTrace();
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (Throwable th7) {
            th2 = th7;
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public boolean mo12055a(String[] strArr, String str) {
        if (strArr == null || str == null) {
            return false;
        }
        try {
            for (String indexOf : strArr) {
                if (str.indexOf(indexOf) != -1) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }
}
