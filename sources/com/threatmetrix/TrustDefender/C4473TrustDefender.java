package com.threatmetrix.TrustDefender;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.support.p000v4.media.session.PlaybackStateCompat;
import com.threatmetrix.TrustDefender.C4532g.C4518b.C4516a;
import com.threatmetrix.TrustDefender.C4532g.C4518b.C4517b;
import com.threatmetrix.TrustDefender.C4532g.C4522f;
import com.threatmetrix.TrustDefender.C4532g.C4526j;
import com.threatmetrix.TrustDefender.C4532g.C4528l;
import com.threatmetrix.TrustDefender.C4532g.C4531o;
import com.threatmetrix.TrustDefender.C4537o.C4538a;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: com.threatmetrix.TrustDefender.TrustDefender */
public class C4473TrustDefender {
    /* renamed from: d */
    private static final String f7289d = C4549w.m8585a(C4473TrustDefender.class);
    /* renamed from: e */
    private static final boolean f7290e;
    /* renamed from: f */
    private static final Executor f7291f = Executors.newFixedThreadPool(6);
    /* renamed from: g */
    private static final Lock f7292g = new ReentrantLock();
    /* renamed from: h */
    private static volatile C4473TrustDefender f7293h = null;
    /* renamed from: i */
    private static boolean f7294i;
    /* renamed from: j */
    private static boolean f7295j;
    /* renamed from: k */
    private static boolean f7296k;
    /* renamed from: A */
    private volatile boolean f7297A = true;
    /* renamed from: B */
    private volatile long f7298B = 0;
    /* renamed from: C */
    private volatile int f7299C = 0;
    /* renamed from: D */
    private volatile C4536k f7300D = null;
    /* renamed from: E */
    private volatile boolean f7301E = false;
    /* renamed from: F */
    private Timer f7302F;
    /* renamed from: G */
    private int f7303G;
    /* renamed from: H */
    private final ArrayList<C4552y> f7304H = new ArrayList();
    /* renamed from: I */
    private final ReadWriteLock f7305I = new ReentrantReadWriteLock();
    /* renamed from: J */
    private final Lock f7306J = this.f7305I.readLock();
    /* renamed from: K */
    private final Lock f7307K = this.f7305I.writeLock();
    /* renamed from: L */
    private final C4505ap f7308L = new C4505ap();
    /* renamed from: M */
    private final boolean f7309M = true;
    /* renamed from: N */
    private final C4472d f7310N = new C4472d();
    /* renamed from: a */
    final C4506ar f7311a = new C4506ar("");
    /* renamed from: b */
    final C4484ad f7312b = new C4484ad();
    /* renamed from: c */
    volatile C4480am f7313c = null;
    /* renamed from: l */
    private Context f7314l = null;
    /* renamed from: m */
    private int f7315m = 0;
    /* renamed from: n */
    private int f7316n = 30000;
    /* renamed from: o */
    private volatile AtomicLong f7317o = new AtomicLong(0);
    /* renamed from: p */
    private volatile int f7318p = 10000;
    /* renamed from: q */
    private volatile int f7319q = 10000;
    /* renamed from: r */
    private volatile int f7320r = 0;
    /* renamed from: s */
    private volatile boolean f7321s = true;
    /* renamed from: t */
    private volatile boolean f7322t = false;
    /* renamed from: u */
    private volatile String f7323u = null;
    /* renamed from: v */
    private volatile Thread f7324v = null;
    /* renamed from: w */
    private volatile EndNotifierBase f7325w = null;
    /* renamed from: x */
    private volatile C4493ak f7326x = null;
    /* renamed from: y */
    private volatile C4512d f7327y = null;
    /* renamed from: z */
    private volatile boolean f7328z = true;

    /* renamed from: com.threatmetrix.TrustDefender.TrustDefender$a */
    final class C4468a implements Runnable {
        /* renamed from: a */
        final ProfilingResult f7278a;
        /* renamed from: b */
        final EndNotifierBase f7279b;

        C4468a(ProfilingResult t, EndNotifierBase n) {
            this.f7278a = t;
            this.f7279b = n;
        }

