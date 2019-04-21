package com.facebook.stetho.inspector.elements.android;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.Predicate;
import com.facebook.stetho.common.ThreadBound;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.common.android.ViewUtil;
import com.facebook.stetho.inspector.elements.Descriptor;
import com.facebook.stetho.inspector.elements.DescriptorMap;
import com.facebook.stetho.inspector.elements.DocumentProvider;
import com.facebook.stetho.inspector.elements.DocumentProviderListener;
import com.facebook.stetho.inspector.elements.NodeDescriptor;
import com.facebook.stetho.inspector.elements.ObjectDescriptor;
import com.facebook.stetho.inspector.helper.ThreadBoundProxy;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

final class AndroidDocumentProvider extends ThreadBoundProxy implements DocumentProvider, AndroidDescriptorHost {
    private static final int INSPECT_HOVER_COLOR = 1077952767;
    private static final int INSPECT_OVERLAY_COLOR = 1090519039;
    private static final long REPORT_CHANGED_INTERVAL_MS = 1000;
    private final Application mApplication;
    private final DescriptorMap mDescriptorMap;
    private final AndroidDocumentRoot mDocumentRoot;
    private final ViewHighlighter mHighlighter;
    private final InspectModeHandler mInspectModeHandler;
    private boolean mIsReportChangesTimerPosted = false;
    @Nullable
    private DocumentProviderListener mListener;
    private final Runnable mReportChangesTimer = new C19861();

    /* renamed from: com.facebook.stetho.inspector.elements.android.AndroidDocumentProvider$1 */
    class C19861 implements Runnable {
        C19861() {
        }

        public void run() {
            AndroidDocumentProvider.this.mIsReportChangesTimerPosted = false;
            if (AndroidDocumentProvider.this.mListener != null) {
                AndroidDocumentProvider.this.mListener.onPossiblyChanged();
                AndroidDocumentProvider.this.mIsReportChangesTimerPosted = true;
                AndroidDocumentProvider.this.postDelayed(this, 1000);
            }
        }
    }

    private final class InspectModeHandler {
        private List<View> mOverlays;
        private final Predicate<View> mViewSelector;

        /* renamed from: com.facebook.stetho.inspector.elements.android.AndroidDocumentProvider$InspectModeHandler$1 */
        class C19881 implements Predicate<View> {
            C19881() {
            }

            public boolean apply(View view) {
                return !(view instanceof DocumentHiddenView);
            }
        }

        /* renamed from: com.facebook.stetho.inspector.elements.android.AndroidDocumentProvider$InspectModeHandler$2 */
        class C19892 implements Accumulator<Window> {
            C19892() {
            }

            public void store(Window object) {
                if (object.peekDecorView() instanceof ViewGroup) {
                    ViewGroup decorView = (ViewGroup) object.peekDecorView();
                    OverlayView overlayView = new OverlayView(AndroidDocumentProvider.this.mApplication);
                    LayoutParams layoutParams = new LayoutParams();
                    layoutParams.width = -1;
                    layoutParams.height = -1;
                    decorView.addView(overlayView, layoutParams);
                    decorView.bringChildToFront(overlayView);
                    InspectModeHandler.this.mOverlays.add(overlayView);
                }
            }
        }

        private final class OverlayView extends DocumentHiddenView {
            public OverlayView(Context context) {
                super(context);
            }

            /* Access modifiers changed, original: protected */
            public void onDraw(Canvas canvas) {
                canvas.drawColor(AndroidDocumentProvider.INSPECT_OVERLAY_COLOR);
                super.onDraw(canvas);
            }

            public boolean onTouchEvent(MotionEvent event) {
                if (getParent() instanceof View) {
                    View view = ViewUtil.hitTest((View) getParent(), event.getX(), event.getY(), InspectModeHandler.this.mViewSelector);
                    if (!(event.getAction() == 3 || view == null)) {
                        AndroidDocumentProvider.this.mHighlighter.setHighlightedView(view, AndroidDocumentProvider.INSPECT_HOVER_COLOR);
                        if (event.getAction() == 1 && AndroidDocumentProvider.this.mListener != null) {
                            AndroidDocumentProvider.this.mListener.onInspectRequested(view);
                        }
                    }
                }
                return true;
            }
        }

        private InspectModeHandler() {
            this.mViewSelector = new C19881();
        }

        /* synthetic */ InspectModeHandler(AndroidDocumentProvider x0, C19861 x1) {
            this();
        }

        public void enable() {
            AndroidDocumentProvider.this.verifyThreadAccess();
            if (this.mOverlays != null) {
                disable();
            }
            this.mOverlays = new ArrayList();
            AndroidDocumentProvider.this.getWindows(new C19892());
        }

        public void disable() {
            AndroidDocumentProvider.this.verifyThreadAccess();
            if (this.mOverlays != null) {
                for (int i = 0; i < this.mOverlays.size(); i++) {
                    View overlayView = (View) this.mOverlays.get(i);
                    ((ViewGroup) overlayView.getParent()).removeView(overlayView);
                }
                this.mOverlays = null;
            }
        }
    }

