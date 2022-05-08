package com.ws.api.model;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class LicenseReferenceInfo implements Serializable {

    /* --- Members --- */

    private String referenceType;
    private String reference;

    /* --- Constructors --- */

    public LicenseReferenceInfo() {
    }

    public LicenseReferenceInfo(String referenceType, String reference) {
        this.referenceType = referenceType;
        this.reference = reference;
    }

    /* --- Public methods --- */

    public boolean isValid() {
        return StringUtils.isNotBlank(reference) && StringUtils.isNotBlank(referenceType);
    }

    /* --- Getters / Setters --- */

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
