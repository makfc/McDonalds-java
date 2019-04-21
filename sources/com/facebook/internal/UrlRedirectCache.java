package com.facebook.internal;

import android.content.Context;
import com.facebook.LoggingBehavior;
import com.facebook.internal.FileLruCache.Limits;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

class UrlRedirectCache {
    private static final String REDIRECT_CONTENT_TAG = (TAG + "_Redirect");
    static final String TAG = UrlRedirectCache.class.getSimpleName();
    private static volatile FileLruCache urlRedirectCache;

    UrlRedirectCache() {
    }

    static synchronized FileLruCache getCache(Context context) throws IOException {
        FileLruCache fileLruCache;
        synchronized (UrlRedirectCache.class) {
            if (urlRedirectCache == null) {
                urlRedirectCache = new FileLruCache(context.getApplicationContext(), TAG, new Limits());
            }
            fileLruCache = urlRedirectCache;
        }
        return fileLruCache;
    }

    static URI getRedirectedUri(Context context, URI uri) {
        InputStreamReader reader;
        Throwable th;
        if (uri == null) {
            return null;
        }
        String uriString = uri.toString();
        InputStreamReader reader2 = null;
        try {
            FileLruCache cache = getCache(context);
            boolean redirectExists = false;
            while (true) {
                try {
                    reader = reader2;
                    InputStream stream = cache.get(uriString, REDIRECT_CONTENT_TAG);
                    if (stream == null) {
                        break;
                    }
                    redirectExists = true;
                    reader2 = new InputStreamReader(stream);
                    char[] buffer = new char[128];
                    StringBuilder urlBuilder = new StringBuilder();
                    while (true) {
                        int bufferLength = reader2.read(buffer, 0, buffer.length);
                        if (bufferLength <= 0) {
                            break;
                        }
                        urlBuilder.append(buffer, 0, bufferLength);
                    }
                    Utility.closeQuietly(reader2);
                    uriString = urlBuilder.toString();
                } catch (URISyntaxException e) {
                    reader2 = reader;
                    Utility.closeQuietly(reader2);
                    return null;
                } catch (IOException e2) {
                    reader2 = reader;
                    Utility.closeQuietly(reader2);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    reader2 = reader;
                    Utility.closeQuietly(reader2);
                    throw th;
                }
            }
            if (redirectExists) {
                URI uri2 = new URI(uriString);
                Utility.closeQuietly(reader);
                return uri2;
            }
            Utility.closeQuietly(reader);
            reader2 = reader;
            return null;
        } catch (URISyntaxException e3) {
            Utility.closeQuietly(reader2);
            return null;
        } catch (IOException e4) {
            Utility.closeQuietly(reader2);
            return null;
        } catch (Throwable th3) {
            th = th3;
            Utility.closeQuietly(reader2);
            throw th;
        }
    }

    static void cacheUriRedirect(Context context, URI fromUri, URI toUri) {
        if (fromUri != null && toUri != null) {
            OutputStream redirectStream = null;
            try {
                redirectStream = getCache(context).openPutStream(fromUri.toString(), REDIRECT_CONTENT_TAG);
                redirectStream.write(toUri.toString().getBytes());
            } catch (IOException e) {
            } finally {
                Utility.closeQuietly(redirectStream);
            }
        }
    }

    static void clearCache(Context context) {
        try {
            getCache(context).clearCache();
        } catch (IOException e) {
            Logger.log(LoggingBehavior.CACHE, 5, TAG, "clearCache failed " + e.getMessage());
        }
    }
}
