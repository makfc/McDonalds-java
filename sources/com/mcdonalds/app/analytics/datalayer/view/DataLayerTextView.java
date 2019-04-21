package com.mcdonalds.app.analytics.datalayer.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ensighten.Ensighten;

@SuppressLint({"AppCompatCustomView"})
public class DataLayerTextView extends TextView {
    public DataLayerTextView(Context context) {
        super(context);
    }

    public DataLayerTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        DataLayerClickListener.setDataLayerTag((View) this, context, attrs);
    }

    public DataLayerTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnClickListener(@Nullable OnClickListener l) {
        Ensighten.evaluateEvent(this, "setOnClickListener", new Object[]{l});
        super.setOnClickListener(new DataLayerClickListener(l));
    }
}
