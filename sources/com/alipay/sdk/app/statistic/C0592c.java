package com.alipay.sdk.app.statistic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.alipay.sdk.sys.C0640b;
import com.alipay.sdk.tid.C0643b;
import com.alipay.sdk.util.C0644a;
import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: com.alipay.sdk.app.statistic.c */
public class C0592c {
    /* renamed from: V */
    private String f514V;
    /* renamed from: W */
    private String f515W;
    /* renamed from: X */
    private String f516X;
    /* renamed from: Y */
    private String f517Y;
    /* renamed from: Z */
    private String f518Z;
    /* renamed from: aa */
    private String f519aa;
    /* renamed from: ab */
    private String f520ab;
    /* renamed from: ac */
    private String f521ac;
    /* renamed from: ad */
    private String f522ad = "";
    /* renamed from: ae */
    private String f523ae;

    public C0592c(Context context) {
        if (context != null) {
            context = context.getApplicationContext();
        }
        this.f514V = m808b();
        this.f516X = m806a(context);
        this.f517Y = m811c();
        this.f518Z = m813d();
        this.f519aa = m809b(context);
        this.f520ab = "-";
        this.f521ac = "-";
        this.f523ae = "-";
    }

    /* renamed from: a */
    public boolean mo8015a() {
        return TextUtils.isEmpty(this.f522ad);
    }

    /* renamed from: a */
    public void mo8013a(String str, String str2, Throwable th) {
        mo8011a(str, str2, m807a(th));
    }

    /* renamed from: a */
    public void mo8014a(String str, String str2, Throwable th, String str3) {
        mo8012a(str, str2, m807a(th), str3);
    }

    /* renamed from: a */
    public void mo8012a(String str, String str2, String str3, String str4) {
        String str5 = "";
        if (!TextUtils.isEmpty(this.f522ad)) {
            str5 = str5 + "^";
        }
        this.f522ad += (str5 + String.format("%s,%s,%s,%s", new Object[]{str, str2, m810b(str3), str4}));
    }

    /* renamed from: a */
    public void mo8011a(String str, String str2, String str3) {
        mo8012a(str, str2, str3, "-");
    }

    /* renamed from: b */
    private String m810b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str.replace("[", "【").replace("]", "】").replace("(", "（").replace(")", "）").replace(",", "，").replace("-", "=").replace("^", "~");
    }

    /* renamed from: a */
    private String m807a(Throwable th) {
        if (th == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(th.getClass().getName()).append(":");
            stringBuffer.append(th.getMessage());
            stringBuffer.append(" 》 ");
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null) {
                for (StackTraceElement stackTraceElement : stackTrace) {
                    stringBuffer.append(stackTraceElement.toString() + " 》 ");
                }
            }
        } catch (Throwable th2) {
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public String mo8010a(String str) {
        if (mo8015a()) {
            return "";
        }
        this.f515W = m812c(str);
        return String.format("[(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s)]", new Object[]{this.f514V, this.f515W, this.f516X, this.f517Y, this.f518Z, this.f519aa, this.f520ab, this.f521ac, this.f522ad, this.f523ae});
    }

    @SuppressLint({"SimpleDateFormat"})
    /* renamed from: b */
    private String m808b() {
        String format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date());
        return String.format("123456789,%s", new Object[]{format});
    }

    /* renamed from: c */
    private String m812c(String str) {
        String str2;
        String str3 = null;
        String[] split = str.split("&");
        if (split != null) {
            str2 = null;
            for (String split2 : split) {
                String[] split3 = split2.split("=");
                if (split3 != null && split3.length == 2) {
                    if (split3[0].equalsIgnoreCase("partner")) {
                        split3[1].replace("\"", "");
                    } else if (split3[0].equalsIgnoreCase("out_trade_no")) {
                        str2 = split3[1].replace("\"", "");
                    } else if (split3[0].equalsIgnoreCase("trade_no")) {
                        str3 = split3[1].replace("\"", "");
                    }
                }
            }
        } else {
            str2 = null;
        }
        str3 = m810b(str3);
        String b = m810b(m810b(str2));
        return String.format("%s,%s,-,%s,-,-,-", new Object[]{str3, m810b(str2), b});
    }

    /* renamed from: a */
    private String m806a(Context context) {
        String str = "-";
        String str2 = "-";
        if (context != null) {
            try {
                Context applicationContext = context.getApplicationContext();
                str = applicationContext.getPackageName();
                str2 = applicationContext.getPackageManager().getPackageInfo(str, 0).versionName;
            } catch (Throwable th) {
            }
        }
        return String.format("%s,%s,-,-,-", new Object[]{str, str2});
    }

    /* renamed from: c */
    private String m811c() {
        String b = m810b("15.5.9");
        String b2 = m810b("h.a.3.5.9");
        return String.format("android,3,%s,%s,com.alipay.mcpay,5.0,-,-,-", new Object[]{b, b2});
    }

    /* renamed from: d */
    private String m813d() {
        String b = m810b(C0643b.m990a(C0640b.m974a().mo8088b()).mo8096a());
        String b2 = m810b(C0640b.m974a().mo8090e());
        return String.format("%s,%s,-,-,-", new Object[]{b, b2});
    }

    /* renamed from: b */
    private String m809b(Context context) {
        String b = m810b(C0644a.m1007d(context));
        String b2 = m810b(VERSION.RELEASE);
        String b3 = m810b(Build.MODEL);
        String b4 = m810b(C0644a.m1004a(context).mo8102a());
        String b5 = m810b(C0644a.m1005b(context).mo8109b());
        String b6 = m810b(C0644a.m1004a(context).mo8104b());
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,-", new Object[]{b, "android", b2, b3, "-", b4, b5, "gw", b6});
    }
}
