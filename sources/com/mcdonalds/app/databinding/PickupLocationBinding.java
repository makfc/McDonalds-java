package com.mcdonalds.app.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.util.PickupLocationPresenter;

public class PickupLocationBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final LinearLayout llContainer;
    private long mDirtyFlags = -1;
    private PickupLocationPresenter mPresenter;
    public final LinearLayout pickupStoreButton;
    public final TextView pickupStoreDistance;
    public final LinearLayout pickupStoreExtra;
    public final ImageView pickupStoreImage;
    public final ImageView pickupStoreInfoButton;
    public final TextView pickupStoreName;
    public final TextView pickupStoreTitle;
    public final ImageView redIcon;

    static {
        sViewsWithIds.put(C2358R.C2357id.red_icon, 2);
        sViewsWithIds.put(C2358R.C2357id.ll_container, 3);
        sViewsWithIds.put(C2358R.C2357id.pickup_store_title, 4);
        sViewsWithIds.put(C2358R.C2357id.pickup_store_image, 5);
        sViewsWithIds.put(C2358R.C2357id.pickup_store_extra, 6);
        sViewsWithIds.put(C2358R.C2357id.pickup_store_info_button, 7);
        sViewsWithIds.put(C2358R.C2357id.pickup_store_distance, 8);
    }

    public PickupLocationBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.llContainer = (LinearLayout) bindings[3];
        this.pickupStoreButton = (LinearLayout) bindings[0];
        this.pickupStoreButton.setTag(null);
        this.pickupStoreDistance = (TextView) bindings[8];
        this.pickupStoreExtra = (LinearLayout) bindings[6];
        this.pickupStoreImage = (ImageView) bindings[5];
        this.pickupStoreInfoButton = (ImageView) bindings[7];
        this.pickupStoreName = (TextView) bindings[1];
        this.pickupStoreName.setTag(null);
        this.pickupStoreTitle = (TextView) bindings[4];
        this.redIcon = (ImageView) bindings[2];
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

    public void setPresenter(PickupLocationPresenter Presenter) {
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
                return onChangePresenter((PickupLocationPresenter) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangePresenter(PickupLocationPresenter Presenter, int fieldId) {
        Ensighten.evaluateEvent(this, "onChangePresenter", new Object[]{Presenter, new Integer(fieldId)});
        switch (fieldId) {
            case 0:
                synchronized (this) {
                    this.mDirtyFlags |= 1;
                }
                return true;
            case 47:
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
        PickupLocationPresenter presenter = this.mPresenter;
        String presenterStoreName = null;
        if (!((dirtyFlags & 7) == 0 || presenter == null)) {
            presenterStoreName = presenter.getStoreName();
        }
        if ((dirtyFlags & 7) != 0) {
            TextViewBindingAdapter.setText(this.pickupStoreName, presenterStoreName);
        }
    }

    public static PickupLocationBinding bind(View view, DataBindingComponent bindingComponent) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.databinding.PickupLocationBinding", "bind", new Object[]{view, bindingComponent});
        if ("layout/pickup_location_0".equals(view.getTag())) {
            return new PickupLocationBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
