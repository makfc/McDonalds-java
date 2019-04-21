package com.admaster.square.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import com.admaster.square.utils.CustomPreferenceUtil;
import com.admaster.square.utils.FailedMessage;
import com.admaster.square.utils.Order;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.Thread.State;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SuppressLint({"NewApi"})
/* renamed from: com.admaster.square.api.a */
public class ConvMobiHandler extends HandlerThread {
    /* renamed from: a */
    public static DeviceInfo f151a = null;
    /* renamed from: k */
    private static /* synthetic */ int[] f152k;
    /* renamed from: b */
    private Context f153b = null;
    /* renamed from: c */
    private String f154c = null;
    /* renamed from: d */
    private C0479b f155d = null;
    /* renamed from: e */
    private SendMessageThread f156e = null;
    /* renamed from: f */
    private SendActiveMessageThread f157f = null;
    /* renamed from: g */
    private ConvMobiHelper f158g = null;
    /* renamed from: h */
    private ScheduledExecutorService f159h = null;
    /* renamed from: i */
    private String f160i = null;
    /* renamed from: j */
    private String f161j = null;

    /* renamed from: b */
    static /* synthetic */ int[] m281b() {
        int[] iArr = f152k;
        if (iArr == null) {
            iArr = new int[CustomEvent.values().length];
            try {
                iArr[CustomEvent.ADACTIVE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[CustomEvent.ADAPPLIST.ordinal()] = 13;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[CustomEvent.ADCRASH.ordinal()] = 7;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[CustomEvent.ADCUSTOM1.ordinal()] = 8;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[CustomEvent.ADCUSTOM2.ordinal()] = 9;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[CustomEvent.ADCUSTOM3.ordinal()] = 10;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[CustomEvent.ADCUSTOM4.ordinal()] = 11;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[CustomEvent.ADCUSTOM5.ordinal()] = 12;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[CustomEvent.ADLOGIN.ordinal()] = 4;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[CustomEvent.ADORDER.ordinal()] = 5;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[CustomEvent.ADPLIST.ordinal()] = 14;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[CustomEvent.ADPURCHASE.ordinal()] = 6;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[CustomEvent.ADREG.ordinal()] = 3;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[CustomEvent.ADSTART.ordinal()] = 2;
            } catch (NoSuchFieldError e14) {
            }
            f152k = iArr;
        }
        return iArr;
    }

    private ConvMobiHandler(Context context, String str, String str2, String str3, boolean z) {
        super("AdMasterConvMobi", 1);
        start();
        this.f153b = context;
        this.f154c = str2;
        this.f160i = str3;
        m282c();
        this.f155d = new C0479b(getLooper(), this);
        this.f161j = CustomPreferenceUtil.m409a(context, "sp_store", "ch");
        if (!TextUtils.isEmpty(this.f161j)) {
            CustomPreferenceUtil.m413b(context, "sp_store", str, this.f161j);
        } else if (!TextUtils.isEmpty(this.f160i)) {
            CustomPreferenceUtil.m413b(context, "sp_store", str, this.f160i);
        } else if (TextUtils.isEmpty(this.f160i)) {
            if (TextUtils.isEmpty(str2)) {
                CustomPreferenceUtil.m413b(context, "sp_store", str, null);
            } else {
                CustomPreferenceUtil.m413b(context, "sp_store", str, str2);
            }
        }
        if (this.f158g == null) {
            this.f158g = ConvMobiHelper.m299a(this.f153b, f151a);
        }
        boolean d = m283d();
        Message obtain = Message.obtain();
        if (d) {
            obtain.arg1 = CustomEvent.ADSTART.ordinal();
        } else if (z) {
            obtain.arg1 = CustomEvent.ADSTART.ordinal();
        } else {
            obtain.arg1 = CustomEvent.ADACTIVE.ordinal();
        }
        this.f155d.sendMessage(obtain);
    }

    private ConvMobiHandler(Context context, String str, String str2, boolean z) {
        super("AdMasterConvMobi", 1);
        start();
        this.f153b = context;
        this.f154c = CustomPreferenceUtil.m409a(context, "sp_store", "ch");
        this.f160i = str2;
        m282c();
        this.f155d = new C0479b(getLooper(), this);
        if (TextUtils.isEmpty(this.f154c)) {
            if (TextUtils.isEmpty(this.f160i)) {
                CustomPreferenceUtil.m413b(context, "sp_store", str, null);
            } else {
                CustomPreferenceUtil.m413b(context, "sp_store", str, this.f160i);
            }
        } else if (TextUtils.isEmpty(this.f160i)) {
            CustomPreferenceUtil.m413b(context, "sp_store", str, this.f154c);
        } else {
            CustomPreferenceUtil.m413b(context, "sp_store", str, this.f160i);
        }
        if (this.f158g == null) {
            this.f158g = ConvMobiHelper.m299a(this.f153b, f151a);
        }
        boolean d = m283d();
        Message obtain = Message.obtain();
        if (d) {
            obtain.arg1 = CustomEvent.ADSTART.ordinal();
        } else if (z) {
            obtain.arg1 = CustomEvent.ADSTART.ordinal();
        } else {
            obtain.arg1 = CustomEvent.ADACTIVE.ordinal();
        }
        this.f155d.sendMessage(obtain);
    }

    /* renamed from: a */
    public static synchronized ConvMobiHandler m275a(Context context, String str, String str2, String str3, boolean z) {
        ConvMobiHandler convMobiHandler = null;
        synchronized (ConvMobiHandler.class) {
            if (TextUtils.isEmpty(str)) {
                Logger.m364b("AdMasterConvMobi missing m2id!");
            } else if (ConvMobiInstance.m314a(context)) {
                convMobiHandler = new ConvMobiHandler(context, str, str2, str3, z);
            } else {
                Logger.m364b("AdMasterConvMobiis not initialize correctly");
            }
        }
        return convMobiHandler;
    }

    /* renamed from: a */
    public static synchronized ConvMobiHandler m276a(Context context, String str, String str2, boolean z) {
        ConvMobiHandler convMobiHandler = null;
        synchronized (ConvMobiHandler.class) {
            if (TextUtils.isEmpty(str)) {
                Logger.m364b("AdMasterConvMobi missing m2id!");
            } else if (ConvMobiInstance.m314a(context)) {
                convMobiHandler = new ConvMobiHandler(context, str, str2, z);
            } else {
                Logger.m364b("AdMasterConvMobiis not initialize correctly");
            }
        }
        return convMobiHandler;
    }

    /* renamed from: a */
    public void mo7742a(String str) {
        this.f160i = str;
        if (!TextUtils.isEmpty(this.f160i)) {
            CustomPreferenceUtil.m411a(this.f153b, "sp_store", "ch", this.f160i);
        }
    }

    /* renamed from: a */
    public void mo7743a(String str, long j) {
        Message obtain = Message.obtain();
        obtain.arg1 = CustomEvent.ADREG.ordinal();
        Bundle bundle = new Bundle();
        bundle.putString(AnalyticAttribute.USER_ID_ATTRIBUTE, str);
        bundle.putLong("timer", j);
        obtain.setData(bundle);
        this.f155d.sendMessage(obtain);
    }

    /* renamed from: b */
    public void mo7746b(String str, long j) {
        Message obtain = Message.obtain();
        obtain.arg1 = CustomEvent.ADLOGIN.ordinal();
        Bundle bundle = new Bundle();
        bundle.putString(AnalyticAttribute.USER_ID_ATTRIBUTE, str);
        bundle.putLong("timer", j);
        obtain.setData(bundle);
        this.f155d.sendMessage(obtain);
    }

    /* renamed from: c */
    private void m282c() {
        if (f151a == null) {
            f151a = new DeviceInfo(this.f153b);
        }
    }

    /* renamed from: a */
    private void m279a(String str, Order order, long j, CustomEvent customEvent) {
        try {
            SharedPreferences a = CustomPreferenceUtil.m407a(this.f153b, "com.admaster.convmobisdk.failed.active");
            switch (ConvMobiHandler.m281b()[customEvent.ordinal()]) {
                case 1:
                    if (a == null || a.getAll().isEmpty()) {
                        this.f158g.mo7751a(str, null, customEvent, j);
                        return;
                    } else {
                        m284e();
                        return;
                    }
                case 2:
                case 4:
                    this.f158g.mo7751a(str, null, customEvent, j);
                    return;
                case 3:
                    this.f158g.mo7751a(str, null, customEvent, j);
                    return;
                case 5:
                case 6:
                    this.f158g.mo7751a(str, (Object) order, customEvent, j);
                    return;
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                    this.f158g.mo7751a(null, null, customEvent, j);
                    return;
                default:
                    return;
            }
        } catch (Exception e) {
            Logger.m364b(e.getMessage());
        }
        Logger.m364b(e.getMessage());
    }

    /* renamed from: a */
    public void mo7744a(String str, Order order) {
        Message obtain = Message.obtain();
        obtain.arg1 = CustomEvent.ADORDER.ordinal();
        Bundle bundle = new Bundle();
        bundle.putString(AnalyticAttribute.USER_ID_ATTRIBUTE, str);
        bundle.putSerializable(JiceArgs.EVENT_SUBMIT_ORDER, order);
        obtain.setData(bundle);
        this.f155d.sendMessage(obtain);
    }

    /* renamed from: b */
    public void mo7747b(String str, Order order) {
        if (TextUtils.isEmpty(str)) {
            Logger.m364b("userId cann't be null or empty!");
            return;
        }
        Message obtain = Message.obtain();
        obtain.arg1 = CustomEvent.ADPURCHASE.ordinal();
        Bundle bundle = new Bundle();
        bundle.putString(AnalyticAttribute.USER_ID_ATTRIBUTE, str);
        bundle.putSerializable(JiceArgs.EVENT_SUBMIT_ORDER, order);
        obtain.setData(bundle);
        this.f155d.sendMessage(obtain);
    }

    /* renamed from: a */
    public void mo7741a(CustomEvent customEvent, long j) {
        Message obtain = Message.obtain();
        obtain.arg1 = customEvent.ordinal();
        this.f155d.sendMessage(obtain);
    }

    /* renamed from: a */
    public void mo7745a(Throwable th) {
        String str = null;
        try {
            Message.obtain().arg1 = CustomEvent.ADCRASH.ordinal();
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                th.printStackTrace(new PrintStream(byteArrayOutputStream));
                str = new String(byteArrayOutputStream.toByteArray());
            } catch (Exception e) {
                Logger.m364b(e.getMessage());
            }
            if (!TextUtils.isEmpty(str)) {
                long currentTimeMillis = System.currentTimeMillis();
                Object failedMessage = new FailedMessage();
                try {
                    failedMessage.mo7828a(currentTimeMillis);
                    failedMessage.mo7831b(currentTimeMillis);
                } catch (Exception e2) {
                    Logger.m364b(e2.getMessage());
                }
                failedMessage.mo7827a(1);
                m282c();
                String a = this.f158g.mo7749a(str, new StringBuilder(String.valueOf(currentTimeMillis)).toString(), null, CustomEvent.ADCRASH);
                if (!TextUtils.isEmpty(a)) {
                    failedMessage.mo7829a(a);
                    CustomPreferenceUtil.m410a(this.f153b, "com.admaster.convmobisdk.falied", Base64.encodeToString(str.getBytes(), 2), failedMessage);
                }
            }
        } catch (Exception e22) {
            Logger.m364b(e22.getMessage());
        }
    }

