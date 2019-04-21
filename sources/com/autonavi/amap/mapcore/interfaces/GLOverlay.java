package com.autonavi.amap.mapcore.interfaces;

import javax.microedition.khronos.opengles.GL10;

public abstract class GLOverlay {
    private IAMapDelegate map;

    public abstract int getZIndex();

    public abstract void onDrawFrame(GL10 gl10);

    public void setMap(IAMapDelegate iAMapDelegate) {
        this.map = iAMapDelegate;
    }

    public void destroy() {
        if (this.map != null) {
            this.map.removeOverlay(this);
        }
    }
}
