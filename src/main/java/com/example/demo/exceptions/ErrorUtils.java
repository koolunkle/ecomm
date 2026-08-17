package com.example.demo.exceptions;

public class ErrorUtils {

    private ErrorUtils() {
    }

    public static Error createError(
            final String errMsgKey,
            final String errCode,
            final Integer httpStatusCode,
            final String url,
            final String reqMethod) {
        return new Error(errCode, errMsgKey, httpStatusCode, url, reqMethod);
    }
}
