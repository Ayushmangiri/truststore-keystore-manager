package com.company.security.truststore_manager.controller;

import com.company.security.truststore_manager.dto.CertificateDetailsResponse;
import com.company.security.truststore_manager.service.TrustStoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    private final TrustStoreService trustStoreService;

    public StoreController(TrustStoreService trustStoreService) {
        this.trustStoreService = trustStoreService;
    }

    @GetMapping("/truststore/aliases")
    public List<String> listTrustStoreAliases() {
        return trustStoreService.listCertificates();
    }

    @GetMapping("/truststore/certificate/details/{alias}")
    public CertificateDetailsResponse getCertificateDetails(
            @PathVariable String alias) {

        return trustStoreService.getCertificateDetails(alias);
    }

    @GetMapping("/truststore/certificate/{alias}")
    public String getCertificate(@PathVariable String alias) {
        return trustStoreService
                .getCertificate(alias)
                .getSubjectX500Principal()
                .getName();
    }
}
