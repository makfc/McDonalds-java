package com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.TextView;
import com.ensighten.Ensighten;

/* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.AccessibleTextView */
public class AccessibleTextView extends TextView {
    public AccessibleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        Ensighten.evaluateEvent(this, "onInitializeAccessibilityEvent", new Object[]{event});
        super.onInitializeAccessibilityEvent(event);
        event.setClassName(Button.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        Ensighten.evaluateEvent(this, "onInitializeAccessibilityNodeInfo", new Object[]{info});
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(Button.class.getName());
    }
}
