package android.support.transition;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.transition.ChangeBounds;

@TargetApi(19)
@RequiresApi
class ChangeBoundsKitKat extends TransitionKitKat implements ChangeBoundsInterface {
    public ChangeBoundsKitKat(TransitionInterface transition) {
        init(transition, new ChangeBounds());
    }
}
