package android.support.transition;

import android.view.ViewGroup;

abstract class TransitionManagerStaticsImpl {
    public abstract void beginDelayedTransition(ViewGroup viewGroup, TransitionImpl transitionImpl);

    TransitionManagerStaticsImpl() {
    }
}
