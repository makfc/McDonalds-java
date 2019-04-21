package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Looper;
import java.util.List;

/* renamed from: com.amap.api.mapcore.util.eh */
public class ExceptionLogProcessor extends LogProcessor {
    /* renamed from: a */
    private static boolean f1879a = true;

    protected ExceptionLogProcessor(int i) {
        super(i);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public boolean mo9318a(Context context) {
        if (C0820dq.m2439m(context) != 1 || !f1879a) {
            return false;
        }
        f1879a = false;
        synchronized (Looper.getMainLooper()) {
            UpdateLogDBOperation updateLogDBOperation = new UpdateLogDBOperation(context);
            UpdateLogInfo a = updateLogDBOperation.mo9353a();
            if (a == null) {
                return true;
            } else if (a.mo9358b()) {
                a.mo9357b(false);
                updateLogDBOperation.mo9354a(a);
                return true;
            } else {
                return false;
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public String mo9314a(List<SDKInfo> list) {
        return null;
    }
}
