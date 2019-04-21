package android.databinding;

import android.annotation.TargetApi;
import android.databinding.CallbackRegistry.NotifierCallback;
import android.databinding.Observable.OnPropertyChangedCallback;
import android.databinding.ObservableList.OnListChangedCallback;
import android.databinding.ObservableMap.OnMapChangedCallback;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewGroup;
import com.android.databinding.library.C1195R;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public abstract class ViewDataBinding extends BaseObservable {
    private static final int BINDING_NUMBER_START = "binding_".length();
    private static final CreateWeakListener CREATE_LIST_LISTENER = new C00142();
    private static final CreateWeakListener CREATE_MAP_LISTENER = new C00153();
    private static final CreateWeakListener CREATE_PROPERTY_LISTENER = new C00131();
    private static final NotifierCallback<OnRebindCallback, ViewDataBinding, Void> REBIND_NOTIFIER = new C00164();
    private static final OnAttachStateChangeListener ROOT_REATTACHED_LISTENER;
    static int SDK_INT = VERSION.SDK_INT;
    private static final boolean USE_CHOREOGRAPHER;
    private static final boolean USE_TAG_ID = (DataBinderMapper.TARGET_MIN_SDK >= 14);
    private static final ReferenceQueue<ViewDataBinding> sReferenceQueue = new ReferenceQueue();
    protected final DataBindingComponent mBindingComponent;
    private Choreographer mChoreographer;
    private ViewDataBinding mContainingBinding;
    private final FrameCallback mFrameCallback;
    private boolean mIsExecutingPendingBindings;
    private WeakListener[] mLocalFieldObservers;
    private boolean mPendingRebind = false;
    private CallbackRegistry<OnRebindCallback, ViewDataBinding, Void> mRebindCallbacks;
    private boolean mRebindHalted = false;
    private final Runnable mRebindRunnable = new C00186();
    private final View mRoot;
    private Handler mUIThreadHandler;

    private interface CreateWeakListener {
        WeakListener create(ViewDataBinding viewDataBinding, int i);
    }

    /* renamed from: android.databinding.ViewDataBinding$1 */
    static class C00131 implements CreateWeakListener {
        C00131() {
        }

        public WeakListener create(ViewDataBinding viewDataBinding, int localFieldId) {
            return new WeakPropertyListener(viewDataBinding, localFieldId).getListener();
        }
    }

    /* renamed from: android.databinding.ViewDataBinding$2 */
    static class C00142 implements CreateWeakListener {
        C00142() {
        }

        public WeakListener create(ViewDataBinding viewDataBinding, int localFieldId) {
            return new WeakListListener(viewDataBinding, localFieldId).getListener();
        }
    }

    /* renamed from: android.databinding.ViewDataBinding$3 */
    static class C00153 implements CreateWeakListener {
        C00153() {
        }

        public WeakListener create(ViewDataBinding viewDataBinding, int localFieldId) {
            return new WeakMapListener(viewDataBinding, localFieldId).getListener();
        }
    }

    /* renamed from: android.databinding.ViewDataBinding$4 */
    static class C00164 extends NotifierCallback<OnRebindCallback, ViewDataBinding, Void> {
        C00164() {
        }

        public void onNotifyCallback(OnRebindCallback callback, ViewDataBinding sender, int mode, Void arg2) {
            switch (mode) {
                case 1:
                    if (!callback.onPreBind(sender)) {
                        sender.mRebindHalted = true;
                        return;
                    }
                    return;
                case 2:
                    callback.onCanceled(sender);
                    return;
                case 3:
                    callback.onBound(sender);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: android.databinding.ViewDataBinding$5 */
    static class C00175 implements OnAttachStateChangeListener {
        C00175() {
        }

        @TargetApi(19)
        public void onViewAttachedToWindow(View v) {
            ViewDataBinding.getBinding(v).mRebindRunnable.run();
            v.removeOnAttachStateChangeListener(this);
        }

        public void onViewDetachedFromWindow(View v) {
        }
    }

    /* renamed from: android.databinding.ViewDataBinding$6 */
    class C00186 implements Runnable {
        C00186() {
        }

        public void run() {
            synchronized (this) {
                ViewDataBinding.this.mPendingRebind = false;
            }
            ViewDataBinding.processReferenceQueue();
            if (VERSION.SDK_INT < 19 || ViewDataBinding.this.mRoot.isAttachedToWindow()) {
                ViewDataBinding.this.executePendingBindings();
                return;
            }
            ViewDataBinding.this.mRoot.removeOnAttachStateChangeListener(ViewDataBinding.ROOT_REATTACHED_LISTENER);
            ViewDataBinding.this.mRoot.addOnAttachStateChangeListener(ViewDataBinding.ROOT_REATTACHED_LISTENER);
        }
    }

    /* renamed from: android.databinding.ViewDataBinding$7 */
    class C00197 implements FrameCallback {
        C00197() {
        }

        public void doFrame(long frameTimeNanos) {
            ViewDataBinding.this.mRebindRunnable.run();
        }
    }

    protected static class IncludedLayouts {
        public final int[][] indexes;
        public final int[][] layoutIds;
        public final String[][] layouts;

        public IncludedLayouts(int bindingCount) {
            this.layouts = new String[bindingCount][];
            this.indexes = new int[bindingCount][];
            this.layoutIds = new int[bindingCount][];
        }

        public void setIncludes(int index, String[] layouts, int[] indexes, int[] layoutIds) {
            this.layouts[index] = layouts;
            this.indexes[index] = indexes;
            this.layoutIds[index] = layoutIds;
        }
    }

    private interface ObservableReference<T> {
        void addListener(T t);

        void removeListener(T t);
    }

    protected static abstract class PropertyChangedInverseListener extends OnPropertyChangedCallback implements InverseBindingListener {
    }

    private static class WeakListListener extends OnListChangedCallback implements ObservableReference<ObservableList> {
        final WeakListener<ObservableList> mListener;

        public WeakListListener(ViewDataBinding binder, int localFieldId) {
            this.mListener = new WeakListener(binder, localFieldId, this);
        }

        public WeakListener<ObservableList> getListener() {
            return this.mListener;
        }

        public void addListener(ObservableList target) {
            target.addOnListChangedCallback(this);
        }

        public void removeListener(ObservableList target) {
            target.removeOnListChangedCallback(this);
        }

        public void onChanged(ObservableList sender) {
            ViewDataBinding binder = this.mListener.getBinder();
            if (binder != null) {
                ObservableList target = (ObservableList) this.mListener.getTarget();
                if (target == sender) {
                    binder.handleFieldChange(this.mListener.mLocalFieldId, target, 0);
                }
            }
        }

        public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {
            onChanged(sender);
        }

        public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
            onChanged(sender);
        }

        public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {
            onChanged(sender);
        }

        public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
            onChanged(sender);
        }
    }

    private static class WeakListener<T> extends WeakReference<ViewDataBinding> {
        protected final int mLocalFieldId;
        private final ObservableReference<T> mObservable;
        private T mTarget;

        public WeakListener(ViewDataBinding binder, int localFieldId, ObservableReference<T> observable) {
            super(binder, ViewDataBinding.sReferenceQueue);
            this.mLocalFieldId = localFieldId;
            this.mObservable = observable;
        }

        public void setTarget(T object) {
            unregister();
            this.mTarget = object;
            if (this.mTarget != null) {
                this.mObservable.addListener(this.mTarget);
            }
        }

        public boolean unregister() {
            boolean unregistered = false;
            if (this.mTarget != null) {
                this.mObservable.removeListener(this.mTarget);
                unregistered = true;
            }
            this.mTarget = null;
            return unregistered;
        }

        public T getTarget() {
            return this.mTarget;
        }

        /* Access modifiers changed, original: protected */
        public ViewDataBinding getBinder() {
            ViewDataBinding binder = (ViewDataBinding) get();
            if (binder == null) {
                unregister();
            }
            return binder;
        }
    }

    private static class WeakMapListener extends OnMapChangedCallback implements ObservableReference<ObservableMap> {
        final WeakListener<ObservableMap> mListener;

        public WeakMapListener(ViewDataBinding binder, int localFieldId) {
            this.mListener = new WeakListener(binder, localFieldId, this);
        }

        public WeakListener<ObservableMap> getListener() {
            return this.mListener;
        }

        public void addListener(ObservableMap target) {
            target.addOnMapChangedCallback(this);
        }

        public void removeListener(ObservableMap target) {
            target.removeOnMapChangedCallback(this);
        }

        public void onMapChanged(ObservableMap sender, Object key) {
            ViewDataBinding binder = this.mListener.getBinder();
            if (binder != null && sender == this.mListener.getTarget()) {
                binder.handleFieldChange(this.mListener.mLocalFieldId, sender, 0);
            }
        }
    }

    private static class WeakPropertyListener extends OnPropertyChangedCallback implements ObservableReference<Observable> {
        final WeakListener<Observable> mListener;

        public WeakPropertyListener(ViewDataBinding binder, int localFieldId) {
            this.mListener = new WeakListener(binder, localFieldId, this);
        }

        public WeakListener<Observable> getListener() {
            return this.mListener;
        }

        public void addListener(Observable target) {
            target.addOnPropertyChangedCallback(this);
        }

        public void removeListener(Observable target) {
            target.removeOnPropertyChangedCallback(this);
        }

        public void onPropertyChanged(Observable sender, int propertyId) {
            ViewDataBinding binder = this.mListener.getBinder();
            if (binder != null && ((Observable) this.mListener.getTarget()) == sender) {
                binder.handleFieldChange(this.mListener.mLocalFieldId, sender, propertyId);
            }
        }
    }

    public abstract void executeBindings();

    public abstract boolean hasPendingBindings();

    public abstract void invalidateAll();

    public abstract boolean onFieldChange(int i, Object obj, int i2);

    static {
        boolean z = true;
        if (SDK_INT < 16) {
            z = false;
        }
        USE_CHOREOGRAPHER = z;
        if (VERSION.SDK_INT < 19) {
            ROOT_REATTACHED_LISTENER = null;
        } else {
            ROOT_REATTACHED_LISTENER = new C00175();
        }
    }

    protected ViewDataBinding(DataBindingComponent bindingComponent, View root, int localFieldCount) {
        this.mBindingComponent = bindingComponent;
        this.mLocalFieldObservers = new WeakListener[localFieldCount];
        this.mRoot = root;
        if (Looper.myLooper() == null) {
            throw new IllegalStateException("DataBinding must be created in view's UI Thread");
        } else if (USE_CHOREOGRAPHER) {
            this.mChoreographer = Choreographer.getInstance();
            this.mFrameCallback = new C00197();
        } else {
            this.mFrameCallback = null;
            this.mUIThreadHandler = new Handler(Looper.myLooper());
        }
    }

    /* Access modifiers changed, original: protected */
    public void setRootTag(View view) {
        if (USE_TAG_ID) {
            view.setTag(C1195R.C1194id.dataBinding, this);
        } else {
            view.setTag(this);
        }
    }

    public void executePendingBindings() {
        if (this.mContainingBinding == null) {
            executeBindingsInternal();
        } else {
            this.mContainingBinding.executePendingBindings();
        }
    }

    private void executeBindingsInternal() {
        if (this.mIsExecutingPendingBindings) {
            requestRebind();
        } else if (hasPendingBindings()) {
            this.mIsExecutingPendingBindings = true;
            this.mRebindHalted = false;
            if (this.mRebindCallbacks != null) {
                this.mRebindCallbacks.notifyCallbacks(this, 1, null);
                if (this.mRebindHalted) {
                    this.mRebindCallbacks.notifyCallbacks(this, 2, null);
                }
            }
            if (!this.mRebindHalted) {
                executeBindings();
                if (this.mRebindCallbacks != null) {
                    this.mRebindCallbacks.notifyCallbacks(this, 3, null);
                }
            }
            this.mIsExecutingPendingBindings = false;
        }
    }

    protected static void executeBindingsOn(ViewDataBinding other) {
        other.executeBindingsInternal();
    }

    /* Access modifiers changed, original: 0000 */
    public void forceExecuteBindings() {
        executeBindings();
    }

    static ViewDataBinding getBinding(View v) {
        if (v != null) {
            if (USE_TAG_ID) {
                return (ViewDataBinding) v.getTag(C1195R.C1194id.dataBinding);
            }
            Object tag = v.getTag();
            if (tag instanceof ViewDataBinding) {
                return (ViewDataBinding) tag;
            }
        }
        return null;
    }

    public View getRoot() {
        return this.mRoot;
    }

    private void handleFieldChange(int mLocalFieldId, Object object, int fieldId) {
        if (onFieldChange(mLocalFieldId, object, fieldId)) {
            requestRebind();
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean unregisterFrom(int localFieldId) {
        WeakListener listener = this.mLocalFieldObservers[localFieldId];
        if (listener != null) {
            return listener.unregister();
        }
        return false;
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Missing block: B:17:0x001a, code skipped:
            if (USE_CHOREOGRAPHER == false) goto L_0x0024;
     */
    /* JADX WARNING: Missing block: B:18:0x001c, code skipped:
            r2.mChoreographer.postFrameCallback(r2.mFrameCallback);
     */
    /* JADX WARNING: Missing block: B:19:0x0024, code skipped:
            r2.mUIThreadHandler.post(r2.mRebindRunnable);
     */
    /* JADX WARNING: Missing block: B:22:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:23:?, code skipped:
            return;
     */
    public void requestRebind() {
        /*
        r2 = this;
        r0 = r2.mContainingBinding;
        if (r0 == 0) goto L_0x000a;
    L_0x0004:
        r0 = r2.mContainingBinding;
        r0.requestRebind();
    L_0x0009:
        return;
    L_0x000a:
        monitor-enter(r2);
        r0 = r2.mPendingRebind;	 Catch:{ all -> 0x0011 }
        if (r0 == 0) goto L_0x0014;
    L_0x000f:
        monitor-exit(r2);	 Catch:{ all -> 0x0011 }
        goto L_0x0009;
    L_0x0011:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0011 }
        throw r0;
    L_0x0014:
        r0 = 1;
        r2.mPendingRebind = r0;	 Catch:{ all -> 0x0011 }
        monitor-exit(r2);	 Catch:{ all -> 0x0011 }
        r0 = USE_CHOREOGRAPHER;
        if (r0 == 0) goto L_0x0024;
    L_0x001c:
        r0 = r2.mChoreographer;
        r1 = r2.mFrameCallback;
        r0.postFrameCallback(r1);
        goto L_0x0009;
    L_0x0024:
        r0 = r2.mUIThreadHandler;
        r1 = r2.mRebindRunnable;
        r0.post(r1);
        goto L_0x0009;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.databinding.ViewDataBinding.requestRebind():void");
    }

    private boolean updateRegistration(int localFieldId, Object observable, CreateWeakListener listenerCreator) {
        if (observable == null) {
            return unregisterFrom(localFieldId);
        }
        WeakListener listener = this.mLocalFieldObservers[localFieldId];
        if (listener == null) {
            registerTo(localFieldId, observable, listenerCreator);
            return true;
        } else if (listener.getTarget() == observable) {
            return false;
        } else {
            unregisterFrom(localFieldId);
            registerTo(localFieldId, observable, listenerCreator);
            return true;
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean updateRegistration(int localFieldId, Observable observable) {
        return updateRegistration(localFieldId, observable, CREATE_PROPERTY_LISTENER);
    }

    /* Access modifiers changed, original: protected */
    public void ensureBindingComponentIsNotNull(Class<?> oneExample) {
        if (this.mBindingComponent == null) {
            throw new IllegalStateException("Required DataBindingComponent is null in class " + getClass().getSimpleName() + ". A BindingAdapter in " + oneExample.getCanonicalName() + " is not static and requires an object to use, retrieved from the " + "DataBindingComponent. If you don't use an inflation method taking a " + "DataBindingComponent, use DataBindingUtil.setDefaultComponent or " + "make all BindingAdapter methods static.");
        }
    }

    /* Access modifiers changed, original: protected */
    public void registerTo(int localFieldId, Object observable, CreateWeakListener listenerCreator) {
        if (observable != null) {
            WeakListener listener = this.mLocalFieldObservers[localFieldId];
            if (listener == null) {
                listener = listenerCreator.create(this, localFieldId);
                this.mLocalFieldObservers[localFieldId] = listener;
            }
            listener.setTarget(observable);
        }
    }

    protected static Object[] mapBindings(DataBindingComponent bindingComponent, View root, int numBindings, IncludedLayouts includes, SparseIntArray viewsWithIds) {
        Object[] bindings = new Object[numBindings];
        mapBindings(bindingComponent, root, bindings, includes, viewsWithIds, true);
        return bindings;
    }

    /* Access modifiers changed, original: protected */
    public void setContainedBinding(ViewDataBinding included) {
        if (included != null) {
            included.mContainingBinding = this;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:75:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:75:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:75:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x006c  */
    private static void mapBindings(android.databinding.DataBindingComponent r30, android.view.View r31, java.lang.Object[] r32, android.databinding.ViewDataBinding.IncludedLayouts r33, android.util.SparseIntArray r34, boolean r35) {
        /*
        r11 = getBinding(r31);
        if (r11 == 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r25 = r31.getTag();
        r0 = r25;
        r3 = r0 instanceof java.lang.String;
        if (r3 == 0) goto L_0x00ee;
    L_0x0011:
        r25 = (java.lang.String) r25;
        r26 = r25;
    L_0x0015:
        r19 = 0;
        if (r35 == 0) goto L_0x00fa;
    L_0x0019:
        if (r26 == 0) goto L_0x00fa;
    L_0x001b:
        r3 = "layout";
        r0 = r26;
        r3 = r0.startsWith(r3);
        if (r3 == 0) goto L_0x00fa;
    L_0x0025:
        r3 = 95;
        r0 = r26;
        r28 = r0.lastIndexOf(r3);
        if (r28 <= 0) goto L_0x00f6;
    L_0x002f:
        r3 = r28 + 1;
        r0 = r26;
        r3 = isNumeric(r0, r3);
        if (r3 == 0) goto L_0x00f6;
    L_0x0039:
        r3 = r28 + 1;
        r0 = r26;
        r17 = parseTagInt(r0, r3);
        r3 = r32[r17];
        if (r3 != 0) goto L_0x0047;
    L_0x0045:
        r32[r17] = r31;
    L_0x0047:
        if (r33 != 0) goto L_0x00f2;
    L_0x0049:
        r18 = -1;
    L_0x004b:
        r19 = 1;
    L_0x004d:
        if (r19 != 0) goto L_0x0066;
    L_0x004f:
        r13 = r31.getId();
        if (r13 <= 0) goto L_0x0066;
    L_0x0055:
        if (r34 == 0) goto L_0x0066;
    L_0x0057:
        r3 = -1;
        r0 = r34;
        r17 = r0.get(r13, r3);
        if (r17 < 0) goto L_0x0066;
    L_0x0060:
        r3 = r32[r17];
        if (r3 != 0) goto L_0x0066;
    L_0x0064:
        r32[r17] = r31;
    L_0x0066:
        r0 = r31;
        r3 = r0 instanceof android.view.ViewGroup;
        if (r3 == 0) goto L_0x0006;
    L_0x006c:
        r29 = r31;
        r29 = (android.view.ViewGroup) r29;
        r10 = r29.getChildCount();
        r24 = 0;
        r12 = 0;
    L_0x0077:
        if (r12 >= r10) goto L_0x0006;
    L_0x0079:
        r0 = r29;
        r4 = r0.getChildAt(r12);
        r20 = 0;
        if (r18 < 0) goto L_0x00dd;
    L_0x0083:
        r3 = r4.getTag();
        r3 = r3 instanceof java.lang.String;
        if (r3 == 0) goto L_0x00dd;
    L_0x008b:
        r9 = r4.getTag();
        r9 = (java.lang.String) r9;
        r3 = "_0";
        r3 = r9.endsWith(r3);
        if (r3 == 0) goto L_0x00dd;
    L_0x0099:
        r3 = "layout";
        r3 = r9.startsWith(r3);
        if (r3 == 0) goto L_0x00dd;
    L_0x00a1:
        r3 = 47;
        r3 = r9.indexOf(r3);
        if (r3 <= 0) goto L_0x00dd;
    L_0x00a9:
        r0 = r24;
        r1 = r33;
        r2 = r18;
        r15 = findIncludeIndex(r9, r0, r1, r2);
        if (r15 < 0) goto L_0x00dd;
    L_0x00b5:
        r20 = 1;
        r24 = r15 + 1;
        r0 = r33;
        r3 = r0.indexes;
        r3 = r3[r18];
        r17 = r3[r15];
        r0 = r33;
        r3 = r0.layoutIds;
        r3 = r3[r18];
        r23 = r3[r15];
        r0 = r29;
        r22 = findLastMatching(r0, r12);
        r0 = r22;
        if (r0 != r12) goto L_0x0123;
    L_0x00d3:
        r0 = r30;
        r1 = r23;
        r3 = android.databinding.DataBindingUtil.bind(r0, r4, r1);
        r32[r17] = r3;
    L_0x00dd:
        if (r20 != 0) goto L_0x00eb;
    L_0x00df:
        r8 = 0;
        r3 = r30;
        r5 = r32;
        r6 = r33;
        r7 = r34;
        mapBindings(r3, r4, r5, r6, r7, r8);
    L_0x00eb:
        r12 = r12 + 1;
        goto L_0x0077;
    L_0x00ee:
        r26 = 0;
        goto L_0x0015;
    L_0x00f2:
        r18 = r17;
        goto L_0x004b;
    L_0x00f6:
        r18 = -1;
        goto L_0x004d;
    L_0x00fa:
        if (r26 == 0) goto L_0x011f;
    L_0x00fc:
        r3 = "binding_";
        r0 = r26;
        r3 = r0.startsWith(r3);
        if (r3 == 0) goto L_0x011f;
    L_0x0106:
        r3 = BINDING_NUMBER_START;
        r0 = r26;
        r27 = parseTagInt(r0, r3);
        r3 = r32[r27];
        if (r3 != 0) goto L_0x0114;
    L_0x0112:
        r32[r27] = r31;
    L_0x0114:
        r19 = 1;
        if (r33 != 0) goto L_0x011c;
    L_0x0118:
        r18 = -1;
    L_0x011a:
        goto L_0x004d;
    L_0x011c:
        r18 = r27;
        goto L_0x011a;
    L_0x011f:
        r18 = -1;
        goto L_0x004d;
    L_0x0123:
        r3 = r22 - r12;
        r14 = r3 + 1;
        r0 = new android.view.View[r14];
        r16 = r0;
        r21 = 0;
    L_0x012d:
        r0 = r21;
        if (r0 >= r14) goto L_0x013e;
    L_0x0131:
        r3 = r12 + r21;
        r0 = r29;
        r3 = r0.getChildAt(r3);
        r16[r21] = r3;
        r21 = r21 + 1;
        goto L_0x012d;
    L_0x013e:
        r0 = r30;
        r1 = r16;
        r2 = r23;
        r3 = android.databinding.DataBindingUtil.bind(r0, r1, r2);
        r32[r17] = r3;
        r3 = r14 + -1;
        r12 = r12 + r3;
        goto L_0x00dd;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.databinding.ViewDataBinding.mapBindings(android.databinding.DataBindingComponent, android.view.View, java.lang.Object[], android.databinding.ViewDataBinding$IncludedLayouts, android.util.SparseIntArray, boolean):void");
    }

    private static int findIncludeIndex(String tag, int minInclude, IncludedLayouts included, int includedIndex) {
        CharSequence layoutName = tag.subSequence(tag.indexOf(47) + 1, tag.length() - 2);
        String[] layouts = included.layouts[includedIndex];
        int length = layouts.length;
        for (int i = minInclude; i < length; i++) {
            if (TextUtils.equals(layoutName, layouts[i])) {
                return i;
            }
        }
        return -1;
    }

    private static int findLastMatching(ViewGroup viewGroup, int firstIncludedIndex) {
        String firstViewTag = (String) viewGroup.getChildAt(firstIncludedIndex).getTag();
        String tagBase = firstViewTag.substring(0, firstViewTag.length() - 1);
        int tagSequenceIndex = tagBase.length();
        int count = viewGroup.getChildCount();
        int max = firstIncludedIndex;
        for (int i = firstIncludedIndex + 1; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            String tag = view.getTag() instanceof String ? (String) view.getTag() : null;
            if (tag != null && tag.startsWith(tagBase)) {
                if (tag.length() == firstViewTag.length() && tag.charAt(tag.length() - 1) == '0') {
                    break;
                } else if (isNumeric(tag, tagSequenceIndex)) {
                    max = i;
                }
            }
        }
        return max;
    }

    private static boolean isNumeric(String tag, int startIndex) {
        int length = tag.length();
        if (length == startIndex) {
            return false;
        }
        for (int i = startIndex; i < length; i++) {
            if (!Character.isDigit(tag.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static int parseTagInt(String str, int startIndex) {
        int val = 0;
        for (int i = startIndex; i < str.length(); i++) {
            val = (val * 10) + (str.charAt(i) - 48);
        }
        return val;
    }

    private static void processReferenceQueue() {
        while (true) {
            Reference<? extends ViewDataBinding> ref = sReferenceQueue.poll();
            if (ref == null) {
                return;
            }
            if (ref instanceof WeakListener) {
                ((WeakListener) ref).unregister();
            }
        }
    }
}
