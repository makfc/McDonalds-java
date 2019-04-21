package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.p000v4.util.ArrayMap;
import android.support.p000v4.util.LongSparseArray;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;

@TargetApi(14)
@RequiresApi
abstract class TransitionPort implements Cloneable {
    private static ThreadLocal<ArrayMap<Animator, AnimationInfo>> sRunningAnimators = new ThreadLocal();
    ArrayList<Animator> mAnimators = new ArrayList();
    boolean mCanRemoveViews = false;
    ArrayList<Animator> mCurrentAnimators = new ArrayList();
    long mDuration = -1;
    private TransitionValuesMaps mEndValues = new TransitionValuesMaps();
    private boolean mEnded = false;
    TimeInterpolator mInterpolator = null;
    ArrayList<TransitionListener> mListeners = null;
    private String mName = getClass().getName();
    int mNumInstances = 0;
    TransitionSetPort mParent = null;
    boolean mPaused = false;
    ViewGroup mSceneRoot = null;
    long mStartDelay = -1;
    private TransitionValuesMaps mStartValues = new TransitionValuesMaps();
    ArrayList<View> mTargetChildExcludes = null;
    ArrayList<View> mTargetExcludes = null;
    ArrayList<Integer> mTargetIdChildExcludes = null;
    ArrayList<Integer> mTargetIdExcludes = null;
    ArrayList<Integer> mTargetIds = new ArrayList();
    ArrayList<Class> mTargetTypeChildExcludes = null;
    ArrayList<Class> mTargetTypeExcludes = null;
    ArrayList<View> mTargets = new ArrayList();

    public interface TransitionListener {
        void onTransitionEnd(TransitionPort transitionPort);

        void onTransitionPause(TransitionPort transitionPort);

        void onTransitionResume(TransitionPort transitionPort);

        void onTransitionStart(TransitionPort transitionPort);
    }

    @RestrictTo
    public static class TransitionListenerAdapter implements TransitionListener {
        public void onTransitionStart(TransitionPort transition) {
        }

        public void onTransitionEnd(TransitionPort transition) {
        }

        public void onTransitionPause(TransitionPort transition) {
        }

        public void onTransitionResume(TransitionPort transition) {
        }
    }

    /* renamed from: android.support.transition.TransitionPort$2 */
    class C01352 extends AnimatorListenerAdapter {
        C01352() {
        }

        public void onAnimationEnd(Animator animation) {
            TransitionPort.this.end();
            animation.removeListener(this);
        }
    }

    private static class AnimationInfo {
        String name;
        TransitionValues values;
        View view;
        WindowIdPort windowId;

        AnimationInfo(View view, String name, WindowIdPort windowId, TransitionValues values) {
            this.view = view;
            this.name = name;
            this.values = values;
            this.windowId = windowId;
        }
    }

    private static class ArrayListManager {
        private ArrayListManager() {
        }
    }

    public abstract void captureEndValues(TransitionValues transitionValues);

    public abstract void captureStartValues(TransitionValues transitionValues);

    private static ArrayMap<Animator, AnimationInfo> getRunningAnimators() {
        ArrayMap<Animator, AnimationInfo> runningAnimators = (ArrayMap) sRunningAnimators.get();
        if (runningAnimators != null) {
            return runningAnimators;
        }
        runningAnimators = new ArrayMap();
        sRunningAnimators.set(runningAnimators);
        return runningAnimators;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public TransitionPort setDuration(long duration) {
        this.mDuration = duration;
        return this;
    }

    public long getStartDelay() {
        return this.mStartDelay;
    }

    public TimeInterpolator getInterpolator() {
        return this.mInterpolator;
    }

    public TransitionPort setInterpolator(TimeInterpolator interpolator) {
        this.mInterpolator = interpolator;
        return this;
    }

    public String[] getTransitionProperties() {
        return null;
    }

    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        return null;
    }

