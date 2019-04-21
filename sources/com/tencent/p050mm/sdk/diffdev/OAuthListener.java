package com.tencent.p050mm.sdk.diffdev;

/* renamed from: com.tencent.mm.sdk.diffdev.OAuthListener */
public interface OAuthListener {
    void onAuthFinish(OAuthErrCode oAuthErrCode, String str);

    void onAuthGotQrcode(String str, byte[] bArr);

    void onQrcodeScanned();
}
