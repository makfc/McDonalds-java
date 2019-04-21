package com.bumptech.glide.load.resource.transcode;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;

public class BitmapToGlideDrawableTranscoder implements ResourceTranscoder<Bitmap, GlideDrawable> {
    private final GlideBitmapDrawableTranscoder glideBitmapDrawableTranscoder;

    public Resource<GlideDrawable> transcode(Resource<Bitmap> toTranscode) {
        return this.glideBitmapDrawableTranscoder.transcode(toTranscode);
    }

    public String getId() {
        return this.glideBitmapDrawableTranscoder.getId();
    }
}
