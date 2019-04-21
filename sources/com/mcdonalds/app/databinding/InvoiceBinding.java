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
import com.mcdonalds.app.util.InvoicePresenter;

public class InvoiceBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    public final LinearLayout companyContainer;
    public final EditText companyName;
    private InverseBindingListener companyNameandroidTextAttrChanged = new C31121();
    public final Switch invoiceSwitch;
    private InverseBindingListener invoiceSwitchandroidCheckedAttrChanged = new C31132();
    private long mDirtyFlags = -1;
    private InvoicePresenter mPresenter;
    private final LinearLayout mboundView0;

    /* renamed from: com.mcdonalds.app.databinding.InvoiceBinding$1 */
    class C31121 implements InverseBindingListener {
        C31121() {
        }

        public void onChange() {
            Ensighten.evaluateEvent(this, "onChange", null);
            String callbackArg_0 = TextViewBindingAdapter.getTextString(InvoiceBinding.this.companyName);
            InvoicePresenter presenter = Ensighten.evaluateEvent(null, "com.mcdonalds.app.databinding.InvoiceBinding", "access$000", new Object[]{InvoiceBinding.this});
            if (presenter != null) {
                presenter.setPayer(callbackArg_0);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.databinding.InvoiceBinding$2 */
    class C31132 implements InverseBindingListener {
        C31132() {
        }

        public void onChange() {
            Ensighten.evaluateEvent(this, "onChange", null);
            boolean callbackArg_0 = InvoiceBinding.this.invoiceSwitch.isChecked();
            InvoicePresenter presenter = Ensighten.evaluateEvent(null, "com.mcdonalds.app.databinding.InvoiceBinding", "access$000", new Object[]{InvoiceBinding.this});
            if (presenter != null) {
                presenter.setInvoiceEnabled(callbackArg_0);
            }
        }
    }

    public InvoiceBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.companyContainer = (LinearLayout) bindings[2];
        this.companyContainer.setTag(null);
        this.companyName = (EditText) bindings[3];
        this.companyName.setTag(null);
        this.invoiceSwitch = (Switch) bindings[1];
        this.invoiceSwitch.setTag(null);
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
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

    public void setPresenter(InvoicePresenter Presenter) {
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
                return onChangePresenter((InvoicePresenter) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangePresenter(InvoicePresenter Presenter, int fieldId) {
        Ensighten.evaluateEvent(this, "onChangePresenter", new Object[]{Presenter, new Integer(fieldId)});
        switch (fieldId) {
            case 0:
                synchronized (this) {
                    this.mDirtyFlags |= 1;
                }
                return true;
            case 12:
                synchronized (this) {
                    this.mDirtyFlags |= 2;
                }
                return true;
            case 15:
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
        InvoicePresenter presenter = this.mPresenter;
        boolean presenterInvoiceEnabled = false;
        String presenterPayer = null;
        if ((15 & dirtyFlags) != 0) {
            if (!((11 & dirtyFlags) == 0 || presenter == null)) {
                presenterInvoiceEnabled = presenter.getInvoiceEnabled();
            }
            if (!((13 & dirtyFlags) == 0 || presenter == null)) {
                presenterPayer = presenter.getPayer();
            }
        }
        if ((11 & dirtyFlags) != 0) {
            BindingAdapters.booleanVisibility(this.companyContainer, presenterInvoiceEnabled);
            CompoundButtonBindingAdapter.setChecked(this.invoiceSwitch, presenterInvoiceEnabled);
        }
        if ((13 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.companyName, presenterPayer);
        }
        if ((8 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.companyName, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.companyNameandroidTextAttrChanged);
            CompoundButtonBindingAdapter.setListeners(this.invoiceSwitch, (OnCheckedChangeListener) null, this.invoiceSwitchandroidCheckedAttrChanged);
        }
    }

    public static InvoiceBinding bind(View view, DataBindingComponent bindingComponent) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.databinding.InvoiceBinding", "bind", new Object[]{view, bindingComponent});
        if ("layout/invoice_0".equals(view.getTag())) {
            return new InvoiceBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
