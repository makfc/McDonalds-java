package android.support.p001v7.util;

import java.util.Comparator;

/* renamed from: android.support.v7.util.SortedList */
public class SortedList<T> {

    /* renamed from: android.support.v7.util.SortedList$Callback */
    public static abstract class Callback<T2> implements ListUpdateCallback, Comparator<T2> {
        public abstract int compare(T2 t2, T2 t22);
    }

    /* renamed from: android.support.v7.util.SortedList$BatchedCallback */
    public static class BatchedCallback<T2> extends Callback<T2> {
        final Callback<T2> mWrappedCallback;

        public int compare(T2 o1, T2 o2) {
            return this.mWrappedCallback.compare(o1, o2);
        }
    }
}
