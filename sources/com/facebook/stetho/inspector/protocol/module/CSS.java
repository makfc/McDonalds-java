package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.common.ListUtil;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.elements.Document;
import com.facebook.stetho.inspector.elements.Origin;
import com.facebook.stetho.inspector.elements.StyleAccumulator;
import com.facebook.stetho.inspector.helper.ChromePeerManager;
import com.facebook.stetho.inspector.helper.PeersRegisteredListener;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsMethod;
import com.facebook.stetho.json.ObjectMapper;
import com.facebook.stetho.json.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

public class CSS implements ChromeDevtoolsDomain {
    private final Document mDocument;
    private final ObjectMapper mObjectMapper = new ObjectMapper();
    private final ChromePeerManager mPeerManager = new ChromePeerManager();

    private static class CSSComputedStyleProperty {
        @JsonProperty(required = true)
        public String name;
        @JsonProperty(required = true)
        public String value;

        private CSSComputedStyleProperty() {
        }

        /* synthetic */ CSSComputedStyleProperty(C20011 x0) {
            this();
        }
    }

    private static class CSSProperty {
        @JsonProperty
        public Boolean disabled;
        @JsonProperty
        public Boolean implicit;
        @JsonProperty
        public Boolean important;
        @JsonProperty(required = true)
        public String name;
        @JsonProperty
        public Boolean parsedOk;
        @JsonProperty
        public SourceRange range;
        @JsonProperty
        public String text;
        @JsonProperty(required = true)
        public String value;

        private CSSProperty() {
        }

        /* synthetic */ CSSProperty(C20011 x0) {
            this();
        }
    }

    private static class CSSRule {
        @JsonProperty
        public Origin origin;
        @JsonProperty(required = true)
        public SelectorList selectorList;
        @JsonProperty
        public CSSStyle style;
        @JsonProperty
        public String styleSheetId;

        private CSSRule() {
        }

        /* synthetic */ CSSRule(C20011 x0) {
            this();
        }
    }

    private static class CSSStyle {
        @JsonProperty(required = true)
        public List<CSSProperty> cssProperties;
        @JsonProperty
        public String cssText;
        @JsonProperty
        public SourceRange range;
        @JsonProperty
        public List<ShorthandEntry> shorthandEntries;
        @JsonProperty
        public String styleSheetId;

        private CSSStyle() {
        }

        /* synthetic */ CSSStyle(C20011 x0) {
            this();
        }
    }

    private static class GetComputedStyleForNodeRequest {
        @JsonProperty(required = true)
        public int nodeId;

        private GetComputedStyleForNodeRequest() {
        }
    }

    private static class GetComputedStyleForNodeResult implements JsonRpcResult {
        @JsonProperty(required = true)
        public List<CSSComputedStyleProperty> computedStyle;

        private GetComputedStyleForNodeResult() {
        }

        /* synthetic */ GetComputedStyleForNodeResult(C20011 x0) {
            this();
        }
    }

    private static class GetMatchedStylesForNodeRequest implements JsonRpcResult {
        @JsonProperty
        public Boolean excludeInherited;
        @JsonProperty
        public Boolean excludePseudo;
        @JsonProperty(required = true)
        public int nodeId;

        private GetMatchedStylesForNodeRequest() {
        }
    }

    private static class GetMatchedStylesForNodeResult implements JsonRpcResult {
        @JsonProperty
        public List<InheritedStyleEntry> inherited;
        @JsonProperty
        public List<RuleMatch> matchedCSSRules;
        @JsonProperty
        public List<PseudoIdMatches> pseudoElements;

        private GetMatchedStylesForNodeResult() {
        }

        /* synthetic */ GetMatchedStylesForNodeResult(C20011 x0) {
            this();
        }
    }

    private static class InheritedStyleEntry {
        @JsonProperty(required = true)
        public CSSStyle inlineStyle;
        @JsonProperty(required = true)
        public List<RuleMatch> matchedCSSRules;

        private InheritedStyleEntry() {
        }
    }

    private final class PeerManagerListener extends PeersRegisteredListener {
        private PeerManagerListener() {
        }

        /* synthetic */ PeerManagerListener(CSS x0, C20011 x1) {
            this();
        }

        /* Access modifiers changed, original: protected|declared_synchronized */
        public synchronized void onFirstPeerRegistered() {
            CSS.this.mDocument.addRef();
        }

        /* Access modifiers changed, original: protected|declared_synchronized */
        public synchronized void onLastPeerUnregistered() {
            CSS.this.mDocument.release();
        }
    }

    private static class PseudoIdMatches {
        @JsonProperty(required = true)
        public List<RuleMatch> matches = new ArrayList();
        @JsonProperty(required = true)
        public int pseudoId;
    }

    private static class RuleMatch {
        @JsonProperty
        public List<Integer> matchingSelectors;
        @JsonProperty
        public CSSRule rule;

        private RuleMatch() {
        }

