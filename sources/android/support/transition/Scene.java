package android.support.transition;

import android.os.Build.VERSION;

public class Scene {
    private static SceneStaticsImpl sImpl;

    static {
        if (VERSION.SDK_INT >= 21) {
            sImpl = new SceneStaticsApi21();
        } else if (VERSION.SDK_INT >= 19) {
            sImpl = new SceneStaticsKitKat();
        } else {
            sImpl = new SceneStaticsIcs();
        }
    }
}
