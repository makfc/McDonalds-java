package android.support.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.support.transition.TransitionPort.TransitionListener;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

@TargetApi(14)
@RequiresApi
class TransitionIcs extends TransitionImpl {
    TransitionInterface mExternalTransition;
    TransitionPort mTransition;

    private class CompatListener implements TransitionListener {
        private final ArrayList<TransitionInterfaceListener> mListeners;
        final /* synthetic */ TransitionIcs this$0;

        public void onTransitionStart(TransitionPort transition) {
            Iterator it = this.mListeners.iterator();
            while (it.hasNext()) {
                ((TransitionInterfaceListener) it.next()).onTransitionStart(this.this$0.mExternalTransition);
            }
        }

        public void onTransitionEnd(TransitionPort transition) {
            Iterator it = this.mListeners.iterator();
            while (it.hasNext()) {
                ((TransitionInterfaceListener) it.next()).onTransitionEnd(this.this$0.mExternalTransition);
            }
        }

        public void onTransitionPause(TransitionPort transition) {
            Iterator it = this.mListeners.iterator();
            while (it.hasNext()) {
                ((TransitionInterfaceListener) it.next()).onTransitionPause(this.this$0.mExternalTransition);
            }
        }

        public void onTransitionResume(TransitionPort transition) {
            Iterator it = this.mListeners.iterator();
            while (it.hasNext()) {
                ((TransitionInterfaceListener) it.next()).onTransitionResume(this.this$0.mExternalTransition);
            }
        }
    }

    private static class TransitionWrapper extends TransitionPort {
        private TransitionInterface mTransition;

        public TransitionWrapper(TransitionInterface transition) {
            this.mTransition = transition;
        }

        public void captureStartValues(TransitionValues transitionValues) {
            this.mTransition.captureStartValues(transitionValues);
        }

        public void captureEndValues(TransitionValues transitionValues) {
            this.mTransition.captureEndValues(transitionValues);
        }

        public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
            return this.mTransition.createAnimator(sceneRoot, startValues, endValues);
        }
    }

    TransitionIcs() {
    }

    public void init(TransitionInterface external, Object internal) {
        this.mExternalTransition = external;
        if (internal == null) {
            this.mTransition = new TransitionWrapper(external);
        } else {
            this.mTransition = (TransitionPort) internal;
        }
    }

    public void captureEndValues(TransitionValues transitionValues) {
        this.mTransition.captureEndValues(transitionValues);
    }

    public void captureStartValues(TransitionValues transitionValues) {
        this.mTransition.captureStartValues(transitionValues);
    }

    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        return this.mTransition.createAnimator(sceneRoot, startValues, endValues);
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
