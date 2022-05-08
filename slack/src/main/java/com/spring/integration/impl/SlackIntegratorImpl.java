package com.spring.integration.impl;

import com.spring.integration.BaseIntegration;
import com.spring.model.VulnerabilityInfo;
import com.spring.policy.PolicyRejectionModel;
import com.spring.policy.RejectedLibrary;
import com.spring.policy.RejectingPolicy;
import com.spring.properties.ConfigProperties;
import com.spring.response.SlackResponse;
import com.spring.slack.AbstractSlackBlock;
import com.spring.slack.SlackBlock;
import com.spring.slack.SlackBlockId;
import com.spring.slack.SlackBlockType;
import com.spring.slack.SlackButtonElement;
import com.spring.slack.SlackElement;
import com.spring.slack.SlackText;
import com.spring.slack.SlackTextType;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

@Component
public class SlackIntegratorImpl implements BaseIntegration {

    /* --- Static members --- */

    private static final String HIGH_SEVERITY = "high";
    private static final String MEDIUM_SEVERITY = "medium";
    private static final String LOW_SEVERITY = "low";

    private int DEFAULT_MAX_RESULTS;

    private static final String WSS_URL_BUTTON = "wssUrlButton";
    private static final String WHITESOURCE_ACCOUNT_LABEL = "WhiteSource Account";

    /* --- Members --- */

    @Autowired
    private ConfigProperties properties;

    @PostConstruct
    public void init() {
        DEFAULT_MAX_RESULTS = properties.getSlackMaxResults() == null ? 5 : properties.getSlackMaxResults();
    }

    /* --- Public methods --- */

    @Override
    public SlackResponse createNewPublishedVulnerabilitiesResponse(Collection<VulnerabilityInfo> vulnerabilities, String productToken,
                                                                   String productName, String wssUrl, Map<String, Collection<Integer>> libraryOccurrencesMap) {
        SlackResponse response = new SlackResponse();
        if (CollectionUtils.isNotEmpty(vulnerabilities)) {
            // create initial blocks
            Collection<AbstractSlackBlock> blocks = new LinkedList<>();
            blocks.addAll(SlackBlock.createVulnerabilitiesInitialBlocks(productName, vulnerabilities.size()));

            blocks.add(SlackBlock.createDividerBlock());

            if (vulnerabilities.size() > DEFAULT_MAX_RESULTS) {
                blocks.add(new SlackBlock(SlackBlockType.SECTION, new SlackText(SlackTextType.MRKDWN,
                        "*Showing " + DEFAULT_MAX_RESULTS + " results out of " + vulnerabilities.size() + ". " +
                                "To review all new vulnerabilities, please go to your WhiteSource account.*")));

                // add button
                Collection<SlackButtonElement> actionElements = new LinkedList<>();
                actionElements.add(new SlackButtonElement(SlackTextType.BUTTON, wssUrl, new SlackText(SlackTextType.PLAIN_TEXT, WHITESOURCE_ACCOUNT_LABEL)));
                SlackBlockId actionBlock = new SlackBlockId(WSS_URL_BUTTON, actionElements, SlackBlockType.ACTIONS);
                blocks.add(actionBlock);
                blocks.add(SlackBlock.createDividerBlock());
            }

            int counter = 0;
            for (VulnerabilityInfo vulnerability : vulnerabilities) {
                SlackBlock vulnerabilityBlock = new SlackBlock();
                vulnerabilityBlock.setType(SlackBlockType.CONTEXT);
                Collection<SlackElement> elements = new LinkedList<>();

                String imageUrl = getSeverityImageUrl(vulnerability.getSeverity());
                elements.add(new SlackElement(SlackTextType.IMAGE, imageUrl, vulnerability.getSeverity()));
                StringBuilder sb = new StringBuilder();

                sb.append("*<");
                if (properties.isWssLinkToWssApplication()) {                   // Link to CVE in the org
                    sb.append(wssUrl)
                        .append("/Wss/WSS.html#!securityVulnerability;id=")
                        .append(vulnerability.getCve())
                        .append(";productToken=")
                        .append(productToken);
                } else {                                                        // Link to WS Vulnerability DB
                    sb.append(vulnerability.getFullVulnerabilityUrl());
                }
                sb.append("|")
                    .append(vulnerability.getCve())
                    .append(">*\n")
                    .append("*")
                    .append(vulnerability.getVulnerableLibraries().size())
                    .append(" affected libraries*\n")
                    .append("*Publish Date: ").append(vulnerability.getPublishDate())
                    .append("*\n")
                    .append("*Found in ")
                    .append(vulnerability.getProjectIds().size())
                    .append(" Projects*");
                elements.add(new SlackElement(SlackTextType.MRKDWN, sb.toString()));
                vulnerabilityBlock.setElements(elements);

                // old implementation
//                String imageUrl = getSeverityImageUrl(vulnerability.getSeverity());
//                // severity image element
//                elements.add(new SlackElement(SlackTextType.IMAGE, imageUrl, vulnerability.getSeverity()));
//                elements.add(SlackElement.createWhitespaceElement());
//                elements.add(SlackElement.createWhitespaceElement());
//
//                // vulnerability element
//                elements.add(SlackElement.createVulnerabilityElement(vulnerability));
//                elements.add(SlackElement.createWhitespaceElement());
//                elements.add(SlackElement.createWhitespaceElement());
//
//                // library element
//                elements.add(SlackElement.createLibraryElement(vulnerability.getVulnerableLibrary()));
//                elements.add(SlackElement.createWhitespaceElement());
//                elements.add(SlackElement.createWhitespaceElement());
//
//                // occurrences element
//                elements.add(SlackElement.createOccurrencesElement());
//                vulnerabilityBlock.setElements(elements);
                blocks.add(vulnerabilityBlock);
                if (++counter == DEFAULT_MAX_RESULTS) {
                    break;
                }
                blocks.add(SlackBlock.createDividerBlock());
            }
            response.setBlocks(blocks);
        }

        // TODO remove
//        if (response.isNotBlank()) {
//            String bla = new Gson().toJson(response);
//            bla = bla.replace("\\\\t", "\\t");
//        }
        return response;
    }

