package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.os.StatFs;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.security.MessageDigest;
import java.util.HashMap;

/* renamed from: com.amap.api.mapcore.util.da */
public class ImageCache {
    /* renamed from: a */
    private static final CompressFormat f1722a = CompressFormat.PNG;
    /* renamed from: b */
    private DiskLruCache f1723b;
    /* renamed from: c */
    private LruCache<String, Bitmap> f1724c;
    /* renamed from: d */
    private C0808a f1725d;
    /* renamed from: e */
    private final Object f1726e = new Object();
    /* renamed from: f */
    private boolean f1727f = true;
    /* renamed from: g */
    private HashMap<String, WeakReference<Bitmap>> f1728g;

    /* compiled from: ImageCache */
    /* renamed from: com.amap.api.mapcore.util.da$a */
    public static class C0808a {
        /* renamed from: a */
        public int f1714a = 5242880;
        /* renamed from: b */
        public int f1715b = 10485760;
        /* renamed from: c */
        public File f1716c;
        /* renamed from: d */
        public CompressFormat f1717d = ImageCache.f1722a;
        /* renamed from: e */
        public int f1718e = 100;
        /* renamed from: f */
        public boolean f1719f = true;
        /* renamed from: g */
        public boolean f1720g = true;
        /* renamed from: h */
        public boolean f1721h = false;

        public C0808a(Context context, String str) {
            this.f1716c = ImageCache.m2266a(context, str);
        }

        /* renamed from: a */
        public void mo9237a(int i) {
            this.f1714a = i;
        }

        /* renamed from: b */
        public void mo9240b(int i) {
            if (i <= 0) {
                this.f1720g = false;
            }
            this.f1715b = i;
        }

        /* renamed from: a */
        public void mo9238a(String str) {
            this.f1716c = new File(str);
        }

        /* renamed from: a */
        public void mo9239a(boolean z) {
            this.f1719f = z;
        }

        /* renamed from: b */
        public void mo9241b(boolean z) {
            this.f1720g = z;
        }
    }

    private ImageCache(C0808a c0808a) {
        m2269b(c0808a);
    }

    /* renamed from: a */
    public static ImageCache m2264a(C0808a c0808a) {
        return new ImageCache(c0808a);
    }

    /* renamed from: b */
    private void m2269b(C0808a c0808a) {
        this.f1725d = c0808a;
        if (this.f1725d.f1719f) {
            if (Util.m2374c()) {
                this.f1728g = new HashMap();
            }
            this.f1724c = new LruCache<String, Bitmap>(this.f1725d.f1714a) {
                /* Access modifiers changed, original: protected */
                /* renamed from: a */
                public void mo9231a(boolean z, String str, Bitmap bitmap, Bitmap bitmap2) {
                    if (Util.m2374c() && ImageCache.this.f1728g != null && bitmap != null && !bitmap.isRecycled()) {
                        ImageCache.this.f1728g.put(str, new WeakReference(bitmap));
                    }
                }

                /* Access modifiers changed, original: protected */
                /* renamed from: a */
                public int mo9228a(String str, Bitmap bitmap) {
                    int a = ImageCache.m2262a(bitmap);
                    return a == 0 ? 1 : a;
                }
            };
        }
        if (c0808a.f1721h) {
            mo9243a();
        }
    }

    /* renamed from: a */
    public void mo9243a() {
        synchronized (this.f1726e) {
            if (this.f1723b == null || this.f1723b.mo9401a()) {
                File file = this.f1725d.f1716c;
                if (this.f1725d.f1720g && file != null) {
                    try {
                        if (file.exists()) {
                            m2270b(file);
                        }
                        file.mkdir();
                    } catch (Throwable th) {
                    }
                    if (ImageCache.m2263a(file) > ((long) this.f1725d.f1715b)) {
                        try {
                            this.f1723b = DiskLruCache.m2767a(file, 1, 1, (long) this.f1725d.f1715b);
                        } catch (Throwable th2) {
                            this.f1725d.f1716c = null;
                        }
                    }
                }
            }
            this.f1727f = false;
            this.f1726e.notifyAll();
        }
    }

