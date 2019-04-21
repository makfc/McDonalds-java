package com.google.api.client.http;

import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StringUtils;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.io.IOException;

public class HttpResponseException extends IOException {
    private final String content;
    private final transient HttpHeaders headers;
    private final int statusCode;
    private final String statusMessage;

    public static class Builder {
        String content;
        HttpHeaders headers;
        String message;
        int statusCode;
        String statusMessage;

        public Builder(int statusCode, String statusMessage, HttpHeaders headers) {
            setStatusCode(statusCode);
            setStatusMessage(statusMessage);
            setHeaders(headers);
        }

        public Builder(HttpResponse response) {
            this(response.getStatusCode(), response.getStatusMessage(), response.getHeaders());
            try {
                this.content = response.parseAsString();
                if (this.content.length() == 0) {
                    this.content = null;
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            StringBuilder builder = HttpResponseException.computeMessageBuffer(response);
            if (this.content != null) {
                builder.append(StringUtils.LINE_SEPARATOR).append(this.content);
            }
            this.message = builder.toString();
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setStatusCode(int statusCode) {
            Preconditions.checkArgument(statusCode >= 0);
            this.statusCode = statusCode;
            return this;
        }

        public Builder setStatusMessage(String statusMessage) {
            this.statusMessage = statusMessage;
            return this;
        }

        public Builder setHeaders(HttpHeaders headers) {
            this.headers = (HttpHeaders) Preconditions.checkNotNull(headers);
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }
    }

    public HttpResponseException(HttpResponse response) {
        this(new Builder(response));
    }

    protected HttpResponseException(Builder builder) {
        super(builder.message);
        this.statusCode = builder.statusCode;
        this.statusMessage = builder.statusMessage;
        this.headers = builder.headers;
        this.content = builder.content;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    public static StringBuilder computeMessageBuffer(HttpResponse response) {
        StringBuilder builder = new StringBuilder();
        int statusCode = response.getStatusCode();
        if (statusCode != 0) {
            builder.append(statusCode);
        }
        String statusMessage = response.getStatusMessage();
        if (statusMessage != null) {
            if (statusCode != 0) {
                builder.append(SafeJsonPrimitive.NULL_CHAR);
            }
            builder.append(statusMessage);
        }
        return builder;
    }
}
