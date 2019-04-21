package com.ensighten;

import android.content.Context;
import android.os.AsyncTask;
import com.ensighten.C1714N.C17131;
import com.ensighten.C1742X.C1740b;
import com.ensighten.C1742X.C1741c;
import com.ensighten.C1750e.C17491;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.ensighten.f */
public final class C1755f implements C1740b, C1741c {
    /* renamed from: a */
    public C1750e f5875a;
    /* renamed from: b */
    public Set<C1696a> f5876b = new HashSet();

    /* renamed from: com.ensighten.f$a */
    public interface C1696a {
        /* renamed from: a */
        void mo15024a(C1750e c1750e);

        /* renamed from: a */
        void mo15025a(Class<?> cls);

        /* renamed from: b */
        void mo15026b();
    }

    /* renamed from: com.ensighten.f$1 */
    static /* synthetic */ class C17541 {
        /* renamed from: a */
        static final /* synthetic */ int[] f5874a = new int[4];

        static {
            int[] iArr;
            int i;
            C1756g.m7328a();
            try {
                iArr = f5874a;
                i = C1756g.f5878b;
                iArr[1] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr = f5874a;
                i = C1756g.f5880d;
                iArr[3] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr = f5874a;
                i = C1756g.f5879c;
                iArr[2] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public C1755f(Context context) {
        this.f5875a = new C1750e(context);
    }

    /* renamed from: a */
    public final void mo15116a(JSONObject jSONObject, int i) {
        C1750e c1750e = this.f5875a;
        if (jSONObject != null) {
            try {
                if (!(jSONObject.length() == 0 || (jSONObject.has("isCurrent") && jSONObject.getBoolean("isCurrent")))) {
                    String jSONObjectInstrumentation;
                    String str = c1750e.f5845c;
                    if (jSONObject instanceof JSONObject) {
                        jSONObjectInstrumentation = JSONObjectInstrumentation.toString(jSONObject);
                    } else {
                        jSONObjectInstrumentation = jSONObject.toString();
                    }
                    if (!str.equals(Utils.sha256(jSONObjectInstrumentation))) {
                        JSONObject jSONObject2;
                        Iterator keys;
                        JSONObject jSONObject3;
                        c1750e.f5848f.clear();
                        c1750e.f5849g.clear();
                        c1750e.f5851i.clear();
                        c1750e.f5852j.clear();
                        c1750e.f5845c = "unknown";
                        c1750e.f5844b = jSONObject;
                        if (c1750e.f5844b.has("version")) {
                            jSONObjectInstrumentation = c1750e.f5844b.getString("version");
                        } else {
                            Ensighten.getInstance();
                            jSONObjectInstrumentation = Ensighten.getStorageManager().f5696d.f6012a;
                        }
                        c1750e.f5846d = jSONObjectInstrumentation;
                        c1750e.f5855m = c1750e.f5844b.has("refreshInterval") ? (long) c1750e.f5844b.getInt("refreshInterval") : c1750e.f5855m;
                        JSONObject jSONObject4 = c1750e.f5844b;
                        c1750e.f5845c = Utils.sha256(!(jSONObject4 instanceof JSONObject) ? jSONObject4.toString() : JSONObjectInstrumentation.toString(jSONObject4));
                        c1750e.f5853k = c1750e.f5844b.has("maxQueue") ? c1750e.f5844b.getInt("maxQueue") : c1750e.f5853k;
                        c1750e.f5847e = c1750e.f5844b.has("init") ? c1750e.f5844b.getJSONArray("init") : null;
                        c1750e.f5854l = c1750e.f5844b.has("queueDepth") ? c1750e.f5844b.getInt("queueDepth") : c1750e.f5854l;
                        Utils.setUploadURL(c1750e.f5844b.has("uploadUrl") ? c1750e.f5844b.getString("uploadUrl") : "api.ensighten.com");
                        boolean z = c1750e.f5861s;
                        c1750e.f5861s = c1750e.f5844b.has("persistentStoreEnabled") ? c1750e.f5844b.getBoolean("persistentStoreEnabled") : c1750e.f5861s;
                        if (!(c1750e.f5861s || z == c1750e.f5861s)) {
                            boolean deleteFile = c1750e.f5843a.deleteFile("ensighten");
                            z = c1750e.f5843a.deleteFile("ensightenQ");
                            boolean deleteFile2 = c1750e.f5843a.deleteFile("ensightenOptimizationConfig");
                            if (C1845i.m7352b()) {
                                C1845i.m7350b(String.format("Cleared persistent storage. Configuration file success: %s. Queue file success: %s. Optimization file success = %s.", new Object[]{Boolean.valueOf(deleteFile), Boolean.valueOf(z), Boolean.valueOf(deleteFile2)}));
                            }
                        }
                        c1750e.f5862t = c1750e.f5844b.has("privacyEnabled") ? c1750e.f5844b.getBoolean("privacyEnabled") : c1750e.f5862t;
                        c1750e.f5859q = c1750e.f5844b.has("privacyStatement") ? c1750e.f5844b.getString("privacyStatement") : c1750e.f5859q;
                        jSONObjectInstrumentation = Utils.sha256(c1750e.f5859q);
                        if (!c1750e.f5860r.equals(jSONObjectInstrumentation)) {
                            c1750e.f5857o = false;
                            c1750e.f5860r = jSONObjectInstrumentation;
                        }
                        if (c1750e.f5856n && !c1750e.f5857o) {
                            Utils.showPrivacyDialog(Ensighten.getCurrentActivity());
                        }
                        c1750e.f5865w = c1750e.f5844b.has("videoTracking") ? c1750e.f5844b.getBoolean("videoTracking") : c1750e.f5865w;
                        c1750e.f5866x = c1750e.f5844b.has("adTracking") ? c1750e.f5844b.getBoolean("adTracking") : c1750e.f5866x;
                        c1750e.f5868z = c1750e.f5844b.has("batteryWatchEnabled") ? c1750e.f5844b.getBoolean("batteryWatchEnabled") : c1750e.f5868z;
                        c1750e.f5841A = c1750e.f5844b.has("batteryWatchLevelDefault") ? c1750e.f5844b.getInt("batteryWatchLevelDefault") : c1750e.f5841A;
                        c1750e.f5842B = c1750e.f5844b.has("batteryWatchLevelClientPreferred") ? c1750e.f5844b.getInt("batteryWatchLevelClientPreferred") : c1750e.f5842B;
                        if (c1750e.f5844b == null || !c1750e.f5844b.has("classes")) {
                            jSONObject2 = null;
                        } else {
                            jSONObject2 = c1750e.f5844b.getJSONObject("classes");
                        }
                        if (jSONObject2 != null) {
                            keys = jSONObject2.keys();
                            while (keys.hasNext()) {
                                jSONObjectInstrumentation = (String) keys.next();
                                c1750e.f5848f.put(jSONObjectInstrumentation, Pattern.compile(jSONObjectInstrumentation));
                                Iterator keys2 = jSONObject2.getJSONObject(jSONObjectInstrumentation).keys();
                                while (keys2.hasNext()) {
                                    str = (String) keys2.next();
                                    c1750e.f5849g.put(str, Pattern.compile(str));
                                    if (jSONObjectInstrumentation.equals(C1743a.f5832d) && str.equals("onCrash")) {
                                        Ensighten.getExceptionManager().mo15112b();
                                    }
                                }
                            }
                        }
                        if (c1750e.f5844b == null || !c1750e.f5844b.has("notifications")) {
                            jSONObject2 = null;
                        } else {
                            jSONObject2 = c1750e.f5844b.getJSONObject("notifications");
                        }
                        if (jSONObject2 != null) {
                            keys = jSONObject2.keys();
                            while (keys.hasNext()) {
                                jSONObjectInstrumentation = (String) keys.next();
                                HashMap hashMap = new HashMap();
                                JSONObject jSONObject5 = jSONObject2.getJSONObject(jSONObjectInstrumentation);
                                Iterator keys3 = jSONObject5.keys();
                                while (keys3.hasNext()) {
                                    str = (String) keys3.next();
                                    hashMap.put(str, Pattern.compile(str));
                                    if ("MoviePlayback".equals(jSONObjectInstrumentation)) {
                                        jSONObject3 = jSONObject5.getJSONObject(str);
                                        if (jSONObject3.has("keyframe") && jSONObject3.getJSONObject("keyframe").has("keyframe")) {
                                            c1750e.f5852j.put(str, jSONObject3.getJSONObject("keyframe").getJSONArray("keyframes"));
                                        }
                                    }
                                }
                                c1750e.f5851i.put(jSONObjectInstrumentation, hashMap);
                            }
                        }
                        JSONArray jSONArray = c1750e.f5844b.has("modules") ? c1750e.f5844b.getJSONArray("modules") : null;
                        int length = jSONArray == null ? 0 : jSONArray.length();
                        c1750e.f5864v = length == 0;
                        for (int i2 = 0; i2 < length; i2++) {
                            jSONObject3 = (JSONObject) jSONArray.get(i2);
                            jSONObjectInstrumentation = Ensighten.getClientId();
                            str = Ensighten.getAppId();
                            Ensighten.getInstance();
                            jSONObjectInstrumentation = Utils.buildModuleURL(jSONObjectInstrumentation, str, Ensighten.getStorageManager().f5696d.f6012a, (String) jSONObject3.get("name"), Version.getLabel(), String.valueOf(Ensighten.isAdminMode()), String.valueOf(Ensighten.isTestMode()));
                            if (C1845i.m7352b()) {
                                C1845i.m7350b(String.format("Requested module %s.", new Object[]{jSONObject3.get("name")}));
                            }
                            C1856o c1856o = new C1856o();
                            C17491 c17491 = new C17491(new String[]{".*"});
                            c1750e.f5863u.put(c17491, jSONObject3);
                            c1750e.f5850h.add(c1856o);
                            c1856o.mo15510a(jSONObjectInstrumentation, c17491);
                        }
                        if (C1845i.m7352b()) {
                            C1845i.m7350b("Set the configuration successfully.");
                        }
                    }
                }
                if (C1845i.m7352b()) {
                    C1845i.m7350b("The configuration was received but is the same as the cached one and was not processed.");
                }
            } catch (JSONException e) {
                if (C1845i.m7352b()) {
                    C1845i.m7353c(e);
                }
            }
        }
        m7320a(this.f5875a, i);
    }

    /* renamed from: a */
    public final void mo15113a() {
        if (C1845i.m7357d()) {
            C1845i.m7350b("Loading the configuration");
        }
        Ensighten.getStorageManager();
        mo15116a(C1714N.m7241a("ensighten"), C1756g.f5878b);
    }

    /* renamed from: b */
    public final void mo15092b(JSONObject jSONObject, int i) {
        switch (C17541.f5874a[i - 1]) {
            case 1:
                mo15116a(jSONObject, i);
                return;
            case 2:
                mo15113a();
                return;
            case 3:
                mo15116a(jSONObject, i);
                if (this.f5875a.f5861s) {
                    if (C1845i.m7357d()) {
                        C1845i.m7350b("Saving the configuration.");
                    }
                    C17131 c17131 = new C17131(this.f5875a.f5844b, "ensighten");
                    Void[] voidArr = new Void[0];
                    if (c17131 instanceof AsyncTask) {
                        AsyncTaskInstrumentation.execute(c17131, voidArr);
                        return;
                    } else {
                        c17131.execute(voidArr);
                        return;
                    }
                } else if (C1845i.m7352b()) {
                    C1845i.m7350b("Persistent storage is disabled. Failed to save configuration.");
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    /* renamed from: a */
    public final void mo15093a(boolean z) {
        if (!z && this.f5875a.f5844b == null) {
            mo15113a();
        }
    }

    /* renamed from: a */
    public final void mo15114a(C1696a c1696a) {
        synchronized (this.f5876b) {
            this.f5876b.add(c1696a);
        }
    }

    /* renamed from: a */
    private void m7320a(C1750e c1750e, int i) {
        synchronized (this.f5876b) {
            for (C1696a a : this.f5876b) {
                a.mo15024a(c1750e);
            }
        }
    }

    /* renamed from: b */
    public final void mo15117b() {
        synchronized (this.f5876b) {
            for (C1696a b : this.f5876b) {
                b.mo15026b();
            }
        }
    }

    /* renamed from: a */
    public final void mo15115a(Class<?> cls) {
        synchronized (this.f5876b) {
            for (C1696a a : this.f5876b) {
                a.mo15025a((Class) cls);
            }
        }
    }
}