        public final void run() {
            if (this.f7279b != null && (this.f7279b instanceof EndNotifier)) {
                ((EndNotifier) this.f7279b).complete(this.f7278a);
            }
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.TrustDefender$b */
    final class C4469b implements Runnable {
        /* renamed from: a */
        final Thread f7281a;

        C4469b(Thread t) {
            this.f7281a = t;
        }

        public final void run() {
            C4549w.m8594c(C4473TrustDefender.f7289d, "sending interrupt to TID: " + this.f7281a.getId());
            this.f7281a.interrupt();
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.TrustDefender$c */
    private enum C4470c {
        doProfileRequest,
        doPackageScan,
        init
    }

    /* renamed from: com.threatmetrix.TrustDefender.TrustDefender$d */
    class C4472d extends BroadcastReceiver {

        /* renamed from: com.threatmetrix.TrustDefender.TrustDefender$d$1 */
        class C44711 extends TimerTask {
            C44711() {
            }

            public final void run() {
                synchronized (this) {
                    if (!C4473TrustDefender.this.f7297A) {
                        C4473TrustDefender.this.f7328z = false;
                        C4473TrustDefender.this.pauseLocationServices(true);
                    }
                }
            }
        }

        C4472d() {
        }

        public final void onReceive(Context context, Intent intent) {
            if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                C4473TrustDefender.this.f7297A = false;
                C4549w.m8594c(C4473TrustDefender.f7289d, "Screen is off, any future profiling will be blocked after " + C4473TrustDefender.this.f7303G + " seconds.");
                if (C4473TrustDefender.this.f7302F != null) {
                    C4473TrustDefender.this.f7302F.cancel();
                }
                C4473TrustDefender.this.f7302F = new Timer();
                C4473TrustDefender.this.f7302F.schedule(new C44711(), TimeUnit.SECONDS.toMillis((long) C4473TrustDefender.this.f7303G));
            } else if ("android.intent.action.SCREEN_ON".equals(intent.getAction())) {
                synchronized (this) {
                    C4473TrustDefender.this.f7297A = true;
                    C4473TrustDefender.this.f7328z = true;
                    if (C4473TrustDefender.this.f7302F != null) {
                        C4473TrustDefender.this.f7302F.cancel();
                    }
                    C4473TrustDefender.this.pauseLocationServices(false);
                    C4549w.m8594c(C4473TrustDefender.f7289d, "Screen is on profiling is unblocked.");
                }
            }
        }
    }

    static {
        boolean z = true;
        f7294i = false;
        f7295j = false;
        f7296k = false;
        String vmVersion = System.getProperty("java.vm.version");
        boolean z2 = vmVersion != null && vmVersion.equals("2.0.0");
        f7290e = z2;
        if (z2) {
            C4549w.m8594c(f7289d, "Broken join() detected, activating fallback routine");
        }
        if (C4485at.m8327b("com.squareup.okhttp.OkHttpClient") != null) {
            z2 = true;
        } else {
            z2 = false;
        }
        f7295j = z2;
        if (C4485at.m8327b("okhttp3.OkHttpClient") != null) {
            z2 = true;
        } else {
            z2 = false;
        }
        f7294i = z2;
        if (C4485at.m8327b("okio.Okio") == null) {
            z = false;
        }
        f7296k = z;
    }

    private C4473TrustDefender() {
    }

    public static C4473TrustDefender getInstance() {
        if (f7293h != null) {
            return f7293h;
        }
        try {
            f7292g.lock();
            if (f7293h == null) {
                f7293h = new C4473TrustDefender();
            }
            C4473TrustDefender c4473TrustDefender = f7293h;
            return c4473TrustDefender;
        } finally {
            f7292g.unlock();
        }
    }

    public THMStatusCode init(final Config config) {
        if (!this.f7312b.mo34120d()) {
            C4549w.m8594c(f7289d, "Already init'd");
            return THMStatusCode.THM_Already_Initialised;
        } else if (config.mo34008j() == null) {
            this.f7312b.mo34115a(false);
            mo34091a(THMStatusCode.THM_Invalid_Context);
            return this.f7311a.mo34200d();
        } else if ((!f7295j && !f7294i) || !f7296k) {
            C4549w.m8587a(f7289d, "OkHttp library not available, please include the library. For information about how to include the library see http://square.github.io/okhttp/");
            this.f7312b.mo34115a(false);
            mo34091a(THMStatusCode.THM_ThirdPartyLibrary_Not_Found);
            return this.f7311a.mo34200d();
        } else if (!this.f7311a.mo34201d(config.mo34017s())) {
            this.f7312b.mo34115a(false);
            mo34091a(THMStatusCode.THM_Invalid_OrgID);
            return this.f7311a.mo34200d();
        } else if (this.f7311a.mo34199c(config.mo34014p())) {
            boolean z;
            C4549w.m8594c(f7289d, "Starting init()");
            m8251d();
            this.f7297A = true;
            this.f7328z = true;
            this.f7311a.mo34208k();
            this.f7314l = config.mo34008j().getApplicationContext();
            this.f7311a.mo34186a(this.f7314l);
            this.f7317o.set(config.mo34007i());
            this.f7311a.mo34185a(this.f7317o.get());
            this.f7318p = config.mo34001c() * 1000;
            this.f7311a.mo34192a(config.mo34002d());
            if (this.f7302F != null) {
                this.f7302F.cancel();
            }
            if (this.f7327y != null) {
                this.f7327y.mo34217a(false);
            }
            C4489ag.m8333b();
            this.f7308L.mo34177a(config.mo34003e());
            this.f7308L.mo34179b(config.mo34015q());
            this.f7308L.mo34175a(this.f7314l, config.mo34004f(), config.mo34005g(), config.mo34006h());
            this.f7320r = config.mo34009k();
            this.f7319q = config.mo34010l();
            this.f7316n = config.mo34012n();
            this.f7315m = config.mo34011m();
            this.f7322t = config.mo34013o();
            if (config.mo33999a()) {
                z = false;
            } else {
                z = true;
            }
            this.f7321s = z;
            String packageName = this.f7314l.getPackageName();
            String g = this.f7311a.mo34205g();
            if (this.f7323u == null) {
                this.f7323u = packageName + "TDM" + g;
            }
            this.f7311a.mo34203e(packageName);
            this.f7303G = config.mo34016r();
            if (this.f7303G > 0) {
                IntentFilter filter = new IntentFilter();
                filter.addAction("android.intent.action.SCREEN_ON");
                filter.addAction("android.intent.action.SCREEN_OFF");
                this.f7314l.registerReceiver(this.f7310N, filter);
            }
            if ((this.f7317o.get() & PlaybackStateCompat.ACTION_PREPARE_FROM_URI) != 0) {
                this.f7300D = new C4536k();
                this.f7301E = this.f7300D.mo34243a(this.f7314l);
            }
            new Thread(new C4465h(this) {
                public final void run() {
                    boolean initWebView = true;
                    boolean initSuccess = true;
                    try {
                        C4549w.m8594c(C4473TrustDefender.f7289d, "Doing slow init stuff");
                        C4506ar c4506ar = C4473TrustDefender.this.f7311a;
                        if (C4506ar.m8421r()) {
                            C4473TrustDefender.this.f7311a.mo34184a(1);
                        }
                        NativeGatherer.m8207a().mo34048a(C4473TrustDefender.this.f7314l, C4549w.m8591b());
                        C4549w.m8592b(C4473TrustDefender.f7289d, "Native libs: " + (NativeGatherer.m8207a().mo34053b() ? "available" : "unavailable"));
                        C4473TrustDefender.this.mo34093b();
                        if (C4473TrustDefender.this.f7326x != null) {
                            C4549w.m8594c(C4473TrustDefender.f7289d, "applying saved options (" + C4473TrustDefender.this.f7326x.mo34137a() + " / " + C4473TrustDefender.this.f7326x.mo34142b() + ") to " + C4473TrustDefender.this.f7317o);
                            C4473TrustDefender.this.f7317o.set((C4473TrustDefender.this.f7317o.get() & ((C4473TrustDefender.this.f7326x.mo34142b() & 262142) ^ -1)) | (C4473TrustDefender.this.f7326x.mo34137a() & 262142));
                            C4473TrustDefender.f7289d;
                            C4473TrustDefender.this.f7326x.mo34145d();
                            C4473TrustDefender.this.f7299C = C4473TrustDefender.this.f7326x.mo34145d();
                        }
                        C4473TrustDefender.this.f7311a.mo34195a(C4473TrustDefender.this.f7317o);
                        if ((C4473TrustDefender.this.f7317o.get() & 38) == 0) {
                            initWebView = false;
                        }
                        if (C4531o.m8529a()) {
                            C4473TrustDefender.this.f7327y = new C4512d();
                            C4473TrustDefender.this.f7327y.mo34218a(C4473TrustDefender.this.f7314l, initWebView, C4473TrustDefender.this.f7317o.get());
                            C4473TrustDefender.this.f7311a.mo34193a(C4473TrustDefender.this.f7327y.mo34215a(), true);
                        } else {
                            C4473TrustDefender.this.f7327y = null;
                        }
                        C4549w.m8594c(C4473TrustDefender.f7289d, "Creating HTTP Client");
                        if (!C4473TrustDefender.this.m8257g()) {
                            initSuccess = false;
                        }
                        C4549w.m8594c(C4473TrustDefender.f7289d, "HTTP Client created and user agent set");
                        try {
                            C4491ai.m8344b(null);
                        } catch (InterruptedException e) {
                        }
                        if (!NativeGatherer.m8207a().mo34053b() && NativeGatherer.m8207a().mo34065j()) {
                            C4473TrustDefender.this.f7311a.mo34184a(2);
                        }
                        if (!config.mo34000b()) {
                            C4473TrustDefender.this.mo34092a(C4473TrustDefender.this.f7316n, false, false, C4470c.init);
                        }
                        C4473TrustDefender.this.f7312b.mo34115a(initSuccess);
                        C4549w.m8594c(C4473TrustDefender.f7289d, "init completed " + (initSuccess ? "successfully" : "unsuccessfully"));
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        C4473TrustDefender.this.f7312b.mo34115a(initSuccess);
                        C4549w.m8594c(C4473TrustDefender.f7289d, "init completed " + (initSuccess ? "successfully" : "unsuccessfully"));
                    }
                }
            }).start();
            return THMStatusCode.THM_OK;
        } else {
            this.f7312b.mo34115a(false);
            mo34091a(THMStatusCode.THM_Invalid_FP_Server);
            return this.f7311a.mo34200d();
        }
    }

    public THMStatusCode doProfileRequest(String sessionID, EndNotifier endNotifier) {
        return doProfileRequest(new ProfilingOptions().setSessionID(sessionID).setEndNotifier(endNotifier));
    }

    public THMStatusCode doProfileRequest(ProfilingOptions options) {
        Object obj = 1;
        if (!this.f7312b.mo34117b()) {
            mo34091a(THMStatusCode.THM_Internal_Error);
            return this.f7311a.mo34200d();
        } else if (!this.f7312b.mo34122g()) {
            mo34091a(THMStatusCode.THM_NotYet);
            return this.f7311a.mo34200d();
        } else if (this.f7328z && m8255f()) {
            if (this.f7298B == 0 || this.f7299C == 0) {
                obj = null;
            } else if (this.f7298B + TimeUnit.MILLISECONDS.convert((long) this.f7299C, TimeUnit.MINUTES) <= System.currentTimeMillis()) {
                obj = null;
            }
            if (obj != null) {
                mo34091a(THMStatusCode.THM_In_Quiet_Period);
                this.f7312b.mo34123h();
                return this.f7311a.mo34200d();
            } else if (options.mo34071d() == null) {
                mo34091a(THMStatusCode.THM_EndNotifier_NotFound);
                this.f7312b.mo34123h();
                return this.f7311a.mo34200d();
            } else {
                this.f7311a.mo34188a(THMStatusCode.THM_NotYet);
                this.f7311a.f7529W = System.currentTimeMillis();
                C4549w.m8591b();
                THMStatusCode tHMStatusCode;
                try {
                    this.f7307K.lockInterruptibly();
                    C4549w.m8594c(f7289d, "starting profile request using - 4.0-90 options " + this.f7317o + " timeout " + this.f7318p + "ms fp " + this.f7311a.mo34204f() + " java.vm.version " + System.getProperty("java.vm.version"));
                    m8251d();
                    this.f7311a.mo34191a(this.f7312b);
                    if (this.f7304H.size() > 0) {
                        C4549w.m8594c(f7289d, "outstanding requests... interrupting");
                        m8246b(true);
                    }
                    this.f7304H.clear();
                    this.f7325w = options.mo34071d();
                    if (!(this.f7300D == null || !this.f7301E || (this.f7317o.get() & PlaybackStateCompat.ACTION_PREPARE_FROM_URI) == 0)) {
                        this.f7311a.f7515I = this.f7300D.mo34242a(this.f7318p / 10);
                    }
                    this.f7311a.mo34194a(options.mo34069b());
                    if (C4491ai.m8349f(options.mo34068a())) {
                        this.f7311a.mo34197b(options.mo34068a());
                    } else {
                        this.f7311a.mo34197b(C4491ai.m8337a());
                    }
                    if (C4522f.m8497b()) {
                        this.f7308L.mo34176a(options.mo34070c());
                    }
                    this.f7324v = new Thread(new C4465h(this));
                    this.f7324v.start();
                    tHMStatusCode = THMStatusCode.THM_OK;
                    return tHMStatusCode;
                } catch (InterruptedException e) {
                    if (this.f7324v != null) {
                        this.f7324v.interrupt();
                    }
                    this.f7312b.mo34123h();
                    mo34091a(THMStatusCode.THM_Interrupted_Error);
                    tHMStatusCode = this.f7311a.mo34200d();
                    return tHMStatusCode;
                } finally {
                    this.f7307K.unlock();
                }
            }
        } else {
            mo34091a(THMStatusCode.THM_Blocked);
            this.f7312b.mo34123h();
            return this.f7311a.mo34200d();
        }
    }

    public ProfilingResult getResult() {
        return new ProfilingResult(this.f7311a.mo34198c(), this.f7311a.mo34200d());
    }

    public boolean doPackageScan(int timeout) {
        return mo34092a(timeout, true, true, C4470c.doPackageScan);
    }

    public void pauseLocationServices(boolean pause) {
        if (pause) {
            this.f7308L.mo34174a();
        } else {
            this.f7308L.mo34178b();
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final boolean mo34092a(int timeout, boolean checkProfiling, boolean checkInit, C4470c source) {
        C4549w.m8594c(f7289d, "doPackageScan(" + source + "): marking scan as started");
        if ((!checkInit || this.f7312b.mo34119c()) && (checkInit || this.f7312b.mo34117b())) {
            long allowedScanOptions;
            int package_limit;
            if (source == C4470c.doProfileRequest || source == C4470c.init) {
                allowedScanOptions = this.f7317o.get() & PlaybackStateCompat.ACTION_PREPARE;
                package_limit = source == C4470c.init ? this.f7315m : this.f7320r;
            } else {
                allowedScanOptions = this.f7317o.get();
                package_limit = 0;
            }
            if ((28672 & allowedScanOptions) != 0) {
                if (this.f7312b.mo34118b(checkProfiling)) {
                    final int i = timeout;
                    final C4470c c4470c = source;
                    new Thread(new C4465h(this) {
                        public final void run() {
                            int flags = 0;
                            try {
                                if ((allowedScanOptions & 12288) != 0) {
                                    flags = 2;
                                }
                                if (!((allowedScanOptions & PlaybackStateCompat.ACTION_PREPARE) == 0 && (allowedScanOptions & PlaybackStateCompat.ACTION_PLAY_FROM_URI) == 0)) {
                                    flags |= 1;
                                }
                                NativeGatherer.m8207a().mo34044a(C4473TrustDefender.this.f7314l, flags, package_limit, i);
                            } catch (InterruptedException e) {
                            } finally {
                                C4549w.m8594c(C4473TrustDefender.f7289d, "doPackageScan(" + c4470c + "): complete");
                                C4473TrustDefender.this.f7312b.mo34125l();
                            }
                        }
                    }).start();
                } else {
                    C4549w.m8592b(f7289d, "Scan " + (checkProfiling ? "or profile" : "") + " already in progress or cancel requested, aborting");
                    return false;
                }
            }
            return true;
        }
        C4549w.m8587a(f7289d, "doPackageScan(" + source + "): aborted! not inited");
        return false;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo34090a() {
        ProfilingResult result;
        EndNotifierBase notifier;
        try {
            this.f7311a.mo34196b();
            C4549w.m8594c(f7289d, "continuing profile request " + (this.f7312b.mo34117b() ? "inited already" : " needs init"));
            if (this.f7312b.mo34114a() || Thread.currentThread().isInterrupted()) {
                throw new InterruptedException();
            }
            boolean waitedForScan = false;
            boolean shouldWaitForScan = false;
            if (this.f7312b.mo34117b()) {
                boolean initInProgress = this.f7312b.mo34121e();
                boolean scanInProgress = this.f7312b.mo34127n();
                if (initInProgress || scanInProgress) {
                    if (this.f7322t) {
                        C4549w.m8594c(f7289d, "Setting flag to for interrupting " + (initInProgress ? "init" : "package") + " scan");
                        this.f7312b.mo34124k();
                    } else {
                        shouldWaitForScan = true;
                        waitedForScan = true;
                    }
                    boolean waitForInitResult = this.f7312b.mo34116a(this.f7318p);
                    if (this.f7322t) {
                        NativeGatherer.m8207a().mo34054c();
                        this.f7312b.mo34126m();
                        NativeGatherer.m8207a().mo34056d();
                    }
                    if (!waitForInitResult) {
                        if (this.f7312b.mo34114a()) {
                            C4549w.m8587a(f7289d, "Thread interrupted, returning");
                        } else {
                            C4549w.m8587a(f7289d, "Timed out waiting for init thread, aborting");
                            this.f7311a.mo34188a(THMStatusCode.THM_Internal_Error);
                        }
                        if (this.f7312b.mo34114a()) {
                            this.f7311a.mo34188a(THMStatusCode.THM_Interrupted_Error);
                            Thread.interrupted();
                        }
                        result = getResult();
                        notifier = this.f7325w;
                        this.f7311a.f7530X = System.currentTimeMillis() - this.f7311a.f7529W;
                        this.f7312b.mo34123h();
                        f7291f.execute(new C4468a(result, notifier));
                        return;
                    } else if (shouldWaitForScan) {
                        this.f7312b.mo34126m();
                    }
                }
                if (!waitedForScan && this.f7321s) {
                    mo34092a(this.f7319q, false, true, C4470c.doProfileRequest);
                }
                if (this.f7312b.mo34114a() || Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException();
                }
                Runnable c4539m = new C4539m(this.f7313c, this.f7311a.mo34206h(), this.f7311a.mo34207j(), this.f7311a.mo34210m(), this, this.f7312b);
                C4539m configRunner = m8241a(c4539m) != null ? c4539m : null;
                if (configRunner == null) {
                    C4549w.m8587a(f7289d, "Failed to connect to server, aborting");
                    this.f7311a.mo34188a(THMStatusCode.THM_Internal_Error);
                    if (this.f7312b.mo34114a()) {
                        this.f7311a.mo34188a(THMStatusCode.THM_Interrupted_Error);
                        Thread.interrupted();
                    }
                    result = getResult();
                    notifier = this.f7325w;
                    this.f7311a.f7530X = System.currentTimeMillis() - this.f7311a.f7529W;
                    this.f7312b.mo34123h();
                    f7291f.execute(new C4468a(result, notifier));
                    return;
                } else if (this.f7312b.mo34114a() || Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException();
                } else {
                    boolean needToWaitForBrowser = false;
                    if (this.f7327y != null) {
                        needToWaitForBrowser = this.f7327y.mo34219b();
                        if (needToWaitForBrowser) {
                            this.f7327y.mo34220c();
                        }
                    }
                    this.f7312b.mo34126m();
                    this.f7311a.mo34209l();
                    if (this.f7312b.mo34114a() || Thread.currentThread().isInterrupted()) {
                        throw new InterruptedException();
                    }
                    if (this.f7327y != null && needToWaitForBrowser) {
                        this.f7327y.mo34217a(true);
                        this.f7311a.mo34190a(this.f7327y);
                    }
                    THMStatusCode configStatus = m8239a(false);
                    m8252e();
                    if (configStatus != THMStatusCode.THM_OK) {
                        C4549w.m8587a(f7289d, "Failed to retrieve config, aborting: " + configStatus.toString());
                        this.f7311a.mo34188a(configStatus);
                        NativeGatherer.m8207a().mo34054c();
                        NativeGatherer.m8207a().mo34056d();
                        if (this.f7312b.mo34114a()) {
                            this.f7311a.mo34188a(THMStatusCode.THM_Interrupted_Error);
                            Thread.interrupted();
                        }
                        result = getResult();
                        notifier = this.f7325w;
                        this.f7311a.f7530X = System.currentTimeMillis() - this.f7311a.f7529W;
                        this.f7312b.mo34123h();
                        f7291f.execute(new C4468a(result, notifier));
                        return;
                    }
                    this.f7311a.mo34189a(configRunner.f7802a);
                    C4492aj confRef = this.f7311a.mo34202e();
                    if (confRef != null) {
                        if (this.f7326x == null || this.f7326x.mo34141a(confRef.f7398a, confRef.f7399b, "4.0-90", confRef.f7404g)) {
                            String str;
                            if (this.f7326x != null) {
                                str = f7289d;
                                new StringBuilder("dynamic enableOptions / disableOptions (").append(confRef.f7398a).append(" / ").append(confRef.f7399b).append(") != saved: m_default values enableOptions / disableOptions / sdk_version / quietPeriod (").append(this.f7326x.mo34137a()).append(" / ").append(this.f7326x.mo34142b()).append(" / ").append(this.f7326x.mo34144c()).append(" / ").append(this.f7326x.mo34145d()).append(")");
                            } else {
                                str = f7289d;
                                new StringBuilder("dynamic enableOptions / disableOptions (").append(confRef.f7398a).append(" / ").append(confRef.f7399b).append(") != saved: m_default is null");
                            }
                            NativeGatherer.m8207a().mo34045a("enableOptions", String.valueOf(confRef.f7398a));
                            NativeGatherer.m8207a().mo34045a("disableOptions", String.valueOf(confRef.f7399b));
                            NativeGatherer.m8207a().mo34045a("sdkVersion", "4.0-90");
                            NativeGatherer.m8207a().mo34045a("quietPeriod", String.valueOf(confRef.f7404g));
                            C4528l c4528l = new C4528l(this.f7314l, this.f7323u, 0);
                            c4528l.mo34234b("enableOptions", confRef.f7398a);
                            c4528l.mo34234b("disableOptions", confRef.f7399b);
                            c4528l.mo34235b("sdkVersion", "4.0-90");
                            c4528l.mo34233b("quietPeriod", confRef.f7404g);
                            c4528l.mo34232a();
                        }
                        this.f7299C = confRef.f7404g;
                    } else if (!this.f7312b.mo34114a()) {
                        C4549w.m8587a(f7289d, "Failed to get config, bailing out");
                        if (this.f7312b.mo34114a()) {
                            this.f7311a.mo34188a(THMStatusCode.THM_Interrupted_Error);
                            Thread.interrupted();
                        }
                        result = getResult();
                        notifier = this.f7325w;
                        this.f7311a.f7530X = System.currentTimeMillis() - this.f7311a.f7529W;
                        this.f7312b.mo34123h();
                        f7291f.execute(new C4468a(result, notifier));
                        return;
                    }
                    if (this.f7312b.mo34114a()) {
                        throw new InterruptedException();
                    }
                    if ((this.f7317o.get() & PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) != 0 && C4491ai.m8349f(this.f7311a.mo34202e().f7403f)) {
                        String str2 = this.f7311a.mo34202e().f7403f;
                        C4506ar c4506ar = this.f7311a;
                        m8241a(new C4537o(this.f7313c, C4538a.GET_CONSUME, str2, null, C4506ar.m8420n(), this, this.f7314l, this.f7312b));
                    }
                    if ((this.f7317o.get() & PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) != 0) {
                        String fqdn = this.f7311a.mo34211o();
                        if (fqdn != null) {
                            m8241a(new C4533i(fqdn));
                        }
                    }
                    if ((this.f7317o.get() & 64) != 0) {
                        m8241a(new C4487af(this.f7311a.mo34204f(), this.f7311a.mo34205g(), this.f7311a.mo34198c(), this.f7311a.mo34202e().f7400c, this.f7318p));
                    }
                    if (C4522f.m8497b()) {
                        this.f7311a.mo34187a(this.f7308L.mo34180c(), this.f7308L.mo34181d());
                    }
                    m8241a(new C4537o(this.f7313c, C4538a.POST_CONSUME, "https://" + this.f7311a.mo34204f() + "/fp/clear.png", this.f7311a.mo34213q(), this.f7311a.mo34212p(), this, this.f7314l, this.f7312b));
                    THMStatusCode status = m8239a(true);
                    this.f7311a.mo34188a(status);
                    if (status != THMStatusCode.THM_OK) {
                        C4549w.m8592b(f7289d, "Received " + status.getDesc() + " error, profiling will be incomplete");
                        this.f7311a.mo34188a(THMStatusCode.THM_PartialProfile);
                    } else {
                        this.f7298B = System.currentTimeMillis();
                    }
                    m8252e();
                    C4549w.m8594c(f7289d, "profile request complete");
                    if (this.f7312b.mo34114a()) {
                        this.f7311a.mo34188a(THMStatusCode.THM_Interrupted_Error);
                        Thread.interrupted();
                    }
                    result = getResult();
                    notifier = this.f7325w;
                    this.f7311a.f7530X = System.currentTimeMillis() - this.f7311a.f7529W;
                    this.f7312b.mo34123h();
                    f7291f.execute(new C4468a(result, notifier));
                    return;
                }
            }
            C4549w.m8594c(f7289d, "Not inited");
            throw new IllegalArgumentException("Not inited");
        } catch (InterruptedException e) {
            if (this.f7312b.mo34114a()) {
                C4549w.m8594c(f7289d, "profile request interrupted due to cancel");
            } else {
                C4549w.m8595c(f7289d, "profile request interrupted", e);
            }
            this.f7311a.mo34188a(THMStatusCode.THM_Internal_Error);
            if (this.f7312b.mo34114a()) {
                this.f7311a.mo34188a(THMStatusCode.THM_Interrupted_Error);
                Thread.interrupted();
            }
            result = getResult();
            notifier = this.f7325w;
            this.f7311a.f7530X = System.currentTimeMillis() - this.f7311a.f7529W;
            this.f7312b.mo34123h();
            f7291f.execute(new C4468a(result, notifier));
        } catch (Exception e2) {
            this.f7311a.mo34188a(THMStatusCode.THM_Internal_Error);
            C4549w.m8595c(f7289d, "profile request failed", e2);
            if (this.f7312b.mo34114a()) {
                this.f7311a.mo34188a(THMStatusCode.THM_Interrupted_Error);
                Thread.interrupted();
            }
            result = getResult();
            notifier = this.f7325w;
            this.f7311a.f7530X = System.currentTimeMillis() - this.f7311a.f7529W;
            this.f7312b.mo34123h();
            f7291f.execute(new C4468a(result, notifier));
        } catch (Throwable th) {
            if (this.f7312b.mo34114a()) {
                this.f7311a.mo34188a(THMStatusCode.THM_Interrupted_Error);
                Thread.interrupted();
            }
            result = getResult();
            notifier = this.f7325w;
            this.f7311a.f7530X = System.currentTimeMillis() - this.f7311a.f7529W;
            this.f7312b.mo34123h();
            f7291f.execute(new C4468a(result, notifier));
            throw th;
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: b */
    public final void mo34093b() {
        if (NativeGatherer.m8207a().mo34053b()) {
            this.f7326x = new C4493ak();
            try {
                String temp = NativeGatherer.m8207a().mo34055c("enableOptions");
                if (temp != null) {
                    this.f7326x.mo34139a(Long.parseLong(temp));
                }
                temp = NativeGatherer.m8207a().mo34055c("disableOptions");
                if (temp != null) {
                    this.f7326x.mo34143b(Long.parseLong(temp));
                }
                temp = NativeGatherer.m8207a().mo34055c("quietPeriod");
                if (temp != null) {
                    this.f7326x.mo34138a(Integer.parseInt(temp));
                }
                temp = NativeGatherer.m8207a().mo34055c("sdkVersion");
                if (temp != null) {
                    this.f7326x.mo34140a(temp);
                } else {
                    this.f7326x = null;
                }
            } catch (NumberFormatException e) {
                C4549w.m8595c(f7289d, "Options/ quietPeriod are not a number", e);
                this.f7326x = null;
            } catch (InterruptedException e2) {
                C4549w.m8595c(f7289d, "Interrupted", e2);
                this.f7326x = null;
            }
        }
        if (this.f7326x == null || !this.f7326x.mo34144c().equals("4.0-90")) {
            C4528l settings = new C4528l(this.f7314l, this.f7323u, 0);
            try {
                this.f7326x = new C4493ak();
                this.f7326x.mo34139a(settings.mo34230a("enableOptions", 0));
                this.f7326x.mo34143b(settings.mo34230a("disableOptions", 0));
                this.f7326x.mo34140a(settings.mo34231a("sdkVersion", ""));
                this.f7326x.mo34138a(settings.mo34229a("quietPeriod", 0));
            } catch (ClassCastException e3) {
                C4549w.m8595c(f7289d, "Found preference of different type", e3);
                this.f7326x = null;
            }
        }
        if (this.f7326x != null && !this.f7326x.mo34144c().equals("4.0-90")) {
            this.f7326x = null;
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo34091a(THMStatusCode status) {
        this.f7311a.mo34188a(status);
    }

    /* renamed from: a */
    private THMStatusCode m8239a(boolean failedOkay) throws InterruptedException {
        THMStatusCode statusCode = THMStatusCode.THM_NotYet;
        try {
            this.f7306J.lockInterruptibly();
            Iterator i$ = this.f7304H.iterator();
            while (i$.hasNext()) {
                Thread t = (C4552y) i$.next();
                if (this.f7312b.mo34114a() || Thread.currentThread().isInterrupted()) {
                    statusCode = THMStatusCode.THM_Interrupted_Error;
                    break;
                }
                t.join((long) this.f7318p);
                if (t.getState() != State.TERMINATED) {
                    C4549w.m8587a(f7289d, "Connection hasn't completed before the timeout expired, aborting");
                    statusCode = THMStatusCode.THM_Connection_Error;
                    if (!failedOkay) {
                        m8246b(true);
                        break;
                    }
                    m8243a(t);
                } else {
                    C4537o runner = t.mo34268a();
                    if (runner != null) {
                        THMStatusCode tempStatus = t.mo34268a().mo34244a();
                        if (tempStatus == THMStatusCode.THM_OK && runner.mo34245b() != 200) {
                            C4549w.m8594c(f7289d, "Connection returned http status code:" + runner.mo34245b());
                            statusCode = THMStatusCode.THM_Connection_Error;
                            if (!failedOkay) {
                                m8246b(true);
                                break;
                            }
                        } else if (tempStatus != THMStatusCode.THM_OK) {
                            C4549w.m8594c(f7289d, "Connection returned status :" + runner.mo34244a().getDesc());
                            statusCode = tempStatus;
                            if (!failedOkay) {
                                m8246b(true);
                                break;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
            }
        } catch (InterruptedException e) {
            if (!this.f7312b.mo34114a()) {
                if (statusCode == THMStatusCode.THM_NotYet) {
                    statusCode = THMStatusCode.THM_Connection_Error;
                }
                m8246b(true);
                C4549w.m8595c(f7289d, "thread join: this thread = TID " + Thread.currentThread().getId(), e);
            }
        } catch (Throwable th) {
            this.f7306J.unlock();
        }
        this.f7306J.unlock();
        if (statusCode == THMStatusCode.THM_NotYet) {
            return THMStatusCode.THM_OK;
        }
        return statusCode;
    }

    /* renamed from: b */
    private void m8246b(boolean listLocked) {
        if (!listLocked) {
            try {
                this.f7306J.lock();
            } catch (Throwable th) {
                if (!listLocked) {
                    this.f7306J.unlock();
                }
            }
        }
        Iterator i$ = this.f7304H.iterator();
        while (i$.hasNext()) {
            m8243a((C4552y) i$.next());
        }
        if (!listLocked) {
            this.f7306J.unlock();
        }
    }

    /* renamed from: a */
    private void m8243a(Thread t) {
        f7291f.execute(new C4469b(t));
    }

    /* renamed from: d */
    private void m8251d() {
        this.f7311a.mo34183a();
        this.f7308L.mo34174a();
    }

    /* renamed from: e */
    private void m8252e() throws InterruptedException {
        try {
            this.f7307K.lockInterruptibly();
            this.f7304H.clear();
        } finally {
            this.f7307K.unlock();
        }
    }

    /* renamed from: a */
    private C4552y m8241a(Runnable r) {
        if (r == null) {
            return null;
        }
        if (this.f7312b.mo34114a()) {
            return null;
        }
        try {
            C4552y t = new C4552y(r);
            if (r instanceof C4537o) {
                C4549w.m8594c(f7289d, "Adding thread ID: " + t.getId() + " for: " + ((C4537o) r).f7791c);
                this.f7307K.lock();
                this.f7304H.add(t);
                this.f7307K.unlock();
            }
            t.start();
            return t;
        } catch (RuntimeException e) {
            String str = f7289d;
            return null;
        } catch (Throwable th) {
            this.f7307K.unlock();
        }
    }

    /* renamed from: f */
    private boolean m8255f() {
        if (!C4526j.m8507a()) {
            return true;
        }
        if (C4516a.f7584c >= C4517b.f7598m) {
            return C4545r.m8575a(this.f7314l);
        }
        try {
            Object powerService = this.f7314l.getSystemService("power");
            if (powerService == null || !(powerService instanceof PowerManager)) {
                return true;
            }
            return ((PowerManager) powerService).isScreenOn();
        } catch (SecurityException e) {
            String str = f7289d;
            return true;
        } catch (Exception e2) {
            C4549w.m8594c(f7289d, e2.getMessage());
            return true;
        }
    }

    /* renamed from: g */
    private boolean m8257g() {
        C4480am c4480am;
        Context context;
        if (f7294i) {
            this.f7313c = new C4556z();
            try {
                c4480am = this.f7313c;
                context = this.f7314l;
                c4480am.mo34111a(this.f7318p, this.f7311a.f7509C, true, true);
                return true;
            } catch (RuntimeException e) {
                if (e instanceof IllegalStateException) {
                    C4549w.m8587a(f7289d, "Failed to build OkHttp3 client, most probably because of TLS factory");
                    if (f7295j) {
                        String str = f7289d;
                    } else {
                        C4549w.m8594c(f7289d, "Okhttp2 is not available going to okhttp3 without TLS");
                        try {
                            c4480am = this.f7313c;
                            context = this.f7314l;
                            c4480am.mo34111a(this.f7318p, this.f7311a.f7509C, true, false);
                            return true;
                        } catch (RuntimeException e2) {
                            C4549w.m8594c(f7289d, "Failed to build OkHttp3 client even without TLS factory");
                        }
                    }
                } else {
                    C4549w.m8594c(f7289d, "Failed to build OkHttp3 client");
                }
            }
        }
        if (f7295j) {
            this.f7313c = new C4481ab();
            try {
                c4480am = this.f7313c;
                context = this.f7314l;
                c4480am.mo34111a(this.f7318p, this.f7311a.f7509C, true, true);
                return true;
            } catch (RuntimeException e3) {
                C4549w.m8594c(f7289d, "Failed to build okhttp2 client, init failed.");
            }
        } else {
            C4549w.m8587a(f7289d, "OkHttp3 and okHttp2 libraries can't be found aborting init()");
            return false;
        }
    }
}
