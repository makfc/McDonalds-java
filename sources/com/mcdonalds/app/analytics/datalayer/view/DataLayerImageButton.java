package com.mcdonalds.app.analytics.datalayer.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import com.ensighten.Ensighten;

@SuppressLint({"AppCompatCustomView"})
public class DataLayerImageButton extends ImageButton {
    public DataLayerImageButton(Context context) {
        super(context);
    }

    public DataLayerImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        DataLayerClickListener.setDataLayerTag((View) this, context, attrs);
    }

    public DataLayerImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnClickListener(@Nullable OnClickListener l) {
        Ensighten.evaluateEvent(this, "setOnClickListener", new Object[]{l});
        super.setOnClickListener(new DataLayerClickListener(l));
    }
}
