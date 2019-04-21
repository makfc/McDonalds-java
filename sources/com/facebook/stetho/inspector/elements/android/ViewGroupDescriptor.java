package com.facebook.stetho.inspector.elements.android;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.android.FragmentCompatUtil;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

final class ViewGroupDescriptor extends AbstractChainedDescriptor<ViewGroup> {
    private final Map<View, Object> mViewToElementMap = Collections.synchronizedMap(new WeakHashMap());

    /* Access modifiers changed, original: protected */
    public void onGetChildren(ViewGroup element, Accumulator<Object> children) {
        int N = element.getChildCount();
        for (int i = 0; i < N; i++) {
            View childView = element.getChildAt(i);
            if (isChildVisible(childView)) {
                children.store(getElementForView(element, childView));
            }
        }
    }

    private boolean isChildVisible(View child) {
        return !(child instanceof DocumentHiddenView);
    }

    private Object getElementForView(ViewGroup parentView, View childView) {
        Object element = this.mViewToElementMap.get(childView);
        if (element != null) {
            if (childView.getParent() == parentView) {
                return element;
            }
            this.mViewToElementMap.remove(childView);
        }
        Object fragment = FragmentCompatUtil.findFragmentForView(childView);
        if (fragment == null || FragmentCompatUtil.isDialogFragment(fragment)) {
            this.mViewToElementMap.put(childView, childView);
            return childView;
        }
        this.mViewToElementMap.put(childView, fragment);
        return fragment;
    }
}
