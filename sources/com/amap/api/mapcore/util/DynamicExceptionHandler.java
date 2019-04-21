package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import java.lang.Thread.UncaughtExceptionHandler;

/* renamed from: com.amap.api.mapcore.util.fb */
public class DynamicExceptionHandler implements UncaughtExceptionHandler {
    /* renamed from: a */
    private static DynamicExceptionHandler f1919a;
    /* renamed from: b */
    private UncaughtExceptionHandler f1920b = Thread.getDefaultUncaughtExceptionHandler();
    /* renamed from: c */
    private Context f1921c;
    /* renamed from: d */
    private SDKInfo f1922d;

    private DynamicExceptionHandler(Context context, SDKInfo sDKInfo) {
        this.f1921c = context.getApplicationContext();
        this.f1922d = sDKInfo;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /* renamed from: a */
    static synchronized DynamicExceptionHandler m2705a(Context context, SDKInfo sDKInfo) {
        DynamicExceptionHandler dynamicExceptionHandler;
        synchronized (DynamicExceptionHandler.class) {
            if (f1919a == null) {
                f1919a = new DynamicExceptionHandler(context, sDKInfo);
            }
            dynamicExceptionHandler = f1919a;
        }
        return dynamicExceptionHandler;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        String a = Utils.m2507a(th);
        try {
            if (!TextUtils.isEmpty(a) && ((a.contains("amapdynamic") || a.contains("admic")) && a.contains("com.amap.api"))) {
                DexFileManager.m2697a(new DBOperation(this.f1921c, DynamicFileDBCreator.m2706a()), this.f1921c, this.f1922d);
            }
        } catch (Throwable th2) {
            BasicLogHandler.m2542a(th2, "DynamicExceptionHandler", "uncaughtException");
        }
        if (this.f1920b != null) {
            this.f1920b.uncaughtException(thread, th);
        }
    }
}
