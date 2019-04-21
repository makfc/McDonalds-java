package com.facebook.stetho.inspector.elements.android;

import android.view.View;
import javax.annotation.Nullable;

interface HighlightableDescriptor {
    @Nullable
    View getViewForHighlighting(Object obj);
}
