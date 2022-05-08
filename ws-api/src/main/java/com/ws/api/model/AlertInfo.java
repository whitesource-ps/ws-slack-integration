package com.ws.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AlertInfo implements Serializable {

    /* --- Members --- */

    private Vulnerability vulnerability;
    private LibraryInfo library;
    private String description;
    private String date;
    @SerializedName("creation_date")
    private String creationDate;
    private Integer projectId;
    private String projectName;
    private String projectToken;

    /* --- Constructors --- */

    public AlertInfo() {
    }

    /* --- Getters / Setters --- */

    public Vulnerability getVulnerability() {
        return vulnerability;
    }

    public void setVulnerability(Vulnerability vulnerability) {
        this.vulnerability = vulnerability;
    }

    public LibraryInfo getLibrary() {
        return library;
    }

    public void setLibrary(LibraryInfo library) {
        this.library = library;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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
