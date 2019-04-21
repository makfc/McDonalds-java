package com.mcdonalds.app.analytics.datalayer.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.ensighten.Ensighten;

@SuppressLint({"AppCompatCustomView"})
public class DataLayerButton extends Button {
    public DataLayerButton(Context context) {
        super(context);
    }

    public DataLayerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        DataLayerClickListener.setDataLayerTag((View) this, context, attrs);
    }

    public DataLayerButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnClickListener(@Nullable OnClickListener l) {
        Ensighten.evaluateEvent(this, "setOnClickListener", new Object[]{l});
        super.setOnClickListener(new DataLayerClickListener(l));
    }
}
