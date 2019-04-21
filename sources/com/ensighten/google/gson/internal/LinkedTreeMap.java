package com.ensighten.google.gson.internal;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

public final class LinkedTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = (!LinkedTreeMap.class.desiredAssertionStatus());
    private static final Comparator<Comparable> NATURAL_ORDER = new C17871();
    Comparator<? super K> comparator;
    private EntrySet entrySet;
    final Node<K, V> header;
    private KeySet keySet;
    int modCount;
    Node<K, V> root;
    int size;

    /* renamed from: com.ensighten.google.gson.internal.LinkedTreeMap$1 */
    static class C17871 implements Comparator<Comparable> {
        C17871() {
        }

        public final int compare(Comparable a, Comparable b) {
            return a.compareTo(b);
        }
    }

    abstract class LinkedTreeMapIterator<T> implements Iterator<T> {
        int expectedModCount;
        Node<K, V> lastReturned;
        Node<K, V> next;

        private LinkedTreeMapIterator() {
            this.next = LinkedTreeMap.this.header.next;
            this.lastReturned = null;
            this.expectedModCount = LinkedTreeMap.this.modCount;
        }

        /* synthetic */ LinkedTreeMapIterator(LinkedTreeMap x0, C17871 x1) {
            this();
        }

        public final boolean hasNext() {
            return this.next != LinkedTreeMap.this.header;
        }

        /* Access modifiers changed, original: final */
        public final Node<K, V> nextNode() {
            Node node = this.next;
            if (node == LinkedTreeMap.this.header) {
                throw new NoSuchElementException();
            } else if (LinkedTreeMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            } else {
                this.next = node.next;
                this.lastReturned = node;
                return node;
            }
        }

        public final void remove() {
            if (this.lastReturned == null) {
                throw new IllegalStateException();
            }
            LinkedTreeMap.this.removeInternal(this.lastReturned, true);
            this.lastReturned = null;
            this.expectedModCount = LinkedTreeMap.this.modCount;
        }
    }

    class EntrySet extends AbstractSet<Entry<K, V>> {

        /* renamed from: com.ensighten.google.gson.internal.LinkedTreeMap$EntrySet$1 */
        class C17881 extends LinkedTreeMapIterator<Entry<K, V>> {
            C17881() {
                super(LinkedTreeMap.this, null);
            }

            public Entry<K, V> next() {
                return nextNode();
            }
        }

        EntrySet() {
        }

        public int size() {
            return LinkedTreeMap.this.size;
        }

        public Iterator<Entry<K, V>> iterator() {
            return new C17881();
        }

        public boolean contains(Object o) {
            return (o instanceof Entry) && LinkedTreeMap.this.findByEntry((Entry) o) != null;
        }

        public boolean remove(Object o) {
            if (!(o instanceof Entry)) {
                return false;
            }
            Node findByEntry = LinkedTreeMap.this.findByEntry((Entry) o);
            if (findByEntry == null) {
                return false;
            }
            LinkedTreeMap.this.removeInternal(findByEntry, true);
            return true;
        }

        public void clear() {
            LinkedTreeMap.this.clear();
        }
    }

    final class KeySet extends AbstractSet<K> {

        /* renamed from: com.ensighten.google.gson.internal.LinkedTreeMap$KeySet$1 */
        class C17891 extends LinkedTreeMapIterator<K> {
            C17891() {
                super(LinkedTreeMap.this, null);
            }

            public K next() {
                return nextNode().key;
            }
        }

        KeySet() {
        }

        public final int size() {
            return LinkedTreeMap.this.size;
        }

        public final Iterator<K> iterator() {
            return new C17891();
        }

        public final boolean contains(Object o) {
            return LinkedTreeMap.this.containsKey(o);
        }

        public final boolean remove(Object key) {
            return LinkedTreeMap.this.removeInternalByKey(key) != null;
        }

        public final void clear() {
            LinkedTreeMap.this.clear();
        }
    }

    static final class Node<K, V> implements Entry<K, V> {
        int height;
        final K key;
        Node<K, V> left;
        Node<K, V> next;
        Node<K, V> parent;
        Node<K, V> prev;
        Node<K, V> right;
        V value;

        Node() {
            this.key = null;
            this.prev = this;
            this.next = this;
        }

        Node(Node<K, V> parent, K key, Node<K, V> next, Node<K, V> prev) {
            this.parent = parent;
            this.key = key;
            this.height = 1;
            this.next = next;
            this.prev = prev;
            prev.next = this;
            next.prev = this;
        }

        public final K getKey() {
            return this.key;
        }

        public final V getValue() {
            return this.value;
        }

        public final V setValue(V value) {
            Object obj = this.value;
            this.value = value;
            return obj;
        }

        public final boolean equals(Object o) {
            if (!(o instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) o;
            if (this.key == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!this.key.equals(entry.getKey())) {
                return false;
            }
            if (this.value == null) {
                if (entry.getValue() != null) {
                    return false;
                }
            } else if (!this.value.equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        public final int hashCode() {
            int i = 0;
            int hashCode = this.key == null ? 0 : this.key.hashCode();
            if (this.value != null) {
                i = this.value.hashCode();
            }
            return hashCode ^ i;
        }

        public final String toString() {
            return this.key + "=" + this.value;
        }

        public final Node<K, V> first() {
            Node<K, V> thisR;
            for (Node<K, V> node = this.left; node != null; node = node.left) {
                thisR = node;
            }
            return thisR;
        }

        public final Node<K, V> last() {
            Node<K, V> thisR;
            for (Node<K, V> node = this.right; node != null; node = node.right) {
                thisR = node;
            }
            return thisR;
        }
    }

    public LinkedTreeMap() {
        this(NATURAL_ORDER);
    }

    public LinkedTreeMap(Comparator<? super K> comparator) {
        this.header = new Node();
        this.size = 0;
        this.modCount = 0;
        if (comparator == null) {
            comparator = NATURAL_ORDER;
        }
        this.comparator = comparator;
    }

    public final int size() {
        return this.size;
    }

    public final V get(Object key) {
        Node findByObject = findByObject(key);
        return findByObject != null ? findByObject.value : null;
    }

    public final boolean containsKey(Object key) {
        return findByObject(key) != null;
    }

    public final V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("key == null");
        }
        Node find = find(key, true);
        Object obj = find.value;
        find.value = value;
        return obj;
    }

    public final void clear() {
        this.root = null;
        this.size = 0;
        this.modCount++;
        Node node = this.header;
        node.prev = node;
        node.next = node;
    }

    public final V remove(Object key) {
        Node removeInternalByKey = removeInternalByKey(key);
        return removeInternalByKey != null ? removeInternalByKey.value : null;
    }

    /* Access modifiers changed, original: final */
    public final Node<K, V> find(K key, boolean create) {
        Node node;
        int i;
        Comparator comparator = this.comparator;
        Node<K, V> node2 = this.root;
        if (node2 != null) {
            Comparable comparable = comparator == NATURAL_ORDER ? (Comparable) key : null;
            while (true) {
                int compareTo = comparable != null ? comparable.compareTo(node2.key) : comparator.compare(key, node2.key);
                if (compareTo != 0) {
                    Node<K, V> node3 = compareTo < 0 ? node2.left : node2.right;
                    if (node3 == null) {
                        int i2 = compareTo;
                        node = node2;
                        i = i2;
                        break;
                    }
                    node2 = node3;
                } else {
                    return node2;
                }
            }
        }
        Node<K, V> node4 = node2;
        i = 0;
        if (!create) {
            return null;
        }
        Node<K, V> node5;
        Node node6 = this.header;
        if (node4 != null) {
            node5 = new Node(node4, key, node6, node6.prev);
            if (i < 0) {
                node4.left = node5;
            } else {
                node4.right = node5;
            }
            rebalance(node4, true);
        } else if (comparator != NATURAL_ORDER || (key instanceof Comparable)) {
            node5 = new Node(node4, key, node6, node6.prev);
            this.root = node5;
        } else {
            throw new ClassCastException(key.getClass().getName() + " is not Comparable");
        }
        this.size++;
        this.modCount++;
        return node5;
    }

    /* Access modifiers changed, original: final */
    public final Node<K, V> findByObject(Object key) {
        Node<K, V> node = null;
        if (key == null) {
            return node;
        }
        try {
            return find(key, false);
        } catch (ClassCastException e) {
            return node;
        }
    }

    /* Access modifiers changed, original: final */
    public final Node<K, V> findByEntry(Entry<?, ?> entry) {
        Node findByObject = findByObject(entry.getKey());
        Object obj = (findByObject == null || !equal(findByObject.value, entry.getValue())) ? null : 1;
        return obj != null ? findByObject : null;
    }

    private boolean equal(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    /* Access modifiers changed, original: final */
    public final void removeInternal(Node<K, V> node, boolean unlink) {
        int i = 0;
        if (unlink) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        Node node2 = node.left;
        Node node3 = node.right;
        Node node4 = node.parent;
        if (node2 == null || node3 == null) {
            if (node2 != null) {
                replaceInParent(node, node2);
                node.left = null;
            } else if (node3 != null) {
                replaceInParent(node, node3);
                node.right = null;
            } else {
                replaceInParent(node, null);
            }
            rebalance(node4, false);
            this.size--;
            this.modCount++;
            return;
        }
        int i2;
        node2 = node2.height > node3.height ? node2.last() : node3.first();
        removeInternal(node2, false);
        node4 = node.left;
        if (node4 != null) {
            i2 = node4.height;
            node2.left = node4;
            node4.parent = node2;
            node.left = null;
        } else {
            i2 = 0;
        }
        node4 = node.right;
        if (node4 != null) {
            i = node4.height;
            node2.right = node4;
            node4.parent = node2;
            node.right = null;
        }
        node2.height = Math.max(i2, i) + 1;
        replaceInParent(node, node2);
    }

    /* Access modifiers changed, original: final */
    public final Node<K, V> removeInternalByKey(Object key) {
        Node findByObject = findByObject(key);
        if (findByObject != null) {
            removeInternal(findByObject, true);
        }
        return findByObject;
    }

    private void replaceInParent(Node<K, V> node, Node<K, V> replacement) {
        Node node2 = node.parent;
        node.parent = null;
        if (replacement != null) {
            replacement.parent = node2;
        }
        if (node2 == null) {
            this.root = replacement;
        } else if (node2.left == node) {
            node2.left = replacement;
        } else if ($assertionsDisabled || node2.right == node) {
            node2.right = replacement;
        } else {
            throw new AssertionError();
        }
    }

    private void rebalance(Node<K, V> node, boolean insert) {
        Node node2;
        while (node2 != null) {
            int i;
            int i2;
            Node node3 = node2.left;
            Node node4 = node2.right;
            if (node3 != null) {
                i = node3.height;
            } else {
                i = 0;
            }
            if (node4 != null) {
                i2 = node4.height;
            } else {
                i2 = 0;
            }
            int i3 = i - i2;
            Node node5;
            if (i3 == -2) {
                node3 = node4.left;
                node5 = node4.right;
                if (node5 != null) {
                    i = node5.height;
                } else {
                    i = 0;
                }
                if (node3 != null) {
                    i2 = node3.height;
                } else {
                    i2 = 0;
                }
                i2 -= i;
                if (i2 == -1 || (i2 == 0 && !insert)) {
                    rotateLeft(node2);
                } else if ($assertionsDisabled || i2 == 1) {
                    rotateRight(node4);
                    rotateLeft(node2);
                } else {
                    throw new AssertionError();
                }
                if (insert) {
                    return;
                }
            } else if (i3 == 2) {
                node4 = node3.left;
                node5 = node3.right;
                i = node5 != null ? node5.height : 0;
                if (node4 != null) {
                    i2 = node4.height;
                } else {
                    i2 = 0;
                }
                i2 -= i;
                if (i2 == 1 || (i2 == 0 && !insert)) {
                    rotateRight(node2);
                } else if ($assertionsDisabled || i2 == -1) {
                    rotateLeft(node3);
                    rotateRight(node2);
                } else {
                    throw new AssertionError();
                }
                if (insert) {
                    return;
                }
            } else if (i3 == 0) {
                node2.height = i + 1;
                if (insert) {
                    return;
                }
            } else if ($assertionsDisabled || i3 == -1 || i3 == 1) {
                node2.height = Math.max(i, i2) + 1;
                if (!insert) {
                    return;
                }
            } else {
                throw new AssertionError();
            }
            node2 = node2.parent;
        }
    }

    private void rotateLeft(Node<K, V> root) {
        int i;
        int i2 = 0;
        Node node = root.left;
        Node node2 = root.right;
        Node node3 = node2.left;
        Node node4 = node2.right;
        root.right = node3;
        if (node3 != null) {
            node3.parent = root;
        }
        replaceInParent(root, node2);
        node2.left = root;
        root.parent = node2;
        if (node != null) {
            i = node.height;
        } else {
            i = 0;
        }
        root.height = Math.max(i, node3 != null ? node3.height : 0) + 1;
        int i3 = root.height;
        if (node4 != null) {
            i2 = node4.height;
        }
        node2.height = Math.max(i3, i2) + 1;
    }

    private void rotateRight(Node<K, V> root) {
        int i;
        int i2 = 0;
        Node node = root.left;
        Node node2 = root.right;
        Node node3 = node.left;
        Node node4 = node.right;
        root.left = node4;
        if (node4 != null) {
            node4.parent = root;
        }
        replaceInParent(root, node);
        node.right = root;
        root.parent = node;
        if (node2 != null) {
            i = node2.height;
        } else {
            i = 0;
        }
        root.height = Math.max(i, node4 != null ? node4.height : 0) + 1;
        int i3 = root.height;
        if (node3 != null) {
            i2 = node3.height;
        }
        node.height = Math.max(i3, i2) + 1;
    }

    public final Set<Entry<K, V>> entrySet() {
        EntrySet entrySet = this.entrySet;
        if (entrySet != null) {
            return entrySet;
        }
        Set<Entry<K, V>> entrySet2 = new EntrySet();
        this.entrySet = entrySet2;
        return entrySet2;
    }

    public final Set<K> keySet() {
        KeySet keySet = this.keySet;
        if (keySet != null) {
            return keySet;
        }
        Set<K> keySet2 = new KeySet();
        this.keySet = keySet2;
        return keySet2;
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }
}