        /* synthetic */ RuleMatch(C20011 x0) {
            this();
        }
    }

    private static class Selector {
        @JsonProperty
        public SourceRange range;
        @JsonProperty(required = true)
        public String value;

        private Selector() {
        }

        /* synthetic */ Selector(C20011 x0) {
            this();
        }
    }

    private static class SelectorList {
        @JsonProperty
        public List<Selector> selectors;
        @JsonProperty
        public String text;

        private SelectorList() {
        }

        /* synthetic */ SelectorList(C20011 x0) {
            this();
        }
    }

    private static class ShorthandEntry {
        @JsonProperty
        public Boolean imporant;
        @JsonProperty(required = true)
        public String name;
        @JsonProperty(required = true)
        public String value;

        private ShorthandEntry() {
        }
    }

    private static class SourceRange {
        @JsonProperty(required = true)
        public int endColumn;
        @JsonProperty(required = true)
        public int endLine;
        @JsonProperty(required = true)
        public int startColumn;
        @JsonProperty(required = true)
        public int startLine;

        private SourceRange() {
        }
    }

    public CSS(Document document) {
        this.mDocument = (Document) Util.throwIfNull(document);
        this.mPeerManager.setListener(new PeerManagerListener(this, null));
    }

    @ChromeDevtoolsMethod
    public void enable(JsonRpcPeer peer, JSONObject params) {
    }

    @ChromeDevtoolsMethod
    public void disable(JsonRpcPeer peer, JSONObject params) {
    }

    @ChromeDevtoolsMethod
    public JsonRpcResult getComputedStyleForNode(JsonRpcPeer peer, JSONObject params) {
        final GetComputedStyleForNodeRequest request = (GetComputedStyleForNodeRequest) this.mObjectMapper.convertValue(params, GetComputedStyleForNodeRequest.class);
        final GetComputedStyleForNodeResult result = new GetComputedStyleForNodeResult();
        result.computedStyle = new ArrayList();
        this.mDocument.postAndWait((Runnable) new Runnable() {

            /* renamed from: com.facebook.stetho.inspector.protocol.module.CSS$1$1 */
            class C20001 implements StyleAccumulator {
                C20001() {
                }

                public void store(String name, String value, boolean isDefault) {
                    if (!isDefault) {
                        CSSComputedStyleProperty property = new CSSComputedStyleProperty();
                        property.name = name;
                        property.value = value;
                        result.computedStyle.add(property);
                    }
                }
            }

            public void run() {
                Object element = CSS.this.mDocument.getElementForNodeId(request.nodeId);
                if (element == null) {
                    LogUtil.m7447e("Tried to get the style of an element that does not exist, using nodeid=" + request.nodeId);
                } else {
                    CSS.this.mDocument.getElementStyles(element, new C20001());
                }
            }
        });
        return result;
    }

    @ChromeDevtoolsMethod
    public JsonRpcResult getMatchedStylesForNode(JsonRpcPeer peer, JSONObject params) {
        final GetMatchedStylesForNodeRequest request = (GetMatchedStylesForNodeRequest) this.mObjectMapper.convertValue(params, GetMatchedStylesForNodeRequest.class);
        GetMatchedStylesForNodeResult result = new GetMatchedStylesForNodeResult();
        final RuleMatch match = new RuleMatch();
        result.matchedCSSRules = ListUtil.newImmutableList(match);
        match.matchingSelectors = ListUtil.newImmutableList(Integer.valueOf(0));
        Selector selector = new Selector();
        selector.value = "<this_element>";
        CSSRule rule = new CSSRule();
        rule.origin = Origin.REGULAR;
        rule.selectorList = new SelectorList();
        rule.selectorList.selectors = ListUtil.newImmutableList(selector);
        rule.style = new CSSStyle();
        rule.style.cssProperties = new ArrayList();
        match.rule = rule;
        rule.style.shorthandEntries = Collections.emptyList();
        this.mDocument.postAndWait((Runnable) new Runnable() {

            /* renamed from: com.facebook.stetho.inspector.protocol.module.CSS$2$1 */
            class C20021 implements StyleAccumulator {
                C20021() {
                }

                public void store(String name, String value, boolean isDefault) {
                    if (!isDefault) {
                        CSSProperty property = new CSSProperty();
                        property.name = name;
                        property.value = value;
                        match.rule.style.cssProperties.add(property);
                    }
                }
            }

            public void run() {
                Object elementForNodeId = CSS.this.mDocument.getElementForNodeId(request.nodeId);
                if (elementForNodeId == null) {
                    LogUtil.m7459w("Failed to get style of an element that does not exist, nodeid=" + request.nodeId);
                } else {
                    CSS.this.mDocument.getElementStyles(elementForNodeId, new C20021());
                }
            }
        });
        result.inherited = Collections.emptyList();
        result.pseudoElements = Collections.emptyList();
        return result;
    }
}
