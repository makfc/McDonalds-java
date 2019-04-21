package android.support.p001v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.p001v7.app.AppCompatDelegate;
import java.lang.ref.WeakReference;

@RestrictTo
/* renamed from: android.support.v7.widget.VectorEnabledTintResources */
public class VectorEnabledTintResources extends Resources {
    private final WeakReference<Context> mContextRef;

    public static boolean shouldBeUsed() {
        return AppCompatDelegate.isCompatVectorFromResourcesEnabled() && VERSION.SDK_INT <= 20;
    }

    public VectorEnabledTintResources(@NonNull Context context, @NonNull Resources res) {
        super(res.getAssets(), res.getDisplayMetrics(), res.getConfiguration());
        this.mContextRef = new WeakReference(context);
    }

    public Drawable getDrawable(int id) throws NotFoundException {
        Context context = (Context) this.mContextRef.get();
        if (context != null) {
            return AppCompatDrawableManager.get().onDrawableLoadedFromResources(context, this, id);
        }
        return super.getDrawable(id);
    }

    /* Access modifiers changed, original: final */
    public final Drawable superGetDrawable(int id) {
        return super.getDrawable(id);
    }
}
