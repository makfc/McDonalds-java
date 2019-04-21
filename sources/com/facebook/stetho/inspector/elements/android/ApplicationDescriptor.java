package com.facebook.stetho.inspector.elements.android;

import android.app.Activity;
import android.app.Application;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import com.facebook.stetho.inspector.elements.NodeType;
import com.facebook.stetho.inspector.elements.android.ActivityTracker.Listener;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

final class ApplicationDescriptor extends AbstractChainedDescriptor<Application> {
    private final ActivityTracker mActivityTracker = ActivityTracker.get();
    private final Map<Application, ElementContext> mElementToContextMap = Collections.synchronizedMap(new IdentityHashMap());

    private class ElementContext {
        private Application mElement;
        private final Listener mListener = new C19901();

        /* renamed from: com.facebook.stetho.inspector.elements.android.ApplicationDescriptor$ElementContext$1 */
        class C19901 implements Listener {
            C19901() {
            }

            public void onActivityAdded(Activity activity) {
            }

            public void onActivityRemoved(Activity activity) {
            }
        }

        public void hook(Application element) {
            this.mElement = element;
            ApplicationDescriptor.this.mActivityTracker.registerListener(this.mListener);
        }

        public void unhook() {
            ApplicationDescriptor.this.mActivityTracker.unregisterListener(this.mListener);
            this.mElement = null;
        }

        public List<Activity> getActivitiesList() {
            return ApplicationDescriptor.this.mActivityTracker.getActivitiesView();
        }
    }

    ApplicationDescriptor() {
    }

    private ElementContext getContext(Application element) {
        return (ElementContext) this.mElementToContextMap.get(element);
    }

    /* Access modifiers changed, original: protected */
    public void onHook(Application element) {
        ElementContext context = new ElementContext();
        context.hook(element);
        this.mElementToContextMap.put(element, context);
    }

    /* Access modifiers changed, original: protected */
    public void onUnhook(Application element) {
        ((ElementContext) this.mElementToContextMap.remove(element)).unhook();
    }

    /* Access modifiers changed, original: protected */
    public NodeType onGetNodeType(Application element) {
        return NodeType.ELEMENT_NODE;
    }

    /* Access modifiers changed, original: protected */
    public void onGetChildren(Application element, Accumulator<Object> children) {
        List<Activity> activities = getContext(element).getActivitiesList();
        for (int i = activities.size() - 1; i >= 0; i--) {
            children.store(activities.get(i));
        }
    }
}
