package org.acra.collector;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.acra.ACRA;

final class DisplayManagerCollector {
    static final SparseArray<String> mDensities = new SparseArray();
    static final SparseArray<String> mFlagsNames = new SparseArray();

    DisplayManagerCollector() {
    }

    public static String collectDisplays(Context ctx) {
        StringBuilder stringBuilder = new StringBuilder();
        Display[] displayArr;
        if (Compatibility.getAPILevel() < 17) {
            displayArr = new Display[]{((WindowManager) ctx.getSystemService("window")).getDefaultDisplay()};
        } else {
            try {
                Object systemService = ctx.getSystemService((String) ctx.getClass().getField("DISPLAY_SERVICE").get(null));
                displayArr = (Display[]) systemService.getClass().getMethod("getDisplays", new Class[0]).invoke(systemService, new Object[0]);
            } catch (IllegalArgumentException e) {
                ACRA.log.mo23354w(ACRA.LOG_TAG, "Error while collecting DisplayManager data: ", e);
                displayArr = null;
            } catch (SecurityException e2) {
                ACRA.log.mo23354w(ACRA.LOG_TAG, "Error while collecting DisplayManager data: ", e2);
                displayArr = null;
            } catch (IllegalAccessException e3) {
                ACRA.log.mo23354w(ACRA.LOG_TAG, "Error while collecting DisplayManager data: ", e3);
                displayArr = null;
            } catch (NoSuchFieldException e4) {
                ACRA.log.mo23354w(ACRA.LOG_TAG, "Error while collecting DisplayManager data: ", e4);
                displayArr = null;
            } catch (NoSuchMethodException e5) {
                ACRA.log.mo23354w(ACRA.LOG_TAG, "Error while collecting DisplayManager data: ", e5);
                displayArr = null;
            } catch (InvocationTargetException e6) {
                ACRA.log.mo23354w(ACRA.LOG_TAG, "Error while collecting DisplayManager data: ", e6);
                displayArr = null;
            }
        }
        for (Display collectDisplayData : displayArr) {
            stringBuilder.append(collectDisplayData(collectDisplayData));
        }
        return stringBuilder.toString();
    }

    private static Object collectDisplayData(Display display) {
        display.getMetrics(new DisplayMetrics());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(collectCurrentSizeRange(display));
        stringBuilder.append(collectFlags(display));
        stringBuilder.append(display.getDisplayId()).append(".height=").append(display.getHeight()).append(10);
        stringBuilder.append(collectMetrics(display, "getMetrics"));
        stringBuilder.append(collectName(display));
        stringBuilder.append(display.getDisplayId()).append(".orientation=").append(display.getOrientation()).append(10);
        stringBuilder.append(display.getDisplayId()).append(".pixelFormat=").append(display.getPixelFormat()).append(10);
        stringBuilder.append(collectMetrics(display, "getRealMetrics"));
        stringBuilder.append(collectSize(display, "getRealSize"));
        stringBuilder.append(collectRectSize(display));
        stringBuilder.append(display.getDisplayId()).append(".refreshRate=").append(display.getRefreshRate()).append(10);
        stringBuilder.append(collectRotation(display));
        stringBuilder.append(collectSize(display, "getSize"));
        stringBuilder.append(display.getDisplayId()).append(".width=").append(display.getWidth()).append(10);
        stringBuilder.append(collectIsValid(display));
        return stringBuilder.toString();
    }

