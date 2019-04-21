package android.support.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.view.ViewGroup;

abstract class TransitionImpl {
    public abstract void captureEndValues(TransitionValues transitionValues);

    public abstract void captureStartValues(TransitionValues transitionValues);

    public abstract Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2);

    public abstract void init(TransitionInterface transitionInterface, Object obj);

    public abstract TransitionImpl setDuration(long j);

    public abstract TransitionImpl setInterpolator(TimeInterpolator timeInterpolator);

    TransitionImpl() {
    }

    public void init(TransitionInterface external) {
        init(external, null);
    }
}
