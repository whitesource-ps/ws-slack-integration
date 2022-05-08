package com.ws.api.response;

import java.io.File;

public class GetLicensesZipFileResponse extends BaseApiResponse {

    /* --- Members --- */

    private File licensesZipFile;

    /* --- Constructor --- */

    public GetLicensesZipFileResponse(File licensesZipFile) {
        this.licensesZipFile = licensesZipFile;
    }

    /* --- Getters / Setters --- */

    public File getLicensesZipFile() {
        return licensesZipFile;
    }

    public void setLicensesZipFile(File licensesZipFile) {
        this.licensesZipFile = licensesZipFile;
    }
}
