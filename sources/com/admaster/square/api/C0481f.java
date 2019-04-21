package com.admaster.square.api;

import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.admaster.square.utils.ConnectUtil;

/* compiled from: ConvMobiInstance */
/* renamed from: com.admaster.square.api.f */
class C0481f extends Handler {
    /* renamed from: a */
    final /* synthetic */ ConvMobiInstance f184a;

    C0481f(ConvMobiInstance convMobiInstance, Looper looper) {
        this.f184a = convMobiInstance;
        super(looper);
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                if (VERSION.SDK_INT < 19) {
                    try {
                        ConnectUtil.m398a().mo7804a(new WebView(this.f184a.f182f).getSettings().getUserAgentString());
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                ConnectUtil.m398a().mo7804a(WebSettings.getDefaultUserAgent(this.f184a.f182f));
                return;
            default:
                return;
        }
    }
}
