package android.support.transition;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;

@TargetApi(14)
@RequiresApi
final class ScenePort {
    Runnable mExitAction;
    private ViewGroup mSceneRoot;

    static void setCurrentScene(View view, ScenePort scene) {
        view.setTag(C0145R.C0144id.transition_current_scene, scene);
    }

    static ScenePort getCurrentScene(View view) {
        return (ScenePort) view.getTag(C0145R.C0144id.transition_current_scene);
    }

    public void exit() {
        if (getCurrentScene(this.mSceneRoot) == this && this.mExitAction != null) {
            this.mExitAction.run();
        }
    }
}
