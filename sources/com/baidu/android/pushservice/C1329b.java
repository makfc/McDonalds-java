package com.baidu.android.pushservice;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p037i.C1441h;
import com.baidu.android.pushservice.p037i.C1456u;
import com.baidu.android.pushservice.p037i.p038a.C1432b;
import com.baidu.android.pushservice.util.C1578v;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;

/* renamed from: com.baidu.android.pushservice.b */
class C1329b implements UncaughtExceptionHandler {
    /* renamed from: a */
    private String f4707a = "DefaultExceptionHandler";
    /* renamed from: b */
    private Context f4708b = null;

    public C1329b(Context context) {
        this.f4708b = context;
    }

    /* renamed from: a */
    private String m6010a() {
        String str = "";
        int myPid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) this.f4708b.getSystemService("activity");
        if (activityManager != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == myPid) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        return str;
    }

    /* renamed from: b */
    private void m6011b(Throwable th) {
        String a = mo13581a(th);
        C1425a.m6442c(this.f4707a, "error :" + a);
        C1578v.m7095b("exception " + a + " at Time " + System.currentTimeMillis(), this.f4708b.getApplicationContext());
        C1441h c1441h = new C1441h();
        c1441h.f5036f = "040101";
        c1441h.f5037g = System.currentTimeMillis();
        c1441h.f5038h = C1432b.m6486c(this.f4708b);
        c1441h.f5089a = a;
        C1456u.m6610a(this.f4708b, c1441h);
    }

    /* renamed from: a */
    public String mo13581a(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        while (th != null) {
            th.printStackTrace(printWriter);
            th = th.getCause();
        }
        String obj = stringWriter.toString();
        String a = m6010a();
        if (!TextUtils.isEmpty(a)) {
            obj = a + "\n" + obj;
        }
        printWriter.close();
        return obj;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        m6011b(th);
        C1578v.m7117f(this.f4708b, this.f4708b.getPackageName());
    }
}
