package com.mcdonalds.app.ordering.customization;

import android.content.Context;
import android.support.p001v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.ensighten.Ensighten;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.gma.hongkong.C2658R;
import java.util.ArrayList;
import java.util.List;

public class ProductCustomizationAdapter extends Adapter<ProductCustomizationViewHolder> {
    private OnUpdateDataListener listener;
    private LayoutInflater mInflater;
    private List<ProductCustomizationItem> mItems = new ArrayList();

    public interface OnUpdateDataListener {
        void onChangeDataInAdapter();
    }

    public void setOnUpdateDataListener(OnUpdateDataListener listener) {
        Ensighten.evaluateEvent(this, "setOnUpdateDataListener", new Object[]{listener});
        this.listener = listener;
    }

    public ProductCustomizationAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public ProductCustomizationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductCustomizationViewHolder(this.mInflater.inflate(C2658R.layout.item_customization, parent, false), this);
    }

    public void onBindViewHolder(ProductCustomizationViewHolder holder, int position) {
        Ensighten.evaluateEvent(this, "onBindViewHolder", new Object[]{holder, new Integer(position)});
        holder.bind((ProductCustomizationItem) this.mItems.get(position));
        DataLayerClickListener.setDataLayerTag(holder.itemView, ProductCustomizationViewHolder.class, position);
    }

    public int getItemCount() {
        Ensighten.evaluateEvent(this, "getItemCount", null);
        return this.mItems.size();
    }

    public void setItems(List<ProductCustomizationItem> items) {
        Ensighten.evaluateEvent(this, "setItems", new Object[]{items});
        this.mItems.clear();
        this.mItems.addAll(items);
        notifyDataSetChanged();
    }

    public List<ProductCustomizationItem> getItems() {
        Ensighten.evaluateEvent(this, "getItems", null);
        return this.mItems;
    }

    public void changeDataInAdapter() {
        Ensighten.evaluateEvent(this, "changeDataInAdapter", null);
        this.listener.onChangeDataInAdapter();
    }
}
