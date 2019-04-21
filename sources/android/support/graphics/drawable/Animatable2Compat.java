package android.support.graphics.drawable;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

public interface Animatable2Compat extends Animatable {

    public static abstract class AnimationCallback {

        /* renamed from: android.support.graphics.drawable.Animatable2Compat$AnimationCallback$1 */
        class C01251 extends android.graphics.drawable.Animatable2.AnimationCallback {
            final /* synthetic */ AnimationCallback this$0;

            public void onAnimationStart(Drawable drawable) {
                this.this$0.onAnimationStart(drawable);
            }

            public void onAnimationEnd(Drawable drawable) {
                this.this$0.onAnimationEnd(drawable);
            }
        }

        public void onAnimationStart(Drawable drawable) {
        }

        public void onAnimationEnd(Drawable drawable) {
        }
    }
}
