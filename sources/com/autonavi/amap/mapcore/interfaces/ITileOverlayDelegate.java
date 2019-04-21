package com.autonavi.amap.mapcore.interfaces;

import javax.microedition.khronos.opengles.GL10;

public interface ITileOverlayDelegate {
    void clearTileCache();

    void drawTiles(GL10 gl10);

    boolean equalsRemote(ITileOverlayDelegate iTileOverlayDelegate);

    String getId();

    float getZIndex();

    int hashCodeRemote();

    boolean isVisible();

    void onFling(boolean z);

    void onPause();

    void onResume();

    void reLoadTexture();

    void refresh(boolean z);

    void remove();

    void setVisible(boolean z);

    void setZIndex(float f);
}
