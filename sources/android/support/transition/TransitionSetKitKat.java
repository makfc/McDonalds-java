package android.support.transition;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.transition.TransitionSet;

@TargetApi(19)
@RequiresApi
class TransitionSetKitKat extends TransitionKitKat implements TransitionSetImpl {
    private TransitionSet mTransitionSet = new TransitionSet();

    public TransitionSetKitKat(TransitionInterface transition) {
        init(transition, this.mTransitionSet);
    }

    public TransitionSetKitKat setOrdering(int ordering) {
        this.mTransitionSet.setOrdering(ordering);
        return this;
    }

    public TransitionSetKitKat addTransition(TransitionImpl transition) {
        this.mTransitionSet.addTransition(((TransitionKitKat) transition).mTransition);
        return this;
    }
}
