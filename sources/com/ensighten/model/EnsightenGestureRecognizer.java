package com.ensighten.model;

import android.view.MotionEvent;

public interface EnsightenGestureRecognizer {
    void process(MotionEvent motionEvent);

    void reset();
}
