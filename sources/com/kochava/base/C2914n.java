package com.kochava.base;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.amap.api.location.LocationManagerProxy;
import com.kochava.base.Tracker.ConsentPartner;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.kochava.base.n */
final class C2914n extends C2907i {
    @Nullable
    /* renamed from: b */
    private JSONObject f6652b = null;

    /* renamed from: com.kochava.base.n$1 */
    class C29131 implements Runnable {
        C29131() {
        }

        public void run() {
            try {
                if (C2914n.this.f6629a.f6614g != null) {
                    C2914n.this.f6629a.f6614g.onConsentStatusChange();
                }
            } catch (Throwable th) {
                Tracker.m7517a(2, "TIN", "run", "Exception in Host App", th);
            }
        }
    }

    @AnyThread
    C2914n(@NonNull C2906h c2906h) {
        super(c2906h, false);
    }

    @VisibleForTesting
    /* renamed from: a */
    static void m7729a(@NonNull C2906h c2906h, @NonNull JSONObject jSONObject) {
        c2906h.f6611d.mo26569b("blacklist");
        c2906h.f6611d.mo26569b("whitelist");
        c2906h.f6611d.mo26569b("eventname_blacklist");
        c2906h.f6611d.mo26565a("session_tracking", Boolean.valueOf(true));
        c2906h.f6611d.mo26565a("session_minimum", Integer.valueOf(45));
        c2906h.f6611d.mo26565a("session_window", Integer.valueOf(600));
        c2906h.f6611d.mo26565a("send_updates", Boolean.valueOf(true));
        c2906h.f6611d.mo26566a("kvinit_wait", Integer.valueOf(60), true);
        c2906h.f6611d.mo26566a("kvinit_staleness", Integer.valueOf(86400), true);
        c2906h.f6611d.mo26565a("initial_wait", Integer.valueOf(0));
        c2906h.f6611d.mo26565a("kvtracker_wait", Integer.valueOf(5));
        c2906h.f6611d.mo26565a("getattribution_wait", Integer.valueOf(7));
        c2906h.f6611d.mo26565a("attribution_staleness", Integer.valueOf(-1));
        c2906h.f6611d.mo26565a("batch_max_quantity", Integer.valueOf(25));
        c2906h.f6611d.mo26565a("push", Boolean.valueOf(false));
        c2906h.f6611d.mo26565a("dp_options", new JSONObject());
        C2914n.m7732c(c2906h, new JSONObject());
        Object g = C2901d.m7662g(jSONObject.opt("blacklist"));
        if (g != null) {
            if (C2901d.m7645a((JSONArray) g, "email")) {
                g.put("ids");
            }
            c2906h.f6611d.mo26565a("blacklist", g);
        } else {
            c2906h.f6611d.mo26565a("blacklist", new JSONArray());
        }
        g = C2901d.m7662g(jSONObject.opt("whitelist"));
        if (g != null) {
            if (C2901d.m7645a((JSONArray) g, "email")) {
                g.put("ids");
            }
            c2906h.f6611d.mo26565a("whitelist", g);
        } else {
            c2906h.f6611d.mo26565a("whitelist", new JSONArray());
        }
        g = C2901d.m7662g(jSONObject.opt("eventname_blacklist"));
        if (g != null) {
            c2906h.f6611d.mo26565a("eventname_blacklist", g);
        } else {
            c2906h.f6611d.mo26565a("eventname_blacklist", new JSONArray());
        }
        JSONObject f = C2901d.m7661f(jSONObject.opt("flags"));
        if (f != null) {
            g = C2901d.m7628a(f.opt("kochava_app_id"));
            if (!(g == null || g.isEmpty())) {
                c2906h.f6611d.mo26565a("kochava_app_id_override", g);
            }
            g = C2901d.m7628a(f.opt("kochava_device_id"));
            if (!(g == null || g.isEmpty())) {
                c2906h.f6611d.mo26565a("kochava_device_id", g);
            }
            if (C2901d.m7644a(f.opt("resend_initial"), false)) {
                c2906h.f6611d.mo26565a("initial_needs_sent", Boolean.valueOf(true));
            }
            if (C2901d.m7644a(f.opt("resend_push"), false)) {
                c2906h.f6611d.mo26565a("push_token_sent", Boolean.valueOf(false));
            }
            boolean a = C2901d.m7644a(f.opt("session_tracking"), true);
            int i = !"NONE".equalsIgnoreCase(C2901d.m7628a(f.opt("session_tracking"))) ? 1 : 0;
            C2901d c2901d = c2906h.f6611d;
            String str = "session_tracking";
            boolean z = a && i != 0;
            c2901d.mo26565a(str, Boolean.valueOf(z));
            c2906h.f6611d.mo26565a("push", Boolean.valueOf(C2901d.m7644a(f.opt("push"), false)));
            c2906h.f6611d.mo26565a("send_updates", Boolean.valueOf(C2901d.m7644a(f.opt("send_updates"), true)));
            c2906h.f6611d.mo26565a("session_minimum", Integer.valueOf(C2901d.m7624a(C2901d.m7647b(f.opt("session_minimum"), 45), 0, Integer.MAX_VALUE)));
            c2906h.f6611d.mo26565a("session_window", Integer.valueOf(C2901d.m7624a(C2901d.m7647b(f.opt("session_window"), 600), 0, Integer.MAX_VALUE)));
            i = C2901d.m7624a(C2901d.m7647b(f.opt("kvinit_wait"), 60), 0, Integer.MAX_VALUE);
            c2906h.f6611d.mo26566a("kvinit_wait", Integer.valueOf(i), true);
            c2906h.f6611d.mo26566a("kvinit_staleness", Integer.valueOf(C2901d.m7624a(C2901d.m7647b(f.opt("kvinit_staleness"), 86400), i, Integer.MAX_VALUE)), true);
            c2906h.f6611d.mo26565a("initial_wait", Integer.valueOf(C2901d.m7624a(C2901d.m7647b(f.opt("initial_wait"), 0), 0, Integer.MAX_VALUE)));
            c2906h.f6611d.mo26565a("kvtracker_wait", Integer.valueOf(C2901d.m7624a(C2901d.m7647b(f.opt("kvtracker_wait"), 5), 0, Integer.MAX_VALUE)));
            c2906h.f6611d.mo26565a("getattribution_wait", Integer.valueOf(C2901d.m7624a(C2901d.m7647b(f.opt("getattribution_wait"), 7), 0, Integer.MAX_VALUE)));
            c2906h.f6611d.mo26565a("attribution_staleness", Integer.valueOf(C2901d.m7624a(C2901d.m7647b(f.opt("attribution_staleness"), -1), -1, Integer.MAX_VALUE)));
            c2906h.f6611d.mo26565a("batch_max_quantity", Integer.valueOf(C2901d.m7624a(C2901d.m7647b(f.opt("batch_max_quantity"), 25), 1, Integer.MAX_VALUE)));
            C2914n.m7732c(c2906h, f);
        }
        C2914n.m7730b(jSONObject);
    }

