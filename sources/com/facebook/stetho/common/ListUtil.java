package com.facebook.stetho.common;

import java.util.AbstractList;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class ListUtil {

    private interface ImmutableList<E> extends List<E>, RandomAccess {
    }

    private static final class FiveItemImmutableList<E> extends AbstractList<E> implements ImmutableList<E> {
        private final E mItem0;
        private final E mItem1;
        private final E mItem2;
        private final E mItem3;
        private final E mItem4;

        public FiveItemImmutableList(E item0, E item1, E item2, E item3, E item4) {
            this.mItem0 = item0;
            this.mItem1 = item1;
            this.mItem2 = item2;
            this.mItem3 = item3;
            this.mItem4 = item4;
        }

        public E get(int location) {
            switch (location) {
                case 0:
                    return this.mItem0;
                case 1:
                    return this.mItem1;
                case 2:
                    return this.mItem2;
                case 3:
                    return this.mItem3;
                case 4:
                    return this.mItem4;
                default:
                    throw new IndexOutOfBoundsException();
            }
        }

        public int size() {
            return 5;
        }
    }

    private static final class FourItemImmutableList<E> extends AbstractList<E> implements ImmutableList<E> {
        private final E mItem0;
        private final E mItem1;
        private final E mItem2;
        private final E mItem3;

        public FourItemImmutableList(E item0, E item1, E item2, E item3) {
            this.mItem0 = item0;
            this.mItem1 = item1;
            this.mItem2 = item2;
            this.mItem3 = item3;
        }

        public E get(int location) {
            switch (location) {
                case 0:
                    return this.mItem0;
                case 1:
                    return this.mItem1;
                case 2:
                    return this.mItem2;
                case 3:
                    return this.mItem3;
                default:
                    throw new IndexOutOfBoundsException();
            }
        }

        public int size() {
            return 4;
        }
    }

    private static final class ImmutableArrayList<E> extends AbstractList<E> implements ImmutableList<E> {
        private final Object[] mArray;

        public ImmutableArrayList(Object[] array) {
            this.mArray = array;
        }

        public E get(int location) {
            return this.mArray[location];
        }

        public int size() {
            return this.mArray.length;
        }
    }

    private static final class OneItemImmutableList<E> extends AbstractList<E> implements ImmutableList<E> {
        private final E mItem;

        public OneItemImmutableList(E item) {
            this.mItem = item;
        }

        public E get(int location) {
            if (location == 0) {
                return this.mItem;
            }
            throw new IndexOutOfBoundsException();
        }

        public int size() {
            return 1;
        }
    }

    private static final class ThreeItemImmutableList<E> extends AbstractList<E> implements ImmutableList<E> {
        private final E mItem0;
        private final E mItem1;
        private final E mItem2;

        public ThreeItemImmutableList(E item0, E item1, E item2) {
            this.mItem0 = item0;
            this.mItem1 = item1;
            this.mItem2 = item2;
        }

        public E get(int location) {
            switch (location) {
                case 0:
                    return this.mItem0;
                case 1:
                    return this.mItem1;
                case 2:
                    return this.mItem2;
                default:
                    throw new IndexOutOfBoundsException();
            }
        }

        public int size() {
            return 3;
        }
    }

    private static final class TwoItemImmutableList<E> extends AbstractList<E> implements ImmutableList<E> {
        private final E mItem0;
        private final E mItem1;

        public TwoItemImmutableList(E item0, E item1) {
            this.mItem0 = item0;
            this.mItem1 = item1;
        }

        public E get(int location) {
            switch (location) {
                case 0:
                    return this.mItem0;
                case 1:
                    return this.mItem1;
                default:
                    throw new IndexOutOfBoundsException();
            }
        }

        public int size() {
            return 2;
        }
    }

    private ListUtil() {
    }

    public static <T> boolean identityEquals(List<? extends T> list1, List<? extends T> list2) {
        if (list1 == list2) {
            return true;
        }
        int size = list1.size();
        if (size != list2.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (list1.get(i) != list2.get(i)) {
                return false;
            }
        }
        return true;
    }

    public static <T> List<T> copyToImmutableList(List<T> list) {
        if (list instanceof ImmutableList) {
            return list;
        }
        switch (list.size()) {
            case 0:
                return Collections.emptyList();
            case 1:
                return new OneItemImmutableList(list.get(0));
            case 2:
                return new TwoItemImmutableList(list.get(0), list.get(1));
            case 3:
                return new ThreeItemImmutableList(list.get(0), list.get(1), list.get(2));
            case 4:
                return new FourItemImmutableList(list.get(0), list.get(1), list.get(2), list.get(3));
            case 5:
                return new FiveItemImmutableList(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4));
            default:
                return new ImmutableArrayList(list.toArray());
        }
    }

    public static <T> List<T> newImmutableList(T item) {
        return new OneItemImmutableList(item);
    }
}
