package com.mcdonalds.app.p043ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.autonavi.amap.mapcore.MapCore;
import com.ensighten.Ensighten;
import com.facebook.internal.AnalyticsEvents;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.gma.hongkong.C2658R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import p046se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/* renamed from: com.mcdonalds.app.ui.ConfigSwitcherListAdapter */
public class ConfigSwitcherListAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    private static final int[] SECTION_MAX_VALUES = new int[]{9, 499, 899, 998, MapCore.MAPRENDER_CAN_STOP_AND_FULLSCREEN_RENDEROVER};
    private static final String[] SECTION_TITLES = new String[]{"Master", "Core GMA", "DAP", "RDI/eCP", "Temporary"};
    private String[] configList;
    private Context context;
    private Integer[] sectionIndices;

    /* renamed from: com.mcdonalds.app.ui.ConfigSwitcherListAdapter$HeaderViewHolder */
    class HeaderViewHolder {
        TextView text;

        HeaderViewHolder() {
        }
    }

    /* renamed from: com.mcdonalds.app.ui.ConfigSwitcherListAdapter$ViewHolder */
    class ViewHolder {
        TextView text;

        ViewHolder() {
        }
    }

    public ConfigSwitcherListAdapter(Context context, List<String> configList) {
        this.context = context;
        this.configList = (String[]) configList.toArray(new String[configList.size()]);
        this.sectionIndices = getSectionIndices(configList);
    }

    private Integer[] getSectionIndices(List<String> configList) {
        Ensighten.evaluateEvent(this, "getSectionIndices", new Object[]{configList});
        List<Integer> result = new ArrayList();
        result.add(Integer.valueOf(0));
        int sectionIndexIndex = 0;
        for (int ii = 0; ii < configList.size(); ii++) {
            int configNum = -1;
            try {
                configNum = Integer.parseInt(((String) configList.get(ii)).split("_")[0]);
            } catch (NumberFormatException e) {
            }
            if (configNum > SECTION_MAX_VALUES[sectionIndexIndex]) {
                result.add(Integer.valueOf(ii));
                sectionIndexIndex++;
            }
        }
        return (Integer[]) result.toArray(new Integer[result.size()]);
    }

    public int getCount() {
        Ensighten.evaluateEvent(this, "getCount", null);
        return this.configList.length;
    }

    public Object getItem(int position) {
        Ensighten.evaluateEvent(this, "getItem", new Object[]{new Integer(position)});
        return this.configList[position];
    }

    public long getItemId(int position) {
        Ensighten.evaluateEvent(this, "getItemId", new Object[]{new Integer(position)});
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(this.context).inflate(17367062, parent, false);
            holder.text = (TextView) convertView.findViewById(16908308);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DataLayerClickListener.setDataLayerTag(convertView, ViewHolder.class, position);
        holder.text.setText(getDisplayName(this.configList[position]));
        Ensighten.getViewReturnValue(convertView, position);
        Ensighten.processView((Object) this, "getView");
        Ensighten.getViewReturnValue(null, -1);
        return convertView;
    }

    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        Ensighten.evaluateEvent(this, "getHeaderView", new Object[]{new Integer(position), convertView, parent});
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = LayoutInflater.from(this.context).inflate(C2658R.layout.section_header_w_bar, parent, false);
            holder.text = (TextView) convertView.findViewById(C2358R.C2357id.section_name);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        holder.text.setText(getSectionTitle(position));
        return convertView;
    }

    public long getHeaderId(int position) {
        Ensighten.evaluateEvent(this, "getHeaderId", new Object[]{new Integer(position)});
        long result = 0;
        int configNum = -1;
        try {
            configNum = Integer.parseInt(this.configList[position].split("_")[0]);
        } catch (NumberFormatException e) {
        }
        while (result < ((long) SECTION_MAX_VALUES.length) && configNum > SECTION_MAX_VALUES[(int) result]) {
            result++;
        }
        return result;
    }

    private String getDisplayName(String key) {
        Ensighten.evaluateEvent(this, "getDisplayName", new Object[]{key});
        String cutoff = "config";
        String[] split = key.split("_");
        if (split.length < 3 || !cutoff.equals(split[1])) {
            return TextUtils.join(" ", split);
        }
        return TextUtils.join(" ", (String[]) Arrays.copyOfRange(split, 2, split.length));
    }

    private String getSectionTitle(int position) {
        Ensighten.evaluateEvent(this, "getSectionTitle", new Object[]{new Integer(position)});
        for (int ii = this.sectionIndices.length - 1; ii >= 0; ii--) {
            if (position >= this.sectionIndices[ii].intValue()) {
                return SECTION_TITLES[ii];
            }
        }
        return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
    }
}
