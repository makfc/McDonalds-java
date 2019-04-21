package p049hk.com.aisoft.easyaddrui;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: hk.com.aisoft.easyaddrui.StreamUtils */
class StreamUtils {
    StreamUtils() {
    }

    static int readAllBytes(InputStream in, byte[] buffer) throws IOException {
        int index = 0;
        while (index < buffer.length) {
            int read = in.read(buffer, index, buffer.length - index);
            if (read == -1) {
                break;
            }
            index += read;
        }
        return index;
    }

    static void readAllBytesOrFail(InputStream in, byte[] buffer) throws IOException {
        if (StreamUtils.readAllBytes(in, buffer) != buffer.length) {
            throw new EOFException(String.format("Expected %d bytes but read %d bytes.", new Object[]{Integer.valueOf(buffer.length), Integer.valueOf(StreamUtils.readAllBytes(in, buffer))}));
        }
    }
}
