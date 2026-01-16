package com.company.security.truststore_manager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

@Configuration
public class TrustStoreConfig {

    @Value("${crypto.truststore.path}")
    private String path;

    @Value("${crypto.truststore.password}")
    private String password;

    @Value("${crypto.truststore.type}")
    private String type;

    @Bean
    public KeyStore trustStore() throws Exception {
        KeyStore trustStore = KeyStore.getInstance(type);
        try (InputStream is = new FileInputStream(path)) {
            trustStore.load(is, password.toCharArray());
        }
        return trustStore;
    }
}
