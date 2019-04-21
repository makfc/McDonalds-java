package com.mcdonalds.app.util;

import android.databinding.ViewDataBinding;
import android.support.p001v7.widget.RecyclerView.ViewHolder;
import com.ensighten.Ensighten;

public class BindingHolder extends ViewHolder {
    private ViewDataBinding mBinding;

    public BindingHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.mBinding = binding;
    }

    public ViewDataBinding getBinding() {
        Ensighten.evaluateEvent(this, "getBinding", null);
        return this.mBinding;
    }
}
