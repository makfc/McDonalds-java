package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.common.C4433k;
import com.tencent.wxop.stat.common.C4438p;
import com.tencent.wxop.stat.p069a.C4377e;

/* renamed from: com.tencent.wxop.stat.aq */
class C4406aq {
    /* renamed from: f */
    private static volatile long f7033f = 0;
    /* renamed from: a */
    private C4377e f7034a;
    /* renamed from: b */
    private StatReportStrategy f7035b = null;
    /* renamed from: c */
    private boolean f7036c = false;
    /* renamed from: d */
    private Context f7037d = null;
    /* renamed from: e */
    private long f7038e = System.currentTimeMillis();

    public C4406aq(C4377e c4377e) {
        this.f7034a = c4377e;
        this.f7035b = StatConfig.getStatSendStrategy();
        this.f7036c = c4377e.mo33889f();
        this.f7037d = c4377e.mo33888e();
    }

    /* renamed from: a */
    private void m8014a(C4407h c4407h) {
        C4445i.m8180b(StatServiceImpl.f6937t).mo33980a(this.f7034a, c4407h);
    }

    /* renamed from: b */
    private void m8016b() {
        if (this.f7034a.mo33887d() != null && this.f7034a.mo33887d().isSendImmediately()) {
            this.f7035b = StatReportStrategy.INSTANT;
        }
        if (StatConfig.f6896j && C4389a.m7995a(StatServiceImpl.f6937t).mo33903e()) {
            this.f7035b = StatReportStrategy.INSTANT;
        }
        if (StatConfig.isDebugEnable()) {
            StatServiceImpl.f6934q.mo33952i("strategy=" + this.f7035b.name());
        }
        switch (C4396ag.f7011a[this.f7035b.ordinal()]) {
            case 1:
                m8017c();
                return;
            case 2:
                C4411au.m8029a(this.f7037d).mo33927a(this.f7034a, null, this.f7036c, false);
                if (StatConfig.isDebugEnable()) {
                    StatServiceImpl.f6934q.mo33952i("PERIOD currTime=" + this.f7038e + ",nextPeriodSendTs=" + StatServiceImpl.f6920c + ",difftime=" + (StatServiceImpl.f6920c - this.f7038e));
                }
                if (StatServiceImpl.f6920c == 0) {
                    StatServiceImpl.f6920c = C4438p.m8150a(this.f7037d, "last_period_ts", 0);
                    if (this.f7038e > StatServiceImpl.f6920c) {
                        StatServiceImpl.m7954e(this.f7037d);
                    }
                    long sendPeriodMinutes = this.f7038e + ((long) ((StatConfig.getSendPeriodMinutes() * 60) * 1000));
                    if (StatServiceImpl.f6920c > sendPeriodMinutes) {
                        StatServiceImpl.f6920c = sendPeriodMinutes;
                    }
                    C4441d.m8169a(this.f7037d).mo33972a();
                }
                if (StatConfig.isDebugEnable()) {
                    StatServiceImpl.f6934q.mo33952i("PERIOD currTime=" + this.f7038e + ",nextPeriodSendTs=" + StatServiceImpl.f6920c + ",difftime=" + (StatServiceImpl.f6920c - this.f7038e));
                }
                if (this.f7038e > StatServiceImpl.f6920c) {
                    StatServiceImpl.m7954e(this.f7037d);
                    return;
                }
                return;
            case 3:
            case 4:
                C4411au.m8029a(this.f7037d).mo33927a(this.f7034a, null, this.f7036c, false);
                return;
            case 5:
                C4411au.m8029a(this.f7037d).mo33927a(this.f7034a, new C4408ar(this), this.f7036c, true);
                return;
            case 6:
                if (C4389a.m7995a(StatServiceImpl.f6937t).mo33901c() == 1) {
                    m8017c();
                    return;
                } else {
                    C4411au.m8029a(this.f7037d).mo33927a(this.f7034a, null, this.f7036c, false);
                    return;
                }
            case 7:
                if (C4433k.m8119e(this.f7037d)) {
                    m8014a(new C4409as(this));
                    return;
                }
                return;
            default:
                StatServiceImpl.f6934q.error("Invalid stat strategy:" + StatConfig.getStatSendStrategy());
                return;
        }
    }

    /* renamed from: c */
    private void m8017c() {
        if (C4411au.m8043b().f7045a <= 0 || !StatConfig.f6898l) {
            m8014a(new C4410at(this));
            return;
        }
        C4411au.m8043b().mo33927a(this.f7034a, null, this.f7036c, true);
        C4411au.m8043b().mo33926a(-1);
    }

    /* renamed from: d */
    private boolean m8019d() {
        if (StatConfig.f6894h > 0) {
            if (this.f7038e > StatServiceImpl.f6925h) {
                StatServiceImpl.f6924g.clear();
                StatServiceImpl.f6925h = this.f7038e + StatConfig.f6895i;
                if (StatConfig.isDebugEnable()) {
                    StatServiceImpl.f6934q.mo33952i("clear methodsCalledLimitMap, nextLimitCallClearTime=" + StatServiceImpl.f6925h);
                }
            }
            Integer valueOf = Integer.valueOf(this.f7034a.mo33883a().mo33896a());
            Integer num = (Integer) StatServiceImpl.f6924g.get(valueOf);
            if (num != null) {
                StatServiceImpl.f6924g.put(valueOf, Integer.valueOf(num.intValue() + 1));
                if (num.intValue() > StatConfig.f6894h) {
                    if (StatConfig.isDebugEnable()) {
                        StatServiceImpl.f6934q.mo33948e("event " + this.f7034a.mo33890g() + " was discard, cause of called limit, current:" + num + ", limit:" + StatConfig.f6894h + ", period:" + StatConfig.f6895i + " ms");
                    }
                    return true;
                }
            }
            StatServiceImpl.f6924g.put(valueOf, Integer.valueOf(1));
        }
        return false;
    }

    /* renamed from: a */
    public void mo33922a() {
        if (!m8019d()) {
            if (StatConfig.f6899m > 0 && this.f7038e >= f7033f) {
                StatServiceImpl.flushDataToDB(this.f7037d);
                f7033f = this.f7038e + StatConfig.f6900n;
                if (StatConfig.isDebugEnable()) {
                    StatServiceImpl.f6934q.mo33952i("nextFlushTime=" + f7033f);
                }
            }
            if (C4389a.m7995a(this.f7037d).mo33904f()) {
                if (StatConfig.isDebugEnable()) {
                    StatServiceImpl.f6934q.mo33952i("sendFailedCount=" + StatServiceImpl.f6918a);
                }
                if (StatServiceImpl.m7942a()) {
                    C4411au.m8029a(this.f7037d).mo33927a(this.f7034a, null, this.f7036c, false);
                    if (this.f7038e - StatServiceImpl.f6919b > 1800000) {
                        StatServiceImpl.m7952d(this.f7037d);
                        return;
                    }
                    return;
                }
                m8016b();
                return;
            }
            C4411au.m8029a(this.f7037d).mo33927a(this.f7034a, null, this.f7036c, false);
        }
    }
}
