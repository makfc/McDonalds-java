package com.admaster.jice.p004a;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

/* compiled from: JicePushView */
/* renamed from: com.admaster.jice.a.r */
class C0468r implements OnKeyListener {
    /* renamed from: a */
    final /* synthetic */ JicePushView f85a;

    C0468r(JicePushView jicePushView) {
        this.f85a = jicePushView;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getAction() == 1) {
            this.f85a.mo7624b();
        }
        return true;
    }
}
