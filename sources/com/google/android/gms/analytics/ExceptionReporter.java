package com.google.android.gms.analytics;

import android.content.Context;
import com.google.android.gms.analytics.HitBuilders.ExceptionBuilder;
import com.google.android.gms.analytics.internal.zzae;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;

public class ExceptionReporter implements UncaughtExceptionHandler {
    private final Context mContext;
    private final UncaughtExceptionHandler zzTT;
    private final Tracker zzTU;
    private ExceptionParser zzTV;
    private GoogleAnalytics zzTW;

    public ExceptionReporter(Tracker tracker, UncaughtExceptionHandler uncaughtExceptionHandler, Context context) {
        if (tracker == null) {
            throw new NullPointerException("tracker cannot be null");
        } else if (context == null) {
            throw new NullPointerException("context cannot be null");
        } else {
            this.zzTT = uncaughtExceptionHandler;
            this.zzTU = tracker;
            this.zzTV = new StandardExceptionParser(context, new ArrayList());
            this.mContext = context.getApplicationContext();
            String str = "ExceptionReporter created, original handler is ";
            String valueOf = String.valueOf(uncaughtExceptionHandler == null ? SafeJsonPrimitive.NULL_STRING : uncaughtExceptionHandler.getClass().getName());
            zzae.m7466v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        Object obj = "UncaughtException";
        if (this.zzTV != null) {
            obj = this.zzTV.getDescription(thread != null ? thread.getName() : null, th);
        }
        String str = "Reporting uncaught exception: ";
        String valueOf = String.valueOf(obj);
        zzae.m7466v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        this.zzTU.send(new ExceptionBuilder().setDescription(obj).setFatal(true).build());
        GoogleAnalytics zzkq = zzkq();
        zzkq.dispatchLocalHits();
        zzkq.zzkv();
        if (this.zzTT != null) {
            zzae.m7466v("Passing exception to the original handler");
            this.zzTT.uncaughtException(thread, th);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public GoogleAnalytics zzkq() {
        if (this.zzTW == null) {
            this.zzTW = GoogleAnalytics.getInstance(this.mContext);
        }
        return this.zzTW;
    }
}
