package com.amap.api.mapcore2d;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/* renamed from: com.amap.api.mapcore2d.bk */
class SyncList<T> implements List<T> {
    /* renamed from: a */
    private LinkedList<T> f2235a = new LinkedList();

    SyncList() {
    }

    /* renamed from: a */
    public synchronized void mo9731a(T t) {
        add(t);
    }

    public synchronized void add(int i, T t) {
        this.f2235a.add(i, t);
    }

    public synchronized boolean addAll(Collection<? extends T> collection) {
        return this.f2235a.addAll(collection);
    }

    public synchronized boolean addAll(int i, Collection<? extends T> collection) {
        return this.f2235a.addAll(i, collection);
    }

    public synchronized void clear() {
        this.f2235a.clear();
    }

    public synchronized boolean contains(Object obj) {
        return this.f2235a.contains(obj);
    }

    public synchronized boolean containsAll(Collection<?> collection) {
        return this.f2235a.containsAll(collection);
    }

    public synchronized T get(int i) {
        T t;
        String str = "get";
        t = null;
        try {
            t = this.f2235a.get(i);
        } catch (Exception e) {
            C0955ck.m3888a(e, "SyncList", str);
        }
        return t;
    }

    public synchronized int indexOf(Object obj) {
        return this.f2235a.indexOf(obj);
    }

    public synchronized boolean isEmpty() {
        return this.f2235a.isEmpty();
    }

    public synchronized Iterator<T> iterator() {
        return this.f2235a.listIterator();
    }

    public synchronized int lastIndexOf(Object obj) {
        return this.f2235a.lastIndexOf(obj);
    }

    public synchronized ListIterator<T> listIterator() {
        return this.f2235a.listIterator();
    }

    public synchronized ListIterator<T> listIterator(int i) {
        return this.f2235a.listIterator(i);
    }

    public synchronized T remove(int i) {
        return this.f2235a.remove(i);
    }

    public synchronized boolean remove(Object obj) {
        return this.f2235a.remove(obj);
    }

    public synchronized boolean removeAll(Collection<?> collection) {
        return this.f2235a.removeAll(collection);
    }

    public synchronized boolean retainAll(Collection<?> collection) {
        return this.f2235a.retainAll(collection);
    }

    public synchronized T set(int i, T t) {
        return this.f2235a.set(i, t);
    }

    public synchronized int size() {
        return this.f2235a.size();
    }

    public synchronized List<T> subList(int i, int i2) {
        return this.f2235a.subList(i, i2);
    }

    public synchronized Object[] toArray() {
        return this.f2235a.toArray();
    }

    public synchronized <V> V[] toArray(V[] vArr) {
        return this.f2235a.toArray(vArr);
    }

    public synchronized boolean add(T t) {
        return this.f2235a.add(t);
    }
}
