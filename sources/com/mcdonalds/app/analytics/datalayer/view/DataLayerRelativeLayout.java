package com.mcdonalds.app.analytics.datalayer.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import com.ensighten.Ensighten;

public class DataLayerRelativeLayout extends RelativeLayout {
    public DataLayerRelativeLayout(Context context) {
        super(context);
    }

    public DataLayerRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DataLayerRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnClickListener(@Nullable OnClickListener l) {
        Ensighten.evaluateEvent(this, "setOnClickListener", new Object[]{l});
        super.setOnClickListener(new DataLayerClickListener(l));
    }
}
