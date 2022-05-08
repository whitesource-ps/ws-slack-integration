package com.ws.api.response;

import com.ws.api.model.LicenseInfo;

import java.util.Collection;
import java.util.LinkedList;

public class GetProjectDueDiligenceResponse extends BaseApiResponse {

    /* --- Members --- */

    private Collection<LicenseInfo> licenses;

    /* --- Constructors --- */

    public GetProjectDueDiligenceResponse() {
        licenses = new LinkedList<>();
    }

    /* --- Getters / Setters --- */

    public Collection<LicenseInfo> getLicenses() {
        return licenses;
    }

    public void setLicenses(Collection<LicenseInfo> licenses) {
        this.licenses = licenses;
    }
}
