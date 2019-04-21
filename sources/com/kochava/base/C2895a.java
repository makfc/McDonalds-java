package com.kochava.base;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import com.facebook.internal.ServerProtocol;
import com.kochava.base.Tracker.ConsentPartner;
import com.kochava.base.Tracker.IdentityLink;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.Contract;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.kochava.base.a */
final class C2895a implements C2893b, C2894f, Runnable {
    @VisibleForTesting
    @NonNull
    /* renamed from: a */
    final C2906h f6562a;
    @VisibleForTesting
    @NonNull
    /* renamed from: b */
    final C2914n f6563b;
    @VisibleForTesting
    @NonNull
    /* renamed from: c */
    final C2918s f6564c;
    @VisibleForTesting
    @NonNull
    /* renamed from: d */
    final C2915o f6565d;
    @VisibleForTesting
    @NonNull
    /* renamed from: e */
    final C2911l f6566e;
    @VisibleForTesting
    @NonNull
    /* renamed from: f */
    final C2917q f6567f;
    @VisibleForTesting
    @NonNull
    /* renamed from: g */
    final C2892c f6568g;
    @Nullable
    /* renamed from: h */
    private final C2904g f6569h;
    @NonNull
    /* renamed from: i */
    private final C2890a f6570i;
    @Nullable
    /* renamed from: j */
    private C2902e f6571j = null;

    /* renamed from: com.kochava.base.a$a */
    private static class C2890a {
        @NonNull
        /* renamed from: a */
        final String f6552a;
        @Nullable
        /* renamed from: b */
        final String f6553b;
        @Nullable
        /* renamed from: c */
        final String f6554c;
        @Nullable
        /* renamed from: d */
        final JSONObject f6555d;
        @Nullable
        /* renamed from: e */
        final String f6556e;

        C2890a(@NonNull String str, @Nullable String str2, @Nullable String str3, @Nullable JSONObject jSONObject, @Nullable String str4) {
            this.f6552a = str;
            this.f6553b = str2;
            this.f6554c = str3;
            this.f6555d = jSONObject;
            this.f6556e = str4;
        }

        /* Access modifiers changed, original: final */
        /* renamed from: a */
        public final void mo26525a(@NonNull C2906h c2906h) {
            c2906h.f6611d.mo26565a("sdk_version", this.f6552a);
            if (this.f6553b != null) {
                c2906h.f6611d.mo26565a("kochava_app_id", this.f6553b);
            } else {
                c2906h.f6611d.mo26569b("kochava_app_id");
            }
            if (this.f6554c != null) {
                c2906h.f6611d.mo26565a("partner_name", this.f6554c);
            } else {
                c2906h.f6611d.mo26569b("partner_name");
            }
            if (this.f6555d != null) {
                c2906h.f6611d.mo26565a("custom", this.f6555d);
            } else {
                c2906h.f6611d.mo26569b("custom");
            }
            if (this.f6556e != null) {
                c2906h.f6611d.mo26565a("ext_date", this.f6556e);
            } else {
                c2906h.f6611d.mo26569b("ext_date");
            }
            if (C2901d.m7628a(c2906h.f6611d.mo26572c("kochava_device_id")) == null) {
                String replace = "3.4.0".replace(".", "");
                String format = new SimpleDateFormat("yyyyMMdd", Locale.US).format(new Date(C2901d.m7626a()));
                c2906h.f6611d.mo26565a("kochava_device_id", "KA" + replace + format + UUID.randomUUID().toString().replaceAll("-", ""));
            }
        }
    }

    /* renamed from: com.kochava.base.a$b */
    private static class C2891b {
        /* renamed from: a */
        final String f6557a;
        /* renamed from: b */
        final Bundle f6558b;

        C2891b(@NonNull String str, @NonNull Bundle bundle) {
            this.f6557a = str;
            this.f6558b = bundle;
        }
    }

