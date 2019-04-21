package com.mcdonalds.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;
import com.ensighten.Ensighten;

public class KeyboardListenerEditText extends EditText {
    private KeyboardDismissListener mKeyboardDismissListener;

    public interface KeyboardDismissListener {
        void onKeyboardDismiss();
    }

    public KeyboardListenerEditText(Context context) {
        super(context);
    }

    public KeyboardListenerEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyboardListenerEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        Ensighten.evaluateEvent(this, "onKeyPreIme", new Object[]{new Integer(keyCode), event});
        if (event.getKeyCode() == 4 && this.mKeyboardDismissListener != null) {
            this.mKeyboardDismissListener.onKeyboardDismiss();
        }
        return super.onKeyPreIme(keyCode, event);
    }

    public void setKeyboardDismissListener(KeyboardDismissListener keyboardDismissListener) {
        Ensighten.evaluateEvent(this, "setKeyboardDismissListener", new Object[]{keyboardDismissListener});
        this.mKeyboardDismissListener = keyboardDismissListener;
    }
}
