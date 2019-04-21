package com.crashlytics.android.core;

import android.content.Context;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import p041io.fabric.sdk.android.services.common.CommonUtils;

final class NativeFileUtils {
    private NativeFileUtils() {
    }

    static byte[] readFile(File file) {
        Throwable th;
        byte[] bArr = null;
        FileInputStream fis = null;
        try {
            FileInputStream fis2 = new FileInputStream(file);
            try {
                bArr = readBytes(fis2);
                CommonUtils.closeQuietly(fis2);
                fis = fis2;
            } catch (FileNotFoundException e) {
                fis = fis2;
                CommonUtils.closeQuietly(fis);
                return bArr;
            } catch (IOException e2) {
                fis = fis2;
                CommonUtils.closeQuietly(fis);
                return bArr;
            } catch (Throwable th2) {
                th = th2;
                fis = fis2;
                CommonUtils.closeQuietly(fis);
                throw th;
            }
        } catch (FileNotFoundException e3) {
            CommonUtils.closeQuietly(fis);
            return bArr;
        } catch (IOException e4) {
            CommonUtils.closeQuietly(fis);
            return bArr;
        } catch (Throwable th3) {
            th = th3;
            CommonUtils.closeQuietly(fis);
            throw th;
        }
        return bArr;
    }

    private static byte[] readBytes(InputStream inputStream) throws IOException {
        byte[] b = new byte[1024];
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        while (true) {
            int c = inputStream.read(b);
            if (c == -1) {
                return os.toByteArray();
            }
            os.write(b, 0, c);
        }
    }

    private static File filter(File directory, String extension) {
        for (File file : directory.listFiles()) {
            if (file.getName().endsWith(extension)) {
                return file;
            }
        }
        return null;
    }

    static byte[] minidumpFromDirectory(File directory) {
        File file = filter(directory, ".dmp");
        return file == null ? new byte[0] : minidumpFromFile(file);
    }

    private static byte[] minidumpFromFile(File file) {
        return readFile(file);
    }

    static byte[] binaryImagesJsonFromDirectory(File directory, Context context) throws IOException {
        File file = filter(directory, ".maps");
        if (file != null) {
            return binaryImagesJsonFromMapsFile(file, context);
        }
        file = filter(directory, ".binary_libs");
        if (file != null) {
            return binaryImagesJsonFromBinaryLibsFile(file, context);
        }
        return null;
    }

    private static byte[] binaryImagesJsonFromBinaryLibsFile(File file, Context context) throws IOException {
        byte[] raw = readFile(file);
        if (raw == null || raw.length == 0) {
            return null;
        }
        return processBinaryImages(context, new String(raw));
    }

    private static byte[] binaryImagesJsonFromMapsFile(File file, Context context) throws IOException {
        Throwable th;
        if (!file.exists()) {
            return null;
        }
        BufferedReader reader = null;
        try {
            BufferedReader reader2 = new BufferedReader(new FileReader(file));
            try {
                byte[] convert = new BinaryImagesConverter(context, new Sha1FileIdStrategy()).convert(reader2);
                CommonUtils.closeQuietly(reader2);
                return convert;
            } catch (Throwable th2) {
                th = th2;
                reader = reader2;
                CommonUtils.closeQuietly(reader);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            CommonUtils.closeQuietly(reader);
            throw th;
        }
    }

    static byte[] metadataJsonFromDirectory(File directory) {
        File file = filter(directory, ".device_info");
        return file == null ? null : readFile(file);
    }

    private static byte[] processBinaryImages(Context context, String binaryImages) throws IOException {
        return new BinaryImagesConverter(context, new Sha1FileIdStrategy()).convert(binaryImages);
    }
}
