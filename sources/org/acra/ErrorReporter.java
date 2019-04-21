package org.acra;

import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.support.p000v4.app.NotificationCompat.Builder;
import java.io.File;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import org.acra.collector.Compatibility;
import org.acra.collector.ConfigurationCollector;
import org.acra.collector.CrashReportData;
import org.acra.collector.CrashReportDataFactory;
import org.acra.jraf.android.util.activitylifecyclecallbackscompat.ActivityLifecycleCallbacksCompat;
import org.acra.jraf.android.util.activitylifecyclecallbackscompat.ApplicationHelper;
import org.acra.sender.EmailIntentSender;
import org.acra.sender.HttpSender;
import org.acra.sender.ReportSender;
import org.acra.util.PackageManagerWrapper;
import org.acra.util.ToastSender;

public class ErrorReporter implements UncaughtExceptionHandler {
    private static final ExceptionHandlerInitializer NULL_EXCEPTION_HANDLER_INITIALIZER = new C26331();
    private static int mNotificationCounter = 0;
    private final CrashReportDataFactory crashReportDataFactory;
    private boolean enabled = false;
    private volatile ExceptionHandlerInitializer exceptionHandlerInitializer = NULL_EXCEPTION_HANDLER_INITIALIZER;
    private final CrashReportFileNameParser fileNameParser = new CrashReportFileNameParser();
    private WeakReference<Activity> lastActivityCreated = new WeakReference(null);
    private final Application mContext;
    private final UncaughtExceptionHandler mDfltExceptionHandler;
    private final List<ReportSender> mReportSenders = new ArrayList();
    private final SharedPreferences prefs;
    private final boolean supportedAndroidVersion;
    private boolean toastWaitEnded = true;

    /* renamed from: org.acra.ErrorReporter$1 */
    static class C26331 implements ExceptionHandlerInitializer {
        C26331() {
        }

        public final void initializeExceptionHandler(ErrorReporter reporter) {
        }
    }

    /* renamed from: org.acra.ErrorReporter$2 */
    class C26342 implements ActivityLifecycleCallbacksCompat {
        C26342() {
        }

        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            if (!(activity instanceof BaseCrashReportDialog)) {
                ErrorReporter.this.lastActivityCreated = new WeakReference(activity);
            }
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        public void onActivityDestroyed(Activity activity) {
        }
    }

    public final class ReportBuilder {
        private Map<String, String> mCustomData;
        private boolean mEndsApplication = false;
        private Throwable mException;
        private boolean mForceSilent = false;
        private String mMessage;
        private Thread mUncaughtExceptionThread;

        private ReportBuilder uncaughtExceptionThread(Thread thread) {
            this.mUncaughtExceptionThread = thread;
            return this;
        }

        public final ReportBuilder exception(Throwable e) {
            this.mException = e;
            return this;
        }

        public final ReportBuilder forceSilent() {
            this.mForceSilent = true;
            return this;
        }

        public final ReportBuilder endsApplication() {
            this.mEndsApplication = true;
            return this;
        }

        public final void send() {
            if (this.mMessage == null && this.mException == null) {
                this.mMessage = "Report requested by developer";
            }
            ErrorReporter.this.report(this);
        }
    }

    static class TimeHelper {
        private Long initialTimeMillis;

        private TimeHelper() {
        }

        /* synthetic */ TimeHelper(C26331 x0) {
            this();
        }

        public void setInitialTimeMillis(long initialTimeMillis) {
            this.initialTimeMillis = Long.valueOf(initialTimeMillis);
        }

        public long getElapsedTime() {
            return this.initialTimeMillis == null ? 0 : System.currentTimeMillis() - this.initialTimeMillis.longValue();
        }
    }

