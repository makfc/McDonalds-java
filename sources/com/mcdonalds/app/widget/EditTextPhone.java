package com.mcdonalds.app.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;
import com.ensighten.Ensighten;
import com.mcdonalds.sdk.services.log.MCDLog;

public class EditTextPhone extends EditText {
    private String mCountryCode = "";

    private class CustomInputConnection extends InputConnectionWrapper {
        public CustomInputConnection(InputConnection target, boolean mutable) {
            super(target, mutable);
        }

        public boolean sendKeyEvent(KeyEvent event) {
            Ensighten.evaluateEvent(this, "sendKeyEvent", new Object[]{event});
            if (event.getAction() == 0 && event.getKeyCode() == 67) {
                MCDLog.debug("Click Del on Soft Keyboard.");
            }
            return super.sendKeyEvent(event);
        }

        public boolean deleteSurroundingText(int beforeLength, int afterLength) {
            Ensighten.evaluateEvent(this, "deleteSurroundingText", new Object[]{new Integer(beforeLength), new Integer(afterLength)});
            if (beforeLength != 1 || afterLength != 0) {
                return super.deleteSurroundingText(beforeLength, afterLength);
            }
            if (sendKeyEvent(new KeyEvent(0, 67)) && sendKeyEvent(new KeyEvent(1, 67))) {
                return true;
            }
            return false;
        }
    }

    public EditTextPhone(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setNoSelectionMode();
    }

    public EditTextPhone(Context context, AttributeSet attrs) {
        super(context, attrs);
        setNoSelectionMode();
    }

    public EditTextPhone(Context context) {
        super(context);
        setNoSelectionMode();
    }

    public String getPhoneWithoutCountryCode() {
        Ensighten.evaluateEvent(this, "getPhoneWithoutCountryCode", null);
        String text = getText().toString();
        if (TextUtils.isEmpty(text)) {
            return "";
        }
        return text.replace(this.mCountryCode, "");
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        Ensighten.evaluateEvent(this, "onDraw", new Object[]{canvas});
        String text = getText().toString();
        if (!TextUtils.isEmpty(text)) {
            setSelection(text.length());
        }
        super.onDraw(canvas);
    }

    private void setNoSelectionMode() {
        Ensighten.evaluateEvent(this, "setNoSelectionMode", null);
        setLongClickable(false);
    }

    public String getCountryCode() {
        Ensighten.evaluateEvent(this, "getCountryCode", null);
        return this.mCountryCode;
    }

    public void setCountryCode(String mCountryCode) {
        Ensighten.evaluateEvent(this, "setCountryCode", new Object[]{mCountryCode});
        this.mCountryCode = mCountryCode;
    }

    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        Ensighten.evaluateEvent(this, "onCreateInputConnection", new Object[]{outAttrs});
        return new CustomInputConnection(super.onCreateInputConnection(outAttrs), true);
    }
}
