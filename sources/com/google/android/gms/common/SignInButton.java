package com.google.android.gms.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import com.google.android.gms.C2063R;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzae;
import com.google.android.gms.common.internal.zzaf;
import com.google.android.gms.dynamic.zzg.zza;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class SignInButton extends FrameLayout implements OnClickListener {
    private int mColor;
    private int mSize;
    private Scope[] zzakD;
    private View zzakE;
    private OnClickListener zzakF;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ButtonSize {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ColorScheme {
    }

    public SignInButton(Context context) {
        this(context, null);
    }

    public SignInButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SignInButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzakF = null;
        zza(context, attributeSet);
        setStyle(this.mSize, this.mColor, this.zzakD);
    }

    private static Button zza(Context context, int i, int i2, Scope[] scopeArr) {
        zzaf zzaf = new zzaf(context);
        zzaf.zza(context.getResources(), i, i2, scopeArr);
        return zzaf;
    }

    private void zza(Context context, AttributeSet attributeSet) {
        int i = 0;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C2063R.styleable.SignInButton, 0, 0);
        try {
            this.mSize = obtainStyledAttributes.getInt(C2063R.styleable.SignInButton_buttonSize, 0);
            this.mColor = obtainStyledAttributes.getInt(C2063R.styleable.SignInButton_colorScheme, 2);
            String string = obtainStyledAttributes.getString(C2063R.styleable.SignInButton_scopeUris);
            if (string == null) {
                this.zzakD = null;
            } else {
                String[] split = string.trim().split("\\s+");
                this.zzakD = new Scope[split.length];
                while (i < split.length) {
                    this.zzakD[i] = new Scope(split[i].toString());
                    i++;
                }
            }
            obtainStyledAttributes.recycle();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
        }
    }

    private void zzar(Context context) {
        if (this.zzakE != null) {
            removeView(this.zzakE);
        }
        try {
            this.zzakE = zzae.zzb(context, this.mSize, this.mColor, this.zzakD);
        } catch (zza e) {
            Log.w("SignInButton", "Sign in button not found, using placeholder instead");
            this.zzakE = zza(context, this.mSize, this.mColor, this.zzakD);
        }
        addView(this.zzakE);
        this.zzakE.setEnabled(isEnabled());
        this.zzakE.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (this.zzakF != null && view == this.zzakE) {
            this.zzakF.onClick(this);
        }
    }

    public void setColorScheme(int i) {
        setStyle(this.mSize, i, this.zzakD);
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.zzakE.setEnabled(z);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.zzakF = onClickListener;
        if (this.zzakE != null) {
            this.zzakE.setOnClickListener(this);
        }
    }

    public void setScopes(Scope[] scopeArr) {
        setStyle(this.mSize, this.mColor, scopeArr);
    }

    public void setSize(int i) {
        setStyle(i, this.mColor, this.zzakD);
    }

    public void setStyle(int i, int i2) {
        setStyle(i, i2, this.zzakD);
    }

    public void setStyle(int i, int i2, Scope[] scopeArr) {
        this.mSize = i;
        this.mColor = i2;
        this.zzakD = scopeArr;
        zzar(getContext());
    }
}
