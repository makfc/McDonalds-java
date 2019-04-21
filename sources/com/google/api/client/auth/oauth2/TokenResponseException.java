package com.google.api.client.auth.oauth2;

import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpResponseException.Builder;

public class TokenResponseException extends HttpResponseException {
    private final transient TokenErrorResponse details;

    TokenResponseException(Builder builder, TokenErrorResponse details) {
        super(builder);
        this.details = details;
    }

    public final TokenErrorResponse getDetails() {
        return this.details;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0055  */
    public static com.google.api.client.auth.oauth2.TokenResponseException from(com.google.api.client.json.JsonFactory r11, com.google.api.client.http.HttpResponse r12) {
        /*
        r1 = new com.google.api.client.http.HttpResponseException$Builder;
        r7 = r12.getStatusCode();
        r8 = r12.getStatusMessage();
        r9 = r12.getHeaders();
        r1.<init>(r7, r8, r9);
        com.google.api.client.util.Preconditions.checkNotNull(r11);
        r4 = 0;
        r3 = 0;
        r2 = r12.getContentType();
        r7 = r12.isSuccessStatusCode();	 Catch:{ IOException -> 0x0073 }
        if (r7 != 0) goto L_0x006e;
    L_0x0020:
        if (r2 == 0) goto L_0x006e;
    L_0x0022:
        r7 = r12.getContent();	 Catch:{ IOException -> 0x0073 }
        if (r7 == 0) goto L_0x006e;
    L_0x0028:
        r7 = "application/json; charset=UTF-8";
        r7 = com.google.api.client.http.HttpMediaType.equalsIgnoreParameters(r7, r2);	 Catch:{ IOException -> 0x0073 }
        if (r7 == 0) goto L_0x006e;
    L_0x0030:
        r7 = new com.google.api.client.json.JsonObjectParser;	 Catch:{ IOException -> 0x0073 }
        r7.<init>(r11);	 Catch:{ IOException -> 0x0073 }
        r8 = r12.getContent();	 Catch:{ IOException -> 0x0073 }
        r9 = r12.getContentCharset();	 Catch:{ IOException -> 0x0073 }
        r10 = com.google.api.client.auth.oauth2.TokenErrorResponse.class;
        r7 = r7.parseAndClose(r8, r9, r10);	 Catch:{ IOException -> 0x0073 }
        r0 = r7;
        r0 = (com.google.api.client.auth.oauth2.TokenErrorResponse) r0;	 Catch:{ IOException -> 0x0073 }
        r4 = r0;
        r3 = r4.toPrettyString();	 Catch:{ IOException -> 0x0073 }
    L_0x004b:
        r6 = com.google.api.client.http.HttpResponseException.computeMessageBuffer(r12);
        r7 = com.google.api.client.util.Strings.isNullOrEmpty(r3);
        if (r7 != 0) goto L_0x0061;
    L_0x0055:
        r7 = com.google.api.client.util.StringUtils.LINE_SEPARATOR;
        r7 = r6.append(r7);
        r7.append(r3);
        r1.setContent(r3);
    L_0x0061:
        r7 = r6.toString();
        r1.setMessage(r7);
        r7 = new com.google.api.client.auth.oauth2.TokenResponseException;
        r7.<init>(r1, r4);
        return r7;
    L_0x006e:
        r3 = r12.parseAsString();	 Catch:{ IOException -> 0x0073 }
        goto L_0x004b;
    L_0x0073:
        r5 = move-exception;
        r5.printStackTrace();
        goto L_0x004b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.auth.oauth2.TokenResponseException.from(com.google.api.client.json.JsonFactory, com.google.api.client.http.HttpResponse):com.google.api.client.auth.oauth2.TokenResponseException");
    }
}
