package com.google.api.client.json;

import com.google.api.client.util.ObjectParser;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sets;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class JsonObjectParser implements ObjectParser {
    private final JsonFactory jsonFactory;
    private final Set<String> wrapperKeys;

    public static class Builder {
        final JsonFactory jsonFactory;
        Collection<String> wrapperKeys = Sets.newHashSet();

        public Builder(JsonFactory jsonFactory) {
            this.jsonFactory = (JsonFactory) Preconditions.checkNotNull(jsonFactory);
        }
    }

    public JsonObjectParser(JsonFactory jsonFactory) {
        this(new Builder(jsonFactory));
    }

    protected JsonObjectParser(Builder builder) {
        this.jsonFactory = builder.jsonFactory;
        this.wrapperKeys = new HashSet(builder.wrapperKeys);
    }

    public <T> T parseAndClose(InputStream in, Charset charset, Class<T> dataClass) throws IOException {
        return parseAndClose(in, charset, (Type) dataClass);
    }

    public Object parseAndClose(InputStream in, Charset charset, Type dataType) throws IOException {
        JsonParser parser = this.jsonFactory.createJsonParser(in, charset);
        initializeParser(parser);
        return parser.parse(dataType, true);
    }

    /* JADX WARNING: Failed to extract finally block: empty outs */
    /* JADX WARNING: Missing block: B:19:?, code skipped:
            return;
     */
    private void initializeParser(com.google.api.client.json.JsonParser r8) throws java.io.IOException {
        /*
        r7 = this;
        r2 = 1;
        r3 = 0;
        r4 = r7.wrapperKeys;
        r4 = r4.isEmpty();
        if (r4 == 0) goto L_0x000b;
    L_0x000a:
        return;
    L_0x000b:
        r0 = 1;
        r4 = r7.wrapperKeys;	 Catch:{ all -> 0x0032 }
        r1 = r8.skipToKey(r4);	 Catch:{ all -> 0x0032 }
        if (r1 == 0) goto L_0x0030;
    L_0x0014:
        r4 = r8.getCurrentToken();	 Catch:{ all -> 0x0032 }
        r5 = com.google.api.client.json.JsonToken.END_OBJECT;	 Catch:{ all -> 0x0032 }
        if (r4 == r5) goto L_0x0030;
    L_0x001c:
        r3 = "wrapper key(s) not found: %s";
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x0032 }
        r5 = 0;
        r6 = r7.wrapperKeys;	 Catch:{ all -> 0x0032 }
        r4[r5] = r6;	 Catch:{ all -> 0x0032 }
        com.google.api.client.util.Preconditions.checkArgument(r2, r3, r4);	 Catch:{ all -> 0x0032 }
        r0 = 0;
        if (r0 == 0) goto L_0x000a;
    L_0x002c:
        r8.close();
        goto L_0x000a;
    L_0x0030:
        r2 = r3;
        goto L_0x001c;
    L_0x0032:
        r2 = move-exception;
        if (r0 == 0) goto L_0x0038;
    L_0x0035:
        r8.close();
    L_0x0038:
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.json.JsonObjectParser.initializeParser(com.google.api.client.json.JsonParser):void");
    }
}
