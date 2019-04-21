package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Bundle;
import com.amap.api.mapcore.util.IDownloadListener.C0758a;
import com.amap.api.mapcore.util.NetFileFetch.C0765a;
import com.amap.api.maps.AMap;
import java.io.IOException;

/* renamed from: com.amap.api.mapcore.util.bn */
public class OfflineMapDownloadTask extends ThreadTask implements C0765a {
    /* renamed from: a */
    private NetFileFetch f1414a;
    /* renamed from: b */
    private UnZipFile f1415b;
    /* renamed from: c */
    private IDownloadItem f1416c;
    /* renamed from: e */
    private Context f1417e;
    /* renamed from: f */
    private Bundle f1418f;
    /* renamed from: g */
    private AMap f1419g;
    /* renamed from: h */
    private boolean f1420h;

    public OfflineMapDownloadTask(IDownloadItem iDownloadItem, Context context) {
        this.f1418f = new Bundle();
        this.f1420h = false;
        this.f1416c = iDownloadItem;
        this.f1417e = context;
    }

    public OfflineMapDownloadTask(IDownloadItem iDownloadItem, Context context, AMap aMap) {
        this(iDownloadItem, context);
        this.f1419g = aMap;
    }

    /* renamed from: a */
    public void mo8931a() {
        if (this.f1416c.mo8848x()) {
            this.f1416c.mo8843a(C0758a.file_io_exception);
            return;
        }
        try {
            m1917g();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public void mo8938b() {
        this.f1420h = true;
        if (this.f1414a != null) {
            this.f1414a.mo9191c();
        } else {
            mo8932e();
        }
        if (this.f1415b != null) {
            this.f1415b.mo9206a();
        }
    }

    /* renamed from: f */
    private String m1916f() {
        return Util.m2369b(this.f1417e);
    }

    /* renamed from: g */
    private void m1917g() throws IOException {
        this.f1414a = new NetFileFetch(new SiteInfoBean(this.f1416c.getUrl(), m1916f(), this.f1416c.mo8849y(), 1, this.f1416c.mo8850z()), this.f1416c.getUrl(), this.f1417e, this.f1416c);
        this.f1414a.mo9189a((C0765a) this);
        this.f1415b = new UnZipFile(this.f1416c, this.f1416c);
        if (!this.f1420h) {
            this.f1414a.mo9188a();
        }
    }

    /* renamed from: c */
    public void mo8939c() {
        this.f1419g = null;
        if (this.f1418f != null) {
            this.f1418f.clear();
            this.f1418f = null;
        }
    }

    /* renamed from: d */
    public void mo8937d() {
        if (this.f1415b != null) {
            this.f1415b.mo9207b();
        }
    }
}
