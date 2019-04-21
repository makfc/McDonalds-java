package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.p000v4.util.ArrayMap;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

@Instrumented
@TargetApi(11)
public final class zzos extends Fragment implements zzor, TraceFieldInterface {
    private static WeakHashMap<Activity, WeakReference<zzos>> zzaoq = new WeakHashMap();
    public Trace _nr_trace;
    private int zzDO = 0;
    private Map<String, zzoq> zzaor = new ArrayMap();
    private Bundle zzaos;

    private void zzb(final String str, @NonNull final zzoq zzoq) {
        if (this.zzDO > 0) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    if (zzos.this.zzDO >= 1) {
                        zzoq.onCreate(zzos.this.zzaos != null ? zzos.this.zzaos.getBundle(str) : null);
                    }
                    if (zzos.this.zzDO >= 2) {
                        zzoq.onStart();
                    }
                    if (zzos.this.zzDO >= 3) {
                        zzoq.onStop();
                    }
                }
            });
        }
    }

    /* JADX WARNING: Missing block: B:3:0x0010, code skipped:
            if (r0 != null) goto L_0x0012;
     */
    public static com.google.android.gms.internal.zzos zzt(android.app.Activity r3) {
        /*
        r0 = zzaoq;
        r0 = r0.get(r3);
        r0 = (java.lang.ref.WeakReference) r0;
        if (r0 == 0) goto L_0x0013;
    L_0x000a:
        r0 = r0.get();
        r0 = (com.google.android.gms.internal.zzos) r0;
        if (r0 == 0) goto L_0x0013;
    L_0x0012:
        return r0;
    L_0x0013:
        r0 = r3.getFragmentManager();	 Catch:{ ClassCastException -> 0x0048 }
        r1 = "LifecycleFragmentImpl";
        r0 = r0.findFragmentByTag(r1);	 Catch:{ ClassCastException -> 0x0048 }
        r0 = (com.google.android.gms.internal.zzos) r0;	 Catch:{ ClassCastException -> 0x0048 }
        if (r0 == 0) goto L_0x0027;
    L_0x0021:
        r1 = r0.isRemoving();
        if (r1 == 0) goto L_0x003d;
    L_0x0027:
        r0 = new com.google.android.gms.internal.zzos;
        r0.<init>();
        r1 = r3.getFragmentManager();
        r1 = r1.beginTransaction();
        r2 = "LifecycleFragmentImpl";
        r1 = r1.add(r0, r2);
        r1.commitAllowingStateLoss();
    L_0x003d:
        r1 = zzaoq;
        r2 = new java.lang.ref.WeakReference;
        r2.<init>(r0);
        r1.put(r3, r2);
        goto L_0x0012;
    L_0x0048:
        r0 = move-exception;
        r1 = new java.lang.IllegalStateException;
        r2 = "Fragment with tag LifecycleFragmentImpl is not a LifecycleFragmentImpl";
        r1.<init>(r2, r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzos.zzt(android.app.Activity):com.google.android.gms.internal.zzos");
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        for (zzoq dump : this.zzaor.values()) {
            dump.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        for (zzoq onActivityResult : this.zzaor.values()) {
            onActivityResult.onActivityResult(i, i2, intent);
        }
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("zzos");
        try {
            TraceMachine.enterMethod(this._nr_trace, "zzos#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "zzos#onCreate", null);
            }
        }
        super.onCreate(bundle);
        this.zzDO = 1;
        this.zzaos = bundle;
        for (Entry entry : this.zzaor.entrySet()) {
            ((zzoq) entry.getValue()).onCreate(bundle != null ? bundle.getBundle((String) entry.getKey()) : null);
        }
        TraceMachine.exitMethod();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            for (Entry entry : this.zzaor.entrySet()) {
                Bundle bundle2 = new Bundle();
                ((zzoq) entry.getValue()).onSaveInstanceState(bundle2);
                bundle.putBundle((String) entry.getKey(), bundle2);
            }
        }
    }

    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        super.onStop();
        this.zzDO = 2;
        for (zzoq onStart : this.zzaor.values()) {
            onStart.onStart();
        }
    }

    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        this.zzDO = 3;
        for (zzoq onStop : this.zzaor.values()) {
            onStop.onStop();
        }
    }

    public <T extends zzoq> T zza(String str, Class<T> cls) {
        return (zzoq) cls.cast(this.zzaor.get(str));
    }

    public void zza(String str, @NonNull zzoq zzoq) {
        if (this.zzaor.containsKey(str)) {
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 59).append("LifecycleCallback with tag ").append(str).append(" already added to this fragment.").toString());
        }
        this.zzaor.put(str, zzoq);
        zzb(str, zzoq);
    }

    public Activity zzsF() {
        return getActivity();
    }
}
