package io.enosix.salesdoc.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Configuration
@ConfigurationProperties(prefix = "enosix.rest")
public class RestConfig {

    private String username;
    private String password;
    private String rootUri;

    public String getRootUri() {
        return rootUri;
    }

    public RestConfig setRootUri(String rootUri) {
        this.rootUri = rootUri;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public RestConfig setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RestConfig setPassword(String password) {
        this.password = password;
        return this;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .rootUri(rootUri)
                .defaultHeader(HttpHeaders.AUTHORIZATION,
                        String.format("basic %s", HttpHeaders.encodeBasicAuth(username, password, StandardCharsets.UTF_8)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
