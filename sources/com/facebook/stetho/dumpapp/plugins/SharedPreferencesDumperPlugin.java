package com.facebook.stetho.dumpapp.plugins;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.facebook.stetho.dumpapp.DumpUsageException;
import com.facebook.stetho.dumpapp.DumperContext;
import com.facebook.stetho.dumpapp.DumperPlugin;
import java.io.File;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SharedPreferencesDumperPlugin implements DumperPlugin {
    private static final String NAME = "prefs";
    private static final String XML_SUFFIX = ".xml";
    private final Context mAppContext;

    private enum Type {
        BOOLEAN("boolean"),
        INT("int"),
        LONG("long"),
        FLOAT("float"),
        STRING("string"),
        SET("set");
        
        private final String name;

        private Type(String name) {
            this.name = name;
        }

        @Nullable
        /* renamed from: of */
        public static Type m7463of(String name) {
            for (Type type : values()) {
                if (type.name.equals(name)) {
                    return type;
                }
            }
            return null;
        }

        public static StringBuilder appendNamesList(StringBuilder builder, String separator) {
            boolean isFirst = true;
            for (Type type : values()) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    builder.append(separator);
                }
                builder.append(type.name);
            }
            return builder;
        }
    }

    public SharedPreferencesDumperPlugin(Context context) {
        this.mAppContext = context.getApplicationContext();
    }

    public String getName() {
        return NAME;
    }

    public void dump(DumperContext dumpContext) throws DumpUsageException {
        PrintStream writer = dumpContext.getStdout();
        List<String> args = dumpContext.getArgsAsList();
        String commandName = args.isEmpty() ? "" : (String) args.remove(0);
        if (commandName.equals("print")) {
            doPrint(writer, args);
        } else if (commandName.equals("write")) {
            doWrite(args);
        } else {
            doUsage(writer);
        }
    }

    private void doWrite(List<String> args) throws DumpUsageException {
        String usagePrefix = "Usage: prefs write <path> <key> <type> <value>, where type is one of: ";
        Iterator<String> argsIter = args.iterator();
        String path = nextArg(argsIter, "Expected <path>");
        String key = nextArg(argsIter, "Expected <key>");
        Type type = Type.m7463of(nextArg(argsIter, "Expected <type>"));
        if (type == null) {
            throw new DumpUsageException(Type.appendNamesList(new StringBuilder(usagePrefix), ", ").toString());
        }
        Editor editor = getSharedPreferences(path).edit();
        switch (type) {
            case BOOLEAN:
                editor.putBoolean(key, Boolean.valueOf(nextArgValue(argsIter)).booleanValue());
                break;
            case INT:
                editor.putInt(key, Integer.valueOf(nextArgValue(argsIter)).intValue());
                break;
            case LONG:
                editor.putLong(key, Long.valueOf(nextArgValue(argsIter)).longValue());
                break;
            case FLOAT:
                editor.putFloat(key, Float.valueOf(nextArgValue(argsIter)).floatValue());
                break;
            case STRING:
                editor.putString(key, nextArgValue(argsIter));
                break;
            case SET:
                putStringSet(editor, key, argsIter);
                break;
        }
        editor.commit();
    }

    @Nonnull
    private static String nextArg(Iterator<String> iter, String messageIfNotPresent) throws DumpUsageException {
        if (iter.hasNext()) {
            return (String) iter.next();
        }
        throw new DumpUsageException(messageIfNotPresent);
    }

    @Nonnull
    private static String nextArgValue(Iterator<String> iter) throws DumpUsageException {
        return nextArg(iter, "Expected <value>");
    }

    @TargetApi(11)
    private static void putStringSet(Editor editor, String key, Iterator<String> remainingArgs) {
        HashSet<String> set = new HashSet();
        while (remainingArgs.hasNext()) {
            set.add(remainingArgs.next());
        }
        editor.putStringSet(key, set);
    }

    private void doPrint(PrintStream writer, List<String> args) {
        printRecursive(writer, this.mAppContext.getApplicationInfo().dataDir + "/shared_prefs", "", args.isEmpty() ? "" : (String) args.get(0), args.size() > 1 ? (String) args.get(1) : "");
    }

    private void printRecursive(PrintStream writer, String rootPath, String offsetPath, String pathPrefix, String keyPrefix) {
        File file = new File(rootPath, offsetPath);
        if (file.isFile()) {
            if (offsetPath.endsWith(XML_SUFFIX)) {
                printFile(writer, offsetPath.substring(0, offsetPath.length() - XML_SUFFIX.length()), keyPrefix);
            }
        } else if (file.isDirectory()) {
            String[] children = file.list();
            if (children != null) {
                int i = 0;
                while (i < children.length) {
                    String childOffsetPath = TextUtils.isEmpty(offsetPath) ? children[i] : offsetPath + File.separator + children[i];
                    if (childOffsetPath.startsWith(pathPrefix)) {
                        printRecursive(writer, rootPath, childOffsetPath, pathPrefix, keyPrefix);
                    }
                    i++;
                }
            }
        }
    }

    private void printFile(PrintStream writer, String prefsName, String keyPrefix) {
        writer.println(prefsName + ":");
        for (Entry<String, ?> entry : getSharedPreferences(prefsName).getAll().entrySet()) {
            if (((String) entry.getKey()).startsWith(keyPrefix)) {
                writer.println("  " + ((String) entry.getKey()) + " = " + entry.getValue());
            }
        }
    }

    private void doUsage(PrintStream writer) {
        String cmdName = "dumpapp prefs";
        String usagePrefix = "Usage: dumpapp prefs ";
        writer.println(usagePrefix + "<command> [command-options]");
        writer.println(usagePrefix + "print [pathPrefix [keyPrefix]]");
        writer.println(Type.appendNamesList(new StringBuilder("       dumpapp prefs ").append("write <path> <key> <"), "|").append("> <value>"));
        writer.println();
        writer.println("dumpapp prefs print: Print all matching values from the shared preferences");
        writer.println();
        writer.println("dumpapp prefs write: Writes a value to the shared preferences");
    }

    private SharedPreferences getSharedPreferences(String name) {
        return this.mAppContext.getSharedPreferences(name, 4);
    }
}
