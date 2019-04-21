package com.facebook;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.util.Pair;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.UUID;

public class NativeAppCallContentProvider extends ContentProvider {
    private static final String ATTACHMENT_URL_BASE = "content://com.facebook.app.NativeAppCallContentProvider";
    private static final String TAG = NativeAppCallContentProvider.class.getName();
    private final AttachmentDataSource dataSource;

    interface AttachmentDataSource {
        File openAttachment(UUID uuid, String str) throws FileNotFoundException;
    }

    public NativeAppCallContentProvider() {
        this(new NativeAppCallAttachmentStore());
    }

    NativeAppCallContentProvider(AttachmentDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static String getAttachmentUrl(String applicationId, UUID callId, String attachmentName) {
        return String.format("%s%s/%s/%s", new Object[]{ATTACHMENT_URL_BASE, applicationId, callId.toString(), attachmentName});
    }

    public boolean onCreate() {
        return true;
    }

    public Cursor query(Uri uri, String[] strings, String s, String[] strings2, String s2) {
        return null;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }

    public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {
        Pair<UUID, String> callIdAndAttachmentName = parseCallIdAndAttachmentName(uri);
        if (callIdAndAttachmentName == null) {
            throw new FileNotFoundException();
        }
        try {
            return ParcelFileDescriptor.open(this.dataSource.openAttachment((UUID) callIdAndAttachmentName.first, (String) callIdAndAttachmentName.second), 268435456);
        } catch (FileNotFoundException exception) {
            Log.e(TAG, "Got unexpected exception:" + exception);
            throw exception;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public Pair<UUID, String> parseCallIdAndAttachmentName(Uri uri) {
        try {
            String[] parts = uri.getPath().substring(1).split("/");
            String callIdString = parts[0];
            return new Pair(UUID.fromString(callIdString), parts[1]);
        } catch (Exception e) {
            return null;
        }
    }
}
