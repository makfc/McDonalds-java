package com.newrelic.agent.android.instrumentation;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Rect;
import android.util.TypedValue;
import com.facebook.internal.NativeProtocol;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class BitmapFactoryInstrumentation {
    private static final ArrayList<String> categoryParams = new ArrayList(Arrays.asList(new String[]{"category", MetricCategory.class.getName(), NativeProtocol.METHOD_ARGS_IMAGE}));

    private BitmapFactoryInstrumentation() {
    }

    @ReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeFile(String pathName, Options opts) {
        TraceMachine.enterMethod("BitmapFactory#decodeFile", categoryParams);
        Bitmap bitmap = BitmapFactory.decodeFile(pathName, opts);
        TraceMachine.exitMethod();
        return bitmap;
    }

    @ReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeFile(String pathName) {
        TraceMachine.enterMethod("BitmapFactory#decodeFile", categoryParams);
        Bitmap bitmap = BitmapFactory.decodeFile(pathName);
        TraceMachine.exitMethod();
        return bitmap;
    }

    @ReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeResourceStream(Resources res, TypedValue value, InputStream is, Rect pad, Options opts) {
        TraceMachine.enterMethod("BitmapFactory#decodeResourceStream", categoryParams);
        Bitmap bitmap = BitmapFactory.decodeResourceStream(res, value, is, pad, opts);
        TraceMachine.exitMethod();
        return bitmap;
    }

    @ReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeResource(Resources res, int id, Options opts) {
        TraceMachine.enterMethod("BitmapFactory#decodeResource", categoryParams);
        Bitmap bitmap = BitmapFactory.decodeResource(res, id, opts);
        TraceMachine.exitMethod();
        return bitmap;
    }

    @ReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeResource(Resources res, int id) {
        TraceMachine.enterMethod("BitmapFactory#decodeResource", categoryParams);
        Bitmap bitmap = BitmapFactory.decodeResource(res, id);
        TraceMachine.exitMethod();
        return bitmap;
    }

    @ReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeByteArray(byte[] data, int offset, int length, Options opts) {
        TraceMachine.enterMethod("BitmapFactory#decodeByteArray", categoryParams);
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, offset, length, opts);
        TraceMachine.exitMethod();
        return bitmap;
    }

    @ReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeByteArray(byte[] data, int offset, int length) {
        TraceMachine.enterMethod("BitmapFactory#decodeByteArray", categoryParams);
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, offset, length);
        TraceMachine.exitMethod();
        return bitmap;
    }

    @ReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeStream(InputStream is, Rect outPadding, Options opts) {
        TraceMachine.enterMethod("BitmapFactory#decodeStream", categoryParams);
        Bitmap bitmap = BitmapFactory.decodeStream(is, outPadding, opts);
        TraceMachine.exitMethod();
        return bitmap;
    }

    @ReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeStream(InputStream is) {
        TraceMachine.enterMethod("BitmapFactory#decodeStream", categoryParams);
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        TraceMachine.exitMethod();
        return bitmap;
    }

    @ReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeFileDescriptor(FileDescriptor fd, Rect outPadding, Options opts) {
        TraceMachine.enterMethod("BitmapFactory#decodeFileDescriptor", categoryParams);
        Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fd, outPadding, opts);
        TraceMachine.exitMethod();
        return bitmap;
    }

    @ReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeFileDescriptor(FileDescriptor fd) {
        TraceMachine.enterMethod("BitmapFactory#decodeFileDescriptor", categoryParams);
        Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fd);
        TraceMachine.exitMethod();
        return bitmap;
    }
}
