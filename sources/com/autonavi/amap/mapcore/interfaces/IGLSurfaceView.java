package com.autonavi.amap.mapcore.interfaces;

import android.opengl.GLSurfaceView.Renderer;

public interface IGLSurfaceView {
    int getHeight();

    int getWidth();

    boolean isEnabled();

    void queueEvent(Runnable runnable);

    void requestRender();

    void setBackgroundColor(int i);

    void setRenderMode(int i);

    void setRenderer(Renderer renderer);

    void setVisibility(int i);

    void setZOrderOnTop(boolean z);
}