    /* renamed from: b */
    private static void m7730b(@NonNull JSONObject jSONObject) {
        JSONArray g = C2901d.m7662g(jSONObject.opt("log_messages"));
        if (g != null) {
            for (int i = 0; i < g.length(); i++) {
                JSONObject f = C2901d.m7661f(g.opt(i));
                if (f != null) {
                    String a = C2901d.m7628a(f.opt("text"));
                    int a2 = C2901d.m7625a(C2901d.m7628a(f.opt("level")), 0);
                    if (!(a2 == 0 || a == null || a.isEmpty())) {
                        Tracker.m7517a(a2, "TIN", "decodeLogMess", a);
                    }
                }
            }
        }
    }

    /* renamed from: b */
    private static boolean m7731b(@NonNull C2906h c2906h, @NonNull JSONObject jSONObject) {
        int a = (int) (C2901d.m7626a() / 1000);
        int b = C2901d.m7647b(c2906h.f6611d.mo26572c("consent_last_prompt"), 0);
        Object b2 = C2901d.m7649b(c2906h.f6611d.mo26572c("consent"), true);
        JSONArray c = C2901d.m7657c(b2.opt(ConsentPartner.KEY_PARTNERS), true);
        boolean a2 = C2901d.m7644a(b2.opt("required"), true);
        boolean a3 = C2901d.m7644a(b2.opt(ConsentPartner.KEY_GRANTED), false);
        long a4 = C2901d.m7627a(b2.opt(ConsentPartner.KEY_RESPONSE_TIME), 0);
        boolean a5 = C2901d.m7644a(b2.opt("should_prompt"), false);
        Object a6 = C2901d.m7629a(b2.opt("prompt_id"), "");
        Object obj = b2.length() != 0 ? 1 : null;
        Object b3 = C2901d.m7649b(jSONObject.opt("consent"), true);
        boolean a7 = C2901d.m7644a(b3.opt("required"), true);
        JSONArray c2 = C2901d.m7657c(b3.opt(ConsentPartner.KEY_PARTNERS), true);
        if (b3.length() == 0) {
            Tracker.m7517a(2, "TIN", "decodeConsent", "Consent information not returned by server. Ensure it is enabled on the Kochava dashboard.");
            return false;
        } else if (a7) {
            C2901d.m7636a(ConsentPartner.KEY_GRANTED, Boolean.valueOf(a3), (JSONObject) b3);
            C2901d.m7636a(ConsentPartner.KEY_RESPONSE_TIME, Long.valueOf(a4), (JSONObject) b3);
            C2901d.m7636a("should_prompt", Boolean.valueOf(a5), (JSONObject) b3);
            int c3 = C2901d.m7655c(c, c2);
            a6 = !C2901d.m7643a(C2901d.m7629a(b3.opt("prompt_id"), ""), a6) ? 1 : null;
            Object obj2 = c3 == 2 ? 1 : null;
            if (!(a6 == null && obj2 == null) && a3) {
                C2901d.m7636a(ConsentPartner.KEY_GRANTED, Boolean.valueOf(false), (JSONObject) b3);
                C2901d.m7636a(ConsentPartner.KEY_RESPONSE_TIME, Long.valueOf(0), (JSONObject) b3);
                c2906h.f6611d.mo26575c(false);
                c2906h.f6611d.mo26567a(true);
            }
            b2 = (b == 0 || a - b <= C2901d.m7647b(b3.opt("prompt_retry_interval"), 2592000)) ? null : 1;
            boolean a8 = C2901d.m7644a(b3.opt(ConsentPartner.KEY_GRANTED), false);
            b2 = (a8 || !(obj == null || a3 || b2 != null || b == 0)) ? null : 1;
            boolean z = b2 != null || (!a8 && a5);
            C2901d.m7636a("should_prompt", Boolean.valueOf(z), (JSONObject) b3);
            if (b2 != null) {
                c2906h.f6611d.mo26566a("consent_last_prompt", Integer.valueOf(a), true);
            }
            c2906h.f6611d.mo26566a("consent", b3, true);
            return (((obj2 == null && a6 == null) || !a3) && a2 && b2 == null) ? false : true;
        } else {
            C2901d.m7636a("required", Boolean.valueOf(false), (JSONObject) b2);
            C2901d.m7636a("should_prompt", Boolean.valueOf(false), (JSONObject) b2);
            c2906h.f6611d.mo26566a("consent", b2, true);
            c2906h.f6611d.mo26575c(true);
            return a7 != a2;
        }
    }

