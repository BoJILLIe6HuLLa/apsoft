package com.apsoft.parser.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class ParagraphNode {
    private String name;
    @JsonIgnore
    private ParagraphNode parent;
    @JsonIgnore
    private int level;
    private List<ParagraphNode> subNodes =  new ArrayList<>();

    public ParagraphNode() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ParagraphNode> getSubNodes() {
        return subNodes;
    }

    public void setSubNodes(List<ParagraphNode> subNodes) {
        this.subNodes = subNodes;
    }

    public ParagraphNode getParent() {
        return parent;
    }

    public void setParent(ParagraphNode parent) {
        this.parent = parent;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
