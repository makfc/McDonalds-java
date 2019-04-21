package com.bumptech.glide.request.target;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;

public final class PreloadTarget<Z> extends SimpleTarget<Z> {
    public void onResourceReady(Z z, GlideAnimation<? super Z> glideAnimation) {
        Glide.clear(this);
    }
}
