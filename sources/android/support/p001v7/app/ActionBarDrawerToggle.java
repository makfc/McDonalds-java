package android.support.p001v7.app;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.p000v4.view.GravityCompat;
import android.support.p000v4.widget.DrawerLayout;
import android.support.p000v4.widget.DrawerLayout.DrawerListener;
import android.support.p001v7.app.ActionBarDrawerToggleHoneycomb.SetIndicatorInfo;
import android.support.p001v7.graphics.drawable.DrawerArrowDrawable;
import android.support.p001v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: android.support.v7.app.ActionBarDrawerToggle */
public class ActionBarDrawerToggle implements DrawerListener {
    private final Delegate mActivityImpl;
    private final int mCloseDrawerContentDescRes;
    boolean mDrawerIndicatorEnabled;
    private final DrawerLayout mDrawerLayout;
    private boolean mDrawerSlideAnimationEnabled;
    private final int mOpenDrawerContentDescRes;
    private DrawerArrowDrawable mSlider;
    OnClickListener mToolbarNavigationClickListener;

    /* renamed from: android.support.v7.app.ActionBarDrawerToggle$DelegateProvider */
    public interface DelegateProvider {
    }

    /* renamed from: android.support.v7.app.ActionBarDrawerToggle$1 */
    class C03051 implements OnClickListener {
        final /* synthetic */ ActionBarDrawerToggle this$0;

        public void onClick(View v) {
            if (this.this$0.mDrawerIndicatorEnabled) {
                this.this$0.toggle();
            } else if (this.this$0.mToolbarNavigationClickListener != null) {
                this.this$0.mToolbarNavigationClickListener.onClick(v);
            }
        }
    }

    /* renamed from: android.support.v7.app.ActionBarDrawerToggle$Delegate */
    public interface Delegate {
        void setActionBarDescription(@StringRes int i);
    }

    /* renamed from: android.support.v7.app.ActionBarDrawerToggle$DummyDelegate */
    static class DummyDelegate implements Delegate {
        public void setActionBarDescription(@StringRes int contentDescRes) {
        }
    }

    @RequiresApi
    @TargetApi(11)
    /* renamed from: android.support.v7.app.ActionBarDrawerToggle$HoneycombDelegate */
    private static class HoneycombDelegate implements Delegate {
        final Activity mActivity;
        SetIndicatorInfo mSetIndicatorInfo;

        public void setActionBarDescription(int contentDescRes) {
            this.mSetIndicatorInfo = ActionBarDrawerToggleHoneycomb.setActionBarDescription(this.mSetIndicatorInfo, this.mActivity, contentDescRes);
        }
    }

    @RequiresApi
    @TargetApi(14)
    /* renamed from: android.support.v7.app.ActionBarDrawerToggle$IcsDelegate */
    private static class IcsDelegate extends HoneycombDelegate {
    }

    @RequiresApi
    @TargetApi(18)
    /* renamed from: android.support.v7.app.ActionBarDrawerToggle$JellybeanMr2Delegate */
    private static class JellybeanMr2Delegate implements Delegate {
        final Activity mActivity;

        public void setActionBarDescription(int contentDescRes) {
            ActionBar actionBar = this.mActivity.getActionBar();
            if (actionBar != null) {
                actionBar.setHomeActionContentDescription(contentDescRes);
            }
        }
    }

    /* renamed from: android.support.v7.app.ActionBarDrawerToggle$ToolbarCompatDelegate */
    static class ToolbarCompatDelegate implements Delegate {
        final CharSequence mDefaultContentDescription;
        final Toolbar mToolbar;

        public void setActionBarDescription(@StringRes int contentDescRes) {
            if (contentDescRes == 0) {
                this.mToolbar.setNavigationContentDescription(this.mDefaultContentDescription);
            } else {
                this.mToolbar.setNavigationContentDescription(contentDescRes);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void toggle() {
        int drawerLockMode = this.mDrawerLayout.getDrawerLockMode((int) GravityCompat.START);
        if (this.mDrawerLayout.isDrawerVisible((int) GravityCompat.START) && drawerLockMode != 2) {
            this.mDrawerLayout.closeDrawer((int) GravityCompat.START);
        } else if (drawerLockMode != 1) {
            this.mDrawerLayout.openDrawer((int) GravityCompat.START);
        }
    }

    public void onDrawerSlide(View drawerView, float slideOffset) {
        if (this.mDrawerSlideAnimationEnabled) {
            setPosition(Math.min(1.0f, Math.max(0.0f, slideOffset)));
        } else {
            setPosition(0.0f);
        }
    }

    public void onDrawerOpened(View drawerView) {
        setPosition(1.0f);
        if (this.mDrawerIndicatorEnabled) {
            setActionBarDescription(this.mCloseDrawerContentDescRes);
        }
    }

    public void onDrawerClosed(View drawerView) {
        setPosition(0.0f);
        if (this.mDrawerIndicatorEnabled) {
            setActionBarDescription(this.mOpenDrawerContentDescRes);
        }
    }

    public void onDrawerStateChanged(int newState) {
    }

    /* Access modifiers changed, original: 0000 */
    public void setActionBarDescription(int contentDescRes) {
        this.mActivityImpl.setActionBarDescription(contentDescRes);
    }

    private void setPosition(float position) {
        if (position == 1.0f) {
            this.mSlider.setVerticalMirror(true);
        } else if (position == 0.0f) {
            this.mSlider.setVerticalMirror(false);
        }
        this.mSlider.setProgress(position);
    }
}
