package com.facebook.stetho.inspector.protocol.module;

import android.graphics.Color;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.ArrayListAccumulator;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.UncheckedCallable;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.elements.Document;
import com.facebook.stetho.inspector.elements.Document.AttributeListAccumulator;
import com.facebook.stetho.inspector.elements.Document.UpdateListener;
import com.facebook.stetho.inspector.elements.DocumentView;
import com.facebook.stetho.inspector.elements.ElementInfo;
import com.facebook.stetho.inspector.elements.NodeDescriptor;
import com.facebook.stetho.inspector.elements.NodeType;
import com.facebook.stetho.inspector.helper.ChromePeerManager;
import com.facebook.stetho.inspector.helper.PeersRegisteredListener;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcException;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError.ErrorCode;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsMethod;
import com.facebook.stetho.inspector.protocol.module.Runtime.ObjectSubType;
import com.facebook.stetho.inspector.protocol.module.Runtime.ObjectType;
import com.facebook.stetho.inspector.protocol.module.Runtime.RemoteObject;
import com.facebook.stetho.json.ObjectMapper;
import com.facebook.stetho.json.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

public class DOM implements ChromeDevtoolsDomain {
    private ChildNodeInsertedEvent mCachedChildNodeInsertedEvent;
    private ChildNodeRemovedEvent mCachedChildNodeRemovedEvent;
    private final Document mDocument;
    private final DocumentUpdateListener mListener;
    private final ObjectMapper mObjectMapper = new ObjectMapper();
    private final ChromePeerManager mPeerManager;
    private final AtomicInteger mResultCounter;
    private final Map<String, List<Integer>> mSearchResults;

    /* renamed from: com.facebook.stetho.inspector.protocol.module.DOM$1 */
    class C20041 implements UncheckedCallable<Node> {
        C20041() {
        }

        public Node call() {
            return DOM.this.createNodeForElement(DOM.this.mDocument.getRootElement(), DOM.this.mDocument.getDocumentView());
        }
    }

    /* renamed from: com.facebook.stetho.inspector.protocol.module.DOM$3 */
    class C20063 implements Runnable {
        C20063() {
        }

        public void run() {
            DOM.this.mDocument.hideHighlight();
        }
    }

    private static class AttributeModifiedEvent {
        @JsonProperty(required = true)
        public String name;
        @JsonProperty(required = true)
        public int nodeId;
        @JsonProperty(required = true)
        public String value;

        private AttributeModifiedEvent() {
        }

        /* synthetic */ AttributeModifiedEvent(C20041 x0) {
            this();
        }
    }

    private static class AttributeRemovedEvent {
        @JsonProperty(required = true)
        public String name;
        @JsonProperty(required = true)
        public int nodeId;

        private AttributeRemovedEvent() {
        }

        /* synthetic */ AttributeRemovedEvent(C20041 x0) {
            this();
        }
    }

    private static class ChildNodeInsertedEvent {
        @JsonProperty(required = true)
        public Node node;
        @JsonProperty(required = true)
        public int parentNodeId;
        @JsonProperty(required = true)
        public int previousNodeId;

        private ChildNodeInsertedEvent() {
        }

        /* synthetic */ ChildNodeInsertedEvent(C20041 x0) {
            this();
        }
    }

    private static class ChildNodeRemovedEvent {
        @JsonProperty(required = true)
        public int nodeId;
        @JsonProperty(required = true)
        public int parentNodeId;

        private ChildNodeRemovedEvent() {
        }

        /* synthetic */ ChildNodeRemovedEvent(C20041 x0) {
            this();
        }
    }

    private static class DiscardSearchResultsRequest {
        @JsonProperty(required = true)
        public String searchId;

        private DiscardSearchResultsRequest() {
        }
    }

    private final class DocumentUpdateListener implements UpdateListener {
        private DocumentUpdateListener() {
        }

        /* synthetic */ DocumentUpdateListener(DOM x0, C20041 x1) {
            this();
        }

        public void onAttributeModified(Object element, String name, String value) {
            AttributeModifiedEvent message = new AttributeModifiedEvent();
            message.nodeId = DOM.this.mDocument.getNodeIdForElement(element).intValue();
            message.name = name;
            message.value = value;
            DOM.this.mPeerManager.sendNotificationToPeers("DOM.onAttributeModified", message);
        }

