package com.tencent.p050mm.sdk.diffdev.p068a;

import com.tencent.p050mm.sdk.diffdev.OAuthListener;
import java.util.ArrayList;

/* renamed from: com.tencent.mm.sdk.diffdev.a.c */
final class C4367c implements Runnable {
    /* renamed from: ah */
    final /* synthetic */ C4366b f6822ah;

    C4367c(C4366b c4366b) {
        this.f6822ah = c4366b;
    }

    public final void run() {
        ArrayList<OAuthListener> arrayList = new ArrayList();
        arrayList.addAll(this.f6822ah.f6821ag.f6818ad);
        for (OAuthListener onQrcodeScanned : arrayList) {
            onQrcodeScanned.onQrcodeScanned();
        }
    }
}
