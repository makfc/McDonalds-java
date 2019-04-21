package android.support.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

public abstract class Transition implements TransitionInterface {
    TransitionImpl mImpl;

    public interface TransitionListener extends TransitionInterfaceListener<Transition> {
    }

    public Transition() {
        this(false);
    }

    Transition(boolean deferred) {
        if (!deferred) {
            if (VERSION.SDK_INT >= 23) {
                this.mImpl = new TransitionApi23();
            } else if (VERSION.SDK_INT >= 19) {
                this.mImpl = new TransitionKitKat();
            } else {
                this.mImpl = new TransitionIcs();
            }
            this.mImpl.init(this);
        }
    }

    @Nullable
    public Animator createAnimator(@NonNull ViewGroup sceneRoot, @Nullable TransitionValues startValues, @Nullable TransitionValues endValues) {
        return null;
    }

    @NonNull
    public Transition setDuration(long duration) {
        this.mImpl.setDuration(duration);
        return this;
    }

    @NonNull
    public Transition setInterpolator(@Nullable TimeInterpolator interpolator) {
        this.mImpl.setInterpolator(interpolator);
        return this;
    }

    public String toString() {
        return this.mImpl.toString();
    }
}
