package com.facebook.stetho.common.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.view.View;
import javax.annotation.Nullable;

@TargetApi(11)
final class FragmentCompatFramework extends FragmentCompat<Fragment, DialogFragment, FragmentManager, Activity> {
    private static final DialogFragmentAccessorFramework sDialogFragmentAccessor = new DialogFragmentAccessorFramework(sFragmentAccessor);
    private static final FragmentAccessorFrameworkHoneycomb sFragmentAccessor;
    private static final FragmentActivityAccessorFramework sFragmentActivityAccessor = new FragmentActivityAccessorFramework();
    private static final FragmentManagerAccessorViaReflection<FragmentManager, Fragment> sFragmentManagerAccessor = new FragmentManagerAccessorViaReflection();

    private static class DialogFragmentAccessorFramework implements DialogFragmentAccessor<DialogFragment, Fragment, FragmentManager> {
        private final FragmentAccessor<Fragment, FragmentManager> mFragmentAccessor;

        public DialogFragmentAccessorFramework(FragmentAccessor<Fragment, FragmentManager> fragmentAccessor) {
            this.mFragmentAccessor = fragmentAccessor;
        }

        public Dialog getDialog(DialogFragment dialogFragment) {
            return dialogFragment.getDialog();
        }

        @Nullable
        public FragmentManager getFragmentManager(Fragment fragment) {
            return (FragmentManager) this.mFragmentAccessor.getFragmentManager(fragment);
        }

        public Resources getResources(Fragment fragment) {
            return this.mFragmentAccessor.getResources(fragment);
        }

        public int getId(Fragment fragment) {
            return this.mFragmentAccessor.getId(fragment);
        }

        @Nullable
        public String getTag(Fragment fragment) {
            return this.mFragmentAccessor.getTag(fragment);
        }

        @Nullable
        public View getView(Fragment fragment) {
            return this.mFragmentAccessor.getView(fragment);
        }

        @Nullable
        public FragmentManager getChildFragmentManager(Fragment fragment) {
            return (FragmentManager) this.mFragmentAccessor.getChildFragmentManager(fragment);
        }
    }

    private static class FragmentAccessorFrameworkHoneycomb implements FragmentAccessor<Fragment, FragmentManager> {
        private FragmentAccessorFrameworkHoneycomb() {
        }

        @Nullable
        public FragmentManager getFragmentManager(Fragment fragment) {
            return fragment.getFragmentManager();
        }

        public Resources getResources(Fragment fragment) {
            return fragment.getResources();
        }

        public int getId(Fragment fragment) {
            return fragment.getId();
        }

        @Nullable
        public String getTag(Fragment fragment) {
            return fragment.getTag();
        }

        @Nullable
        public View getView(Fragment fragment) {
            return fragment.getView();
        }

        @Nullable
        public FragmentManager getChildFragmentManager(Fragment fragment) {
            return null;
        }
    }

    @TargetApi(17)
    private static class FragmentAccessorFrameworkJellyBean extends FragmentAccessorFrameworkHoneycomb {
        private FragmentAccessorFrameworkJellyBean() {
            super();
        }

        @Nullable
        public FragmentManager getChildFragmentManager(Fragment fragment) {
            return fragment.getChildFragmentManager();
        }
    }

    private static class FragmentActivityAccessorFramework implements FragmentActivityAccessor<Activity, FragmentManager> {
        private FragmentActivityAccessorFramework() {
        }

        @Nullable
        public FragmentManager getFragmentManager(Activity activity) {
            return activity.getFragmentManager();
        }
    }

    FragmentCompatFramework() {
    }

    static {
        if (VERSION.SDK_INT >= 17) {
            sFragmentAccessor = new FragmentAccessorFrameworkJellyBean();
        } else {
            sFragmentAccessor = new FragmentAccessorFrameworkHoneycomb();
        }
    }

    public Class<Fragment> getFragmentClass() {
        return Fragment.class;
    }

    public Class<DialogFragment> getDialogFragmentClass() {
        return DialogFragment.class;
    }

    public Class<Activity> getFragmentActivityClass() {
        return Activity.class;
    }

    public FragmentAccessorFrameworkHoneycomb forFragment() {
        return sFragmentAccessor;
    }

    public DialogFragmentAccessorFramework forDialogFragment() {
        return sDialogFragmentAccessor;
    }

    public FragmentManagerAccessorViaReflection<FragmentManager, Fragment> forFragmentManager() {
        return sFragmentManagerAccessor;
    }

    public FragmentActivityAccessorFramework forFragmentActivity() {
        return sFragmentActivityAccessor;
    }
}
