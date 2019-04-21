package com.admaster.jice.p004a;

import android.text.TextUtils;
import com.admaster.jice.p005b.C0476e;
import com.admaster.jice.p005b.HttpConfig;
import com.admaster.jice.p007d.FileUtils;
import com.admaster.jice.p007d.LOG;
import com.admaster.jice.p007d.ManagerUtils;

/* compiled from: JicePushManager */
/* renamed from: com.admaster.jice.a.m */
class C0464m extends Thread {
    /* renamed from: a */
    final /* synthetic */ JicePushManager f67a;
    /* renamed from: b */
    private C0476e f68b = null;

    public C0464m(JicePushManager jicePushManager, C0476e c0476e) {
        this.f67a = jicePushManager;
        this.f68b = c0476e;
    }

    public void run() {
        String url = this.f68b.getUrl();
        byte[] a;
        try {
            if (TextUtils.isEmpty(url)) {
                this.f67a.m85c("JCMaterial:" + this.f68b + "`s url:" + url + "is illegal format");
                return;
            }
            a = this.f67a.f60i.mo7715a(url, HttpConfig.m167f());
            if (a == null || a.length <= 0) {
                this.f67a.m85c("JCMaterial:" + this.f68b + "`s url:" + url + " download failed");
            } else {
                m101a(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
            a = null;
        }
    }

    /* renamed from: a */
    private void m101a(byte[] bArr) {
        if (ManagerUtils.m235b(this.f67a.f58g)) {
            String path = this.f68b.getPath();
            if (bArr.length < 10485760) {
                this.f67a.m85c("JCMaterial:" + this.f68b + "`s url:" + this.f68b.getUrl() + "cache state:" + FileUtils.m213a(path, bArr));
                return;
            }
            this.f67a.m85c("download image file is too larger to cache.");
            return;
        }
        LOG.m224a("JiceSDK.JicePushManager", "Read or Write Permission Denied,image matericals can`t be cached");
    }
}
