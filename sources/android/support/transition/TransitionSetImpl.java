package android.support.transition;

interface TransitionSetImpl {
    TransitionSetImpl addTransition(TransitionImpl transitionImpl);

    TransitionSetImpl setOrdering(int i);
}
