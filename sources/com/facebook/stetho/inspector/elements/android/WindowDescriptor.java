package com.facebook.stetho.inspector.elements.android;

import android.view.View;
import android.view.Window;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import javax.annotation.Nullable;

final class WindowDescriptor extends AbstractChainedDescriptor<Window> implements HighlightableDescriptor {
    WindowDescriptor() {
    }

    /* Access modifiers changed, original: protected */
    public void onGetChildren(Window element, Accumulator<Object> children) {
        View decorView = element.peekDecorView();
        if (decorView != null) {
            children.store(decorView);
        }
    }

    @Nullable
    public View getViewForHighlighting(Object element) {
        return ((Window) element).peekDecorView();
    }
}
