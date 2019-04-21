package com.facebook.stetho.inspector.protocol.module;

import android.content.Context;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.console.RuntimeRepl;
import com.facebook.stetho.inspector.console.RuntimeReplFactory;
import com.facebook.stetho.inspector.helper.ObjectIdMapper;
import com.facebook.stetho.inspector.jsonrpc.DisconnectReceiver;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcException;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError.ErrorCode;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsMethod;
import com.facebook.stetho.inspector.runtime.RhinoDetectingRuntimeReplFactory;
import com.facebook.stetho.json.ObjectMapper;
import com.facebook.stetho.json.annotation.JsonProperty;
import com.facebook.stetho.json.annotation.JsonValue;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class Runtime implements ChromeDevtoolsDomain {
    private static final Map<JsonRpcPeer, Session> sSessions = Collections.synchronizedMap(new HashMap());
    private final ObjectMapper mObjectMapper;
    private final RuntimeReplFactory mReplFactory;

    /* renamed from: com.facebook.stetho.inspector.protocol.module.Runtime$1 */
    class C20181 implements RuntimeReplFactory {

        /* renamed from: com.facebook.stetho.inspector.protocol.module.Runtime$1$1 */
        class C20171 implements RuntimeRepl {
            C20171() {
            }

            public Object evaluate(String expression) throws Throwable {
                return "Not supported with legacy Runtime module";
            }
        }

        C20181() {
        }

        public RuntimeRepl newInstance() {
            return new C20171();
        }
    }

    private static class CallArgument {
        @JsonProperty(required = false)
        public String objectId;
        @JsonProperty(required = false)
        public ObjectType type;
        @JsonProperty(required = false)
        public Object value;

        private CallArgument() {
        }
    }

    private static class CallFunctionOnRequest {
        @JsonProperty
        public List<CallArgument> arguments;
        @JsonProperty(required = false)
        public Boolean doNotPauseOnExceptionsAndMuteConsole;
        @JsonProperty
        public String functionDeclaration;
        @JsonProperty(required = false)
        public Boolean generatePreview;
        @JsonProperty
        public String objectId;
        @JsonProperty(required = false)
        public Boolean returnByValue;

        private CallFunctionOnRequest() {
        }
    }

    private static class CallFunctionOnResponse implements JsonRpcResult {
        @JsonProperty
        public RemoteObject result;
        @JsonProperty(required = false)
        public Boolean wasThrown;

        private CallFunctionOnResponse() {
        }

        /* synthetic */ CallFunctionOnResponse(C20181 x0) {
            this();
        }
    }

    private static class EvaluateRequest implements JsonRpcResult {
        @JsonProperty(required = true)
        public String expression;
        @JsonProperty(required = true)
        public String objectGroup;

        private EvaluateRequest() {
        }
    }

    private static class EvaluateResponse implements JsonRpcResult {
        @JsonProperty
        public ExceptionDetails exceptionDetails;
        @JsonProperty(required = true)
        public RemoteObject result;
        @JsonProperty(required = true)
        public boolean wasThrown;

        private EvaluateResponse() {
        }

        /* synthetic */ EvaluateResponse(C20181 x0) {
            this();
        }
    }

    private static class ExceptionDetails {
        @JsonProperty(required = true)
        public String text;

        private ExceptionDetails() {
        }

        /* synthetic */ ExceptionDetails(C20181 x0) {
            this();
        }
    }

    private static class GetPropertiesRequest implements JsonRpcResult {
        @JsonProperty(required = true)
        public String objectId;
        @JsonProperty(required = true)
        public boolean ownProperties;

        private GetPropertiesRequest() {
        }
    }

    private static class GetPropertiesResponse implements JsonRpcResult {
        @JsonProperty(required = true)
        public List<PropertyDescriptor> result;

        private GetPropertiesResponse() {
        }

        /* synthetic */ GetPropertiesResponse(C20181 x0) {
            this();
        }
    }

    private static class ObjectProtoContainer {
        public final Object object;

        public ObjectProtoContainer(Object object) {
            this.object = object;
        }
    }

    public enum ObjectSubType {
        ARRAY("array"),
        NULL(SafeJsonPrimitive.NULL_STRING),
        NODE("node"),
        REGEXP("regexp"),
        DATE("date"),
        MAP("map"),
        SET("set"),
        ITERATOR("iterator"),
        GENERATOR("generator"),
        ERROR("error");
        
        private final String mProtocolValue;

        private ObjectSubType(String protocolValue) {
            this.mProtocolValue = protocolValue;
        }

        @JsonValue
        public String getProtocolValue() {
            return this.mProtocolValue;
        }
    }

    public enum ObjectType {
        OBJECT("object"),
        FUNCTION("function"),
        UNDEFINED("undefined"),
        STRING("string"),
        NUMBER("number"),
        BOOLEAN("boolean"),
        SYMBOL("symbol");
        
        private final String mProtocolValue;

        private ObjectType(String protocolValue) {
            this.mProtocolValue = protocolValue;
        }

        @JsonValue
        public String getProtocolValue() {
            return this.mProtocolValue;
        }
    }

    private static class PropertyDescriptor {
        @JsonProperty(required = true)
        public final boolean configurable;
        @JsonProperty(required = true)
        public final boolean enumerable;
        @JsonProperty(required = true)
        public final boolean isOwn;
        @JsonProperty(required = true)
        public String name;
        @JsonProperty(required = true)
        public RemoteObject value;
        @JsonProperty(required = true)
        public final boolean writable;

        private PropertyDescriptor() {
            this.isOwn = true;
            this.configurable = false;
            this.enumerable = true;
            this.writable = false;
        }

        /* synthetic */ PropertyDescriptor(C20181 x0) {
            this();
        }
    }

    public static class RemoteObject {
        @JsonProperty
        public String className;
        @JsonProperty
        public String description;
        @JsonProperty
        public String objectId;
        @JsonProperty
        public ObjectSubType subtype;
        @JsonProperty(required = true)
        public ObjectType type;
        @JsonProperty
        public Object value;
    }

    private static class Session {
        private final ObjectMapper mObjectMapper;
        private final ObjectIdMapper mObjects;
        @Nullable
        private RuntimeRepl mRepl;

        private Session() {
            this.mObjects = new ObjectIdMapper();
            this.mObjectMapper = new ObjectMapper();
        }

        /* synthetic */ Session(C20181 x0) {
            this();
        }

        public ObjectIdMapper getObjects() {
            return this.mObjects;
        }

        public Object getObjectOrThrow(String objectId) throws JsonRpcException {
            Object object = getObjects().getObjectForId(Integer.parseInt(objectId));
            if (object != null) {
                return object;
            }
            throw new JsonRpcException(new JsonRpcError(ErrorCode.INVALID_REQUEST, "No object found for " + objectId, null));
        }

        public RemoteObject objectForRemote(Object value) {
            RemoteObject result = new RemoteObject();
            if (value == null) {
                result.type = ObjectType.OBJECT;
                result.subtype = ObjectSubType.NULL;
                result.value = JSONObject.NULL;
            } else if (value instanceof Boolean) {
                result.type = ObjectType.BOOLEAN;
                result.value = value;
            } else if (value instanceof Number) {
                result.type = ObjectType.NUMBER;
                result.value = value;
            } else if (value instanceof Character) {
                result.type = ObjectType.NUMBER;
                result.value = Integer.valueOf(((Character) value).charValue());
            } else if (value instanceof String) {
                result.type = ObjectType.STRING;
                result.value = String.valueOf(value);
            } else {
                result.type = ObjectType.OBJECT;
                result.className = "What??";
                result.objectId = String.valueOf(this.mObjects.putObject(value));
                if (value.getClass().isArray()) {
                    result.description = "array";
                } else if (value instanceof List) {
                    result.description = "List";
                } else if (value instanceof Set) {
                    result.description = "Set";
                } else if (value instanceof Map) {
                    result.description = "Map";
                } else {
                    result.description = Runtime.getPropertyClassName(value);
                }
            }
            return result;
        }

        public EvaluateResponse evaluate(RuntimeReplFactory replFactory, JSONObject params) {
            EvaluateRequest request = (EvaluateRequest) this.mObjectMapper.convertValue(params, EvaluateRequest.class);
            try {
                if (request.objectGroup.equals("console")) {
                    return buildNormalResponse(getRepl(replFactory).evaluate(request.expression));
                }
                return buildExceptionResponse("Not supported by FAB");
            } catch (Throwable t) {
                return buildExceptionResponse(t);
            }
        }

        @Nonnull
        private synchronized RuntimeRepl getRepl(RuntimeReplFactory replFactory) {
            if (this.mRepl == null) {
                this.mRepl = replFactory.newInstance();
            }
            return this.mRepl;
        }

        private EvaluateResponse buildNormalResponse(Object retval) {
            EvaluateResponse response = new EvaluateResponse();
            response.wasThrown = false;
            response.result = objectForRemote(retval);
            return response;
        }

        private EvaluateResponse buildExceptionResponse(Object retval) {
            EvaluateResponse response = new EvaluateResponse();
            response.wasThrown = true;
            response.result = objectForRemote(retval);
            response.exceptionDetails = new ExceptionDetails();
            response.exceptionDetails.text = retval.toString();
            return response;
        }

        public GetPropertiesResponse getProperties(JSONObject params) throws JsonRpcException {
            GetPropertiesRequest request = (GetPropertiesRequest) this.mObjectMapper.convertValue(params, GetPropertiesRequest.class);
            if (request.ownProperties) {
                Object object = getObjectOrThrow(request.objectId);
                if (object.getClass().isArray()) {
                    object = arrayToList(object);
                }
                if (object instanceof ObjectProtoContainer) {
                    return getPropertiesForProtoContainer((ObjectProtoContainer) object);
                }
                if (object instanceof List) {
                    return getPropertiesForIterable((List) object, true);
                }
                if (object instanceof Set) {
                    return getPropertiesForIterable((Set) object, false);
                }
                if (object instanceof Map) {
                    return getPropertiesForMap(object);
                }
                return getPropertiesForObject(object);
            }
            GetPropertiesResponse response = new GetPropertiesResponse();
            response.result = new ArrayList();
            return response;
        }

        private List<?> arrayToList(Object object) {
            Class<?> type = object.getClass();
            if (!type.isArray()) {
                throw new IllegalArgumentException("Argument must be an array.  Was " + type);
            } else if (!type.getComponentType().isPrimitive()) {
                return Arrays.asList((Object[]) object);
            } else {
                int length = Array.getLength(object);
                List<?> ret = new ArrayList(length);
                for (int i = 0; i < length; i++) {
                    ret.add(Array.get(object, i));
                }
                return ret;
            }
        }

        private GetPropertiesResponse getPropertiesForProtoContainer(ObjectProtoContainer proto) {
            Object target = proto.object;
            RemoteObject protoRemote = new RemoteObject();
            protoRemote.type = ObjectType.OBJECT;
            protoRemote.subtype = ObjectSubType.NODE;
            protoRemote.className = target.getClass().getName();
            protoRemote.description = Runtime.getPropertyClassName(target);
            protoRemote.objectId = String.valueOf(this.mObjects.putObject(target));
            PropertyDescriptor descriptor = new PropertyDescriptor();
            descriptor.name = "1";
            descriptor.value = protoRemote;
            GetPropertiesResponse response = new GetPropertiesResponse();
            response.result = new ArrayList(1);
            response.result.add(descriptor);
            return response;
        }

        private GetPropertiesResponse getPropertiesForIterable(Iterable<?> object, boolean enumerate) {
            GetPropertiesResponse response = new GetPropertiesResponse();
            List<PropertyDescriptor> properties = new ArrayList();
            int index = 0;
            for (Object value : object) {
                String valueOf;
                PropertyDescriptor property = new PropertyDescriptor();
                if (enumerate) {
                    int index2 = index + 1;
                    valueOf = String.valueOf(index);
                    index = index2;
                } else {
                    valueOf = null;
                }
                property.name = valueOf;
                property.value = objectForRemote(value);
                properties.add(property);
            }
            response.result = properties;
            return response;
        }

        private GetPropertiesResponse getPropertiesForMap(Object object) {
            GetPropertiesResponse response = new GetPropertiesResponse();
            List<PropertyDescriptor> properties = new ArrayList();
            for (Entry<?, ?> entry : ((Map) object).entrySet()) {
                PropertyDescriptor property = new PropertyDescriptor();
                property.name = String.valueOf(entry.getKey());
                property.value = objectForRemote(entry.getValue());
                properties.add(property);
            }
            response.result = properties;
            return response;
        }

        private GetPropertiesResponse getPropertiesForObject(Object object) {
            GetPropertiesResponse response = new GetPropertiesResponse();
            List<PropertyDescriptor> properties = new ArrayList();
            for (Class<?> declaringClass = object.getClass(); declaringClass != null; declaringClass = declaringClass.getSuperclass()) {
                String prefix;
                List<Field> fields = new ArrayList(Arrays.asList(declaringClass.getDeclaredFields()));
                Collections.reverse(fields);
                if (declaringClass == object.getClass()) {
                    prefix = "";
                } else {
                    prefix = declaringClass.getSimpleName() + ".";
                }
                for (Field field : fields) {
                    if (!Modifier.isStatic(field.getModifiers())) {
                        field.setAccessible(true);
                        try {
                            Object fieldValue = field.get(object);
                            PropertyDescriptor property = new PropertyDescriptor();
                            property.name = prefix + field.getName();
                            property.value = objectForRemote(fieldValue);
                            properties.add(property);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
            Collections.reverse(properties);
            response.result = properties;
            return response;
        }
    }

    @Deprecated
    public Runtime() {
        this(new C20181());
    }

    public Runtime(Context context) {
        this(new RhinoDetectingRuntimeReplFactory(context));
    }

    public Runtime(RuntimeReplFactory replFactory) {
        this.mObjectMapper = new ObjectMapper();
        this.mReplFactory = replFactory;
    }

    public static int mapObject(JsonRpcPeer peer, Object object) {
        return getSession(peer).getObjects().putObject(object);
    }

    @Nonnull
    private static synchronized Session getSession(final JsonRpcPeer peer) {
        Session session;
        synchronized (Runtime.class) {
            session = (Session) sSessions.get(peer);
            if (session == null) {
                session = new Session();
                sSessions.put(peer, session);
                peer.registerDisconnectReceiver(new DisconnectReceiver() {
                    public void onDisconnect() {
                        Runtime.sSessions.remove(peer);
                    }
                });
            }
        }
        return session;
    }

    @ChromeDevtoolsMethod
    public void releaseObject(JsonRpcPeer peer, JSONObject params) throws JSONException {
        getSession(peer).getObjects().removeObjectById(Integer.parseInt(params.getString("objectId")));
    }

    @ChromeDevtoolsMethod
    public void releaseObjectGroup(JsonRpcPeer peer, JSONObject params) {
        LogUtil.m7459w("Ignoring request to releaseObjectGroup: " + params);
    }

    @ChromeDevtoolsMethod
    public CallFunctionOnResponse callFunctionOn(JsonRpcPeer peer, JSONObject params) throws JsonRpcException {
        CallFunctionOnRequest args = (CallFunctionOnRequest) this.mObjectMapper.convertValue(params, CallFunctionOnRequest.class);
        Session session = getSession(peer);
        Object object = session.getObjectOrThrow(args.objectId);
        if (args.functionDeclaration.startsWith("function protoList(")) {
            ObjectProtoContainer objectContainer = new ObjectProtoContainer(object);
            RemoteObject result = new RemoteObject();
            result.type = ObjectType.OBJECT;
            result.subtype = ObjectSubType.NODE;
            result.className = object.getClass().getName();
            result.description = getPropertyClassName(object);
            result.objectId = String.valueOf(session.getObjects().putObject(objectContainer));
            CallFunctionOnResponse response = new CallFunctionOnResponse();
            response.result = result;
            response.wasThrown = Boolean.valueOf(false);
            return response;
        }
        throw new JsonRpcException(new JsonRpcError(ErrorCode.INTERNAL_ERROR, "Expected protoList, got: " + args.functionDeclaration, null));
    }

    @ChromeDevtoolsMethod
    public JsonRpcResult evaluate(JsonRpcPeer peer, JSONObject params) {
        return getSession(peer).evaluate(this.mReplFactory, params);
    }

    @ChromeDevtoolsMethod
    public JsonRpcResult getProperties(JsonRpcPeer peer, JSONObject params) throws JsonRpcException {
        return getSession(peer).getProperties(params);
    }

    private static String getPropertyClassName(Object o) {
        String name = o.getClass().getSimpleName();
        if (name == null || name.length() == 0) {
            return o.getClass().getName();
        }
        return name;
    }
}
