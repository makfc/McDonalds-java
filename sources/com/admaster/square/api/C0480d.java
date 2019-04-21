package com.admaster.square.api;

import android.text.TextUtils;
import com.admaster.square.utils.ConnectUtil;
import com.admaster.square.utils.Constant;
import com.admaster.square.utils.NetWorkInfoUtil;

/* compiled from: ConvMobiHelper */
/* renamed from: com.admaster.square.api.d */
class C0480d implements Runnable {
    /* renamed from: a */
    final /* synthetic */ ConvMobiHelper f173a;
    /* renamed from: b */
    private final /* synthetic */ String f174b;
    /* renamed from: c */
    private final /* synthetic */ CustomEvent f175c;
    /* renamed from: d */
    private final /* synthetic */ String f176d;

    C0480d(ConvMobiHelper convMobiHelper, String str, CustomEvent customEvent, String str2) {
        this.f173a = convMobiHelper;
        this.f174b = str;
        this.f175c = customEvent;
        this.f176d = str2;
    }

    public void run() {
        try {
            if (NetWorkInfoUtil.m452a(this.f173a.f167c)) {
                ConnectUtil a = ConnectUtil.m398a();
                if (!TextUtils.isEmpty(a.mo7805b())) {
                    String a2 = a.mo7803a(Constant.f259a, this.f174b);
                    if (a2 == null || !a2.contains("200")) {
                        if (this.f175c != CustomEvent.ADACTIVE) {
                            this.f173a.mo7750a(this.f173a.f167c, this.f176d, this.f174b);
                            return;
                        } else if (this.f175c == CustomEvent.ADACTIVE) {
                            this.f173a.m303a(this.f173a.f167c, this.f174b);
                            return;
                        } else {
                            return;
                        }
                    } else if (this.f175c == CustomEvent.ADACTIVE) {
                        ConvMobiHelper.m302a(this.f173a.f167c);
                        return;
                    } else {
                        return;
                    }
                }
                return;
            }
            Logger.m364b("network connected is failed!");
            if (this.f175c == CustomEvent.ADACTIVE) {
                this.f173a.m303a(this.f173a.f167c, this.f174b);
            } else {
                this.f173a.mo7750a(this.f173a.f167c, this.f176d, this.f174b);
            }
        } catch (Exception e) {
            Logger.m364b(e.getMessage());
        }
    }
}