    /* renamed from: a */
    public void mo7739a() {
        try {
            if (this.f156e != null) {
                this.f156e = null;
            }
            if (this.f157f != null && (this.f157f.getState() == State.RUNNABLE || this.f157f.isAlive())) {
                this.f157f.interrupt();
                this.f157f = null;
            }
            m285f();
            getLooper().quit();
        } catch (Exception e) {
            Logger.m364b(e.getMessage());
        }
    }

    /* renamed from: d */
    private boolean m283d() {
        return CustomPreferenceUtil.m414b(this.f153b, "sp_store", "is_install");
    }

    /* renamed from: e */
    private void m284e() {
        if ((this.f157f == null || !(this.f157f.getState() == State.NEW || this.f157f.isAlive())) && this.f153b != null) {
            SharedPreferences a = CustomPreferenceUtil.m407a(this.f153b, "com.admaster.convmobisdk.failed.active");
            if (a != null && !a.getAll().isEmpty()) {
                this.f157f = new SendActiveMessageThread("com.admaster.convmobisdk.failed.active", this.f153b);
                this.f157f.start();
            }
        }
    }

    /* renamed from: f */
    private void m285f() {
        try {
            if (this.f159h != null) {
                this.f159h.shutdown();
                this.f159h = null;
            }
        } catch (Exception e) {
            Logger.m364b(e.getMessage());
        }
    }

    /* renamed from: g */
    private void m286g() {
        try {
            if (this.f159h == null || this.f159h.isShutdown()) {
                this.f156e = new SendMessageThread("com.admaster.convmobisdk.falied", this.f153b);
                this.f159h = Executors.newSingleThreadScheduledExecutor();
                this.f159h.scheduleWithFixedDelay(this.f156e, 1000, 300000, TimeUnit.MILLISECONDS);
            }
        } catch (Exception e) {
            Logger.m364b(e.getMessage());
        }
    }

    /* renamed from: a */
    public void mo7740a(Intent intent) {
        CharSequence charSequence;
        if (intent != null) {
            String charSequence2;
            try {
                charSequence2 = intent.getData().getQueryParameter("referrer");
            } catch (Exception e) {
                charSequence2 = null;
            }
            if (TextUtils.isEmpty(charSequence2)) {
                try {
                    charSequence2 = (String) intent.getExtras().get("referrer");
                } catch (Exception e2) {
                    charSequence2 = null;
                }
            }
            if (!TextUtils.isEmpty(charSequence2)) {
                CustomPreferenceUtil.m411a(this.f153b, "sp_store", "ch", charSequence2);
            }
        }
    }
}