    /* renamed from: b */
    private void m2270b(File file) throws IOException {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            throw new IOException("not a readable directory: " + file);
        }
        int length = listFiles.length;
        int i = 0;
        while (i < length) {
            File file2 = listFiles[i];
            if (file2.isDirectory()) {
                m2270b(file2);
            }
            if (file2.delete()) {
                i++;
            } else {
                throw new IOException("failed to delete file: " + file2);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0059 A:{ExcHandler: Throwable (th java.lang.Throwable), Splitter:B:14:0x0020} */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0068 A:{SYNTHETIC, Splitter:B:42:0x0068} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:35:0x005a, code skipped:
            if (r0 != null) goto L_0x005c;
     */
    /* JADX WARNING: Missing block: B:37:?, code skipped:
            r0.close();
     */
    /* JADX WARNING: Missing block: B:43:?, code skipped:
            r1.close();
     */
    /* JADX WARNING: Missing block: B:48:0x0070, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:49:0x0071, code skipped:
            r5 = r1;
            r1 = r0;
            r0 = r5;
     */
    /* renamed from: a */
    public void mo9244a(java.lang.String r7, android.graphics.Bitmap r8) {
        /*
        r6 = this;
        if (r7 == 0) goto L_0x000a;
    L_0x0002:
        if (r8 == 0) goto L_0x000a;
    L_0x0004:
        r0 = r8.isRecycled();
        if (r0 == 0) goto L_0x000b;
    L_0x000a:
        return;
    L_0x000b:
        r0 = r6.f1724c;
        if (r0 == 0) goto L_0x0014;
    L_0x000f:
        r0 = r6.f1724c;
        r0.mo9233b(r7, r8);
    L_0x0014:
        r2 = r6.f1726e;
        monitor-enter(r2);
        r0 = r6.f1723b;	 Catch:{ all -> 0x004d }
        if (r0 == 0) goto L_0x004b;
    L_0x001b:
        r1 = com.amap.api.mapcore.util.ImageCache.m2271c(r7);	 Catch:{ all -> 0x004d }
        r0 = 0;
        r3 = r6.f1723b;	 Catch:{ Throwable -> 0x0059, all -> 0x0062 }
        r3 = r3.mo9399a(r1);	 Catch:{ Throwable -> 0x0059, all -> 0x0062 }
        if (r3 != 0) goto L_0x0050;
    L_0x0028:
        r3 = r6.f1723b;	 Catch:{ Throwable -> 0x0059, all -> 0x0062 }
        r1 = r3.mo9402b(r1);	 Catch:{ Throwable -> 0x0059, all -> 0x0062 }
        if (r1 == 0) goto L_0x0046;
    L_0x0030:
        r3 = 0;
        r0 = r1.mo9391a(r3);	 Catch:{ Throwable -> 0x0059, all -> 0x0062 }
        r3 = r6.f1725d;	 Catch:{ Throwable -> 0x0059, all -> 0x0070 }
        r3 = r3.f1717d;	 Catch:{ Throwable -> 0x0059, all -> 0x0070 }
        r4 = r6.f1725d;	 Catch:{ Throwable -> 0x0059, all -> 0x0070 }
        r4 = r4.f1718e;	 Catch:{ Throwable -> 0x0059, all -> 0x0070 }
        r8.compress(r3, r4, r0);	 Catch:{ Throwable -> 0x0059, all -> 0x0070 }
        r1.mo9392a();	 Catch:{ Throwable -> 0x0059, all -> 0x0070 }
        r0.close();	 Catch:{ Throwable -> 0x0059, all -> 0x0070 }
    L_0x0046:
        if (r0 == 0) goto L_0x004b;
    L_0x0048:
        r0.close();	 Catch:{ Throwable -> 0x006c }
    L_0x004b:
        monitor-exit(r2);	 Catch:{ all -> 0x004d }
        goto L_0x000a;
    L_0x004d:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x004d }
        throw r0;
    L_0x0050:
        r1 = 0;
        r1 = r3.mo9394a(r1);	 Catch:{ Throwable -> 0x0059, all -> 0x0062 }
        r1.close();	 Catch:{ Throwable -> 0x0059, all -> 0x0062 }
        goto L_0x0046;
    L_0x0059:
        r1 = move-exception;
        if (r0 == 0) goto L_0x004b;
    L_0x005c:
        r0.close();	 Catch:{ Throwable -> 0x0060 }
        goto L_0x004b;
    L_0x0060:
        r0 = move-exception;
        goto L_0x004b;
    L_0x0062:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
    L_0x0066:
        if (r1 == 0) goto L_0x006b;
    L_0x0068:
        r1.close();	 Catch:{ Throwable -> 0x006e }
    L_0x006b:
        throw r0;	 Catch:{ all -> 0x004d }
    L_0x006c:
        r0 = move-exception;
        goto L_0x004b;
    L_0x006e:
        r1 = move-exception;
        goto L_0x006b;
    L_0x0070:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x0066;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ImageCache.mo9244a(java.lang.String, android.graphics.Bitmap):void");
    }

    /* renamed from: a */
    public Bitmap mo9242a(String str) {
        Bitmap bitmap;
        if (Util.m2374c() && this.f1728g != null) {
            WeakReference weakReference = (WeakReference) this.f1728g.get(str);
            if (weakReference != null) {
                bitmap = (Bitmap) weakReference.get();
                if (bitmap == null || bitmap.isRecycled()) {
                    bitmap = null;
                }
                this.f1728g.remove(str);
                if (bitmap == null && this.f1724c != null) {
                    bitmap = (Bitmap) this.f1724c.mo9229a((Object) str);
                }
                if (bitmap == null && !bitmap.isRecycled()) {
                    return bitmap;
                }
            }
        }
        bitmap = null;
        bitmap = (Bitmap) this.f1724c.mo9229a((Object) str);
        return bitmap == null ? null : null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x004e A:{SYNTHETIC, Splitter:B:35:0x004e} */
    /* renamed from: b */
    public android.graphics.Bitmap mo9245b(java.lang.String r8) {
        /*
        r7 = this;
        r3 = 0;
        r1 = com.amap.api.mapcore.util.ImageCache.m2271c(r8);
        r4 = r7.f1726e;
        monitor-enter(r4);
    L_0x0008:
        r2 = r7.f1727f;	 Catch:{ all -> 0x0052 }
        if (r2 == 0) goto L_0x0014;
    L_0x000c:
        r2 = r7.f1726e;	 Catch:{ Throwable -> 0x0012 }
        r2.wait();	 Catch:{ Throwable -> 0x0012 }
        goto L_0x0008;
    L_0x0012:
        r2 = move-exception;
        goto L_0x0008;
    L_0x0014:
        r2 = r7.f1723b;	 Catch:{ all -> 0x0052 }
        if (r2 == 0) goto L_0x003e;
    L_0x0018:
        r2 = r7.f1723b;	 Catch:{ Throwable -> 0x0040, all -> 0x004a }
        r1 = r2.mo9399a(r1);	 Catch:{ Throwable -> 0x0040, all -> 0x004a }
        if (r1 == 0) goto L_0x005d;
    L_0x0020:
        r2 = 0;
        r2 = r1.mo9394a(r2);	 Catch:{ Throwable -> 0x0040, all -> 0x004a }
        if (r2 == 0) goto L_0x0039;
    L_0x0027:
        r0 = r2;
        r0 = (java.io.FileInputStream) r0;	 Catch:{ Throwable -> 0x005b, all -> 0x0059 }
        r1 = r0;
        r1 = r1.getFD();	 Catch:{ Throwable -> 0x005b, all -> 0x0059 }
        r5 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r6 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r3 = com.amap.api.mapcore.util.ImageResizer.m2312a(r1, r5, r6, r7);	 Catch:{ Throwable -> 0x005b, all -> 0x0059 }
    L_0x0039:
        if (r2 == 0) goto L_0x003e;
    L_0x003b:
        r2.close();	 Catch:{ Throwable -> 0x0055 }
    L_0x003e:
        monitor-exit(r4);	 Catch:{ all -> 0x0052 }
        return r3;
    L_0x0040:
        r1 = move-exception;
        r2 = r3;
    L_0x0042:
        if (r2 == 0) goto L_0x003e;
    L_0x0044:
        r2.close();	 Catch:{ Throwable -> 0x0048 }
        goto L_0x003e;
    L_0x0048:
        r1 = move-exception;
        goto L_0x003e;
    L_0x004a:
        r1 = move-exception;
        r2 = r3;
    L_0x004c:
        if (r2 == 0) goto L_0x0051;
    L_0x004e:
        r2.close();	 Catch:{ Throwable -> 0x0057 }
    L_0x0051:
        throw r1;	 Catch:{ all -> 0x0052 }
    L_0x0052:
        r1 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0052 }
        throw r1;
    L_0x0055:
        r1 = move-exception;
        goto L_0x003e;
    L_0x0057:
        r2 = move-exception;
        goto L_0x0051;
    L_0x0059:
        r1 = move-exception;
        goto L_0x004c;
    L_0x005b:
        r1 = move-exception;
        goto L_0x0042;
    L_0x005d:
        r2 = r3;
        goto L_0x0039;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ImageCache.mo9245b(java.lang.String):android.graphics.Bitmap");
    }

    /* renamed from: b */
    public void mo9246b() {
        if (Util.m2374c() && this.f1728g != null) {
            this.f1728g.clear();
        }
        if (this.f1724c != null) {
            this.f1724c.mo9230a();
        }
        synchronized (this.f1726e) {
            this.f1727f = true;
            if (!(this.f1723b == null || this.f1723b.mo9401a())) {
                try {
                    this.f1723b.mo9404c();
                } catch (Throwable th) {
                }
                this.f1723b = null;
                mo9243a();
            }
        }
    }

    /* renamed from: c */
    public void mo9247c() {
        synchronized (this.f1726e) {
            if (this.f1723b != null) {
                try {
                    this.f1723b.mo9403b();
                } catch (Throwable th) {
                }
            }
        }
    }

    /* renamed from: d */
    public void mo9248d() {
        if (Util.m2374c() && this.f1728g != null) {
            this.f1728g.clear();
        }
        if (this.f1724c != null) {
            this.f1724c.mo9230a();
        }
        synchronized (this.f1726e) {
            if (this.f1723b != null) {
                try {
                    if (!this.f1723b.mo9401a()) {
                        this.f1723b.mo9404c();
                        this.f1723b = null;
                    }
                } catch (Throwable th) {
                }
            }
        }
    }

    /* renamed from: a */
    public static File m2266a(Context context, String str) {
        String path;
        File a = ImageCache.m2265a(context);
        if (("mounted".equals(Environment.getExternalStorageState()) || !ImageCache.m2272e()) && a != null) {
            path = a.getPath();
        } else {
            path = context.getCacheDir().getPath();
        }
        return new File(path + File.separator + str);
    }

    /* renamed from: c */
    public static String m2271c(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes("utf-8"));
            return ImageCache.m2267a(instance.digest());
        } catch (Throwable th) {
            return String.valueOf(str.hashCode());
        }
    }

    /* renamed from: a */
    private static String m2267a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                stringBuilder.append('0');
            }
            stringBuilder.append(toHexString);
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public static int m2262a(Bitmap bitmap) {
        if (Util.m2376d()) {
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    /* renamed from: e */
    public static boolean m2272e() {
        if (Util.m2371b()) {
            return Environment.isExternalStorageRemovable();
        }
        return true;
    }

    /* renamed from: a */
    public static File m2265a(Context context) {
        if (Util.m2361a()) {
            return context.getExternalCacheDir();
        }
        return new File(Environment.getExternalStorageDirectory().getPath() + ("/Android/data/" + context.getPackageName() + "/cache/"));
    }

    /* renamed from: a */
    public static long m2263a(File file) {
        if (Util.m2371b()) {
            return file.getUsableSpace();
        }
        StatFs statFs = new StatFs(file.getPath());
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }
}
