package com.facebook.stetho.inspector.elements.android;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import com.facebook.stetho.inspector.elements.AttributeAccumulator;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;

final class TextViewDescriptor extends AbstractChainedDescriptor<TextView> {
    private static final String TEXT_ATTRIBUTE_NAME = "text";
    private final Map<TextView, ElementContext> mElementToContextMap = Collections.synchronizedMap(new IdentityHashMap());

    private final class ElementContext implements TextWatcher {
        private TextView mElement;

        private ElementContext() {
        }

        public void hook(TextView element) {
            this.mElement = (TextView) Util.throwIfNull(element);
            this.mElement.addTextChangedListener(this);
        }

        public void unhook() {
            if (this.mElement != null) {
                this.mElement.removeTextChangedListener(this);
                this.mElement = null;
            }
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable s) {
            if (s.length() == 0) {
                TextViewDescriptor.this.getHost().onAttributeRemoved(this.mElement, "text");
            } else {
                TextViewDescriptor.this.getHost().onAttributeModified(this.mElement, "text", s.toString());
            }
        }
    }

    TextViewDescriptor() {
    }

    /* Access modifiers changed, original: protected */
    public void onHook(TextView element) {
        ElementContext context = new ElementContext();
        context.hook(element);
        this.mElementToContextMap.put(element, context);
    }

    /* Access modifiers changed, original: protected */
    public void onUnhook(TextView element) {
        ((ElementContext) this.mElementToContextMap.remove(element)).unhook();
    }

    /* Access modifiers changed, original: protected */
    public void onGetAttributes(TextView element, AttributeAccumulator attributes) {
        CharSequence text = element.getText();
        if (text.length() != 0) {
            attributes.store("text", text.toString());
        }
    }
}
