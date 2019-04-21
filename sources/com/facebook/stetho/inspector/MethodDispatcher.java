package com.facebook.stetho.inspector;

import com.facebook.stetho.common.ExceptionUtil;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcException;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.inspector.jsonrpc.protocol.EmptyResult;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError.ErrorCode;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsMethod;
import com.facebook.stetho.json.ObjectMapper;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import org.json.JSONException;
import org.json.JSONObject;

@ThreadSafe
public class MethodDispatcher {
    private final Iterable<ChromeDevtoolsDomain> mDomainHandlers;
    @GuardedBy
    private Map<String, MethodDispatchHelper> mMethods;
    private final ObjectMapper mObjectMapper;

    private static class MethodDispatchHelper {
        private final ChromeDevtoolsDomain mInstance;
        private final Method mMethod;
        private final ObjectMapper mObjectMapper;

        public MethodDispatchHelper(ObjectMapper objectMapper, ChromeDevtoolsDomain instance, Method method) {
            this.mObjectMapper = objectMapper;
            this.mInstance = instance;
            this.mMethod = method;
        }

        public JSONObject invoke(JsonRpcPeer peer, @Nullable JSONObject params) throws InvocationTargetException, IllegalAccessException, JSONException, JsonRpcException {
            JsonRpcResult internalResult = this.mMethod.invoke(this.mInstance, new Object[]{peer, params});
            if (internalResult == null || (internalResult instanceof EmptyResult)) {
                return new JSONObject();
            }
            return (JSONObject) this.mObjectMapper.convertValue(internalResult, JSONObject.class);
        }
    }

    public MethodDispatcher(ObjectMapper objectMapper, Iterable<ChromeDevtoolsDomain> domainHandlers) {
        this.mObjectMapper = objectMapper;
        this.mDomainHandlers = domainHandlers;
    }

    private synchronized MethodDispatchHelper findMethodDispatcher(String methodName) {
        if (this.mMethods == null) {
            this.mMethods = buildDispatchTable(this.mObjectMapper, this.mDomainHandlers);
        }
        return (MethodDispatchHelper) this.mMethods.get(methodName);
    }

    public JSONObject dispatch(JsonRpcPeer peer, String methodName, @Nullable JSONObject params) throws JsonRpcException {
        MethodDispatchHelper dispatchHelper = findMethodDispatcher(methodName);
        if (dispatchHelper == null) {
            throw new JsonRpcException(new JsonRpcError(ErrorCode.METHOD_NOT_FOUND, "Not implemented: " + methodName, null));
        }
        try {
            return dispatchHelper.invoke(peer, params);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            ExceptionUtil.propagateIfInstanceOf(cause, JsonRpcException.class);
            throw ExceptionUtil.propagate(cause);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (JSONException e3) {
            throw new JsonRpcException(new JsonRpcError(ErrorCode.INTERNAL_ERROR, e3.toString(), null));
        }
    }

    private static Map<String, MethodDispatchHelper> buildDispatchTable(ObjectMapper objectMapper, Iterable<ChromeDevtoolsDomain> domainHandlers) {
        Util.throwIfNull(objectMapper);
        HashMap<String, MethodDispatchHelper> methods = new HashMap();
        for (ChromeDevtoolsDomain domainHandler : (Iterable) Util.throwIfNull(domainHandlers)) {
            Class<?> handlerClass = domainHandler.getClass();
            String domainName = handlerClass.getSimpleName();
            for (Method method : handlerClass.getDeclaredMethods()) {
                if (isDevtoolsMethod(method)) {
                    methods.put(domainName + "." + method.getName(), new MethodDispatchHelper(objectMapper, domainHandler, method));
                }
            }
        }
        return Collections.unmodifiableMap(methods);
    }

    private static boolean isDevtoolsMethod(Method method) throws IllegalArgumentException {
        if (!method.isAnnotationPresent(ChromeDevtoolsMethod.class)) {
            return false;
        }
        boolean z;
        Class<?>[] args = method.getParameterTypes();
        String methodName = method.getDeclaringClass().getSimpleName() + "." + method.getName();
        if (args.length == 2) {
            z = true;
        } else {
            z = false;
        }
        Util.throwIfNot(z, "%s: expected 2 args, got %s", methodName, Integer.valueOf(args.length));
        Util.throwIfNot(args[0].equals(JsonRpcPeer.class), "%s: expected 1st arg of JsonRpcPeer, got %s", methodName, args[0].getName());
        Util.throwIfNot(args[1].equals(JSONObject.class), "%s: expected 2nd arg of JSONObject, got %s", methodName, args[1].getName());
        Class<?> returnType = method.getReturnType();
        if (!returnType.equals(Void.TYPE)) {
            Util.throwIfNot(JsonRpcResult.class.isAssignableFrom(returnType), "%s: expected JsonRpcResult return type, got %s", methodName, returnType.getName());
        }
        return true;
    }
}
