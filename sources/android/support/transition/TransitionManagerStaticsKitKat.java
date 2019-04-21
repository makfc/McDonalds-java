package android.support.transition;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.transition.TransitionManager;
import android.view.ViewGroup;

@TargetApi(19)
@RequiresApi
class TransitionManagerStaticsKitKat extends TransitionManagerStaticsImpl {
    TransitionManagerStaticsKitKat() {
    }

    public void beginDelayedTransition(ViewGroup sceneRoot, TransitionImpl transition) {
        TransitionManager.beginDelayedTransition(sceneRoot, transition == null ? null : ((TransitionKitKat) transition).mTransition);
    }
}
