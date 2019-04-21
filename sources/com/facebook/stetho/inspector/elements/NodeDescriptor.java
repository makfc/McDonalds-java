package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.ThreadBound;
import javax.annotation.Nullable;

public interface NodeDescriptor extends ThreadBound {
    void getAttributes(Object obj, AttributeAccumulator attributeAccumulator);

    void getChildren(Object obj, Accumulator<Object> accumulator);

    String getLocalName(Object obj);

    String getNodeName(Object obj);

    NodeType getNodeType(Object obj);

    @Nullable
    String getNodeValue(Object obj);

    void getStyles(Object obj, StyleAccumulator styleAccumulator);

    void hook(Object obj);

    void setAttributesAsText(Object obj, String str);

    void unhook(Object obj);
}
