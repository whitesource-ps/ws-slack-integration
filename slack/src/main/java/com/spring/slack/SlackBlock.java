package com.spring.slack;

import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

public class SlackBlock extends AbstractSlackBlock implements Serializable {

    /* --- Static members --- */

    private static final long serialVersionUID = 5120312925536877772L;

    /* --- Members --- */

    private SlackBlockType type;
    private SlackText text;
    private Collection<SlackElement> elements;

    /* --- Constructor --- */

    public SlackBlock() {
        elements = new LinkedList<>();
    }

    public SlackBlock(SlackBlockType type, SlackText text) {
        this.type = type;
        this.text = text;
    }

    public SlackBlock(SlackBlockType type, Collection<SlackElement> elements) {
        this.type = type;
        this.elements = elements;
    }

    public SlackBlock(SlackBlockType type) {
        this.type = type;
    }

    /* --- Public methods --- */

    public static Collection<SlackBlock> createVulnerabilitiesInitialBlocks(String productName, int totalVulnerabilities) {
        Collection<SlackBlock> blocks = new LinkedList<>();
//        blocks.add(new SlackBlock(SlackBlockType.SECTION, new SlackText(SlackTextType.MRKDWN, ":wss: * " + totalVulnerabilities +
//                " New Vulnerabilities Found in Organization: " + orgName + "* :wss:")));
        Collection<SlackElement> elements = new LinkedList<>();
        elements.add(new SlackElement(SlackTextType.IMAGE, "https://i.imgur.com/LNQxHEj.png", "WhiteSource"));
        elements.add(new SlackElement(SlackTextType.MRKDWN, "*" + totalVulnerabilities + " New Vulnerabilities Found in Product: " + productName + "*"));
        blocks.add(new SlackBlock(SlackBlockType.CONTEXT, elements));
        return blocks;
    }

    public static Collection<SlackBlock> createPoliciesInitialBlocks(int totalRejectedLibraries) {
        Collection<SlackBlock> blocks = new LinkedList<>();
//        blocks.add(new SlackBlock(SlackBlockType.SECTION, new SlackText(SlackTextType.MRKDWN, ":wss: *" + totalRejectedLibraries + " New Policy Violations* :wss:")));
        Collection<SlackElement> elements = new LinkedList<>();
        elements.add(new SlackElement(SlackTextType.IMAGE, "https://i.imgur.com/LNQxHEj.png", "WhiteSource"));
        elements.add(new SlackElement(SlackTextType.MRKDWN, "*" + totalRejectedLibraries + " New Policy Violations*"));
        blocks.add(new SlackBlock(SlackBlockType.CONTEXT, elements));
        return blocks;
    }

    public static SlackBlock createDividerBlock() {
        return new SlackBlock(SlackBlockType.DIVIDER);
    }

    public static SlackBlock createBuildJobBlock(String project, String buildNumber, Collection<String> projectNames) {
        SlackBlock block = new SlackBlock(SlackBlockType.CONTEXT);
        Collection<SlackElement> elements = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        if (CollectionUtils.isNotEmpty(projectNames)) {
            if (projectNames.size() == 1) {
                sb.append(projectNames.iterator().next());
            } else {
                sb.append(projectNames.iterator().next())
                        .append(" and ")
                        .append(projectNames.size() - 1).append(" others");
            }
        } else {
            sb.append(project);
        }
//        String text = "*Project: " + sb.toString() + ", Build #" + buildNumber + "*";
        String projectText = "*Project: " + sb.toString() + "*";
        String buildText = "*Build #" + buildNumber + "*";
        elements.add(new SlackElement(SlackTextType.MRKDWN, projectText));
        elements.add(new SlackElement(SlackTextType.MRKDWN, buildText));
        block.setElements(elements);
//        block.setText(new SlackText(SlackTextType.MRKDWN, text));
        return block;
    }

    /* --- Getters / Setters --- */

    public SlackBlockType getType() {
        return type;
    }

    public void setType(SlackBlockType type) {
        this.type = type;
    }

    public SlackText getText() {
        return text;
    }

    public void setText(SlackText text) {
        this.text = text;
    }

    public Collection<SlackElement> getElements() {
        return elements;
    }

    public void setElements(Collection<SlackElement> elements) {
        this.elements = elements;
    }
}
