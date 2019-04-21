package com.newrelic.agent.android;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.os.StatFs;
import android.text.TextUtils;
import com.amap.api.location.LocationManagerProxy;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.analytics.AnalyticsControllerImpl;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.api.p047v2.TraceMachineInterface;
import com.newrelic.agent.android.api.p052v1.ConnectionEvent;
import com.newrelic.agent.android.api.p052v1.ConnectionListener;
import com.newrelic.agent.android.api.p052v1.DeviceForm;
import com.newrelic.agent.android.background.ApplicationStateEvent;
import com.newrelic.agent.android.background.ApplicationStateListener;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.harvest.AgentHealth;
import com.newrelic.agent.android.harvest.ApplicationInformation;
import com.newrelic.agent.android.harvest.ConnectInformation;
import com.newrelic.agent.android.harvest.DeviceInformation;
import com.newrelic.agent.android.harvest.EnvironmentInformation;
import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.instrumentation.MetricCategory;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.metric.MetricNames;
import com.newrelic.agent.android.metric.MetricUnit;
import com.newrelic.agent.android.payload.PayloadController;
import com.newrelic.agent.android.sample.MachineMeasurementConsumer;
import com.newrelic.agent.android.sample.Sampler;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.agent.android.stores.SharedPrefsAnalyticAttributeStore;
import com.newrelic.agent.android.stores.SharedPrefsCrashStore;
import com.newrelic.agent.android.stores.SharedPrefsPayloadStore;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.newrelic.agent.android.util.ActivityLifecycleBackgroundListener;
import com.newrelic.agent.android.util.AndroidEncoder;
import com.newrelic.agent.android.util.Connectivity;
import com.newrelic.agent.android.util.Encoder;
import com.newrelic.agent.android.util.PersistentUUID;
import com.newrelic.agent.android.util.Reachability;
import com.newrelic.agent.android.util.UiBackgroundListener;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import proguard.canary.NewRelicCanary;

public class AndroidAgentImpl implements AgentImpl, ConnectionListener, TraceMachineInterface, ApplicationStateListener {
    private static final float LOCATION_ACCURACY_THRESHOLD = 500.0f;
    private static final Comparator<TransactionData> cmp = new C26211();
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private final AgentConfiguration agentConfiguration;
    private ApplicationInformation applicationInformation;
    private final Context context;
    private DeviceInformation deviceInformation;
    private final Encoder encoder = new AndroidEncoder();
    private LocationListener locationListener;
    private final Lock lock = new ReentrantLock();
    private MachineMeasurementConsumer machineMeasurementConsumer;
    private SavedState savedState;

    /* renamed from: com.newrelic.agent.android.AndroidAgentImpl$1 */
    static class C26211 implements Comparator<TransactionData> {
        C26211() {
        }

        public int compare(TransactionData lhs, TransactionData rhs) {
            if (lhs.getTimestamp() > rhs.getTimestamp()) {
                return -1;
            }
            if (lhs.getTimestamp() < rhs.getTimestamp()) {
                return 1;
            }
            return 0;
        }
    }

    /* renamed from: com.newrelic.agent.android.AndroidAgentImpl$2 */
    class C26222 implements LocationListener {
        C26222() {
        }

        public void onLocationChanged(Location location) {
            if (AndroidAgentImpl.this.isAccurate(location)) {
                AndroidAgentImpl.this.setLocation(location);
            }
        }

        public void onProviderDisabled(String provider) {
            if ("passive".equals(provider)) {
                AndroidAgentImpl.this.removeLocationListener();
            }
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }

