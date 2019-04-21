package com.google.android.gms.tagmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;

@Instrumented
public class PreviewActivity extends Activity implements TraceFieldInterface {
    public Trace _nr_trace;

    /* renamed from: com.google.android.gms.tagmanager.PreviewActivity$1 */
    class C27151 implements OnClickListener {
        C27151() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
        }
    }

    private void zzm(String str, String str2, String str3) {
        AlertDialog create = new Builder(this).create();
        create.setTitle(str);
        create.setMessage(str2);
        create.setButton(-1, str3, new C27151());
        create.show();
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0027 A:{Catch:{ Exception -> 0x0093 }} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00ac A:{SYNTHETIC, Splitter:B:26:0x00ac} */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0066 A:{Catch:{ Exception -> 0x0093 }} */
    public void onCreate(android.os.Bundle r5) {
        /*
        r4 = this;
        r0 = "PreviewActivity";
        com.newrelic.agent.android.tracing.TraceMachine.startTracing(r0);
        r0 = r4._nr_trace;	 Catch:{ NoSuchFieldError -> 0x0084 }
        r1 = "PreviewActivity#onCreate";
        r2 = 0;
        com.newrelic.agent.android.tracing.TraceMachine.enterMethod(r0, r1, r2);	 Catch:{ NoSuchFieldError -> 0x0084 }
    L_0x000d:
        super.onCreate(r5);	 Catch:{ Exception -> 0x0093 }
        r0 = "Preview activity";
        com.google.android.gms.tagmanager.zzbn.zzaV(r0);	 Catch:{ Exception -> 0x0093 }
        r0 = r4.getIntent();	 Catch:{ Exception -> 0x0093 }
        r0 = r0.getData();	 Catch:{ Exception -> 0x0093 }
        r1 = com.google.android.gms.tagmanager.TagManager.getInstance(r4);	 Catch:{ Exception -> 0x0093 }
        r1 = r1.zzr(r0);	 Catch:{ Exception -> 0x0093 }
        if (r1 != 0) goto L_0x0058;
    L_0x0027:
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Exception -> 0x0093 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0093 }
        r2 = java.lang.String.valueOf(r0);	 Catch:{ Exception -> 0x0093 }
        r2 = r2.length();	 Catch:{ Exception -> 0x0093 }
        r2 = r2 + 73;
        r1.<init>(r2);	 Catch:{ Exception -> 0x0093 }
        r2 = "Cannot preview the app with the uri: ";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x0093 }
        r0 = r1.append(r0);	 Catch:{ Exception -> 0x0093 }
        r1 = ". Launching current version instead.";
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x0093 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0093 }
        com.google.android.gms.tagmanager.zzbn.zzaW(r0);	 Catch:{ Exception -> 0x0093 }
        r1 = "Preview failure";
        r2 = "Continue";
        r4.zzm(r1, r0, r2);	 Catch:{ Exception -> 0x0093 }
    L_0x0058:
        r0 = r4.getPackageManager();	 Catch:{ Exception -> 0x0093 }
        r1 = r4.getPackageName();	 Catch:{ Exception -> 0x0093 }
        r1 = r0.getLaunchIntentForPackage(r1);	 Catch:{ Exception -> 0x0093 }
        if (r1 == 0) goto L_0x00ac;
    L_0x0066:
        r2 = "Invoke the launch activity for package name: ";
        r0 = r4.getPackageName();	 Catch:{ Exception -> 0x0093 }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Exception -> 0x0093 }
        r3 = r0.length();	 Catch:{ Exception -> 0x0093 }
        if (r3 == 0) goto L_0x008d;
    L_0x0076:
        r0 = r2.concat(r0);	 Catch:{ Exception -> 0x0093 }
    L_0x007a:
        com.google.android.gms.tagmanager.zzbn.zzaV(r0);	 Catch:{ Exception -> 0x0093 }
        r4.startActivity(r1);	 Catch:{ Exception -> 0x0093 }
    L_0x0080:
        com.newrelic.agent.android.tracing.TraceMachine.exitMethod();
        return;
    L_0x0084:
        r0 = move-exception;
        r0 = 0;
        r1 = "PreviewActivity#onCreate";
        r2 = 0;
        com.newrelic.agent.android.tracing.TraceMachine.enterMethod(r0, r1, r2);	 Catch:{ NoSuchFieldError -> 0x0084 }
        goto L_0x000d;
    L_0x008d:
        r0 = new java.lang.String;	 Catch:{ Exception -> 0x0093 }
        r0.<init>(r2);	 Catch:{ Exception -> 0x0093 }
        goto L_0x007a;
    L_0x0093:
        r0 = move-exception;
        r1 = "Calling preview threw an exception: ";
        r0 = r0.getMessage();
        r0 = java.lang.String.valueOf(r0);
        r2 = r0.length();
        if (r2 == 0) goto L_0x00ca;
    L_0x00a4:
        r0 = r1.concat(r0);
    L_0x00a8:
        com.google.android.gms.tagmanager.zzbn.m7501e(r0);
        goto L_0x0080;
    L_0x00ac:
        r1 = "No launch activity found for package name: ";
        r0 = r4.getPackageName();	 Catch:{ Exception -> 0x0093 }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Exception -> 0x0093 }
        r2 = r0.length();	 Catch:{ Exception -> 0x0093 }
        if (r2 == 0) goto L_0x00c4;
    L_0x00bc:
        r0 = r1.concat(r0);	 Catch:{ Exception -> 0x0093 }
    L_0x00c0:
        com.google.android.gms.tagmanager.zzbn.zzaV(r0);	 Catch:{ Exception -> 0x0093 }
        goto L_0x0080;
    L_0x00c4:
        r0 = new java.lang.String;	 Catch:{ Exception -> 0x0093 }
        r0.<init>(r1);	 Catch:{ Exception -> 0x0093 }
        goto L_0x00c0;
    L_0x00ca:
        r0 = new java.lang.String;
        r0.<init>(r1);
        goto L_0x00a8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.PreviewActivity.onCreate(android.os.Bundle):void");
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
