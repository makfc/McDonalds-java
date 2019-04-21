package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.support.design.C0045R;
import android.support.p000v4.content.ContextCompat;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.Interpolator;

abstract class FloatingActionButtonImpl {
    static final Interpolator ANIM_INTERPOLATOR = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
    static final int[] EMPTY_STATE_SET = new int[0];
    static final int[] ENABLED_STATE_SET = new int[]{16842910};
    static final int[] FOCUSED_ENABLED_STATE_SET = new int[]{16842908, 16842910};
    static final int[] PRESSED_ENABLED_STATE_SET = new int[]{16842919, 16842910};
    int mAnimState = 0;
    final Creator mAnimatorCreator;
    CircularBorderDrawable mBorderDrawable;
    Drawable mContentBackground;
    float mElevation;
    private OnPreDrawListener mPreDrawListener;
    float mPressedTranslationZ;
    Drawable mRippleDrawable;
    final ShadowViewDelegate mShadowViewDelegate;
    Drawable mShapeDrawable;
    private final Rect mTmpRect = new Rect();
    final VisibilityAwareImageButton mView;

    interface InternalVisibilityChangedListener {
        void onHidden();

        void onShown();
    }

    /* renamed from: android.support.design.widget.FloatingActionButtonImpl$1 */
    class C01011 implements OnPreDrawListener {
        C01011() {
        }

        public boolean onPreDraw() {
            FloatingActionButtonImpl.this.onPreDraw();
            return true;
        }
    }

    public abstract float getElevation();

    public abstract void getPadding(Rect rect);

    public abstract void hide(@Nullable InternalVisibilityChangedListener internalVisibilityChangedListener, boolean z);

    public abstract void jumpDrawableToCurrentState();

    public abstract void onCompatShadowChanged();

    public abstract void onDrawableStateChanged(int[] iArr);

    public abstract void onElevationsChanged(float f, float f2);

    public abstract void setBackgroundDrawable(ColorStateList colorStateList, Mode mode, int i, int i2);

    public abstract void setBackgroundTintList(ColorStateList colorStateList);

    public abstract void setBackgroundTintMode(Mode mode);

    public abstract void setRippleColor(int i);

    public abstract void show(@Nullable InternalVisibilityChangedListener internalVisibilityChangedListener, boolean z);

    FloatingActionButtonImpl(VisibilityAwareImageButton view, ShadowViewDelegate shadowViewDelegate, Creator animatorCreator) {
        this.mView = view;
        this.mShadowViewDelegate = shadowViewDelegate;
        this.mAnimatorCreator = animatorCreator;
    }

    /* Access modifiers changed, original: final */
    public final void setElevation(float elevation) {
        if (this.mElevation != elevation) {
            this.mElevation = elevation;
            onElevationsChanged(elevation, this.mPressedTranslationZ);
        }
    }

    /* Access modifiers changed, original: final */
    public final void setPressedTranslationZ(float translationZ) {
        if (this.mPressedTranslationZ != translationZ) {
            this.mPressedTranslationZ = translationZ;
            onElevationsChanged(this.mElevation, translationZ);
        }
    }

    /* Access modifiers changed, original: final */
    public final Drawable getContentBackground() {
        return this.mContentBackground;
    }

    /* Access modifiers changed, original: final */
    public final void updatePadding() {
        Rect rect = this.mTmpRect;
        getPadding(rect);
        onPaddingUpdated(rect);
        this.mShadowViewDelegate.setShadowPadding(rect.left, rect.top, rect.right, rect.bottom);
    }

    /* Access modifiers changed, original: 0000 */
    public void onPaddingUpdated(Rect padding) {
    }

    /* Access modifiers changed, original: 0000 */
    public void onAttachedToWindow() {
        if (requirePreDrawListener()) {
            ensurePreDrawListener();
            this.mView.getViewTreeObserver().addOnPreDrawListener(this.mPreDrawListener);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void onDetachedFromWindow() {
        if (this.mPreDrawListener != null) {
            this.mView.getViewTreeObserver().removeOnPreDrawListener(this.mPreDrawListener);
            this.mPreDrawListener = null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean requirePreDrawListener() {
        return false;
    }

    /* Access modifiers changed, original: 0000 */
    public CircularBorderDrawable createBorderDrawable(int borderWidth, ColorStateList backgroundTint) {
        Context context = this.mView.getContext();
        CircularBorderDrawable borderDrawable = newCircularDrawable();
        borderDrawable.setGradientColors(ContextCompat.getColor(context, C0045R.color.design_fab_stroke_top_outer_color), ContextCompat.getColor(context, C0045R.color.design_fab_stroke_top_inner_color), ContextCompat.getColor(context, C0045R.color.design_fab_stroke_end_inner_color), ContextCompat.getColor(context, C0045R.color.design_fab_stroke_end_outer_color));
        borderDrawable.setBorderWidth((float) borderWidth);
        borderDrawable.setBorderTint(backgroundTint);
        return borderDrawable;
    }

    /* Access modifiers changed, original: 0000 */
    public CircularBorderDrawable newCircularDrawable() {
        return new CircularBorderDrawable();
    }

    /* Access modifiers changed, original: 0000 */
    public void onPreDraw() {
    }

    private void ensurePreDrawListener() {
        if (this.mPreDrawListener == null) {
            this.mPreDrawListener = new C01011();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public GradientDrawable createShapeDrawable() {
        GradientDrawable d = newGradientDrawableForShape();
        d.setShape(1);
        d.setColor(-1);
        return d;
    }

    /* Access modifiers changed, original: 0000 */
    public GradientDrawable newGradientDrawableForShape() {
        return new GradientDrawable();
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isOrWillBeShown() {
        if (this.mView.getVisibility() != 0) {
            if (this.mAnimState == 2) {
                return true;
            }
            return false;
        } else if (this.mAnimState == 1) {
            return false;
        } else {
            return true;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isOrWillBeHidden() {
        if (this.mView.getVisibility() == 0) {
            if (this.mAnimState == 1) {
                return true;
            }
            return false;
        } else if (this.mAnimState == 2) {
            return false;
        } else {
            return true;
        }
    }
}
