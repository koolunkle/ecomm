package com.example.ecomm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * H2 웹 콘솔 관련 설정.
 *
 * @param port H2 웹 콘솔이 바인딩할 포트.
 */
@ConfigurationProperties(prefix = "h2.console")
public record H2ConsoleProperties(@DefaultValue("8081") Integer port) {
}
