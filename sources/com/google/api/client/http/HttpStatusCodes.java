package com.google.api.client.http;

public class HttpStatusCodes {
    public static boolean isSuccess(int statusCode) {
        return statusCode >= 200 && statusCode < 300;
    }

    public static boolean isRedirect(int statusCode) {
        switch (statusCode) {
            case 301:
            case 302:
            case 303:
            case 307:
                return true;
            default:
                return false;
        }
    }
}
