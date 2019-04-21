package com.google.android.gms.plus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import com.google.android.gms.common.internal.zzaj;
import com.google.android.gms.plus.internal.zzg;

public final class PlusOneButton extends FrameLayout {
    private int mSize;
    private String zzE;
    private View zzblo;
    private int zzblp;
    private int zzblq;
    private OnPlusOneClickListener zzblr;

    public interface OnPlusOneClickListener {
        void onPlusOneClick(Intent intent);
    }

    protected class DefaultOnPlusOneClickListener implements OnClickListener, OnPlusOneClickListener {
        private final OnPlusOneClickListener zzbls;

        public DefaultOnPlusOneClickListener(OnPlusOneClickListener onPlusOneClickListener) {
            this.zzbls = onPlusOneClickListener;
        }

        public void onClick(View view) {
            Intent intent = (Intent) PlusOneButton.this.zzblo.getTag();
            if (this.zzbls != null) {
                this.zzbls.onPlusOneClick(intent);
            } else {
                onPlusOneClick(intent);
            }
        }

        public void onPlusOneClick(Intent intent) {
            Context context = PlusOneButton.this.getContext();
            if ((context instanceof Activity) && intent != null) {
                ((Activity) context).startActivityForResult(intent, PlusOneButton.this.zzblq);
            }
        }
    }

    public PlusOneButton(Context context) {
        this(context, null);
    }

    public PlusOneButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSize = getSize(context, attributeSet);
        this.zzblp = getAnnotation(context, attributeSet);
        this.zzblq = -1;
        zzar(getContext());
        if (isInEditMode()) {
        }
    }

    protected static int getAnnotation(Context context, AttributeSet attributeSet) {
        String zza = zzaj.zza("http://schemas.android.com/apk/lib/com.google.android.gms.plus", "annotation", context, attributeSet, true, false, "PlusOneButton");
        return "INLINE".equalsIgnoreCase(zza) ? 2 : !"NONE".equalsIgnoreCase(zza) ? 1 : 0;
    }

    protected static int getSize(Context context, AttributeSet attributeSet) {
        String zza = zzaj.zza("http://schemas.android.com/apk/lib/com.google.android.gms.plus", "size", context, attributeSet, true, false, "PlusOneButton");
        return "SMALL".equalsIgnoreCase(zza) ? 0 : "MEDIUM".equalsIgnoreCase(zza) ? 1 : "TALL".equalsIgnoreCase(zza) ? 2 : 3;
    }

    private void zzar(Context context) {
        if (this.zzblo != null) {
            removeView(this.zzblo);
        }
        this.zzblo = zzg.zza(context, this.mSize, this.zzblp, this.zzE, this.zzblq);
        setOnPlusOneClickListener(this.zzblr);
        addView(this.zzblo);
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.zzblo.layout(0, 0, i3 - i, i4 - i2);
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        View view = this.zzblo;
        measureChild(view, i, i2);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    public void setAnnotation(int i) {
        this.zzblp = i;
        zzar(getContext());
    }

    public void setIntent(Intent intent) {
        this.zzblo.setTag(intent);
    }

    public void setOnPlusOneClickListener(OnPlusOneClickListener onPlusOneClickListener) {
        this.zzblr = onPlusOneClickListener;
        this.zzblo.setOnClickListener(new DefaultOnPlusOneClickListener(onPlusOneClickListener));
    }

    public void setSize(int i) {
        this.mSize = i;
        zzar(getContext());
    }
}
