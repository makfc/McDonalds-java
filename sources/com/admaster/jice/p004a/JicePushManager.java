package com.admaster.jice.p004a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.text.TextUtils;
import com.admaster.jice.api.JicePushShowError;
import com.admaster.jice.api.JiceSDK;
import com.admaster.jice.api.JiceViewListener;
import com.admaster.jice.p005b.C0476e;
import com.admaster.jice.p005b.C0477f;
import com.admaster.jice.p005b.HttpConfig;
import com.admaster.jice.p005b.JicePushConfig;
import com.admaster.jice.p007d.HttpURLRequest;
import com.admaster.jice.p007d.LOG;
import com.admaster.jice.p007d.ManagerUtils;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.admaster.jice.a.j */
public class JicePushManager {
    /* renamed from: a */
    JiceViewVisitor f52a = new C0462k(this);
    /* renamed from: b */
    private List<JicePushConfig> f53b = null;
    /* renamed from: c */
    private List<JicePushConfig> f54c = null;
    /* renamed from: d */
    private JiceViewListener f55d = null;
    /* renamed from: e */
    private boolean f56e = false;
    /* renamed from: f */
    private boolean f57f = false;
    /* renamed from: g */
    private Context f58g;
    /* renamed from: h */
    private JiceSDK f59h = null;
    /* renamed from: i */
    private HttpURLRequest f60i = null;
    /* renamed from: j */
    private ExecutorService f61j = null;
    /* renamed from: k */
    private SharedPreferences f62k = null;
    /* renamed from: l */
    private String f63l = "";

    public JicePushManager(JiceSDK jiceSDK, Context context, String str, HttpURLRequest httpURLRequest) {
        this.f58g = context;
        this.f59h = jiceSDK;
        this.f63l = str;
        this.f60i = httpURLRequest;
        this.f53b = new ArrayList();
        this.f54c = new ArrayList();
        this.f61j = Executors.newSingleThreadExecutor();
        this.f62k = this.f58g.getSharedPreferences("com.admaster.jice.pushconfig" + str, 0);
        m84c();
    }

    /* renamed from: a */
    private synchronized void m75a(String str) {
        Editor edit = this.f62k.edit();
        edit.putString("configlist", str);
        edit.commit();
    }

    /* renamed from: b */
    private synchronized String m79b() {
        return this.f62k.getString("configlist", "");
    }

    /* renamed from: a */
    private synchronized void m76a(String str, int i) {
        Editor edit = this.f62k.edit();
        edit.putInt(str, i);
        edit.commit();
    }

    /* renamed from: b */
    private synchronized int m78b(String str) {
        return this.f62k.getInt(str, 0);
    }

    /* renamed from: c */
    private void m84c() {
        String b = m79b();
        if (!TextUtils.isEmpty(b)) {
            try {
                JSONArray init = JSONArrayInstrumentation.init(b);
                this.f53b.clear();
                int length = init.length();
                for (int i = 0; i < length; i++) {
                    this.f53b.add(new JicePushConfig(this.f63l, init.getJSONObject(i)));
                }
            } catch (Exception e) {
                LOG.m224a("JiceSDK.JicePushManager", "pushconfig data legal format.");
            }
        }
    }

