package android.databinding;

import android.view.View;
import android.view.ViewStub;
import android.view.ViewStub.OnInflateListener;

public class ViewStubProxy {
    private ViewDataBinding mContainingBinding;
    private OnInflateListener mOnInflateListener;
    private View mRoot;
    private ViewDataBinding mViewDataBinding;
    private ViewStub mViewStub;

    /* renamed from: android.databinding.ViewStubProxy$1 */
    class C00201 implements OnInflateListener {
        final /* synthetic */ ViewStubProxy this$0;

        public void onInflate(ViewStub stub, View inflated) {
            this.this$0.mRoot = inflated;
            this.this$0.mViewDataBinding = DataBindingUtil.bind(this.this$0.mContainingBinding.mBindingComponent, inflated, stub.getLayoutResource());
            this.this$0.mViewStub = null;
            if (this.this$0.mOnInflateListener != null) {
                this.this$0.mOnInflateListener.onInflate(stub, inflated);
                this.this$0.mOnInflateListener = null;
            }
            this.this$0.mContainingBinding.invalidateAll();
            this.this$0.mContainingBinding.forceExecuteBindings();
        }
    }
}
