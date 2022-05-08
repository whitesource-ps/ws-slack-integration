package com.ws.api.model;

import java.util.Collection;

public class ProjectInventoryInfo {

    /* --- Members --- */

    private ProjectVitalsInfo projectVitals;
    private Collection<LibraryInfo> libraries;

    /* --- Constructors --- */

    public ProjectInventoryInfo() {
    }

    public ProjectInventoryInfo(ProjectVitalsInfo projectVitals, Collection<LibraryInfo> libraries) {
        this.projectVitals = projectVitals;
        this.libraries = libraries;
    }

    /* --- Getters / Setters --- */

    public ProjectVitalsInfo getProjectVitals() {
        return projectVitals;
    }

    public void setProjectVitals(ProjectVitalsInfo projectVitals) {
        this.projectVitals = projectVitals;
    }

    public Collection<LibraryInfo> getLibraries() {
        return libraries;
    }

    public void setLibraries(Collection<LibraryInfo> libraries) {
        this.libraries = libraries;
    }
}
