package com.facebook.stetho.common.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.PointF;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.facebook.stetho.common.Predicate;
import com.facebook.stetho.common.Util;
import javax.annotation.Nullable;

public final class ViewUtil {

    private static class ViewCompat {
        private static final ViewCompat sInstance;

        @TargetApi(11)
        private static class ViewCompatHoneycomb extends ViewCompat {
            private ViewCompatHoneycomb() {
            }

            public float getAlpha(View view) {
                return view.getAlpha();
            }
        }

        static {
            if (VERSION.SDK_INT >= 11) {
                sInstance = new ViewCompatHoneycomb();
            } else {
                sInstance = new ViewCompat();
            }
        }

        public static ViewCompat getInstance() {
            return sInstance;
        }

        protected ViewCompat() {
        }

        public float getAlpha(View view) {
            return 1.0f;
        }
    }

    private ViewUtil() {
    }

    private static boolean isHittable(View view) {
        if (view.getVisibility() == 0 && ViewCompat.getInstance().getAlpha(view) >= 0.001f) {
            return true;
        }
        return false;
    }

    @Nullable
    public static View hitTest(View view, float x, float y) {
        return hitTest(view, x, y, null);
    }

    @Nullable
    public static View hitTest(View view, float x, float y, @Nullable Predicate<View> viewSelector) {
        View result = hitTestImpl(view, x, y, viewSelector, false);
        if (result == null) {
            return hitTestImpl(view, x, y, viewSelector, true);
        }
        return result;
    }

    private static View hitTestImpl(View view, float x, float y, @Nullable Predicate<View> viewSelector, boolean allowViewGroupResult) {
        if (!isHittable(view) || !pointInView(view, x, y)) {
            return null;
        }
        if (viewSelector != null && !viewSelector.apply(view)) {
            return null;
        }
        if (!(view instanceof ViewGroup)) {
            return view;
        }
        View viewGroup = (ViewGroup) view;
        if (viewGroup.getChildCount() > 0) {
            PointF localPoint = new PointF();
            for (int i = viewGroup.getChildCount() - 1; i >= 0; i--) {
                View child = viewGroup.getChildAt(i);
                if (isTransformedPointInView(viewGroup, child, x, y, localPoint)) {
                    View childResult = hitTestImpl(child, localPoint.x, localPoint.y, viewSelector, allowViewGroupResult);
                    if (childResult != null) {
                        return childResult;
                    }
                }
            }
        }
        if (!allowViewGroupResult) {
            viewGroup = null;
        }
        return viewGroup;
    }

    public static boolean pointInView(View view, float localX, float localY) {
        return localX >= 0.0f && localX < ((float) (view.getRight() - view.getLeft())) && localY >= 0.0f && localY < ((float) (view.getBottom() - view.getTop()));
    }

    public static boolean isTransformedPointInView(ViewGroup parent, View child, float x, float y, @Nullable PointF outLocalPoint) {
        Util.throwIfNull(parent);
        Util.throwIfNull(child);
        float localX = (((float) parent.getScrollX()) + x) - ((float) child.getLeft());
        float localY = (((float) parent.getScrollY()) + y) - ((float) child.getTop());
        boolean isInView = pointInView(child, localX, localY);
        if (isInView && outLocalPoint != null) {
            outLocalPoint.set(localX, localY);
        }
        return isInView;
    }

    @Nullable
    public static Activity tryGetActivity(View view) {
        if (view == null) {
            return null;
        }
        Activity activityFromContext = tryGetActivity(view.getContext());
        if (activityFromContext != null) {
            return activityFromContext;
        }
        ViewParent parent = view.getParent();
        return parent instanceof View ? tryGetActivity((View) parent) : null;
    }

    @Nullable
    private static Activity tryGetActivity(Context context) {
        while (context != null) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            if (!(context instanceof ContextWrapper)) {
                return null;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }
}
