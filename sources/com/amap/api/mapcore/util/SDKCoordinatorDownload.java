package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.amap.api.mapcore.util.DownloadManager.C0799a;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;

/* renamed from: com.amap.api.mapcore.util.du */
public class SDKCoordinatorDownload extends Thread implements C0799a {
    /* renamed from: h */
    private static String f1811h = "sodownload";
    /* renamed from: i */
    private static String f1812i = "sofail";
    /* renamed from: a */
    private DownloadManager f1813a = new DownloadManager(this.f1814b);
    /* renamed from: b */
    private C0823a f1814b;
    /* renamed from: c */
    private RandomAccessFile f1815c;
    /* renamed from: d */
    private String f1816d;
    /* renamed from: e */
    private String f1817e;
    /* renamed from: f */
    private String f1818f;
    /* renamed from: g */
    private Context f1819g;

    /* compiled from: SDKCoordinatorDownload */
    /* renamed from: com.amap.api.mapcore.util.du$a */
    private static class C0823a extends Request {
        /* renamed from: a */
        private String f1810a;

        C0823a(String str) {
            this.f1810a = str;
        }

        /* renamed from: c */
        public Map<String, String> mo8907c() {
            return null;
        }

        /* renamed from: b */
        public Map<String, String> mo8905b() {
            return null;
        }

        /* renamed from: a */
        public String mo8901a() {
            return this.f1810a;
        }
    }

    public SDKCoordinatorDownload(Context context, String str, String str2, String str3) {
        this.f1819g = context;
        this.f1818f = str3;
        this.f1816d = SDKCoordinatorDownload.m2480a(context, str + "temp.so");
        this.f1817e = SDKCoordinatorDownload.m2480a(context, "libwgs2gcj.so");
        this.f1814b = new C0823a(str2);
    }

    /* renamed from: a */
    public static String m2480a(Context context, String str) {
        return context.getFilesDir().getAbsolutePath() + File.separator + "libso" + File.separator + str;
    }

    /* renamed from: b */
    private static String m2481b(Context context, String str) {
        return SDKCoordinatorDownload.m2480a(context, str);
    }

    /* renamed from: a */
    public void mo9288a() {
        if (this.f1814b != null && !TextUtils.isEmpty(this.f1814b.mo8901a()) && this.f1814b.mo8901a().contains("libJni_wgs2gcj.so") && this.f1814b.mo8901a().contains(Build.CPU_ABI) && !new File(this.f1817e).exists()) {
            start();
        }
    }

    public void run() {
        try {
            File file = new File(SDKCoordinatorDownload.m2481b(this.f1819g, "tempfile"));
            if (file.exists()) {
                file.delete();
            }
            this.f1813a.mo9418a(this);
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "SDKCoordinatorDownload", "run");
            m2482b();
        }
    }

    /* renamed from: a */
    public void mo9185a(byte[] bArr, long j) {
        try {
            if (this.f1815c == null) {
                File file = new File(this.f1816d);
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                this.f1815c = new RandomAccessFile(file, "rw");
            }
        } catch (FileNotFoundException e) {
            BasicLogHandler.m2542a(e, "SDKCoordinatorDownload", "onDownload");
            m2482b();
        } catch (Throwable e2) {
            m2482b();
            BasicLogHandler.m2542a(e2, "SDKCoordinatorDownload", "onDownload");
            return;
        }
        try {
            this.f1815c.seek(j);
            this.f1815c.write(bArr);
        } catch (IOException e22) {
            m2482b();
            BasicLogHandler.m2542a(e22, "SDKCoordinatorDownload", "onDownload");
        }
    }

    /* renamed from: d */
    public void mo9186d() {
        m2482b();
    }

    /* renamed from: e */
    public void mo9187e() {
        try {
            if (this.f1815c != null) {
                this.f1815c.close();
            }
            String a = C0822ds.m2461a(this.f1816d);
            if (a == null || !a.equalsIgnoreCase(this.f1818f)) {
                m2482b();
            } else if (new File(this.f1817e).exists()) {
                m2482b();
            } else {
                new File(this.f1816d).renameTo(new File(this.f1817e));
            }
        } catch (Throwable th) {
            m2482b();
            File file = new File(this.f1817e);
            if (file.exists()) {
                file.delete();
            }
            BasicLogHandler.m2542a(th, "SDKCoordinatorDownload", "onFinish");
        }
    }

    /* renamed from: a */
    public void mo9184a(Throwable th) {
        try {
            if (this.f1815c != null) {
                this.f1815c.close();
            }
            m2482b();
            File file = new File(SDKCoordinatorDownload.m2481b(this.f1819g, "tempfile"));
            if (!file.exists()) {
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdir();
                }
                file.createNewFile();
            }
        } catch (Throwable th2) {
            BasicLogHandler.m2542a(th2, "SDKCoordinatorDownload", "onException");
        }
    }

    /* renamed from: b */
    private void m2482b() {
        File file = new File(this.f1816d);
        if (file.exists()) {
            file.delete();
        }
    }
}