    private static Object collectIsValid(Display display) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(display.getDisplayId()).append(".isValid=").append((Boolean) display.getClass().getMethod("isValid", new Class[0]).invoke(display, new Object[0])).append(10);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
        }
        return stringBuilder.toString();
    }

    private static Object collectRotation(Display display) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            int intValue = ((Integer) display.getClass().getMethod("getRotation", new Class[0]).invoke(display, new Object[0])).intValue();
            stringBuilder.append(display.getDisplayId()).append(".rotation=");
            switch (intValue) {
                case 0:
                    stringBuilder.append("ROTATION_0");
                    break;
                case 1:
                    stringBuilder.append("ROTATION_90");
                    break;
                case 2:
                    stringBuilder.append("ROTATION_180");
                    break;
                case 3:
                    stringBuilder.append("ROTATION_270");
                    break;
                default:
                    stringBuilder.append(intValue);
                    break;
            }
            stringBuilder.append(10);
        } catch (SecurityException e) {
        } catch (NoSuchMethodException e2) {
        } catch (IllegalArgumentException e3) {
        } catch (IllegalAccessException | InvocationTargetException e4) {
        }
        return stringBuilder.toString();
    }

    private static Object collectRectSize(Display display) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Method method = display.getClass().getMethod("getRectSize", new Class[]{Rect.class});
            Rect rect = new Rect();
            method.invoke(display, new Object[]{rect});
            stringBuilder.append(display.getDisplayId()).append(".rectSize=[").append(rect.top).append(',').append(rect.left).append(',').append(rect.width()).append(',').append(rect.height()).append(']').append(10);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
        }
        return stringBuilder.toString();
    }

    private static Object collectSize(Display display, String methodName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Method method = display.getClass().getMethod(methodName, new Class[]{Point.class});
            Point point = new Point();
            method.invoke(display, new Object[]{point});
            stringBuilder.append(display.getDisplayId()).append('.').append(methodName).append("=[").append(point.x).append(',').append(point.y).append(']').append(10);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
        }
        return stringBuilder.toString();
    }

    private static String collectCurrentSizeRange(Display display) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Method method = display.getClass().getMethod("getCurrentSizeRange", new Class[]{Point.class, Point.class});
            Point point = new Point();
            Point point2 = new Point();
            method.invoke(display, new Object[]{point, point2});
            stringBuilder.append(display.getDisplayId()).append(".currentSizeRange.smallest=[").append(point.x).append(',').append(point.y).append(']').append(10);
            stringBuilder.append(display.getDisplayId()).append(".currentSizeRange.largest=[").append(point2.x).append(',').append(point2.y).append(']').append(10);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
        }
        return stringBuilder.toString();
    }

    private static String collectFlags(Display display) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            int intValue = ((Integer) display.getClass().getMethod("getFlags", new Class[0]).invoke(display, new Object[0])).intValue();
            for (Field field : display.getClass().getFields()) {
                if (field.getName().startsWith("FLAG_")) {
                    mFlagsNames.put(field.getInt(null), field.getName());
                }
            }
            stringBuilder.append(display.getDisplayId()).append(".flags=").append(activeFlags(mFlagsNames, intValue)).append(10);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
        }
        return stringBuilder.toString();
    }

    private static String collectName(Display display) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(display.getDisplayId()).append(".name=").append((String) display.getClass().getMethod("getName", new Class[0]).invoke(display, new Object[0])).append(10);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
        }
        return stringBuilder.toString();
    }

    private static Object collectMetrics(Display display, String methodName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            DisplayMetrics displayMetrics = (DisplayMetrics) display.getClass().getMethod(methodName, new Class[0]).invoke(display, new Object[0]);
            for (Field field : DisplayMetrics.class.getFields()) {
                if (field.getType().equals(Integer.class) && field.getName().startsWith("DENSITY_") && !field.getName().equals("DENSITY_DEFAULT")) {
                    mDensities.put(field.getInt(null), field.getName());
                }
            }
            stringBuilder.append(display.getDisplayId()).append('.').append(methodName).append(".density=").append(displayMetrics.density).append(10);
            stringBuilder.append(display.getDisplayId()).append('.').append(methodName).append(".densityDpi=").append(displayMetrics.getClass().getField("densityDpi")).append(10);
            stringBuilder.append(display.getDisplayId()).append('.').append(methodName).append("scaledDensity=x").append(displayMetrics.scaledDensity).append(10);
            stringBuilder.append(display.getDisplayId()).append('.').append(methodName).append(".widthPixels=").append(displayMetrics.widthPixels).append(10);
            stringBuilder.append(display.getDisplayId()).append('.').append(methodName).append(".heightPixels=").append(displayMetrics.heightPixels).append(10);
            stringBuilder.append(display.getDisplayId()).append('.').append(methodName).append(".xdpi=").append(displayMetrics.xdpi).append(10);
            stringBuilder.append(display.getDisplayId()).append('.').append(methodName).append(".ydpi=").append(displayMetrics.ydpi).append(10);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
        }
        return stringBuilder.toString();
    }

    private static String activeFlags(SparseArray<String> valueNames, int bitfield) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= valueNames.size()) {
                return stringBuilder.toString();
            }
            i = valueNames.keyAt(i2) & bitfield;
            if (i > 0) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append('+');
                }
                stringBuilder.append((String) valueNames.get(i));
            }
            i = i2 + 1;
        }
    }
}
