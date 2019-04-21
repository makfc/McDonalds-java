package com.baidu.android.pushservice.config;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.android.pushservice.jni.BaiduAppSSOJni;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p039k.C1465b;
import java.io.File;

/* renamed from: com.baidu.android.pushservice.config.b */
public class C1347b {
    protected static boolean mIsLoadDefaultConfig = false;
    private String localConfigPath = null;
    protected String mConfigContent;
    protected Context mContext;

    protected C1347b(Context context) {
        this.mContext = context;
        this.localConfigPath = "/data/data/" + this.mContext.getPackageName() + "/files/bdpush_modeconfig.json";
    }

    public boolean loadConfig() {
        CharSequence a;
        if (new File(this.localConfigPath).exists()) {
            a = C1348a.m6083a(this.mContext, this.localConfigPath);
            mIsLoadDefaultConfig = false;
        } else {
            a = C1348a.m6083a(this.mContext, "/com/baidu/android/pushservice/assets/defaultconfig.json");
            mIsLoadDefaultConfig = true;
        }
        if (!TextUtils.isEmpty(a)) {
            try {
                byte[] a2 = C1465b.m6679a(a.getBytes());
                if (a2 != null && a2.length > 0) {
                    this.mConfigContent = new String(BaiduAppSSOJni.decryptAES(a2, a2.length, 0));
                    C1425a.m6442c("BaseConfig", "the config file content = " + this.mConfigContent);
                }
            } catch (Exception e) {
                C1425a.m6440a("BaseConfig", e);
            } catch (UnsatisfiedLinkError e2) {
                C1425a.m6440a("BaseConfig", e2);
            }
        }
        return !TextUtils.isEmpty(this.mConfigContent);
    }

    public boolean writeConfig(String str) {
        return C1348a.m6084a(this.localConfigPath, str);
    }
}
