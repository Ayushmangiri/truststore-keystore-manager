package com.company.security.truststore_manager.service;

import com.company.security.truststore_manager.dto.CertificateDetailsResponse;
import jakarta.annotation.PostConstruct;
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

    public List<String> listCertificates() {
        try {
            List<String> aliases = new ArrayList<>();
            Enumeration<String> enumeration = trustStore.aliases();
            while (enumeration.hasMoreElements()) {
                aliases.add(enumeration.nextElement());
            }
            return aliases;
        } catch (Exception e) {
            throw new RuntimeException("Failed to list truststore certificates", e);
        }
    }

    public X509Certificate getCertificate(String alias) {
        try {
            X509Certificate cert =
                    (X509Certificate) trustStore.getCertificate(alias);

            if (cert == null) {
                throw new RuntimeException("Certificate not found for alias: " + alias);
            }
            return cert;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch certificate", e);
        }
    }

    public CertificateDetailsResponse getCertificateDetails(String alias) {
        try {
            X509Certificate cert = getCertificate(alias);

            CertificateDetailsResponse response = new CertificateDetailsResponse();
            response.setAlias(alias);
            response.setSerialNumber(cert.getSerialNumber().toString());
            response.setSubject(cert.getSubjectX500Principal().getName());
            response.setIssuer(cert.getIssuerX500Principal().getName());
            response.setValidFrom(cert.getNotBefore().toString());
            response.setValidTo(cert.getNotAfter().toString());

            return response;
        } catch (Exception e) {
            throw new RuntimeException("Failed to read certificate details", e);
        }
    }

    @PostConstruct
    public void logCertificates() {
        try {
            Enumeration<String> aliases = trustStore.aliases();
            while (aliases.hasMoreElements()) {
                String alias = aliases.nextElement();
                X509Certificate cert =
                        (X509Certificate) trustStore.getCertificate(alias);

                System.out.println("Loaded certificate alias: " + alias);
                System.out.println("Subject: " + cert.getSubjectX500Principal());
                System.out.println("Serial: " + cert.getSerialNumber());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
