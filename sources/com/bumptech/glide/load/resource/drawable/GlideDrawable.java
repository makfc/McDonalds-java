package com.bumptech.glide.load.resource.drawable;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

public abstract class GlideDrawable extends Drawable implements Animatable {
    public abstract boolean isAnimated();

    public abstract void setLoopCount(int i);
}
