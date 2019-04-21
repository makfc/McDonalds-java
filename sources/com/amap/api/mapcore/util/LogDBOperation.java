package com.amap.api.mapcore.util;

import android.content.Context;
import java.util.List;

/* renamed from: com.amap.api.mapcore.util.es */
public class LogDBOperation {
    /* renamed from: a */
    private DBOperation f1889a;

    public LogDBOperation(Context context) {
        try {
            this.f1889a = new DBOperation(context, DBOperation.m2614a(LogDBCreator.class));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public void mo9348a(String str, Class<? extends LogInfo> cls) {
        try {
            m2650c(str, cls);
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "LogDB", "delLog");
        }
    }

    /* renamed from: b */
    public void mo9350b(String str, Class<? extends LogInfo> cls) {
        try {
            m2650c(str, cls);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: c */
    private void m2650c(String str, Class<? extends LogInfo> cls) {
        this.f1889a.mo9328a(LogInfo.m2638c(str), (Class) cls);
    }

    /* renamed from: a */
    public List<? extends LogInfo> mo9346a(int i, Class<? extends LogInfo> cls) {
        List<? extends LogInfo> list = null;
        try {
            return this.f1889a.mo9332b(LogInfo.m2637c(i), cls);
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "LogDB", "ByState");
            return list;
        }
    }

    /* renamed from: a */
    public void mo9347a(LogInfo logInfo) {
        if (logInfo != null) {
            String c = LogInfo.m2638c(logInfo.mo9340b());
            List a = this.f1889a.mo9324a(c, logInfo.getClass(), true);
            if (a == null || a.size() == 0) {
                this.f1889a.mo9327a((Object) logInfo, true);
                return;
            }
            Object obj = (LogInfo) a.get(0);
            if (logInfo.mo9337a() == 0) {
                obj.mo9341b(obj.mo9343c() + 1);
            } else {
                obj.mo9341b(0);
            }
            this.f1889a.mo9330a(c, obj, true);
        }
    }

    /* renamed from: b */
    public void mo9349b(LogInfo logInfo) {
        try {
            this.f1889a.mo9329a(LogInfo.m2638c(logInfo.mo9340b()), (Object) logInfo);
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "LogDB", "updateLogInfo");
        }
    }
}
