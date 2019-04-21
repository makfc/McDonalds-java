package com.amap.api.mapcore2d;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.amap.api.mapcore2d.C1022ec.C0973a;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;

/* compiled from: SDKCoordinatorDownload */
/* renamed from: com.amap.api.mapcore2d.cu */
public class C0974cu extends Thread implements C0973a {
    /* renamed from: h */
    private static String f2767h = "sodownload";
    /* renamed from: i */
    private static String f2768i = "sofail";
    /* renamed from: a */
    private C1022ec f2769a = new C1022ec(this.f2770b);
    /* renamed from: b */
    private C0972a f2770b;
    /* renamed from: c */
    private RandomAccessFile f2771c;
    /* renamed from: d */
    private String f2772d;
    /* renamed from: e */
    private String f2773e;
    /* renamed from: f */
    private String f2774f;
    /* renamed from: g */
    private Context f2775g;

    /* compiled from: SDKCoordinatorDownload */
    /* renamed from: com.amap.api.mapcore2d.cu$a */
    private static class C0972a extends C0931eg {
        /* renamed from: a */
        private String f2766a;

        C0972a(String str) {
            this.f2766a = str;
        }

        /* renamed from: e */
        public Map<String, String> mo10071e() {
            return null;
        }

        /* renamed from: f */
        public Map<String, String> mo10072f() {
            return null;
        }

        /* renamed from: g */
        public String mo10073g() {
            return this.f2766a;
        }
    }

    public C0974cu(Context context, String str, String str2, String str3) {
        this.f2775g = context;
        this.f2774f = str3;
        this.f2772d = C0974cu.m4015a(context, str + "temp.so");
        this.f2773e = C0974cu.m4015a(context, "libwgs2gcj.so");
        this.f2770b = new C0972a(str2);
    }

    /* renamed from: a */
    public static String m4015a(Context context, String str) {
        return context.getFilesDir().getAbsolutePath() + File.separator + "libso" + File.separator + str;
    }

    /* renamed from: b */
    private static String m4016b(Context context, String str) {
        return C0974cu.m4015a(context, str);
    }

    /* renamed from: a */
    public void mo10168a() {
        if (this.f2770b != null && !TextUtils.isEmpty(this.f2770b.mo10073g()) && this.f2770b.mo10073g().contains("libJni_wgs2gcj.so") && this.f2770b.mo10073g().contains(Build.CPU_ABI) && !new File(this.f2773e).exists()) {
            start();
        }
    }

    public void run() {
        try {
            File file = new File(C0974cu.m4016b(this.f2775g, "tempfile"));
            if (file.exists()) {
                file.delete();
            }
            this.f2769a.mo10272a(this);
        } catch (Throwable th) {
            C0982da.m4076a(th, "SDKCoordinatorDownload", "run");
            m4017d();
        }
    }

    /* renamed from: a */
    public void mo10165a(byte[] bArr, long j) {
        try {
            if (this.f2771c == null) {
                File file = new File(this.f2772d);
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                this.f2771c = new RandomAccessFile(file, "rw");
            }
        } catch (FileNotFoundException e) {
            C0982da.m4076a(e, "SDKCoordinatorDownload", "onDownload");
            m4017d();
        } catch (Throwable e2) {
            m4017d();
            C0982da.m4076a(e2, "SDKCoordinatorDownload", "onDownload");
            return;
        }
        try {
            this.f2771c.seek(j);
            this.f2771c.write(bArr);
        } catch (IOException e22) {
            m4017d();
            C0982da.m4076a(e22, "SDKCoordinatorDownload", "onDownload");
        }
    }

    /* renamed from: b */
    public void mo10166b() {
        m4017d();
    }

    /* renamed from: c */
    public void mo10167c() {
        try {
            if (this.f2771c != null) {
                this.f2771c.close();
            }
            String a = C0970cs.m3992a(this.f2772d);
            if (a == null || !a.equalsIgnoreCase(this.f2774f)) {
                m4017d();
            } else if (new File(this.f2773e).exists()) {
                m4017d();
            } else {
                new File(this.f2772d).renameTo(new File(this.f2773e));
            }
        } catch (Throwable th) {
            m4017d();
            File file = new File(this.f2773e);
            if (file.exists()) {
                file.delete();
            }
            C0982da.m4076a(th, "SDKCoordinatorDownload", "onFinish");
        }
    }

    /* renamed from: a */
    public void mo10164a(Throwable th) {
        try {
            if (this.f2771c != null) {
                this.f2771c.close();
            }
            m4017d();
            File file = new File(C0974cu.m4016b(this.f2775g, "tempfile"));
            if (!file.exists()) {
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdir();
                }
                file.createNewFile();
            }
        } catch (Throwable th2) {
            C0982da.m4076a(th2, "SDKCoordinatorDownload", "onException");
        }
    }

    /* renamed from: d */
    private void m4017d() {
        File file = new File(this.f2772d);
        if (file.exists()) {
            file.delete();
        }
    }
}
