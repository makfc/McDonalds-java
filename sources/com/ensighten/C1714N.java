package com.ensighten;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.facebook.internal.ServerProtocol;
import com.mcdonalds.sdk.modules.notification.PushConstants;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.json.JSONObject;

/* renamed from: com.ensighten.N */
public final class C1714N {
    /* renamed from: a */
    Context f5693a;
    /* renamed from: b */
    public String f5694b;
    /* renamed from: c */
    public Configuration f5695c;
    /* renamed from: d */
    public C1866z f5696d;
    /* renamed from: e */
    private String f5697e;
    /* renamed from: f */
    private String f5698f = "unknown";
    /* renamed from: g */
    private C1744b f5699g;
    /* renamed from: h */
    private C1701J f5700h;

    /* renamed from: com.ensighten.N$1 */
    public class C17131 extends AsyncTask<Void, Void, Void> implements TraceFieldInterface {
        public Trace _nr_trace;
        /* renamed from: a */
        final /* synthetic */ JSONObject f5690a;
        /* renamed from: b */
        final /* synthetic */ String f5691b;

        public void _nr_setTrace(Trace trace) {
            try {
                this._nr_trace = trace;
            } catch (Exception e) {
            }
        }

        public C17131(JSONObject jSONObject, String str) {
            this.f5690a = jSONObject;
            this.f5691b = str;
        }

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            try {
                TraceMachine.enterMethod(this._nr_trace, "N$1#doInBackground", null);
            } catch (NoSuchFieldError e) {
                while (true) {
                    TraceMachine.enterMethod(null, "N$1#doInBackground", null);
                }
            }
            Void a = m7238a();
            TraceMachine.exitMethod();
            TraceMachine.unloadTraceContext(this);
            return a;
        }

