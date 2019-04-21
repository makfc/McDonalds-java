package com.amap.api.mapcore.util;

import android.content.Context;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/* renamed from: com.amap.api.mapcore.util.fe */
public class InstanceFactory {
    /* renamed from: a */
    public static <T> T m2730a(Context context, SDKInfo sDKInfo, String str, Class cls, Class[] clsArr, Object[] objArr) throws AMapCoreException {
        BaseClassLoader a;
        try {
            a = ClassLoaderFactory.m2668a().mo9361a(context, sDKInfo);
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "InstanceFactory", "getInstance");
            a = null;
        }
        if (a != null) {
            try {
                if (a.mo9381a() && a.f1939d) {
                    Class loadClass = a.loadClass(str);
                    if (loadClass != null) {
                        Constructor declaredConstructor = loadClass.getDeclaredConstructor(clsArr);
                        declaredConstructor.setAccessible(true);
                        return declaredConstructor.newInstance(objArr);
                    }
                }
            } catch (ClassNotFoundException th2) {
                BasicLogHandler.m2542a(th2, "InstanceFactory", "getInstance()");
            } catch (NoSuchMethodException th22) {
                BasicLogHandler.m2542a(th22, "InstanceFactory", "getInstance()");
            } catch (InstantiationException th222) {
                BasicLogHandler.m2542a(th222, "InstanceFactory", "getInstance()");
            } catch (IllegalAccessException th2222) {
                BasicLogHandler.m2542a(th2222, "InstanceFactory", "getInstance()");
            } catch (IllegalArgumentException th22222) {
                BasicLogHandler.m2542a(th22222, "InstanceFactory", "getInstance()");
            } catch (InvocationTargetException th222222) {
                BasicLogHandler.m2542a(th222222, "InstanceFactory", "getInstance()");
            } catch (Throwable th2222222) {
                BasicLogHandler.m2542a(th2222222, "InstanceFactory", "getInstance()");
            }
        }
        if (cls == null) {
            return null;
        }
        try {
            Constructor declaredConstructor2 = cls.getDeclaredConstructor(clsArr);
            declaredConstructor2.setAccessible(true);
            return declaredConstructor2.newInstance(objArr);
        } catch (NoSuchMethodException e) {
            BasicLogHandler.m2542a(e, "InstanceFactory", "getInstance()");
        } catch (InstantiationException e2) {
            BasicLogHandler.m2542a(e2, "InstanceFactory", "getInstance()");
        } catch (IllegalAccessException e22) {
            BasicLogHandler.m2542a(e22, "InstanceFactory", "getInstance()");
        } catch (IllegalArgumentException e222) {
            BasicLogHandler.m2542a(e222, "InstanceFactory", "getInstance()");
        } catch (InvocationTargetException e2222) {
            BasicLogHandler.m2542a(e2222, "InstanceFactory", "getInstance()");
        } catch (Throwable e22222) {
            BasicLogHandler.m2542a(e22222, "InstanceFactory", "getInstance()");
        }
        throw new AMapCoreException("获取对象错误");
    }
}
