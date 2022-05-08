package com.spring.response;

import com.spring.slack.AbstractSlackBlock;

import java.util.Collection;
import java.util.LinkedList;

public class SlackResponse extends BaseResponse {

    /* --- Static members --- */

    private static final long serialVersionUID = -2168120450536136587L;

    /* --- Members --- */

    private Collection<AbstractSlackBlock> blocks;

    /* --- Constructor --- */

    public SlackResponse() {
        blocks = new LinkedList<>();
    }

    /* --- Public methods --- */

    public boolean isNotBlank() {
        return !blocks.isEmpty();
    }

    /* --- Getters / Setters --- */

    public Collection<AbstractSlackBlock> getBlocks() {
        return blocks;
    }

    public void setBlocks(Collection<AbstractSlackBlock> blocks) {
        this.blocks = blocks;
    }
}
