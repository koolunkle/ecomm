package com.example.ecomm.exception;

import java.util.Map;

import org.jspecify.annotations.Nullable;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.webflux.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class ApiErrorAttributes extends DefaultErrorAttributes {

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    private String message = ErrorCode.GENERIC_ERROR.getErrMsgKey();

    @Override
    public Map<String, @Nullable Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        var attributes = super.getErrorAttributes(request, options);

        attributes.put("status", status);
        attributes.put("message", message);
        attributes.put("code", ErrorCode.GENERIC_ERROR.getErrCode());

        return attributes;
    }
}
