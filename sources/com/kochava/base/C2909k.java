package com.kochava.base;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.kochava.base.k */
final class C2909k extends C2907i {
    /* renamed from: b */
    private final int f6640b;
    @Nullable
    /* renamed from: c */
    private final String f6641c;
    @Nullable
    /* renamed from: d */
    private final String f6642d;
    @Nullable
    /* renamed from: e */
    private final String f6643e;
    @Nullable
    /* renamed from: f */
    private final String f6644f;
    @Nullable
    /* renamed from: g */
    private final String f6645g;

    C2909k(@NonNull C2906h c2906h, int i, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        super(c2906h, true);
        this.f6640b = i;
        this.f6641c = str;
        this.f6642d = str2;
        this.f6643e = str3;
        this.f6644f = str4;
        this.f6645g = str5;
    }

    @CheckResult
    /* renamed from: b */
    private boolean m7727b(@NonNull JSONObject jSONObject) {
        String str = "processSessio";
        if (this.f6640b != 2 && this.f6640b != 3) {
            return true;
        }
        int b = C2901d.m7647b(this.f6629a.f6611d.mo26572c("session_minimum"), 45);
        int b2 = C2901d.m7647b(this.f6629a.f6611d.mo26572c("session_window"), 600);
        if (b2 == 0 || b == 0) {
            return true;
        }
        int c = (int) (mo26613c() / 1000);
        int b3 = C2901d.m7647b(this.f6629a.f6611d.mo26572c("session_resume_time"), 0);
        boolean a = C2901d.m7644a(this.f6629a.f6611d.mo26572c("session_pause_sent_this_window"), false);
        boolean a2 = C2901d.m7644a(this.f6629a.f6611d.mo26572c("session_pause_ever_sent"), false);
        if (this.f6640b == 2) {
            this.f6629a.f6611d.mo26565a("session_state_active_count", Integer.valueOf(C2901d.m7647b(this.f6629a.f6611d.mo26572c("session_state_active_count"), 0) + 1));
            if (c < b3 + b2) {
                return false;
            }
            Tracker.m7517a(4, "TBE", "processSessio", "Resume: Sufficient Time");
            JSONObject f = C2901d.m7661f(this.f6629a.f6611d.mo26572c("session_pause"));
            if (f != null) {
                Tracker.m7517a(4, "TBE", "processSessio", "Resume: Queuing Cached Pause");
                this.f6629a.f6611d.mo26570b(f);
                this.f6629a.f6611d.mo26569b("session_pause");
                this.f6629a.f6611d.mo26565a("session_pause_ever_sent", Boolean.valueOf(true));
                this.f6629a.f6611d.mo26565a("session_state_active_count", Integer.valueOf(1));
            }
            this.f6629a.f6611d.mo26565a("session_resume_time", Integer.valueOf(c));
            this.f6629a.f6611d.mo26565a("session_window_uptime", Integer.valueOf(0));
            this.f6629a.f6611d.mo26565a("session_pause_sent_this_window", Boolean.valueOf(false));
        } else {
            int b4 = (int) (((long) C2901d.m7647b(this.f6629a.f6611d.mo26572c("session_window_uptime"), 0)) + ((mo26613c() - mo26604a()) / 1000));
            this.f6629a.f6611d.mo26565a("session_window_uptime", Integer.valueOf(b4));
            if (!a2 || (!a && (b4 >= b || c >= b3 + b2))) {
                Tracker.m7517a(4, "TBE", "processSessio", "Pause: Sending");
                this.f6629a.f6611d.mo26569b("session_pause");
                this.f6629a.f6611d.mo26565a("session_pause_ever_sent", Boolean.valueOf(true));
                this.f6629a.f6611d.mo26565a("session_pause_sent_this_window", Boolean.valueOf(true));
                this.f6629a.f6611d.mo26565a("session_state_active_count", Integer.valueOf(0));
            } else {
                if (a) {
                    Tracker.m7517a(4, "TBE", "processSessio", "Pause: Not Updating");
                } else {
                    Tracker.m7517a(4, "TBE", "processSessio", "Pause: Updating");
                    this.f6629a.f6611d.mo26565a("session_pause", (Object) jSONObject);
                }
                return false;
            }
        }
        return true;
    }

    public final void run() {
        String str = "run";
        Tracker.m7517a(4, "TBE", "run", new Object[0]);
        if (this.f6629a.f6611d.mo26577e() >= 1000) {
            Tracker.m7517a(2, "TBE", "run", "Database Full. Dropping: " + this.f6641c);
            return;
        }
        JSONArray g = C2901d.m7662g(this.f6629a.f6611d.mo26572c("eventname_blacklist"));
        if (g == null || this.f6641c == null || !C2901d.m7645a(g, this.f6641c)) {
            Object f;
            JSONObject jSONObject = new JSONObject();
            if (this.f6641c != null) {
                C2901d.m7636a("event_name", this.f6641c, jSONObject);
            }
            if (this.f6642d != null) {
                f = C2901d.m7661f(this.f6642d);
                if (f != null) {
                    if ("Consent Granted".equals(this.f6641c) && this.f6629a.f6622o) {
                        if (f.opt("content_id") == null) {
                            C2901d.m7636a("content_id", this.f6629a.f6616i.mo26532g(), (JSONObject) f);
                        }
                        if (this.f6629a.f6616i.mo26530e() && f.opt("date") == null) {
                            C2901d.m7636a("date", Long.toString(this.f6629a.f6616i.mo26531f()), (JSONObject) f);
                        }
                    }
                    C2901d.m7636a("event_data", f, jSONObject);
                } else {
                    C2901d.m7636a("event_data", this.f6642d, jSONObject);
                }
            }
            if (!(this.f6643e == null || this.f6644f == null)) {
                f = new JSONObject();
                C2901d.m7636a("purchaseData", this.f6643e, (JSONObject) f);
                C2901d.m7636a("dataSignature", this.f6644f, (JSONObject) f);
                C2901d.m7636a("receipt", f, jSONObject);
            }
            if (this.f6645g != null) {
                C2901d.m7636a("uri", this.f6645g, jSONObject);
            }
            JSONObject jSONObject2 = new JSONObject();
            mo26607a(this.f6640b, jSONObject2, jSONObject);
            boolean b = m7727b(jSONObject2);
            if (b) {
                this.f6629a.f6611d.mo26570b(jSONObject2);
            } else {
                Tracker.m7517a(4, "TBE", "run", "Not sending deferred/dropped event.");
            }
            if (b || this.f6640b == 3) {
                b = this.f6629a.f6611d.mo26577e() >= C2901d.m7647b(this.f6629a.f6611d.mo26572c("batch_max_quantity"), 25) || this.f6640b == 3;
                mo26608a(b);
            }
            Tracker.m7517a(4, "TBE", "run", "Complete");
            return;
        }
        Tracker.m7517a(3, "TBE", "run", this.f6641c + " blacklisted");
    }
}
