package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build.VERSION;
import com.amap.api.mapcore.util.DexFileManager.C0834a;
import com.amap.api.mapcore.util.DownloadManager.C0799a;
import com.amap.api.mapcore.util.DynamicSDKFile.C0835a;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

/* renamed from: com.amap.api.mapcore.util.ey */
public class DexDownLoad extends Thread implements C0799a {
    /* renamed from: a */
    private DexDownloadItem f1899a;
    /* renamed from: b */
    private DownloadManager f1900b;
    /* renamed from: c */
    private SDKInfo f1901c;
    /* renamed from: d */
    private String f1902d;
    /* renamed from: e */
    private RandomAccessFile f1903e;
    /* renamed from: f */
    private String f1904f;
    /* renamed from: g */
    private Context f1905g;
    /* renamed from: h */
    private String f1906h;
    /* renamed from: i */
    private String f1907i;
    /* renamed from: j */
    private String f1908j;
    /* renamed from: k */
    private String f1909k;
    /* renamed from: l */
    private int f1910l;
    /* renamed from: m */
    private int f1911m;

    public DexDownLoad(Context context, DexDownloadItem dexDownloadItem, SDKInfo sDKInfo) {
        try {
            this.f1905g = context.getApplicationContext();
            this.f1901c = sDKInfo;
            if (dexDownloadItem != null) {
                this.f1899a = dexDownloadItem;
                this.f1900b = new DownloadManager(new DexDownLoadRequest(this.f1899a));
                String[] split = this.f1899a.mo9365a().split("/");
                this.f1904f = split[split.length - 1];
                split = this.f1904f.split("_");
                this.f1906h = split[0];
                this.f1907i = split[2];
                this.f1908j = split[1];
                this.f1910l = Integer.parseInt(split[3]);
                this.f1911m = Integer.parseInt(split[4].split("\\.")[0]);
                this.f1909k = dexDownloadItem.mo9366b();
                this.f1902d = DexFileManager.m2692a(context, this.f1904f);
            }
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "DexDownLoad", "DexDownLoad");
        }
    }

    /* renamed from: a */
    public void mo9363a() {
        try {
            start();
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "DexDownLoad", "startDownload");
        }
    }

    public void run() {
        try {
            if (m2678g()) {
                this.f1900b.mo9418a(this);
            }
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "DexDownLoad", "run");
        }
    }

    /* renamed from: a */
    private boolean m2674a(DBOperation dBOperation, DynamicSDKFile dynamicSDKFile, String str, String str2, String str3, String str4) {
        if ("errorstatus".equals(dynamicSDKFile.mo9378e())) {
            if (!new File(DexFileManager.m2702b(this.f1905g, this.f1901c.mo9292a(), this.f1901c.mo9294b())).exists()) {
                DexFileManager.m2691a(this.f1905g, dBOperation, this.f1901c);
                try {
                    ClassLoaderFactory.m2668a().mo9362b(this.f1905g, this.f1901c);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return true;
        } else if (!new File(this.f1902d).exists()) {
            return false;
        } else {
            List b = dBOperation.mo9332b(DynamicSDKFile.m2721a(DexFileManager.m2693a(this.f1905g, str, str2), str, str2, str3), DynamicSDKFile.class);
            if (b != null && b.size() > 0) {
                return true;
            }
            try {
                DexFileManager.m2695a(this.f1905g, dBOperation, this.f1901c, new C0835a(DexFileManager.m2693a(this.f1905g, str, this.f1901c.mo9294b()), str4, str, str2, str3).mo9371a("usedex").mo9372a(), this.f1902d);
                ClassLoaderFactory.m2668a().mo9362b(this.f1905g, this.f1901c);
            } catch (FileNotFoundException e2) {
                BasicLogHandler.m2542a(e2, "DexDownLoad", "processDownloadedFile()");
            } catch (IOException e22) {
                BasicLogHandler.m2542a(e22, "DexDownLoad", "processDownloadedFile()");
            } catch (Throwable e222) {
                BasicLogHandler.m2542a(e222, "DexDownLoad", "processDownloadedFile()");
            }
            return true;
        }
    }

    /* renamed from: b */
    private boolean m2675b() {
        DBOperation dBOperation = new DBOperation(this.f1905g, DynamicFileDBCreator.m2706a());
        try {
            List a = C0834a.m2688a(dBOperation, this.f1906h, "usedex");
            if (a != null && a.size() > 0 && C0836ff.m2731a(((DynamicSDKFile) a.get(0)).mo9377d(), this.f1908j) > 0) {
                return true;
            }
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "DexDownLoad", "isDownloaded()");
        }
        DynamicSDKFile a2 = C0834a.m2687a(dBOperation, this.f1904f);
        if (a2 == null) {
            return false;
        }
        return m2674a(dBOperation, a2, this.f1906h, this.f1907i, this.f1908j, this.f1909k);
    }

    /* renamed from: c */
    private boolean m2676c() {
        if (this.f1901c != null && this.f1901c.mo9292a().equals(this.f1906h) && this.f1901c.mo9294b().equals(this.f1907i)) {
            return true;
        }
        return false;
    }

    /* renamed from: f */
    private boolean m2677f() {
        return VERSION.SDK_INT >= this.f1911m && VERSION.SDK_INT <= this.f1910l;
    }

    /* renamed from: a */
    private boolean m2673a(Context context) {
        return C0820dq.m2439m(context) == 1;
    }

    /* renamed from: g */
    private boolean m2678g() {
        try {
            if (!m2676c() || m2675b() || !m2677f() || !m2673a(this.f1905g)) {
                return false;
            }
            m2672a(this.f1901c.mo9292a());
            return true;
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "DexDownLoad", "isNeedDownload()");
            return false;
        }
    }

    /* renamed from: a */
    private void m2672a(String str) {
        DBOperation dBOperation = new DBOperation(this.f1905g, DynamicFileDBCreator.m2706a());
        List a = C0834a.m2688a(dBOperation, str, "copy");
        DexFileManager.m2698a(a);
        if (a != null && a.size() > 1) {
            int size = a.size();
            for (int i = 1; i < size; i++) {
                DexFileManager.m2703b(this.f1905g, dBOperation, ((DynamicSDKFile) a.get(i)).mo9373a());
            }
        }
    }

    /* renamed from: a */
    public void mo9185a(byte[] bArr, long j) {
        try {
            if (this.f1903e == null) {
                File file = new File(this.f1902d);
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                this.f1903e = new RandomAccessFile(file, "rw");
            }
        } catch (FileNotFoundException e) {
            BasicLogHandler.m2542a(e, "DexDownLoad", "onDownload()");
        } catch (Throwable e2) {
            BasicLogHandler.m2542a(e2, "DexDownLoad", "onDownload()");
            return;
        }
        try {
            this.f1903e.seek(j);
            this.f1903e.write(bArr);
        } catch (IOException e22) {
            BasicLogHandler.m2542a(e22, "DexDownLoad", "onDownload()");
        }
    }

    /* renamed from: a */
    public void mo9184a(Throwable th) {
        try {
            if (this.f1903e != null) {
                this.f1903e.close();
            }
        } catch (IOException e) {
            BasicLogHandler.m2542a(e, "DexDownLoad", "onException()");
        } catch (Throwable e2) {
            BasicLogHandler.m2542a(e2, "DexDownLoad", "onException()");
        }
    }

    /* renamed from: e */
    public void mo9187e() {
        try {
            if (this.f1903e != null) {
                try {
                    this.f1903e.close();
                } catch (IOException e) {
                    BasicLogHandler.m2542a(e, "DexDownLoad", "onFinish()");
                }
                String b = this.f1899a.mo9366b();
                if (DexFileManager.m2701a(this.f1902d, b)) {
                    String c = this.f1899a.mo9367c();
                    DBOperation dBOperation = new DBOperation(this.f1905g, DynamicFileDBCreator.m2706a());
                    C0834a.m2689a(dBOperation, new C0835a(this.f1904f, b, this.f1906h, c, this.f1908j).mo9371a("copy").mo9372a(), DynamicSDKFile.m2721a(this.f1904f, this.f1906h, c, this.f1908j));
                    DexFileManager.m2695a(this.f1905g, dBOperation, this.f1901c, new C0835a(DexFileManager.m2693a(this.f1905g, this.f1906h, this.f1901c.mo9294b()), b, this.f1906h, c, this.f1908j).mo9371a("usedex").mo9372a(), this.f1902d);
                    ClassLoaderFactory.m2668a().mo9362b(this.f1905g, this.f1901c);
                    return;
                }
                try {
                    new File(this.f1902d).delete();
                } catch (Throwable e2) {
                    BasicLogHandler.m2542a(e2, "DexDownLoad", "onFinish()");
                }
            }
        } catch (FileNotFoundException e22) {
            BasicLogHandler.m2542a(e22, "DexDownLoad", "onFinish()");
        } catch (IOException e222) {
            BasicLogHandler.m2542a(e222, "DexDownLoad", "onFinish()");
        } catch (Throwable e2222) {
            BasicLogHandler.m2542a(e2222, "DexDownLoad", "onFinish()");
        }
    }

    /* renamed from: d */
    public void mo9186d() {
    }
}
