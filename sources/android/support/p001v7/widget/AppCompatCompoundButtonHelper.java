package android.support.p001v7.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p000v4.widget.CompoundButtonCompat;
import android.support.p001v7.appcompat.C0334R;
import android.support.p001v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.CompoundButton;

/* renamed from: android.support.v7.widget.AppCompatCompoundButtonHelper */
class AppCompatCompoundButtonHelper {
    private ColorStateList mButtonTintList = null;
    private Mode mButtonTintMode = null;
    private boolean mHasButtonTint = false;
    private boolean mHasButtonTintMode = false;
    private boolean mSkipNextApply;
    private final CompoundButton mView;

    /* renamed from: android.support.v7.widget.AppCompatCompoundButtonHelper$DirectSetButtonDrawableInterface */
    interface DirectSetButtonDrawableInterface {
    }

    AppCompatCompoundButtonHelper(CompoundButton view) {
        this.mView = view;
    }

    /* Access modifiers changed, original: 0000 */
    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = this.mView.getContext().obtainStyledAttributes(attrs, C0334R.styleable.CompoundButton, defStyleAttr, 0);
        try {
            if (a.hasValue(C0334R.styleable.CompoundButton_android_button)) {
                int resourceId = a.getResourceId(C0334R.styleable.CompoundButton_android_button, 0);
                if (resourceId != 0) {
                    this.mView.setButtonDrawable(AppCompatResources.getDrawable(this.mView.getContext(), resourceId));
                }
            }
            if (a.hasValue(C0334R.styleable.CompoundButton_buttonTint)) {
                CompoundButtonCompat.setButtonTintList(this.mView, a.getColorStateList(C0334R.styleable.CompoundButton_buttonTint));
            }
            if (a.hasValue(C0334R.styleable.CompoundButton_buttonTintMode)) {
                CompoundButtonCompat.setButtonTintMode(this.mView, DrawableUtils.parseTintMode(a.getInt(C0334R.styleable.CompoundButton_buttonTintMode, -1), null));
            }
            a.recycle();
        } catch (Throwable th) {
            a.recycle();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void setSupportButtonTintList(ColorStateList tint) {
        this.mButtonTintList = tint;
        this.mHasButtonTint = true;
        applyButtonTint();
    }

    /* Access modifiers changed, original: 0000 */
    public ColorStateList getSupportButtonTintList() {
        return this.mButtonTintList;
    }

    /* Access modifiers changed, original: 0000 */
    public void setSupportButtonTintMode(@Nullable Mode tintMode) {
        this.mButtonTintMode = tintMode;
        this.mHasButtonTintMode = true;
        applyButtonTint();
    }

    /* Access modifiers changed, original: 0000 */
    public Mode getSupportButtonTintMode() {
        return this.mButtonTintMode;
    }

    /* Access modifiers changed, original: 0000 */
    public void onSetButtonDrawable() {
        if (this.mSkipNextApply) {
            this.mSkipNextApply = false;
            return;
        }
        this.mSkipNextApply = true;
        applyButtonTint();
    }

    /* Access modifiers changed, original: 0000 */
    public void applyButtonTint() {
        Drawable buttonDrawable = CompoundButtonCompat.getButtonDrawable(this.mView);
        if (buttonDrawable == null) {
            return;
        }
        if (this.mHasButtonTint || this.mHasButtonTintMode) {
            buttonDrawable = DrawableCompat.wrap(buttonDrawable).mutate();
            if (this.mHasButtonTint) {
                DrawableCompat.setTintList(buttonDrawable, this.mButtonTintList);
            }
            if (this.mHasButtonTintMode) {
                DrawableCompat.setTintMode(buttonDrawable, this.mButtonTintMode);
            }
            if (buttonDrawable.isStateful()) {
                buttonDrawable.setState(this.mView.getDrawableState());
            }
            this.mView.setButtonDrawable(buttonDrawable);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public int getCompoundPaddingLeft(int superValue) {
        if (VERSION.SDK_INT >= 17) {
            return superValue;
        }
        Drawable buttonDrawable = CompoundButtonCompat.getButtonDrawable(this.mView);
        if (buttonDrawable != null) {
            return superValue + buttonDrawable.getIntrinsicWidth();
        }
        return superValue;
    }
}
