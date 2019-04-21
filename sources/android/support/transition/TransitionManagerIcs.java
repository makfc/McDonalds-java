package android.support.transition;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;

@TargetApi(14)
@RequiresApi
class TransitionManagerIcs extends TransitionManagerImpl {
    private final TransitionManagerPort mTransitionManager = new TransitionManagerPort();

    TransitionManagerIcs() {
    }
}
