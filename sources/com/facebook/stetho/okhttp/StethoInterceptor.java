package com.facebook.stetho.okhttp;

import com.facebook.stetho.inspector.network.DefaultResponseHandler;
import com.facebook.stetho.inspector.network.NetworkEventReporter;
import com.facebook.stetho.inspector.network.NetworkEventReporter.InspectorRequest;
import com.facebook.stetho.inspector.network.NetworkEventReporter.InspectorResponse;
import com.facebook.stetho.inspector.network.NetworkEventReporterImpl;
import com.facebook.stetho.inspector.network.RequestBodyHelper;
import com.newrelic.agent.android.instrumentation.okhttp2.OkHttp2Instrumentation;
import com.squareup.okhttp.Connection;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Interceptor.Chain;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.Response.Builder;
import com.squareup.okhttp.ResponseBody;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

public class StethoInterceptor implements Interceptor {
    private final NetworkEventReporter mEventReporter = NetworkEventReporterImpl.get();
    private final AtomicInteger mNextRequestId = new AtomicInteger(0);

    private static class ForwardingResponseBody extends ResponseBody {
        private final ResponseBody mBody;
        private final BufferedSource mInterceptedSource;

        public ForwardingResponseBody(ResponseBody body, InputStream interceptedStream) {
            this.mBody = body;
            this.mInterceptedSource = Okio.buffer(Okio.source(interceptedStream));
        }

        public MediaType contentType() {
            return this.mBody.contentType();
        }

        public long contentLength() {
            return this.mBody.contentLength();
        }

        public BufferedSource source() {
            return this.mInterceptedSource;
        }
    }

    private static class OkHttpInspectorRequest implements InspectorRequest {
        private final Request mRequest;
        private RequestBodyHelper mRequestBodyHelper;
        private final String mRequestId;

        public OkHttpInspectorRequest(String requestId, Request request, RequestBodyHelper requestBodyHelper) {
            this.mRequestId = requestId;
            this.mRequest = request;
            this.mRequestBodyHelper = requestBodyHelper;
        }

        /* renamed from: id */
        public String mo16730id() {
            return this.mRequestId;
        }

        public String friendlyName() {
            return null;
        }

        @Nullable
        public Integer friendlyNameExtra() {
            return null;
        }

        public String url() {
            return this.mRequest.urlString();
        }

        public String method() {
            return this.mRequest.method();
        }

        @Nullable
        public byte[] body() throws IOException {
            RequestBody body = this.mRequest.body();
            if (body == null) {
                return null;
            }
            BufferedSink bufferedSink = Okio.buffer(Okio.sink(this.mRequestBodyHelper.createBodySink(firstHeaderValue("Content-Encoding"))));
            try {
                body.writeTo(bufferedSink);
                return this.mRequestBodyHelper.getDisplayBody();
            } finally {
                bufferedSink.close();
            }
        }

        public int headerCount() {
            return this.mRequest.headers().size();
        }

        public String headerName(int index) {
            return this.mRequest.headers().name(index);
        }

        public String headerValue(int index) {
            return this.mRequest.headers().value(index);
        }

        @Nullable
        public String firstHeaderValue(String name) {
            return this.mRequest.header(name);
        }
    }

    private static class OkHttpInspectorResponse implements InspectorResponse {
        private final Connection mConnection;
        private final Request mRequest;
        private final String mRequestId;
        private final Response mResponse;

        public OkHttpInspectorResponse(String requestId, Request request, Response response, Connection connection) {
            this.mRequestId = requestId;
            this.mRequest = request;
            this.mResponse = response;
            this.mConnection = connection;
        }

        public String requestId() {
            return this.mRequestId;
        }

        public String url() {
            return this.mRequest.urlString();
        }

        public int statusCode() {
            return this.mResponse.code();
        }

        public String reasonPhrase() {
            return this.mResponse.message();
        }

        public boolean connectionReused() {
            return false;
        }

        public int connectionId() {
            return this.mConnection.hashCode();
        }

        public boolean fromDiskCache() {
            return this.mResponse.cacheResponse() != null;
        }

        public int headerCount() {
            return this.mResponse.headers().size();
        }

        public String headerName(int index) {
            return this.mResponse.headers().name(index);
        }

        public String headerValue(int index) {
            return this.mResponse.headers().value(index);
        }

        @Nullable
        public String firstHeaderValue(String name) {
            return this.mResponse.header(name);
        }
    }

    public Response intercept(Chain chain) throws IOException {
        String requestId = String.valueOf(this.mNextRequestId.getAndIncrement());
        Request request = chain.request();
        RequestBodyHelper requestBodyHelper = null;
        if (this.mEventReporter.isEnabled()) {
            requestBodyHelper = new RequestBodyHelper(this.mEventReporter, requestId);
            this.mEventReporter.requestWillBeSent(new OkHttpInspectorRequest(requestId, request, requestBodyHelper));
        }
        try {
            Response response = chain.proceed(request);
            if (!this.mEventReporter.isEnabled()) {
                return response;
            }
            if (requestBodyHelper != null && requestBodyHelper.hasBody()) {
                requestBodyHelper.reportDataSent();
            }
            this.mEventReporter.responseHeadersReceived(new OkHttpInspectorResponse(requestId, request, response, chain.connection()));
            ResponseBody body = response.body();
            MediaType contentType = null;
            InputStream responseStream = null;
            if (body != null) {
                contentType = body.contentType();
                responseStream = body.byteStream();
            }
            responseStream = this.mEventReporter.interpretResponseStream(requestId, contentType != null ? contentType.toString() : null, response.header("Content-Encoding"), responseStream, new DefaultResponseHandler(this.mEventReporter, requestId));
            if (responseStream == null) {
                return response;
            }
            Builder newBuilder = !(response instanceof Builder) ? response.newBuilder() : OkHttp2Instrumentation.newBuilder((Builder) response);
            ForwardingResponseBody forwardingResponseBody = new ForwardingResponseBody(body, responseStream);
            return (!(newBuilder instanceof Builder) ? newBuilder.body(forwardingResponseBody) : OkHttp2Instrumentation.body(newBuilder, forwardingResponseBody)).build();
        } catch (IOException e) {
            if (this.mEventReporter.isEnabled()) {
                this.mEventReporter.httpExchangeFailed(requestId, e.toString());
            }
            throw e;
        }
    }
}
