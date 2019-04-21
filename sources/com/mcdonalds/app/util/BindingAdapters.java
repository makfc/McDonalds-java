package com.mcdonalds.app.util;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;

public class BindingAdapters {
    @BindingAdapter
    public static void loadImageWifhError(ImageView view, String url) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.BindingAdapters", "loadImageWifhError", new Object[]{view, url});
        Glide.with(view.getContext()).load(url).placeholder((int) C2358R.C2359drawable.icon_meal_gray).error((int) C2358R.C2359drawable.icon_meal_gray).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(view);
    }

    @BindingAdapter
    public static void loadResource(ImageView imageView, int resourceId) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.BindingAdapters", "loadResource", new Object[]{imageView, new Integer(resourceId)});
        imageView.setImageResource(resourceId);
    }

    @BindingAdapter
    public static void booleanVisibility(View view, boolean visible) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.BindingAdapters", "booleanVisibility", new Object[]{view, new Boolean(visible)});
        if (visible) {
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
    }
}
