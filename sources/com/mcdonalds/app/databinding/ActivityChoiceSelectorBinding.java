package com.mcdonalds.app.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.p001v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.View;
import com.ensighten.Ensighten;

public class ActivityChoiceSelectorBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    public final RecyclerView choiceList;
    private long mDirtyFlags = -1;

    public ActivityChoiceSelectorBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        this.choiceList = (RecyclerView) ViewDataBinding.mapBindings(bindingComponent, root, 1, sIncludes, sViewsWithIds)[0];
        this.choiceList.setTag(null);
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

    public static ActivityChoiceSelectorBinding bind(View view, DataBindingComponent bindingComponent) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.databinding.ActivityChoiceSelectorBinding", "bind", new Object[]{view, bindingComponent});
        if ("layout/activity_choice_selector_0".equals(view.getTag())) {
            return new ActivityChoiceSelectorBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
