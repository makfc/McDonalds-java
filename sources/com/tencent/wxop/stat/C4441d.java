package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.common.C4433k;
import java.util.Timer;
import java.util.TimerTask;

/* renamed from: com.tencent.wxop.stat.d */
public class C4441d {
    /* renamed from: b */
    private static volatile C4441d f7166b = null;
    /* renamed from: a */
    private Timer f7167a = null;
    /* renamed from: c */
    private Context f7168c = null;

    private C4441d(Context context) {
        this.f7168c = context.getApplicationContext();
        this.f7167a = new Timer(false);
    }

    /* renamed from: a */
    public static C4441d m8169a(Context context) {
        if (f7166b == null) {
            synchronized (C4441d.class) {
                if (f7166b == null) {
                    f7166b = new C4441d(context);
                }
            }
        }
        return f7166b;
    }

    /* renamed from: a */
    public void mo33972a() {
        if (StatConfig.getStatSendStrategy() == StatReportStrategy.PERIOD) {
            long sendPeriodMinutes = (long) ((StatConfig.getSendPeriodMinutes() * 60) * 1000);
            if (StatConfig.isDebugEnable()) {
                C4433k.m8111b().mo33952i("setupPeriodTimer delay:" + sendPeriodMinutes);
            }
            mo33973a(new C4442e(this), sendPeriodMinutes);
        }
    }

    /* renamed from: a */
    public void mo33973a(TimerTask timerTask, long j) {
        if (this.f7167a != null) {
            if (StatConfig.isDebugEnable()) {
                C4433k.m8111b().mo33952i("setupPeriodTimer schedule delay:" + j);
            }
            this.f7167a.schedule(timerTask, j);
        } else if (StatConfig.isDebugEnable()) {
            C4433k.m8111b().mo33956w("setupPeriodTimer schedule timer == null");
        }
    }
}
