package com.spring.policy;

import java.util.Collection;
import java.util.LinkedList;

public class PolicyRejectionModel {

    /* --- Members --- */

    private Collection<RejectingPolicy> rejectingPolicies;
    private PolicySummary summary;

    /* --- Constructor --- */

    public PolicyRejectionModel() {
        rejectingPolicies = new LinkedList<>();
    }

    /* --- Getters / Setters --- */

    public Collection<RejectingPolicy> getRejectingPolicies() {
        return rejectingPolicies;
    }

    public void setRejectingPolicies(Collection<RejectingPolicy> rejectingPolicies) {
        this.rejectingPolicies = rejectingPolicies;
    }

    public PolicySummary getSummary() {
        return summary;
    }

    public void setSummary(PolicySummary summary) {
        this.summary = summary;
    }
}
