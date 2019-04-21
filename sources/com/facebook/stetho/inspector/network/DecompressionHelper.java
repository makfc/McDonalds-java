package com.facebook.stetho.inspector.network;

import com.facebook.stetho.inspector.console.CLog;
import com.facebook.stetho.inspector.protocol.module.Console.MessageLevel;
import com.facebook.stetho.inspector.protocol.module.Console.MessageSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.InflaterOutputStream;
import javax.annotation.Nullable;

public class DecompressionHelper {
    static final String DEFLATE_ENCODING = "deflate";
    static final String GZIP_ENCODING = "gzip";

    public static InputStream teeInputWithDecompression(NetworkPeerManager peerManager, String requestId, InputStream availableInputStream, OutputStream decompressedOutput, @Nullable String contentEncoding, ResponseHandler responseHandler) throws IOException {
        OutputStream output = decompressedOutput;
        CountingOutputStream decompressedCounter = null;
        if (contentEncoding != null) {
            boolean gzipEncoding = GZIP_ENCODING.equals(contentEncoding);
            boolean deflateEncoding = DEFLATE_ENCODING.equals(contentEncoding);
            if (gzipEncoding || deflateEncoding) {
                decompressedCounter = new CountingOutputStream(decompressedOutput);
                if (gzipEncoding) {
                    output = GunzippingOutputStream.create(decompressedCounter);
                } else if (deflateEncoding) {
                    output = new InflaterOutputStream(decompressedCounter);
                }
            } else {
                CLog.writeToConsole(peerManager, MessageLevel.WARNING, MessageSource.NETWORK, "Unsupported Content-Encoding in response for request #" + requestId + ": " + contentEncoding);
            }
        }
        return new ResponseHandlingInputStream(availableInputStream, requestId, output, decompressedCounter, peerManager, responseHandler);
    }
}
