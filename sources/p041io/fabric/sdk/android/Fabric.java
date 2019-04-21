package p041io.fabric.sdk.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import p041io.fabric.sdk.android.ActivityLifecycleManager.Callbacks;
import p041io.fabric.sdk.android.services.common.DataCollectionArbiter;
import p041io.fabric.sdk.android.services.common.IdManager;
import p041io.fabric.sdk.android.services.concurrency.DependsOn;
import p041io.fabric.sdk.android.services.concurrency.PriorityThreadPoolExecutor;
import p041io.fabric.sdk.android.services.concurrency.Task;
import p041io.fabric.sdk.android.services.concurrency.UnmetDependencyException;

/* renamed from: io.fabric.sdk.android.Fabric */
public class Fabric {
    static final Logger DEFAULT_LOGGER = new DefaultLogger();
    static volatile Fabric singleton;
    private WeakReference<Activity> activity;
    private ActivityLifecycleManager activityLifecycleManager;
    private final Context context;
    final boolean debuggable;
    private final ExecutorService executorService;
    private final IdManager idManager;
    private final InitializationCallback<Fabric> initializationCallback;
    private AtomicBoolean initialized = new AtomicBoolean(false);
    private final InitializationCallback<?> kitInitializationCallback;
    private final Map<Class<? extends Kit>, Kit> kits;
    final Logger logger;
    private final Handler mainHandler;

    /* renamed from: io.fabric.sdk.android.Fabric$1 */
    class C26261 extends Callbacks {
        C26261() {
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            Fabric.this.setCurrentActivity(activity);
        }

        public void onActivityStarted(Activity activity) {
            Fabric.this.setCurrentActivity(activity);
        }

        public void onActivityResumed(Activity activity) {
            Fabric.this.setCurrentActivity(activity);
        }
    }

    /* renamed from: io.fabric.sdk.android.Fabric$Builder */
    public static class Builder {
        private String appIdentifier;
        private String appInstallIdentifier;
        private final Context context;
        private boolean debuggable;
        private Handler handler;
        private InitializationCallback<Fabric> initializationCallback;
        private Kit[] kits;
        private Logger logger;
        private PriorityThreadPoolExecutor threadPoolExecutor;

        public Builder(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.context = context;
        }

        public Builder kits(Kit... kits) {
            if (this.kits != null) {
                throw new IllegalStateException("Kits already set.");
            }
            if (!DataCollectionArbiter.getInstance(this.context).isDataCollectionEnabled()) {
                List<Kit> whitelistedKits = new ArrayList();
                boolean warned = false;
                for (Kit kit : kits) {
                    String identifier = kit.getIdentifier();
                    int i = -1;
                    switch (identifier.hashCode()) {
                        case 607220212:
                            if (identifier.equals("com.crashlytics.sdk.android:answers")) {
                                i = 1;
                                break;
                            }
                            break;
                        case 1830452504:
                            if (identifier.equals("com.crashlytics.sdk.android:crashlytics")) {
                                i = 0;
                                break;
                            }
                            break;
                    }
                    switch (i) {
                        case 0:
                        case 1:
                            whitelistedKits.add(kit);
                            break;
                        default:
                            if (!warned) {
                                warned = true;
                                Fabric.getLogger().mo34411w("Fabric", "Fabric will not initialize any kits when Firebase automatic data collection is disabled; to use Third-party kits with automatic data collection disabled, initialize these kits via non-Fabric means.");
                                break;
                            }
                            break;
                    }
                }
                kits = (Kit[]) whitelistedKits.toArray(new Kit[0]);
            }
            this.kits = kits;
            return this;
        }

        public Fabric build() {
            Map<Class<? extends Kit>, Kit> kitMap;
            if (this.threadPoolExecutor == null) {
                this.threadPoolExecutor = PriorityThreadPoolExecutor.create();
            }
            if (this.handler == null) {
                this.handler = new Handler(Looper.getMainLooper());
            }
            if (this.logger == null) {
                if (this.debuggable) {
                    this.logger = new DefaultLogger(3);
                } else {
                    this.logger = new DefaultLogger();
                }
            }
            if (this.appIdentifier == null) {
                this.appIdentifier = this.context.getPackageName();
            }
            if (this.initializationCallback == null) {
                this.initializationCallback = InitializationCallback.EMPTY;
            }
            if (this.kits == null) {
                kitMap = new HashMap();
            } else {
                kitMap = Fabric.getKitMap(Arrays.asList(this.kits));
            }
            Context appContext = this.context.getApplicationContext();
            return new Fabric(appContext, kitMap, this.threadPoolExecutor, this.handler, this.logger, this.debuggable, this.initializationCallback, new IdManager(appContext, this.appIdentifier, this.appInstallIdentifier, kitMap.values()), Fabric.extractActivity(this.context));
        }
    }

    static Fabric singleton() {
        if (singleton != null) {
            return singleton;
        }
        throw new IllegalStateException("Must Initialize Fabric before using singleton()");
    }

    Fabric(Context context, Map<Class<? extends Kit>, Kit> kits, PriorityThreadPoolExecutor threadPoolExecutor, Handler mainHandler, Logger logger, boolean debuggable, InitializationCallback callback, IdManager idManager, Activity rootActivity) {
        this.context = context;
        this.kits = kits;
        this.executorService = threadPoolExecutor;
        this.mainHandler = mainHandler;
        this.logger = logger;
        this.debuggable = debuggable;
        this.initializationCallback = callback;
        this.kitInitializationCallback = createKitInitializationCallback(kits.size());
        this.idManager = idManager;
        setCurrentActivity(rootActivity);
    }

