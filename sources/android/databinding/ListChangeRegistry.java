package android.databinding;

import android.databinding.CallbackRegistry.NotifierCallback;
import android.databinding.ObservableList.OnListChangedCallback;
import android.support.p000v4.util.Pools.SynchronizedPool;

public class ListChangeRegistry extends CallbackRegistry<OnListChangedCallback, ObservableList, ListChanges> {
    private static final NotifierCallback<OnListChangedCallback, ObservableList, ListChanges> NOTIFIER_CALLBACK = new C00001();
    private static final SynchronizedPool<ListChanges> sListChanges = new SynchronizedPool(10);

    /* renamed from: android.databinding.ListChangeRegistry$1 */
    static class C00001 extends NotifierCallback<OnListChangedCallback, ObservableList, ListChanges> {
        C00001() {
        }

        public void onNotifyCallback(OnListChangedCallback callback, ObservableList sender, int notificationType, ListChanges listChanges) {
            switch (notificationType) {
                case 1:
                    callback.onItemRangeChanged(sender, listChanges.start, listChanges.count);
                    return;
                case 2:
                    callback.onItemRangeInserted(sender, listChanges.start, listChanges.count);
                    return;
                case 3:
                    callback.onItemRangeMoved(sender, listChanges.start, listChanges.f0to, listChanges.count);
                    return;
                case 4:
                    callback.onItemRangeRemoved(sender, listChanges.start, listChanges.count);
                    return;
                default:
                    callback.onChanged(sender);
                    return;
            }
        }
    }

    static class ListChanges {
        public int count;
        public int start;
        /* renamed from: to */
        public int f0to;

        ListChanges() {
        }
    }

    public void notifyChanged(ObservableList list, int start, int count) {
        notifyCallbacks(list, 1, acquire(start, 0, count));
    }

    public void notifyInserted(ObservableList list, int start, int count) {
        notifyCallbacks(list, 2, acquire(start, 0, count));
    }

    public void notifyRemoved(ObservableList list, int start, int count) {
        notifyCallbacks(list, 4, acquire(start, 0, count));
    }

    private static ListChanges acquire(int start, int to, int count) {
        ListChanges listChanges = (ListChanges) sListChanges.acquire();
        if (listChanges == null) {
            listChanges = new ListChanges();
        }
        listChanges.start = start;
        listChanges.f0to = to;
        listChanges.count = count;
        return listChanges;
    }

    public synchronized void notifyCallbacks(ObservableList sender, int notificationType, ListChanges listChanges) {
        super.notifyCallbacks(sender, notificationType, listChanges);
        if (listChanges != null) {
            sListChanges.release(listChanges);
        }
    }

    public ListChangeRegistry() {
        super(NOTIFIER_CALLBACK);
    }
}
