package com.company.security.truststore_manager.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CertificateDetailsResponse {

    private String alias;
    private String serialNumber;
    private String subject;
    private String issuer;
    private String validFrom;
    private String validTo;

}

