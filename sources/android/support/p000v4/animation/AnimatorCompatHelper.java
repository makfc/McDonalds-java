package android.support.p000v4.animation;

import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.view.View;

@RestrictTo
/* renamed from: android.support.v4.animation.AnimatorCompatHelper */
public final class AnimatorCompatHelper {
    private static final AnimatorProvider IMPL;

    static {
        if (VERSION.SDK_INT >= 12) {
            IMPL = new HoneycombMr1AnimatorCompatProvider();
        } else {
            IMPL = new GingerbreadAnimatorCompatProvider();
        }
    }

    public static ValueAnimatorCompat emptyValueAnimator() {
        return IMPL.emptyValueAnimator();
    }

    private AnimatorCompatHelper() {
    }

    public static void clearInterpolator(View view) {
        IMPL.clearInterpolator(view);
    }
}
