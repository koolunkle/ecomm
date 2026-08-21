package com.example.ecomm.hateoas;

import org.springframework.http.server.PathContainer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;

public interface HateoasSupport {

    default UriComponentsBuilder getUriComponentBuilder(ServerWebExchange exchange) {
        if (exchange == null) {
            return UriComponentsBuilder.fromPath("/");
        }

        ServerHttpRequest request = exchange.getRequest();
        PathContainer contextPath = request.getPath().contextPath();

        return UriComponentsBuilder.fromUri(request.getURI())
                .replacePath(contextPath.toString())
                .replaceQuery("");
    }
}
