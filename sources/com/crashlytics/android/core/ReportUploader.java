package com.crashlytics.android.core;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import p041io.fabric.sdk.android.Fabric;
import p041io.fabric.sdk.android.services.common.BackgroundPriorityRunnable;

class ReportUploader {
    static final Map<String, String> HEADER_INVALID_CLS_FILE = Collections.singletonMap("X-CRASHLYTICS-INVALID-SESSION", "1");
    private static final short[] RETRY_INTERVALS = new short[]{(short) 10, (short) 20, (short) 30, (short) 60, (short) 120, (short) 300};
    private final String apiKey;
    private final CreateReportSpiCall createReportCall;
    private final Object fileAccessLock = new Object();
    private final HandlingExceptionCheck handlingExceptionCheck;
    private final ReportFilesProvider reportFilesProvider;
    private Thread uploadThread;

    interface SendCheck {
        boolean canSendReports();
    }

    interface ReportFilesProvider {
        File[] getCompleteSessionFiles();

        File[] getInvalidSessionFiles();

        File[] getNativeReportFiles();
    }

    interface HandlingExceptionCheck {
        boolean isHandlingException();
    }

    static final class AlwaysSendCheck implements SendCheck {
        AlwaysSendCheck() {
        }

        public boolean canSendReports() {
            return true;
        }
    }

    private class Worker extends BackgroundPriorityRunnable {
        private final float delay;
        private final SendCheck sendCheck;

        Worker(float delay, SendCheck sendCheck) {
            this.delay = delay;
            this.sendCheck = sendCheck;
        }

        public void onRun() {
            try {
                attemptUploadWithRetry();
            } catch (Exception e) {
                Fabric.getLogger().mo34406e("CrashlyticsCore", "An unexpected error occurred while attempting to upload crash reports.", e);
            }
            ReportUploader.this.uploadThread = null;
        }

        private void attemptUploadWithRetry() {
            Fabric.getLogger().mo34403d("CrashlyticsCore", "Starting report processing in " + this.delay + " second(s)...");
            if (this.delay > 0.0f) {
                try {
                    Thread.sleep((long) (this.delay * 1000.0f));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            List<Report> reports = ReportUploader.this.findReports();
            if (!ReportUploader.this.handlingExceptionCheck.isHandlingException()) {
                if (reports.isEmpty() || this.sendCheck.canSendReports()) {
                    int retryCount = 0;
                    while (!reports.isEmpty() && !ReportUploader.this.handlingExceptionCheck.isHandlingException()) {
                        Fabric.getLogger().mo34403d("CrashlyticsCore", "Attempting to send " + reports.size() + " report(s)");
                        for (Report report : reports) {
                            ReportUploader.this.forceUpload(report);
                        }
                        reports = ReportUploader.this.findReports();
                        if (!reports.isEmpty()) {
                            int retryCount2 = retryCount + 1;
                            long interval = (long) ReportUploader.RETRY_INTERVALS[Math.min(retryCount, ReportUploader.RETRY_INTERVALS.length - 1)];
                            Fabric.getLogger().mo34403d("CrashlyticsCore", "Report submisson: scheduling delayed retry in " + interval + " seconds");
                            try {
                                Thread.sleep(1000 * interval);
                                retryCount = retryCount2;
                            } catch (InterruptedException e2) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                        }
                    }
                    return;
                }
                Fabric.getLogger().mo34403d("CrashlyticsCore", "User declined to send. Removing " + reports.size() + " Report(s).");
                for (Report report2 : reports) {
                    report2.remove();
                }
            }
        }
    }

    public ReportUploader(String apiKey, CreateReportSpiCall createReportCall, ReportFilesProvider reportFilesProvider, HandlingExceptionCheck handlingExceptionCheck) {
        if (createReportCall == null) {
            throw new IllegalArgumentException("createReportCall must not be null.");
        }
        this.createReportCall = createReportCall;
        this.apiKey = apiKey;
        this.reportFilesProvider = reportFilesProvider;
        this.handlingExceptionCheck = handlingExceptionCheck;
    }

    public synchronized void uploadReports(float delay, SendCheck sendCheck) {
        if (this.uploadThread != null) {
            Fabric.getLogger().mo34403d("CrashlyticsCore", "Report upload has already been started.");
        } else {
            this.uploadThread = new Thread(new Worker(delay, sendCheck), "Crashlytics Report Uploader");
            this.uploadThread.start();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean forceUpload(Report report) {
        boolean removed = false;
        synchronized (this.fileAccessLock) {
            try {
                boolean sent = this.createReportCall.invoke(new CreateReportRequest(this.apiKey, report));
                Fabric.getLogger().mo34407i("CrashlyticsCore", "Crashlytics report upload " + (sent ? "complete: " : "FAILED: ") + report.getIdentifier());
                if (sent) {
                    report.remove();
                    removed = true;
                }
            } catch (Exception e) {
                Fabric.getLogger().mo34406e("CrashlyticsCore", "Error occurred sending report " + report, e);
            }
        }
        return removed;
    }

    /* Access modifiers changed, original: 0000 */
    public List<Report> findReports() {
        File[] clsFiles;
        File[] invalidClsFiles;
        File[] nativeReportFiles;
        Fabric.getLogger().mo34403d("CrashlyticsCore", "Checking for crash reports...");
        synchronized (this.fileAccessLock) {
            clsFiles = this.reportFilesProvider.getCompleteSessionFiles();
            invalidClsFiles = this.reportFilesProvider.getInvalidSessionFiles();
            nativeReportFiles = this.reportFilesProvider.getNativeReportFiles();
        }
        List<Report> reports = new LinkedList();
        if (clsFiles != null) {
            for (File file : clsFiles) {
                Fabric.getLogger().mo34403d("CrashlyticsCore", "Found crash report " + file.getPath());
                reports.add(new SessionReport(file));
            }
        }
        Map<String, List<File>> invalidSessionFiles = new HashMap();
        if (invalidClsFiles != null) {
            for (File invalidFile : invalidClsFiles) {
                String sessionId = CrashlyticsController.getSessionIdFromSessionFile(invalidFile);
                if (!invalidSessionFiles.containsKey(sessionId)) {
                    invalidSessionFiles.put(sessionId, new LinkedList());
                }
                ((List) invalidSessionFiles.get(sessionId)).add(invalidFile);
            }
        }
        for (String key : invalidSessionFiles.keySet()) {
            Fabric.getLogger().mo34403d("CrashlyticsCore", "Found invalid session: " + key);
            List<File> invalidFiles = (List) invalidSessionFiles.get(key);
            reports.add(new InvalidSessionReport(key, (File[]) invalidFiles.toArray(new File[invalidFiles.size()])));
        }
        if (nativeReportFiles != null) {
            for (File dir : nativeReportFiles) {
                reports.add(new NativeSessionReport(dir));
            }
        }
        if (reports.isEmpty()) {
            Fabric.getLogger().mo34403d("CrashlyticsCore", "No reports found.");
        }
        return reports;
    }
}
