package com.spring.policy;

import java.util.Collection;
import java.util.LinkedList;

public class RejectingPolicy {

    /* --- Members --- */

    private String policyName;
    private String filterType;
    private boolean productLevel;
    private boolean inclusive;
    private Collection<RejectedLibrary> rejectedLibraries;

    /* --- Constructor --- */

    public RejectingPolicy() {
        rejectedLibraries = new LinkedList<>();
    }

    /* --- Getters / Setters --- */

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public Collection<RejectedLibrary> getRejectedLibraries() {
        return rejectedLibraries;
    }

    public void setRejectedLibraries(Collection<RejectedLibrary> rejectedLibraries) {
        this.rejectedLibraries = rejectedLibraries;
    }
}

