package com.google.api.client.auth.oauth2;

import com.google.api.client.auth.oauth2.Credential.AccessMethod;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.util.Data;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class BearerToken {
    static final Pattern INVALID_TOKEN_ERROR = Pattern.compile("\\s*error\\s*=\\s*\"?invalid_token\"?");

    static final class AuthorizationHeaderAccessMethod implements AccessMethod {
        AuthorizationHeaderAccessMethod() {
        }

        public void intercept(HttpRequest request, String accessToken) throws IOException {
            HttpHeaders headers = request.getHeaders();
            String valueOf = String.valueOf("Bearer ");
            String valueOf2 = String.valueOf(accessToken);
            headers.setAuthorization(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        }

        public String getAccessTokenFromRequest(HttpRequest request) {
            List<String> authorizationAsList = request.getHeaders().getAuthorizationAsList();
            if (authorizationAsList != null) {
                for (String header : authorizationAsList) {
                    if (header.startsWith("Bearer ")) {
                        return header.substring("Bearer ".length());
                    }
                }
            }
            return null;
        }
    }

    static final class FormEncodedBodyAccessMethod implements AccessMethod {
        FormEncodedBodyAccessMethod() {
        }

        public void intercept(HttpRequest request, String accessToken) throws IOException {
            Preconditions.checkArgument(!"GET".equals(request.getRequestMethod()), "HTTP GET method is not supported");
            getData(request).put("access_token", accessToken);
        }

        public String getAccessTokenFromRequest(HttpRequest request) {
            Object bodyParam = getData(request).get("access_token");
            return bodyParam == null ? null : bodyParam.toString();
        }

        private static Map<String, Object> getData(HttpRequest request) {
            return Data.mapOf(UrlEncodedContent.getContent(request).getData());
        }
    }

    static final class QueryParameterAccessMethod implements AccessMethod {
        QueryParameterAccessMethod() {
        }

        public void intercept(HttpRequest request, String accessToken) throws IOException {
            request.getUrl().set("access_token", (Object) accessToken);
        }

        public String getAccessTokenFromRequest(HttpRequest request) {
            Object param = request.getUrl().get("access_token");
            return param == null ? null : param.toString();
        }
    }

    public static AccessMethod authorizationHeaderAccessMethod() {
        return new AuthorizationHeaderAccessMethod();
    }
}