        public void onAttributeRemoved(Object element, String name) {
            AttributeRemovedEvent message = new AttributeRemovedEvent();
            message.nodeId = DOM.this.mDocument.getNodeIdForElement(element).intValue();
            message.name = name;
            DOM.this.mPeerManager.sendNotificationToPeers("DOM.attributeRemoved", message);
        }

        public void onInspectRequested(Object element) {
            Integer nodeId = DOM.this.mDocument.getNodeIdForElement(element);
            if (nodeId == null) {
                LogUtil.m7444d("DocumentProvider.Listener.onInspectRequested() called for a non-mapped node: element=%s", element);
                return;
            }
            InspectNodeRequestedEvent message = new InspectNodeRequestedEvent();
            message.nodeId = nodeId.intValue();
            DOM.this.mPeerManager.sendNotificationToPeers("DOM.inspectNodeRequested", message);
        }

        public void onChildNodeRemoved(int parentNodeId, int nodeId) {
            ChildNodeRemovedEvent removedEvent = DOM.this.acquireChildNodeRemovedEvent();
            removedEvent.parentNodeId = parentNodeId;
            removedEvent.nodeId = nodeId;
            DOM.this.mPeerManager.sendNotificationToPeers("DOM.childNodeRemoved", removedEvent);
            DOM.this.releaseChildNodeRemovedEvent(removedEvent);
        }

        public void onChildNodeInserted(DocumentView view, Object element, int parentNodeId, int previousNodeId, Accumulator<Object> insertedElements) {
            ChildNodeInsertedEvent insertedEvent = DOM.this.acquireChildNodeInsertedEvent();
            insertedEvent.parentNodeId = parentNodeId;
            insertedEvent.previousNodeId = previousNodeId;
            insertedEvent.node = DOM.this.createNodeForElement(element, view);
            insertedElements.store(element);
            DOM.this.mPeerManager.sendNotificationToPeers("DOM.childNodeInserted", insertedEvent);
            DOM.this.releaseChildNodeInsertedEvent(insertedEvent);
        }
    }

    private static class GetDocumentResponse implements JsonRpcResult {
        @JsonProperty(required = true)
        public Node root;

        private GetDocumentResponse() {
        }

        /* synthetic */ GetDocumentResponse(C20041 x0) {
            this();
        }
    }

    private static class GetSearchResultsRequest {
        @JsonProperty(required = true)
        public int fromIndex;
        @JsonProperty(required = true)
        public String searchId;
        @JsonProperty(required = true)
        public int toIndex;

        private GetSearchResultsRequest() {
        }
    }

    private static class GetSearchResultsResponse implements JsonRpcResult {
        @JsonProperty(required = true)
        public List<Integer> nodeIds;

        private GetSearchResultsResponse() {
        }

        /* synthetic */ GetSearchResultsResponse(C20041 x0) {
            this();
        }
    }

    private static class HighlightConfig {
        @JsonProperty
        public RGBAColor contentColor;

        private HighlightConfig() {
        }
    }

    private static class HighlightNodeRequest {
        @JsonProperty(required = true)
        public HighlightConfig highlightConfig;
        @JsonProperty
        public Integer nodeId;
        @JsonProperty
        public String objectId;

        private HighlightNodeRequest() {
        }
    }

    private static class InspectNodeRequestedEvent {
        @JsonProperty
        public int nodeId;

        private InspectNodeRequestedEvent() {
        }

        /* synthetic */ InspectNodeRequestedEvent(C20041 x0) {
            this();
        }
    }

    private static class Node implements JsonRpcResult {
        @JsonProperty
        public List<String> attributes;
        @JsonProperty
        public Integer childNodeCount;
        @JsonProperty
        public List<Node> children;
        @JsonProperty(required = true)
        public String localName;
        @JsonProperty(required = true)
        public int nodeId;
        @JsonProperty(required = true)
        public String nodeName;
        @JsonProperty(required = true)
        public NodeType nodeType;
        @JsonProperty(required = true)
        public String nodeValue;

        private Node() {
        }

        /* synthetic */ Node(C20041 x0) {
            this();
        }
    }