    /* renamed from: com.kochava.base.a$c */
    static class C2892c implements Runnable {
        @VisibleForTesting
        @NonNull
        /* renamed from: a */
        final BlockingQueue<C2891b> f6559a = new ArrayBlockingQueue(100);
        @NonNull
        /* renamed from: b */
        private final C2906h f6560b;
        @NonNull
        /* renamed from: c */
        private final C2893b f6561c;

        C2892c(@NonNull C2906h c2906h, @NonNull C2893b c2893b) {
            this.f6560b = c2906h;
            this.f6561c = c2893b;
        }

        /* Access modifiers changed, original: final */
        /* renamed from: a */
        public final void mo26526a() {
            Tracker.m7517a(5, "QUP", "queueProcess", "start");
            this.f6560b.mo26598a(this);
        }

        /* Access modifiers changed, original: final */
        /* renamed from: a */
        public final void mo26527a(@NonNull C2891b c2891b) {
            Tracker.m7517a(5, "QUP", "queueProcess", "add");
            this.f6559a.offer(c2891b);
            mo26526a();
        }

        public final void run() {
            Tracker.m7517a(5, "QUP", "queueProcess", "run");
            while (!this.f6559a.isEmpty()) {
                if (!this.f6560b.f6622o || !this.f6561c.mo26529d() || this.f6561c.mo26531f() != 0) {
                    try {
                        C2891b c2891b = (C2891b) this.f6559a.poll();
                        if (c2891b != null) {
                            if (!this.f6560b.f6622o || !this.f6561c.mo26529d() || this.f6561c.mo26530e()) {
                                String str = c2891b.f6557a;
                                int i = -1;
                                switch (str.hashCode()) {
                                    case -1878682790:
                                        if (str.equals("setIdentityLink")) {
                                            i = 2;
                                            break;
                                        }
                                        break;
                                    case -548851274:
                                        if (str.equals("setAppLimitAdTracking")) {
                                            i = 1;
                                            break;
                                        }
                                        break;
                                    case 2762738:
                                        if (str.equals("sendEvent")) {
                                            i = 3;
                                            break;
                                        }
                                        break;
                                    case 760458429:
                                        if (str.equals("setPushToken")) {
                                            i = 0;
                                            break;
                                        }
                                        break;
                                }
                                switch (i) {
                                    case 0:
                                        new C2916p(this.f6560b, c2891b.f6558b.getString(ServerProtocol.DIALOG_RESPONSE_TYPE_TOKEN, ""), c2891b.f6558b.getBoolean("enable"), false).run();
                                        break;
                                    case 1:
                                        new C2908j(this.f6560b, c2891b.f6558b.getBoolean("appLimitAdTracking")).run();
                                        break;
                                    case 2:
                                        new C2912m(this.f6560b, C2901d.m7649b(c2891b.f6558b.getString("identityLink"), true)).run();
                                        break;
                                    case 3:
                                        new C2909k(this.f6560b, c2891b.f6558b.getInt("payload"), c2891b.f6558b.getString("eventName"), c2891b.f6558b.getString("eventData"), c2891b.f6558b.getString("receiptJson"), c2891b.f6558b.getString("receiptSignature"), c2891b.f6558b.getString("uri")).run();
                                        break;
                                    default:
                                        break;
                                }
                            }
                            Tracker.m7517a(3, "QUP", "queueProcess", "Consent denied. Dropping Incoming Action: " + c2891b.f6557a);
                        }
                    } catch (Throwable th) {
                        Tracker.m7517a(2, "QUP", "queue", th);
                    }
                } else {
                    return;
                }
            }
        }
    }

