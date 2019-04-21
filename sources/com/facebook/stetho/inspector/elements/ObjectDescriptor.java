package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;

public final class ObjectDescriptor extends Descriptor {
    public void hook(Object element) {
    }

    public void unhook(Object element) {
    }

    public NodeType getNodeType(Object element) {
        return NodeType.ELEMENT_NODE;
    }

    public String getNodeName(Object element) {
        return element.getClass().getName();
    }

    public String getLocalName(Object element) {
        return getNodeName(element);
    }

    public String getNodeValue(Object element) {
        return null;
    }

    public void getChildren(Object element, Accumulator<Object> accumulator) {
    }

    public void getAttributes(Object element, AttributeAccumulator attributes) {
    }

    public void setAttributesAsText(Object element, String text) {
    }

    public void getStyles(Object element, StyleAccumulator accumulator) {
    }
}
