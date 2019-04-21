package org.acra;

import android.app.Activity;
import android.os.Bundle;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.IOException;
import org.acra.collector.CrashReportData;
import org.acra.util.ToastSender;

@Instrumented
public abstract class BaseCrashReportDialog extends Activity implements TraceFieldInterface {
    public Trace _nr_trace;
    private String mReportFileName;

    /* Access modifiers changed, original: protected */
    public void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("BaseCrashReportDialog");
        try {
            TraceMachine.enterMethod(this._nr_trace, "BaseCrashReportDialog#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "BaseCrashReportDialog#onCreate", null);
            }
        }
        super.onCreate(bundle);
        ACRA.log.mo23347d(ACRA.LOG_TAG, "CrashReportDialog extras=" + getIntent().getExtras());
        if (getIntent().getBooleanExtra("FORCE_CANCEL", false)) {
            ACRA.log.mo23347d(ACRA.LOG_TAG, "Forced reports deletion.");
            cancelReports();
            finish();
            TraceMachine.exitMethod();
            return;
        }
        this.mReportFileName = getIntent().getStringExtra("REPORT_FILE_NAME");
        ACRA.log.mo23347d(ACRA.LOG_TAG, "Opening CrashReportDialog for " + this.mReportFileName);
        if (this.mReportFileName == null) {
            finish();
        }
        TraceMachine.exitMethod();
    }

    /* Access modifiers changed, original: protected */
    public void cancelReports() {
        ACRA.getErrorReporter().deletePendingNonApprovedReports(false);
    }

    /* Access modifiers changed, original: protected */
    public void sendCrash(String comment, String userEmail) {
        CrashReportPersister crashReportPersister = new CrashReportPersister(getApplicationContext());
        try {
            ACRA.log.mo23347d(ACRA.LOG_TAG, "Add user comment to " + this.mReportFileName);
            CrashReportData load = crashReportPersister.load(this.mReportFileName);
            ReportField reportField = ReportField.USER_COMMENT;
            if (comment == null) {
                comment = "";
            }
            load.put(reportField, comment);
            reportField = ReportField.USER_EMAIL;
            if (userEmail == null) {
                userEmail = "";
            }
            load.put(reportField, userEmail);
            crashReportPersister.store(load, this.mReportFileName);
        } catch (IOException e) {
            ACRA.log.mo23354w(ACRA.LOG_TAG, "User comment not added: ", e);
        }
        ACRA.log.mo23352v(ACRA.LOG_TAG, "About to start SenderWorker from CrashReportDialog");
        ACRA.getErrorReporter().startSendingReports(false, true);
        int resDialogOkToast = ACRA.getConfig().resDialogOkToast();
        if (resDialogOkToast != 0) {
            ToastSender.sendToast(getApplicationContext(), resDialogOkToast, 1);
        }
    }
}
