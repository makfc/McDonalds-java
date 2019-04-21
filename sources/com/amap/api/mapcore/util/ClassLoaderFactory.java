package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.amap.api.mapcore.util.ex */
public class ClassLoaderFactory {
    /* renamed from: a */
    private static final ClassLoaderFactory f1897a = new ClassLoaderFactory();
    /* renamed from: b */
    private final Map<String, BaseClassLoader> f1898b = new HashMap();

    private ClassLoaderFactory() {
    }

    /* renamed from: a */
    public static ClassLoaderFactory m2668a() {
        return f1897a;
    }

    /* renamed from: a */
    public synchronized BaseClassLoader mo9361a(Context context, SDKInfo sDKInfo) throws Exception {
        BaseClassLoader baseClassLoader;
        if (m2669a(sDKInfo)) {
            String a = sDKInfo.mo9292a();
            baseClassLoader = (BaseClassLoader) this.f1898b.get(a);
            if (baseClassLoader == null) {
                try {
                    BaseClassLoader dynamicClassLoader = new DynamicClassLoader(context.getApplicationContext(), sDKInfo, true);
                    try {
                        this.f1898b.put(a, dynamicClassLoader);
                        DynamicExceptionHandler.m2705a(context, sDKInfo);
                        baseClassLoader = dynamicClassLoader;
                    } catch (Throwable th) {
                        baseClassLoader = dynamicClassLoader;
                    }
                } catch (Throwable th2) {
                }
            }
        } else {
            throw new Exception("sdkInfo referance is null");
        }
        return baseClassLoader;
    }

    /* renamed from: b */
    public BaseClassLoader mo9362b(Context context, SDKInfo sDKInfo) throws Exception {
        BaseClassLoader baseClassLoader = (BaseClassLoader) this.f1898b.get(sDKInfo.mo9292a());
        if (baseClassLoader != null) {
            baseClassLoader.mo9379a(context, sDKInfo);
            return baseClassLoader;
        }
        baseClassLoader = new DynamicClassLoader(context.getApplicationContext(), sDKInfo, false);
        baseClassLoader.mo9379a(context, sDKInfo);
        this.f1898b.put(sDKInfo.mo9292a(), baseClassLoader);
        DynamicExceptionHandler.m2705a(context, sDKInfo);
        return baseClassLoader;
    }

    /* renamed from: a */
    private boolean m2669a(SDKInfo sDKInfo) {
        if (sDKInfo == null || TextUtils.isEmpty(sDKInfo.mo9294b()) || TextUtils.isEmpty(sDKInfo.mo9292a())) {
            return false;
        }
        return true;
    }
}
