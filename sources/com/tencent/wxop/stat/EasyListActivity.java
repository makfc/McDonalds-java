package com.tencent.wxop.stat;

import android.app.ListActivity;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;

@Instrumented
public class EasyListActivity extends ListActivity implements TraceFieldInterface {
    /* Access modifiers changed, original: protected */
    public void onPause() {
        super.onPause();
        StatService.onPause(this);
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        StatService.onResume(this);
    }

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
}
