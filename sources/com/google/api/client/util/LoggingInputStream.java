package com.google.api.client.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingInputStream extends FilterInputStream {
    private final LoggingByteArrayOutputStream logStream;

    public LoggingInputStream(InputStream inputStream, Logger logger, Level loggingLevel, int contentLoggingLimit) {
        super(inputStream);
        this.logStream = new LoggingByteArrayOutputStream(logger, loggingLevel, contentLoggingLimit);
    }

    public int read() throws IOException {
        int read = super.read();
        this.logStream.write(read);
        return read;
    }

    public int read(byte[] b, int off, int len) throws IOException {
        int read = super.read(b, off, len);
        if (read > 0) {
            this.logStream.write(b, off, read);
        }
        return read;
    }

    public void close() throws IOException {
        this.logStream.close();
        super.close();
    }
}
