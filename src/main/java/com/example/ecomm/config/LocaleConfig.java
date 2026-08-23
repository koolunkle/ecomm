package com.example.ecomm.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.i18n.AcceptHeaderLocaleContextResolver;
import org.springframework.web.server.i18n.LocaleContextResolver;

// WebFlux는 LocaleContextResolver를 자동 등록하지 않으므로 Accept-Language 헤더 기반으로 직접 등록 (기본 로케일: 한국어)
@Configuration
public class LocaleConfig {

    @Bean
    public LocaleContextResolver localeContextResolver() {
        AcceptHeaderLocaleContextResolver localeContextResolver = new AcceptHeaderLocaleContextResolver();
        localeContextResolver.setDefaultLocale(Locale.KOREAN);
        return localeContextResolver;
    }
}
