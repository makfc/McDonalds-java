package com.facebook.stetho.dumpapp.plugins;

import android.content.Context;
import android.os.Debug;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.dumpapp.DumpException;
import com.facebook.stetho.dumpapp.DumpUsageException;
import com.facebook.stetho.dumpapp.DumperContext;
import com.facebook.stetho.dumpapp.DumperPlugin;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Iterator;

public class HprofDumperPlugin implements DumperPlugin {
    private static final String NAME = "hprof";
    private final Context mContext;

    public HprofDumperPlugin(Context context) {
        this.mContext = context;
    }

    public String getName() {
        return NAME;
    }

    public void dump(DumperContext dumpContext) throws DumpException {
        PrintStream output = dumpContext.getStdout();
        Iterator<String> argsIter = dumpContext.getArgsAsList().iterator();
        String outputPath = argsIter.hasNext() ? (String) argsIter.next() : null;
        if (outputPath == null) {
            usage(output);
        } else if ("-".equals(outputPath)) {
            handlePipeOutput(output);
        } else {
            File outputFile = new File(outputPath);
            if (!outputFile.isAbsolute()) {
                outputFile = this.mContext.getFileStreamPath(outputPath);
            }
            writeHprof(outputFile);
            output.println("Wrote to " + outputFile);
        }
    }

    private void handlePipeOutput(OutputStream output) throws DumpException {
        File hprofFile = this.mContext.getFileStreamPath("hprof-dump.hprof");
        try {
            writeHprof(hprofFile);
            InputStream input;
            try {
                input = new FileInputStream(hprofFile);
                Util.copy(input, output, new byte[2048]);
                input.close();
            } catch (IOException e) {
                throw new DumpException("Failure copying " + hprofFile + " to dumper output");
            } catch (Throwable th) {
                input.close();
            }
        } finally {
            if (hprofFile.exists()) {
                hprofFile.delete();
            }
        }
    }

    private void writeHprof(File outputPath) throws DumpException {
        try {
            truncateAndDeleteFile(outputPath);
            Debug.dumpHprofData(outputPath.getAbsolutePath());
        } catch (IOException e) {
            throw new DumpException("Failure writing to " + outputPath + ": " + e.getMessage());
        }
    }

    private static void truncateAndDeleteFile(File file) throws IOException {
        new FileOutputStream(file).close();
        if (!file.delete()) {
            throw new IOException("Failed to delete " + file);
        }
    }

    private void usage(PrintStream output) throws DumpUsageException {
        output.println("Usage: dumpapp hprof [ path ]");
        output.println("Dump HPROF memory usage data from the running application.");
        output.println();
        output.println("Where path can be any of:");
        output.println("  -           Output directly to stdout");
        output.println("  <path>      Full path to a writable file on the device");
        output.println("  <filename>  Relative filename that will be stored in the app internal storage");
        throw new DumpUsageException("Missing path");
    }
}
