package com.google.api.client.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

public class IOUtils {
    public static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        copy(inputStream, outputStream, true);
    }

    public static void copy(InputStream inputStream, OutputStream outputStream, boolean closeInputStream) throws IOException {
        try {
            ByteStreams.copy(inputStream, outputStream);
        } finally {
            if (closeInputStream) {
                inputStream.close();
            }
        }
    }

    public static long computeLength(StreamingContent content) throws IOException {
        ByteCountingOutputStream countingStream = new ByteCountingOutputStream();
        try {
            content.writeTo(countingStream);
            return countingStream.count;
        } finally {
            countingStream.close();
        }
    }

    public static byte[] serialize(Object value) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        serialize(value, out);
        return out.toByteArray();
    }

    public static void serialize(Object value, OutputStream outputStream) throws IOException {
        try {
            new ObjectOutputStream(outputStream).writeObject(value);
        } finally {
            outputStream.close();
        }
    }

    public static <S extends Serializable> S deserialize(byte[] bytes) throws IOException {
        if (bytes == null) {
            return null;
        }
        return deserialize(new ByteArrayInputStream(bytes));
    }

    public static <S extends Serializable> S deserialize(InputStream inputStream) throws IOException {
        try {
            Serializable serializable = (Serializable) new ObjectInputStream(inputStream).readObject();
            inputStream.close();
            return serializable;
        } catch (ClassNotFoundException exception) {
            IOException ioe = new IOException("Failed to deserialize object");
            ioe.initCause(exception);
            throw ioe;
        } catch (Throwable th) {
            inputStream.close();
        }
    }

    public static boolean isSymbolicLink(File file) throws IOException {
        boolean z = false;
        try {
            Class<?> filesClass = Class.forName("java.nio.file.Files");
            Class<?> pathClass = Class.forName("java.nio.file.Path");
            Object path = File.class.getMethod("toPath", new Class[0]).invoke(file, new Object[0]);
            return ((Boolean) filesClass.getMethod("isSymbolicLink", new Class[]{pathClass}).invoke(null, new Object[]{path})).booleanValue();
        } catch (InvocationTargetException exception) {
            Throwable cause = exception.getCause();
            Throwables.propagateIfPossible(cause, IOException.class);
            throw new RuntimeException(cause);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException e) {
            if (File.separatorChar == '\\') {
                return z;
            }
            File canonical = file;
            if (file.getParent() != null) {
                canonical = new File(file.getParentFile().getCanonicalFile(), file.getName());
            }
            return !canonical.getCanonicalFile().equals(canonical.getAbsoluteFile()) ? true : z;
        }
    }
}
