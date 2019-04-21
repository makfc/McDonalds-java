package com.crashlytics.android.answers;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import com.crashlytics.android.answers.BackgroundManager.Listener;
import java.util.concurrent.ScheduledExecutorService;
import p041io.fabric.sdk.android.ActivityLifecycleManager;
import p041io.fabric.sdk.android.Fabric;
import p041io.fabric.sdk.android.Kit;
import p041io.fabric.sdk.android.services.common.ExecutorUtils;
import p041io.fabric.sdk.android.services.common.IdManager;
import p041io.fabric.sdk.android.services.network.DefaultHttpRequestFactory;
import p041io.fabric.sdk.android.services.persistence.FileStoreImpl;
import p041io.fabric.sdk.android.services.settings.AnalyticsSettingsData;

class SessionAnalyticsManager implements Listener {
    final BackgroundManager backgroundManager;
    final AnswersEventsHandler eventsHandler;
    private final long installedAt;
    final ActivityLifecycleManager lifecycleManager;
    final AnswersPreferenceManager preferenceManager;

    public static SessionAnalyticsManager build(Kit kit, Context context, IdManager idManager, String versionCode, String versionName, long installedAt) {
        SessionMetadataCollector metadataCollector = new SessionMetadataCollector(context, idManager, versionCode, versionName);
        AnswersFilesManagerProvider filesManagerProvider = new AnswersFilesManagerProvider(context, new FileStoreImpl(kit));
        DefaultHttpRequestFactory httpRequestFactory = new DefaultHttpRequestFactory(Fabric.getLogger());
        ActivityLifecycleManager lifecycleManager = new ActivityLifecycleManager(context);
        ScheduledExecutorService executorService = ExecutorUtils.buildSingleThreadScheduledExecutorService("Answers Events Handler");
        BackgroundManager backgroundManager = new BackgroundManager(executorService);
        return new SessionAnalyticsManager(new AnswersEventsHandler(kit, context, filesManagerProvider, metadataCollector, httpRequestFactory, executorService, new FirebaseAnalyticsApiAdapter(context)), lifecycleManager, backgroundManager, AnswersPreferenceManager.build(context), installedAt);
    }

    SessionAnalyticsManager(AnswersEventsHandler eventsHandler, ActivityLifecycleManager lifecycleManager, BackgroundManager backgroundManager, AnswersPreferenceManager preferenceManager, long installedAt) {
        this.eventsHandler = eventsHandler;
        this.lifecycleManager = lifecycleManager;
        this.backgroundManager = backgroundManager;
        this.preferenceManager = preferenceManager;
        this.installedAt = installedAt;
    }

    public void enable() {
        this.eventsHandler.enable();
        this.lifecycleManager.registerCallbacks(new AnswersLifecycleCallbacks(this, this.backgroundManager));
        this.backgroundManager.registerListener(this);
        if (isFirstLaunch()) {
            onInstall(this.installedAt);
            this.preferenceManager.setAnalyticsLaunched();
        }
    }

    public void disable() {
        this.lifecycleManager.resetCallbacks();
        this.eventsHandler.disable();
    }

    public void onCrash(String sessionId, String exceptionName) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("onCrash called from main thread!!!");
        }
        Fabric.getLogger().mo34403d("Answers", "Logged crash");
        this.eventsHandler.processEventSync(SessionEvent.crashEventBuilder(sessionId, exceptionName));
    }

    public void onError(String sessionId) {
    }

    public void onInstall(long installedAt) {
        Fabric.getLogger().mo34403d("Answers", "Logged install");
        this.eventsHandler.processEventAsyncAndFlush(SessionEvent.installEventBuilder(installedAt));
    }

    public void onLifecycle(Activity activity, Type type) {
        Fabric.getLogger().mo34403d("Answers", "Logged lifecycle event: " + type.name());
        this.eventsHandler.processEventAsync(SessionEvent.lifecycleEventBuilder(type, activity));
    }

    public void onBackground() {
        Fabric.getLogger().mo34403d("Answers", "Flush events when app is backgrounded");
        this.eventsHandler.flushEvents();
    }

    public void setAnalyticsSettingsData(AnalyticsSettingsData analyticsSettingsData, String protocolAndHostOverride) {
        this.backgroundManager.setFlushOnBackground(analyticsSettingsData.flushOnBackground);
        this.eventsHandler.setAnalyticsSettingsData(analyticsSettingsData, protocolAndHostOverride);
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isFirstLaunch() {
        return !this.preferenceManager.hasAnalyticsLaunched();
    }
}
