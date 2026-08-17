package com.example.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    GENERIC_ERROR(
            "DEMO-0001",
            "error.generic"),
    HTTP_MEDIATYPE_NOT_SUPPORTED(
            "DEMO-0002",
            "error.http-media-type-not-supported"),
    HTTP_MESSAGE_NOT_WRITABLE(
            "DEMO-0003",
            "error.http-message-not-writable"),
    HTTP_MEDIA_TYPE_NOT_ACCEPTABLE(
            "DEMO-0004",
            "error.http-media-type-not-acceptable"),
    JSON_PARSE_ERROR(
            "DEMO-0005",
            "error.json-parse"),
    HTTP_MESSAGE_NOT_READABLE(
            "DEMO-0006",
            "error.http-message-not-readable");

    private String errCode;
    private String errMsgKey;
}
