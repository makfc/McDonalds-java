package android.support.transition;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;

@TargetApi(14)
@RequiresApi
class ChangeBoundsIcs extends TransitionIcs implements ChangeBoundsInterface {
    public ChangeBoundsIcs(TransitionInterface transition) {
        init(transition, new ChangeBoundsPort());
    }
}