    ErrorReporter(Application context, SharedPreferences prefs, boolean enabled, boolean supportedAndroidVersion) {
        String str = null;
        this.mContext = context;
        this.prefs = prefs;
        this.enabled = enabled;
        this.supportedAndroidVersion = supportedAndroidVersion;
        if (ACRA.getConfig().getReportFields().contains(ReportField.INITIAL_CONFIGURATION)) {
            str = ConfigurationCollector.collectConfiguration(this.mContext);
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        if (Compatibility.getAPILevel() >= 14) {
            ApplicationHelper.registerActivityLifecycleCallbacks(context, new C26342());
        }
        this.crashReportDataFactory = new CrashReportDataFactory(this.mContext, prefs, gregorianCalendar, str);
        this.mDfltExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void addReportSender(ReportSender sender) {
        this.mReportSenders.add(sender);
    }

    public void removeAllReportSenders() {
        this.mReportSenders.clear();
    }

    public void setReportSender(ReportSender sender) {
        removeAllReportSenders();
        addReportSender(sender);
    }

    public void uncaughtException(Thread t, Throwable e) {
        try {
            if (this.enabled) {
                ACRA.log.mo23350e(ACRA.LOG_TAG, "ACRA caught a " + e.getClass().getSimpleName() + " for " + this.mContext.getPackageName(), e);
                ACRA.log.mo23347d(ACRA.LOG_TAG, "Building report");
                reportBuilder().uncaughtExceptionThread(t).exception(e).endsApplication().send();
            } else if (this.mDfltExceptionHandler != null) {
                ACRA.log.mo23349e(ACRA.LOG_TAG, "ACRA is disabled for " + this.mContext.getPackageName() + " - forwarding uncaught Exception on to default ExceptionHandler");
                this.mDfltExceptionHandler.uncaughtException(t, e);
            } else {
                ACRA.log.mo23349e(ACRA.LOG_TAG, "ACRA is disabled for " + this.mContext.getPackageName() + " - no default ExceptionHandler");
                ACRA.log.mo23350e(ACRA.LOG_TAG, "ACRA caught a " + e.getClass().getSimpleName() + " for " + this.mContext.getPackageName(), e);
            }
        } catch (Throwable th) {
            if (this.mDfltExceptionHandler != null) {
                this.mDfltExceptionHandler.uncaughtException(t, e);
            }
        }
    }

    private void endApplication(Thread uncaughtExceptionThread, Throwable th) {
        Object obj = 1;
        Object obj2 = (ACRA.getConfig().mode() == ReportingInteractionMode.SILENT || (ACRA.getConfig().mode() == ReportingInteractionMode.TOAST && ACRA.getConfig().forceCloseDialogAfterToast())) ? 1 : null;
        if (uncaughtExceptionThread == null) {
            obj = null;
        }
        if (obj == null || obj2 == null || this.mDfltExceptionHandler == null) {
            ACRA.log.mo23350e(ACRA.LOG_TAG, this.mContext.getPackageName() + " fatal error : " + th.getMessage(), th);
            Activity activity = (Activity) this.lastActivityCreated.get();
            if (activity != null) {
                ACRA.log.mo23351i(ACRA.LOG_TAG, "Finishing the last Activity prior to killing the Process");
                activity.finish();
                ACRA.log.mo23351i(ACRA.LOG_TAG, "Finished " + activity.getClass());
                this.lastActivityCreated.clear();
            }
            Process.killProcess(Process.myPid());
            System.exit(10);
            return;
        }
        ACRA.log.mo23347d(ACRA.LOG_TAG, "Handing Exception on to default ExceptionHandler");
        this.mDfltExceptionHandler.uncaughtException(uncaughtExceptionThread, th);
    }

    public void handleSilentException(Throwable e) {
        if (this.enabled) {
            reportBuilder().exception(e).forceSilent().send();
            ACRA.log.mo23347d(ACRA.LOG_TAG, "ACRA sent Silent report.");
            return;
        }
        ACRA.log.mo23347d(ACRA.LOG_TAG, "ACRA is disabled. Silent report not sent.");
    }

    public void setEnabled(boolean enabled) {
        if (this.supportedAndroidVersion) {
            ACRA.log.mo23351i(ACRA.LOG_TAG, "ACRA is " + (enabled ? "enabled" : "disabled") + " for " + this.mContext.getPackageName());
            this.enabled = enabled;
            return;
        }
        ACRA.log.mo23353w(ACRA.LOG_TAG, "ACRA 4.7.0+ requires Froyo or greater. ACRA is disabled and will NOT catch crashes or send messages.");
    }

    /* Access modifiers changed, original: 0000 */
    public SendWorker startSendingReports(boolean onlySendSilentReports, boolean approveReportsFirst) {
        SendWorker sendWorker = new SendWorker(this.mContext, this.mReportSenders, onlySendSilentReports, approveReportsFirst);
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            sendWorker.run();
        } else {
            new Handler(this.mContext.getMainLooper()).post(sendWorker);
        }
        return sendWorker;
    }

    /* Access modifiers changed, original: 0000 */
    public void deletePendingReports() {
        deletePendingReports(true, true, 0);
    }

    public void checkReportsOnApplicationStart() {
        if (ACRA.getConfig().deleteOldUnsentReportsOnApplicationStart()) {
            long j = (long) this.prefs.getInt(ACRA.PREF_LAST_VERSION_NR, 0);
            PackageInfo packageInfo = new PackageManagerWrapper(this.mContext).getPackageInfo();
            if (packageInfo != null) {
                if (((long) packageInfo.versionCode) > j) {
                    deletePendingReports();
                }
                Editor edit = this.prefs.edit();
                edit.putInt(ACRA.PREF_LAST_VERSION_NR, packageInfo.versionCode);
                edit.commit();
            }
        }
        ReportingInteractionMode mode = ACRA.getConfig().mode();
        if ((mode == ReportingInteractionMode.NOTIFICATION || mode == ReportingInteractionMode.DIALOG) && ACRA.getConfig().deleteUnapprovedReportsOnApplicationStart()) {
            deletePendingNonApprovedReports(true);
        }
        String[] crashReportFiles = new CrashReportFinder(this.mContext).getCrashReportFiles();
        if (crashReportFiles != null && crashReportFiles.length > 0) {
            boolean containsOnlySilentOrApprovedReports = containsOnlySilentOrApprovedReports(crashReportFiles);
            if (!(mode == ReportingInteractionMode.SILENT || mode == ReportingInteractionMode.TOAST)) {
                if (!containsOnlySilentOrApprovedReports) {
                    return;
                }
                if (!(mode == ReportingInteractionMode.NOTIFICATION || mode == ReportingInteractionMode.DIALOG)) {
                    return;
                }
            }
            if (mode == ReportingInteractionMode.TOAST && !containsOnlySilentOrApprovedReports) {
                ToastSender.sendToast(this.mContext, ACRA.getConfig().resToastText(), 1);
            }
            ACRA.log.mo23352v(ACRA.LOG_TAG, "About to start ReportSenderWorker from #checkReportOnApplicationStart");
            startSendingReports(false, false);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void deletePendingNonApprovedReports(boolean keepOne) {
        int i;
        if (keepOne) {
            i = 1;
        } else {
            i = 0;
        }
        deletePendingReports(false, true, i);
    }

    public ReportBuilder reportBuilder() {
        return new ReportBuilder();
    }

    private void report(ReportBuilder reportBuilder) {
        if (this.enabled) {
            ReportingInteractionMode reportingInteractionMode;
            boolean z;
            boolean z2;
            SendWorker startSendingReports;
            try {
                this.exceptionHandlerInitializer.initializeExceptionHandler(this);
            } catch (Exception e) {
                ACRA.log.mo23347d(ACRA.LOG_TAG, "Failed to initlize " + this.exceptionHandlerInitializer + " from #handleException");
            }
            if (reportBuilder.mForceSilent) {
                ReportingInteractionMode reportingInteractionMode2 = ReportingInteractionMode.SILENT;
                if (ACRA.getConfig().mode() != ReportingInteractionMode.SILENT) {
                    reportingInteractionMode = reportingInteractionMode2;
                    z = true;
                } else {
                    reportingInteractionMode = reportingInteractionMode2;
                    z = false;
                }
            } else {
                reportingInteractionMode = ACRA.getConfig().mode();
                z = false;
            }
            if (reportingInteractionMode == ReportingInteractionMode.TOAST || (ACRA.getConfig().resToastText() != 0 && (reportingInteractionMode == ReportingInteractionMode.NOTIFICATION || reportingInteractionMode == ReportingInteractionMode.DIALOG))) {
                z2 = true;
            } else {
                z2 = false;
            }
            final TimeHelper timeHelper = new TimeHelper();
            if (z2) {
                new Thread() {
                    public void run() {
                        Looper.prepare();
                        ToastSender.sendToast(ErrorReporter.this.mContext, ACRA.getConfig().resToastText(), 1);
                        timeHelper.setInitialTimeMillis(System.currentTimeMillis());
                        Looper.loop();
                    }
                }.start();
            }
            CrashReportData createCrashData = this.crashReportDataFactory.createCrashData(reportBuilder.mMessage, reportBuilder.mException, reportBuilder.mCustomData, reportBuilder.mForceSilent, reportBuilder.mUncaughtExceptionThread);
            final String reportFileName = getReportFileName(createCrashData);
            saveCrashReportFile(reportFileName, createCrashData);
            if (reportBuilder.mEndsApplication && !ACRA.getConfig().sendReportsAtShutdown()) {
                endApplication(reportBuilder.mUncaughtExceptionThread, reportBuilder.mException);
            }
            if (reportingInteractionMode == ReportingInteractionMode.SILENT || reportingInteractionMode == ReportingInteractionMode.TOAST || this.prefs.getBoolean(ACRA.PREF_ALWAYS_ACCEPT, false)) {
                ACRA.log.mo23347d(ACRA.LOG_TAG, "About to start ReportSenderWorker from #handleException");
                startSendingReports = startSendingReports(z, true);
                if (reportingInteractionMode == ReportingInteractionMode.SILENT && !reportBuilder.mEndsApplication) {
                    return;
                }
            }
            if (reportingInteractionMode == ReportingInteractionMode.NOTIFICATION) {
                ACRA.log.mo23347d(ACRA.LOG_TAG, "Creating Notification.");
                createNotification(reportFileName, reportBuilder);
            }
            startSendingReports = null;
            this.toastWaitEnded = true;
            if (z2) {
                this.toastWaitEnded = false;
                new Thread() {
                    public void run() {
                        ACRA.log.mo23347d(ACRA.LOG_TAG, "Waiting for 2000 millis from " + timeHelper.initialTimeMillis + " currentMillis=" + System.currentTimeMillis());
                        while (timeHelper.getElapsedTime() < 2000) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                ACRA.log.mo23348d(ACRA.LOG_TAG, "Interrupted while waiting for Toast to end.", e);
                            }
                        }
                        ErrorReporter.this.toastWaitEnded = true;
                    }
                }.start();
            }
            final boolean z3 = reportingInteractionMode == ReportingInteractionMode.DIALOG && !this.prefs.getBoolean(ACRA.PREF_ALWAYS_ACCEPT, false);
            final ReportBuilder reportBuilder2 = reportBuilder;
            new Thread() {
                public void run() {
                    ACRA.log.mo23347d(ACRA.LOG_TAG, "Waiting for Toast");
                    while (!ErrorReporter.this.toastWaitEnded) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            ACRA.log.mo23348d(ACRA.LOG_TAG, "Error : ", e);
                        }
                    }
                    ACRA.log.mo23347d(ACRA.LOG_TAG, "Finished waiting for Toast");
                    if (startSendingReports != null) {
                        ACRA.log.mo23347d(ACRA.LOG_TAG, "Waiting for Worker");
                        while (startSendingReports.isAlive()) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e2) {
                                ACRA.log.mo23348d(ACRA.LOG_TAG, "Error : ", e2);
                            }
                        }
                        ACRA.log.mo23347d(ACRA.LOG_TAG, "Finished waiting for Worker");
                    }
                    if (z3) {
                        ACRA.log.mo23347d(ACRA.LOG_TAG, "Creating CrashReportDialog for " + reportFileName);
                        Intent access$1200 = ErrorReporter.this.createCrashReportDialogIntent(reportFileName, reportBuilder2);
                        access$1200.setFlags(268435456);
                        ErrorReporter.this.mContext.startActivity(access$1200);
                    }
                    ACRA.log.mo23347d(ACRA.LOG_TAG, "Wait for Toast + worker ended. Kill Application ? " + reportBuilder2.mEndsApplication);
                    if (reportBuilder2.mEndsApplication) {
                        ErrorReporter.this.endApplication(reportBuilder2.mUncaughtExceptionThread, reportBuilder2.mException);
                    }
                }
            }.start();
        }
    }

    private Intent createCrashReportDialogIntent(String reportFileName, ReportBuilder reportBuilder) {
        ACRA.log.mo23347d(ACRA.LOG_TAG, "Creating DialogIntent for " + reportFileName + " exception=" + reportBuilder.mException);
        Intent intent = new Intent(this.mContext, ACRA.getConfig().reportDialogClass());
        intent.putExtra("REPORT_FILE_NAME", reportFileName);
        intent.putExtra("REPORT_EXCEPTION", reportBuilder.mException);
        return intent;
    }

    private void createNotification(String reportFileName, ReportBuilder reportBuilder) {
        NotificationManager notificationManager = (NotificationManager) this.mContext.getSystemService("notification");
        ACRAConfiguration config = ACRA.getConfig();
        int resNotifIcon = config.resNotifIcon();
        CharSequence text = this.mContext.getText(config.resNotifTickerText());
        long currentTimeMillis = System.currentTimeMillis();
        ACRA.log.mo23347d(ACRA.LOG_TAG, "Creating Notification for " + reportFileName);
        Intent createCrashReportDialogIntent = createCrashReportDialogIntent(reportFileName, reportBuilder);
        Application application = this.mContext;
        int i = mNotificationCounter;
        mNotificationCounter = i + 1;
        PendingIntent activity = PendingIntent.getActivity(application, i, createCrashReportDialogIntent, 134217728);
        CharSequence text2 = this.mContext.getText(config.resNotifTitle());
        Notification build = new Builder(this.mContext).setSmallIcon(resNotifIcon).setTicker(text).setWhen(currentTimeMillis).setAutoCancel(true).setContentTitle(text2).setContentText(this.mContext.getText(config.resNotifText())).setContentIntent(activity).build();
        build.flags |= 16;
        Intent createCrashReportDialogIntent2 = createCrashReportDialogIntent(reportFileName, reportBuilder);
        createCrashReportDialogIntent2.putExtra("FORCE_CANCEL", true);
        build.deleteIntent = PendingIntent.getActivity(this.mContext, -1, createCrashReportDialogIntent2, 0);
        notificationManager.notify(666, build);
    }

    private String getReportFileName(CrashReportData crashData) {
        return new GregorianCalendar().getTimeInMillis() + (crashData.getProperty(ReportField.IS_SILENT) != null ? ACRAConstants.SILENT_SUFFIX : "") + ".stacktrace";
    }

    private void saveCrashReportFile(String fileName, CrashReportData crashData) {
        try {
            ACRA.log.mo23347d(ACRA.LOG_TAG, "Writing crash report file " + fileName + ".");
            new CrashReportPersister(this.mContext).store(crashData, fileName);
        } catch (Exception e) {
            ACRA.log.mo23350e(ACRA.LOG_TAG, "An error occurred while writing the report file...", e);
        }
    }

    private void deletePendingReports(boolean deleteApprovedReports, boolean deleteNonApprovedReports, int nbOfLatestToKeep) {
        String[] crashReportFiles = new CrashReportFinder(this.mContext).getCrashReportFiles();
        Arrays.sort(crashReportFiles);
        for (int i = 0; i < crashReportFiles.length - nbOfLatestToKeep; i++) {
            String str = crashReportFiles[i];
            boolean isApproved = this.fileNameParser.isApproved(str);
            if ((isApproved && deleteApprovedReports) || (!isApproved && deleteNonApprovedReports)) {
                File file = new File(this.mContext.getFilesDir(), str);
                ACRA.log.mo23347d(ACRA.LOG_TAG, "Deleting file " + str);
                if (!file.delete()) {
                    ACRA.log.mo23349e(ACRA.LOG_TAG, "Could not delete report : " + file);
                }
            }
        }
    }

    private boolean containsOnlySilentOrApprovedReports(String[] reportFileNames) {
        for (String isApproved : reportFileNames) {
            if (!this.fileNameParser.isApproved(isApproved)) {
                return false;
            }
        }
        return true;
    }

    public void setDefaultReportSenders() {
        ACRAConfiguration config = ACRA.getConfig();
        Application application = ACRA.getApplication();
        removeAllReportSenders();
        if (!"".equals(config.mailTo())) {
            ACRA.log.mo23353w(ACRA.LOG_TAG, application.getPackageName() + " reports will be sent by email (if accepted by user).");
            setReportSender(new EmailIntentSender(application));
        } else if (!new PackageManagerWrapper(application).hasPermission("android.permission.INTERNET")) {
            ACRA.log.mo23349e(ACRA.LOG_TAG, application.getPackageName() + " should be granted permission android.permission.INTERNET" + " if you want your crash reports to be sent. If you don't want to add this permission to your application you can also enable sending reports by email. If this is your will then provide your email address in @ReportsCrashes(mailTo=\"your.account@domain.com\"");
        } else if (config.formUri() != null && !"".equals(config.formUri())) {
            setReportSender(new HttpSender(ACRA.getConfig().httpMethod(), ACRA.getConfig().reportType(), null));
        }
    }
}
