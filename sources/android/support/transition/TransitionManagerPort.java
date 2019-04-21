package android.support.transition;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.support.p000v4.util.ArrayMap;
import android.support.p000v4.view.ViewCompat;
import android.support.transition.TransitionPort.TransitionListenerAdapter;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

@TargetApi(14)
@RequiresApi
class TransitionManagerPort {
    private static final String[] EMPTY_STRINGS = new String[0];
    private static String LOG_TAG = "TransitionManager";
    private static TransitionPort sDefaultTransition = new AutoTransitionPort();
    static ArrayList<ViewGroup> sPendingTransitions = new ArrayList();
    private static ThreadLocal<WeakReference<ArrayMap<ViewGroup, ArrayList<TransitionPort>>>> sRunningTransitions = new ThreadLocal();
    ArrayMap<String, ArrayMap<ScenePort, TransitionPort>> mNameSceneTransitions = new ArrayMap();
    ArrayMap<ScenePort, ArrayMap<String, TransitionPort>> mSceneNameTransitions = new ArrayMap();
    ArrayMap<ScenePort, ArrayMap<ScenePort, TransitionPort>> mScenePairTransitions = new ArrayMap();
    ArrayMap<ScenePort, TransitionPort> mSceneTransitions = new ArrayMap();

    private static class MultiListener implements OnAttachStateChangeListener, OnPreDrawListener {
        ViewGroup mSceneRoot;
        TransitionPort mTransition;

        MultiListener(TransitionPort transition, ViewGroup sceneRoot) {
            this.mTransition = transition;
            this.mSceneRoot = sceneRoot;
        }

        private void removeListeners() {
            this.mSceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
            this.mSceneRoot.removeOnAttachStateChangeListener(this);
        }

        public void onViewAttachedToWindow(View v) {
        }

        public void onViewDetachedFromWindow(View v) {
            removeListeners();
            TransitionManagerPort.sPendingTransitions.remove(this.mSceneRoot);
            ArrayList<TransitionPort> runningTransitions = (ArrayList) TransitionManagerPort.getRunningTransitions().get(this.mSceneRoot);
            if (runningTransitions != null && runningTransitions.size() > 0) {
                Iterator it = runningTransitions.iterator();
                while (it.hasNext()) {
                    ((TransitionPort) it.next()).resume(this.mSceneRoot);
                }
            }
            this.mTransition.clearValues(true);
        }

        public boolean onPreDraw() {
            removeListeners();
            TransitionManagerPort.sPendingTransitions.remove(this.mSceneRoot);
            final ArrayMap<ViewGroup, ArrayList<TransitionPort>> runningTransitions = TransitionManagerPort.getRunningTransitions();
            ArrayList<TransitionPort> currentTransitions = (ArrayList) runningTransitions.get(this.mSceneRoot);
            ArrayList<TransitionPort> previousRunningTransitions = null;
            if (currentTransitions == null) {
                currentTransitions = new ArrayList();
                runningTransitions.put(this.mSceneRoot, currentTransitions);
            } else if (currentTransitions.size() > 0) {
                previousRunningTransitions = new ArrayList(currentTransitions);
            }
            currentTransitions.add(this.mTransition);
            this.mTransition.addListener(new TransitionListenerAdapter() {
                public void onTransitionEnd(TransitionPort transition) {
                    ((ArrayList) runningTransitions.get(MultiListener.this.mSceneRoot)).remove(transition);
                }
            });
            this.mTransition.captureValues(this.mSceneRoot, false);
            if (previousRunningTransitions != null) {
                Iterator it = previousRunningTransitions.iterator();
                while (it.hasNext()) {
                    ((TransitionPort) it.next()).resume(this.mSceneRoot);
                }
            }
            this.mTransition.playTransition(this.mSceneRoot);
            return true;
        }
    }

    TransitionManagerPort() {
    }

    static ArrayMap<ViewGroup, ArrayList<TransitionPort>> getRunningTransitions() {
        WeakReference<ArrayMap<ViewGroup, ArrayList<TransitionPort>>> runningTransitions = (WeakReference) sRunningTransitions.get();
        if (runningTransitions == null || runningTransitions.get() == null) {
            runningTransitions = new WeakReference(new ArrayMap());
            sRunningTransitions.set(runningTransitions);
        }
        return (ArrayMap) runningTransitions.get();
    }

    private static void sceneChangeRunTransition(ViewGroup sceneRoot, TransitionPort transition) {
        if (transition != null && sceneRoot != null) {
            MultiListener listener = new MultiListener(transition, sceneRoot);
            sceneRoot.addOnAttachStateChangeListener(listener);
            sceneRoot.getViewTreeObserver().addOnPreDrawListener(listener);
        }
    }

    private static void sceneChangeSetup(ViewGroup sceneRoot, TransitionPort transition) {
        ArrayList<TransitionPort> runningTransitions = (ArrayList) getRunningTransitions().get(sceneRoot);
        if (runningTransitions != null && runningTransitions.size() > 0) {
            Iterator it = runningTransitions.iterator();
            while (it.hasNext()) {
                ((TransitionPort) it.next()).pause(sceneRoot);
            }
        }
        if (transition != null) {
            transition.captureValues(sceneRoot, true);
        }
        ScenePort previousScene = ScenePort.getCurrentScene(sceneRoot);
        if (previousScene != null) {
            previousScene.exit();
        }
    }

    public static void beginDelayedTransition(ViewGroup sceneRoot, TransitionPort transition) {
        if (!sPendingTransitions.contains(sceneRoot) && ViewCompat.isLaidOut(sceneRoot)) {
            sPendingTransitions.add(sceneRoot);
            if (transition == null) {
                transition = sDefaultTransition;
            }
            TransitionPort transitionClone = transition.clone();
            sceneChangeSetup(sceneRoot, transitionClone);
            ScenePort.setCurrentScene(sceneRoot, null);
            sceneChangeRunTransition(sceneRoot, transitionClone);
        }
    }
}
