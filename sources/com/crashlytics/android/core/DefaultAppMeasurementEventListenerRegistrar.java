package com.crashlytics.android.core;

import android.content.Context;
import android.os.Bundle;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import p041io.fabric.sdk.android.Fabric;

class DefaultAppMeasurementEventListenerRegistrar implements AppMeasurementEventListenerRegistrar {
    private static final List<Class<?>> ONEVENT_EXPECTED_ARGTYPES = Collections.unmodifiableList(Arrays.asList(new Class[]{String.class, String.class, Bundle.class, Long.class}));
    private final CrashlyticsCore crashlyticsCore;
    private Object eventListenerProxy;

    /* renamed from: com.crashlytics.android.core.DefaultAppMeasurementEventListenerRegistrar$1 */
    class C16721 implements InvocationHandler {
        C16721() {
        }

        public Object invoke(Object proxy, Method method, Object[] args) {
            String methodName = method.getName();
            if (args.length == 1 && methodName.equals("equals")) {
                return Boolean.valueOf(equalsImpl(proxy, args[0]));
            }
            if (args.length == 0 && methodName.equals("hashCode")) {
                return Integer.valueOf(super.hashCode());
            }
            if (args.length == 0 && methodName.equals("toString")) {
                return super.toString();
            }
            if (args.length == 4 && methodName.equals("onEvent") && DefaultAppMeasurementEventListenerRegistrar.validateOnEventArgTypes(args)) {
                String origin = args[0];
                String name = args[1];
                Bundle params = args[2];
                if (!(origin == null || origin.equals("crash"))) {
                    DefaultAppMeasurementEventListenerRegistrar.writeEventToUserLog(DefaultAppMeasurementEventListenerRegistrar.this.crashlyticsCore, name, params);
                    return null;
                }
            }
            StringBuilder error = new StringBuilder("Unexpected method invoked on AppMeasurement.EventListener: " + methodName + "(");
            for (int i = 0; i < args.length; i++) {
                if (i > 0) {
                    error.append(", ");
                }
                error.append(args[0].getClass().getName());
            }
            error.append("); returning null");
            Fabric.getLogger().mo34405e("CrashlyticsCore", error.toString());
            return null;
        }

        public boolean equalsImpl(Object proxy, Object obj) {
            if (proxy == obj) {
                return true;
            }
            if (obj != null && Proxy.isProxyClass(obj.getClass()) && super.equals(Proxy.getInvocationHandler(obj))) {
                return true;
            }
            return false;
        }
    }

    public DefaultAppMeasurementEventListenerRegistrar(CrashlyticsCore crashlyticsCore) {
        this.crashlyticsCore = crashlyticsCore;
    }

    public boolean register() {
        Class appMeasurementClass = getClass("com.google.android.gms.measurement.AppMeasurement");
        if (appMeasurementClass == null) {
            Fabric.getLogger().mo34403d("CrashlyticsCore", "Firebase Analytics is not present; you will not see automatic logging of events before a crash occurs.");
            return false;
        }
        Object appMeasurementInstance = getInstance(appMeasurementClass);
        if (appMeasurementInstance == null) {
            Fabric.getLogger().mo34411w("CrashlyticsCore", "Cannot register AppMeasurement Listener for Crashlytics breadcrumbs: Could not create an instance of Firebase Analytics.");
            return false;
        }
        if (getClass("com.google.android.gms.measurement.AppMeasurement$OnEventListener") == null) {
            Fabric.getLogger().mo34411w("CrashlyticsCore", "Cannot register AppMeasurement Listener for Crashlytics breadcrumbs: Could not get class com.google.android.gms.measurement.AppMeasurement$OnEventListener");
            return false;
        }
        try {
            appMeasurementClass.getDeclaredMethod("registerOnMeasurementEventListener", new Class[]{getClass("com.google.android.gms.measurement.AppMeasurement$OnEventListener")}).invoke(appMeasurementInstance, new Object[]{getOnEventListenerProxy(onEventListenerClass)});
        } catch (NoSuchMethodException e) {
            Fabric.getLogger().mo34412w("CrashlyticsCore", "Cannot register AppMeasurement Listener for Crashlytics breadcrumbs: Method registerOnMeasurementEventListener not found.", e);
            return false;
        } catch (Exception e2) {
            Fabric.getLogger().mo34412w("CrashlyticsCore", "Cannot register AppMeasurement Listener for Crashlytics breadcrumbs: " + e2.getMessage(), e2);
        }
        return true;
    }

    private Class<?> getClass(String clazz) {
        try {
            return this.crashlyticsCore.getContext().getClassLoader().loadClass(clazz);
        } catch (Exception e) {
            return null;
        }
    }

    private Object getInstance(Class<?> instanceClass) {
        try {
            return instanceClass.getDeclaredMethod("getInstance", new Class[]{Context.class}).invoke(instanceClass, new Object[]{this.crashlyticsCore.getContext()});
        } catch (Exception e) {
            return null;
        }
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized Object getOnEventListenerProxy(Class appMeasurementEventListenerClass) {
        if (this.eventListenerProxy == null) {
            this.eventListenerProxy = Proxy.newProxyInstance(this.crashlyticsCore.getContext().getClassLoader(), new Class[]{appMeasurementEventListenerClass}, new C16721());
        }
        return this.eventListenerProxy;
    }

    static boolean validateOnEventArgTypes(Object[] args) {
        if (args.length != ONEVENT_EXPECTED_ARGTYPES.size()) {
            return false;
        }
        Iterator<Class<?>> typeItr = ONEVENT_EXPECTED_ARGTYPES.iterator();
        for (Object arg : args) {
            if (!arg.getClass().equals(typeItr.next())) {
                return false;
            }
        }
        return true;
    }

    private static void writeEventToUserLog(CrashlyticsCore crashlyticsCore, String name, Bundle params) {
        try {
            crashlyticsCore.log("$A$:" + serializeEvent(name, params));
        } catch (JSONException e) {
            Fabric.getLogger().mo34411w("CrashlyticsCore", "Unable to serialize Firebase Analytics event; " + name);
        }
    }

    private static String serializeEvent(String name, Bundle params) throws JSONException {
        JSONObject enclosingObject = new JSONObject();
        JSONObject paramsObject = new JSONObject();
        for (String key : params.keySet()) {
            paramsObject.put(key, params.get(key));
        }
        enclosingObject.put("name", name);
        enclosingObject.put("parameters", paramsObject);
        return !(enclosingObject instanceof JSONObject) ? enclosingObject.toString() : JSONObjectInstrumentation.toString(enclosingObject);
    }
}
