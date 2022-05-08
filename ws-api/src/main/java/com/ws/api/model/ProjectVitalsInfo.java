package com.ws.api.model;

import java.io.Serializable;

public class ProjectVitalsInfo implements Serializable {

    /* --- Members --- */

    private String productName;
    private String projectName;
    private String projectToken;

    /* --- Constructors --- */

    public ProjectVitalsInfo() {
    }

    public ProjectVitalsInfo(String productName, String projectName, String projectToken) {
        this.productName = productName;
        this.projectName = projectName;
        this.projectToken = projectToken;
    }

    /* --- Getters / Setters --- */

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectToken() {
        return projectToken;
    }

    public void setProjectToken(String projectToken) {
        this.projectToken = projectToken;
    }
}
