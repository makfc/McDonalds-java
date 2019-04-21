package com.mcdonalds.app.widget;

import android.content.Context;
import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.ArrayList;
import java.util.List;

public class GeoSuggestionsAdapter extends BaseAdapter implements Filterable {
    private List<Address> addresses = new ArrayList();
    private Context context;
    private Filter filter;
    private LayoutInflater inflater;

    private class ViewHolder {
        public ImageView pin;
        public TextView subtitle;
        public TextView title;

        private ViewHolder() {
        }
    }

    public GeoSuggestionsAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public int getCount() {
        Ensighten.evaluateEvent(this, "getCount", null);
        return this.addresses.size();
    }

    public String getItem(int position) {
        Address address = (Address) this.addresses.get(position);
        if (address.getCountryName() != null) {
            return address.getFeatureName() + ", " + address.getCountryName();
        }
        return address.getFeatureName();
    }

    public long getItemId(int position) {
        Ensighten.evaluateEvent(this, "getItemId", new Object[]{new Integer(position)});
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = this.inflater.inflate(C2658R.layout.item_geo_suggestion, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(2131820647);
            holder.subtitle = (TextView) convertView.findViewById(C2358R.C2357id.subtitle);
            setPinImage(convertView, holder);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DataLayerClickListener.setDataLayerTag(convertView, ViewHolder.class, position);
        Address address = (Address) this.addresses.get(position);
        holder.title.setText(address.getFeatureName());
        StringBuilder subtitleBuilder = new StringBuilder();
        if (address.getAdminArea() != null) {
            subtitleBuilder.append(address.getAdminArea());
        }
        if (address.getCountryCode() != null) {
            if (subtitleBuilder.length() > 0) {
                subtitleBuilder.append(", ");
            }
            subtitleBuilder.append(address.getCountryCode());
        }
        holder.subtitle.setText(subtitleBuilder.toString());
        Ensighten.getViewReturnValue(convertView, position);
        Ensighten.processView((Object) this, "getView");
        Ensighten.getViewReturnValue(null, -1);
        return convertView;
    }

    private void setPinImage(View convertView, ViewHolder holder) {
        Ensighten.evaluateEvent(this, "setPinImage", new Object[]{convertView, holder});
        String pinId = Configuration.getSharedInstance().getStringForKey("interface.storelocator.predictiveGeocoderResultIcon");
        if (pinId != null) {
            int imgRes = this.context.getResources().getIdentifier(pinId, "drawable", this.context.getPackageName());
            if (imgRes != 0) {
                holder.pin = (ImageView) convertView.findViewById(2131820646);
                holder.pin.setImageResource(imgRes);
            }
        }
    }

    public Filter getFilter() {
        Ensighten.evaluateEvent(this, "getFilter", null);
        return this.filter;
    }

    public void setFilter(Filter filter) {
        Ensighten.evaluateEvent(this, "setFilter", new Object[]{filter});
        this.filter = filter;
    }

    public void setAddresses(List<Address> addresses) {
        Ensighten.evaluateEvent(this, "setAddresses", new Object[]{addresses});
        this.addresses.clear();
        this.addresses.addAll(addresses);
        notifyDataSetChanged();
    }
}
