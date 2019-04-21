package com.admaster.jice.api;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.admaster.jice.p004a.JiceCore;
import com.admaster.jice.p005b.HttpConfig;
import com.admaster.jice.p007d.ManagerUtils;
import com.facebook.stetho.common.Utf8Charset;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.net.URLEncoder;
import java.util.HashMap;
import org.json.JSONObject;

public class JiceSDK {
    /* renamed from: b */
    private static JiceCore f108b = null;
    /* renamed from: d */
    private static volatile JiceSDK f109d = null;
    /* renamed from: a */
    private Context f110a;
    /* renamed from: c */
    private long f111c = -1;

    private JiceSDK() {
    }

    public static JiceSDK getInstance(Context context, JiceConfig jiceConfig) {
        if (f109d == null) {
            synchronized (JiceSDK.class) {
                if (f109d == null) {
                    f109d = new JiceSDK();
                    f109d.m148a(context, jiceConfig);
                }
            }
        }
        return f109d;
    }

    public static JiceSDK getInstance(Context context, String str, JiceConfig jiceConfig) {
        if (f109d == null) {
            synchronized (JiceSDK.class) {
                if (f109d == null) {
                    f109d = new JiceSDK();
                    f109d.m149a(context, str, jiceConfig);
                }
            }
        }
        return f109d;
    }

    public static JiceSDK getInstance(Context context, String str, String str2, String str3, JiceConfig jiceConfig) {
        if (f109d == null) {
            synchronized (JiceSDK.class) {
                if (f109d == null) {
                    f109d = new JiceSDK();
                    f109d.m150a(context, str, str2, str3, jiceConfig);
                }
            }
        }
        return f109d;
    }

    /* renamed from: a */
    private synchronized void m148a(Context context, JiceConfig jiceConfig) {
        String a = ManagerUtils.m232a(context, "com.admaster.jicesdk.appkey");
        String a2 = ManagerUtils.m232a(context, "com.admaster.jicesdk.pushserverurl");
        String a3 = ManagerUtils.m232a(context, "com.admaster.jicesdk.trackserverurl");
        if (TextUtils.isEmpty(a2) || TextUtils.isEmpty(a3)) {
            m149a(context, a, jiceConfig);
        } else {
            m150a(context, a, a2, a3, jiceConfig);
        }
    }

    /* renamed from: a */
    private synchronized void m149a(Context context, String str, JiceConfig jiceConfig) {
        m154b(context, str, null, null, jiceConfig);
    }

    /* renamed from: a */
    private synchronized void m150a(Context context, String str, String str2, String str3, JiceConfig jiceConfig) {
        m154b(context, str, str2, str3, jiceConfig);
    }

    /* renamed from: b */
    private void m154b(Context context, String str, String str2, String str3, JiceConfig jiceConfig) {
        if ((System.currentTimeMillis() - this.f111c) / 1000 < 30) {
            Log.d("JiceSDK", "has already init");
        } else if (context == null) {
            Log.e("JiceSDK", "JiceSDK init,context can`t be null!");
        } else if (str == null) {
            Log.e("JiceSDK", "JiceSDK init,appkey can`t be null!");
        } else {
            try {
                String encode = URLEncoder.encode(str.trim().toString(), Utf8Charset.NAME);
                if (TextUtils.isEmpty(encode)) {
                    Log.e("JiceSDK", "appKey is empty,please add appKey!");
                } else if (!ManagerUtils.m236b(context, "android.permission.INTERNET")) {
                    Log.e("JiceSDK", "JiceSDK require INTERNET permission,please add INTERNET permission on your Manifest.xml");
                } else if (ManagerUtils.m240e(this.f110a)) {
                    this.f111c = -1;
                    this.f110a = context.getApplicationContext();
                    if (jiceConfig == null) {
                        jiceConfig = new JiceConfig();
                    }
                    HttpConfig.m156a(encode);
                    HttpConfig.m157a(jiceConfig.isDebugMode());
                    HttpConfig.m160b(jiceConfig.isEventWifiOnly());
                    HttpConfig.m163c(jiceConfig.isPushWifiOnly());
                    if (!TextUtils.isEmpty(str2)) {
                        HttpConfig.m159b(str2);
                    }
                    if (!TextUtils.isEmpty(str3)) {
                        HttpConfig.m162c(str3);
                    }
                    f108b = new JiceCore(this.f110a, encode, this);
                    f108b.mo7599a();
                    this.f111c = System.currentTimeMillis();
                } else {
                    Log.e("JiceSDK", "JiceSDK require android.support.v4.util.Pair,please add android-support-v4.jar on your project libs");
                }
            } catch (Exception e) {
                Log.e("JiceSDK", "JiceSDK init failed:", e);
            }
        }
    }

