package com.google.api.client.util;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingOutputStream extends FilterOutputStream {
    private final LoggingByteArrayOutputStream logStream;

    public LoggingOutputStream(OutputStream outputStream, Logger logger, Level loggingLevel, int contentLoggingLimit) {
        super(outputStream);
        this.logStream = new LoggingByteArrayOutputStream(logger, loggingLevel, contentLoggingLimit);
    }

    public void write(int b) throws IOException {
        this.out.write(b);
        this.logStream.write(b);
    }

    public void write(byte[] b, int off, int len) throws IOException {
        this.out.write(b, off, len);
        this.logStream.write(b, off, len);
    }

    public void close() throws IOException {
        this.logStream.close();
        super.close();
    }

    public final LoggingByteArrayOutputStream getLogStream() {
        return this.logStream;
    }
}
