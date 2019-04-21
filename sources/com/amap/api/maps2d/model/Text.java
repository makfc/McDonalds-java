package com.amap.api.maps2d.model;

import android.graphics.Typeface;
import com.amap.api.mapcore2d.ITextDelegate;

public final class Text {
    public static final int ALIGN_BOTTOM = 5;
    public static final int ALIGN_CENTER_HORIZONTAL = 3;
    public static final int ALIGN_CENTER_VERTICAL = 6;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_RIGHT = 2;
    public static final int ALIGN_TOP = 4;
    /* renamed from: a */
    private ITextDelegate f3462a;

    public Text(ITextDelegate iTextDelegate) {
        this.f3462a = iTextDelegate;
    }

    public void setPosition(LatLng latLng) {
        this.f3462a.mo9612b(latLng);
    }

    public LatLng getPosition() {
        return this.f3462a.mo9615t();
    }

    public float getZIndex() {
        return this.f3462a.mo9613r();
    }

    public void setZIndex(float f) {
        this.f3462a.mo9610b(f);
    }

    public void remove() {
        this.f3462a.mo9699i();
    }

    public void setObject(Object obj) {
        this.f3462a.mo9609a(obj);
    }

    public Object getObject() {
        return this.f3462a.mo9616u();
    }

    public void setText(String str) {
        this.f3462a.mo9688a(str);
    }

    public String getText() {
        return this.f3462a.mo9682a();
    }

    public void setFontSize(int i) {
        this.f3462a.mo9684a(i);
    }

    public int getFontSize() {
        return this.f3462a.mo9690b();
    }

    public void setFontColor(int i) {
        this.f3462a.mo9692c(i);
    }

    public int getFontColor() {
        return this.f3462a.mo9691c();
    }

    public void setRotate(float f) {
        this.f3462a.mo9683a(f);
    }

    public float getRotate() {
        return this.f3462a.mo9693d();
    }

    public void setBackgroundColor(int i) {
        this.f3462a.mo9694d(i);
    }

    public int getBackgroundColor() {
        return this.f3462a.mo9695e();
    }

    public void setTypeface(Typeface typeface) {
        this.f3462a.mo9687a(typeface);
    }

    public Typeface getTypeface() {
        return this.f3462a.mo9696f();
    }

    public int getAlignX() {
        return this.f3462a.mo9697g();
    }

    public int getAlignY() {
        return this.f3462a.mo9698h();
    }

    public void setAlign(int i, int i2) {
        this.f3462a.mo9685a(i, i2);
    }

    public void setVisible(boolean z) {
        this.f3462a.mo9689a(z);
    }

    public boolean isVisible() {
        return this.f3462a.mo9614s();
    }
}
