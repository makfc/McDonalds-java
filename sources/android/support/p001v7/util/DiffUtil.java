package android.support.p001v7.util;

import java.util.Comparator;

/* renamed from: android.support.v7.util.DiffUtil */
public class DiffUtil {
    private static final Comparator<Snake> SNAKE_COMPARATOR = new C03411();

    /* renamed from: android.support.v7.util.DiffUtil$1 */
    static class C03411 implements Comparator<Snake> {
        C03411() {
        }

        public int compare(Snake o1, Snake o2) {
            int cmpX = o1.f16x - o2.f16x;
            return cmpX == 0 ? o1.f17y - o2.f17y : cmpX;
        }
    }

    /* renamed from: android.support.v7.util.DiffUtil$Callback */
    public static abstract class Callback {
    }

    /* renamed from: android.support.v7.util.DiffUtil$DiffResult */
    public static class DiffResult {

        /* renamed from: android.support.v7.util.DiffUtil$DiffResult$1 */
        class C03421 implements ListUpdateCallback {
        }
    }

    /* renamed from: android.support.v7.util.DiffUtil$PostponedUpdate */
    private static class PostponedUpdate {
    }

    /* renamed from: android.support.v7.util.DiffUtil$Range */
    static class Range {
    }

    /* renamed from: android.support.v7.util.DiffUtil$Snake */
    static class Snake {
        /* renamed from: x */
        int f16x;
        /* renamed from: y */
        int f17y;

        Snake() {
        }
    }

    private DiffUtil() {
    }
}