    public AndroidDocumentProvider(Application application, ThreadBound enforcer) {
        super(enforcer);
        this.mApplication = (Application) Util.throwIfNull(application);
        this.mDocumentRoot = new AndroidDocumentRoot(application);
        this.mDescriptorMap = new DescriptorMap().beginInit().register(Activity.class, new ActivityDescriptor()).register(AndroidDocumentRoot.class, this.mDocumentRoot).register(Application.class, new ApplicationDescriptor()).register(Dialog.class, new DialogDescriptor());
        DialogFragmentDescriptor.register(this.mDescriptorMap);
        FragmentDescriptor.register(this.mDescriptorMap).register(Object.class, new ObjectDescriptor()).register(TextView.class, new TextViewDescriptor()).register(View.class, new ViewDescriptor()).register(ViewGroup.class, new ViewGroupDescriptor()).register(Window.class, new WindowDescriptor()).setHost(this).endInit();
        this.mHighlighter = ViewHighlighter.newInstance();
        this.mInspectModeHandler = new InspectModeHandler(this, null);
    }

    public void dispose() {
        verifyThreadAccess();
        this.mHighlighter.clearHighlight();
        this.mInspectModeHandler.disable();
        removeCallbacks(this.mReportChangesTimer);
        this.mIsReportChangesTimerPosted = false;
        this.mListener = null;
    }

    public void setListener(DocumentProviderListener listener) {
        verifyThreadAccess();
        this.mListener = listener;
        if (this.mListener == null && this.mIsReportChangesTimerPosted) {
            this.mIsReportChangesTimerPosted = false;
            removeCallbacks(this.mReportChangesTimer);
        } else if (this.mListener != null && !this.mIsReportChangesTimerPosted) {
            this.mIsReportChangesTimerPosted = true;
            postDelayed(this.mReportChangesTimer, 1000);
        }
    }

    public Object getRootElement() {
        verifyThreadAccess();
        return this.mDocumentRoot;
    }

    public NodeDescriptor getNodeDescriptor(Object element) {
        verifyThreadAccess();
        return getDescriptor(element);
    }

    public void highlightElement(Object element, int color) {
        verifyThreadAccess();
        View highlightingView = getHighlightingView(element);
        if (highlightingView == null) {
            this.mHighlighter.clearHighlight();
        } else {
            this.mHighlighter.setHighlightedView(highlightingView, color);
        }
    }

    public void hideHighlight() {
        verifyThreadAccess();
        this.mHighlighter.clearHighlight();
    }

    public void setInspectModeEnabled(boolean enabled) {
        verifyThreadAccess();
        if (enabled) {
            this.mInspectModeHandler.enable();
        } else {
            this.mInspectModeHandler.disable();
        }
    }

    public void setAttributesAsText(Object element, String text) {
        verifyThreadAccess();
        Descriptor descriptor = this.mDescriptorMap.get(element.getClass());
        if (descriptor != null) {
            descriptor.setAttributesAsText(element, text);
        }
    }

    public Descriptor getDescriptor(Object element) {
        return element == null ? null : this.mDescriptorMap.get(element.getClass());
    }

    public void onAttributeModified(Object element, String name, String value) {
        if (this.mListener != null) {
            this.mListener.onAttributeModified(element, name, value);
        }
    }

    public void onAttributeRemoved(Object element, String name) {
        if (this.mListener != null) {
            this.mListener.onAttributeRemoved(element, name);
        }
    }

    public View getHighlightingView(Object element) {
        if (element == null) {
            return null;
        }
        View highlightingView = null;
        Class<?> theClass = element.getClass();
        Descriptor lastDescriptor = null;
        while (highlightingView == null && theClass != null) {
            Descriptor descriptor = this.mDescriptorMap.get(theClass);
            if (descriptor == null) {
                return null;
            }
            if (descriptor != lastDescriptor && (descriptor instanceof HighlightableDescriptor)) {
                highlightingView = ((HighlightableDescriptor) descriptor).getViewForHighlighting(element);
            }
            lastDescriptor = descriptor;
            theClass = theClass.getSuperclass();
        }
        return highlightingView;
    }

    private void getWindows(final Accumulator<Window> accumulator) {
        Descriptor appDescriptor = getDescriptor(this.mApplication);
        if (appDescriptor != null) {
            appDescriptor.getChildren(this.mApplication, new Accumulator<Object>() {
                public void store(Object element) {
                    if (element instanceof Window) {
                        accumulator.store((Window) element);
                        return;
                    }
                    Descriptor elementDescriptor = AndroidDocumentProvider.this.getDescriptor(element);
                    if (elementDescriptor != null) {
                        elementDescriptor.getChildren(element, this);
                    }
                }
            });
        }
    }
}
