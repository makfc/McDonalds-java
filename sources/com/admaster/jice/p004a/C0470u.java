package com.admaster.jice.p004a;

import android.text.TextUtils;
import com.admaster.jice.p005b.C0476e;
import com.admaster.jice.p007d.FileUtils;
import com.admaster.jice.p007d.LOG;
import com.admaster.jice.p007d.ManagerUtils;
import java.io.IOException;

/* compiled from: JiceTestPushManager */
/* renamed from: com.admaster.jice.a.u */
class C0470u extends Thread {
    /* renamed from: a */
    final /* synthetic */ JiceTestPushManager f90a;
    /* renamed from: b */
    private C0476e f91b = null;

    public C0470u(JiceTestPushManager jiceTestPushManager, C0476e c0476e) {
        this.f90a = jiceTestPushManager;
        this.f91b = c0476e;
    }

    public void run() {
        String url = this.f91b.getUrl();
        byte[] a;
        try {
            if (TextUtils.isEmpty(url)) {
                this.f90a.m124a("JCMaterial:" + this.f91b + "`s url:" + url + "is illegal format");
                return;
            }
            a = this.f90a.f89c.mo7715a(url, null);
            if (a == null || a.length <= 0) {
                this.f90a.m124a("JCMaterial:" + this.f91b + "`s url:" + url + "download failed");
            } else {
                m129a(a);
            }
        } catch (IOException e) {
            e.printStackTrace();
            a = null;
        }
    }

    /* renamed from: a */
    private void m129a(byte[] bArr) {
        if (ManagerUtils.m235b(this.f90a.f87a)) {
            String path = this.f91b.getPath();
            if (bArr.length < 10485760) {
                this.f90a.m124a("JCMaterial:" + this.f91b + "`s url:" + this.f91b.getUrl() + "cache state:" + FileUtils.m213a(path, bArr));
                return;
            }
            this.f90a.m124a("download image file is too larger to cache.");
            return;
        }
        LOG.m224a("JiceSDK.JiceTestPushManager", "Read or Write Permission Denied,image matericals can`t be cache");
    }
}
