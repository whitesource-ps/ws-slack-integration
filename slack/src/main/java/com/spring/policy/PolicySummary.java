package com.spring.policy;

import java.io.Serializable;

public class PolicySummary implements Serializable {

    /* --- Members --- */

    private int totalRejectedLibraries;

    /* --- Constructor --- */

    public PolicySummary() {
    }

    /* --- Getters / Setters --- */

    public int getTotalRejectedLibraries() {
        return totalRejectedLibraries;
    }

    public void setTotalRejectedLibraries(int totalRejectedLibraries) {
        this.totalRejectedLibraries = totalRejectedLibraries;
    }
}
