package com.mcdonalds.sdk.modules.storelocator;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;

@Instrumented
@Deprecated
class GooglePlayServicesErrorDialog extends DialogFragment implements TraceFieldInterface {
    private Dialog mDialog = null;

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

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return this.mDialog;
    }
}
