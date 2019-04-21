package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Looper;
import java.util.Date;
import java.util.List;

/* renamed from: com.amap.api.mapcore.util.eg */
public class CrashLogProcessor extends LogProcessor {
    /* renamed from: a */
    private static boolean f1878a = true;

    protected CrashLogProcessor(int i) {
        super(i);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public boolean mo9318a(Context context) {
        if (!f1878a) {
            return false;
        }
        f1878a = false;
        synchronized (Looper.getMainLooper()) {
            UpdateLogDBOperation updateLogDBOperation = new UpdateLogDBOperation(context);
            UpdateLogInfo a = updateLogDBOperation.mo9353a();
            if (a == null) {
                return true;
            } else if (a.mo9356a()) {
                a.mo9355a(false);
                updateLogDBOperation.mo9354a(a);
                return true;
            } else {
                return false;
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public String mo9312a(String str) {
        return C0822ds.m2466c(str + Utils.m2506a(new Date().getTime()));
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public String mo9314a(List<SDKInfo> list) {
        return null;
    }
}