    private final class PeerManagerListener extends PeersRegisteredListener {
        private PeerManagerListener() {
        }

        /* synthetic */ PeerManagerListener(DOM x0, C20041 x1) {
            this();
        }

        /* Access modifiers changed, original: protected|declared_synchronized */
        public synchronized void onFirstPeerRegistered() {
            DOM.this.mDocument.addRef();
            DOM.this.mDocument.addUpdateListener(DOM.this.mListener);
        }

        /* Access modifiers changed, original: protected|declared_synchronized */
        public synchronized void onLastPeerUnregistered() {
            DOM.this.mSearchResults.clear();
            DOM.this.mDocument.removeUpdateListener(DOM.this.mListener);
            DOM.this.mDocument.release();
        }
    }

    private static class PerformSearchRequest {
        @JsonProperty
        public Boolean includeUserAgentShadowDOM;
        @JsonProperty(required = true)
        public String query;

        private PerformSearchRequest() {
        }
    }

    private static class PerformSearchResponse implements JsonRpcResult {
        @JsonProperty(required = true)
        public int resultCount;
        @JsonProperty(required = true)
        public String searchId;

        private PerformSearchResponse() {
        }

        /* synthetic */ PerformSearchResponse(C20041 x0) {
            this();
        }
    }

    private static class RGBAColor {
        @JsonProperty
        /* renamed from: a */
        public Double f6026a;
        @JsonProperty(required = true)
        /* renamed from: b */
        public int f6027b;
        @JsonProperty(required = true)
        /* renamed from: g */
        public int f6028g;
        @JsonProperty(required = true)
        /* renamed from: r */
        public int f6029r;

        private RGBAColor() {
        }

        public int getColor() {
            byte alpha;
            if (this.f6026a == null) {
                alpha = (byte) -1;
            } else {
                long aLong = Math.round(this.f6026a.doubleValue() * 255.0d);
                alpha = aLong < 0 ? (byte) 0 : aLong >= 255 ? (byte) -1 : (byte) ((int) aLong);
            }
            return Color.argb(alpha, this.f6029r, this.f6028g, this.f6027b);
        }
    }

    private static class ResolveNodeRequest {
        @JsonProperty(required = true)
        public int nodeId;
        @JsonProperty
        public String objectGroup;

        private ResolveNodeRequest() {
        }
    }

    private static class ResolveNodeResponse implements JsonRpcResult {
        @JsonProperty(required = true)
        public RemoteObject object;

        private ResolveNodeResponse() {
        }

        /* synthetic */ ResolveNodeResponse(C20041 x0) {
            this();
        }
    }

    private static class SetAttributesAsTextRequest {
        @JsonProperty(required = true)
        public int nodeId;
        @JsonProperty(required = true)
        public String text;

        private SetAttributesAsTextRequest() {
        }
    }

    private static class SetInspectModeEnabledRequest {
        @JsonProperty(required = true)
        public boolean enabled;
        @JsonProperty
        public HighlightConfig highlightConfig;
        @JsonProperty
        public Boolean inspectShadowDOM;

        private SetInspectModeEnabledRequest() {
        }
    }

    public DOM(Document document) {
        this.mDocument = (Document) Util.throwIfNull(document);
        this.mSearchResults = Collections.synchronizedMap(new HashMap());
        this.mResultCounter = new AtomicInteger(0);
        this.mPeerManager = new ChromePeerManager();
        this.mPeerManager.setListener(new PeerManagerListener(this, null));
        this.mListener = new DocumentUpdateListener(this, null);
    }

    @ChromeDevtoolsMethod
    public void enable(JsonRpcPeer peer, JSONObject params) {
        this.mPeerManager.addPeer(peer);
    }

    @ChromeDevtoolsMethod
    public void disable(JsonRpcPeer peer, JSONObject params) {
        this.mPeerManager.removePeer(peer);
    }

    @ChromeDevtoolsMethod
    public JsonRpcResult getDocument(JsonRpcPeer peer, JSONObject params) {
        GetDocumentResponse result = new GetDocumentResponse();
        result.root = (Node) this.mDocument.postAndWait((UncheckedCallable) new C20041());
        return result;
    }

