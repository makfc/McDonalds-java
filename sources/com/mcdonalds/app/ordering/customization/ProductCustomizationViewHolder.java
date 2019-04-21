package com.mcdonalds.app.ordering.customization;

import android.support.p001v7.widget.RecyclerView.Adapter;
import android.support.p001v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.gma.hongkong.C2658R;

public class ProductCustomizationViewHolder extends ViewHolder {
    private Adapter mAdapter;
    private TextView mCost;
    private TextView mHeader;
    private ProductCustomizationItem mItem;
    private View mMinus;
    private final OnClickListener mOnClickMinus = new C34702();
    private final OnClickListener mOnClickPlus = new C34691();
    private View mPlus;
    private TextView mQuantity;
    private TextView mTitle;

    /* renamed from: com.mcdonalds.app.ordering.customization.ProductCustomizationViewHolder$1 */
    class C34691 implements OnClickListener {
        C34691() {
        }

        public void onClick(View view) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.customization.ProductCustomizationViewHolder", "access$000", new Object[]{ProductCustomizationViewHolder.this}).add();
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.customization.ProductCustomizationViewHolder", "access$100", new Object[]{ProductCustomizationViewHolder.this}).notifyItemChanged(ProductCustomizationViewHolder.this.getAdapterPosition());
            ((ProductCustomizationAdapter) Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.customization.ProductCustomizationViewHolder", "access$100", new Object[]{ProductCustomizationViewHolder.this})).changeDataInAdapter();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.customization.ProductCustomizationViewHolder$2 */
    class C34702 implements OnClickListener {
        C34702() {
        }

        public void onClick(View view) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.customization.ProductCustomizationViewHolder", "access$000", new Object[]{ProductCustomizationViewHolder.this}).remove();
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.customization.ProductCustomizationViewHolder", "access$100", new Object[]{ProductCustomizationViewHolder.this}).notifyItemChanged(ProductCustomizationViewHolder.this.getAdapterPosition());
            ((ProductCustomizationAdapter) Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.customization.ProductCustomizationViewHolder", "access$100", new Object[]{ProductCustomizationViewHolder.this})).changeDataInAdapter();
        }
    }

    public ProductCustomizationViewHolder(View view, Adapter adapter) {
        super(view);
        this.mAdapter = adapter;
        bindViews(view);
    }

    private void bindViews(View view) {
        Ensighten.evaluateEvent(this, "bindViews", new Object[]{view});
        this.mHeader = (TextView) view.findViewById(C2358R.C2357id.header);
        this.mTitle = (TextView) view.findViewById(2131820647);
        this.mCost = (TextView) view.findViewById(C2358R.C2357id.cost);
        this.mQuantity = (TextView) view.findViewById(C2358R.C2357id.quantity);
        this.mPlus = view.findViewById(C2358R.C2357id.plus);
        this.mPlus.setOnClickListener(this.mOnClickPlus);
        this.mMinus = view.findViewById(C2358R.C2357id.minus);
        this.mMinus.setOnClickListener(this.mOnClickMinus);
    }

    public void bind(ProductCustomizationItem item) {
        Ensighten.evaluateEvent(this, "bind", new Object[]{item});
        this.mItem = item;
        if (item.isHeader()) {
            this.mHeader.setVisibility(0);
            this.mHeader.setText(item.getType() == 0 ? C2658R.string.comes_with : C2658R.string.additional_condiments);
        } else {
            this.mHeader.setVisibility(8);
        }
        this.mTitle.setText(item.getTitle());
        this.mCost.setText(item.getCost());
        this.mQuantity.setText(item.getQuantity());
        this.mPlus.setEnabled(item.canAdd());
        this.mMinus.setEnabled(item.canRemove());
    }
}
