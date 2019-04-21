package com.mcdonalds.app.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.InverseBindingListener;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.CompoundButtonBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter.AfterTextChanged;
import android.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged;
import android.databinding.adapters.TextViewBindingAdapter.OnTextChanged;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import com.ensighten.Ensighten;
import com.mcdonalds.app.util.BindingAdapters;
import com.mcdonalds.app.util.OrderRemarkPresenter;

public class OrderRemarkBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags = -1;
    private OrderRemarkPresenter mPresenter;
    private final LinearLayout mboundView0;
    public final EditText orderRemarkContent;
    private InverseBindingListener orderRemarkContentandroidTextAttrChanged = new C31141();
    public final Switch orderRemarkSwitch;
    private InverseBindingListener orderRemarkSwitchandroidCheckedAttrChanged = new C31152();
    public final LinearLayout remarkContainer;

    /* renamed from: com.mcdonalds.app.databinding.OrderRemarkBinding$1 */
    class C31141 implements InverseBindingListener {
        C31141() {
        }

        public void onChange() {
            Ensighten.evaluateEvent(this, "onChange", null);
            String callbackArg_0 = TextViewBindingAdapter.getTextString(OrderRemarkBinding.this.orderRemarkContent);
            OrderRemarkPresenter presenter = Ensighten.evaluateEvent(null, "com.mcdonalds.app.databinding.OrderRemarkBinding", "access$000", new Object[]{OrderRemarkBinding.this});
            if (presenter != null) {
                presenter.setRemark(callbackArg_0);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.databinding.OrderRemarkBinding$2 */
    class C31152 implements InverseBindingListener {
        C31152() {
        }

        public void onChange() {
            Ensighten.evaluateEvent(this, "onChange", null);
            boolean callbackArg_0 = OrderRemarkBinding.this.orderRemarkSwitch.isChecked();
            OrderRemarkPresenter presenter = Ensighten.evaluateEvent(null, "com.mcdonalds.app.databinding.OrderRemarkBinding", "access$000", new Object[]{OrderRemarkBinding.this});
            if (presenter != null) {
                presenter.setRemarkEnabled(callbackArg_0);
            }
        }
    }

    public OrderRemarkBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.orderRemarkContent = (EditText) bindings[3];
        this.orderRemarkContent.setTag(null);
        this.orderRemarkSwitch = (Switch) bindings[1];
        this.orderRemarkSwitch.setTag(null);
        this.remarkContainer = (LinearLayout) bindings[2];
        this.remarkContainer.setTag(null);
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

    public void setPresenter(OrderRemarkPresenter Presenter) {
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
                return onChangePresenter((OrderRemarkPresenter) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangePresenter(OrderRemarkPresenter Presenter, int fieldId) {
        Ensighten.evaluateEvent(this, "onChangePresenter", new Object[]{Presenter, new Integer(fieldId)});
        switch (fieldId) {
            case 0:
                synchronized (this) {
                    this.mDirtyFlags |= 1;
                }
                return true;
            case 21:
                synchronized (this) {
                    this.mDirtyFlags |= 4;
                }
                return true;
            case 22:
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
        OrderRemarkPresenter presenter = this.mPresenter;
        String presenterRemark = null;
        boolean presenterRemarkEnabled = false;
        if ((15 & dirtyFlags) != 0) {
            if (!((13 & dirtyFlags) == 0 || presenter == null)) {
                presenterRemark = presenter.getRemark();
            }
            if (!((11 & dirtyFlags) == 0 || presenter == null)) {
                presenterRemarkEnabled = presenter.getRemarkEnabled();
            }
        }
        if ((13 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.orderRemarkContent, presenterRemark);
        }
        if ((8 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.orderRemarkContent, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.orderRemarkContentandroidTextAttrChanged);
            CompoundButtonBindingAdapter.setListeners(this.orderRemarkSwitch, (OnCheckedChangeListener) null, this.orderRemarkSwitchandroidCheckedAttrChanged);
        }
        if ((11 & dirtyFlags) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.orderRemarkSwitch, presenterRemarkEnabled);
            BindingAdapters.booleanVisibility(this.remarkContainer, presenterRemarkEnabled);
        }
    }

    public static OrderRemarkBinding bind(View view, DataBindingComponent bindingComponent) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.databinding.OrderRemarkBinding", "bind", new Object[]{view, bindingComponent});
        if ("layout/order_remark_0".equals(view.getTag())) {
            return new OrderRemarkBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
