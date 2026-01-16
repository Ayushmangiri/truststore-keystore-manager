package com.company.security.truststore_manager.controller;

import com.company.security.truststore_manager.dto.CertificateDetailsResponse;
import com.company.security.truststore_manager.service.TrustStoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    private final TrustStoreService trustStoreService;

    public StoreController(TrustStoreService trustStoreService) {
        this.trustStoreService = trustStoreService;
    }

    @GetMapping("/truststore/aliases")
    public ResponseEntity<List<String>> listTrustStoreAliases() {
        return ResponseEntity.ok(trustStoreService.listCertificates());
    }

    @GetMapping("/truststore/certificate/details/{alias}")
    public ResponseEntity<CertificateDetailsResponse> getCertificateDetails(
            @PathVariable String alias) {

        return ResponseEntity.ok(
                trustStoreService.getCertificateDetails(alias)
        );
    }

    @GetMapping("/truststore/certificate/{alias}")
    public ResponseEntity<String> getCertificate(@PathVariable String alias) {

        String subject = trustStoreService
                .getCertificate(alias)
                .getSubjectX500Principal()
                .getName();

        return ResponseEntity.ok(subject);
    }
}
