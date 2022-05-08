package com.spring.service;

import com.spring.integration.impl.SlackIntegratorImpl;
import com.spring.model.VulnerabilityInfo;
import com.spring.policy.PolicyRejectionModel;
import com.spring.properties.ConfigProperties;
import com.spring.response.SlackResponse;
import com.spring.utils.HttpUtils;
import com.ws.api.ApiRequestFactory;
import com.ws.api.exception.ApiException;
import com.ws.api.model.AlertInfo;
import com.ws.api.model.ProductTags;
import com.ws.api.model.Vulnerability;
import com.ws.api.model.VulnerabilityFix;
import com.ws.api.response.GetOrgAlertsByTypeResponse;
import com.ws.api.response.GetOrganizationDetailsResponse;
import com.ws.api.response.GetProductAlertsByTypeResponse;
import com.ws.api.response.GetProductTagsResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
@EnableScheduling
public class SlackService {

    /* --- Static members --- */

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /* --- Members --- */

    @Autowired
    private ConfigProperties properties;

    @Autowired
    private SlackIntegratorImpl slackIntegrator;

    /* --- Public methods --- */

    @Scheduled(cron = "${scheduler.getSecurityAlerts}")
    public void getSecurityAlerts() throws IOException, ApiException {
        if (properties.isSchedulingSecurityAlertsEnabled()) {
            System.out.println("***** Starting scheduled task for newly published vulnerabilities *****");
            String fromDate = StringUtils.isNotBlank(properties.getFromDate()) ? properties.getFromDate() : getTodayDate();
            Float minScore = properties.getMinScore() != null ? properties.getMinScore() : 0;
            String wssUrl = properties.getWssUrl();
            String productToken = properties.getProductToken();
            //String orgToken = properties.getOrgToken();
            String userKey = properties.getUserKey();

            //GetOrgAlertsByTypeResponse response = ApiRequestFactory.getOrgAlertsByType(orgToken, wssUrl, fromDate);
            GetProductAlertsByTypeResponse response = ApiRequestFactory.getProductAlertsByType(productToken, wssUrl, fromDate);
            //GetOrganizationDetailsResponse organizationDetails = ApiRequestFactory.getOrganizationDetails(orgToken, wssUrl, userKey);
            GetProductTagsResponse productTagsResponse = ApiRequestFactory.getProductTags(productToken, wssUrl, userKey);
            Collection<ProductTags> productTags = productTagsResponse.getProductTags();
            Map<String, Collection<Integer>> vulnerabilityOccurrencesMap = new HashMap<>();
            Collection<VulnerabilityInfo> vulnerabilities = processResponse(response, fromDate, minScore);
//            SlackResponse slackResponse = slackIntegrator.createNewPublishedVulnerabilitiesResponse(vulnerabilities, orgToken, organizationDetails.getOrgName(), wssUrl,
//                    vulnerabilityOccurrencesMap);
            SlackResponse slackResponse = slackIntegrator.createNewPublishedVulnerabilitiesResponse(vulnerabilities, productToken, productTags.stream().findFirst().get().getName(), wssUrl,
                    vulnerabilityOccurrencesMap);
            if (slackResponse.isNotBlank()) {
                HttpUtils.sendHTTPRequest(slackResponse, properties.getSlackUrl());
            } else {
                System.out.println("No new security vulnerabilities were found.");
            }
            System.out.println("***** Finished scheduled task for newly published vulnerabilities *****");
        } else {
            System.out.println("Newly published vulnerabilities scheduling is not enabled... aborting.");
        }
    }

    public void checkForPolicyViolations(PolicyRejectionModel policyRejectionModel, String project, String build) throws IOException {
        SlackResponse slackResponse = slackIntegrator.createPolicyViolationsResponse(policyRejectionModel, properties.getWssUrl(), project, build);
        if (slackResponse.isNotBlank()) {
            HttpUtils.sendHTTPRequest(slackResponse, properties.getSlackUrl());
            System.out.println("***** Finished policy violation checks *****");
        } else {
            System.out.println("No policy violations were found.");
        }
    }

    /* --- Private methods --- */

    private Collection<VulnerabilityInfo> processResponse(GetProductAlertsByTypeResponse response, String fromDate,
                                                          Float minScore) {
        Map<String, VulnerabilityInfo> cveToVulnerabilityMap = new HashMap<>();
        if (response != null) {
            Collection<AlertInfo> alerts = response.getAlerts();
            if (CollectionUtils.isNotEmpty(alerts)) {
                for (AlertInfo alert : alerts) {
                    Vulnerability vulnerability = alert.getVulnerability();
                    if (afterFromDate(vulnerability.getPublishDate(), fromDate) && vulnerability.getScore() >= minScore) {
                        VulnerabilityInfo info = cveToVulnerabilityMap.get(vulnerability.getName());
                        if (info == null) {
                            VulnerabilityFix topFix = vulnerability.getTopFix();
                            String fixResolution = "";
                            if (topFix != null) {
                                fixResolution = topFix.getFixResolution();
                            }
                            info = new VulnerabilityInfo(vulnerability.getName(), vulnerability.getSeverity(), String.valueOf(vulnerability.getScore()),
                                    vulnerability.getPublishDate(), vulnerability.getDescription(), fixResolution);
                        }
                        info.getProjectIds().add(alert.getProjectId());
                        info.getVulnerableLibraries().add(alert.getLibrary());
                        cveToVulnerabilityMap.put(vulnerability.getName(), info);
                    }
                }
            }
        }
        return cveToVulnerabilityMap.values();
    }

    private boolean afterFromDate(String publishDate, String fromDate) {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT);
        try {
            Date publish = format.parse(publishDate);
            Date from = format.parse(fromDate);
            return publish.after(from) || publish.equals(from);
        } catch (ParseException e) {
            return false;
        }
    }

    private String getTodayDate() {
        ZonedDateTime today = ZonedDateTime.now();
        return DateTimeFormatter.ofPattern(DATE_FORMAT).format(today);
    }
}
