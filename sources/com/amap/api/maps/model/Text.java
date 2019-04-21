package com.amap.api.maps.model;

import android.graphics.Typeface;
import android.os.RemoteException;
import com.autonavi.amap.mapcore.interfaces.ITextDelegate;

public final class Text {
    public static final int ALIGN_BOTTOM = 16;
    public static final int ALIGN_CENTER_HORIZONTAL = 4;
    public static final int ALIGN_CENTER_VERTICAL = 32;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_RIGHT = 2;
    public static final int ALIGN_TOP = 8;
    /* renamed from: a */
    private ITextDelegate f3260a;

    public Text(ITextDelegate iTextDelegate) {
        this.f3260a = iTextDelegate;
    }

    public void remove() {
        try {
            this.f3260a.remove();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void destroy() {
        try {
            if (this.f3260a != null) {
                this.f3260a.destroy();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public String getId() {
        try {
            return this.f3260a.getId();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setPosition(LatLng latLng) {
        try {
            this.f3260a.setPosition(latLng);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public LatLng getPosition() {
        try {
            return this.f3260a.getPosition();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setText(String str) {
        try {
            this.f3260a.setText(str);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getText() {
        try {
            return this.f3260a.getText();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setBackgroundColor(int i) {
        try {
            this.f3260a.setBackgroundColor(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getBackgroundColor() {
        try {
            return this.f3260a.getBackgroundColor();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setFontColor(int i) {
        try {
            this.f3260a.setFontColor(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getFontColor() {
        try {
            return this.f3260a.getFontColor();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setFontSize(int i) {
        try {
            this.f3260a.setFontSize(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getFontSize() {
        try {
            return this.f3260a.getFontSize();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setTypeface(Typeface typeface) {
        try {
            this.f3260a.setTypeface(typeface);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public Typeface getTypeface() {
        try {
            return this.f3260a.getTypeface();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setAlign(int i, int i2) {
        try {
            this.f3260a.setAlign(i, i2);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getAlignX() {
        try {
            return this.f3260a.getAlignX();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getAlignY() {
        try {
            return this.f3260a.getAlignY();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setVisible(boolean z) {
        try {
            this.f3260a.setVisible(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isVisible() {
        try {
            return this.f3260a.isVisible();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean equals(Object obj) {
        try {
            if (obj instanceof Text) {
                return this.f3260a.equalsRemote(((Text) obj).f3260a);
            }
            return false;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int hashCode() {
        return this.f3260a.hashCodeRemote();
    }

    public void setObject(Object obj) {
        this.f3260a.setObject(obj);
    }

    public Object getObject() {
        return this.f3260a.getObject();
    }

    public void setRotate(float f) {
        try {
            this.f3260a.setRotateAngle(f);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public float getRotate() {
        return this.f3260a.getRotateAngle();
    }

    public void setZIndex(float f) {
        this.f3260a.setZIndex(f);
    }

    public float getZIndex() {
        return this.f3260a.getZIndex();
    }
}
