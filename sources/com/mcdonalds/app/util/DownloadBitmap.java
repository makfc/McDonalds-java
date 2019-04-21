package com.mcdonalds.app.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ensighten.Ensighten;

public class DownloadBitmap extends SimpleTarget<Bitmap> {
    private Context mContext;
    private ImageView mImageView;

    public DownloadBitmap(Context context, ImageView imageView) {
        this.mContext = context;
        this.mImageView = imageView;
    }

    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
        Ensighten.evaluateEvent(this, "onResourceReady", new Object[]{bitmap, glideAnimation});
        if (this.mContext != null) {
            if (!(this.mContext instanceof Activity) || !((Activity) this.mContext).isFinishing()) {
                this.mImageView.setImageBitmap(bitmap);
            }
        }
    }
}