    public AndroidAgentImpl(Context context, AgentConfiguration agentConfiguration) throws AgentInitializationException {
        this.context = appContext(context);
        this.agentConfiguration = agentConfiguration;
        this.savedState = new SavedState(this.context);
        if (isDisabled()) {
            throw new AgentInitializationException("This version of the agent has been disabled");
        }
        initApplicationInformation();
        if (agentConfiguration.useLocationService() && this.context.getPackageManager().checkPermission("android.permission.ACCESS_FINE_LOCATION", getApplicationInformation().getPackageId()) == 0) {
            log.debug("Location stats enabled");
            addLocationListener();
        }
        TraceMachine.setTraceMachineInterface(this);
        agentConfiguration.setCrashStore(new SharedPrefsCrashStore(context));
        agentConfiguration.setPayloadStore(new SharedPrefsPayloadStore(context));
        agentConfiguration.setAnalyticAttributeStore(new SharedPrefsAnalyticAttributeStore(context));
        ApplicationStateMonitor.getInstance().addApplicationStateListener(this);
        if (VERSION.SDK_INT >= 14) {
            UiBackgroundListener backgroundListener;
            if (Agent.getUnityInstrumentationFlag().equals("YES")) {
                backgroundListener = new ActivityLifecycleBackgroundListener();
                if (backgroundListener instanceof ActivityLifecycleCallbacks) {
                    try {
                        if (context.getApplicationContext() instanceof Application) {
                            ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks((ActivityLifecycleCallbacks) backgroundListener);
                        }
                    } catch (Exception e) {
                    }
                }
            } else {
                backgroundListener = new UiBackgroundListener();
            }
            context.registerComponentCallbacks(backgroundListener);
            setupSession();
        }
    }

    /* Access modifiers changed, original: protected */
    public void initialize() {
        setupSession();
        AnalyticsControllerImpl.getInstance();
        AnalyticsControllerImpl.initialize(this.agentConfiguration, this);
        Harvest.addHarvestListener(this.savedState);
        Harvest.initialize(this.agentConfiguration);
        Harvest.setHarvestConfiguration(this.savedState.getHarvestConfiguration());
        Harvest.setHarvestConnectInformation(this.savedState.getConnectInformation());
        Measurements.initialize();
        log.info(MessageFormat.format("New Relic Agent v{0}", new Object[]{Agent.getVersion()}));
        log.verbose(MessageFormat.format("Application token: {0}", new Object[]{this.agentConfiguration.getApplicationToken()}));
        this.machineMeasurementConsumer = new MachineMeasurementConsumer();
        Measurements.addMeasurementConsumer(this.machineMeasurementConsumer);
        StatsEngine.get().inc("Supportability/AgentHealth/UncaughtExceptionHandler/" + getUnhandledExceptionHandlerName());
        PayloadController.initialize(this.agentConfiguration);
        Sampler.init(this.context);
    }

    /* Access modifiers changed, original: protected */
    public void setupSession() {
        TraceMachine.clearActivityHistory();
        this.agentConfiguration.provideSessionId();
    }

    /* Access modifiers changed, original: protected */
    public void finalizeSession() {
    }

    public boolean updateSavedConnectInformation() {
        ConnectInformation savedConnectInformation = this.savedState.getConnectInformation();
        ConnectInformation newConnectInformation = new ConnectInformation(getApplicationInformation(), getDeviceInformation());
        String savedAppToken = this.savedState.getAppToken();
        if (newConnectInformation.equals(savedConnectInformation) && this.agentConfiguration.getApplicationToken().equals(savedAppToken)) {
            return false;
        }
        if (newConnectInformation.getApplicationInformation().isAppUpgrade(savedConnectInformation.getApplicationInformation())) {
            StatsEngine.get().inc(MetricNames.MOBILE_APP_UPGRADE);
            AnalyticsControllerImpl.getInstance().addAttributeUnchecked(new AnalyticAttribute(AnalyticAttribute.APP_UPGRADE_ATTRIBUTE, savedConnectInformation.getApplicationInformation().getAppVersion()), false);
        }
        this.savedState.clear();
        this.savedState.saveConnectInformation(newConnectInformation);
        this.savedState.saveAppToken(this.agentConfiguration.getApplicationToken());
        return true;
    }