    public synchronized void addUserIdentifier(String str, HashMap<String, Object> hashMap) {
        try {
            m152a(str, new JSONObject(hashMap));
        } catch (Exception e) {
            Log.e("JiceSDK", "user profile is undefined format!");
        }
        return;
    }

    public synchronized void addUserIdentifier(String str, JSONObject jSONObject) {
        m152a(str, jSONObject);
    }

    /* renamed from: a */
    private void m152a(String str, JSONObject jSONObject) {
        if (f108b == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.e("JiceSDK", "userId can`t be empty!");
        } else if (m153a(str)) {
            if (jSONObject == null) {
                try {
                    jSONObject = new JSONObject();
                } catch (Exception e) {
                    Log.e("JiceSDK", "user profile is undefined format!");
                    return;
                }
            }
            jSONObject.put("id", str);
            f108b.mo7603a(jSONObject);
        } else {
            Log.e("JiceSDK", "The name of userId a maximum length of 255 characters！");
        }
    }

    public synchronized void track(String str, HashMap<String, ?> hashMap) {
        m151a(str, (Object) hashMap);
    }

    public synchronized void track(String str) {
        m151a(str, null);
    }

    public synchronized void track(String str, JSONObject jSONObject) {
        m151a(str, (Object) jSONObject);
    }

    /* renamed from: a */
    private void m151a(String str, Object obj) {
        if (f108b != null) {
            try {
                if (m155b(str)) {
                    Bundle bundle = new Bundle();
                    String str2 = "";
                    if (obj != null) {
                        if (obj instanceof HashMap) {
                            if (obj != null) {
                                JSONObject jSONObject = new JSONObject((HashMap) obj);
                                str2 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
                            }
                        } else if (obj instanceof JSONObject) {
                            JSONObject jSONObject2 = (JSONObject) obj;
                            str2 = !(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : JSONObjectInstrumentation.toString(jSONObject2);
                        }
                    }
                    bundle.putString("eventname", str);
                    bundle.putString("eventlabel", str2);
                    f108b.mo7602a(bundle);
                }
            } catch (Exception e) {
                Log.e("JiceSDK", "llegal track event data format,please correct and retry!");
            }
        }
    }

    /* renamed from: a */
    private boolean m153a(String str) {
        if (str.length() > 255) {
            return false;
        }
        return true;
    }

    /* renamed from: b */
    private boolean m155b(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e("JiceSDK", "The name of event can`t be empty!");
            return false;
        } else if (m153a(str)) {
            return true;
        } else {
            Log.e("JiceSDK", "The name of event maximum length of 255 characters！");
            return false;
        }
    }

    public void addJicePushView(Context context, JiceViewListener jiceViewListener) {
        if (f108b == null) {
            return;
        }
        if (context == null) {
            Log.e("JiceSDK", "the context of the current parameters can`t be null!");
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(context, "com.admaster.jice.api.JicePushActivity");
        if (context.getPackageManager().resolveActivity(intent, 0) == null) {
            Log.e("JiceSDK", "unable to find explicit activity class { com.admaster.jice.api.JicePushActivity },if you want show push view,you must declared this activity in your AndroidManifest.xml");
        } else {
            f108b.mo7600a(context, jiceViewListener);
        }
    }

    public void handleOpenURL(Intent intent) {
        if (f108b != null) {
            f108b.mo7601a(intent);
        }
    }
}
