package android.support.p001v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.p000v4.app.FragmentTransaction;
import android.support.p001v7.appcompat.C0334R;
import android.support.p001v7.view.ActionMode;
import android.support.p001v7.view.ActionMode.Callback;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* renamed from: android.support.v7.app.ActionBar */
public abstract class ActionBar {

    @RestrictTo
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v7.app.ActionBar$DisplayOptions */
    public @interface DisplayOptions {
    }

    /* renamed from: android.support.v7.app.ActionBar$LayoutParams */
    public static class LayoutParams extends MarginLayoutParams {
        public int gravity;

        public LayoutParams(@NonNull Context c, AttributeSet attrs) {
            super(c, attrs);
            this.gravity = 0;
            TypedArray a = c.obtainStyledAttributes(attrs, C0334R.styleable.ActionBarLayout);
            this.gravity = a.getInt(C0334R.styleable.ActionBarLayout_android_layout_gravity, 0);
            a.recycle();
        }

        public LayoutParams(int width, int height) {
            super(width, height);
            this.gravity = 0;
            this.gravity = 8388627;
        }

        public LayoutParams(LayoutParams source) {
            super(source);
            this.gravity = 0;
            this.gravity = source.gravity;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams source) {
            super(source);
            this.gravity = 0;
        }
    }

    @RestrictTo
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v7.app.ActionBar$NavigationMode */
    public @interface NavigationMode {
    }

    /* renamed from: android.support.v7.app.ActionBar$OnMenuVisibilityListener */
    public interface OnMenuVisibilityListener {
        void onMenuVisibilityChanged(boolean z);
    }

    @Deprecated
    /* renamed from: android.support.v7.app.ActionBar$OnNavigationListener */
    public interface OnNavigationListener {
        boolean onNavigationItemSelected(int i, long j);
    }

    @Deprecated
    /* renamed from: android.support.v7.app.ActionBar$Tab */
    public static abstract class Tab {
        public abstract CharSequence getContentDescription();

        public abstract View getCustomView();

        public abstract Drawable getIcon();

        public abstract int getPosition();

        public abstract CharSequence getText();

        public abstract void select();
    }

    @Deprecated
    /* renamed from: android.support.v7.app.ActionBar$TabListener */
    public interface TabListener {
        void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction);

        void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction);

        void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction);
    }

    public abstract View getCustomView();

    public abstract int getDisplayOptions();

    public abstract boolean isShowing();

    public abstract void setCustomView(int i);

    public abstract void setCustomView(View view);

    public abstract void setDisplayHomeAsUpEnabled(boolean z);

    public abstract void setDisplayShowCustomEnabled(boolean z);

    public abstract void setDisplayShowHomeEnabled(boolean z);

    public abstract void setDisplayShowTitleEnabled(boolean z);

    public abstract void setTitle(@StringRes int i);

    public abstract void setTitle(CharSequence charSequence);

    public void setHomeButtonEnabled(boolean enabled) {
    }

    public Context getThemedContext() {
        return null;
    }

    public void setHomeActionContentDescription(@StringRes int resId) {
    }

    public void setHideOnContentScrollEnabled(boolean hideOnContentScroll) {
        if (hideOnContentScroll) {
            throw new UnsupportedOperationException("Hide on content scroll is not supported in this action bar configuration.");
        }
    }

    public int getHideOffset() {
        return 0;
    }

    public void setElevation(float elevation) {
        if (elevation != 0.0f) {
            throw new UnsupportedOperationException("Setting a non-zero elevation is not supported in this action bar configuration.");
        }
    }

    @RestrictTo
    public void setDefaultDisplayHomeAsUpEnabled(boolean enabled) {
    }

    @RestrictTo
    public void setShowHideAnimationEnabled(boolean enabled) {
    }

    @RestrictTo
    public void onConfigurationChanged(Configuration config) {
    }

    @RestrictTo
    public void dispatchMenuVisibilityChanged(boolean visible) {
    }

    @RestrictTo
    public ActionMode startActionMode(Callback callback) {
        return null;
    }

    @RestrictTo
    public boolean invalidateOptionsMenu() {
        return false;
    }

    @RestrictTo
    public boolean onKeyShortcut(int keyCode, KeyEvent ev) {
        return false;
    }

    @RestrictTo
    public boolean collapseActionView() {
        return false;
    }

    @RestrictTo
    public void setWindowTitle(CharSequence title) {
    }

    /* Access modifiers changed, original: 0000 */
    public boolean requestFocus() {
        return false;
    }

    /* Access modifiers changed, original: 0000 */
    public void onDestroy() {
    }
}
