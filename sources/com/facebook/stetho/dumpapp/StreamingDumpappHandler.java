package com.facebook.stetho.dumpapp;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.AbstractHttpEntity;

public class StreamingDumpappHandler extends DumpappHandler {

    private class DumpappHttpEntity extends AbstractHttpEntity {
        private final Dumper mDumper;
        private final InputStream mInput;
        private final HttpRequest mRequest;

        DumpappHttpEntity(HttpRequest request, Dumper dumper, InputStream input) {
            this.mRequest = request;
            this.mDumper = dumper;
            this.mInput = input;
        }

        public boolean isRepeatable() {
            return false;
        }

        public long getContentLength() {
            return -1;
        }

        public InputStream getContent() throws IOException, IllegalStateException {
            throw new UnsupportedOperationException();
        }

        public boolean isStreaming() {
            return true;
        }

        public void writeTo(OutputStream output) throws IOException {
            StreamingDumpappHandler.writeTo(this.mRequest, this.mDumper, this.mInput, output);
        }
    }

    public StreamingDumpappHandler(Context context, Dumper dumper) {
        super(context, dumper);
    }

    /* Access modifiers changed, original: protected */
    public HttpEntity getResponseEntity(HttpRequest request, InputStream bufferedInput, HttpResponse response) throws IOException {
        DumpappHttpEntity entity = new DumpappHttpEntity(request, getDumper(), bufferedInput);
        entity.setChunked(true);
        entity.setContentType("application/octet-stream");
        return entity;
    }

    private static void writeTo(HttpRequest request, Dumper dumper, InputStream input, OutputStream output) throws IOException {
        StreamFramer framer = new StreamFramer(output);
        try {
            framer.writeExitCode(dumper.dump(input, framer.getStdout(), framer.getStderr(), DumpappHandler.getArgs(request)));
        } catch (DumpappOutputBrokenException e) {
        } finally {
            framer.close();
        }
    }
}
