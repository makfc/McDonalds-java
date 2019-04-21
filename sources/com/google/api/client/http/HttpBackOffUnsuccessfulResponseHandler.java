package com.google.api.client.http;

import com.google.api.client.util.BackOff;
import com.google.api.client.util.BackOffUtils;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Sleeper;
import java.io.IOException;

@Beta
public class HttpBackOffUnsuccessfulResponseHandler implements HttpUnsuccessfulResponseHandler {
    private final BackOff backOff;
    private BackOffRequired backOffRequired;
    private Sleeper sleeper;

    @Beta
    public interface BackOffRequired {
        public static final BackOffRequired ALWAYS = new C27571();
        public static final BackOffRequired ON_SERVER_ERROR = new C27582();

        /* renamed from: com.google.api.client.http.HttpBackOffUnsuccessfulResponseHandler$BackOffRequired$1 */
        static class C27571 implements BackOffRequired {
            C27571() {
            }

            public boolean isRequired(HttpResponse response) {
                return true;
            }
        }

        /* renamed from: com.google.api.client.http.HttpBackOffUnsuccessfulResponseHandler$BackOffRequired$2 */
        static class C27582 implements BackOffRequired {
            C27582() {
            }

            public boolean isRequired(HttpResponse response) {
                return response.getStatusCode() / 100 == 5;
            }
        }

        boolean isRequired(HttpResponse httpResponse);
    }

    public final boolean handleResponse(HttpRequest request, HttpResponse response, boolean supportsRetry) throws IOException {
        boolean z = false;
        if (!supportsRetry || !this.backOffRequired.isRequired(response)) {
            return z;
        }
        try {
            return BackOffUtils.next(this.sleeper, this.backOff);
        } catch (InterruptedException e) {
            return z;
        }
    }
}
