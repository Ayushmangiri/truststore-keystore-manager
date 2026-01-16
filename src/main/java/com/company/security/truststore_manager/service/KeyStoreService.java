package com.company.security.truststore_manager.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.KeyStore;
import java.security.PrivateKey;

@Service
public class KeyStoreService {

    private final KeyStore keyStore;

    @Value("${crypto.keystore.password}")
    private String password;

    public KeyStoreService(KeyStore keyStore) {
        this.keyStore = keyStore;
    }

    public PrivateKey getPrivateKey(String alias) throws Exception {
        return (PrivateKey) keyStore.getKey(alias, password.toCharArray());
    }
}
