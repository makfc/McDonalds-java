package com.ensighten;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Looper;
import android.support.p000v4.widget.ExploreByTouchHelper;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.webkit.CookieSyncManager;
import android.widget.VideoView;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager;
import com.ensighten.C1755f.C1696a;
import com.ensighten.C1843h.C18381;
import com.ensighten.C1843h.C1841a;
import com.ensighten.google.ads.AdHandler;
import com.ensighten.model.EnsightenGestureRecognizerFactory;
import com.ensighten.utils.BatteryInfoManager;
import com.facebook.internal.ServerProtocol;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.ensighten.l */
public final class C1850l implements C1696a, C1841a {
    /* renamed from: a */
    public SparseArray<ArrayList<C1729U>> f5946a = new SparseArray();
    /* renamed from: b */
    public SparseArray<ArrayList<C1729U>> f5947b = new SparseArray();
    /* renamed from: c */
    public String f5948c;
    /* renamed from: d */
    public boolean f5949d;
    /* renamed from: e */
    public Set<C1753a> f5950e = new HashSet();
    /* renamed from: f */
    public BatteryInfoManager f5951f;
    /* renamed from: g */
    public JSONObject f5952g;
    /* renamed from: h */
    private Context f5953h;
    /* renamed from: i */
    private C1851m f5954i = new C1851m();
    /* renamed from: j */
    private String f5955j;
    /* renamed from: k */
    private String f5956k;
    /* renamed from: l */
    private C1728T f5957l;
    /* renamed from: m */
    private boolean f5958m = false;

    /* renamed from: com.ensighten.l$a */
    public interface C1753a {
        /* renamed from: a */
        void mo15111a();
    }

