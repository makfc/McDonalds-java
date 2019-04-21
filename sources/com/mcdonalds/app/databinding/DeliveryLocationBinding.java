package com.mcdonalds.app.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.text.Spanned;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.util.DeliveryTimePresenter;
import com.mcdonalds.app.widget.AutoResizeTextView;

public class DeliveryLocationBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final RelativeLayout bottomRow;
    public final ImageView deliveryDisclosureImage;
    public final TextView deliveryFee;
    public final TextView deliveryFeeLabel;
    public final LinearLayout deliveryStoreContainer;
    public final LinearLayout deliveryTimeAddressButton;
    public final AutoResizeTextView deliveryTimeLabel;
    public final TextView estimatedArrival;
    public final TextView estimatedArrivalLabel;
    private long mDirtyFlags = -1;
    private DeliveryTimePresenter mPresenter;
    public final ImageView redIcon;

    static {
        sViewsWithIds.put(C2358R.C2357id.delivery_time_address_button, 2);
        sViewsWithIds.put(C2358R.C2357id.red_icon, 3);
        sViewsWithIds.put(C2358R.C2357id.delivery_disclosure_image, 4);
        sViewsWithIds.put(C2358R.C2357id.bottom_row, 5);
        sViewsWithIds.put(C2358R.C2357id.estimated_arrival_label, 6);
        sViewsWithIds.put(C2358R.C2357id.estimated_arrival, 7);
        sViewsWithIds.put(C2358R.C2357id.delivery_fee, 8);
        sViewsWithIds.put(C2358R.C2357id.delivery_fee_label, 9);
    }

    public DeliveryLocationBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds);
        this.bottomRow = (RelativeLayout) bindings[5];
        this.deliveryDisclosureImage = (ImageView) bindings[4];
        this.deliveryFee = (TextView) bindings[8];
        this.deliveryFeeLabel = (TextView) bindings[9];
        this.deliveryStoreContainer = (LinearLayout) bindings[0];
        this.deliveryStoreContainer.setTag(null);
        this.deliveryTimeAddressButton = (LinearLayout) bindings[2];
        this.deliveryTimeLabel = (AutoResizeTextView) bindings[1];
        this.deliveryTimeLabel.setTag(null);
        this.estimatedArrival = (TextView) bindings[7];
        this.estimatedArrivalLabel = (TextView) bindings[6];
        this.redIcon = (ImageView) bindings[3];
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

    public void setPresenter(DeliveryTimePresenter Presenter) {
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
                return onChangePresenter((DeliveryTimePresenter) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangePresenter(DeliveryTimePresenter Presenter, int fieldId) {
        Ensighten.evaluateEvent(this, "onChangePresenter", new Object[]{Presenter, new Integer(fieldId)});
        switch (fieldId) {
            case 0:
                synchronized (this) {
                    this.mDirtyFlags |= 1;
                }
                return true;
            case 5:
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
        DeliveryTimePresenter presenter = this.mPresenter;
        Spanned presenterDeliveryHeaderText = null;
        if (!((dirtyFlags & 7) == 0 || presenter == null)) {
            presenterDeliveryHeaderText = presenter.getDeliveryHeaderText();
        }
        if ((dirtyFlags & 7) != 0) {
            TextViewBindingAdapter.setText(this.deliveryTimeLabel, presenterDeliveryHeaderText);
        }
    }

    public static DeliveryLocationBinding bind(View view, DataBindingComponent bindingComponent) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.databinding.DeliveryLocationBinding", "bind", new Object[]{view, bindingComponent});
        if ("layout/delivery_location_0".equals(view.getTag())) {
            return new DeliveryLocationBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
