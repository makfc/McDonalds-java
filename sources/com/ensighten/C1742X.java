package com.ensighten;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONObject;

/* renamed from: com.ensighten.X */
public final class C1742X {
    /* renamed from: a */
    public Context f5811a;
    /* renamed from: b */
    public WebView f5812b;
    /* renamed from: c */
    public WebChromeClient f5813c;
    /* renamed from: d */
    public C1686B f5814d;
    /* renamed from: e */
    public boolean f5815e = false;
    /* renamed from: f */
    boolean f5816f = false;
    /* renamed from: g */
    public boolean f5817g = false;
    /* renamed from: h */
    public String f5818h;
    /* renamed from: i */
    public String f5819i;
    /* renamed from: j */
    public boolean f5820j = false;
    /* renamed from: k */
    public boolean f5821k = false;
    /* renamed from: l */
    public boolean f5822l = false;
    /* renamed from: m */
    public C1858t f5823m;
    /* renamed from: n */
    public Set<C1740b> f5824n = new HashSet();
    /* renamed from: o */
    private Handler f5825o;
    /* renamed from: p */
    private C1856o f5826p;
    /* renamed from: q */
    private Set<C1741c> f5827q = new HashSet();
    /* renamed from: r */
    private C1739a f5828r = new C1739a(this, (byte) 0);

    /* renamed from: com.ensighten.X$1 */
    class C17361 extends BroadcastReceiver {
        C17361() {
        }

        public final void onReceive(Context context, Intent intent) {
            try {
                if (C1845i.m7365l()) {
                    C1845i.m7350b("The connection status has changed.");
                }
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                NetworkInfo networkInfo = connectivityManager == null ? null : connectivityManager.getNetworkInfo(1);
                NetworkInfo networkInfo2 = connectivityManager == null ? null : connectivityManager.getNetworkInfo(0);
                boolean z = C1742X.this.f5820j;
                if (networkInfo != null && networkInfo.isConnected()) {
                    C1742X.this.f5820j = true;
                } else if (networkInfo2 == null || !networkInfo2.isConnected()) {
                    C1742X.this.f5820j = false;
                } else {
                    C1742X.this.f5820j = true;
                }
                if (C1742X.this.f5820j && !z) {
                    C1742X.this.mo15100c();
                    C1742X.this.mo15098a(true);
                } else if (!C1742X.this.f5820j) {
                    C1742X.this.mo15098a(false);
                }
            } catch (Exception e) {
                if (C1845i.m7365l()) {
                    C1845i.m7353c(e);
                }
            }
        }
    }

    /* renamed from: com.ensighten.X$a */
    class C1739a implements Runnable {
        private C1739a() {
        }

        /* synthetic */ C1739a(C1742X c1742x, byte b) {
            this();
        }

        public final void run() {
            C1742X c1742x = C1742X.this;
            if (!Ensighten.getWebManager().f5820j) {
                c1742x.mo15094a();
            } else if (c1742x.f5820j) {
                if (C1845i.m7365l()) {
                    C1845i.m7345a("Refreshing configuration due to the refresh interval being exceeded.");
                }
                c1742x.f5817g = true;
                c1742x.mo15100c();
            }
        }
    }

    /* renamed from: com.ensighten.X$b */
    public interface C1740b {
        /* renamed from: b */
        void mo15092b(JSONObject jSONObject, int i);
    }

    /* renamed from: com.ensighten.X$c */
    public interface C1741c {
        /* renamed from: a */
        void mo15093a(boolean z);
    }

    public C1742X(Context context) {
        this.f5811a = context;
        this.f5825o = new Handler(context.getMainLooper());
        try {
            this.f5826p = new C1856o();
            CookieSyncManager.createInstance(this.f5811a);
            this.f5823m = new C1858t(this.f5811a);
        } catch (Exception e) {
            if (C1845i.m7352b()) {
                C1845i.m7353c(e);
            }
        }
    }

    /* renamed from: a */
    public void mo15094a() {
        long j = Ensighten.getConfigurationManager().f5875a.f5855m * 60000;
        this.f5825o.removeCallbacks(this.f5828r);
        this.f5825o.postDelayed(this.f5828r, j);
    }

    /* renamed from: b */
    public final void mo15099b() {
        this.f5811a.registerReceiver(new C17361(), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.f5821k = true;
    }

    /* renamed from: c */
    public final void mo15100c() {
        if (this.f5817g && !this.f5816f) {
            Ensighten.getInstance();
            C1714N storageManager = Ensighten.getStorageManager();
            final String clientId = Ensighten.getClientId();
            final String appId = Ensighten.getAppId();
            this.f5818h = Utils.buildConfigURL(clientId, appId, storageManager.f5696d.f6012a, Ensighten.getConfigurationManager().f5875a.f5845c, Version.getLabel(), String.valueOf(Ensighten.isAdminMode()), String.valueOf(Ensighten.isTestMode()));
            if (C1845i.m7365l()) {
                C1845i.m7350b(String.format("Downloading the configuration %s.", new Object[]{this.f5818h}));
            }
            this.f5826p.mo15510a(this.f5818h, new C1737s() {
                /* renamed from: a */
                public final void mo15090a(JSONObject jSONObject) {
                    if (C1845i.m7365l()) {
                        C1845i.m7350b("Downloaded the configuration successfully.");
                    }
                    C1742X.this.mo15097a(jSONObject, C1756g.f5879c);
                    if ((!C1742X.this.f5822l || C1742X.this.f5817g) && !C1742X.this.f5815e) {
                        C1742X.this.f5819i = Utils.buildTagContainerURL(clientId, appId, Ensighten.getConfigurationManager().f5875a.f5846d, Version.getLabel(), String.valueOf(Ensighten.isAdminMode()), String.valueOf(Ensighten.isTestMode()));
                        if (C1742X.this.f5819i != null) {
                            C1742X.this.f5812b.loadUrl(C1742X.this.f5819i);
                        }
                        C1742X.this.f5822l = false;
                        C1742X.this.f5815e = true;
                    }
                    C1742X.this.f5817g = false;
                    C1742X.this.f5816f = false;
                    C1742X.this.mo15097a(jSONObject, C1756g.f5879c);
                    C1742X.this.mo15094a();
                }

                /* renamed from: b */
                public final void mo15079b(Throwable th) {
                    if (C1845i.m7365l()) {
                        C1845i.m7350b("Error downloading the configuration.");
                    }
                    C1742X.this.f5816f = false;
                    C1742X.this.mo15097a(null, C1756g.f5880d);
                    C1742X.this.mo15094a();
                }
            });
            this.f5816f = true;
        }
    }

    /* renamed from: a */
    public final void mo15096a(C1741c c1741c) {
        synchronized (this.f5827q) {
            this.f5827q.add(c1741c);
        }
    }

    /* renamed from: a */
    public final void mo15098a(boolean z) {
        synchronized (this.f5827q) {
            for (C1741c a : this.f5827q) {
                a.mo15093a(z);
            }
        }
    }

    /* renamed from: a */
    public final void mo15095a(C1740b c1740b) {
        synchronized (this.f5824n) {
            this.f5824n.add(c1740b);
        }
    }

    /* renamed from: a */
    public final void mo15097a(JSONObject jSONObject, int i) {
        synchronized (this.f5824n) {
            for (C1740b b : this.f5824n) {
                b.mo15092b(jSONObject, i);
            }
        }
    }
}
