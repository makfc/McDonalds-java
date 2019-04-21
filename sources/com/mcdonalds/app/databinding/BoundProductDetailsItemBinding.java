package com.mcdonalds.app.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.CompoundButtonBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.util.BindingAdapters;
import com.mcdonalds.app.util.ProductDetailsItemPresenter;
import com.mcdonalds.app.widget.CheckableRelativeLayout;
import com.mcdonalds.app.widget.InertCheckBox;
import com.mcdonalds.gma.hongkong.C2658R;
import p070me.grantland.widget.AutofitTextView;

public class BoundProductDetailsItemBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final LinearLayout buttonLayout;
    public final TextView customSpecialInstructions;
    public final ImageView disclosureArrow;
    public final ImageView foodImageSmall;
    public final ImageButton hatButton;
    public final ImageButton infoButton;
    public final LinearLayout llName;
    private long mDirtyFlags = -1;
    private ProductDetailsItemPresenter mPresenter;
    public final AutofitTextView name;
    public final TextView nameDetails;
    public final TextView priceUplift;
    public final InertCheckBox productCheckBox;
    public final CheckableRelativeLayout productDetailsItem;

    static {
        sViewsWithIds.put(C2358R.C2357id.ll_name, 10);
        sViewsWithIds.put(C2358R.C2357id.button_layout, 11);
    }

    public BoundProductDetailsItemBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds);
        this.buttonLayout = (LinearLayout) bindings[11];
        this.customSpecialInstructions = (TextView) bindings[6];
        this.customSpecialInstructions.setTag(null);
        this.disclosureArrow = (ImageView) bindings[9];
        this.disclosureArrow.setTag(null);
        this.foodImageSmall = (ImageView) bindings[2];
        this.foodImageSmall.setTag(null);
        this.hatButton = (ImageButton) bindings[8];
        this.hatButton.setTag(null);
        this.infoButton = (ImageButton) bindings[7];
        this.infoButton.setTag(null);
        this.llName = (LinearLayout) bindings[10];
        this.name = (AutofitTextView) bindings[3];
        this.name.setTag(null);
        this.nameDetails = (TextView) bindings[5];
        this.nameDetails.setTag(null);
        this.priceUplift = (TextView) bindings[4];
        this.priceUplift.setTag(null);
        this.productCheckBox = (InertCheckBox) bindings[1];
        this.productCheckBox.setTag(null);
        this.productDetailsItem = (CheckableRelativeLayout) bindings[0];
        this.productDetailsItem.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        Ensighten.evaluateEvent(this, "invalidateAll", null);
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PREPARE;
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

    public void setPresenter(ProductDetailsItemPresenter Presenter) {
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
                return onChangePresenter((ProductDetailsItemPresenter) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangePresenter(ProductDetailsItemPresenter Presenter, int fieldId) {
        Ensighten.evaluateEvent(this, "onChangePresenter", new Object[]{Presenter, new Integer(fieldId)});
        switch (fieldId) {
            case 0:
                synchronized (this) {
                    this.mDirtyFlags |= 1;
                }
                return true;
            case 2:
                synchronized (this) {
                    this.mDirtyFlags |= 2;
                }
                return true;
            case 11:
                synchronized (this) {
                    this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                }
                return true;
            case 14:
                synchronized (this) {
                    this.mDirtyFlags |= 256;
                }
                return true;
            case 18:
                synchronized (this) {
                    this.mDirtyFlags |= 16;
                }
                return true;
            case 19:
                synchronized (this) {
                    this.mDirtyFlags |= 64;
                }
                return true;
            case 24:
                synchronized (this) {
                    this.mDirtyFlags |= 4;
                }
                return true;
            case 27:
                synchronized (this) {
                    this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                }
                return true;
            case 29:
                synchronized (this) {
                    this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                }
                return true;
            case 30:
                synchronized (this) {
                    this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                }
                return true;
            case 33:
                synchronized (this) {
                    this.mDirtyFlags |= 128;
                }
                return true;
            case 45:
                synchronized (this) {
                    this.mDirtyFlags |= 32;
                }
                return true;
            case 46:
                synchronized (this) {
                    this.mDirtyFlags |= 512;
                }
                return true;
            case 50:
                synchronized (this) {
                    this.mDirtyFlags |= 8;
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
        boolean presenterShowHatButton = false;
        ProductDetailsItemPresenter presenter = this.mPresenter;
        boolean presenterChecked = false;
        int presenterHatButtonResourceId = 0;
        String presenterThumbnailImageUrl = null;
        boolean presenterShowInfoButton = false;
        String presenterProductUplift = null;
        String presenterSpecialInstructions = null;
        boolean presenterShowDisclosureArrow = false;
        boolean presenterShowNameDetails = false;
        String presenterProductName = null;
        boolean presenterShowCheckBox = false;
        boolean presenterShowUplift = false;
        String presenterNameDetails = null;
        if ((32767 & dirtyFlags) != 0) {
            if (!((20481 & dirtyFlags) == 0 || presenter == null)) {
                presenterShowHatButton = presenter.getShowHatButton();
            }
            if (!((16387 & dirtyFlags) == 0 || presenter == null)) {
                presenterChecked = presenter.getChecked();
            }
            if (!((18433 & dirtyFlags) == 0 || presenter == null)) {
                presenterHatButtonResourceId = presenter.getHatButtonResourceId();
            }
            if (!((16393 & dirtyFlags) == 0 || presenter == null)) {
                presenterThumbnailImageUrl = presenter.getThumbnailImageUrl();
            }
            if (!((17409 & dirtyFlags) == 0 || presenter == null)) {
                presenterShowInfoButton = presenter.getShowInfoButton();
            }
            if (!((16449 & dirtyFlags) == 0 || presenter == null)) {
                presenterProductUplift = presenter.getProductUplift();
            }
            if (!((16897 & dirtyFlags) == 0 || presenter == null)) {
                presenterSpecialInstructions = presenter.getSpecialInstructions();
            }
            if (!((24577 & dirtyFlags) == 0 || presenter == null)) {
                presenterShowDisclosureArrow = presenter.getShowDisclosureArrow();
            }
            if (!((16513 & dirtyFlags) == 0 || presenter == null)) {
                presenterShowNameDetails = presenter.getShowNameDetails();
            }
            if (!((16401 & dirtyFlags) == 0 || presenter == null)) {
                presenterProductName = presenter.getProductName();
            }
            if (!((16389 & dirtyFlags) == 0 || presenter == null)) {
                presenterShowCheckBox = presenter.getShowCheckBox();
            }
            if (!((16417 & dirtyFlags) == 0 || presenter == null)) {
                presenterShowUplift = presenter.getShowUplift();
            }
            if (!((16641 & dirtyFlags) == 0 || presenter == null)) {
                presenterNameDetails = presenter.getNameDetails();
            }
        }
        if ((16897 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.customSpecialInstructions, presenterSpecialInstructions);
        }
        if ((24577 & dirtyFlags) != 0) {
            BindingAdapters.booleanVisibility(this.disclosureArrow, presenterShowDisclosureArrow);
        }
        if ((16393 & dirtyFlags) != 0) {
            BindingAdapters.loadImageWifhError(this.foodImageSmall, presenterThumbnailImageUrl);
        }
        if ((18433 & dirtyFlags) != 0) {
            BindingAdapters.loadResource(this.hatButton, presenterHatButtonResourceId);
        }
        if ((20481 & dirtyFlags) != 0) {
            BindingAdapters.booleanVisibility(this.hatButton, presenterShowHatButton);
        }
        if ((17409 & dirtyFlags) != 0) {
            BindingAdapters.booleanVisibility(this.infoButton, presenterShowInfoButton);
        }
        if ((16401 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.name, presenterProductName);
        }
        if ((16513 & dirtyFlags) != 0) {
            BindingAdapters.booleanVisibility(this.nameDetails, presenterShowNameDetails);
        }
        if ((16641 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.nameDetails, presenterNameDetails);
        }
        if ((16417 & dirtyFlags) != 0) {
            BindingAdapters.booleanVisibility(this.priceUplift, presenterShowUplift);
        }
        if ((16449 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.priceUplift, presenterProductUplift);
        }
        if ((16387 & dirtyFlags) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.productCheckBox, presenterChecked);
        }
        if ((16389 & dirtyFlags) != 0) {
            BindingAdapters.booleanVisibility(this.productCheckBox, presenterShowCheckBox);
        }
    }

    public static BoundProductDetailsItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.databinding.BoundProductDetailsItemBinding", "inflate", new Object[]{inflater, root, new Boolean(attachToRoot)});
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static BoundProductDetailsItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.databinding.BoundProductDetailsItemBinding", "inflate", new Object[]{inflater, root, new Boolean(attachToRoot), bindingComponent});
        return (BoundProductDetailsItemBinding) DataBindingUtil.inflate(inflater, C2658R.layout.bound_product_details_item, root, attachToRoot, bindingComponent);
    }

    public static BoundProductDetailsItemBinding bind(View view, DataBindingComponent bindingComponent) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.databinding.BoundProductDetailsItemBinding", "bind", new Object[]{view, bindingComponent});
        if ("layout/bound_product_details_item_0".equals(view.getTag())) {
            return new BoundProductDetailsItemBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
