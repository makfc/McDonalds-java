package com.mcdonalds.app.p043ui.animation;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import com.ensighten.Ensighten;

/* renamed from: com.mcdonalds.app.ui.animation.AnimatorEndListener */
public abstract class AnimatorEndListener implements AnimatorListener {
    public void onAnimationStart(Animator animation) {
        Ensighten.evaluateEvent(this, "onAnimationStart", new Object[]{animation});
    }

    public void onAnimationCancel(Animator animation) {
        Ensighten.evaluateEvent(this, "onAnimationCancel", new Object[]{animation});
    }

    public void onAnimationRepeat(Animator animation) {
        Ensighten.evaluateEvent(this, "onAnimationRepeat", new Object[]{animation});
    }
}