    public DeviceInformation getDeviceInformation() {
        if (this.deviceInformation != null) {
            return this.deviceInformation;
        }
        DeviceInformation info = new DeviceInformation();
        info.setOsName("Android");
        info.setOsVersion(VERSION.RELEASE);
        info.setOsBuild(VERSION.INCREMENTAL);
        info.setModel(Build.MODEL);
        info.setAgentName("AndroidAgent");
        info.setAgentVersion(Agent.getVersion());
        info.setManufacturer(Build.MANUFACTURER);
        info.setDeviceId(getUUID());
        info.setArchitecture(System.getProperty("os.arch"));
        info.setRunTime(System.getProperty("java.vm.version"));
        info.setSize(deviceForm(this.context).name().toLowerCase(Locale.getDefault()));
        info.setApplicationPlatform(this.agentConfiguration.getApplicationPlatform());
        info.setApplicationPlatformVersion(this.agentConfiguration.getApplicationPlatformVersion());
        this.deviceInformation = info;
        return this.deviceInformation;
    }

    public EnvironmentInformation getEnvironmentInformation() {
        EnvironmentInformation envInfo = new EnvironmentInformation();
        ActivityManager activityManager = (ActivityManager) this.context.getSystemService("activity");
        long[] free = new long[2];
        try {
            StatFs rootStatFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
            StatFs externalStatFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
            if (VERSION.SDK_INT >= 18) {
                free[0] = rootStatFs.getAvailableBlocksLong() * rootStatFs.getBlockSizeLong();
                free[1] = externalStatFs.getAvailableBlocksLong() * rootStatFs.getBlockSizeLong();
            } else {
                free[0] = (long) (rootStatFs.getAvailableBlocks() * rootStatFs.getBlockSize());
                free[1] = (long) (externalStatFs.getAvailableBlocks() * externalStatFs.getBlockSize());
            }
            if (free[0] < 0) {
                free[0] = 0;
            }
            if (free[1] < 0) {
                free[1] = 0;
            }
            envInfo.setDiskAvailable(free);
        } catch (Exception e) {
            AgentHealth.noticeException(e);
            if (free[0] < 0) {
                free[0] = 0;
            }
            if (free[1] < 0) {
                free[1] = 0;
            }
            envInfo.setDiskAvailable(free);
        } catch (Throwable th) {
            if (free[0] < 0) {
                free[0] = 0;
            }
            if (free[1] < 0) {
                free[1] = 0;
            }
            envInfo.setDiskAvailable(free);
            throw th;
        }
        envInfo.setMemoryUsage(Sampler.sampleMemory(activityManager).getSampleValue().asLong().longValue());
        envInfo.setOrientation(this.context.getResources().getConfiguration().orientation);
        envInfo.setNetworkStatus(getNetworkCarrier());
        envInfo.setNetworkWanType(getNetworkWanType());
        return envInfo;
    }

    public void initApplicationInformation() throws AgentInitializationException {
        if (this.applicationInformation != null) {
            log.debug("attempted to reinitialize ApplicationInformation.");
            return;
        }
        String packageName = this.context.getPackageName();
        PackageManager packageManager = this.context.getPackageManager();
        try {
            String appName;
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            String appVersion = this.agentConfiguration.getCustomApplicationVersion();
            if (TextUtils.isEmpty(appVersion)) {
                if (packageInfo == null || packageInfo.versionName == null || packageInfo.versionName.length() <= 0) {
                    throw new AgentInitializationException("Your app doesn't appear to have a version defined. Ensure you have defined 'versionName' in your manifest.");
                }
                appVersion = packageInfo.versionName;
            }
            log.debug("Using application version " + appVersion);
            try {
                ApplicationInfo info = packageManager.getApplicationInfo(packageName, 0);
                if (info != null) {
                    appName = packageManager.getApplicationLabel(info).toString();
                } else {
                    appName = packageName;
                }
            } catch (NameNotFoundException e) {
                log.warning(e.toString());
                appName = packageName;
            } catch (SecurityException e2) {
                log.warning(e2.toString());
                appName = packageName;
            }
            log.debug("Using application name " + appName);
            String build = this.agentConfiguration.getCustomBuildIdentifier();
            if (TextUtils.isEmpty(build)) {
                if (packageInfo != null) {
                    build = String.valueOf(packageInfo.versionCode);
                } else {
                    build = "";
                    log.warning("Your app doesn't appear to have a version code defined. Ensure you have defined 'versionCode' in your manifest.");
                }
            }
            log.debug("Using build " + build);
            this.applicationInformation = new ApplicationInformation(appName, appVersion, packageName, build);
            this.applicationInformation.setVersionCode(packageInfo.versionCode);
        } catch (NameNotFoundException e3) {
            throw new AgentInitializationException("Could not determine package version: " + e3.getMessage());
        }
    }

