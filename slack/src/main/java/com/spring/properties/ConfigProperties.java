package com.spring.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@EnableConfigurationProperties
@Component
//@ConfigurationProperties
@PropertySource("classpath:application.properties")
public class ConfigProperties {

    /* --- Members --- */

    @Value("${server.port}")
    private String serverPort;

    @Value("${slack.url}")
    private String slackUrl;

    @Value("${slack.maxResults}")
    private Integer slackMaxResults;

    @Value("${ws.url}")
    private String wssUrl;

    @Value("${ws.linkToOrgVul}")
    private boolean linkToOrgVul;
//
//    @Value("${ws.orgToken}")
//    private String orgToken;

    @Value("${ws.productToken}")
    private String productToken;

    @Value("${ws.userKey}")
    private String userKey;

    @Value("${scheduler.getSecurityAlerts}")
    private String scheduling;

    @Value("${scheduler.getSecurityAlertsEnabled}")
    private boolean schedulingSecurityAlertsEnabled;

    @Value("${vulnerability.fromDate}")
    private String fromDate;

    @Value("${vulnerability.minScore}")
    private Float minScore;

    @Value("${proxy.host}")
    private String proxyHost;

    @Value("${proxy.port}")
    private String proxyPort;

    @Value("${proxy.username}")
    private String proxyUsername;

    @Value("${proxy.password}")
    private String proxyPassword;

    /* --- Getters / Setters --- */

    public String getServerPort() {
        return serverPort;
    }

    public String getSlackUrl() {
        return slackUrl;
    }

    public String getWssUrl() {
        return wssUrl;
    }

    public boolean isWssLinkToWssApplication() {
        return linkToOrgVul;
    }

//    public String getOrgToken() {
//        return orgToken;
//    }

    public String getProductToken() {
        return productToken;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public String getProxyUsername() {
        return proxyUsername;
    }

    public String getProxyPassword() {
        return proxyPassword;
    }

    public String getUserKey() {
        return userKey;
    }

    public String getScheduling() {
        return scheduling;
    }

    public boolean isSchedulingSecurityAlertsEnabled() {
        return schedulingSecurityAlertsEnabled;
    }

    public String getFromDate() {
        return fromDate;
    }

    public Float getMinScore() {
        return minScore;
    }

    public Integer getSlackMaxResults() {
        return slackMaxResults;
    }
}
