package com.tencent.p050mm.sdk.diffdev.p068a;

import android.util.Log;
import com.tencent.p050mm.sdk.diffdev.OAuthErrCode;
import com.tencent.p050mm.sdk.diffdev.OAuthListener;
import java.util.ArrayList;

/* renamed from: com.tencent.mm.sdk.diffdev.a.b */
final class C4366b implements OAuthListener {
    /* renamed from: ag */
    final /* synthetic */ C4365a f6821ag;

    C4366b(C4365a c4365a) {
        this.f6821ag = c4365a;
    }

    public final void onAuthFinish(OAuthErrCode oAuthErrCode, String str) {
        Log.d("MicroMsg.SDK.ListenerWrapper", String.format("onAuthFinish, errCode = %s, authCode = %s", new Object[]{oAuthErrCode.toString(), str}));
        this.f6821ag.f6819ae = null;
        ArrayList<OAuthListener> arrayList = new ArrayList();
        arrayList.addAll(this.f6821ag.f6818ad);
        for (OAuthListener onAuthFinish : arrayList) {
            onAuthFinish.onAuthFinish(oAuthErrCode, str);
        }
    }

    public final void onAuthGotQrcode(String str, byte[] bArr) {
        Log.d("MicroMsg.SDK.ListenerWrapper", "onAuthGotQrcode, qrcodeImgPath = " + str);
        ArrayList<OAuthListener> arrayList = new ArrayList();
        arrayList.addAll(this.f6821ag.f6818ad);
        for (OAuthListener onAuthGotQrcode : arrayList) {
            onAuthGotQrcode.onAuthGotQrcode(str, bArr);
        }
    }

    public final void onQrcodeScanned() {
        Log.d("MicroMsg.SDK.ListenerWrapper", "onQrcodeScanned");
        if (this.f6821ag.f6817ac != null) {
            this.f6821ag.f6817ac.post(new C4367c(this));
        }
    }
}
