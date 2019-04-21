package com.mcdonalds.app.analytics.datalayer.view;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;

class DataLayerTabLayout extends TabLayout {
    public DataLayerTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        addOnTabSelectedListener(new DataLayerClickListener());
    }

    public DataLayerTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
