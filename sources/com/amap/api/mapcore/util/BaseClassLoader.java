package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import dalvik.system.DexFile;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.amap.api.mapcore.util.fg */
abstract class BaseClassLoader extends ClassLoader {
    /* renamed from: a */
    protected final Context f1936a;
    /* renamed from: b */
    protected final Map<String, Class<?>> f1937b = new HashMap();
    /* renamed from: c */
    protected DexFile f1938c = null;
    /* renamed from: d */
    volatile boolean f1939d = true;
    /* renamed from: e */
    protected SDKInfo f1940e;
    /* renamed from: f */
    protected String f1941f;

    /* renamed from: a */
    public abstract void mo9380a(File file, String str, String str2, DBOperation dBOperation);

    public BaseClassLoader(Context context, SDKInfo sDKInfo, boolean z) {
        super(context.getClassLoader());
        this.f1936a = context;
        this.f1940e = sDKInfo;
    }

    /* renamed from: a */
    public boolean mo9381a() {
        return this.f1938c != null;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo9379a(Context context, SDKInfo sDKInfo) {
        String b = DexFileManager.m2702b(context, sDKInfo.mo9292a(), sDKInfo.mo9294b());
        String a = DexFileManager.m2690a(context);
        if (!TextUtils.isEmpty(b) && !TextUtils.isEmpty(a)) {
            DynamicExceptionHandler.m2705a(context, sDKInfo);
            try {
                File file = new File(b);
                File parentFile = file.getParentFile();
                if (file.exists()) {
                    a = a + File.separator + DexFileManager.m2694a(file.getName());
                    DexFile loadDex = DexFile.loadDex(b, a, 0);
                    if (loadDex != null) {
                        loadDex.close();
                        mo9380a(new File(a), a, this.f1941f, new DBOperation(context, DynamicFileDBCreator.m2706a()));
                    }
                } else if (parentFile != null && parentFile.exists()) {
                    DexFileManager.m2704c(context, sDKInfo.mo9292a(), sDKInfo.mo9294b());
                }
            } catch (Throwable th) {
                BasicLogHandler.m2542a(th, "DynamicClassLoader", "getInstanceByThread()");
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public void mo9382b() {
        try {
            this.f1937b.clear();
            if (this.f1938c != null) {
                this.f1938c.close();
            }
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "DynamicClassLoader", "preReleaseDexFile()");
        }
    }
}
