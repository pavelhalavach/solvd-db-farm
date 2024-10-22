package com.solvd.laba.xml;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data public class Node {
    private String nameTag;
    private String text;
    private Map<String,String> attributes;
    private List<Node> children;

    public void addAttribute(String key, String value){
        attributes.put(key, value);
    }

    public void addChild(Node node){
        children.add(node);
    }
}
