package com.alipay.apmobilesecuritysdk.p013a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import com.alipay.apmobilesecuritysdk.common.C0553a;
import com.alipay.apmobilesecuritysdk.otherid.UmidSdkWrapper;
import com.alipay.apmobilesecuritysdk.p014b.C0550a;
import com.alipay.apmobilesecuritysdk.p015c.C0551a;
import com.alipay.apmobilesecuritysdk.p015c.C0552b;
import com.alipay.apmobilesecuritysdk.p016d.C0558e;
import com.alipay.apmobilesecuritysdk.p017e.C0559a;
import com.alipay.apmobilesecuritysdk.p017e.C0560b;
import com.alipay.apmobilesecuritysdk.p017e.C0561c;
import com.alipay.apmobilesecuritysdk.p017e.C0562d;
import com.alipay.apmobilesecuritysdk.p017e.C0565g;
import com.alipay.apmobilesecuritysdk.p017e.C0566h;
import com.alipay.apmobilesecuritysdk.p017e.C0567i;
import com.alipay.security.mobile.module.http.C0709d;
import com.alipay.security.mobile.module.http.model.C0712c;
import com.alipay.security.mobile.module.http.model.C0713d;
import com.alipay.security.mobile.module.http.p024v2.C0714a;
import com.alipay.security.mobile.module.p019a.C0689a;
import com.alipay.security.mobile.module.p021b.C0692b;
import com.alipay.security.mobile.module.p023d.C0702b;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/* renamed from: com.alipay.apmobilesecuritysdk.a.a */
public final class C0549a {
    /* renamed from: a */
    private Context f409a;
    /* renamed from: b */
    private C0550a f410b = C0550a.m638a();
    /* renamed from: c */
    private int f411c = 4;

    public C0549a(Context context) {
        this.f409a = context;
    }

    /* renamed from: a */
    public static String m631a(Context context) {
        String b = C0549a.m635b(context);
        return C0689a.m1169a(b) ? C0566h.m708f(context) : b;
    }

    /* renamed from: a */
    public static String m632a(Context context, String str) {
        try {
            C0549a.m636b();
            String a = C0567i.m712a(str);
            if (!C0689a.m1169a(a)) {
                return a;
            }
            a = C0565g.m691a(context, str);
            C0567i.m716a(str, a);
            if (!C0689a.m1169a(a)) {
                return a;
            }
            return "";
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    private static boolean m633a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] strArr = new String[]{"2017-01-27 2017-01-28", "2017-11-10 2017-11-11", "2017-12-11 2017-12-12"};
        int random = ((int) (((Math.random() * 24.0d) * 60.0d) * 60.0d)) * 1;
        int i = 0;
        while (i < 3) {
            try {
                String[] split = strArr[i].split(" ");
                if (split != null && split.length == 2) {
                    Date date = new Date();
                    Date parse = simpleDateFormat.parse(split[0] + " 00:00:00");
                    Date parse2 = simpleDateFormat.parse(split[1] + " 23:59:59");
                    Calendar instance = Calendar.getInstance();
                    instance.setTime(parse2);
                    instance.add(13, random);
                    parse2 = instance.getTime();
                    if (date.after(parse) && date.before(parse2)) {
                        return true;
                    }
                }
                i++;
            } catch (Exception e) {
            }
        }
        return false;
    }

