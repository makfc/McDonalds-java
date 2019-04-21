package com.google.api.client.http;

import com.google.api.client.util.StreamingContent;
import java.io.IOException;

public interface HttpContent extends StreamingContent {
    long getLength() throws IOException;

    String getType();

    boolean retrySupported();
}
