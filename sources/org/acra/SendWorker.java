package org.acra;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.acra.collector.CrashReportData;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderException;

final class SendWorker implements Runnable {
    private final boolean approvePendingReports;
    private final Context context;
    private final CrashReportFileNameParser fileNameParser = new CrashReportFileNameParser();
    private boolean mAlive = true;
    private final List<ReportSender> reportSenders;
    private final boolean sendOnlySilentReports;

    public SendWorker(Context context, List<ReportSender> reportSenders, boolean sendOnlySilentReports, boolean approvePendingReports) {
        this.context = context;
        this.reportSenders = reportSenders;
        this.sendOnlySilentReports = sendOnlySilentReports;
        this.approvePendingReports = approvePendingReports;
    }

    public final void run() {
        if (this.approvePendingReports) {
            approvePendingReports();
        }
        checkAndSendReports(this.context, this.sendOnlySilentReports);
        this.mAlive = false;
    }

    private void approvePendingReports() {
        ACRA.log.mo23347d(ACRA.LOG_TAG, "Mark all pending reports as approved.");
        for (String str : new CrashReportFinder(this.context).getCrashReportFiles()) {
            if (!this.fileNameParser.isApproved(str)) {
                File file = new File(this.context.getFilesDir(), str);
                File file2 = new File(this.context.getFilesDir(), str.replace(".stacktrace", "-approved.stacktrace"));
                if (!file.renameTo(file2)) {
                    ACRA.log.mo23349e(ACRA.LOG_TAG, "Could not rename approved report from " + file + " to " + file2);
                }
            }
        }
    }

    private void checkAndSendReports(Context context, boolean sendOnlySilentReports) {
        int i = 0;
        ACRA.log.mo23347d(ACRA.LOG_TAG, "#checkAndSendReports - start");
        String[] crashReportFiles = new CrashReportFinder(context).getCrashReportFiles();
        Arrays.sort(crashReportFiles);
        for (String str : crashReportFiles) {
            if (!sendOnlySilentReports || this.fileNameParser.isSilent(str)) {
                if (i >= 5) {
                    break;
                }
                ACRA.log.mo23351i(ACRA.LOG_TAG, "Sending file " + str);
                try {
                    sendCrashReport(new CrashReportPersister(context).load(str));
                    deleteFile(context, str);
                } catch (RuntimeException e) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Failed to send crash reports for " + str, e);
                    deleteFile(context, str);
                } catch (IOException e2) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Failed to load crash report for " + str, e2);
                    deleteFile(context, str);
                } catch (ReportSenderException e3) {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "Failed to send crash report for " + str, e3);
                }
                i++;
            }
        }
        ACRA.log.mo23347d(ACRA.LOG_TAG, "#checkAndSendReports - finish");
    }

    private void sendCrashReport(CrashReportData errorContent) throws ReportSenderException {
        if (!ACRA.isDebuggable() || ACRA.getConfig().sendReportsInDevMode()) {
            ReportSenderException reportSenderException = null;
            Object obj = null;
            String str = null;
            for (ReportSender reportSender : this.reportSenders) {
                try {
                    ACRA.log.mo23347d(ACRA.LOG_TAG, "Sending report using " + reportSender.getClass().getName());
                    reportSender.send(this.context, errorContent);
                    ACRA.log.mo23347d(ACRA.LOG_TAG, "Sent report using " + reportSender.getClass().getName());
                    obj = 1;
                } catch (ReportSenderException e) {
                    reportSenderException = e;
                    str = reportSender.getClass().getName();
                }
            }
            if (reportSenderException == null) {
                return;
            }
            if (obj == null) {
                throw reportSenderException;
            }
            ACRA.log.mo23353w(ACRA.LOG_TAG, "ReportSender of class " + str + " failed but other senders completed their task. ACRA will not send this report again.");
        }
    }

    private void deleteFile(Context context, String fileName) {
        if (!context.deleteFile(fileName)) {
            ACRA.log.mo23353w(ACRA.LOG_TAG, "Could not delete error report : " + fileName);
        }
    }

    public final boolean isAlive() {
        return this.mAlive;
    }
}
