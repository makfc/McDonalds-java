package com.facebook.stetho.dumpapp;

import android.content.Context;
import android.net.Uri;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.server.SecureHttpRequestHandler;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HttpContext;

public abstract class DumpappHandler extends SecureHttpRequestHandler {
    private static final String QUERY_PARAM_ARGV = "argv";
    private static final String RESPONSE_HEADER_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    private final Dumper mDumper;

    public abstract HttpEntity getResponseEntity(HttpRequest httpRequest, InputStream inputStream, HttpResponse httpResponse) throws IOException;

    public DumpappHandler(Context context, Dumper dumper) {
        super(context);
        this.mDumper = dumper;
    }

    /* Access modifiers changed, original: protected */
    public Dumper getDumper() {
        return this.mDumper;
    }

    /* Access modifiers changed, original: protected */
    public void handleSecured(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
        response.setEntity(getResponseEntity(request, bufferInput(request), response));
        response.addHeader(RESPONSE_HEADER_ALLOW_ORIGIN, "*");
        response.setStatusCode(200);
    }

    private static InputStream bufferInput(HttpRequest request) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        Util.copy(getCallerInput(request), buffer, new byte[256]);
        return new ByteArrayInputStream(buffer.toByteArray());
    }

    private static InputStream getCallerInput(HttpRequest request) throws IOException {
        if (request instanceof HttpEntityEnclosingRequest) {
            HttpEntity entity = ((HttpEntityEnclosingRequest) request).getEntity();
            if (entity != null) {
                return entity.getContent();
            }
        }
        return new ByteArrayInputStream(new byte[0]);
    }

    protected static String[] getArgs(HttpRequest request) {
        List<String> argsList = Uri.parse(request.getRequestLine().getUri()).getQueryParameters(QUERY_PARAM_ARGV);
        return (String[]) argsList.toArray(new String[argsList.size()]);
    }
}
