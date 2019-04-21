package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.mapcore.util.DexFileManager.C0834a;
import com.amap.api.mapcore.util.DynamicSDKFile.C0835a;
import dalvik.system.DexFile;
import java.io.File;
import java.util.Date;

/* renamed from: com.amap.api.mapcore.util.fi */
class DynamicClassLoader extends BaseClassLoader {
    /* renamed from: a */
    public void mo9385a(String str, String str2) throws Exception {
        try {
            mo9382b();
            this.f1938c = DexFile.loadDex(str, str2, 0);
        } catch (Exception e) {
            BasicLogHandler.m2542a(e, "DynamicClassLoader", "loadDexFile()");
            throw new Exception("load dex fail");
        }
    }

    /* renamed from: a */
    private boolean m2739a(DBOperation dBOperation, SDKInfo sDKInfo, String str) {
        return DexFileManager.m2700a(dBOperation, DexFileManager.m2693a(this.f1936a, sDKInfo.mo9292a(), sDKInfo.mo9294b()), str, sDKInfo);
    }

    /* renamed from: a */
    private boolean m2740a(DBOperation dBOperation, String str, String str2) {
        String a = DexFileManager.m2692a(this.f1936a, str);
        if (DexFileManager.m2700a(dBOperation, str, a, this.f1940e)) {
            return true;
        }
        if (C0834a.m2687a(dBOperation, str) != null) {
            return false;
        }
        if (!TextUtils.isEmpty(this.f1941f)) {
            C0834a.m2689a(dBOperation, new C0835a(str, C0822ds.m2461a(a), this.f1940e.mo9292a(), this.f1940e.mo9294b(), str2).mo9371a("useodex").mo9372a(), DynamicSDKFile.m2722b(str));
        }
        return true;
    }

    public DynamicClassLoader(final Context context, SDKInfo sDKInfo, boolean z) throws Exception {
        super(context, sDKInfo, z);
        final String b = DexFileManager.m2702b(context, sDKInfo.mo9292a(), sDKInfo.mo9294b());
        final String a = DexFileManager.m2690a(context);
        if (TextUtils.isEmpty(b) || TextUtils.isEmpty(a)) {
            throw new Exception("dexPath or dexOutputDir is null.");
        }
        File file = new File(b);
        File parentFile = file.getParentFile();
        if (!file.exists()) {
            if (parentFile != null && parentFile.exists()) {
                DexFileManager.m2704c(context, sDKInfo.mo9292a(), sDKInfo.mo9294b());
            }
            throw new Exception("file not exist!");
        } else if (z) {
            mo9385a(b, a + File.separator + DexFileManager.m2694a(file.getName()));
            new Thread() {
                public void run() {
                    try {
                        DynamicClassLoader.this.mo9384a(context, b, a);
                    } catch (Throwable th) {
                        BasicLogHandler.m2542a(th, "DynamicClassLoader", "run()");
                    }
                }
            }.start();
        }
    }

    /* Access modifiers changed, original: protected */
    public Class<?> findClass(String str) throws ClassNotFoundException {
        try {
            if (this.f1938c == null) {
                throw new ClassNotFoundException(str);
            }
            Class<?> cls = (Class) this.f1937b.get(str);
            if (cls == null) {
                cls = this.f1938c.loadClass(str, this);
                this.f1937b.put(str, cls);
                if (cls == null) {
                    throw new ClassNotFoundException(str);
                }
            }
            return cls;
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "DynamicClassLoader", "findClass()");
            ClassNotFoundException classNotFoundException = new ClassNotFoundException(str);
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo9384a(Context context, String str, String str2) throws Exception {
        new Date().getTime();
        try {
            String a;
            DBOperation dBOperation = new DBOperation(context, DynamicFileDBCreator.m2706a());
            File file = new File(str);
            DynamicSDKFile a2 = C0834a.m2687a(dBOperation, file.getName());
            if (a2 != null) {
                this.f1941f = a2.mo9377d();
            }
            if (!m2739a(dBOperation, this.f1940e, file.getAbsolutePath())) {
                this.f1939d = false;
                DexFileManager.m2696a(this.f1936a, dBOperation, file.getName());
                a = DexFileManager.m2691a(this.f1936a, dBOperation, this.f1940e);
                if (!TextUtils.isEmpty(a)) {
                    this.f1941f = a;
                    mo9379a(this.f1936a, this.f1940e);
                }
            }
            if (file.exists()) {
                a = str2 + File.separator + DexFileManager.m2694a(file.getName());
                File file2 = new File(a);
                if (file2.exists() && !m2740a(dBOperation, DexFileManager.m2694a(file.getName()), this.f1941f)) {
                    mo9385a(str, str2 + File.separator + DexFileManager.m2694a(file.getName()));
                    mo9380a(file2, a, this.f1941f, dBOperation);
                }
                new Date().getTime();
            }
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "DynamicClassLoader", "verifyDynamicSDK()");
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo9380a(File file, String str, String str2, DBOperation dBOperation) {
        if (!TextUtils.isEmpty(this.f1941f) || !file.exists()) {
            String a = C0822ds.m2461a(str);
            String name = file.getName();
            C0834a.m2689a(dBOperation, new C0835a(name, a, this.f1940e.mo9292a(), this.f1940e.mo9294b(), str2).mo9371a("useodex").mo9372a(), DynamicSDKFile.m2722b(name));
        }
    }
}
