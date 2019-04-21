package com.threatmetrix.TrustDefender;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.location.Location;
import android.os.Looper;
import android.support.p000v4.media.session.PlaybackStateCompat;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.threatmetrix.TrustDefender.C4532g.C4518b;
import com.threatmetrix.TrustDefender.C4532g.C4518b.C4516a;
import com.threatmetrix.TrustDefender.C4532g.C4522f;
import com.threatmetrix.TrustDefender.C4532g.C4529m;
import com.threatmetrix.TrustDefender.C4543p.C4542b;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;

@TargetApi(9)
/* renamed from: com.threatmetrix.TrustDefender.ar */
class C4506ar {
    /* renamed from: Y */
    private static final String f7505Y = C4549w.m8585a(C4506ar.class);
    /* renamed from: a */
    public static String f7506a = "4.0-90";
    /* renamed from: A */
    String f7507A = null;
    /* renamed from: B */
    String f7508B = null;
    /* renamed from: C */
    String f7509C = null;
    /* renamed from: D */
    String f7510D = null;
    /* renamed from: E */
    String f7511E = null;
    /* renamed from: F */
    String f7512F = null;
    /* renamed from: G */
    String f7513G = "";
    /* renamed from: H */
    String f7514H = null;
    /* renamed from: I */
    String f7515I = null;
    /* renamed from: J */
    String f7516J = null;
    /* renamed from: K */
    String[] f7517K = null;
    /* renamed from: L */
    ArrayList<String> f7518L = null;
    /* renamed from: M */
    C4492aj f7519M = null;
    /* renamed from: N */
    Location f7520N = null;
    /* renamed from: O */
    Context f7521O = null;
    /* renamed from: P */
    C4483e f7522P = null;
    /* renamed from: Q */
    C4508c f7523Q = new C4508c();
    /* renamed from: R */
    C4551x f7524R = null;
    /* renamed from: S */
    long f7525S = 0;
    /* renamed from: T */
    int f7526T = 0;
    /* renamed from: U */
    long f7527U = 0;
    /* renamed from: V */
    volatile THMStatusCode f7528V = THMStatusCode.THM_OK;
    /* renamed from: W */
    volatile long f7529W = 0;
    /* renamed from: X */
    volatile long f7530X = 0;
    /* renamed from: b */
    int f7531b = 0;
    /* renamed from: c */
    int f7532c = 0;
    /* renamed from: d */
    int f7533d = 0;
    /* renamed from: e */
    int f7534e = 0;
    /* renamed from: f */
    int f7535f = 0;
    /* renamed from: g */
    int f7536g = 0;
    /* renamed from: h */
    AtomicLong f7537h = new AtomicLong(0);
    /* renamed from: i */
    long f7538i = 0;
    /* renamed from: j */
    long f7539j = 0;
    /* renamed from: k */
    long f7540k = 0;
    /* renamed from: l */
    boolean f7541l = false;
    /* renamed from: m */
    String f7542m = null;
    /* renamed from: n */
    String f7543n = null;
    /* renamed from: o */
    String f7544o = null;
    /* renamed from: p */
    String f7545p = null;
    /* renamed from: q */
    String f7546q = null;
    /* renamed from: r */
    String f7547r = null;
    /* renamed from: s */
    String f7548s = null;
    /* renamed from: t */
    String f7549t = null;
    /* renamed from: u */
    String f7550u = null;
    /* renamed from: v */
    String f7551v = null;
    /* renamed from: w */
    String f7552w = null;
    /* renamed from: x */
    String f7553x = null;
    /* renamed from: y */
    String f7554y = null;
    /* renamed from: z */
    String f7555z = null;