    /* Access modifiers changed, original: protected */
    @RestrictTo
    public void createAnimators(ViewGroup sceneRoot, TransitionValuesMaps startValues, TransitionValuesMaps endValues) {
        int i;
        TransitionValues end;
        View view;
        TransitionValues start;
        int id;
        long id2;
        ArrayMap<View, TransitionValues> endCopy = new ArrayMap(endValues.viewValues);
        SparseArray<TransitionValues> endIdCopy = new SparseArray(endValues.idValues.size());
        for (i = 0; i < endValues.idValues.size(); i++) {
            endIdCopy.put(endValues.idValues.keyAt(i), endValues.idValues.valueAt(i));
        }
        LongSparseArray<TransitionValues> endItemIdCopy = new LongSparseArray(endValues.itemIdValues.size());
        for (i = 0; i < endValues.itemIdValues.size(); i++) {
            endItemIdCopy.put(endValues.itemIdValues.keyAt(i), endValues.itemIdValues.valueAt(i));
        }
        ArrayList<TransitionValues> startValuesList = new ArrayList();
        ArrayList<TransitionValues> endValuesList = new ArrayList();
        for (View view2 : startValues.viewValues.keySet()) {
            end = null;
            boolean isInListView = false;
            if (view2.getParent() instanceof ListView) {
                isInListView = true;
            }
            if (isInListView) {
                ListView parent = (ListView) view2.getParent();
                if (parent.getAdapter().hasStableIds()) {
                    long itemId = parent.getItemIdAtPosition(parent.getPositionForView(view2));
                    start = (TransitionValues) startValues.itemIdValues.get(itemId);
                    endItemIdCopy.remove(itemId);
                    startValuesList.add(start);
                    endValuesList.add(null);
                }
            } else {
                id = view2.getId();
                start = startValues.viewValues.get(view2) != null ? (TransitionValues) startValues.viewValues.get(view2) : (TransitionValues) startValues.idValues.get(id);
                if (endValues.viewValues.get(view2) != null) {
                    end = (TransitionValues) endValues.viewValues.get(view2);
                    endCopy.remove(view2);
                } else if (id != -1) {
                    end = (TransitionValues) endValues.idValues.get(id);
                    View removeView = null;
                    for (View viewToRemove : endCopy.keySet()) {
                        if (viewToRemove.getId() == id) {
                            removeView = viewToRemove;
                        }
                    }
                    if (removeView != null) {
                        endCopy.remove(removeView);
                    }
                }
                endIdCopy.remove(id);
                if (isValidTarget(view2, (long) id)) {
                    startValuesList.add(start);
                    endValuesList.add(end);
                }
            }
        }
        int startItemIdCopySize = startValues.itemIdValues.size();
        for (i = 0; i < startItemIdCopySize; i++) {
            id2 = startValues.itemIdValues.keyAt(i);
            if (isValidTarget(null, id2)) {
                start = (TransitionValues) startValues.itemIdValues.get(id2);
                end = (TransitionValues) endValues.itemIdValues.get(id2);
                endItemIdCopy.remove(id2);
                startValuesList.add(start);
                endValuesList.add(end);
            }
        }
        for (View view22 : endCopy.keySet()) {
            id = view22.getId();
            if (isValidTarget(view22, (long) id)) {
                start = (TransitionValues) (startValues.viewValues.get(view22) != null ? startValues.viewValues.get(view22) : startValues.idValues.get(id));
                end = (TransitionValues) endCopy.get(view22);
                endIdCopy.remove(id);
                startValuesList.add(start);
                endValuesList.add(end);
            }
        }
        int endIdCopySize = endIdCopy.size();
        for (i = 0; i < endIdCopySize; i++) {
            id = endIdCopy.keyAt(i);
            if (isValidTarget(null, (long) id)) {
                end = (TransitionValues) endIdCopy.get(id);
                startValuesList.add((TransitionValues) startValues.idValues.get(id));
                endValuesList.add(end);
            }
        }
        int endItemIdCopySize = endItemIdCopy.size();
        for (i = 0; i < endItemIdCopySize; i++) {
            id2 = endItemIdCopy.keyAt(i);
            end = (TransitionValues) endItemIdCopy.get(id2);
            startValuesList.add((TransitionValues) startValues.itemIdValues.get(id2));
            endValuesList.add(end);
        }
        ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
        for (i = 0; i < startValuesList.size(); i++) {
            start = (TransitionValues) startValuesList.get(i);
            end = (TransitionValues) endValuesList.get(i);
            if (!(start == null && end == null) && (start == null || !start.equals(end))) {
                Animator animator = createAnimator(sceneRoot, start, end);
                if (animator != null) {
                    TransitionValues infoValues = null;
                    if (end != null) {
                        view22 = end.view;
                        String[] properties = getTransitionProperties();
                        if (view22 != null && properties != null && properties.length > 0) {
                            int j;
                            infoValues = new TransitionValues();
                            infoValues.view = view22;
                            TransitionValues newValues = (TransitionValues) endValues.viewValues.get(view22);
                            if (newValues != null) {
                                for (j = 0; j < properties.length; j++) {
                                    infoValues.values.put(properties[j], newValues.values.get(properties[j]));
                                }
                            }
                            int numExistingAnims = runningAnimators.size();
                            for (j = 0; j < numExistingAnims; j++) {
                                AnimationInfo info = (AnimationInfo) runningAnimators.get((Animator) runningAnimators.keyAt(j));
                                if (info.values != null && info.view == view22 && (((info.name == null && getName() == null) || info.name.equals(getName())) && info.values.equals(infoValues))) {
                                    animator = null;
                                    break;
                                }
                            }
                        }
                    } else {
                        view22 = start.view;
                    }
                    if (animator != null) {
                        runningAnimators.put(animator, new AnimationInfo(view22, getName(), WindowIdPort.getWindowId(sceneRoot), infoValues));
                        this.mAnimators.add(animator);
                    }
                }
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isValidTarget(View target, long targetId) {
        if (this.mTargetIdExcludes != null && this.mTargetIdExcludes.contains(Integer.valueOf((int) targetId))) {
            return false;
        }
        if (this.mTargetExcludes != null && this.mTargetExcludes.contains(target)) {
            return false;
        }
        int i;
        if (!(this.mTargetTypeExcludes == null || target == null)) {
            int numTypes = this.mTargetTypeExcludes.size();
            for (i = 0; i < numTypes; i++) {
                if (((Class) this.mTargetTypeExcludes.get(i)).isInstance(target)) {
                    return false;
                }
            }
        }
        if (this.mTargetIds.size() == 0 && this.mTargets.size() == 0) {
            return true;
        }
        if (this.mTargetIds.size() > 0) {
            for (i = 0; i < this.mTargetIds.size(); i++) {
                if (((long) ((Integer) this.mTargetIds.get(i)).intValue()) == targetId) {
                    return true;
                }
            }
        }
        if (target != null && this.mTargets.size() > 0) {
            for (i = 0; i < this.mTargets.size(); i++) {
                if (this.mTargets.get(i) == target) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Access modifiers changed, original: protected */
    @RestrictTo
    public void runAnimators() {
        start();
        ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
        Iterator it = this.mAnimators.iterator();
        while (it.hasNext()) {
            Animator anim = (Animator) it.next();
            if (runningAnimators.containsKey(anim)) {
                start();
                runAnimator(anim, runningAnimators);
            }
        }
        this.mAnimators.clear();
        end();
    }

    private void runAnimator(Animator animator, final ArrayMap<Animator, AnimationInfo> runningAnimators) {
        if (animator != null) {
            animator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animation) {
                    TransitionPort.this.mCurrentAnimators.add(animation);
                }

                public void onAnimationEnd(Animator animation) {
                    runningAnimators.remove(animation);
                    TransitionPort.this.mCurrentAnimators.remove(animation);
                }
            });
            animate(animator);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void captureValues(ViewGroup sceneRoot, boolean start) {
        clearValues(start);
        if (this.mTargetIds.size() > 0 || this.mTargets.size() > 0) {
            int i;
            View view;
            TransitionValues values;
            if (this.mTargetIds.size() > 0) {
                for (i = 0; i < this.mTargetIds.size(); i++) {
                    int id = ((Integer) this.mTargetIds.get(i)).intValue();
                    view = sceneRoot.findViewById(id);
                    if (view != null) {
                        values = new TransitionValues();
                        values.view = view;
                        if (start) {
                            captureStartValues(values);
                        } else {
                            captureEndValues(values);
                        }
                        if (start) {
                            this.mStartValues.viewValues.put(view, values);
                            if (id >= 0) {
                                this.mStartValues.idValues.put(id, values);
                            }
                        } else {
                            this.mEndValues.viewValues.put(view, values);
                            if (id >= 0) {
                                this.mEndValues.idValues.put(id, values);
                            }
                        }
                    }
                }
            }
            if (this.mTargets.size() > 0) {
                for (i = 0; i < this.mTargets.size(); i++) {
                    view = (View) this.mTargets.get(i);
                    if (view != null) {
                        values = new TransitionValues();
                        values.view = view;
                        if (start) {
                            captureStartValues(values);
                        } else {
                            captureEndValues(values);
                        }
                        if (start) {
                            this.mStartValues.viewValues.put(view, values);
                        } else {
                            this.mEndValues.viewValues.put(view, values);
                        }
                    }
                }
                return;
            }
            return;
        }
        captureHierarchy(sceneRoot, start);
    }

    /* Access modifiers changed, original: 0000 */
    public void clearValues(boolean start) {
        if (start) {
            this.mStartValues.viewValues.clear();
            this.mStartValues.idValues.clear();
            this.mStartValues.itemIdValues.clear();
            return;
        }
        this.mEndValues.viewValues.clear();
        this.mEndValues.idValues.clear();
        this.mEndValues.itemIdValues.clear();
    }

    private void captureHierarchy(View view, boolean start) {
        if (view != null) {
            boolean isListViewItem = false;
            if (view.getParent() instanceof ListView) {
                isListViewItem = true;
            }
            if (!isListViewItem || ((ListView) view.getParent()).getAdapter().hasStableIds()) {
                int id = -1;
                long itemId = -1;
                if (isListViewItem) {
                    ListView listview = (ListView) view.getParent();
                    itemId = listview.getItemIdAtPosition(listview.getPositionForView(view));
                } else {
                    id = view.getId();
                }
                if (this.mTargetIdExcludes != null && this.mTargetIdExcludes.contains(Integer.valueOf(id))) {
                    return;
                }
                if (this.mTargetExcludes == null || !this.mTargetExcludes.contains(view)) {
                    int numTypes;
                    int i;
                    if (!(this.mTargetTypeExcludes == null || view == null)) {
                        numTypes = this.mTargetTypeExcludes.size();
                        i = 0;
                        while (i < numTypes) {
                            if (!((Class) this.mTargetTypeExcludes.get(i)).isInstance(view)) {
                                i++;
                            } else {
                                return;
                            }
                        }
                    }
                    TransitionValues values = new TransitionValues();
                    values.view = view;
                    if (start) {
                        captureStartValues(values);
                    } else {
                        captureEndValues(values);
                    }
                    if (start) {
                        if (isListViewItem) {
                            this.mStartValues.itemIdValues.put(itemId, values);
                        } else {
                            this.mStartValues.viewValues.put(view, values);
                            if (id >= 0) {
                                this.mStartValues.idValues.put(id, values);
                            }
                        }
                    } else if (isListViewItem) {
                        this.mEndValues.itemIdValues.put(itemId, values);
                    } else {
                        this.mEndValues.viewValues.put(view, values);
                        if (id >= 0) {
                            this.mEndValues.idValues.put(id, values);
                        }
                    }
                    if (!(view instanceof ViewGroup)) {
                        return;
                    }
                    if (this.mTargetIdChildExcludes != null && this.mTargetIdChildExcludes.contains(Integer.valueOf(id))) {
                        return;
                    }
                    if (this.mTargetChildExcludes == null || !this.mTargetChildExcludes.contains(view)) {
                        if (!(this.mTargetTypeChildExcludes == null || view == null)) {
                            numTypes = this.mTargetTypeChildExcludes.size();
                            i = 0;
                            while (i < numTypes) {
                                if (!((Class) this.mTargetTypeChildExcludes.get(i)).isInstance(view)) {
                                    i++;
                                } else {
                                    return;
                                }
                            }
                        }
                        ViewGroup parent = (ViewGroup) view;
                        for (i = 0; i < parent.getChildCount(); i++) {
                            captureHierarchy(parent.getChildAt(i), start);
                        }
                    }
                }
            }
        }
    }

    @RestrictTo
    public void pause(View sceneRoot) {
        if (!this.mEnded) {
            int i;
            ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
            int numOldAnims = runningAnimators.size();
            WindowIdPort windowId = WindowIdPort.getWindowId(sceneRoot);
            for (i = numOldAnims - 1; i >= 0; i--) {
                AnimationInfo info = (AnimationInfo) runningAnimators.valueAt(i);
                if (info.view != null && windowId.equals(info.windowId)) {
                    ((Animator) runningAnimators.keyAt(i)).cancel();
                }
            }
            if (this.mListeners != null && this.mListeners.size() > 0) {
                ArrayList<TransitionListener> tmpListeners = (ArrayList) this.mListeners.clone();
                int numListeners = tmpListeners.size();
                for (i = 0; i < numListeners; i++) {
                    ((TransitionListener) tmpListeners.get(i)).onTransitionPause(this);
                }
            }
            this.mPaused = true;
        }
    }

    @RestrictTo
    public void resume(View sceneRoot) {
        if (this.mPaused) {
            if (!this.mEnded) {
                int i;
                ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
                int numOldAnims = runningAnimators.size();
                WindowIdPort windowId = WindowIdPort.getWindowId(sceneRoot);
                for (i = numOldAnims - 1; i >= 0; i--) {
                    AnimationInfo info = (AnimationInfo) runningAnimators.valueAt(i);
                    if (info.view != null && windowId.equals(info.windowId)) {
                        ((Animator) runningAnimators.keyAt(i)).end();
                    }
                }
                if (this.mListeners != null && this.mListeners.size() > 0) {
                    ArrayList<TransitionListener> tmpListeners = (ArrayList) this.mListeners.clone();
                    int numListeners = tmpListeners.size();
                    for (i = 0; i < numListeners; i++) {
                        ((TransitionListener) tmpListeners.get(i)).onTransitionResume(this);
                    }
                }
            }
            this.mPaused = false;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void playTransition(ViewGroup sceneRoot) {
        ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
        for (int i = runningAnimators.size() - 1; i >= 0; i--) {
            Animator anim = (Animator) runningAnimators.keyAt(i);
            if (anim != null) {
                AnimationInfo oldInfo = (AnimationInfo) runningAnimators.get(anim);
                if (!(oldInfo == null || oldInfo.view == null || oldInfo.view.getContext() != sceneRoot.getContext())) {
                    boolean cancel = false;
                    TransitionValues oldValues = oldInfo.values;
                    View oldView = oldInfo.view;
                    TransitionValues newValues = this.mEndValues.viewValues != null ? (TransitionValues) this.mEndValues.viewValues.get(oldView) : null;
                    if (newValues == null) {
                        newValues = (TransitionValues) this.mEndValues.idValues.get(oldView.getId());
                    }
                    if (oldValues != null && newValues != null) {
                        for (String key : oldValues.values.keySet()) {
                            Object oldValue = oldValues.values.get(key);
                            Object newValue = newValues.values.get(key);
                            if (oldValue != null && newValue != null && !oldValue.equals(newValue)) {
                                cancel = true;
                                break;
                            }
                        }
                    }
                    if (cancel) {
                        if (anim.isRunning() || anim.isStarted()) {
                            anim.cancel();
                        } else {
                            runningAnimators.remove(anim);
                        }
                    }
                }
            }
        }
        createAnimators(sceneRoot, this.mStartValues, this.mEndValues);
        runAnimators();
    }

    /* Access modifiers changed, original: protected */
    @RestrictTo
    public void animate(Animator animator) {
        if (animator == null) {
            end();
            return;
        }
        if (getDuration() >= 0) {
            animator.setDuration(getDuration());
        }
        if (getStartDelay() >= 0) {
            animator.setStartDelay(getStartDelay());
        }
        if (getInterpolator() != null) {
            animator.setInterpolator(getInterpolator());
        }
        animator.addListener(new C01352());
        animator.start();
    }

    /* Access modifiers changed, original: protected */
    @RestrictTo
    public void start() {
        if (this.mNumInstances == 0) {
            if (this.mListeners != null && this.mListeners.size() > 0) {
                ArrayList<TransitionListener> tmpListeners = (ArrayList) this.mListeners.clone();
                int numListeners = tmpListeners.size();
                for (int i = 0; i < numListeners; i++) {
                    ((TransitionListener) tmpListeners.get(i)).onTransitionStart(this);
                }
            }
            this.mEnded = false;
        }
        this.mNumInstances++;
    }

    /* Access modifiers changed, original: protected */
    @RestrictTo
    public void end() {
        this.mNumInstances--;
        if (this.mNumInstances == 0) {
            int i;
            View view;
            if (this.mListeners != null && this.mListeners.size() > 0) {
                ArrayList<TransitionListener> tmpListeners = (ArrayList) this.mListeners.clone();
                int numListeners = tmpListeners.size();
                for (i = 0; i < numListeners; i++) {
                    ((TransitionListener) tmpListeners.get(i)).onTransitionEnd(this);
                }
            }
            for (i = 0; i < this.mStartValues.itemIdValues.size(); i++) {
                view = ((TransitionValues) this.mStartValues.itemIdValues.valueAt(i)).view;
            }
            for (i = 0; i < this.mEndValues.itemIdValues.size(); i++) {
                view = ((TransitionValues) this.mEndValues.itemIdValues.valueAt(i)).view;
            }
            this.mEnded = true;
        }
    }

    public TransitionPort addListener(TransitionListener listener) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList();
        }
        this.mListeners.add(listener);
        return this;
    }

    public TransitionPort removeListener(TransitionListener listener) {
        if (this.mListeners != null) {
            this.mListeners.remove(listener);
            if (this.mListeners.size() == 0) {
                this.mListeners = null;
            }
        }
        return this;
    }

    public String toString() {
        return toString("");
    }

    public TransitionPort clone() {
        TransitionPort clone = null;
        try {
            clone = (TransitionPort) super.clone();
            clone.mAnimators = new ArrayList();
            clone.mStartValues = new TransitionValuesMaps();
            clone.mEndValues = new TransitionValuesMaps();
            return clone;
        } catch (CloneNotSupportedException e) {
            return clone;
        }
    }

    public String getName() {
        return this.mName;
    }

    /* Access modifiers changed, original: 0000 */
    public String toString(String indent) {
        String result = indent + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + ": ";
        if (this.mDuration != -1) {
            result = result + "dur(" + this.mDuration + ") ";
        }
        if (this.mStartDelay != -1) {
            result = result + "dly(" + this.mStartDelay + ") ";
        }
        if (this.mInterpolator != null) {
            result = result + "interp(" + this.mInterpolator + ") ";
        }
        if (this.mTargetIds.size() <= 0 && this.mTargets.size() <= 0) {
            return result;
        }
        int i;
        result = result + "tgts(";
        if (this.mTargetIds.size() > 0) {
            for (i = 0; i < this.mTargetIds.size(); i++) {
                if (i > 0) {
                    result = result + ", ";
                }
                result = result + this.mTargetIds.get(i);
            }
        }
        if (this.mTargets.size() > 0) {
            for (i = 0; i < this.mTargets.size(); i++) {
                if (i > 0) {
                    result = result + ", ";
                }
                result = result + this.mTargets.get(i);
            }
        }
        return result + ")";
    }
}