    @AnyThread
    C2895a(@NonNull Context context, @NonNull String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable AttributionListener attributionListener, @Nullable AttributionUpdateListener attributionUpdateListener, @Nullable ConsentStatusChangeListener consentStatusChangeListener, @NonNull JSONObject jSONObject, @Nullable JSONObject jSONObject2, boolean z) {
        String str5 = "Controller";
        String str6 = "CTR";
        String str7 = "Controller";
        Object[] objArr = new Object[6];
        objArr[0] = "version: " + str;
        objArr[1] = "extensionDate: " + str2;
        objArr[2] = "appGuid: " + str3;
        objArr[3] = "partnerName: " + str4;
        objArr[4] = "attributionListener: " + (attributionListener != null);
        objArr[5] = "custom: " + jSONObject2;
        Tracker.m7517a(4, str6, str7, objArr);
        this.f6570i = new C2890a(str, str3, str4, jSONObject2, str2);
        this.f6562a = new C2906h(context, this, this, jSONObject, attributionListener, attributionUpdateListener, consentStatusChangeListener, z);
        this.f6568g = new C2892c(this.f6562a, this);
        this.f6563b = new C2914n(this.f6562a);
        this.f6564c = new C2918s(this.f6562a);
        this.f6565d = new C2915o(this.f6562a);
        this.f6566e = new C2911l(this.f6562a);
        this.f6567f = new C2917q(this.f6562a);
        this.f6562a.f6611d.mo26565a("sdk_version", (Object) str);
        this.f6570i.mo26525a(this.f6562a);
        this.f6569h = new C2904g(this.f6562a.f6608a, this.f6562a.f6617j, this);
        if (!(this.f6562a.f6622o && mo26529d() && !mo26530e())) {
            this.f6562a.f6611d.mo26575c(true);
        }
        this.f6562a.mo26599a(this.f6562a.f6615h, 50);
    }