    /* renamed from: c */
    private static void m7732c(@NonNull C2906h c2906h, @NonNull JSONObject jSONObject) {
        Object jSONObject2 = new JSONObject();
        Object jSONObject3 = new JSONObject();
        C2901d.m7636a("accuracy", Integer.valueOf(C2901d.m7624a(C2901d.m7647b(jSONObject.opt("location_accuracy"), 50), 0, Integer.MAX_VALUE)), (JSONObject) jSONObject3);
        C2901d.m7636a("timeout", Integer.valueOf(C2901d.m7624a(C2901d.m7647b(jSONObject.opt("location_timeout"), 10), 1, Integer.MAX_VALUE)), (JSONObject) jSONObject3);
        C2901d.m7636a("staleness", Integer.valueOf(C2901d.m7624a(C2901d.m7647b(jSONObject.opt("location_staleness"), 90), 0, Integer.MAX_VALUE)), (JSONObject) jSONObject3);
        C2901d.m7636a("mode", C2901d.m7629a(jSONObject.opt("location_mode"), "auto"), (JSONObject) jSONObject3);
        C2901d.m7636a(LocationManagerProxy.KEY_LOCATION_CHANGED, jSONObject3, (JSONObject) jSONObject2);
        Object jSONObject4 = new JSONObject();
        C2901d.m7636a("install_referrer_attempts", Integer.valueOf(C2901d.m7624a(C2901d.m7647b(jSONObject.opt("install_referrer_attempts"), 2), 1, Integer.MAX_VALUE)), (JSONObject) jSONObject4);
        C2901d.m7636a("install_referrer_wait", Integer.valueOf(C2901d.m7624a(C2901d.m7647b(jSONObject.opt("install_referrer_wait"), 10), 1, Integer.MAX_VALUE)), (JSONObject) jSONObject4);
        C2901d.m7636a("install_referrer_retry_wait", Double.valueOf(C2901d.m7622a(C2901d.m7623a(jSONObject.opt("install_referrer_retry_wait"), 1.0d), 0.0d, Double.MAX_VALUE)), (JSONObject) jSONObject4);
        C2901d.m7636a("install_referrer", jSONObject4, (JSONObject) jSONObject2);
        c2906h.f6611d.mo26565a("dp_options", jSONObject2);
    }