    @ChromeDevtoolsMethod
    public void highlightNode(JsonRpcPeer peer, JSONObject params) {
        final HighlightNodeRequest request = (HighlightNodeRequest) this.mObjectMapper.convertValue(params, HighlightNodeRequest.class);
        if (request.nodeId == null) {
            LogUtil.m7459w("DOM.highlightNode was not given a nodeId; JS objectId is not supported");
            return;
        }
        final RGBAColor contentColor = request.highlightConfig.contentColor;
        if (contentColor == null) {
            LogUtil.m7459w("DOM.highlightNode was not given a color to highlight with");
        } else {
            this.mDocument.postAndWait((Runnable) new Runnable() {
                public void run() {
                    Object element = DOM.this.mDocument.getElementForNodeId(request.nodeId.intValue());
                    if (element != null) {
                        DOM.this.mDocument.highlightElement(element, contentColor.getColor());
                    }
                }
            });
        }
    }

    @ChromeDevtoolsMethod
    public void hideHighlight(JsonRpcPeer peer, JSONObject params) {
        this.mDocument.postAndWait((Runnable) new C20063());
    }

    @ChromeDevtoolsMethod
    public ResolveNodeResponse resolveNode(JsonRpcPeer peer, JSONObject params) throws JsonRpcException {
        final ResolveNodeRequest request = (ResolveNodeRequest) this.mObjectMapper.convertValue(params, ResolveNodeRequest.class);
        Object element = this.mDocument.postAndWait((UncheckedCallable) new UncheckedCallable<Object>() {
            public Object call() {
                return DOM.this.mDocument.getElementForNodeId(request.nodeId);
            }
        });
        if (element == null) {
            throw new JsonRpcException(new JsonRpcError(ErrorCode.INVALID_PARAMS, "No known nodeId=" + request.nodeId, null));
        }
        int mappedObjectId = Runtime.mapObject(peer, element);
        RemoteObject remoteObject = new RemoteObject();
        remoteObject.type = ObjectType.OBJECT;
        remoteObject.subtype = ObjectSubType.NODE;
        remoteObject.className = element.getClass().getName();
        remoteObject.value = null;
        remoteObject.description = null;
        remoteObject.objectId = String.valueOf(mappedObjectId);
        ResolveNodeResponse response = new ResolveNodeResponse();
        response.object = remoteObject;
        return response;
    }

    @ChromeDevtoolsMethod
    public void setAttributesAsText(JsonRpcPeer peer, JSONObject params) {
        final SetAttributesAsTextRequest request = (SetAttributesAsTextRequest) this.mObjectMapper.convertValue(params, SetAttributesAsTextRequest.class);
        this.mDocument.postAndWait((Runnable) new Runnable() {
            public void run() {
                Object element = DOM.this.mDocument.getElementForNodeId(request.nodeId);
                if (element != null) {
                    DOM.this.mDocument.setAttributesAsText(element, request.text);
                }
            }
        });
    }

    @ChromeDevtoolsMethod
    public void setInspectModeEnabled(JsonRpcPeer peer, JSONObject params) {
        final SetInspectModeEnabledRequest request = (SetInspectModeEnabledRequest) this.mObjectMapper.convertValue(params, SetInspectModeEnabledRequest.class);
        this.mDocument.postAndWait((Runnable) new Runnable() {
            public void run() {
                DOM.this.mDocument.setInspectModeEnabled(request.enabled);
            }
        });
    }

    @ChromeDevtoolsMethod
    public PerformSearchResponse performSearch(JsonRpcPeer peer, JSONObject params) {
        final PerformSearchRequest request = (PerformSearchRequest) this.mObjectMapper.convertValue(params, PerformSearchRequest.class);
        final ArrayListAccumulator<Integer> resultNodeIds = new ArrayListAccumulator();
        this.mDocument.postAndWait((Runnable) new Runnable() {
            public void run() {
                DOM.this.mDocument.findMatchingElements(request.query, resultNodeIds);
            }
        });
        String searchId = String.valueOf(this.mResultCounter.getAndIncrement());
        this.mSearchResults.put(searchId, resultNodeIds);
        PerformSearchResponse response = new PerformSearchResponse();
        response.searchId = searchId;
        response.resultCount = resultNodeIds.size();
        return response;
    }

