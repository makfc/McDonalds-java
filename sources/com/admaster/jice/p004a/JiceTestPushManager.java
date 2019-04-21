package com.admaster.jice.p004a;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.admaster.jice.p005b.C0476e;
import com.admaster.jice.p005b.JicePushConfig;
import com.admaster.jice.p007d.HttpURLRequest;
import com.admaster.jice.p007d.LOG;

/* renamed from: com.admaster.jice.a.t */
public class JiceTestPushManager {
    /* renamed from: a */
    private Context f87a = null;
    /* renamed from: b */
    private C0471v f88b = null;
    /* renamed from: c */
    private HttpURLRequest f89c = null;

    public JiceTestPushManager(Context context, HttpURLRequest httpURLRequest, String str, String str2) {
        this.f87a = context;
        this.f89c = httpURLRequest;
        this.f88b = new C0471v(this, str, str2);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo7631a() {
        this.f88b.start();
    }

    /* renamed from: a */
    private synchronized void m125a(String str, String str2) {
        Editor edit = this.f87a.getSharedPreferences("com.admaster.jice.testpushconfig" + str2, 0).edit();
        edit.putString("testconfiglist", str);
        edit.commit();
    }

    /* renamed from: a */
    protected static synchronized String m119a(Context context, String str) {
        String string;
        synchronized (JiceTestPushManager.class) {
            string = context.getSharedPreferences("com.admaster.jice.testpushconfig" + str, 0).getString("testconfiglist", null);
        }
        return string;
    }

    /* renamed from: b */
    protected static synchronized void m127b(Context context, String str) {
        synchronized (JiceTestPushManager.class) {
            Editor edit = context.getSharedPreferences("com.admaster.jice.testpushconfig" + str, 0).edit();
            edit.remove("testconfiglist");
            edit.commit();
        }
    }

    /* renamed from: a */
    private void m123a(JicePushConfig jicePushConfig) {
        String str = null;
        try {
            int pushId = jicePushConfig.getPushId();
            C0476e vertical = jicePushConfig.getVertical();
            C0476e horizontal = jicePushConfig.getHorizontal();
            if (("id:" + pushId + "  ver:" + vertical) != null) {
                str = vertical.toString();
            } else if ((null + "  hor:" + horizontal) != null) {
                str = horizontal.toString();
            }
            m124a(str);
            if (!(vertical == null || jicePushConfig.hasCacheImage(vertical))) {
                new C0470u(this, vertical).start();
            }
            if (horizontal != null && !jicePushConfig.hasCacheImage(horizontal)) {
                new C0470u(this, horizontal).start();
            }
        } catch (Exception e) {
            LOG.m225a("JiceSDK.JiceTestPushManager", "Test JCMaterial parsing failed.", e);
        }
    }

    /* renamed from: a */
    private void m124a(String str) {
    }
}
