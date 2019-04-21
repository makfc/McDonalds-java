package com.admaster.square.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.admaster.square.utils.AndroidUtil;
import com.admaster.square.utils.ConnectUtil;
import com.admaster.square.utils.Constant;
import com.admaster.square.utils.Order;
import com.admaster.square.utils.UdidInfoUtil;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressLint({"NewApi"})
/* renamed from: com.admaster.square.api.e */
public class ConvMobiInstance {
    /* renamed from: c */
    private static volatile ConvMobiInstance f177c = null;
    /* renamed from: d */
    private static ExecutorService f178d;
    /* renamed from: a */
    private ConvMobiHandler f179a;
    /* renamed from: b */
    private String f180b = null;
    /* renamed from: e */
    private ConnectUtil f181e;
    /* renamed from: f */
    private Context f182f;
    /* renamed from: g */
    private Handler f183g = new C0481f(this, Looper.getMainLooper());

    private ConvMobiInstance() {
        f178d = Executors.newFixedThreadPool(3);
    }

    /* renamed from: a */
    public static synchronized ConvMobiInstance m312a() {
        ConvMobiInstance convMobiInstance;
        synchronized (ConvMobiInstance.class) {
            if (f177c == null) {
                f177c = new ConvMobiInstance();
            }
            convMobiInstance = f177c;
        }
        return convMobiInstance;
    }

    /* renamed from: a */
    public static boolean m314a(Context context) {
        if (ConvMobiInstance.m317c(context)) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public void mo7759a(String str) {
        this.f180b = str;
        if (this.f179a != null) {
            this.f179a.mo7742a(str);
        }
    }

    /* renamed from: c */
    private static boolean m317c(Context context) {
        if (context == null) {
            try {
                Logger.m364b("Missing context");
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else if (AndroidUtil.m397b(context, "android.permission.INTERNET")) {
            return true;
        } else {
            Logger.m364b("Missing permission: INTERNET");
            return false;
        }
    }

    /* renamed from: a */
    public void mo7755a(Context context, String str, String str2, boolean z) {
        try {
            this.f182f = context;
            this.f181e = ConnectUtil.m398a();
            UdidInfoUtil.m491b(context);
            m318d(context);
            f178d.execute(new C0482g(this, context, str, str2, z));
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    public void mo7754a(Context context, String str, String str2) {
        try {
            this.f182f = context;
            this.f181e = ConnectUtil.m398a();
            UdidInfoUtil.m491b(context);
            m318d(context);
            f178d.execute(new C0483h(this, context, str, str2));
        } catch (Exception e) {
        }
    }

    /* renamed from: d */
    private void m318d(Context context) {
        try {
            this.f183g.sendEmptyMessage(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void mo7756a(Context context, String str, boolean z) {
        try {
            this.f181e = ConnectUtil.m398a();
            this.f182f = context;
            UdidInfoUtil.m491b(context);
            m318d(context);
            f178d.execute(new C0484i(this, context, str, z));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void mo7753a(Context context, String str) {
        try {
            this.f181e = ConnectUtil.m398a();
            this.f182f = context;
            UdidInfoUtil.m491b(context);
            m318d(context);
            f178d.execute(new C0485j(this, context, str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public void mo7765b(Context context) {
        String a = AndroidUtil.m390a(context, Constant.f263e);
        String a2 = AndroidUtil.m390a(context, Constant.f264f);
        if (TextUtils.isEmpty(a2)) {
            mo7756a(context, a, true);
        } else {
            mo7755a(context, a, a2, true);
        }
    }

    /* renamed from: c */
    private boolean m316c() {
        if (this.f179a != null) {
            return true;
        }
        Logger.m364b("Please initialize AdconvMobi by calling 'init' before");
        return false;
    }

    /* renamed from: a */
    public void mo7763a(boolean z) {
        Logger.m363a(z);
    }

    /* renamed from: a */
    public void mo7760a(String str, long j) {
        if (this.f179a != null) {
            this.f179a.mo7743a(str, j);
        } else {
            Logger.m364b("Please initialize AdconvMobi by calling 'init' before");
        }
    }

    /* renamed from: b */
    public void mo7766b(String str, long j) {
        if (this.f179a != null) {
            this.f179a.mo7746b(str, j);
        } else {
            Logger.m364b("Please initialize AdconvMobi by calling 'init' before");
        }
    }

    /* renamed from: a */
    public void mo7761a(String str, Order order) {
        if (this.f179a != null) {
            this.f179a.mo7744a(str, order);
        } else {
            Logger.m364b("Please initialize AdconvMobi by calling 'init' before");
        }
    }

    /* renamed from: b */
    public void mo7767b(String str, Order order) {
        if (this.f179a != null) {
            this.f179a.mo7747b(str, order);
        } else {
            Logger.m364b("Please initialize AdconvMobi by calling 'init' before");
        }
    }

    /* renamed from: a */
    public void mo7758a(CustomEvent customEvent, long j) {
        if (this.f179a != null) {
            this.f179a.mo7741a(customEvent, j);
        } else {
            Logger.m364b("Please initialize AdconvMobi by calling 'init' before");
        }
    }

    /* renamed from: b */
    public void mo7764b() {
        if (m316c()) {
            this.f179a.mo7739a();
            this.f179a = null;
        }
    }

    /* renamed from: a */
    public void mo7762a(Throwable th) {
        if (this.f179a != null) {
            this.f179a.mo7745a(th);
        } else {
            Logger.m364b("Please initialize AdconvMobi by calling 'init' before");
        }
    }

    /* renamed from: a */
    public void mo7757a(Intent intent) {
        if (this.f179a != null) {
            this.f179a.mo7740a(intent);
        }
    }
}
