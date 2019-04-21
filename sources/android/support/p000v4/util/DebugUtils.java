package android.support.p000v4.util;

import android.support.annotation.RestrictTo;
import com.newrelic.agent.android.util.SafeJsonPrimitive;

@RestrictTo
/* renamed from: android.support.v4.util.DebugUtils */
public class DebugUtils {
    public static void buildShortClassTag(Object cls, StringBuilder out) {
        if (cls == null) {
            out.append(SafeJsonPrimitive.NULL_STRING);
            return;
        }
        String simpleName = cls.getClass().getSimpleName();
        if (simpleName == null || simpleName.length() <= 0) {
            simpleName = cls.getClass().getName();
            int end = simpleName.lastIndexOf(46);
            if (end > 0) {
                simpleName = simpleName.substring(end + 1);
            }
        }
        out.append(simpleName);
        out.append('{');
        out.append(Integer.toHexString(System.identityHashCode(cls)));
    }
}
