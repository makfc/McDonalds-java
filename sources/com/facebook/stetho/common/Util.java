package com.facebook.stetho.common;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Util {
    public static <T> T throwIfNull(T item) {
        if (item != null) {
            return item;
        }
        throw new NullPointerException();
    }

    public static <T1, T2> void throwIfNull(T1 item1, T2 item2) {
        throwIfNull(item1);
        throwIfNull(item2);
    }

    public static <T1, T2, T3> void throwIfNull(T1 item1, T2 item2, T3 item3) {
        throwIfNull(item1);
        throwIfNull(item2);
        throwIfNull(item3);
    }

    public static void throwIfNotNull(Object item) {
        if (item != null) {
            throw new IllegalStateException();
        }
    }

    public static void throwIf(boolean condition) {
        if (condition) {
            throw new IllegalStateException();
        }
    }

    public static void throwIfNot(boolean condition) {
        if (!condition) {
            throw new IllegalStateException();
        }
    }

    public static void throwIfNot(boolean condition, String format, Object... args) {
        if (!condition) {
            throw new IllegalStateException(String.format(format, args));
        }
    }

    public static void copy(InputStream input, OutputStream output, byte[] buffer) throws IOException {
        while (true) {
            int n = input.read(buffer);
            if (n != -1) {
                output.write(buffer, 0, n);
            } else {
                return;
            }
        }
    }

    public static void close(Closeable closeable, boolean hideException) throws IOException {
        if (closeable == null) {
            return;
        }
        if (hideException) {
            try {
                closeable.close();
                return;
            } catch (IOException e) {
                LogUtil.m7449e(e, "Hiding IOException because another is pending");
                return;
            }
        }
        closeable.close();
    }

    public static void sleepUninterruptibly(long millis) {
        long remaining = millis;
        long startTime = System.currentTimeMillis();
        do {
            try {
                Thread.sleep(remaining);
                return;
            } catch (InterruptedException e) {
                remaining -= System.currentTimeMillis() - startTime;
                if (remaining <= 0) {
                }
            }
        } while (remaining <= 0);
    }

    public static void joinUninterruptibly(Thread t) {
        while (true) {
            try {
                t.join();
                break;
            } catch (InterruptedException e) {
            }
        }
    }

    public static void awaitUninterruptibly(CountDownLatch latch) {
        while (true) {
            try {
                latch.await();
                break;
            } catch (InterruptedException e) {
            }
        }
    }

    public static <T> T getUninterruptibly(Future<T> future, long timeout, TimeUnit unit) throws TimeoutException, ExecutionException {
        long remaining = unit.toMillis(timeout);
        while (true) {
            try {
                break;
            } catch (InterruptedException e) {
                remaining -= System.currentTimeMillis() - System.currentTimeMillis();
            }
        }
        return future.get(remaining, TimeUnit.MILLISECONDS);
    }

    public static <T> T getUninterruptibly(Future<T> future) throws ExecutionException {
        while (true) {
            try {
                break;
            } catch (InterruptedException e) {
            }
        }
        return future.get();
    }

    public static String readAsUTF8(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        copy(in, out, new byte[1024]);
        return out.toString(Utf8Charset.NAME);
    }
}
