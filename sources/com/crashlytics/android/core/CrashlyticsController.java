package com.crashlytics.android.core;

import android.app.Activity;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.EventLogger;
import com.crashlytics.android.core.LogFileManager.DirectoryProvider;
import com.newrelic.agent.android.agentdata.HexAttributes;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;
import org.json.JSONObject;
import p041io.fabric.sdk.android.Fabric;
import p041io.fabric.sdk.android.Kit;
import p041io.fabric.sdk.android.services.common.CommonUtils;
import p041io.fabric.sdk.android.services.common.Crash.FatalException;
import p041io.fabric.sdk.android.services.common.Crash.LoggedException;
import p041io.fabric.sdk.android.services.common.DataCollectionArbiter;
import p041io.fabric.sdk.android.services.common.DeliveryMechanism;
import p041io.fabric.sdk.android.services.common.IdManager;
import p041io.fabric.sdk.android.services.common.IdManager.DeviceIdentifierType;
import p041io.fabric.sdk.android.services.network.HttpRequestFactory;
import p041io.fabric.sdk.android.services.persistence.FileStore;
import p041io.fabric.sdk.android.services.settings.FeaturesSettingsData;
import p041io.fabric.sdk.android.services.settings.PromptSettingsData;
import p041io.fabric.sdk.android.services.settings.SessionSettingsData;
import p041io.fabric.sdk.android.services.settings.Settings;
import p041io.fabric.sdk.android.services.settings.SettingsData;

class CrashlyticsController {
    private static final String[] INITIAL_SESSION_PART_TAGS = new String[]{"SessionUser", "SessionApp", "SessionOS", "SessionDevice"};
    static final Comparator<File> LARGEST_FILE_NAME_FIRST = new C16614();
    private static final Map<String, String> SEND_AT_CRASHTIME_HEADER = Collections.singletonMap("X-CRASHLYTICS-SEND-FLAGS", "1");
    static final FilenameFilter SESSION_BEGIN_FILE_FILTER = new FileNameContainsFilter("BeginSession") {
        public boolean accept(File dir, String filename) {
            return super.accept(dir, filename) && filename.endsWith(".cls");
        }
    };
    static final FileFilter SESSION_DIRECTORY_FILTER = new C16603();
    static final FilenameFilter SESSION_FILE_FILTER = new C16592();
    private static final Pattern SESSION_FILE_PATTERN = Pattern.compile("([\\d|A-Z|a-z]{12}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{12}).+");
    static final Comparator<File> SMALLEST_FILE_NAME_FIRST = new C16625();
    private final AppData appData;
    private final AppMeasurementEventListenerRegistrar appMeasurementEventListenerRegistrar;
    private final CrashlyticsBackgroundWorker backgroundWorker;
    private CrashlyticsUncaughtExceptionHandler crashHandler;
    private final CrashlyticsCore crashlyticsCore;
    private final DevicePowerStateListener devicePowerStateListener;
    private final AtomicInteger eventCounter = new AtomicInteger(0);
    private final FileStore fileStore;
    private final EventLogger firebaseAnalyticsLogger;
    private final HandlingExceptionCheck handlingExceptionCheck;
    private final HttpRequestFactory httpRequestFactory;
    private final IdManager idManager;
    private final LogFileDirectoryProvider logFileDirectoryProvider;
    private final LogFileManager logFileManager;
    private final PreferenceManager preferenceManager;
    private final ReportFilesProvider reportFilesProvider;
    private final StackTraceTrimmingStrategy stackTraceTrimmingStrategy;
    private final String unityVersion;

    /* renamed from: com.crashlytics.android.core.CrashlyticsController$10 */
    class C163810 implements Callable<Void> {
        final /* synthetic */ CrashlyticsController this$0;
        final /* synthetic */ String val$userEmail;
        final /* synthetic */ String val$userId;
        final /* synthetic */ String val$userName;

