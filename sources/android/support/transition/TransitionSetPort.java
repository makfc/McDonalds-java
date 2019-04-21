package android.support.transition;

import android.animation.TimeInterpolator;
import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.transition.TransitionPort.TransitionListener;
import android.support.transition.TransitionPort.TransitionListenerAdapter;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

@TargetApi(14)
@RequiresApi
class TransitionSetPort extends TransitionPort {
    int mCurrentListeners;
    private boolean mPlayTogether = true;
    boolean mStarted = false;
    ArrayList<TransitionPort> mTransitions = new ArrayList();

    static class TransitionSetListener extends TransitionListenerAdapter {
        TransitionSetPort mTransitionSet;

        TransitionSetListener(TransitionSetPort transitionSet) {
            this.mTransitionSet = transitionSet;
        }

        public void onTransitionStart(TransitionPort transition) {
            if (!this.mTransitionSet.mStarted) {
                this.mTransitionSet.start();
                this.mTransitionSet.mStarted = true;
            }
        }

        public void onTransitionEnd(TransitionPort transition) {
            TransitionSetPort transitionSetPort = this.mTransitionSet;
            transitionSetPort.mCurrentListeners--;
            if (this.mTransitionSet.mCurrentListeners == 0) {
                this.mTransitionSet.mStarted = false;
                this.mTransitionSet.end();
            }
            transition.removeListener(this);
        }
    }

    public TransitionSetPort setOrdering(int ordering) {
        switch (ordering) {
            case 0:
                this.mPlayTogether = true;
                break;
            case 1:
                this.mPlayTogether = false;
                break;
            default:
                throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + ordering);
        }
        return this;
    }

    public TransitionSetPort addTransition(TransitionPort transition) {
        if (transition != null) {
            this.mTransitions.add(transition);
            transition.mParent = this;
            if (this.mDuration >= 0) {
                transition.setDuration(this.mDuration);
            }
        }
        return this;
    }

    public TransitionSetPort setDuration(long duration) {
        super.setDuration(duration);
        if (this.mDuration >= 0) {
            int numTransitions = this.mTransitions.size();
            for (int i = 0; i < numTransitions; i++) {
                ((TransitionPort) this.mTransitions.get(i)).setDuration(duration);
            }
        }
        return this;
    }

    public TransitionSetPort setInterpolator(TimeInterpolator interpolator) {
        return (TransitionSetPort) super.setInterpolator(interpolator);
    }

    public TransitionSetPort addListener(TransitionListener listener) {
        return (TransitionSetPort) super.addListener(listener);
    }

    public TransitionSetPort removeListener(TransitionListener listener) {
        return (TransitionSetPort) super.removeListener(listener);
    }

    private void setupStartEndListeners() {
        TransitionSetListener listener = new TransitionSetListener(this);
        Iterator it = this.mTransitions.iterator();
        while (it.hasNext()) {
            ((TransitionPort) it.next()).addListener(listener);
        }
        this.mCurrentListeners = this.mTransitions.size();
    }

    /* Access modifiers changed, original: protected */
    @RestrictTo
    public void createAnimators(ViewGroup sceneRoot, TransitionValuesMaps startValues, TransitionValuesMaps endValues) {
        Iterator it = this.mTransitions.iterator();
        while (it.hasNext()) {
            ((TransitionPort) it.next()).createAnimators(sceneRoot, startValues, endValues);
        }
    }

    /* Access modifiers changed, original: protected */
    @RestrictTo
    public void runAnimators() {
        if (this.mTransitions.isEmpty()) {
            start();
            end();
            return;
        }
        setupStartEndListeners();
        if (this.mPlayTogether) {
            Iterator it = this.mTransitions.iterator();
            while (it.hasNext()) {
                ((TransitionPort) it.next()).runAnimators();
            }
            return;
        }
        for (int i = 1; i < this.mTransitions.size(); i++) {
            final TransitionPort nextTransition = (TransitionPort) this.mTransitions.get(i);
            ((TransitionPort) this.mTransitions.get(i - 1)).addListener(new TransitionListenerAdapter() {
                public void onTransitionEnd(TransitionPort transition) {
                    nextTransition.runAnimators();
                    transition.removeListener(this);
                }
            });
        }
        TransitionPort firstTransition = (TransitionPort) this.mTransitions.get(0);
        if (firstTransition != null) {
            firstTransition.runAnimators();
        }
    }

    public void captureStartValues(TransitionValues transitionValues) {
        int targetId = transitionValues.view.getId();
        if (isValidTarget(transitionValues.view, (long) targetId)) {
            Iterator it = this.mTransitions.iterator();
            while (it.hasNext()) {
                TransitionPort childTransition = (TransitionPort) it.next();
                if (childTransition.isValidTarget(transitionValues.view, (long) targetId)) {
                    childTransition.captureStartValues(transitionValues);
                }
            }
        }
    }

    public void captureEndValues(TransitionValues transitionValues) {
        int targetId = transitionValues.view.getId();
        if (isValidTarget(transitionValues.view, (long) targetId)) {
            Iterator it = this.mTransitions.iterator();
            while (it.hasNext()) {
                TransitionPort childTransition = (TransitionPort) it.next();
                if (childTransition.isValidTarget(transitionValues.view, (long) targetId)) {
                    childTransition.captureEndValues(transitionValues);
                }
            }
        }
    }

    @RestrictTo
    public void pause(View sceneRoot) {
        super.pause(sceneRoot);
        int numTransitions = this.mTransitions.size();
        for (int i = 0; i < numTransitions; i++) {
            ((TransitionPort) this.mTransitions.get(i)).pause(sceneRoot);
        }
    }

    @RestrictTo
    public void resume(View sceneRoot) {
        super.resume(sceneRoot);
        int numTransitions = this.mTransitions.size();
        for (int i = 0; i < numTransitions; i++) {
            ((TransitionPort) this.mTransitions.get(i)).resume(sceneRoot);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public String toString(String indent) {
        String result = super.toString(indent);
        for (int i = 0; i < this.mTransitions.size(); i++) {
            result = result + "\n" + ((TransitionPort) this.mTransitions.get(i)).toString(indent + "  ");
        }
        return result;
    }

    public TransitionSetPort clone() {
        TransitionSetPort clone = (TransitionSetPort) super.clone();
        clone.mTransitions = new ArrayList();
        int numTransitions = this.mTransitions.size();
        for (int i = 0; i < numTransitions; i++) {
            clone.addTransition(((TransitionPort) this.mTransitions.get(i)).clone());
        }
        return clone;
    }
}
