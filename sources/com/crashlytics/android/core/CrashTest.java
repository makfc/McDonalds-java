package com.crashlytics.android.core;

import android.os.AsyncTask;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;

public class CrashTest {

    /* renamed from: com.crashlytics.android.core.CrashTest$1 */
    class C16351 extends AsyncTask<Void, Void, Void> implements TraceFieldInterface {
        public Trace _nr_trace;
        final /* synthetic */ CrashTest this$0;
        final /* synthetic */ long val$delayMs;

        public void _nr_setTrace(Trace trace) {
            try {
                this._nr_trace = trace;
            } catch (Exception e) {
            }
        }

        /* Access modifiers changed, original: protected|varargs */
        public Void doInBackground(Void... params) {
            try {
                Thread.sleep(this.val$delayMs);
            } catch (InterruptedException e) {
            }
            this.this$0.throwRuntimeException("Background thread crash");
            return null;
        }
    }

    public void throwRuntimeException(String message) {
        throw new RuntimeException(message);
    }
}