    /* renamed from: b */
    private C0712c m634b(Map<String, String> map) {
        try {
            C0560b c;
            Context context = this.f409a;
            C0713d c0713d = new C0713d();
            String a = C0689a.m1168a(map, AnalyticAttribute.APP_NAME_ATTRIBUTE, "");
            String a2 = C0689a.m1168a(map, "sessionId", "");
            String a3 = C0689a.m1168a(map, "rpcVersion", "");
            a = C0549a.m632a(context, a);
            String securityToken = UmidSdkWrapper.getSecurityToken(context);
            String d = C0566h.m704d(context);
            if (C0689a.m1172b(a2)) {
                c0713d.mo8202d(a2);
            } else {
                c0713d.mo8202d(a);
            }
            c0713d.mo8204e(securityToken);
            c0713d.mo8206f(d);
            c0713d.mo8198b("android");
            a2 = "";
            d = "";
            securityToken = "";
            a = "";
            C0561c c2 = C0562d.m679c(context);
            if (c2 != null) {
                d = c2.mo7939a();
                securityToken = c2.mo7941c();
            }
            if (C0689a.m1169a(d)) {
                c = C0559a.m664c(context);
                if (c != null) {
                    d = c.mo7936a();
                    securityToken = c.mo7938c();
                }
            }
            c2 = C0562d.m677b();
            if (c2 != null) {
                a2 = c2.mo7939a();
                a = c2.mo7941c();
            }
            if (C0689a.m1169a(a2)) {
                c = C0559a.m662b();
                if (c != null) {
                    a2 = c.mo7936a();
                    a = c.mo7938c();
                }
            }
            c0713d.mo8209h(d);
            c0713d.mo8208g(a2);
            c0713d.mo8195a(a3);
            if (C0689a.m1169a(d)) {
                c0713d.mo8200c(a2);
                c0713d.mo8210i(a);
            } else {
                c0713d.mo8200c(d);
                c0713d.mo8210i(securityToken);
            }
            c0713d.mo8196a(C0558e.m654a(context, map));
            return C0709d.m1274b(this.f409a, this.f410b.mo7935c()).mo8211a(c0713d);
        } catch (Throwable th) {
            C0551a.m644a(th);
            return null;
        }
    }

