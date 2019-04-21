package com.kochava.base;

import android.support.annotation.AnyThread;
import android.support.annotation.CheckResult;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager;
import com.facebook.Response;
import com.facebook.stetho.common.Utf8Charset;
import com.mcdonalds.sdk.services.analytics.tagmanager.Parameters;
import com.newrelic.agent.android.agentdata.HexAttributes;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.Contract;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.kochava.base.i */
abstract class C2907i implements Runnable {
    @NonNull
    /* renamed from: b */
    private static final Object f6628b = new Object();
    @NonNull
    /* renamed from: a */
    final C2906h f6629a;
    /* renamed from: c */
    private final boolean f6630c;
    /* renamed from: d */
    private final long f6631d;
    /* renamed from: e */
    private final boolean f6632e;
    /* renamed from: f */
    private final long f6633f;
    /* renamed from: g */
    private int f6634g = 0;
    /* renamed from: h */
    private int f6635h = 0;
    /* renamed from: i */
    private boolean f6636i = false;
    /* renamed from: j */
    private boolean f6637j = false;
    /* renamed from: k */
    private boolean f6638k = false;

    @AnyThread
    C2907i(@NonNull C2906h c2906h, boolean z) {
        this.f6629a = c2906h;
        this.f6630c = z;
        this.f6632e = c2906h.f6616i.mo26534l();
        this.f6631d = c2906h.f6626s;
        this.f6633f = C2901d.m7626a();
    }

