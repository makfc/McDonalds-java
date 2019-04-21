package com.facebook.stetho.inspector.elements.android;

import android.view.View;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.common.android.DialogFragmentAccessor;
import com.facebook.stetho.common.android.FragmentCompat;
import com.facebook.stetho.inspector.elements.AttributeAccumulator;
import com.facebook.stetho.inspector.elements.ChainedDescriptor;
import com.facebook.stetho.inspector.elements.Descriptor;
import com.facebook.stetho.inspector.elements.Descriptor.Host;
import com.facebook.stetho.inspector.elements.DescriptorMap;
import com.facebook.stetho.inspector.elements.NodeType;
import com.facebook.stetho.inspector.elements.StyleAccumulator;
import javax.annotation.Nullable;

final class DialogFragmentDescriptor extends Descriptor implements ChainedDescriptor, HighlightableDescriptor {
    private final DialogFragmentAccessor mAccessor;
    private Descriptor mSuper;

    public static DescriptorMap register(DescriptorMap map) {
        maybeRegister(map, FragmentCompat.getSupportLibInstance());
        maybeRegister(map, FragmentCompat.getFrameworkInstance());
        return map;
    }

    private static void maybeRegister(DescriptorMap map, @Nullable FragmentCompat compat) {
        if (compat != null) {
            LogUtil.m7444d("Adding support for %s", compat.getDialogFragmentClass());
            map.register(dialogFragmentClass, new DialogFragmentDescriptor(compat));
        }
    }

    private DialogFragmentDescriptor(FragmentCompat compat) {
        this.mAccessor = compat.forDialogFragment();
    }

    public void setSuper(Descriptor superDescriptor) {
        Util.throwIfNull(superDescriptor);
        if (superDescriptor == this.mSuper) {
            return;
        }
        if (this.mSuper != null) {
            throw new IllegalStateException();
        }
        this.mSuper = superDescriptor;
    }

    public void hook(Object element) {
        this.mSuper.hook(element);
    }

    public void unhook(Object element) {
        this.mSuper.unhook(element);
    }

    public NodeType getNodeType(Object element) {
        return this.mSuper.getNodeType(element);
    }

    public String getNodeName(Object element) {
        return this.mSuper.getNodeName(element);
    }

    public String getLocalName(Object element) {
        return this.mSuper.getLocalName(element);
    }

    @Nullable
    public String getNodeValue(Object element) {
        return this.mSuper.getNodeValue(element);
    }

    public void getChildren(Object element, Accumulator<Object> children) {
        children.store(this.mAccessor.getDialog(element));
    }

    public void getAttributes(Object element, AttributeAccumulator attributes) {
        this.mSuper.getAttributes(element, attributes);
    }

    public void setAttributesAsText(Object element, String text) {
        this.mSuper.setAttributesAsText(element, text);
    }

    @Nullable
    public View getViewForHighlighting(Object element) {
        Host host = getHost();
        if (!(host instanceof AndroidDescriptorHost)) {
            return null;
        }
        return ((AndroidDescriptorHost) host).getHighlightingView(this.mAccessor.getDialog(element));
    }

    public void getStyles(Object element, StyleAccumulator styles) {
    }
}
