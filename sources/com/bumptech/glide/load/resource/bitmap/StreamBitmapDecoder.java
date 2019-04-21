package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.io.InputStream;

public class StreamBitmapDecoder implements ResourceDecoder<InputStream, Bitmap> {
    private BitmapPool bitmapPool;
    private DecodeFormat decodeFormat;
    private final Downsampler downsampler;
    /* renamed from: id */
    private String f5581id;

    public StreamBitmapDecoder(BitmapPool bitmapPool, DecodeFormat decodeFormat) {
        this(Downsampler.AT_LEAST, bitmapPool, decodeFormat);
    }

    public StreamBitmapDecoder(Downsampler downsampler, BitmapPool bitmapPool, DecodeFormat decodeFormat) {
        this.downsampler = downsampler;
        this.bitmapPool = bitmapPool;
        this.decodeFormat = decodeFormat;
    }

    public Resource<Bitmap> decode(InputStream source, int width, int height) {
        return BitmapResource.obtain(this.downsampler.decode(source, this.bitmapPool, width, height, this.decodeFormat), this.bitmapPool);
    }

    public String getId() {
        if (this.f5581id == null) {
            this.f5581id = "StreamBitmapDecoder.com.bumptech.glide.load.resource.bitmap" + this.downsampler.getId() + this.decodeFormat.name();
        }
        return this.f5581id;
    }
}
