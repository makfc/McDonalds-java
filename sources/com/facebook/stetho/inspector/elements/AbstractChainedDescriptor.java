package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.Util;
import javax.annotation.Nullable;

public abstract class AbstractChainedDescriptor<E> extends Descriptor implements ChainedDescriptor {
    private Descriptor mSuper;

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

    /* Access modifiers changed, original: final */
    public final Descriptor getSuper() {
        return this.mSuper;
    }

    public final void hook(Object element) {
        verifyThreadAccess();
        this.mSuper.hook(element);
        onHook(element);
    }

    /* Access modifiers changed, original: protected */
    public void onHook(E e) {
    }

    public final void unhook(Object element) {
        verifyThreadAccess();
        onUnhook(element);
        this.mSuper.unhook(element);
    }

    /* Access modifiers changed, original: protected */
    public void onUnhook(E e) {
    }

    public final NodeType getNodeType(Object element) {
        return onGetNodeType(element);
    }

    /* Access modifiers changed, original: protected */
    public NodeType onGetNodeType(E element) {
        return this.mSuper.getNodeType(element);
    }

    public final String getNodeName(Object element) {
        return onGetNodeName(element);
    }

    /* Access modifiers changed, original: protected */
    public String onGetNodeName(E element) {
        return this.mSuper.getNodeName(element);
    }

    public final String getLocalName(Object element) {
        return onGetLocalName(element);
    }

    /* Access modifiers changed, original: protected */
    public String onGetLocalName(E element) {
        return this.mSuper.getLocalName(element);
    }

    public final String getNodeValue(Object element) {
        return onGetNodeValue(element);
    }

    @Nullable
    public String onGetNodeValue(E element) {
        return this.mSuper.getNodeValue(element);
    }

    public final void getChildren(Object element, Accumulator<Object> children) {
        this.mSuper.getChildren(element, children);
        onGetChildren(element, children);
    }

    /* Access modifiers changed, original: protected */
    public void onGetChildren(E e, Accumulator<Object> accumulator) {
    }

    public final void getAttributes(Object element, AttributeAccumulator attributes) {
        this.mSuper.getAttributes(element, attributes);
        onGetAttributes(element, attributes);
    }

    /* Access modifiers changed, original: protected */
    public void onGetAttributes(E e, AttributeAccumulator attributes) {
    }

    public final void setAttributesAsText(Object element, String text) {
        onSetAttributesAsText(element, text);
    }

    /* Access modifiers changed, original: protected */
    public void onSetAttributesAsText(E element, String text) {
        this.mSuper.setAttributesAsText(element, text);
    }

    public final void getStyles(Object element, StyleAccumulator accumulator) {
        this.mSuper.getStyles(element, accumulator);
        onGetStyles(element, accumulator);
    }

    /* Access modifiers changed, original: protected */
    public void onGetStyles(E e, StyleAccumulator accumulator) {
    }
}