    public ApplicationInformation getApplicationInformation() {
        return this.applicationInformation;
    }

    public long getSessionDurationMillis() {
        return Harvest.getMillisSinceStart();
    }

    private static DeviceForm deviceForm(Context context) {
        int deviceSize = context.getResources().getConfiguration().screenLayout & 15;
        switch (deviceSize) {
            case 1:
                return DeviceForm.SMALL;
            case 2:
                return DeviceForm.NORMAL;
            case 3:
                return DeviceForm.LARGE;
            default:
                if (deviceSize > 3) {
                    return DeviceForm.XLARGE;
                }
                return DeviceForm.UNKNOWN;
        }
    }

    private static Context appContext(Context context) {
        if (context instanceof Application) {
            return context;
        }
        return context.getApplicationContext();
    }

    @Deprecated
    public void addTransactionData(TransactionData transactionData) {
    }

    @Deprecated
    public void mergeTransactionData(List<TransactionData> list) {
    }

    @Deprecated
    public List<TransactionData> getAndClearTransactionData() {
        return null;
    }

    public String getCrossProcessId() {
        this.lock.lock();
        try {
            String crossProcessId = this.savedState.getCrossProcessId();
            return crossProcessId;
        } finally {
            this.lock.unlock();
        }
    }

    public int getStackTraceLimit() {
        this.lock.lock();
        try {
            int stackTraceLimit = this.savedState.getStackTraceLimit();
            return stackTraceLimit;
        } finally {
            this.lock.unlock();
        }
    }

    public int getResponseBodyLimit() {
        this.lock.lock();
        try {
            int response_body_limit = this.savedState.getHarvestConfiguration().getResponse_body_limit();
            return response_body_limit;
        } finally {
            this.lock.unlock();
        }
    }

    public void start() {
        if (isDisabled()) {
            stop(false);
            return;
        }
        initialize();
        Harvest.start();
    }

    public void stop() {
        stop(true);
    }

    private void stop(boolean finalSendData) {
        finalizeSession();
        Sampler.shutdown();
        TraceMachine.haltTracing();
        int eventsRecorded = AnalyticsControllerImpl.getInstance().getEventManager().getEventsRecorded();
        int eventsEjected = AnalyticsControllerImpl.getInstance().getEventManager().getEventsEjected();
        Measurements.addCustomMetric("Supportability/Events/Recorded", MetricCategory.NONE.name(), eventsRecorded, (double) eventsEjected, (double) eventsEjected, MetricUnit.OPERATIONS, MetricUnit.OPERATIONS);
        if (finalSendData) {
            if (isUIThread()) {
                StatsEngine.get().inc("Supportability/AgentHealth/HarvestOnMainThread");
            }
            Harvest.harvestNow();
        }
        AnalyticsControllerImpl.shutdown();
        TraceMachine.clearActivityHistory();
        Harvest.shutdown();
        Measurements.shutdown();
        PayloadController.shutdown();
    }

    public void disable() {
        log.warning("PERMANENTLY DISABLING AGENT v" + Agent.getVersion());
        try {
            this.savedState.saveDisabledVersion(Agent.getVersion());
            try {
                stop(false);
            } finally {
                Agent.setImpl(NullAgentImpl.instance);
            }
        } catch (Throwable th) {
            stop(false);
        } finally {
            Agent.setImpl(NullAgentImpl.instance);
        }
    }

    public boolean isDisabled() {
        return Agent.getVersion().equals(this.savedState.getDisabledVersion());
    }

    public String getNetworkCarrier() {
        return Connectivity.carrierNameFromContext(this.context);
    }