    /* renamed from: b */
    private static String m635b(Context context) {
        try {
            String b = C0567i.m718b();
            if (!C0689a.m1169a(b)) {
                return b;
            }
            C0561c b2 = C0562d.m678b(context);
            if (b2 != null) {
                C0567i.m715a(b2);
                b = b2.mo7939a();
                if (C0689a.m1172b(b)) {
                    return b;
                }
            }
            C0560b b3 = C0559a.m663b(context);
            if (b3 != null) {
                C0567i.m714a(b3);
                b = b3.mo7936a();
                if (C0689a.m1172b(b)) {
                    return b;
                }
            }
            return "";
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    private static void m636b() {
        try {
            String[] strArr = new String[]{"device_feature_file_name", "wallet_times", "wxcasxx_v3", "wxcasxx_v4", "wxxzyy_v1"};
            for (int i = 0; i < 5; i++) {
                File file = new File(Environment.getExternalStorageDirectory(), ".SystemConfig/" + strArr[i]);
                if (file.exists() && file.canWrite()) {
                    file.delete();
                }
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public final int mo7932a(Map<String, String> map) {
        Object obj = 1;
        try {
            int i;
            String a;
            Object i2;
            C0551a.m642a(this.f409a, C0689a.m1168a(map, "tid", ""), C0689a.m1168a(map, "utdid", ""), C0549a.m631a(this.f409a));
            String a2 = C0689a.m1168a(map, AnalyticAttribute.APP_NAME_ATTRIBUTE, "");
            C0549a.m636b();
            C0549a.m635b(this.f409a);
            C0549a.m632a(this.f409a, a2);
            C0567i.m713a();
            if (!C0549a.m633a() && !C0553a.m646a(this.f409a)) {
                C0558e.m655a();
                if ((!C0689a.m1170a(C0558e.m656b(this.f409a, map), C0567i.m720c()) ? 1 : null) != null) {
                    i2 = 1;
                } else {
                    a = C0689a.m1168a(map, "tid", "");
                    String a3 = C0689a.m1168a(map, "utdid", "");
                    if (C0689a.m1172b(a) && !C0689a.m1170a(a, C0567i.m722d())) {
                        i2 = 1;
                    } else if (C0689a.m1172b(a3) && !C0689a.m1170a(a3, C0567i.m724e())) {
                        i2 = 1;
                    } else if (!C0567i.m717a(this.f409a, a2)) {
                        i2 = 1;
                    } else if (C0689a.m1169a(C0549a.m632a(this.f409a, a2))) {
                        i2 = 1;
                    } else if (C0689a.m1169a(C0549a.m635b(this.f409a))) {
                        i2 = 1;
                    } else {
                        i2 = null;
                    }
                }
            } else if (C0689a.m1169a(C0549a.m632a(this.f409a, a2))) {
                i2 = 1;
            } else if (C0689a.m1169a(C0549a.m635b(this.f409a))) {
                i2 = 1;
            } else {
                i2 = null;
            }
            Context context = this.f409a;
            C0692b.m1182a();
            C0566h.m701b(context, String.valueOf(C0692b.m1213p()));
            if (i2 == null) {
                i2 = 0;
            } else {
                Context context2 = this.f409a;
                C0552b c0552b = new C0552b();
                UmidSdkWrapper.startUmidTaskSync(this.f409a, C0550a.m638a().mo7934b());
                C0712c b = m634b((Map) map);
                switch (b != null ? b.mo8185d() : 2) {
                    case 1:
                        C0566h.m699a(this.f409a, b.mo8186e());
                        C0566h.m705d(this.f409a, b.mo8187f());
                        C0566h.m707e(this.f409a, b.mo8190j());
                        C0566h.m696a(this.f409a, b.mo8192l());
                        C0566h.m709f(this.f409a, b.mo8191k());
                        C0566h.m710g(this.f409a, b.mo8184c());
                        C0567i.m721c(C0558e.m656b(this.f409a, map));
                        C0567i.m716a(a2, b.mo8189h());
                        C0567i.m719b(b.mo8188g());
                        C0567i.m723d(b.mo8193m());
                        a = C0689a.m1168a(map, "tid", "");
                        if (!C0689a.m1172b(a) || C0689a.m1170a(a, C0567i.m722d())) {
                            a = C0567i.m722d();
                        } else {
                            C0567i.m725e(a);
                        }
                        C0567i.m725e(a);
                        a = C0689a.m1168a(map, "utdid", "");
                        if (!C0689a.m1172b(a) || C0689a.m1170a(a, C0567i.m724e())) {
                            a = C0567i.m724e();
                        } else {
                            C0567i.m727f(a);
                        }
                        C0567i.m727f(a);
                        C0567i.m713a();
                        C0562d.m676a(this.f409a, C0567i.m728g());
                        context2 = this.f409a;
                        C0562d.m674a();
                        C0559a.m661a(this.f409a, new C0560b(C0567i.m718b(), C0567i.m720c(), C0567i.m726f()));
                        context2 = this.f409a;
                        C0559a.m659a();
                        C0565g.m694a(this.f409a, a2, C0567i.m712a(a2));
                        context2 = this.f409a;
                        C0565g.m692a();
                        C0566h.m697a(this.f409a, a2, System.currentTimeMillis());
                        break;
                    case 3:
                        i2 = 1;
                        break;
                    default:
                        if (b != null) {
                            C0551a.m643a("Server error, result:" + b.mo8182b());
                        } else {
                            C0551a.m643a("Server error, returned null");
                        }
                        if (C0689a.m1169a(C0549a.m632a(this.f409a, a2))) {
                            i2 = 4;
                            break;
                        }
                        break;
                }
                i2 = 0;
            }
            this.f411c = i2;
            C0714a b2 = C0709d.m1274b(this.f409a, this.f410b.mo7935c());
            Context context3 = this.f409a;
            ConnectivityManager connectivityManager = (ConnectivityManager) context3.getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
            if (!(activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getType() == 1)) {
                obj = null;
            }
            if (obj != null && C0566h.m703c(context3)) {
                new C0702b(context3.getFilesDir().getAbsolutePath() + "/log/ap", b2).mo8177a();
            }
        } catch (Exception e) {
            C0551a.m644a(e);
        }
        return this.f411c;
    }
}
