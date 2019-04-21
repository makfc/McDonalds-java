package com.google.api.client.http;

import com.google.api.client.util.ArrayValueMap;
import com.google.api.client.util.Base64;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.GenericData.Flags;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StringUtils;
import com.google.api.client.util.Throwables;
import com.google.api.client.util.Types;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpHeaders extends GenericData {
    @Key("Accept-Encoding")
    private List<String> acceptEncoding = new ArrayList(Collections.singleton("gzip"));
    @Key("WWW-Authenticate")
    private List<String> authenticate;
    @Key("Authorization")
    private List<String> authorization;
    @Key("Content-Encoding")
    private List<String> contentEncoding;
    @Key("Content-Length")
    private List<Long> contentLength;
    @Key("Content-Type")
    private List<String> contentType;
    @Key("If-Match")
    private List<String> ifMatch;
    @Key("If-Modified-Since")
    private List<String> ifModifiedSince;
    @Key("If-None-Match")
    private List<String> ifNoneMatch;
    @Key("If-Range")
    private List<String> ifRange;
    @Key("If-Unmodified-Since")
    private List<String> ifUnmodifiedSince;
    @Key("Location")
    private List<String> location;
    @Key("User-Agent")
    private List<String> userAgent;

    private static class HeaderParsingFakeLevelHttpRequest extends LowLevelHttpRequest {
        private final ParseHeaderState state;
        private final HttpHeaders target;

        HeaderParsingFakeLevelHttpRequest(HttpHeaders target, ParseHeaderState state) {
            this.target = target;
            this.state = state;
        }

        public void addHeader(String name, String value) {
            this.target.parseHeader(name, value, this.state);
        }

        public LowLevelHttpResponse execute() throws IOException {
            throw new UnsupportedOperationException();
        }
    }

    private static final class ParseHeaderState {
        final ArrayValueMap arrayValueMap;
        final ClassInfo classInfo;
        final List<Type> context;
        final StringBuilder logger;

        public ParseHeaderState(HttpHeaders headers, StringBuilder logger) {
            this.context = Arrays.asList(new Type[]{headers.getClass()});
            this.classInfo = ClassInfo.m7509of(clazz, true);
            this.logger = logger;
            this.arrayValueMap = new ArrayValueMap(headers);
        }

        /* Access modifiers changed, original: 0000 */
        public void finish() {
            this.arrayValueMap.setValues();
        }
    }

    public HttpHeaders() {
        super(EnumSet.of(Flags.IGNORE_CASE));
    }

    public HttpHeaders clone() {
        return (HttpHeaders) super.clone();
    }

    public HttpHeaders set(String fieldName, Object value) {
        return (HttpHeaders) super.set(fieldName, value);
    }

    public HttpHeaders setAcceptEncoding(String acceptEncoding) {
        this.acceptEncoding = getAsList(acceptEncoding);
        return this;
    }

    public final List<String> getAuthorizationAsList() {
        return this.authorization;
    }

    public HttpHeaders setAuthorization(String authorization) {
        return setAuthorization(getAsList(authorization));
    }

    public HttpHeaders setAuthorization(List<String> authorization) {
        this.authorization = authorization;
        return this;
    }

    public HttpHeaders setContentEncoding(String contentEncoding) {
        this.contentEncoding = getAsList(contentEncoding);
        return this;
    }

    public HttpHeaders setContentLength(Long contentLength) {
        this.contentLength = getAsList(contentLength);
        return this;
    }

    public final String getContentType() {
        return (String) getFirstHeaderValue(this.contentType);
    }

    public HttpHeaders setContentType(String contentType) {
        this.contentType = getAsList(contentType);
        return this;
    }

    public HttpHeaders setIfModifiedSince(String ifModifiedSince) {
        this.ifModifiedSince = getAsList(ifModifiedSince);
        return this;
    }

    public HttpHeaders setIfMatch(String ifMatch) {
        this.ifMatch = getAsList(ifMatch);
        return this;
    }

    public HttpHeaders setIfNoneMatch(String ifNoneMatch) {
        this.ifNoneMatch = getAsList(ifNoneMatch);
        return this;
    }

    public HttpHeaders setIfUnmodifiedSince(String ifUnmodifiedSince) {
        this.ifUnmodifiedSince = getAsList(ifUnmodifiedSince);
        return this;
    }

    public HttpHeaders setIfRange(String ifRange) {
        this.ifRange = getAsList(ifRange);
        return this;
    }

    public final String getLocation() {
        return (String) getFirstHeaderValue(this.location);
    }

    public final String getUserAgent() {
        return (String) getFirstHeaderValue(this.userAgent);
    }

    public HttpHeaders setUserAgent(String userAgent) {
        this.userAgent = getAsList(userAgent);
        return this;
    }

    public final List<String> getAuthenticateAsList() {
        return this.authenticate;
    }

    public HttpHeaders setBasicAuthentication(String username, String password) {
        String valueOf = String.valueOf(String.valueOf((String) Preconditions.checkNotNull(username)));
        String valueOf2 = String.valueOf(String.valueOf((String) Preconditions.checkNotNull(password)));
        valueOf = "Basic ";
        valueOf2 = String.valueOf(Base64.encodeBase64String(StringUtils.getBytesUtf8(new StringBuilder((valueOf.length() + 1) + valueOf2.length()).append(valueOf).append(":").append(valueOf2).toString())));
        return setAuthorization(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    private static void addHeader(Logger logger, StringBuilder logbuf, StringBuilder curlbuf, LowLevelHttpRequest lowLevelHttpRequest, String name, Object value, Writer writer) throws IOException {
        if (value != null && !Data.isNull(value)) {
            String stringValue = toStringValue(value);
            String loggedStringValue = stringValue;
            if (("Authorization".equalsIgnoreCase(name) || "Cookie".equalsIgnoreCase(name)) && (logger == null || !logger.isLoggable(Level.ALL))) {
                loggedStringValue = "<Not Logged>";
            }
            if (logbuf != null) {
                logbuf.append(name).append(": ");
                logbuf.append(loggedStringValue);
                logbuf.append(StringUtils.LINE_SEPARATOR);
            }
            if (curlbuf != null) {
                curlbuf.append(" -H '").append(name).append(": ").append(loggedStringValue).append("'");
            }
            if (lowLevelHttpRequest != null) {
                lowLevelHttpRequest.addHeader(name, stringValue);
            }
            if (writer != null) {
                writer.write(name);
                writer.write(": ");
                writer.write(stringValue);
                writer.write("\r\n");
            }
        }
    }

    private static String toStringValue(Object headerValue) {
        return headerValue instanceof Enum ? FieldInfo.m7510of((Enum) headerValue).getName() : headerValue.toString();
    }

    static void serializeHeaders(HttpHeaders headers, StringBuilder logbuf, StringBuilder curlbuf, Logger logger, LowLevelHttpRequest lowLevelHttpRequest) throws IOException {
        serializeHeaders(headers, logbuf, curlbuf, logger, lowLevelHttpRequest, null);
    }

    public static void serializeHeadersForMultipartRequests(HttpHeaders headers, StringBuilder logbuf, Logger logger, Writer writer) throws IOException {
        serializeHeaders(headers, logbuf, null, logger, null, writer);
    }

    static void serializeHeaders(HttpHeaders headers, StringBuilder logbuf, StringBuilder curlbuf, Logger logger, LowLevelHttpRequest lowLevelHttpRequest, Writer writer) throws IOException {
        HashSet<String> headerNames = new HashSet();
        for (Entry<String, Object> headerEntry : headers.entrySet()) {
            String name = (String) headerEntry.getKey();
            Preconditions.checkArgument(headerNames.add(name), "multiple headers of the same name (headers are case insensitive): %s", name);
            Object value = headerEntry.getValue();
            if (value != null) {
                String displayName = name;
                FieldInfo fieldInfo = headers.getClassInfo().getFieldInfo(name);
                if (fieldInfo != null) {
                    displayName = fieldInfo.getName();
                }
                Class<? extends Object> valueClass = value.getClass();
                if ((value instanceof Iterable) || valueClass.isArray()) {
                    for (Object repeatedValue : Types.iterableOf(value)) {
                        addHeader(logger, logbuf, curlbuf, lowLevelHttpRequest, displayName, repeatedValue, writer);
                    }
                } else {
                    addHeader(logger, logbuf, curlbuf, lowLevelHttpRequest, displayName, value, writer);
                }
            }
        }
        if (writer != null) {
            writer.flush();
        }
    }

    public final void fromHttpResponse(LowLevelHttpResponse response, StringBuilder logger) throws IOException {
        clear();
        ParseHeaderState state = new ParseHeaderState(this, logger);
        int headerCount = response.getHeaderCount();
        for (int i = 0; i < headerCount; i++) {
            parseHeader(response.getHeaderName(i), response.getHeaderValue(i), state);
        }
        state.finish();
    }

    private <T> T getFirstHeaderValue(List<T> internalValue) {
        return internalValue == null ? null : internalValue.get(0);
    }

    private <T> List<T> getAsList(T passedValue) {
        if (passedValue == null) {
            return null;
        }
        List<T> result = new ArrayList();
        result.add(passedValue);
        return result;
    }

    public final void fromHttpHeaders(HttpHeaders headers) {
        try {
            ParseHeaderState state = new ParseHeaderState(this, null);
            serializeHeaders(headers, null, null, null, new HeaderParsingFakeLevelHttpRequest(this, state));
            state.finish();
        } catch (IOException ex) {
            throw Throwables.propagate(ex);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void parseHeader(String headerName, String headerValue, ParseHeaderState state) {
        List<Type> context = state.context;
        ClassInfo classInfo = state.classInfo;
        ArrayValueMap arrayValueMap = state.arrayValueMap;
        StringBuilder logger = state.logger;
        if (logger != null) {
            String valueOf = String.valueOf(String.valueOf(headerName));
            String valueOf2 = String.valueOf(String.valueOf(headerValue));
            logger.append(new StringBuilder((valueOf.length() + 2) + valueOf2.length()).append(valueOf).append(": ").append(valueOf2).toString()).append(StringUtils.LINE_SEPARATOR);
        }
        FieldInfo fieldInfo = classInfo.getFieldInfo(headerName);
        if (fieldInfo != null) {
            Type type = Data.resolveWildcardTypeOrTypeVariable(context, fieldInfo.getGenericType());
            if (Types.isArray(type)) {
                Class<?> rawArrayComponentType = Types.getRawArrayComponentType(context, Types.getArrayComponentType(type));
                arrayValueMap.put(fieldInfo.getField(), rawArrayComponentType, parseValue(rawArrayComponentType, context, headerValue));
                return;
            } else if (Types.isAssignableToOrFrom(Types.getRawArrayComponentType(context, type), Iterable.class)) {
                Collection<Object> collection = (Collection) fieldInfo.getValue(this);
                if (collection == null) {
                    collection = Data.newCollectionInstance(type);
                    fieldInfo.setValue(this, collection);
                }
                collection.add(parseValue(type == Object.class ? null : Types.getIterableParameter(type), context, headerValue));
                return;
            } else {
                fieldInfo.setValue(this, parseValue(type, context, headerValue));
                return;
            }
        }
        ArrayList<String> listValue = (ArrayList) get(headerName);
        if (listValue == null) {
            listValue = new ArrayList();
            set(headerName, (Object) listValue);
        }
        listValue.add(headerValue);
    }

    private static Object parseValue(Type valueType, List<Type> context, String value) {
        return Data.parsePrimitiveValue(Data.resolveWildcardTypeOrTypeVariable(context, valueType), value);
    }
}
