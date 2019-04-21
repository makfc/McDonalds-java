package com.google.api.client.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public interface ObjectParser {
    <T> T parseAndClose(InputStream inputStream, Charset charset, Class<T> cls) throws IOException;
}
