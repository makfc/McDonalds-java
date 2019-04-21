package com.facebook.stetho.inspector.elements;

import android.os.SystemClock;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.ArrayListAccumulator;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.elements.ShadowDocument.Update;
import com.facebook.stetho.inspector.elements.ShadowDocument.UpdateBuilder;
import com.facebook.stetho.inspector.helper.ObjectIdMapper;
import com.facebook.stetho.inspector.helper.ThreadBoundProxy;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class Document extends ThreadBoundProxy {
    private AttributeListAccumulator mCachedAttributeAccumulator;
    private ChildEventingList mCachedChildEventingList;
    private ArrayListAccumulator<Object> mCachedChildrenAccumulator;
    private final Queue<Object> mCachedUpdateQueue = new ArrayDeque();
    private DocumentProvider mDocumentProvider;
    private final DocumentProviderFactory mFactory;
    private final ObjectIdMapper mObjectIdMapper = new DocumentObjectIdMapper(this, null);
    @GuardedBy
    private int mReferenceCounter = 0;
    private ShadowDocument mShadowDocument;
    private UpdateListenerCollection mUpdateListeners = new UpdateListenerCollection();

    /* renamed from: com.facebook.stetho.inspector.elements.Document$1 */
    class C19791 implements Runnable {
        C19791() {
        }

        public void run() {
            Document.this.mShadowDocument = new ShadowDocument(Document.this.mDocumentProvider.getRootElement());
            Document.this.createShadowDocumentUpdate().commit();
            Document.this.mDocumentProvider.setListener(new ProviderListener(Document.this, null));
        }
    }

    /* renamed from: com.facebook.stetho.inspector.elements.Document$2 */
    class C19802 implements Runnable {
        C19802() {
        }

        public void run() {
            Document.this.mDocumentProvider.setListener(null);
            Document.this.mShadowDocument = null;
            Document.this.mObjectIdMapper.clear();
            Document.this.mDocumentProvider.dispose();
            Document.this.mDocumentProvider = null;
        }
    }

    public static final class AttributeListAccumulator extends ArrayList<String> implements AttributeAccumulator {
        public void store(String name, String value) {
            add(name);
            add(value);
        }
    }

    private final class ChildEventingList extends ArrayList<Object> {
        private DocumentView mDocumentView;
        private Object mParentElement;
        private int mParentNodeId;

        private ChildEventingList() {
            this.mParentElement = null;
            this.mParentNodeId = -1;
        }

        /* synthetic */ ChildEventingList(Document x0, C19791 x1) {
            this();
        }

        public void acquire(Object parentElement, DocumentView documentView) {
            int i;
            this.mParentElement = parentElement;
            if (this.mParentElement == null) {
                i = -1;
            } else {
                i = Document.this.mObjectIdMapper.getIdForObject(this.mParentElement).intValue();
            }
            this.mParentNodeId = i;
            this.mDocumentView = documentView;
        }

        public void release() {
            clear();
            this.mParentElement = null;
            this.mParentNodeId = -1;
            this.mDocumentView = null;
        }

        public void addWithEvent(int index, Object element, Accumulator<Object> insertedElements) {
            int previousNodeId;
            Object previousElement = index == 0 ? null : get(index - 1);
            if (previousElement == null) {
                previousNodeId = -1;
            } else {
                previousNodeId = Document.this.mObjectIdMapper.getIdForObject(previousElement).intValue();
            }
            add(index, element);
            Document.this.mUpdateListeners.onChildNodeInserted(this.mDocumentView, element, this.mParentNodeId, previousNodeId, insertedElements);
        }

        public void removeWithEvent(int index) {
            Document.this.mUpdateListeners.onChildNodeRemoved(this.mParentNodeId, Document.this.mObjectIdMapper.getIdForObject(remove(index)).intValue());
        }
    }

    private final class DocumentObjectIdMapper extends ObjectIdMapper {
        private DocumentObjectIdMapper() {
        }

        /* synthetic */ DocumentObjectIdMapper(Document x0, C19791 x1) {
            this();
        }

        /* Access modifiers changed, original: protected */
        public void onMapped(Object object, int id) {
            Document.this.verifyThreadAccess();
            Document.this.mDocumentProvider.getNodeDescriptor(object).hook(object);
        }

        /* Access modifiers changed, original: protected */
        public void onUnmapped(Object object, int id) {
            Document.this.verifyThreadAccess();
            Document.this.mDocumentProvider.getNodeDescriptor(object).unhook(object);
        }
    }

    private final class ProviderListener implements DocumentProviderListener {
        private ProviderListener() {
        }

        /* synthetic */ ProviderListener(Document x0, C19791 x1) {
            this();
        }

        public void onPossiblyChanged() {
            Document.this.updateTree();
        }

        public void onAttributeModified(Object element, String name, String value) {
            Document.this.verifyThreadAccess();
            Document.this.mUpdateListeners.onAttributeModified(element, name, value);
        }

        public void onAttributeRemoved(Object element, String name) {
            Document.this.verifyThreadAccess();
            Document.this.mUpdateListeners.onAttributeRemoved(element, name);
        }

        public void onInspectRequested(Object element) {
            Document.this.verifyThreadAccess();
            Document.this.mUpdateListeners.onInspectRequested(element);
        }
    }

    public interface UpdateListener {
        void onAttributeModified(Object obj, String str, String str2);

        void onAttributeRemoved(Object obj, String str);

        void onChildNodeInserted(DocumentView documentView, Object obj, int i, int i2, Accumulator<Object> accumulator);

        void onChildNodeRemoved(int i, int i2);

        void onInspectRequested(Object obj);
    }

    private class UpdateListenerCollection implements UpdateListener {
        private final List<UpdateListener> mListeners = new ArrayList();
        private volatile UpdateListener[] mListenersSnapshot;

        public synchronized void add(UpdateListener listener) {
            this.mListeners.add(listener);
            this.mListenersSnapshot = null;
        }

        public synchronized void remove(UpdateListener listener) {
            this.mListeners.remove(listener);
            this.mListenersSnapshot = null;
        }

        public synchronized void clear() {
            this.mListeners.clear();
            this.mListenersSnapshot = null;
        }

        private UpdateListener[] getListenersSnapshot() {
            UpdateListener[] listenersSnapshot;
            while (true) {
                listenersSnapshot = this.mListenersSnapshot;
                if (listenersSnapshot != null) {
                    break;
                }
                synchronized (this) {
                    if (this.mListenersSnapshot == null) {
                        this.mListenersSnapshot = (UpdateListener[]) this.mListeners.toArray(new UpdateListener[this.mListeners.size()]);
                        listenersSnapshot = this.mListenersSnapshot;
                    }
                }
            }
            return listenersSnapshot;
        }

        public void onAttributeModified(Object element, String name, String value) {
            for (UpdateListener listener : getListenersSnapshot()) {
                listener.onAttributeModified(element, name, value);
            }
        }

        public void onAttributeRemoved(Object element, String name) {
            for (UpdateListener listener : getListenersSnapshot()) {
                listener.onAttributeRemoved(element, name);
            }
        }

        public void onInspectRequested(Object element) {
            for (UpdateListener listener : getListenersSnapshot()) {
                listener.onInspectRequested(element);
            }
        }

        public void onChildNodeRemoved(int parentNodeId, int nodeId) {
            for (UpdateListener listener : getListenersSnapshot()) {
                listener.onChildNodeRemoved(parentNodeId, nodeId);
            }
        }

        public void onChildNodeInserted(DocumentView view, Object element, int parentNodeId, int previousNodeId, Accumulator<Object> insertedItems) {
            for (UpdateListener listener : getListenersSnapshot()) {
                listener.onChildNodeInserted(view, element, parentNodeId, previousNodeId, insertedItems);
            }
        }
    }

    public Document(DocumentProviderFactory factory) {
        super(factory);
        this.mFactory = factory;
    }

    public synchronized void addRef() {
        int i = this.mReferenceCounter;
        this.mReferenceCounter = i + 1;
        if (i == 0) {
            init();
        }
    }

    public synchronized void release() {
        if (this.mReferenceCounter > 0) {
            int i = this.mReferenceCounter - 1;
            this.mReferenceCounter = i;
            if (i == 0) {
                cleanUp();
            }
        }
    }

    private void init() {
        this.mDocumentProvider = this.mFactory.create();
        this.mDocumentProvider.postAndWait((Runnable) new C19791());
    }

    private void cleanUp() {
        this.mDocumentProvider.postAndWait((Runnable) new C19802());
        this.mUpdateListeners.clear();
    }

    public void addUpdateListener(UpdateListener updateListener) {
        this.mUpdateListeners.add(updateListener);
    }

    public void removeUpdateListener(UpdateListener updateListener) {
        this.mUpdateListeners.remove(updateListener);
    }

    @Nullable
    public NodeDescriptor getNodeDescriptor(Object element) {
        verifyThreadAccess();
        return this.mDocumentProvider.getNodeDescriptor(element);
    }

    public void highlightElement(Object element, int color) {
        verifyThreadAccess();
        this.mDocumentProvider.highlightElement(element, color);
    }

    public void hideHighlight() {
        verifyThreadAccess();
        this.mDocumentProvider.hideHighlight();
    }

    public void setInspectModeEnabled(boolean enabled) {
        verifyThreadAccess();
        this.mDocumentProvider.setInspectModeEnabled(enabled);
    }

    @Nullable
    public Integer getNodeIdForElement(Object element) {
        return this.mObjectIdMapper.getIdForObject(element);
    }

    @Nullable
    public Object getElementForNodeId(int id) {
        return this.mObjectIdMapper.getObjectForId(id);
    }

    public void setAttributesAsText(Object element, String text) {
        verifyThreadAccess();
        this.mDocumentProvider.setAttributesAsText(element, text);
    }

    public void getElementStyles(Object element, StyleAccumulator styleAccumulator) {
        getNodeDescriptor(element).getStyles(element, styleAccumulator);
    }

    public DocumentView getDocumentView() {
        verifyThreadAccess();
        return this.mShadowDocument;
    }

    public Object getRootElement() {
        verifyThreadAccess();
        Object rootElement = this.mDocumentProvider.getRootElement();
        if (rootElement == null) {
            throw new IllegalStateException();
        } else if (rootElement == this.mShadowDocument.getRootElement()) {
            return rootElement;
        } else {
            throw new IllegalStateException();
        }
    }

    public void findMatchingElements(String query, Accumulator<Integer> matchedIds) {
        verifyThreadAccess();
        findMatches(this.mDocumentProvider.getRootElement(), Pattern.compile(Pattern.quote(query), 2), matchedIds);
    }

    private void findMatches(Object element, Pattern queryPattern, Accumulator<Integer> matchedIds) {
        ElementInfo info = this.mShadowDocument.getElementInfo(element);
        int size = info.children.size();
        for (int i = 0; i < size; i++) {
            Object childElement = info.children.get(i);
            if (doesElementMatch(childElement, queryPattern)) {
                matchedIds.store(this.mObjectIdMapper.getIdForObject(childElement));
            }
            findMatches(childElement, queryPattern, matchedIds);
        }
    }

    private boolean doesElementMatch(Object element, Pattern queryPattern) {
        AttributeListAccumulator accumulator = acquireCachedAttributeAccumulator();
        NodeDescriptor descriptor = this.mDocumentProvider.getNodeDescriptor(element);
        descriptor.getAttributes(element, accumulator);
        int N = accumulator.size();
        for (int i = 0; i < N; i++) {
            if (queryPattern.matcher((CharSequence) accumulator.get(i)).find()) {
                releaseCachedAttributeAccumulator(accumulator);
                return true;
            }
        }
        releaseCachedAttributeAccumulator(accumulator);
        return queryPattern.matcher(descriptor.getNodeName(element)).find();
    }

    private ChildEventingList acquireChildEventingList(Object parentElement, DocumentView documentView) {
        ChildEventingList childEventingList = this.mCachedChildEventingList;
        if (childEventingList == null) {
            childEventingList = new ChildEventingList(this, null);
        }
        this.mCachedChildEventingList = null;
        childEventingList.acquire(parentElement, documentView);
        return childEventingList;
    }

    private void releaseChildEventingList(ChildEventingList childEventingList) {
        childEventingList.release();
        if (this.mCachedChildEventingList == null) {
            this.mCachedChildEventingList = childEventingList;
        }
    }

    private AttributeListAccumulator acquireCachedAttributeAccumulator() {
        AttributeListAccumulator accumulator = this.mCachedAttributeAccumulator;
        if (accumulator == null) {
            accumulator = new AttributeListAccumulator();
        }
        this.mCachedChildrenAccumulator = null;
        return accumulator;
    }

    private void releaseCachedAttributeAccumulator(AttributeListAccumulator accumulator) {
        accumulator.clear();
        if (this.mCachedAttributeAccumulator == null) {
            this.mCachedAttributeAccumulator = accumulator;
        }
    }

    private ArrayListAccumulator<Object> acquireChildrenAccumulator() {
        ArrayListAccumulator<Object> accumulator = this.mCachedChildrenAccumulator;
        if (accumulator == null) {
            accumulator = new ArrayListAccumulator();
        }
        this.mCachedChildrenAccumulator = null;
        return accumulator;
    }

    private void releaseChildrenAccumulator(ArrayListAccumulator<Object> accumulator) {
        accumulator.clear();
        if (this.mCachedChildrenAccumulator == null) {
            this.mCachedChildrenAccumulator = accumulator;
        }
    }

    private Update createShadowDocumentUpdate() {
        verifyThreadAccess();
        if (this.mDocumentProvider.getRootElement() != this.mShadowDocument.getRootElement()) {
            throw new IllegalStateException();
        }
        ArrayListAccumulator<Object> childrenAccumulator = acquireChildrenAccumulator();
        UpdateBuilder updateBuilder = this.mShadowDocument.beginUpdate();
        this.mCachedUpdateQueue.add(this.mDocumentProvider.getRootElement());
        while (!this.mCachedUpdateQueue.isEmpty()) {
            Object element = this.mCachedUpdateQueue.remove();
            NodeDescriptor descriptor = this.mDocumentProvider.getNodeDescriptor(element);
            this.mObjectIdMapper.putObject(element);
            descriptor.getChildren(element, childrenAccumulator);
            int i = 0;
            int size = childrenAccumulator.size();
            while (i < size) {
                Object child = childrenAccumulator.get(i);
                if (child != null) {
                    this.mCachedUpdateQueue.add(child);
                } else {
                    LogUtil.m7448e("%s.getChildren() emitted a null child at position %s for element %s", descriptor.getClass().getName(), Integer.toString(i), element);
                    childrenAccumulator.remove(i);
                    i--;
                    size--;
                }
                i++;
            }
            updateBuilder.setElementChildren(element, childrenAccumulator);
            childrenAccumulator.clear();
        }
        releaseChildrenAccumulator(childrenAccumulator);
        return updateBuilder.build();
    }

    private void updateTree() {
        long startTimeMs = SystemClock.elapsedRealtime();
        Update docUpdate = createShadowDocumentUpdate();
        boolean isEmpty = docUpdate.isEmpty();
        if (isEmpty) {
            docUpdate.abandon();
        } else {
            applyDocumentUpdate(docUpdate);
        }
        String str = "Document.updateTree() completed in %s ms%s";
        Object[] objArr = new Object[2];
        objArr[0] = Long.toString(SystemClock.elapsedRealtime() - startTimeMs);
        objArr[1] = isEmpty ? " (no changes)" : "";
        LogUtil.m7444d(str, objArr);
    }

    private void applyDocumentUpdate(final Update docUpdate) {
        docUpdate.getGarbageElements(new Accumulator<Object>() {
            public void store(Object element) {
                if (Document.this.mObjectIdMapper.containsObject(element)) {
                    if (docUpdate.getElementInfo(element).parentElement == null) {
                        Document.this.mUpdateListeners.onChildNodeRemoved(Document.this.mObjectIdMapper.getIdForObject(Document.this.mShadowDocument.getElementInfo(element).parentElement).intValue(), Document.this.mObjectIdMapper.getIdForObject(element).intValue());
                    }
                    Document.this.mObjectIdMapper.removeObject(element);
                    return;
                }
                throw new IllegalStateException();
            }
        });
        docUpdate.getChangedElements(new Accumulator<Object>() {
            private Accumulator<Object> insertedElements = new C19821();
            private final HashSet<Object> listenerInsertedElements = new HashSet();

            /* renamed from: com.facebook.stetho.inspector.elements.Document$4$1 */
            class C19821 implements Accumulator<Object> {
                C19821() {
                }

                public void store(Object element) {
                    if (docUpdate.isElementChanged(element)) {
                        C19834.this.listenerInsertedElements.add(element);
                    }
                }
            }

            public void store(Object element) {
                if (Document.this.mObjectIdMapper.containsObject(element) && !this.listenerInsertedElements.contains(element)) {
                    List<Object> oldChildren;
                    ElementInfo newElementInfo = docUpdate.getElementInfo(element);
                    ElementInfo oldElementInfo = Document.this.mShadowDocument.getElementInfo(element);
                    if (oldElementInfo != null) {
                        oldChildren = oldElementInfo.children;
                    } else {
                        oldChildren = Collections.emptyList();
                    }
                    List<Object> newChildren = newElementInfo.children;
                    ChildEventingList listenerChildren = Document.this.acquireChildEventingList(element, docUpdate);
                    int N = oldChildren.size();
                    for (int i = 0; i < N; i++) {
                        Object childElement = oldChildren.get(i);
                        if (Document.this.mObjectIdMapper.containsObject(childElement)) {
                            listenerChildren.add(childElement);
                        }
                    }
                    Document.updateListenerChildren(listenerChildren, newChildren, this.insertedElements);
                    Document.this.releaseChildEventingList(listenerChildren);
                }
            }
        });
        docUpdate.commit();
    }

    private static void updateListenerChildren(ChildEventingList listenerChildren, List<Object> newChildren, Accumulator<Object> insertedElements) {
        int index = 0;
        while (index <= listenerChildren.size()) {
            if (index == listenerChildren.size()) {
                if (index != newChildren.size()) {
                    listenerChildren.addWithEvent(index, newChildren.get(index), insertedElements);
                    index++;
                } else {
                    return;
                }
            } else if (index == newChildren.size()) {
                listenerChildren.removeWithEvent(index);
            } else {
                Object listenerElement = listenerChildren.get(index);
                Object newElement = newChildren.get(index);
                if (listenerElement == newElement) {
                    index++;
                } else {
                    int newElementListenerIndex = listenerChildren.indexOf(newElement);
                    if (newElementListenerIndex == -1) {
                        listenerChildren.addWithEvent(index, newElement, insertedElements);
                        index++;
                    } else {
                        listenerChildren.removeWithEvent(newElementListenerIndex);
                        listenerChildren.addWithEvent(index, newElement, insertedElements);
                        index++;
                    }
                }
            }
        }
    }
}
