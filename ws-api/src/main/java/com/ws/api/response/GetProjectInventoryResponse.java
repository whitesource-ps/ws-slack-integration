package com.ws.api.response;

import com.ws.api.model.LibraryInfo;
import com.ws.api.model.ProjectVitalsInfo;

import java.util.Collection;

public class GetProjectInventoryResponse extends BaseApiResponse {

    /* --- Members --- */

    private ProjectVitalsInfo projectVitals;
    private Collection<LibraryInfo> libraries;

    /* --- Constructors --- */

    public GetProjectInventoryResponse() {
    }

    public GetProjectInventoryResponse(ProjectVitalsInfo projectVitals, Collection<LibraryInfo> libraries) {
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
