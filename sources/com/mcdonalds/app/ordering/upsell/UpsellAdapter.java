package com.mcdonalds.app.ordering.upsell;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.support.p001v7.widget.RecyclerView.Adapter;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Order.PriceType;
import com.mcdonalds.sdk.modules.models.Product;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class UpsellAdapter extends Adapter<ViewHolder> {
    private UpsellButtonsCallback mCallback;
    private Context mContext;
    private PriceType mPriceType = PriceType.EatIn;
    private List<Product> mProducts;
    private SparseArray<Integer> mQuantities;

    public interface UpsellButtonsCallback {
        void onItemClicked(int i);

        void onMinusButtonClicked(int i);

        void onPlusButtonClicked(int i);
    }

    public class ViewHolder extends android.support.p001v7.widget.RecyclerView.ViewHolder {
        ImageView imageView;
        TextView itemPrice;
        TextView itemQuantity;
        TextView itemTitle;
        Button minusButton;
        Button plusButton;
        View selectionMark;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(2131820643);
            this.itemTitle = (TextView) itemView.findViewById(C2358R.C2357id.item_title);
            this.itemPrice = (TextView) itemView.findViewById(C2358R.C2357id.item_price);
            this.selectionMark = itemView.findViewById(C2358R.C2357id.selection_mark);
            this.minusButton = (Button) itemView.findViewById(C2358R.C2357id.minus_button);
            this.plusButton = (Button) itemView.findViewById(C2358R.C2357id.plus_button);
            this.itemQuantity = (TextView) itemView.findViewById(C2358R.C2357id.item_quantity);
        }
    }

    public UpsellAdapter(Context context, UpsellButtonsCallback callback) {
        this.mContext = context;
        this.mCallback = callback;
        this.mProducts = new ArrayList();
    }

    public void setRecipes(List<Product> products, PriceType priceType) {
        Ensighten.evaluateEvent(this, "setRecipes", new Object[]{products, priceType});
        this.mProducts = products;
        this.mPriceType = priceType;
        this.mQuantities = new SparseArray();
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(C2658R.layout.upsell_item, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Ensighten.evaluateEvent(this, "onBindViewHolder", new Object[]{holder, new Integer(position)});
        Product product = (Product) this.mProducts.get(position);
        holder.itemTitle.setText(product.getLongName());
        double price = product.getPrice(this.mPriceType);
        holder.itemPrice.setText(UIUtils.getLocalizedCurrencyFormatter().format(price));
        loadImage(product.getImageUrl(), holder.imageView);
        Integer quantity = (Integer) this.mQuantities.get(position);
        if (quantity == null || quantity.intValue() < 1) {
            holder.itemView.setBackgroundColor(0);
            holder.selectionMark.setVisibility(8);
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(this.mContext, C2658R.color.mcd_red));
            holder.selectionMark.setVisibility(0);
            holder.itemQuantity.setText(String.valueOf(quantity));
        }
        final int holderPosition = holder.getAdapterPosition();
        holder.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.upsell.UpsellAdapter", "access$000", new Object[]{UpsellAdapter.this}).onItemClicked(holderPosition);
            }
        });
        holder.minusButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.upsell.UpsellAdapter", "access$000", new Object[]{UpsellAdapter.this}).onMinusButtonClicked(holderPosition);
            }
        });
        holder.plusButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.upsell.UpsellAdapter", "access$000", new Object[]{UpsellAdapter.this}).onPlusButtonClicked(holderPosition);
            }
        });
        DataLayerClickListener.setDataLayerTag(holder.itemView, ViewHolder.class, position);
    }

    public int getItemCount() {
        Ensighten.evaluateEvent(this, "getItemCount", null);
        return this.mProducts.size();
    }

    public void updateQuantity(int position, int quantity) {
        Ensighten.evaluateEvent(this, "updateQuantity", new Object[]{new Integer(position), new Integer(quantity)});
        this.mQuantities.put(position, Integer.valueOf(quantity));
        notifyItemChanged(position);
    }

    public boolean hasProductSelected() {
        Ensighten.evaluateEvent(this, "hasProductSelected", null);
        if (this.mQuantities == null || this.mQuantities.size() <= 0) {
            return false;
        }
        int size = this.mQuantities.size();
        for (int i = 0; i < size; i++) {
            Integer item = (Integer) this.mQuantities.get(i);
            if (item != null && item.intValue() >= 1) {
                return true;
            }
        }
        return false;
    }

    private void loadImage(String imageUrl, ImageView imageView) {
        Ensighten.evaluateEvent(this, "loadImage", new Object[]{imageUrl, imageView});
        imageView.setImageDrawable(null);
        if (TextUtils.isEmpty(imageUrl)) {
            imageView.setBackgroundResource(C2358R.C2359drawable.icon_large_meal);
            return;
        }
        try {
            String escapedImageUrl = AppUtils.stringByAddingPercentEscapesUsingEncoding(imageUrl, "utf8");
            imageView.setBackgroundResource(0);
            Glide.with(this.mContext).load(escapedImageUrl).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder((int) C2358R.C2359drawable.icon_large_meal).into(imageView);
        } catch (UnsupportedEncodingException e) {
        }
    }
}
