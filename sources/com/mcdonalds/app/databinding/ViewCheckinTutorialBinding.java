package com.mcdonalds.app.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;

public class ViewCheckinTutorialBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final Button scanCancelButton;
    public final Button scanContinueButton;

    static {
        sViewsWithIds.put(C2358R.C2357id.scan_cancel_button, 1);
        sViewsWithIds.put(C2358R.C2357id.scan_continue_button, 2);
    }

    public ViewCheckinTutorialBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.scanCancelButton = (Button) bindings[1];
        this.scanContinueButton = (Button) bindings[2];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        Ensighten.evaluateEvent(this, "invalidateAll", null);
        synchronized (this) {
            this.mDirtyFlags = 1;
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

    /* Access modifiers changed, original: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        Ensighten.evaluateEvent(this, "onFieldChange", new Object[]{new Integer(localFieldId), object, new Integer(fieldId)});
        return false;
    }

    /* Access modifiers changed, original: protected */
    public void executeBindings() {
        Ensighten.evaluateEvent(this, "executeBindings", null);
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
    }

    public static ViewCheckinTutorialBinding bind(View view, DataBindingComponent bindingComponent) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.databinding.ViewCheckinTutorialBinding", "bind", new Object[]{view, bindingComponent});
        if ("layout/view_checkin_tutorial_0".equals(view.getTag())) {
            return new ViewCheckinTutorialBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