        /* renamed from: a */
        private Void m7238a() {
            C1714N c1714n = C1714N.this;
            JSONObject jSONObject = this.f5690a;
            try {
                FileOutputStream openFileOutput = c1714n.f5693a.openFileOutput(this.f5691b, 0);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(openFileOutput);
                objectOutputStream.writeObject(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject));
                objectOutputStream.close();
                openFileOutput.close();
            } catch (Exception e) {
                if (C1845i.m7363j()) {
                    C1845i.m7351b("Error saving file to storage.", e);
                }
            }
            return null;
        }
    }

    public C1714N(Context context) {
        this.f5693a = context;
        long c = mo15055c("installDate");
        Date date = new Date();
        long longValue = Long.valueOf(date.getTime()).longValue();
        if (c == 0) {
            mo15051a("installDate", longValue);
            mo15051a("lastUseDate", longValue);
            mo15051a("previousSessionLength", 0);
            mo15051a("launchNumber", 1);
        } else {
            int a = mo15049a(date);
            mo15051a("lastUseDate", longValue);
            mo15051a("previousSessionLength", (long) a);
            mo15050a();
        }
        try {
            this.f5697e = ((TelephonyManager) this.f5693a.getSystemService("phone")).getNetworkOperatorName();
        } catch (Exception e) {
            this.f5697e = "na";
        }
        this.f5694b = System.getString(this.f5693a.getContentResolver(), "android_id");
        this.f5694b = this.f5694b == null ? "undefined" : this.f5694b;
        this.f5699g = new C1744b(this.f5694b, "");
        this.f5695c = this.f5693a.getResources().getConfiguration();
        this.f5700h = VERSION.SDK_INT >= 9 ? new C1703K(this.f5693a) : new C1704L();
        this.f5696d = new C1866z(this.f5693a);
    }

    /* renamed from: a */
    public static JSONObject m7241a(String str) {
        JSONObject init;
        Exception e;
        try {
            FileInputStream openFileInput = Ensighten.getContext().openFileInput(str);
            ObjectInputStream objectInputStream = new ObjectInputStream(openFileInput);
            init = JSONObjectInstrumentation.init((String) objectInputStream.readObject());
            try {
                openFileInput.close();
                objectInputStream.close();
            } catch (Exception e2) {
                e = e2;
                if (C1845i.m7363j()) {
                    C1845i.m7351b("Error loading file from storage.", e);
                }
                return init;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            init = null;
            e = exception;
        }
        return init;
    }

    /* renamed from: a */
    public final void mo15052a(String str, String str2) {
        this.f5693a.getSharedPreferences("EnsightenPrefs", 0).edit().putString(str, str2).commit();
    }

    /* renamed from: b */
    public final String mo15053b(String str) {
        return this.f5693a.getSharedPreferences("EnsightenPrefs", 0).getString(str, "");
    }

    /* renamed from: a */
    public final void mo15050a() {
        mo15051a("launchNumber", mo15055c("launchNumber") + 1);
    }

    /* renamed from: a */
    public final int mo15049a(Date date) {
        return (int) ((date.getTime() - mo15055c("lastUseDate")) / 1000);
    }

    /* renamed from: a */
    private static int m7239a(long j, long j2) {
        return (int) ((j2 - j) / 86400000);
    }

    /* renamed from: a */
    public final void mo15051a(String str, long j) {
        this.f5693a.getSharedPreferences("lifecycle", 0).edit().putLong(str, j).commit();
    }

    /* renamed from: c */
    public final long mo15055c(String str) {
        return this.f5693a.getSharedPreferences("lifecycle", 0).getLong(str, 0);
    }

    /* renamed from: b */
    public final JSONObject mo15054b() {
        try {
            int i;
            Object obj;
            JSONObject jSONObject = new JSONObject();
            C1850l eventManager = Ensighten.getEventManager();
            C1735W viewManager = Ensighten.getViewManager();
            C1750e c1750e = Ensighten.getConfigurationManager().f5875a;
            Date date = new Date();
            jSONObject.put("build", this.f5696d.f6012a);
            jSONObject.put("id", this.f5696d.f6013b);
            jSONObject.put("date", date);
            jSONObject.put("queueDepth", c1750e.f5853k);
            jSONObject.put(AnalyticAttribute.CARRIER_ATTRIBUTE, this.f5697e);
            jSONObject.put("configVersion", c1750e.f5846d);
            jSONObject.put("carrierName", this.f5697e);
            jSONObject.put("prevappversions", this.f5696d.f6015d);
            jSONObject.put("appversion", this.f5696d.f6012a);
            jSONObject.put("configHash", c1750e.f5845c);
            jSONObject.put("eVersion", Version.getLabel());
            jSONObject.put("referrerInfo", eventManager.f5952g);
            jSONObject.put("buildInfo", this.f5699g.f5833a);
            jSONObject.put("versionInfo", this.f5699g.f5834b);
            viewManager.mo15088c();
            jSONObject.put("activityInfo", C1714N.m7240a(viewManager.mo15088c()));
            jSONObject.put("batteryInfo", eventManager.f5951f.mo15522a());
            jSONObject.put("advertisingId", this.f5700h.mo15031a());
            jSONObject.put("isLimitAdTrackingEnabled", this.f5700h.mo15032b());
            Long valueOf = Long.valueOf(mo15055c("installDate"));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/d/yyyy", Locale.US);
            Calendar instance = Calendar.getInstance();
            long c = mo15055c("lastUseDate");
            instance.setTimeInMillis(c);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("installDate", simpleDateFormat.format(valueOf));
            jSONObject2.put("previousSessionLength", Long.toString(mo15055c("previousSessionLength")));
            jSONObject2.put("launchNumber", Long.toString(mo15055c("launchNumber")));
            jSONObject2.put("lastLaunchDate", simpleDateFormat.format(Long.valueOf(c)));
            jSONObject2.put("lastUseDate", simpleDateFormat.format(Long.valueOf(c)));
            jSONObject2.put("daysSinceFirstUse", Long.toString((long) C1714N.m7239a(valueOf.longValue(), date.getTime())));
            jSONObject2.put("daysSinceLastUse", Long.toString((long) C1714N.m7239a(c, date.getTime())));
            jSONObject2.put("dayOfWeek", Integer.valueOf(instance.get(7)).toString());
            jSONObject2.put("hourOfDay", Integer.valueOf(instance.get(11)).toString());
            jSONObject2.put("currentSessionLength", Long.toString((long) mo15049a(date)));
            jSONObject.put("lifecycle", jSONObject2);
            String appId = Ensighten.getAppId();
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("previousVersions", this.f5696d.f6015d);
            jSONObject3.put("bundleVersion", this.f5696d.f6013b);
            jSONObject3.put("currentVersion", this.f5696d.f6012a);
            jSONObject3.put("publicVersion", this.f5696d.f6014c);
            jSONObject3.put("buildNumber", this.f5696d.f6013b);
            jSONObject3.put("launchType", this.f5696d.f6016e);
            jSONObject3.put("installDate", simpleDateFormat.format(valueOf));
            jSONObject3.put("launchNumber", Long.toString(mo15055c("launchNumber")));
            jSONObject3.put("lastLaunchDate", simpleDateFormat.format(Long.valueOf(c)));
            jSONObject3.put(AnalyticAttribute.APP_ID_ATTRIBUTE, appId);
            jSONObject3.put("ensAppId", appId);
            jSONObject.put("appVersionInfo", jSONObject3);
            jSONObject3 = new JSONObject();
            jSONObject3.put("nstnid", appId);
            jSONObject3.put("systemName", "Android");
            jSONObject3.put("systemVersion", VERSION.RELEASE);
            jSONObject3.put("model", Build.MODEL);
            jSONObject3.put("batteryLevel", eventManager.f5951f.f5987a);
            jSONObject3.put("machine", Build.MODEL);
            int i2 = -1;
            try {
                DisplayMetrics displayMetrics = this.f5693a.getResources().getDisplayMetrics();
                i2 = displayMetrics.widthPixels;
                i = i2;
                i2 = displayMetrics.heightPixels;
            } catch (Exception e) {
                if (C1845i.m7363j()) {
                    C1845i.m7344a(e);
                }
                i = i2;
                i2 = -1;
            }
            jSONObject3.put("systemVersion", VERSION.RELEASE);
            jSONObject3.put("width", i);
            jSONObject3.put("height", i2);
            jSONObject.put("deviceInfo", jSONObject3);
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("adTrackingEnabled", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            jSONObject4.put("advertisingIdentifier", this.f5694b);
            jSONObject4.put("identifierForVendor", this.f5694b);
            jSONObject.put("identifierForVendor", jSONObject4);
            if (this.f5693a.getResources().getConfiguration().orientation == 1) {
                obj = "portrait";
            } else {
                obj = "landscape";
            }
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.put("orientation", obj);
            jSONObject5.put("width", i);
            jSONObject5.put("height", i2);
            jSONObject.put("screenInfo", jSONObject5);
            return jSONObject;
        } catch (Exception e2) {
            if (C1845i.m7363j()) {
                C1845i.m7353c(e2);
            }
            return new JSONObject();
        }
    }

    /* renamed from: c */
    public final String mo15056c() {
        JSONObject b = mo15054b();
        return (!(b instanceof JSONObject) ? b.toString() : JSONObjectInstrumentation.toString(b)).replaceAll("'", "\\'");
    }

    /* renamed from: a */
    private static JSONObject m7240a(Activity activity) {
        JSONObject jSONObject = new JSONObject();
        if (activity != null) {
            try {
                Object charSequence;
                jSONObject.put("name", activity.getClass().getName());
                String str = "none";
                try {
                    charSequence = activity.getTitle().toString();
                } catch (Exception e) {
                    if (C1845i.m7363j()) {
                        C1845i.m7344a(e);
                    }
                    String charSequence2 = str;
                }
                jSONObject.put(PushConstants.TITLE_KEY, charSequence2);
            } catch (Exception e2) {
                if (C1845i.m7363j()) {
                    C1845i.m7353c(e2);
                }
            }
        }
        return jSONObject;
    }
}