    public final void run() {
        String str = "run";
        Tracker.m7517a(4, "TIN", "run", new Object[0]);
        int b = C2901d.m7647b(this.f6629a.f6611d.mo26572c("init_last_sent"), 0);
        Tracker.m7517a(5, "TIN", "run", "LastSent: " + b, "InitWait: " + C2901d.m7647b(this.f6629a.f6611d.mo26572c("kvinit_wait"), 60));
        if (((long) (C2901d.m7647b(this.f6629a.f6611d.mo26572c("kvinit_wait"), 60) + b)) >= C2901d.m7626a() / 1000) {
            Tracker.m7517a(4, "TIN", "run", "Skip");
            mo26614d();
            mo26621k();
            return;
        }
        if (this.f6652b == null) {
            Tracker.m7517a(5, "TIN", "run", "Gather");
            this.f6652b = new JSONObject();
            mo26607a(0, this.f6652b, new JSONObject());
        }
        Tracker.m7517a(5, "TIN", "run", "Send");
        JSONObject a = mo26605a(0, (Object) this.f6652b);
        if (!mo26609a(a, b == 0)) {
            Tracker.m7517a(5, "TIN", "run", a);
            if (!C2901d.m7643a(C2901d.m7628a(this.f6652b.opt("nt_id")), C2901d.m7628a(a.opt("nt_id")))) {
                Tracker.m7517a(4, "TIN", "run", "nt_id mismatch");
            }
            C2914n.m7729a(this.f6629a, a);
            int i = (this.f6629a.f6622o && C2914n.m7731b(this.f6629a, a)) ? 1 : 0;
            this.f6629a.f6611d.mo26566a("init_last_sent", Integer.valueOf((int) (C2901d.m7626a() / 1000)), true);
            this.f6652b = null;
            mo26614d();
            Tracker.m7517a(3, "TIN", "init", "Complete");
            Tracker.m7517a(4, "TIN", "run", "Complete");
            mo26621k();
            if (i != 0) {
                new Handler(Looper.getMainLooper()).post(new C29131());
            }
        } else if (b != 0) {
            Tracker.m7517a(5, "TIN", "run", "Failed. Skip");
            mo26614d();
            mo26621k();
        } else {
            Tracker.m7517a(5, "TIN", "run", "Retry");
        }
    }
}
