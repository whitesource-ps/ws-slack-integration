package com.ws.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AgentInfo implements Serializable {

    /* --- Members --- */

    private String agent;
    private String agentVersion;
    private String agentContext;

    /* --- Constructors --- */

    public AgentInfo() {
        this.agent = "ps-slack";
        this.agentVersion = "22.5.2";
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getAgentVersion() {
        return agentVersion;
    }

    public void setAgentVersion(String agentVersion) {
        this.agentVersion = agentVersion;
    }

    public String getAgentContext() {
        return agentContext;
    }

    public void setAgentContext(String agentContext) {
        this.agentContext = agentContext;
    }
}
