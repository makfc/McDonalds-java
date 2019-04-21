package com.facebook.stetho.inspector.elements.android;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.StringUtil;
import com.facebook.stetho.common.android.FragmentCompat;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import com.facebook.stetho.inspector.elements.Descriptor.Host;
import java.util.List;
import javax.annotation.Nullable;

final class ActivityDescriptor extends AbstractChainedDescriptor<Activity> implements HighlightableDescriptor {
    ActivityDescriptor() {
    }

    /* Access modifiers changed, original: protected */
    public String onGetNodeName(Activity element) {
        return StringUtil.removePrefix(element.getClass().getName(), "android.app.");
    }

    /* Access modifiers changed, original: protected */
    public void onGetChildren(Activity element, Accumulator<Object> children) {
        getDialogFragments(FragmentCompat.getSupportLibInstance(), element, children);
        getDialogFragments(FragmentCompat.getFrameworkInstance(), element, children);
        Window window = element.getWindow();
        if (window != null) {
            children.store(window);
        }
    }

    public View getViewForHighlighting(Object element) {
        Host host = getHost();
        if (!(host instanceof AndroidDescriptorHost)) {
            return null;
        }
        return ((AndroidDescriptorHost) host).getHighlightingView(((Activity) element).getWindow());
    }

    private static void getDialogFragments(@Nullable FragmentCompat compat, Activity activity, Accumulator<Object> accumulator) {
        if (compat != null && compat.getFragmentActivityClass().isInstance(activity)) {
            Object fragmentManager = compat.forFragmentActivity().getFragmentManager(activity);
            if (fragmentManager != null) {
                List<Object> addedFragments = compat.forFragmentManager().getAddedFragments(fragmentManager);
                if (addedFragments != null) {
                    int N = addedFragments.size();
                    for (int i = 0; i < N; i++) {
                        Object fragment = addedFragments.get(i);
                        if (compat.getDialogFragmentClass().isInstance(fragment)) {
                            accumulator.store(fragment);
                        }
                    }
                }
            }
        }
    }
}
