package com.baidu.android.pushservice.p033e;

import android.app.PendingIntent;
import android.content.Intent;
import android.text.TextUtils;

/* renamed from: com.baidu.android.pushservice.e.l */
public class C1378l {
    /* renamed from: a */
    public String f4829a = "";
    /* renamed from: b */
    public String f4830b = "";
    /* renamed from: c */
    public String f4831c = "";
    /* renamed from: d */
    public String f4832d = "";
    /* renamed from: e */
    public String f4833e = "";
    /* renamed from: f */
    public String f4834f = "";
    /* renamed from: g */
    public String f4835g = "";
    /* renamed from: h */
    public String f4836h = "";
    /* renamed from: i */
    public String f4837i = "";
    /* renamed from: j */
    public String f4838j = "";
    /* renamed from: k */
    public boolean f4839k = false;
    /* renamed from: l */
    public String f4840l = "";

    public C1378l(Intent intent) {
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("app");
        if (pendingIntent != null) {
            this.f4833e = pendingIntent.getTargetPackage();
        }
        if (TextUtils.isEmpty(this.f4833e)) {
            this.f4833e = intent.getStringExtra("pkg_name");
        }
        this.f4832d = intent.getStringExtra("access_token");
        this.f4837i = intent.getStringExtra("secret_key");
        this.f4829a = intent.getStringExtra("method");
        this.f4830b = intent.getStringExtra("method_type");
        this.f4831c = intent.getStringExtra("method_version");
        this.f4836h = intent.getStringExtra("bduss");
        this.f4834f = intent.getStringExtra("appid");
        this.f4838j = intent.getStringExtra("is_baidu_internal_bind");
        this.f4839k = intent.getBooleanExtra("bd_push_extra_is_baidu_app", false);
        this.f4840l = intent.getStringExtra("push_proxy");
    }

    public C1378l(String str, String str2, String str3) {
        this.f4837i = str2;
        this.f4834f = str3;
        this.f4829a = str;
    }

    public String toString() {
        return "method=" + this.f4829a + ", accessToken=" + this.f4832d + ", packageName=" + this.f4833e + ", appId=" + this.f4834f + ", userId=" + this.f4835g + ", rsaBduss=" + this.f4836h + ", isInternalBind=" + this.f4838j;
    }
}