    /* renamed from: a */
    private List<JicePushConfig> m69a(int i) {
        this.f54c.clear();
        for (JicePushConfig jicePushConfig : this.f53b) {
            Object horizontal;
            int b = m78b(String.valueOf(jicePushConfig.getPushId()));
            if (i == 2) {
                horizontal = jicePushConfig.getHorizontal();
            } else {
                horizontal = jicePushConfig.getVertical();
            }
            m85c("canShowlistItem:" + jicePushConfig.toString() + ",hasShowCount:" + b + ",Material:" + horizontal);
            if (horizontal != null) {
                try {
                    if ((horizontal.getType() != C0477f.IMAGE || horizontal.hasCached()) && jicePushConfig.hasInSchedule() && b < jicePushConfig.getShowTimes()) {
                        this.f54c.add(jicePushConfig);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return this.f54c;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo7613a(Context context, JiceViewListener jiceViewListener) {
        this.f55d = jiceViewListener;
        String str = "";
        if (this.f56e) {
            str = "[JicePush] " + JicePushShowError.JicePushIsShowing.toString();
            LOG.m226b("JiceSDK", str);
            if (this.f55d != null) {
                this.f55d.onJiceViewError(str);
                return;
            }
            return;
        }
        int c = ManagerUtils.m237c(context);
        List a = m69a(c);
        int size = a.size();
        m85c("addJiceView-->canshowlist:" + size + "  has callback:" + jiceViewListener);
        if (size < 1) {
            str = "[JicePush] " + JicePushShowError.JicePushNoData.toString();
            LOG.m226b("JiceSDK", str);
            if (this.f55d != null) {
                this.f55d.onJiceViewError(str);
            }
        } else if (ManagerUtils.m233a(this.f58g)) {
            if (size > 1) {
                try {
                    size = new Random().nextInt(size);
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f56e = false;
                    return;
                }
            }
            size = 0;
            JicePushConfig jicePushConfig = (JicePushConfig) a.get(size);
            if (jicePushConfig.getVertical() == null && jicePushConfig.getHorizontal() == null) {
                m85c("the pushview both vertical and horizontal material is null!");
                return;
            }
            C0476e c0476e = null;
            if (c == 1) {
                c0476e = jicePushConfig.getVertical();
            } else if (c == 2) {
                c0476e = jicePushConfig.getHorizontal();
            }
            if (c0476e == null) {
                String str2 = "JiceSDK.JicePushManager";
                LOG.m224a(str2, "current orientation:" + ManagerUtils.m231a(c) + "  has not material,please confirm your pushview Material on Jice System");
                return;
            }
            if (this.f55d != null) {
                this.f55d.onJiceViewWillShow();
            }
            String valueOf = String.valueOf(jicePushConfig.getPushId());
            int b = m78b(valueOf) + 1;
            m76a(valueOf, b);
            this.f56e = true;
            new Handler(this.f58g.getMainLooper()).postDelayed(new C0463l(this, jicePushConfig), 200);
            m85c(" jicePushView [id:" + valueOf + "] has Showed " + b + "  materical has cached:" + c0476e.hasCached());
        } else {
            str = "[JicePush] " + JicePushShowError.JicePushNoNet.toString();
            LOG.m228c("JiceSDK", str);
            if (this.f55d != null) {
                this.f55d.onJiceViewError(str);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo7612a() {
        m85c("start update jice conf:" + m87d() + "   isUpdating:" + this.f57f);
        if (m87d() && !this.f57f) {
            this.f61j.execute(new C0465n(this, this.f63l));
        }
    }

    /* renamed from: a */
    private void m74a(JicePushConfig jicePushConfig) {
        String str = null;
        if (m87d()) {
            int pushId = jicePushConfig.getPushId();
            C0476e vertical = jicePushConfig.getVertical();
            C0476e horizontal = jicePushConfig.getHorizontal();
            try {
                if (("handlerViewMaterial:" + pushId + "  ver:" + vertical) != null) {
                    str = vertical.toString();
                } else if ((null + "  hor:" + horizontal) != null) {
                    str = horizontal.toString();
                }
                m85c(str);
                if (!(vertical == null || jicePushConfig.hasCacheImage(vertical))) {
                    new C0464m(this, vertical).start();
                }
                if (horizontal != null && !jicePushConfig.hasCacheImage(horizontal)) {
                    new C0464m(this, horizontal).start();
                }
            } catch (Exception e) {
                LOG.m225a("JiceSDK.JicePushManager", "JCMaterial parsing failed.", e);
            }
        }
    }

    /* renamed from: a */
    private void m77a(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pushid", str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.f59h.track(str, jSONObject);
    }

    /* renamed from: d */
    private boolean m87d() {
        if (!this.f60i.mo7713a(this.f58g)) {
            m85c("network is disable!");
            return false;
        } else if (!HttpConfig.m164c() || ManagerUtils.m239d(this.f58g)) {
            return true;
        } else {
            LOG.m228c("JiceSDK.JicePushManager", "load push config or material condition only in WIFI");
            return false;
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo7614a(Context context, String str, JiceViewListener jiceViewListener) {
        this.f55d = jiceViewListener;
        String str2;
        if (this.f56e) {
            str2 = "[JicePushTest] " + JicePushShowError.JicePushIsShowing;
            LOG.m224a("JiceSDK", str2);
            if (this.f55d != null) {
                this.f55d.onJiceViewError(str2);
                return;
            }
            return;
        }
        int c = ManagerUtils.m237c(this.f58g);
        try {
            Object horizontal;
            JicePushConfig jicePushConfig = new JicePushConfig(this.f63l, JSONArrayInstrumentation.init(str).getJSONObject(0));
            if (c == 2) {
                horizontal = jicePushConfig.getHorizontal();
            } else {
                horizontal = jicePushConfig.getVertical();
            }
            m85c("testShowConfig:" + jicePushConfig.toString() + ",Material:" + horizontal);
            if (horizontal == null) {
                str2 = ManagerUtils.m231a(c);
                LOG.m224a("JiceSDK.JicePushManager", "current orientation:" + str2 + "  has not material,please confirm your test pushview Material on Jice System");
                JiceTestPushManager.m127b(this.f58g, this.f63l);
            } else if (horizontal.getType() != C0477f.IMAGE || horizontal.hasCached()) {
                if (this.f55d != null) {
                    this.f55d.onJiceViewWillShow();
                }
                this.f56e = true;
                JicePushView jicePushView = new JicePushView(context, jicePushConfig, this.f52a);
                jicePushView.mo7622a(Boolean.valueOf(true));
                jicePushView.mo7625c();
                JiceTestPushManager.m127b(this.f58g, this.f63l);
            } else {
                LOG.m228c("JiceSDK.JicePushManager", "测试推送活动没有缓存素材文件，请重新扫描二维码测试");
                JiceTestPushManager.m127b(this.f58g, this.f63l);
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.f56e = false;
        }
    }

    /* renamed from: c */
    private void m85c(String str) {
    }
}