    @Override
    public SlackResponse createPolicyViolationsResponse(PolicyRejectionModel policyRejectionModel, String wssUrl, String project, String build) {
        SlackResponse response = new SlackResponse();
        // create initial blocks
        Collection<RejectingPolicy> rejectingPolicies = policyRejectionModel.getRejectingPolicies();
        if (CollectionUtils.isNotEmpty(rejectingPolicies)) {
            Collection<AbstractSlackBlock> blocks = new LinkedList<>();
            int totalRejectedLibraries = policyRejectionModel.getSummary().getTotalRejectedLibraries();
            blocks.addAll(SlackBlock.createPoliciesInitialBlocks(totalRejectedLibraries));
            blocks.add(SlackBlock.createDividerBlock());

            // add CI/CD blocks
            if (StringUtils.isNotBlank(project) && StringUtils.isNotBlank(build)) {
                Collection<String> projectNames = new HashSet<>();
                if (StringUtils.isBlank(project)) {
                    for (RejectingPolicy policy : rejectingPolicies) {
                        for (RejectedLibrary rejectedLibrary : policy.getRejectedLibraries()) {
                            projectNames.addAll(rejectedLibrary.getProjects());
                        }
                    }
                }

                blocks.add(SlackBlock.createBuildJobBlock(project, build, projectNames));
                blocks.add(SlackBlock.createDividerBlock());
            }

            if (totalRejectedLibraries > DEFAULT_MAX_RESULTS) {
                blocks.add(new SlackBlock(SlackBlockType.SECTION, new SlackText(SlackTextType.MRKDWN,
                        "*Showing " + DEFAULT_MAX_RESULTS + " results out of " +
                                totalRejectedLibraries + ". To review all policy violations, please go to your WhiteSource account.*")));

                // add button
                Collection<SlackButtonElement> actionElements = new LinkedList<>();
                actionElements.add(new SlackButtonElement(SlackTextType.BUTTON, wssUrl, new SlackText(SlackTextType.PLAIN_TEXT, WHITESOURCE_ACCOUNT_LABEL)));
                SlackBlockId actionBlock = new SlackBlockId(WSS_URL_BUTTON, actionElements, SlackBlockType.ACTIONS);
                blocks.add(actionBlock);
                blocks.add(SlackBlock.createDividerBlock());
            }

            int counter = 0;
            boolean stop = false;
            for (RejectingPolicy policy : rejectingPolicies) {
                for (RejectedLibrary library : policy.getRejectedLibraries()) {
                    SlackBlock policyBlock = new SlackBlock();
                    policyBlock.setType(SlackBlockType.CONTEXT);

                    Collection<SlackElement> elements = new LinkedList<>();
                    StringBuilder sb = new StringBuilder();
                    sb.append(":red_circle: *Policy:* ").append("*").append(policy.getPolicyName()).append("*\n");
                    sb.append("*Violating library: <").append(library.getLink()).append("|")
                            .append(library.getName()).append(">*\n").append("*Found in ").append(library.getProjects().size()).append(" projects*");
                    elements.add(new SlackElement(SlackTextType.MRKDWN, sb.toString()));
                    policyBlock.setElements(elements);
                    blocks.add(policyBlock);
                    if (++counter == DEFAULT_MAX_RESULTS) {
                        stop = true;
                        break;
                    }
                    if (stop) {
                        break;
                    }
                    blocks.add(SlackBlock.createDividerBlock());
                }
                response.setBlocks(blocks);
            }

        }

        // TODO remove
//        if (response.isNotBlank()) {
//            String bla = new Gson().toJson(response);
//            bla = bla.replace("\\\\t", "\\t");
//            System.out.println("bla");
//        }
        return response;
    }

    /* --- Private methods --- */

    private static String getSeverityImageUrl(String severity) {
        switch (severity) {
            case HIGH_SEVERITY:
                return "https://i.imgur.com/7h75Aox.png";
            case MEDIUM_SEVERITY:
                return "https://i.imgur.com/MtPw8GH.png";
            case LOW_SEVERITY:
                return "https://i.imgur.com/4ZJPTpS.png";
            default:
                break;
        }
        return "";
    }
}
