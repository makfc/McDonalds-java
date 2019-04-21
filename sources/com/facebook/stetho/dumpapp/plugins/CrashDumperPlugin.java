package com.facebook.stetho.dumpapp.plugins;

import android.os.Process;
import com.facebook.stetho.common.ExceptionUtil;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.dumpapp.ArgsHelper;
import com.facebook.stetho.dumpapp.DumpException;
import com.facebook.stetho.dumpapp.DumpUsageException;
import com.facebook.stetho.dumpapp.DumperContext;
import com.facebook.stetho.dumpapp.DumperPlugin;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import javax.annotation.Nullable;

public class CrashDumperPlugin implements DumperPlugin {
    private static final String NAME = "crash";
    private static final String OPTION_EXIT_DEFAULT = "0";
    private static final String OPTION_KILL_DEFAULT = "9";
    private static final String OPTION_THROW_DEFAULT = "java.lang.Error";

    private static class ThrowRunnable implements Runnable {
        private final Throwable mThrowable;

        public ThrowRunnable(Throwable t) {
            this.mThrowable = t;
        }

        public void run() {
            ExceptionUtil.sneakyThrow(this.mThrowable);
        }
    }

    public String getName() {
        return NAME;
    }

    public void dump(DumperContext dumpContext) throws DumpException {
        Iterator<String> argsIter = dumpContext.getArgsAsList().iterator();
        String command = ArgsHelper.nextOptionalArg(argsIter, null);
        if ("throw".equals(command)) {
            doUncaughtException(argsIter);
        } else if ("kill".equals(command)) {
            doKill(dumpContext, argsIter);
        } else if ("exit".equals(command)) {
            doSystemExit(argsIter);
        } else {
            doUsage(dumpContext.getStdout());
            if (command != null) {
                throw new DumpUsageException("Unsupported command: " + command);
            }
        }
    }

    private void doUsage(PrintStream out) {
        String cmdName = "dumpapp crash";
        String usagePrefix = "Usage: dumpapp crash ";
        String blankPrefix = "       dumpapp crash ";
        out.println(usagePrefix + "<command> [command-options]");
        out.println(usagePrefix + "throw");
        out.println(blankPrefix + "kill");
        out.println(blankPrefix + "exit");
        out.println();
        out.println("dumpapp crash throw: Throw an uncaught exception (simulates a program crash)");
        out.println("    <Throwable>: Throwable class to use (default: java.lang.Error)");
        out.println();
        out.println("dumpapp crash kill: Send a signal to this process (simulates the low memory killer)");
        out.println("    <SIGNAL>: Either signal name or number to send (default: 9)");
        out.println("              See `adb shell kill -l` for more information");
        out.println();
        out.println("dumpapp crash exit: Invoke System.exit (simulates an abnormal Android exit strategy)");
        out.println("    <code>: Exit code (default: 0)");
    }

    private void doSystemExit(Iterator<String> argsIter) {
        System.exit(Integer.parseInt(ArgsHelper.nextOptionalArg(argsIter, "0")));
    }

    private void doKill(DumperContext dumpContext, Iterator<String> argsIter) throws DumpException {
        String signal = ArgsHelper.nextOptionalArg(argsIter, "9");
        Process kill;
        try {
            kill = new ProcessBuilder(new String[0]).command(new String[]{"/system/bin/kill", "-" + signal, String.valueOf(Process.myPid())}).redirectErrorStream(true).start();
            Util.copy(kill.getInputStream(), dumpContext.getStdout(), new byte[1024]);
            kill.destroy();
        } catch (IOException e) {
            throw new DumpException("Failed to invoke kill: " + e);
        } catch (Throwable th) {
            kill.destroy();
        }
    }

    private void doUncaughtException(Iterator<String> argsIter) throws DumpException {
        try {
            Throwable t;
            Class<? extends Throwable> throwableClass = Class.forName(ArgsHelper.nextOptionalArg(argsIter, OPTION_THROW_DEFAULT));
            Constructor<? extends Throwable> ctorWithMessage = tryGetDeclaredConstructor(throwableClass, String.class);
            if (ctorWithMessage != null) {
                t = (Throwable) ctorWithMessage.newInstance(new Object[]{"Uncaught exception triggered by Stetho"});
            } else {
                t = (Throwable) throwableClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            }
            Thread crashThread = new Thread(new ThrowRunnable(t));
            crashThread.start();
            Util.joinUninterruptibly(crashThread);
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException e) {
            throw new DumpException("Invalid supplied Throwable class: " + e);
        } catch (InvocationTargetException e2) {
            throw ExceptionUtil.propagate(e2.getCause());
        }
    }

    @Nullable
    private static <T> Constructor<? extends T> tryGetDeclaredConstructor(Class<T> clazz, Class<?>... parameterTypes) {
        try {
            return clazz.getDeclaredConstructor(parameterTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
