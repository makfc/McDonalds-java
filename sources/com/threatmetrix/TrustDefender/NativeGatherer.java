package com.threatmetrix.TrustDefender;

import android.content.Context;
import com.threatmetrix.TrustDefender.C4532g.C4515a;
import com.threatmetrix.TrustDefender.C4532g.C4525i;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class NativeGatherer {
    /* renamed from: a */
    static final /* synthetic */ boolean f7253a = (!NativeGatherer.class.desiredAssertionStatus());
    /* renamed from: b */
    private static volatile NativeGatherer f7254b;
    /* renamed from: c */
    private static final String f7255c = C4549w.m8585a(NativeGatherer.class);
    /* renamed from: d */
    private static final Lock f7256d = new ReentrantLock();
    /* renamed from: e */
    private NativeGathererHelper f7257e = new NativeGathererHelper();
    /* renamed from: f */
    private String[] f7258f = null;
    /* renamed from: g */
    private long f7259g = 0;

    private class NativeGathererHelper {
        /* renamed from: g */
        static final /* synthetic */ boolean f7243g = (!NativeGatherer.class.desiredAssertionStatus());
        /* renamed from: a */
        boolean f7244a = false;
        /* renamed from: b */
        boolean f7245b = false;
        /* renamed from: c */
        boolean f7246c = false;
        /* renamed from: d */
        String f7247d = "";
        /* renamed from: e */
        int f7248e = 0;
        /* renamed from: f */
        String[] f7249f = new String[]{"/system/app", "/system/priv-app"};
        /* renamed from: i */
        private final String f7251i = C4549w.m8585a(NativeGathererHelper.class);
        /* renamed from: j */
        private final Lock f7252j = new ReentrantLock();

        /* renamed from: com.threatmetrix.TrustDefender.NativeGatherer$NativeGathererHelper$1 */
        class C44641 implements FilenameFilter {
            C44641() {
            }

            public final boolean accept(File dir, String filename) {
                return filename.contains("tdm-4.0-90-jni");
            }
        }

        public native int cancel();

        public native String[] checkURLs(String[] strArr);

        public native String[] findAllProcs();

        public native String[] findInstalledProcs();

        public native int findPackages(int i, int i2, String[] strArr, int i3);

        public native String[] findRunningProcs();

        public native void finit();

        public native String getBinaryArch();

        public native String getConfig(String str);

        public native String[] getFontList(String str);

        public native String[] getNetworkInfo();

        public native String getRandomString(int i);

        public native String hashFile(String str);

        public native boolean init(int i, String str, int i2);

        public native String md5(String str);

        public native int setConfig(String str, String str2);

        public native void setInfoLogging(int i);

        public native String sha1(String str);

        public native String urlEncode(String str);

        public native int waitUntilCancelled();

        public native String xor(String str, String str2);

        NativeGathererHelper() {
        }

        /* renamed from: a */
        private boolean m8205a(String dataPath, String sharedObjPath, int infoLoggingStatus) {
            if (this.f7244a) {
                return this.f7244a;
            }
            try {
                this.f7252j.lock();
                if (this.f7244a) {
                    boolean z = this.f7244a;
                    this.f7252j.unlock();
                    return z;
                }
                String shPath = sharedObjPath;
                Object classLoader = NativeGatherer.class.getClassLoader();
                if (!(classLoader instanceof PathClassLoader)) {
                    classLoader = classLoader.getParent();
                }
                if (classLoader instanceof PathClassLoader) {
                    Object returned = C4485at.m8323a(classLoader, C4485at.m8328b(ClassLoader.class, "findLibrary", String.class), "tdm-4.0-90-jni");
                    if (returned != null) {
                        shPath = String.valueOf(returned);
                    }
                    if (C4491ai.m8349f(shPath)) {
                        String str = this.f7251i;
                        this.f7247d = C4491ai.m8346c(shPath);
                        if ("36a80b06f150a2992b344b46296c9ec471d45caf|f3477b1e7ad0cdcd08e78f5573a7bb22a5db312d|003c121233031073e0ef405c247f3a2df6dd5b54|6391b0a41ca80934ae9df3b825236d78efa69559|8b4fc32b863660b423159d5320028bc04ae0bea6|46488b641dbcdd70a559755dfed006a3bd1a59b4|aee9a1076de5436f2aad09f444cc6642634c8fe4|9aafeaee349c8909dcc88dfe1bb83dc21a50b3e6|7e204bbb780a37fb49c88cd6603b10f30580c7c6|27fe124a88e49203d77859d80d87d2c3b7feda03|5faf2a774672f3ee03b65f386c2f0cb09b9ece16|79e97d903d5edbaa95195b8fa367e2f9497abeb4|f0d896d61f4283a49089acf5ac6d8d86e1fb6563|824513af9ae7006357cbc74678d26a663729a16b".contains(this.f7247d)) {
                            this.f7246c = true;
                        } else {
                            this.f7244a = false;
                            this.f7252j.unlock();
                            return false;
                        }
                    }
                }
                System.loadLibrary("tdm-4.0-90-jni");
                this.f7244a = init(TrustDefenderVersion.numeric.intValue(), dataPath, infoLoggingStatus);
                String str2 = this.f7251i;
                new StringBuilder("NativeGatherer() complete, found ").append(this.f7248e);
                this.f7252j.unlock();
                return this.f7244a;
            } catch (UnsatisfiedLinkError e) {
                C4549w.m8588a(this.f7251i, "Native code:", e);
                this.f7244a = false;
            } catch (Throwable th) {
                this.f7252j.unlock();
            }
        }

        /* Access modifiers changed, original: final */
        /* renamed from: a */
        public final boolean mo34021a(Context context, int infoLoggingStatus) {
            if (f7243g || context != null) {
                String path = context.getFilesDir().getAbsolutePath();
                String nativePath = new C4515a(context).mo34223c();
                if (!m8205a(path, nativePath, infoLoggingStatus)) {
                    String[] files = new File(nativePath).list(new C44641());
                    if (!(files == null || files.length == 0)) {
                        this.f7245b = true;
                    }
                }
                return this.f7244a;
            }
            throw new AssertionError();
        }

        /* Access modifiers changed, original: protected|final */
        public final void finalize() throws Throwable {
            finit();
        }
    }

    /* renamed from: a */
    static NativeGatherer m8207a() {
        if (f7254b == null) {
            try {
                f7256d.lock();
                if (f7254b == null) {
                    f7254b = new NativeGatherer();
                }
                f7256d.unlock();
            } catch (Throwable th) {
                f7256d.unlock();
            }
        }
        return f7254b;
    }

    private NativeGatherer() {
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final boolean mo34048a(Context context, int infoLoggingStatus) {
        return this.f7257e.mo34021a(context, infoLoggingStatus);
    }

    /* Access modifiers changed, original: final */
    /* renamed from: b */
    public final boolean mo34053b() {
        return this.f7257e.f7244a;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final int mo34044a(Context context, int flags, int pkgLimit, int timeLimit) throws InterruptedException {
        InterruptedException interruptedException;
        if (f7253a || context != null) {
            int found = 0;
            try {
                if (this.f7257e.f7244a) {
                    String[] strArr;
                    NativeGathererHelper nativeGathererHelper = this.f7257e;
                    if (this.f7258f == null || TimeUnit.SECONDS.convert(System.nanoTime() - this.f7259g, TimeUnit.NANOSECONDS) >= 60) {
                        C4549w.m8594c(f7255c, "Starting path find for apk");
                        this.f7259g = System.nanoTime();
                        ArrayList a = new C4525i(context).mo34226a();
                        C4549w.m8594c(f7255c, "findAPKPaths found : " + a.size());
                        this.f7258f = (String[]) a.toArray(new String[a.size()]);
                        strArr = this.f7258f;
                    } else {
                        strArr = this.f7258f;
                    }
                    nativeGathererHelper.f7249f = strArr;
                    found = this.f7257e.findPackages(pkgLimit, timeLimit, this.f7257e.f7249f, flags);
                }
                if (Thread.interrupted()) {
                    C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                    throw new InterruptedException();
                }
            } catch (Throwable th) {
                if (Thread.interrupted()) {
                    C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                    interruptedException = new InterruptedException();
                }
            }
            return found;
        }
        throw new AssertionError();
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final String[] mo34049a(String[] urls) throws InterruptedException {
        InterruptedException interruptedException;
        try {
            C4549w.m8594c(f7255c, (this.f7257e.f7244a ? " available " : "not available ") + " Found " + this.f7257e.f7248e);
            if (!this.f7257e.f7244a || urls == null) {
                if (Thread.interrupted()) {
                    C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                    throw new InterruptedException();
                }
                return null;
            }
            String[] checkURLs = this.f7257e.checkURLs(urls);
            if (!Thread.interrupted()) {
                return checkURLs;
            }
            C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
            throw new InterruptedException();
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                interruptedException = new InterruptedException();
            }
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: c */
    public final int mo34054c() {
        try {
            if (this.f7257e.f7244a) {
                return this.f7257e.cancel();
            }
        } catch (Throwable t) {
            C4549w.m8588a(f7255c, "Native code:", t);
        }
        return -1;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: d */
    public final int mo34056d() {
        try {
            if (this.f7257e.f7244a) {
                return this.f7257e.waitUntilCancelled();
            }
        } catch (Throwable t) {
            C4549w.m8588a(f7255c, "Native code:", t);
        }
        return -1;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final String mo34047a(String filename) throws InterruptedException {
        InterruptedException interruptedException;
        try {
            if (!this.f7257e.f7244a || filename == null) {
                if (Thread.interrupted()) {
                    C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                    throw new InterruptedException();
                }
                return null;
            }
            String hashFile = this.f7257e.hashFile(filename);
            if (!Thread.interrupted()) {
                return hashFile;
            }
            C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
            throw new InterruptedException();
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                interruptedException = new InterruptedException();
            }
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: b */
    public final String mo34050b(String blob) throws InterruptedException {
        InterruptedException interruptedException;
        try {
            if (!this.f7257e.f7244a || blob == null) {
                if (Thread.interrupted()) {
                    C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                    throw new InterruptedException();
                }
                return null;
            }
            String md5 = this.f7257e.md5(blob);
            if (!Thread.interrupted()) {
                return md5;
            }
            C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
            throw new InterruptedException();
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                interruptedException = new InterruptedException();
            }
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: c */
    public final String mo34055c(String key) throws InterruptedException {
        InterruptedException interruptedException;
        try {
            if (!this.f7257e.f7244a || key == null) {
                if (Thread.interrupted()) {
                    C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                    throw new InterruptedException();
                }
                return null;
            }
            String config = this.f7257e.getConfig(key);
            if (!Thread.interrupted()) {
                return config;
            }
            C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
            throw new InterruptedException();
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                interruptedException = new InterruptedException();
            }
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final int mo34045a(String key, String value) throws InterruptedException {
        InterruptedException interruptedException;
        try {
            if (!this.f7257e.f7244a || key == null || value == null) {
                if (Thread.interrupted()) {
                    C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                    throw new InterruptedException();
                }
                return -1;
            }
            int config = this.f7257e.setConfig(key, value);
            if (!Thread.interrupted()) {
                return config;
            }
            C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
            throw new InterruptedException();
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                interruptedException = new InterruptedException();
            }
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: d */
    public final String mo34057d(String blob) throws InterruptedException {
        InterruptedException interruptedException;
        try {
            if (!this.f7257e.f7244a || blob == null) {
                if (Thread.interrupted()) {
                    C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                    throw new InterruptedException();
                }
                return null;
            }
            String sha1 = this.f7257e.sha1(blob);
            if (!Thread.interrupted()) {
                return sha1;
            }
            C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
            throw new InterruptedException();
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                interruptedException = new InterruptedException();
            }
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final String mo34046a(int length) throws InterruptedException {
        InterruptedException interruptedException;
        try {
            if (this.f7257e.f7244a) {
                String randomString = this.f7257e.getRandomString(32);
                if (!Thread.interrupted()) {
                    return randomString;
                }
                C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            if (Thread.interrupted()) {
                C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            return null;
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                interruptedException = new InterruptedException();
            }
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: e */
    public final String mo34058e(String in) throws InterruptedException {
        InterruptedException interruptedException;
        try {
            if (!this.f7257e.f7244a || in == null) {
                if (Thread.interrupted()) {
                    C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                    throw new InterruptedException();
                }
                return null;
            }
            String urlEncode = this.f7257e.urlEncode(in);
            if (!Thread.interrupted()) {
                return urlEncode;
            }
            C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
            throw new InterruptedException();
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                interruptedException = new InterruptedException();
            }
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: f */
    public final List<String> mo34060f(String fontPath) throws InterruptedException {
        InterruptedException interruptedException;
        try {
            if (!this.f7257e.f7244a || fontPath == null) {
                if (Thread.interrupted()) {
                    C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                    throw new InterruptedException();
                }
                return null;
            }
            String[] fonts = this.f7257e.getFontList(fontPath);
            List<String> asList;
            if (fonts != null) {
                asList = Arrays.asList(fonts);
                if (!Thread.interrupted()) {
                    return asList;
                }
                C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            asList = new ArrayList();
            if (!Thread.interrupted()) {
                return asList;
            }
            C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
            throw new InterruptedException();
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                interruptedException = new InterruptedException();
            }
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: e */
    public final String[] mo34059e() throws InterruptedException {
        InterruptedException interruptedException;
        try {
            if (this.f7257e.f7244a) {
                String[] findRunningProcs = this.f7257e.findRunningProcs();
                if (!Thread.interrupted()) {
                    return findRunningProcs;
                }
                C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            if (Thread.interrupted()) {
                C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            return null;
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                interruptedException = new InterruptedException();
            }
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: f */
    public final String[] mo34061f() throws InterruptedException {
        InterruptedException interruptedException;
        try {
            if (this.f7257e.f7244a) {
                String[] findInstalledProcs = this.f7257e.findInstalledProcs();
                if (!Thread.interrupted()) {
                    return findInstalledProcs;
                }
                C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            if (Thread.interrupted()) {
                C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            return null;
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                interruptedException = new InterruptedException();
            }
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: g */
    public final String[] mo34062g() throws InterruptedException {
        InterruptedException interruptedException;
        try {
            if (this.f7257e.f7244a) {
                String[] findAllProcs = this.f7257e.findAllProcs();
                if (!Thread.interrupted()) {
                    return findAllProcs;
                }
                C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            if (Thread.interrupted()) {
                C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            return null;
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                interruptedException = new InterruptedException();
            }
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: b */
    public final String mo34051b(String source, String key) throws InterruptedException {
        InterruptedException interruptedException;
        try {
            if (!this.f7257e.f7244a || key == null || source == null || key.length() <= 0 || source.isEmpty()) {
                if (Thread.interrupted()) {
                    C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                    throw new InterruptedException();
                }
                return null;
            }
            String xor = this.f7257e.xor(source, key);
            if (!Thread.interrupted()) {
                return xor;
            }
            C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
            throw new InterruptedException();
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4549w.m8594c(f7255c, "Thread interrupt detected, throwing");
                interruptedException = new InterruptedException();
            }
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: h */
    public final String mo34063h() {
        try {
            if (this.f7257e.f7244a) {
                return this.f7257e.getBinaryArch();
            }
        } catch (Throwable t) {
            C4549w.m8588a(f7255c, "Native code:", t);
        }
        return null;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: i */
    public final String[] mo34064i() {
        try {
            if (this.f7257e.f7244a) {
                return this.f7257e.getNetworkInfo();
            }
        } catch (Throwable t) {
            C4549w.m8588a(f7255c, "Native code:", t);
        }
        return null;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: j */
    public final boolean mo34065j() {
        return this.f7257e.f7245b;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: k */
    public final String mo34066k() {
        return this.f7257e.f7247d;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: l */
    public final boolean mo34067l() {
        return this.f7257e.f7246c;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: b */
    public final void mo34052b(int infoLogging) {
        try {
            if (this.f7257e.f7244a) {
                this.f7257e.setInfoLogging(infoLogging);
            }
        } catch (Throwable t) {
            C4549w.m8588a(f7255c, "Native code:", t);
        }
    }
}