    @ChromeDevtoolsMethod
    public GetSearchResultsResponse getSearchResults(JsonRpcPeer peer, JSONObject params) {
        GetSearchResultsRequest request = (GetSearchResultsRequest) this.mObjectMapper.convertValue(params, GetSearchResultsRequest.class);
        if (request.searchId == null) {
            LogUtil.m7459w("searchId may not be null");
            return null;
        }
        List<Integer> results = (List) this.mSearchResults.get(request.searchId);
        if (results == null) {
            LogUtil.m7459w("\"" + request.searchId + "\" is not a valid reference to a search result");
            return null;
        }
        List<Integer> resultsRange = results.subList(request.fromIndex, request.toIndex);
        GetSearchResultsResponse response = new GetSearchResultsResponse();
        response.nodeIds = resultsRange;
        return response;
    }

    @ChromeDevtoolsMethod
    public void discardSearchResults(JsonRpcPeer peer, JSONObject params) {
        DiscardSearchResultsRequest request = (DiscardSearchResultsRequest) this.mObjectMapper.convertValue(params, DiscardSearchResultsRequest.class);
        if (request.searchId != null) {
            this.mSearchResults.remove(request.searchId);
        }
    }

    private Node createNodeForElement(Object element, DocumentView view) {
        List<Node> childrenNodes;
        NodeDescriptor descriptor = this.mDocument.getNodeDescriptor(element);
        Node node = new Node();
        node.nodeId = this.mDocument.getNodeIdForElement(element).intValue();
        node.nodeType = descriptor.getNodeType(element);
        node.nodeName = descriptor.getNodeName(element);
        node.localName = descriptor.getLocalName(element);
        node.nodeValue = descriptor.getNodeValue(element);
        AttributeListAccumulator accumulator = new AttributeListAccumulator();
        descriptor.getAttributes(element, accumulator);
        node.attributes = accumulator;
        ElementInfo elementInfo = view.getElementInfo(element);
        if (elementInfo.children.size() == 0) {
            childrenNodes = Collections.emptyList();
        } else {
            childrenNodes = new ArrayList(elementInfo.children.size());
        }
        int N = elementInfo.children.size();
        for (int i = 0; i < N; i++) {
            childrenNodes.add(createNodeForElement(elementInfo.children.get(i), view));
        }
        node.children = childrenNodes;
        node.childNodeCount = Integer.valueOf(childrenNodes.size());
        return node;
    }

    private ChildNodeInsertedEvent acquireChildNodeInsertedEvent() {
        ChildNodeInsertedEvent childNodeInsertedEvent = this.mCachedChildNodeInsertedEvent;
        if (childNodeInsertedEvent == null) {
            childNodeInsertedEvent = new ChildNodeInsertedEvent();
        }
        this.mCachedChildNodeInsertedEvent = null;
        return childNodeInsertedEvent;
    }

    private void releaseChildNodeInsertedEvent(ChildNodeInsertedEvent childNodeInsertedEvent) {
        childNodeInsertedEvent.parentNodeId = -1;
        childNodeInsertedEvent.previousNodeId = -1;
        childNodeInsertedEvent.node = null;
        if (this.mCachedChildNodeInsertedEvent == null) {
            this.mCachedChildNodeInsertedEvent = childNodeInsertedEvent;
        }
    }

    private ChildNodeRemovedEvent acquireChildNodeRemovedEvent() {
        ChildNodeRemovedEvent childNodeRemovedEvent = this.mCachedChildNodeRemovedEvent;
        if (childNodeRemovedEvent == null) {
            childNodeRemovedEvent = new ChildNodeRemovedEvent();
        }
        this.mCachedChildNodeRemovedEvent = null;
        return childNodeRemovedEvent;
    }

    private void releaseChildNodeRemovedEvent(ChildNodeRemovedEvent childNodeRemovedEvent) {
        childNodeRemovedEvent.parentNodeId = -1;
        childNodeRemovedEvent.nodeId = -1;
        if (this.mCachedChildNodeRemovedEvent == null) {
            this.mCachedChildNodeRemovedEvent = childNodeRemovedEvent;
        }
    }
}
