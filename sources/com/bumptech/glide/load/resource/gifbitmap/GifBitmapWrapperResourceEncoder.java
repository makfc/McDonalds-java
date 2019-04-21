package com.bumptech.glide.load.resource.gifbitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import java.io.OutputStream;

public class GifBitmapWrapperResourceEncoder implements ResourceEncoder<GifBitmapWrapper> {
    private final ResourceEncoder<Bitmap> bitmapEncoder;
    private final ResourceEncoder<GifDrawable> gifEncoder;
    /* renamed from: id */
    private String f5583id;

    public GifBitmapWrapperResourceEncoder(ResourceEncoder<Bitmap> bitmapEncoder, ResourceEncoder<GifDrawable> gifEncoder) {
        this.bitmapEncoder = bitmapEncoder;
        this.gifEncoder = gifEncoder;
    }

    public boolean encode(Resource<GifBitmapWrapper> resource, OutputStream os) {
        GifBitmapWrapper gifBitmap = (GifBitmapWrapper) resource.get();
        Resource<Bitmap> bitmapResource = gifBitmap.getBitmapResource();
        if (bitmapResource != null) {
            return this.bitmapEncoder.encode(bitmapResource, os);
        }
        return this.gifEncoder.encode(gifBitmap.getGifResource(), os);
    }

    public String getId() {
        if (this.f5583id == null) {
            this.f5583id = this.bitmapEncoder.getId() + this.gifEncoder.getId();
        }
        return this.f5583id;
    }
}
