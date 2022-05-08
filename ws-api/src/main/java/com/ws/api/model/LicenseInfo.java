package com.ws.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.Collection;

public class LicenseInfo {

    /* --- Members --- */

    @SerializedName("library")
    private String libraryName;
    private String copyright;
    @SerializedName("name")
    private String licenseName;
    private String projectName;
    private String productName;
    private String url;
    private String homepage;
    private Collection<LicenseReferenceInfo> references;

    /* --- Constructors --- */

    public LicenseInfo() {
    }

    public LicenseInfo(String libraryName, String copyright, String licenseName, String projectName, String productName) {
        this.libraryName = libraryName;
        this.copyright = copyright;
        this.licenseName = licenseName;
        this.projectName = projectName;
        this.productName = productName;
    }

    /* --- Getters / Setters --- */

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getLicenseName() {
        return licenseName;
    }

    public void setLicenseName(String licenseName) {
        this.licenseName = licenseName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Collection<LicenseReferenceInfo> getReferences() {
        return references;
    }

    public void setReferences(Collection<LicenseReferenceInfo> references) {
        this.references = references;
    }
}
