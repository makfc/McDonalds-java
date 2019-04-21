package com.mcdonalds.app.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.payment.PaymentActivity.TakeOutButtonStyleAdapter;
import com.mcdonalds.app.ordering.payment.PaymentPresenter;
import com.mcdonalds.app.util.BindingAdapters;

public class ActivityPaymentBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button eatinButton;
    private long mDirtyFlags = -1;
    private PaymentPresenter mPresenter;
    public final LinearLayout mainView;
    public final Button tableServiceButton;
    public final Button takeoutButton;
    public final TextView textView;
    public final TextView textView2;

    static {
        sViewsWithIds.put(C2358R.C2357id.textView2, 3);
        sViewsWithIds.put(C2358R.C2357id.textView, 4);
        sViewsWithIds.put(C2358R.C2357id.eatin_button, 5);
    }

    public ActivityPaymentBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        ensureBindingComponentIsNotNull(TakeOutButtonStyleAdapter.class);
        this.eatinButton = (Button) bindings[5];
        this.mainView = (LinearLayout) bindings[0];
        this.mainView.setTag(null);
        this.tableServiceButton = (Button) bindings[1];
        this.tableServiceButton.setTag(null);
        this.takeoutButton = (Button) bindings[2];
        this.takeoutButton.setTag(null);
        this.textView = (TextView) bindings[4];
        this.textView2 = (TextView) bindings[3];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        Ensighten.evaluateEvent(this, "invalidateAll", null);
        synchronized (this) {
            this.mDirtyFlags = 8;
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

    public void setPresenter(PaymentPresenter Presenter) {
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
                return onChangePresenter((PaymentPresenter) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangePresenter(PaymentPresenter Presenter, int fieldId) {
        Ensighten.evaluateEvent(this, "onChangePresenter", new Object[]{Presenter, new Integer(fieldId)});
        switch (fieldId) {
            case 0:
                synchronized (this) {
                    this.mDirtyFlags |= 1;
                }
                return true;
            case 32:
                synchronized (this) {
                    this.mDirtyFlags |= 2;
                }
                return true;
            case 40:
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
        PaymentPresenter presenter = this.mPresenter;
        boolean presenterShowTableService = false;
        boolean presenterShowLocationSelection = false;
        if ((15 & dirtyFlags) != 0) {
            if (!((dirtyFlags & 13) == 0 || presenter == null)) {
                presenterShowTableService = presenter.getShowTableService();
            }
            if (!((dirtyFlags & 11) == 0 || presenter == null)) {
                presenterShowLocationSelection = presenter.getShowLocationSelection();
            }
        }
        if ((dirtyFlags & 11) != 0) {
            BindingAdapters.booleanVisibility(this.mainView, presenterShowLocationSelection);
        }
        if ((dirtyFlags & 13) != 0) {
            BindingAdapters.booleanVisibility(this.tableServiceButton, presenterShowTableService);
            this.mBindingComponent.getTakeOutButtonStyleAdapter().isRed(this.takeoutButton, presenterShowTableService);
        }
    }

    public static ActivityPaymentBinding bind(View view, DataBindingComponent bindingComponent) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.databinding.ActivityPaymentBinding", "bind", new Object[]{view, bindingComponent});
        if ("layout/activity_payment_0".equals(view.getTag())) {
            return new ActivityPaymentBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
