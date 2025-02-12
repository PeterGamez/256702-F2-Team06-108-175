package com.mechat.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConfigurationProperties(prefix = "api.cors")
public class APIConfig implements WebMvcConfigurer {

    @Value("${api.prefix}")
    private String prefix;

    @Value("${api.cors.allowed-origins}")
    private String allowedOrigins;

    private List<String> allowedMethods;

    @Value("${api.cors.allowed-headers}")
    private String allowedHeaders;

    public List<String> getAllowedMethods() {
        return allowedMethods;
    }

    public void setAllowedMethods(List<String> allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping(prefix + "/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods(allowedMethods.toArray(new String[0]))
                .allowedHeaders(allowedHeaders);
    }
}
