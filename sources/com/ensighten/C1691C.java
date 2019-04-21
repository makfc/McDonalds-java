package com.ensighten;

import android.view.MotionEvent;
import com.ensighten.model.EnsightenGestureRecognizer;
import com.ensighten.model.EnsightenGestureRecognizerCompletionHandler;
import java.util.Timer;
import java.util.TimerTask;

/* renamed from: com.ensighten.C */
public final class C1691C implements EnsightenGestureRecognizer {
    /* renamed from: a */
    private long f5593a;
    /* renamed from: b */
    private int f5594b;
    /* renamed from: c */
    private EnsightenGestureRecognizerCompletionHandler f5595c;
    /* renamed from: d */
    private Timer f5596d;
    /* renamed from: e */
    private C1689b f5597e;

    /* renamed from: com.ensighten.C$1 */
    class C16871 implements EnsightenGestureRecognizerCompletionHandler {
        C16871() {
        }

        public final void doAction() {
            if (C1845i.m7358e()) {
                C1845i.m7345a("Running complete action for the four-finger press action.");
            }
            Ensighten.evaluateJS("Bootstrapper.insertScript('https://nexus.ensighten.com/mobile2/device.js')");
        }
    }

    /* renamed from: com.ensighten.C$a */
    enum C1688a {
        ;

        static {
            f5587a = 1;
            f5588b = 2;
            f5589c = 3;
            f5590d = new int[]{1, 2, 3};
        }
    }

    /* renamed from: com.ensighten.C$b */
    class C1689b extends TimerTask {
        private C1689b() {
        }

        /* synthetic */ C1689b(C1691C c1691c, byte b) {
            this();
        }

        public final void run() {
            if (C1691C.this.f5594b != C1688a.f5589c) {
                C1691C.this.f5594b = C1688a.f5589c;
                C1691C.this.f5595c.doAction();
            }
        }
    }

    /* renamed from: com.ensighten.C$c */
    static class C1690c {
        /* renamed from: a */
        public static final C1691C f5592a = new C1691C();
    }

    /* synthetic */ C1691C(byte b) {
        this();
    }

    /* renamed from: a */
    public static C1691C m7176a() {
        return C1690c.f5592a;
    }

    private C1691C() {
        this.f5593a = 0;
        this.f5594b = C1688a.f5587a;
        this.f5596d = new Timer();
        this.f5595c = new C16871();
    }

    public final void process(MotionEvent event) {
        if (this.f5594b == C1688a.f5589c) {
            if (C1845i.m7358e()) {
                C1845i.m7345a("The four-finger press action was already recognized.");
            }
        } else if (this.f5594b == C1688a.f5587a && event.getActionMasked() == 5 && event.getPointerCount() == 4) {
            this.f5593a = System.currentTimeMillis();
            this.f5594b = C1688a.f5588b;
            if (this.f5597e != null) {
                this.f5597e.cancel();
            }
            this.f5597e = new C1689b(this, (byte) 0);
            this.f5596d.schedule(this.f5597e, 4000);
            if (C1845i.m7358e()) {
                C1845i.m7345a(String.format("The four-finger press action has started at %d.", new Object[]{Long.valueOf(this.f5593a)}));
            }
        } else if (this.f5594b == C1688a.f5588b && event.getActionMasked() == 6 && event.getPointerCount() == 4) {
            if (!m7178b()) {
                if (C1845i.m7358e()) {
                    C1845i.m7345a("The four-finger press action was interrupted.");
                }
                reset();
            }
        } else if (this.f5594b == C1688a.f5588b && m7178b()) {
            m7179c();
            this.f5594b = C1688a.f5589c;
            this.f5595c.doAction();
        }
    }

    /* renamed from: b */
    private boolean m7178b() {
        long currentTimeMillis = System.currentTimeMillis() - this.f5593a;
        if (C1845i.m7358e()) {
            C1845i.m7345a(String.format("The four-finger press action duration is %d.", new Object[]{Long.valueOf(currentTimeMillis)}));
        }
        if (currentTimeMillis < 4000) {
            return false;
        }
        if (!C1845i.m7358e()) {
            return true;
        }
        C1845i.m7345a("The four-finger press action was recognized.");
        return true;
    }

    public final void reset() {
        this.f5594b = C1688a.f5587a;
        this.f5593a = 0;
        m7179c();
    }

    /* renamed from: c */
    private void m7179c() {
        this.f5597e.cancel();
    }
}
