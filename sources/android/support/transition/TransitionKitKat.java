package android.support.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.transition.Transition;
import android.transition.Transition.TransitionListener;
import android.transition.TransitionValues;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

@TargetApi(19)
@RequiresApi
class TransitionKitKat extends TransitionImpl {
    TransitionInterface mExternalTransition;
    Transition mTransition;

    private class CompatListener implements TransitionListener {
        private final ArrayList<TransitionInterfaceListener> mListeners;
        final /* synthetic */ TransitionKitKat this$0;

        public void onTransitionStart(Transition transition) {
            Iterator it = this.mListeners.iterator();
            while (it.hasNext()) {
                ((TransitionInterfaceListener) it.next()).onTransitionStart(this.this$0.mExternalTransition);
            }
        }

        public void onTransitionEnd(Transition transition) {
            Iterator it = this.mListeners.iterator();
            while (it.hasNext()) {
                ((TransitionInterfaceListener) it.next()).onTransitionEnd(this.this$0.mExternalTransition);
            }
        }

        public void onTransitionCancel(Transition transition) {
            Iterator it = this.mListeners.iterator();
            while (it.hasNext()) {
                ((TransitionInterfaceListener) it.next()).onTransitionCancel(this.this$0.mExternalTransition);
            }
        }

        public void onTransitionPause(Transition transition) {
            Iterator it = this.mListeners.iterator();
            while (it.hasNext()) {
                ((TransitionInterfaceListener) it.next()).onTransitionPause(this.this$0.mExternalTransition);
            }
        }

        public void onTransitionResume(Transition transition) {
            Iterator it = this.mListeners.iterator();
            while (it.hasNext()) {
                ((TransitionInterfaceListener) it.next()).onTransitionResume(this.this$0.mExternalTransition);
            }
        }
    }

    private static class TransitionWrapper extends Transition {
        private TransitionInterface mTransition;

        public TransitionWrapper(TransitionInterface transition) {
            this.mTransition = transition;
        }

        public void captureStartValues(TransitionValues transitionValues) {
            TransitionKitKat.wrapCaptureStartValues(this.mTransition, transitionValues);
        }

        public void captureEndValues(TransitionValues transitionValues) {
            TransitionKitKat.wrapCaptureEndValues(this.mTransition, transitionValues);
        }

        public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
            return this.mTransition.createAnimator(sceneRoot, TransitionKitKat.convertToSupport(startValues), TransitionKitKat.convertToSupport(endValues));
        }
    }

    TransitionKitKat() {
    }

    static void copyValues(TransitionValues source, TransitionValues dest) {
        if (source != null) {
            dest.view = source.view;
            if (source.values.size() > 0) {
                dest.values.putAll(source.values);
            }
        }
    }

    static void copyValues(TransitionValues source, TransitionValues dest) {
        if (source != null) {
            dest.view = source.view;
            if (source.values.size() > 0) {
                dest.values.putAll(source.values);
            }
        }
    }

    static void wrapCaptureStartValues(TransitionInterface transition, TransitionValues transitionValues) {
        TransitionValues externalValues = new TransitionValues();
        copyValues(transitionValues, externalValues);
        transition.captureStartValues(externalValues);
        copyValues(externalValues, transitionValues);
    }

    static void wrapCaptureEndValues(TransitionInterface transition, TransitionValues transitionValues) {
        TransitionValues externalValues = new TransitionValues();
        copyValues(transitionValues, externalValues);
        transition.captureEndValues(externalValues);
        copyValues(externalValues, transitionValues);
    }

    static TransitionValues convertToSupport(TransitionValues values) {
        if (values == null) {
            return null;
        }
        TransitionValues supportValues = new TransitionValues();
        copyValues(values, supportValues);
        return supportValues;
    }

    static TransitionValues convertToPlatform(TransitionValues values) {
        if (values == null) {
            return null;
        }
        TransitionValues platformValues = new TransitionValues();
        copyValues(values, platformValues);
        return platformValues;
    }

    public void init(TransitionInterface external, Object internal) {
        this.mExternalTransition = external;
        if (internal == null) {
            this.mTransition = new TransitionWrapper(external);
        } else {
            this.mTransition = (Transition) internal;
        }
    }

    public void captureEndValues(TransitionValues transitionValues) {
        TransitionValues internalValues = new TransitionValues();
        copyValues(transitionValues, internalValues);
        this.mTransition.captureEndValues(internalValues);
        copyValues(internalValues, transitionValues);
    }

    public void captureStartValues(TransitionValues transitionValues) {
        TransitionValues internalValues = new TransitionValues();
        copyValues(transitionValues, internalValues);
        this.mTransition.captureStartValues(internalValues);
        copyValues(internalValues, transitionValues);
    }

    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        TransitionValues internalStartValues;
        TransitionValues internalEndValues;
        if (startValues != null) {
            internalStartValues = new TransitionValues();
            copyValues(startValues, internalStartValues);
        } else {
            internalStartValues = null;
        }
        if (endValues != null) {
            internalEndValues = new TransitionValues();
            copyValues(endValues, internalEndValues);
        } else {
            internalEndValues = null;
        }
        return this.mTransition.createAnimator(sceneRoot, internalStartValues, internalEndValues);
    }

    public TransitionImpl setDuration(long duration) {
        this.mTransition.setDuration(duration);
        return this;
    }

    public TransitionImpl setInterpolator(TimeInterpolator interpolator) {
        this.mTransition.setInterpolator(interpolator);
        return this;
    }

    public String toString() {
        return this.mTransition.toString();
    }
}
