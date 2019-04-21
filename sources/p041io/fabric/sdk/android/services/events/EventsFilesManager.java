package p041io.fabric.sdk.android.services.events;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;
import p041io.fabric.sdk.android.services.common.CommonUtils;
import p041io.fabric.sdk.android.services.common.CurrentTimeProvider;

/* renamed from: io.fabric.sdk.android.services.events.EventsFilesManager */
public abstract class EventsFilesManager<T> {
    protected final Context context;
    protected final CurrentTimeProvider currentTimeProvider;
    private final int defaultMaxFilesToKeep;
    protected final EventsStorage eventStorage;
    protected volatile long lastRollOverTime;
    protected final List<EventsStorageListener> rollOverListeners = new CopyOnWriteArrayList();
    protected final EventTransform<T> transform;

    /* renamed from: io.fabric.sdk.android.services.events.EventsFilesManager$1 */
    class C46001 implements Comparator<FileWithTimestamp> {
        C46001() {
        }

        public int compare(FileWithTimestamp arg0, FileWithTimestamp arg1) {
            return (int) (arg0.timestamp - arg1.timestamp);
        }
    }

    /* renamed from: io.fabric.sdk.android.services.events.EventsFilesManager$FileWithTimestamp */
    static class FileWithTimestamp {
        final File file;
        final long timestamp;

        public FileWithTimestamp(File file, long timestamp) {
            this.file = file;
            this.timestamp = timestamp;
        }
    }

    public abstract String generateUniqueRollOverFileName();

    public EventsFilesManager(Context context, EventTransform<T> transform, CurrentTimeProvider currentTimeProvider, EventsStorage eventStorage, int defaultMaxFilesToKeep) throws IOException {
        this.context = context.getApplicationContext();
        this.transform = transform;
        this.eventStorage = eventStorage;
        this.currentTimeProvider = currentTimeProvider;
        this.lastRollOverTime = this.currentTimeProvider.getCurrentTimeMillis();
        this.defaultMaxFilesToKeep = defaultMaxFilesToKeep;
    }

    public void writeEvent(T event) throws IOException {
        byte[] eventBytes = this.transform.toBytes(event);
        rollFileOverIfNeeded(eventBytes.length);
        this.eventStorage.add(eventBytes);
    }

    public void registerRollOverListener(EventsStorageListener listener) {
        if (listener != null) {
            this.rollOverListeners.add(listener);
        }
    }

    public boolean rollFileOver() throws IOException {
        boolean fileRolledOver = false;
        String targetFileName = null;
        if (!this.eventStorage.isWorkingFileEmpty()) {
            targetFileName = generateUniqueRollOverFileName();
            this.eventStorage.rollOver(targetFileName);
            CommonUtils.logControlled(this.context, 4, "Fabric", String.format(Locale.US, "generated new file %s", new Object[]{targetFileName}));
            this.lastRollOverTime = this.currentTimeProvider.getCurrentTimeMillis();
            fileRolledOver = true;
        }
        triggerRollOverOnListeners(targetFileName);
        return fileRolledOver;
    }

    private void rollFileOverIfNeeded(int newEventSizeInBytes) throws IOException {
        if (!this.eventStorage.canWorkingFileStore(newEventSizeInBytes, getMaxByteSizePerFile())) {
            CommonUtils.logControlled(this.context, 4, "Fabric", String.format(Locale.US, "session analytics events file is %d bytes, new event is %d bytes, this is over flush limit of %d, rolling it over", new Object[]{Integer.valueOf(this.eventStorage.getWorkingFileUsedSizeInBytes()), Integer.valueOf(newEventSizeInBytes), Integer.valueOf(getMaxByteSizePerFile())}));
            rollFileOver();
        }
    }

    /* Access modifiers changed, original: protected */
    public int getMaxFilesToKeep() {
        return this.defaultMaxFilesToKeep;
    }

    /* Access modifiers changed, original: protected */
    public int getMaxByteSizePerFile() {
        return 8000;
    }

    private void triggerRollOverOnListeners(String rolledOverFile) {
        for (EventsStorageListener eventStorageRollOverListener : this.rollOverListeners) {
            try {
                eventStorageRollOverListener.onRollOver(rolledOverFile);
            } catch (Exception e) {
                CommonUtils.logControlledError(this.context, "One of the roll over listeners threw an exception", e);
            }
        }
    }

    public List<File> getBatchOfFilesToSend() {
        return this.eventStorage.getBatchOfFilesToSend(1);
    }

    public void deleteSentFiles(List<File> files) {
        this.eventStorage.deleteFilesInRollOverDirectory(files);
    }

    public void deleteAllEventsFiles() {
        this.eventStorage.deleteFilesInRollOverDirectory(this.eventStorage.getAllFilesInRollOverDirectory());
        this.eventStorage.deleteWorkingFile();
    }

    public void deleteOldestInRollOverIfOverMax() {
        List<File> allFiles = this.eventStorage.getAllFilesInRollOverDirectory();
        int maxFiles = getMaxFilesToKeep();
        if (allFiles.size() > maxFiles) {
            int numberOfFilesToDelete = allFiles.size() - maxFiles;
            CommonUtils.logControlled(this.context, String.format(Locale.US, "Found %d files in  roll over directory, this is greater than %d, deleting %d oldest files", new Object[]{Integer.valueOf(allFiles.size()), Integer.valueOf(maxFiles), Integer.valueOf(numberOfFilesToDelete)}));
            TreeSet<FileWithTimestamp> sortedFiles = new TreeSet(new C46001());
            for (File file : allFiles) {
                sortedFiles.add(new FileWithTimestamp(file, parseCreationTimestampFromFileName(file.getName())));
            }
            ArrayList<File> toDelete = new ArrayList();
            Iterator it = sortedFiles.iterator();
            while (it.hasNext()) {
                toDelete.add(((FileWithTimestamp) it.next()).file);
                if (toDelete.size() == numberOfFilesToDelete) {
                    break;
                }
            }
            this.eventStorage.deleteFilesInRollOverDirectory(toDelete);
        }
    }

    public long parseCreationTimestampFromFileName(String fileName) {
        long j = 0;
        String[] fileNameParts = fileName.split("_");
        if (fileNameParts.length != 3) {
            return j;
        }
        try {
            return Long.valueOf(fileNameParts[2]).longValue();
        } catch (NumberFormatException e) {
            return j;
        }
    }
}