    public String getNetworkWanType() {
        return Connectivity.wanType(this.context);
    }

    public static void init(Context context, AgentConfiguration agentConfiguration) {
        try {
            Agent.setImpl(new AndroidAgentImpl(context, agentConfiguration));
            Agent.start();
        } catch (AgentInitializationException e) {
            log.error("Failed to initialize the agent: " + e.toString());
        }
    }

    @Deprecated
    public void connected(ConnectionEvent e) {
        log.error("AndroidAgentImpl: connected ");
    }

    @Deprecated
    public void disconnected(ConnectionEvent e) {
        this.savedState.clear();
    }

    public void applicationForegrounded(ApplicationStateEvent e) {
        log.info("AndroidAgentImpl: application foregrounded ");
        start();
    }

    public void applicationBackgrounded(ApplicationStateEvent e) {
        log.info("AndroidAgentImpl: application backgrounded ");
        stop();
    }

    public void setLocation(String countryCode, String adminRegion) {
        if (countryCode == null || adminRegion == null) {
            throw new IllegalArgumentException("Country code and administrative region are required.");
        }
    }

    public void setLocation(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("Location must not be null.");
        }
        List<Address> addresses = null;
        try {
            addresses = new Geocoder(this.context).getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException e) {
            log.error("Unable to geocode location: " + e.toString());
        }
        if (addresses != null && addresses.size() != 0) {
            Address address = (Address) addresses.get(0);
            if (address != null) {
                String countryCode = address.getCountryCode();
                String adminArea = address.getAdminArea();
                if (countryCode != null && adminArea != null) {
                    setLocation(countryCode, adminArea);
                    removeLocationListener();
                }
            }
        }
    }

    @SuppressLint({"MissingPermission"})
    private void addLocationListener() {
        LocationManager locationManager = (LocationManager) this.context.getSystemService(LocationManagerProxy.KEY_LOCATION_CHANGED);
        if (locationManager == null) {
            log.error("Unable to retrieve reference to LocationManager. Disabling location listener.");
            return;
        }
        this.locationListener = new C26222();
        locationManager.requestLocationUpdates("passive", 1000, 0.0f, this.locationListener);
    }

    @SuppressLint({"MissingPermission"})
    private void removeLocationListener() {
        if (this.locationListener != null) {
            LocationManager locationManager = (LocationManager) this.context.getSystemService(LocationManagerProxy.KEY_LOCATION_CHANGED);
            if (locationManager == null) {
                log.error("Unable to retrieve reference to LocationManager. Can't unregister location listener.");
                return;
            }
            synchronized (locationManager) {
                locationManager.removeUpdates(this.locationListener);
                this.locationListener = null;
            }
        }
    }

    private boolean isAccurate(Location location) {
        if (location != null && LOCATION_ACCURACY_THRESHOLD >= location.getAccuracy()) {
            return true;
        }
        return false;
    }

    private String getUUID() {
        String uuid = this.savedState.getConnectInformation().getDeviceInformation().getDeviceId();
        if (!TextUtils.isEmpty(uuid)) {
            return uuid;
        }
        uuid = new PersistentUUID(this.context).getPersistentUUID();
        this.savedState.saveDeviceId(uuid);
        return uuid;
    }

    private String getUnhandledExceptionHandlerName() {
        try {
            return Thread.getDefaultUncaughtExceptionHandler().getClass().getName();
        } catch (Exception e) {
            return "unknown";
        }
    }

    public Encoder getEncoder() {
        return this.encoder;
    }

    public long getCurrentThreadId() {
        return Thread.currentThread().getId();
    }

    public boolean isUIThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

    private void pokeCanary() {
        NewRelicCanary.canaryMethod();
    }

    /* Access modifiers changed, original: protected */
    public SavedState getSavedState() {
        return this.savedState;
    }

    /* Access modifiers changed, original: protected */
    public void setSavedState(SavedState savedState) {
        this.savedState = savedState;
    }

    public boolean hasReachableNetworkConnection(String reachableHost) {
        return Reachability.hasReachableNetworkConnection(this.context, reachableHost);
    }
}
