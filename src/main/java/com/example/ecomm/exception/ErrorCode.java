package com.example.ecomm.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

        GENERIC_ERROR(
                        "ECOMM-0001",
                        "error.generic",
                        HttpStatus.INTERNAL_SERVER_ERROR),
        HTTP_MEDIATYPE_NOT_SUPPORTED(
                        "ECOMM-0002",
                        "error.http-media-type-not-supported",
                        HttpStatus.UNSUPPORTED_MEDIA_TYPE),
        HTTP_MESSAGE_NOT_WRITABLE(
                        "ECOMM-0003",
                        "error.http-message-not-writable",
                        HttpStatus.INTERNAL_SERVER_ERROR),
        HTTP_MEDIA_TYPE_NOT_ACCEPTABLE(
                        "ECOMM-0004",
                        "error.http-media-type-not-acceptable",
                        HttpStatus.NOT_ACCEPTABLE),
        JSON_PARSE_ERROR(
                        "ECOMM-0005",
                        "error.json-parse",
                        HttpStatus.BAD_REQUEST),
        HTTP_MESSAGE_NOT_READABLE(
                        "ECOMM-0006",
                        "error.http-message-not-readable",
                        HttpStatus.BAD_REQUEST),
        HTTP_REQUEST_METHOD_NOT_SUPPORTED(
                        "ECOMM-0007",
                        "error.http-request-method-not-supported",
                        HttpStatus.METHOD_NOT_ALLOWED),
        CONSTRAINT_VIOLATION(
                        "ECOMM-0008",
                        "error.constraint-violation",
                        HttpStatus.BAD_REQUEST),
        ILLEGAL_ARGUMENT_EXCEPTION(
                        "ECOMM-0009",
                        "error.illegal-argument-exception",
                        HttpStatus.BAD_REQUEST),
        RESOURCE_NOT_FOUND(
                        "ECOMM-0010",
                        "error.resource-not-found",
                        HttpStatus.NOT_FOUND),
        CUSTOMER_NOT_FOUND(
                        "ECOMM-0011",
                        "error.customer-not-found",
                        HttpStatus.NOT_FOUND),
        ITEM_NOT_FOUND(
                        "ECOMM-0012",
                        "error.item-not-found",
                        HttpStatus.NOT_FOUND),
        GENERIC_ALREADY_EXISTS(
                        "ECOMM-0013",
                        "error.generic-already-exists",
                        HttpStatus.CONFLICT),
        // 원본 ResponseStatusException의 상태를 우선 사용하며, 해당 값은 폴백용으로만 활용
        GENERIC_STATUS_ERROR(
                        "ECOMM-0014",
                        "error.generic-status",
                        HttpStatus.INTERNAL_SERVER_ERROR),
        CARD_ALREADY_EXISTS(
                        "ECOMM-0015",
                        "error.card-already-exists",
                        HttpStatus.CONFLICT);

        private String errCode;
        private String errMsgKey;
        private HttpStatus httpStatus;
}
