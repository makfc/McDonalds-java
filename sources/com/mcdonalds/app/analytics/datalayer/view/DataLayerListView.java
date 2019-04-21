package com.mcdonalds.app.analytics.datalayer.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.ensighten.Ensighten;

public class DataLayerListView extends ListView {
    public DataLayerListView(Context context) {
        super(context);
    }

    public DataLayerListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DataLayerListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnItemClickListener(@Nullable OnItemClickListener listener) {
        Ensighten.evaluateEvent(this, "setOnItemClickListener", new Object[]{listener});
        super.setOnItemClickListener(new DataLayerClickListener(listener));
    }
}
