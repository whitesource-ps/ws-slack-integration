package com.spring.slack;

import java.io.Serializable;
import java.util.Collection;

public class SlackBlockId extends AbstractSlackBlock implements Serializable {

    /* --- Static members --- */

    private static final long serialVersionUID = 3090884258789611770L;

    /* --- Members --- */

    private String block_id;
    private Collection<SlackButtonElement> elements;
    private SlackBlockType type;

    /* --- Constructor --- */

    public SlackBlockId() {
    }

    public SlackBlockId(String block_id, Collection<SlackButtonElement> elements, SlackBlockType type) {
        this.block_id = block_id;
        this.elements = elements;
        this.type = type;
    }

    /* --- Getters / Setters --- */

    public String getBlock_id() {
        return block_id;
    }

    public void setBlock_id(String block_id) {
        this.block_id = block_id;
    }

    public Collection<SlackButtonElement> getElements() {
        return elements;
    }

    public void setElements(Collection<SlackButtonElement> elements) {
        this.elements = elements;
    }

    public SlackBlockType getType() {
        return type;
    }

    public void setType(SlackBlockType type) {
        this.type = type;
    }
}
