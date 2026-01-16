package com.company.security.truststore_manager.service;

import org.springframework.stereotype.Service;

import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Service
public class TrustStoreService {

    private final KeyStore trustStore;

    public TrustStoreService(KeyStore trustStore) {
        this.trustStore = trustStore;
    }

    public List<String> listCertificates() throws Exception {
        List<String> aliases = new ArrayList<>();

        Enumeration<String> enumeration = trustStore.aliases();
        while (enumeration.hasMoreElements()) {
            aliases.add(enumeration.nextElement());
        }

        return aliases;
    }

    public X509Certificate getCertificate(String alias) throws Exception {
        return (X509Certificate) trustStore.getCertificate(alias);
    }
}

