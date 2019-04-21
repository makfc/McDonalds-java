package android.support.p001v7.content.res;

import java.lang.reflect.Array;

/* renamed from: android.support.v7.content.res.GrowingArrayUtils */
final class GrowingArrayUtils {
    static final /* synthetic */ boolean $assertionsDisabled = (!GrowingArrayUtils.class.desiredAssertionStatus());

    public static <T> T[] append(T[] array, int currentSize, T element) {
        if ($assertionsDisabled || currentSize <= array.length) {
            if (currentSize + 1 > array.length) {
                T[] newArray = (Object[]) ((Object[]) Array.newInstance(array.getClass().getComponentType(), GrowingArrayUtils.growSize(currentSize)));
                System.arraycopy(array, 0, newArray, 0, currentSize);
                array = newArray;
            }
            array[currentSize] = element;
            return array;
        }
        throw new AssertionError();
    }

    public static int[] append(int[] array, int currentSize, int element) {
        if ($assertionsDisabled || currentSize <= array.length) {
            if (currentSize + 1 > array.length) {
                int[] newArray = new int[GrowingArrayUtils.growSize(currentSize)];
                System.arraycopy(array, 0, newArray, 0, currentSize);
                array = newArray;
            }
            array[currentSize] = element;
            return array;
        }
        throw new AssertionError();
    }

    public static int growSize(int currentSize) {
        return currentSize <= 4 ? 8 : currentSize * 2;
    }

    private GrowingArrayUtils() {
    }
}
