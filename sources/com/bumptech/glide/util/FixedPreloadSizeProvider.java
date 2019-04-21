package com.bumptech.glide.util;

import com.bumptech.glide.ListPreloader.PreloadSizeProvider;
import java.util.Arrays;

public class FixedPreloadSizeProvider<T> implements PreloadSizeProvider<T> {
    private final int[] size;

    public int[] getPreloadSize(T t, int adapterPosition, int itemPosition) {
        return Arrays.copyOf(this.size, this.size.length);
    }
}
