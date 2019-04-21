package com.mcdonalds.app.analytics.datalayer.view;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.ensighten.Ensighten;
import com.facebook.internal.AnalyticsEvents;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.analytics.datalayer.mapping.DataLayerButtonMapping;
import com.mcdonalds.app.analytics.datalayer.mapping.DataLayerItemMapping;
import com.mcdonalds.app.analytics.datalayer.mapping.DataLayerTabMapping;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.sdk.services.configuration.Configuration;

public class DataLayerClickListener implements OnTabSelectedListener, OnClickListener, OnItemClickListener {
    private OnItemClickListener innerItemViewListener;
    private OnClickListener innerViewListener;
    private String tag;

    DataLayerClickListener() {
    }

    public DataLayerClickListener(OnClickListener innerViewListener) {
        this.innerViewListener = innerViewListener;
    }

    public DataLayerClickListener(OnItemClickListener innerItemViewListener) {
        this.innerItemViewListener = innerItemViewListener;
    }

    public DataLayerClickListener(String tag, OnItemClickListener innerItemViewListener) {
        this.tag = tag;
        this.innerItemViewListener = innerItemViewListener;
    }

    public void onClick(View view) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
        if (DataLayerManager.isEnabled(Configuration.getSharedInstance())) {
            reportClick(view);
        }
        if (this.innerViewListener != null) {
            this.innerViewListener.onClick(view);
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Ensighten.evaluateEvent(this, "onItemClick", new Object[]{parent, view, new Integer(position), new Long(id)});
        if (DataLayerManager.isEnabled(Configuration.getSharedInstance())) {
            if (this.tag != null) {
                DataLayerManager.getInstance().logItemClick(this.tag, position);
            } else {
                reportClick(view);
            }
        }
        if (this.innerItemViewListener != null) {
            this.innerItemViewListener.onItemClick(parent, view, position, id);
        }
    }

    public void onTabSelected(Tab tab) {
        Ensighten.evaluateEvent(this, "onTabSelected", new Object[]{tab});
        if (DataLayerManager.isEnabled(Configuration.getSharedInstance())) {
            String tabTitle = null;
            if (tab.getTag() != null && (tab.getTag() instanceof String)) {
                String tabTag = (String) tab.getTag();
                tabTitle = DataLayerTabMapping.get(tabTag) != null ? DataLayerTabMapping.get(tabTag) : tabTag;
            }
            if (tabTitle == null) {
                DataLayerManager.reportWarning("There is no text for this tab click");
            } else {
                DataLayerManager.getInstance().logItemClick(tabTitle, tab.getPosition());
            }
        }
    }

    public void onTabUnselected(Tab tab) {
        Ensighten.evaluateEvent(this, "onTabUnselected", new Object[]{tab});
    }

    public void onTabReselected(Tab tab) {
        Ensighten.evaluateEvent(this, "onTabReselected", new Object[]{tab});
    }

    private void reportClick(View view) {
        Ensighten.evaluateEvent(this, "reportClick", new Object[]{view});
        int position = getPositionTag(view);
        if (position == -1) {
            DataLayerManager.getInstance().logButtonClick(getButtonTag(view));
            return;
        }
        DataLayerManager.getInstance().logItemClick(getItemTag(view), position);
    }

    private String getButtonTag(View view) {
        Ensighten.evaluateEvent(this, "getButtonTag", new Object[]{view});
        String realTag = (String) view.getTag(C2358R.C2357id.data_layer_tag);
        String rawTag = (String) view.getTag(C2358R.C2357id.data_layer_raw_tag);
        String pageTag = (String) view.getTag(C2358R.C2357id.data_layer_page_tag);
        if (realTag != null) {
            return realTag;
        }
        if (DataLayerButtonMapping.get(pageTag, rawTag) != null) {
            return DataLayerButtonMapping.get(pageTag, rawTag);
        }
        if (rawTag != null) {
            return rawTag;
        }
        String result = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        DataLayerManager.reportWarning("There is no tag set for this button click.");
        return result;
    }

    private String getItemTag(View view) {
        Ensighten.evaluateEvent(this, "getItemTag", new Object[]{view});
        String realTag = (String) view.getTag(C2358R.C2357id.data_layer_tag);
        String rawTag = (String) view.getTag(C2358R.C2357id.data_layer_raw_tag);
        if (realTag != null) {
            return realTag;
        }
        if (DataLayerItemMapping.get(rawTag) != null) {
            return DataLayerItemMapping.get(rawTag);
        }
        if (rawTag != null) {
            return rawTag;
        }
        String result = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        DataLayerManager.reportWarning("There is no tag set for this list item click.");
        return result;
    }

    public static void setDataLayerTag(View view, String tag) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener", "setDataLayerTag", new Object[]{view, tag});
        if (view != null) {
            view.setTag(C2358R.C2357id.data_layer_tag, tag);
        }
    }

    public static void setDataLayerTag(Tab tab, String tag) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener", "setDataLayerTag", new Object[]{tab, tag});
        if (tab != null) {
            tab.setTag(tag);
        }
    }

    public static void setDataLayerTag(View v, Context context, AttributeSet attrs) {
        String asString;
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener", "setDataLayerTag", new Object[]{v, context, attrs});
        TypedArray a = context.obtainStyledAttributes(attrs, new int[]{16843087});
        try {
            asString = context.getResources().getResourceEntryName(a.getResourceId(0, -1));
        } catch (NotFoundException e) {
            asString = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
        v.setTag(C2358R.C2357id.data_layer_raw_tag, asString);
        a.recycle();
        if (context instanceof URLNavigationActivity) {
            v.setTag(C2358R.C2357id.data_layer_page_tag, ((URLNavigationActivity) context).getVisibleDataLayerPage());
        }
    }

    public static void setDataLayerTag(View view, String itemTag, int position) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener", "setDataLayerTag", new Object[]{view, itemTag, new Integer(position)});
        if (view != null) {
            view.setTag(C2358R.C2357id.data_layer_tag, itemTag);
            view.setTag(C2358R.C2357id.data_layer_position_tag, Integer.valueOf(position));
        }
    }

    public static void setDataLayerTag(View view, Class viewHolderClass, int position) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener", "setDataLayerTag", new Object[]{view, viewHolderClass, new Integer(position)});
        if (view != null) {
            view.setTag(C2358R.C2357id.data_layer_raw_tag, viewHolderClass.getName());
            view.setTag(C2358R.C2357id.data_layer_position_tag, Integer.valueOf(position));
        }
    }

    private static int getPositionTag(View view) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener", "getPositionTag", new Object[]{view});
        return view.getTag(C2358R.C2357id.data_layer_position_tag) != null ? ((Integer) view.getTag(C2358R.C2357id.data_layer_position_tag)).intValue() : -1;
    }
}
