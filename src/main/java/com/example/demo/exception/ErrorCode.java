package com.example.demo.exception;

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
                        "error.http-message-not-readable"),
        HTTP_REQUEST_METHOD_NOT_SUPPORTED(
                        "DEMO-0007",
                        "error.http-request-method-not-supported"),
        CONSTRAINT_VIOLATION(
                        "DEMO-0008",
                        "error.constraint-violation"),
        ILLEGAL_ARGUMENT_EXCEPTION(
                        "DEMO-0009",
                        "error.illegal-argument-exception"),
        RESOURCE_NOT_FOUND(
                        "DEMO-0010",
                        "error.resource-not-found"),
        CUSTOMER_NOT_FOUND(
                        "DEMO-0011",
                        "error.customer-not-found"),
        ITEM_NOT_FOUND(
                        "DEMO-0012",
                        "error.item-not-found"),
        GENERIC_ALREADY_EXISTS(
                        "DEMO-0013",
                        "error.generic-already-exists");

        private String errCode;
        private String errMsgKey;
}
