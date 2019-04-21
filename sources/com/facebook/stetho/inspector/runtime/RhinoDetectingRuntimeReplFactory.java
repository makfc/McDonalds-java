package com.facebook.stetho.inspector.runtime;

import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.console.RuntimeRepl;
import com.facebook.stetho.inspector.console.RuntimeReplFactory;
import java.lang.reflect.InvocationTargetException;

public class RhinoDetectingRuntimeReplFactory implements RuntimeReplFactory {
    private final Context mContext;
    private RuntimeException mRhinoJsUnexpectedError;
    private RuntimeReplFactory mRhinoReplFactory;
    private boolean mSearchedForRhinoJs;

    /* renamed from: com.facebook.stetho.inspector.runtime.RhinoDetectingRuntimeReplFactory$1 */
    class C20201 implements RuntimeRepl {
        C20201() {
        }

        public Object evaluate(String expression) throws Exception {
            if (RhinoDetectingRuntimeReplFactory.this.mRhinoJsUnexpectedError != null) {
                return "stetho-js-rhino threw: " + RhinoDetectingRuntimeReplFactory.this.mRhinoJsUnexpectedError.toString();
            }
            return "Not supported without stetho-js-rhino dependency";
        }
    }

    public RhinoDetectingRuntimeReplFactory(Context context) {
        this.mContext = context;
    }

    public RuntimeRepl newInstance() {
        if (!this.mSearchedForRhinoJs) {
            this.mSearchedForRhinoJs = true;
            try {
                this.mRhinoReplFactory = findRhinoReplFactory(this.mContext);
            } catch (RuntimeException e) {
                this.mRhinoJsUnexpectedError = e;
            }
        }
        if (this.mRhinoReplFactory != null) {
            return this.mRhinoReplFactory.newInstance();
        }
        return new C20201();
    }

    @Nullable
    private static RuntimeReplFactory findRhinoReplFactory(Context context) throws RuntimeException {
        try {
            return (RuntimeReplFactory) Class.forName("com.facebook.stetho.rhino.JsRuntimeReplFactoryBuilder").getDeclaredMethod("defaultFactory", new Class[]{Context.class}).invoke(null, new Object[]{context});
        } catch (ClassNotFoundException e) {
            LogUtil.m7451i("Error finding stetho-js-rhino, cannot enable console evaluation!");
            return null;
        } catch (NoSuchMethodException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        } catch (IllegalAccessException e4) {
            throw new RuntimeException(e4);
        }
    }
}
