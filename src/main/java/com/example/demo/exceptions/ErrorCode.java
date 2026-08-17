package com.example.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    GENERIC_ERROR(
            "DEMO-0001",
            "시스템이 요청을 완료할 수 없습니다, 시스템 지원 담당자에게 연락하세요."),
    HTTP_MEDIATYPE_NOT_SUPPORTED(
            "DEMO-0002",
            "요청한 미디어 타입을 지원하지 않습니다. 'Content-Type' 헤더 값으로 application/json이나 application/xml를 사용하세요."),
    HTTP_MESSAGE_NOT_WRITABLE(
            "DEMO-0003",
            "Missing 'Accept' 헤더가 없습니다. 'Accept' 헤더를 추가하세요."),
    HTTP_MEDIA_TYPE_NOT_ACCEPTABLE(
            "DEMO-0004",
            "요청한 'Accept' 헤더 값을 지원하지 않습니다. 'Accept' 값으로 application/json 이나 application/xml를 사용하세요."),
    JSON_PARSE_ERROR(
            "DEMO-0005",
            "요청 페이로드가 유효한 JSON 객체여야 합니다."),
    HTTP_MESSAGE_NOT_READABLE(
            "DEMO-0006",
            "요청 페이로드는 'Content-Type'에 따라 유효한 JSON 또는 XML 객체여야 합니다.");

    private String errCode;
    private String errMsgKey;
}
