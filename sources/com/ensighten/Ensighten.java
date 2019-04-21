package com.ensighten;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.p001v7.app.AlertDialog.Builder;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout.LayoutParams;
import com.ensighten.C1742X.C1740b;
import com.ensighten.C1742X.C1741c;
import com.ensighten.C1746c.C1745a;
import com.ensighten.C1755f.C1696a;
import com.ensighten.C1843h.C18392;
import com.ensighten.C1843h.C1841a;
import com.ensighten.C1845i.C1844a;
import com.ensighten.exception.ExceptionManager;
import com.ensighten.exception.ExceptionManager.C17511;
import com.ensighten.exception.ExceptionManager.C17522;
import com.kochava.base.InstallReferrer;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import dalvik.system.DexFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Ensighten implements OnClickListener, C1696a {
    /* renamed from: a */
    private Context f5614a;
    /* renamed from: b */
    private C1755f f5615b;
    /* renamed from: c */
    private C1849k f5616c;
    /* renamed from: d */
    private C1850l f5617d;
    /* renamed from: e */
    private ExceptionManager f5618e;
    /* renamed from: f */
    private C1843h f5619f;
    /* renamed from: g */
    private C1693E f5620g;
    /* renamed from: h */
    private C1712M f5621h;
    /* renamed from: i */
    private C1714N f5622i;
    /* renamed from: j */
    private C1735W f5623j;
    /* renamed from: k */
    private C1742X f5624k;
    /* renamed from: l */
    private String f5625l;
    /* renamed from: m */
    private String f5626m;
    /* renamed from: n */
    private boolean f5627n;
    /* renamed from: o */
    private boolean f5628o;
    /* renamed from: p */
    private boolean f5629p;

    /* renamed from: com.ensighten.Ensighten$a */
    static class C1695a {
        /* renamed from: a */
        public static final Ensighten f5613a = new Ensighten();
    }

    /* synthetic */ Ensighten(byte b) {
        this();
    }

    public static Ensighten getInstance() {
        return C1695a.f5613a;
    }

    private Ensighten() {
        this.f5627n = false;
        this.f5628o = false;
        this.f5629p = false;
    }

    public static void bootstrap(Context context, String clientId, String appId) {
        bootstrap(context, clientId, appId, false);
    }

    public static void bootstrap(Context context, String clientId, String appId, boolean enableCrashReporting) {
        if (context != null) {
            if (C1845i.m7348a()) {
                C1845i.m7345a("Bootstrap...");
            }
            if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                getInstance().m7198a(context, clientId, appId, enableCrashReporting);
                return;
            }
            final ReentrantLock reentrantLock = new ReentrantLock();
            final Condition newCondition = reentrantLock.newCondition();
            reentrantLock.lock();
            final Context context2 = context;
            final String str = clientId;
            final String str2 = appId;
            final boolean z = enableCrashReporting;
            new Handler(context.getMainLooper()).post(new Runnable() {
                public final void run() {
                    reentrantLock.lock();
                    Ensighten.getInstance().m7198a(context2, str, str2, z);
                    newCondition.signal();
                    reentrantLock.unlock();
                }
            });
            try {
                newCondition.await(1000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
            }
            reentrantLock.unlock();
        }
    }

    @SuppressLint({"SetJavaScriptEnabled", "InlinedApi"})
    /* renamed from: a */
    private synchronized void m7198a(Context context, String str, String str2, boolean z) {
        if (!this.f5627n) {
            C1845i.m7345a(C1844a.f5902a);
            try {
                this.f5614a = context.getApplicationContext();
                this.f5625l = str;
                this.f5626m = str2;
                this.f5615b = new C1755f(this.f5614a);
                this.f5620g = C1745a.f5835a.mo15101a();
                this.f5616c = new C1849k(this.f5614a);
                this.f5617d = new C1850l(this.f5614a);
                this.f5618e = new ExceptionManager(this.f5614a);
                this.f5619f = new C1843h(this.f5614a);
                this.f5622i = new C1714N(this.f5614a);
                this.f5623j = new C1735W(this.f5614a);
                this.f5624k = new C1742X(this.f5614a);
                C1740b c1740b = this.f5615b;
                getWebManager().mo15096a((C1741c) c1740b);
                getWebManager().mo15095a(c1740b);
                c1740b.mo15113a();
                getWebManager().mo15096a(this.f5619f);
                C1841a c1841a = this.f5617d;
                getInstance();
                getConfigurationManager().mo15114a((C1696a) c1841a);
                getInstance();
                getJavascriptProcessor().mo15484a(c1841a);
                C1742X c1742x = this.f5624k;
                getInstance();
                String clientId = getClientId();
                String appId = getAppId();
                c1742x.f5818h = Utils.buildConfigURL(clientId, appId, getStorageManager().f5696d.f6012a, getConfigurationManager().f5875a.f5845c, Version.getLabel(), String.valueOf(isAdminMode()), String.valueOf(isTestMode()));
                c1742x.f5813c = new C1685A(getInstructionProcessor());
                c1742x.f5812b = new WebView(c1742x.f5811a);
                c1742x.f5812b.setLayoutParams(new LayoutParams(0, 0));
                WebSettings settings = c1742x.f5812b.getSettings();
                settings.setJavaScriptEnabled(true);
                if (VERSION.SDK_INT >= 21) {
                    settings.setMixedContentMode(0);
                }
                settings.setDomStorageEnabled(true);
                c1742x.f5814d = new C1686B();
                c1742x.f5812b.setWebViewClient(c1742x.f5814d);
                c1742x.f5812b.setWebChromeClient(c1742x.f5813c);
                c1742x.f5819i = Utils.buildTagContainerURL(clientId, appId, getConfigurationManager().f5875a.f5846d, Version.getLabel(), String.valueOf(isAdminMode()), String.valueOf(isTestMode()));
                if (C1845i.m7365l()) {
                    C1845i.m7350b(String.format("The url of the tag container is %s.", new Object[]{c1742x.f5819i}));
                }
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) c1742x.f5811a.getSystemService("connectivity")).getActiveNetworkInfo();
                Object obj = (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) ? null : 1;
                if (obj != null) {
                    c1742x.f5820j = true;
                    c1742x.f5817g = true;
                    c1742x.mo15100c();
                    c1742x.mo15098a(true);
                }
                if (c1742x.f5811a instanceof Activity) {
                    c1742x.mo15099b();
                }
                try {
                    InputStream open = c1742x.f5811a.getAssets().open("ensighten.json");
                    StringWriter stringWriter = new StringWriter();
                    Utils.copy(new InputStreamReader(open), stringWriter);
                    if (C1845i.m7365l()) {
                        C1845i.m7345a(String.format("Parsed embedded config with content %s.", new Object[]{stringWriter.toString()}));
                    }
                    c1742x.mo15097a(JSONObjectInstrumentation.init(stringWriter.toString()), C1756g.f5878b);
                    c1742x.mo15094a();
                } catch (FileNotFoundException e) {
                } catch (Exception e2) {
                    if (C1845i.m7365l()) {
                        C1845i.m7353c(e2);
                    }
                }
                this.f5621h = new C1712M(this.f5614a, this.f5622i.f5695c);
                mo15027a();
                if (z) {
                    getExceptionManager().mo15112b();
                }
                this.f5627n = true;
            } catch (Exception e22) {
                if (C1845i.m7348a()) {
                    C1845i.m7353c(e22);
                }
            }
        } else if (C1845i.m7348a()) {
            C1845i.m7345a("Attempting to start Ensighten when it is already running.");
        }
    }

    /* renamed from: a */
    public final void mo15027a() {
        Activity c = getViewManager().mo15088c();
        if (c != null) {
            Intent intent = c.getIntent();
            if (intent != null && "android.intent.action.VIEW".equalsIgnoreCase(intent.getAction())) {
                Uri data = intent.getData();
                if (C1845i.m7357d()) {
                    String str = "Launch URI is %s.";
                    Object[] objArr = new Object[1];
                    objArr[0] = data == null ? "" : data.toString();
                    C1845i.m7350b(String.format(str, objArr));
                }
                if (data != null && "ensighten".equalsIgnoreCase(data.getScheme())) {
                    try {
                        this.f5628o = Boolean.parseBoolean(data.getQueryParameter("adminMode"));
                        this.f5629p = Boolean.parseBoolean(data.getQueryParameter("testMode"));
                    } catch (Throwable th) {
                    }
                    if (this.f5628o) {
                        if (C1845i.m7357d()) {
                            C1845i.m7350b("Administrator mode enabled.");
                        }
                        Utils.showAdminNotification(c);
                    }
                }
            }
        }
        if (C1845i.m7348a()) {
            C1845i.m7345a(String.format("Ensighten AdminMode: %b TestMode: %b.", new Object[]{Boolean.valueOf(isAdminMode()), Boolean.valueOf(isTestMode())}));
        }
    }

    public void onClick(View v) {
        if (this.f5627n) {
            Object tag = v.getTag();
            if (tag != null && (tag instanceof String)) {
                String str = (String) tag;
                if (str.indexOf("com.ensighten.privacy") == 0) {
                    this.f5615b.f5875a.f5857o = true;
                    this.f5615b.f5875a.f5856n = str.equals("com.ensighten.privacy.dialog.btn.yes");
                    if (C1845i.m7348a()) {
                        C1845i.m7350b(String.format("Set privacy mode to %b.", new Object[]{Boolean.valueOf(r0)}));
                    }
                    Utils.hidePrivacyDialog();
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo15024a(C1750e c1750e) {
    }

    /* renamed from: b */
    public final void mo15026b() {
    }

    /* renamed from: a */
    public final void mo15025a(Class<?> cls) {
        Ensighten instance = getInstance();
        if (instance.f5627n) {
            C1745a.f5835a.f5837b = cls;
            instance.f5620g = C1745a.f5835a.mo15101a();
        }
    }

    public static Context getContext() {
        return getInstance().f5614a;
    }

    public static String getAppId() {
        return getInstance().f5626m;
    }

    public static String getClientId() {
        return getInstance().f5625l;
    }

    public static boolean isAdminMode() {
        return getInstance().f5628o;
    }

    public static boolean isTestMode() {
        return getInstance().f5629p;
    }

    public static C1755f getConfigurationManager() {
        return getInstance().f5615b;
    }

    public static C1849k getDumpManager() {
        return getInstance().f5616c;
    }

    public static ExceptionManager getExceptionManager() {
        return getInstance().f5618e;
    }

    public static C1850l getEventManager() {
        return getInstance().f5617d;
    }

    public static C1843h getJavascriptProcessor() {
        return getInstance().f5619f;
    }

    public static C1693E getInstructionProcessor() {
        return getInstance().f5620g;
    }

    public static C1712M getOptimizationManager() {
        return getInstance().f5621h;
    }

    public static C1714N getStorageManager() {
        return getInstance().f5622i;
    }

    public static C1735W getViewManager() {
        return getInstance().f5623j;
    }

    public static C1742X getWebManager() {
        return getInstance().f5624k;
    }

    public static boolean getPersistentStoreEnabled() {
        if (getInstance().f5627n) {
            return getConfigurationManager().f5875a.f5861s;
        }
        return false;
    }

    public static String getConfigVersion() {
        if (getInstance().f5627n) {
            return getConfigurationManager().f5875a.f5846d;
        }
        return "";
    }

    public static void setPrivacyDialog(Dialog privacyDialog) {
        if (getInstance().f5627n) {
            getConfigurationManager().f5875a.f5858p = privacyDialog;
        }
    }

    public static Dialog getPrivacyDialog() {
        if (getInstance().f5627n) {
            return getConfigurationManager().f5875a.f5858p;
        }
        return null;
    }

    public static void parseConfig(JSONObject config) {
        if (getInstance().f5627n) {
            getConfigurationManager().mo15116a(config, 0);
        }
    }

    public static String getPrivacyStatement() {
        if (getInstance().f5627n) {
            return getConfigurationManager().f5875a.f5859q;
        }
        return "";
    }

    public static boolean isPrivacyMode() {
        if (getInstance().f5627n) {
            return getConfigurationManager().f5875a.f5856n;
        }
        return false;
    }

    public static void setBatteryKillEnabled(boolean batteryKillEnabled) {
        if (getInstance().f5627n) {
            getConfigurationManager().f5875a.f5867y = batteryKillEnabled;
        }
    }

    public static boolean isBatteryKillEnabled() {
        if (getInstance().f5627n) {
            return getConfigurationManager().f5875a.f5867y;
        }
        return false;
    }

    public static boolean isBatteryWatchEnabled() {
        if (getInstance().f5627n) {
            return getConfigurationManager().f5875a.f5868z;
        }
        return false;
    }

    public static int getBatteryWatchThreshold() {
        if (!getInstance().f5627n) {
            return 0;
        }
        C1750e c1750e = getConfigurationManager().f5875a;
        return Math.max(c1750e.f5841A, c1750e.f5842B);
    }

    public static void setDebugEnabled(boolean enableLogging) {
        C1845i.m7347a(enableLogging);
    }

    public static void onFrameReceived(int frameId) {
        if (getInstance().f5627n) {
            C1849k dumpManager = getDumpManager();
            if (frameId >= 0) {
                dumpManager.f5935h = frameId;
            }
        }
    }

    public static void sendClassFullDumpObjectToSocket() {
        if (getInstance().f5627n) {
            String str;
            C1849k dumpManager = getDumpManager();
            JSONObject jSONObject = new JSONObject();
            try {
                Enumeration entries = new DexFile(dumpManager.f5928a.getPackageCodePath()).entries();
                while (entries.hasMoreElements()) {
                    str = (String) entries.nextElement();
                    if (!(str.startsWith("android.") || str.startsWith("com.ensighten.") || str.startsWith("com.google.") || str.startsWith("org.acra.") || str.endsWith(".BuildConfig") || str.matches(".*\\.R\\$?.*"))) {
                        JSONObject jSONObject2 = new JSONObject();
                        JSONArray jSONArray = new JSONArray();
                        try {
                            for (Method method : Class.forName(str).getDeclaredMethods()) {
                                String name = method.getName();
                                if (!(name.startsWith("access$") || method.isBridge())) {
                                    try {
                                        StringBuilder stringBuilder = new StringBuilder(method.toString());
                                        int indexOf = stringBuilder.indexOf(" throws ");
                                        if (indexOf != -1) {
                                            stringBuilder.delete(indexOf, stringBuilder.length());
                                        }
                                        indexOf = stringBuilder.lastIndexOf(name + "(");
                                        if (indexOf != -1) {
                                            stringBuilder.delete(0, indexOf);
                                        }
                                        jSONArray.put(stringBuilder);
                                    } catch (StringIndexOutOfBoundsException e) {
                                        if (C1845i.m7355c()) {
                                            C1845i.m7349b(e);
                                        }
                                    }
                                }
                            }
                        } catch (ClassNotFoundException e2) {
                            if (C1845i.m7355c()) {
                                C1845i.m7349b(e2);
                            }
                        } catch (NoClassDefFoundError e3) {
                            if (C1845i.m7355c()) {
                                C1845i.m7354c(e3.getMessage());
                            }
                        } catch (NoSuchFieldError e4) {
                            if (C1845i.m7355c()) {
                                C1845i.m7354c(e4.getMessage());
                            }
                        } catch (NoSuchMethodError e5) {
                            if (C1845i.m7355c()) {
                                C1845i.m7354c(e5.getMessage());
                            }
                        } catch (LinkageError e6) {
                            if (C1845i.m7355c()) {
                                C1845i.m7354c(e6.getMessage());
                            }
                        }
                        jSONObject2.put("methods", jSONArray);
                        jSONObject.put(str, jSONObject2);
                    }
                }
            } catch (IOException e7) {
                if (C1845i.m7355c()) {
                    C1845i.m7349b(e7);
                }
            } catch (JSONException e72) {
                if (C1845i.m7355c()) {
                    C1845i.m7349b(e72);
                }
            }
            str = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
            getJavascriptProcessor().mo15487b(String.format("socket.sendMessage('class.fullDump.receipt',%s);", new Object[]{str}));
        }
    }

    public static void sendTracerObjectToSocket() {
        if (getInstance().f5627n) {
            C1849k dumpManager = getDumpManager();
            C1693E instructionProcessor = getInstructionProcessor();
            Object obj = instructionProcessor.f5605e;
            String name = obj.getClass().getName();
            String str = instructionProcessor.f5606f;
            Object[] a = instructionProcessor.mo15022a();
            JSONObject jSONObject = new JSONObject();
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(name, dumpManager.mo15493a(obj));
                jSONObject.put("class", jSONObject2);
                jSONObject.put("method", str);
                if (a != null) {
                    for (int i = 0; i < a.length; i++) {
                        JSONObject jSONObject3 = new JSONObject();
                        Object obj2 = a[i];
                        jSONObject3.put(obj2.getClass().getName(), dumpManager.mo15493a(obj2));
                        jSONObject.put("arg" + i, jSONObject3);
                    }
                }
            } catch (JSONException e) {
                if (C1845i.m7355c()) {
                    C1845i.m7353c(e);
                }
            }
            C1843h javascriptProcessor = getJavascriptProcessor();
            String str2 = "socket.sendMessage('tracer.receipt',%s);";
            Object[] objArr = new Object[1];
            objArr[0] = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
            javascriptProcessor.mo15487b(String.format(str2, objArr));
        }
    }

    public static void addCustomDumpPoint(String className, String eventValue) {
        if (getInstance().f5627n) {
            C1849k dumpManager = getDumpManager();
            if (className != null && eventValue != null) {
                dumpManager.f5934g.put(className, eventValue);
            }
        }
    }

    public static void setViewHierarchyInDumpEnabled(boolean enableViewHierarchyInDump) {
        if (getInstance().f5627n) {
            getDumpManager().f5931d = enableViewHierarchyInDump;
        }
    }

    public static void setImagesInDumpEnabled(boolean enableImagesInDump) {
        if (getInstance().f5627n) {
            getDumpManager().f5932e = enableImagesInDump;
        }
    }

    public static void setRealtimeDumpEnabled(boolean enableRealtimeDump) {
        if (getInstance().f5627n) {
            getDumpManager().f5933f = enableRealtimeDump;
        }
    }

    public static Activity getCurrentActivity() {
        if (getInstance().f5627n) {
            return getViewManager().mo15088c();
        }
        return null;
    }

    public static String getCurrentActivityName() {
        if (getInstance().f5627n) {
            return getViewManager().f5781b;
        }
        return null;
    }

    public static void evaluateEvent(Object source, String method, Object[] args) {
        if (getInstance().f5627n) {
            getEventManager().mo15498a(source, method, args);
        }
    }

    public static void evaluateEvent(Object source, Class<?> declaringType, String method, Object[] args) {
        if (getInstance().f5627n) {
            C1850l eventManager = getEventManager();
            if (source != null || declaringType == null) {
                eventManager.mo15498a(source, method, args);
            } else {
                eventManager.mo15497a((Class) declaringType, method, args);
            }
        }
    }

    public static void evaluateEvent(Object source, String declaringClassName, String method, Object[] args) {
        if (getInstance().f5627n) {
            C1850l eventManager = getEventManager();
            if (source != null || declaringClassName == null) {
                eventManager.mo15498a(source, method, args);
                return;
            }
            Class cls = null;
            try {
                cls = Class.forName(declaringClassName);
            } catch (ClassNotFoundException e) {
                if (C1845i.m7357d()) {
                    C1845i.m7345a(String.format("Unable to load class with name %s.", new Object[]{declaringClassName}));
                }
            }
            eventManager.mo15497a(cls, method, args);
        }
    }

    public static void evaluateVideoEvent(String videoName, String event, int position, int duration) {
        if (getInstance().f5627n) {
            getEventManager();
            if (videoName != null && event != null) {
                try {
                    getInstance();
                    JSONObject b = getStorageManager().mo15054b();
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("videoName", videoName);
                    jSONObject.put("event", event);
                    jSONObject.put("position", position);
                    jSONObject.put(InstallReferrer.KEY_DURATION, duration);
                    b.put("videoEvent", jSONObject);
                } catch (JSONException e) {
                    if (C1845i.m7357d()) {
                        C1845i.m7351b("Unable to create a video event in JSON.", e);
                    }
                }
            }
        }
    }

    public static void evaluateNotification(C1698H notification) {
        if (getInstance().f5627n) {
            C1850l eventManager = getEventManager();
            try {
                C1750e c1750e = getConfigurationManager().f5875a;
                if (!c1750e.mo15108a() || !c1750e.f5864v || c1750e.f5856n) {
                    return;
                }
                if (notification instanceof C1699G) {
                    eventManager.mo15496a(notification);
                } else if (c1750e.f5866x && (notification instanceof C1864y)) {
                    eventManager.mo15501b(notification);
                }
            } catch (Throwable th) {
                if (C1845i.m7357d()) {
                    C1845i.m7356d(th.toString());
                }
            }
        }
    }

    public static void setReferrerInfo(JSONObject referrerInfo) {
        if (getInstance().f5627n) {
            getEventManager().f5952g = referrerInfo;
        }
    }

    public static void processBatteryLevel() {
        if (getInstance().f5627n) {
            getEventManager().mo15502c();
        }
    }

    public static void setVideoHeartbeatEnabled(boolean videoHeartbeatEnabled) {
        if (getInstance().f5627n) {
            getEventManager().f5949d = videoHeartbeatEnabled;
        }
    }

    public static boolean isVideoHeartbeatEnabled() {
        if (getInstance().f5627n) {
            return getEventManager().f5949d;
        }
        return false;
    }

    public static void setVideoHeartbeatAdBreaks(SparseArray<ArrayList<C1729U>> videoHeartbeatAdBreaks) {
        if (getInstance().f5627n) {
            getEventManager().f5946a = videoHeartbeatAdBreaks;
        }
    }

    public static SparseArray<ArrayList<C1729U>> getVideoHeartbeatAdBreaks() {
        if (getInstance().f5627n) {
            return getEventManager().f5946a;
        }
        return null;
    }

    public static void setVideoHeartbeatChapters(SparseArray<ArrayList<C1729U>> videoHeartbeatChapters) {
        if (getInstance().f5627n) {
            getEventManager().f5947b = videoHeartbeatChapters;
        }
    }

    public static SparseArray<ArrayList<C1729U>> getVideoHeartbeatChapters() {
        if (getInstance().f5627n) {
            return getEventManager().f5947b;
        }
        return null;
    }

    public static List<C1721R> getVideoKeyframes(String videoName) {
        if (!getInstance().f5627n) {
            return null;
        }
        getEventManager();
        return C1850l.m7393a(videoName);
    }

    public static void enableCrashReporting() {
        if (getInstance().f5627n) {
            getExceptionManager().mo15112b();
        }
    }

    public static void isCrashReportingEnabled() {
        if (getInstance().f5627n) {
            getExceptionManager();
        }
    }

    public static void showCrashDialog() {
        if (getInstance().f5627n) {
            ExceptionManager exceptionManager = getExceptionManager();
            if (!exceptionManager.f5872b) {
                Builder message = new Builder(exceptionManager.f5871a).setTitle((CharSequence) "Preview Crash").setMessage((CharSequence) "Would you like to crash this app?");
                message.setPositiveButton(exceptionManager.f5871a.getString(17039370), new C17511());
                message.setNegativeButton(exceptionManager.f5871a.getString(17039360), null);
                message.setOnDismissListener(new C17522());
                message.create().show();
                exceptionManager.f5872b = true;
            }
        }
    }

    public static void evaluateJS(String js) {
        if (getInstance().f5627n) {
            getJavascriptProcessor().mo15488c(js);
        }
    }

    public static void evaluateSyncJS(String js) {
        if (getInstance().f5627n) {
            C1843h javascriptProcessor = getJavascriptProcessor();
            getInstance();
            C1700I c1700i = new C1700I(js, getEventManager().f5948c, new Date(), getStorageManager().mo15056c());
            javascriptProcessor.mo15489d(c1700i.f5635a);
            String a = c1700i.mo15030a();
            if (C1845i.m7357d()) {
                C1845i.m7350b(String.format("The gateway wrapper is %s.", new Object[]{a}));
            }
            if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                C1843h.m7332a(String.format("%s", new Object[]{a}));
                return;
            }
            ReentrantLock reentrantLock = new ReentrantLock();
            Condition newCondition = reentrantLock.newCondition();
            reentrantLock.lock();
            new Handler(javascriptProcessor.f5891a.getMainLooper()).post(new C18392(reentrantLock, a, newCondition));
            try {
                newCondition.await(1000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
            }
            reentrantLock.unlock();
        }
    }

    public static void callJSErrorHandler(JSONObject errorInfo) {
        if (getInstance().f5627n) {
            getJavascriptProcessor().mo15485a(errorInfo);
        }
    }

    public static void setInstrumentationEnabled(boolean enableInstrumentation) {
        C1846j.m7368a(enableInstrumentation);
    }

    public static void getChildViewReturnValue(View getChildViewResult, int groupPosition, int childPosition) {
        if (getInstance().f5627n) {
            C1712M optimizationManager = getOptimizationManager();
            optimizationManager.f5674b = getChildViewResult;
            optimizationManager.f5675c = childPosition;
            optimizationManager.f5677e = groupPosition;
        }
    }

    public static void getGroupViewReturnValue(View getGroupViewResult, int groupPosition) {
        if (getInstance().f5627n) {
            C1712M optimizationManager = getOptimizationManager();
            optimizationManager.f5676d = getGroupViewResult;
            optimizationManager.f5677e = groupPosition;
        }
    }

    public static boolean isOptimizationLifecycle(String lifecycleMethod) {
        if (lifecycleMethod.equals("onCreate") || lifecycleMethod.equals("onActivityCreated") || lifecycleMethod.equals("onStart") || lifecycleMethod.equals("onResume") || lifecycleMethod.equals("onPause")) {
            return true;
        }
        return false;
    }

    public static boolean isOptimizationEnabled() {
        if (getInstance().f5627n) {
            return getOptimizationManager().f5681i;
        }
        return false;
    }

    public static void toggleOptimization(boolean value) {
        if (getInstance().f5627n) {
            getOptimizationManager().f5681i = value;
        }
    }

    public static void clearConfiguration() {
        if (getInstance().f5627n) {
            getOptimizationManager().mo15047b();
        }
    }

    public static boolean addTestToClassAndEvent(String testJSONString, String className, String eventValue) {
        if (getInstance().f5627n) {
            return getOptimizationManager().mo15046a(testJSONString, className, eventValue);
        }
        return false;
    }

    public static void removeTest(String testName) {
        if (getInstance().f5627n) {
            C1712M optimizationManager = getOptimizationManager();
            String str = "removeTest";
            if (testName == null || optimizationManager.f5678f == null) {
                if (C1845i.m7362i()) {
                    C1845i.m7345a("The test was not removed due to one or more arguments being null.");
                }
            } else if (!isPrivacyMode() && !isBatteryKillEnabled()) {
                try {
                    int length = optimizationManager.f5678f.length();
                    Iterator keys = optimizationManager.f5678f.keys();
                    for (int i = length; i > 0; i--) {
                        String str2 = ((String) keys.next()).toString();
                        JSONObject jSONObject = optimizationManager.f5678f.getJSONObject(str2);
                        length = jSONObject.length();
                        Iterator keys2 = jSONObject.keys();
                        for (int i2 = length; i2 > 0; i2--) {
                            String str3 = ((String) keys2.next()).toString();
                            JSONObject jSONObject2 = jSONObject.getJSONObject(str3).getJSONObject("tests");
                            length = jSONObject2.length();
                            Iterator keys3 = jSONObject2.keys();
                            for (int i3 = length; i3 > 0; i3--) {
                                if (((String) keys3.next()).toString().equals(testName)) {
                                    jSONObject2.remove(testName);
                                    new StringBuilder().append(str2).append(".").append(str3).append("():").append(testName);
                                    if (C1845i.m7362i()) {
                                        C1845i.m7345a(String.format("Removed test %s from the optimization configuration.", new Object[]{testName}));
                                    }
                                    new StringBuilder().append(str).append(" test ").append(testName);
                                    optimizationManager.mo15044a();
                                    return;
                                }
                            }
                        }
                    }
                } catch (JSONException e) {
                    if (C1845i.m7362i()) {
                        C1845i.m7344a(e);
                    }
                }
            } else if (C1845i.m7362i()) {
                C1845i.m7345a("The test was not removed due to privacy mode or battery kill enabled.");
            }
        }
    }

    public static void setOptimizationDumpMode(boolean enabled) {
        if (getInstance().f5627n) {
            C1849k dumpManager = getDumpManager();
            dumpManager.f5930c = enabled;
            if (dumpManager.f5930c) {
                dumpManager.f5936i = 0;
                dumpManager.f5935h = 0;
                dumpManager.f5934g.clear();
                dumpManager.f5939l = false;
            }
            C1712M optimizationManager = getOptimizationManager();
            if (enabled) {
                optimizationManager.mo15047b();
            }
            if (enabled) {
                getViewManager().mo15084a();
            }
        }
    }

    public static String getUdid() {
        if (getInstance().f5627n) {
            return getStorageManager().f5694b;
        }
        return "";
    }

    public static String getVersion() {
        if (getInstance().f5627n) {
            return getStorageManager().f5696d.f6012a;
        }
        return null;
    }

    public static void saveStringToSharedPrefs(String key, String value) {
        if (getInstance().f5627n) {
            getStorageManager().mo15052a(key, value);
        }
    }

    public static String getStringFromSharedPrefsForKey(String key) {
        if (getInstance().f5627n) {
            return getStorageManager().mo15053b(key);
        }
        return "";
    }

    public static void getViewReturnValue(View getViewResult, int position) {
        if (getInstance().f5627n) {
            C1735W viewManager = getViewManager();
            viewManager.f5784e = getViewResult;
            viewManager.f5790k = position;
        }
    }

    public static void processView(Object source, String method) {
        if (getInstance().f5627n) {
            getViewManager().mo15086a(source, method);
        }
    }

    public static void processView(String className, String method) {
        if (getInstance().f5627n) {
            C1735W viewManager = getViewManager();
            if (!C1735W.m7282b()) {
                String currentActivityName = getCurrentActivityName();
                if (currentActivityName != null && currentActivityName.equals(className)) {
                    viewManager.mo15086a(getCurrentActivity(), method);
                } else if (viewManager.f5787h == null || !viewManager.f5787h.equals(className)) {
                    if (viewManager.f5783d != null && viewManager.f5783d.equals(className)) {
                        viewManager.mo15086a(viewManager.f5782c, method);
                    } else if (C1845i.m7364k()) {
                        C1845i.m7354c(String.format("Class %s was not found for view processing.", new Object[]{className}));
                    }
                } else if (viewManager.f5785f != null) {
                    viewManager.mo15086a(viewManager.f5785f, method);
                } else {
                    viewManager.mo15086a(viewManager.f5786g, method);
                }
            } else if (C1845i.m7364k()) {
                C1845i.m7345a("View processing is disabled.");
            }
        }
    }

    public static String[] getXPathAndClassNameForGeneratedResId(int resId, View view) {
        if (!getInstance().f5627n) {
            return null;
        }
        C1735W viewManager = getViewManager();
        String str = (String) view.getTag(-559039810);
        if (str == null || !viewManager.f5788i.containsKey(str) || ((String) ((Map) viewManager.f5788i.get(str)).get(Integer.valueOf(resId))) == null) {
            return null;
        }
        return new String[]{(String) ((Map) viewManager.f5788i.get(str)).get(Integer.valueOf(resId)), str};
    }

    public static String getXPathForGeneratedFragmentResId(int fragmentResId) {
        if (getInstance().f5627n) {
            return (String) getViewManager().f5789j.get(fragmentResId);
        }
        return "";
    }

    public static void restartActivity() {
        if (getInstance().f5627n) {
            getViewManager().mo15084a();
        }
    }

    public static void loadNetworkResources() {
        if (getInstance().f5627n) {
            getWebManager().mo15100c();
        }
    }

    public static void setWaitingForWebview(boolean value) {
        if (getInstance().f5627n) {
            getWebManager().f5815e = value;
        }
    }

    public static boolean getWaitingForWebview() {
        if (getInstance().f5627n) {
            return getWebManager().f5815e;
        }
        return false;
    }

    public static String getTagContainerUrl() {
        if (getInstance().f5627n) {
            return getWebManager().f5819i;
        }
        return "";
    }

    public static boolean isConnectionStatus() {
        if (getInstance().f5627n) {
            return getWebManager().f5820j;
        }
        return false;
    }

    public static void setConnectionStatus(boolean connectionStatus) {
        if (getInstance().f5627n) {
            getWebManager().f5820j = connectionStatus;
        }
    }

    public static void webviewLoaded() {
        if (getInstance().f5627n) {
            getWebManager().f5822l = true;
        }
    }

    public static void addPersistentCookie(String cookieName, String cookieValue) {
        if (getInstance().f5627n) {
            C1742X webManager = getWebManager();
            if (cookieName == null) {
                return;
            }
            if (!getConfigurationManager().f5875a.f5856n) {
                BasicClientCookie basicClientCookie = new BasicClientCookie(cookieName, cookieValue);
                basicClientCookie.setDomain("");
                webManager.f5823m.addCookie(basicClientCookie);
            } else if (C1845i.m7357d()) {
                C1845i.m7345a(String.format("Cookie %s was not stored because privacy mode is enabled.", new Object[]{cookieName}));
            }
        }
    }

    public static String getPersistentCookie(String videoName) {
        if (!getInstance().f5627n) {
            return null;
        }
        C1742X webManager = getWebManager();
        if (videoName != null) {
            if (!getConfigurationManager().f5875a.f5856n) {
                for (Cookie cookie : webManager.f5823m.getCookies()) {
                    if (cookie.getName().equals(videoName)) {
                        String value = cookie.getValue();
                        if (!C1845i.m7357d()) {
                            return value;
                        }
                        C1845i.m7345a(String.format("Cookie %s was found with value %s.", new Object[]{videoName, value}));
                        return value;
                    }
                }
                if (C1845i.m7357d()) {
                    C1845i.m7345a(String.format("Cookie %s was not found.", new Object[]{videoName}));
                }
            } else if (C1845i.m7357d()) {
                C1845i.m7345a(String.format("Cookie %s was not fetched because privacy mode is enabled.", new Object[]{videoName}));
            }
        }
        return null;
    }

    public static void removePersistentCookie(String cookieName) {
        if (getInstance().f5627n) {
            C1742X webManager = getWebManager();
            if (cookieName != null) {
                BasicClientCookie basicClientCookie = new BasicClientCookie(cookieName, null);
                Date date = new Date();
                basicClientCookie.setDomain("");
                basicClientCookie.setExpiryDate(date);
                webManager.f5823m.addCookie(basicClientCookie);
                Editor edit = webManager.f5811a.getSharedPreferences("CookiePrefsFile", 0).edit();
                edit.remove("cookie_" + cookieName);
                edit.commit();
            }
        }
    }
}