    public C1850l(Context context) {
        this.f5953h = context;
        try {
            this.f5951f = new BatteryInfoManager();
            if (context instanceof Activity) {
                this.f5953h.registerReceiver(this.f5951f, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                this.f5958m = true;
            }
        } catch (Exception e) {
            if (C1845i.m7357d()) {
                C1845i.m7353c(e);
            }
        }
    }

    /* renamed from: a */
    public final void mo15481a() {
        C1750e c1750e = Ensighten.getConfigurationManager().f5875a;
        if (c1750e.f5864v) {
            mo15500a(c1750e.f5847e, null, null, null);
            c1750e.f5847e = null;
        }
    }

    /* renamed from: a */
    public final void mo15024a(C1750e c1750e) {
        C1851m c1851m = this.f5954i;
        c1851m.f5960b.clear();
        if (!(c1750e == null || c1750e.f5844b == null)) {
            String str;
            String str2;
            JSONObject jSONObject = c1750e.f5844b;
            JSONObject jSONObject2 = new JSONObject();
            try {
                JSONObject jSONObject3;
                if (jSONObject.has("classes")) {
                    jSONObject3 = jSONObject.getJSONObject("classes");
                } else {
                    jSONObject3 = null;
                }
                if (jSONObject3 != null) {
                    JSONObject jSONObject4 = new JSONObject();
                    Iterator keys = jSONObject3.keys();
                    while (keys.hasNext()) {
                        str = (String) keys.next();
                        JSONObject jSONObject5 = jSONObject3.getJSONObject(str);
                        if (Utils.isSimpleClassOrMethodName(str)) {
                            JSONObject jSONObject6 = new JSONObject();
                            Object obj = null;
                            if (jSONObject5 != null) {
                                Iterator keys2 = jSONObject5.keys();
                                Object obj2 = null;
                                while (keys2.hasNext()) {
                                    str2 = (String) keys2.next();
                                    Object jSONArray = jSONObject5.getJSONObject(str2).getJSONArray("rules");
                                    if (Utils.isSimpleClassOrMethodName(str2)) {
                                        if (jSONArray == null) {
                                            jSONArray = C1743a.f5830b;
                                        }
                                        c1851m.f5960b.put(str + "|" + str2, jSONArray);
                                    } else {
                                        jSONObject6.put(str2, jSONArray);
                                        obj2 = 1;
                                    }
                                }
                                obj = obj2;
                            }
                            if (obj != null) {
                                jSONObject4.put(str, jSONObject6);
                            }
                        } else {
                            jSONObject4.put(str, jSONObject5);
                        }
                    }
                    jSONObject2.put("classes", jSONObject4);
                }
            } catch (JSONException e) {
            }
            c1851m.f5959a = jSONObject2;
            if (C1845i.m7357d()) {
                C1845i.m7345a(String.format("New rule cache after processing config is %s.", new Object[]{c1851m.f5960b.toString()}));
                str2 = "New config cache after processing config is %s.";
                Object[] objArr = new Object[1];
                jSONObject = c1851m.f5959a;
                objArr[0] = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
                C1845i.m7345a(String.format(str2, objArr));
            }
            try {
                JSONObject jSONObject7 = (c1851m.f5959a == null || !c1851m.f5959a.has("classes")) ? null : c1851m.f5959a.getJSONObject("classes");
                if (jSONObject7 != null) {
                    Iterator keys3 = jSONObject7.keys();
                    while (keys3.hasNext()) {
                        str = (String) keys3.next();
                        c1851m.f5961c.put(str, Pattern.compile(str));
                        Iterator keys4 = jSONObject7.getJSONObject(str).keys();
                        while (keys4.hasNext()) {
                            str2 = (String) keys4.next();
                            c1851m.f5962d.put(str2, Pattern.compile(str2));
                            if (str.equals(C1743a.f5832d) && str2.equals("onCrash")) {
                                Ensighten.getExceptionManager().mo15112b();
                            }
                        }
                    }
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        mo15502c();
    }

    /* renamed from: b */
    public final void mo15026b() {
        C1750e c1750e = Ensighten.getConfigurationManager().f5875a;
        if (c1750e != null) {
            mo15500a(c1750e.f5847e, null, null, null);
        }
    }

    /* renamed from: a */
    public final void mo15025a(Class<?> cls) {
    }

    /* renamed from: a */
    public final void mo15497a(Class<?> cls, String str, Object[] objArr) {
        if (cls != null && str != null) {
            long currentTimeMillis;
            if (C1846j.m7369a() && Looper.myLooper() == Looper.getMainLooper()) {
                currentTimeMillis = System.currentTimeMillis();
            } else {
                currentTimeMillis = 0;
            }
            C1750e c1750e = Ensighten.getConfigurationManager().f5875a;
            if (c1750e.mo15108a() && c1750e.f5864v) {
                String name = cls.getName();
                if (!m7396a(name, str)) {
                    this.f5955j = name;
                    this.f5956k = str;
                    if (!c1750e.f5856n) {
                        try {
                            JSONArray a = this.f5954i.mo15503a(str, objArr, name);
                            if (a != null) {
                                m7395a(a, cls, str, objArr, false);
                            }
                            m7397d();
                        } catch (Exception e) {
                            if (C1845i.m7357d()) {
                                C1845i.m7353c(e);
                            }
                            m7397d();
                        } catch (Throwable th) {
                            m7397d();
                        }
                    }
                } else {
                    return;
                }
            }
            if (C1846j.m7369a() && Looper.myLooper() == Looper.getMainLooper() && cls != null) {
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                C1846j.m7366a(String.format("\tEvent evaluation of %s.%s took %d.", new Object[]{cls.getName(), str, Long.valueOf(currentTimeMillis2)}));
            }
        } else if (C1845i.m7357d()) {
            C1845i.m7345a("The class or method is null during event evaluation.");
        }
    }

    /* renamed from: a */
    public final void mo15498a(Object obj, String str, Object[] objArr) {
        mo15499a(obj, str, objArr, false);
    }

    /* renamed from: a */
    public final void mo15499a(Object obj, String str, Object[] objArr, boolean z) {
        if (!(obj == null || str == null)) {
            try {
                Class cls = obj.getClass();
                if (cls != null) {
                    long currentTimeMillis;
                    if (C1846j.m7369a() && Looper.myLooper() == Looper.getMainLooper()) {
                        currentTimeMillis = System.currentTimeMillis();
                    } else {
                        currentTimeMillis = 0;
                    }
                    String name = cls.getName();
                    if (m7396a(name, str)) {
                        m7397d();
                        return;
                    }
                    Object obj2;
                    this.f5955j = name;
                    this.f5956k = str;
                    C1750e c1750e = Ensighten.getConfigurationManager().f5875a;
                    if ((obj instanceof VideoView) && ((this.f5949d || (c1750e.f5865w && "start".equals(str))) && this.f5957l == null)) {
                        this.f5957l = new C1728T((VideoView) obj);
                        if (C1845i.m7357d()) {
                            C1845i.m7345a(String.format("A video tracker has been created for source %s", new Object[]{obj.toString()}));
                        }
                    }
                    try {
                        if ("loadAd".equals(str)) {
                            AdHandler.handleLoadAd(obj);
                        }
                    } catch (Throwable th) {
                        if (C1845i.m7357d()) {
                            C1845i.m7356d("Unable to find Google AdMob library, no ad tracking is available");
                        }
                    }
                    Ensighten.getInstance();
                    C1714N storageManager = Ensighten.getStorageManager();
                    long time;
                    Object obj3;
                    if ("onResume".equalsIgnoreCase(str) && (obj instanceof Activity)) {
                        Activity activity = (Activity) obj;
                        Ensighten.getViewManager().mo15085a(activity);
                        String name2 = activity.getClass().getName();
                        if (!this.f5958m) {
                            this.f5953h.registerReceiver(this.f5951f, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                        }
                        m7394a(activity);
                        C1742X webManager = Ensighten.getWebManager();
                        if (!webManager.f5821k) {
                            webManager.mo15099b();
                        }
                        if (C1845i.m7357d()) {
                            C1845i.m7345a(String.format("Checking if current activity %s is root: %b", new Object[]{name2, Boolean.valueOf(activity.isTaskRoot())}));
                        }
                        time = new Date().getTime();
                        long c = storageManager.mo15055c("onPauseTime");
                        if (c == 0) {
                            Ensighten.getJavascriptProcessor().mo15488c("Bootstrapper.onAppEnterForeground(params);");
                        } else if (time - c > 2000) {
                            Ensighten.getJavascriptProcessor().mo15488c("Bootstrapper.onAppEnterBackground(params);");
                            Ensighten.getJavascriptProcessor().mo15488c("Bootstrapper.onAppEnterForeground(params);");
                        }
                        if (c1750e.f5862t && !c1750e.f5857o) {
                            Utils.showPrivacyDialog(activity);
                        }
                        obj2 = 1;
                        obj3 = null;
                    } else if ("onPause".equalsIgnoreCase(str) && (obj instanceof Activity)) {
                        storageManager.mo15051a("onPauseTime", new Date().getTime());
                        C1843h javascriptProcessor = Ensighten.getJavascriptProcessor();
                        if (javascriptProcessor.f5895e) {
                            if (Ensighten.getConfigurationManager().f5875a.f5861s) {
                                if (javascriptProcessor.f5892b == null || !javascriptProcessor.f5892b.isAlive()) {
                                    javascriptProcessor.f5892b = new C18381();
                                    javascriptProcessor.f5892b.start();
                                } else if (C1845i.m7357d()) {
                                    C1845i.m7354c("Queue save called while previous save is in progress.");
                                }
                            } else if (C1845i.m7357d()) {
                                C1845i.m7350b("Not saving queue due to disabled persistent storage.");
                            }
                        }
                        if (c1750e.f5861s) {
                            CookieSyncManager.getInstance().sync();
                        }
                        Utils.hidePrivacyDialog();
                        if (this.f5957l != null) {
                            C1728T c1728t;
                            if (C1845i.m7357d()) {
                                String str2;
                                String str3 = "Video paused for activity %s. Shutting down the tracker for source %s.";
                                Object[] objArr2 = new Object[2];
                                objArr2[0] = Ensighten.getViewManager().f5781b;
                                c1728t = this.f5957l;
                                if (c1728t.f5750h == null) {
                                    str2 = "undefined";
                                } else {
                                    str2 = c1728t.f5750h.getClass().toString();
                                }
                                objArr2[1] = str2.toString();
                                C1845i.m7345a(String.format(str3, objArr2));
                            }
                            c1728t = this.f5957l;
                            if (c1728t.f5751i != null) {
                                c1728t.f5751i.f5722a.set(true);
                            }
                            c1728t.f5748f.sendMessage(C1728T.m7259a(c1728t.f5749g, "closed", ExploreByTouchHelper.INVALID_ID, ExploreByTouchHelper.INVALID_ID));
                            this.f5957l = null;
                        }
                        int obj32 = 1;
                        obj2 = null;
                    } else {
                        if (str.equals("onRestart") && (obj instanceof Activity)) {
                            Date date = new Date();
                            time = date.getTime();
                            int a = storageManager.mo15049a(date);
                            storageManager.mo15051a("lastUseDate", time);
                            storageManager.mo15051a("previousSessionLength", (long) a);
                            storageManager.mo15050a();
                            obj2 = null;
                            obj32 = null;
                        } else {
                            if (str.equals("dispatchTouchEvent") && (obj instanceof Activity)) {
                                if (C1845i.m7357d()) {
                                    C1845i.m7345a(String.format("Proxying dispatchTouchEvent via Ensighten from class %s.", new Object[]{getClass().getSimpleName()}));
                                }
                                EnsightenGestureRecognizerFactory.getFourFingerGestureRecognizer().process((MotionEvent) objArr[0]);
                            }
                            obj2 = null;
                            obj32 = null;
                        }
                    }
                    if (obj2 != null) {
                        Ensighten.getInstance().mo15027a();
                    }
                    if (c1750e.mo15108a() && c1750e.f5864v) {
                        JSONObject jSONObject = c1750e.f5844b;
                        if (jSONObject.has("debugMode") && jSONObject.getBoolean("debugMode")) {
                            Ensighten.getJavascriptProcessor().mo15488c(String.format("%s('%s','%s','%s');", new Object[]{"ensightenDebug", obj, str, objArr}));
                        } else if (!Ensighten.isAdminMode() || obj32 == null) {
                            if (!(Ensighten.isAdminMode() || c1750e.f5856n)) {
                                JSONArray a2 = this.f5954i.mo15503a(str, objArr, name);
                                if (a2 != null) {
                                    m7395a(a2, obj, str, objArr, z);
                                }
                            }
                        } else if (obj instanceof Activity) {
                            Utils.takeScreenShot(((Activity) obj).getWindow().getDecorView().findViewById(16908290));
                        }
                    }
                    if (C1846j.m7369a() && Looper.myLooper() == Looper.getMainLooper()) {
                        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                        C1846j.m7366a(String.format("\tEvent evaluation of %s.%s took %d.", new Object[]{name, str, Long.valueOf(currentTimeMillis2)}));
                    }
                    m7397d();
                    return;
                }
            } catch (Exception e) {
                if (C1845i.m7357d()) {
                    C1845i.m7353c(e);
                }
                m7397d();
                return;
            } catch (Throwable th2) {
                m7397d();
                throw th2;
            }
        }
        if (C1845i.m7357d()) {
            C1845i.m7345a("The class or method is null during event evaluation.");
        }
        m7397d();
    }

    /* renamed from: a */
    public void mo15496a(C1698H c1698h) {
        C1750e c1750e = Ensighten.getConfigurationManager().f5875a;
        try {
            String str;
            JSONObject jSONObject;
            C1699G c1699g = (C1699G) c1698h;
            String a = c1698h.mo15029a();
            String str2 = c1699g.f5631b;
            String str3 = c1699g.f5630a;
            if (str3 != null) {
                if (str3.equals("seekComplete")) {
                    Ensighten.getJavascriptProcessor().mo15488c("Bootstrapper.onSeekComplete();");
                } else if (str3.equals("seekStart")) {
                    Ensighten.getJavascriptProcessor().mo15488c("Bootstrapper.onSeekStart();");
                } else if (str3.equals("chapterComplete")) {
                    Ensighten.getJavascriptProcessor().mo15488c("Bootstrapper.onChapterComplete();");
                } else if (str3.equals("chapterStart")) {
                    Ensighten.getJavascriptProcessor().mo15488c("Bootstrapper.onChapterComplete();");
                } else if (str3.equals("adComplete")) {
                    Ensighten.getJavascriptProcessor().mo15488c("Bootstrapper.onAdComplete();");
                } else if (str3.equals("adStart")) {
                    Ensighten.getJavascriptProcessor().mo15488c("Bootstrapper.onAdStart();");
                } else if (str3.equals("pause")) {
                    Ensighten.getJavascriptProcessor().mo15488c("Bootstrapper.onVideoPause();");
                } else if (str3.equals("resume")) {
                    Ensighten.getJavascriptProcessor().mo15488c("Bootstrapper.onVideoPlay();");
                } else if (str3.equals("complete")) {
                    Ensighten.getJavascriptProcessor().mo15488c("Bootstrapper.onVideoComplete();  Bootstrapper.onVideoUnload();");
                } else if (str3.equals("videoLoad")) {
                    Ensighten.getJavascriptProcessor().mo15488c("Bootstrapper.onVideoLoad();");
                }
            }
            if (str3.indexOf(":") != -1) {
                str = str3.split(":")[0];
            } else {
                str = str3;
            }
            String str4 = a + "|" + str2 + "|" + str;
            JSONArray jSONArray = (JSONArray) this.f5954i.f5960b.get(str4);
            Map map = (Map) c1750e.f5851i.get(a);
            JSONObject jSONObject2 = c1750e.f5844b;
            jSONObject2 = jSONObject2.has("notifications") ? jSONObject2.getJSONObject("notifications") : null;
            if (jSONObject2.has("MoviePlayback")) {
                jSONObject = jSONObject2.getJSONObject("MoviePlayback");
            } else {
                jSONObject = null;
            }
            if (!(jSONArray != null || map == null || jSONObject == null)) {
                if (jSONObject.has(str2)) {
                    jSONObject2 = jSONObject.getJSONObject(str2);
                } else {
                    jSONObject2 = null;
                }
                if (jSONObject2 == null || !jSONObject2.has(str)) {
                    jSONObject2 = null;
                } else {
                    jSONObject2 = jSONObject2.getJSONObject(str);
                }
                if (jSONObject2 == null) {
                    JSONObject jSONObject3 = jSONObject2;
                    for (String str5 : map.keySet()) {
                        if (((Pattern) map.get(str5)).matcher(str2).matches()) {
                            if (jSONObject.has(str5)) {
                                jSONObject2 = jSONObject.getJSONObject(str5);
                            } else {
                                jSONObject2 = null;
                            }
                            if (jSONObject2 == null || !jSONObject2.has(str)) {
                                jSONObject2 = null;
                            } else {
                                jSONObject2 = jSONObject2.getJSONObject(str);
                            }
                            if (jSONObject2 != null) {
                                break;
                            }
                        } else {
                            jSONObject2 = jSONObject3;
                        }
                        jSONObject3 = jSONObject2;
                    }
                    jSONObject2 = jSONObject3;
                }
                if (jSONObject2 != null && jSONObject2.has("rules")) {
                    jSONArray = jSONObject2.getJSONArray("rules");
                    if (C1845i.m7357d()) {
                        C1845i.m7345a(String.format("Adding notification rule to queue with key %d.", new Object[]{str4}));
                    }
                    this.f5954i.f5960b.put(str4, jSONArray);
                }
            }
            if (jSONArray != null) {
                mo15500a(jSONArray, new Object(), "undefined", new Object[]{c1699g.f5631b, c1699g.f5630a, Integer.valueOf(c1699g.f5632c), Integer.valueOf(c1699g.f5633d)});
                return;
            }
            if (C1845i.m7357d()) {
                C1845i.m7345a(String.format("Adding empty notification rule to queue with key %d.", new Object[]{str4}));
            }
            this.f5954i.f5960b.put(str4, C1743a.f5830b);
        } catch (Exception e) {
            if (C1845i.m7357d() && c1750e.f5865w) {
                C1845i.m7349b(e);
            }
        }
    }

    /* renamed from: b */
    public void mo15501b(C1698H c1698h) {
        try {
            JSONObject jSONObject;
            C1864y c1864y = (C1864y) c1698h;
            String str = c1864y.f6006a;
            String str2 = c1864y.f6007b;
            String str3 = c1698h.mo15029a() + "|" + str2;
            JSONArray jSONArray = (JSONArray) this.f5954i.f5960b.get(str3);
            C1750e c1750e = Ensighten.getConfigurationManager().f5875a;
            Map map = (Map) c1750e.f5851i.get(str);
            JSONObject jSONObject2 = c1750e.f5844b;
            jSONObject2 = jSONObject2.has("notifications") ? jSONObject2.getJSONObject("notifications") : null;
            if (jSONObject2.has("Ad")) {
                jSONObject = jSONObject2.getJSONObject("Ad");
            } else {
                jSONObject = null;
            }
            if (jSONArray == null && jSONObject != null) {
                if (jSONObject.has(str2)) {
                    jSONObject2 = jSONObject.getJSONObject(str2);
                } else {
                    jSONObject2 = null;
                }
                if (jSONObject2 == null) {
                    JSONObject jSONObject3 = jSONObject2;
                    for (String str4 : map.keySet()) {
                        if (((Pattern) map.get(str4)).matcher(str2).matches()) {
                            if (jSONObject.has(str4)) {
                                jSONObject2 = jSONObject.getJSONObject(str4);
                            } else {
                                jSONObject2 = null;
                            }
                            if (jSONObject2 != null) {
                                break;
                            }
                        } else {
                            jSONObject2 = jSONObject3;
                        }
                        jSONObject3 = jSONObject2;
                    }
                    jSONObject2 = jSONObject3;
                }
                if (jSONObject2 != null && jSONObject2.has("rules")) {
                    jSONArray = jSONObject2.getJSONArray("rules");
                    if (C1845i.m7357d()) {
                        C1845i.m7345a(String.format("Inserted notification rule %s into cache.", new Object[]{str3}));
                    }
                    this.f5954i.f5960b.put(str3, jSONArray);
                }
            }
            if (jSONArray != null) {
                mo15500a(jSONArray, new Object(), "undefined", new Object[]{c1864y.f6007b});
                return;
            }
            if (C1845i.m7357d()) {
                C1845i.m7345a(String.format("Inserted empty notification rule %s into cache.", new Object[]{str3}));
            }
            this.f5954i.f5960b.put(str3, C1743a.f5830b);
        } catch (Exception e) {
            if (C1845i.m7357d()) {
                C1845i.m7349b(e);
            }
        }
    }

    /* renamed from: a */
    private boolean m7396a(String str, String str2) {
        return str.equals(this.f5955j) && str2.equals(this.f5956k);
    }

    /* renamed from: d */
    private void m7397d() {
        this.f5955j = null;
        this.f5956k = null;
    }

    /* renamed from: a */
    public final void mo15500a(JSONArray jSONArray, Object obj, String str, Object[] objArr) {
        m7395a(jSONArray, obj, str, objArr, false);
    }

    /* renamed from: a */
    private void m7395a(JSONArray jSONArray, Object obj, String str, Object[] objArr, boolean z) {
        C1750e c1750e = Ensighten.getConfigurationManager().f5875a;
        if (c1750e.f5856n || c1750e.f5867y) {
            if (C1845i.m7357d()) {
                C1845i.m7345a(String.format("Skipped processing rules: isPrivacyMode: %b batteryKillEnabled: %b", new Object[]{Boolean.valueOf(c1750e.f5856n), Boolean.valueOf(c1750e.f5867y)}));
            }
        } else if (jSONArray != null) {
            try {
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (jSONObject.getBoolean("enabled")) {
                        JSONArray jSONArray2;
                        this.f5948c = jSONObject.has("id") ? jSONObject.getString("id") : "";
                        if (C1845i.m7357d()) {
                            C1845i.m7350b(String.format("Processing rule %s.", new Object[]{this.f5948c}));
                        }
                        if (jSONObject.has("criteria")) {
                            jSONArray2 = jSONObject.getJSONArray("criteria");
                        } else {
                            jSONArray2 = null;
                        }
                        JSONArray jSONArray3 = jSONObject.getJSONArray(MapTilsCacheAndResManager.AUTONAVI_DATA_PATH);
                        C1693E instructionProcessor = Ensighten.getInstructionProcessor();
                        String str2;
                        if (jSONArray2 == null || jSONArray2.length() <= 0) {
                            str2 = null;
                        } else {
                            if (C1845i.m7357d()) {
                                C1845i.m7350b("Processing criteria");
                            }
                            instructionProcessor.mo15021a(objArr);
                            instructionProcessor.f5605e = obj;
                            instructionProcessor.f5606f = str;
                            str2 = instructionProcessor.mo15020a(Utils.jsonRulesToArrayList(jSONArray2), z);
                            if (C1845i.m7357d()) {
                                C1845i.m7350b(String.format("Criteria return value is %s.", new Object[]{str2}));
                            }
                        }
                        if ((jSONArray2 == null || jSONArray2.length() == 0 || str2.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) && jSONArray3 != null && jSONArray3.length() > 0) {
                            instructionProcessor.mo15021a(objArr);
                            instructionProcessor.f5605e = obj;
                            instructionProcessor.f5606f = str;
                            String a = instructionProcessor.mo15020a(Utils.jsonRulesToArrayList(jSONArray3), z);
                            if (C1845i.m7357d()) {
                                C1845i.m7350b(String.format("Instructions return value is %s.", new Object[]{a}));
                            }
                        }
                    }
                }
            } catch (Exception e) {
                if (C1845i.m7357d()) {
                    C1845i.m7353c(e);
                }
            }
        }
    }

    /* renamed from: c */
    public final void mo15502c() {
        C1750e c1750e = Ensighten.getConfigurationManager().f5875a;
        if (c1750e.f5868z && this.f5951f != null) {
            if (this.f5951f.f5987a < Math.max(c1750e.f5842B, c1750e.f5842B)) {
                c1750e.f5867y = true;
            } else {
                c1750e.f5867y = false;
            }
        }
    }

    /* renamed from: a */
    public static List<C1721R> m7393a(String str) {
        ArrayList arrayList = new ArrayList();
        C1750e c1750e = Ensighten.getConfigurationManager().f5875a;
        try {
            Map map = (Map) c1750e.f5851i.get("MoviePlayback");
            if (!(map == null || map.isEmpty())) {
                for (Entry entry : map.entrySet()) {
                    if (((Pattern) entry.getValue()).matcher(str).matches()) {
                        JSONArray jSONArray = (JSONArray) c1750e.f5852j.get(entry.getKey());
                        if (jSONArray != null) {
                            for (int i = 0; i < jSONArray.length(); i++) {
                                arrayList.add(new C1721R(jSONArray.getInt(i)));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (C1845i.m7357d()) {
                C1845i.m7351b("The JSON format for keyframes in the configuration was invalid.", e);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private void m7394a(Activity activity) {
        synchronized (this.f5950e) {
            for (C1753a a : this.f5950e) {
                a.mo15111a();
            }
        }
    }
}
