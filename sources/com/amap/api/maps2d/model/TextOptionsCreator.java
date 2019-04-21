package com.amap.api.maps2d.model;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.mapcore2d.C0955ck;

public class TextOptionsCreator implements Creator<TextOptions> {
    public TextOptions createFromParcel(Parcel parcel) {
        boolean z = true;
        TextOptions textOptions = new TextOptions();
        Bundle readBundle = parcel.readBundle();
        textOptions.position(new LatLng(readBundle.getDouble("lat"), readBundle.getDouble("lng")));
        textOptions.text(parcel.readString());
        textOptions.typeface(Typeface.defaultFromStyle(parcel.readInt()));
        textOptions.rotate(parcel.readFloat());
        textOptions.align(parcel.readInt(), parcel.readInt());
        textOptions.backgroundColor(parcel.readInt());
        textOptions.fontColor(parcel.readInt());
        textOptions.fontSize(parcel.readInt());
        textOptions.zIndex((float) parcel.readInt());
        if (parcel.readByte() != (byte) 1) {
            z = false;
        }
        textOptions.visible(z);
        try {
            Parcelable parcelable = parcel.readBundle().getParcelable("obj");
            if (parcelable != null) {
                textOptions.setObject(parcelable);
            }
        } catch (Throwable th) {
            C0955ck.m3888a(th, "TextOptionsCreator", "createFromParcel");
        }
        return textOptions;
    }

    public TextOptions[] newArray(int i) {
        return new TextOptions[i];
    }
}