    C4506ar(String source) {
        this.f7513G = source;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo34183a() {
        this.f7545p = null;
        this.f7531b = 0;
        this.f7532c = 0;
        this.f7553x = null;
        this.f7520N = null;
        this.f7511E = null;
        this.f7512F = null;
        this.f7519M = null;
        this.f7538i = 0;
        this.f7539j = 0;
        this.f7540k = 0;
        this.f7524R = null;
        this.f7535f = 0;
        this.f7541l = false;
        this.f7514H = null;
        this.f7516J = null;
        this.f7533d = 0;
        this.f7534e = 0;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: b */
    public final void mo34196b() {
        this.f7526T++;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo34185a(long options) {
        this.f7525S = options;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo34190a(C4508c browserInfo) {
        this.f7523Q = browserInfo;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: c */
    public final String mo34198c() {
        return this.f7542m;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo34192a(String apikey) {
        this.f7508B = apikey;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: b */
    public final void mo34197b(String sessionID) {
        this.f7542m = C4491ai.m8350g(sessionID);
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo34194a(List<String> list) {
        if (list != null && !list.isEmpty()) {
            this.f7518L = new ArrayList(list);
        } else if (this.f7518L != null) {
            this.f7518L.clear();
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: d */
    public final THMStatusCode mo34200d() {
        C4549w.m8594c(f7505Y, "getStatus returns: " + this.f7528V.toString());
        return this.f7528V;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo34188a(THMStatusCode status) {
        this.f7528V = status;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo34187a(Location location, boolean isManualLocation) {
        if (C4522f.m8497b()) {
            this.f7520N = location;
            this.f7541l = isManualLocation;
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo34191a(C4483e state) {
        this.f7522P = state;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo34189a(C4492aj conf) {
        this.f7519M = conf;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: e */
    public final C4492aj mo34202e() {
        return this.f7519M;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo34186a(Context context) {
        this.f7521O = context;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: f */
    public final String mo34204f() {
        return this.f7555z;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: g */
    public final String mo34205g() {
        return this.f7554y;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo34193a(String browserString, boolean escapeNonAscii) {
        this.f7509C = C4491ai.m8352i(browserString);
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo34195a(AtomicLong options) {
        this.f7537h = options;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: h */
    public final String mo34206h() {
        return "https://" + this.f7555z + "/fp/mobile/conf";
    }

    /* Access modifiers changed, original: final */
    /* renamed from: c */
    public final boolean mo34199c(String fp_server) {
        if (fp_server == null) {
            fp_server = "h-sdk.online-metrix.net";
        }
        try {
            URL url = new URL("https://" + fp_server);
            this.f7555z = fp_server;
            return true;
        } catch (MalformedURLException e) {
            C4549w.m8588a(f7505Y, "Invalid hostname " + fp_server, e);
            return false;
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: d */
    public final boolean mo34201d(String org_id) {
        if (C4491ai.m8353j(org_id)) {
            this.f7554y = org_id;
            return true;
        }
        C4549w.m8587a(f7505Y, "Invalid org_id");
        return false;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: e */
    public final boolean mo34203e(String packageName) {
        if (C4491ai.m8348e(packageName)) {
            packageName = "TrustDefenderSDK";
        }
        this.f7551v = "http://" + packageName;
        this.f7547r = "http://" + packageName + "/mobile";
        return true;
    }

    /* renamed from: i */
    static String m8419i() {
        return "h-sdk.online-metrix.net";
    }

    /* Access modifiers changed, original: final */
    /* renamed from: j */
    public final C4540n mo34207j() {
        C4540n params = new C4540n();
        params.mo34249a("org_id", this.f7554y);
        params.mo34249a("session_id", this.f7542m);
        params.mo34249a("os", C4543p.m8569f());
        params.mo34249a(AnalyticAttribute.OS_VERSION_ATTRIBUTE, C4516a.f7582a);
        if (!(this.f7508B == null || this.f7508B.isEmpty())) {
            params.mo34249a("api_key", this.f7508B);
        }
        params.mo34249a("sdk_version", f7506a);
        return params;
    }

    /* renamed from: a */
    public final void mo34184a(int bitMask) {
        this.f7536g |= bitMask;
    }

    /* renamed from: k */
    public final void mo34208k() {
        this.f7536g = 0;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: l */
    public final void mo34209l() throws InterruptedException {
        long gatherInfoStart = C4529m.m8516a();
        C4549w.m8594c(f7505Y, "Getting timezone info");
        if ((this.f7537h.get() & 8) != 0) {
            C4542b tzInfo = new C4542b();
            if (C4543p.m8560a(tzInfo)) {
                this.f7532c = tzInfo.f7816b;
                this.f7531b = tzInfo.f7815a;
            }
        }
        if (this.f7545p == null || this.f7543n == null || this.f7544o == null) {
            String genID = null;
            m8425v();
            if (this.f7544o == null) {
                genID = C4500an.m8394a(this.f7521O);
                m8423t();
                this.f7544o = C4500an.m8397a(genID, m8424u());
            }
            if (this.f7548s == null) {
                this.f7548s = C4500an.m8401c(this.f7521O);
            }
            if (!(this.f7548s == null || this.f7548s.isEmpty())) {
                String imei = C4500an.m8401c(this.f7521O);
                if (!(imei == null || imei.isEmpty() || imei.equalsIgnoreCase(this.f7548s))) {
                    this.f7548s = "";
                    mo34184a(4);
                }
            }
            if (this.f7543n == null) {
                if (this.f7546q == null) {
                    this.f7546q = C4500an.m8399b(this.f7521O);
                }
                if (genID == null) {
                    genID = C4500an.m8394a(this.f7521O);
                }
                m8423t();
                this.f7543n = C4500an.m8396a(this.f7546q, genID, this.f7548s, m8424u());
            }
        }
        m8423t();
        if ((this.f7537h.get() & 16) != 0 && (this.f7534e == 0 || this.f7533d == 0)) {
            C4534j display = new C4534j(this.f7521O);
            this.f7533d = display.mo34237a();
            this.f7534e = display.mo34238b();
        }
        m8423t();
        if (this.f7552w == null) {
            this.f7552w = C4543p.m8555a(this.f7521O, this.f7521O);
        }
        if ((this.f7537h.get() & PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH) != 0 && this.f7510D == null) {
            this.f7510D = C4474a.m8264a(this.f7521O);
        }
        if ((this.f7537h.get() & 12288) == 12288) {
            this.f7517K = NativeGatherer.m8207a().mo34062g();
        } else if ((this.f7537h.get() & PlaybackStateCompat.ACTION_PLAY_FROM_URI) != 0) {
            this.f7517K = NativeGatherer.m8207a().mo34059e();
        } else if ((this.f7537h.get() & PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) != 0) {
            this.f7517K = NativeGatherer.m8207a().mo34061f();
        }
        if (this.f7550u == null || this.f7549t == null) {
            StringBuilder fontCount = new StringBuilder();
            this.f7549t = C4543p.m8557a(fontCount);
            if (this.f7549t != null) {
                this.f7550u = fontCount.toString();
            }
            String str = f7505Y;
            new StringBuilder("Got ").append(this.f7550u).append(" fonts gives: ").append(this.f7549t);
        }
        m8423t();
        if (this.f7538i == 0) {
            this.f7538i = C4543p.m8563c();
        }
        m8423t();
        if (this.f7507A == null) {
            this.f7507A = C4543p.m8562b(this.f7521O);
        }
        m8423t();
        if (this.f7539j == 0) {
            this.f7539j = C4543p.m8566d();
        }
        m8423t();
        if (this.f7540k == 0) {
            this.f7540k = C4543p.m8567e();
        }
        m8423t();
        this.f7553x = C4543p.m8554a(this.f7539j, this.f7538i);
        if ((this.f7537h.get() & PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID) != 0) {
            m8423t();
            this.f7524R = C4543p.m8552a(this.f7521O);
        }
        m8423t();
        this.f7514H = C4543p.m8568e(this.f7521O);
        m8423t();
        this.f7516J = C4543p.m8573h(this.f7521O);
        m8423t();
        if (C4491ai.m8348e(this.f7515I) && (this.f7537h.get() & PlaybackStateCompat.ACTION_PREPARE_FROM_URI) != 0) {
            this.f7515I = C4543p.m8570f(this.f7521O);
        }
        this.f7527U = C4529m.m8516a() - gatherInfoStart;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: m */
    public final Map<String, String> mo34210m() {
        Map<String, String> map = new HashMap();
        if (C4491ai.m8349f(this.f7509C)) {
            map.put("User-Agent", this.f7509C);
        }
        try {
            if (C4491ai.m8349f(this.f7546q) || this.f7521O != null) {
                m8425v();
            }
        } catch (InterruptedException e) {
            this.f7545p = null;
        }
        map.put("Cookie", "thx_guid=" + (this.f7545p == null ? "" : this.f7545p));
        map.put("Referer", this.f7551v);
        map.put("Accept-Language", m8422s());
        map.put("Cache-Control", "no-cache, no-store, must-revalidate, no-transform");
        return map;
    }

    /* renamed from: n */
    static Map<String, String> m8420n() {
        Map<String, String> map = new HashMap();
        map.put("Cache-Control", "no-cache, no-store, must-revalidate, no-transform");
        return map;
    }

    /* renamed from: o */
    public final String mo34211o() {
        StringBuilder host = new StringBuilder();
        host.append(this.f7554y).append("-").append(C4491ai.m8351h(this.f7542m)).append("-mob");
        if (host.length() >= 64) {
            C4549w.m8592b(f7505Y, "combined session id and org id too long for host name fragment");
            return null;
        }
        C4549w.m8594c(f7505Y, "Launching DNS profiling request");
        String prod = "d";
        if (this.f7555z.equals("qa2-h.online-metrix.net")) {
            prod = "q";
        }
        return host.append(".").append(prod).append(".aa.online-metrix.net").toString();
    }

    /* Access modifiers changed, original: final */
    /* renamed from: p */
    public final Map<String, String> mo34212p() {
        HashMap<String, String> extra_headers = new HashMap();
        if (!(this.f7509C == null || this.f7509C.isEmpty())) {
            C4549w.m8594c(f7505Y, "Setting User-Agent to " + this.f7509C);
            extra_headers.put("User-Agent", this.f7509C);
        }
        extra_headers.put("Cookie", "thx_guid=" + (this.f7545p == null ? "" : this.f7545p));
        extra_headers.put("Referer", this.f7551v);
        extra_headers.put(TransactionStateUtil.CONTENT_TYPE_HEADER, "application/x-www-form-urlencoded");
        extra_headers.put("Accept-Language", m8422s());
        extra_headers.put("Cache-Control", "no-cache, no-store, must-revalidate, no-transform");
        return extra_headers;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: q */
    public final C4540n mo34213q() throws InterruptedException {
        String urlCheckCount = "";
        String foundPaths = null;
        if ((this.f7537h.get() & PlaybackStateCompat.ACTION_PREPARE) != 0) {
            List<String> foundURLs = C4543p.m8559a(this.f7521O, (List) this.f7519M.f7401d);
            if (this.f7519M.f7401d.size() > 0) {
                urlCheckCount = String.valueOf(foundURLs.size());
                foundPaths = C4491ai.m8341a(foundURLs, ";", true);
            }
        }
        String experimentalAttributes = "";
        if ((this.f7519M.f7398a & 256) != 0) {
            experimentalAttributes = C4543p.m8558a((List) this.f7519M.f7402e);
        }
        C4540n jaParams = new C4540n();
        jaParams.mo34253a(255);
        if (this.f7533d >= this.f7534e) {
            jaParams.mo34249a("f", this.f7533d + "x" + this.f7534e);
        } else {
            jaParams.mo34249a("f", this.f7534e + "x" + this.f7533d);
        }
        jaParams.mo34249a("w", this.f7519M.f7400c);
        jaParams.mo34249a("c", String.valueOf(this.f7531b));
        jaParams.mo34249a("z", String.valueOf(this.f7532c));
        jaParams.mo34251a("lh", this.f7547r, true);
        jaParams.mo34251a("dr", this.f7551v, true);
        if (!this.f7523Q.f7559b.equals("0")) {
            jaParams.mo34251a("p", this.f7523Q.f7558a, true);
            jaParams.mo34251a("pl", this.f7523Q.f7559b, true);
            jaParams.mo34251a("ph", this.f7523Q.f7561d, true);
        }
        jaParams.mo34249a("hh", C4491ai.m8344b(this.f7554y + this.f7542m));
        if (this.f7523Q.f7563f > 0) {
            jaParams.mo34249a("mt", this.f7523Q.f7565h);
            jaParams.mo34249a("mn", String.valueOf(this.f7523Q.f7563f));
        }
        jaParams.mo34251a("mdf", this.f7552w, true);
        jaParams.mo34251a(JiceArgs.EVENT_START_DELIVERY_ORDER, this.f7553x, true);
        jaParams.mo34251a("imei", this.f7548s, true);
        if (this.f7520N != null) {
            jaParams.mo34249a("tdlat", String.valueOf(this.f7520N.getLatitude()));
            jaParams.mo34249a("tdlon", String.valueOf(this.f7520N.getLongitude()));
            jaParams.mo34249a("tdlacc", String.valueOf(this.f7520N.getAccuracy()));
        }
        if (this.f7518L != null && this.f7518L.size() > 0) {
            int count = 0;
            Iterator i$ = this.f7518L.iterator();
            while (i$.hasNext()) {
                int count2 = count + 1;
                jaParams.mo34251a("aca" + count, (String) i$.next(), true);
                if (count2 >= 5) {
                    break;
                }
                count = count2;
            }
        }
        jaParams.mo34249a("ah", this.f7510D);
        jaParams.mo34249a("la", this.f7519M.f7400c + this.f7544o);
        jaParams.mo34249a("lq", this.f7509C);
        jaParams.mo34249a("fc", this.f7519M.f7400c + this.f7543n);
        jaParams.mo34249a("ftsn", this.f7550u);
        jaParams.mo34251a("fts", this.f7549t, true);
        jaParams.mo34251a("aov", C4516a.f7582a, true);
        jaParams.mo34251a("al", m8422s(), true);
        String str = "alo";
        if (this.f7512F == null) {
            this.f7512F = C4543p.m8561b();
        }
        jaParams.mo34251a(str, this.f7512F, true);
        jaParams.mo34251a("ab", C4518b.f7606e + ", " + C4518b.f7614m, true);
        jaParams.mo34251a("am", C4518b.f7611j, true);
        jaParams.mo34251a("aos", C4543p.m8571g(), true);
        jaParams.mo34251a("cos", C4543p.m8569f(), true);
        jaParams.mo34249a("fg", this.f7543n);
        jaParams.mo34249a("ls", this.f7544o);
        jaParams.mo34249a("gr", urlCheckCount);
        jaParams.mo34250a("grr", foundPaths, Integer.valueOf(1024));
        jaParams.mo34249a("at", "agent_mobile");
        String str2 = "av";
        StringBuilder append = new StringBuilder().append(f7506a);
        if (this.f7513G.isEmpty()) {
            str = "";
        } else {
            str = " : " + this.f7513G;
        }
        jaParams.mo34249a(str2, append.append(str).toString());
        jaParams.mo34249a("mex3", experimentalAttributes);
        if (C4543p.m8564c(this.f7521O)) {
            this.f7535f |= 2;
        }
        if (this.f7520N != null && new C4548v(this.f7520N).mo34261a()) {
            this.f7535f |= 1;
        }
        if (this.f7541l) {
            this.f7535f |= 4;
        }
        jaParams.mo34249a("mex4", String.valueOf(this.f7535f));
        jaParams.mo34249a("mex5", String.valueOf(this.f7536g));
        jaParams.mo34249a("mex6", String.valueOf(C4543p.m8565d(this.f7521O)));
        jaParams.mo34249a("mex7", this.f7514H == null ? "" : this.f7514H);
        jaParams.mo34249a("mex8", this.f7515I == null ? "" : this.f7515I);
        jaParams.mo34249a("mex10", C4543p.m8572g(this.f7521O));
        jaParams.mo34249a("abt", this.f7538i == 0 ? "" : String.valueOf(this.f7538i));
        jaParams.mo34249a("anv", this.f7507A);
        jaParams.mo34249a("afs", this.f7539j == 0 ? "" : String.valueOf(this.f7539j));
        jaParams.mo34249a("ats", this.f7540k == 0 ? "" : String.valueOf(this.f7540k));
        jaParams.mo34249a("aci", this.f7516J == null ? "" : this.f7516J);
        if (this.f7524R != null) {
            jaParams.mo34249a("wbs", this.f7524R.mo34264b() == null ? "" : this.f7524R.mo34264b());
            jaParams.mo34249a("wss", this.f7524R.mo34263a() == null ? "" : this.f7524R.mo34263a());
            jaParams.mo34249a("wrr", this.f7524R.mo34266d() == null ? "" : this.f7524R.mo34266d());
            str2 = "wc";
            if (this.f7524R.mo34265c() == null) {
                str = "";
            } else {
                str = this.f7524R.mo34265c();
            }
            jaParams.mo34249a(str2, str);
        }
        long currentTime = System.currentTimeMillis();
        jaParams.mo34249a("atr", m8418b(currentTime));
        jaParams.mo34249a("apd", String.valueOf(currentTime - this.f7529W));
        C4540n postParams = new C4540n();
        postParams.mo34249a("org_id", this.f7554y);
        postParams.mo34249a("session_id", this.f7542m);
        if (this.f7517K != null) {
            StringBuilder apps = new StringBuilder();
            for (String app : this.f7517K) {
                if (apps.length() > 0) {
                    apps.append(",");
                }
                apps.append(app);
            }
            str = f7505Y;
            new StringBuilder("Found: ").append(apps.toString());
            postParams.mo34249a("ih", apps.toString());
        }
        String encoded = jaParams.mo34254b();
        str = f7505Y;
        new StringBuilder("encoded ja = ").append(jaParams);
        postParams.mo34249a("ja", C4491ai.m8339a(encoded, this.f7542m));
        postParams.mo34249a("h", "0").mo34249a("m", "2");
        return postParams;
    }

    /* renamed from: r */
    static boolean m8421r() {
        StackTraceElement[] stack = Looper.getMainLooper().getThread().getStackTrace();
        int i = 0;
        while (i < stack.length) {
            String className = stack[i].getClassName();
            if ((className != null && className.contains("de.robv.android.xposed.XposedBridge")) || "XposedBridge.java".equalsIgnoreCase(stack[i].getFileName())) {
                return true;
            }
            i++;
        }
        return false;
    }

    /* renamed from: b */
    private String m8418b(long timeOfCall) {
        JSONObject json = new JSONObject();
        try {
            json.put("opts", this.f7525S);
            json.put("dyOpt", this.f7537h);
            json.put("psi", this.f7526T);
            json.put("pr", this.f7527U);
            json.put("cpi", timeOfCall - this.f7529W);
            json.put("lpi", this.f7530X);
            json.put("ori", this.f7533d >= this.f7534e ? "landscape" : "portrait");
            json.put("crc", NativeGatherer.m8207a().mo34066k());
            json.put("matched", NativeGatherer.m8207a().mo34067l());
            String str = "debug";
            ApplicationInfo applicationInfo = this.f7521O.getApplicationInfo();
            int i = applicationInfo.flags & 2;
            applicationInfo.flags = i;
            json.put(str, i != 0);
            return !(json instanceof JSONObject) ? json.toString() : JSONObjectInstrumentation.toString(json);
        } catch (JSONException e) {
            return "";
        }
    }

    /* renamed from: s */
    private String m8422s() {
        if (this.f7511E == null) {
            this.f7511E = C4543p.m8553a();
        }
        return this.f7511E;
    }

    /* renamed from: t */
    private void m8423t() throws InterruptedException {
        if (this.f7522P != null && this.f7522P.mo34114a()) {
            throw new InterruptedException();
        }
    }

    /* renamed from: u */
    private boolean m8424u() {
        return (this.f7537h.get() & 512) != 0 && C4500an.m8398a();
    }

    /* renamed from: v */
    private void m8425v() throws InterruptedException {
        if (C4491ai.m8348e(this.f7546q)) {
            this.f7546q = C4500an.m8399b(this.f7521O);
        }
        if (C4491ai.m8348e(this.f7545p)) {
            this.f7545p = C4500an.m8400b(this.f7546q, m8424u());
        }
    }
}
