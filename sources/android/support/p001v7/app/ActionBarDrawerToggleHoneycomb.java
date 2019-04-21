package android.support.p001v7.app;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.lang.reflect.Method;

@TargetApi(11)
@RequiresApi
/* renamed from: android.support.v7.app.ActionBarDrawerToggleHoneycomb */
class ActionBarDrawerToggleHoneycomb {
    private static final int[] THEME_ATTRS = new int[]{16843531};

    /* renamed from: android.support.v7.app.ActionBarDrawerToggleHoneycomb$SetIndicatorInfo */
    static class SetIndicatorInfo {
        public Method setHomeActionContentDescription;
        public Method setHomeAsUpIndicator;
        public ImageView upIndicatorView;

        SetIndicatorInfo(Activity activity) {
            try {
                this.setHomeAsUpIndicator = ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", new Class[]{Drawable.class});
                this.setHomeActionContentDescription = ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", new Class[]{Integer.TYPE});
            } catch (NoSuchMethodException e) {
                View home = activity.findViewById(16908332);
                if (home != null) {
                    ViewGroup parent = (ViewGroup) home.getParent();
                    if (parent.getChildCount() == 2) {
                        View up;
                        View first = parent.getChildAt(0);
                        View second = parent.getChildAt(1);
                        if (first.getId() == 16908332) {
                            up = second;
                        } else {
                            up = first;
                        }
                        if (up instanceof ImageView) {
                            this.upIndicatorView = (ImageView) up;
                        }
                    }
                }
            }
        }
    }

    ActionBarDrawerToggleHoneycomb() {
    }

    public static SetIndicatorInfo setActionBarDescription(SetIndicatorInfo info, Activity activity, int contentDescRes) {
        if (info == null) {
            info = new SetIndicatorInfo(activity);
        }
        if (info.setHomeAsUpIndicator != null) {
            try {
                ActionBar actionBar = activity.getActionBar();
                info.setHomeActionContentDescription.invoke(actionBar, new Object[]{Integer.valueOf(contentDescRes)});
                if (VERSION.SDK_INT <= 19) {
                    actionBar.setSubtitle(actionBar.getSubtitle());
                }
            } catch (Exception e) {
                Log.w("ActionBarDrawerToggleHoneycomb", "Couldn't set content description via JB-MR2 API", e);
            }
        }
        return info;
    }
}
