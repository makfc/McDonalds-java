package com.mcdonalds.app.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.util.ProductQuantityControlsPresenter;
import com.mcdonalds.gma.hongkong.C2658R;

public class ProductQuantityControlsBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = new IncludedLayouts(5);
    private static final SparseIntArray sViewsWithIds = null;
    public final Button decreaseQuantity;
    public final Button increaseQuantity;
    private long mDirtyFlags = -1;
    private ProductQuantityControlsPresenter mPresenter;
    private final TotalCaloriePriceBinding mboundView0;
    public final LinearLayout quantityTotalContainer;
    public final TextView quantityValue;

    static {
        sIncludes.setIncludes(0, new String[]{"total_calorie_price"}, new int[]{4}, new int[]{C2658R.layout.total_calorie_price});
    }

    public ProductQuantityControlsBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.decreaseQuantity = (Button) bindings[3];
        this.decreaseQuantity.setTag(null);
        this.increaseQuantity = (Button) bindings[1];
        this.increaseQuantity.setTag(null);
        this.mboundView0 = (TotalCaloriePriceBinding) bindings[4];
        setContainedBinding(this.mboundView0);
        this.quantityTotalContainer = (LinearLayout) bindings[0];
        this.quantityTotalContainer.setTag(null);
        this.quantityValue = (TextView) bindings[2];
        this.quantityValue.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        Ensighten.evaluateEvent(this, "invalidateAll", null);
        synchronized (this) {
            this.mDirtyFlags = 16;
        }
        this.mboundView0.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Missing block: B:8:0x0019, code skipped:
            if (r6.mboundView0.hasPendingBindings() != false) goto L_?;
     */
    /* JADX WARNING: Missing block: B:18:?, code skipped:
            return true;
     */
    /* JADX WARNING: Missing block: B:19:?, code skipped:
            return false;
     */
    public boolean hasPendingBindings() {
        /*
        r6 = this;
        r0 = 1;
        r1 = "hasPendingBindings";
        r2 = 0;
        com.ensighten.Ensighten.evaluateEvent(r6, r1, r2);
        monitor-enter(r6);
        r2 = r6.mDirtyFlags;	 Catch:{ all -> 0x001d }
        r4 = 0;
        r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r1 == 0) goto L_0x0012;
    L_0x0010:
        monitor-exit(r6);	 Catch:{ all -> 0x001d }
    L_0x0011:
        return r0;
    L_0x0012:
        monitor-exit(r6);	 Catch:{ all -> 0x001d }
        r1 = r6.mboundView0;
        r1 = r1.hasPendingBindings();
        if (r1 != 0) goto L_0x0011;
    L_0x001b:
        r0 = 0;
        goto L_0x0011;
    L_0x001d:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x001d }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mcdonalds.app.databinding.ProductQuantityControlsBinding.hasPendingBindings():boolean");
    }

    public void setPresenter(ProductQuantityControlsPresenter Presenter) {
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
                return onChangePresenter((ProductQuantityControlsPresenter) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangePresenter(ProductQuantityControlsPresenter Presenter, int fieldId) {
        Ensighten.evaluateEvent(this, "onChangePresenter", new Object[]{Presenter, new Integer(fieldId)});
        switch (fieldId) {
            case 0:
                synchronized (this) {
                    this.mDirtyFlags |= 1;
                }
                return true;
            case 8:
                synchronized (this) {
                    this.mDirtyFlags |= 8;
                }
                return true;
            case 10:
                synchronized (this) {
                    this.mDirtyFlags |= 2;
                }
                return true;
            case 20:
                synchronized (this) {
                    this.mDirtyFlags |= 4;
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
        ProductQuantityControlsPresenter presenter = this.mPresenter;
        String presenterQuantity = null;
        boolean presenterEnableMinusButton = false;
        boolean presenterEnablePlusButton = false;
        if ((31 & dirtyFlags) != 0) {
            if (!((21 & dirtyFlags) == 0 || presenter == null)) {
                presenterQuantity = presenter.getQuantity();
            }
            if (!((25 & dirtyFlags) == 0 || presenter == null)) {
                presenterEnableMinusButton = presenter.getEnableMinusButton();
            }
            if (!((19 & dirtyFlags) == 0 || presenter == null)) {
                presenterEnablePlusButton = presenter.getEnablePlusButton();
            }
        }
        if ((25 & dirtyFlags) != 0) {
            this.decreaseQuantity.setEnabled(presenterEnableMinusButton);
        }
        if ((19 & dirtyFlags) != 0) {
            this.increaseQuantity.setEnabled(presenterEnablePlusButton);
        }
        if ((17 & dirtyFlags) != 0) {
            this.mboundView0.setPresenter(presenter);
        }
        if ((21 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.quantityValue, presenterQuantity);
        }
        ViewDataBinding.executeBindingsOn(this.mboundView0);
    }

    public static ProductQuantityControlsBinding bind(View view, DataBindingComponent bindingComponent) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.databinding.ProductQuantityControlsBinding", "bind", new Object[]{view, bindingComponent});
        if ("layout/product_quantity_controls_0".equals(view.getTag())) {
            return new ProductQuantityControlsBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
