package android.support.transition;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.transition.TransitionManager;

@TargetApi(19)
@RequiresApi
class TransitionManagerKitKat extends TransitionManagerImpl {
    private final TransitionManager mTransitionManager = new TransitionManager();

    TransitionManagerKitKat() {
    }
}
