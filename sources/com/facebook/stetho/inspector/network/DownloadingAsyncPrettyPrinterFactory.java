package com.facebook.stetho.inspector.network;

import com.facebook.stetho.common.Util;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import javax.annotation.Nullable;

public abstract class DownloadingAsyncPrettyPrinterFactory implements AsyncPrettyPrinterFactory {

    protected class MatchResult {
        private final PrettyPrinterDisplayType mDisplayType;
        private final String mSchemaUri;

        public MatchResult(String schemaUri, PrettyPrinterDisplayType displayType) {
            this.mSchemaUri = schemaUri;
            this.mDisplayType = displayType;
        }

        public String getSchemaUri() {
            return this.mSchemaUri;
        }

        public PrettyPrinterDisplayType getDisplayType() {
            return this.mDisplayType;
        }
    }

    private static class Request implements Callable<String> {
        private URL url;

        public Request(URL url) {
            this.url = url;
        }

        public String call() throws IOException {
            HttpURLConnection connection = (HttpURLConnection) HttpInstrumentation.openConnection(this.url.openConnection());
            int statusCode = connection.getResponseCode();
            if (statusCode != 200) {
                throw new IOException("Got status code: " + statusCode + " while downloading " + "schema with url: " + this.url.toString());
            }
            InputStream urlStream = connection.getInputStream();
            try {
                String readAsUTF8 = Util.readAsUTF8(urlStream);
                return readAsUTF8;
            } finally {
                urlStream.close();
            }
        }
    }

    public abstract void doPrint(PrintWriter printWriter, InputStream inputStream, String str) throws IOException;

    @Nullable
    public abstract MatchResult matchAndParseHeader(String str, String str2);

    public AsyncPrettyPrinter getInstance(String headerName, String headerValue) {
        final MatchResult result = matchAndParseHeader(headerName, headerValue);
        if (result == null) {
            return null;
        }
        URL schemaURL = parseURL(result.getSchemaUri());
        if (schemaURL == null) {
            return getErrorAsyncPrettyPrinter(headerName, headerValue);
        }
        ExecutorService executorService = AsyncPrettyPrinterExecutorHolder.getExecutorService();
        if (executorService == null) {
            return null;
        }
        final Future<String> response = executorService.submit(new Request(schemaURL));
        return new AsyncPrettyPrinter() {
            /* JADX WARNING: Removed duplicated region for block: B:8:0x0036 A:{ExcHandler: InterruptedException (r1_1 'e' java.util.concurrent.ExecutionException), Splitter:B:0:0x0000} */
            /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
            /* JADX WARNING: Missing block: B:8:0x0036, code skipped:
            r1 = move-exception;
     */
            /* JADX WARNING: Missing block: B:9:0x0037, code skipped:
            com.facebook.stetho.inspector.network.DownloadingAsyncPrettyPrinterFactory.access$000(r6, r7, "Encountered spurious interrupt while downloading schema for pretty printing: " + r1.getMessage());
     */
            /* JADX WARNING: Missing block: B:16:?, code skipped:
            return;
     */
            public void printTo(java.io.PrintWriter r6, java.io.InputStream r7) throws java.io.IOException {
                /*
                r5 = this;
                r3 = r1;	 Catch:{ ExecutionException -> 0x000e, InterruptedException -> 0x0036 }
                r2 = r3.get();	 Catch:{ ExecutionException -> 0x000e, InterruptedException -> 0x0036 }
                r2 = (java.lang.String) r2;	 Catch:{ ExecutionException -> 0x000e, InterruptedException -> 0x0036 }
                r3 = com.facebook.stetho.inspector.network.DownloadingAsyncPrettyPrinterFactory.this;	 Catch:{ InterruptedException -> 0x0036, ExecutionException -> 0x0053 }
                r3.doPrint(r6, r7, r2);	 Catch:{ InterruptedException -> 0x0036, ExecutionException -> 0x0053 }
            L_0x000d:
                return;
            L_0x000e:
                r1 = move-exception;
                r0 = r1.getCause();	 Catch:{ InterruptedException -> 0x0036, ExecutionException -> 0x0053 }
                r3 = java.io.IOException.class;
                r3 = r3.isInstance(r0);	 Catch:{ InterruptedException -> 0x0036, ExecutionException -> 0x0053 }
                if (r3 == 0) goto L_0x0052;
            L_0x001b:
                r3 = new java.lang.StringBuilder;	 Catch:{ InterruptedException -> 0x0036, ExecutionException -> 0x0053 }
                r3.<init>();	 Catch:{ InterruptedException -> 0x0036, ExecutionException -> 0x0053 }
                r4 = "Cannot successfully download schema: ";
                r3 = r3.append(r4);	 Catch:{ InterruptedException -> 0x0036, ExecutionException -> 0x0053 }
                r4 = r1.getMessage();	 Catch:{ InterruptedException -> 0x0036, ExecutionException -> 0x0053 }
                r3 = r3.append(r4);	 Catch:{ InterruptedException -> 0x0036, ExecutionException -> 0x0053 }
                r3 = r3.toString();	 Catch:{ InterruptedException -> 0x0036, ExecutionException -> 0x0053 }
                com.facebook.stetho.inspector.network.DownloadingAsyncPrettyPrinterFactory.doErrorPrint(r6, r7, r3);	 Catch:{ InterruptedException -> 0x0036, ExecutionException -> 0x0053 }
                goto L_0x000d;
            L_0x0036:
                r1 = move-exception;
                r3 = new java.lang.StringBuilder;
                r3.<init>();
                r4 = "Encountered spurious interrupt while downloading schema for pretty printing: ";
                r3 = r3.append(r4);
                r4 = r1.getMessage();
                r3 = r3.append(r4);
                r3 = r3.toString();
                com.facebook.stetho.inspector.network.DownloadingAsyncPrettyPrinterFactory.doErrorPrint(r6, r7, r3);
                goto L_0x000d;
            L_0x0052:
                throw r1;	 Catch:{ InterruptedException -> 0x0036, ExecutionException -> 0x0053 }
            L_0x0053:
                r1 = move-exception;
                r0 = r1.getCause();
                r3 = com.facebook.stetho.common.ExceptionUtil.propagate(r0);
                throw r3;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.stetho.inspector.network.DownloadingAsyncPrettyPrinterFactory$C19971.printTo(java.io.PrintWriter, java.io.InputStream):void");
            }

            public PrettyPrinterDisplayType getPrettifiedType() {
                return result.getDisplayType();
            }
        };
    }

    @Nullable
    private static URL parseURL(String uri) {
        try {
            return new URL(uri);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    private static void doErrorPrint(PrintWriter output, InputStream payload, String errorMessage) throws IOException {
        output.print(errorMessage + "\n" + Util.readAsUTF8(payload));
    }

    private static AsyncPrettyPrinter getErrorAsyncPrettyPrinter(final String headerName, final String headerValue) {
        return new AsyncPrettyPrinter() {
            public void printTo(PrintWriter output, InputStream payload) throws IOException {
                DownloadingAsyncPrettyPrinterFactory.doErrorPrint(output, payload, "[Failed to parse header: " + headerName + " : " + headerValue + " ]");
            }

            public PrettyPrinterDisplayType getPrettifiedType() {
                return PrettyPrinterDisplayType.TEXT;
            }
        };
    }
}
