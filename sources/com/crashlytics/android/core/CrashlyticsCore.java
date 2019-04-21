package com.crashlytics.android.core;

import android.content.Context;
import android.util.Log;
import com.crashlytics.android.answers.AppMeasurementEventLogger;
import com.crashlytics.android.answers.EventLogger;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import p041io.fabric.sdk.android.Fabric;
import p041io.fabric.sdk.android.Kit;
import p041io.fabric.sdk.android.services.common.ApiKey;
import p041io.fabric.sdk.android.services.common.CommonUtils;
import p041io.fabric.sdk.android.services.common.DataCollectionArbiter;
import p041io.fabric.sdk.android.services.common.ExecutorUtils;
import p041io.fabric.sdk.android.services.common.FirebaseInfo;
import p041io.fabric.sdk.android.services.common.IdManager;
import p041io.fabric.sdk.android.services.concurrency.DependsOn;
import p041io.fabric.sdk.android.services.concurrency.Priority;
import p041io.fabric.sdk.android.services.concurrency.PriorityCallable;
import p041io.fabric.sdk.android.services.concurrency.Task;
import p041io.fabric.sdk.android.services.concurrency.UnmetDependencyException;
import p041io.fabric.sdk.android.services.network.DefaultHttpRequestFactory;
import p041io.fabric.sdk.android.services.network.HttpRequestFactory;
import p041io.fabric.sdk.android.services.persistence.FileStore;
import p041io.fabric.sdk.android.services.persistence.FileStoreImpl;
import p041io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;
import p041io.fabric.sdk.android.services.settings.Settings;
import p041io.fabric.sdk.android.services.settings.SettingsData;

@DependsOn({CrashlyticsNdkDataProvider.class})
public class CrashlyticsCore extends Kit<Void> {
    private final ConcurrentHashMap<String, String> attributes;
    private CrashlyticsBackgroundWorker backgroundWorker;
    private CrashlyticsController controller;
    private CrashlyticsFileMarker crashMarker;
    private CrashlyticsNdkDataProvider crashlyticsNdkDataProvider;
    private float delay;
    private boolean disabled;
    private HttpRequestFactory httpRequestFactory;
    private CrashlyticsFileMarker initializationMarker;
    private CrashlyticsListener listener;
    private final PinningInfoProvider pinningInfo;
    private final long startTime;
    private String userEmail;
    private String userId;
    private String userName;

    /* renamed from: com.crashlytics.android.core.CrashlyticsCore$1 */
    class C16691 extends PriorityCallable<Void> {
        C16691() {
        }

        public Void call() throws Exception {
            return CrashlyticsCore.this.doInBackground();
        }

