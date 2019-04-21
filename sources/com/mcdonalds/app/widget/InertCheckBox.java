package com.mcdonalds.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.CheckBox;
import com.ensighten.Ensighten;

public class InertCheckBox extends CheckBox {
    public InertCheckBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public InertCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InertCheckBox(Context context) {
        super(context);
    }

    public boolean onTouchEvent(MotionEvent event) {
        Ensighten.evaluateEvent(this, "onTouchEvent", new Object[]{event});
        return false;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Ensighten.evaluateEvent(this, "onKeyDown", new Object[]{new Integer(keyCode), event});
        return false;
    }

    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        Ensighten.evaluateEvent(this, "onKeyMultiple", new Object[]{new Integer(keyCode), new Integer(repeatCount), event});
        return false;
    }

    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        Ensighten.evaluateEvent(this, "onKeyPreIme", new Object[]{new Integer(keyCode), event});
        return false;
    }

    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
        Ensighten.evaluateEvent(this, "onKeyShortcut", new Object[]{new Integer(keyCode), event});
        return false;
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Ensighten.evaluateEvent(this, "onKeyUp", new Object[]{new Integer(keyCode), event});
        return false;
    }

    public boolean onTrackballEvent(MotionEvent event) {
        Ensighten.evaluateEvent(this, "onTrackballEvent", new Object[]{event});
        return false;
    }
}
