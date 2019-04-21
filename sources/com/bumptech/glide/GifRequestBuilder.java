package com.bumptech.glide;

import android.graphics.Bitmap;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawableTransformation;
import java.io.InputStream;

public class GifRequestBuilder<ModelType> extends GenericRequestBuilder<ModelType, InputStream, GifDrawable, GifDrawable> implements BitmapOptions, DrawableOptions {
    public GifRequestBuilder<ModelType> decoder(ResourceDecoder<InputStream, GifDrawable> decoder) {
        super.decoder(decoder);
        return this;
    }

    public GifRequestBuilder<ModelType> centerCrop() {
        return transformFrame(this.glide.getBitmapCenterCrop());
    }

    public GifRequestBuilder<ModelType> fitCenter() {
        return transformFrame(this.glide.getBitmapFitCenter());
    }

    public GifRequestBuilder<ModelType> transformFrame(BitmapTransformation... bitmapTransformations) {
        return transform(toGifTransformations(bitmapTransformations));
    }

    private GifDrawableTransformation[] toGifTransformations(Transformation<Bitmap>[] bitmapTransformations) {
        GifDrawableTransformation[] transformations = new GifDrawableTransformation[bitmapTransformations.length];
        for (int i = 0; i < bitmapTransformations.length; i++) {
            transformations[i] = new GifDrawableTransformation(bitmapTransformations[i], this.glide.getBitmapPool());
        }
        return transformations;
    }

    public GifRequestBuilder<ModelType> transform(Transformation<GifDrawable>... transformations) {
        super.transform(transformations);
        return this;
    }

    public GifRequestBuilder<ModelType> placeholder(int resourceId) {
        super.placeholder(resourceId);
        return this;
    }

    public GifRequestBuilder<ModelType> error(int resourceId) {
        super.error(resourceId);
        return this;
    }

    public GifRequestBuilder<ModelType> skipMemoryCache(boolean skip) {
        super.skipMemoryCache(skip);
        return this;
    }

    public GifRequestBuilder<ModelType> diskCacheStrategy(DiskCacheStrategy strategy) {
        super.diskCacheStrategy(strategy);
        return this;
    }

    public GifRequestBuilder<ModelType> override(int width, int height) {
        super.override(width, height);
        return this;
    }

    public GifRequestBuilder<ModelType> sourceEncoder(Encoder<InputStream> sourceEncoder) {
        super.sourceEncoder(sourceEncoder);
        return this;
    }

    public GifRequestBuilder<ModelType> signature(Key signature) {
        super.signature(signature);
        return this;
    }

    public GifRequestBuilder<ModelType> load(ModelType model) {
        super.load(model);
        return this;
    }

    public GifRequestBuilder<ModelType> clone() {
        return (GifRequestBuilder) super.clone();
    }

    /* Access modifiers changed, original: 0000 */
    public void applyFitCenter() {
        fitCenter();
    }

    /* Access modifiers changed, original: 0000 */
    public void applyCenterCrop() {
        centerCrop();
    }
}
