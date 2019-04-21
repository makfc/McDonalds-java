package com.admaster.jice.p004a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;
import android.support.p000v4.view.InputDeviceCompat;
import android.text.TextUtils;
import android.util.Log;
import com.admaster.jice.api.JicePushShowError;
import com.admaster.jice.api.JiceSDK;
import com.admaster.jice.api.JiceViewListener;
import com.admaster.jice.p007d.LOG;
import org.json.JSONObject;

/* renamed from: com.admaster.jice.a.c */
public class JiceCore {
    /* renamed from: a */
    private C0456d f23a;
    /* renamed from: b */
    private JiceSDK f24b;
    /* renamed from: c */
    private Context f25c = null;
    /* renamed from: d */
    private String f26d;
    /* renamed from: e */
    private StoreManager f27e = null;
    /* renamed from: f */
    private EventManager f28f = null;
    /* renamed from: g */
    private JicePushManager f29g = null;
    /* renamed from: h */
    private boolean f30h = false;

    public JiceCore(Context context, String str, JiceSDK jiceSDK) {
        this.f24b = jiceSDK;
        this.f25c = context;
        this.f26d = str;
        this.f27e = StoreManager.m130a(context);
        this.f28f = EventManager.m19a(context);
        this.f27e.mo7636a(str);
        HandlerThread handlerThread = new HandlerThread(JiceCore.class.getCanonicalName());
        handlerThread.setPriority(10);
        handlerThread.start();
        this.f23a = new C0456d(this, context, handlerThread.getLooper());
    }

    /* renamed from: a */
    public void mo7599a() {
        this.f23a.mo7604a();
        this.f23a.sendMessage(this.f23a.obtainMessage(256));
    }

    /* renamed from: a */
    public void mo7602a(Bundle bundle) {
        m28a("start addTackEvent:" + bundle.toString());
        Message obtainMessage = this.f23a.obtainMessage(InputDeviceCompat.SOURCE_KEYBOARD);
        obtainMessage.obj = bundle;
        this.f23a.sendMessage(obtainMessage);
    }

    /* renamed from: a */
    public void mo7603a(JSONObject jSONObject) {
        Message obtainMessage = this.f23a.obtainMessage(258);
        obtainMessage.obj = jSONObject;
        this.f23a.sendMessage(obtainMessage);
    }

    /* renamed from: a */
    public void mo7600a(Context context, JiceViewListener jiceViewListener) {
        String a = JiceTestPushManager.m119a(this.f25c, this.f26d);
        if (!TextUtils.isEmpty(a)) {
            m28a("#####add Jice View on Test mode#####");
            this.f29g.mo7614a(context, a, jiceViewListener);
            this.f30h = false;
        } else if (this.f30h) {
            a = "[JicePushTest] " + JicePushShowError.JicePushIsDownloading.toString();
            Log.d("JiceSDK", a);
            if (jiceViewListener != null) {
                jiceViewListener.onJiceViewError(a);
            }
        } else {
            m28a("#####add Jice View on Official mode#####");
            this.f29g.mo7613a(context, jiceViewListener);
        }
    }

    /* renamed from: a */
    public void mo7601a(Intent intent) {
        if (intent != null) {
            try {
                Uri data = intent.getData();
                LOG.m226b("JiceSDK", "#####open with deep link, Uri is：" + data + "#####");
                if (data != null) {
                    this.f30h = true;
                    Message obtainMessage = this.f23a.obtainMessage(259);
                    obtainMessage.obj = data;
                    this.f23a.sendMessage(obtainMessage);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private void m28a(String str) {
    }
}
