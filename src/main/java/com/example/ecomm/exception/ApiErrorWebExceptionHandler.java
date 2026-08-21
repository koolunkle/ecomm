package com.example.ecomm.exception;

import java.util.Map;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.webflux.autoconfigure.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.webflux.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;

import reactor.core.publisher.Mono;

@Component
@Order(-2)
public class ApiErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

    public ApiErrorWebExceptionHandler(ApiErrorAttributes errorAttributes, ApplicationContext applicationContext,
            ServerCodecConfigurer serverCodeConfigurer) {
        super(errorAttributes, new WebProperties().getResources(), applicationContext);
        super.setMessageWriters(serverCodeConfigurer.getWriters());
        super.setMessageReaders(serverCodeConfigurer.getReaders());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        Map<String, Object> errorPropertiesMap = getErrorAttributes(request, ErrorAttributeOptions.defaults());
        Throwable throwable = getError(request);

        if (throwable instanceof ResponseStatusException responseStatusException) {
            HttpStatus status = HttpStatus.valueOf(responseStatusException.getStatusCode().value());
            return buildErrorResponse(errorPropertiesMap, throwable, ErrorCode.GENERIC_STATUS_ERROR, status);
        }

        ErrorCode errorCode = resolveErrorCode(throwable);

        return buildErrorResponse(errorPropertiesMap, throwable, errorCode, errorCode.getHttpStatus());
    }

    private ErrorCode resolveErrorCode(Throwable throwable) {
        if (throwable instanceof IllegalArgumentException || throwable instanceof DataIntegrityViolationException
                || throwable instanceof ServerWebInputException) {
            return ErrorCode.ILLEGAL_ARGUMENT_EXCEPTION;
        } else if (throwable instanceof CustomerNotFoundException) {
            return ErrorCode.CUSTOMER_NOT_FOUND;
        } else if (throwable instanceof ResourceNotFoundException) {
            return ErrorCode.RESOURCE_NOT_FOUND;
        } else if (throwable instanceof CardAlreadyExistsException) {
            return ErrorCode.CARD_ALREADY_EXISTS;
        } else if (throwable instanceof GenericAlreadyExistsException) {
            return ErrorCode.GENERIC_ALREADY_EXISTS;
        }

        return ErrorCode.GENERIC_ERROR;
    }

    private Mono<ServerResponse> buildErrorResponse(Map<String, Object> errorPropertiesMap, Throwable throwable,
            ErrorCode errorCode, HttpStatus status) {
        errorPropertiesMap.put("status", status);
        errorPropertiesMap.put("code", errorCode.getErrCode());
        errorPropertiesMap.put("error", errorCode);
        errorPropertiesMap.put("message", String.format("%s %s", errorCode.getErrMsgKey(), throwable.getMessage()));

        return ServerResponse.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorPropertiesMap));
    }
}
