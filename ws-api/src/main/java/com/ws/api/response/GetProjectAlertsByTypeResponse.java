package com.ws.api.response;

import com.ws.api.model.AlertInfo;

import java.util.Collection;
import java.util.LinkedList;

public class GetProjectAlertsByTypeResponse extends BaseApiResponse {

    /* --- Members --- */

    private Collection<AlertInfo> alerts;

    /* --- Constructors --- */

    public GetProjectAlertsByTypeResponse() {
        alerts = new LinkedList<>();
    }

    /* --- Getters / Setters --- */

    public Collection<AlertInfo> getAlerts() {
        return alerts;
    }

    public void setAlerts(Collection<AlertInfo> alerts) {
        this.alerts = alerts;
    }
}