        public Void call() throws Exception {
            new MetaDataStore(this.this$0.getFilesDir()).writeUserData(this.this$0.getCurrentSessionId(), new UserMetaData(this.val$userId, this.val$userName, this.val$userEmail));
            return null;
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsController$11 */
    class C163911 implements Callable<Void> {
        final /* synthetic */ CrashlyticsController this$0;
        final /* synthetic */ Map val$keyData;

        public Void call() throws Exception {
            new MetaDataStore(this.this$0.getFilesDir()).writeKeyData(this.this$0.getCurrentSessionId(), this.val$keyData);
            return null;
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsController$12 */
    class C164012 implements Callable<Void> {
        C164012() {
        }

        public Void call() throws Exception {
            CrashlyticsController.this.doOpenSession();
            return null;
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsController$14 */
    class C164214 implements Runnable {
        C164214() {
        }

        public void run() {
            CrashlyticsController.this.doCleanInvalidTempFiles(CrashlyticsController.this.listFilesMatching((FilenameFilter) new InvalidPartFileFilter()));
        }
    }

    private interface CodedOutputStreamWriteAction {
        void writeTo(CodedOutputStream codedOutputStream) throws Exception;
    }

    private interface FileOutputStreamWriteAction {
        void writeTo(FileOutputStream fileOutputStream) throws Exception;
    }

    static class FileNameContainsFilter implements FilenameFilter {
        private final String string;

        public FileNameContainsFilter(String s) {
            this.string = s;
        }

        public boolean accept(File dir, String filename) {
            return filename.contains(this.string) && !filename.endsWith(".cls_temp");
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsController$2 */
    static class C16592 implements FilenameFilter {
        C16592() {
        }

        public boolean accept(File dir, String filename) {
            return filename.length() == ".cls".length() + 35 && filename.endsWith(".cls");
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsController$3 */
    static class C16603 implements FileFilter {
        C16603() {
        }

        public boolean accept(File file) {
            return file.isDirectory() && file.getName().length() == 35;
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsController$4 */
    static class C16614 implements Comparator<File> {
        C16614() {
        }

        public int compare(File file1, File file2) {
            return file2.getName().compareTo(file1.getName());
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsController$5 */
    static class C16625 implements Comparator<File> {
        C16625() {
        }

        public int compare(File file1, File file2) {
            return file1.getName().compareTo(file2.getName());
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsController$6 */
    class C16636 implements CrashListener {
        C16636() {
        }

        public void onUncaughtException(SettingsDataProvider settingsDataProvider, Thread thread, Throwable ex, boolean firebaseCrashlyticsClientFlag) {
            CrashlyticsController.this.handleUncaughtException(settingsDataProvider, thread, ex, firebaseCrashlyticsClientFlag);
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsController$9 */
    class C16669 implements Runnable {
        final /* synthetic */ CrashlyticsController this$0;
        final /* synthetic */ Throwable val$ex;
        final /* synthetic */ Date val$now;
        final /* synthetic */ Thread val$thread;

        public void run() {
            if (!this.this$0.isHandlingException()) {
                this.this$0.doWriteNonFatal(this.val$now, this.val$thread, this.val$ex);
            }
        }
    }

    private static class AnySessionPartFileFilter implements FilenameFilter {
        private AnySessionPartFileFilter() {
        }

        /* synthetic */ AnySessionPartFileFilter(C16491 x0) {
            this();
        }

        public boolean accept(File file, String fileName) {
            return !CrashlyticsController.SESSION_FILE_FILTER.accept(file, fileName) && CrashlyticsController.SESSION_FILE_PATTERN.matcher(fileName).matches();
        }
    }

    private static final class DefaultSettingsDataProvider implements SettingsDataProvider {
        private DefaultSettingsDataProvider() {
        }

        /* synthetic */ DefaultSettingsDataProvider(C16491 x0) {
            this();
        }

        public SettingsData getSettingsData() {
            return Settings.getInstance().awaitSettingsData();
        }
    }

    static class InvalidPartFileFilter implements FilenameFilter {
        InvalidPartFileFilter() {
        }

        public boolean accept(File file, String fileName) {
            return ClsFileOutputStream.TEMP_FILENAME_FILTER.accept(file, fileName) || fileName.contains("SessionMissingBinaryImages");
        }
    }

    private static final class LogFileDirectoryProvider implements DirectoryProvider {
        private final FileStore rootFileStore;

        public LogFileDirectoryProvider(FileStore rootFileStore) {
            this.rootFileStore = rootFileStore;
        }

        public File getLogFileDir() {
            File logFileDir = new File(this.rootFileStore.getFilesDir(), "log-files");
            if (!logFileDir.exists()) {
                logFileDir.mkdirs();
            }
            return logFileDir;
        }
    }

    private static final class PrivacyDialogCheck implements SendCheck {
        private final Kit kit;
        private final PreferenceManager preferenceManager;
        private final PromptSettingsData promptData;

        /* renamed from: com.crashlytics.android.core.CrashlyticsController$PrivacyDialogCheck$1 */
        class C16671 implements AlwaysSendCallback {
            C16671() {
            }

            public void sendUserReportsWithoutPrompting(boolean send) {
                PrivacyDialogCheck.this.preferenceManager.setShouldAlwaysSendReports(send);
            }
        }

        public PrivacyDialogCheck(Kit kit, PreferenceManager preferenceManager, PromptSettingsData promptData) {
            this.kit = kit;
            this.preferenceManager = preferenceManager;
            this.promptData = promptData;
        }

        public boolean canSendReports() {
            Activity activity = this.kit.getFabric().getCurrentActivity();
            if (activity == null || activity.isFinishing()) {
                return true;
            }
            final CrashPromptDialog dialog = CrashPromptDialog.create(activity, this.promptData, new C16671());
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    dialog.show();
                }
            });
            Fabric.getLogger().mo34403d("CrashlyticsCore", "Waiting for user opt-in.");
            dialog.await();
            return dialog.getOptIn();
        }
    }

    private final class ReportUploaderFilesProvider implements ReportFilesProvider {
        private ReportUploaderFilesProvider() {
        }

        /* synthetic */ ReportUploaderFilesProvider(CrashlyticsController x0, C16491 x1) {
            this();
        }

        public File[] getCompleteSessionFiles() {
            return CrashlyticsController.this.listCompleteSessionFiles();
        }

        public File[] getInvalidSessionFiles() {
            return CrashlyticsController.this.getInvalidFilesDir().listFiles();
        }

        public File[] getNativeReportFiles() {
            return CrashlyticsController.this.listNativeSessionFileDirectories();
        }
    }

    private final class ReportUploaderHandlingExceptionCheck implements HandlingExceptionCheck {
        private ReportUploaderHandlingExceptionCheck() {
        }

        /* synthetic */ ReportUploaderHandlingExceptionCheck(CrashlyticsController x0, C16491 x1) {
            this();
        }

        public boolean isHandlingException() {
            return CrashlyticsController.this.isHandlingException();
        }
    }

    private static final class SendReportRunnable implements Runnable {
        private final Context context;
        private final Report report;
        private final ReportUploader reportUploader;

        public SendReportRunnable(Context context, Report report, ReportUploader reportUploader) {
            this.context = context;
            this.report = report;
            this.reportUploader = reportUploader;
        }

        public void run() {
            if (CommonUtils.canTryConnection(this.context)) {
                Fabric.getLogger().mo34403d("CrashlyticsCore", "Attempting to send crash report at time of crash...");
                this.reportUploader.forceUpload(this.report);
            }
        }
    }

    static class SessionPartFileFilter implements FilenameFilter {
        private final String sessionId;

        public SessionPartFileFilter(String sessionId) {
            this.sessionId = sessionId;
        }

        public boolean accept(File file, String fileName) {
            if (fileName.equals(this.sessionId + ".cls") || !fileName.contains(this.sessionId) || fileName.endsWith(".cls_temp")) {
                return false;
            }
            return true;
        }
    }

    CrashlyticsController(CrashlyticsCore crashlyticsCore, CrashlyticsBackgroundWorker backgroundWorker, HttpRequestFactory httpRequestFactory, IdManager idManager, PreferenceManager preferenceManager, FileStore fileStore, AppData appData, UnityVersionProvider unityVersionProvider, AppMeasurementEventListenerRegistrar appMeasurementEventListenerRegistrar, EventLogger firebaseAnalyticsLogger) {
        this.crashlyticsCore = crashlyticsCore;
        this.backgroundWorker = backgroundWorker;
        this.httpRequestFactory = httpRequestFactory;
        this.idManager = idManager;
        this.preferenceManager = preferenceManager;
        this.fileStore = fileStore;
        this.appData = appData;
        this.unityVersion = unityVersionProvider.getUnityVersion();
        this.appMeasurementEventListenerRegistrar = appMeasurementEventListenerRegistrar;
        this.firebaseAnalyticsLogger = firebaseAnalyticsLogger;
        Context context = crashlyticsCore.getContext();
        this.logFileDirectoryProvider = new LogFileDirectoryProvider(fileStore);
        this.logFileManager = new LogFileManager(context, this.logFileDirectoryProvider);
        this.reportFilesProvider = new ReportUploaderFilesProvider(this, null);
        this.handlingExceptionCheck = new ReportUploaderHandlingExceptionCheck(this, null);
        this.devicePowerStateListener = new DevicePowerStateListener(context);
        this.stackTraceTrimmingStrategy = new MiddleOutFallbackStrategy(1024, new RemoveRepeatsStrategy(10));
    }

    /* Access modifiers changed, original: 0000 */
    public void enableExceptionHandling(UncaughtExceptionHandler defaultHandler, boolean firebaseCrashlyticsClientFlag) {
        openSession();
        this.crashHandler = new CrashlyticsUncaughtExceptionHandler(new C16636(), new DefaultSettingsDataProvider(), firebaseCrashlyticsClientFlag, defaultHandler);
        Thread.setDefaultUncaughtExceptionHandler(this.crashHandler);
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized void handleUncaughtException(SettingsDataProvider settingsDataProvider, Thread thread, Throwable ex, boolean firebaseCrashlyticsClientFlag) {
        Fabric.getLogger().mo34403d("CrashlyticsCore", "Crashlytics is handling uncaught exception \"" + ex + "\" from thread " + thread.getName());
        this.devicePowerStateListener.dispose();
        final Date time = new Date();
        final Thread thread2 = thread;
        final Throwable th = ex;
        final SettingsDataProvider settingsDataProvider2 = settingsDataProvider;
        final boolean z = firebaseCrashlyticsClientFlag;
        this.backgroundWorker.submitAndWait(new Callable<Void>() {
            public Void call() throws Exception {
                boolean firebaseCrashlyticsEnabledSetting;
                boolean sendReports = true;
                CrashlyticsController.this.crashlyticsCore.createCrashMarker();
                CrashlyticsController.this.writeFatal(time, thread2, th);
                SettingsData settingsData = settingsDataProvider2.getSettingsData();
                SessionSettingsData sessionSettings = null;
                FeaturesSettingsData featuresSettings = null;
                if (settingsData != null) {
                    sessionSettings = settingsData.sessionData;
                    featuresSettings = settingsData.featuresData;
                }
                if (featuresSettings == null || featuresSettings.firebaseCrashlyticsEnabled) {
                    firebaseCrashlyticsEnabledSetting = true;
                } else {
                    firebaseCrashlyticsEnabledSetting = false;
                }
                if (firebaseCrashlyticsEnabledSetting || z) {
                    CrashlyticsController.this.recordFatalFirebaseEvent(time.getTime());
                }
                CrashlyticsController.this.doCloseSessions(sessionSettings);
                CrashlyticsController.this.doOpenSession();
                if (sessionSettings != null) {
                    CrashlyticsController.this.trimSessionFiles(sessionSettings.maxCompleteSessionsCount);
                }
                if (!DataCollectionArbiter.getInstance(CrashlyticsController.this.crashlyticsCore.getContext()).isDataCollectionEnabled() || CrashlyticsController.this.shouldPromptUserBeforeSendingCrashReports(settingsData)) {
                    sendReports = false;
                }
                if (sendReports) {
                    CrashlyticsController.this.sendSessionReports(settingsData);
                }
                return null;
            }
        });
    }

    /* Access modifiers changed, original: 0000 */
    public void submitAllReports(float delay, SettingsData settingsData) {
        if (settingsData == null) {
            Fabric.getLogger().mo34411w("CrashlyticsCore", "Could not send reports. Settings are not available.");
            return;
        }
        new ReportUploader(this.appData.apiKey, getCreateReportSpiCall(settingsData.appData.reportsUrl, settingsData.appData.ndkReportsUrl), this.reportFilesProvider, this.handlingExceptionCheck).uploadReports(delay, shouldPromptUserBeforeSendingCrashReports(settingsData) ? new PrivacyDialogCheck(this.crashlyticsCore, this.preferenceManager, settingsData.promptData) : new AlwaysSendCheck());
    }

    /* Access modifiers changed, original: 0000 */
    public void writeToLog(final long timestamp, final String msg) {
        this.backgroundWorker.submit(new Callable<Void>() {
            public Void call() throws Exception {
                if (!CrashlyticsController.this.isHandlingException()) {
                    CrashlyticsController.this.logFileManager.writeToLog(timestamp, msg);
                }
                return null;
            }
        });
    }

    /* Access modifiers changed, original: 0000 */
    public void openSession() {
        this.backgroundWorker.submit(new C164012());
    }

    private String getCurrentSessionId() {
        File[] sessionBeginFiles = listSortedSessionBeginFiles();
        return sessionBeginFiles.length > 0 ? getSessionIdFromSessionFile(sessionBeginFiles[0]) : null;
    }

    private String getPreviousSessionId() {
        File[] sessionBeginFiles = listSortedSessionBeginFiles();
        return sessionBeginFiles.length > 1 ? getSessionIdFromSessionFile(sessionBeginFiles[1]) : null;
    }

    static String getSessionIdFromSessionFile(File sessionFile) {
        return sessionFile.getName().substring(0, 35);
    }

    /* Access modifiers changed, original: 0000 */
    public boolean finalizeSessions(final SessionSettingsData sessionSettingsData) {
        return ((Boolean) this.backgroundWorker.submitAndWait(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                if (CrashlyticsController.this.isHandlingException()) {
                    Fabric.getLogger().mo34403d("CrashlyticsCore", "Skipping session finalization because a crash has already occurred.");
                    return Boolean.FALSE;
                }
                Fabric.getLogger().mo34403d("CrashlyticsCore", "Finalizing previously open sessions.");
                CrashlyticsController.this.doCloseSessions(sessionSettingsData, true);
                Fabric.getLogger().mo34403d("CrashlyticsCore", "Closed all previously open sessions");
                return Boolean.TRUE;
            }
        })).booleanValue();
    }

    private void doOpenSession() throws Exception {
        Date startedAt = new Date();
        String sessionIdentifier = new CLSUUID(this.idManager).toString();
        Fabric.getLogger().mo34403d("CrashlyticsCore", "Opening a new session with ID " + sessionIdentifier);
        writeBeginSession(sessionIdentifier, startedAt);
        writeSessionApp(sessionIdentifier);
        writeSessionOS(sessionIdentifier);
        writeSessionDevice(sessionIdentifier);
        this.logFileManager.setCurrentSession(sessionIdentifier);
    }

    /* Access modifiers changed, original: 0000 */
    public void doCloseSessions(SessionSettingsData sessionSettingsData) throws Exception {
        doCloseSessions(sessionSettingsData, false);
    }

    private void doCloseSessions(SessionSettingsData sessionSettingsData, boolean excludeCurrent) throws Exception {
        int offset = excludeCurrent ? 1 : 0;
        trimOpenSessions(offset + 8);
        File[] sessionBeginFiles = listSortedSessionBeginFiles();
        if (sessionBeginFiles.length <= offset) {
            Fabric.getLogger().mo34403d("CrashlyticsCore", "No open sessions to be closed.");
            return;
        }
        writeSessionUser(getSessionIdFromSessionFile(sessionBeginFiles[offset]));
        if (sessionSettingsData == null) {
            Fabric.getLogger().mo34403d("CrashlyticsCore", "Unable to close session. Settings are not loaded.");
        } else {
            closeOpenSessions(sessionBeginFiles, offset, sessionSettingsData.maxCustomExceptionEvents);
        }
    }

    private void closeOpenSessions(File[] sessionBeginFiles, int beginIndex, int maxLoggedExceptionsCount) {
        Fabric.getLogger().mo34403d("CrashlyticsCore", "Closing open sessions.");
        for (int i = beginIndex; i < sessionBeginFiles.length; i++) {
            File sessionBeginFile = sessionBeginFiles[i];
            String sessionIdentifier = getSessionIdFromSessionFile(sessionBeginFile);
            Fabric.getLogger().mo34403d("CrashlyticsCore", "Closing session: " + sessionIdentifier);
            writeSessionPartsToSessionFile(sessionBeginFile, sessionIdentifier, maxLoggedExceptionsCount);
        }
    }

    private void closeWithoutRenamingOrLog(ClsFileOutputStream fos) {
        if (fos != null) {
            try {
                fos.closeInProgressStream();
            } catch (IOException ex) {
                Fabric.getLogger().mo34406e("CrashlyticsCore", "Error closing session file stream in the presence of an exception", ex);
            }
        }
    }

    private void recursiveDelete(Set<File> files) {
        for (File f : files) {
            recursiveDelete(f);
        }
    }

    private void recursiveDelete(File f) {
        if (f.isDirectory()) {
            for (File s : f.listFiles()) {
                recursiveDelete(s);
            }
        }
        f.delete();
    }

    private void deleteSessionPartFilesFor(String sessionId) {
        for (File file : listSessionPartFilesFor(sessionId)) {
            file.delete();
        }
    }

    private File[] listSessionPartFilesFor(String sessionId) {
        return listFilesMatching(new SessionPartFileFilter(sessionId));
    }

    /* Access modifiers changed, original: 0000 */
    public File[] listCompleteSessionFiles() {
        List<File> completeSessionFiles = new LinkedList();
        Collections.addAll(completeSessionFiles, listFilesMatching(getFatalSessionFilesDir(), SESSION_FILE_FILTER));
        Collections.addAll(completeSessionFiles, listFilesMatching(getNonFatalSessionFilesDir(), SESSION_FILE_FILTER));
        Collections.addAll(completeSessionFiles, listFilesMatching(getFilesDir(), SESSION_FILE_FILTER));
        return (File[]) completeSessionFiles.toArray(new File[completeSessionFiles.size()]);
    }

    /* Access modifiers changed, original: 0000 */
    public File[] listNativeSessionFileDirectories() {
        return listFilesMatching(SESSION_DIRECTORY_FILTER);
    }

    /* Access modifiers changed, original: 0000 */
    public File[] listSessionBeginFiles() {
        return listFilesMatching(SESSION_BEGIN_FILE_FILTER);
    }

    private File[] listSortedSessionBeginFiles() {
        File[] sessionBeginFiles = listSessionBeginFiles();
        Arrays.sort(sessionBeginFiles, LARGEST_FILE_NAME_FIRST);
        return sessionBeginFiles;
    }

    private File[] listFilesMatching(FileFilter filter) {
        return ensureFileArrayNotNull(getFilesDir().listFiles(filter));
    }

    private File[] listFilesMatching(FilenameFilter filter) {
        return listFilesMatching(getFilesDir(), filter);
    }

    private File[] listFilesMatching(File directory, FilenameFilter filter) {
        return ensureFileArrayNotNull(directory.listFiles(filter));
    }

    private File[] listFiles(File directory) {
        return ensureFileArrayNotNull(directory.listFiles());
    }

    private File[] ensureFileArrayNotNull(File[] files) {
        return files == null ? new File[0] : files;
    }

    private void trimSessionEventFiles(String sessionId, int limit) {
        Utils.capFileCount(getFilesDir(), new FileNameContainsFilter(sessionId + "SessionEvent"), limit, SMALLEST_FILE_NAME_FIRST);
    }

    /* Access modifiers changed, original: 0000 */
    public void trimSessionFiles(int maxCompleteSessionsCount) {
        int remaining = maxCompleteSessionsCount;
        remaining -= Utils.capFileCount(getFatalSessionFilesDir(), remaining, SMALLEST_FILE_NAME_FIRST);
        Utils.capFileCount(getFilesDir(), SESSION_FILE_FILTER, remaining - Utils.capFileCount(getNonFatalSessionFilesDir(), remaining, SMALLEST_FILE_NAME_FIRST), SMALLEST_FILE_NAME_FIRST);
    }

    private void trimOpenSessions(int maxOpenSessionCount) {
        Set<String> sessionIdsToKeep = new HashSet();
        File[] beginSessionFiles = listSortedSessionBeginFiles();
        int count = Math.min(maxOpenSessionCount, beginSessionFiles.length);
        for (int i = 0; i < count; i++) {
            sessionIdsToKeep.add(getSessionIdFromSessionFile(beginSessionFiles[i]));
        }
        this.logFileManager.discardOldLogFiles(sessionIdsToKeep);
        retainSessions(listFilesMatching(new AnySessionPartFileFilter()), sessionIdsToKeep);
    }

    private void retainSessions(File[] files, Set<String> sessionIdsToKeep) {
        for (File sessionPartFile : files) {
            String fileName = sessionPartFile.getName();
            Matcher matcher = SESSION_FILE_PATTERN.matcher(fileName);
            if (!matcher.matches()) {
                Fabric.getLogger().mo34403d("CrashlyticsCore", "Deleting unknown file: " + fileName);
                sessionPartFile.delete();
            } else if (!sessionIdsToKeep.contains(matcher.group(1))) {
                Fabric.getLogger().mo34403d("CrashlyticsCore", "Trimming session file: " + fileName);
                sessionPartFile.delete();
            }
        }
    }

    private File[] getTrimmedNonFatalFiles(String sessionId, File[] nonFatalFiles, int maxLoggedExceptionsCount) {
        if (nonFatalFiles.length <= maxLoggedExceptionsCount) {
            return nonFatalFiles;
        }
        Fabric.getLogger().mo34403d("CrashlyticsCore", String.format(Locale.US, "Trimming down to %d logged exceptions.", new Object[]{Integer.valueOf(maxLoggedExceptionsCount)}));
        trimSessionEventFiles(sessionId, maxLoggedExceptionsCount);
        return listFilesMatching(new FileNameContainsFilter(sessionId + "SessionEvent"));
    }

    /* Access modifiers changed, original: 0000 */
    public void cleanInvalidTempFiles() {
        this.backgroundWorker.submit(new C164214());
    }

    /* Access modifiers changed, original: 0000 */
    public void doCleanInvalidTempFiles(File[] invalidFiles) {
        int length;
        int i = 0;
        final Set<String> invalidSessionIds = new HashSet();
        for (File invalidFile : invalidFiles) {
            Fabric.getLogger().mo34403d("CrashlyticsCore", "Found invalid session part file: " + invalidFile);
            invalidSessionIds.add(getSessionIdFromSessionFile(invalidFile));
        }
        if (!invalidSessionIds.isEmpty()) {
            File invalidFilesDir = getInvalidFilesDir();
            if (!invalidFilesDir.exists()) {
                invalidFilesDir.mkdir();
            }
            File[] listFilesMatching = listFilesMatching(new FilenameFilter() {
                public boolean accept(File dir, String filename) {
                    if (filename.length() < 35) {
                        return false;
                    }
                    return invalidSessionIds.contains(filename.substring(0, 35));
                }
            });
            length = listFilesMatching.length;
            while (i < length) {
                File sessionFile = listFilesMatching[i];
                Fabric.getLogger().mo34403d("CrashlyticsCore", "Moving session file: " + sessionFile);
                if (!sessionFile.renameTo(new File(invalidFilesDir, sessionFile.getName()))) {
                    Fabric.getLogger().mo34403d("CrashlyticsCore", "Could not move session file. Deleting " + sessionFile);
                    sessionFile.delete();
                }
                i++;
            }
            trimInvalidSessionFiles();
        }
    }

    private void trimInvalidSessionFiles() {
        File invalidFilesDir = getInvalidFilesDir();
        if (invalidFilesDir.exists()) {
            File[] oldInvalidFiles = listFilesMatching(invalidFilesDir, new InvalidPartFileFilter());
            Arrays.sort(oldInvalidFiles, Collections.reverseOrder());
            Set<String> sessionIdsToKeep = new HashSet();
            for (int i = 0; i < oldInvalidFiles.length && sessionIdsToKeep.size() < 4; i++) {
                sessionIdsToKeep.add(getSessionIdFromSessionFile(oldInvalidFiles[i]));
            }
            retainSessions(listFiles(invalidFilesDir), sessionIdsToKeep);
        }
    }

    private void finalizeMostRecentNativeCrash(Context context, File latestDir, String previousSessionId) throws IOException {
        byte[] minidump = NativeFileUtils.minidumpFromDirectory(latestDir);
        byte[] metadata = NativeFileUtils.metadataJsonFromDirectory(latestDir);
        byte[] binaryImages = NativeFileUtils.binaryImagesJsonFromDirectory(latestDir, context);
        if (minidump == null || minidump.length == 0) {
            Fabric.getLogger().mo34411w("CrashlyticsCore", "No minidump data found in directory " + latestDir);
            return;
        }
        recordFatalExceptionAnswersEvent(previousSessionId, "<native-crash: minidump>");
        byte[] sessionMetadata = readFile(previousSessionId, "BeginSession.json");
        byte[] appMetadata = readFile(previousSessionId, "SessionApp.json");
        byte[] deviceMetadata = readFile(previousSessionId, "SessionDevice.json");
        byte[] osMetadata = readFile(previousSessionId, "SessionOS.json");
        byte[] userMetadata = NativeFileUtils.readFile(new MetaDataStore(getFilesDir()).getUserDataFileForSession(previousSessionId));
        LogFileManager previousSessionLogManager = new LogFileManager(this.crashlyticsCore.getContext(), this.logFileDirectoryProvider, previousSessionId);
        byte[] logs = previousSessionLogManager.getBytesForLog();
        previousSessionLogManager.clearLog();
        byte[] keys = NativeFileUtils.readFile(new MetaDataStore(getFilesDir()).getKeysFileForSession(previousSessionId));
        File nativeSessionDirectory = new File(this.fileStore.getFilesDir(), previousSessionId);
        if (nativeSessionDirectory.mkdir()) {
            gzipIfNotEmpty(minidump, new File(nativeSessionDirectory, "minidump"));
            gzipIfNotEmpty(metadata, new File(nativeSessionDirectory, "metadata"));
            gzipIfNotEmpty(binaryImages, new File(nativeSessionDirectory, "binaryImages"));
            gzipIfNotEmpty(sessionMetadata, new File(nativeSessionDirectory, "session"));
            gzipIfNotEmpty(appMetadata, new File(nativeSessionDirectory, "app"));
            gzipIfNotEmpty(deviceMetadata, new File(nativeSessionDirectory, "device"));
            gzipIfNotEmpty(osMetadata, new File(nativeSessionDirectory, "os"));
            gzipIfNotEmpty(userMetadata, new File(nativeSessionDirectory, "user"));
            gzipIfNotEmpty(logs, new File(nativeSessionDirectory, "logs"));
            gzipIfNotEmpty(keys, new File(nativeSessionDirectory, "keys"));
            return;
        }
        Fabric.getLogger().mo34403d("CrashlyticsCore", "Couldn't create native sessions directory");
    }

    /* Access modifiers changed, original: 0000 */
    public boolean finalizeNativeReport(final CrashlyticsNdkData ndkData) {
        if (ndkData == null) {
            return true;
        }
        return ((Boolean) this.backgroundWorker.submitAndWait(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                TreeSet<File> timestampedNativeDirectories = ndkData.timestampedDirectories;
                String previousSessionId = CrashlyticsController.this.getPreviousSessionId();
                if (!(previousSessionId == null || timestampedNativeDirectories.isEmpty())) {
                    File latestDir = (File) timestampedNativeDirectories.first();
                    if (latestDir != null) {
                        CrashlyticsController.this.finalizeMostRecentNativeCrash(CrashlyticsController.this.crashlyticsCore.getContext(), latestDir, previousSessionId);
                    }
                }
                CrashlyticsController.this.recursiveDelete((Set) timestampedNativeDirectories);
                return Boolean.TRUE;
            }
        })).booleanValue();
    }

    private void gzipIfNotEmpty(byte[] content, File path) throws IOException {
        if (content != null && content.length > 0) {
            gzip(content, path);
        }
    }

    private void gzip(byte[] bytes, File path) throws IOException {
        Throwable th;
        GZIPOutputStream gos = null;
        try {
            GZIPOutputStream gos2 = new GZIPOutputStream(new FileOutputStream(path));
            try {
                gos2.write(bytes);
                gos2.finish();
                CommonUtils.closeQuietly(gos2);
            } catch (Throwable th2) {
                th = th2;
                gos = gos2;
                CommonUtils.closeQuietly(gos);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            CommonUtils.closeQuietly(gos);
            throw th;
        }
    }

    private void writeFatal(Date time, Thread thread, Throwable ex) {
        Exception e;
        Throwable th;
        ClsFileOutputStream fos = null;
        CodedOutputStream cos = null;
        try {
            String currentSessionId = getCurrentSessionId();
            if (currentSessionId == null) {
                Fabric.getLogger().mo34406e("CrashlyticsCore", "Tried to write a fatal exception while no session was open.", null);
                CommonUtils.flushOrLog(null, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(null, "Failed to close fatal exception file output stream.");
                return;
            }
            recordFatalExceptionAnswersEvent(currentSessionId, ex.getClass().getName());
            ClsFileOutputStream fos2 = new ClsFileOutputStream(getFilesDir(), currentSessionId + "SessionCrash");
            try {
                cos = CodedOutputStream.newInstance(fos2);
                writeSessionEvent(cos, time, thread, ex, "crash", true);
                CommonUtils.flushOrLog(cos, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(fos2, "Failed to close fatal exception file output stream.");
                fos = fos2;
            } catch (Exception e2) {
                e = e2;
                fos = fos2;
                try {
                    Fabric.getLogger().mo34406e("CrashlyticsCore", "An error occurred in the fatal exception logger", e);
                    CommonUtils.flushOrLog(cos, "Failed to flush to session begin file.");
                    CommonUtils.closeOrLog(fos, "Failed to close fatal exception file output stream.");
                } catch (Throwable th2) {
                    th = th2;
                    CommonUtils.flushOrLog(cos, "Failed to flush to session begin file.");
                    CommonUtils.closeOrLog(fos, "Failed to close fatal exception file output stream.");
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fos = fos2;
                CommonUtils.flushOrLog(cos, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(fos, "Failed to close fatal exception file output stream.");
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            Fabric.getLogger().mo34406e("CrashlyticsCore", "An error occurred in the fatal exception logger", e);
            CommonUtils.flushOrLog(cos, "Failed to flush to session begin file.");
            CommonUtils.closeOrLog(fos, "Failed to close fatal exception file output stream.");
        }
    }

    private void doWriteNonFatal(Date time, Thread thread, Throwable ex) {
        Exception e;
        Throwable th;
        String currentSessionId = getCurrentSessionId();
        if (currentSessionId == null) {
            Fabric.getLogger().mo34406e("CrashlyticsCore", "Tried to write a non-fatal exception while no session was open.", null);
            return;
        }
        recordLoggedExceptionAnswersEvent(currentSessionId, ex.getClass().getName());
        ClsFileOutputStream fos = null;
        CodedOutputStream cos = null;
        try {
            Fabric.getLogger().mo34403d("CrashlyticsCore", "Crashlytics is logging non-fatal exception \"" + ex + "\" from thread " + thread.getName());
            ClsFileOutputStream fos2 = new ClsFileOutputStream(getFilesDir(), currentSessionId + "SessionEvent" + CommonUtils.padWithZerosToMaxIntWidth(this.eventCounter.getAndIncrement()));
            try {
                cos = CodedOutputStream.newInstance(fos2);
                writeSessionEvent(cos, time, thread, ex, "error", false);
                CommonUtils.flushOrLog(cos, "Failed to flush to non-fatal file.");
                CommonUtils.closeOrLog(fos2, "Failed to close non-fatal file output stream.");
                fos = fos2;
            } catch (Exception e2) {
                e = e2;
                fos = fos2;
                try {
                    Fabric.getLogger().mo34406e("CrashlyticsCore", "An error occurred in the non-fatal exception logger", e);
                    CommonUtils.flushOrLog(cos, "Failed to flush to non-fatal file.");
                    CommonUtils.closeOrLog(fos, "Failed to close non-fatal file output stream.");
                    trimSessionEventFiles(currentSessionId, 64);
                } catch (Throwable th2) {
                    th = th2;
                    CommonUtils.flushOrLog(cos, "Failed to flush to non-fatal file.");
                    CommonUtils.closeOrLog(fos, "Failed to close non-fatal file output stream.");
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fos = fos2;
                CommonUtils.flushOrLog(cos, "Failed to flush to non-fatal file.");
                CommonUtils.closeOrLog(fos, "Failed to close non-fatal file output stream.");
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            Fabric.getLogger().mo34406e("CrashlyticsCore", "An error occurred in the non-fatal exception logger", e);
            CommonUtils.flushOrLog(cos, "Failed to flush to non-fatal file.");
            CommonUtils.closeOrLog(fos, "Failed to close non-fatal file output stream.");
            trimSessionEventFiles(currentSessionId, 64);
        }
        try {
            trimSessionEventFiles(currentSessionId, 64);
        } catch (Exception e4) {
            Fabric.getLogger().mo34406e("CrashlyticsCore", "An error occurred when trimming non-fatal files.", e4);
        }
    }

    private void writeSessionPartFile(String sessionId, String tag, CodedOutputStreamWriteAction writeAction) throws Exception {
        Throwable th;
        FileOutputStream fos = null;
        CodedOutputStream cos = null;
        try {
            FileOutputStream fos2 = new ClsFileOutputStream(getFilesDir(), sessionId + tag);
            try {
                cos = CodedOutputStream.newInstance(fos2);
                writeAction.writeTo(cos);
                CommonUtils.flushOrLog(cos, "Failed to flush to session " + tag + " file.");
                CommonUtils.closeOrLog(fos2, "Failed to close session " + tag + " file.");
            } catch (Throwable th2) {
                th = th2;
                fos = fos2;
                CommonUtils.flushOrLog(cos, "Failed to flush to session " + tag + " file.");
                CommonUtils.closeOrLog(fos, "Failed to close session " + tag + " file.");
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            CommonUtils.flushOrLog(cos, "Failed to flush to session " + tag + " file.");
            CommonUtils.closeOrLog(fos, "Failed to close session " + tag + " file.");
            throw th;
        }
    }

    private void writeFile(String sessionId, String tag, FileOutputStreamWriteAction writeAction) throws Exception {
        Throwable th;
        FileOutputStream fos = null;
        try {
            FileOutputStream fos2 = new FileOutputStream(new File(getFilesDir(), sessionId + tag));
            try {
                writeAction.writeTo(fos2);
                CommonUtils.closeOrLog(fos2, "Failed to close " + tag + " file.");
            } catch (Throwable th2) {
                th = th2;
                fos = fos2;
                CommonUtils.closeOrLog(fos, "Failed to close " + tag + " file.");
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            CommonUtils.closeOrLog(fos, "Failed to close " + tag + " file.");
            throw th;
        }
    }

    private byte[] readFile(String sessionId, String tag) {
        return NativeFileUtils.readFile(new File(getFilesDir(), sessionId + tag));
    }

    private void writeBeginSession(String sessionId, Date startedAt) throws Exception {
        final String generator = String.format(Locale.US, "Crashlytics Android SDK/%s", new Object[]{this.crashlyticsCore.getVersion()});
        final long startedAtSeconds = startedAt.getTime() / 1000;
        final String str = sessionId;
        writeSessionPartFile(sessionId, "BeginSession", new CodedOutputStreamWriteAction() {
            public void writeTo(CodedOutputStream arg) throws Exception {
                SessionProtobufHelper.writeBeginSession(arg, str, generator, startedAtSeconds);
            }
        });
        str = sessionId;
        writeFile(sessionId, "BeginSession.json", new FileOutputStreamWriteAction() {

            /* renamed from: com.crashlytics.android.core.CrashlyticsController$18$1 */
            class C16461 extends HashMap<String, Object> {
                C16461() {
                    put("session_id", str);
                    put("generator", generator);
                    put("started_at_seconds", Long.valueOf(startedAtSeconds));
                }
            }

            public void writeTo(FileOutputStream arg) throws Exception {
                String jSONObjectInstrumentation;
                JSONObject jSONObject = new JSONObject(new C16461());
                if (jSONObject instanceof JSONObject) {
                    jSONObjectInstrumentation = JSONObjectInstrumentation.toString(jSONObject);
                } else {
                    jSONObjectInstrumentation = jSONObject.toString();
                }
                arg.write(jSONObjectInstrumentation.getBytes());
            }
        });
    }

    private void writeSessionApp(String sessionId) throws Exception {
        final String appIdentifier = this.idManager.getAppIdentifier();
        final String versionCode = this.appData.versionCode;
        final String versionName = this.appData.versionName;
        final String installUuid = this.idManager.getAppInstallIdentifier();
        final int deliveryMechanism = DeliveryMechanism.determineFrom(this.appData.installerPackageName).getId();
        writeSessionPartFile(sessionId, "SessionApp", new CodedOutputStreamWriteAction() {
            public void writeTo(CodedOutputStream arg) throws Exception {
                SessionProtobufHelper.writeSessionApp(arg, appIdentifier, CrashlyticsController.this.appData.apiKey, versionCode, versionName, installUuid, deliveryMechanism, CrashlyticsController.this.unityVersion);
            }
        });
        writeFile(sessionId, "SessionApp.json", new FileOutputStreamWriteAction() {

            /* renamed from: com.crashlytics.android.core.CrashlyticsController$20$1 */
            class C16501 extends HashMap<String, Object> {
                C16501() {
                    put("app_identifier", appIdentifier);
                    put("api_key", CrashlyticsController.this.appData.apiKey);
                    put("version_code", versionCode);
                    put("version_name", versionName);
                    put("install_uuid", installUuid);
                    put("delivery_mechanism", Integer.valueOf(deliveryMechanism));
                    put("unity_version", TextUtils.isEmpty(CrashlyticsController.this.unityVersion) ? "" : CrashlyticsController.this.unityVersion);
                }
            }

            public void writeTo(FileOutputStream arg) throws Exception {
                String jSONObjectInstrumentation;
                JSONObject jSONObject = new JSONObject(new C16501());
                if (jSONObject instanceof JSONObject) {
                    jSONObjectInstrumentation = JSONObjectInstrumentation.toString(jSONObject);
                } else {
                    jSONObjectInstrumentation = jSONObject.toString();
                }
                arg.write(jSONObjectInstrumentation.getBytes());
            }
        });
    }

    private void writeSessionOS(String sessionId) throws Exception {
        final boolean isRooted = CommonUtils.isRooted(this.crashlyticsCore.getContext());
        writeSessionPartFile(sessionId, "SessionOS", new CodedOutputStreamWriteAction() {
            public void writeTo(CodedOutputStream arg) throws Exception {
                SessionProtobufHelper.writeSessionOS(arg, VERSION.RELEASE, VERSION.CODENAME, isRooted);
            }
        });
        writeFile(sessionId, "SessionOS.json", new FileOutputStreamWriteAction() {

            /* renamed from: com.crashlytics.android.core.CrashlyticsController$22$1 */
            class C16531 extends HashMap<String, Object> {
                C16531() {
                    put("version", VERSION.RELEASE);
                    put("build_version", VERSION.CODENAME);
                    put("is_rooted", Boolean.valueOf(isRooted));
                }
            }

            public void writeTo(FileOutputStream arg) throws Exception {
                String jSONObjectInstrumentation;
                JSONObject jSONObject = new JSONObject(new C16531());
                if (jSONObject instanceof JSONObject) {
                    jSONObjectInstrumentation = JSONObjectInstrumentation.toString(jSONObject);
                } else {
                    jSONObjectInstrumentation = jSONObject.toString();
                }
                arg.write(jSONObjectInstrumentation.getBytes());
            }
        });
    }

    private void writeSessionDevice(String sessionId) throws Exception {
        Context context = this.crashlyticsCore.getContext();
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        final int arch = CommonUtils.getCpuArchitectureInt();
        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        final long totalRam = CommonUtils.getTotalRamInBytes();
        final long diskSpace = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        final boolean isEmulator = CommonUtils.isEmulator(context);
        final Map<DeviceIdentifierType, String> ids = this.idManager.getDeviceIdentifiers();
        final int state = CommonUtils.getDeviceState(context);
        writeSessionPartFile(sessionId, "SessionDevice", new CodedOutputStreamWriteAction() {
            public void writeTo(CodedOutputStream arg) throws Exception {
                SessionProtobufHelper.writeSessionDevice(arg, arch, Build.MODEL, availableProcessors, totalRam, diskSpace, isEmulator, ids, state, Build.MANUFACTURER, Build.PRODUCT);
            }
        });
        writeFile(sessionId, "SessionDevice.json", new FileOutputStreamWriteAction() {

            /* renamed from: com.crashlytics.android.core.CrashlyticsController$24$1 */
            class C16561 extends HashMap<String, Object> {
                C16561() {
                    put("arch", Integer.valueOf(arch));
                    put("build_model", Build.MODEL);
                    put("available_processors", Integer.valueOf(availableProcessors));
                    put("total_ram", Long.valueOf(totalRam));
                    put("disk_space", Long.valueOf(diskSpace));
                    put("is_emulator", Boolean.valueOf(isEmulator));
                    put("ids", ids);
                    put(HexAttributes.HEX_ATTR_THREAD_STATE, Integer.valueOf(state));
                    put("build_manufacturer", Build.MANUFACTURER);
                    put("build_product", Build.PRODUCT);
                }
            }

            public void writeTo(FileOutputStream arg) throws Exception {
                String jSONObjectInstrumentation;
                JSONObject jSONObject = new JSONObject(new C16561());
                if (jSONObject instanceof JSONObject) {
                    jSONObjectInstrumentation = JSONObjectInstrumentation.toString(jSONObject);
                } else {
                    jSONObjectInstrumentation = jSONObject.toString();
                }
                arg.write(jSONObjectInstrumentation.getBytes());
            }
        });
    }

    private void writeSessionUser(String sessionId) throws Exception {
        final UserMetaData userMetaData = getUserMetaData(sessionId);
        writeSessionPartFile(sessionId, "SessionUser", new CodedOutputStreamWriteAction() {
            public void writeTo(CodedOutputStream arg) throws Exception {
                SessionProtobufHelper.writeSessionUser(arg, userMetaData.f5584id, userMetaData.name, userMetaData.email);
            }
        });
    }

    private void writeSessionEvent(CodedOutputStream cos, Date time, Thread thread, Throwable ex, String eventType, boolean includeAllThreads) throws Exception {
        Thread[] threads;
        Map<String, String> attributes;
        TrimmedThrowableData trimmedEx = new TrimmedThrowableData(ex, this.stackTraceTrimmingStrategy);
        Context context = this.crashlyticsCore.getContext();
        long eventTime = time.getTime() / 1000;
        Float batteryLevel = CommonUtils.getBatteryLevel(context);
        int batteryVelocity = CommonUtils.getBatteryVelocity(context, this.devicePowerStateListener.isPowerConnected());
        boolean proximityEnabled = CommonUtils.getProximitySensorEnabled(context);
        int orientation = context.getResources().getConfiguration().orientation;
        long usedRamBytes = CommonUtils.getTotalRamInBytes() - CommonUtils.calculateFreeRamInBytes(context);
        long diskUsedBytes = CommonUtils.calculateUsedDiskSpaceInBytes(Environment.getDataDirectory().getPath());
        RunningAppProcessInfo runningAppProcessInfo = CommonUtils.getAppProcessInfo(context.getPackageName(), context);
        List<StackTraceElement[]> stacks = new LinkedList();
        StackTraceElement[] exceptionStack = trimmedEx.stacktrace;
        String buildId = this.appData.buildId;
        String appIdentifier = this.idManager.getAppIdentifier();
        if (includeAllThreads) {
            Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
            threads = new Thread[allStackTraces.size()];
            int i = 0;
            for (Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
                threads[i] = (Thread) entry.getKey();
                stacks.add(this.stackTraceTrimmingStrategy.getTrimmedStackTrace((StackTraceElement[]) entry.getValue()));
                i++;
            }
        } else {
            threads = new Thread[0];
        }
        if (CommonUtils.getBooleanResourceValue(context, "com.crashlytics.CollectCustomKeys", true)) {
            attributes = this.crashlyticsCore.getAttributes();
            if (attributes != null && attributes.size() > 1) {
                attributes = new TreeMap(attributes);
            }
        } else {
            attributes = new TreeMap();
        }
        SessionProtobufHelper.writeSessionEvent(cos, eventTime, eventType, trimmedEx, thread, exceptionStack, threads, stacks, attributes, this.logFileManager, runningAppProcessInfo, orientation, appIdentifier, buildId, batteryLevel, batteryVelocity, proximityEnabled, usedRamBytes, diskUsedBytes);
    }

    private void writeSessionPartsToSessionFile(File sessionBeginFile, String sessionId, int maxLoggedExceptionsCount) {
        Fabric.getLogger().mo34403d("CrashlyticsCore", "Collecting session parts for ID " + sessionId);
        File[] fatalFiles = listFilesMatching(new FileNameContainsFilter(sessionId + "SessionCrash"));
        boolean hasFatal = fatalFiles != null && fatalFiles.length > 0;
        Fabric.getLogger().mo34403d("CrashlyticsCore", String.format(Locale.US, "Session %s has fatal exception: %s", new Object[]{sessionId, Boolean.valueOf(hasFatal)}));
        File[] nonFatalFiles = listFilesMatching(new FileNameContainsFilter(sessionId + "SessionEvent"));
        boolean hasNonFatal = nonFatalFiles != null && nonFatalFiles.length > 0;
        Fabric.getLogger().mo34403d("CrashlyticsCore", String.format(Locale.US, "Session %s has non-fatal exceptions: %s", new Object[]{sessionId, Boolean.valueOf(hasNonFatal)}));
        if (hasFatal || hasNonFatal) {
            synthesizeSessionFile(sessionBeginFile, sessionId, getTrimmedNonFatalFiles(sessionId, nonFatalFiles, maxLoggedExceptionsCount), hasFatal ? fatalFiles[0] : null);
        } else {
            Fabric.getLogger().mo34403d("CrashlyticsCore", "No events present for session ID " + sessionId);
        }
        Fabric.getLogger().mo34403d("CrashlyticsCore", "Removing session part files for ID " + sessionId);
        deleteSessionPartFilesFor(sessionId);
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00c7  */
    private void synthesizeSessionFile(java.io.File r15, java.lang.String r16, java.io.File[] r17, java.io.File r18) {
        /*
        r14 = this;
        if (r18 == 0) goto L_0x0080;
    L_0x0002:
        r7 = 1;
    L_0x0003:
        r4 = 0;
        if (r7 == 0) goto L_0x0082;
    L_0x0006:
        r8 = r14.getFatalSessionFilesDir();
    L_0x000a:
        r9 = r8.exists();
        if (r9 != 0) goto L_0x0013;
    L_0x0010:
        r8.mkdirs();
    L_0x0013:
        r5 = 0;
        r2 = 0;
        r6 = new com.crashlytics.android.core.ClsFileOutputStream;	 Catch:{ Exception -> 0x008e }
        r0 = r16;
        r6.<init>(r8, r0);	 Catch:{ Exception -> 0x008e }
        r2 = com.crashlytics.android.core.CodedOutputStream.newInstance(r6);	 Catch:{ Exception -> 0x00d4, all -> 0x00d1 }
        r9 = p041io.fabric.sdk.android.Fabric.getLogger();	 Catch:{ Exception -> 0x00d4, all -> 0x00d1 }
        r10 = "CrashlyticsCore";
        r11 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00d4, all -> 0x00d1 }
        r11.<init>();	 Catch:{ Exception -> 0x00d4, all -> 0x00d1 }
        r12 = "Collecting SessionStart data for session ID ";
        r11 = r11.append(r12);	 Catch:{ Exception -> 0x00d4, all -> 0x00d1 }
        r0 = r16;
        r11 = r11.append(r0);	 Catch:{ Exception -> 0x00d4, all -> 0x00d1 }
        r11 = r11.toString();	 Catch:{ Exception -> 0x00d4, all -> 0x00d1 }
        r9.mo34403d(r10, r11);	 Catch:{ Exception -> 0x00d4, all -> 0x00d1 }
        writeToCosFromFile(r2, r15);	 Catch:{ Exception -> 0x00d4, all -> 0x00d1 }
        r9 = 4;
        r10 = new java.util.Date;	 Catch:{ Exception -> 0x00d4, all -> 0x00d1 }
        r10.<init>();	 Catch:{ Exception -> 0x00d4, all -> 0x00d1 }
        r10 = r10.getTime();	 Catch:{ Exception -> 0x00d4, all -> 0x00d1 }
        r12 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r10 = r10 / r12;
        r2.writeUInt64(r9, r10);	 Catch:{ Exception -> 0x00d4, all -> 0x00d1 }
        r9 = 5;
        r2.writeBool(r9, r7);	 Catch:{ Exception -> 0x00d4, all -> 0x00d1 }
        r9 = 11;
        r10 = 1;
        r2.writeUInt32(r9, r10);	 Catch:{ Exception -> 0x00d4, all -> 0x00d1 }
        r9 = 12;
        r10 = 3;
        r2.writeEnum(r9, r10);	 Catch:{ Exception -> 0x00d4, all -> 0x00d1 }
        r0 = r16;
        r14.writeInitialPartsTo(r2, r0);	 Catch:{ Exception -> 0x00d4, all -> 0x00d1 }
        r0 = r17;
        r1 = r16;
        writeNonFatalEventsTo(r2, r0, r1);	 Catch:{ Exception -> 0x00d4, all -> 0x00d1 }
        if (r7 == 0) goto L_0x0074;
    L_0x006f:
        r0 = r18;
        writeToCosFromFile(r2, r0);	 Catch:{ Exception -> 0x00d4, all -> 0x00d1 }
    L_0x0074:
        r9 = "Error flushing session file stream";
        p041io.fabric.sdk.android.services.common.CommonUtils.flushOrLog(r2, r9);
        if (r4 == 0) goto L_0x0087;
    L_0x007b:
        r14.closeWithoutRenamingOrLog(r6);
        r5 = r6;
    L_0x007f:
        return;
    L_0x0080:
        r7 = 0;
        goto L_0x0003;
    L_0x0082:
        r8 = r14.getNonFatalSessionFilesDir();
        goto L_0x000a;
    L_0x0087:
        r9 = "Failed to close CLS file";
        p041io.fabric.sdk.android.services.common.CommonUtils.closeOrLog(r6, r9);
        r5 = r6;
        goto L_0x007f;
    L_0x008e:
        r3 = move-exception;
    L_0x008f:
        r9 = p041io.fabric.sdk.android.Fabric.getLogger();	 Catch:{ all -> 0x00bf }
        r10 = "CrashlyticsCore";
        r11 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00bf }
        r11.<init>();	 Catch:{ all -> 0x00bf }
        r12 = "Failed to write session file for session ID: ";
        r11 = r11.append(r12);	 Catch:{ all -> 0x00bf }
        r0 = r16;
        r11 = r11.append(r0);	 Catch:{ all -> 0x00bf }
        r11 = r11.toString();	 Catch:{ all -> 0x00bf }
        r9.mo34406e(r10, r11, r3);	 Catch:{ all -> 0x00bf }
        r4 = 1;
        r9 = "Error flushing session file stream";
        p041io.fabric.sdk.android.services.common.CommonUtils.flushOrLog(r2, r9);
        if (r4 == 0) goto L_0x00b9;
    L_0x00b5:
        r14.closeWithoutRenamingOrLog(r5);
        goto L_0x007f;
    L_0x00b9:
        r9 = "Failed to close CLS file";
        p041io.fabric.sdk.android.services.common.CommonUtils.closeOrLog(r5, r9);
        goto L_0x007f;
    L_0x00bf:
        r9 = move-exception;
    L_0x00c0:
        r10 = "Error flushing session file stream";
        p041io.fabric.sdk.android.services.common.CommonUtils.flushOrLog(r2, r10);
        if (r4 == 0) goto L_0x00cb;
    L_0x00c7:
        r14.closeWithoutRenamingOrLog(r5);
    L_0x00ca:
        throw r9;
    L_0x00cb:
        r10 = "Failed to close CLS file";
        p041io.fabric.sdk.android.services.common.CommonUtils.closeOrLog(r5, r10);
        goto L_0x00ca;
    L_0x00d1:
        r9 = move-exception;
        r5 = r6;
        goto L_0x00c0;
    L_0x00d4:
        r3 = move-exception;
        r5 = r6;
        goto L_0x008f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crashlytics.android.core.CrashlyticsController.synthesizeSessionFile(java.io.File, java.lang.String, java.io.File[], java.io.File):void");
    }

    private static void writeNonFatalEventsTo(CodedOutputStream cos, File[] nonFatalFiles, String sessionId) {
        Arrays.sort(nonFatalFiles, CommonUtils.FILE_MODIFIED_COMPARATOR);
        for (File nonFatalFile : nonFatalFiles) {
            try {
                Fabric.getLogger().mo34403d("CrashlyticsCore", String.format(Locale.US, "Found Non Fatal for session ID %s in %s ", new Object[]{sessionId, nonFatalFile.getName()}));
                writeToCosFromFile(cos, nonFatalFile);
            } catch (Exception e) {
                Fabric.getLogger().mo34406e("CrashlyticsCore", "Error writting non-fatal to session.", e);
            }
        }
    }

    private void writeInitialPartsTo(CodedOutputStream cos, String sessionId) throws IOException {
        for (String tag : INITIAL_SESSION_PART_TAGS) {
            File[] sessionPartFiles = listFilesMatching(new FileNameContainsFilter(sessionId + tag + ".cls"));
            if (sessionPartFiles.length == 0) {
                Fabric.getLogger().mo34406e("CrashlyticsCore", "Can't find " + tag + " data for session ID " + sessionId, null);
            } else {
                Fabric.getLogger().mo34403d("CrashlyticsCore", "Collecting " + tag + " data for session ID " + sessionId);
                writeToCosFromFile(cos, sessionPartFiles[0]);
            }
        }
    }

    private static void writeToCosFromFile(CodedOutputStream cos, File file) throws IOException {
        Throwable th;
        if (file.exists()) {
            FileInputStream fis = null;
            try {
                FileInputStream fis2 = new FileInputStream(file);
                try {
                    copyToCodedOutputStream(fis2, cos, (int) file.length());
                    CommonUtils.closeOrLog(fis2, "Failed to close file input stream.");
                    return;
                } catch (Throwable th2) {
                    th = th2;
                    fis = fis2;
                    CommonUtils.closeOrLog(fis, "Failed to close file input stream.");
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                CommonUtils.closeOrLog(fis, "Failed to close file input stream.");
                throw th;
            }
        }
        Fabric.getLogger().mo34406e("CrashlyticsCore", "Tried to include a file that doesn't exist: " + file.getName(), null);
    }

    private static void copyToCodedOutputStream(InputStream inStream, CodedOutputStream cos, int bufferLength) throws IOException {
        byte[] buffer = new byte[bufferLength];
        int offset = 0;
        while (offset < buffer.length) {
            int numRead = inStream.read(buffer, offset, buffer.length - offset);
            if (numRead < 0) {
                break;
            }
            offset += numRead;
        }
        cos.writeRawBytes(buffer);
    }

    private UserMetaData getUserMetaData(String sessionId) {
        if (isHandlingException()) {
            return new UserMetaData(this.crashlyticsCore.getUserIdentifier(), this.crashlyticsCore.getUserName(), this.crashlyticsCore.getUserEmail());
        }
        return new MetaDataStore(getFilesDir()).readUserData(sessionId);
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isHandlingException() {
        return this.crashHandler != null && this.crashHandler.isHandlingException();
    }

    /* Access modifiers changed, original: 0000 */
    public File getFilesDir() {
        return this.fileStore.getFilesDir();
    }

    /* Access modifiers changed, original: 0000 */
    public File getFatalSessionFilesDir() {
        return new File(getFilesDir(), "fatal-sessions");
    }

    /* Access modifiers changed, original: 0000 */
    public File getNonFatalSessionFilesDir() {
        return new File(getFilesDir(), "nonfatal-sessions");
    }

    /* Access modifiers changed, original: 0000 */
    public File getInvalidFilesDir() {
        return new File(getFilesDir(), "invalidClsFiles");
    }

    /* Access modifiers changed, original: 0000 */
    public void registerAnalyticsEventListener(SettingsData settingsData) {
        if (settingsData.featuresData.firebaseCrashlyticsEnabled) {
            Fabric.getLogger().mo34403d("CrashlyticsCore", "Registered Firebase Analytics event listener for breadcrumbs: " + this.appMeasurementEventListenerRegistrar.register());
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void registerDevicePowerStateListener() {
        this.devicePowerStateListener.initialize();
    }

    private boolean shouldPromptUserBeforeSendingCrashReports(SettingsData settingsData) {
        if (settingsData == null || !settingsData.featuresData.promptEnabled || this.preferenceManager.shouldAlwaysSendReports()) {
            return false;
        }
        return true;
    }

    private CreateReportSpiCall getCreateReportSpiCall(String reportsUrl, String ndkReportsUrl) {
        String overriddenHost = CommonUtils.getStringsFileValue(this.crashlyticsCore.getContext(), "com.crashlytics.ApiEndpoint");
        return new CompositeCreateReportSpiCall(new DefaultCreateReportSpiCall(this.crashlyticsCore, overriddenHost, reportsUrl, this.httpRequestFactory), new NativeCreateReportSpiCall(this.crashlyticsCore, overriddenHost, ndkReportsUrl, this.httpRequestFactory));
    }

    private void sendSessionReports(SettingsData settingsData) {
        if (settingsData == null) {
            Fabric.getLogger().mo34411w("CrashlyticsCore", "Cannot send reports. Settings are unavailable.");
            return;
        }
        Context context = this.crashlyticsCore.getContext();
        ReportUploader reportUploader = new ReportUploader(this.appData.apiKey, getCreateReportSpiCall(settingsData.appData.reportsUrl, settingsData.appData.ndkReportsUrl), this.reportFilesProvider, this.handlingExceptionCheck);
        for (File finishedSessionFile : listCompleteSessionFiles()) {
            this.backgroundWorker.submit(new SendReportRunnable(context, new SessionReport(finishedSessionFile, SEND_AT_CRASHTIME_HEADER), reportUploader));
        }
    }

    private static void recordLoggedExceptionAnswersEvent(String sessionId, String exceptionName) {
        Answers answers = (Answers) Fabric.getKit(Answers.class);
        if (answers == null) {
            Fabric.getLogger().mo34403d("CrashlyticsCore", "Answers is not available");
        } else {
            answers.onException(new LoggedException(sessionId, exceptionName));
        }
    }

    private static void recordFatalExceptionAnswersEvent(String sessionId, String exceptionName) {
        Answers answers = (Answers) Fabric.getKit(Answers.class);
        if (answers == null) {
            Fabric.getLogger().mo34403d("CrashlyticsCore", "Answers is not available");
        } else {
            answers.onException(new FatalException(sessionId, exceptionName));
        }
    }

    private void recordFatalFirebaseEvent(long timestamp) {
        if (firebaseCrashExists()) {
            Fabric.getLogger().mo34403d("CrashlyticsCore", "Skipping logging Crashlytics event to Firebase, FirebaseCrash exists");
        } else if (this.firebaseAnalyticsLogger != null) {
            Fabric.getLogger().mo34403d("CrashlyticsCore", "Logging Crashlytics event to Firebase");
            Bundle params = new Bundle();
            params.putInt("_r", 1);
            params.putInt("fatal", 1);
            params.putLong(AnalyticAttribute.EVENT_TIMESTAMP_ATTRIBUTE, timestamp);
            this.firebaseAnalyticsLogger.logEvent("clx", "_ae", params);
        } else {
            Fabric.getLogger().mo34403d("CrashlyticsCore", "Skipping logging Crashlytics event to Firebase, no Firebase Analytics");
        }
    }

    private boolean firebaseCrashExists() {
        try {
            Class clazz = Class.forName("com.google.firebase.crash.FirebaseCrash");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
