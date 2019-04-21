package com.facebook.stetho.inspector.elements.android;

import android.view.View;
import com.facebook.stetho.inspector.elements.Descriptor.Host;
import javax.annotation.Nullable;

interface AndroidDescriptorHost extends Host {
    @Nullable
    View getHighlightingView(@Nullable Object obj);
}
