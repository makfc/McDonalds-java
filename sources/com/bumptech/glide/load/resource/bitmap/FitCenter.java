package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;

public class FitCenter extends BitmapTransformation {
    public FitCenter(BitmapPool bitmapPool) {
        super(bitmapPool);
    }

    /* Access modifiers changed, original: protected */
    public Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return TransformationUtils.fitCenter(toTransform, pool, outWidth, outHeight);
    }

    public String getId() {
        return "FitCenter.com.bumptech.glide.load.resource.bitmap";
    }
}
