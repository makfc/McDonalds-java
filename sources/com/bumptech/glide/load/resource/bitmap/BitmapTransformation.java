package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.support.p000v4.widget.ExploreByTouchHelper;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Util;

public abstract class BitmapTransformation implements Transformation<Bitmap> {
    private BitmapPool bitmapPool;

    public abstract Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i2);

    public BitmapTransformation(BitmapPool bitmapPool) {
        this.bitmapPool = bitmapPool;
    }

    public final Resource<Bitmap> transform(Resource<Bitmap> resource, int outWidth, int outHeight) {
        if (Util.isValidDimensions(outWidth, outHeight)) {
            int targetWidth;
            int targetHeight;
            Bitmap toTransform = (Bitmap) resource.get();
            if (outWidth == ExploreByTouchHelper.INVALID_ID) {
                targetWidth = toTransform.getWidth();
            } else {
                targetWidth = outWidth;
            }
            if (outHeight == ExploreByTouchHelper.INVALID_ID) {
                targetHeight = toTransform.getHeight();
            } else {
                targetHeight = outHeight;
            }
            Bitmap transformed = transform(this.bitmapPool, toTransform, targetWidth, targetHeight);
            if (toTransform.equals(transformed)) {
                return resource;
            }
            return BitmapResource.obtain(transformed, this.bitmapPool);
        }
        throw new IllegalArgumentException("Cannot apply transformation on width: " + outWidth + " or height: " + outHeight + " less than or equal to zero and not Target.SIZE_ORIGINAL");
    }
}