    /* Access modifiers changed, original: final */
    @AnyThread
    @NonNull
    /* renamed from: a */
    public final String mo26536a(@NonNull String str) {
        return C2901d.m7629a(this.f6562a.f6611d.mo26572c(str), "");
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo26537a() {
        if (this.f6562a != null) {
            if (this.f6562a.f6617j != null) {
                this.f6562a.f6617j.removeCallbacksAndMessages(null);
            }
            if (this.f6562a.f6618k != null) {
                this.f6562a.f6618k.removeCallbacksAndMessages(null);
            }
            if (this.f6562a.f6619l != null) {
                this.f6562a.f6619l.quit();
            }
            if (this.f6562a.f6620m != null) {
                this.f6562a.f6620m.quit();
            }
            if (this.f6562a.f6611d != null) {
                this.f6562a.f6611d.mo26568b();
            }
            if (this.f6569h != null && this.f6562a.f6608a != null) {
                ((Application) this.f6562a.f6608a).unregisterActivityLifecycleCallbacks(this.f6569h);
                this.f6562a.f6608a.unregisterComponentCallbacks(this.f6569h);
            }
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo26538a(int i) {
        if (this.f6562a.f6625r == i) {
            Tracker.m7517a(4, "CTR", "setOperatingM", "Rejecting same as current");
            return;
        }
        Tracker.m7517a(3, "CTR", "setOperatingM", Integer.valueOf(this.f6562a.f6625r));
        this.f6562a.f6625r = i;
        if (this.f6562a.f6625r == 0) {
            this.f6562a.mo26600a(this.f6562a.f6615h, true);
        }
    }

    /* Access modifiers changed, original: final */
    @AnyThread
    /* renamed from: a */
    public final void mo26539a(int i, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        Tracker.m7517a(5, "CTR", "sendEvent", new Object[0]);
        Bundle bundle = new Bundle();
        bundle.putInt("payload", i);
        bundle.putString("eventName", str);
        bundle.putString("eventData", str2);
        bundle.putString("receiptJson", str3);
        bundle.putString("receiptSignature", str4);
        bundle.putString("uri", str5);
        this.f6568g.mo26527a(new C2891b("sendEvent", bundle));
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo26540a(@Nullable Uri uri, int i, @NonNull DeepLinkListener deepLinkListener) {
        if (this.f6571j != null) {
            this.f6571j.mo26583a();
            this.f6571j = null;
        }
        this.f6571j = new C2902e(uri, i, this.f6562a, deepLinkListener);
    }

    /* Access modifiers changed, original: final */
    @AnyThread
    /* renamed from: a */
    public final void mo26541a(@NonNull IdentityLink identityLink) {
        Bundle bundle = new Bundle();
        bundle.putString("identityLink", C2901d.m7632a(identityLink.f6544a));
        this.f6568g.mo26527a(new C2891b("setIdentityLink", bundle));
    }

    /* Access modifiers changed, original: final */
    @AnyThread
    /* renamed from: a */
    public final void mo26542a(@NonNull String str, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString(ServerProtocol.DIALOG_RESPONSE_TYPE_TOKEN, str);
        bundle.putBoolean("enable", z);
        this.f6568g.mo26527a(new C2891b("setPushToken", bundle));
    }

    /* Access modifiers changed, original: final */
    @AnyThread
    /* renamed from: a */
    public final void mo26543a(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("appLimitAdTracking", z);
        this.f6568g.mo26527a(new C2891b("setAppLimitAdTracking", bundle));
    }

    /* Access modifiers changed, original: final */
    @Contract(pure = true)
    /* renamed from: b */
    public final int mo26544b() {
        return this.f6562a.f6625r;
    }

    /* Access modifiers changed, original: final */
    @AnyThread
    @NonNull
    /* renamed from: b */
    public final JSONObject mo26545b(@NonNull String str) {
        return C2901d.m7649b(this.f6562a.f6611d.mo26572c(str), true);
    }

    /* Access modifiers changed, original: final */
    /* renamed from: b */
    public final void mo26546b(boolean z) {
        if (this.f6562a.f6622o) {
            Object b = mo26545b("consent");
            C2901d.m7639a((JSONObject) b, z);
            C2901d.m7636a("should_prompt", Boolean.valueOf(false), (JSONObject) b);
            this.f6562a.f6611d.mo26566a("consent_last_prompt", Integer.valueOf((int) (C2901d.m7626a() / 1000)), true);
            this.f6562a.f6611d.mo26566a("consent", b, true);
            if (z) {
                this.f6562a.f6611d.mo26575c(true);
                this.f6565d.mo26620j();
                this.f6564c.mo26620j();
                this.f6567f.mo26620j();
                this.f6566e.mo26620j();
                if (this.f6562a.f6611d.mo26572c("blacklist") == null) {
                    this.f6563b.mo26620j();
                    this.f6562a.f6611d.mo26566a("init_last_sent", Integer.valueOf(0), true);
                }
                this.f6570i.mo26525a(this.f6562a);
                this.f6562a.mo26599a(this.f6562a.f6615h, 50);
            } else {
                this.f6562a.f6618k.removeCallbacks(this);
                this.f6562a.f6618k.removeCallbacks(this.f6563b);
                this.f6562a.f6618k.removeCallbacks(this.f6565d);
                this.f6562a.f6618k.removeCallbacks(this.f6564c);
                this.f6562a.f6618k.removeCallbacks(this.f6567f);
                this.f6562a.f6618k.removeCallbacks(this.f6566e);
                this.f6562a.f6611d.mo26567a(false);
                this.f6562a.f6611d.mo26575c(false);
            }
            this.f6568g.mo26526a();
            return;
        }
        Tracker.m7517a(3, "CTR", "setConsentGra", "Consent system disabled: Ignoring");
    }

    /* Access modifiers changed, original: final */
    @NonNull
    /* renamed from: c */
    public final String mo26547c() {
        if (this.f6562a.f6622o) {
            return C2901d.m7629a(mo26545b("consent").opt(ConsentPartner.KEY_DESCRIPTION), "");
        }
        Tracker.m7517a(3, "CTR", "getConsentDes", "Consent system disabled: Ignoring");
        return "";
    }

    @AnyThread
    /* renamed from: c */
    public final void mo26535c(boolean z) {
        boolean z2 = true;
        boolean a = C2901d.m7644a(this.f6562a.f6611d.mo26572c("session_tracking"), true);
        if (C2901d.m7628a(this.f6562a.f6611d.mo26572c("initial_data")) == null) {
            z2 = false;
        }
        boolean a2 = C2901d.m7644a(this.f6562a.f6611d.mo26572c("initial_ever_sent"), false);
        if (a && (z2 || a2)) {
            if (z) {
                this.f6562a.f6626s = C2901d.m7626a();
                this.f6562a.mo26600a(new C2909k(this.f6562a, 2, null, null, null, null, null), false);
                return;
            }
            this.f6562a.mo26600a(new C2909k(this.f6562a, 3, null, null, null, null, null), false);
        } else if (z) {
            this.f6562a.mo26599a(this.f6562a.f6615h, 50);
        }
    }

    /* renamed from: d */
    public final boolean mo26529d() {
        if (this.f6562a.f6622o) {
            return C2901d.m7644a(mo26545b("consent").opt("required"), true);
        }
        Tracker.m7517a(3, "CTR", "isConsentRequ", "Consent system disabled: Ignoring");
        return false;
    }

    /* renamed from: e */
    public final boolean mo26530e() {
        if (this.f6562a.f6622o) {
            return C2901d.m7644a(mo26545b("consent").opt(ConsentPartner.KEY_GRANTED), false);
        }
        Tracker.m7517a(3, "CTR", "isConsentGran", "Consent system disabled: Ignoring");
        return false;
    }

    /* renamed from: f */
    public final long mo26531f() {
        if (this.f6562a.f6622o) {
            return C2901d.m7627a(mo26545b("consent").opt(ConsentPartner.KEY_RESPONSE_TIME), 0);
        }
        Tracker.m7517a(3, "CTR", "getConsentTim", "Consent system disabled: Ignoring");
        return 0;
    }

    @NonNull
    /* renamed from: g */
    public final String mo26532g() {
        if (this.f6562a.f6622o) {
            return C2901d.m7629a(mo26545b("consent").opt("prompt_id"), "");
        }
        Tracker.m7517a(3, "CTR", "getConsentPro", "Consent system disabled: Ignoring");
        return "";
    }

    /* Access modifiers changed, original: final */
    /* renamed from: h */
    public final boolean mo26548h() {
        if (this.f6562a.f6622o) {
            return C2901d.m7644a(mo26545b("consent").opt("should_prompt"), false);
        }
        Tracker.m7517a(3, "CTR", "isConsentShou", "Consent system disabled: Ignoring");
        return false;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: i */
    public final void mo26549i() {
        if (this.f6562a.f6622o) {
            Object b = mo26545b("consent");
            C2901d.m7636a("should_prompt", Boolean.valueOf(false), (JSONObject) b);
            this.f6562a.f6611d.mo26566a("consent", b, true);
            this.f6562a.f6611d.mo26566a("consent_last_prompt", Integer.valueOf((int) (C2901d.m7626a() / 1000)), true);
            return;
        }
        Tracker.m7517a(3, "CTR", "clearConsentS", "Consent system disabled: Ignoring");
    }

    /* Access modifiers changed, original: final */
    @NonNull
    /* renamed from: j */
    public final JSONArray mo26550j() {
        if (this.f6562a.f6622o) {
            return C2901d.m7657c(mo26545b("consent").opt(ConsentPartner.KEY_PARTNERS), true);
        }
        Tracker.m7517a(3, "CTR", "getConsentPar", "Consent system disabled: Ignoring");
        return new JSONArray();
    }

    @Contract(pure = true)
    /* renamed from: k */
    public final boolean mo26533k() {
        return this.f6563b.mo26615e() && this.f6564c.mo26615e() && this.f6565d.mo26615e();
    }

    @Contract(pure = true)
    /* renamed from: l */
    public final boolean mo26534l() {
        return this.f6569h == null || this.f6569h.mo26586a();
    }

    @WorkerThread
    public final void run() {
        String str = "Controller";
        Tracker.m7517a(5, "CTR", "Controller", "WAKE");
        if (this.f6562a.f6625r == 1 || this.f6562a.f6625r == 2) {
            Tracker.m7517a(5, "CTR", "Controller", "SLEEP: SKIP");
            return;
        }
        this.f6562a.mo26602b();
        if (this.f6563b.mo26615e()) {
            int b = C2901d.m7647b(this.f6562a.f6611d.mo26572c("kvinit_staleness"), 86400);
            Tracker.m7517a(5, "CTR", "Controller", "initLastSent: " + C2901d.m7647b(this.f6562a.f6611d.mo26572c("init_last_sent"), (int) (C2901d.m7626a() / 1000)), "initStaleness: " + b, "now: " + ((int) (C2901d.m7626a() / 1000)));
            if (b + C2901d.m7647b(this.f6562a.f6611d.mo26572c("init_last_sent"), (int) (C2901d.m7626a() / 1000)) <= ((int) (C2901d.m7626a() / 1000))) {
                this.f6562a.f6611d.mo26566a("init_last_sent", Integer.valueOf(0), true);
                this.f6563b.mo26620j();
            }
        }
        if (!this.f6563b.mo26615e()) {
            Tracker.m7517a(5, "CTR", "Controller", JiceArgs.INIT);
            if (this.f6563b.mo26617g()) {
                Tracker.m7517a(5, "CTR", "Controller", "INIT SKIP");
                return;
            }
            this.f6563b.mo26616f();
            this.f6562a.mo26600a(this.f6563b, true);
        } else if (!this.f6562a.f6622o || !mo26529d() || mo26530e()) {
            this.f6568g.mo26526a();
            if (C2901d.m7644a(this.f6562a.f6611d.mo26572c("push"), false)) {
                str = C2901d.m7628a(this.f6562a.f6611d.mo26572c("push_token"));
                Boolean b2 = C2901d.m7648b(this.f6562a.f6611d.mo26572c("push_token_enable"));
                boolean a = C2901d.m7644a(this.f6562a.f6611d.mo26572c("push_token_sent"), false);
                if (!(str == null || b2 == null || a)) {
                    this.f6562a.mo26600a(new C2916p(this.f6562a, str, b2.booleanValue(), true), true);
                }
            }
            if (!this.f6564c.mo26615e()) {
                Tracker.m7517a(5, "CTR", "Controller", "UPDATE");
                if (this.f6564c.mo26617g()) {
                    Tracker.m7517a(5, "CTR", "Controller", "UPDATE SKIP");
                    return;
                }
                this.f6564c.mo26616f();
                this.f6562a.mo26600a(this.f6564c, true);
            } else if (this.f6565d.mo26615e()) {
                if (!this.f6566e.mo26615e()) {
                    Tracker.m7517a(5, "CTR", "Controller", "GET_ATTRIBUTION");
                    if (this.f6566e.mo26617g()) {
                        Tracker.m7517a(5, "CTR", "Controller", "GET_ATTRIBUTION SKIP");
                    } else {
                        this.f6566e.mo26616f();
                        this.f6562a.mo26600a(this.f6566e, true);
                    }
                }
                if (!this.f6567f.mo26615e()) {
                    Tracker.m7517a(5, "CTR", "Controller", "QUEUE");
                    if (this.f6567f.mo26617g()) {
                        Tracker.m7517a(5, "CTR", "Controller", "QUEUE SKIP");
                        return;
                    }
                    this.f6567f.mo26616f();
                    this.f6562a.mo26600a(this.f6567f, false);
                }
            } else {
                Tracker.m7517a(5, "CTR", "Controller", "INITIAL");
                if (this.f6565d.mo26617g()) {
                    Tracker.m7517a(5, "CTR", "Controller", "INITIAL SKIP");
                    return;
                }
                this.f6565d.mo26616f();
                this.f6562a.mo26600a(this.f6565d, true);
            }
        }
    }
}