    /* renamed from: a */
    static int m7694a(@NonNull JSONObject jSONObject) {
        String a = C2901d.m7629a(jSONObject.opt(Parameters.ACTION), "");
        int i = -1;
        switch (a.hashCode()) {
            case -1239656817:
                if (a.equals("push_token_remove")) {
                    i = 9;
                    break;
                }
                break;
            case -838846263:
                if (a.equals("update")) {
                    i = 3;
                    break;
                }
                break;
            case -120977960:
                if (a.equals("identityLink")) {
                    i = 6;
                    break;
                }
                break;
            case 3237136:
                if (a.equals("init")) {
                    i = 0;
                    break;
                }
                break;
            case 96891546:
                if (a.equals("event")) {
                    i = 5;
                    break;
                }
                break;
            case 530263318:
                if (a.equals("get_attribution")) {
                    i = 4;
                    break;
                }
                break;
            case 629233382:
                if (a.equals("deeplink")) {
                    i = 7;
                    break;
                }
                break;
            case 1948342084:
                if (a.equals("initial")) {
                    i = 1;
                    break;
                }
                break;
            case 1951714934:
                if (a.equals("push_token_add")) {
                    i = 8;
                    break;
                }
                break;
            case 1984987798:
                if (a.equals("session")) {
                    i = 2;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                JSONObject f = C2901d.m7661f(jSONObject.opt(MapTilsCacheAndResManager.AUTONAVI_DATA_PATH));
                return (f == null || !"pause".equalsIgnoreCase(C2901d.m7629a(f.opt(HexAttributes.HEX_ATTR_THREAD_STATE), ""))) ? 2 : 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 7;
            case 7:
                return 8;
            case 8:
                return 9;
            case 9:
                return 10;
            default:
                return 6;
        }
    }

    @NonNull
    /* renamed from: a */
    private String m7695a(@NonNull Object obj) {
        if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            m7707b(jSONObject);
            return C2901d.m7632a(jSONObject);
        } else if (obj instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) obj;
            for (int i = 0; i < jSONArray.length(); i++) {
                m7707b(C2901d.m7649b(jSONArray.opt(i), true));
            }
            return C2901d.m7631a(jSONArray);
        } else {
            throw new IOException("Invalid Payload Type");
        }
    }

    /* renamed from: a */
    static void m7696a(int i, @NonNull JSONObject jSONObject) {
        switch (i) {
            case 0:
                C2901d.m7636a(Parameters.ACTION, (Object) "init", jSONObject);
                return;
            case 1:
                C2901d.m7636a(Parameters.ACTION, (Object) "initial", jSONObject);
                return;
            case 2:
            case 3:
                C2901d.m7636a(Parameters.ACTION, (Object) "session", jSONObject);
                return;
            case 4:
                C2901d.m7636a(Parameters.ACTION, (Object) "update", jSONObject);
                return;
            case 5:
                C2901d.m7636a(Parameters.ACTION, (Object) "get_attribution", jSONObject);
                return;
            case 6:
                C2901d.m7636a(Parameters.ACTION, (Object) "event", jSONObject);
                return;
            case 7:
                C2901d.m7636a(Parameters.ACTION, (Object) "identityLink", jSONObject);
                return;
            case 8:
                C2901d.m7636a(Parameters.ACTION, (Object) "deeplink", jSONObject);
                return;
            case 9:
                C2901d.m7636a(Parameters.ACTION, (Object) "push_token_add", jSONObject);
                return;
            case 10:
                C2901d.m7636a(Parameters.ACTION, (Object) "push_token_remove", jSONObject);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private static void m7697a(@NonNull C2901d c2901d, @NonNull JSONObject jSONObject) {
        String a = C2901d.m7628a(c2901d.mo26572c("ext_date"));
        C2901d.m7636a("sdk_build_date", "2018-05-01T20:19:09Z" + (a != null ? " (" + a + ")" : ""), jSONObject);
    }

    /* renamed from: a */
    private static void m7698a(@NonNull C2901d c2901d, @NonNull JSONObject jSONObject, @Nullable JSONArray jSONArray) {
        if (jSONArray != null && !C2901d.m7645a(jSONArray, "identity_link")) {
            Object f = C2901d.m7661f(c2901d.mo26572c("identity_link_all"));
            if (f != null) {
                c2901d.mo26569b("identity_link");
                C2901d.m7636a("identity_link", f, jSONObject);
            }
        }
    }

    /* renamed from: a */
    private static void m7699a(@NonNull C2901d c2901d, @NonNull JSONObject jSONObject, @Nullable JSONArray jSONArray, @Nullable JSONArray jSONArray2) {
        String str = "addCustomItem";
        JSONObject f = C2901d.m7661f(c2901d.mo26572c("custom"));
        if (f != null && f.length() > 0) {
            Iterator keys = f.keys();
            while (keys.hasNext()) {
                str = (String) keys.next();
                Object a = C2901d.m7628a(f.opt(str));
                if (a == null || !C2901d.m7645a(jSONArray2, str) || C2901d.m7645a(jSONArray, str)) {
                    Tracker.m7517a(4, "TSK", "addCustomItem", "Custom item not in whitelist. Ignoring.", str, a);
                } else {
                    C2901d.m7636a(str, a, jSONObject);
                }
            }
        }
    }

    /* renamed from: a */
    private static void m7700a(@NonNull C2901d c2901d, @NonNull JSONObject jSONObject, @Nullable JSONArray jSONArray, boolean z) {
        if (jSONArray != null && !C2901d.m7645a(jSONArray, "app_limit_tracking")) {
            if (!z || C2901d.m7644a(c2901d.mo26572c("app_limit_trackingupd"), false)) {
                c2901d.mo26565a("app_limit_trackingupd", Boolean.valueOf(false));
                Object b = C2901d.m7648b(c2901d.mo26572c("app_limit_tracking"));
                if (b == null) {
                    if (!z) {
                        b = Boolean.valueOf(false);
                    } else {
                        return;
                    }
                }
                C2901d.m7636a("app_limit_tracking", b, jSONObject);
            }
        }
    }

    /* renamed from: a */
    static void m7701a(@NonNull JSONObject jSONObject, @NonNull C2901d c2901d) {
        Object a = C2901d.m7628a(c2901d.mo26572c("kochava_app_id_override"));
        if (a != null) {
            C2901d.m7636a("kochava_app_id", a, jSONObject);
        } else {
            a = C2901d.m7628a(c2901d.mo26572c("kochava_app_id"));
            if (a != null) {
                C2901d.m7636a("kochava_app_id", a, jSONObject);
            }
        }
        a = C2901d.m7628a(c2901d.mo26572c("kochava_device_id"));
        if (a != null) {
            C2901d.m7636a("kochava_device_id", a, jSONObject);
        }
    }

    /* renamed from: a */
    private static void m7702a(@NonNull JSONObject jSONObject, @Nullable JSONArray jSONArray, int i) {
        if (jSONArray != null && !C2901d.m7645a(jSONArray, "state_active_count")) {
            C2901d.m7636a("state_active_count", Integer.valueOf(i), jSONObject);
        }
    }

    /* renamed from: a */
    private static void m7703a(@NonNull JSONObject jSONObject, @Nullable JSONArray jSONArray, boolean z) {
        if (jSONArray != null && !C2901d.m7645a(jSONArray, "state_active")) {
            C2901d.m7636a("state_active", Boolean.valueOf(z), jSONObject);
        }
    }

    @Contract(pure = true)
    @NonNull
    /* renamed from: b */
    private String m7704b(int i) {
        String optString;
        switch (i) {
            case 0:
                optString = this.f6629a.f6610c.optString("url_init", null);
                return optString == null ? "https://kvinit-prod.api.kochava.com/track/kvinit" : optString;
            case 1:
                optString = this.f6629a.f6610c.optString("url_initial", null);
                return optString == null ? "https://control.kochava.com/track/json" : optString;
            case 2:
            case 3:
            case 6:
            case 8:
                optString = this.f6629a.f6610c.optString("url_event", null);
                return optString == null ? "https://control.kochava.com/track/json" : optString;
            case 4:
                optString = this.f6629a.f6610c.optString("url_update", null);
                return optString == null ? "https://control.kochava.com/track/json" : optString;
            case 5:
                optString = this.f6629a.f6610c.optString("url_get_attribution", null);
                return optString == null ? "https://control.kochava.com/track/kvquery" : optString;
            case 7:
                optString = this.f6629a.f6610c.optString("url_identity_link", null);
                return optString == null ? "https://control.kochava.com/track/json" : optString;
            case 9:
                optString = this.f6629a.f6610c.optString("url_push_token_add", null);
                return optString == null ? "https://token.api.kochava.com/token/add" : optString;
            case 10:
                optString = this.f6629a.f6610c.optString("url_push_token_remove", null);
                return optString == null ? "https://token.api.kochava.com/token/remove" : optString;
            default:
                return "https://control.kochava.com/track/json";
        }
    }

    /* renamed from: b */
    private static void m7705b(@NonNull C2901d c2901d, @NonNull JSONObject jSONObject) {
        Object a = C2901d.m7628a(c2901d.mo26572c("partner_name"));
        if (a != null) {
            C2901d.m7636a("partner_name", a, jSONObject);
        }
    }

    @WorkerThread
    /* renamed from: b */
    private static void m7706b(@NonNull C2901d c2901d, @NonNull JSONObject jSONObject, @Nullable JSONArray jSONArray) {
        String str = "addConversion";
        if (!C2901d.m7645a(jSONArray, "conversion_type") && !C2901d.m7645a(jSONArray, "conversion_data")) {
            Object a = C2901d.m7628a(c2901d.mo26572c("referrer"));
            if (a == null) {
                String a2 = C2901d.m7629a(jSONObject.opt("installer_package"), "");
                int i = (C2901d.m7661f(jSONObject.opt("install_referrer")) == null || !"ok".equals(C2901d.m7629a(jSONObject.opt("status"), ""))) ? 0 : 1;
                if ("com.android.vending".equalsIgnoreCase(a2) && i == 0) {
                    try {
                        ReferralReceiver.f6537a.await(10, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        Tracker.m7517a(4, "TSK", "addConversion", e);
                    }
                }
                a = C2901d.m7628a(c2901d.mo26572c("referrer"));
            }
            Object a3 = C2901d.m7628a(c2901d.mo26572c("referrer_source"));
            if (a != null && a3 != null) {
                C2901d.m7636a("conversion_type", a3, jSONObject);
                C2901d.m7636a("conversion_data", a, jSONObject);
            }
        }
    }

    /* renamed from: b */
    private void m7707b(@NonNull JSONObject jSONObject) {
        byte[] bytes;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String format = simpleDateFormat.format(new Date(C2901d.m7626a()));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(C2901d.m7629a(jSONObject.opt("nt_id"), ""));
        stringBuilder.append(C2901d.m7629a(jSONObject.opt("kochava_app_id"), ""));
        stringBuilder.append(C2901d.m7629a(jSONObject.opt("kochava_device_id"), ""));
        stringBuilder.append(C2901d.m7629a(jSONObject.opt("sdk_version"), ""));
        stringBuilder.append(format);
        JSONObject b = C2901d.m7649b(jSONObject.opt(MapTilsCacheAndResManager.AUTONAVI_DATA_PATH), true);
        for (String opt : new String[]{"adid", "android_id", "fire_adid", "fb_attribution_id", "custom", "custom_id", "conversion_data"}) {
            stringBuilder.append(C2901d.m7629a(b.opt(opt), ""));
        }
        for (String opt2 : new String[]{"usertime"}) {
            stringBuilder.append(Integer.toString(C2901d.m7647b(b.opt(opt2), 0)));
        }
        JSONObject f = C2901d.m7661f(b.opt("ids"));
        if (f != null) {
            stringBuilder.append(C2901d.m7629a(f.opt("email"), ""));
        }
        f = C2901d.m7661f(b.opt("install_referrer"));
        if (f != null) {
            stringBuilder.append(C2901d.m7629a(f.opt("referrer"), ""));
            stringBuilder.append(C2901d.m7629a(f.opt("status"), ""));
            Integer c = C2901d.m7656c(f.opt("install_begin_time"));
            if (c != null) {
                stringBuilder.append(Integer.toString(c.intValue()));
            }
            Integer c2 = C2901d.m7656c(f.opt("referrer_click_time"));
            if (c2 != null) {
                stringBuilder.append(Integer.toString(c2.intValue()));
            }
        }
        long j = 0;
        try {
            bytes = stringBuilder.toString().getBytes(Utf8Charset.NAME);
        } catch (UnsupportedEncodingException e) {
            Tracker.m7517a(4, "TSK", "processPayloa", e);
            bytes = new byte[0];
        }
        for (byte b2 : bytes) {
            j += (long) (b2 & 255);
        }
        C2901d.m7636a("send_date", format + "." + String.format(Locale.US, "%03d", new Object[]{Long.valueOf(j % 1000)}) + "Z", jSONObject);
    }

    /* renamed from: c */
    private static void m7708c(@NonNull C2901d c2901d, @NonNull JSONObject jSONObject) {
        JSONObject f = C2901d.m7661f(c2901d.mo26572c("identity_link"));
        if (f != null) {
            c2901d.mo26569b("identity_link");
            C2901d.m7651b(jSONObject, f);
        }
    }

    /* Access modifiers changed, original: final */
    @Contract(pure = true)
    /* renamed from: a */
    public final long mo26604a() {
        return this.f6630c ? this.f6631d : this.f6629a.f6626s;
    }

    /* Access modifiers changed, original: final */
    @Nullable
    @WorkerThread
    @CheckResult
    /* renamed from: a */
    public final JSONObject mo26605a(int i, @NonNull Object obj) {
        try {
            if (C2901d.m7652b(this.f6629a.f6608a)) {
                return JSONObjectInstrumentation.init(C2901d.m7630a(m7704b(i), m7695a(obj)));
            }
            Tracker.m7517a(4, "TSK", "post", "Error: No Network Connection");
            return null;
        } catch (Throwable th) {
            Tracker.m7517a(4, "TSK", "post", th);
            return null;
        }
    }

    /* Access modifiers changed, original: final */
    @WorkerThread
    /* renamed from: a */
    public final void mo26606a(@IntRange int i) {
        Tracker.m7517a(4, "TSK", "wakeSelf", Integer.toString(i));
        this.f6629a.mo26599a((Runnable) this, (long) (C2901d.m7624a(i, 0, Integer.MAX_VALUE) * 1000));
    }

    /* Access modifiers changed, original: final */
    @WorkerThread
    /* renamed from: a */
    public final void mo26607a(int i, @NonNull JSONObject jSONObject, @NonNull JSONObject jSONObject2) {
        String str = "buildPayload";
        JSONObject b = C2901d.m7649b(this.f6629a.f6611d.mo26572c("dp_options"), true);
        JSONObject b2 = C2901d.m7649b(this.f6629a.f6611d.mo26572c("dp_override"), true);
        JSONArray g = C2901d.m7662g(this.f6629a.f6611d.mo26572c("blacklist"));
        JSONArray g2 = C2901d.m7662g(this.f6629a.f6611d.mo26572c("whitelist"));
        Tracker.m7517a(5, "TSK", "buildPayload", "Main");
        C2900c.m7581a(this.f6629a.f6608a, this.f6629a.f6611d, b, b2, this.f6629a.f6609b, g, g2, i, jSONObject2);
        synchronized (f6628b) {
            Tracker.m7517a(5, "TSK", "buildPayload", "Extra in lock");
            if (g == null && (i == 6 || i == 8 || i == 2 || i == 3)) {
                C2901d.m7636a("backfilled", Boolean.valueOf(true), jSONObject);
            }
            C2907i.m7696a(i, jSONObject);
            C2907i.m7701a(jSONObject, this.f6629a.f6611d);
            C2901d.m7636a("sdk_protocol", Integer.toString(11), jSONObject);
            C2901d.m7636a("sdk_version", C2901d.m7629a(this.f6629a.f6611d.mo26572c("sdk_version"), ""), jSONObject);
            C2901d.m7636a("nt_id", this.f6629a.f6621n + "-" + UUID.randomUUID().toString(), jSONObject);
            C2901d.m7636a(MapTilsCacheAndResManager.AUTONAVI_DATA_PATH, (Object) jSONObject2, jSONObject);
            C2901d.m7636a("usertime", Long.valueOf(mo26613c() / 1000), jSONObject2);
            if (mo26612b() || i == 3 || i == 2) {
                int i2 = 0;
                if (i != 2) {
                    i2 = C2901d.m7647b(this.f6629a.f6611d.mo26572c("session_window_uptime"), 0);
                }
                C2901d.m7636a("uptime", Double.valueOf((((double) ((mo26613c() - mo26604a()) * 10)) / 10000.0d) + ((double) i2)), jSONObject2);
            } else {
                C2901d.m7636a("uptime", Double.valueOf(((double) ((mo26613c() - this.f6629a.f6623p) * 10)) / 10000.0d), jSONObject2);
            }
            if (this.f6629a.f6622o) {
                Object jSONObject3 = new JSONObject();
                C2901d.m7636a("required", Boolean.valueOf(this.f6629a.f6616i.mo26529d()), (JSONObject) jSONObject3);
                if (this.f6629a.f6616i.mo26530e()) {
                    C2901d.m7636a("time", Long.valueOf(this.f6629a.f6616i.mo26531f()), (JSONObject) jSONObject3);
                }
                C2901d.m7636a("consent", jSONObject3, jSONObject);
            }
            switch (i) {
                case 0:
                    C2907i.m7697a(this.f6629a.f6611d, jSONObject);
                    C2907i.m7705b(this.f6629a.f6611d, jSONObject2);
                    break;
                case 1:
                    C2907i.m7703a(jSONObject2, g, mo26612b());
                    C2907i.m7700a(this.f6629a.f6611d, jSONObject2, g, false);
                    C2907i.m7698a(this.f6629a.f6611d, jSONObject2, g);
                    C2907i.m7706b(this.f6629a.f6611d, jSONObject2, g);
                    C2907i.m7699a(this.f6629a.f6611d, jSONObject2, g, g2);
                    break;
                case 2:
                    C2907i.m7703a(jSONObject2, g, true);
                    C2901d.m7636a(HexAttributes.HEX_ATTR_THREAD_STATE, (Object) "resume", jSONObject2);
                    break;
                case 3:
                    C2907i.m7703a(jSONObject2, g, true);
                    C2907i.m7702a(jSONObject2, g, C2901d.m7647b(this.f6629a.f6611d.mo26572c("session_state_active_count"), 1));
                    C2901d.m7636a(HexAttributes.HEX_ATTR_THREAD_STATE, (Object) "pause", jSONObject2);
                    break;
                case 4:
                    C2907i.m7700a(this.f6629a.f6611d, jSONObject2, g, true);
                    break;
                case 6:
                    C2907i.m7703a(jSONObject2, g, mo26612b());
                    break;
                case 7:
                    C2907i.m7708c(this.f6629a.f6611d, jSONObject2);
                    break;
                case 8:
                    C2907i.m7703a(jSONObject2, g, mo26612b());
                    break;
            }
        }
        Tracker.m7517a(5, "TSK", "buildPayload", jSONObject);
    }

    /* Access modifiers changed, original: final */
    @WorkerThread
    /* renamed from: a */
    public final void mo26608a(boolean z) {
        if (!this.f6629a.f6616i.mo26533k()) {
            Tracker.m7517a(4, "TSK", "wakeControlle", "Controller Busy. Returning.");
        } else if (z) {
            mo26621k();
        } else if (!this.f6629a.mo26601a()) {
            this.f6629a.mo26603b(this.f6629a.f6615h, (long) (C2901d.m7647b(this.f6629a.f6611d.mo26572c("kvtracker_wait"), 5) * 1000));
        }
    }

    /* Access modifiers changed, original: final */
    @WorkerThread
    @Contract("null, _ -> true")
    /* renamed from: a */
    public final boolean mo26609a(@Nullable JSONObject jSONObject, boolean z) {
        String str = "checkErrorAnd";
        if (jSONObject == null) {
            Tracker.m7517a(4, "TSK", "checkErrorAnd", "Network Error");
            if (z) {
                mo26611b(true);
            }
            return true;
        }
        if (!C2901d.m7629a(jSONObject.opt("error"), "").isEmpty()) {
            Tracker.m7517a(2, "TSK", "checkErrorAnd", "Error: " + C2901d.m7629a(jSONObject.opt("error"), ""));
        }
        if (C2901d.m7644a(jSONObject.opt(Response.SUCCESS_KEY), false)) {
            int b;
            JSONObject f = C2901d.m7661f(jSONObject.opt(MapTilsCacheAndResManager.AUTONAVI_DATA_PATH));
            if (f != null) {
                b = C2901d.m7647b(f.opt("retry"), -1);
                if (b != -1) {
                    Tracker.m7517a(4, "TSK", "checkErrorAnd", "Retry Time");
                    if (z) {
                        mo26606a(C2901d.m7624a(b, 0, Integer.MAX_VALUE));
                    }
                    return true;
                }
            }
            JSONArray g = C2901d.m7662g(jSONObject.opt("msg"));
            if (g == null) {
                return false;
            }
            for (b = 0; b < g.length(); b++) {
                if ("resonance_cascade".equalsIgnoreCase(C2901d.m7628a(g.opt(b)))) {
                    if (z) {
                        mo26611b(false);
                    }
                    return true;
                }
            }
            return false;
        }
        Tracker.m7517a(4, "TSK", "checkErrorAnd", "Success False");
        if (z) {
            mo26611b(false);
        }
        return true;
    }

    /* Access modifiers changed, original: final */
    @WorkerThread
    @NonNull
    /* renamed from: b */
    public final JSONObject mo26610b(int i, @NonNull JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        mo26607a(i, jSONObject2, jSONObject3);
        C2901d.m7651b(jSONObject3, jSONObject);
        return jSONObject3;
    }

    /* Access modifiers changed, original: final */
    @WorkerThread
    /* renamed from: b */
    public final void mo26611b(boolean z) {
        Tracker.m7517a(4, "TSK", "retry", Boolean.toString(z));
        if (z) {
            this.f6635h = C2901d.m7624a(this.f6635h + 1, 1, 4);
            switch (this.f6635h) {
                case 2:
                    mo26606a(60);
                    return;
                case 3:
                    mo26606a(300);
                    return;
                case 4:
                    mo26606a(3600);
                    return;
                default:
                    mo26606a(10);
                    return;
            }
        }
        this.f6634g = C2901d.m7624a(this.f6634g + 1, 1, 4);
        switch (this.f6634g) {
            case 2:
                mo26606a(20);
                return;
            case 3:
                mo26606a(30);
                return;
            case 4:
                mo26606a(60);
                return;
            default:
                mo26606a(10);
                return;
        }
    }

    /* Access modifiers changed, original: final */
    @Contract(pure = true)
    /* renamed from: b */
    public final boolean mo26612b() {
        return this.f6630c ? this.f6632e : this.f6629a.f6616i.mo26534l();
    }

    /* Access modifiers changed, original: final */
    @Contract(pure = true)
    /* renamed from: c */
    public final long mo26613c() {
        return this.f6630c ? this.f6633f : C2901d.m7626a();
    }

    /* Access modifiers changed, original: final */
    @WorkerThread
    /* renamed from: d */
    public final void mo26614d() {
        mo26620j();
        this.f6636i = true;
    }

    /* Access modifiers changed, original: final */
    @WorkerThread
    @Contract(pure = true)
    /* renamed from: e */
    public final boolean mo26615e() {
        return this.f6636i;
    }

    /* Access modifiers changed, original: final */
    @WorkerThread
    /* renamed from: f */
    public final void mo26616f() {
        this.f6637j = true;
    }

    /* Access modifiers changed, original: final */
    @WorkerThread
    @Contract(pure = true)
    /* renamed from: g */
    public final boolean mo26617g() {
        return this.f6637j;
    }

    /* Access modifiers changed, original: final */
    @WorkerThread
    /* renamed from: h */
    public final void mo26618h() {
        this.f6638k = true;
    }

    /* Access modifiers changed, original: final */
    @WorkerThread
    @Contract(pure = true)
    /* renamed from: i */
    public final boolean mo26619i() {
        return this.f6638k;
    }

    /* Access modifiers changed, original: final */
    @WorkerThread
    /* renamed from: j */
    public final void mo26620j() {
        this.f6636i = false;
        this.f6637j = false;
        this.f6634g = 0;
        this.f6635h = 0;
        this.f6638k = false;
    }

    /* Access modifiers changed, original: final */
    @WorkerThread
    /* renamed from: k */
    public final void mo26621k() {
        this.f6629a.mo26600a(this.f6629a.f6615h, false);
    }
}
