package com.ws.api.model;

import java.io.Serializable;

public class LibraryReferencesInfo implements Serializable {

    /* --- Members --- */

    private String url;
    private String homePage;
    private String genericPackageIndex;
    private String pomUrl;

    /* --- Constructors --- */

    public LibraryReferencesInfo() {
    }

    public LibraryReferencesInfo(String url, String homePage, String genericPackageIndex) {
        this.url = url;
        this.homePage = homePage;
        this.genericPackageIndex = genericPackageIndex;
    }

    /* --- Getters / Setters --- */

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getGenericPackageIndex() {
        return genericPackageIndex;
    }

    public void setGenericPackageIndex(String genericPackageIndex) {
        this.genericPackageIndex = genericPackageIndex;
    }

    public String getPomUrl() {
        return pomUrl;
    }

    public void setPomUrl(String pomUrl) {
        this.pomUrl = pomUrl;
    }
}