    public static Fabric with(Context context, Kit... kits) {
        if (singleton == null) {
            synchronized (Fabric.class) {
                if (singleton == null) {
                    Fabric.setFabric(new Builder(context).kits(kits).build());
                }
            }
        }
        return singleton;
    }

    private static void setFabric(Fabric fabric) {
        singleton = fabric;
        fabric.init();
    }

    public Fabric setCurrentActivity(Activity activity) {
        this.activity = new WeakReference(activity);
        return this;
    }

    public Activity getCurrentActivity() {
        if (this.activity != null) {
            return (Activity) this.activity.get();
        }
        return null;
    }

    private void init() {
        this.activityLifecycleManager = new ActivityLifecycleManager(this.context);
        this.activityLifecycleManager.registerCallbacks(new C26261());
        initializeKits(this.context);
    }

    public String getVersion() {
        return "1.4.8.32";
    }

    public String getIdentifier() {
        return "io.fabric.sdk.android:fabric";
    }

    /* Access modifiers changed, original: 0000 */
    public void initializeKits(Context context) {
        StringBuilder initInfo;
        Future<Map<String, KitInfo>> installedKitsFuture = getKitsFinderFuture(context);
        Collection<Kit> providedKits = getKits();
        Onboarding onboarding = new Onboarding(installedKitsFuture, providedKits);
        List<Kit> kits = new ArrayList(providedKits);
        Collections.sort(kits);
        onboarding.injectParameters(context, this, InitializationCallback.EMPTY, this.idManager);
        for (Kit kit : kits) {
            kit.injectParameters(context, this, this.kitInitializationCallback, this.idManager);
        }
        onboarding.initialize();
        if (Fabric.getLogger().isLoggable("Fabric", 3)) {
            initInfo = new StringBuilder("Initializing ").append(getIdentifier()).append(" [Version: ").append(getVersion()).append("], with the following kits:\n");
        } else {
            initInfo = null;
        }
        for (Kit kit2 : kits) {
            kit2.initializationTask.addDependency((Task) onboarding.initializationTask);
            addAnnotatedDependencies(this.kits, kit2);
            kit2.initialize();
            if (initInfo != null) {
                initInfo.append(kit2.getIdentifier()).append(" [Version: ").append(kit2.getVersion()).append("]\n");
            }
        }
        if (initInfo != null) {
            Fabric.getLogger().mo34403d("Fabric", initInfo.toString());
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void addAnnotatedDependencies(Map<Class<? extends Kit>, Kit> kits, Kit dependentKit) {
        DependsOn dependsOn = dependentKit.dependsOnAnnotation;
        if (dependsOn != null) {
            for (Class<?> dependency : dependsOn.value()) {
                if (dependency.isInterface()) {
                    for (Kit kit : kits.values()) {
                        if (dependency.isAssignableFrom(kit.getClass())) {
                            dependentKit.initializationTask.addDependency((Task) kit.initializationTask);
                        }
                    }
                } else if (((Kit) kits.get(dependency)) == null) {
                    throw new UnmetDependencyException("Referenced Kit was null, does the kit exist?");
                } else {
                    dependentKit.initializationTask.addDependency((Task) ((Kit) kits.get(dependency)).initializationTask);
                }
            }
        }
    }

    private static Activity extractActivity(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return null;
    }

    public ExecutorService getExecutorService() {
        return this.executorService;
    }

    public Collection<Kit> getKits() {
        return this.kits.values();
    }

    public static <T extends Kit> T getKit(Class<T> cls) {
        return (Kit) Fabric.singleton().kits.get(cls);
    }

    public static Logger getLogger() {
        if (singleton == null) {
            return DEFAULT_LOGGER;
        }
        return singleton.logger;
    }

    public static boolean isDebuggable() {
        if (singleton == null) {
            return false;
        }
        return singleton.debuggable;
    }

    private static Map<Class<? extends Kit>, Kit> getKitMap(Collection<? extends Kit> kits) {
        HashMap<Class<? extends Kit>, Kit> map = new HashMap(kits.size());
        Fabric.addToKitMap(map, kits);
        return map;
    }

    private static void addToKitMap(Map<Class<? extends Kit>, Kit> map, Collection<? extends Kit> kits) {
        for (Kit kit : kits) {
            map.put(kit.getClass(), kit);
            if (kit instanceof KitGroup) {
                Fabric.addToKitMap(map, ((KitGroup) kit).getKits());
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public InitializationCallback<?> createKitInitializationCallback(final int size) {
        return new InitializationCallback() {
            final CountDownLatch kitInitializedLatch = new CountDownLatch(size);

            public void success(Object o) {
                this.kitInitializedLatch.countDown();
                if (this.kitInitializedLatch.getCount() == 0) {
                    Fabric.this.initialized.set(true);
                    Fabric.this.initializationCallback.success(Fabric.this);
                }
            }

            public void failure(Exception exception) {
                Fabric.this.initializationCallback.failure(exception);
            }
        };
    }

    /* Access modifiers changed, original: 0000 */
    public Future<Map<String, KitInfo>> getKitsFinderFuture(Context context) {
        return getExecutorService().submit(new FabricKitsFinder(context.getPackageCodePath()));
    }
}
