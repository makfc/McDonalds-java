package com.admaster.jice.p004a;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/* compiled from: JicePushView */
/* renamed from: com.admaster.jice.a.p */
class C0466p implements OnTouchListener {
    /* renamed from: a */
    final /* synthetic */ JicePushView f83a;

    C0466p(JicePushView jicePushView) {
        this.f83a = jicePushView;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.f83a.mo7624b();
        }
        if (motionEvent.getAction() == 8) {
            this.f83a.mo7624b();
        }
        return false;
    }
}
