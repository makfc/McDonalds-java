package com.google.android.gms.internal;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class zzabu {
    public static final Integer zzbwi = Integer.valueOf(0);
    public static final Integer zzbwj = Integer.valueOf(1);
    private final Context mContext;
    private final ExecutorService zzbqx;

    public zzabu(Context context) {
        this(context, Executors.newSingleThreadExecutor());
    }

    zzabu(Context context, ExecutorService executorService) {
        this.mContext = context;
        this.zzbqx = executorService;
    }
}
