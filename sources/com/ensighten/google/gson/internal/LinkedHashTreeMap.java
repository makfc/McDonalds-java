package com.ensighten.google.gson.internal;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

public final class LinkedHashTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = (!LinkedHashTreeMap.class.desiredAssertionStatus());
    private static final Comparator<Comparable> NATURAL_ORDER = new C17841();
    Comparator<? super K> comparator;
    private EntrySet entrySet;
    final Node<K, V> header;
    private KeySet keySet;
    int modCount;
    int size;
    Node<K, V>[] table;
    int threshold;

    /* renamed from: com.ensighten.google.gson.internal.LinkedHashTreeMap$1 */
    static class C17841 implements Comparator<Comparable> {
        C17841() {
        }

        public final int compare(Comparable a, Comparable b) {
            return a.compareTo(b);
        }
    }

    static final class AvlBuilder<K, V> {
        private int leavesSkipped;
        private int leavesToSkip;
        private int size;
        private Node<K, V> stack;

        AvlBuilder() {
        }

        /* Access modifiers changed, original: final */
        public final void reset(int targetSize) {
            this.leavesToSkip = ((Integer.highestOneBit(targetSize) * 2) - 1) - targetSize;
            this.size = 0;
            this.leavesSkipped = 0;
            this.stack = null;
        }

        /* Access modifiers changed, original: final */
        public final void add(Node<K, V> node) {
            node.right = null;
            node.parent = null;
            node.left = null;
            node.height = 1;
            if (this.leavesToSkip > 0 && (this.size & 1) == 0) {
                this.size++;
                this.leavesToSkip--;
                this.leavesSkipped++;
            }
            node.parent = this.stack;
            this.stack = node;
            this.size++;
            if (this.leavesToSkip > 0 && (this.size & 1) == 0) {
                this.size++;
                this.leavesToSkip--;
                this.leavesSkipped++;
            }
            for (int i = 4; (this.size & (i - 1)) == i - 1; i *= 2) {
                Node node2;
                Node node3;
                if (this.leavesSkipped == 0) {
                    node2 = this.stack;
                    node3 = node2.parent;
                    Node node4 = node3.parent;
                    node3.parent = node4.parent;
                    this.stack = node3;
                    node3.left = node4;
                    node3.right = node2;
                    node3.height = node2.height + 1;
                    node4.parent = node3;
                    node2.parent = node3;
                } else if (this.leavesSkipped == 1) {
                    node2 = this.stack;
                    node3 = node2.parent;
                    this.stack = node3;
                    node3.right = node2;
                    node3.height = node2.height + 1;
                    node2.parent = node3;
                    this.leavesSkipped = 0;
                } else if (this.leavesSkipped == 2) {
                    this.leavesSkipped = 0;
                }
            }
        }

        /* Access modifiers changed, original: final */
        public final Node<K, V> root() {
            Node node = this.stack;
            if (node.parent == null) {
                return node;
            }
            throw new IllegalStateException();
        }
    }

    static class AvlIterator<K, V> {
        private Node<K, V> stackTop;

        AvlIterator() {
        }

        /* Access modifiers changed, original: 0000 */
        public void reset(Node<K, V> node) {
            Node node2 = null;
            Node node3;
            while (node3 != null) {
                node3.parent = node2;
                node2 = node3;
                node3 = node3.left;
            }
            this.stackTop = node2;
        }

        public Node<K, V> next() {
            Node<K, V> node = this.stackTop;
            if (node == null) {
                return null;
            }
            Node node2 = node.parent;
            node.parent = null;
            for (Node node3 = node.right; node3 != null; node3 = node3.left) {
                node3.parent = node2;
                node2 = node3;
            }
            this.stackTop = node2;
            return node;
        }
    }

    abstract class LinkedTreeMapIterator<T> implements Iterator<T> {
        int expectedModCount;
        Node<K, V> lastReturned;
        Node<K, V> next;

        private LinkedTreeMapIterator() {
            this.next = LinkedHashTreeMap.this.header.next;
            this.lastReturned = null;
            this.expectedModCount = LinkedHashTreeMap.this.modCount;
        }

        /* synthetic */ LinkedTreeMapIterator(LinkedHashTreeMap x0, C17841 x1) {
            this();
        }

        public final boolean hasNext() {
            return this.next != LinkedHashTreeMap.this.header;
        }

        /* Access modifiers changed, original: final */
        public final Node<K, V> nextNode() {
            Node node = this.next;
            if (node == LinkedHashTreeMap.this.header) {
                throw new NoSuchElementException();
            } else if (LinkedHashTreeMap.this.modCount != this.expectedModCount) {
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
            LinkedHashTreeMap.this.removeInternal(this.lastReturned, true);
            this.lastReturned = null;
            this.expectedModCount = LinkedHashTreeMap.this.modCount;
        }
    }

    final class EntrySet extends AbstractSet<Entry<K, V>> {

        /* renamed from: com.ensighten.google.gson.internal.LinkedHashTreeMap$EntrySet$1 */
        class C17851 extends LinkedTreeMapIterator<Entry<K, V>> {
            C17851() {
                super(LinkedHashTreeMap.this, null);
            }

            public Entry<K, V> next() {
                return nextNode();
            }
        }

        EntrySet() {
        }

        public final int size() {
            return LinkedHashTreeMap.this.size;
        }

        public final Iterator<Entry<K, V>> iterator() {
            return new C17851();
        }

        public final boolean contains(Object o) {
            return (o instanceof Entry) && LinkedHashTreeMap.this.findByEntry((Entry) o) != null;
        }

        public final boolean remove(Object o) {
            if (!(o instanceof Entry)) {
                return false;
            }
            Node findByEntry = LinkedHashTreeMap.this.findByEntry((Entry) o);
            if (findByEntry == null) {
                return false;
            }
            LinkedHashTreeMap.this.removeInternal(findByEntry, true);
            return true;
        }

        public final void clear() {
            LinkedHashTreeMap.this.clear();
        }
    }

    final class KeySet extends AbstractSet<K> {

        /* renamed from: com.ensighten.google.gson.internal.LinkedHashTreeMap$KeySet$1 */
        class C17861 extends LinkedTreeMapIterator<K> {
            C17861() {
                super(LinkedHashTreeMap.this, null);
            }

            public K next() {
                return nextNode().key;
            }
        }

        KeySet() {
        }

        public final int size() {
            return LinkedHashTreeMap.this.size;
        }

        public final Iterator<K> iterator() {
            return new C17861();
        }

        public final boolean contains(Object o) {
            return LinkedHashTreeMap.this.containsKey(o);
        }

        public final boolean remove(Object key) {
            return LinkedHashTreeMap.this.removeInternalByKey(key) != null;
        }

        public final void clear() {
            LinkedHashTreeMap.this.clear();
        }
    }

    static final class Node<K, V> implements Entry<K, V> {
        final int hash;
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
            this.hash = -1;
            this.prev = this;
            this.next = this;
        }

        Node(Node<K, V> parent, K key, int hash, Node<K, V> next, Node<K, V> prev) {
            this.parent = parent;
            this.key = key;
            this.hash = hash;
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

    public LinkedHashTreeMap() {
        this(NATURAL_ORDER);
    }

    public LinkedHashTreeMap(Comparator<? super K> comparator) {
        this.size = 0;
        this.modCount = 0;
        if (comparator == null) {
            comparator = NATURAL_ORDER;
        }
        this.comparator = comparator;
        this.header = new Node();
        this.table = new Node[16];
        this.threshold = (this.table.length / 2) + (this.table.length / 4);
    }

    private static int secondaryHash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return ((h >>> 7) ^ h) ^ (h >>> 4);
    }

    static <K, V> Node<K, V>[] doubleCapacity(Node<K, V>[] oldTable) {
        int length = oldTable.length;
        Node[] nodeArr = new Node[(length * 2)];
        AvlIterator avlIterator = new AvlIterator();
        AvlBuilder avlBuilder = new AvlBuilder();
        AvlBuilder avlBuilder2 = new AvlBuilder();
        for (int i = 0; i < length; i++) {
            Node node = oldTable[i];
            if (node != null) {
                Node root;
                avlIterator.reset(node);
                int i2 = 0;
                int i3 = 0;
                while (true) {
                    Node next = avlIterator.next();
                    if (next == null) {
                        break;
                    } else if ((next.hash & length) == 0) {
                        i3++;
                    } else {
                        i2++;
                    }
                }
                avlBuilder.reset(i3);
                avlBuilder2.reset(i2);
                avlIterator.reset(node);
                while (true) {
                    node = avlIterator.next();
                    if (node == null) {
                        break;
                    } else if ((node.hash & length) == 0) {
                        avlBuilder.add(node);
                    } else {
                        avlBuilder2.add(node);
                    }
                }
                nodeArr[i] = i3 > 0 ? avlBuilder.root() : null;
                i3 = i + length;
                if (i2 > 0) {
                    root = avlBuilder2.root();
                } else {
                    root = null;
                }
                nodeArr[i3] = root;
            }
        }
        return nodeArr;
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
        Arrays.fill(this.table, null);
        this.size = 0;
        this.modCount++;
        Node node = this.header;
        Node node2 = node.next;
        while (node2 != node) {
            Node node3 = node2.next;
            node2.prev = null;
            node2.next = null;
            node2 = node3;
        }
        node.prev = node;
        node.next = node;
    }

    public final V remove(Object key) {
        Node removeInternalByKey = removeInternalByKey(key);
        return removeInternalByKey != null ? removeInternalByKey.value : null;
    }

    /* Access modifiers changed, original: final */
    public final Node<K, V> find(K key, boolean create) {
        int i;
        Comparator comparator = this.comparator;
        Node[] nodeArr = this.table;
        int secondaryHash = secondaryHash(key.hashCode());
        int length = secondaryHash & (nodeArr.length - 1);
        Node node = nodeArr[length];
        if (node != null) {
            Comparable comparable = comparator == NATURAL_ORDER ? (Comparable) key : null;
            while (true) {
                int compareTo = comparable != null ? comparable.compareTo(node.key) : comparator.compare(key, node.key);
                if (compareTo != 0) {
                    Node node2 = compareTo < 0 ? node.left : node.right;
                    if (node2 == null) {
                        i = compareTo;
                        break;
                    }
                    node = node2;
                } else {
                    return node;
                }
            }
        }
        i = 0;
        if (!create) {
            return null;
        }
        Node<K, V> node3;
        Node node4 = this.header;
        if (node != null) {
            node3 = new Node(node, key, secondaryHash, node4, node4.prev);
            if (i < 0) {
                node.left = node3;
            } else {
                node.right = node3;
            }
            rebalance(node, true);
        } else if (comparator != NATURAL_ORDER || (key instanceof Comparable)) {
            node3 = new Node(node, key, secondaryHash, node4, node4.prev);
            nodeArr[length] = node3;
        } else {
            throw new ClassCastException(key.getClass().getName() + " is not Comparable");
        }
        int i2 = this.size;
        this.size = i2 + 1;
        if (i2 > this.threshold) {
            doubleCapacity();
        }
        this.modCount++;
        return node3;
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
            node.prev = null;
            node.next = null;
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
            this.table[node.hash & (this.table.length - 1)] = replacement;
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

    private void doubleCapacity() {
        this.table = doubleCapacity(this.table);
        this.threshold = (this.table.length / 2) + (this.table.length / 4);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }
}
