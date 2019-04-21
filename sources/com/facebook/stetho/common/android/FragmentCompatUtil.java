package com.facebook.stetho.common.android;

import android.app.Activity;
import android.view.View;
import java.util.List;
import javax.annotation.Nullable;

public final class FragmentCompatUtil {
    private FragmentCompatUtil() {
    }

    public static boolean isDialogFragment(Object fragment) {
        FragmentCompat supportLib = FragmentCompat.getSupportLibInstance();
        if (supportLib != null && supportLib.getDialogFragmentClass().isInstance(fragment)) {
            return true;
        }
        FragmentCompat framework = FragmentCompat.getFrameworkInstance();
        if (framework == null || !framework.getDialogFragmentClass().isInstance(fragment)) {
            return false;
        }
        return true;
    }

    @Nullable
    public static Object findFragmentForView(View view) {
        Activity activity = ViewUtil.tryGetActivity(view);
        if (activity == null) {
            return null;
        }
        return findFragmentForViewInActivity(activity, view);
    }

    @Nullable
    private static Object findFragmentForViewInActivity(Activity activity, View view) {
        Object fragment;
        FragmentCompat supportLib = FragmentCompat.getSupportLibInstance();
        if (supportLib != null && supportLib.getFragmentActivityClass().isInstance(activity)) {
            fragment = findFragmentForViewInActivity(supportLib, activity, view);
            if (fragment != null) {
                return fragment;
            }
        }
        FragmentCompat framework = FragmentCompat.getFrameworkInstance();
        if (framework != null) {
            fragment = findFragmentForViewInActivity(framework, activity, view);
            if (fragment != null) {
                return fragment;
            }
        }
        return null;
    }

    private static Object findFragmentForViewInActivity(FragmentCompat compat, Activity activity, View view) {
        Object fragmentManager = compat.forFragmentActivity().getFragmentManager(activity);
        if (fragmentManager != null) {
            return findFragmentForViewInFragmentManager(compat, fragmentManager, view);
        }
        return null;
    }

    @Nullable
    private static Object findFragmentForViewInFragmentManager(FragmentCompat compat, Object fragmentManager, View view) {
        List<?> fragments = compat.forFragmentManager().getAddedFragments(fragmentManager);
        if (fragments != null) {
            int N = fragments.size();
            for (int i = 0; i < N; i++) {
                Object result = findFragmentForViewInFragment(compat, fragments.get(i), view);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    @Nullable
    private static Object findFragmentForViewInFragment(FragmentCompat compat, Object fragment, View view) {
        FragmentAccessor accessor = compat.forFragment();
        if (accessor.getView(fragment) == view) {
            return fragment;
        }
        Object childFragmentManager = accessor.getChildFragmentManager(fragment);
        if (childFragmentManager != null) {
            return findFragmentForViewInFragmentManager(compat, childFragmentManager, view);
        }
        return null;
    }
}
