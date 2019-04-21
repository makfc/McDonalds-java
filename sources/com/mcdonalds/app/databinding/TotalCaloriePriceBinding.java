package com.mcdonalds.app.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.util.TotalCaloriePricePresenter;

public class TotalCaloriePriceBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final TextView calTotalValue;
    private long mDirtyFlags = -1;
    private TotalCaloriePricePresenter mPresenter;
    public final TextView totalLabel;
    public final TextView totalPrice;
    public final LinearLayout totalPriceEnergy;

    static {
        sViewsWithIds.put(C2358R.C2357id.total_label, 2);
        sViewsWithIds.put(C2358R.C2357id.cal_total_value, 3);
    }

    public TotalCaloriePriceBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.calTotalValue = (TextView) bindings[3];
        this.totalLabel = (TextView) bindings[2];
        this.totalPrice = (TextView) bindings[1];
        this.totalPrice.setTag(null);
        this.totalPriceEnergy = (LinearLayout) bindings[0];
        this.totalPriceEnergy.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        Ensighten.evaluateEvent(this, "invalidateAll", null);
        synchronized (this) {
            this.mDirtyFlags = 4;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        Ensighten.evaluateEvent(this, "hasPendingBindings", null);
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public void setPresenter(TotalCaloriePricePresenter Presenter) {
        Ensighten.evaluateEvent(this, "setPresenter", new Object[]{Presenter});
        updateRegistration(0, Presenter);
        this.mPresenter = Presenter;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(17);
        super.requestRebind();
    }

    /* Access modifiers changed, original: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        Ensighten.evaluateEvent(this, "onFieldChange", new Object[]{new Integer(localFieldId), object, new Integer(fieldId)});
        switch (localFieldId) {
            case 0:
                return onChangePresenter((TotalCaloriePricePresenter) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangePresenter(TotalCaloriePricePresenter Presenter, int fieldId) {
        Ensighten.evaluateEvent(this, "onChangePresenter", new Object[]{Presenter, new Integer(fieldId)});
        switch (fieldId) {
            case 0:
                synchronized (this) {
                    this.mDirtyFlags |= 1;
                }
                return true;
            case 54:
                synchronized (this) {
                    this.mDirtyFlags |= 2;
                }
                return true;
            default:
                return false;
        }
    }

    /* Access modifiers changed, original: protected */
    public void executeBindings() {
        long dirtyFlags;
        Ensighten.evaluateEvent(this, "executeBindings", null);
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        TotalCaloriePricePresenter presenter = this.mPresenter;
        String presenterTotalPrice = null;
        if (!((dirtyFlags & 7) == 0 || presenter == null)) {
            presenterTotalPrice = presenter.getTotalPrice();
        }
        if ((dirtyFlags & 7) != 0) {
            TextViewBindingAdapter.setText(this.totalPrice, presenterTotalPrice);
        }
    }

    public static TotalCaloriePriceBinding bind(View view, DataBindingComponent bindingComponent) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.databinding.TotalCaloriePriceBinding", "bind", new Object[]{view, bindingComponent});
        if ("layout/total_calorie_price_0".equals(view.getTag())) {
            return new TotalCaloriePriceBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
