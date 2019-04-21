package com.alipay.sdk.widget;

import android.app.Activity;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.FrameLayout;

/* renamed from: com.alipay.sdk.widget.g */
public abstract class C0670g extends FrameLayout {
    /* renamed from: a */
    protected Activity f694a;

    /* renamed from: a */
    public abstract void mo8147a();

    /* renamed from: a */
    public abstract void mo8148a(String str);

    /* renamed from: b */
    public abstract boolean mo8150b();

    public C0670g(Activity activity) {
        super(activity);
        this.f694a = activity;
    }

    /* renamed from: a */
    public void mo8149a(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            CookieSyncManager.createInstance(this.f694a.getApplicationContext()).sync();
            CookieManager.getInstance().setCookie(str, str2);
            CookieSyncManager.getInstance().sync();
        }
    }
}
