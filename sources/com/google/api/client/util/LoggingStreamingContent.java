package com.google.api.client.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class LoggingStreamingContent implements StreamingContent {
    private final StreamingContent content;
    private final int contentLoggingLimit;
    private final Logger logger;
    private final Level loggingLevel;

    public LoggingStreamingContent(StreamingContent content, Logger logger, Level loggingLevel, int contentLoggingLimit) {
        this.content = content;
        this.logger = logger;
        this.loggingLevel = loggingLevel;
        this.contentLoggingLimit = contentLoggingLimit;
    }

    public void writeTo(OutputStream out) throws IOException {
        LoggingOutputStream loggableOutputStream = new LoggingOutputStream(out, this.logger, this.loggingLevel, this.contentLoggingLimit);
        try {
            this.content.writeTo(loggableOutputStream);
            out.flush();
        } finally {
            loggableOutputStream.getLogStream().close();
        }
    }
}
