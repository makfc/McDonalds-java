package com.mcdonalds.app.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.bagcharge.BagChargePresenter;
import com.mcdonalds.gma.hongkong.C2658R;

public class ActivityBagChargeBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = new IncludedLayouts(5);
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final LinearLayout activityBagCharge;
    public final Button continueButton;
    public final Button dismissButton;
    private long mDirtyFlags = -1;
    private BagChargePresenter mPresenter;
    public final BoundProductDetailsItemBinding productDetails;
    public final ProductQuantityControlsBinding quantityControls;

    static {
        sIncludes.setIncludes(0, new String[]{"bound_product_details_item", "product_quantity_controls"}, new int[]{1, 2}, new int[]{C2658R.layout.bound_product_details_item, C2658R.layout.product_quantity_controls});
        sViewsWithIds.put(C2358R.C2357id.dismiss_button, 3);
        sViewsWithIds.put(C2358R.C2357id.continue_button, 4);
    }

    public ActivityBagChargeBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 3);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.activityBagCharge = (LinearLayout) bindings[0];
        this.activityBagCharge.setTag(null);
        this.continueButton = (Button) bindings[4];
        this.dismissButton = (Button) bindings[3];
        this.productDetails = (BoundProductDetailsItemBinding) bindings[1];
        setContainedBinding(this.productDetails);
        this.quantityControls = (ProductQuantityControlsBinding) bindings[2];
        setContainedBinding(this.quantityControls);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        Ensighten.evaluateEvent(this, "invalidateAll", null);
        synchronized (this) {
            this.mDirtyFlags = 8;
        }
        this.productDetails.invalidateAll();
        this.quantityControls.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Missing block: B:8:0x0019, code skipped:
            if (r6.productDetails.hasPendingBindings() != false) goto L_?;
     */
    /* JADX WARNING: Missing block: B:10:0x0021, code skipped:
            if (r6.quantityControls.hasPendingBindings() != false) goto L_?;
     */
    /* JADX WARNING: Missing block: B:20:?, code skipped:
            return true;
     */
    /* JADX WARNING: Missing block: B:21:?, code skipped:
            return true;
     */
    /* JADX WARNING: Missing block: B:22:?, code skipped:
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
        r2 = r6.mDirtyFlags;	 Catch:{ all -> 0x0025 }
        r4 = 0;
        r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r1 == 0) goto L_0x0012;
    L_0x0010:
        monitor-exit(r6);	 Catch:{ all -> 0x0025 }
    L_0x0011:
        return r0;
    L_0x0012:
        monitor-exit(r6);	 Catch:{ all -> 0x0025 }
        r1 = r6.productDetails;
        r1 = r1.hasPendingBindings();
        if (r1 != 0) goto L_0x0011;
    L_0x001b:
        r1 = r6.quantityControls;
        r1 = r1.hasPendingBindings();
        if (r1 != 0) goto L_0x0011;
    L_0x0023:
        r0 = 0;
        goto L_0x0011;
    L_0x0025:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0025 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mcdonalds.app.databinding.ActivityBagChargeBinding.hasPendingBindings():boolean");
    }

    public void setPresenter(BagChargePresenter Presenter) {
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
                return onChangePresenter((BagChargePresenter) object, fieldId);
            case 1:
                return onChangeProductDetails((BoundProductDetailsItemBinding) object, fieldId);
            case 2:
                return onChangeQuantityControls((ProductQuantityControlsBinding) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangePresenter(BagChargePresenter Presenter, int fieldId) {
        Ensighten.evaluateEvent(this, "onChangePresenter", new Object[]{Presenter, new Integer(fieldId)});
        switch (fieldId) {
            case 0:
                synchronized (this) {
                    this.mDirtyFlags |= 1;
                }
                return true;
            default:
                return false;
        }
    }

    private boolean onChangeProductDetails(BoundProductDetailsItemBinding ProductDetails, int fieldId) {
        Ensighten.evaluateEvent(this, "onChangeProductDetails", new Object[]{ProductDetails, new Integer(fieldId)});
        switch (fieldId) {
            case 0:
                synchronized (this) {
                    this.mDirtyFlags |= 2;
                }
                return true;
            default:
                return false;
        }
    }

    private boolean onChangeQuantityControls(ProductQuantityControlsBinding QuantityControls, int fieldId) {
        Ensighten.evaluateEvent(this, "onChangeQuantityControls", new Object[]{QuantityControls, new Integer(fieldId)});
        switch (fieldId) {
            case 0:
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
        BagChargePresenter presenter = this.mPresenter;
        if ((dirtyFlags & 9) != 0) {
        }
        if ((dirtyFlags & 9) != 0) {
            this.productDetails.setPresenter(presenter);
            this.quantityControls.setPresenter(presenter);
        }
        ViewDataBinding.executeBindingsOn(this.productDetails);
        ViewDataBinding.executeBindingsOn(this.quantityControls);
    }

    public static ActivityBagChargeBinding bind(View view, DataBindingComponent bindingComponent) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.databinding.ActivityBagChargeBinding", "bind", new Object[]{view, bindingComponent});
        if ("layout/activity_bag_charge_0".equals(view.getTag())) {
            return new ActivityBagChargeBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