        public Priority getPriority() {
            return Priority.IMMEDIATE;
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsCore$2 */
    class C16702 implements Callable<Void> {
        C16702() {
        }

        public Void call() throws Exception {
            CrashlyticsCore.this.initializationMarker.create();
            Fabric.getLogger().mo34403d("CrashlyticsCore", "Initialization marker file created.");
            return null;
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsCore$3 */
    class C16713 implements Callable<Boolean> {
        C16713() {
        }

        public Boolean call() throws Exception {
            try {
                boolean removed = CrashlyticsCore.this.initializationMarker.remove();
                Fabric.getLogger().mo34403d("CrashlyticsCore", "Initialization marker file removed: " + removed);
                return Boolean.valueOf(removed);
            } catch (Exception e) {
                Fabric.getLogger().mo34406e("CrashlyticsCore", "Problem encountered deleting Crashlytics initialization marker.", e);
                return Boolean.valueOf(false);
            }
        }
    }

    public static class Builder {
        private float delay = -1.0f;
        private boolean disabled = false;
    }

    private static final class CrashMarkerCheck implements Callable<Boolean> {
        private final CrashlyticsFileMarker crashMarker;

        public CrashMarkerCheck(CrashlyticsFileMarker crashMarker) {
            this.crashMarker = crashMarker;
        }

        public Boolean call() throws Exception {
            if (!this.crashMarker.isPresent()) {
                return Boolean.FALSE;
            }
            Fabric.getLogger().mo34403d("CrashlyticsCore", "Found previous crash marker.");
            this.crashMarker.remove();
            return Boolean.TRUE;
        }
    }

    private static final class NoOpListener implements CrashlyticsListener {
        private NoOpListener() {
        }

        /* synthetic */ NoOpListener(C16691 x0) {
            this();
        }

        public void crashlyticsDidDetectCrashDuringPreviousExecution() {
        }
    }

    public CrashlyticsCore() {
        this(1.0f, null, null, false);
    }

    CrashlyticsCore(float delay, CrashlyticsListener listener, PinningInfoProvider pinningInfo, boolean disabled) {
        this(delay, listener, pinningInfo, disabled, ExecutorUtils.buildSingleThreadExecutorService("Crashlytics Exception Handler"));
    }

    CrashlyticsCore(float delay, CrashlyticsListener listener, PinningInfoProvider pinningInfo, boolean disabled, ExecutorService crashHandlerExecutor) {
        this.userId = null;
        this.userEmail = null;
        this.userName = null;
        this.delay = delay;
        if (listener == null) {
            listener = new NoOpListener();
        }
        this.listener = listener;
        this.pinningInfo = pinningInfo;
        this.disabled = disabled;
        this.backgroundWorker = new CrashlyticsBackgroundWorker(crashHandlerExecutor);
        this.attributes = new ConcurrentHashMap();
        this.startTime = System.currentTimeMillis();
    }

    /* Access modifiers changed, original: protected */
    public boolean onPreExecute() {
        String googlePlaySdkVersionString = "!SDK-VERSION-STRING!:com.crashlytics.sdk.android:crashlytics-core:2.6.8.32";
        return onPreExecute(super.getContext());
    }

    /* Access modifiers changed, original: 0000 */
    public boolean onPreExecute(Context context) {
        if (!DataCollectionArbiter.getInstance(context).isDataCollectionEnabled()) {
            Fabric.getLogger().mo34403d("CrashlyticsCore", "Crashlytics is disabled, because data collection is disabled by Firebase.");
            this.disabled = true;
        }
        if (this.disabled) {
            return false;
        }
        String apiKey = new ApiKey().getValue(context);
        if (apiKey == null) {
            return false;
        }
        String buildId = CommonUtils.resolveBuildId(context);
        if (isBuildIdValid(buildId, CommonUtils.getBooleanResourceValue(context, "com.crashlytics.RequireBuildId", true))) {
            try {
                CrashlyticsPinningInfoProvider infoProvider;
                Fabric.getLogger().mo34407i("CrashlyticsCore", "Initializing Crashlytics " + getVersion());
                FileStore fileStore = new FileStoreImpl(this);
                this.crashMarker = new CrashlyticsFileMarker("crash_marker", fileStore);
                this.initializationMarker = new CrashlyticsFileMarker("initialization_marker", fileStore);
                PreferenceManager preferenceManager = PreferenceManager.create(new PreferenceStoreImpl(getContext(), "com.crashlytics.android.core.CrashlyticsCore"), this);
                if (this.pinningInfo != null) {
                    CrashlyticsPinningInfoProvider crashlyticsPinningInfoProvider = new CrashlyticsPinningInfoProvider(this.pinningInfo);
                } else {
                    infoProvider = null;
                }
                this.httpRequestFactory = new DefaultHttpRequestFactory(Fabric.getLogger());
                this.httpRequestFactory.setPinningInfoProvider(infoProvider);
                IdManager idManager = getIdManager();
                AppData appData = AppData.create(context, idManager, apiKey, buildId);
                UnityVersionProvider unityVersionProvider = new ResourceUnityVersionProvider(context, new ManifestUnityVersionProvider(context, appData.packageName));
                AppMeasurementEventListenerRegistrar appMeasurementEventListenerRegistrar = new DefaultAppMeasurementEventListenerRegistrar(this);
                EventLogger firebaseAnalyticsLogger = AppMeasurementEventLogger.getEventLogger(context);
                Fabric.getLogger().mo34403d("CrashlyticsCore", "Installer package name is: " + appData.installerPackageName);
                this.controller = new CrashlyticsController(this, this.backgroundWorker, this.httpRequestFactory, idManager, preferenceManager, fileStore, appData, unityVersionProvider, appMeasurementEventListenerRegistrar, firebaseAnalyticsLogger);
                boolean initializeSynchronously = didPreviousInitializationFail();
                checkForPreviousCrash();
                this.controller.enableExceptionHandling(Thread.getDefaultUncaughtExceptionHandler(), new FirebaseInfo().isFirebaseCrashlyticsEnabled(context));
                if (initializeSynchronously && CommonUtils.canTryConnection(context)) {
                    Fabric.getLogger().mo34403d("CrashlyticsCore", "Crashlytics did not finish previous background initialization. Initializing synchronously.");
                    finishInitSynchronously();
                    return false;
                }
                Fabric.getLogger().mo34403d("CrashlyticsCore", "Exception handling initialization successful");
                return true;
            } catch (Exception e) {
                Fabric.getLogger().mo34406e("CrashlyticsCore", "Crashlytics was not started due to an exception during initialization", e);
                this.controller = null;
                return false;
            }
        }
        throw new UnmetDependencyException("The Crashlytics build ID is missing. This occurs when Crashlytics tooling is absent from your app's build configuration. Please review Crashlytics onboarding instructions and ensure you have a valid Crashlytics account.");
    }

    /* Access modifiers changed, original: protected */
    public Void doInBackground() {
        markInitializationStarted();
        this.controller.cleanInvalidTempFiles();
        try {
            this.controller.registerDevicePowerStateListener();
            SettingsData settingsData = Settings.getInstance().awaitSettingsData();
            if (settingsData == null) {
                Fabric.getLogger().mo34411w("CrashlyticsCore", "Received null settings, skipping report submission!");
            } else {
                this.controller.registerAnalyticsEventListener(settingsData);
                if (!settingsData.featuresData.collectReports) {
                    Fabric.getLogger().mo34403d("CrashlyticsCore", "Collection of crash reports disabled in Crashlytics settings.");
                    markInitializationComplete();
                } else if (DataCollectionArbiter.getInstance(getContext()).isDataCollectionEnabled()) {
                    CrashlyticsNdkData ndkData = getNativeCrashData();
                    if (!(ndkData == null || this.controller.finalizeNativeReport(ndkData))) {
                        Fabric.getLogger().mo34403d("CrashlyticsCore", "Could not finalize previous NDK sessions.");
                    }
                    if (!this.controller.finalizeSessions(settingsData.sessionData)) {
                        Fabric.getLogger().mo34403d("CrashlyticsCore", "Could not finalize previous sessions.");
                    }
                    this.controller.submitAllReports(this.delay, settingsData);
                    markInitializationComplete();
                } else {
                    Fabric.getLogger().mo34403d("CrashlyticsCore", "Automatic collection of crash reports disabled by Firebase settings.");
                    markInitializationComplete();
                }
            }
        } catch (Exception e) {
            Fabric.getLogger().mo34406e("CrashlyticsCore", "Crashlytics encountered a problem during asynchronous initialization.", e);
        } finally {
            markInitializationComplete();
        }
        return null;
    }

    public String getIdentifier() {
        return "com.crashlytics.sdk.android.crashlytics-core";
    }

    public String getVersion() {
        return "2.6.8.32";
    }

    public static CrashlyticsCore getInstance() {
        return (CrashlyticsCore) Fabric.getKit(CrashlyticsCore.class);
    }

    public void log(String msg) {
        doLog(3, "CrashlyticsCore", msg);
    }

    private void doLog(int priority, String tag, String msg) {
        if (!this.disabled && ensureFabricWithCalled("prior to logging messages.")) {
            this.controller.writeToLog(System.currentTimeMillis() - this.startTime, formatLogMessage(priority, tag, msg));
        }
    }

    /* Access modifiers changed, original: 0000 */
    public Map<String, String> getAttributes() {
        return Collections.unmodifiableMap(this.attributes);
    }

    /* Access modifiers changed, original: 0000 */
    public String getUserIdentifier() {
        return getIdManager().canCollectUserIds() ? this.userId : null;
    }

    /* Access modifiers changed, original: 0000 */
    public String getUserEmail() {
        return getIdManager().canCollectUserIds() ? this.userEmail : null;
    }

    /* Access modifiers changed, original: 0000 */
    public String getUserName() {
        return getIdManager().canCollectUserIds() ? this.userName : null;
    }

    private void finishInitSynchronously() {
        PriorityCallable<Void> callable = new C16691();
        for (Task task : getDependencies()) {
            callable.addDependency(task);
        }
        Future<Void> future = getFabric().getExecutorService().submit(callable);
        Fabric.getLogger().mo34403d("CrashlyticsCore", "Crashlytics detected incomplete initialization on previous app launch. Will initialize synchronously.");
        try {
            future.get(4, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Fabric.getLogger().mo34406e("CrashlyticsCore", "Crashlytics was interrupted during initialization.", e);
        } catch (ExecutionException e2) {
            Fabric.getLogger().mo34406e("CrashlyticsCore", "Problem encountered during Crashlytics initialization.", e2);
        } catch (TimeoutException e3) {
            Fabric.getLogger().mo34406e("CrashlyticsCore", "Crashlytics timed out during initialization.", e3);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void markInitializationStarted() {
        this.backgroundWorker.submitAndWait(new C16702());
    }

    /* Access modifiers changed, original: 0000 */
    public void markInitializationComplete() {
        this.backgroundWorker.submit(new C16713());
    }

    /* Access modifiers changed, original: 0000 */
    public boolean didPreviousInitializationFail() {
        return this.initializationMarker.isPresent();
    }

    /* Access modifiers changed, original: 0000 */
    public CrashlyticsNdkData getNativeCrashData() {
        if (this.crashlyticsNdkDataProvider != null) {
            return this.crashlyticsNdkDataProvider.getCrashlyticsNdkData();
        }
        return null;
    }

    private void checkForPreviousCrash() {
        if (Boolean.TRUE.equals((Boolean) this.backgroundWorker.submitAndWait(new CrashMarkerCheck(this.crashMarker)))) {
            try {
                this.listener.crashlyticsDidDetectCrashDuringPreviousExecution();
            } catch (Exception e) {
                Fabric.getLogger().mo34406e("CrashlyticsCore", "Exception thrown by CrashlyticsListener while notifying of previous crash.", e);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void createCrashMarker() {
        this.crashMarker.create();
    }

    private static String formatLogMessage(int priority, String tag, String msg) {
        return CommonUtils.logPriorityToString(priority) + "/" + tag + " " + msg;
    }

    private static boolean ensureFabricWithCalled(String msg) {
        CrashlyticsCore instance = getInstance();
        if (instance != null && instance.controller != null) {
            return true;
        }
        Fabric.getLogger().mo34406e("CrashlyticsCore", "Crashlytics must be initialized by calling Fabric.with(Context) " + msg, null);
        return false;
    }

    static boolean isBuildIdValid(String buildId, boolean requiresBuildId) {
        if (!requiresBuildId) {
            Fabric.getLogger().mo34403d("CrashlyticsCore", "Configured not to require a build ID.");
            return true;
        } else if (!CommonUtils.isNullOrEmpty(buildId)) {
            return true;
        } else {
            Log.e("CrashlyticsCore", ".");
            Log.e("CrashlyticsCore", ".     |  | ");
            Log.e("CrashlyticsCore", ".     |  |");
            Log.e("CrashlyticsCore", ".     |  |");
            Log.e("CrashlyticsCore", ".   \\ |  | /");
            Log.e("CrashlyticsCore", ".    \\    /");
            Log.e("CrashlyticsCore", ".     \\  /");
            Log.e("CrashlyticsCore", ".      \\/");
            Log.e("CrashlyticsCore", ".");
            Log.e("CrashlyticsCore", "The Crashlytics build ID is missing. This occurs when Crashlytics tooling is absent from your app's build configuration. Please review Crashlytics onboarding instructions and ensure you have a valid Crashlytics account.");
            Log.e("CrashlyticsCore", ".");
            Log.e("CrashlyticsCore", ".      /\\");
            Log.e("CrashlyticsCore", ".     /  \\");
            Log.e("CrashlyticsCore", ".    /    \\");
            Log.e("CrashlyticsCore", ".   / |  | \\");
            Log.e("CrashlyticsCore", ".     |  |");
            Log.e("CrashlyticsCore", ".     |  |");
            Log.e("CrashlyticsCore", ".     |  |");
            Log.e("CrashlyticsCore", ".");
            return false;
        }
    }
}
