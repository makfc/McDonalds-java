package p041io.fabric.sdk.android;

import p041io.fabric.sdk.android.services.common.TimingMetric;
import p041io.fabric.sdk.android.services.concurrency.Priority;
import p041io.fabric.sdk.android.services.concurrency.PriorityAsyncTask;

/* renamed from: io.fabric.sdk.android.InitializationTask */
class InitializationTask<Result> extends PriorityAsyncTask<Void, Void, Result> {
    final Kit<Result> kit;

    public InitializationTask(Kit<Result> kit) {
        this.kit = kit;
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Missing block: B:23:?, code skipped:
            return;
     */
    public void onPreExecute() {
        /*
        r7 = this;
        r6 = 1;
        super.onPreExecute();
        r3 = "onPreExecute";
        r2 = r7.createAndStartTimingMetric(r3);
        r1 = 0;
        r3 = r7.kit;	 Catch:{ UnmetDependencyException -> 0x001a, Exception -> 0x0026 }
        r1 = r3.onPreExecute();	 Catch:{ UnmetDependencyException -> 0x001a, Exception -> 0x0026 }
        r2.stopMeasuring();
        if (r1 != 0) goto L_0x0019;
    L_0x0016:
        r7.cancel(r6);
    L_0x0019:
        return;
    L_0x001a:
        r0 = move-exception;
        throw r0;	 Catch:{ all -> 0x001c }
    L_0x001c:
        r3 = move-exception;
        r2.stopMeasuring();
        if (r1 != 0) goto L_0x0025;
    L_0x0022:
        r7.cancel(r6);
    L_0x0025:
        throw r3;
    L_0x0026:
        r0 = move-exception;
        r3 = p041io.fabric.sdk.android.Fabric.getLogger();	 Catch:{ all -> 0x001c }
        r4 = "Fabric";
        r5 = "Failure onPreExecute()";
        r3.mo34406e(r4, r5, r0);	 Catch:{ all -> 0x001c }
        r2.stopMeasuring();
        if (r1 != 0) goto L_0x0019;
    L_0x0037:
        r7.cancel(r6);
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: p041io.fabric.sdk.android.InitializationTask.onPreExecute():void");
    }

    /* Access modifiers changed, original: protected|varargs */
    public Result doInBackground(Void... voids) {
        TimingMetric timingMetric = createAndStartTimingMetric("doInBackground");
        Result result = null;
        if (!isCancelled()) {
            result = this.kit.doInBackground();
        }
        timingMetric.stopMeasuring();
        return result;
    }

    /* Access modifiers changed, original: protected */
    public void onPostExecute(Result result) {
        this.kit.onPostExecute(result);
        this.kit.initializationCallback.success(result);
    }

    /* Access modifiers changed, original: protected */
    public void onCancelled(Result result) {
        this.kit.onCancelled(result);
        this.kit.initializationCallback.failure(new InitializationException(this.kit.getIdentifier() + " Initialization was cancelled"));
    }

    public Priority getPriority() {
        return Priority.HIGH;
    }

    private TimingMetric createAndStartTimingMetric(String event) {
        TimingMetric timingMetric = new TimingMetric(this.kit.getIdentifier() + "." + event, "KitInitialization");
        timingMetric.startMeasuring();
        return timingMetric;
    }
}
