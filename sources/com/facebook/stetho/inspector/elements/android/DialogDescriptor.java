package com.facebook.stetho.inspector.elements.android;

import android.app.Dialog;
import android.view.View;
import android.view.Window;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import com.facebook.stetho.inspector.elements.Descriptor.Host;
import javax.annotation.Nullable;

final class DialogDescriptor extends AbstractChainedDescriptor<Dialog> implements HighlightableDescriptor {
    DialogDescriptor() {
    }

    /* Access modifiers changed, original: protected */
    public void onGetChildren(Dialog element, Accumulator<Object> children) {
        Window window = element.getWindow();
        if (window != null) {
            children.store(window);
        }
    }

    @Nullable
    public View getViewForHighlighting(Object element) {
        Host host = getHost();
        if (host instanceof AndroidDescriptorHost) {
            return ((AndroidDescriptorHost) host).getHighlightingView(((Dialog) element).getWindow());
        }
        return null;
    }
}
