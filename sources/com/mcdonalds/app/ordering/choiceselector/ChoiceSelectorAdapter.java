package com.mcdonalds.app.ordering.choiceselector;

import android.support.p001v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.databinding.BoundProductDetailsItemBinding;
import com.mcdonalds.app.util.BindingHolder;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import java.util.ArrayList;
import java.util.List;
import p070me.grantland.widget.AutofitTextView;

public class ChoiceSelectorAdapter extends Adapter<BindingHolder> {
    private double mBasePrice;
    private OnProductClickedListener mOnProductClickedListener;
    private OnProductCustomizeClickedListener mOnProductCustomizeClickedListener;
    private OnProductInfoClickedListener mOnProductInfoClickedListener;
    private List<OrderProduct> mOptions = new ArrayList();
    private List<Integer> mOutageCode;
    private int mSelected = -1;

    public interface OnProductClickedListener {
        void onProductClicked(OrderProduct orderProduct);
    }

    public interface OnProductCustomizeClickedListener {
        void onProductCustomizeClicked(OrderProduct orderProduct);
    }

    public interface OnProductInfoClickedListener {
        void onProductInfoClicked(String str);
    }

    static /* synthetic */ void access$000(ChoiceSelectorAdapter x0, OrderProduct x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.choiceselector.ChoiceSelectorAdapter", "access$000", new Object[]{x0, x1});
        x0.productClicked(x1);
    }

    static /* synthetic */ void access$100(ChoiceSelectorAdapter x0, OrderProduct x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.choiceselector.ChoiceSelectorAdapter", "access$100", new Object[]{x0, x1});
        x0.productCustomizeClicked(x1);
    }

    static /* synthetic */ void access$200(ChoiceSelectorAdapter x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.choiceselector.ChoiceSelectorAdapter", "access$200", new Object[]{x0, x1});
        x0.productInfoClicked(x1);
    }

    public void setOptions(List<OrderProduct> options, List<Integer> outageCodes) {
        Ensighten.evaluateEvent(this, "setOptions", new Object[]{options, outageCodes});
        this.mOptions = options;
        this.mSelected = -1;
        this.mOutageCode = outageCodes;
        notifyDataSetChanged();
    }

    public void setBasePrice(double basePrice) {
        Ensighten.evaluateEvent(this, "setBasePrice", new Object[]{new Double(basePrice)});
        this.mBasePrice = basePrice;
        notifyDataSetChanged();
    }

    public void setOnProductClickedListener(OnProductClickedListener onProductClickedListener) {
        Ensighten.evaluateEvent(this, "setOnProductClickedListener", new Object[]{onProductClickedListener});
        this.mOnProductClickedListener = onProductClickedListener;
    }

    public void setOnProductCustomizeClickedListener(OnProductCustomizeClickedListener onProductCustomizeClickedListener) {
        Ensighten.evaluateEvent(this, "setOnProductCustomizeClickedListener", new Object[]{onProductCustomizeClickedListener});
        this.mOnProductCustomizeClickedListener = onProductCustomizeClickedListener;
    }

    public void setOnProductInfoClickedListener(OnProductInfoClickedListener onProductInfoClickedListener) {
        Ensighten.evaluateEvent(this, "setOnProductInfoClickedListener", new Object[]{onProductInfoClickedListener});
        this.mOnProductInfoClickedListener = onProductInfoClickedListener;
    }

    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BindingHolder(BoundProductDetailsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    public void onBindViewHolder(BindingHolder holder, int position) {
        boolean z;
        boolean z2 = true;
        Ensighten.evaluateEvent(this, "onBindViewHolder", new Object[]{holder, new Integer(position)});
        BoundProductDetailsItemBinding binding = (BoundProductDetailsItemBinding) holder.getBinding();
        final OrderProduct option = (OrderProduct) this.mOptions.get(position);
        final ChoiceSelectorListItemPresenter productDetailsItemPresenter = new ChoiceSelectorListItemPresenter(option, this.mOutageCode.contains(option.getProduct().getExternalId()));
        productDetailsItemPresenter.setBasePrice(this.mBasePrice);
        if (position == this.mSelected) {
            z = true;
        } else {
            z = false;
        }
        productDetailsItemPresenter.setChecked(z);
        binding.setPresenter(productDetailsItemPresenter);
        AutofitTextView mAutofitTextView = (AutofitTextView) binding.getRoot().findViewById(C2358R.C2357id.name);
        if (this.mOutageCode.contains(option.getProduct().getExternalId())) {
            z = false;
        } else {
            z = true;
        }
        mAutofitTextView.setEnabled(z);
        View root = binding.getRoot();
        if (this.mOutageCode.contains(option.getProduct().getExternalId())) {
            z = false;
        } else {
            z = true;
        }
        root.setEnabled(z);
        ImageButton imageButton = binding.hatButton;
        if (this.mOutageCode.contains(option.getProduct().getExternalId())) {
            z = false;
        } else {
            z = true;
        }
        imageButton.setEnabled(z);
        ImageButton imageButton2 = binding.infoButton;
        if (this.mOutageCode.contains(option.getProduct().getExternalId())) {
            z2 = false;
        }
        imageButton2.setEnabled(z2);
        binding.getRoot().setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                ChoiceSelectorAdapter.access$000(ChoiceSelectorAdapter.this, option);
            }
        });
        binding.hatButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                ChoiceSelectorAdapter.access$100(ChoiceSelectorAdapter.this, option);
            }
        });
        binding.infoButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                ChoiceSelectorAdapter.access$200(ChoiceSelectorAdapter.this, productDetailsItemPresenter.getRecipeId());
            }
        });
        ((TextView) binding.getRoot().findViewById(C2358R.C2357id.custom_special_instructions)).setVisibility(8);
        DataLayerClickListener.setDataLayerTag(binding.getRoot(), BindingHolder.class, position);
        DataLayerClickListener.setDataLayerTag(binding.getRoot(), "ProductItemPressed", position);
    }

    private void productClicked(OrderProduct product) {
        Ensighten.evaluateEvent(this, "productClicked", new Object[]{product});
        if (this.mOnProductClickedListener != null) {
            this.mOnProductClickedListener.onProductClicked(product);
        }
    }

    private void productCustomizeClicked(OrderProduct product) {
        Ensighten.evaluateEvent(this, "productCustomizeClicked", new Object[]{product});
        if (this.mOnProductCustomizeClickedListener != null) {
            this.mOnProductCustomizeClickedListener.onProductCustomizeClicked(product);
        }
    }

    private void productInfoClicked(String recipeID) {
        Ensighten.evaluateEvent(this, "productInfoClicked", new Object[]{recipeID});
        if (this.mOnProductInfoClickedListener != null) {
            this.mOnProductInfoClickedListener.onProductInfoClicked(recipeID);
        }
    }

    public void setSelected(int position) {
        Ensighten.evaluateEvent(this, "setSelected", new Object[]{new Integer(position)});
        if (this.mSelected != -1) {
            notifyItemChanged(this.mSelected);
        }
        this.mSelected = position;
        notifyItemChanged(position);
    }

    public int getItemCount() {
        Ensighten.evaluateEvent(this, "getItemCount", null);
        return this.mOptions.size();
    }
}
